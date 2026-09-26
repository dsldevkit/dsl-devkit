/-
Model of `PatternAwareEObjectDescriptionLookUp.getExportedObjects(type, name, ignoreCase)`
(PatternAwareEObjectDescriptionLookUp.java:49-66) and `getNameToObjectsLookup` (93-117).
All descriptions are assumed type-compatible (`EcoreUtil2.isAssignableFrom` = true), so the
type filter is the identity. A description is (name, id).
-/
import Trie.TreeSet

namespace Trie

inductive Arg where
  | name (q : QN)
  | pat (p : Pat)
deriving Repr, Inhabited

/-- Lines 100-111: `localMap.put(description.getName().toLowerCase(), description)` on a
`QualifiedNameSegmentTreeLookup` with `shareValues = false` (line 101). -/
def buildLookup (descs : List (QN × Nat)) : Lookup :=
  descs.foldl (fun t (n, id) => t.put (lowerQ n) id) (Lookup.empty false)

/-- `getExportedObjects` as written (lines 49-66). `fixConsumer` swaps line 63's
`matches(name)` for `matches(input.getName())`. -/
def exportedObjects (fixConsumer : Bool) (getP : Lookup → Pat → List Nat)
    (descs : List (QN × Nat)) (arg : Arg) (ignoreCase : Bool) : List Nat :=
  let t := buildLookup descs
  let values : Option (List Nat) :=
    match arg with
    | .pat p => some (getP t p.toLower)                      -- 50, 53-55
    | .name q => t.get (lowerQ q)                            -- 56-57
  match values with
  | none => []                                               -- 59-61
  | some vs =>
    vs.filter fun id =>
      let inputName := ((descs.find? (·.2 == id)).map (·.1)).getD []
      if ignoreCase then true                                -- 62
      else match arg with
        | .pat p =>                                          -- 63
          if fixConsumer then p.matches inputName
          else (p.matchesQ ⟨p.segs, true⟩).getD false        -- `((QualifiedNamePattern) name).matches(name)`
        | .name q => q == inputName                          -- 64

/-- Intended result: ids whose name the argument matches (case-(in)sensitively). -/
def exportedSpec (descs : List (QN × Nat)) (arg : Arg) (ignoreCase : Bool) : List Nat :=
  (descs.filter fun (n, _) =>
    match arg, ignoreCase with
    | .pat p, false => p.matches n
    | .pat p, true => p.toLower.matches (lowerQ n)
    | .name q, false => q == n
    | .name q, true => lowerQ q == lowerQ n).map (·.2)

end Trie
