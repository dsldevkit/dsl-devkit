# FindRefs TLA+ model: FastReferenceSearchResultContentProvider

This directory holds a blind model of `com.avaloq.tools.ddk.xtext.ui/.../findrefs/FastReferenceSearchResultContentProvider.java` (FRSRCP). It also models the Xtext classes the provider works with: `ReferenceSearchResult` (RSR), `ReferenceQuery` and `ReferenceSearchViewPage` (RSVP), and the Eclipse Search classes `InternalSearchUI`, `SearchView` and `SearchViewManager`. The Xtext sources come from eclipse/xtext at e8855b27fd. The Eclipse Search sources come from the `org.eclipse.search.source_3.19.0` p2 bundle (extract locally; they are not committed).

## Files
- `FindRefs.tla`: the spec, 413 non-blank lines. `Fixes` (a set of fix names) and `Planted` switch between the original code, the fixed code and the planted bug.
- `FindRefs.cfg` checks the original code. `FindRefsFixed.cfg` checks the model with all minimal fixes.
- `run.sh <label> <Fixes> <Planted> <Refs> <MaxRuns> <MaxSwitch> <INV...>` runs a single configuration. TLC cfg files cannot hold sequences, so it writes a small MC wrapper under `runs/`. Set `NODL=1` to pass `-deadlock`.
- `matrix.sh` runs the full matrix: one property per run on the original code, the fixed model, the ablations, the planted bug and the witnesses. `matrix-small.log` is its latest output.
- `coverage.sh` is a sanity check that every action fires, using TLC `-coverage`.
- `trace.py` condenses a TLC trace to one line per step, showing only the variables that changed. `traces/*.txt` holds the condensed shortest counterexamples.

## Threading assumptions
These were decided from the code and from Eclipse semantics.
1. **Search thread.** The events `Added`, `Reset` and `Finish` all run on one Job worker, `InternalSearchJob` → `ReferenceQuery.run`. `run()` calls `reset()` first, then the finder calls `accept` sequentially.
   - `RSR.fireEvent` holds the `listeners` monitor while it calls each listener (RSR:54-57), so all provider handlers for one result are serialized.
   - `matchingReferences.add` / `clear` run **outside** that monitor (RSR:85, 104).
   - The model has one search result R. B stands for another search that has already finished, such as an entry in the search history.
2. **UI thread.** The UI thread runs one runnable or event handler at a time. When idle, it picks any pending runnable (a nondeterministic choice). This covers:
   - the `syncExec` runnable for Reset (FRSRCP:178);
   - the `UIUpdater` UIJob. `schedule()` adds one pending run; `schedule(250)` from inside `runInUIThread` adds another after the current run;
   - user actions, which are split into steps so that the search thread can interleave with them.
3. **New search.** `runSearchInBackground` calls `addQuery` → `fireAdded` → `SearchViewManager.showNewSearchQuery` → `page.setInput(R)` synchronously on the UI thread, and only afterwards schedules the job. So in the initial state the provider is already R's listener and the job is pending.
4. **"Search Again"** (`SearchAgainAction`) schedules the same query again. `addQuery` does nothing because the query already exists, and there is no `setInput`. `isQueryRunning` blocks a rerun while a run is in progress.
5. **View switch.** `SearchView.internalShowSearchPage` calls `page.setInput(null)` and then `page.setInput(X)`. `setInput(null)` only removes `labelUpdater` from the old result (RSVP:139), which takes that result's `listeners` monitor. `setInput(X)` → `viewer.setInput` → `inputChanged` (FRSRCP:109-123), then `refresh()`.
6. **Viewer.** The viewer is modelled as the set of its root items. `add` is a union and `remove` is a set difference. `refresh()` sets the root items to `getElements()`, which is `rootNodes.values()`. Children are fetched lazily from `node.getChildren()`, so a reference counts as visible when it is a child of a shown root.
7. **Granularity.** Each `batchAddNodes` operation is atomic (its own lock). A `ConcurrentHashMap` read or write is atomic, and a whole `values()` snapshot is treated as atomic. Every check-then-act is split into separate steps:
   - `get` → `put` → `batch.add` → `addChild`;
   - read the flag → set the flag → `schedule`;
   - the `isEmpty` check → `isUIUpdateScheduled=false`;
   - the `ArrayList` iterator's `hasNext` and `next`, with a modCount check.
