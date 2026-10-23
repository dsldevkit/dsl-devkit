/-
Bounded exhaustive property checks. Every checker is a plain function returning the first
counterexample in size order (or `none`), so it can be run by the executable (`Main.lean`) and
used under `native_decide` (`Theorems.lean`).
-/
import Trie.Fixed

namespace Trie.Checks
open Trie

/-! ### Implementations under test -/

structure Impl where
  name : String
  fixes : Fixed.Fixes
  plant : Bool := false   -- planted bug: tree ignores `recursive`

def Impl.treeGet (i : Impl) (t : Lookup) (p : Pat) : List Nat :=
  if i.plant then
    let f := i.fixes
    let upperNode := (find t.root [] (Fixed.upperExclusive f p) false).map Prod.fst
    valuesOf (t.share && !f.share) (collect t.root [] p.lowerInclusive.qn upperNode false []).2
  else Fixed.treeGet i.fixes t p
def Impl.tslGet (i : Impl) : TSL → Pat → Option (List Nat) := Fixed.tslGet i.fixes
def Impl.matchesP (i : Impl) : Pat → QN → Bool := Fixed.matchesP i.fixes
def Impl.lower (_ : Impl) : Pat → Key := Pat.lowerInclusive
def Impl.upper (i : Impl) : Pat → QN := Fixed.upperExclusive i.fixes
def Impl.cmp (i : Impl) : Key → Key → Ordering := Fixed.comparatorCmp i.fixes
def Impl.put (i : Impl) : Lookup → QN → Nat → Lookup := Fixed.put i.fixes
def Impl.putAll (i : Impl) : Lookup → QN → List Nat → Lookup := Fixed.putAll i.fixes
def Impl.getMappings (i : Impl) : Lookup → Nat → List QN := Fixed.getMappings i.fixes
def Impl.consumerFix (i : Impl) : Bool := i.fixes.consumer

def asWritten : Impl := { name := "as-written", fixes := {} }
def fixed : Impl := { name := "fixed", fixes := Fixed.Fixes.all }
def planted : Impl := { name := "planted", fixes := Fixed.Fixes.all, plant := true }

/-- The fix ladder: cumulative fixes, in order. -/
def ladder : List Impl :=
  let steps : List (String × (Fixed.Fixes → Fixed.Fixes)) :=
    [("+F3 exactLen", fun f => { f with exactLen := true }),
     ("+F1 topUpper", fun f => { f with topUpper := true }),
     ("+F2 nulSucc", fun f => { f with nulSucc := true }),
     ("+F4 cmpEmpty", fun f => { f with cmpEmpty := true }),
     ("+F5 size", fun f => { f with size := true }),
     ("+F6 mappings", fun f => { f with mappings := true }),
     ("+F7 share", fun f => { f with share := true }),
     ("+F8 consumer", fun f => { f with consumer := true }),
     ("+F9 glob", fun f => { f with glob := true }),
     ("+F10 globCase", fun f => { f with globCase := true }),
     ("+A1 noMax", fun f => { f with noMax := true })]
  (steps.foldl (fun (acc : List Impl × Fixed.Fixes) (nm, g) =>
    let f := g acc.2
    (acc.1 ++ [{ name := nm, fixes := f }], f)) ([], {})).1

/-- Domain restriction under assumption A1. -/
def Impl.admQ (i : Impl) (q : QN) : Bool := !i.fixes.noMax || !(q.any (·.contains CMAX))
def Impl.fN (i : Impl) (l : List QN) : List QN := l.filter i.admQ
def Impl.fP (i : Impl) (l : List Pat) : List Pat := l.filter (i.admQ ·.segs)
def Impl.fS (i : Impl) (l : List (List QN)) : List (List QN) := l.filter (·.all i.admQ)

def treeQuery (impl : Impl) (t : Lookup) (p : Pat) : List Nat := impl.treeGet t p

/-! ### Domains -/

