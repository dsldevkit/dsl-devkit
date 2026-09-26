# Formal-methods smoke test: ParallelResourceLoader (2026-09-25)

## Question
Does Cherny-style modelling find real bugs in DDK, done blind and with each tool? The modelling agents were never told about the known bug (F1).

## Results
| | TLA+ (TLC 1.7.4) | Lean 4 (v4.35.0-rc2, no Mathlib) |
|---|---|---|
| Found F1 blind | yes | yes |
| New findings | F2, F3 | F2 |
| False alarms | 0 | 0 |
| Model size | 306 lines (+314 for the fixed variant) | 686 lines (Model 212, Check 116, Results 104, Proof 250) |
| Checking | Exhaustive TLC; largest run 37k states in 3 s; the full matrix takes ~45 s | Exhaustive BFS to a fixpoint over 80 configurations (38.5k states); `native_decide` theorems; one inductive **proof** of P1 for all sizes once F1 is fixed |
| Sanity: fixed model passes | yes | yes |
| Sanity: planted bug caught | yes | yes |
| Agent wall time | ~11 min | ~15 min |

PRL = `ParallelResourceLoader.java`; MCBS = `MonitoredClusteringBuilderState.java`.

## Findings
- **F1 (confirmed by a Java test):** a single `poll()` timeout decrements `toProcess` (PRL:220-221). `hasNext()` then returns false while a URI is still queued, and MCBS:531-536 aborts the build with `OperationCanceledException` although nobody cancelled. The default timeout is 300 s, so this happens whenever one resource takes longer than that to load. Upstream Xtext's `ParallelResourceLoader` has the same code.
  - Fix: decrement only when `result != null`.
- **F2 (confirmed by reading the code; needs an external interrupt):** the builder thread is interrupted and the queue is `LinkedBlockingQueue` or `ArrayBlockingQueue`.
  - `poll` throws `InterruptedException` even though a result is waiting. The interrupt flag is restored and a fake timeout is thrown (PRL:222-230).
  - `pollForCancellation` uses `sleepUninterruptibly` (MCBS:1359-1365), so the flag survives.
  - The loop spins every ~5 s until the user cancels. `SynchronousQueue` (queueSize 0) is not affected.
  - Fix: treat an interrupt as a cancel.
- **F3 (TLA+ only; depends on whether a load swallows the interrupt):** after `cancel()`, a worker whose load cleared the interrupt flag blocks forever in `put()` (PRL:367) with `SynchronousQueue` or a full `ArrayBlockingQueue`. The result is a leaked thread.
  - Fix: a volatile `cancelled` flag checked by a timed `offer` loop.

## Java regression test (F1)
`com.avaloq.tools.ddk.xtext.test/.../builder/resourceloader/ParallelResourceLoaderTest`, registered in `XtextTestSuite`.
- **Master:** FAILS as predicted: `hasNext()` is false after the timeout (`ParallelResourceLoaderTest.java:76`).
- **With the one-line fix** (plus the required builder bundle bump 17.3.1 to 17.3.2; saved as `formal/f1-fix.patch`, not applied): PASSES. The other failures are unchanged, and `CheckQuickfixTest.testBulkApplyingQuickfix` failed once, a known flake.
- **Unrelated failures on master in the same local run (JDK 27):** `Export/ScopeExpressionCodeGenerationTest.testNestedArithmetic`, `IssueExpressionGenerationTest.testDynamicMarkerFeature` and `CheckApiAccessValidationsTest.testNonAvaloqTypeAccessable`.

## Takeaways
- Both tools found the known bug blind within about 15 minutes and raised no false alarms. Both also surfaced a new, real livelock (F2).
- TLA+ gives more per line for concurrency: its model is half the size, supports several clusters and liveness out of the box, and found F3 as well.
- Lean's strength is the all-sizes proof that the fix is right (P1), which TLC can't give.
- A practical recipe: TLA+ to find bugs, Lean to prove the fix when the invariant matters.

---

# Round 2: find-references batching (#3) and binary storage executor (#5)

Four blind agents ran in parallel, one per target per tool. None was told about any suspected bug. For each finding, "TLA+" and "Lean" give that finding's number in the tool's own `NOTES.md`; "–" means that tool didn't report it.

