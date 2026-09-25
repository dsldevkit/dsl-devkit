/-
Exhaustive BFS to a fixpoint, properties, and shortest-counterexample extraction.
-/
import FindRefs.State

namespace FindRefs
open Std

abbrev Next := St → List (String × St)

structure Explored where
  order : Array St                              -- BFS discovery order
  parent : HashMap St (Option (String × St))    -- predecessor + step label
  edges : Nat
  complete : Bool                               -- fixpoint reached within the cap

/-- Breadth-first exploration until no new states (or `cap` states). -/
def explore (next : Next) (init : St) (cap : Nat := 2000000) : Explored := Id.run do
  let mut parent : HashMap St (Option (String × St)) := HashMap.emptyWithCapacity 4096
  parent := parent.insert init none
  let mut order : Array St := #[init]
  let mut i := 0
  let mut edges := 0
  -- `order` doubles as the BFS queue; loop bounded by `cap`
  for _ in [0:cap] do
    if h : i < order.size then
      let s := order[i]
      i := i + 1
      for (lbl, t) in next s do
        edges := edges + 1
        if !parent.contains t then
          parent := parent.insert t (some (lbl, s))
          order := order.push t
    else
      break
  return { order, parent, edges, complete := i ≥ order.size }

def trace (e : Explored) (s : St) : List String := Id.run do
  let mut acc : List String := []
  let mut cur := s
  for _ in [0:10000] do
    match e.parent[cur]? with
    | some (some (lbl, p)) => acc := lbl :: acc; cur := p
    | _ => break
  return acc

/-! Properties -/

def uiIdle (s : St) : Bool := s.upc == .idle

/-- No internal step pending: only environment actions (new accept/reset, user switches) remain. -/
def quiescent (s : St) : Bool :=
  s.spc == .idle && s.upc == .idle && s.jobs == 0 && !s.syncReq && s.input == .A && s.listening && !s.cme

/-- P1 no lost update: at quiescence every matching reference hangs under a shown root node. -/
def p1 (s : St) : Bool :=
  !quiescent s || s.matching.all fun r => s.viewer.any fun n => s.attach.contains (r, n)

/-- P1' (root-level form): at quiescence every current root node is shown. -/
def p1root (s : St) : Bool :=
  !quiescent s || (rootsVals s.roots).all fun n => s.viewer.contains n

/-- P2 one root node per URI: between UI runnables, no two shown root nodes share a URI. -/
def p2 (s : St) : Bool :=
  !uiIdle s || (s.viewer.map (uriOfNode s)).Nodup

/-- P3 no stale node: between UI runnables, once the viewer reflects the last reset,
    every shown node belongs to the current input and the current search. -/
def p3 (s : St) : Bool :=
  !uiIdle s ||
    (match s.input with
     | .B => s.viewer.isEmpty
     | .A => s.resetPending || s.viewer.all fun n => epochOfNode s n == s.epoch)

/-- P4 no exception on the UI thread. -/
def p4 (s : St) : Bool := !s.cme

def props : List (String × (St → Bool)) :=
  [("P1 no lost update (refs)", p1), ("P1' no lost update (roots)", p1root),
   ("P2 one root per URI", p2), ("P3 no stale node", p3), ("P4 no UI-thread CME", p4)]

/-- Deadlock: no successor although some actor is still mid-flight. -/
def deadlock (next : Next) (s : St) : Bool :=
  (next s).isEmpty && !s.cme &&
    !(s.spc == .idle && s.upc == .idle && s.jobs == 0 && !s.syncReq)

def firstViolation (e : Explored) (p : St → Bool) : Option St :=
  e.order.find? fun s => !p s

def allProps (next : Next) (e : Explored) : Bool :=
  e.complete && props.all (fun (_, p) => e.order.all p) && e.order.all (fun s => !deadlock next s)

def report (name : String) (next : Next) (init : St) : IO Unit := do
  let e := explore next init
  IO.println s!"== {name}: {e.order.size} states, {e.edges} edges, fixpoint={e.complete}"
  let checks := props ++ [("DL no deadlock", fun s => !deadlock next s)]
  for (pn, p) in checks do
    match firstViolation e p with
    | none => IO.println s!"  {pn}: holds"
    | some s =>
      let t := trace e s
      IO.println s!"  {pn}: VIOLATED, shortest trace ({t.length} steps):"
      for (l, k) in t.zipIdx do IO.println s!"    {k+1}. {l}"
      IO.println s!"    final: roots={s.roots} batch={s.batch} flag={s.flag} jobs={s.jobs} viewer={s.viewer} matching={s.matching} attach={s.attach} nodes={s.nodes} epoch={s.epoch} input={repr s.input} spc={repr s.spc} upc={repr s.upc} lockS={s.lockS} syncReq={s.syncReq}"

end FindRefs