def allStrings (alpha : List JChar) : Nat → List Seg
  | 0 => [[]]
  | n + 1 => [[]] ++ (alpha.flatMap fun c => (allStrings alpha n).map (c :: ·))

def dedupL {α} [BEq α] (l : List α) : List α := l.foldl (fun acc x => if acc.contains x then acc else acc ++ [x]) []

def qsize (q : QN) : Nat := q.length + (q.map List.length).sum
def psize (p : Pat) : Nat := qsize p.segs

def namesOf (segs : List Seg) (maxSegs : Nat) : List QN :=
  let rec go : Nat → List QN
    | 0 => []
    | k + 1 => (segs.map ([·])) ++ ((go k).flatMap fun q => segs.map (q ++ [·]))
  (dedupL (go maxSegs)).mergeSort (fun a b => qsize a ≤ qsize b)

/-- Non-glob patterns `prefix ++ [base ++ kind]`, kind ∈ {"", "*", "**"}. -/
def patsOf (prefixSegs : List Seg) (maxPrefix : Nat) (bases : List Seg) : List Pat :=
  let prefixes : List QN := [[]] ++ namesOf prefixSegs maxPrefix
  let ps := prefixes.flatMap fun pre => bases.flatMap fun b =>
    [[], [STAR], [STAR, STAR]].map fun k => ({ segs := pre ++ [b ++ k] } : Pat)
  (ps.filter (verifySegs ·.segs)).mergeSort (fun a b => psize a ≤ psize b)

def globsOf (gsegs : List Seg) (maxSegs : Nat) : List Pat :=
  ((namesOf gsegs maxSegs).filter (fun q => !(lastSeg q).isEmpty)).map fun q => { segs := q, glob := true }

/-- All sub-lists of `l` with at most `k` elements, by increasing size. -/
def subsetsUpTo {α} (l : List α) (k : Nat) : List (List α) :=
  let rec go : List α → Nat → List (List α)
    | _, 0 => [[]]
    | [], _ => [[]]
    | x :: xs, k + 1 => go xs (k + 1) ++ (go xs k).map (x :: ·)
  (go l k).mergeSort (fun a b => a.length ≤ b.length)

/-- Stores ordered by (number of names, total size) so the first counterexample is minimal. -/
def storesBySize (l : List QN) (k : Nat) : List (List QN) :=
  (subsetsUpTo l k).mergeSort (fun a b =>
    a.length < b.length || (a.length == b.length && (a.map qsize).sum ≤ (b.map qsize).sum))

/-! ### Helpers -/

def msEq (a b : List Nat) : Bool := a.mergeSort (· ≤ ·) == b.mergeSort (· ≤ ·)
def setOf (a : List Nat) : List Nat := (a.mergeSort (· ≤ ·)).eraseDups
def subsetOf (a b : List Nat) : Bool := a.all b.contains
def showL (l : List Nat) : String := toString (l.mergeSort (· ≤ ·))
def showPat (p : Pat) : String := (if p.glob then "glob" else "pattern") ++ showQN p.segs

def buildTree (share : Bool) (impl : Impl) (store : List (QN × Nat)) : Lookup :=
  store.foldl (fun t (n, v) => impl.put t n v) (Lookup.empty share)
def buildTSL (store : List (QN × Nat)) : TSL := store.foldl (fun t (n, v) => t.put n v) TSL.empty
def spec (impl : Impl) (store : List (QN × Nat)) (p : Pat) : List Nat :=
  (store.filter (impl.matchesP p ·.1)).map (·.2)
def showStore (store : List (QN × Nat)) : String :=
  "{" ++ ", ".intercalate (store.map fun (n, v) => showQN n ++ "↦" ++ toString v) ++ "}"

def firstSome {α β} (l : List α) (f : α → Option β) : Option β :=
  l.foldl (fun acc x => match acc with | some b => some b | none => f x) none

/-! ### P1: range soundness — every matched name lies in [lowerInclusive, upperExclusive) -/

