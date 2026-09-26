# BinaryStorage: TLA+ model notes

This model covers binary-model storage in `MonitoredClusteringBuilderState` (MCBS): the storage executor, the shared source-level URI set, and the `ParallelResourceLoader` (PRL) jobs of the current and next cluster. Everything was checked with TLC (`formal/.tools/tla2tools.jar`).

- MCBS: `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/MonitoredClusteringBuilderState.java`
- PRL: `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/resourceloader/ParallelResourceLoader.java`
- DLRSF: `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/resource/persistence/DirectLinkingResourceStorageFacade.java`
- Xtext 2.44 classes read from the source and bytecode jars in `~/.m2`: `SourceLevelURICache`, `SourceLevelURIsAdapter`, `ResourceStorageFacade`, `StorageAwareResource`, `PortableURIs`, `AbstractResourceLoader`.

PRL findings F1-F3 (poll timeout counter, interrupt livelock, worker leak) are known and are not modelled here.

## Files

| File | What |
|---|---|
| `BinaryStorage.tla` | 488 lines, about 380 non-comment. With every `Fix*`/`Plant*` flag FALSE it is the code as written. |
| `MC.tla` | Size definitions S, M, L and R. A cfg file cannot write sequences or functions. |
| `gen.sh`, `run.sh`, `check_all.sh`, `check_all.out` | Generate the cfgs, run TLC, run the whole matrix, and the results of the last run. |
| `trace.py` | Reads TLC output on stdin and prints only the variables that changed at each step. |

Sizes (`Clusters`, `Deps`):

| Size | Clusters | Dependencies |
|---|---|---|
| S | `<<{a},{b}>>` | b→a |
| M | `<<{a1,a2},{b}>>` | a2→a1, b→{a1,a2} |
| L | `<<{a1,a2},{b},{c}>>` | a2→a1, b→a1, c→b |
| R | `<<{a1,a2,a3},{b}>>` | b→a1. With NStore=1 and QCap=1 this fills the executor and reaches CallerRunsPolicy. |

## What is modelled

- **Builder thread**, one step per code step:
  - `next()`/`addResource` and the queue removal (MCBS:553-556);
  - linking loads dependencies into the builder's resource set, and asks `shouldLoadFromStorage`, which reads the sources set (MCBS:569);
  - the outer catch for a failed load (MCBS:595-613) and for a link exception after `addResource` (MCBS:608);
  - `storeBinaryResource` → `ThreadPoolExecutor.execute` (MCBS:716-736). The executor semantics are: a core thread is started while fewer than NStore exist, then the task goes into the bounded queue, and when the queue is full `CallerRunsPolicy` runs the task on the builder thread. After `shutdown`, `CallerRunsPolicy` discards the task silently.
  - `sources.remove(changedURI)` (MCBS:656);
  - `loadOperation.cancel()`, then `queueAffectedResources` with `queueURI` and `sources.add` (MCBS:1296-1298, 1312-1314);
  - the next load operation, `load(queue)` (MCBS:667-670), which starts **before** `clearResourceSet` (MCBS:672-674);
  - `awaitBinaryStorageExecutorTermination` (MCBS:819-874): `shutdown`, `awaitTermination`, then either a timeout or `InterruptedException`. With `retryCount` 0 either one leads to `shutdownNow` (MCBS:877-881), which drops the queued tasks (reported only as a count) and interrupts the workers. A new executor is then created (MCBS:871).
  - clearing the resource set (MCBS:1207); the same await also runs in `finally` (MCBS:684).
- **Storage workers**: `doStoreBinaryResource` (MCBS:746-769), split into:
  - serialise the resource in memory (this reads the resource and its resource set);
  - `fsa.generateFile`: the binary is `partial`, then `new`. If the resource has errors, `deleteStorage` makes the binary `none` (DLRSF:72-76). An interrupted write can throw: the facade then deletes the storage and rethrows, and the error is logged (DLRSF:77-79, MCBS:765);
  - `sources.remove(uri)` (MCBS:754).
- **PRL jobs**, one per queued URI: `localResourceSet.getResource(u, true)` (PRL:344-349 → AbstractResourceLoader).
  - Every `StorageAwareResource.load` asks `shouldLoadFromStorage` = `!sources.contains(uri) && storageExists`.
  - PRL installs the builder's live sources set into each job's resource set without copying it (PRL:168-174).
  - `LoaderLoadsDeps` controls whether loading u also loads u's dependencies. PRL:131-137 unloads the extra resources such loads leave behind, so the code expects them to happen.
