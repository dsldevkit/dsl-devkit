import BinaryStorage.Model

/-!
All-sizes inductive-invariant proof of P1/P3 for the FIXED protocol.

Abstraction: each URI carries its own store-lifecycle phase; the executor (any number of
workers, any queue capacity, CallerRunsPolicy, shutdown/shutdownNow, any number of clusters and
executor generations) only decides *which* URI steps next, so the global system is
"any URI takes one protocol step at a time", over an unbounded URI space (`Nat → U`).
The sources set is concurrent in the fix, so each set operation is one atomic step.
-/
namespace BinaryStorage.Generic

inductive GPh | idle | queued | ser | writing | written | done | dropped
  deriving DecidableEq, Repr

structure U where
  rebuilt : Bool
  inSrc : Bool
  bin : Bin
  ph : GPh

/-- One step of the fixed per-URI protocol. -/
inductive FStep : U → U → Prop
  /-- installSourceLevelURIs / queueAffectedResources: URI becomes (re)built, added to sources. -/
  | mark (u : U) : FStep u { u with rebuilt := true, inSrc := true }
  | submit (u : U) : u.rebuilt = true → u.ph = .idle → FStep u { u with ph := .queued }
  | start (u : U) : u.ph = .queued → FStep u { u with ph := .ser }
  /-- shutdownNow returns the task; fix deletes its outdated binary. -/
  | drop (u : U) : u.ph = .queued → FStep u { u with ph := .dropped, bin := .none }
  | beginWrite (u : U) : u.ph = .ser → FStep u { u with ph := .writing, bin := .part }
  /-- detached resource / IOException: fix deletes storage, keeps URI in sources. -/
  | failEarly (u : U) : u.ph = .ser → FStep u { u with ph := .done, bin := .none }
  | endWrite (u : U) : u.ph = .writing → FStep u { u with ph := .written, bin := .fresh }
  | abortWrite (u : U) : u.ph = .writing → FStep u { u with ph := .done, bin := .none }
  /-- the only removal from sources: after the complete write. -/
  | remove (u : U) : u.ph = .written → FStep u { u with ph := .done, inSrc := false }

/-- The code as written adds MCBS:656 — removal right after submission, in any phase. -/
inductive BStep : U → U → Prop
  | fixed (u v : U) : FStep u v → BStep u v
  | mainRemove (u : U) : u.ph ≠ .idle → BStep u { u with inSrc := false }

abbrev Sys := Nat → U

def initial (σ : Sys) : Prop := ∀ k, (σ k).rebuilt = false ∧ (σ k).ph = .idle

inductive Reach (R : U → U → Prop) : Sys → Prop
  | init (σ : Sys) : initial σ → Reach R σ
  | step (σ : Sys) (k : Nat) (v : U) : Reach R σ → R (σ k) v →
      Reach R (fun j => if j = k then v else σ j)

/-- Per-URI invariant. -/
def Inv (u : U) : Prop :=
  (u.rebuilt = false → u.ph = .idle) ∧
  (u.rebuilt = true → (u.ph = .written → u.bin = .fresh) ∧ (u.inSrc = false → u.bin = .fresh ∧ u.ph = .done))

theorem inv_step {u v : U} (h : Inv u) (s : FStep u v) : Inv v := by
  obtain ⟨h1, h2⟩ := h
  cases s with
  | mark =>
    refine ⟨by simp, fun _ => ⟨fun hw => ?_, by simp⟩⟩
    cases hr : u.rebuilt
    · have := h1 hr; simp_all
    · exact (h2 hr).1 hw
  | submit hr hi => exact ⟨by simp_all, fun _ => ⟨by simp, fun hs => by have := (h2 hr).2 hs; simp_all⟩⟩
  | start hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | drop hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | beginWrite hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | failEarly hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | endWrite hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | abortWrite hq =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun hs => ?_⟩⟩
    have := (h2 hr).2 hs; simp_all
  | remove hw =>
    refine ⟨fun hr => by have := h1 hr; simp_all, fun hr => ⟨by simp, fun _ => ?_⟩⟩
    exact ⟨(h2 hr).1 hw, rfl⟩

theorem inv_reach {σ : Sys} (h : Reach FStep σ) : ∀ k, Inv (σ k) := by
  induction h with
  | init σ hi =>
    intro k; obtain ⟨hr, hp⟩ := hi k
    exact ⟨fun _ => hp, fun h => by simp_all⟩
  | step σ k v _ hs ih =>
    intro j
    by_cases hj : j = k
    · subst hj; simpa using inv_step (ih j) hs
    · simpa [hj] using ih j

/-- **P1 + P3 for all sizes (fixed protocol).** Whenever a rebuilt URI is binary-loadable
    (not a source-level URI and a binary exists) the binary is completely written: no reader can
    observe a partial or outdated binary, for any number of URIs, workers, clusters, interleavings. -/
theorem fixed_safe {σ : Sys} (h : Reach FStep σ) (k : Nat) (hr : (σ k).rebuilt = true)
    (hs : (σ k).inSrc = false) : (σ k).bin = .fresh :=
  ((inv_reach h k).2 hr).2 hs |>.1

/-- The same statement fails for the code as written (MCBS:656), already with one URI. -/
theorem buggy_unsafe : ∃ σ, Reach BStep σ ∧ (σ 0).rebuilt = true ∧ (σ 0).inSrc = false ∧ (σ 0).bin = .stale := by
  let σ0 : Sys := fun _ => { rebuilt := false, inSrc := false, bin := .stale, ph := .idle }
  have r0 : Reach BStep σ0 := .init σ0 (fun _ => ⟨rfl, rfl⟩)
  let u1 : U := { rebuilt := true, inSrc := true, bin := .stale, ph := .idle }
  have r1 := Reach.step σ0 0 u1 r0 (.fixed _ _ (.mark _))
  let σ1 : Sys := fun j => if j = 0 then u1 else σ0 j
  let u2 : U := { u1 with ph := .queued }
  have r2 := Reach.step σ1 0 u2 r1 (.fixed _ _ (.submit _ (by simp [σ1, u1]) (by simp [σ1, u1])))
  let σ2 : Sys := fun j => if j = 0 then u2 else σ1 j
  have r3 := Reach.step σ2 0 { u2 with inSrc := false } r2 (.mainRemove _ (by simp [σ2, u2]))
  exact ⟨_, r3, by simp [u2, u1], by simp, by simp [u2, u1]⟩

end BinaryStorage.Generic