def rangeSound (impl : Impl) (pats : List Pat) (names : List QN) : Option String :=
  firstSome pats fun p =>
    let lo := impl.lower p
    let hi := plain (impl.upper p)
    firstSome names fun n =>
      if impl.matchesP p n then
        let c1 := impl.cmp (plain n) lo
        let c2 := impl.cmp (plain n) hi
        if c1 == .lt || c2 != .lt then
          some s!"{showPat p} matches {showQN n} but lower={showQN lo.qn} upper={showQN hi.qn} (cmp(n,lower)={repr c1}, cmp(n,upper)={repr c2})"
        else none
      else none

/-- P1b: `compare(lower, upper) <= 0` (else `TreeMap.subMap` throws). -/
def boundsOrdered (impl : Impl) (pats : List Pat) : Option String :=
  firstSome pats fun p =>
    if impl.cmp (impl.lower p) (plain (impl.upper p)) == .gt then
      some s!"{showPat p}: lower={showQN (impl.lower p).qn} > upper={showQN (impl.upper p)}"
    else none

/-- P1c (range precision, strict): every name inside the range with the right segment count
(non-recursive) is matched — i.e. the range filter used by both lookups returns no extras. -/
def rangePrecise (impl : Impl) (pats : List Pat) (names : List QN) : Option String :=
  firstSome (pats.filter (!·.glob)) fun p =>
    let lo := impl.lower p
    let hi := plain (impl.upper p)
    firstSome names fun n =>
      let inR := impl.cmp (plain n) lo != .lt && impl.cmp (plain n) hi == .lt
      let cnt := p.isRecursive || n.length == p.segs.length
      if inR && cnt && !impl.matchesP p n then
        some s!"{showPat p}: {showQN n} is in [{showQN lo.qn}, {showQN hi.qn}) but not matched"
      else none

/-! ### P2: lookups agree with the spec and with each other -/

inductive Which | treeVsSpec | tslVsSpec | treeVsTsl | shareVsPlain | globTreeComplete | globTslComplete
deriving BEq, Repr

def lookupCheck (impl : Impl) (w : Which) (stores : List (List QN)) (pats : List Pat) : Option String :=
  firstSome stores fun names =>
    let store := names.zip (List.range names.length)
    let t := buildTree false impl store
    let tsl := buildTSL store
    -- all-equal values expose multiplicity differences between sharing and non-sharing trees
    let store0 := names.map (·, 0)
    let t0s := buildTree true impl store0
    let t0 := buildTree false impl store0
    firstSome pats fun p =>
      let sp := spec impl store p
      let tr := treeQuery impl t p
      let ts := impl.tslGet tsl p
      let fail (what exp act : String) : Option String :=
        some s!"{what}: store={showStore store} {showPat p}: expected {exp}, got {act}"
      match w with
      | .treeVsSpec => if p.glob || msEq tr sp then none else fail "tree≠spec" (showL sp) (showL tr)
      | .tslVsSpec =>
        if p.glob then none else
        match ts with
        | none => fail "TreeSetLookup throws" (showL sp) "IllegalArgumentException(fromKey > toKey)"
        | some ts => if msEq ts sp then none else fail "TreeSetLookup≠spec" (showL sp) (showL ts)
      | .treeVsTsl =>
        match ts with
        | none => none
        | some ts => if msEq tr ts then none else fail "tree≠TreeSetLookup" (showL ts) (showL tr)
      | .shareVsPlain =>
        let a := treeQuery impl t0s p
        let b := treeQuery impl t0 p
        if msEq a b then none
        else some s!"share≠plain: store={showStore store0} {showPat p}: shareValues=false gives {showL b}, shareValues=true gives {showL a}"
      | .globTreeComplete =>
        if !p.glob then none else
        if subsetOf sp tr then none else fail "glob tree misses a match" (showL sp) (showL tr)
      | .globTslComplete =>
        if !p.glob then none else
        match ts with
        | none => fail "TreeSetLookup throws (glob)" (showL sp) "IllegalArgumentException"
        | some ts => if subsetOf sp ts then none else fail "glob TreeSetLookup misses a match" (showL sp) (showL ts)

