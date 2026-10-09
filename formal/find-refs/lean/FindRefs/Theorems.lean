/-
Bounded model-checking results as kernel-accepted theorems (native_decide over the
exhaustive BFS to a fixpoint).  Default Cfg: 2 URIs, 3 accepts, 1 reset, 2 input switches.
-/
import FindRefs.Buggy
import FindRefs.Fixed
import FindRefs.Check
import FindRefs.Proof

namespace FindRefs.Theorems
open FindRefs

def cfg : Cfg := {}

/-- Verdict vector for a model: fixpoint reached, then for P1, P1', P2, P3, P4, DL whether it holds. -/
def verdicts (next : St → List (String × St)) (c : Cfg) : List Bool :=
  let e := explore next (St.init c)
  e.complete :: (props.map fun (_, p) => (firstViolation e p).isNone) ++
    [(firstViolation e (fun s => !deadlock next s)).isNone]

/-- As written: P1, P1', P3, P4 and deadlock-freedom are violated; P2 holds. -/
theorem buggy_verdicts : verdicts (Buggy.next .none cfg) cfg = [true, false, false, true, false, false, false] := by
  native_decide

/-- Fixed design: everything holds within the bound. -/
theorem fixed_verdicts : verdicts (Fixed.next false cfg) cfg = [true, true, true, true, true, true, true] := by
  native_decide

/-- Sanity: a planted bug (flag set without scheduling) is caught (P1, P1'). -/
theorem planted_caught : verdicts (Fixed.next true cfg) cfg = [true, false, false, true, true, true, true] := by
  native_decide

end FindRefs.Theorems

#print axioms FindRefs.Proof.no_lost_root
#print axioms FindRefs.Theorems.fixed_verdicts
