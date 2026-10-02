import Pipeline
open Pipeline

/-!
# Machine-checked verdicts (exhaustive BFS to a fixpoint, evaluated by `native_decide`)

Verdict vector order (see `Pipeline.analyze`):
`[P1, P2, P3, P4, P5, P6, P7, R1, R2, W1, W2, W4, W5, W6, W3]`
P* / R* = true means the property holds; W* = true means the good path is reachable.
-/

def cfgAt (i : Nat) : Cfg := ((configs[i]?).map (·.2)).getD {}

/-- The code as written: P1, P2, P3, P5 violated, R1 (stuck dangling tag) violated,
    maintenance snapshot and release unreachable (W2, W5). -/
theorem faithful_verdicts :
    bits (cfgAt 0) =
      [false, false, false, true, false, true, true, false, true,
       true, false, true, false, true, true] := by native_decide

theorem faithful_keep1_verdicts :
    bits (cfgAt 1) =
      [false, false, false, true, false, true, true, false, true,
       true, false, true, false, true, true] := by native_decide

/-- All fixes A-F: every property holds and every witness is reachable. -/
theorem fixed_verdicts :
    bits (cfgAt 2) = List.replicate 15 true := by native_decide

theorem fixed_keep1_verdicts :
    bits (cfgAt 3) = List.replicate 15 true := by native_decide

/-- Planted bug (blind force push) is caught: P4 NoLostReleaseRepo fails (and with it P1/R1).
    W4/W6 need a push, which budget 0 does not allow. -/
theorem planted_caught :
    bits (cfgAt 4) =
      [false, true, true, false, true, true, true, false, true,
       true, true, false, true, false, true] := by native_decide

/-- The two maintenance blockers are independent: fixing only the baseline (D) lets maintenance
    snapshots build (W5) but not release (W2); fixing only the tag line (C) fixes P3 but neither
    W5 nor W2; both together make a maintenance release reachable. -/
theorem fixD_only : bits (cfgAt 5) =
    [false, false, false, true, false, true, true, false, true,
     true, false, true, true, true, true] := by native_decide

theorem fixC_only : bits (cfgAt 6) =
    [false, false, true, true, false, true, true, false, true,
     true, false, true, false, true, true] := by native_decide

theorem fixCD : bits (cfgAt 7) =
    [false, false, true, true, false, true, true, false, true,
     true, true, true, true, true, true] := by native_decide

/-- Fix A alone repairs P1/R1 and keeps R2; P5 needs fix E. -/
theorem fixA_only : bits (cfgAt 8) =
    [true, false, false, true, false, true, true, true, true,
     true, false, true, false, true, true] := by native_decide

theorem fixes_without_E : bits (cfgAt 9) =
    [true, true, true, true, false, true, true, true, true,
     true, true, true, true, true, true] := by native_decide
