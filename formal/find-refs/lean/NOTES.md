# Find-references batching: Lean 4 model

Subject: `com.avaloq.tools.ddk.xtext.ui/src/com/avaloq/tools/ddk/xtext/ui/editor/findrefs/FastReferenceSearchResultContentProvider.java`
(called **FRS** below; `FRS:n` is a line number). Context read: Xtext 2.44 `ReferenceSearchResult` (RSR),
`ReferenceQuery`, `ReferenceSearchViewPage`, superclass `ReferenceSearchResultContentProvider`;
Eclipse Search 3.19 `InternalSearchUI`, `SearchView`, `SearchViewManager`; JFace 3.40 `AbstractTreeViewer`.

Toolchain: `leanprover/lean4:v4.35.0-rc2`, no Mathlib, no dependencies, nothing downloaded.

```
lake build                     # models, BFS checker, all-sizes proof, native_decide theorems (~65 s)
lake env lean Report.lean      # every variant with shortest traces (~2 min 15 s); output saved in report.txt
```

| File | Lines | Contents |
|---|---|---|
| `FindRefs/State.lean` | 152 | state, pcs, helpers, environment (accept/reset choices) |
| `FindRefs/Buggy.lean` | 127 | the code as written, plus a patch ladder (`Patch`) |
| `FindRefs/Fixed.lean` | 82 | repaired design, plus `plant` (a deliberately planted bug) |
| `FindRefs/Check.lean` | 106 | BFS to a fixpoint, properties, shortest-trace extraction |
| `FindRefs/Proof.lean` | 203 | all-sizes inductive-invariant proof (no `sorry`) |
| `FindRefs/Theorems.lean` | 36 | `native_decide` verdict theorems + `#print axioms` |
| total | 723 | (plus `Report.lean`, 11) |

## Threading assumptions

* **Search thread S.** Eclipse `InternalSearchJob` runs `ReferenceQuery.run`, which calls `RSR.reset()` once and then `RSR.accept(ref)` once per match. There is one S per result: `InternalSearchUI.runSearchInBackground` refuses to start a query that is already running.
  * `accept` and `reset` change `matchingReferences` (an `ArrayList`) with no lock. They then call `fireEvent`, which holds `synchronized(listeners)` while it calls `FRS.searchResultChanged`.
  * So events reach FRS one at a time, and S holds the `listeners` monitor for the whole handler (`lockS`).
* **UI thread.** UI runnables (a UIJob body, a `syncExec` runnable, `setInput`→`inputChanged`) never interleave with each other. S steps can interleave between any two shared-memory accesses inside a UI runnable.
  * `RSR.addListener` and `RSR.removeListener` are `synchronized(listeners)`, so the UI blocks on them while S is inside a handler.
* **UIJobs.** `new UIUpdater().schedule()` and `schedule(250)` add one pending instance (`jobs`), which runs later as a UI runnable.
* **Viewer (JFace).** `refresh()` rebuilds the root items from `getElements()` = `rootNodes.values()`. `add` of an element that is already present does nothing (`itemExists`). Children are fetched lazily, so a reference counts as visible if its root node is shown. This is an optimistic assumption.
* **Input switches (user).** The search page, viewer and FRS are one per view. `setInput(B)` for another reference-search result B calls `inputChanged(A,B)`, and showing A again calls `inputChanged(B,A)`. `page.setInput(null)` does not touch the viewer (`ReferenceSearchViewPage.setInput`), so it is not modelled. B is static (no search runs on it).
* **Memory model.** Sequential consistency (optimistic): the unsynchronized `batchAddNodes.isEmpty()` at FRS:217 and the `ArrayList` children are treated as SC. Under the real JMM things can only get worse. `ConcurrentHashMap` operations are atomic, and a refresh takes an atomic snapshot of the map.
* **Not modelled:** `descriptionsChanged` (index deltas), `dispose`, Removed/Finish events, races on the `children` list inside `ReferenceSearchViewTreeNode`.

## Model

