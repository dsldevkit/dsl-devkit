/-
Shared state of the find-references model.

Code under study (all line numbers refer to this file unless prefixed):
  com.avaloq.tools.ddk.xtext.ui/src/com/avaloq/tools/ddk/xtext/ui/editor/findrefs/
    FastReferenceSearchResultContentProvider.java            ("FRS:<line>")
Xtext 2.44 org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResult   ("RSR.<method>")

Actors
  * S  : the search job thread (Eclipse InternalSearchJob -> ReferenceQuery.run).
         It calls RSR.reset() once at start and RSR.accept(ref) per match.
         RSR.accept/reset mutate `matchingReferences` WITHOUT a lock and then call
         RSR.fireEvent, which holds `synchronized(listeners)` while calling
         FRS.searchResultChanged.  => all event deliveries are serialised by the
         `listeners` monitor (modelled by `lockS`).
  * UI : the single SWT display thread.  Each UI runnable (a UIJob body, a
         syncExec runnable, a viewer.setInput -> inputChanged call) runs to
         completion without interleaving with other UI runnables, but S steps
         interleave freely between its individual shared-memory accesses.
  * Jobs: `new UIUpdater().schedule()` / `schedule(250)` put a UIJob in the
         job queue (`jobs` = number of scheduled-but-not-started instances).
         A UIJob runs later as a UI runnable.
  * Viewer: JFace TreeViewer. `refresh()` re-reads getElements() = rootNodes.values(),
         so after refresh the root items are exactly the current map values.
         `add` of an already present element is deduplicated (AbstractTreeViewer
         itemExists).  Children are fetched lazily => a reference is visible
         iff it is attached to a root node that is shown (optimistic).
  * Input: the viewer input is either the result A under study, or some other
         result B (a different reference search shown in the same page).
-/
import Std.Data.HashSet
import Std.Data.HashMap

namespace FindRefs

structure Cfg where
  nU : Nat := 2        -- number of resource URIs
  adds : Nat := 3      -- accept() calls available to S
  resets : Nat := 1    -- reset() calls available to S (search (re)start)
  switches : Nat := 2  -- page input switches A->B / B->A available to the user
deriving Repr

/-- Search-thread program counter. `r` = reference id, `n` = node id. -/
inductive SPc where
  | idle
  | fire (r : Nat)          -- RSR.accept: matchingReferences.add done; fireEvent next
  | get (r : Nat)           -- FRS:158 rootNodes.get(uri)
  | put (r : Nat)           -- FRS:160-161 new node, rootNodes.put
  | bat (r n : Nat)         -- FRS:162-164 synchronized batchAddNodes.add
  | child (r n : Nat)       -- FRS:137 new DynamicReferenceSearchViewTreeNode(resourceNode, ..)
  | flg                     -- FRS:173 read isUIUpdateScheduled
  | setf                    -- FRS:174 isUIUpdateScheduled = true
  | sched                   -- FRS:175 new UIUpdater().schedule(); then release `listeners`
  | rfire                   -- RSR.reset: matchingReferences.clear done; fireEvent next
  | rwait                   -- FRS:178 blocked in Display.syncExec
deriving BEq, Hashable, Repr, DecidableEq

/-- UI-thread program counter (which UI runnable is mid-flight, and where). -/
inductive UPc where
  | idle
  | uRefresh                -- UIUpdater: FRS:216 viewer.refresh() next
  | uCheck                  -- FRS:217 batchAddNodes.isEmpty() next
  | uDecide (empty : Bool)  -- FRS:217-221 reschedule or clear flag next
  | uDecideF                -- fixed model: atomic decide under batch lock
  | wRemove                 -- inputChanged(A,B): cleared (FRS:111); removeListener(A) next (FRS:113)
  | wFinish                 -- inputChanged(A,B): addListener(B) + setInput refresh
  | bAdd                    -- inputChanged(B,A): cleared; removeListener(B), addListener(A) next (FRS:116)
  | bIter                   -- FRS:118 getMatchingReferences().iterator() next
  | bLoop (c m : Nat)       -- FRS:118 for-each over live matchingReferences (cursor c, expected modCount m)
  | bGet (c m r : Nat)      -- FRS:119 -> 158
  | bPut (c m r : Nat)      -- FRS:119 -> 160-161
  | bBat (c m r n : Nat)    -- FRS:119 -> 162-164
  | bChild (c m r n : Nat)  -- FRS:119 -> 137
  | bDone                   -- ContentViewer.setInput: input := A, refresh
  | wFix                     -- fixed model: second half of inputChanged(A,B)
  | bFix                     -- fixed model: second half of inputChanged(B,A)
