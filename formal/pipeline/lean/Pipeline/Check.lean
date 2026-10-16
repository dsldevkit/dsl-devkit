import Pipeline.Model
import Std.Data.HashMap
open Std

/-! # Exhaustive BFS to a fixpoint + safety / recoverability / witness checks -/
namespace Pipeline

structure Graph where
  states : Array St
  parent : Array (Nat × Label)
  succ : Array (Array Nat)
  depth : Array Nat
  edges : Nat

def explore (cfg : Cfg) (limit : Nat := 100000000) : Graph := Id.run do
  let s0 := init cfg
  let mut idx : HashMap St Nat := (HashMap.emptyWithCapacity 4096).insert s0 0
  let mut states : Array St := #[s0]
  let mut parent : Array (Nat × Label) := #[(0, "init")]
  let mut depth : Array Nat := #[0]
  let mut succ : Array (Array Nat) := #[]
  let mut edges := 0
  let mut i := 0
  while i < states.size && states.size < limit do
    let s := states[i]!
    let mut out : Array Nat := #[]
    for (lbl, t) in succs cfg s do
      edges := edges + 1
      match idx.get? t with
      | some j => out := out.push j
      | none =>
        let j := states.size
        idx := idx.insert t j
        states := states.push t
        parent := parent.push (i, lbl)
        depth := depth.push (depth[i]! + 1)
        out := out.push j
    succ := succ.push out
    i := i + 1
  return { states, parent, succ, depth, edges }

/-- backward closure: states from which some path reaches a `goal` state (EF goal). -/
def efSet (g : Graph) (goal : St → Bool) : Array Bool := Id.run do
  let n := g.states.size
  let mut pred : Array (Array Nat) := Array.replicate n #[]
  for i in [0:n] do
    for j in g.succ[i]! do
      pred := pred.modify j (·.push i)
  let mut mark : Array Bool := g.states.map goal
  let mut work : Array Nat := (Array.range n).filter (mark[·]!)
  while work.size > 0 do
    let j := work.back!
    work := work.pop
    for i in pred[j]! do
      if !mark[i]! then
        mark := mark.set! i true
        work := work.push i
  return mark

def trace (g : Graph) (i : Nat) : List Label := Id.run do
  let mut acc : List Label := []
  let mut j := i
  let mut fuel := g.states.size
  while j != 0 && fuel > 0 do
    let (p, l) := g.parent[j]!
    acc := l :: acc
    j := p
    fuel := fuel - 1
  return acc

/-! ## Properties -/

def quiescent (s : St) : Bool := s.slots.all fun sl => sl.running.isNone && sl.pending.isNone

/-- P1: when nothing is running, every tag has its p2/releases/<v>/ repository. -/
def pTagHasRepo (s : St) : Bool :=
  !quiescent s || s.tags.all fun t => s.gh.rels.any (·.1 == t.1)

/-- P2: p2/snapshots/latest only ever points at a master build. -/
def pLatestFromMaster (s : St) : Bool := s.gh.latest.all (·.br == .master)

/-- P3: a release run derives its next version from its own branch's tag line. -/
def pOwnLine (cfg : Cfg) (s : St) : Bool :=
  s.slots.all fun sl => sl.running.all fun r => match r.kind with
    | .rel b => (if r.pc == 0 then computedNext cfg s b else r.next).maj == b.line
    | _ => true

/-- P4: no release repository that was ever pushed to gh-pages disappears (no lost content). -/
def pNoLostRelease (s : St) : Bool := s.pubRels.all fun v => s.gh.rels.any (·.1 == v)

/-- P5: when idle and no snapshot run ever failed, master's head has a snapshot. -/
def pMasterHeadSnapshotted (s : St) : Bool :=
  !quiescent s || s.snapFail || s.gh.snaps.any (·.sha == s.hMaster)

/-- P6: a tag and its release repository name the same commit. -/
def pTagRepoSameCommit (s : St) : Bool :=
  s.tags.all fun (v, c) => s.gh.rels.all fun (w, d) => w != v || d == c

/-- P7 (latest composite not dangling): latest points at an existing snapshot dir. -/
def pLatestExists (s : St) : Bool :=
  s.gh.latest.all fun l => s.gh.snaps.any (·.sha == l.sha)

def noDangling (s : St) : Bool := s.tags.all fun t => s.gh.rels.any (·.1 == t.1)

def maxLineTag (s : St) (line : Nat) : Nat :=
  (s.tags.filter (·.1.maj == line)).foldl (fun m t => Nat.max m t.1.pat) 0

/-! ## Witnesses (good paths reachable) -/
def wMasterRelease (s : St) : Bool :=
  s.tags.any (·.1 == ⟨19, 1⟩) && s.gh.rels.any (·.1 == ⟨19, 1⟩) && s.ghRels.contains ⟨19, 1⟩
def wMaintRelease (s : St) : Bool :=
  s.tags.any (·.1 == ⟨18, 1⟩) && s.gh.rels.any (·.1 == ⟨18, 1⟩)
