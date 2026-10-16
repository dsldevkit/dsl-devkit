/-
All-sizes proof (any Cfg: any number of URIs, accepts, resets, switches) for the fixed model:
"no lost root node": whenever no UIUpdater is scheduled or running, every root node in
rootNodes is shown in the viewer.  Proved via an inductive invariant.
-/
import FindRefs.Fixed

namespace FindRefs.Proof
open FindRefs

inductive Reach (next : St → List (String × St)) (init : St) : St → Prop
  | init : Reach next init init
  | step {s t : St} {l : String} : Reach next init s → (l, t) ∈ next s → Reach next init t

def Inv (s : St) : Prop :=
  (s.batch ≠ [] → s.flag = true) ∧
  (s.flag = true → s.jobs > 0 ∨ s.upc = .uDecideF) ∧
  (∀ n ∈ rootsVals s.roots, n ∈ s.viewer ∨ n ∈ s.batch)

theorem rootsVals_set (l : List (Option Nat)) (u n m : Nat) :
    m ∈ rootsVals (setRoot l u (some n)) → m = n ∨ m ∈ rootsVals l := by
  unfold rootsVals setRoot
  simp only [List.mem_filterMap, id_eq, exists_eq_right]
  intro h
  rcases List.mem_or_eq_of_mem_set h with h | h
  · exact Or.inr h
  · exact Or.inl (Option.some.inj h)

theorem rootsVals_clear (l : List (Option Nat)) : rootsVals (clearRoots l) = [] := by
  unfold rootsVals clearRoots
  induction l with
  | nil => rfl
  | cons x xs ih => simp

theorem init_inv (c : Cfg) : Inv (St.init c) := by
  refine ⟨?_, ?_, ?_⟩
  · simp [St.init]
  · simp [St.init]
  · intro n hn
    have : rootsVals (List.replicate c.nU (none : Option Nat)) = [] := by
      unfold rootsVals; induction c.nU <;> simp_all [List.replicate_succ]
    simp [St.init, this] at hn

/-- searchStarts only touches search-side fields. -/
theorem searchStarts_frame (c : Cfg) (s : St) (p : String × St) (h : p ∈ searchStarts c s) :
    p.2.batch = s.batch ∧ p.2.flag = s.flag ∧ p.2.jobs = s.jobs ∧ p.2.upc = s.upc ∧
    p.2.roots = s.roots ∧ p.2.viewer = s.viewer := by
  unfold searchStarts at h
  simp only [List.mem_append] at h
  rcases h with h | h
  · split at h
    · simp only [List.mem_map, List.mem_range] at h
      obtain ⟨_, _, rfl⟩ := h
      simp
    · simp at h
  · split at h
    · simp only [List.mem_cons, List.not_mem_nil, or_false] at h
      subst h; simp
    · simp at h

theorem attachRef_frame (s : St) (r : Nat) (wb : Bool) :
    (Fixed.attachRef s r wb).flag = s.flag ∧ (Fixed.attachRef s r wb).jobs = s.jobs ∧
    (Fixed.attachRef s r wb).upc = s.upc ∧ (Fixed.attachRef s r wb).viewer = s.viewer ∧
    (wb = false → (Fixed.attachRef s r wb).batch = s.batch) := by
  unfold Fixed.attachRef
  cases h : getRoot s.roots (uriOfRef s r) with
  | none => cases wb <;> simp [h]
  | some n => simp only [h]; split <;> simp

