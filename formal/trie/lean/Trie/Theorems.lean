/-
Theorems.

* `Cex.*`  — concrete counterexamples on the as-written model, closed by kernel `decide`
             (no extra axioms) or, where the statement involves well-founded recursion
             (glob matching), by `native_decide`.
* `All.*`  — root-cause facts proved for ALL inputs (induction / case analysis, no `sorry`).
* `Bounded.*` — bounded exhaustive results via `native_decide` (fixed model passes, planted bug
             caught, as-written fails) on the smaller domains.

`#print axioms` for each is recorded in NOTES.md (see `lake env lean Trie/Axioms.lean`).
-/
import Trie.Domains

namespace Trie.Theorems
open Trie Trie.Checks

def s (str : String) : Seg := str.toList.map Char.toNat
def P (l : List String) : Pat := { segs := l.map s }
def G (l : List String) : Pat := { segs := l.map s, glob := true }
def mkT (share : Bool) (l : List (List String × Nat)) : Lookup :=
  l.foldl (fun t (n, v) => t.put (n.map s) v) (Lookup.empty share)
def mkS (l : List (List String × Nat)) : TSL := l.foldl (fun t (n, v) => t.put (n.map s) v) TSL.empty

namespace Cex

/-- B1: top-level `*` has upper bound `"!"`, so a stored `b` is not found although `*` matches it. -/
theorem topStar_upper : (P ["*"]).upperExclusive = [[BANG]] := by decide
theorem topStar_matches : (P ["*"]).matches [s "b"] = true := by decide
theorem topStar_tree : (mkT false [(["b"], 0)]).getPattern (P ["*"]) = [] := by decide
theorem topStar_tsl : (mkS [(["b"], 0)]).getPattern (P ["*"]) = some [] := by decide
theorem topStarStar_tree : (mkT false [(["b", "c"], 0)]).getPattern (P ["**"]) = [] := by decide

/-- B2: `'!'` is not the successor of a segment: `"a␠"` lies in `[a, a!)`, so pattern `a`
returns it from both lookups, but `a` does not match `a␠`. -/
theorem bang_tree : (mkT false [(["a "], 0)]).getPattern (P ["a"]) = [0] := by decide
theorem bang_tsl : (mkS [(["a "], 0)]).getPattern (P ["a"]) = some [0] := by decide
theorem bang_nomatch : (P ["a"]).matches [s "a "] = false := by decide
/-- ... and for `a.*` the tree returns the grandchild `(a␠, x)` of a sibling only when `a` exists. -/
theorem bang_tree_sibling : (mkT false [(["a ", "x"], 1), (["a", "b"], 2)]).getPattern (P ["a", "*"]) = [2, 1] := by decide
theorem bang_tree_sibling_absent : (mkT false [(["a ", "x"], 1)]).getPattern (P ["a", "*"]) = [] := by decide
theorem bang_tsl_sibling : (mkS [(["a ", "x"], 1)]).getPattern (P ["a", "*"]) = some [1] := by decide

/-- B3: a wildcard-free pattern `matches` every longer name it prefixes, while both lookups
return only the exact name. -/
theorem exact_matches_longer : (P ["a"]).matches [s "a", s "b"] = true := by decide
theorem exact_tree : (mkT false [(["a"], 0), (["a", "b"], 1)]).getPattern (P ["a"]) = [0] := by decide

/-- B4 (TreeSetLookup only): `compareTo` treats an empty pattern segment as smaller than
everything, so `("", "")` is returned for pattern `("", "␠")`. The tree is correct here. -/
theorem emptySeg_tsl : (mkS [(["", ""], 0)]).getPattern (P ["", " "]) = some [0] := by decide
theorem emptySeg_tree : (mkT false [(["", ""], 0)]).getPattern (P ["", " "]) = [] := by decide
theorem emptySeg_nomatch : (P ["", " "]).matches [s "", s ""] = false := by decide

/-- B5: `put` increments `size` even when the value is already mapped. -/
theorem size_drift :
    let t := mkT false [(["a"], 0), (["a"], 0)]
    t.size = 2 ∧ countVals t.root = 1 := by decide
theorem size_drift_remove :
    let t := (mkT false [(["a"], 0), (["a"], 0)]).remove [s "a"] 0
    t.size = 1 ∧ countVals t.root = 0 ∧ t.get [s "a"] = none := by decide

/-- B6: `getMappings` drops blank intermediate segments. -/
theorem mappings_blank : (mkT false [([" ", "a"], 0)]).getMappings 0 = [[s "a"]] := by decide
theorem mappings_empty : (mkT false [(["", "a"], 0)]).getMappings 0 = [[s "a"]] := by decide

/-- B7: with `shareValues = true` a child that shares its parent's array is counted once. -/
theorem share_tree : (mkT true [(["a"], 0), (["a", "b"], 0)]).getPattern (P ["a**"]) = [0] := by decide
theorem share_plain : (mkT false [(["a"], 0), (["a", "b"], 0)]).getPattern (P ["a**"]) = [0, 0] := by decide
theorem share_tsl : (mkS [(["a"], 0), (["a", "b"], 0)]).getPattern (P ["a**"]) = some [0, 0] := by decide

