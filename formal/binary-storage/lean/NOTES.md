# Binary-model storage: Lean model notes

Subject: `MonitoredClusteringBuilderState` (MCBS), `com.avaloq.tools.ddk.xtext.builder`, its binary
storage executor, and the shared `SourceLevelURICache.sources` set, together with the DDK
`ParallelResourceLoader` (PRL) and Xtext 2.44 `ResourceStorageFacade` (RSF), `StorageAwareResource`
(SAR), `SourceLevelURIsAdapter`, and DDK `DirectLinkingResourceStorageFacade` (DLRSF).

Build: `lake build` (toolchain `leanprover/lean4:v4.35.0-rc2`, no dependencies, no downloads).
A clean build takes about 8 s. Lean size: 617 lines (Model 338, Check 103, Proof 120, Results 52).
Wall time for the whole exercise: about 14 minutes.

## Facts established from the code

| Fact | Where |
|---|---|
| `sources` is a plain `HashSet` (not thread-safe) | Xtext `SourceLevelURICache:14` (`Sets.newHashSet()`) |
| The main resource set's adapter wraps that same set without a copy | MCBS:1536 → `SourceLevelURIsAdapter.setSourceLevelUrisWithoutCopy` |
| Every loader thread's local resource set gets the same view | PRL:168, PRL:174 |
| Every load asks `sources.contains(uri)`. If the URI is absent and a binary exists, it loads the binary | SAR:82-88 → RSF:49-54 (`doesStorageExist`); DLRSF:58-63 only adds the SKIP mode |
| Writers of `sources`: main thread `remove` right after submitting the store | MCBS:656 |
| Writers of `sources`: each storage worker `remove`s after `saveResource` (up to 4 at once) | MCBS:754 |
| Writers of `sources`: main thread `add` in `queueAffectedResources` | MCBS:1296 |
| The executor is fixed (4 threads, 15 000 queue slots) with `CallerRunsPolicy` | MCBS:181-192 |
| `awaitBinaryStorageExecutorTermination()` waits 1 minute with 0 retries. On timeout or interrupt it calls `shutdownNow()`, logs only a count, does not wait for running tasks, and replaces the executor | MCBS:803-872, MCBS:877-881 |
| The next cluster's loaders start before storage is awaited: `load(queue)` at L668-669 comes before `clearResourceSet` → await at L673/L1204 | MCBS:668-673, MCBS:1204 |
| `saveResource` swallows `IOException` from `writeResource` and leaves the old file in place. DLRSF deletes only on a thrown exception or when the resource has errors | RSF:95-104, DLRSF:70-80 |
| Nothing deletes the old binary of a URI that is being rebuilt. Only `toBeDeleted` URIs are cleaned | MCBS:452-453, MCBS:777-787 |

## Model (`BinaryStorage/Model.lean`)

The model covers one build with three URIs. Cluster 1 is `a` and `c`, both initial sources.
Cluster 2 is `b`: it is affected by `a`, becomes a source at L1296, and linking it resolves `a`.
Threads:
- the main builder thread, running the doUpdate program L529-690;
- a cluster-1 loader and a cluster-2 loader (PRL);
- the storage workers. Each executor generation has `nW` workers, and the model has 3 generations
  because MCBS:871 recreates the pool.

What the state tracks:
- per URI: `inSrc`, the binary state (`none | stale | part | fresh`), the store task state,
  whether the resource is in the resource set, and whether it has been loaded;
- the executor: queue, `shut`, generation, and workers, including zombie workers left over from an
  earlier generation;
- the CallerRuns inline job;
- in-flight HashSet operations;
- the hazard flags.

Steps that are not atomic in the code are separate steps in the model:
- `contains`, `add` and `remove` each have a begin step and an end step;
- a store has four phases: serialize, write begins (binary becomes partial), write ends (binary
  becomes fresh), and L754 removal (begin and end).

The executor follows Java semantics:
- core worker threads are created lazily, then tasks queue until the capacity is reached;
- when the queue is full, CallerRuns runs the task on the main thread; after shutdown it silently
  discards it;
- `shutdown` lets queued tasks drain;
- `awaitTermination` either returns true or times out (nondeterministically; this also stands for
  an `InterruptedException`);
- `shutdownNow` drops the queued tasks and interrupts running ones, which keep running;
- an interrupted write fails, and DLRSF then deletes the storage.

Configuration switches:

| Switch | What it enables |
|---|---|
| `timeout` | `awaitTermination` may time out |
| `ioFail` | the IOException in `writeResource` is swallowed |
| `touchDep` | loading `b` in a loader thread also loads `a` into its local set |
| `raceNondet` | racing HashSet operations may miss a key or lose a write. With `false`, races are only flagged |
| `fixed` | applies the candidate fix |
| `plant` | adds a planted bug on top of the fix |

Candidate fix (`fixed := true`), not applied to the Java:
1. Remove from `sources` only after the binary is completely written: drop L656 and keep L754.
2. Make `sources` a concurrent set.
3. Await storage termination before starting the next cluster's loaders. On timeout, wait for the
   running tasks as well.
