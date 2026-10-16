/-
Minimal, code-faithful fixes, each switchable independently (a "fix ladder"), used for
root-cause isolation and for the sanity check "the fully fixed model passes".
Each flag corresponds to a small change in the Java (see NOTES.md, "Fix plan").
-/
import Trie.Consumer

namespace Trie.Fixed
open Trie

structure Fixes where
  topUpper : Bool := false   -- F1  single-segment `*`/`**` (and glob) upper bound "￿" instead of "!"
  nulSucc : Bool := false    -- F2  append '\u0000' instead of '!' to form the successor segment
  exactLen : Bool := false   -- F3  wildcard-free pattern matches only names of equal length
  cmpEmpty : Bool := false   -- F4  compareTo: drop the `seg1.length()==0 → -1` special case
  size : Bool := false       -- F5  size counts only values actually added
  mappings : Bool := false   -- F6  getMappings prefixes every non-root segment (not `!isBlank`)
  share : Bool := false      -- F7  value-sharing: no identity dedup when !excludeDuplicates
  consumer : Bool := false   -- F8  PatternAware...: `matches(input.getName())`
  glob : Bool := false       -- F9  glob: walk the range recursively (candidate superset)
  globCase : Bool := false   -- F10 glob: case-sensitive regexps (`Regexps.fromGlob(glob, false)`), matching the case-sensitive bounds
  noMax : Bool := false      -- A1  assumption: no name/pattern contains U+FFFF (the tree's sentinel)
deriving Repr, Inhabited

def Fixes.all : Fixes := ⟨true, true, true, true, true, true, true, true, true, true, true⟩

def upperExclusive (f : Fixes) (p : Pat) : QN :=
  let bang : Seg := if f.nulSucc then [0] else [BANG]
  let top : QN := if f.topUpper then [[CMAX]] else [[BANG]]
  if p.glob then
    let li := (p.lowerInclusive).qn
    let ls := lastSeg li
    if ls.isEmpty then
      if li.length == 1 then top
      else let li' := dropLastQ li; dropLastQ li' ++ [lastSeg li' ++ bang]
    else
      let lci := ls.length - 1
      dropLastQ li ++ [ls.take lci ++ [charSucc (ls.getD lci 0)]]
  else
    let ls := lastSeg p.segs
    let w := lastIndexOf STAR ls
    if w == 0 || (w == 1 && ls == [STAR, STAR]) then
      if p.segs.length == 1 then top
      else let ub := dropLastQ p.segs; dropLastQ ub ++ [lastSeg ub ++ bang]
    else if w == -1 then dropLastQ p.segs ++ [ls ++ bang]
    else
      let lci : Int := if charAt ls (w - 1) == STAR then w - 2 else w - 1
      dropLastQ p.segs ++ [substr0 ls lci ++ [charSucc (charAt ls lci)]]

def matchesP (f : Fixes) (p : Pat) (n : QN) : Bool :=
  if p.glob && f.globCase then
    -- lines 266-276 with case-sensitive regexps
    let pc := p.segs.length
    let ls := lastSeg p.segs
    if n.length < pc || ls.isEmpty then false
    else if ls.getLast! != STAR && n.length > pc then false
    else (List.range pc).all fun i => globMatchC false (p.segs.getD i []) (n.getD i [])
  else if p.glob || !f.exactLen then p.matches n
  else p.matches n && (n.length == p.segs.length || (lastSeg p.segs).contains STAR)

def patCmpLoopF (cmpEmpty : Bool) : List Seg → List Seg → Ordering
  | [], _ => .lt
  | _ :: _, [] => .gt
  | s1 :: r1, s2 :: r2 =>
    if s1.isEmpty && !cmpEmpty then .lt
    else if s1 == [STAR] then patCmpLoopF cmpEmpty r1 r2
    else
      let w := indexOf STAR s1
      if w == -1 then
        match strCmp s1 s2 with
        | .eq => patCmpLoopF cmpEmpty r1 r2
        | o => o
      else if s2.length < w.toNat then strCmp (substr0 s1 w) s2
      else match strCmp (substr0 s1 w) (substr0 s2 w) with
        | .eq => patCmpLoopF cmpEmpty r1 r2
        | o => o

def comparatorCmp (f : Fixes) (o1 o2 : Key) : Ordering :=
  let kc (a b : Key) : Ordering :=
    if a.isPat && !b.isPat then patCmpLoopF f.cmpEmpty a.qn b.qn else qnCmp a.qn b.qn
  if o1.isPat then kc o1 o2 else (kc o2 o1).swap

def isRecursive (f : Fixes) (p : Pat) : Bool := (f.glob && p.glob) || p.isRecursive

def treeGet (f : Fixes) (t : Lookup) (p : Pat) : List Nat :=
  let upperNode := (find t.root [] (upperExclusive f p) false).map Prod.fst
  let (_, acc) := collect t.root [] p.lowerInclusive.qn upperNode (isRecursive f p) []
  valuesOf (t.share && !f.share) acc

def tslGet (f : Fixes) (t : TSL) (p : Pat) : Option (List Nat) :=
  let lo := p.lowerInclusive
  let hi := plain (upperExclusive f p)
  if comparatorCmp f lo hi == .gt then none
  else
    let inRange := t.entries.filter fun (k, _) =>
      comparatorCmp f (plain k) lo != .lt && comparatorCmp f (plain k) hi == .lt
    let sel := if isRecursive f p then inRange else inRange.filter (·.1.length == p.segs.length)
    some (sel.flatMap (·.2.elems))

def put (f : Fixes) (t : Lookup) (n : QN) (v : Nat) : Lookup :=
  let t' := t.put n v
  if f.size then { t' with size := t.size + ((countVals t'.root : Int) - countVals t.root) } else t'

def putAll (f : Fixes) (t : Lookup) (n : QN) (vs : List Nat) : Lookup :=
  let t' := t.putAll n vs
  if f.size then { t' with size := t.size + ((countVals t'.root : Int) - countVals t.root) } else t'

-- F6 getMappings: prepend the segment for every non-root node.
mutual
def mappingsR (n : Node) (v : Nat) : List QN :=
  match n with
  | .mk seg vals kids =>
    (mappingsListR kids v).map (seg :: ·) ++ (if auFind vals v ≥ 0 then [[seg]] else [])
def mappingsListR : List Node → Nat → List QN
  | [], _ => []
  | c :: cs, v => mappingsR c v ++ mappingsListR cs v
end

def getMappings (f : Fixes) (t : Lookup) (v : Nat) : List QN :=
  if f.mappings then mappingsListR t.root.kids v else t.getMappings v

end Trie.Fixed
