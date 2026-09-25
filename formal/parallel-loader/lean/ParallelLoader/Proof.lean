import ParallelLoader.Model

/-!
Inductive-invariant proof of P1 (bookkeeping) for the timeout-fixed model, for ALL sizes:
any number of URIs, threads, timeouts, interrupts and every queue kind.
-/
namespace ParallelLoader

abbrev live (j : Job) : Bool := j != .consumed

theorem count_set_keep {l : List Job} {i : Nat} {x y : Job} (hx : l[i]? = some x)
    (hxn : x ≠ .consumed) (hyn : y ≠ .consumed) : countJ live (l.set i y) = countJ live l := by
  induction l generalizing i with
  | nil => simp at hx
  | cons a l ih =>
    cases i with
    | zero =>
      simp at hx; subst hx
      simp [countJ, hxn, hyn]
    | succ i =>
      simp at hx
      have := ih hx
      simp only [countJ] at this ⊢
      simp [List.filter_cons]; split <;> simp_all

theorem count_set_consume {l : List Job} {i : Nat} {x : Job} (hx : l[i]? = some x)
    (hxn : x ≠ .consumed) : countJ live (l.set i .consumed) + 1 = countJ live l := by
  induction l generalizing i with
  | nil => simp at hx
  | cons a l ih =>
    cases i with
    | zero =>
      simp at hx; subst hx
      simp [countJ, hxn]
    | succ i =>
      simp at hx
      have := ih hx
      simp only [countJ] at this ⊢
      simp [List.filter_cons]; split <;> simp_all <;> omega

/-- Results in the queue are exactly the jobs in state `queued`, without duplicates. -/
def RqInv (s : St) : Prop := s.rq.Nodup ∧ ∀ i, i ∈ s.rq ↔ s.jobs[i]? = some .queued

def Inv (s : St) : Prop :=
  (terminal s = true ∧ s.opCancelled = true) ∨
  (terminal s = false ∧ s.opCancelled = false ∧ s.toProcess = (countJ live s.jobs : Int) ∧ RqInv s)

theorem rq_set_other {jobs : List Job} {rq : List Nat} {j : Nat} {x y : Job}
    (h : ∀ i, i ∈ rq ↔ jobs[i]? = some .queued) (hx : jobs[j]? = some x)
    (hxq : x ≠ .queued) (hyq : y ≠ .queued) :
    ∀ i, i ∈ rq ↔ (jobs.set j y)[i]? = some .queued := by
  intro i
  rw [List.getElem?_set, h i]
  by_cases hji : j = i
  · subst hji
    obtain ⟨hlt, hget⟩ := List.getElem?_eq_some_iff.1 hx
    simp [hlt, hget, hxq, hyq]
  · simp [hji]