4. Delete the outdated binary of any store that did not complete (dropped, IOException, or
   detached resource).

Assumptions and abstractions:
- A loader is modelled as one sequential thread, where the code uses N threads.
- Linking and validation are atomic steps on the main thread.
- The watchdog and cancellation are left out. Findings F1-F3 of `formal/parallel-loader` are not
  re-modelled: loaders always finish before the main thread consumes them.
- `touchDep` assumes a language whose load pulls in other resources. PRL:131-137 exists to handle
  exactly that case.
- A racy `contains` may return a false "absent". This is backed by `HashMap.resize` publishing the
  empty new table before the entries are transferred, and by removals from treeified bins.
- A racy write may be lost.

## Properties

| ID | Statement |
|---|---|
| P1 | A rebuilt URI that is not in sources and has a binary has a complete (`fresh`) binary |
| P2 | When the build returns, every submitted store is done, failed (and logged), or dropped (and reported) |
| P3 | No thread loads a partial binary (P3partial) or an outdated binary of a URI rebuilt in this build (P3stale) |
| P4 | No unsynchronised overlapping access to the `sources` HashSet |
| P5 (pEnd) | When everything has stopped, no rebuilt URI still has an outdated binary on disk |
| Termination | The reachable graph is acyclic (Kahn) and every non-terminal state has a successor |

## Results

All BFS runs reach a fixpoint. The Lean theorems are in `Results.lean` and `Proof.lean`.

| Configuration | States | P1 | P2 | P3partial | P3stale | P4 | P5 | Acyclic, no deadlock |
|---|---|---|---|---|---|---|---|---|
| Code as written, defaults (1 worker, queue 1) | 15 699 | ✗7 | ✗35 | ✗22 | ✗8 | ✗7 | ✗38 | ✓ |
| Code, 2 workers | 70 908 | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ | ✓ |
| Code, `ioFail` | 50 197 | ✗ | ✗ | ✗ | ✗ | ✗ | ✗ | ✓ |
| Code, queue 0 (CallerRuns reachable) | 7 097 | ✗ | ✗ | ✗ | ✗ | ✗ | ✓ | ✓ |
| Code, no timeout, no `touchDep`, no race nondeterminism | 508 | ✗ | ✓ | ✓ | ✓ | ✗ | ✓ | ✓ |
| Fixed (4 configurations, including 2 workers, CallerRuns, ioFail) | 105–240 | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Fixed plus planted bug (removal before the write) | 162 | ✗5 | | | | | | |

In the table, ✗n means the property is violated and the shortest counterexample has n steps.

What is proved and what is only bounded-checked:
- **Proved for all sizes, without `sorry`** (`Proof.lean`, `fixed_safe`): P1 and P3 for the fixed
  protocol.
  - This covers any number of URIs (`Nat → U`), workers, clusters, executor generations and
    interleavings, with the executor abstracted to per-URI lifecycle phases.
  - It uses only the `propext` axiom.
  - `buggy_unsafe` proves that the same statement fails once the L656 removal is added.
- **Bounded, exhaustive, via `native_decide`**: P2, P4, P5, termination and deadlock freedom, and
  every counterexample.

Sanity checks:
- The fixed model passes P1-P5, termination and deadlock freedom in 4 configurations.
- The planted bug is caught by P1 in 5 steps.
- The CallerRuns path is reachable.
- The silent CallerRuns discard is unreachable (no `execute` on a pool that is shut down).

## Findings

Step numbers below refer to the traces printed by `cex`. Loader bookkeeping steps are elided.

### B1: unsynchronised concurrent mutation of the `sources` HashSet (P4). CONFIRMED

Shortest trace, 7 steps:
1. loader1: `contains(a)` (PRL:174 → RSF:51).
2. loader1 publishes `a`.
3. main `next()` and `addResource` (MCBS:537).
4. main `execute(store a)` (MCBS:726).
5. main `sources.remove(a)` begins (MCBS:656).
6. loader1 `contains(c)` begins (RSF:51) while the removal is still in flight.

The same set is also written by up to 4 storage workers at once (MCBS:754), and by the main thread
at MCBS:1296 while workers from the previous cluster are still removing entries. There is no
synchronisation anywhere.

Possible consequences, reproduced in the model with `raceNondet`:
- a spurious "absent" from `contains`, so a source URI is loaded from its old binary (P3stale in
  8 steps);
- a lost `add(b)` at L1296, so `b` is not a source when loader2 loads it, and the builder relinks
  a resource loaded from its binary. `addResource` (MCBS:691-705) exists precisely to avoid that.

These corruption outcomes are plausible under the Java Memory Model and HashMap internals but were
not demonstrated in Java. The data race itself is certain.

### B2: URI becomes binary-loadable before its binary is written (P1). CONFIRMED

