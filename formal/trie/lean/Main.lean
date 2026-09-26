import Trie.Domains

open Trie Trie.Checks Trie.Domains

def timed (label : String) (act : Unit → Option String) : IO Bool := do
  let t0 ← IO.monoMsNow
  let r ← IO.lazyPure act
  let msg := match r with | none => "PASS" | some m => "FAIL  " ++ m
  let t1 ← IO.monoMsNow
  IO.println s!"  [{t1 - t0} ms] {label}: {msg}"
  pure r.isNone

def runImpl (impl : Impl) (storeK : Nat) (opLen : Nat) : IO Unit := do
  IO.println s!"=== {impl.name} ==="
  let _ ← timed s!"P1  range soundness ({patsP1.length} patterns x {namesP1.length} names)" fun _ => rangeSound impl (impl.fP patsP1) (impl.fN namesP1)
  let _ ← timed "P1b lower <= upper (no subMap IAE)" fun _ => boundsOrdered impl (impl.fP (patsP1 ++ patsMax ++ globsP5))
  let _ ← timed "P1c range precision (in-range & right count ⇒ matched)" fun _ => rangePrecise impl (impl.fP patsP1) (impl.fN namesP1)
  let _ ← timed "P1g glob range soundness" fun _ => rangeSound impl globsP5 (impl.fN namesP1)
  let _ ← timed "P5  subMap predicates monotone" fun _ => subMapMonotone impl (impl.fP (patsP1 ++ globsP5)) (impl.fN namesP1)
  let stores := storesP2 storeK
  let label (w : String) := s!"P2  {w} ({stores.length} stores x {patsP2.length} patterns)"
  let _ ← timed (label "tree = spec") fun _ => lookupCheck impl .treeVsSpec stores patsP2
  let _ ← timed (label "TreeSetLookup = spec") fun _ => lookupCheck impl .tslVsSpec stores patsP2
  let _ ← timed (label "tree = TreeSetLookup") fun _ => lookupCheck impl .treeVsTsl stores patsP2
  let _ ← timed (label "shareValues=true multiset = shareValues=false") fun _ => lookupCheck impl .shareVsPlain stores patsP2
  let smax := impl.fS (storesBySize universeMax 2)
  let _ ← timed s!"P2m tree = spec near U+FFFF ({smax.length} stores)" fun _ => lookupCheck impl .treeVsSpec smax (impl.fP patsMax)
  let _ ← timed s!"P2m TreeSetLookup = spec near U+FFFF" fun _ => lookupCheck impl .tslVsSpec smax (impl.fP patsMax)
  let s2 := storesP2 2
  let _ ← timed s!"P2g glob: tree ⊇ spec ({s2.length} stores x {globsP5.length} globs)" fun _ => lookupCheck impl .globTreeComplete s2 globsP5
  let _ ← timed "P2g glob: TreeSetLookup ⊇ spec" fun _ => lookupCheck impl .globTslComplete s2 globsP5
  for share in [false, true] do
    for (prop, nm) in [(OpProp.size, "size = #stored pairs"), (.get, "get(name) = TreeSetLookup"),
                       (.mappings, "getMappings = TreeSetLookup"), (.patternGet, "get(pattern) = TreeSetLookup")] do
      let t0 ← IO.monoMsNow
      let (r, n) ← IO.lazyPure fun _ => opsCheck impl prop share namesP3 valsP3 patsP3 opsP3 opLen
      let t1 ← IO.monoMsNow
      IO.println s!"  [{t1 - t0} ms] P3  ops≤{opLen} share={share} {nm} ({n} states): {match r with | none => "PASS" | some m => "FAIL  " ++ m}"
  let _ ← timed s!"P4  consumer case-sensitive = spec ({descSetsP4.length} desc sets x {argsP4.length} args)" fun _ => consumerCheck impl descSetsP4 argsP4 false
  let _ ← timed "P4  consumer case-insensitive = spec" fun _ => consumerCheck impl descSetsP4 argsP4 true
  let _ ← timed "P4  consumer case-sensitive ⊆ case-insensitive" fun _ => caseMonotone impl descSetsP4 argsP4
  let wn := witnessCount impl (storesP2 2) patsP2 false
  let wr := witnessCount impl (storesP2 2) patsP2 true
  IO.println s!"  witnesses (store,pattern) with non-empty correct tree result: non-recursive={wn}, recursive={wr}"

def main (args : List String) : IO Unit := do
  let storeK := (args.head? >>= String.toNat?).getD 3
  let opLen := ((args.drop 1).head? >>= String.toNat?).getD 4
  let which := (args.drop 2).head?.getD "all"
  let t0 ← IO.monoMsNow
  let _ ← timed "E0  ladder base (no fixes) ≡ literal as-written model" fun _ =>
    baseEquiv (storesP2 2) (patsP2 ++ globsP5 ++ patsMax) (universeP2 ++ universeMax)
  for impl in [asWritten] ++ ladder ++ [planted] do
    if which == "all" || which == impl.name then runImpl impl storeK opLen
  let t1 ← IO.monoMsNow
  IO.println s!"total wall time {t1 - t0} ms"