- **Shared sources set**: the type is `java.util.HashSet`. The bytecode shows `SourceLevelURICache.<init>` calling `Sets.newHashSet()`. `setSourceLevelUrisWithoutCopy` wraps it in `Collections.unmodifiableSet`, which is a view, not a copy (MCBS:1536, PRL:174). Every access is modelled as two steps, begin and end. A mutation that overlaps any other thread's access is the hazard state.
- **Binary file per URI**: `old` (left by the previous build), `none`, `partial` (being written) or `new`.
- **Environment**: at most `MaxTimeouts` await timeouts or interrupts; loader load failures (`AllowLoadFail`); link exceptions (`AllowLinkFail`).
- **Fairness**: weak fairness on each thread's steps, and none on timeouts.

**Not modelled:** the PRL result queue, counters and cancellation (covered by the earlier model); the order the sorter imposes (the model covers every order); deleted resources (`toBeDeleted`, `deleteBinaryResources`); what a corrupted HashSet actually does (only the overlap is flagged); an Error in a store.

## Properties

| Name | Meaning |
|---|---|
| `NotSourceOnlyWhenStored` (P1) | A URI rebuilt in this build leaves `sources` only after its store has finished, with the binary either `new` or deleted. The store must not be queued, dropped, skipped, or still serialising or writing. |
| `StoresAccounted` (P2a) | At `done`, no store is still queued and none was silently discarded. |
| `StoreResolved` (P2b, liveness) | Every queued or running store eventually ends as `ok`, `failed` (logged) or `dropped` (counted by `shutdownNow`). |
| `LoaderNoPartialRead` (P3) | PRL jobs never open a binary that is still being written. |
| `MainNoPartialRead` (P3') | Same check for the builder thread while it links. |
| `NoStaleRead` (P3'') | Nobody opens the previous build's binary of a resource that is being rebuilt in this build. |
| `SetThreadSafe` (P4) | While one thread mutates the HashSet, no other thread accesses it. Two refinements classify the overlaps: `NoReadDuringWrite` and `NoAddDuringRemove`. |
| `NoDetachedStore` (P6) | A store never serialises a resource that is no longer in the builder's resource set. This is the hazard the comment at MCBS:1203 names. |
| `BuildTerminates`, `WorkersQuiesce` (P5, liveness) | The build finishes, and all storage workers eventually stop running. |
| `Witness*` | Invariants that must be violated. They show that each good path is reachable: the build finishes, a second cluster runs, CallerRuns happens, a timeout happens, a `new` binary is read, and a store completes. |

## Results

The complete output is in `check_all.out`. The whole matrix took about 11 min wall time on this machine, plus 8m45s for `orig_live_M`.

| Run | Result |
|---|---|
| The code as written, S | P1, P3, P3', P3'', P4 and P6 are **violated**. P2a, P2b and P5 pass (62,148 distinct states). |
| The code as written, M, liveness (LoadFail and LinkFail off) | P2b and P5 pass (3,859,440 distinct states, 8m45s). |
| Happy environment, M (no timeouts or failures, loaders do not load dependencies) | P1 and P4 are still **violated**. P3, P3', P3'' and P6 pass (15,188 distinct states). |
| Fixed model (FixA-D), all environment options on | All safety and liveness properties pass: S 4.5k, M 364k, M with NStore=2 561k, L 1.72M (4 min), R 911k distinct states. L with NStore=2 and 2 timeouts (2.75M) was checked for safety only. |
| Fixed model with one fix undone | Undoing A → P1 fails. B → P4 fails. C → P6 fails. D → P6 fails. So every fix is needed. |
| Planted bug (a worker removes the URI from sources before writing) | P1 is caught (154 distinct states). |
| Witnesses | All 6 are violated, as intended, in both the original and the fixed model. |

## Findings

Traces are the BFS-shortest ones from `./run.sh cfg/<name>.cfg | ./trace.py`.

### B1: The builder removes a URI from `sources` before its binary is written. CONFIRMED. Violates P1 and causes B2 and B3.

Trace `orig_P1_nofail` (9 states), with no environment faults needed:

1. The PRL job loads `a`, and `next()` returns it (MCBS:553-556).
2. `Link` (MCBS:569).
3. `Submit`: `storeBinaryResource` hands the task to a storage thread (MCBS:726). The task is now running or queued.
4. `RmSrc`: `getSources().remove(a)` runs **immediately** (MCBS:656). `a` is now "binary-loadable" while its store is still queued or writing.

The worker's own `remove` after `saveResource` (MCBS:753-754) is the correct one. MCBS:656 defeats it.

Checking this against the Java code: MCBS:654 submits the task asynchronously and MCBS:656 runs right after on the builder thread. The same line also removes URIs that were never stored, and that gives a second variant (`orig_NotSourceOnlyWhenStored`, 7 states):
- a PRL load fails, so `LoadOperationException(uri)` sets `changedURI` (MCBS:596);
- `resource` stays null, so no store happens (MCBS:654);
- the URI is still removed from `sources` (MCBS:656), and its **previous build's binary** stays on disk and becomes loadable.

Upstream Xtext never removes URIs from the source set. It reinstalls a copy per cluster (ClusteringBuilderState.installSourceLevelURIs).

### B2: The next cluster's loaders read a binary that is being written, or the stale one. CONFIRMED, given that loading u also loads u's dependencies.

Trace `orig_LoaderNoPartialRead` (19 states):

1. Cluster 1 processes `a`: `Submit` (MCBS:726), then `RmSrc` (MCBS:656), so `sources = {}`.
2. `EndCluster`, then `QA`: `b` is queued and added to `sources` (MCBS:1297-1298).
3. `NewLoad`: `loadOperation.load(queue)` (MCBS:668-669) starts b's PRL job **before** `clearResourceSet` awaits the storage executor (MCBS:673, 1204).
4. `WSer`, then `WWrite`: `fsa.generateFile` for `a` starts, so `binary[a] = partial`.
5. The PRL job loads `b`, which pulls in dependency `a`. `shouldLoadFromStorage(a)`: `a` is not in `sources` and a storage file exists, so the job opens a's **partial** binary.

The M trace `orig_loaderpartial_M` (13 states) shows the same thing **within one cluster**:
- a1's store is writing and a1 has already been removed at MCBS:656;
- a2's PRL job, which is still running concurrently, loads dependency a1 from the partial binary.

`orig_NoStaleRead` (17 states) is the same schedule, but the read happens before the worker starts writing. The job then loads the **previous build's** binary of `a`, even though `a` is being rebuilt. The same happens in the load-failure variant of B1, where a's old binary is never replaced.

Checking this against the Java code: PRL:168/174 share the live set. `StorageAwareResource.load` → `ResourceStorageFacade.shouldLoadFromStorage` → `doesStorageExist`, then `getOrCreateResourceStorageLoadable` opens the file.
- A truncated read throws `IOException`, and `load` then falls back to the source (`clearAndUnload()` + `super.load`). That costs time but gives the right result.
- A `RuntimeIOException` escapes and fails the load.
- A read of the old binary is silently wrong: the resource is linked against the previous version of a resource that is being rebuilt.

The trigger needs a PRL load of u to also load another resource. PRL:131-137 exists precisely to unload such extra resources, so this does happen in practice. How often depends on the language: derived state or inference that touches other resources during load.

Removing MCBS:656 (FixA) closes both the partial and the stale window. Ordering the await before `load(queue)` would only close the next-cluster case, not the within-cluster one.

### B3: After an await timeout or interrupt, the builder reads a partial binary and clears resources that are still being serialised. CONFIRMED. Violates P3' and P6.

Trace `orig_mainpartial` (26 states):

1. `a` is stored asynchronously and removed from `sources` (MCBS:726, 656).
2. `clearResourceSet` → await: `shutdown` (MCBS:826). `AwTimeout` covers `awaitTermination` returning false after 1 min (with `retryCount` 0 the loop exits), or an `InterruptedException`. Both lead to `shutdownNow` (MCBS:837-867, 879). The running task is **not** stopped: an interrupt does not abort in-memory serialisation.
3. `AwDone`: a new executor is created (MCBS:871) and the resource set is cleared (MCBS:1207), while the old worker is still serialising `a`. This is `orig_NoDetachedStore` (19 states, P6).
4. Cluster 2: linking `b` loads `a`. `a` is not in `sources`, and `WWrite` has just made a's binary `partial`, so the builder opens the **partial** binary.

Checking this against the Java code:
- MCBS:1203 says "this is important as otherwise the resources would unexpectedly become detached from the resource set". The timeout path breaks exactly that guarantee.
- Serialising a detached or cleared resource reaches `PortableURIs.toPortableURI`, which calls `sourceResource.getResourceSet().getEObject(...)` (PortableURIs:183). With the resource set null or cleared, this throws a `NullPointerException` or returns null.
- DLRSF:77-79 then deletes the storage and the failure is logged at MCBS:765. That is benign. The remaining risk is a binary with non-portable references written from a half-cleared set.

The trigger is a store batch that takes more than 1 minute, or an interrupted build thread. Separately, the catch at MCBS:865-867 swallows `InterruptedException` without re-interrupting the thread.

### B4: Linking throws, and the resource is removed from the set but still stored. CONFIRMED, low severity. Violates P6.

Trace `orig_NoDetachedStore` with `AllowLinkFail` (8 states):
1. An exception is thrown after `addResource`, and the outer catch runs `resourceSet.getResources().remove(resource)` (MCBS:608).
2. Execution falls through to `storeBinaryResource(resource, …)` (MCBS:654).
3. The worker serialises a resource whose `getResourceSet()` is null.

Checking this against the Java code: `resource` is non-null on this path and nothing guards MCBS:654. The outcome is the same as the detached case in B3, most likely an NPE that is logged and the storage deleted. The practical impact is mostly log noise, plus the deletion of a binary that was valid.

### B5: The HashSet is mutated concurrently by up to three threads without synchronisation. CONFIRMED. Violates P4, even in the happy environment.

Traces:
- `orig_SetThreadSafe` (11 states): the builder's `remove(a)` (MCBS:656) overlaps the worker's `remove(a)` (MCBS:754).
- `orig_adr` (15 states): the builder's `sources.add(b)` in `queueAffectedResources` (MCBS:1298) overlaps the worker's `remove(a)` (MCBS:754).
- `orig_rdw` (19 states): a PRL job's `contains()`, reached through `shouldLoadFromStorage`, overlaps the worker's `remove` (MCBS:754).

Checking this against the Java code:
- The set is a plain `HashSet` (bytecode of `SourceLevelURICache.<init>`), shared without copying with the storage threads and every PRL job thread (MCBS:1536, PRL:168-174).
- `update` is `synchronized`, but only the builder thread takes that lock. No other lock is involved.
- Under the Java memory model these are data races. With `HashMap` they can lose an `add`: a queued URI is then missing from `sources`, and its stale binary is loaded instead of the source. That is the bad direction. They can also lose a `remove` (harmless), make `size` wrong, or let a `contains()` miss an entry during a resize.

The model only flags the overlap. It does not simulate how the `HashMap` gets corrupted.

### P2 (stores accounted) and P5 (termination) hold.

- `CallerRunsPolicy` never throws. A discard after `shutdown` is unreachable, because the builder never submits between MCBS:826 and MCBS:871. So the `RejectedExecutionException` catch at MCBS:727-734 is dead code in practice.
- Tasks dropped by `shutdownNow` are reported only as a count, with no URIs.
- Orphaned running tasks finish, under fairness.

## Minimal fixes in the fixed model

| Fix | Change | What it resolves |
|---|---|---|
| A | Delete MCBS:656. MCBS:754 already removes the URI once the binary is saved; on the `CallerRunsPolicy` path the builder does this itself. | B1, B2 |
| B | Make the sources set thread-safe, for example by installing `ConcurrentHashMap.newKeySet()` contents via the adapter. `SourceLevelURICache` itself is Xtext-owned. Alternatively, confine all mutations to the builder thread. | B5 |
| C | After `shutdownNow`, keep waiting until the running tasks have finished before clearing or continuing. The trade-off: a truly hung store now blocks the build. | B3 |
| D | Skip `storeBinaryResource` when the outer catch ran, for example by setting `resource = null` after MCBS:608. | B4 |

## Wall time

About 40 min of modelling and checking (14:29 to 15:01 for the model and the matrix), plus the 8m45s liveness rerun at size M.