MCBS:656 removes `changedURI` from `sources` on the main thread immediately after
`storeBinaryResource` only submitted the task. MCBS:754 does the same removal properly, after
`saveResource`. From this point until the write finishes, any load of the URI takes the binary
path (RSF:51-53). The P1 trace is the first 7 steps of B1.

Taken alone this window is harmless: with no timeout, no dependency loading in loaders and no race
nondeterminism, P1 is violated but P2, P3 and P5 hold. B3-B6 are the paths that make it
observable.

### B3: next cluster's loaders run before storage is awaited, and read partial or outdated binaries (P3). CONFIRMED (ordering); impact depends on the language

MCBS:668-669 starts the loader for cluster N+1. The storage await happens only afterwards, inside
`clearResourceSet` (MCBS:673 → 1204).

Stale-read trace, 21 steps, no timeout and atomic set semantics:
1. main `next(a)`; the store of `a` goes to a new worker (726); `remove(a)` (656).
2. main `next(c)`; the store of `c` is queued (726); `remove(c)` (656).
3. main `add(b)` (1296).
4. main starts loader2 (668-669).
5. loader2 `contains(b)` returns true, so it parses `b`.
6. Loading `b` pulls in `a`: `contains(a)` returns false (removed at 656). Worker0 has not started
   writing yet, so loader2 loads `a`'s **binary from the previous build** (SAR:86).

Partial-read trace, 22 steps: the same interleaving, with worker0 already at `generateFile`
(RSF:104) when loader2 reads.

Impact:
- A truncated binary usually throws `IOException`, and SAR:89-90 then falls back to parsing, which
  mitigates the partial read.
- An outdated but complete binary is accepted silently.
- This needs a language whose load pulls in referenced resources (`touchDep`). With
  `touchDep := false` the loader only ever asks about its own URI, which is still a source.

### B4: after an await timeout, stores are silently lost and the main thread links against outdated binaries (P3stale, P5). CONFIRMED

The default await is 1 minute with 0 retries (MCBS:804). A backlog of more than a minute makes it
fail: for example, 15 000 queue slots with 4 workers only needs about 16 ms per store.
`shutdownNow` (MCBS:879) then drops the queued stores and logs only their count.

Their URIs were already removed from `sources` at L656, so they stay binary-loadable with the old
file on disk. Trace (P3stale on the main thread), 29 steps:
- steps 1-17 as in B3;
- 18: `shutdown` (826);
- 19: `awaitTermination` times out, then `shutdownNow` (837, 879); the store of `c` is dropped;
- 20: the executor is recreated (871);
- 21: `clearResourceSet` (1207);
- 27-29: main `next(b)`, then `resolveLazyCrossReferences` loads `a`: `contains(a)` returns false,
  and the **outdated binary** of `a` is loaded (MCBS:564, SAR:86).

P5 trace, 38 steps: the dropped store of `c` leaves `c`'s old binary on disk. Later builds install
only `toBeUpdated` and queue URIs as sources (MCBS:1524-1536), so every later build loads the
outdated binary until `c` changes again. The problem persists beyond the build.

### B5: running stores are neither completed nor reported when the build returns (P2). CONFIRMED

`terminateBinaryStorageExecutor` never waits after `shutdownNow`. Running tasks keep going as
zombies: they write binaries and mutate `sources`, and nothing awaits them. Meanwhile:
- the main thread clears the resource set they are serializing (MCBS:1207; the comment at MCBS:1203
  names exactly this hazard);
- the main thread starts the next cluster;
- `doUpdate` returns.

Trace, 35 steps: B4 up to step 29; the store of `b` goes to a gen-1 worker (726); `remove(b)`
(656); the finally block's await times out and calls `shutdownNow` (684 → 879); `doUpdate` returns
while `b`'s store is still running. Only queued tasks are counted in "{} tasks not processed"
(MCBS:880). The running ones are not.

### B6: an IOException swallowed by Xtext leaves an outdated binary that is treated as valid (P5). CONFIRMED (code path); trigger is rare

- RSF:97-103 catches the `IOException` from `writeResource`, logs a warning and returns normally
  without touching the old file.
- DLRSF:70-80 deletes storage only when an exception is thrown.
- MCBS:754 then removes the URI from `sources`.

Trace, 45 steps, no timeout and no race: the IOException hits at step 8 for `a` and at step 23 for
`c`. At step 32 loader2 loads `a`'s outdated binary, at step 36 main does the same, and the files
stay outdated after the build.

### Not findings

- **Termination and deadlock freedom:** hold in every configuration (bounded). There are no cycles
  and no stuck states.
- **CallerRunsPolicy:** reachable. A silent discard would need `execute` after `shutdown`, which
  never happens: only the main thread submits stores, and it always recreates the executor right
  after shutting it down.
- **Serialization of a detached resource after a timeout:** modelled as the store failing and
  DLRSF deleting the storage, which is safe. This is a MODEL-SIMPLIFICATION: whether serialization
  of a detached resource succeeds or throws was not checked in the DDK writable.
