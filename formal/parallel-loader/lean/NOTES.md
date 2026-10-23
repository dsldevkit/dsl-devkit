# ParallelLoadOperation / cluster loop: Lean 4 model

Toolchain `leanprover/lean4:v4.35.0-rc2`, no dependencies (core `Std` only). Build: `lake build`
(counterexample traces and the result matrix are printed as `info:` messages during the build;
`lake env lean ParallelLoader/Results.lean` prints the same output).

Abbreviations: PRL = `ParallelResourceLoader.java`, MCBS = `MonitoredClusteringBuilderState.java`.

## Size

| File | Lines | Content |
|---|---|---|
| `ParallelLoader/Model.lean` | 212 | state, step relation (`succ`), P1/P2 |
| `ParallelLoader/Check.lean` | 116 | BFS explorer, shortest traces, backward fixpoint for P3 |
| `ParallelLoader/Results.lean` | 104 | configs, `#eval` output, `native_decide` and `decide` theorems |
| `ParallelLoader/Proof.lean` | 250 | inductive-invariant proof of P1 for the fixed model |
| total | 686 | |

## Model

The state holds: a per-URI job state (`pending | loading | putting | queued | consumed`), the result queue
(FIFO), `toProcess`, the builder's URI `queue` membership, the builder pc (`head | poll | done | aborted`),
the builder thread's interrupt flag, the user-cancel flag, and the `CheckedLoadOperation`/executor cancelled flag.
- Workers: at most `threads` jobs are active (a thread blocked in `put` still counts), and jobs start in FIFO order.
- Queues: `LinkedBlockingQueue` (-1), `SynchronousQueue` (0, hand-off only from a blocked putter) and
  `ArrayBlockingQueue(k)`. Interrupt semantics follow the JDK 21 `poll(timeout)` sources:
  Array/Linked call `lockInterruptibly` first, so they throw even when an item is available. Sync matches a
  waiting producer before it checks the interrupt.
- Builder: MCBS:531 check, then `next()` (PRL:212-262), with the exact counter updates (PRL:220-223).
  The catch at MCBS:585-605 is included: `pollForCancellation` aborts only if cancel is set, and a timeout
  leaves `queue` untouched because `changedURI == null`.
- Environment: user cancel at any time, a bounded number of builder-thread interrupts and a bounded number of
  poll timeouts (a timeout can happen only while no result is available).
- Out of scope: follow-up clusters (`queueAffectedResources`), the `toBeDeleted` break, `Error` rethrow and
  duplicate URIs.

## Properties

- **P1 bookkeeping.** While the operation is live, `toProcess` equals the number of results not yet handed
  to the builder.
- **P2 abort only on request.** `pc = aborted` requires a user cancel, or (lenient) a delivered interrupt.
- **P3 termination.** From every reachable state, `done` or `aborted` can be reached using system steps only.
  Those steps exclude user cancel, new interrupts and timeouts, so "the user eventually clicks cancel" does
  not count as termination.

## How each result was checked

- **Exhaustive search.** Every configuration's state space is finite, and BFS reached a fixpoint
  (`complete=true`, depth bound 200 never hit). So each result is exhaustive for its instance size, not
  merely depth-bounded. The search covered 80 configurations: {clean, T, I, all} x 5 variants x 4 queue kinds,
  with n <= 3 URIs, 2 threads, <= 2 timeouts and <= 1 interrupt. It explored 38,550 states in total; the
  largest configuration had 2,367 states. A full clean `lake build` takes about 7 s.
- **Theorems in `Results.lean` using `native_decide`** (they trust the compiler, `Lean.ofReduceBool`):
  `original_timeout_violates_P1/P2(_all_queues)`, `original_interrupt_violates_P3`,
  `original_interrupt_sync_ok`, `original_clean_ok`, `fixBoth_ok`, `fixTimeout_alone_still_livelocks`,
  `fixInterrupt_alone_still_aborts` and `planted_caught`.
- **Theorems checked by the kernel with plain `decide`:**
  - `timeout_trace_kernel` replays the 3-step abort trace.
  - `livelock_trace_kernel` shows the interrupted builder returns to the identical state via
    callNext + pollInterrupted, and that these are its only system moves.