/-! ### P3: operation sequences (size, get, getMappings, pattern get vs TreeSetLookup) -/

inductive Op
  | put (n : QN) (v : Nat)
  | putAll (n : QN) (vs : List Nat)
  | remove (n : QN) (v : Nat)
  | removeMappings (v : Nat)
deriving Repr, BEq

def showOp : Op → String
  | .put n v => s!"put({showQN n},{v})"
  | .putAll n vs => s!"putAll({showQN n},{vs})"
  | .remove n v => s!"remove({showQN n},{v})"
  | .removeMappings v => s!"removeMappings({v})"

def applyT (impl : Impl) (t : Lookup) : Op → Lookup
  | .put n v => impl.put t n v
  | .putAll n vs => impl.putAll t n vs
  | .remove n v => t.remove n v
  | .removeMappings v => t.removeMappings v

def applyS (s : TSL) : Op → TSL
  | .put n v => s.put n v
  | .putAll n vs => s.putAll n vs
  | .remove n v => s.remove n v
  | .removeMappings v => s.removeMappings v

inductive OpProp | size | get | mappings | patternGet
deriving BEq, Repr

def opsCheck (impl : Impl) (prop : OpProp) (share : Bool) (names : List QN) (vals : List Nat)
    (pats : List Pat) (ops : List Op) (maxLen : Nat) : Option String × Nat :=
  let rec bfs : Nat → List (List Op × Lookup × TSL) → Nat → Option String × Nat
    | 0, _, n => (none, n)
    | fuel + 1, frontier, n =>
      let next := frontier.flatMap fun (seq, t, s) =>
        ops.map fun o => (seq ++ [o], applyT impl t o, applyS s o)
      let bad := next.find? fun (_, t, s) =>
        match prop with
        | .size => t.size != (countVals t.root : Int)
        | .get => names.any fun n => (t.get n).map setOf != (s.get n).map setOf
        | .mappings => vals.any fun v =>
            let a := impl.getMappings t v
            let b := s.getMappings v
            !(a.length == b.length && a.all b.contains)
        | .patternGet => pats.any fun p =>
            match impl.tslGet s p with
            | none => false
            | some r => !msEq (treeQuery impl t p) r
      match bad with
      | some (seq, t, s) =>
        let detail := match prop with
          | .size => s!"size={t.size} but stored pairs={countVals t.root}"
          | .get => String.intercalate "; " ((names.filter fun n => (t.get n).map setOf != (s.get n).map setOf).map
              fun n => s!"get({showQN n}): tree={repr ((t.get n).map setOf)} TreeSetLookup={repr ((s.get n).map setOf)}")
          | .mappings => String.intercalate "; " (vals.map fun v =>
              s!"getMappings({v}): tree={(impl.getMappings t v).map showQN} TreeSetLookup={(s.getMappings v).map showQN}")
          | .patternGet => String.intercalate "; " ((pats.filter fun p => match impl.tslGet s p with
              | none => false | some r => !msEq (treeQuery impl t p) r).map fun p =>
              s!"{showPat p}: tree={showL (treeQuery impl t p)} TreeSetLookup={showL ((impl.tslGet s p).getD [])}")
        (some s!"[{", ".intercalate (seq.map showOp)}] → {detail}", n + next.length)
      | none => bfs fuel next (n + next.length)
  bfs maxLen [([], Lookup.empty share, TSL.empty)] 1

/-! ### P4: consumer (PatternAwareEObjectDescriptionLookUp) -/

