/-
Repaired design (not the Java as written). Changes w.r.t. FRS:
  F1 addReference/resourceNode + flag test-and-set run atomically under one provider lock
     (synchronized(batchAddNodes)); the UIUpdater's "batch empty? clear flag : reschedule"
     is atomic under the same lock.                       [fixes lost update]
  F2 Reset is handled on the search thread under that lock (clear rootNodes and batch,
     ensure an updater is scheduled); no Display.syncExec, so no UI wait while the
     RSR listeners monitor is held.                        [fixes deadlock, stale batch]
  F3 inputChanged removes the old listener before clearing, and rebuilds from a
     snapshot taken after addListener, under the provider lock; addReference skips a
     reference already attached to the current root.       [fixes stale-on-switch, CME,
                                                            duplicate root / lost ref]
`plant := true` plants an obvious bug (flag set without scheduling the job).
-/
import FindRefs.State

namespace FindRefs.Fixed
open FindRefs

/-- resourceNode + attach child, deduplicated (part of the atomic addReference). -/
def attachRef (s : St) (r : Nat) (withBatch : Bool) : St :=
  let u := uriOfRef s r
  match getRoot s.roots u with
  | some n => if s.attach.contains (r, n) then s else { s with attach := s.attach ++ [(r, n)] }
  | none =>
    let n := s.nodes.length
    { s with nodes := s.nodes ++ [(u, epochOfRef s r)], roots := setRoot s.roots u (some n),
             batch := if withBatch then s.batch ++ [n] else s.batch, attach := s.attach ++ [(r, n)] }

/-- Flag test-and-set under the batch lock. -/
def ensureScheduled (plant : Bool) (s : St) : St :=
  if s.flag then s else { s with flag := true, jobs := if plant then s.jobs else s.jobs + 1 }

/-- Atomic addReference under the provider lock (F1 + dedupe). -/
def addRef (plant : Bool) (s : St) (r : Nat) : St := ensureScheduled plant (attachRef s r true)

/-- Rebuild from a snapshot of matchingReferences (F3); the viewer is refreshed right after. -/
def rebuild (s : St) : St :=
  s.matching.foldl (fun acc r => attachRef acc r false) { s with roots := clearRoots s.roots }

def sStep (plant : Bool) (c : Cfg) (s : St) : List (String × St) :=
  match s.spc with
  | .idle => searchStarts c s
  | .fire r =>
    if s.listening then [(s!"S  fireEvent(Added ref{r}) -> atomic addReference", { addRef plant s r with spc := .idle })]
    else [(s!"S  fireEvent(Added ref{r}): not a listener, dropped", { s with spc := .idle })]
  | .rfire =>
    if s.listening then
      [("S  fireEvent(Reset) -> atomic clear rootNodes+batch, ensure updater",
        ensureScheduled false { s with roots := clearRoots s.roots, batch := [], spc := .idle })]
    else [("S  fireEvent(Reset): not a listener, dropped", { s with spc := .idle, resetPending := false })]
  | _ => []

def uStep (_c : Cfg) (s : St) : List (String × St) :=
  match s.upc with
  | .idle =>
    (if s.jobs > 0 then
      [("UI UIUpdater: atomic snapshot+clear batch, add, refresh",
        { s with jobs := s.jobs - 1, batch := [], viewer := rootsVals s.roots,
                 resetPending := s.resetPending && resetInFlight s, upc := .uDecideF })]
     else []) ++
    (if s.switchB > 0 && s.input == .A then
      [("UI inputChanged(A,B): A.removeListener", { s with switchB := s.switchB - 1, listening := false, upc := .wFix })]
     else []) ++
    (if s.switchB > 0 && s.input == .B then
      [("UI inputChanged(B,A): A.addListener", { s with switchB := s.switchB - 1, listening := true, upc := .bFix })]
     else [])
  | .uDecideF =>
    if s.batch.isEmpty then [("UI atomic: batch empty -> flag := false", { s with flag := false, upc := .idle })]
    else [("UI atomic: batch non-empty -> schedule(250)", { s with jobs := s.jobs + 1, upc := .idle })]
  | .wFix => [("UI inputChanged(A,B): clear rootNodes+batch; setInput(B) refresh",
      { s with roots := clearRoots s.roots, batch := [], input := .B, viewer := [], upc := .idle })]
  | .bFix => [("UI inputChanged(B,A): rebuild from snapshot; setInput(A) refresh",
      let s1 := rebuild s
      { s1 with input := .A, viewer := rootsVals s1.roots,
                resetPending := s.resetPending && resetInFlight s, upc := .idle })]
  | _ => []

def next (plant : Bool) (c : Cfg) (s : St) : List (String × St) :=
  sStep plant c s ++ uStep c s

end FindRefs.Fixed