- **Real inductive proof (`Proof.lean`).** `P1_fixTimeout` states that for every `Cfg` whose variant has the
  timeout fix (any n, threads, queue kind, timeout and interrupt budgets), P1 holds in every reachable state.
  The invariant is `toProcess = #non-consumed jobs`, the result queue is duplicate-free, and it contains
  exactly the `queued` jobs. Axioms used: `propext`, `Classical.choice`, `Quot.sound` (no `sorry`).
- P2 and P3 for the fixed model are checked by bounded exhaustive search only.

## Findings

### F1: a poll timeout makes the build abort as "cancelled" (CONFIRMED)

Shortest trace (n=1, any queue kind):

1. `callNext`: MCBS:531 passes (`toProcess=1`); MCBS:553 calls `next()`.
2. `pollTimeout`: PRL:220 `poll` returns null after `waitTime`. PRL:221 runs `toProcess--` anyway (it sits
   after `poll` in the same try block). PRL:225-230 throws `LoadOperationException(null, TimeoutException)`.
   MCBS:585-593 catches it, and `pollForCancellation` finds no cancel. `changedURI == null`, so `queue` still
   holds the URI (MCBS:603).
3. `abort`: MCBS:531 sees `!hasNext()` (`toProcess=0`) while `queue` is non-empty. It logs
   "No more resources left to load.", calls `cancel()` and throws `OperationCanceledException` (MCBS:532-536).

For general n, one timeout leaves `toProcess = outstanding - 1`. After the other results are consumed, the
builder aborts with one URI still queued; its result may be sitting unread in `resourceQueue`. One timeout
therefore always ends the build with `OperationCanceledException` without a user cancel. The only exception
is the `toBeDeleted` break, which is outside the model.

Checked against Java: `LoadOperationException extends WrappedException` (a RuntimeException), so
`catch (Exception)` catches it. `getSavedResourceDescription(.., null)` returns null, and
`storeBinaryResource(null, ..)` is a no-op. Nothing else touches `toProcess`.

Minimal fix (variant `fixTimeout`): at PRL:221, decrement only when `result != null`. With this fix, P1 is
proved for all sizes; P2 holds in every bounded configuration, and P3 holds whenever no interrupt is injected.

### F2: an interrupt on the builder thread livelocks the cluster loop (CONFIRMED in code; needs an external interrupt)

Shortest trace (n=2, LinkedBlockingQueue or ArrayBlockingQueue):

1. `interrupt`: `Thread.interrupt()` on the builder thread.
2. `callNext`, then `pollInterrupted`. At PRL:220, `poll` → `lockInterruptibly` throws
   `InterruptedException` at once (JDK 21), so `toProcess` is not decremented. PRL:223 re-sets the flag, and
   PRL:225-230 throws the "timeout" `LoadOperationException`. MCBS:587 `pollForCancellation` sleeps 5 s with
   `sleepUninterruptibly`, which restores the flag. With no cancel, MCBS:603 leaves `queue` unchanged.
3. Back at MCBS:531, `toProcess > 0`, so step 2 repeats forever. The loop logs one warning every ~5 s and makes
   no progress until the user cancels. Workers fill the queue and block.

The kernel-checked witness `livelock_trace_kernel` shows the builder returning to the identical state. The
fault does not occur with `queueSize == 0` (`SynchronousQueue`), because `xfer` hands over a blocked
producer's item before it checks the interrupt.

Checked against Java: in production code, only the builder thread's own PRL:223 interrupts it, so the trigger
must be external or a stale flag, for example a job framework, a test harness or a third-party plugin. The
code path itself is real.

Minimal fix (variant `fixInterrupt`): also leave the loop at MCBS:531 when
`Thread.currentThread().isInterrupted()` is set. The alternative is to throw `OperationCanceledException`
from `next()` and not swallow it in MCBS:585, since OCE is a RuntimeException and `catch (Exception)`
catches it today.

### Fix combinations

`fixBoth` satisfies P1, P2 and P3 for all 4 queue kinds under the full failure model (`fixBoth_ok`). Neither
fix is enough alone (`fixTimeout_alone_still_livelocks`, `fixInterrupt_alone_still_aborts`). With no injected
failures, the original code satisfies all three properties (`original_clean_ok`).

### Sanity: planted bug

In `plantedNoIncrement`, PRL:272 never adds to `toProcess`. The check catches it for every queue kind with
zero injected failures (P1 and P2 both violated, `planted_caught`); the trace is `init` → `abort` at MCBS:531.

## Wall time

About 15 minutes: reading the code, building the model and search, and writing the proof.