## #3 FastReferenceSearchResultContentProvider (FRSRCP)
| Finding | TLA+ | Lean | Verified by me |
|---|---|---|---|
| Lost update: the updater clears `isUIUpdateScheduled` (L220) after its unlocked `isEmpty()` check (L217). A node added in that window is never shown. Upstream Xtext clears the flag *first*. | F1 | F1 | yes (javap of upstream `UIUpdater`) |
| **UI deadlock**: the Reset handler calls `syncExec` (L178) while `fireEvent` holds `listeners`. The UI thread switching to another search calls `removeListener` (L113) on the same lock. | F2 | F2 | yes (javap: `fireEvent` and `removeListener` both lock `listeners`) |
| A search's node appears in another search's view: `inputChanged` clears `rootNodes` (L111) before `removeListener` (L113). | F3 | F3 | code |
| Duplicate root / lost references: unguarded get-then-put in `resourceNode` (L158-161). | F4 | F5 | code |
| Duplicate reference row | F5 | (in F5) | code |
| **CME in `inputChanged`**: iterates the live `getMatchingReferences()` list (L118) while the search thread appends to it. | F6 | F4 | **Java test** |

Held: nothing from before a Reset is shown, and the viewer shows one root per URI.

## #5 Binary storage executor (MonitoredClusteringBuilderState = MCBS)
| Finding | TLA+ | Lean | Verified by me |
|---|---|---|---|
| Premature removal: MCBS:656 removes the URI from `sources` right after submitting the store, before the binary exists. The worker's own removal at L754 is the correct one. The load-failure path also removes the URI with no store at all. | B1 | B2 | code |
| Next cluster's loaders start (L668-669) before `clearResourceSet` awaits storage (L673→1204). They can read a half-written or stale binary, which requires the load to pull in dependencies. | B2 | B3 | code |
| After a timeout or interrupt → `shutdownNow`: running stores are neither awaited nor reported, and dropped stores have already left `sources`. Stale binaries get used and persist. Stores run on a detached resource set. | B3 | B4+B5 | code |
| **Data race**: `sources` is a plain `HashSet` (Xtext `SourceLevelURICache`) mutated by the builder thread, up to 4 storage workers and loader threads. | B5 | B1 | yes (javap) |
| Link exception → the resource is detached (L608) but still stored (L654), so its valid binary gets deleted. | B4 | – | yes (code) |
| A swallowed IOException keeps the old binary, yet the URI leaves `sources` (rare trigger). | – | B6 | code path exists |

Held: every submitted store is either completed or dropped by `shutdownNow` (dropped ones only as a count, no URIs), and the build terminates. The `RejectedExecutionException` catch at L727 is effectively dead code.

## Java tests (round 2)
- **`FastReferenceSearchResultContentProviderTest`** (xtext.ui.test, in `XtextUiTestSuite`):
  - Master: FAILS with `ConcurrentModificationException`, as predicted.
  - With the snapshot fix (`formal/find-refs/fix-cme.patch` plus the xtext.ui bundle bump 17.3.3 to 17.3.4): PASSES.
  - Other failures are unchanged: the 4 known local JDK-27 failures.
- **Lint:** checkstyle + PMD report BUILD SUCCESS.
- **No other Java tests:**
  - The #3 lost update and deadlock need either a seam in production code or a timing-based test. The deadlock is confirmed from bytecode.
  - #5 needs a builder-state harness. Its findings are confirmed by the models and by reading the code, and B5 also from bytecode.

## Round 2 numbers
| | refs TLA+ | refs Lean | storage TLA+ | storage Lean |
|---|---|---|---|---|
| Model size | 483 lines | 723 | 488 | 617 |
| Largest check | 50.6M states (fixed) | 1.89M (fixed), fixpoint | 3.86M (liveness at size M, with load/link failures off) | ~71k, fixpoint |
| All-sizes proof | – | `no_lost_root` (fixed) | – | P1+P3 (fixed) |
| Fixed model passes / planted bug caught / fixes each necessary | ✅ / ✅ / ✅ | ✅ / ✅ / ✅ | ✅ / ✅ / ✅ | ✅ / ✅ / – |
| False alarms | 0 | 1 (caught and fixed in the model's bookkeeping) | 0 | 0 |
| Agent time | ~25 min (+400 s large run) | ~27 min | ~50 min (plus a ~4 h stall waiting for a lost notification) | ~14 min |

## Takeaways
- On two targets with no known bugs, the approach found **11 distinct real issues**. The two tools agreed on the core ones, and each tool contributed one unique finding on #5.
- **Most severe:**
  - the find-references UI deadlock (the workbench freezes);
  - the #5 premature `sources` removal combined with the `HashSet` data race, which can make builds silently link against stale binaries.
- **Tool split holds:** TLA+ scales further (tens of millions of states); Lean proves the fixed invariant for all sizes.
- **Suggested fix PRs later, in order:**
  1. find-references (deadlock + lost update + CME);
  2. binary storage (delete MCBS:656, make `sources` thread-safe, skip the store after the outer catch);
  3. the round-1 loader F1/F2.
