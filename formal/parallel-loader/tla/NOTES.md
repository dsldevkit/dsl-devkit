# ParallelLoader: TLA+ model notes

The model covers `ParallelResourceLoader.ParallelLoadOperation` (PRL) and the cluster loop in
`MonitoredClusteringBuilderState` (MCBS) that consumes it. Everything was checked with TLC 2.19.

- PRL: `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/resourceloader/ParallelResourceLoader.java`
- MCBS: `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/MonitoredClusteringBuilderState.java`

## Files

| File | Lines | What |
|---|---|---|
| `ParallelLoader.tla` | 306 (about 214 non-comment) | Model of the code as written |
| `ParallelLoaderFixed.tla` | 314 | Copy with the minimal fixes FIX-1..3 |
| `variants/AblateFix{1,2,3}.tla` | about 314 each | The fixed model with one fix undone, to show each fix is needed |
| `variants/ParallelLoaderPlanted.tla` | 314 | The fixed model with a planted bug: `load()` never increments `toProcess` |
| `ParallelLoader.cfg` | | Default: SynchronousQueue, 1 worker, 2 URIs, all properties |
| `cfg/*.cfg` | | Configurations produced by `gen.sh` (per property, per queue kind, per size) |
| `run.sh`, `trace.sh`, `check_all.sh` | | Run TLC, print a compact trace, rerun the whole matrix (about 45 s) |

## What is modelled

- **Builder thread** (`pc`):
  - inner-loop head, MCBS:531-537;
  - `next()` / `poll`, PRL:212-262;
  - the exception handler with `pollForCancellation`, MCBS:585-613;
  - the end of a cluster: `cancel()`, `queueAffectedResources`, and a new load operation, MCBS:660-669;
  - the `finally` block, MCBS:679-680.
- **Executor threads**: `NWorkers` threads per load operation, so each cluster gets a new executor.
  - Each job runs `loadResource` and ends in `ok` or a captured exception (PRL:307-323).
  - The result is then passed to `publishLoadResult`, which calls `put` (PRL:365-371).
- **Result queue** (`QCap`):
  - `0` is a SynchronousQueue handoff, the production default (`ResourceLoaderProviders.getParallelLoader(n)` passes bufferSize 0);
  - `k` is an `ArrayBlockingQueue(k)`;
  - `Unbounded` (-1) is a `LinkedBlockingQueue`.
- **Counters and flags, exactly as the code updates them**:
  - `toProcess`: `+= size` in `load()`. `--` runs after every `poll()` that returns, and a `poll()` that returns null (a timeout) counts too. It is set to 0 in `cancel()`.
  - `cancel()` calls `shutdownNow()`, which drops the pending jobs and interrupts the executor threads.
- **Environment**:
  - user cancellation, which covers both `subProgress.isCanceled()` and `monitor`;
  - external interrupts of the builder thread, bounded by `MaxBuilderInterrupts`;
  - poll timeouts, which can fire whenever the builder waits and no result is available.
- **Optional (`LoadMaySwallowInterrupt`)**: `doLoadResource` may clear the interrupt flag of an executor thread.
- **Interrupt semantics of the JDK queues**: the model uses the semantics below. I checked them empirically with a probe on JDK 27 (`QProbe.java` in the scratchpad, not committed).
  - With the interrupt flag set, `LinkedBlockingQueue.poll` and `ArrayBlockingQueue.poll` throw even when an item is present.
  - `SynchronousQueue.poll` returns the item if a producer is already waiting, and keeps the flag set. It throws only when no producer is waiting.
  - `put` throws when the flag is set.
- **Fairness**: weak fairness on every builder and worker step, including the timeout step (a poll that keeps finding nothing times out eventually). There is no fairness on cancellation or interrupts.
- **Not modelled**:
  - the order imposed by the Sorter (the model is nondeterministic, which covers every order);
  - the `break` when a URI is in `toBeDeleted` (MCBS:557);
  - a load that ends in an `Error` rather than an `Exception`, which is rethrown at PRL:242 and aborts the build by design;
  - linking and validation internals;
  - the `currentlyProcessedUris` diagnostics.

## Properties