theorem attachRef_cover (s : St) (r : Nat)
    (h3 : ∀ n ∈ rootsVals s.roots, n ∈ s.viewer ∨ n ∈ s.batch) :
    ∀ n ∈ rootsVals (Fixed.attachRef s r true).roots,
      n ∈ (Fixed.attachRef s r true).viewer ∨ n ∈ (Fixed.attachRef s r true).batch := by
  unfold Fixed.attachRef
  cases h : getRoot s.roots (uriOfRef s r) with
  | some n => simp only [h]; split <;> exact h3
  | none =>
    simp only [h, ite_true]
    intro n hn
    rcases rootsVals_set _ _ _ _ hn with h | h
    · exact Or.inr (by simp [h])
    · rcases h3 n h with h' | h'
      · exact Or.inl h'
      · exact Or.inr (by simp [h'])

theorem ensureScheduled_inv (s : St)
    (h2 : s.flag = true → s.jobs > 0 ∨ s.upc = .uDecideF)
    (h3 : ∀ n ∈ rootsVals s.roots, n ∈ s.viewer ∨ n ∈ s.batch) :
    Inv (Fixed.ensureScheduled false s) := by
  unfold Fixed.ensureScheduled
  split
  · rename_i hf; exact ⟨fun _ => hf, h2, h3⟩
  · exact ⟨fun _ => rfl, fun _ => Or.inl (by simp), h3⟩

theorem addRef_inv (s : St) (r : Nat) (hI : Inv s) : Inv (Fixed.addRef false s r) := by
  obtain ⟨_, h2, h3⟩ := hI
  obtain ⟨ef, ej, eu, _, _⟩ := attachRef_frame s r true
  unfold Fixed.addRef
  apply ensureScheduled_inv
  · intro hf; rw [ej, eu]; exact h2 (ef ▸ hf)
  · exact attachRef_cover s r h3

theorem rebuild_frame (s : St) :
    (Fixed.rebuild s).batch = s.batch ∧ (Fixed.rebuild s).flag = s.flag ∧
    (Fixed.rebuild s).jobs = s.jobs ∧ (Fixed.rebuild s).upc = s.upc := by
  unfold Fixed.rebuild
  suffices ∀ (l : List Nat) (acc : St), acc.batch = s.batch → acc.flag = s.flag → acc.jobs = s.jobs →
      acc.upc = s.upc →
      let t := l.foldl (fun acc r => Fixed.attachRef acc r false) acc
      t.batch = s.batch ∧ t.flag = s.flag ∧ t.jobs = s.jobs ∧ t.upc = s.upc from
    this _ _ rfl rfl rfl rfl
  intro l
  induction l with
  | nil => intro acc h1 h2 h3 h4; exact ⟨h1, h2, h3, h4⟩
  | cons r rs ih =>
    intro acc h1 h2 h3 h4
    obtain ⟨ef, ej, eu, _, eb⟩ := attachRef_frame acc r false
    exact ih _ (by rw [eb rfl]; exact h1) (by rw [ef]; exact h2) (by rw [ej]; exact h3) (by rw [eu]; exact h4)

theorem step_inv (c : Cfg) (s : St) (hI : Inv s) (p : String × St) (hp : p ∈ Fixed.next false c s) :
    Inv p.2 := by
  obtain ⟨h1, h2, h3⟩ := hI
  unfold Fixed.next at hp
  rcases List.mem_append.mp hp with hp | hp
  · -- search thread
    unfold Fixed.sStep at hp
    split at hp
    · obtain ⟨eb, ef, ej, eu, er, ev⟩ := searchStarts_frame c s p hp
      exact ⟨by rw [eb, ef]; exact h1, by rw [ef, ej, eu]; exact h2, by rw [er, ev, eb]; exact h3⟩
    · split at hp
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        exact addRef_inv s _ ⟨h1, h2, h3⟩
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        exact ⟨h1, h2, h3⟩
    · split at hp
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        apply ensureScheduled_inv
        · exact h2
        · intro n hn; simp [rootsVals_clear] at hn
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        exact ⟨h1, h2, h3⟩
    · simp at hp
  · -- UI thread
    unfold Fixed.uStep at hp
    split at hp
    · simp only [List.mem_append] at hp
      rcases hp with (hp | hp) | hp
      · split at hp
        · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
          refine ⟨fun h => absurd rfl h, fun _ => Or.inr rfl, fun n hn => Or.inl hn⟩
        · simp at hp
      · split at hp
        · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
          refine ⟨h1, fun hf => Or.inl ((h2 hf).resolve_right (by intro h; simp_all)), h3⟩
        · simp at hp
      · split at hp
        · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
          refine ⟨h1, fun hf => Or.inl ((h2 hf).resolve_right (by intro h; simp_all)), h3⟩
        · simp at hp
    · split at hp
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        refine ⟨fun h => absurd (List.isEmpty_iff.mp (by assumption)) h, fun h => by simp at h, h3⟩
      · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
        exact ⟨h1, fun _ => Or.inl (by simp), h3⟩
    · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
      refine ⟨fun h => absurd rfl h, fun hf => Or.inl ((h2 hf).resolve_right (by intro h; simp_all)),
        fun n hn => by simp [rootsVals_clear] at hn⟩
    · simp only [List.mem_cons, List.not_mem_nil, or_false] at hp; subst hp
      obtain ⟨eb, ef, ej, _⟩ := rebuild_frame s
      refine ⟨fun h => ?_, fun hf => ?_, fun n hn => Or.inl hn⟩
      · simp only [eb, ef] at h ⊢; exact h1 h
      · simp only [ef, ej] at hf ⊢
        exact Or.inl ((h2 hf).resolve_right (by intro h; simp_all))
    · simp at hp

theorem reach_inv (c : Cfg) (s : St) (h : Reach (Fixed.next false c) (St.init c) s) : Inv s := by
  induction h with
  | init => exact init_inv c
  | step _ hmem ih => exact step_inv c _ ih _ hmem

/-- Main theorem (all sizes): in the fixed design, whenever no UIUpdater is scheduled or
    running, every root node in rootNodes is shown in the viewer. -/
theorem no_lost_root (c : Cfg) (s : St) (h : Reach (Fixed.next false c) (St.init c) s)
    (hj : s.jobs = 0) (hu : s.upc = .idle) : ∀ n ∈ rootsVals s.roots, n ∈ s.viewer := by
  obtain ⟨h1, h2, h3⟩ := reach_inv c s h
  have hf : s.flag = false := by
    cases hfl : s.flag
    · rfl
    · rcases h2 hfl with h | h
      · omega
      · rw [hu] at h; cases h
  have hb : s.batch = [] := by
    cases hb : s.batch with
    | nil => rfl
    | cons x xs =>
      have := h1 (by rw [hb]; simp)
      rw [this] at hf; cases hf
  intro n hn
  rcases h3 n hn with h | h
  · exact h
  · rw [hb] at h; cases h

end FindRefs.Proof