deriving BEq, Hashable, Repr, DecidableEq

inductive Inp where
  | A | B
deriving BEq, Hashable, Repr, DecidableEq

structure St where
  spc : SPc := .idle
  addB : Nat
  resetB : Nat
  switchB : Nat
  lockS : Bool := false               -- S holds RSR(A).listeners monitor
  listening : Bool := true            -- FRS registered as listener of A
  matching : List Nat := []           -- RSR(A).matchingReferences
  mc : Nat := 0                       -- its ArrayList modCount
  epoch : Nat := 0                    -- number of reset() calls so far (ghost)
  refs : List (Nat × Nat) := []       -- ref id  ↦ (uri, epoch)  (ghost)
  nodes : List (Nat × Nat) := []      -- node id ↦ (uri, epoch of the ref that created it) (ghost)
  roots : List (Option Nat)           -- FRS.rootNodes : uri ↦ node
  batch : List Nat := []              -- FRS.batchAddNodes
  flag : Bool := false                -- FRS.isUIUpdateScheduled
  attach : List (Nat × Nat) := []     -- (ref, node): reference node attached under root node
  jobs : Nat := 0                     -- scheduled, not yet started UIUpdater instances
  upc : UPc := .idle
  syncReq : Bool := false             -- Reset runnable posted by syncExec, not yet run
  input : Inp := .A
  viewer : List Nat := []             -- root items shown (sorted, dedup)
  resetPending : Bool := false        -- ghost: reset() called, effect not yet reflected in viewer
  snap : Option (List Nat) := none     -- UI-local snapshot (only used by the `snap` patch)
  cme : Bool := false                 -- ConcurrentModificationException thrown on UI thread
deriving BEq, Hashable, Repr

def St.init (c : Cfg) : St :=
  { addB := c.adds, resetB := c.resets, switchB := c.switches, roots := List.replicate c.nU none }

/-! Helpers -/

def rootsVals (roots : List (Option Nat)) : List Nat := roots.filterMap id

def getRoot (roots : List (Option Nat)) (u : Nat) : Option Nat := (roots[u]?).getD none

def setRoot (roots : List (Option Nat)) (u : Nat) (v : Option Nat) : List (Option Nat) := roots.set u v

def clearRoots (roots : List (Option Nat)) : List (Option Nat) := roots.map (fun _ => none)

def sins (x : Nat) : List Nat → List Nat
  | [] => [x]
  | y :: ys => if x < y then x :: y :: ys else if x = y then y :: ys else y :: sins x ys

def sset (l : List Nat) : List Nat := l.foldr sins []

def uriOfRef (s : St) (r : Nat) : Nat := ((s.refs[r]?).getD (0, 0)).1
def epochOfRef (s : St) (r : Nat) : Nat := ((s.refs[r]?).getD (0, 0)).2
def uriOfNode (s : St) (n : Nat) : Nat := ((s.nodes[n]?).getD (0, 0)).1
def epochOfNode (s : St) (n : Nat) : Nat := ((s.nodes[n]?).getD (0, 0)).2

/-- Ghost bookkeeping for P3: a refresh only "reflects the last reset" when no Reset
    event is still in flight to the provider. -/
def resetInFlight (s : St) : Bool := s.spc == .rfire || s.spc == .rwait || s.syncReq

/-- S idle: environment chooses the next RSR call (accept of a ref in uri u, or reset). -/
def searchStarts (c : Cfg) (s : St) : List (String × St) :=
  (if s.addB > 0 then
    (List.range c.nU).map fun u =>
      let r := s.refs.length
      (s!"S  RSR.accept(ref{r} in uri{u}): matchingReferences.add",
        { s with addB := s.addB - 1, refs := s.refs ++ [(u, s.epoch)], matching := s.matching ++ [r],
                 mc := s.mc + 1, spc := .fire r })
   else []) ++
  (if s.resetB > 0 then
    [("S  RSR.reset(): matchingReferences.clear  [search (re)started]",
      { s with resetB := s.resetB - 1, matching := [], mc := s.mc + 1, epoch := s.epoch + 1,
               resetPending := true, spc := .rfire })]
   else [])

end FindRefs