8. **Not modelled.**
   - `descriptionsChanged`, which the builder runs via `asyncExec`.
   - `dispose`.
   - Label providers.
   - The data race on `ReferenceSearchViewTreeNode.children`: an `ArrayList` that the search thread writes while the UI thread reads it. See "Code-reading extras" below.
   - JMM visibility. Every write is modelled as immediately visible, which only removes behaviours, so every violation found is still a real behaviour.

## Properties
`Settled` means the search is done, the UI thread is idle, no job or runnable is pending, the view shows R, and no CME has occurred.

| Name | Meaning | Requested expectation |
|---|---|---|
| `NoLostRoot`, `NoLostRef` | When Settled, every accepted reference, and its resource's root node, is visible | no lost update |
| `OneRootPerUri` | Whenever the UI thread is idle, the viewer shows at most one root per URI | one root per URI (viewer level) |
| `OneRootCreated` | Since the last `rootNodes.clear()`, at most one root node was created per URI | one root per URI (provider level) |
| `NoDupRef` | When Settled, no reference appears twice | extra (a consequence of the above) |
| `NoStale` | Whenever the UI thread is idle, no shown node predates the last Reset or inputChanged it has processed | no pre-reset node shown |
| `NoForeign` | While B is displayed, no node of R is shown | no pre-reset node shown (input change) |
| `NoCME` | `inputChanged` never throws `ConcurrentModificationException` | extra |
| `NoDeadlock` | The search thread is not stuck in `syncExec` while the UI thread waits for R.listeners. TLC's own deadlock check is also on, with `Terminated` as the only allowed final stutter | extra |

Witnesses are expected to be violated. Each violation proves that the named good path is reachable:
- `W_AllShown`: a search completes with every reference shown.
- `W_RerunShown`: the same after Search Again.
- `W_SwitchBack`: the same after switching to B and back.
- `W_ResetRan`: the second Reset is processed.
- `W_Rescheduled` (original code only): `schedule(250)` is reached.

## Results
TLC 2.19. Unless noted, the configuration is Refs=<<u1,u2>>, MaxRuns=2, MaxSwitch=2, run with `-workers auto` on 14 cores. BFS, so every trace is a shortest one.

| Run | Result | States (distinct) | Time |
|---|---|---|---|
| Original, each property separately | violated: NoLostRoot (31 steps), NoLostRef (31), OneRootCreated (22), NoDupRef (46), NoForeign (23), NoCME (19), NoDeadlock (6; TLC also reports "Deadlock reached") | 60 to 20k before the violation | ≤2 s each |
| Original, `OneRootPerUri` and `NoStale` | **hold** (whole space) | 159,326 | 2 s |
| Original, same two properties, Refs=<<u1,u2,u1>>, MaxRuns=2, MaxSwitch=3 | **hold** | 5,672,600 | 27 s |
| Fixed, all properties plus the deadlock check | pass | 78,755 | 3 s |
| Fixed, Refs=<<u1,u2,u1>>, MaxRuns=2, MaxSwitch=3 | pass | 486,346 | 4 s |
| Fixed, Refs=<<u1,u2,u1,u2>>, MaxRuns=3, MaxSwitch=4 | pass | 50,559,501 (depth 202) | 400 s |
| Planted bug on the fixed model | caught: NoLostRoot | 1,144 | 1 s |
| Ablations: the fixed model minus one fix | each fails; see "Fixes" | — | ≤2 s |
| Witnesses, original and fixed | all reached | — | ≤2 s |
| `coverage.sh` | every action fires. The only exceptions are actions that belong to the other configuration and the `Terminated` stutter | orig 159k, fixed 79k | ~10 s |

The full matrix (`matrix.sh`) takes about 40 s.

Model bugs that the sanity checks caught while building the model:
1. An `UNCHANGED` clash disabled `UIRunSyncReset`. This silently removed every behaviour after a Reset. The unreachable `W_ResetRan` and `W_RerunShown` witnesses exposed it, and the coverage check pinned it down.
2. A first "fixed" design, which moved the Reset work to the search thread with `asyncExec`, raced with `inputChanged` and violated `NoStale`. The model found that my own fix was wrong. It led to the extra `plock` fix.

## Findings
Condensed traces are in `traces/orig_*.txt`. Line numbers are FRSRCP unless another file is named.

