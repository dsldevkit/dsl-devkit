import BinaryStorage.Check

/-!
Machine-checked verdicts of the exhaustive BFS (bounded instance: 3 URIs, 2 clusters,
1-2 storage workers, queue capacity 0-1, 3 executor generations). `native_decide` trusts the
compiler; the BFS reaches a fixpoint (`complete`) so each verdict covers every interleaving.
-/
namespace BinaryStorage

/-! ### Fixed model: every property holds, every run terminates, no deadlock -/
theorem fixed_default : allHold { fixed := true } = true := by native_decide
theorem fixed_two_workers_iofail : allHold { fixed := true, nW := 2, ioFail := true } = true := by native_decide
theorem fixed_caller_runs : allHold { fixed := true, qcap := 0, nW := 2, ioFail := true } = true := by native_decide
theorem fixed_no_old_binary : allHold { fixed := true, initBin := .none } = true := by native_decide

/-! ### Sanity: planted bug (remove from sources before the write) is caught -/
theorem planted_caught : ((report { fixed := true, plant := true }).p1).isSome = true := by native_decide

/-! ### Code as written: violations -/
/-- P4: unsynchronised concurrent access to the sources HashSet (7 steps). -/
theorem bug_p4 : (report {}).p4 = some 7 := by native_decide
/-- P1: URI binary-loadable before its binary is written (MCBS:656), 7 steps. -/
theorem bug_p1 : (report {}).p1 = some 7 := by native_decide
/-- P3 (stale), with atomic set semantics and without any timeout: next-cluster loader reads
    the outdated binary of a URI rebuilt in the previous cluster (21 steps). -/
theorem bug_stale_no_timeout :
    (report { raceNondet := false, timeout := false }).p3stale = some 21 := by native_decide
/-- P3 (partial), atomic set semantics: loader reads a binary being written (22 steps). -/
theorem bug_partial : (report { raceNondet := false }).p3partial = some 22 := by native_decide
/-- P2: store still running (neither completed nor reported) when the build returns (35 steps). -/
theorem bug_p2 : (report {}).p2 = some 35 := by native_decide
/-- P5: an outdated binary survives the build: dropped store (timeout) ... -/
theorem bug_pEnd_timeout : ((report { raceNondet := false }).pEnd).isSome = true := by native_decide
/-- ... or a swallowed IOException, even without timeouts and races. -/
theorem bug_pEnd_iofail :
    ((report { raceNondet := false, timeout := false, ioFail := true }).pEnd).isSome = true := by native_decide
/-- Without timeouts, IOException, dependency loading in loaders and HashSet races, only the
    transient P1 window and the set race itself remain. -/
theorem bug_minimal :
    let r := report { raceNondet := false, timeout := false, touchDep := false }
    r.p2.isNone && r.p3partial.isNone && r.p3stale.isNone && r.pEnd.isNone && r.p4.isSome = true := by
  native_decide
/-- The bounded build always terminates and never deadlocks (code as written). -/
theorem bug_terminates : let r := report {}; r.complete && r.acyclic && r.deadlocks == 0 = true := by
  native_decide
/-- CallerRunsPolicy path is exercised, and a silent discard (execute on a shut-down pool) is unreachable. -/
theorem caller_runs_reachable_discard_unreachable :
    let g := explore { qcap := 0 }
    (g.states.toList.any (·.inl.isSome)) && !(g.states.toList.any (·.task.contains .discarded)) = true := by
  native_decide

end BinaryStorage
