# Formal-verification campaign: defect catalogue and fix plans

This file catalogues the defects found by the formal-verification spike in DDK, with a verification record and a fix plan for each. It accompanies a reference draft PR that is not meant to be merged as a whole: the fixes are meant to land as the small, self-contained PRs in [section 5](#5-proposed-fix-pr-sequence).

## How to reproduce

- **Models:** `formal/check.sh` runs every TLC matrix against its golden verdicts, builds the Lean projects, checks axioms and runs the orphan-test check. `--full` adds the slow TLC runs; `--only=<target>` (`parallel-loader`, `binary-storage`, `find-refs`, `trie`, `pipeline`, `orphans`) restricts it. Per-model details are in each `formal/<model>/{tla,lean}/NOTES.md`; the campaign log is `formal/REPORT.md`.
- **Failing-first Java tests:** this PR registers them in `XtextTestSuite`/`XtextUiTestSuite`, with every method that fails on master annotated `@Disabled("Documents <ID>…")` so CI stays green. To see one fail, delete its `@Disabled` and run the aggregator: `mvn verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.test` (on Linux, wrap the command in `xvfb-run`). Do not rely on Tycho `-Dtest=…` to select a single test, because it can silently match nothing.

## 1. Method and legend

1. **Blind modelling.** Each component was modelled independently in TLA+ (TLC) and Lean 4 (kernel-checked proofs plus bounded search), without being told about suspected bugs. The release pipeline was modelled the same way. Read-only code passes (`ro-*` agents) covered the formatter comparator, test wiring and a trie self-match.
2. **Counterexample → finding.** Each violation was replayed against the Java source and turned into a finding with a trace and `file:line` locations.
3. **Skeptics.** Three independent skeptics tried to refute each finding or narrow it. **Phase A** verified the TRIE, PIPE and RO findings per originating agent, before cataloguing. **Phase B** verified LDR-2/3, REF-1/3–9, STO-1–8 and TRIE-16 after cataloguing, and recorded per skeptic whether the claim was overstated and a suggested severity. **Phase C** (a later sweep) put LDR-1 and REF-2, already confirmed by a failing Java test, and the 9 observations in [4.3](#43-verified-observations) through the same three skeptics, recording the same fields.
4. **Failing-first Java test**, where the interleaving could be made deterministic.
5. **Fix plans.** REF and STO each got two competing plans (A and B) and a judge. LDR, TRIE, PIPE and RO each got a single plan, so there is no judge record.

Every finding records **Verification** (votes) and **Origin** (the agent, its finding ID and its own verdict).

**Editorial rules**

1. **Status.** CONFIRMED = a failing-first Java test reproduces it, or at least 2 of 3 skeptics upheld it. PLAUSIBLE = 1 of 3 upheld. REFUTED = 0 of 3 upheld. A finding whose originating verdict was PLAUSIBLE but which 3/3 skeptics upheld is CONFIRMED and marked "(promoted: originating verdict PLAUSIBLE, 3/3 upheld)": TRIE-11, TRIE-16, PIPE-6, PIPE-7, RO-6. A finding raised as an observation with no verdict takes its status from the votes alone (REF-7, REF-8, REF-9, STO-8).
2. **Severity** is the user-visible impact reachable in DDK or in its typical downstream consumers. **high** = wrong results, or an aborted, hung or blocked build or release, on a path DDK or a typical consumer reaches. **medium** = a reachable race or inconsistency whose impact is recoverable, or a high-class impact behind a narrow or downstream-only trigger. **low** = narrow trigger, cosmetic, latent (no production caller), or test/CI hygiene with no shipped-code impact. When skeptics called a claim overstated, the finding records the narrowed claim, and the severity is lowered if the narrowing reduces reachability. Binary-model storage is disabled on DDK's own target platforms (`DefaultXtextTargetPlatform:81` and `NullXtextTargetPlatform:74` return a null `IBinaryModelStore`, so `MCBS:1547` leaves storage off), so STO impact is downstream-only and STO severities are capped at medium. A finding confirmed by a failing Java test on master is never rated below a finding with the same impact and no test.
3. **Observations.** Adjacent issues raised by judges, planners or skeptics outside the original findings get a stable ID (`<cluster>-A<n>`) and are listed in [4.3](#43-verified-observations). They went through phase C and carry a status and severity under rules 1 and 2, but are counted separately from the 51 catalogued findings. Fix plans reference them as "optional".

**Abbreviations**

| Short | Path |
|---|---|
| `PRL` | `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/resourceloader/ParallelResourceLoader.java` |
| `MCBS` | `com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/xtext/builder/MonitoredClusteringBuilderState.java` |
| `FRSRCP` | `com.avaloq.tools.ddk.xtext.ui/src/com/avaloq/tools/ddk/xtext/ui/editor/findrefs/FastReferenceSearchResultContentProvider.java` |
| `QNP` | `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/naming/QualifiedNamePattern.java` |
| `QNSTL` | `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/naming/QualifiedNameSegmentTreeLookup.java` |
| `PAEDL` | `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/resource/PatternAwareEObjectDescriptionLookUp.java` |
| `EFCBS` | `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/formatting/ExtendedFormattingConfigBasedStream.java` |
| `RSF` / `DLRSF` | Xtext `ResourceStorageFacade` / DDK `DirectLinkingResourceStorageFacade` |
| `QNLFT` | `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/naming/QualifiedNameLookupFormalTest.java` |
| `PAEDLT` | `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/resource/PatternAwareEObjectDescriptionLookUpTest.java` |
| `SLT` | `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/formatting/ExtendedFormattingConfigBasedStreamSortLocatorsTest.java` |
| `PRLT` | `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/builder/resourceloader/ParallelResourceLoaderTest.java` |
| `FRSRCPT` | `com.avaloq.tools.ddk.xtext.ui.test/src/com/avaloq/tools/ddk/xtext/ui/editor/findrefs/FastReferenceSearchResultContentProviderTest.java` |

## 2. Summary

Test column: **disabled: X** = a test in this PR that fails on master, carried `@Disabled` (see [How to reproduce](#how-to-reproduce)). **script** = `formal/readonly/orphans/check-test-reachability.sh`. **proposed** = no test yet; the fix plan names the test to add. **(javac)** = red-on-master and green-with-fix were shown with the javac harness in `formal/trie/lean/java-check`; confirmation under the Tycho aggregator is still pending. LDR-1 and REF-2 were shown red under the aggregator.

| ID | Sev | Status | Title | Test | Fix PR |
|---|---|---|---|---|---|
| LDR-1 | medium | CONFIRMED | Poll timeout decrements `toProcess`; build aborts as cancelled | disabled: `PRLT` (enabled in [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553)) | [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553) (draft) |
| LDR-2 | low | CONFIRMED | Interrupted builder livelocks the cluster loop (buffered queues) | proposed | LDR-PR2 |
| LDR-3 | low | CONFIRMED | Worker thread leaks in `put()` after `cancel()` if a load swallows the interrupt | proposed | LDR-PR3 |
| REF-1 | medium | CONFIRMED | UI deadlock: Reset handler `syncExec`s while holding the listeners monitor | proposed | REF-PR5 |
| REF-2 | low | CONFIRMED | `ConcurrentModificationException` in `inputChanged` during a running search | disabled: `FRSRCPT` (enabled in [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552)) | [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552) (draft) |
| REF-3 | medium | CONFIRMED | Lost update: UIUpdater clears the scheduled flag after its `isEmpty()` check | proposed | REF-PR2 |
| REF-4 | low | CONFIRMED | `resourceNode` check-then-act creates duplicate roots, loses references | proposed | REF-PR4 |
| REF-5 | medium | CONFIRMED | Old search's node leaks into new view (clear before `removeListener`) | proposed | REF-PR3 |
| REF-6 | low | CONFIRMED | Same reference shown twice | proposed | REF-PR4 |
| REF-7 | low | CONFIRMED | Tree node `children` read/written concurrently | proposed | REF-PR6 |
| REF-8 | low | CONFIRMED | `descriptionsChanged` mutates tree concurrently with search thread | proposed | REF-PR6 |
| REF-9 | low | CONFIRMED | Exception in `runInUIThread` leaves the scheduled flag stuck | proposed | REF-PR2 |
| STO-1 | medium | CONFIRMED | URI removed from sources before its binary is written (MCBS:656) | proposed | STO-PR1 |
| STO-2 | low | CONFIRMED | Data race on the sources `HashSet` | proposed | STO-PR2 |
| STO-3 | low | CONFIRMED | Loaders read partial or stale binaries of dependencies | proposed | STO-PR1 |
| STO-4 | medium | CONFIRMED | Stores dropped by `shutdownNow` leave stale binaries across builds | proposed | STO-PR3 |
| STO-5 | low | CONFIRMED | Running stores not awaited after `shutdownNow` | proposed | STO-PR3 |
| STO-6 | low | PLAUSIBLE | Detached resource still stored after a link exception | proposed | STO-PR4 |
| STO-7 | — | REFUTED | Swallowed `IOException` keeps old binary while URI leaves sources | none | no fix |
| STO-8 | low | CONFIRMED | `InterruptedException` in the storage await swallowed | proposed | STO-PR5 |
| TRIE-1 | high | CONFIRMED | Case-sensitive pattern queries always empty (pattern matched against itself) | disabled: `PAEDLT` (enabled in [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551)), `QNLFT` | [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551) (draft) |
| TRIE-2 | high | CONFIRMED | Top-level `*`/`**` upper bound `"!"` misses almost every name | disabled: `QNLFT` | TRIE-PR2 |
| TRIE-3 | low | CONFIRMED | `'!'` is not a segment successor; low-char names leak into results | disabled: `QNLFT` | TRIE-PR3 |
| TRIE-4 | low | CONFIRMED | Wildcard-free `matches()` accepts longer names | disabled: `QNLFT` | TRIE-PR4 |
| TRIE-5 | low | CONFIRMED | Statistics size over-counts duplicate put/putAll | disabled: `QNLFT` | TRIE-PR6 |
| TRIE-6 | low | CONFIRMED | `putAll` stores duplicates; one remove leaves value mapped | proposed | TRIE-PR6 |
| TRIE-7 | low | CONFIRMED | `getMappings` drops blank intermediate segments | disabled: `QNLFT` | TRIE-PR7 |
| TRIE-8 | low | CONFIRMED | `shareValues=true` collapses multiplicities | disabled: `QNLFT` | TRIE-PR8 |
| TRIE-9 | low | CONFIRMED | U+FFFF wraps the upper bound, collides with sentinel | disabled: `QNLFT` (stays red with `fix-plan.patch`) | TRIE-PR10 (issue first) |
| TRIE-10 | low | CONFIRMED | `TreeSetLookup` spurious results for empty-segment patterns | disabled: `QNLFT` | TRIE-PR5 |
| TRIE-11 | low | CONFIRMED (promoted) | `initializeFrom` aliases the source's mutable tree | proposed (after contract decision) | TRIE-PR11 (issue first) |
| TRIE-12 | low | CONFIRMED | Globs inherit `"!"` bound; wildcard-free glob matches nothing | disabled: `QNLFT` (part a only); rest proposed | TRIE-PR2, TRIE-PR3, TRIE-PR9 |
| TRIE-13 | low | CONFIRMED | Globs ending in `*` walk only one level | disabled: `QNLFT` | TRIE-PR9 |
| TRIE-14 | low | CONFIRMED | Glob regexps case-insensitive, bounds case-sensitive | disabled: `QNLFT` | TRIE-PR9 |
| TRIE-15 | low | CONFIRMED | Glob `matches()` SIOOBE on empty last segment | disabled: `QNLFT` | TRIE-PR9 |
| TRIE-16 | low | CONFIRMED (promoted) | `put(QualifiedName.EMPTY, v)` increments size then throws | proposed | TRIE-PR6 |
| PIPE-1 | high | CONFIRMED | Maintenance builds always fail the Tycho baseline gate | proposed | PIPE-PR4 |
| PIPE-2 | high | CONFIRMED | Next release version from global highest tag | proposed | [#1550](https://github.com/dsldevkit/dsl-devkit/pull/1550) (draft) |
| PIPE-3 | high | CONFIRMED | Tag pushed before release repo exists; can be orphaned | proposed | PIPE-PR6 |
| PIPE-4 | medium | CONFIRMED | `p2/snapshots/latest` can point at a non-master build | proposed | PIPE-PR3 |
| PIPE-5 | medium | CONFIRMED | Shared concurrency group cancels pending runs across refs | proposed | PIPE-PR7 |
| PIPE-6 | low | CONFIRMED (promoted) | Publish re-run cannot succeed after `gh release create` | proposed | PIPE-PR5 |
| PIPE-7 | low | CONFIRMED (promoted) | 8-char short SHA may differ between clones | proposed | PIPE-PR2 |
| RO-1 | medium | CONFIRMED | `sortLocators` comparator not a total order | disabled: `SLT` | RO-PR1 |
| RO-2 | low | CONFIRMED | `com.avaloq.tools.ddk.test.ui.test` tests never run | script (exits 1) | RO-PR3 |
| RO-3 | low | CONFIRMED | Comparator makes `Collections.sort` throw for ≥32 locators | proposed (scratch driver `MinSize.java` only) | RO-PR1 |
| RO-4 | low | CONFIRMED | `ErrorLogListenerTest` missing from its own bundle suite | script (exits 1) | RO-PR3 |
| RO-5 | low | CONFIRMED | `ErrorLogListenerTest` does not test ignoring; stale location | proposed | RO-PR3 |
| RO-6 | low | CONFIRMED (promoted) | `DeChKeyboardLayoutTest` stale layout name, leaks SWTBot prefs | proposed | RO-PR3 |
| RO-7 | low | CONFIRMED | CI guard cannot detect orphaned tests | script (proposed replacement guard) | RO-PR5 |
| RO-8 | low | CONFIRMED | Aggregator selects empty placeholder `CheckCfgUiTestSuite` | script (INFO) | RO-PR4 |

## 3. Findings and fix plans by cluster

### 3.1 LDR — `ParallelResourceLoader`

#### LDR-1 — Poll timeout decrements `toProcess`, so the build aborts as cancelled without anyone cancelling

- **Severity / status:** medium / CONFIRMED. **Verification:** failing Java test on master, green with `formal/f1-fix.patch`; Lean proves P1 for all sizes once fixed; phase C 3/3 upheld, 3 overstated; suggested severity medium, low, low. **Origin:** TLA+ round 1 F1 (CONFIRMED), Lean round 1 F1 (CONFIRMED).
- **Fix PR:** [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553) (draft) — decrement only on a delivered result; after 3 consecutive timeouts, give up with an error naming the resources still loading.
- **Locations:** PRL:220, PRL:225, MCBS:531, MCBS:603.
- **Claim:** `ParallelLoadOperation.next()` runs `toProcess--` right after `poll()`, even when `poll()` timed out and returned null (PRL:220-221). `hasNext()` is then false while a URI is still queued, and MCBS:531-536 logs `NO_MORE_RESOURCES`, calls `cancel()` and throws `OperationCanceledException`. All queue kinds are affected. One resource taking more than `MAX_WAIT_TIME` (300 s) to load is enough. Upstream Xtext has the same code. A second consumer, `writeResources` (MCBS:1077; code reading only, not modelled), ends its loop early on the same drift and silently drops the timed-out URI from indexing.
- **Narrowed (skeptics):** One slow resource is not enough: the builder's poll must wait the full 300 s with no worker delivering, in practice because the last outstanding load is stuck. The drift shows at once only if the timed-out load was the last one outstanding; otherwise it shows at the cluster end. The rollback then forces a full build. `writeResources` ends one iteration early and silently skips indexing one resource. The plain one-line fix would turn a hung load into an unbounded re-poll, hence the bounded-retry fix in [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553). **Severity rationale:** lowered from high to medium: reachable in DDK with its default bindings, but the trigger is rare and a naive fix trades the spurious cancel for a liveness problem.
- **Trace:** queue={u1}, toProcess=1 → `next()` poll times out, toProcess→0, `LoadOperationException(null, TimeoutException)`, u1 stays queued (MCBS:603) → loop head: queue non-empty but `!hasNext()` → `cancel()` + OCE.
- **Models:** `formal/parallel-loader/tla/NOTES.md`, `formal/parallel-loader/lean/NOTES.md`, `formal/f1-fix.patch`, `formal/REPORT.md`.
- **Test:** `PRLT#timeoutKeepsPendingResultAvailable`, `@Disabled("Documents LDR-1…")`, enabled in [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553). Fails on master at :76 (`hasNext()` false after the timeout); passes with the fix plus the builder bundle bump.

#### LDR-2 — Interrupted builder thread livelocks the cluster loop with `LinkedBlockingQueue`/`ArrayBlockingQueue`

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity low ×3. **Origin:** TLA+ round 1 F2 (CONFIRMED, external trigger), Lean round 1 F2 (CONFIRMED; kernel-checked `livelock_trace_kernel`).
- **Locations:** PRL:222, MCBS:1359, MCBS:585.
- **Claim:** With the interrupt flag set, `poll()` on `LinkedBlockingQueue`/`ArrayBlockingQueue` throws `InterruptedException` even when a result is waiting. PRL:222-230 restores the flag and throws a fake timeout. `pollForCancellation` sleeps uninterruptibly (MCBS:1359-1365), so the flag survives, and the loop spins every ~5 s with a bogus 300 s warning until the user cancels. `SynchronousQueue` (queueSize 0, the default) does not livelock. An external interrupt is required.
- **Narrowed (skeptics):** DDK's own bindings (`ClusteringModule`) always use queueSize 0, and nothing in the Eclipse build path is known to interrupt the builder thread. Only a downstream `getParallelLoader(n, bufferSize != 0)` plus a leaked interrupt reaches the livelock. With `SynchronousQueue` a leftover interrupt still causes 5 s stalls and fake timeout warnings. **Severity rationale:** lowered from medium because the livelock is unreachable with DDK's bindings.
- **Trace:** worker puts result → builder interrupted → `poll` throws although an item is present → fake timeout → uninterruptible sleep, no cancel → `hasNext()` true → repeat.
- **Models:** `formal/parallel-loader/tla/NOTES.md`, `formal/parallel-loader/lean/NOTES.md`.
- **Test:** proposed (LDR-PR2): interrupted `pollForCancellation` throws OCE quickly, via a test subclass seam.

#### LDR-3 — Executor thread leaks in `put()` after `cancel()` when a load swallows the interrupt

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 1 F3 (CONFIRMED, conditional). Not in Lean.
- **Locations:** PRL:367, PRL:289.
- **Claim:** `cancel()` relies only on the `shutdownNow()` interrupt to unblock `publishLoadResult`'s `put()` (PRL:367). If `doLoadResource` cleared the flag, a worker finishing after cancel blocks forever in `put()` on a `SynchronousQueue` or a full `ArrayBlockingQueue`.
- **Narrowed (skeptics):** No DDK/Xtext/EMF load path is shown to clear the flag. Each occurrence leaks up to `nThreads` (≤4) idle non-daemon threads and their retained resources; the build does not hang. Triggers include the finally-block `cancel()` after a 300 s timeout (MCBS:680) and the cluster-end cancel, not only a user cancel. Hardening.
- **Trace:** worker loading u1 → `shutdownNow` interrupts it → load completes having cleared the flag → `put()` on a dead `SynchronousQueue` blocks forever (WorkersQuiesce violated).
- **Models:** `formal/parallel-loader/tla/NOTES.md`.
- **Test:** proposed (LDR-PR3): `cancelDoesNotLeakWorkerWhenLoadSwallowsInterrupt`.

#### LDR fix plans

Single plan, no judge. The three findings are independent: Lean `fixTimeout_alone_still_livelocks` and `fixInterrupt_alone_still_aborts` show LDR-1 and LDR-2 each need their own fix.

**LDR-PR1 (LDR-1)**

- **Opened as [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553) (draft)** with a bounded-retry variant of the fix below: decrement only on a delivered result, and after 3 consecutive timeouts give up with an error naming the resources still loading. It adds `repeatedTimeoutsGiveUpOnHungLoad`. This replaces the plain one-line fix, which would make a truly hung load re-poll forever (see Risks).
- **Fix:** In `ParallelLoadOperation.next()` (PRL:219-224), decrement only when `poll()` returned a result: `if (result != null) { toProcess--; }` (exactly `formal/f1-fix.patch`). Keep `@SuppressFBWarnings("AT_NONATOMIC_OPERATIONS_ON_SHARED_VARIABLE")`; only the builder thread touches the counter. MCBS needs no change. The same line fixes the `writeResources` consumer; say so in the PR body.
- **Files:** PRL; `com.avaloq.tools.ddk.xtext.builder/META-INF/MANIFEST.MF`; `com.avaloq.tools.ddk.xtext.builder/pom.xml`; `PRLT` (enable); `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/XtextTestSuite.java`.
- **Version bumps:** `com.avaloq.tools.ddk.xtext.builder` 17.3.1 → 17.3.2 (MANIFEST `.qualifier`, pom `-SNAPSHOT`), done only by the first of LDR-PR1/2/3 and STO-PR1..5 to land in the cycle. `xtext.test`: none (already 17.3.2).
- **Test strategy:** `timeoutKeepsPendingResultAvailable` overrides `loadResource(URI, ResourceSet, ResourceSet)` to block on a latch, sets a 50 ms timeout, and asserts `next()` throws `LoadOperationException(TimeoutException)` while `hasNext()` stays true; after the latch is released, `next()` returns the slow resource. Before the PR, parameterise over queueSize {-1, 0, 1}: TLA+ shows the bug for all three, and the spike test covers only -1. No thread is interrupted.
- **Risks:** A truly hung load now logs a timeout about every 305 s instead of ending as a spurious cancel, so it looks like a build that never finishes. User cancel still works. A cap on consecutive timeouts, if wanted, is a follow-up. No API change.
- **Model evidence:** TLA+ `ParallelLoader.tla` violates Bookkeeping, AbortOnlyOnCancel and BuildCompletes (3-step trace); `ParallelLoaderFixed` passes full_s/m/l/xl for all queue kinds; `variants/AblateFix1.tla` violates Bookkeeping again; the planted variant is caught. Lean `Proof.lean` `P1_fixTimeout` (inductive, all configs, no `sorry`); `Results.lean` `original_timeout_violates_P1/P2(_all_queues)`, `timeout_trace_kernel`.
- **Upstream:** Shared: Xtext 2.42 `ParallelResourceLoader.java:229-230` has the same unconditional decrement, mostly masked because upstream NPEs on a null result and its `ClusteringBuilderState` has no `hasNext()` guard. Worth one low-priority Xtext issue together with LDR-3.

**LDR-PR2 (LDR-2)**

- **Fix:** At the top of `pollForCancellation(IProgressMonitor)` (MCBS:1359): `if (Thread.interrupted()) { LOGGER.warn(...); throw new OperationCanceledException(); }`. It is called only from the three load-failure catch blocks (MCBS:587, 1103, 1122), so one site covers the cluster loop and `writeResources`. The OCE is thrown from inside the catch, so `catch(Exception)` does not swallow it. The flag is consumed on purpose: leaving it set would short-circuit `awaitBinaryStorageExecutorTermination` in the finally (MCBS:684). Widen `pollForCancellation` from private to protected as a test seam. Rejected: throwing OCE from PRL `next()` (swallowed by MCBS:585 `catch(Exception)`), and a loop-head `isInterrupted()` check (Lean's fixInterrupt), which misses the `writeResources` loop.
- **Files:** MCBS; builder MANIFEST/pom (if not yet bumped); `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/builder/MonitoredClusteringBuilderStateTest.java` (new); `XtextTestSuite.java`.
- **Version bumps:** builder 17.3.1 → 17.3.2 only if no earlier LDR/STO PR bumped it. `xtext.test`: none.
- **Test strategy:** An anonymous subclass of `MonitoredClusteringBuilderState(mock(IXtextTargetPlatformManager))` exposes `pollForCancellation` (a subclass, because package-private access across OSGi bundles fails). Interrupt the current thread; assert OCE well under the 5 s polling timeout and that the flag is clear. On master the call sleeps 5 s and returns. A finally block calls `Thread.interrupted()`. Optional precondition test in `PRLT` (queueSize -1 and 1): an interrupted `next()` fails fast with a fake timeout. A build-level test needs a test language with bufferSize ≠ 0 and is out of scope.
- **Risks:** Any interrupt that coincides with a failed `next()` now cancels the build, including with `SynchronousQueue`. `pollForCancellation` becomes protected API in an exported package; the baseline comparator is content-only, so a micro bump suffices. Must land before STO-PR5 (STO-8), whose interrupt restore at MCBS:865 would otherwise make the livelock reachable from an internal source.
- **Model evidence:** TLA+ `live_int_arr1`/`live_int_unb` violate BuilderStops/BuildCompletes; `live_int_sync` passes; `AblateFix2` brings the violation back for buffered queues only. Lean `livelock_trace_kernel`, `original_interrupt_violates_P3`, `original_interrupt_sync_ok`, `fixBoth_ok` (P1-P3, all 4 queue kinds). **Caveat:** neither model uses this exact placement; see [open question 3](#6-open-questions). `writeResources` is code reading only.
- **Upstream:** Partly shared: Xtext 2.42 (`ParallelResourceLoader.java:231-236`) also turns an interrupt into a missing result, but the livelock (uninterruptible poll plus `hasNext()` guard) is DDK-specific. At most mention it in the LDR-1/LDR-3 issue.

**LDR-PR3 (LDR-3)**

- **Fix:** Add `private volatile boolean cancelled` to `ParallelLoadOperation`, set first in `cancel()` (PRL:289). In `publishLoadResult` (PRL:365-371), replace `put()` with a timed `offer(result, PUBLISH_RETRY_MILLIS≈100, MILLISECONDS)` loop that exits when published or cancelled and restores the interrupt on `InterruptedException`. Give the loop a non-empty body (PMD `EmptyControlStatement`). Handoff semantics unchanged; no API change (`publishLoadResult` is private).
- **Files:** PRL; builder MANIFEST/pom (if not yet bumped); `PRLT` (add test).
- **Version bumps:** as LDR-PR2.
- **Test strategy:** `cancelDoesNotLeakWorkerWhenLoadSwallowsInterrupt`, parameterised over queueSize 0 and 1, with -1 as a control that also passes on master. The `loadResource` override records the worker, counts down `started`, awaits `release` uninterruptibly, swallows the interrupt with `Thread.interrupted()`, and returns a resource. The test awaits `started`, calls `cancel()`, releases, joins the worker with a 5 s bound and asserts it is dead. A finally block releases and interrupts the worker.
- **Risks:** Low. Blocked workers wake about every 100 ms. A load that never returns is not helped. Frame the PR as hardening.
- **Model evidence:** TLA+ `live_swallow_sync` and `leak_arr1` violate WorkersQuiesce; FIX-3 in `ParallelLoaderFixed.tla` passes full_s/m/l/xl; `AblateFix3` violates it on full_s_sync and full_m_arr1. Lean does not model swallowed interrupts.
- **Upstream:** Shared: Xtext 2.42 `ParallelResourceLoader.java:215-219` publishes with an unbounded `put()`. Same Xtext issue as LDR-1.

### 3.2 REF — find references (`FastReferenceSearchResultContentProvider`)

The class is not bound anywhere in DDK. Every REF finding affects only downstream products that bind it. REF-4, REF-7 and REF-8 are DDK-only: upstream Xtext mutates the tree only on the UI thread.

#### REF-1 — UI deadlock: Reset handler `syncExec`s while holding the listeners monitor

- **Severity / status:** medium / CONFIRMED. **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity medium ×3. **Origin:** TLA+ round 2 refs F2 (CONFIRMED by code reading), Lean round 2 refs F2 (CONFIRMED, timing window); javap of Xtext `ReferenceSearchResult` shows `fireEvent` and `removeListener` both lock `listeners`.
- **Locations:** FRSRCP:178, FRSRCP:113.
- **Claim:** `ReferenceSearchResult.fireEvent` calls listeners while holding the listeners monitor. The Reset handler calls `Display.syncExec` (FRSRCP:178). If the UI thread is switching to another search at that moment, it blocks on the same monitor, and both threads wait forever: the workbench freezes. The Xtext superclass has no `syncExec`.
- **Narrowed (skeptics):** The UI thread actually blocks first in Xtext `ReferenceSearchViewPage.setInput:147` (`removeListener(labelUpdater)`), before FRSRCP:113. The window exists only at job start and needs rapid user input (a new Find References, a history pick, or removing the query). **Severity rationale:** a hard hang, but lowered from high because the window is narrow and the class is downstream-only.
- **Trace:** job start: `ReferenceQuery.run` → `reset()` → `fireEvent(Reset)` takes `listeners` → provider `syncExec` waits for UI. Meanwhile UI: `setInput(B)` → `A.removeListener` blocks on `listeners` → deadlock.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_NoDeadlock.txt`.
- **Test:** proposed (REF-PR5): park the UI thread and run a real `ReferenceSearchResult.reset()` on another thread.

#### REF-2 — `ConcurrentModificationException` in `inputChanged` while the search is still running

- **Severity / status:** low / CONFIRMED. **Verification:** failing Java test on master (CME), green with `fix-cme.patch`; phase C 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs F6 (CONFIRMED, racy), Lean round 2 refs F4 (CONFIRMED).
- **Fix PR:** [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552) (draft) — iterate a `Lists.newArrayList` copy and skip nulls.
- **Locations:** FRSRCP:118.
- **Claim:** `inputChanged` iterates the live `getMatchingReferences()` list while the search thread's `accept()` appends to it without a lock. The CME escapes `viewer.setInput` and leaves the page half-initialised: the listener is registered but the input is not set. The Xtext superclass has the same live iteration. An unsynchronised copy is not enough on its own: in JDK 9+ `ArrayList.clear()` sets size=0 before nulling the slots, but a copy can still see null slots when `toArray` reads the old size and then copies during `clear()`, or when a concurrent grow pads with nulls (REF judge correction).
- **Narrowed (skeptics):** Downstream-only: DDK never binds FRSRCP, and DDK's languages use Xtext's default provider, which has the same live iteration. It matters for consumer products that bind FRSRCP. A new search sets the input before its job is scheduled, so only re-showing a still-running search races (history, page switcher, reopened or second Search view). **Severity rationale:** lowered from medium to low: downstream-only and behind a user action during a running search.
- **Trace:** UI switches back to running R: `inputChanged` → `addListener` → iterator; search thread `accept()` → `matchingReferences.add` → iterator `next()` throws CME.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_NoCME.txt`, `formal/find-refs/fix-cme.patch`.
- **Test:** `FRSRCPT#inputChangedToleratesReferencesAcceptedWhileRepopulating`, `@Disabled("Documents REF-2…")`, enabled in [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552). Fails on master; passes with `fix-cme.patch` plus the `xtext.ui` bump.

#### REF-3 — Lost update: UIUpdater clears `isUIUpdateScheduled` after its unlocked `isEmpty()` check

- **Severity / status:** medium / CONFIRMED. **Verification:** phase B 3/3 upheld, 1 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs F1 (CONFIRMED), Lean round 2 refs F1 (CONFIRMED); javap of upstream `UIUpdater` shows it clears the flag first.
- **Locations:** FRSRCP:217, FRSRCP:220, FRSRCP:173.
- **Claim:** The UIUpdater drains, refreshes, checks `isEmpty()` (FRSRCP:217), and only then clears the flag (FRSRCP:220). A node added in between sees the flag still true and schedules nothing; if it was the last match, it is never shown.
- **Narrowed (skeptics):** The window also covers a new child of an already-expanded root added during `refresh()` (:216). Display only: the model and count are correct, and re-running recovers. **Severity rationale:** kept at medium against the skeptics' low, because the trigger is an ordinary search, not a narrow path; the impact (a missing result) is recoverable, which is the medium definition.
- **Trace:** UI drains [n1], refreshes, `isEmpty()`=true → search thread adds n2, reads flag==true, does not schedule → UI sets flag=false → search ends; n2 never shown.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_NoLostRoot.txt`, `formal/find-refs/tla/traces/orig_NoLostRef.txt`.
- **Test:** proposed (REF-PR2): deterministic `isEmpty()` injection via an `ArrayList` subclass that delivers Added.

#### REF-4 — `resourceNode` check-then-act creates duplicate root nodes and loses references

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs F4 (CONFIRMED), Lean round 2 refs F5 (CONFIRMED).
- **Locations:** FRSRCP:158, FRSRCP:161.
- **Claim:** `resourceNode` (FRSRCP:157-166) does an unguarded get-then-put on `rootNodes`. The UI thread (`inputChanged` repopulation) and the search thread's Added handler call it concurrently; the last put wins and references on the orphaned node disappear. DDK-only. In Lean this was masked by REF-3 until REF-3 was patched.
- **Narrowed (skeptics):** Reachable only when a still-running search is re-shown from history, and today the same interleaving nearly always throws the REF-2 CME first, so REF-4 is an independent defect only after REF-2 is fixed. A lost reference returns on the next `setInput`. Any fix lock must be ordered against `listeners`. **Severity rationale:** lowered from medium; reachability is limited to history re-show and masked by REF-2.
- **Trace:** UI `addReference(r1)`: `get(u1)`=null; search thread Added(r2): `get(u1)`=null; both put, last wins, r1 hangs under the orphan.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_OneRootCreated.txt`, `formal/find-refs/tla/traces/orig_flagfixed_lost_ref_via_duplicate_root.txt`.
- **Test:** proposed (REF-PR4): state-polled two-thread test via a forwarding `rootNodes` map; fails on master.

#### REF-5 — A running search's node appears in another search's view (clear before `removeListener`)

- **Severity / status:** medium / CONFIRMED. **Verification:** phase B 3/3 upheld, 0 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs F3 (CONFIRMED), Lean round 2 refs F3 (CONFIRMED).
- **Locations:** FRSRCP:111, FRSRCP:113.
- **Claim:** `inputChanged` clears `rootNodes` (:111) before `removeListener` (:113). An in-flight Added handler of the old search writes into the cleared map, which now belongs to the new input. The leak persists when the new input is a search picked from history, and is transient for a fresh search. `batchAddNodes` is not cleared on input change either. **Upstream variant (REF judge graft 8):** upstream also clears before `removeListener` and never clears its equivalent `batchedSearchResultEvents` on `setInput`; that part goes in the upstream issue, and REF-PR3 clears DDK's `batchAddNodes`.
- **Skeptic notes:** Swapping the two lines is correct only because `fireEvent` holds the listeners monitor, so `removeListener` waits for in-flight handlers. **Severity rationale:** kept at medium; not called overstated, and reachable whenever the user switches searches while one is running.
- **Trace:** search thread `accept(ref0)`: `get(uri0)`=null → UI `rootNodes.clear()` → search thread puts node0, schedules → UI `A.removeListener` → `setInput(B)` refresh shows A's node0.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_NoForeign.txt`.
- **Test:** proposed (REF-PR3): single-threaded Mockito test.

#### REF-6 — The same reference is shown twice

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs F5 (CONFIRMED), Lean round 2 refs F5 (part).
- **Locations:** FRSRCP:116, FRSRCP:119.
- **Claim:** `accept()` adds to the list outside the lock and fires Added afterwards. If `inputChanged` registers and iterates in between, the reference is added from both the snapshot and the event. Nothing dedupes.
- **Narrowed (skeptics):** Only when a still-running query is re-shown; the window is a few instructions wide, and a CME (REF-2) is more likely. Cosmetic; cleared on rerun. Dedup must use a lock taken by `addReference`, not the viewer lock.
- **Trace:** search `add(r2)` → UI `addListener`, iterate, `addReference(r2)` → search `fireEvent(Added r2)` → `addReference(r2)` again.
- **Models:** `formal/find-refs/tla/NOTES.md`, `formal/find-refs/lean/NOTES.md`, `formal/find-refs/tla/traces/orig_NoDupRef.txt`.
- **Test:** proposed (REF-PR4): an `addListener` stub fires Added for a reference already in the list.

#### REF-7 — Tree node `children` list read and written concurrently without synchronisation

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs, code-reading extra (no verdict, not modelled).
- **Locations:** FRSRCP:137.
- **Claim:** `ReferenceSearchViewTreeNode.children` is a plain `ArrayList`. The search thread writes it via `addChild`; the UI thread reads it in `getChildren`/`hasChildren` and during expansion. DDK-only (the TLA+ note says the race exists upstream; the REF judge found upstream mutates nodes only on the UI thread).
- **Narrowed (skeptics):** `getChildren`/`hasChildren` use `toArray`/`isEmpty` and cannot throw a CME; at worst they read stale or torn state. The real CME risk is the for-each at :243 with `removeChild` at :255 in `descriptionsChanged` during an active search.
- **Models:** `formal/find-refs/tla/NOTES.md` ("Code-reading extras").
- **Test:** proposed (REF-PR6): a seam that pauses a children read while another thread adds.

#### REF-8 — `descriptionsChanged` mutates `rootNodes` and children on the UI thread concurrently with the search thread

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 refs, code-reading extra (no verdict, not modelled).
- **Locations:** FRSRCP:228.
- **Claim:** `descriptionsChanged` (FRSRCP:228-270) mutates `rootNodes` and node children while the search thread adds. Same class as REF-4. DDK-only.
- **Narrowed (skeptics):** `rootNodes` (a `ConcurrentMap`) operations are individually safe. The risk is the children `ArrayList`: a CME, or a match orphaned between `isEmpty()` (:257) and removal (:258). Needs a build delta during an active search; transient.
- **Models:** `formal/find-refs/tla/NOTES.md` ("Code-reading extras").
- **Test:** proposed (REF-PR6): interleave Added into the `descriptionsChanged` check-then-act.

#### REF-9 — An exception in `UIUpdater.runInUIThread` leaves `isUIUpdateScheduled` stuck true

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** Lean round 2 refs, observation (no verdict, not modelled).
- **Locations:** FRSRCP:207, FRSRCP:220.
- **Claim:** If `runInUIThread` throws before clearing the flag, that provider instance never schedules another update.
- **Narrowed (skeptics):** The disposed-viewer example is harmless (the provider is disposed with its viewer). Realistic triggers are a `RuntimeException` from a label provider or comparator on a live viewer, or the UIJob being cancelled before it runs. The page reuses the provider, so later searches in that page are affected until `setInput` or the view is recreated.
- **Models:** `formal/find-refs/lean/NOTES.md` ("Observations").
- **Test:** proposed (REF-PR2): a mocked viewer whose `refresh()` throws once.

#### REF judge

- **Winner:** Plan A. It implements the design that was model-checked (TLA fixed set {flag, detach, dedupe, snapshot, plock, reset}, Lean `Fixed.lean`), and each PR maps to one ablation row, so every intermediate state is model-covered: after PR1-3 the code equals Lean 'lost+order+snap', after PR4 TLA `MC_no_reset`. Plan B's central PR confines the tree to the UI thread with a FIFO queue, which is not model-checked, bundles REF-1/4/7/8 into one rewrite, and falls back to Plan A. Plan A also has more failing-first tests (deterministic REF-3 injection; a REF-4 two-thread test that fails on master).
- **Facts verified by the judge:** FRSRCP:217-220 is the lost-update window; upstream (e8855b27fd) clears the flag first (`ReferenceSearchResultContentProvider:205`); the facts behind REF-A1 and REF-A2 ([4.3](#43-verified-observations)).
- **Correction:** the REF-2 null-slot mechanism (recorded under REF-2).
- **Grafts from Plan B:** REF-9 test via a throwing `refresh()`; a disposed-viewer guard in UIUpdater and `descriptionsChanged`; the REF-1 test through a real `ReferenceSearchResult.reset()`; the REF-6 test shape; REF-A1/REF-A2 as their own optional PR; UI-thread confinement as a follow-up gated on a `confine` model variant; one bundled Xtext issue.
- **Weakest part:** REF-7/REF-8: the fix is residual, argued by analogy, and not modelled.

#### REF fix plans

All REF PRs touch FRSRCP and `FRSRCPT`. **Version bump:** `com.avaloq.tools.ddk.xtext.ui` 17.3.3 → 17.3.4 (MANIFEST + pom), done only by the first REF PR merged in the cycle. `xtext.ui.test`: none (not in the p2 baseline).

**REF-PR1 (REF-2)**

- **Fix:** In `inputChanged` (FRSRCP:118), iterate `Lists.newArrayList(getMatchingReferences())` and skip null elements (`fix-cme.patch` plus a null guard, needed for the reason recorded under REF-2; without it `getContainerEObjectURI()` NPEs).
- **Files:** FRSRCP, `com.avaloq.tools.ddk.xtext.ui/META-INF/MANIFEST.MF`, `com.avaloq.tools.ddk.xtext.ui/pom.xml`, `FRSRCPT`, `com.avaloq.tools.ddk.xtext.ui.test/src/com/avaloq/tools/ddk/xtext/ui/test/XtextUiTestSuite.java`.
- **Test strategy:** Enable `inputChangedToleratesReferencesAcceptedWhileRepopulating`. Add a null-guard case where `toArray()` returns `{ref, null}`: no exception, exactly one root.
- **Risks:** Very low: an O(n) copy per view switch. A reference missed in the window arrives later via its Added event; duplicates in the window are REF-6.
- **Model evidence:** TLA `MC_no_snapshot` violates NoCME; the original model violates it in 19 steps. Lean P4 fails in 8 steps on 'as written' and 'lost', holds on 'lost+order+snap'.
- **Upstream:** Shared (Xtext `inputChanged` :135-138). Bundled Xtext issue with REF-6 and the REF-5 upstream variant, posted only with the user's approval.

**REF-PR2 (REF-3, REF-9)**

- **Fix:** In `UIUpdater.runInUIThread` (FRSRCP:205-223), make `isUIUpdateScheduled = false` the first statement, before the synchronized drain. After the drain, return OK if the viewer or its control is null or disposed. Delete the unlocked `isEmpty()`/`schedule(250)` tail and `JOB_RESCHEDULE_DELAY`. This is upstream's order: the volatile clear happens-before the drain's unlock, so a concurrent add is either drained or sees `false` and reschedules, and an exception can no longer leave the flag stuck. Fallback if throttling matters: Lean F1 option B, an atomic test-and-set under the batch lock.
- **Test strategy:** REF-3: replace the final `batchAddNodes` field by reflection with an `ArrayList` subclass whose `isEmpty()` fires Added inline; invoke `runInUIThread` on a reflectively built UIUpdater; assert `!batchAddNodes.isEmpty() ⇒ isUIUpdateScheduled`. Companion case injects from the `refresh()` answer. REF-9: a mocked viewer's `refresh()` throws on its first call; fire Added(a), then Added(b) with `useUIThread=false`; `verify(viewer, timeout(5000).times(2)).refresh()`. If reflection is rejected, add a package-private `updateViewer(IProgressMonitor)` seam.
- **Risks:** Drops the 250 ms throttle, so full refreshes can run back to back on large searches (upstream accepted this).
- **Model evidence:** The TLA fixed model uses exactly this order. `MC_no_flag` violates NoLostRoot; the original model violates NoLostRoot and NoLostRef in 31 steps. Lean 'lost' turns P1' green; `Proof.no_lost_root` proves option B for all sizes. REF-9 is not modelled; it is removed by construction.
- **Upstream:** Not shared; the DDK copy regressed upstream's order.

**REF-PR3 (REF-5)**

- **Fix:** Move `removeListener(this)` on the old input above `rootNodes.clear()`, and clear `batchAddNodes` under its monitor.
- **Test strategy:** Single-threaded Mockito: A's `removeListener` stub fires Added(A, refA). Call `inputChanged(viewer, null, A)`, then `inputChanged(viewer, A, B)` with B empty; assert `getElements(B).length == 0`.
- **Risks:** Minimal; no new lock ordering.
- **Model evidence:** TLA `MC_no_detach` violates NoForeign; the original model violates it in 23 steps. Lean P3 fails in 12 steps ('as written') and 10 ('lost'), holds with 'order'.
- **Upstream:** The variant recorded under REF-5 is shared; bundled issue, subject to approval.

**REF-PR4 (REF-4, REF-6)**

- **Fix:** Add a private `lock`, documented never to be held while taking `listeners` or the viewer monitor or while waiting on the UI, and an identity `Set<IReferenceDescription> addedReferences` guarded by it. The Added handler does `addReference` and the flag test-and-set under `lock`; `addReference` returns early for an already-added reference. `inputChanged` does `removeListener`, `addListener` and the viewer set outside the lock, then clears, snapshots and repopulates under it. The null-input path and `dispose()` clear under the lock. Lock order: search thread `listeners → lock`, UI `viewer → lock`; no cycle.
- **Test strategy:** REF-6: A's `addListener` stub fires Added(A, r) for an r already in the list; assert exactly one child. REF-4: replace `rootNodes` by reflection with a forwarding map whose `get` starts thread T delivering Added for the same URI, then polls T's state (10 s cap); assert one root and both references reachable.
- **Risks:** The new monitor is the main risk for future edits. Dedup is by identity, so equal-but-distinct descriptions still show twice, as today.
- **Model evidence:** TLA `MC_no_plock` violates OneRootCreated; `MC_no_dedupe` violates NoDupRef (46-step trace); `computeIfAbsent` is redundant under plock and fails NoStale without it. Lean 'lost+order+snap' still fails P1 in 25 steps (REF-4). After this PR the code equals TLA `MC_no_reset`, which fails only NoDeadlock.
- **Upstream:** REF-6 shared; REF-4 DDK-only.

**REF-PR5 (REF-1)**

- **Fix:** In the Reset branch (FRSRCP:177-189), remove `syncExec`. On the search thread, clear `rootNodes`, `addedReferences` and `batchAddNodes` under `lock`, then `asyncExec` a `refresh()` + `expandToLevel(1)` guarded by a non-disposed control, without taking the viewer monitor.
- **Test strategy:** Park the UI thread with an `asyncExec` runnable awaiting a `release` latch. Thread S runs `result.reset()` on a real `ReferenceSearchResult` subclass, so `listeners` is genuinely held. Assert S finishes within 10 s; a finally block releases and joins S.
- **Risks:** Must land with or after REF-PR4: `asyncExec` alone loses post-reset nodes (Lean '+async' P1, 13 steps), and unlocked clearing shows stale nodes (NoStale). Old rows stay visible until the async refresh runs.
- **Model evidence:** TLA `MC_no_reset` violates NoDeadlock (6 steps). The full fixed model passes every invariant, including `<<u1,u2,u1,u2>>`/3 runs/4 switches (50.5M states). Lean 'fixed' is green at both bounds; the planted bug is caught in 2 steps.
- **Upstream:** DDK-only; upstream batches Reset in the UIUpdater.

**REF-PR6 (REF-7, REF-8)**

- **Fix:** Reuse the REF-PR4 lock around the `getChildren`/`hasChildren` reads and the `descriptionsChanged` body, plus a null/disposed viewer guard. Residual: `collectReferenceDescriptions` and `expandToLevel` still read outside the lock. The complete alternative is Plan B's UI-thread confinement, as a follow-up gated on a `confine` variant passing in `FindRefs.tla` and `Fixed.lean`.
- **Files:** FRSRCP, `FRSRCPT`, `formal/find-refs/tla/FindRefs.tla`.
- **Test strategy:** First extend `FindRefs.tla` with a `DescriptionsChanged` action and a modCount-tracking iterator. Then Java seams: pause a children read while T adds (REF-7); interleave Added into the `descriptionsChanged` check-then-act (REF-8). If the seams get too invasive, ship on the model extension plus review, and say so in the PR.
- **Risks:** Brief contention between expansion and the Added handler.
- **Model evidence:** None yet; argued by analogy with plock.
- **Upstream:** DDK-only.

**REF-PR7 (optional: REF-A1, REF-A2)**

- **Fix:** (REF-A1) `dispose()` clears under the lock, then calls `super.dispose()` so `detachListenerFromIndex` runs; drop the constructor's duplicate `addListener` (FRSRCP:69-71). (REF-A2) Override `remove(ReferenceSearchViewTreeNode...)` under the lock: remove a root from `rootNodes`, otherwise `parent.removeChild`, and drop dedupe entries.
- **Test strategy:** (REF-A1) A mock `IResourceDescriptions` implementing `Event.Source`: `removeListener` after `dispose()`, exactly one `addListener` after construction. (REF-A2) Populate one reference, call `remove(root)`, expect an NPE on master. Both must be red on master before the PR claims them (phase C confirmed REF-A2 from bytecode; the NPE has not been run).
- **Risks:** Low. The index listener set is a `CopyOnWriteArraySet`, so the duplicate `addListener` is redundant, not a double notification.
- **Model evidence:** None; code reading of Xtext `ReferenceSearchViewPageActions:150` and `ReferenceSearchResultContentProvider:104-112`, :295. Not an upstream bug.

### 3.3 STO — binary model storage (`MonitoredClusteringBuilderState`)

**Reachability:** DDK never enables binary storage: both of its target platforms return a null `IBinaryModelStore` (see [rule 2](#1-method-and-legend)). Every STO finding affects only downstream platforms that supply a store and bind DLRSF, so STO severities are capped at medium.

#### STO-1 — URI removed from sources before its binary is written (MCBS:656)

- **Severity / status:** medium / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 storage B1 (CONFIRMED), Lean round 2 storage B2 (CONFIRMED).
- **Locations:** MCBS:656, MCBS:654, MCBS:754, MCBS:596.
- **Claim:** `storeBinaryResource` only submits the store (MCBS:654/726), but MCBS:656 removes the URI from the source-level set immediately; the worker's removal after `saveResource` (MCBS:753-754) is the correct one. The load-failure path (changedURI set, resource null) also removes a URI for which no store happens, so the previous build's binary becomes loadable. Upstream never removes URIs from this set. STO-1 enables STO-3 and STO-4. Fix: delete MCBS:656.
- **Narrowed (skeptics):** The main resource set is unaffected: the resource stays in memory, and `clearResourceSet` awaits stores first. Stale or partial reads happen only via (a) parallel-loader child sets loading dependencies while stores are pending, (b) stores dropped after the 1-minute await timeout, and (c) the load-failure path, where the delete delta hides the resource from index-based scoping so only direct URI proxies reach the stale binary. **Severity rationale:** lowered from high to medium. It is downstream-only (cap), and the stale-binary read needs a timeout, a load failure or a same-cluster child-set load. It stays above the skeptics' low because it is the root cause of STO-3/STO-4 and its effect is a silently wrong model.
- **Trace:** `next()` returns a → link → submit store → `getSources().remove(a)` while the store is queued. Load-failure variant: resource null, no store, URI still removed → old binary loadable.
- **Models:** `formal/binary-storage/tla/NOTES.md`, `formal/binary-storage/lean/NOTES.md`.
- **Test:** proposed (STO-PR1, on the STO-PR0 harness): three failing-first cases.

#### STO-2 — Data race on the sources `HashSet`

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 storage B5 (CONFIRMED), Lean round 2 storage B1 (CONFIRMED); javap of `SourceLevelURICache.<init>` shows a plain `HashSet`. The corruption outcomes were not demonstrated in Java.
- **Locations:** MCBS:656, MCBS:754, MCBS:1296, MCBS:1536, PRL:168.
- **Claim:** The set is mutated without synchronisation by the builder (MCBS:656, 1296-1298) and up to 4 storage workers (MCBS:754), and read by loader jobs (PRL:168-174). `update()` is synchronized, but only the builder takes that lock. A lost add makes a queued URI load from its stale binary.
- **Narrowed (skeptics):** The only harmful interleaving is a worker remove racing a builder add or resize in `queueAffectedResources`, in a very narrow window. Other interleavings cost at most an extra parse. One skeptic says a `contains()` miss during a resize cannot happen, because adds run while no load operation is live. The worker remove at 754 duplicates 656. **Severity rationale:** lowered from high to low: downstream-only, one narrow harmful interleaving.
- **Trace:** builder `remove(a)` at 656 overlaps worker remove at 754 (`orig_SetThreadSafe`); builder `add(b)` at 1298 overlaps worker remove (`orig_adr`); loader `contains()` overlaps worker remove (`orig_rdw`).
- **Models:** `formal/binary-storage/tla/NOTES.md`, `formal/binary-storage/lean/NOTES.md`.
- **Test:** proposed (STO-PR2): lock-contract tests, no stress test.

#### STO-3 — Loaders start before storage is awaited and read partial or stale binaries of dependencies

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 1 overstated; suggested severity medium, medium, low. **Origin:** TLA+ round 2 storage B2 (CONFIRMED, given that loading u also loads its dependencies), Lean round 2 storage B3 (CONFIRMED ordering; impact language-dependent).
- **Locations:** MCBS:668, MCBS:673, MCBS:1204.
- **Claim:** MCBS:668-669 starts the next cluster's loader before `clearResourceSet` awaits storage (MCBS:673 → 1204). Combined with STO-1, a loader that pulls in a dependency still being stored reads a half-written or previous-build binary; this also happens within one cluster. A truncated read falls back to parsing (`StorageAwareResource.load` catches the `IOException`), but a stale binary is accepted silently.
- **Narrowed (skeptics):** The root cause is STO-1: reordering the await would not fix it, deleting 656 does. A lasting wrong result needs a language whose `getResource(uri, true)` copies values from other resources during load, and no DDK language does; PRL unloads pulled-in dependencies, and references are re-resolved on the main thread after the await. **Severity rationale:** lowered from medium to low: downstream-only and conditional on a language shape DDK does not have. Its fix is STO-1's.
- **Trace:** cluster 1 stores a and removes it from sources → `queueAffectedResources` adds b → `load(queue)` starts b before the await → b's load pulls a from its partial or stale binary.
- **Models:** `formal/binary-storage/tla/NOTES.md`, `formal/binary-storage/lean/NOTES.md`.
- **Test:** proposed (STO-PR1 case c): within-cluster load sees `contains(a)` while a's store is latched.

#### STO-4 — Stores dropped by `shutdownNow` after an await timeout leave stale binaries that persist across builds

- **Severity / status:** medium / CONFIRMED. **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity medium ×3. **Origin:** Lean round 2 storage B4 (CONFIRMED), TLA+ round 2 storage B3 (part).
- **Locations:** MCBS:804, MCBS:879, MCBS:880, MCBS:1524.
- **Claim:** The storage await defaults to 1 minute with 0 retries (MCBS:804). On timeout or interrupt, `shutdownNow` (MCBS:879) drops queued stores and logs only their count. Their URIs were already removed at 656, so this build and later builds (which install only `toBeUpdated` as sources, MCBS:1524-1536) load outdated binaries until the resource changes.
- **Skeptic notes:** It can happen at any mid-build `clearResourceSet` boundary, not only at the end. A resource with no earlier binary falls back to source. `deleteBinaryResources` covers only `toBeDeleted` (L453). The interrupt path practically never fires. **Severity rationale:** medium (cap): wrong models that persist across builds, behind a 1-minute storage backlog on a downstream platform.
- **Trace:** a and c stored and removed from sources → await times out → `shutdownNow` drops c → next cluster resolves a/c from outdated binaries; c's old binary persists.
- **Models:** `formal/binary-storage/lean/NOTES.md`, `formal/binary-storage/tla/NOTES.md`.
- **Test:** proposed (STO-PR3): 10 ms await must delete the dropped stores' binaries.

#### STO-5 — Running stores are neither awaited nor reported after `shutdownNow`

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 storage B3 (CONFIRMED), Lean round 2 storage B5 (CONFIRMED).
- **Locations:** MCBS:837, MCBS:871, MCBS:1203, MCBS:1207, MCBS:684.
- **Claim:** `terminateBinaryStorageExecutor` never waits after `shutdownNow`. Running tasks continue while the builder recreates the executor (MCBS:871), clears the resource set they serialise (MCBS:1207; the comment at 1203 names this hazard), and continues. The builder can open a partial binary.
- **Narrowed (skeptics):** They disagree on the mechanism: one says the default Xtext `PortableURIs` NPEs on a detached resource, after which DLRSF deletes the binary; another says DDK's write path (`DirectLinkingPortableURIs`, `DirectLinkingResourceStorageWritable`) does not read the resource set, so there is no NPE. All agree the realistic harm is a narrow same-URI race: loading the previous-build binary, or catching the single final `generateFile` write part-way. **Severity rationale:** lowered from medium to low: downstream-only, behind the same timeout as STO-4, and a narrow same-URI window.
- **Trace:** a stored async and removed → await times out → `shutdownNow` (running task continues) → new executor, `clearResourceSet` → cluster 2 loads a from its partial binary (`orig_mainpartial`, `orig_NoDetachedStore`).
- **Models:** `formal/binary-storage/tla/NOTES.md`, `formal/binary-storage/lean/NOTES.md`.
- **Test:** proposed (STO-PR3): after the await returns, all running stores' completion flags are true (ordering-flag assertion).

#### STO-6 — After a link exception the resource is detached but still stored

- **Severity / status:** low / PLAUSIBLE. **Verification:** phase B 1/3 upheld, 3 overstated; suggested severity low, none, none. **Origin:** TLA+ round 2 storage B4 (CONFIRMED, low).
- **Locations:** MCBS:608, MCBS:654.
- **Claim (as raised):** When linking throws after `addResource`, the outer catch removes the resource (MCBS:608) but execution falls through to `storeBinaryResource` (MCBS:654); an NPE follows, DLRSF deletes the storage, and a valid binary is lost.
- **Why only PLAUSIBLE:** All three agree the store of a detached resource happens; they reject the "valid binary lost" part.
  - Skeptic 1 (upheld, narrowed): an NPE (`PortableURIs.toPortableURI` on the null resource set) occurs only if the resource references anything outside itself. The deletion that follows is harmless: the catch registers a delete delta (MCBS:610-612), and the binary was stale anyway because the resource was being rebuilt. The real effect is a misleading ERROR log. A resource with no cross-resource references serialises without an NPE, writing a binary of a resource whose linking threw.
  - Skeptic 2 (refuted): DDK's save path never touches `getResourceSet`, so there is no NPE. If linking recorded errors, DLRSF deletes the stale binary by design (DLRSF:66 Javadoc, 77-79); otherwise a fresh binary is written. That is an overwrite, not a loss. For Check (`BatchLinkableResourceStorageFacade`), `getSourceContainerURI` NPEs, the delete NPEs too, and the old binary is kept.
  - Skeptic 3 (refuted): serialisation does not need the resource set; no NPE, no data loss. Keeping the binary, as a skip-only fix would, is arguably worse than deleting it.
- **Trace:** `orig_NoDetachedStore` with AllowLinkFail: exception → `resources.remove` (608) → `storeBinaryResource` (654).
- **Models:** `formal/binary-storage/tla/NOTES.md`.
- **Test:** proposed (STO-PR4).

#### STO-7 — Swallowed `IOException` in `writeResource` keeps the old binary while the URI leaves sources

- **Severity / status:** — / REFUTED. **Verification:** phase B 0/3 upheld the claim as raised: 2 refuted it outright; the third did not formally refute, but upheld only a different claim (a non-DLRSF facade, practically unreachable, now STO-A4) and said the finding "frames the problem around DLRSF, where it cannot occur". 2 overstated. **Origin:** Lean round 2 storage B6 (CONFIRMED code path, rare trigger).
- **Locations:** MCBS:754; RSF:97-103.
- **Claim (as raised):** RSF catches the `IOException` from `writeResource` and keeps the old file; MCBS:754 then removes the URI.
- **Why refuted:** For DLRSF, `DirectLinkingResourceStorageWritable.writeEntries`/`writeNodeModel` wrap every `IOException` and `RuntimeException` in `WrappedException` (128-134, 145-146), so RSF's `catch (IOException)` is never reached. DLRSF catches it, deletes the old storage and rethrows (77-80); MCBS:761 logs it and 754 is skipped. The only `IOException` left for RSF to swallow would come from `ZipOutputStream.close()` into an in-memory `ByteArrayOutputStream`, which does not throw. The location is also wrong: the removal that matters is the unconditional one at MCBS:656 (STO-1), not 754. The residual gap belongs to a different facade (STO-A4). Lean `ioFail` models RSF's swallow but not DDK's wrapping.
- **Models:** `formal/binary-storage/lean/NOTES.md`.
- **Test:** none. STO-PR0 may add a characterization test that documents the refutation.

#### STO-8 — `InterruptedException` during the storage await swallowed without restoring the flag

- **Severity / status:** low / CONFIRMED. **Verification:** phase B 3/3 upheld, 3 overstated; suggested severity low ×3. **Origin:** TLA+ round 2 storage, side note in B3 (no verdict).
- **Locations:** MCBS:865.
- **Claim:** The catch at MCBS:865-867 does not re-interrupt the thread.
- **Narrowed (skeptics):** Build cancellation uses `IProgressMonitor`, and nothing in DDK interrupts the builder thread. Latent hygiene, matching `ParallelResourceLoader`.
- **Models:** `formal/binary-storage/tla/NOTES.md` (B3).
- **Test:** proposed (STO-PR5): interrupt a helper that is TIMED_WAITING in the await.

#### STO judge

- **Winner:** Plan A.
- **Common ground:** Both plans delete MCBS:656, keep the load/await order at 667-674, skip the store and delete the binary after the outer catch (STO-6), drain running stores and delete dropped ones (STO-4/5), restore the interrupt after executor recreation (STO-8), and treat STO-7 as refuted (no fix). Line numbers checked against the worktree.
- **Rationale:** (1) Plan B's first PR extracts a `BinaryStorageCoordinator`: a production refactor of protected, downstream-overridden API that closes no finding, with tests that bypass `doUpdate` and the loader path. Plan A's STO-PR0 is test-only and exercises the real `doUpdate`, including STO-3's loader-side `contains()`. (2) For STO-2, Plan A's locked view preserves set identity with Xtext's `BuildContext` and is exactly the model-checked TLA FixB. Plan B's per-load-op snapshot is unmodelled and changes the live-view contract (Javadoc 1519-1522); its fallback `ConcurrentHashMap.newKeySet` breaks set identity. (3) Plan B's `ExecutorService.close()` waits in one-day chunks with no logging. Plan A also spells out that restoring the interrupt inside the drain loop would spin.
- **Grafts from Plan B:** `invalidateBinaryResoureCache` alongside `deleteBinaryResources`; an ordering-flag assertion for STO-5; drive STO-8 by interrupting a TIMED_WAITING helper; FixA alone leaves a racy-`contains` residue (Lean `raceNondet`, P3stale), so STO-2 lands before the executor PRs; builder confinement and `ConcurrentHashMap` kept as documented alternatives; the harness must call `updateBinaryStorageAvailability` with a non-null store, and end-to-end validation has to happen downstream; the STO-6 PR-body framing (the deleted binary is outdated; the defect is serialising a detached resource). Follow-ups it named are STO-A1 and STO-A3 ([4.3](#43-verified-observations)), plus an optional end-of-build ledger ("delete binaries of URIs processed but not stored") only if gaps remain.

#### STO fix plans

Test files named below live in `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/builder/`. **Version bump:** `com.avaloq.tools.ddk.xtext.builder` 17.3.1 → 17.3.2, by the first production PR on this bundle in the cycle (shared with LDR). `xtext.test`: none (already 17.3.2 against baseline 17.3.1).

**STO-PR0 (test harness, no finding)**

- A test subclass of MCBS in package `com.avaloq.tools.ddk.xtext.builder` inside `xtext.test`, constructed with a mock `IXtextTargetPlatformManager`, with `@Inject` members from a Guice module of mocks and fakes: `InMemoryFileSystemAccess`, a mock DLRSF, a null `IResourceDescription.Manager` (linking skipped, store still runs), and a fake `RESOURCELOADER_CROSS_LINKING` loader. It calls `updateBinaryStorageAvailability` with a non-null `IBinaryModelStore`. Seam: a latch-gated override of `doStoreBinaryResource`. Includes a positive control and optionally the STO-7 characterization test. Register in `XtextTestSuite`. No bump.

**STO-PR1 (STO-1, STO-3)**

- **Fix:** Delete MCBS:656 and nothing else. A URI then leaves sources only at MCBS:754, after a successful save (under `CallerRunsPolicy` the builder runs the same line). Do not reorder `load(queue)` and `clearResourceSet`: FixA alone closes the cross-cluster and within-cluster reads (TLA `orig_loaderpartial_M`), and the current order keeps storing and loading overlapped.
- **Files:** MCBS, builder MANIFEST/pom, `MonitoredClusteringBuilderStateStorageTest.java` (new), `XtextTestSuite.java`.
- **Test strategy:** All three fail on master. (a) Cluster {a,b}: while a's store is latched, `contains(a)` is true. (b) Load failure `LoadOperationException(a, IOException)`: `contains(a)` is true. (c) Within a cluster, b's fake load sees `contains(a)` true while a's store is latched. Control (d): after `doUpdate`, `contains(a)` is false.
- **Risks:** Performance only: a dependency with a pending store is parsed instead of read from a binary. With storage disabled, processed URIs stay sources for the rest of the build, as upstream. A downstream override of `storeBinaryResource`/`doStoreBinaryResource` that never removes the URI loses binary loads within the same build; say so in the PR body. The cross-build part of the load-failure variant is closed by STO-PR4.
- **Model evidence:** TLA `ablateA` violates NotSourceOnlyWhenStored; `fixed_S/M/M2/L/L2/R` pass P1, LoaderNoPartialRead, MainNoPartialRead and NoStaleRead. Original traces: `orig_P1_nofail`, `orig_NotSourceOnlyWhenStored`, `orig_LoaderNoPartialRead`, `orig_loaderpartial_M`, `orig_NoStaleRead`. Lean `Generic.fixed_safe` proves P1 and P3 for all sizes; `buggy_unsafe`, `bug_p1`, `bug_partial`, `bug_stale_no_timeout` fail on the original.
- **Upstream:** Not shared.

**STO-PR2 (STO-2)**

- **Fix:** One monitor, the Xtext-owned `HashSet`. In `installSourceLevelURIs` (MCBS:1536), install a private `LockedSourcesView extends AbstractSet<URI>`: `contains`/`size`/`isEmpty` under `synchronized (delegate)`, iteration on a copy, `add`/`remove` throw. PRL picks it up through the adapter unchanged. Wrap MCBS:754, 1298 and 1314 in `synchronized (sources)`; comment 452/1528 as pre-publication writes. Assumes STO-PR1 has landed (otherwise 656 needs the lock too).
- **Files:** MCBS, `MonitoredClusteringBuilderStateStorageTest.java`.
- **Test strategy:** Lock contract, no stress test. (a) While the test holds the lock, a helper calling `contains()` reaches BLOCKED. (b) A worker at 754 blocks while the lock is held and removes the URI after release. (c) `add()` on the view is rejected.
- **Risks:** Negligible uncontended lock cost. A future writer could forget the lock; a class comment mitigates. No nesting, so no deadlock risk.
- **Model evidence:** TLA `ablateB` violates SetThreadSafe, including in the happy environment; `orig_SetThreadSafe` (11 states), `orig_adr` (15), `orig_rdw` (19). Lean `bug_p4` fails in 7 steps; `raceNondet` reaches P3stale in 8. The view implements the model-checked FixB.
- **Upstream:** Not shared; comes from DDK's `setSourceLevelUrisWithoutCopy` plus the asynchronous store.

**STO-PR3 (STO-4, STO-5)**

- **Fix:** (1) Replace the lambda at MCBS:726 with an inner `BinaryStoreTask` exposing `getUri()`, so `shutdownNow` returns identifiable tasks. (2) After `shutdownNow`, `terminateBinaryStorageExecutor` logs the dropped count at WARN and the URIs at INFO (the list can reach 15,000 entries), then calls `deleteBinaryResources` and `invalidateBinaryResoureCache` on them. It then drains, `while (!isTerminated()) awaitTermination(...)`, warning the active count each round and remembering any interrupt. All before the executor is recreated (871) and before `clearResourceSet` (1207). (3) Do not restore the interrupt here (STO-PR5). Do not use `ExecutorService.close()`.
- **Files:** MCBS, `MonitoredClusteringBuilderStateExecutorTest.java` (new), `XtextTestSuite.java`.
- **Test strategy:** A blocking `doStoreBinaryResource` that sets a completion flag in finally; submit 6 stores (4 run, 2 queue). STO-4: an await with a 10 ms timeout must `deleteStorage(u5/u6)` and never u1-u4. STO-5: release the latch once `isShutdown()`; when the await returns, all u1-u4 completion flags are true.
- **Risks:** The drain is unbounded, so a hung store (e.g. a stalled NFS) blocks the build; the periodic WARN makes it visible. A cap would reopen STO-5; state the trade-off in the PR body. Deleting dropped binaries costs a re-parse in the next build.
- **Model evidence:** TLA `ablateC` violates NoDetachedStore; the fixed models pass NoDetachedStore, MainNoPartialRead and StoresAccounted (MaxTimeouts 1, 2), and WitnessTimeout is still reached. Original traces `orig_mainpartial` (26), `orig_NoDetachedStore` (19). Lean `bug_p2` (35 steps) and `bug_pEnd_timeout` (38) fail; `fixed_default`, `fixed_caller_runs`, `fixed_no_old_binary` pass. The cross-build deletion is backed by Lean only.
- **Upstream:** Not shared.

**STO-PR4 (STO-6; optional: STO-A2)**

- **Fix:** In the outer catch (MCBS:585-613), set `resource = null` after removing it from the set, so the cache clear (651) and the store (654) skip it. If `changedURI != null`, call `deleteBinaryResources` and `invalidateBinaryResoureCache` on it; this also closes the cross-build part of the STO-1 load-failure variant. Optional: the STO-A2 null guard in `deleteBinaryResources` (MCBS:782).
- **Test strategy:** (a) The manager's `getResourceDescription` throws: no store for a, and `deleteStorage(a)` is called. (b) A load failure deletes a pre-seeded binary.
- **Risks:** One synchronous delete, on the failure path only. Frame the PR as "do not serialise a detached resource", not as fixing a lost valid binary (status PLAUSIBLE). A timeout `LoadOperationException` has no URI, so nothing is deleted there. The half-linked store after a `StackOverflowError` is STO-A3 and not covered.
- **Model evidence:** TLA `ablateD` violates NoDetachedStore (8-state AllowLinkFail trace); TLA FixD is skip-only. The deletion is backed by Lean `fixed_no_old_binary` (P5).
- **Upstream:** Not shared.

**STO-PR5 (STO-8)**

- **Fix:** At the end of `awaitBinaryStorageExecutorTermination(int, TimeUnit, int)`, after the executor is recreated (MCBS:871-873), restore the interrupt if one was caught. Not inside the catch, or the drain loop spins.
- **Test strategy:** A helper TIMED_WAITING in a 1-minute await is interrupted, then the latch is released. The helper sees `isInterrupted()` true afterwards, and the store completed before the await returned.
- **Risks:** The next await in the same build takes the `shutdownNow` path; after STO-PR3 those drops are deleted and logged, so the cost is performance only. Must land after LDR-PR2 and STO-PR3.
- **Model evidence:** Code reading (TLA NOTES B3); `AwTimeout`, `ablateC` and the fixed models cover the drain it depends on.
- **Upstream:** Not shared.

**STO-7: no fix.** Refuted because DDK's DLRSF writable wraps every `IOException`, so the swallowing catch in RSF is unreachable for DLRSF and the old binary is deleted, not kept (see STO-7). Upstream: latent in Xtext 2.44 `RSF.saveResource`, harmless there. Review guidance only: a future facade whose writable lets a raw `IOException` through would reopen the gap (STO-A4).

### 3.4 TRIE — qualified-name lookup

**Reachability:** `ContainerQuery`, `PrefixedContainerBasedScope` and `ContainerBasedScope` use `QualifiedNamePattern.create*` in production. `QualifiedNamePattern.createFromGlobs` has no production caller (only `QualifiedNamePatternTest` and `QNLFT`); `TreeSetLookup` and `shareValues=true` have no production user. So TRIE-2, which shows the same symptom class as TRIE-12/13/14, is high while the glob findings are low: the glob API is latent until a downstream caller uses it.

All TRIE test methods except `PAEDLT` live in `QNLFT`. Their red/green results were confirmed under the Tycho aggregator: red on master; green with `fix-plan.patch` plus the `com.avaloq.tools.ddk.xtext` bump, except `testMaxCharPattern` (TRIE-9, by design). With the patch, 5 existing `QualifiedNamePatternTest` methods (`testPatternWithoutWildcard`, `testRegexpPatterns`, `testAllPattern`, `testRecursiveWildcardPattern`, `testQualifiedPrefixNamePattern`) fail because they assert the old `"!"` bound; the fix PRs must update them. `QNLFT` needs `Import-Package: com.avaloq.tools.ddk.caching` in the `xtext.test` manifest (added in this PR).

#### TRIE-1 — Case-sensitive pattern queries in `PatternAwareEObjectDescriptionLookUp` always return nothing

- **Severity / status:** high / CONFIRMED. **Verification:** phase A 3/3 upheld three times. **Origin:** trie-lean B8, trie-tla F2, ro-selfmatch #0 (all CONFIRMED).
- **Fix PR:** [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551) (draft) — PAEDL:63 matches against `input.getName()`; `PAEDLT` moves into the fix PR with its tests enabled.
- **Locations:** PAEDL:63, QNP:263, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/scoping/ContainerQuery.java:300`.
- **Claim:** PAEDL:63 calls `((QualifiedNamePattern) name).matches(name)`, testing the pattern against itself, and `matches` returns false for any `QualifiedNamePattern` argument (QNP:263-264). So `getExportedObjects(type, pattern, false)` is always empty (Lean `All.consumer_cs_empty`). This affects `ResourceDescription2`, `SimpleResourceDescription`, `FixedCopiedResourceDescription` and `FingerprintResourceDescription` for case-sensitive `ContainerQuery` name patterns. Present since the initial contribution, 4e2c12841.
- **Trace:** descriptions {Foo, FooBar, foobar, Other}; `create("Foo*")` case-sensitive → [] (expected [Foo, FooBar]); `create("Other")` → [].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`, `formal/trie/lean/fix-plan.patch`.
- **Test:** `PAEDLT#testCaseSensitivePatternMatchesCandidateNames` and `#testCaseSensitiveExactPattern` (`@Disabled`, enabled in [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551); the other 3 methods are guards that pass and run), and `QNLFT#testCaseSensitivePatternQuery` (`@Disabled`). All pass with the fix.

#### TRIE-2 — Top-level `*` / `**` pattern uses upper bound `"!"` and misses almost every name

- **Severity / status:** high / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B1, trie-tla F1.
- **Locations:** QNP:372, QNP:374, QNSTL:113, QNSTL:190, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/scoping/PrefixedContainerBasedScope.java:69`.
- **Claim:** `upperExclusive()` returns `QualifiedName.create("!")` for a single-segment `*`/`**`, so every name starting with a character ≥ `!` (letters, digits, `_`, `$`) falls outside the range; both lookups return nothing (Lean `All.topStar_unsound`). Reachable in production via `ContainerQuery.Builder.name("*")` and `PrefixedContainerBasedScope` with an empty prefix.
- **Trace:** `put(("b"),v)`; `get(pattern("*"))`: `find(("!"))` returns node b, taken as the stop marker → [].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testTopLevelWildcardFindsSingleSegmentNames` (tree and `TreeSetLookup`) and `#testTopLevelRecursiveWildcardFindsAllNames`, both `@Disabled`.

#### TRIE-3 — `'!'` is not the successor of a segment, so names with a trailing char below `'!'` leak into results

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean B2, trie-tla F3); the merged trie-tla F4 was 2/3. **Origin:** trie-lean B2, trie-tla F3, trie-tla F4 (sibling-loop manifestation).
- **Locations:** QNP:379, QNP:386, QNP:363, QNSTL:200, QNSTL:223, PAEDL:62.
- **Claim:** Appending `'!'` is not a successor: for any c < `'!'`, [s] < [s+c] < [s+"!"] (Lean `All.bang_not_successor`). Pattern `"a"` returns a stored `"a "`, and `"a.*"` returns descendants of a sibling `"a "`. Neither the trie nor the ignore-case consumer re-checks `matches()`. Appending `'\u0000'` is proved to be the exact successor. Low because it needs names containing whitespace or control characters.
- **Trace:** `put(("a "),v)`; `get(pattern("a"))` → [v]. `put(("a ","x"),1)`, `put(("a","b"),2)`; `get(pattern("a","*"))` → [2,1].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testExactPatternExcludesNameWithLowCharSuffix`, `#testChildWildcardExcludesSiblingWithLowCharSuffix` (`@Disabled`).

#### TRIE-4 — A wildcard-free pattern's `matches()` accepts longer names

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B3, trie-tla F5.
- **Locations:** QNP:304, QNP:278, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/scoping/ContainerBasedScope.java:86`.
- **Claim:** The non-glob loop never compares segment counts, so `pattern("a").matches(("a","b"))` is true (Lean `All.exact_matches_extensions`) while the lookups return only `("a")`. `ContainerBasedScope` filters with `matches()` and so accepts longer names for programmatic wildcard-free pattern criteria.
- **Trace:** store {a:0, a.b:1}; `get(pattern("a"))` = [0], but `matches` accepts a.b.
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testExactPatternMatchesOnlyEqualLength` (`@Disabled`).

#### TRIE-5 — Statistics size over-counts on duplicate put/putAll

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B5, trie-tla F6.
- **Locations:** QNSTL:590, QNSTL:601, QNSTL:639, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/util/ArrayUtils.java:83`.
- **Claim:** `put()` does `size++` unconditionally and `putAll` adds `values.size()`, even when the value is already present. After a remove, statistics report entries for an empty lookup, and `initializeFrom`'s `size>0` guard (:639) then refuses it.
- **Trace:** `put(a,v)` twice → size=2; `remove(a,v)` → size=1, `get(a)`=null.
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testSizeCountsMappingsOnce` (`@Disabled`).

#### TRIE-6 — `putAll` stores duplicate values, so a single remove leaves the value mapped

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-tla). **Origin:** trie-tla F7.
- **Locations:** QNSTL:263, `ArrayUtils.java:79`, `ArrayUtils.java:83`.
- **Claim:** A new node takes `newValues` as-is (:263/:436). `ArrayUtils.addAll` returns the values unchanged for a null or empty target, and dedupes against the original array instead of the growing result. `putAll(n,[v,v])` stores v twice, and one remove leaves it mapped. `TreeSetLookup` shares `ArrayUtils` and behaves the same.
- **Trace:** TLC `op_exact`: `putAll(a,[v1,v1])`; `remove(a,v1)` → `get(a)`=[v1].
- **Models:** `formal/trie/tla/NOTES.md`.
- **Test:** proposed (TRIE-PR6 cases b-d). Not in `QNLFT`, and `fix-plan.patch`'s size fix does not address it.

#### TRIE-7 — `getMappings` drops blank intermediate segments

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B6, trie-tla F10.
- **Locations:** QNSTL:295.
- **Claim:** `!segment.isBlank()` is meant to skip the root but also skips real empty or whitespace segments, so `(" ","a")` comes back as `("a")`.
- **Trace:** `put((" ","a"),v)`; `getMappings(v)` → [("a")]; `TreeSetLookup` → [(" ","a")].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testGetMappingsKeepsBlankSegments` (`@Disabled`).

#### TRIE-8 — With `shareValues=true`, `get(pattern, false)` collapses multiplicities

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B7, trie-tla F8.
- **Locations:** QNSTL:406, QNSTL:436.
- **Claim:** `ValueSharingSegmentNode.matches` collects arrays in an identity-comparing `HashSet<Object[]>`, so a shared array is counted once while distinct equal arrays are counted each time. `shareValues=true` has no production caller in this repo.
- **Trace:** `shareValues=true`: `put(a,v)`, `put(a.b,v)`; `get(pattern("a**"), false)` → [v]; the reference gives [v, v].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testValueSharingKeepsMultiplicity` (`@Disabled`).

#### TRIE-9 — U+FFFF wraps the upper bound and collides with the tree's sentinel

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B9, trie-tla F13.
- **Locations:** QNP:389, QNP:367, QNSTL:520, QNSTL:174.
- **Claim:** `(char)(c+1)` wraps U+FFFF to U+0000. `TreeSetLookup.get` then throws `IllegalArgumentException` (fromKey > toKey), and the trie never meets the bound. A stored name `"￿"` merges into the sentinel node. A fix needs a design decision.
- **Trace:** `new TreeSetLookup().get(pattern("￿*"), false)` → IAE; tree `put("￿", m)`: `get(pattern("￿"))` → [], but `get(name)` → [m].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testMaxCharPattern` (`@Disabled`). It still fails with `fix-plan.patch` by design: the patch and the Lean proofs assume no U+FFFF in names (assumption A1). It stays disabled until TRIE-PR10 is decided.

#### TRIE-10 — `TreeSetLookup` returns spurious names for patterns with an empty segment

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean), 3/3 (trie-tla). **Origin:** trie-lean B4, trie-tla F14.
- **Locations:** QNP:224, QNP:340, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/naming/TreeSetLookup.java:66`.
- **Claim:** `compareTo` returns -1 as soon as a pattern segment is empty (:224-225), which breaks `subMap` bounds. The segment tree is correct. `TreeSetLookup` is the reference implementation, with no production user.
- **Trace:** `put(("",""),e)`; `get(pattern(""," "))` → [e]; the tree gives [].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testTreeSetLookupEmptySegmentPattern` (`@Disabled`).

#### TRIE-11 — `initializeFrom` aliases the source's mutable tree

- **Severity / status:** low / CONFIRMED (promoted: originating verdict PLAUSIBLE, 3/3 upheld). **Verification:** phase A 3/3 (trie-tla). **Origin:** trie-tla F9 (PLAUSIBLE), trie-lean observation (PLAUSIBLE).
- **Locations:** QNSTL:645, QNSTL:646.
- **Claim:** The root reference is copied, so a later put or clear mutates both lookups while each keeps its own, now stale, size. The Javadoc says "shallow copy, values are shared", so whether this is a bug depends on the contract. No caller in this repo.
- **Trace:** TLC `op_copy`: `B.initializeFrom(A)` with A empty, then `A.put(a,v1)` → B's tree holds 1 mapping while `B.size` is 0.
- **Models:** `formal/trie/tla/NOTES.md`, `formal/trie/lean/NOTES.md`.
- **Test:** proposed (TRIE-PR11, option 1 only). None until the contract is decided, because under option 2 the behaviour is correct.

#### TRIE-12 — Glob patterns inherit the `"!"` upper bound; a wildcard-free glob gets range [`""`,`"!"`) and matches nothing

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean B10), 3/3 (ro-selfmatch #1), 3/3 (trie-tla F1). **Origin:** trie-lean B10 (part), trie-tla F1 (glob side-claim), ro-selfmatch #1.
- **Locations:** QNP:315, QNP:332, QNP:357, QNP:359, QNP:363.
- **Claim:** Three parts, fixed in three steps. (a) The glob upper-bound branch uses `"!"` (:357-359), so `createFromGlobs("*")` finds nothing. (b) The glob segment suffix is `'!'` (:363), as in TRIE-3. (c) `firstWildcardSeg` is initialised to 0 (:315), so the `!= -1` guard (:332) is always true and a wildcard-free glob gets [`""`,`"!"`). Low because `createFromGlobs` has no production caller (see the cluster note).
- **Trace:** tree {abc, foo.bar}: `createFromGlobs("abc")` → [] although it matches; `createFromGlobs("ab*")` → [abc].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** part (a): the `createFromGlobs("*")` assertion in `QNLFT#testGlobLookupsFindMatches` (`@Disabled`). Parts (b) and (c): proposed. TRIE-PR3 updates the glob `"!"` expectations, and TRIE-PR9 adds `createFromGlobs("abc")` and `("foo","bar")` found with an exact `lowerInclusive()`. The wildcard-free case previously had only an ro-selfmatch scratch driver.

#### TRIE-13 — Globs ending in `*` match deeper names, but the tree walks only one level

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean B10). **Origin:** trie-lean B10 (part).
- **Locations:** QNP:156.
- **Claim:** `isRecursivePattern()` is false for globs, so the tree walks only the level of the lower bound. `("a*","b")` gives [] in the tree and [ab] in `TreeSetLookup`. Low: glob API, no production caller.
- **Trace:** `put(a.x)`; `get(createFromGlobs("a*"))` → [].
- **Models:** `formal/trie/lean/NOTES.md`.
- **Test:** the `"a*"` assertion in `QNLFT#testGlobLookupsFindMatches` (`@Disabled`).

#### TRIE-14 — Glob regexps are case-insensitive while the lookup bounds are case-sensitive

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean B10), 3/3 (trie-tla F11). **Origin:** trie-lean B10 (part), trie-tla F11.
- **Locations:** QNP:101, QNP:367, `com.avaloq.tools.ddk/src/com/avaloq/tools/ddk/caching/Regexps.java:54`.
- **Claim:** `Regexps.fromGlob(glob)` defaults to `ignoreCase=true` while the bounds use case-sensitive order. A glob `"F*"` matches `foo`, but the lookup misses it. Low: glob API, no production caller.
- **Trace:** `put(foo)`; `get(createFromGlobs("F*"))` → [].
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** the `"F*"` assertion in `QNLFT#testGlobLookupsFindMatches` (`@Disabled`).

#### TRIE-15 — Glob `matches()` throws `StringIndexOutOfBoundsException` on an empty last segment

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (trie-lean B10), 3/3 (trie-tla F12). **Origin:** trie-lean B10 (part), trie-tla F12.
- **Locations:** QNP:268.
- **Claim:** `lastSeg.charAt(lastSeg.length()-1)` throws when the last glob segment is empty. Low: glob API, no production caller.
- **Trace:** `createFromGlobs("a","").matches(("a",""))` → SIOOBE.
- **Models:** `formal/trie/lean/NOTES.md`, `formal/trie/tla/NOTES.md`.
- **Test:** `QNLFT#testGlobWithEmptyLastSegment` (`@Disabled`).

#### TRIE-16 — `put(QualifiedName.EMPTY, v)` increments size and then throws

- **Severity / status:** low / CONFIRMED (promoted: originating verdict PLAUSIBLE, 3/3 upheld). **Verification:** phase B 3/3 upheld, 2 overstated; suggested severity low ×3. **Origin:** trie-lean observation (PLAUSIBLE, not modelled).
- **Locations:** QNSTL:590.
- **Claim:** `size++` runs before `merge`, and `merge` calls `getSegment(0)`, which throws for `EMPTY`.
- **Narrowed (skeptics):** The sole production caller, PAEDL, discards the half-built lookup, so the inflated count is never observed. The practical issue is the exception type (AIOOBE instead of IAE).
- **Models:** `formal/trie/lean/NOTES.md` ("Observations").
- **Test:** proposed (TRIE-PR6 case e).

#### TRIE fix plans

Single plan, no judge. **Version bumps (all TRIE PRs):** none right now: `com.avaloq.tools.ddk.xtext` is 17.4.1 against baseline v19.2.0 = 17.4.0 (bumped in d3b6e083a), and `com.avaloq.tools.ddk.xtext.test` is 17.3.2 against 17.3.1. If a release is published before a merge, micro-bump both (MANIFEST + pom). `Regexps` in `com.avaloq.tools.ddk` is called, not changed. No TRIE defect is shared with upstream Xtext; all affected classes are DDK-only.

Common test rules: ship each fix and its test in one commit (history is rebase-merged). Port the `QNLFT` methods into the already-registered `QualifiedNamePatternTest` and `QualifiedNameSegmentTreeLookupTest`, removing them from `QNLFT` as they are ported. Show red on master and green with the fix under the aggregator, not only the javac harness. Strip `formal/` references from ported test comments.

**TRIE-PR1 (TRIE-1)**

- **Fix:** PAEDL:63 becomes `.matches(input.getName())`; `name` stays the caller's original-case pattern.
- **Files:** PAEDL, `PAEDLT` (enable).
- **Test strategy:** Enable the two `@Disabled` `PAEDLT` methods; the other 3 are guards.
- **Risks:** Visible behaviour change: case-sensitive `ContainerQuery` executions with a name pattern have returned [] since 4e2c12841 and now return results. Downstream code may have compensated, so a downstream smoke run is worthwhile. Single-element lookups are unaffected. A top-level `*` still returns nothing until TRIE-PR2.
- **Model evidence:** Lean `All.consumer_cs_empty`; ladder P4 fails until the "+F8 consumer" rung. TLA `co_cs` and `co_cs_fb` violate ConsCS (needed independently of the bounds); `co_fixed` passes; `co_wit` is reachable.

**TRIE-PR2 (TRIE-2, TRIE-12)**

- **Fix:** At QNP:374 (TRIE-2) and :359 (TRIE-12 step a), replace `QualifiedName.create("!")` with a private constant `UNBOUNDED = QualifiedName.create(String.valueOf(Character.MAX_VALUE))`, commented as equal to the sentinel from `QNSTL.init()` (:520).
- **Files:** QNP, `QualifiedNamePatternTest.java`, `QualifiedNameSegmentTreeLookupTest.java`.
- **Test strategy:** Port the two top-level-wildcard tests and the `createFromGlobs("*")` assertion. Add a consumer assertion: `getExportedObjects(ECLASS, create("*"), true)` over {b} returns b. In the same commit update `QualifiedNamePatternTest.testAllPattern` and `testRegexpPatterns` (`createFromGlobs("*")`, `("?")`, `("?foo*bar*")`).
- **Risks:** Top-level `*` and empty-prefix scopes now enumerate the whole container: correct, but potentially costly on large indexes; say so in the PR body. Relies on every live tree, including deserialized ones, holding the U+FFFF sentinel. Names starting with U+FFFF stay excluded (TRIE-9).
- **Model evidence:** Lean `All.topStar_unsound`; "+F1 topUpper" rung. TLA `pb_rs` violates RangeSound; `tq_spec`/`co_ci` fail on store {a} with `*`; `co_ci_fb` passes; `tq_fixed`/`tq_fixed5` pass.

**TRIE-PR3 (TRIE-3, TRIE-12)**

- **Fix:** Append `'\u0000'` instead of `'!'` at QNP:363 (TRIE-12 step b), :379, :382 and :386 (TRIE-3). The empty-segment special case (:380-383) collapses into the general case. Optional helper `successor(QualifiedName)`. The `(char)(c+1)` successor is left for TRIE-9.
- **Test strategy:** Port the two low-char tests. Add an ignore-case consumer assertion: `create("a")` over {a, "a "} returns only a. Update the `"foo!"` → `"foo\u0000"` expectations in `testQualifiedPrefixNamePattern`, `testRecursiveWildcardPattern`, `testPatternWithoutWildcard` and `testRegexpPatterns`.
- **Risks:** Result sets only shrink, and only for names with chars below `'!'`. Downstream code comparing against a literal `"!"` would break. Touches the same method as TRIE-PR2: merge after it, or combine.
- **Model evidence:** Lean `All.bang_not_successor`, `All.nul_is_successor`, "+F2 nulSucc". TLA `tq_spec`/`tq_star`/`tq_ref` fail; `tq_fixed`/`tq_fixed5` (35,443 stores) pass; `pb_rs2` shows the bug only over-includes.

**TRIE-PR4 (TRIE-4)**

- **Fix:** In the non-glob branch of `matches`, after the loop (:278-302): `return other.getSegmentCount() == getSegmentCount();`.
- **Test strategy:** In `QualifiedNamePatternTest`: `!create("a").matches(("a","b"))` and `create("a").matches(("a"))`; keep the tree-side consistency assertion.
- **Risks:** `ContainerBasedScope` (:86/:116) stops admitting longer names for programmatic wildcard-free pattern criteria, which then fall back to the parent scope.
- **Model evidence:** Lean `All.exact_matches_extensions`, "+F3 exactLen". TLA `pb_cnt1` violates CountFilterSound; `pb_fixed` passes on 714,096 pairs.

**TRIE-PR5 (TRIE-10)**

- **Fix:** Delete the empty-segment `return -1` (QNP:224-225).
- **Test strategy:** `testTreeSetLookupEmptySegmentPattern`, plus `("","b")` vs `("","a")`; extend `testComparison`.
- **Risks:** The public `Comparator` changes order only for empty-segment patterns, exactly where it was wrong.
- **Model evidence:** Lean "+F4 cmpEmpty"; P5 (subMap monotone) holds in every variant. TLA `tq2_ref` fails; `tq_fixed_ref`/`tq_fixed5` pass.

**TRIE-PR6 (TRIE-5, TRIE-6, TRIE-16)**

- **Fix:** (1) `ArrayUtils.addAll` dedupes against the growing result and routes the null/empty case through the same loop, returning `values` unchanged when it has no duplicates. (2) `putAll` passes a de-duplicated array to `merge`. (3) The private `merge` methods return the number of values added. (4) `put`/`putAll` do `size += root.merge(...)`, so a failed `put(EMPTY)` leaves size unchanged; an explicit `IllegalArgumentException` for empty names is optional. This replaces `fix-plan.patch` F5, which walks the tree twice and still counts `putAll(n,[v,v])` as 2. The value-sharing identity check (:436/:442) must compare against the de-duplicated array.
- **Files:** `ArrayUtils.java`, QNSTL, `QualifiedNameSegmentTreeLookupTest.java`.
- **Test strategy:** (a) put twice → 1 entry, remove → 0 (TRIE-5). (b) `putAll(a,[v,v])`, remove → unmapped, 0 entries (TRIE-6). (c) `put(a,w)`; `putAll(a,[v,v])` → [w,v], 2 entries. (d) (b) against `TreeSetLookup`. (e) `put(EMPTY)` throws and leaves 0 entries (TRIE-16). (f) After put/put/remove, `initializeFrom` succeeds. Run with `shareValues` both ways.
- **Risks:** `ArrayUtils` is a public util: `addAll(null|[], values)` now returns a new array when `values` has duplicates; the Javadoc identity promise still holds. `getEntries()` drops for re-puts. Trees deserialized from older caches keep the old size until rebuilt.
- **Model evidence:** TLA `op_sizetree`, `op_sizespec`, `op_nodup`, `op_exact` fail; `op_fixed`, `op_fixed5`, `op_fixed_sh`, `op_fixed_sh5` pass (TrieOps.tla:79/:88 model the length delta); `op_exactref` shows `TreeSetLookup` is also affected. Lean `All.addAll_present`, `All.put_size`, "+F5 size" (245,411 states). Lean does not cover TRIE-6; TRIE-16 is not modelled.

**TRIE-PR7 (TRIE-7)**

- **Fix:** Drop the `isBlank()` guard at :295. The outer `getMappings` iterates `root.children`, so the root's `""` is never prefixed.
- **Test strategy:** `testGetMappingsKeepsBlankSegments` for `(" ","a")` and `("","a")`, `shareValues` both ways; keep `testGetMappings` green.
- **Risks:** None beyond the correction.
- **Model evidence:** Lean "+F6 mappings". TLA `op_map` fails; `op_fixed`/`op_fixed_sh` pass.

**TRIE-PR8 (TRIE-8)**

- **Fix:** In `ValueSharingSegmentNode.matches` (:404-422), collect into a list when `excludeDuplicates` is false (`fix-plan.patch` F7), or delegate to `super.matches` in that case.
- **Test strategy:** `testValueSharingKeepsMultiplicity` → [v,v], plus a differential check against `shareValues=false` and `TreeSetLookup`. The `excludeDuplicates=true` result is unchanged.
- **Risks:** Negligible extra iteration; no in-repo caller.
- **Model evidence:** Lean P2 passes from "+F7 share" (20,876 stores × 315 patterns). TLA `op_share` fails; `op_bagns` passes as written; `op_fixed_sh`/`op_fixed_sh5` pass.

**TRIE-PR9 (TRIE-12, TRIE-13, TRIE-14, TRIE-15)**

- **Fix:** Lands after TRIE-PR2/3. (1) TRIE-12 step c: `firstWildcardSeg = -1` (:315); the else branch returns the exact plain name. (2) TRIE-13: `isRecursivePattern()` is true for globs. (3) TRIE-14: `Regexps.fromGlob(from, false)`; (3a) `toLowerCase()`/`toUpperCase()` (:188-190, :203-205) recompile the glob regexps from the case-mapped segments (missing from `fix-plan.patch`). (4) TRIE-15: :268 uses `endsWith` instead of `charAt(length-1)`. Alternative, given no production caller: deprecate `createFromGlobs` and ship only (4).
- **Test strategy:** One failing-first test per finding: `"a*"` over a.x, and `("a*","b")` tree == `TreeSetLookup` (TRIE-13); `"F*"` does not match foo but its `toLowerCase()` does (TRIE-14); `createFromGlobs("abc")` and `("foo","bar")` are found, with an exact `lowerInclusive()` (TRIE-12); an empty last segment does not throw (TRIE-15).
- **Risks:** Public API: glob case semantics and `isRecursivePattern()` change. Results remain candidate supersets.
- **Model evidence:** Lean P2g fails until "+F9 glob" and "+F10 globCase"; P1g holds under A1. TLA `pbg_rs` fails; `pbg_rs_fb` passes; `pbg_rs_fbA` fails (the case fix is needed); `pbg_exc` violates NoException; `pbg_fixed` passes on 874,104 pairs. **Caveat:** neither model covers (3a).

**TRIE-PR10 (TRIE-9): issue first**

- **Status:** deliberately left open by `fix-plan.patch` (assumption A1: no U+FFFF in names) until a design is chosen.
- **Fix options:** (a) a carry-aware prefix successor at :367/:389 that strips trailing U+FFFF and falls back to the parent's successor or `UNBOUNDED`; (b) an explicit unbounded upper bound plus a sentinel that name lookups cannot reach (structural; needs a serialization compatibility check); (c) document U+FFFF as reserved in the `QualifiedNameLookup`/`QNSTL` Javadoc. Recommended: (a) + (c), deferring (b).
- **Files:** QNP, QNSTL, `com.avaloq.tools.ddk.xtext/src/com/avaloq/tools/ddk/xtext/naming/QualifiedNameLookup.java`, `QualifiedNamePatternTest.java`.
- **Test strategy:** For (a): `TreeSetLookup.get(pattern("￿*"))` does not throw, and `pattern("a￿*").upperExclusive()` is `("b")`. `testMaxCharPattern`'s sentinel half stays `@Disabled` unless (b) is chosen.
- **Model evidence:** Lean P1b/P2m pass only under A1, so Lean gives no evidence for a U+FFFF fix. TLA `pb_ord` fails; `pb_fixed` passes with the carry-aware `PrefixLevelSucc`/`StripMax` and INF mapped to the sentinel. The trie-level collision is not covered.

**TRIE-PR11 (TRIE-11): issue first**

- **Fix options:** (1) a recursive structural copy that shares the value arrays (safe, because `ArrayUtils` is copy-on-write) and copies size; (2) keep the aliasing and document that the source must not be modified. Check downstream callers before choosing.
- **Test strategy:** Option 1: after `B.initializeFrom(A)`, neither `A.put` nor `A.clear()` affects B. Depends on TRIE-PR6.
- **Risks:** Option 1 makes `initializeFrom` O(n) instead of O(1).
- **Model evidence:** TLA `op_copy` violates CopySize; `op_fixed`/`op_fixed_sh` pass with FIX_COPY. Not modelled in Lean.

### 3.5 PIPE — release pipeline (`.github/`)

No upstream branch `v[0-9]*` exists today, so the maintenance-line findings (PIPE-1, PIPE-2, PIPE-4) become reachable when the first maintenance branch is cut. They stay high because that is a routine maintainer action, not a code change.

#### PIPE-1 — Maintenance-branch builds always fail the Tycho baseline gate

- **Severity / status:** high / CONFIRMED. **Verification:** phase A 3/3 (pipeline-lean), 3/3 (pipeline-tla). **Origin:** pipeline-lean F4, pipeline-tla F1 (part).
- **Locations:** `ddk-parent/pom.xml:64`, `ddk-parent/pom.xml:247`, `.github/scripts/publish-p2-ghpages.sh:69`, `.github/workflows/snapshot.yml:67`, `.github/workflows/verify.yml:79`, `.github/workflows/release.yml:74`.
- **Claim:** `baseline.repo.url` is `p2/releases/latest`, the highest release overall, and `compare-version-with-baselines` runs on every build (pom.xml:247-263). After master releases a higher version, a `v[0-9]*` branch fails with "Version has moved backwards" (`onIllegalVersion` defaults to fail in tycho-p2-extras 5.0.4). Maintenance snapshot, PR and release builds all fail. Independent of PIPE-2.
- **Trace:** push v18.x → snapshot 18.0.1 against baseline v19.0.0 → gate fails. W5/W2 are unreachable in all 254k states.
- **Models:** `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/NOTES.md`, `formal/pipeline/tla/logs/asis_live_maint.log`.
- **Test:** proposed (PIPE-PR4): offline `test-resolve-baseline.sh`.

#### PIPE-2 — Next release version comes from the highest tag in the whole repo

- **Severity / status:** high / CONFIRMED. **Verification:** phase A 3/3 (pipeline-lean), 3/3 (pipeline-tla). **Origin:** pipeline-lean F3, pipeline-tla F1 (part).
- **Fix PR:** [#1550](https://github.com/dsldevkit/dsl-devkit/pull/1550) (draft) — `git tag --list 'v*' --merged HEAD`. Necessary but not sufficient for maintenance releases: PIPE-1 still blocks them.
- **Locations:** `.github/workflows/release.yml:44`, `:36`, `:52`, `:87`.
- **Claim:** release.yml:44 takes the highest `v*` tag overall. A maintenance release computes v19.0.(n+1) instead of v18.0.1, and the `feature.xml` check (:87-104) then always fails. Conversely, a master patch can pick up a maintenance tag. Fix: `--merged HEAD`.
- **Trace:** master releases v3.0; a maint/patch dispatch computes v3.1 instead of v2.1.
- **Models:** `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/NOTES.md`, `formal/pipeline/tla/logs/asis_MaintOnOwnLine.log`, `formal/pipeline/tla/logs/asis_OwnLineVersion.log`.
- **Test:** proposed (PIPE-PR1): offline `test-next-release-version.sh`.

#### PIPE-3 — Release tag pushed before the release repository exists; can be left permanently orphaned

- **Severity / status:** high / CONFIRMED. **Verification:** phase A 3/3 (pipeline-tla), 3/3 (pipeline-lean). **Origin:** pipeline-tla F3, pipeline-lean F1.
- **Locations:** `.github/workflows/release.yml:106`, `:114`, `:130`, `:142`; `.github/scripts/publish-p2-ghpages.sh:57`; `.github/scripts/cleanup-p2-snapshots.sh:15`.
- **Claim:** `create_tag` pushes the tag (:106-112) before the `publish` job writes `p2/releases/<v>` and the GitHub release (:114-148). If publish fails, "Re-run failed jobs" recovers only while `p2/snapshots/<sha>` survives cleanup (KEEP=20); after that `cp` fails (publish-p2-ghpages.sh:57). "Re-run all jobs" or a new dispatch computes the next version and fails the `feature.xml` check. Only a manual repair helps. Moving the tag push last on its own creates a new stuck state, so a resume step is also required.
- **Trace:** compute v19.0.1, checks pass, tag pushed → publish fails → newer snapshots evict `<sha>` → nothing can publish v19.0.1 (R1).
- **Models:** `formal/pipeline/tla/NOTES.md`, `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/logs/asis_TagHasRepoOrLive.log`, `formal/pipeline/tla/logs/asis_live_tags.log`, `formal/pipeline/lean/report.txt`.
- **Test:** proposed (PIPE-PR6): failure-injection `test-release-resume.sh`.

#### PIPE-4 — `p2/snapshots/latest` can point at a maintenance or arbitrary dispatched branch build

- **Severity / status:** medium / CONFIRMED. **Verification:** phase A 3/3 (pipeline-tla), 3/3 (pipeline-lean). **Origin:** pipeline-tla F2, pipeline-lean F2.
- **Locations:** `.github/scripts/publish-p2-ghpages.sh:72`, `.github/workflows/snapshot.yml:11`, `:26`, `ddk-parent/pom.xml:65`, `ddk-parent/pom.xml:387`.
- **Claim:** `snapshot.yml` runs for `v[0-9]*` pushes and for dispatches of any branch, and publish-p2-ghpages.sh:72 always rewrites `snapshots/latest`. `snapshot.repo.url` is also a tycho-p2-plugin `baselineRepositories` entry (pom.xml:387-393); that part is PLAUSIBLE in pipeline-lean and not modelled.
- **Trace:** push to maint → gate passes (no higher release yet) → S4 sets latest to the maint commit.
- **Models:** `formal/pipeline/tla/NOTES.md`, `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/logs/asis_LatestFromMaster.log`.
- **Test:** proposed (PIPE-PR3).

#### PIPE-5 — Shared concurrency group silently cancels pending runs across refs and workflows

- **Severity / status:** medium / CONFIRMED. **Verification:** phase A 3/3 (pipeline-tla), 3/3 (pipeline-lean). **Origin:** pipeline-tla F4, pipeline-lean F5.
- **Locations:** `.github/workflows/snapshot.yml:13`, `.github/workflows/release.yml:21`, `.github/workflows/release.yml:81`.
- **Claim:** Both workflows share the group `publish-ghpages-<repo>`. `cancel-in-progress: false` protects only the running run; GitHub replaces the pending one. A pending master snapshot can be cancelled by any maintenance push or dispatch, and release.yml:81-84 then refuses to release master HEAD. A pending release dispatch can be dropped. Relies on GitHub's documented semantics.
- **Trace:** release R1 running → master push (S pending) → R2 dispatch replaces S → R1 and R2 fail: HEAD has no snapshot.
- **Models:** `formal/pipeline/tla/NOTES.md`, `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/logs/asis_NoCrossRefCancel.log`.
- **Test:** proposed (PIPE-PR7).

#### PIPE-6 — A publish-job re-run cannot succeed once `gh release create` has run

- **Severity / status:** low / CONFIRMED (promoted: originating verdict PLAUSIBLE, 3/3 upheld). **Verification:** phase A 3/3 (pipeline-tla), 3/3 (pipeline-lean). **Origin:** pipeline-tla F5 (PLAUSIBLE), pipeline-lean #5 part (PLAUSIBLE).
- **Locations:** `.github/workflows/release.yml:145`, `:152`.
- **Claim:** `gh release create` is not idempotent. If the job fails after the release exists (in the Summary step, or through a lost runner), every re-run fails with "already exists". Nothing is lost, but the run never turns green. That `gh` rejects an existing release was not checked offline (why the origin said PLAUSIBLE).
- **Trace:** T1-T4, P1-P3 (release created) → fail at P4 → re-run: P3 fails with "already exists".
- **Models:** `formal/pipeline/tla/NOTES.md`, `formal/pipeline/lean/NOTES.md`, `formal/pipeline/tla/logs/asis_RerunProgress.log`.
- **Test:** proposed (PIPE-PR5): stub `gh` on PATH.

#### PIPE-7 — An 8-character short SHA computed in different clones may disagree

- **Severity / status:** low / CONFIRMED (promoted: originating verdict PLAUSIBLE, 3/3 upheld). **Verification:** phase A 3/3 (pipeline-lean). **Origin:** pipeline-lean #5 part (PLAUSIBLE, not modelled).
- **Locations:** `.github/workflows/release.yml:63`, `.github/workflows/snapshot.yml:34`.
- **Claim:** Both sides use `git rev-parse --short=8` in different clones. Git lengthens an ambiguous prefix, so the release could fail to find its snapshot.
- **Models:** `formal/pipeline/lean/NOTES.md` (not modelled).
- **Test:** proposed (PIPE-PR2): static check plus `.commit` checks.

#### PIPE fix plans

Single plan, no judge. No PIPE PR touches bundle sources, so no MANIFEST/pom bumps. No PIPE defect is shared upstream. New tests live under `.github/scripts/test/` and run from a cheap new `verify.yml` job (no Maven), added by PIPE-PR1.

**PIPE-PR1 (PIPE-2)**

- **Fix:** Extract release.yml:44-57 into `.github/scripts/next-release-version.sh <bump>`, using `git tag --list 'v*' --merged HEAD --sort=-version:refname | head -1`. Guard: if `NEW_TAG` already exists, fail with `::error::`.
- **Files:** `.github/workflows/release.yml`, `.github/scripts/next-release-version.sh` (new), `.github/scripts/test/test-next-release-version.sh` (new), `.github/workflows/verify.yml`.
- **Test strategy:** Offline in a throwaway repo, all failing first: on v19.1.x a patch release gives v19.1.1; master ignores higher maintenance tags; a minor bump on v19.1.x that collides with an existing tag exits non-zero.
- **Risks:** A maintenance branch merged back into master does not change the result. Needs `fetch-depth: 0` (set, release.yml:39). Not sufficient without PIPE-1.
- **Model evidence:** TLA `fix_MaintOnOwnLine` holds (1.86M states). Lean `fixC_only` flips P3 only; `fixCD` makes W2 reachable.

**PIPE-PR2 (PIPE-7)**

- **Fix:** `git rev-parse HEAD | cut -c1-8` at snapshot.yml:34 and release.yml:63. Write the full SHA to `p2/snapshots/<sha8>/.commit`. The release check (release.yml:81) compares `.commit` with HEAD and warns when it is missing.
- **Files:** `snapshot.yml`, `release.yml`, `publish-p2-ghpages.sh`, `.github/scripts/test/test-sha8.sh` (new).
- **Test strategy:** Forcing a real ambiguity is impractical (~2^32 attempts). Instead: a static check for no `--short`; `.commit` present after publish; a mismatching `.commit` fails the release check.
- **Risks:** None functional. Snapshots without `.commit` age out of KEEP=20.
- **Model evidence:** Not modelled.

**PIPE-PR3 (PIPE-4)**

- **Fix:** (a) Rewrite `latest` only when `SNAPSHOT_REF` (dispatch input or `github.ref_name`) is `master`. (b) Write `$TARGET/.ref`. (c) Cleanup protects the `latest` target and the newest snapshot of master and of each `v[0-9]*` ref; the model showed (c) is required. Optional: relabel "Latest master snapshot" in `index.html`.
- **Files:** `publish-p2-ghpages.sh`, `cleanup-p2-snapshots.sh` (add a `KEEP=${KEEP:-20}` seam), `snapshot.yml`, `.github/scripts/test/test-publish-snapshot-latest.sh`, `.github/scripts/test/test-cleanup-protects-latest.sh` (new).
- **Test strategy:** Against a local bare origin: publishing maintenance after master keeps `latest` on master; `KEEP+1` maintenance snapshots do not evict the `latest` target; the newest `v19.1.x` snapshot survives.
- **Risks:** Consumers following a maintenance line via `latest` lose that. Must land before PIPE-PR4, which unmasks the bug.
- **Model evidence:** TLA `asis_LatestFromMaster` (6 states). `fix_safety`/`fix_safety_maint` hold LatestFromMaster and LatestExists; the cleanup protection was added after TLC found LatestExists failing; `plant_cleanup` is caught. Lean fixes B + F give P2/P7. Lean's fixed config sets `featDispatch=false`, so feature dispatches are covered only by the ref check in (a).

**PIPE-PR4 (PIPE-1)**

- **Fix:** New `.github/scripts/resolve-baseline.sh`: walk the tags merged into HEAD, pick the first with `p2/releases/$TAG` on `origin/gh-pages`, print its URL; fail if none (a silent fallback would make the gate pass vacuously). `snapshot.yml` and `verify.yml` pass `-Dbaseline.repo.url`. `pom.xml:64` keeps the default for local builds. On master this resolves to v19.2.0, as today.
- **Files:** `resolve-baseline.sh` (new), `snapshot.yml`, `verify.yml`, `.github/scripts/test/test-resolve-baseline.sh` (new), README.md or AGENTS.md (one line on local maintenance builds).
- **Test strategy:** Offline, failing first: resolves to v19.1.0 on the branch and v19.2.0 on master; a tag without a repo is skipped; no candidate exits non-zero. Manual Maven reproduction of "Version has moved backwards". A fork rehearsal writes to the fork's gh-pages and needs approval first.
- **Risks:** `verify.yml` exercises only the master path. Correctness relies on the tag-implies-repo order from PIPE-3; skipping repo-less tags keeps today's behaviour until then. Not sufficient without PIPE-2.
- **Model evidence:** The TLA fixed Gate uses the highest ancestor release; `asis_live_maint` violated, `fix_live_maint` holds. Lean `fixD_only` reaches W5 but not W2; `fixCD` reaches both. Gate semantics from the tycho-p2-extras 5.0.4 bytecode.

**PIPE-PR5 (PIPE-6)**

- **Fix:** (a) Zip from `p2/releases/$RELEASE_VERSION`, not from the snapshot. (b) `gh release view` → `upload --clobber`, else `create --verify-tag`. (c) In release mode the publish script writes `.commit`: same commit → no-op, different commit → fail. A published release repo is never overwritten.
- **Files:** `release.yml`, `publish-p2-ghpages.sh`, `.github/scripts/test/test-release-rerun.sh` (new).
- **Test strategy:** A stub `gh` on PATH. A second run after a simulated Summary failure exits 0 with one release and one asset; it still succeeds after the snapshot is deleted; a same-SHA republish is a no-op, a different SHA fails.
- **Risks:** Rests on `gh` rejecting duplicates (unchecked offline); the view-then-create form is correct either way. Prerequisite for PIPE-PR6's resume step.
- **Model evidence:** TLA `asis_RerunProgress` violated; the fixed P3 holds RerunProgress. Lean fix A makes W3 reachable.

**PIPE-PR6 (PIPE-3)**

- **Fix:** One job: prepare → publish the repo → push the tag → create the GitHub release, each step idempotent. (1) Drop the `branch` input and pin `github.sha`; dispatch via "Use workflow from". (2) Resume first: a tag at HEAD, or an orphan `p2/releases/<v>` above the highest merged tag whose `.commit` is an ancestor; otherwise compute the version (PIPE-2). (3) The snapshot check also accepts a matching release repo. (4)-(7) `feature.xml` check, publish, tag push (skip if already at this commit, fail if elsewhere), GitHub release (PIPE-6).
- **Files:** `release.yml`, `.github/scripts/release.sh` (new), `publish-p2-ghpages.sh`, `.github/scripts/test/test-release-resume.sh` (new), README.md (dispatch instructions).
- **Test strategy:** Failure injection in the harness only: a pre-receive hook rejects tags once; the stub `gh` fails create once. Scenario A (orphan tag) is red on today's order and resumes after the fix. B: a new commit between attempts; the pinned re-run finishes `<v>`. C: re-running a completed release is a no-op. A fork rehearsal needs approval.
- **Risks:** Dispatch changes for maintainers. The `environment: release` branch rules (not visible offline) must allow `v[0-9]*`. Cherry-pick onto any maintenance branch cut earlier. Repos published before this change have no `.commit`. There is a short window where `releases/latest` moves before the tag exists. Largest PR: review as an extraction commit plus a behaviour commit.
- **Model evidence:** TLA `asis_TagHasRepoOrLive` and `asis_live_tags` violated. The fixed ordering plus resume plus pinned SHA hold TagHasRepoOrLive, RerunProgress and NoLostContent (`fix_safety_wide`, 177M states) and LTags/LGoal; resume and pin were each added after TLC counterexamples on earlier fixed models. Lean: faithful R1 fails (10-step stuck trace); tag-last without resume breaks R2, so resume is mandatory.

**PIPE-PR7 (PIPE-5)**

- **Fix:** (1) Per-workflow, per-ref groups (`snapshot-<repo>-<ref>`, `release-<repo>-<ref>`), keeping `cancel-in-progress: false`. (2) Both gh-pages scripts wrap their mutation in `apply`; on a rejected push they fetch, reset, re-apply and retry (bounded, with jitter) instead of `git rebase`. Never force-push.
- **Files:** `snapshot.yml`, `release.yml`, `publish-p2-ghpages.sh`, `cleanup-p2-snapshots.sh`, `.github/scripts/test/test-ghpages-concurrent-writers.sh` (new).
- **Test strategy:** A static YAML check of the group keys. A stale-checkout race test: today the rebase conflicts on `index.html`; after the fix both snapshots exist, `releases/latest` is the maximum, nothing is lost. Also the reverse cleanup interleaving.
- **Risks:** Removes the global serialisation that makes several invariants hold trivially; correctness then rests on regenerate-on-retry. Needs PIPE-PR3 and PIPE-PR6 first. Model evidence at larger bounds is thinner. Interim alternative: keep one writer group and add only a separate release group.
- **Model evidence:** TLA `asis_NoCrossRefCancel` (4 states) violated; with `<<wf, ref>>` groups and atomic recompute, `fix_safety`, `fix_safety_maint` and `fix_safety_wide` hold everything. Lean `fixes_without_E` fails only P5; the full config holds all 15 verdicts at budget 1, keep 1/2; perBranch at budget 2 not run (>5M states); `planted_caught` (force push) violates P4.

### 3.6 RO — other code-read findings

**Severity note:** RO-2, RO-4, RO-5, RO-6, RO-7 and RO-8 concern test code and CI only, with no shipped-code impact, so all are low. RO-2 is the only one with an effect today, since it leaves 4 test methods unrun since 2017. RO-4 is masked by RO-2 until the bundle is wired. RO-7 is the root cause that let RO-2 persist; it is sequenced last for mechanical reasons (it turns master red until the others land), not because of severity. RO-1 is medium: interleaved locators are the normal case and corrupt the formatter's column stacks. RO-3 needs 32 or more locators on one token boundary, which is not realistic for normal formatter configurations, so the crash is low; its fix is RO-1's.

#### RO-1 — `sortLocators` comparator is not a total order

- **Severity / status:** medium / CONFIRMED. **Verification:** phase A 3/3 (ro-comparator). **Origin:** ro-comparator.
- **Locations:** EFCBS:547, EFCBS:559, EFCBS:322, EFCBS:569.
- **Claim:** The comparator returns 0 whenever either argument is not a `FixedLocator`, so equality is not transitive. For lists under 32 elements TimSort finds one run and changes nothing, so interleaved `FixedLocator`s stay unsorted. `processColumnLocators` relies on "openers first, then by column". With a closer first, `lastIndexOf` returns -1, an unrelated pair is popped, and the `columnIndents`/`initialIndents` stacks are corrupted. Interleaving is normal (the `activeRangeLocators` `HashSet` is mixed in). Wrong end-to-end formatter output was not shown.
- **Trace:** `[open@5, NoFormatLocator, open@1]` → unchanged; `[close@5, NoFormatLocator, open@5]` → unchanged; a random probe left 15,028 of 20,000 lists unsorted.
- **Models:** `formal/readonly/comparator/Runner.java`, `formal/readonly/comparator/MinSize.java`.
- **Test:** `SLT#testFixedLocatorsAreSortedByColumnAcrossOtherLocator` and `#testOpeningFixedLocatorPrecedesClosingAcrossOtherLocator` (`@Disabled`, plus reflective Runner); `#testFixedLocatorsAreSortedByColumn` is a control that passes and runs.

#### RO-2 — `com.avaloq.tools.ddk.test.ui.test` is built but its tests never run

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan.
- **Locations:** `ddk-parent/pom.xml:44`, `:105`, `:270`; `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/AllTests.java:38`; `com.avaloq.tools.ddk.xtext.test/META-INF/MANIFEST.MF:9`; `com.avaloq.tools.ddk.test.ui.test/META-INF/MANIFEST.MF:1`; `com.avaloq.tools.ddk.test.ui.test/pom.xml:12`.
- **Claim:** `DeChKeyboardLayoutTest`, `SwtBotRadioTest` and `ErrorLogListenerTest` (4 `@Test` methods) are compiled but never run. The module inherits `skip=true`, nothing reachable from `com.avaloq.tools.ddk.xtext.AllTests` selects them, the aggregator does not import the bundle, and the bundle exports nothing. They have not run in CI since the bundle arrived (d08ea194a, 2017). **Severity rationale:** lowered from medium per the cluster note; the impact is lost test coverage, not shipped behaviour.
- **Trace:** `AllTests` selects 14 suites, none from `test.ui.test`; `git log -S'test.ui.test'` on the aggregator MANIFEST and `AllTests` is empty.
- **Models:** `formal/readonly/orphans/check-test-reachability.sh`, `formal/readonly/orphans/selftest.sh`, `formal/readonly/orphans/expected.txt`.
- **Test:** the reachability script exits 1 with UNREACHABLE=3 on a `git archive HEAD` snapshot, and exits 0 after wiring (mutation check); `selftest.sh` passes. Run it with `formal/check.sh --only=orphans`.

#### RO-3 — The `sortLocators` comparator makes `Collections.sort` throw for 32 or more locators

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-comparator). **Origin:** ro-comparator.
- **Locations:** EFCBS:548.
- **Claim:** At `MIN_MERGE`=32, TimSort can detect the contract violation and throw `IllegalArgumentException`, aborting formatting. It needs 32 or more locators on one token boundary (see the severity note). The RO-1 fix also fixes this.
- **Models:** `formal/readonly/comparator/MinSize.java`, `formal/readonly/comparator/Runner.java`.
- **Test:** No JUnit test; `SLT` uses lists of 2-3 locators. `MinSize.java` shows the first IAE at n=32, and the `Runner` probe saw 2,751 of 20,000 lists throw. Proposed (RO-PR1): a fixed-seed large-list case.

#### RO-4 — `ErrorLogListenerTest` is missing even from its own bundle's `AllTests`

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan.
- **Locations:** `com.avaloq.tools.ddk.test.ui.test/src/com/avaloq/tools/ddk/test/ui/test/AllTests.java:24`, `com.avaloq.tools.ddk.test.ui.test/src/com/avaloq/tools/ddk/test/ui/test/logging/ErrorLogListenerTest.java:29`.
- **Claim:** The bundle `AllTests` selects only the other two classes, so wiring the bundle alone would still leave this one orphaned.
- **Models:** `formal/readonly/orphans/check-test-reachability.sh`.
- **Test:** the reachability script lists it as unreachable (exit 1).

#### RO-5 — `ErrorLogListenerTest` does not actually test ignoring, and its ignore location is stale

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan.
- **Locations:** `ErrorLogListenerTest.java:66`, `com.avaloq.tools.ddk.test.core/src/com/avaloq/tools/ddk/test/core/util/ErrorLogListener.java:177`.
- **Claim:** The ignore location names the old package `...test.core.util.ErrorLogListenerTest`. The test then asserts `isExceptionLogged(NPE)`, which never consults the ignore lists (they are read only by `isException()`, `ErrorLogListener.java:138-184`), so it passes even if ignoring is broken.
- **Models:** code read only; the class's unreachability comes from `formal/readonly/orphans/check-test-reachability.sh`.
- **Test:** proposed (RO-PR3): wait for the exact NPE and assert by identity it is not logged, plus a control without the ignore; mutation checks (stale location, or `isExceptionIgnored` forced false) must fail it.

#### RO-6 — `DeChKeyboardLayoutTest` uses a stale keyboard-layout name and leaks global SWTBot preferences

- **Severity / status:** low / CONFIRMED (promoted: originating verdict PLAUSIBLE, 3/3 upheld). **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan (PLAUSIBLE; inferred from javap, not executed).
- **Locations:** `com.avaloq.tools.ddk.test.ui.test/src/com/avaloq/tools/ddk/test/ui/test/swtbot/DeChKeyboardLayoutTest.java:34`, `com.avaloq.tools.ddk.test.ui/src/com/avaloq/tools/ddk/test/ui/swtbot/DE_CH.keyboard`, `com.avaloq.tools.ddk.test.ui/src/com/avaloq/tools/ddk/test/ui/swtbot/util/SwtBotUtil.java:32`.
- **Claim:** `KEYBOARD_LAYOUT="com.avaloq.test.swtbot.DE_CH"`, but the file moved in 27d54e7e8 (2017); SWTBot throws IAE for a missing layout. The test also never restores `KEYBOARD_LAYOUT`/`KEYBOARD_STRATEGY`.
- **Models:** code read only (javap of SWTBot's layout lookup); the class's unreachability comes from `formal/readonly/orphans/check-test-reachability.sh`.
- **Test:** proposed (RO-PR3): wire first and observe the IAE locally, then fix.

#### RO-7 — The CI guard cannot detect orphaned tests

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan.
- **Locations:** `.github/scripts/check-surefire-reports.sh:6`, `.github/workflows/verify.yml:82`.
- **Claim:** The guard fails only if no surefire report exists at all, so any unselected test class passes CI silently. That is why RO-2 went unnoticed.
- **Models:** `formal/readonly/orphans/check-test-reachability.sh`, `formal/readonly/orphans/selftest.sh`.
- **Test:** the replacement guard exists as `formal/readonly/orphans/check-test-reachability.sh` and exits 1 on unreachable tests; proposed (RO-PR5): move it into CI.

#### RO-8 — The aggregator selects an empty placeholder suite (`CheckCfgUiTestSuite`)

- **Severity / status:** low / CONFIRMED. **Verification:** phase A 3/3 (ro-orphan). **Origin:** ro-orphan.
- **Locations:** `com.avaloq.tools.ddk.checkcfg.ui.test/src/com/avaloq/tools/ddk/checkcfg/ui/test/CheckCfgUiTestSuite.java:18`, `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/AllTests.java:50`.
- **Claim:** It has no `@Suite`/`@SelectClasses`, and the bundle has no tests. It looks like coverage but runs nothing.
- **Models:** `formal/readonly/orphans/check-test-reachability.sh`.
- **Test:** the script reports INFO "selected-but-empty" and does not fail.

#### RO fix plans

Single plan, no judge. No RO defect is shared upstream.

**RO-PR1 (RO-1, RO-3)**

- **Fix:** Replace the comparator in `sortLocators` (EFCBS:547), keeping the private signature: non-`FixedLocator`s go last and compare equal to each other; `FixedLocator`s are ordered by `(getLeft() != null)`, then by column. Safe because only `processColumnLocators` reads the order and it skips non-Fixed entries, and `getLocators` returns a `HashSet` anyway (:325). For all-Fixed lists the result is identical to today's. Alternative: sort only the Fixed subsequence (requires rewriting the reflective test). Update the Javadoc.
- **Files:** EFCBS, `SLT` (enable).
- **Version bumps:** `com.avaloq.tools.ddk.xtext`: none at 17.4.1 against 17.4.0; if 17.4.1 is the published baseline by merge time, bump to 17.4.2. `xtext.test`: none.
- **Test strategy:** Enable the two bug cases, and add a fixed-seed (42) large-list case over 2-80 mixed locators asserting no throw and a sorted Fixed subsequence (RO-3). Regression coverage: `FormatterTest.column()` and `FormatTestSuite`.
- **Risks:** Formatter output can change only where the old stacks were corrupted; describe the PR as a correctness and crash fix. The Javadoc must state that nothing reads the list order after `processColumnLocators`.
- **Model evidence:** `Runner.java` and `MinSize.java`. Planned ablation: rerun them on the patched bundle and expect 3/3 PASS, unsorted=0, IAE=0; a half-fix that keeps `return 0` for mixed pairs must still fail.

**RO-PR2 (optional: RO-A1, RO-A2)**

- **Fix:** (RO-A1) `com.avaloq.tools.ddk.test.core/src/com/avaloq/tools/ddk/test/core/jupiter/IssueAwareRule.java:87`: restore `!issueAnnotation.fixed()` (the JUnit 4 original, d08ea194a, had the negation; the Jupiter port c0dd0ad4f dropped it). (RO-A2) `ErrorLogListener.LogListener`: a thread-safe list, with accessors returning snapshot copies.
- **Files:** `IssueAwareRule.java`, `ErrorLogListener.java`, `com.avaloq.tools.ddk.test.core/META-INF/MANIFEST.MF`, `com.avaloq.tools.ddk.test.core/pom.xml`, a new Mockito-based `IssueAwareRuleTest` in `xtext.test`, registered in `XtextTestSuite`.
- **Version bumps:** `com.avaloq.tools.ddk.test.core` 18.0.1 → 18.0.2 (required; it ships in the feature).
- **Test strategy:** Mock `ExtensionContext` and `Invocation`. With `fixed=false`, a throwing invocation passes and a passing one fails with "must fail"; with the default `fixed=true`, the invocation's result propagates. Red on master (RO-A1 confirmed in phase C). No deterministic test for RO-A2.
- **Risks:** Published test API: downstream tests using the default `fixed=true` will now run normally and may surface real failures. `SwtBotRadioTest`'s annotation must be set from an observed local run.
- **Model evidence:** None; git diff and the Javadoc example. RO-A2: `org.eclipse.core.jobs` 3.15.900 bytecode shows `RuntimeLog.log` running on the worker thread.

**RO-PR3 (RO-2, RO-4, RO-5, RO-6)**

- **Fix:** (1) `ErrorLogListenerTest` (RO-5): use `ErrorLogListenerTest.class.getName()` as the location; after `join()`, wait up to 10 s for the exact NPE (`JobManager.endJob` logs after releasing `join()`), assert by identity it is not in `getLoggedExceptions()`, and add a control case without the ignore. (2) `DeChKeyboardLayoutTest` (RO-6): layout `com.avaloq.tools.ddk.test.ui.swtbot.DE_CH`; save and restore the preferences and close editors in finally; fix the `SwtBotUtil.java:32` comment. (3) Add `ErrorLogListenerTest` to the bundle suite (RO-4). (4) Rename the bundle suite to `TestUiTestSuite` (and its `.launch`), export `com.avaloq.tools.ddk.test.ui.test`, import it in `xtext.test`, and select it last in `AllTests` (RO-2).
- **Files:** `ErrorLogListenerTest.java`, `DeChKeyboardLayoutTest.java`, `AllTests.java` → `TestUiTestSuite.java`, `AllTests.launch` → `TestUiTestSuite.launch`, `test.ui.test` MANIFEST, `SwtBotUtil.java` (comment), `xtext.test` MANIFEST, `xtext.test` `AllTests.java`, possibly `SwtBotRadioTest.java` (`@Issue` flag).
- **Version bumps:** `test.ui.test`: optional 17.3.1 → 17.3.2. `xtext.test`: none. `com.avaloq.tools.ddk.test.ui` (comment only): none at 17.3.4 against 17.3.3; if 17.3.4 is published by merge time, bump to 17.3.5 or drop the comment fix.
- **Test strategy:** Wire first, locally: `DeChKeyboardLayoutTest` should fail with IAE, and `ErrorLogListenerTest` passes regardless. Then fix. Mutation checks as under RO-5. Confirm with the reachability script and `surefire-reports` that all 4 methods ran.
- **Risks:** Wiring runs `SwtBotRadioTest` under `IssueAwareRule`, so land RO-PR2 first (RO-A1 is confirmed). The DeCh outcome after the rename is inferred, not run; if unstable, `@Disabled` it with a reason. Window > Preferences is missing on macOS, so local macOS runs may fail. Adds aggregator time. The rename breaks external launch configs.
- **Model evidence:** Reachability script output; `ErrorLogListener.java:138-184`; javap of `JobManager.endJob` (offset 19 before 72); the only `DE_CH.keyboard` is under `com.avaloq.tools.ddk.test.ui`.

**RO-PR4 (RO-8)**

- **Fix:** Remove `CheckCfgUiTestSuite` from `AllTests` and its import from the `xtext.test` MANIFEST. Do not annotate it with `@Suite` (`failIfNoTests=true` would break discovery). Optionally delete the placeholder bundle (module entry at `ddk-parent/pom.xml:122`).
- **Version bumps:** none.
- **Test strategy:** None possible. The script's INFO line disappears; surefire counts are unchanged.
- **Risks:** Minimal.

**RO-PR5 (RO-7)**

- **Fix:** Move the reachability script and its selftest to `.github/scripts/`, replacing `rg` with `git ls-files`/`find` and `grep -Eq`. Add an early `verify.yml` step that runs the selftest, then the check. Keep `check-surefire-reports.sh`. Optional strict mode that fails on selected-but-empty.
- **Files:** `.github/scripts/check-test-reachability.sh` (new), `.github/scripts/selftest-test-reachability.sh` (new), `.github/workflows/verify.yml`.
- **Version bumps:** none.
- **Test strategy:** The selftest's 3 synthetic cases. The real check exits 1 on current master and 0 after the other RO PRs.
- **Risks:** The regex/awk parser does not handle `@SelectPackages`, `@Nested` or pattern selection (none used today); document this. Must land last, or master CI goes red.

## 4. Dropped and refuted items, and observations

### 4.1 Refuted and plausible findings

- **STO-7 (REFUTED, 0/3).** Reasons under [STO-7](#sto-7--swallowed-ioexception-in-writeresource-keeps-the-old-binary-while-the-uri-leaves-sources). No fix.
- **STO-6 (PLAUSIBLE, 1/3).** Reasons under [STO-6](#sto-6--after-a-link-exception-the-resource-is-detached-but-still-stored). The fix (STO-PR4) is kept, framed as "do not serialise a detached resource".

### 4.2 Dropped items

**Merged or split during cataloguing**

- ro-selfmatch #0 (case-sensitive self-match in PAEDL): merged into TRIE-1.
- trie-tla F4 (the sibling loop visits a sibling's descendants but not the sibling; 2/3 upheld, one skeptic said the trace does not exercise the loop): same root cause as the `'!'` gap, unreachable once `'\0'` is used. Merged into TRIE-3.
- trie-tla F1 side-claim (`firstWildcardSeg` initialised to 0, dead `-1` guard): merged into TRIE-12 with ro-selfmatch #1.
- trie-lean B10 (a bundle of 4 glob problems): split into TRIE-12, TRIE-13, TRIE-14, TRIE-15.
- pipeline-tla F1 (a maintenance line cannot release): split into PIPE-1 and PIPE-2, matching pipeline-lean F4/F3.
- pipeline-lean #5: split into PIPE-7 and PIPE-6; the `gh release` part merged with pipeline-tla F5.
- storage round 2 (TLA B3 vs Lean B4+B5): re-split by defect into STO-4 and STO-5.
- REF judge graft 8 (upstream `batchedSearchResultEvents` not cleared on `setInput`): folded into REF-5 as its upstream variant.

**Not defects of the as-written code**

- Pipeline fixed-model constraints: cleanup must protect the `latest` target (in PIPE-PR3), re-runs must pin `github.sha`, and half-done releases must be resumable (both in PIPE-PR6).
- STO: the `RejectedExecutionException` catch at MCBS:727-734 is dead code (`CallerRunsPolicy` never discards). Harmless.
- STO Lean note "serialisation of a detached resource after a timeout": a model simplification, folded into STO-5.
- REF Lean false alarm (ghost `resetPending` cleared too early in 'lost+order+snap'): a model artefact, fixed in the model.
- REF "Reset does not clear `batchAddNodes`": never visible, because the same UI job's `refresh()` removes the nodes (P3 holds). Latent fragility; REF-PR5 clears it anyway.
- REF "`asyncExec` alone is not a fix for REF-1": a fix-design constraint (in REF-PR5).
- TRIE Lean: double-checked locking in PAEDL:95-98 re-tests a local that is always null. Benign: racing threads build equivalent lookups, last write wins.
- LDR "upstream Xtext `ParallelResourceLoader` has the same code": context for LDR-1.
- Trie properties checked and found sound: exact get, non-top wildcards without chars below `'!'`, the `find()` null path, the LastLoop result, and `remove`/`removeMappings` accounting.

**Artefacts and environment**

- ro-orphan #6 (campaign tests not registered in any suite): an artefact of the spike's working tree, not a repo defect. At the time of phase A, `QNLFT`, `PAEDLT` and `SLT` were untracked and unregistered, and `PRLT` and `FRSRCPT` were untracked and registered only by uncommitted suite edits. This PR commits all five and registers them in `XtextTestSuite`/`XtextUiTestSuite`, with their failing methods `@Disabled`.
- Local JDK-27 failures unrelated to the campaign (`ScopeExpressionCodeGenerationTest.testNestedArithmetic`, `IssueExpressionGenerationTest.testDynamicMarkerFeature`, `CheckApiAccessValidationsTest.testNonAvaloqTypeAccessable`) and the `CheckQuickfixTest.testBulkApplyingQuickfix` flake: pre-existing.

### 4.3 Verified observations

Raised by judges or planners, or by skeptics as out-of-scope side notes, then put through the three phase C skeptics. All 9 were upheld 3/3; most were narrowed. They are counted separately from the 51 catalogued findings (rule 3).

| ID | Status | Sev | Observation | Location | Why it matters (as raised) | Narrowed (skeptics) | Source | Plan |
|---|---|---|---|---|---|---|---|---|
| REF-A1 | CONFIRMED (3/3, 3 overstated) | low | `dispose()` never calls `super.dispose()`, so `detachListenerFromIndex` never runs; the constructor registers a second index listener on top of super's `attachListenerToIndex`. The REF judge checked the code. | FRSRCP:104-106, FRSRCP:69-71 | Each disposed provider stays registered on the index (leak) and receives `descriptionsChanged` twice. | Downstream-only (DDK never binds FRSRCP). The leak is real: each disposed provider and its viewer stay on the index and get a no-op `asyncExec` per index change. No double notification: the listener set is a `CopyOnWriteArraySet`. | REF plan B, REF judge | REF-PR7 |
| REF-A2 | CONFIRMED (3/3, 0 overstated) | low | Upstream `remove()` uses super's private `rootNodes`, which DDK never populates, so `RemoveSelectedMatchesAction` NPEs. The REF judge checked the code; the NPE has not been reproduced. | Xtext `ReferenceSearchResultContentProvider:295`, `ReferenceSearchViewPageActions:150` | "Remove selected matches" in the search view would throw. | As stated, downstream-only: where FRSRCP is bound, "Remove Selected Matches" or DEL with a selection always NPEs (logged); nothing is removed, no data lost. Confirmed from bytecode, not run. | REF plan B, REF judge | REF-PR7 |
| STO-A1 | CONFIRMED (3/3, 3 overstated) | low | A storage worker's `PortableURIs.toPortableURI` reads the builder's `ResourceSetImpl` (`getEObject(uri, false)`) while the builder adds resources. | Xtext `PortableURIs:183`, reached from `DirectLinkingResourceStorageWritable:209` | Another cross-thread read on a non-thread-safe resource set, which could write non-portable references; not fixed by any STO PR. | Downstream-only, and only with DLRSF bound alongside stock Xtext `PortableURIs` (DDK's `DirectLinkingPortableURIs` never reads the resource set). A racy miss writes an `#UNRESOLVABLE` reference, not a non-portable one. | STO plan B, STO judge ("file as new finding") | none yet |
| STO-A2 | CONFIRMED (3/3, 3 overstated) | low | `deleteBinaryResources` dereferences `getResourceServiceProvider(uri)` without a null check. | MCBS:782 | A load can fail because no language handles the URI; once STO-PR4 calls `deleteBinaryResources` on the failure path, an NPE there would escape the catch and abort the build. | Downstream-only. `ToBeBuiltComputer.removeStorage` already drops provider-less URIs, so it needs stale persisted-index entries for a deregistered language; the NPE is caught by `update()`, rolls back and forces a full rebuild. | STO plan A, STO judge | STO-PR4 (optional) |
| STO-A3 | CONFIRMED (3/3, 3 overstated) | low | After a `StackOverflowError` inside linking, the half-linked but still attached resource is stored. | MCBS:578-581 | A binary of an incompletely linked resource may be written. The judge called skipping it "a separate behaviour choice, not a confirmed bug". | Downstream-only hygiene: the "no linking errors" gate is bypassed (an SOE adds no diagnostic), but unresolved references stay lazy-link proxies, so the stored model is not wrongly linked. | STO plan B, STO judge | none (reviewer note) |
| STO-A4 | CONFIRMED (3/3, 3 overstated) | low | `CheckBatchLinkableResourceStorageFacade`'s Xbase-based writable declares `throws IOException`, so Xtext RSF's swallowing catch could be reached there; the sink is an in-memory stream, so practically unreachable. | Xtext RSF:97-103 | The residue of refuted STO-7 for non-DLRSF facades; a stale binary would be kept if it ever fired. | Downstream-only and effectively unreachable: the only `IOException` source is a broken inferrer invariant (JvmTypes without a `JvmModelAssociator` adapter). The enabling removal is MCBS:656 (STO-1), not 754. | STO-7 skeptics | none |
| TRIE-A1 | CONFIRMED (3/3, 3 overstated) | none (not a defect) | `TreeSetLookup.getStatistics()` counts names (`lookupMap.size()`), while the segment tree counts name-value pairs. | `TreeSetLookup.java:127-128` | Monitoring-only inconsistency between the two lookups. The TRIE plan notes it and leaves it out of scope. | Accurate but diagnostic-only: `CacheStatistics.entries` has no defined meaning for multimaps, `TreeSetLookup` matches `MapCache`'s key count, is test-only and never registered with `CacheManager`. | TRIE plan | none |
| RO-A1 | CONFIRMED (3/3, 0 overstated) | medium | `IssueAwareRule` has an inverted `fixed()` check (regression from c0dd0ad4f): `@Issue(fixed=false)` tests run normally, and default-annotated tests are expected to fail. | `com.avaloq.tools.ddk.test.core/src/com/avaloq/tools/ddk/test/core/jupiter/IssueAwareRule.java:87` | Published test API with the opposite of its documented behaviour; affects `SwtBotRadioTest` once RO-2 is fixed. | As stated. Latent in DDK CI (`SwtBotRadioTest` is not aggregated, RO-2), but downstream `AbstractTest` subclasses with a default `@Issue` pass real `AssertionError`s silently. Medium per rule 2: silent wrong verdicts behind a downstream-only trigger. | RO plan (found while planning; formerly NEW-RO-A) | RO-PR2 (optional) |
| RO-A2 | CONFIRMED (3/3, 2 overstated) | low | `ErrorLogListener.LogListener` keeps statuses in a plain `ArrayList` that job worker threads write while the test thread reads. | `com.avaloq.tools.ddk.test.core/src/com/avaloq/tools/ddk/test/core/util/ErrorLogListener.java:206` | A data race in test infrastructure that can make log assertions flaky. | Real race (listeners run synchronously on the logging thread), downstream-only since `ErrorLogListenerTest` never runs. The larger flakiness source is ordering: `JobManager.endJob` logs after releasing `join()`, which a thread-safe list does not fix. | RO plan (found while planning; formerly NEW-RO-B) | RO-PR2 (optional) |

## 5. Proposed fix-PR sequence

Ordered by severity × effort, subject to the ordering constraints. Each PR is small and self-contained, branched from an explicit `upstream/master`, and opened as a draft. Before pushing, run `mvn -T 3C verify` plus `checkstyle:check`, `pmd:check` and `spotbugs:check`. Use `-step-N` branch names only if PRs are stacked.

**Tests carried by this reference PR.** Each fix PR removes the `@Disabled` from its test (or ports it) in the same commit as the fix:

- `PRLT` → LDR-PR1
- `FRSRCPT` → REF-PR1
- `PAEDLT` → TRIE-PR1
- `QNLFT` methods → ported into `QualifiedNamePatternTest`/`QualifiedNameSegmentTreeLookupTest` by TRIE-PR1..PR9; `testMaxCharPattern` stays disabled until TRIE-PR10 is decided. Delete `QNLFT` once empty.
- `SLT` → RO-PR1
- `formal/readonly/orphans/check-test-reachability.sh` → RO-PR5 (moved into `.github/scripts/`)

**Shared version bumps.** `com.avaloq.tools.ddk.xtext.builder` 17.3.1 → 17.3.2: whichever of LDR-PR1/2/3 and STO-PR1..5 lands first. `com.avaloq.tools.ddk.xtext.ui` 17.3.3 → 17.3.4: the first REF PR. `com.avaloq.tools.ddk.test.core` 18.0.1 → 18.0.2: RO-PR2. TRIE, PIPE and the other RO PRs need no bump unless a release is published before they merge.

| # | PR | Findings | Sev | Effort | Hard ordering constraints |
|---|---|---|---|---|---|
| 1 | TRIE-PR1 — opened as [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551) (draft) | TRIE-1 | high | one line + test | none |
| 2 | LDR-PR1 — opened as [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553) (draft); bounded retry replaces the one-line fix | LDR-1 | medium | small + tests | first builder bump |
| 3 | TRIE-PR2 | TRIE-2, TRIE-12 | high | small | none |
| 4 | PIPE-PR1 — opened as [#1550](https://github.com/dsldevkit/dsl-devkit/pull/1550) (draft) | PIPE-2 | high | small (adds `.github/scripts/test` harness) | before PIPE-PR4 |
| 5 | PIPE-PR2 | PIPE-7 | low | trivial | early, so later PIPE PRs can rely on `.commit` |
| 6 | PIPE-PR3 | PIPE-4 | medium | small | before PIPE-PR4 |
| 7 | PIPE-PR4 | PIPE-1 | high | medium | after PIPE-PR1, PIPE-PR3 |
| 8 | PIPE-PR5 → PIPE-PR6 | PIPE-6; PIPE-3 | high (PIPE-3) | large (PIPE-PR6) | PIPE-PR5 before PIPE-PR6 |
| 9 | REF-PR1 — opened as [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552) (draft) | REF-2 | low | small | first xtext.ui bump |
| 10 | REF-PR2 | REF-3, REF-9 | medium | small | none |
| 11 | REF-PR3 | REF-5 | medium | small | none |
| 12 | REF-PR4 → REF-PR5 | REF-4, REF-6; REF-1 | medium (REF-1) | medium | REF-PR5 never without REF-PR4 |
| 13 | STO-PR0 → STO-PR1 | STO-1, STO-3 | medium | harness medium, fix one line | PR0 before PR1 |
| 14 | STO-PR2 | STO-2 | low | medium | after STO-PR1; before STO-PR3 |
| 15 | STO-PR3 | STO-4, STO-5 | medium | medium | after STO-PR2 |
| 16 | RO-PR1 | RO-1, RO-3 | medium | small | none |
| 17 | PIPE-PR7 | PIPE-5 | medium | medium | after PIPE-PR3, PIPE-PR6; last PIPE PR |
| 18 | LDR-PR2 | LDR-2 | low | small | before STO-PR5 |
| 19 | STO-PR5 | STO-8 | low | small | after LDR-PR2 and STO-PR3 |
| 20 | STO-PR4 | STO-6 (+ optional STO-A2) | low | small | independent of STO-PR3/PR5 |
| 21 | LDR-PR3 | LDR-3 | low | small | none |
| 22 | TRIE-PR3 | TRIE-3, TRIE-12 | low | small | after TRIE-PR2 (or combined) |
| 23 | TRIE-PR4, TRIE-PR5 | TRIE-4; TRIE-10 | low | small | independent |
| 24 | TRIE-PR6 | TRIE-5, TRIE-6, TRIE-16 | low | small | before TRIE-PR11 |
| 25 | TRIE-PR7, TRIE-PR8 | TRIE-7; TRIE-8 | low | small | independent (may be combined) |
| 26 | TRIE-PR9 | TRIE-12, TRIE-13, TRIE-14, TRIE-15 | low | medium | after TRIE-PR2, TRIE-PR3 |
| 27 | REF-PR6 | REF-7, REF-8 | low | medium (model extension first) | after REF-PR5 |
| 28 | RO-PR2 → RO-PR3 | optional: RO-A1 (medium), RO-A2; then RO-2, RO-4, RO-5, RO-6 | low (RO-A1 medium) | medium | RO-PR2 before RO-PR3 |
| 29 | RO-PR4 | RO-8 | low | trivial | before RO-PR5 |
| 30 | REF-PR7 | optional: REF-A1, REF-A2 | low | small | after REF-PR4 |
| 31 | TRIE-PR10, TRIE-PR11 | TRIE-9; TRIE-11 | low | issue first | TRIE-PR10 after TRIE-PR3; TRIE-PR11 after TRIE-PR6 |
| 32 | RO-PR5 | RO-7 | low | small | last: needs every campaign test registered or excluded |

**Upstream reports** (optional; each needs the user's explicit approval before posting): one Xtext issue for LDR-1 + LDR-3 (plus the interrupt-as-missing-result shape of LDR-2), and one for REF-2 + REF-6 + the REF-5 upstream variant. No other finding is shared upstream.

## 6. Open questions

1. **Tycho confirmation: resolved.** Every `@Disabled` method was run enabled under the Tycho aggregator: all 21 fail on master as documented; with the fix patches (plus bundle bumps) all pass except TRIE-9 (by design) and the two `SLT` methods (RO-1 has no patch yet). The 3 guard methods in `PAEDLT` and 1 in `SLT` pass on master.
2. **Design decisions before a fix:** TRIE-9 (options a/b/c in TRIE-PR10) and TRIE-11 (copy vs. documented aliasing in TRIE-PR11).
3. **LDR-2 model placement.** The chosen fix location (the catch that immediately follows the failed poll) is modelled by neither TLA+ nor Lean. Before LDR-PR2, move the TLA+ FIX-2 abort into `Catch` guarded by `bintr`, rerun `check_all.sh`, and compare with `expected.txt`.
4. **Model notes lag this catalogue.** `formal/find-refs/tla/NOTES.md` still says the REF-7 race exists upstream; this catalogue records the judge's correction (DDK-only) but the NOTES files were not edited.
5. **Observations** in [4.3](#43-verified-observations) are verified (phase C); STO-A1 still needs a decision to catalogue it before any PR claims it.