### F1: lost update from the UIUpdater flag race (`NoLostRoot`, `NoLostRef`). CONFIRMED
Trace (31 steps, main part):
1. Search (S) Added(r1): `get`=null, `put`, `batch.add`, flag false→true, `schedule` (158-175).
2. S calls `accept(r2)` (RSR:85) and locks (RSR:54).
3. UI `runInUIThread`: drains and adds n1 (207-215).
4. S `get(u2)`=null, creates n2 (158-160).
5. UI `refresh()` (216) shows only {n1}.
6. UI `batchAddNodes.isEmpty()` is **true** (217).
7. S `put(u2,n2)` (161), `batch.add(n2)` (163), reads `isUIUpdateScheduled`==**true** → no schedule (173).
8. UI `isUIUpdateScheduled=false` (220).
9. S finishes.

Result: n2 is in `rootNodes` and `batchAddNodes`, but nothing will ever run another UIUpdater or refresh.

Verdict: **CONFIRMED.**
- The window is lines 217-220 on the UI side against 163-173 on the search side. It is small, but nothing closes it: the check and the clear are separate, and the flag is only `volatile`.
- The loss is permanent only if no later `Added` event arrives, so it affects the tail of a search. Any later event re-triggers the updater and drains the stranded node.
- Upstream Xtext's `ReferenceSearchResultContentProvider.UIUpdater` sets `isUIUpdateScheduled = false` *before* draining, which does not have this race. The DDK copy reordered it.
- Fix `flag`: clear the flag first, as upstream does, and drop the `isEmpty`/reschedule tail.

### F2: deadlock between syncExec in the Reset handler and the listeners monitor (`NoDeadlock`). CONFIRMED
Trace (6 steps):
1. The job starts. `ReferenceQuery.run` → `reset()` → `fireEvent(Reset)` takes **R.listeners** (RSR:54).
2. The provider calls `Display.syncExec(...)` (178) and waits for the UI thread.
3. Meanwhile the UI thread is handling a user action that shows another search: a history entry, or a new Find References that goes through `showNewSearchQuery`. That path is `SearchView.internalShowSearchPage` → `page.setInput(null)` → `synchronized(viewer)` → `R.removeListener(labelUpdater)` (RSVP:137-139), which blocks on R.listeners.

Result: the UI thread never runs the syncExec runnable, so both threads wait forever and the workbench freezes.

Verdict: **CONFIRMED** by reading the code.
- The window runs from the job starting (right after the search is launched, or after "Search Again") until the UI thread services the syncExec. It is short, but a second Find References or a history pick fired at that moment hits it.
- Starting a Find References immediately after another one is enough.
- The same shape is reachable through `inputChanged` → `removeListener(this)` (FRSRCP:113).
- Fix `reset`: never block the search thread on the UI while inside `fireEvent`. Clear `rootNodes` and `batchAddNodes` on the search thread and `asyncExec` only `viewer.refresh()`. This requires `plock`; see F4.

### F3: R's nodes leak into another search's view (`NoForeign`). CONFIRMED
Trace (23 steps):
1. R is running and the user switches the view to B.
2. `inputChanged` runs `rootNodes.clear()` (111) **before** `R.removeListener(this)` (113).
3. In between, S's Added handler, which is still registered, puts n1 into `rootNodes` (158-163).
4. `removeListener` then runs, and `setInput`'s `refresh()` shows n1 in B's page.

Result: nothing ever removes n1 while B is shown, because every later `refresh()` re-reads `rootNodes`.

Verdict: **CONFIRMED**.
- It is persistent when B is an existing (finished) search picked from history.
- When B is a brand-new search, B's own Reset clears it, so the leak is transient.
- Fix `detach`: call `removeListener(this)` before `rootNodes.clear()`. Removing the listener waits for any in-flight handler because it needs the same monitor.

### F4: two root nodes for one URI, and a reference lost under the orphan (`OneRootCreated`, and `NoLostRef` once F1 is fixed). CONFIRMED
Trace (22 steps):
1. R is running. The user switches to B and back to R.
2. `inputChanged(R)`: clear (111), `addListener` (116), iterate `matchingReferences` (118-119) → `addReference(r1)` → `resourceNode`: `get(u1)`=null (158), new node n1.
3. Concurrently, S's Added(r2) for the same resource: `get(u1)`=null, new node n2.
4. Both `put` (161) and both `batch.add`. The last `put` wins. The other node is shown by the UIUpdater's `add` and then removed by its `refresh()`.
5. The reference under the losing node disappears from the view.

