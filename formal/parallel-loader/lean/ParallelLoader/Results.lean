import ParallelLoader.Check

namespace ParallelLoader

def qkinds : List QKind := [.unbounded, .sync, .bounded 1, .bounded 2]
def variants : List Variant := [.original, .fixTimeout, .fixInterrupt, .fixBoth, .plantedNoIncrement]

/-- Smallest instance: one URI, one timeout, no interrupts. -/
def cfg1 : Cfg := { n := 1, threads := 1, qk := .unbounded, timeouts := 1, interrupts := 0 }
/-- Only timeouts injected. -/
def cfgT (qk : QKind) (v : Variant := .original) : Cfg := { n := 2, threads := 2, qk, timeouts := 1, interrupts := 0, variant := v }
/-- Only interrupts injected. -/
def cfgI (qk : QKind) (v : Variant := .original) : Cfg := { n := 2, threads := 2, qk, timeouts := 0, interrupts := 1, variant := v }
/-- Everything, a bit larger. -/
def cfgAll (qk : QKind) (v : Variant := .original) : Cfg := { n := 3, threads := 2, qk, timeouts := 2, interrupts := 1, variant := v }
/-- No failures injected at all (only user cancel). -/
def cfgClean (qk : QKind) (v : Variant := .original) : Cfg := { n := 3, threads := 2, qk, timeouts := 0, interrupts := 0, variant := v }

def verdict (o : Option Nat) : String := if o.isNone then "ok " else "BAD"

def matrix : IO Unit := do
  let mut total := 0
  let cfgs : List (String × (QKind → Variant → Cfg)) :=
    [("clean", fun q v => cfgClean q v), ("T", fun q v => cfgT q v), ("I", fun q v => cfgI q v), ("all", fun q v => cfgAll q v)]
  for (nm, mk) in cfgs do
    for v in variants do
      for q in qkinds do
        let (_, r) := check (mk q v)
        total := total + r.states
        IO.println s!"{nm}\t{repr v}\t{repr q}\tstates={r.states}\tdepth={r.maxDepth}\tcomplete={r.complete}\tP1={verdict r.p1} P2={verdict r.p2} P3={verdict r.p3}"
  IO.println s!"total states explored: {total}"

#eval IO.println (summarize "smallest timeout instance" cfg1)
#eval IO.println (summarize "timeouts only" (cfgT .unbounded))
#eval IO.println (summarize "interrupts only, LinkedBlockingQueue" (cfgI .unbounded))
#eval IO.println (summarize "interrupts only, ArrayBlockingQueue(1)" (cfgI (.bounded 1)))
#eval IO.println (summarize "interrupts only, SynchronousQueue" (cfgI .sync))
#eval IO.println (summarize "planted bug" (cfgClean .unbounded .plantedNoIncrement))
#eval matrix

/-! ## Theorems over the bounded (here: exhaustive, `complete = true`) search -/

/-- Timeout bug: a single poll timeout makes the build abort without any cancel request. -/
theorem original_timeout_violates_P2 : ((check cfg1).2.p2.isSome && (check cfg1).2.complete) = true := by
  native_decide

theorem original_timeout_violates_P1 :
    (qkinds.all fun q => (check (cfgT q)).2.p1.isSome) = true := by native_decide

theorem original_timeout_violates_P2_all_queues :
    (qkinds.all fun q => (check (cfgT q)).2.p2.isSome) = true := by native_decide

/-- Interrupt bug: Array/LinkedBlockingQueue livelock, SynchronousQueue does not. -/
theorem original_interrupt_violates_P3 :
    ([QKind.unbounded, .bounded 1, .bounded 2].all fun q => (check (cfgI q)).2.p3.isSome) = true := by
  native_decide

theorem original_interrupt_sync_ok : (check (cfgI .sync)).2.ok = true := by native_decide

/-- Without injected timeouts/interrupts the original code satisfies all three properties. -/
theorem original_clean_ok : (qkinds.all fun q => (check (cfgClean q)).2.ok) = true := by native_decide

/-- Both minimal fixes together: all properties hold for every queue kind, n=3, 2 threads,
    2 timeouts, 1 interrupt, user cancel at any point. -/
theorem fixBoth_ok : (qkinds.all fun q => (check (cfgAll q .fixBoth)).2.ok) = true := by native_decide

/-- Each fix alone is insufficient under the full failure model. -/
theorem fixTimeout_alone_still_livelocks : (check (cfgAll .unbounded .fixTimeout)).2.p3.isSome = true := by
  native_decide
theorem fixInterrupt_alone_still_aborts : (check (cfgAll .unbounded .fixInterrupt)).2.p2.isSome = true := by
  native_decide

/-- Planted bug (PRL:272 never increments toProcess) is caught with zero injected failures. -/
theorem planted_caught :
    (qkinds.all fun q => let r := (check (cfgClean q .plantedNoIncrement)).2; r.p1.isSome && r.p2.isSome) = true := by
  native_decide

/-! ## Kernel-checked counterexample replay (no native code, plain `decide`) -/

/-- Follow `acts` from `init`, taking the successor carrying each label. -/
def replay (c : Cfg) : List Act → St → Option St
  | [], s => some s
  | a :: as, s => match (succ c s).find? (·.1 == a) with
    | some (_, t) => replay c as t
    | none => none

/-- The 3-step timeout counterexample, checked by the kernel. -/
theorem timeout_trace_kernel :
    ((replay cfg1 [.callNext, .pollTimeout, .abort] (init cfg1)).map fun s => !P2 s) = some true := by
  decide

/-- Livelock witness, checked by the kernel: after an interrupt the builder returns to exactly the same
    state via callNext; pollInterrupted (no progress) once the queue is full. -/
def livelockCfg : Cfg := { n := 1, threads := 1, qk := .bounded 1, timeouts := 0, interrupts := 1 }
def livelockPrefix : List Act := [.interrupt, .start 0, .loaded 0, .put 0]
theorem livelock_trace_kernel :
    (match replay livelockCfg livelockPrefix (init livelockCfg) with
     | some s => replay livelockCfg [.callNext, .pollInterrupted] s == some s
                 && (succ livelockCfg s).all (fun (a, _) => !a.isSystem || a == .callNext)
                 && (succ livelockCfg { s with pc := .poll }).all (fun (a, _) => !a.isSystem || a == .pollInterrupted)
     | none => false) = true := by
  decide

end ParallelLoader
