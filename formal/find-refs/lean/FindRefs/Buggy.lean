/-
Faithful model of FastReferenceSearchResultContentProvider as written (FRS = that file).
Every shared-memory access that is not protected by a common lock is its own step.
-/
import FindRefs.State

namespace FindRefs.Buggy
open FindRefs

/-- Individual repairs, applied one at a time to expose bugs masked by earlier ones.
    `Patch.none` is the code as written. -/
structure Patch where
  lost : Bool := false      -- batch-add/flag test-and-set and isEmpty/flag-clear under the batch lock
  order : Bool := false     -- inputChanged: removeListener(old) before rootNodes.clear()
  snap : Bool := false      -- inputChanged: iterate a snapshot copy of matchingReferences
  async : Bool := false     -- Reset via asyncExec instead of syncExec (does not wait)
deriving Repr

def Patch.none : Patch := {}

/-- Search-thread steps. -/
def sStep (P : Patch) (c : Cfg) (s : St) : List (String × St) :=
  match s.spc with
  | .idle => searchStarts c s
  | .fire r =>
    -- RSR.fireEvent: synchronized(listeners) { for l : listeners ... }
    if s.listening then [(s!"S  RSR.fireEvent(Added ref{r}) takes listeners lock -> FRS:170", { s with lockS := true, spc := .get r })]
    else [(s!"S  RSR.fireEvent(Added ref{r}): FRS not a listener, dropped", { s with spc := .idle })]
  | .get r =>
    let u := uriOfRef s r
    match getRoot s.roots u with
    | some n => [(s!"S  FRS:158 rootNodes.get(uri{u}) = node{n}", { s with spc := .child r n })]
    | none => [(s!"S  FRS:158 rootNodes.get(uri{u}) = null", { s with spc := .put r })]
  | .put r =>
    let u := uriOfRef s r
    let n := s.nodes.length
    [(s!"S  FRS:160-161 rootNodes.put(uri{u}, node{n})",
      { s with nodes := s.nodes ++ [(u, epochOfRef s r)], roots := setRoot s.roots u (some n), spc := .bat r n })]
  | .bat r n => [(s!"S  FRS:162-164 batchAddNodes.add(node{n})", { s with batch := s.batch ++ [n], spc := .child r n })]
  | .child r n => [(s!"S  FRS:137 attach ref{r} under node{n}", { s with attach := s.attach ++ [(r, n)], spc := .flg })]
  | .flg =>
    if P.lost then
      if s.flag then [("S  [patched] synchronized(batch){flag already true}; release listeners lock", { s with spc := .idle, lockS := false })]
      else [("S  [patched] synchronized(batch){flag := true; schedule}; release listeners lock", { s with flag := true, jobs := s.jobs + 1, spc := .idle, lockS := false })]
    else if s.flag then [("S  FRS:173 isUIUpdateScheduled == true -> skip schedule; release listeners lock", { s with spc := .idle, lockS := false })]
    else [("S  FRS:173 isUIUpdateScheduled == false", { s with spc := .setf })]
  | .setf => [("S  FRS:174 isUIUpdateScheduled = true", { s with flag := true, spc := .sched })]
  | .sched => [("S  FRS:175 new UIUpdater().schedule(); release listeners lock", { s with jobs := s.jobs + 1, spc := .idle, lockS := false })]
  | .rfire =>
    if s.listening then
      if P.async then [("S  [patched] RSR.fireEvent(Reset) -> Display.asyncExec (no wait)", { s with syncReq := true, spc := .idle })]
      else [("S  RSR.fireEvent(Reset) takes listeners lock -> FRS:178 Display.syncExec (blocks)", { s with lockS := true, syncReq := true, spc := .rwait })]
    else [("S  RSR.fireEvent(Reset): FRS not a listener, dropped", { s with spc := .idle, resetPending := false })]
  | .rwait =>
    if s.syncReq then [] else [("S  FRS:178 syncExec returns; release listeners lock", { s with lockS := false, spc := .idle })]

