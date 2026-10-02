import Std.Data.HashMap
import ParallelLoader.Model

namespace ParallelLoader
open Std

/-- Explored graph: BFS order, so the parent chain of any state is a shortest path from `init`. -/
structure Graph where
  states : Array St := #[]
  parent : Array (Option (Nat × Act)) := #[]
  depth : Array Nat := #[]
  edges : Array (List (Act × Nat)) := #[]
  index : HashMap St Nat := {}
  complete : Bool := true   -- false if the depth bound cut the search

/-- Breadth-first exhaustive exploration up to `maxDepth` (fuel keeps it total). -/
def explore (c : Cfg) (maxDepth : Nat := 200) (fuel : Nat := 2000000) : Graph := Id.run do
  let s0 := init c
  let mut g : Graph := { states := #[s0], parent := #[none], depth := #[0], edges := #[[]],
                         index := ({} : HashMap St Nat).insert s0 0 }
  let mut head := 0
  for _ in [0:fuel] do
    if h : head < g.states.size then
      let s := g.states[head]
      let d := g.depth[head]!
      if d ≥ maxDepth then
        if !(succ c s).isEmpty then g := { g with complete := false }
      else
        let mut out : List (Act × Nat) := []
        for (a, t) in succ c s do
          match g.index.get? t with
          | some j => out := (a, j) :: out
          | none =>
            let j := g.states.size
            g := { g with states := g.states.push t, parent := g.parent.push (some (head, a)),
                          depth := g.depth.push (d + 1), edges := g.edges.push [],
                          index := g.index.insert t j }
            out := (a, j) :: out
        g := { g with edges := g.edges.set! head out.reverse }
      head := head + 1
    else break
  return g

def Graph.trace (g : Graph) (i : Nat) : List (Act × St) := Id.run do
  let mut acc : List (Act × St) := []
  let mut k := i
  for _ in [0:g.states.size] do
    match g.parent[k]! with
    | some (p, a) => acc := (a, g.states[k]!) :: acc; k := p
    | none => break
  return acc

/-- First (hence shallowest) state violating `p`. -/
def Graph.firstViolation (g : Graph) (p : St → Bool) : Option Nat :=
  (List.range g.states.size).find? fun i => !p g.states[i]!

/-- States from which a terminal state is reachable using only system steps
    (no timeout, no user cancel, no new interrupt). Backward fixpoint. -/
def Graph.canTerminate (g : Graph) : Array Bool := Id.run do
  let mut good := g.states.map terminal
  for _ in [0:g.states.size + 1] do
    let mut changed := false
    for i in [0:g.states.size] do
      if !good[i]! then
        if g.edges[i]!.any (fun (a, j) => a.isSystem && good[j]!) then
          good := good.set! i true
          changed := true
    if !changed then break
  return good

/-- P3 (termination): every reachable state can still reach done/aborted on its own. -/
def Graph.firstNonTerminating (g : Graph) : Option Nat :=
  let good := g.canTerminate
  (List.range g.states.size).find? fun i => !good[i]!

structure Report where
  states : Nat
  maxDepth : Nat
  complete : Bool
  p1 : Option Nat
  p2 : Option Nat
  p3 : Option Nat
  deriving Repr

def check (c : Cfg) : Graph × Report :=
  let g := explore c
  (g, { states := g.states.size, maxDepth := g.depth.foldl max 0, complete := g.complete,
        p1 := g.firstViolation P1, p2 := g.firstViolation P2, p3 := g.firstNonTerminating })

def Report.ok (r : Report) : Bool := r.complete && r.p1.isNone && r.p2.isNone && r.p3.isNone

def showState (s : St) : String :=
  s!"pc={repr s.pc} toProcess={s.toProcess} outstanding={outstanding s} inQueue={s.inQueue} rq={s.rq} intr={s.intr} cancelReq={s.cancelReq}"

def showTrace (g : Graph) (i : Nat) : String :=
  let steps := g.trace i
  let lines := steps.zipIdx.map fun ((a, s), k) =>
    s!"  {k+1}. {a.show}\n       [{a.javaRef}]\n       -> {showState s}"
  s!"  0. init  -> {showState g.states[0]!}\n" ++ String.intercalate "\n" lines

/-- For a non-terminating state, show its system-step successors (the trap). -/
def showTrap (g : Graph) (i : Nat) : String :=
  let es := g.edges[i]!.filter (·.1.isSystem)
  "  system-step successors of the final state: " ++ String.intercalate ", " (es.map fun (a, j) => s!"{a.show}->#{j}")

def summarize (name : String) (c : Cfg) : String :=
  let (g, r) := check c
  let hdr := s!"== {name}: {repr c.qk} n={c.n} threads={c.threads} timeouts={c.timeouts} interrupts={c.interrupts} variant={repr c.variant}\n   states={r.states} depth={r.maxDepth} complete={r.complete}"
  let part (lbl : String) (o : Option Nat) (extra : Nat → String) : String :=
    match o with
    | none => s!"\n   {lbl}: holds"
    | some i => s!"\n   {lbl}: VIOLATED, shortest trace ({g.depth[i]!} steps):\n{showTrace g i}{extra i}"
  hdr ++ part "P1 bookkeeping" r.p1 (fun _ => "") ++ part "P2 abort-only-on-request" r.p2 (fun _ => "")
      ++ part "P3 termination" r.p3 (fun i => "\n" ++ showTrap g i)

end ParallelLoader