/-- B8: `PatternAwareEObjectDescriptionLookUp` case-sensitive pattern queries return nothing. -/
theorem consumer_cs :
    exportedObjects false Lookup.getPattern [([s "Foo"], 0), ([s "FooBar"], 1)] (.pat (P ["Foo*"])) false = [] := by decide
theorem consumer_ci :
    exportedObjects false Lookup.getPattern [([s "Foo"], 0), ([s "FooBar"], 1)] (.pat (P ["Foo*"])) true = [0, 1] := by decide

/-- B9: U+FFFF. `(char)(c+1)` wraps, so `"￿*"` has upper `"\u0000"` < lower and
`TreeMap.subMap` throws; and a stored `"￿"` is the tree's sentinel, invisible to patterns. -/
theorem max_upper_wraps : (({ segs := [[CMAX, STAR]] } : Pat)).upperExclusive = [[0]] := by decide
theorem max_tsl_throws : TSL.empty.getPattern { segs := [[CMAX, STAR]] } = none := by decide
theorem max_tree_invisible :
    (Lookup.empty false |>.put [[CMAX]] 0).getPattern { segs := [[CMAX]] } = [] ∧
    (Lookup.empty false |>.put [[CMAX]] 0).get [[CMAX]] = some [0] := by decide

/-- B10: glob lookups miss matches (glob patterns are not used in production code). -/
theorem glob_star_miss : (mkT false [(["b"], 0)]).getPattern (G ["*"]) = [] ∧ (G ["*"]).matches [s "b"] = true := by
  native_decide
theorem glob_level_miss :
    (mkT false [(["a", "b"], 0)]).getPattern (G ["a*", "b"]) = [] ∧
    (mkS [(["a", "b"], 0)]).getPattern (G ["a*", "b"]) = some [0] := by native_decide
theorem glob_deeper_miss : (mkT false [(["a", "x"], 0)]).getPattern (G ["a*"]) = [] ∧ (G ["a*"]).matches [s "a", s "x"] = true := by
  native_decide
theorem glob_case_miss : (mkT false [(["foo"], 0)]).getPattern (G ["F*"]) = [] ∧ (G ["F*"]).matches [s "foo"] = true := by
  native_decide

end Cex

namespace All

/-! #### Java string order facts -/

theorem strCmp_self (a : Seg) : strCmp a a = .eq := by
  induction a with
  | nil => rfl
  | cons x xs ih => simp [strCmp, ih]

theorem strCmp_append_lt (a : Seg) (c : JChar) (r : Seg) : strCmp a (a ++ c :: r) = .lt := by
  induction a with
  | nil => rfl
  | cons x xs ih => simp [strCmp, ih]

theorem strCmp_append_cmp (a : Seg) (c d : JChar) (h : c < d) : strCmp (a ++ [c]) (a ++ [d]) = .lt := by
  induction a with
  | nil => simp [strCmp, h]
  | cons x xs ih => simp [strCmp, ih]

/-- B2 root cause, all sizes: for every segment `a` and every char `c < '!'`, the name `[a ++ c]`
lies strictly inside `[[a], [a ++ "!"])`, the range computed for the exact pattern `a`. -/
theorem bang_not_successor (a : Seg) (c : JChar) (hc : c < BANG) :
    qnCmp [a] [a ++ [c]] = .lt ∧ qnCmp [a ++ [c]] [a ++ [BANG]] = .lt := by
  refine ⟨?_, ?_⟩
  · simp [qnCmp, strCmp_append_lt]
  · simp [qnCmp, strCmp_append_cmp a c BANG hc]

/-- F2 correctness, all sizes: `a ++ "\u0000"` is the immediate successor of `a` in Java string
order — every `t > a` is either `a ++ "\u0000"` itself or greater than it. -/
theorem nul_is_successor : ∀ (a t : Seg), strCmp a t = .lt → t = a ++ [0] ∨ strCmp (a ++ [0]) t = .lt
  | [], [] => by simp [strCmp]
  | [], d :: r => by
    intro _
    cases d with
    | zero => cases r with
      | nil => simp
      | cons e r => right; simp [strCmp]
    | succ d => right; simp [strCmp]
  | _ :: _, [] => by simp [strCmp]
  | x :: xs, y :: ys => by
    intro h
    simp only [strCmp] at h
    by_cases hxy : x < y
    · right; simp [strCmp, hxy]
    · by_cases hyx : y < x
      · simp [hxy, hyx] at h
      · have hxy' : x = y := Nat.le_antisymm (Nat.not_lt.mp hyx) (Nat.not_lt.mp hxy)
        subst hxy'
        simp [hxy] at h
        rcases nul_is_successor xs ys h with h1 | h1
        · left; simp [h1]
        · right; simp [strCmp, h1]

