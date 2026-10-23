import Std.Data.HashMap
import BinaryStorage.Model

namespace BinaryStorage
open Std

/-- Explored graph in BFS order: the parent chain of a state is a shortest path from `init`. -/
structure Graph where
  states : Array St := #[]
  parent : Array (Option (Nat × Act)) := #[]
  edges : Array (List Nat) := #[]
  index : HashMap St Nat := {}
  complete : Bool := true

/-- Exhaustive BFS to a fixpoint (fuel only keeps the definition total). -/
def explore (c : Cfg) (fuel : Nat := 5000000) : Graph := Id.run do
  let s0 := init c
  let mut g : Graph := { states := #[s0], parent := #[none], edges := #[[]],
                         index := ({} : HashMap St Nat).insert s0 0 }
  let mut head := 0
  for _ in [0:fuel] do
    if h : head < g.states.size then
      let s := g.states[head]
      let mut out : List Nat := []
      for (a, t) in succ c s do
        match g.index.get? t with
        | some j => out := j :: out
        | none =>
          let j := g.states.size
          g := { g with states := g.states.push t, parent := g.parent.push (some (head, a)),
                        edges := g.edges.push [], index := g.index.insert t j }
          out := j :: out
      g := { g with edges := g.edges.set! head out }
      head := head + 1
    else break
  if head < g.states.size then g := { g with complete := false }
  return g

def Graph.trace (g : Graph) (i : Nat) : List Act := Id.run do
  let mut acc : List Act := []
  let mut k := i
  for _ in [0:g.states.size] do
    match g.parent[k]! with
    | some (p, a) => acc := a :: acc; k := p
    | none => break
  return acc

def Graph.firstViolation (g : Graph) (p : St → Bool) : Option Nat :=
  (List.range g.states.size).find? fun i => !p g.states[i]!

/-- Kahn's algorithm: true iff the reachable graph is acyclic (every run is finite). -/
def Graph.acyclic (g : Graph) : Bool := Id.run do
  let n := g.states.size
  let mut indeg : Array Nat := Array.replicate n 0
  for es in g.edges do
    for j in es do indeg := indeg.modify j (· + 1)
  let mut stack : List Nat := (List.range n).filter (indeg[·]! == 0)
  let mut seen := 0
  for _ in [0:n] do
    match stack with
    | [] => break
    | i :: rest =>
      stack := rest; seen := seen + 1
      for j in g.edges[i]! do
        indeg := indeg.modify j (· - 1)
        if indeg[j]! == 0 then stack := j :: stack
  return seen == n

/-- Non-terminal states without successors. -/
def Graph.deadlocks (c : Cfg) (g : Graph) : Nat :=
  ((List.range g.states.size).filter fun i => g.edges[i]!.isEmpty && !terminal c g.states[i]!).length

structure Report where
  states : Nat
  complete : Bool
  acyclic : Bool
  deadlocks : Nat
  p1 : Option Nat
  p2 : Option Nat
  p3partial : Option Nat
  p3stale : Option Nat
  p4 : Option Nat
  pEnd : Option Nat
  deriving Repr

def report (c : Cfg) : Report :=
  let g := explore c
  let fv (p : St → Bool) : Option Nat := (g.firstViolation p).map fun i => (g.trace i).length
  { states := g.states.size, complete := g.complete, acyclic := g.acyclic, deadlocks := g.deadlocks c,
    p1 := fv p1, p2 := fv (p2 c), p3partial := fv p3partial, p3stale := fv p3stale, p4 := fv p4, pEnd := fv (pEnd c) }

/-- Shortest counterexample (list of actions) for property `p`, if any. -/
def cex (c : Cfg) (p : St → Bool) : Option (List Act) :=
  let g := explore c
  (g.firstViolation p).map g.trace

/-- Boolean verdict usable by `native_decide`: all five properties hold. -/
def allHold (c : Cfg) : Bool :=
  let r := report c
  r.complete && r.acyclic && r.deadlocks == 0 && r.p1.isNone && r.p2.isNone &&
  r.p3partial.isNone && r.p3stale.isNone && r.p4.isNone && r.pEnd.isNone

end BinaryStorage