State: S pc, the `listeners` lock, `listening`, `matchingReferences` and its modCount, `rootNodes` (uri ↦ node), `batchAddNodes`, `isUIUpdateScheduled`, scheduled job count, UI pc (with the locals of each UI runnable), pending `syncExec`, viewer input and root items, plus ghost data: ref/node → (uri, epoch), ref→node attachments, the reset epoch, `resetPending`, and `cme`.

* Every access that is not atomic is its own step. For example, `rootNodes.get` (158), `put` (160-161), batch add (162-164), attach child (137), flag read (173), flag set (174) and schedule (175) are all separate. The UIUpdater's snapshot (207-215), refresh (216), `isEmpty` (217) and flag write/reschedule (218/220) are separate too.
* The `inputChanged` repopulation loop (118-120) walks the live list, with the `ArrayList.Itr` rules: `hasNext` is `cursor != size`, and `next` throws CME if modCount changed.
* Environment nondeterminism: S can pick `accept(ref in any uri)` or `reset()` within the budgets, and the user can switch A→B or B→A within the budget.
* Default bound: 2 URIs, 3 accepts, 1 reset, 2 switches.

## Properties

| Id | Statement | Checked where |
|---|---|---|
| P1 | No lost update. At quiescence (S idle, UI idle, no scheduled job, no pending `syncExec`, input A), every reference in `matchingReferences` is attached to a root node that is shown. | quiescent states |
| P1' | Root-level form of P1: at quiescence every node in `rootNodes` is shown. | quiescent states |
| P2 | One root per URI: no two shown root nodes have the same URI. | between UI runnables |
| P3 | No stale node. When input is B, nothing is shown. When input is A and no Reset is still in flight, every shown node belongs to the current epoch. | between UI runnables |
| P4 | No `ConcurrentModificationException` on the UI thread. | all states |
| DL | No deadlock: no state without successors while some actor is mid-flight. | all states |

## Results

| Model | States | Edges | P1 | P1' | P2 | P3 | P4 | DL |
|---|---|---|---|---|---|---|---|---|
| **as written** | 635,022 | 1,020,263 | ✗ (20) | ✗ (20) | ✓ | ✗ (12) | ✗ (8) | ✗ (3) |
| patch `lost` | 523,078 | 832,155 | ✗ (28) | ✓ | ✓ | ✗ (10) | ✗ (8) | ✗ (3) |
| patch `lost+order+snap` | 403,838 | 700,869 | ✗ (25) | ✓ | ✓ | ✓ | ✓ | ✗ (3) |
| patch `…+async` | 802,778 | 1,421,456 | ✗ (13) | ✓ | ✓ | ✓ | ✓ | ✓ |
| **fixed** | 9,952 | 16,835 | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| fixed, bound 3 URIs / 4 accepts / 2 resets / 3 switches | 1,892,441 | 3,418,927 | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| **fixed + planted bug** | 6,472 | 9,311 | ✗ (2) | ✗ (2) | ✓ | ✓ | ✓ | ✓ |

✗ (k) = violated, with a shortest counterexample of k steps (BFS order). Every run reached a fixpoint. Full traces are in `report.txt`.

### Proved and bounded-checked