/-- B1 root cause, all sizes: pattern `*` matches every single-segment name, but every such name
whose first char is above `'!'` compares ≥ the upper bound `"!"`. -/
theorem topStar_unsound (c : JChar) (r : Seg) (hc : BANG < c) :
    (P ["*"]).matches [c :: r] = true ∧
    comparatorCmp (plain [c :: r]) (plain (P ["*"]).upperExclusive) = .gt := by
  refine ⟨rfl, ?_⟩
  have hup : (P ["*"]).upperExclusive = [[BANG]] := by decide
  rw [hup]
  unfold BANG at hc ⊢
  simp [comparatorCmp, keyCompareTo, plain, qnCmp, strCmp, hc, Ordering.swap]

/-- B3 root cause, all sizes: a wildcard-free pattern matches every extension of itself. -/
theorem matchLoop_prefix (pc oc : Nat) : ∀ (segs rest : List Seg),
    (∀ seg ∈ segs, STAR ∉ seg) → matchLoop pc oc segs (segs ++ rest) = true
  | [], _, _ => by simp [matchLoop]
  | seg :: ps, rest, h => by
    have hs : STAR ∉ seg := h seg (by simp)
    have hi : indexOf STAR seg = -1 := by
      unfold indexOf
      have : seg.findIdx? (· == STAR) = none := by
        rw [List.findIdx?_eq_none_iff]; intro x hx; simp; rintro rfl; exact hs hx
      simp [this]
    have ih := matchLoop_prefix pc oc ps rest (fun s hs' => h s (by simp [hs']))
    simp [matchLoop, hi, ih]

theorem exact_matches_extensions (segs : List Seg) (x : Seg)
    (h : ∀ seg ∈ segs, STAR ∉ seg) :
    ({ segs := segs } : Pat).matches (segs ++ [x]) = true := by
  simp only [Pat.matches, Pat.matchesQ, plain]
  have hlen : ¬ (segs.length + 1 < segs.length) := by omega
  simp [hlen, matchLoop_prefix _ _ segs [x] h]

/-- B5 root cause, all sizes: `ArrayUtils.addAll` returns the same array when the value is
already present, while `put` unconditionally does `size++`. -/
theorem addAll_present (a : Arr) (v fresh id : Nat) (hv : v ∈ a.elems) :
    auAddAll (some a) (some ⟨id, [v]⟩) fresh = some a := by
  have hne : a.elems ≠ [] := by intro h; simp [h] at hv
  simp [auAddAll, hne, hv]

theorem put_size (t : Lookup) (n : QN) (v : Nat) : (t.put n v).size = t.size + 1 := rfl

/-- B8 root cause, all sizes: for EVERY set of descriptions and EVERY pattern, the case-sensitive
query returns nothing (`name.matches(name)` is always false: `name` is itself a pattern). -/
theorem consumer_cs_empty (getP : Lookup → Pat → List Nat) (descs : List (QN × Nat)) (p : Pat) :
    exportedObjects false getP descs (.pat p) false = [] := by
  simp [exportedObjects, Pat.matchesQ]

end All

namespace Bounded

open Trie.Domains

/-- Sanity: the fully fixed model passes range soundness and range precision on the P1 domain
(7353 patterns × 2235 names, U+FFFF excluded by assumption A1). -/
theorem fixed_rangeSound : rangeSound fixed (fixed.fP patsP1) (fixed.fN namesP1) = none := by native_decide
theorem fixed_rangePrecise : rangePrecise fixed (fixed.fP patsP1) (fixed.fN namesP1) = none := by native_decide
/-- Sanity: fixed tree = spec, TreeSetLookup = spec on all stores of ≤ 2 names. -/
theorem fixed_tree : lookupCheck fixed .treeVsSpec (storesP2 2) patsP2 = none := by native_decide
theorem fixed_tsl : lookupCheck fixed .tslVsSpec (storesP2 2) patsP2 = none := by native_decide
theorem fixed_share : lookupCheck fixed .shareVsPlain (storesP2 2) patsP2 = none := by native_decide
theorem fixed_consumer_cs : consumerCheck fixed descSetsP4 argsP4 false = none := by native_decide
theorem fixed_consumer_ci : consumerCheck fixed descSetsP4 argsP4 true = none := by native_decide
theorem fixed_size : (opsCheck fixed .size false namesP3 valsP3 patsP3 opsP3 3).1 = none := by native_decide
theorem fixed_mappings : (opsCheck fixed .mappings true namesP3 valsP3 patsP3 opsP3 3).1 = none := by native_decide
/-- Sanity: the planted bug (tree ignores `recursive`) is caught. -/
theorem planted_caught : (lookupCheck planted .treeVsSpec (storesP2 2) patsP2).isSome = true := by native_decide
/-- The as-written model fails the same checks. -/
theorem asWritten_fails :
    (rangeSound asWritten patsP1 namesP1).isSome ∧ (lookupCheck asWritten .treeVsSpec (storesP2 2) patsP2).isSome := by
  native_decide
/-- Witnesses: good paths are reachable (non-empty, correct tree results exist). -/
theorem witnesses : 0 < witnessCount asWritten (storesP2 1) patsP2 false ∧ 0 < witnessCount asWritten (storesP2 1) patsP2 true := by
  native_decide

end Bounded
end Trie.Theorems