/-- UI-thread steps. -/
def uStep (P : Patch) (_c : Cfg) (s : St) : List (String × St) :=
  match s.upc with
  | .idle =>
    (if s.jobs > 0 then
      [(s!"UI UIUpdater starts: FRS:207-210 snapshot+clear batch {s.batch}; FRS:212-215 viewer.add each",
        { s with jobs := s.jobs - 1, batch := [], viewer := s.batch.foldr sins s.viewer, upc := .uRefresh })]
     else []) ++
    (if s.syncReq then
      [("UI Reset runnable FRS:181-186: viewer.remove(rootNodes.values), rootNodes.clear, refresh",
        { s with roots := clearRoots s.roots, viewer := [], syncReq := false, resetPending := false })]
     else []) ++
    (if s.switchB > 0 && s.input == .A then
      if P.order then
        [("UI [patched] user shows other result B: inputChanged(A,B) removeListener first",
          { s with switchB := s.switchB - 1, upc := .wRemove })]
      else
      [("UI user shows other result B: setInput(B) -> inputChanged(A,B) FRS:111 rootNodes.clear",
        { s with switchB := s.switchB - 1, roots := clearRoots s.roots, upc := .wRemove })]
     else []) ++
    (if s.switchB > 0 && s.input == .B then
      [("UI user shows result A again: setInput(A) -> inputChanged(B,A) FRS:111 rootNodes.clear",
        { s with switchB := s.switchB - 1, roots := clearRoots s.roots, upc := .bAdd })]
     else [])
  | .uRefresh => [("UI FRS:216 viewer.refresh() (root items := rootNodes.values)", { s with viewer := sset (rootsVals s.roots), upc := .uCheck })]
  | .uCheck =>
    if P.lost then
      if s.batch.isEmpty then [("UI [patched] synchronized(batch){empty -> flag := false}", { s with flag := false, upc := .idle })]
      else [("UI [patched] synchronized(batch){non-empty -> schedule(250)}", { s with jobs := s.jobs + 1, upc := .idle })]
    else [(s!"UI FRS:217 batchAddNodes.isEmpty() = {s.batch.isEmpty}", { s with upc := .uDecide s.batch.isEmpty })]
  | .uDecide e =>
    if e then [("UI FRS:220 isUIUpdateScheduled = false", { s with flag := false, upc := .idle })]
    else [("UI FRS:218 schedule(250)", { s with jobs := s.jobs + 1, upc := .idle })]
  | .wRemove =>
    -- RSR.removeListener is synchronized(listeners): blocks while S holds it
    if s.lockS then []
    else if P.order then [("UI [patched] A.removeListener(this); rootNodes.clear", { s with listening := false, roots := clearRoots s.roots, upc := .wFinish })]
    else [("UI FRS:113 A.removeListener(this)", { s with listening := false, upc := .wFinish })]
  | .wFinish => [("UI FRS:116 B.addListener; ContentViewer.setInput(B) refresh", { s with input := .B, viewer := sset (rootsVals s.roots), upc := .idle })]
  | .bAdd =>
    if s.lockS then []
    else [("UI FRS:116 A.addListener(this)", { s with listening := true, upc := .bIter })]
  | .bIter =>
    if P.snap then [("UI [patched] snapshot = copy of matchingReferences", { s with snap := some s.matching, upc := .bLoop 0 s.mc })]
    else [("UI FRS:118 matchingReferences.iterator()", { s with upc := .bLoop 0 s.mc })]
  | .bLoop cur m =>
    let l := s.snap.getD s.matching
    if cur == l.length then [("UI FRS:118 iterator.hasNext() = false", { s with snap := none, upc := .bDone })]
    else if s.snap.isNone && s.mc != m then [("UI FRS:118 iterator.next() throws ConcurrentModificationException", { s with cme := true, upc := .idle })]
    else
      let r := (l[cur]?).getD 0
      [(s!"UI FRS:118 next() = ref{r}", { s with upc := .bGet cur m r })]
  | .bGet cur m r =>
    let u := uriOfRef s r
    match getRoot s.roots u with
    | some n => [(s!"UI FRS:119->158 rootNodes.get(uri{u}) = node{n}", { s with upc := .bChild cur m r n })]
    | none => [(s!"UI FRS:119->158 rootNodes.get(uri{u}) = null", { s with upc := .bPut cur m r })]
  | .bPut cur m r =>
    let u := uriOfRef s r
    let n := s.nodes.length
    [(s!"UI FRS:119->160-161 rootNodes.put(uri{u}, node{n})",
      { s with nodes := s.nodes ++ [(u, epochOfRef s r)], roots := setRoot s.roots u (some n), upc := .bBat cur m r n })]
  | .bBat cur m r n => [(s!"UI FRS:119->162-164 batchAddNodes.add(node{n})", { s with batch := s.batch ++ [n], upc := .bChild cur m r n })]
  | .bChild cur m r n => [(s!"UI FRS:119->137 attach ref{r} under node{n}", { s with attach := s.attach ++ [(r, n)], upc := .bLoop (cur + 1) m })]
  | .bDone => [("UI ContentViewer.setInput(A): input := A; refresh", { s with input := .A, viewer := sset (rootsVals s.roots), resetPending := s.resetPending && resetInFlight s, upc := .idle })]
  | .uDecideF | .wFix | .bFix => []

def next (P : Patch) (c : Cfg) (s : St) : List (String × St) :=
  if s.cme then [] else sStep P c s ++ uStep P c s

end FindRefs.Buggy