* **Proved for all sizes** (any number of URIs, accepts, resets, switches): `Proof.no_lost_root`. In every reachable state of the **fixed** model, if no UIUpdater is scheduled or running, every root node in `rootNodes` is shown (P1', the roots-level form).
  * Inductive invariant: `batch ≠ [] → flag`, `flag → jobs > 0 ∨ UI at decide`, and `rootNodes.values ⊆ viewer ∪ batch`.
  * Axioms: `propext` and `Quot.sound` only.
* **Kernel-checked bounded results** (`native_decide`, default bound): `Theorems.buggy_verdicts`, `fixed_verdicts` and `planted_caught` pin the verdict vectors in the table above.
* **Bounded only:** P1 (refs level), P2, P3, P4 and DL.

### Sanity checks

* The fixed model passes every property at both bounds.
* The planted bug (flag set without scheduling the job) is caught by P1/P1' in 2 steps.
* By construction, the planted bug also breaks clause 2 of the proof's invariant (flag set with no job). This is not separately proved.

## Findings

### F1: Lost update on the UIUpdater hand-off. **CONFIRMED**
Shortest trace (20 steps). Condensed from step 10 on, after node0 was delivered and a UIUpdater is scheduled:

| # | Actor | Step | Code |
|---|---|---|---|
| 10-12 | S | `accept(ref1 in uri1)`, takes lock, `rootNodes.get(uri1)` = null | RSR.accept, FRS:170, FRS:158 |
| 13 | UI | UIUpdater snapshots and clears the batch [node0], `viewer.add` | FRS:207-215 |
| 14 | UI | `viewer.refresh()` | FRS:216 |
| 15 | S | `rootNodes.put(uri1, node1)` | FRS:160-161 |
| 16 | UI | `batchAddNodes.isEmpty()` = **true** | FRS:217 |
| 17-18 | S | `batchAddNodes.add(node1)`; attach ref1 | FRS:162-164, FRS:137 |
| 19 | S | `isUIUpdateScheduled == true`, so no schedule | FRS:173 |
| 20 | UI | `isUIUpdateScheduled = false` | FRS:220 |

End state: node1 is in `rootNodes` and in the batch, but not in the viewer. The flag is false and no job exists.

* Nothing brings node1 into the viewer until the next `Added` event.
* If this was the last match of the search, node1 never appears: the Finish event is ignored and nothing else refreshes the viewer.
* Why the Java does this: the "batch empty?" check at 217 and the flag clear at 220 are not atomic with S's batch add (162-164) and flag read (173).

### F2: Deadlock between Reset's `syncExec` and `inputChanged`. **CONFIRMED** (timing window)

| # | Actor | Step | Code |
|---|---|---|---|
| 1 | S | `reset()` | RSR.reset, from ReferenceQuery.run at job start |
| 2 | S | `fireEvent(Reset)` holds `A.listeners`, then `Display.syncExec` waits for the UI | FRS:178 |
| 3 | UI | `setInput(B)` → `inputChanged(A,B)` → `A.removeListener` blocks on `A.listeners` | FRS:113 |

* How to trigger: the user starts another find-references (B) while A's job is starting. `InternalSearchUI.runSearchInBackground(B)` → `SearchViewManager.showNewSearchQuery` → `SearchView.showSearchResult` → `page.setInput(B)` runs on the UI thread. Picking another result from the search history also works.
* Result: S waits for the UI and the UI waits for S. The workbench freezes.
* Re-running A itself is safe, because `setInput(A)` happens before `job.schedule()`.
* The Xtext superclass has no `syncExec` and no such deadlock.

### F3: Another search's in-flight node shown under result B. **CONFIRMED**
Shortest trace (12 steps):

| # | Actor | Step | Code |
|---|---|---|---|
| 1-3 | S | `accept(ref0 in uri0)`, lock, `get(uri0)` = null | RSR.accept, FRS:170, FRS:158 |
| 4 | UI | `inputChanged(A,B)`: `rootNodes.clear()` | FRS:111 |
| 5-10 | S | `put(uri0, node0)`, batch add, attach, flag, schedule, release lock | FRS:160-175 |
| 11 | UI | `A.removeListener` (it was waiting for the lock) | FRS:113 |
| 12 | UI | `setInput(B)` refresh | FRS:216 semantics |

End state: B's view shows A's node0.

* Cause: the clear (111) comes before the listener removal (113). An A handler that is already in flight writes into the cleared map, which now belongs to B.
* If B is a fresh search, B's own Reset runnable removes the node shortly after, so it is transient.
* If B was picked from history (not re-run), the node stays for good: it is in `rootNodes`, and A's still-scheduled UIUpdater also refreshes it into view.

### F4: CME on the UI thread in `inputChanged`. **CONFIRMED**
Shortest trace (8 steps):

| # | Actor | Step | Code |
|---|---|---|---|
| 1-3 | UI | switch to B | |
| 4-5 | UI | switch back: `inputChanged(B,A)`, `A.addListener` | FRS:116 |
| 6 | UI | `getMatchingReferences().iterator()` | FRS:118 |
| 7 | S | `accept`: `matchingReferences.add`, no lock | RSR.accept |
| 8 | UI | `next()` throws `ConcurrentModificationException` out of `viewer.setInput` | FRS:118 |

* How to trigger: switching the Search view back to a search that is still running.
* A `reset()` (`clear`) during the loop does the same.
* The Xtext superclass has the same live iteration.

### F5: `resourceNode` check-then-act races the `inputChanged` repopulation. **CONFIRMED**
This was hidden behind F1. It showed up once F1 was patched (28 steps; see `report.txt`, section "patch: lost"). Every step in the trace behaves the same in the unpatched code.

| # | Actor | Step | Code |
|---|---|---|---|
| — | S | `accept(ref1 in uri0)`: added to the list *before* A.addListener; fired *after* it | RSR.accept |
| 10 | S | `get(uri0)` = null | FRS:158 |
| 13-14 | UI | loop for ref0: `get(uri0)` = null; `put(uri0, node0)` | FRS:119→158, FRS:160-161 |
| 15-17 | S | `put(uri0, node1)` (overwrites node0); batch add; attach ref1→node1 | FRS:160-161, 162-164, 137 |
| 19-20 | UI | batch add node0; attach ref0→node0 | FRS:119 |
| 21-23 | UI | loop for ref1: `get(uri0)` = node1; attach ref1→node1 **again** | FRS:119 |

End state:

* Two root nodes were created for uri0. Only node1 is left in `rootNodes` and the viewer.
* ref0 hangs under the orphaned node0 and is never shown (P1 violated).
* ref1 is shown twice (duplicate row).
* Cause: FRS:158-161 is not atomic, and both the UI (inputChanged) and S call it. A reference that is accepted before the iterator is created but fired after `addListener` gets processed twice.
* Trigger: the same history-switch as F4, without the CME.

### Observations (not violations)
* **Reset does not clear `batchAddNodes` (FRS:182-183).** The next UIUpdater re-adds pre-reset nodes at FRS:213. The full `refresh()` at FRS:216 removes them again inside the same UI runnable, so they are never visible: P3 holds on this path in every variant. The code is correct only because of that refresh.
* **P2 holds everywhere.** `rootNodes` is keyed by URI and every UI runnable ends with a full refresh. F5 still shows that two node objects can be created for one URI.
* **`asyncExec` alone is not a fix for F2.** Swapping `syncExec` for `asyncExec` removes the deadlock, but the Reset runnable then clears nodes added *after* the reset, which loses them (P1, 13 steps). The fixed design handles Reset on S under the provider lock.
* **Not modelled:** if `runInUIThread` throws (for example on a disposed viewer), `isUIUpdateScheduled` stays true for good and this FRS instance never schedules again.

### Model artefact (found and fixed)
In the `lost+order+snap` variant, P3 first failed because the ghost `resetPending` was cleared at the end of `inputChanged`, even though a Reset event was still in flight. The real code would clear the node when that Reset arrived. The fix to the ghost: a refresh counts as reflecting the reset only when no Reset is in flight (`resetInFlight`). Verdict for that first trace: **MODEL-ARTEFACT**. No other violation above depends on ghost data except through P3, and F3's end state has input = B, which needs no ghost.

## Fixed design (what `Fixed.lean` checks)
1. `addReference` / `resourceNode` and the flag test-and-set run atomically under one provider lock (dedupe: skip a ref already attached to the current root). UIUpdater's "batch empty ? clear flag : reschedule" runs under the same lock.
2. Reset is handled on S under that lock: clear `rootNodes` and the batch, and make sure an updater is scheduled. No `syncExec`.
3. `inputChanged` calls `removeListener` before clearing. On the way back it calls `addListener`, then snapshots and rebuilds under the provider lock and refreshes.

## Wall time
About 27 minutes end to end: about 6 minutes reading code and framework sources, about 21 minutes modelling, checking, proving and writing. Machine time: `lake build` about 65 s, `Report.lean` about 2 min 15 s.