| Name | Kind | Meaning |
|---|---|---|
| `Bookkeeping` | invariant | While the builder is in the cluster loop and the operation is not cancelled, `toProcess` equals the number of results the operation will still deliver (pending jobs, jobs in flight, and items in the queue). |
| `QueueCovered` | invariant | Every URI still in the builder's queue has a result on its way. |
| `AbortOnlyOnCancel` | invariant | `pc = "aborted"` implies the user cancelled. The fixed model also accepts an interrupt of the builder thread, see FIX-2. |
| `BuildCompletes` | liveness | `<>(pc = "done" \/ cancelReq)`: the queue is drained and the loop exits, or the user cancelled. |
| `BuilderStops` | liveness | `<>(pc \in {done, aborted})`: the builder thread leaves the loop at all. |
| `WorkersQuiesce` | liveness | `<>[]` all executor threads are idle, so no thread stays blocked forever. |
| `Witness*` | invariants that should be violated | Reachability checks against vacuity: the build can finish, a second cluster can start, the user can abort, and two jobs can load at once. All four are violated, as intended, in both the original and the fixed model. |

## Findings

### F1: A timeout decrements `toProcess`, so the build aborts spuriously. CONFIRMED.

The counterexample violates `Bookkeeping`, `AbortOnlyOnCancel` and `BuildCompletes`, for every queue kind. The shortest trace (1 worker, URI u1) is:

1. `LoopHead`: the queue is `{u1}` and `toProcess` is 1, so the builder calls `next()` (MCBS:531, 553).
2. `PollTimeout`: `poll(waitTime)` returns null and `toProcess--` still runs (PRL:220-221), so `toProcess` is now 0. **`Bookkeeping` is violated here**: u1 is still pending.
3. `Catch`: a `LoadOperationException(null, TimeoutException)` is thrown (PRL:225-230) and logged as a warning (MCBS:590-593). `changedURI` is null, so u1 stays in the queue (MCBS:603).
4. `LoopHead`: the queue is not empty but `!hasNext()` holds. The builder logs NO_MORE_RESOURCES, calls `cancel()` and throws `OperationCanceledException` (MCBS:531-536). **`AbortOnlyOnCancel` is violated.**

Checking this against the Java code:
- `toProcess--` at PRL:221 sits inside the `try` right after `poll` and runs before the null check at PRL:225.
- Every later `queue.remove` needs a dequeued result, and each dequeued result also decrements `toProcess`.
- So after a single timeout, the queue can never empty before `toProcess` reaches 0. **Any single timeout in a cluster always ends in an `OperationCanceledException`, even though nobody cancelled.**
- The model also finds variants where the late result still arrives and gets processed, and the abort happens anyway.
- In production the trigger is one resource that takes longer than `MAX_WAIT_TIME` (300 s) to load.

**Fix (FIX-1):** decrement only when a result was dequeued, for example `if (result != null) toProcess--;`. After a timeout the builder then polls again.

### F2: An interrupted builder thread spins forever with LinkedBlockingQueue or ArrayBlockingQueue. CONFIRMED at code level; the trigger is external.

The counterexample violates `BuilderStops` and `BuildCompletes` for `QCap` = 1 and `QCap` = Unbounded. It does not affect SynchronousQueue, which is the production default. Lasso trace:

1. The worker loads u1 and puts the result in the queue.
2. `InterruptBuilder`: something sets the interrupt flag of the builder thread.
3. `PollInterrupted`: `poll` throws `InterruptedException` even though an item is available (`lockInterruptibly`). The code:
   - skips `toProcess--`;
   - restores the flag (PRL:222-224);
   - sees that `result == null` and throws the "timeout" `LoadOperationException` (PRL:225-230).
4. `Catch`: `pollForCancellation` sleeps with `sleepUninterruptibly`, which keeps the flag (MCBS:1359-1365). No URI is removed.
5. `LoopHead`: `hasNext()` is still true, so the builder calls `next()` again. The trace goes back to step 3, forever. Each lap takes about 5 s and logs a bogus "didn't return a result after 300000 ms" warning. Only a user cancellation ends it.

Checking this against the Java code:
- Nothing on this path clears the flag. The only restorations of the interrupt flag outside tests are PRL:223 and PRL:369, and the only `.interrupt()` in non-test code targets the watchdog thread (MCBS:683). So the livelock needs an external interrupt of the Eclipse build thread.
- With SynchronousQueue a producer that is already waiting is served despite the flag, so the build still progresses, only slowly.

