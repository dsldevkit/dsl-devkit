/-
Model of the reference implementation `TreeSetLookup` (TreeSetLookup.java) and of
`QualifiedNamePattern.findNestedArrayMatches` (QualifiedNamePattern.java:408-426).

The map is a `TreeMap` ordered by `QualifiedNamePattern.Comparator`; all stored keys are plain
names. `subMap(lo, hi)` is modelled as the filter `compare(k, lo) >= 0 && compare(k, hi) < 0`
(TreeMap.NavigableSubMap.inRange); this equals TreeMap's ceiling+successor iteration provided the
predicates are monotone along the key order, which `Checks.lean` verifies exhaustively
(`subMapMonotone`). `subMap` throws `IllegalArgumentException` when `compare(lo, hi) > 0`.
-/
import Trie.Tree

namespace Trie

structure TSL where
  entries : List (QN × Arr)   -- sorted by qnCmp, unique keys
  nextId : Nat
deriving Inhabited

def TSL.empty : TSL := ⟨[], 1⟩

def TSL.lookup (t : TSL) (k : QN) : Option Arr := (t.entries.find? (·.1 == k)).map (·.2)

def insertSorted (k : QN) (a : Arr) : List (QN × Arr) → List (QN × Arr)
  | [] => [(k, a)]
  | (k', a') :: r =>
    match qnCmp k k' with
    | .lt => (k, a) :: (k', a') :: r
    | .eq => (k, a) :: r
    | .gt => (k', a') :: insertSorted k a r

def TSL.set (t : TSL) (k : QN) (a : Option Arr) : TSL :=
  match a with
  | some a => { t with entries := insertSorted k a t.entries }
  | none => { t with entries := t.entries.filter (·.1 != k) }

/-- `put` (109-111). -/
def TSL.put (t : TSL) (k : QN) (v : Nat) : TSL :=
  { (t.set k (auAdd (t.lookup k) v t.nextId)) with nextId := t.nextId + 1 }

/-- `putAll` (41-43). -/
def TSL.putAll (t : TSL) (k : QN) (vs : List Nat) : TSL :=
  { (t.set k (auAddAll (t.lookup k) (some ⟨t.nextId, vs⟩) (t.nextId + 1))) with nextId := t.nextId + 2 }

/-- `remove` (114-124). -/
def TSL.remove (t : TSL) (k : QN) (v : Nat) : TSL :=
  match t.lookup k with
  | none => t
  | some a => { (t.set k (auRemove (some a) v t.nextId).1) with nextId := t.nextId + 1 }

/-- `removeMappings` (89-106). -/
def TSL.removeMappings (t : TSL) (v : Nat) : TSL :=
  { t with entries := t.entries.filterMap fun (k, a) =>
      match auRemove (some a) v 0 with
      | (none, _) => none
      | (some a', _) => some (k, a') }

/-- `get(QualifiedName)` (52-61). -/
def TSL.get (t : TSL) (k : QN) : Option (List Nat) := (t.lookup k).map Arr.elems

/-- `get(pattern, false)` → `findNestedArrayMatches` (408-426). `none` = IllegalArgumentException. -/
def TSL.getPattern (t : TSL) (p : Pat) : Option (List Nat) :=
  let lo := p.lowerInclusive
  let hi := plain p.upperExclusive
  if comparatorCmp lo hi == .gt then none
  else
    let inRange := t.entries.filter fun (k, _) =>
      comparatorCmp (plain k) lo != .lt && comparatorCmp (plain k) hi == .lt
    let sel := if p.isRecursive then inRange else inRange.filter (·.1.length == p.segs.length)
    some (sel.flatMap (·.2.elems))

/-- `getMappings` (76-86). -/
def TSL.getMappings (t : TSL) (v : Nat) : List QN :=
  (t.entries.filter (·.2.elems.contains v)).map (·.1)

def TSL.count (t : TSL) : Nat := (t.entries.map (·.2.elems.length)).sum

end Trie