def consumerCheck (impl : Impl) (descSets : List (List QN)) (args : List Arg) (ignoreCase : Bool) : Option String :=
  firstSome descSets fun names =>
    let descs := names.zip (List.range names.length)
    firstSome args fun a =>
      let getP := fun t p => impl.treeGet t p
      let r := exportedObjects impl.consumerFix getP descs a ignoreCase
      let e := (descs.filter fun (n, _) =>
          match a, ignoreCase with
          | .pat p, false => impl.matchesP p n
          | .pat p, true => impl.matchesP p.toLower (lowerQ n)
          | .name q, false => q == n
          | .name q, true => lowerQ q == lowerQ n).map (·.2)
      let argS := match a with | .pat p => showPat p | .name q => "name" ++ showQN q
      if setOf r == setOf e then none
      else some s!"descriptions={showStore descs} getExportedObjects(type, {argS}, ignoreCase={ignoreCase}): expected ids {showL e}, got {showL r}"

/-- Case-sensitive result ⊆ case-insensitive result. -/
def caseMonotone (impl : Impl) (descSets : List (List QN)) (args : List Arg) : Option String :=
  firstSome descSets fun names =>
    let descs := names.zip (List.range names.length)
    firstSome args fun a =>
      let getP := fun t p => impl.treeGet t p
      let cs := exportedObjects impl.consumerFix getP descs a false
      let ci := exportedObjects impl.consumerFix getP descs a true
      if subsetOf cs ci then none
      else some s!"descriptions={showStore descs}: case-sensitive {showL cs} ⊄ case-insensitive {showL ci}"

/-! ### P5: TreeMap.subMap faithfulness — the range predicates are monotone along key order -/

def subMapMonotone (impl : Impl) (pats : List Pat) (names : List QN) : Option String :=
  let sorted := names.mergeSort (fun a b => qnCmp a b != .gt)
  firstSome pats fun p =>
    let lo := impl.lower p
    let hi := plain (impl.upper p)
    let low := sorted.map fun n => impl.cmp (plain n) lo == .lt
    let high := sorted.map fun n => impl.cmp (plain n) hi != .lt
    -- `low` must be T..TF..F and `high` must be F..FT..T
    let isPrefix (l : List Bool) := (l.dropWhile id).all (!·)
    let isSuffix (l : List Bool) := (l.dropWhile (!·)).all id
    if isPrefix low && isSuffix high then none
    else some s!"{showPat p}: non-monotone subMap bound"

/-! ### E0: the ladder's base (no fixes) is literally the as-written model -/

def baseEquiv (stores : List (List QN)) (pats : List Pat) (names : List QN) : Option String :=
  let i := asWritten
  let r1 := firstSome pats fun p =>
    if (i.upper p != p.upperExclusive) then some s!"upper differs on {showPat p}" else
    firstSome names fun n =>
      if i.matchesP p n != p.matches n then some s!"matches differs on {showPat p} {showQN n}"
      else if i.cmp (plain n) p.lowerInclusive != comparatorCmp (plain n) p.lowerInclusive then some "cmp differs"
      else none
  match r1 with
  | some m => some m
  | none => firstSome stores fun names =>
    let store := names.zip (List.range names.length)
    firstSome [false, true] fun share =>
      let t := store.foldl (fun t (n, v) => t.put n v) (Lookup.empty share)
      let t' := buildTree share i store
      let tsl := buildTSL store
      if t.size != t'.size then some "size differs" else
      firstSome pats fun p =>
        if Lookup.getPattern t p != i.treeGet t p then some s!"tree get differs on {showPat p}"
        else if TSL.getPattern tsl p != i.tslGet tsl p then some s!"tsl get differs on {showPat p}"
        else if (List.range 2).any (fun v => t.getMappings v != i.getMappings t v) then some "mappings differ"
        else none

/-! ### Witnesses (good paths are reachable) -/

def witnessCount (impl : Impl) (stores : List (List QN)) (pats : List Pat) (recursive : Bool) : Nat :=
  stores.foldl (fun acc names =>
    let store := names.zip (List.range names.length)
    let t := buildTree false impl store
    acc + (pats.filter fun p =>
      p.isRecursive == recursive && !p.glob &&
      let sp := spec impl store p
      !sp.isEmpty && msEq (treeQuery impl t p) sp).length) 0

end Trie.Checks