The full manifestation, with F1 fixed so that it is not masked, is in `traces/orig_flagfixed_lost_ref_via_duplicate_root.txt`: `shown={1,2}` inside one UI job, then `{1}` after the refresh, with ref r2 lost. The ablation trace is `traces/fixed_minus_plock_OneRootCreated.txt`.

Verdict: **CONFIRMED.** `resourceNode` (157-166) is a non-atomic check-then-act, and `inputChanged`'s `addReference` runs on the UI thread concurrently with the search thread's handler.
- `OneRootPerUri` at the viewer level **holds**, because every UI path ends in `refresh()`. The duplicate root is invisible, but its references are lost.
- Fix `atomic` (`computeIfAbsent`) removes the duplicate root. The fixed model uses `plock`, which subsumes it: the Added/Reset handler bodies and the `inputChanged` population run under one private provider lock. The order is `addListener` first, then under the lock: clear, snapshot, populate. This avoids a lock-order inversion with R.listeners, which TLC's deadlock check on the fixed model confirms.
- `plock` is also what makes `reset` safe. Without it, the model shows (`NoStale`, `traces/fixed_minus_plock_with_atomic_NoStale.txt`) that an asynchronous Reset can interleave with `inputChanged` repopulating from a stale snapshot.

### F5: the same reference is shown twice (`NoDupRef`). CONFIRMED
Trace (46 steps):
1. S calls `matchingReferences.add(r2)` (RSR:85, outside the lock).
2. UI `inputChanged`: `addListener` (116), then iterates and adds r2 (118-119).
3. S's `fireEvent(Added r2)` then reaches the now-registered provider (RSR:54-56) → `addReference(r2)` again.

Result: two `DynamicReferenceSearchViewTreeNode` children for one reference.

Verdict: **CONFIRMED.** There is no dedupe, and the registration/snapshot race is inherent to Xtext's `accept`.
- Fix `dedupe`: skip adding a child if the node already has a child with the same `IReferenceDescription`, done under `plock`.

### F6: ConcurrentModificationException in inputChanged (`NoCME`). CONFIRMED (racy)
Trace (19 steps):
1. The user switches back to a running R.
2. `inputChanged` takes `getMatchingReferences().iterator()` (118) after R's reset.
3. S's `accept` does `matchingReferences.add` (RSR:85).
4. The iterator's `next()` sees a modCount mismatch and throws.

Verdict: **CONFIRMED**, subject to JMM visibility: the write is unsynchronized, so the CME is possible but not guaranteed.
- The exception escapes `viewer.setInput`. The page is then left half-initialised: the provider is registered but the input is not set.
- Fix `snapshot`: copy the list (under `plock`) and iterate the copy.

### Held (no violation)
- `OneRootPerUri` (viewer level) and `NoStale` (nothing from before a Reset is shown at idle) hold for the original code, up to 5.7M states.
- The reasons are that the Reset runnable runs on the UI thread while the search thread is blocked, and that every UI path ends in `refresh()` from `rootNodes`. `batchAddNodes` is **not** cleared on Reset or inputChanged, so stale nodes do get `viewer.add`-ed. The same UI job's `refresh()` removes them before the UI thread goes idle.

## Fixes
The minimal fix set is `{flag, detach, dedupe, snapshot, plock, reset}`. Ablation: removing any one fix makes the fixed model fail.

| Removed fix | Property that fails |
|---|---|
| `flag` | NoLostRoot |
| `detach` | NoForeign |
| `dedupe` | NoDupRef |
| `snapshot` | NoCME |
| `plock` | OneRootCreated |
| `reset` | NoDeadlock |

`atomic` (computeIfAbsent) passes when removed, so it is redundant with `plock`.

## Code-reading extras (not modelled)
- `ReferenceSearchViewTreeNode.children` is a plain `ArrayList`. The search thread writes it through `addChild`, and the UI thread reads it through `getChildren`/`hasChildren` during refresh and expansion. That is a second possible CME or inconsistent read, and the same race exists upstream.
- `descriptionsChanged` (228-270) mutates `rootNodes` and children on the UI thread concurrently with the search thread. It is the same class of check-then-act as F4 and was not modelled.

## Wall time
About 25 minutes, from 14:28 to 14:53 including reading the code, plus the single 400 s large run.