**Fix (FIX-2):** on `InterruptedException`, restore the flag and throw `OperationCanceledException`, so that an interrupt counts as a cancellation request. `AbortOnlyOnCancel` in the fixed model is widened to match. Another option is to decide explicitly to clear the flag and retry; that is a design choice.

### F3: An executor thread leaks after cancellation if a load swallows the interrupt. CONFIRMED, conditional.

The counterexample violates `WorkersQuiesce`. With SynchronousQueue it happens whenever a load is in flight at cancel time. With `ArrayBlockingQueue` it happens when the queue is full; with `LinkedBlockingQueue` it never happens. Trace (SynchronousQueue, 1 worker):

1. The worker starts loading u1.
2. The user cancels.
3. `LoopHead` calls `cancel()` and `shutdownNow()` interrupts the worker (PRL:289-291).
4. `doLoadResource` finishes and has cleared the interrupt flag.
5. `put()` on the dead operation's queue blocks forever: no consumer is left, and no second interrupt ever comes (PRL:367).

Checking this against the Java code:
- The code relies only on the interrupt from `shutdownNow` to unblock `put`.
- Whether the load path can swallow an interrupt depends on the language implementation (Xtext, EMF, or a DSL's own code). DDK code alone cannot prove it either way.
- If the load does not swallow the interrupt, the model shows no leak.
- Cancellation also happens at every cluster end (MCBS:661). That is harmless in the modelled paths, but the unmodelled `break` at MCBS:558 can leave loads in flight at that point.

**Fix (FIX-3):** stop publishing once the operation has been cancelled, using a volatile `cancelled` flag and a timed `offer` loop instead of an unbounded `put`.

### Not violated

- **`QueueCovered`** holds on every queue kind. No result is lost before `cancel()`, so the queue and the in-flight work stay consistent. Only the counter drifts.
- **Without timeouts, interrupts or swallowed interrupts,** the original model satisfies `BuilderStops` and `WorkersQuiesce` (the `live_noint_*` configurations).

## Sanity checks (the model has teeth)

- **`ParallelLoaderFixed`** passes every invariant and every liveness property on all queue kinds:
  - s: 1 worker, 2 URIs, 2 clusters;
  - m: 2 workers, 3 URIs, 2 clusters;
  - l: 3 workers, 4 URIs, 3 clusters;
  - xl: 3 workers, 5 URIs, 3 clusters, ArrayBlockingQueue(2): 37,263 distinct states in 3 s, the largest run.
- **Ablations**: undoing any single fix brings its violation back.
  - Undoing FIX-1 violates `Bookkeeping`.
  - Undoing FIX-2 violates liveness with ArrayBlockingQueue and LinkedBlockingQueue.
  - Undoing FIX-3 violates `WorkersQuiesce` with SynchronousQueue, and with ArrayBlockingQueue in the m configuration.
- **Planted bug** (`toProcess` never incremented): `Bookkeeping` is violated in the initial state. `AbortOnlyOnCancel` is violated in 2 states: the builder aborts immediately.
- **Witness invariants** are all violated, so the good paths are reachable.

## TLC statistics

These come from `check_all.sh`: 14 workers, Apple silicon, JDK 27. Every run finishes in 3 s or less, and the whole matrix takes about 45 s wall time.

| Run | Result | Distinct states |
|---|---|---|
| ParallelLoader bk_* | Bookkeeping violated | 23-27 |
| ParallelLoader abort_* | AbortOnlyOnCancel violated | 88-123 |
| ParallelLoader live_int_{arr1,unb} / sync | violated / OK | 296 / 208 |
| ParallelLoader live_swallow_sync, leak_arr1 | WorkersQuiesce violated | 104 / 594 |
| ParallelLoader big_AbortOnlyOnCancel (3w, 4 URIs, 3 clusters) | violated | 28,269 |
| ParallelLoaderFixed full_s / m / l / xl | no error | 170-230 / 1.1k-1.8k / 16k-34k / 37k |

About 11 minutes of wall time in total (11:40 to 11:51), from reading the code to these notes.