theorem inv_init (c : Cfg) (hv : c.variant ≠ .plantedNoIncrement) : Inv (init c) := by
  right
  refine ⟨rfl, rfl, ?_, ?_, ?_⟩
  · have hv' : (c.variant == .plantedNoIncrement) = false := by simpa using hv
    simp [init, hv', countJ]
  · simp [init]
  · intro i
    simp [init, List.getElem?_replicate]

theorem getElem?_of_findIdx {l : List Job} {i : Nat} (h : l.findIdx? (· == .pending) = some i) :
    l[i]? = some .pending := by
  rw [List.findIdx?_eq_some_iff_getElem] at h
  obtain ⟨hi, hp, -⟩ := h
  simp at hp
  simp [hi, hp]

theorem worker_inv {c : Cfg} {s t : St} {a : Act} (hs : Inv s) (h : (a, t) ∈ workerSteps c s) : Inv t := by
  unfold workerSteps at h
  rcases hs with ⟨-, hoc⟩ | ⟨hterm, hoc, htp, hnd, hrq⟩
  · simp [hoc] at h
  simp only [hoc, Bool.false_eq_true, ↓reduceIte, List.mem_append, List.mem_filterMap,
    List.mem_range] at h
  rcases h with (h | h) | h
  · -- start
    split at h
    · rename_i i hi
      split at h
      · simp at h; obtain ⟨-, rfl⟩ := h
        have hx := getElem?_of_findIdx hi
        right
        refine ⟨hterm, rfl, ?_, hnd, rq_set_other hrq hx (by decide) (by decide)⟩
        simp only; rw [count_set_keep hx (by decide) (by decide)]; exact htp
      · simp at h
    · simp at h
  · -- loaded
    obtain ⟨i, -, hi⟩ := h
    split at hi
    · rename_i hx; simp at hi; obtain ⟨-, rfl⟩ := hi
      have hx : s.jobs[i]? = some .loading := by simpa using hx
      right
      refine ⟨hterm, rfl, ?_, hnd, rq_set_other hrq hx (by decide) (by decide)⟩
      simp only; rw [count_set_keep hx (by decide) (by decide)]; exact htp
    · simp at hi
  · -- put
    obtain ⟨i, -, hi⟩ := h
    split at hi
    · rename_i hx; simp at hi; obtain ⟨-, rfl⟩ := hi
      have hx : s.jobs[i]? = some .putting := by simp at hx; exact hx.1
      have hlen : i < s.jobs.length := by
        rcases Nat.lt_or_ge i s.jobs.length with h' | h'
        · exact h'
        · simp [List.getElem?_eq_none h'] at hx
      have hnotin : i ∉ s.rq := by rw [hrq i, hx]; simp
      right
      refine ⟨hterm, rfl, ?_, ?_, ?_⟩
      · simp only; rw [count_set_keep hx (by decide) (by decide)]; exact htp
      · simp only; rw [List.nodup_append]; refine ⟨hnd, by simp, ?_⟩
        intro a ha b hb; simp at hb; subst hb; intro hab; subst hab; exact hnotin ha
      · intro k
        simp only [List.mem_append, List.mem_singleton, List.getElem?_set]
        by_cases hik : i = k
        · subst hik; simp [hlen]
        · rw [hrq k]; simp [hik, Ne.symm hik]
    · simp at hi

theorem env_inv {c : Cfg} {s t : St} {a : Act} (hs : Inv s) (h : (a, t) ∈ envSteps c s) : Inv t := by
  unfold envSteps at h
  rcases hs with ⟨hterm, -⟩ | ⟨hterm, hoc, htp, hnd, hrq⟩
  · simp [hterm] at h
  simp only [hterm, Bool.false_eq_true, ↓reduceIte, List.mem_append] at h
  rcases h with h | h
  · split at h <;> simp at h
    obtain ⟨-, rfl⟩ := h
    exact Or.inr ⟨by simpa [terminal] using hterm, hoc, htp, hnd, hrq⟩
  · split at h <;> simp at h
    obtain ⟨-, rfl⟩ := h
    exact Or.inr ⟨by simpa [terminal] using hterm, hoc, htp, hnd, hrq⟩

theorem afterExc_inv {s : St} (hterm : terminal s = false) (hoc : s.opCancelled = false)
    (htp : s.toProcess = (countJ live s.jobs : Int)) (hrq : RqInv s) : Inv (afterExc s) := by
  unfold afterExc
  split
  · left; simp [terminal]
  · right; exact ⟨by simp [terminal], hoc, htp, hrq⟩

theorem consume_inv {s : St} {i : Nat} {x : Job} (hoc : s.opCancelled = false)
    (hx : s.jobs[i]? = some x) (hxc : x ≠ .consumed) (hxq : x ≠ .queued)
    (htp : s.toProcess = (countJ live s.jobs : Int)) (hrq : RqInv s) : Inv (consume s i) := by
  right
  obtain ⟨hnd, hrq⟩ := hrq
  refine ⟨by simp [consume, terminal], hoc, ?_, hnd, rq_set_other hrq hx hxq (by decide)⟩
  have := count_set_consume hx hxc
  simp only [consume]; omega

theorem builder_inv {c : Cfg} {s t : St} {a : Act} (hv : c.variant.fixesTimeout = true)
    (hs : Inv s) (h : (a, t) ∈ builderSteps c s) : Inv t := by
  rcases hs with ⟨hterm, -⟩ | ⟨hterm, hoc, htp, hnd, hrq⟩
  · unfold builderSteps at h
    split at h <;> simp_all [terminal]
  unfold builderSteps at h
  split at h
  · simp at h
  · simp at h
  · -- head
    split at h
    · simp at h; obtain ⟨-, rfl⟩ := h; left; simp [terminal]
    · split at h
      · simp at h; obtain ⟨-, rfl⟩ := h; left; simp [terminal]
      · simp at h; obtain ⟨-, rfl⟩ := h
        right; exact ⟨by simp [terminal], hoc, htp, hnd, hrq⟩
  · -- poll
    have hto : ∀ {a t}, (a, t) ∈ (if s.timeoutsLeft > 0 then [(Act.pollTimeout, timedOut c s)] else []) → Inv t := by
      intro a t h
      split at h
      · simp at h; obtain ⟨-, rfl⟩ := h
        unfold timedOut; simp only [hv, ↓reduceIte]
        exact afterExc_inv (by simpa [terminal] using hterm) hoc htp ⟨hnd, hrq⟩
      · simp at h
    cases hq : c.qk with
    | sync =>
      simp only [hq] at h
      rw [apply_ite (fun l => (a, t) ∈ l)] at h
      split at h
      · simp only [List.mem_filterMap, List.mem_range] at h
        obtain ⟨i, -, hi⟩ := h
        split at hi
        · rename_i hx; simp at hi; obtain ⟨-, rfl⟩ := hi
          have hx : s.jobs[i]? = some .putting := by simpa using hx
          exact consume_inv hoc hx (by decide) (by decide) htp ⟨hnd, hrq⟩
        · simp at hi
      · rw [apply_ite (fun l => (a, t) ∈ l)] at h
        split at h
        · simp at h; obtain ⟨-, rfl⟩ := h
          exact afterExc_inv (by simpa [terminal] using hterm) hoc htp ⟨hnd, hrq⟩
        · exact hto h
    | unbounded | bounded _ =>
      simp only [hq] at h
      rw [apply_ite (fun l => (a, t) ∈ l)] at h
      split at h
      · simp at h; obtain ⟨-, rfl⟩ := h
        exact afterExc_inv (by simpa [terminal] using hterm) hoc htp ⟨hnd, hrq⟩
      · cases hrqeq : s.rq with
        | nil => simp only [hrqeq] at h; exact hto h
        | cons i rest =>
          simp only [hrqeq] at h
          simp at h; obtain ⟨-, rfl⟩ := h
          have hx : s.jobs[i]? = some .queued := (hrq i).1 (by simp [hrqeq])
          have hnd' : rest.Nodup ∧ i ∉ rest := by
            rw [hrqeq] at hnd; exact ⟨(List.nodup_cons.1 hnd).2, (List.nodup_cons.1 hnd).1⟩
          right
          refine ⟨by simp [consume, terminal], hoc, ?_, hnd'.1, ?_⟩
          · have := count_set_consume hx (by decide)
            simp only [consume]; omega
          · intro k
            simp only [consume, List.getElem?_set]
            by_cases hik : i = k
            · subst hik
              obtain ⟨hlt, -⟩ := List.getElem?_eq_some_iff.1 hx
              simp [hnd'.2, hlt]
            · have := hrq k; rw [hrqeq] at this
              simp [hik, Ne.symm hik] at this ⊢; exact this

theorem inv_step {c : Cfg} {s t : St} {a : Act} (hv : c.variant.fixesTimeout = true)
    (hs : Inv s) (h : (a, t) ∈ succ c s) : Inv t := by
  simp only [succ, List.mem_append] at h
  rcases h with (h | h) | h
  · exact builder_inv hv hs h
  · exact worker_inv hs h
  · exact env_inv hs h

/-- Reachability via the model's successor function. -/
inductive Reach (c : Cfg) : St → Prop
  | init : Reach c (init c)
  | step {s t : St} {a : Act} : Reach c s → (a, t) ∈ succ c s → Reach c t

theorem P1_of_inv {s : St} (h : Inv s) : P1 s = true := by
  rcases h with ⟨-, hoc⟩ | ⟨-, -, htp, -⟩
  · simp [P1, hoc]
  · simp [P1, outstanding, htp]

/-- P1 holds in every reachable state of the timeout-fixed model, for every configuration. -/
theorem P1_fixTimeout (c : Cfg) (hv : c.variant.fixesTimeout = true) {s : St} (hr : Reach c s) :
    P1 s = true := by
  apply P1_of_inv
  induction hr with
  | init => exact inv_init c (by cases h : c.variant <;> simp_all [Variant.fixesTimeout])
  | step _ hst ih => exact inv_step hv ih hst

end ParallelLoader

#print axioms ParallelLoader.P1_fixTimeout