/-- an inconsistent release state (some tag / release repo / GitHub release missing its peers). -/
def relInconsistent (s : St) : Bool :=
  s.tags.any (fun t => !(s.gh.rels.any (·.1 == t.1)) || !(s.ghRels.contains t.1))
  || s.gh.rels.any (fun r => !(s.tags.any (·.1 == r.1)))
def relConsistent (s : St) : Bool := !relInconsistent s
def wNewMasterSnapshot (s : St) : Bool := s.gh.latest.any fun l => l.br == .master && l.sha != 2
def wMaintSnapshot (s : St) : Bool := s.gh.snaps.any (·.br == .maint)
def wSecondMasterRelease (s : St) : Bool :=
  s.tags.any (·.1 == ⟨19, 2⟩) && s.gh.rels.any (·.1 == ⟨19, 2⟩)

structure Verdict where
  name : String
  ok : Bool
  witness : Option Nat      -- shortest violating state (or reaching state for witnesses)
  deriving Inhabited

structure Result where
  states : Nat
  edges : Nat
  maxDepth : Nat
  safety : List Verdict
  stuck : Verdict           -- recoverability: every dangling tag can be repaired w/o manual steps
  releaseLive : Verdict     -- while a push is left, a further master release is reachable
  witnesses : List Verdict
  deriving Inhabited

def firstViolation (g : Graph) (p : St → Bool) : Option Nat :=
  (List.range g.states.size).find? fun i => !p g.states[i]!

def analyze (cfg : Cfg) : Graph × Result := Id.run do
  let g := explore cfg
  let n := g.states.size
  let safety : List (String × (St → Bool)) :=
    [("P1 TagHasReleaseRepo (at quiescence)", pTagHasRepo),
     ("P2 SnapshotsLatestFromMaster", pLatestFromMaster),
     ("P3 NextVersionFromOwnLine", pOwnLine cfg),
     ("P4 NoLostReleaseRepo", pNoLostRelease),
     ("P5 MasterHeadSnapshotted (idle, no failed snapshot run)", pMasterHeadSnapshotted),
     ("P6 TagAndRepoSameCommit", pTagRepoSameCommit),
     ("P7 LatestSnapshotNotDangling", pLatestExists)]
  let sv := safety.map fun (nm, p) =>
    let w := firstViolation g p
    ({ name := nm, ok := w.isNone, witness := w } : Verdict)
  -- recoverability
  let ef := efSet g noDangling
  let stuckIdx := (List.range n).find? fun i => !ef[i]!
  let stuck : Verdict := { name := "R1 NoStuckDanglingTag (EF all tags published, from every state)",
                           ok := stuckIdx.isNone, witness := stuckIdx }
  -- release liveness per master-line level
  let levels := (g.states.toList.map (maxLineTag · 19)).eraseDups
  let mut liveBad : Option Nat := none
  for m in levels do
    let efm := efSet g (fun s => maxLineTag s 19 > m)
    for i in [0:n] do
      let s := g.states[i]!
      if liveBad.isNone && maxLineTag s 19 == m && s.pushes < cfg.maxPush && !efm[i]! then
        liveBad := some i
  let live : Verdict := { name := "R2 MasterReleaseStillPossible (EF next master tag, push budget left)",
                          ok := liveBad.isNone, witness := liveBad }
  let wits : List (String × (St → Bool)) :=
    [("W1 master patch release v19.0.1 published", wMasterRelease),
     ("W2 maintenance release v18.0.1 published", wMaintRelease),
     ("W4 latest moves to a new master snapshot", wNewMasterSnapshot),
     ("W5 a maintenance snapshot gets published", wMaintSnapshot),
     ("W6 second master release v19.0.2", wSecondMasterRelease)]
  let wv := wits.map fun (nm, p) =>
    let w := (List.range n).find? fun i => p g.states[i]!
    ({ name := nm, ok := w.isSome, witness := w } : Verdict)
  -- W3: some failed-release state can still be completed without manual repair
  let efc := efSet g relConsistent
  let w3 := (List.range n).find? fun i => relInconsistent g.states[i]! && quiescent g.states[i]! && efc[i]!
  let wv := wv ++ [{ name := "W3 a half-finished release is completed by a rerun (from an idle state)",
                     ok := w3.isSome, witness := w3 }]
  let maxDepth := g.depth.foldl Nat.max 0
  return (g, { states := n, edges := g.edges, maxDepth, safety := sv, stuck, releaseLive := live,
               witnesses := wv })

/-- compact verdict vector used by the `native_decide` theorems. -/
def verdictBits (r : Result) : List Bool :=
  r.safety.map (·.ok) ++ [r.stuck.ok, r.releaseLive.ok] ++ r.witnesses.map (·.ok)

def bits (cfg : Cfg) : List Bool := verdictBits (analyze cfg).2

end Pipeline
