/-!
# Release / snapshot pipeline model (release.yml, snapshot.yml, publish/cleanup scripts, pom baseline)

A small, code-faithful state machine. Every step is annotated with the file:line it models.
Configuration flags switch individual minimal fixes on; `Cfg.faithful` is the code as written.
-/
namespace Pipeline

inductive Br | master | maint | feat
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

/-- Major version of each branch's release line (master = 19.x, maintenance = 18.x;
    `feat` is a topic branch cut from master). -/
def Br.line : Br → Nat
  | .maint => 18
  | _ => 19

def Br.name : Br → String
  | .master => "master" | .maint => "v18.x" | .feat => "feature"

/-- `maj.0.pat`; the minor component is never bumped in this model (patch releases only). -/
structure Ver where
  maj : Nat
  pat : Nat
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

def Ver.lt (a b : Ver) : Bool := a.maj < b.maj || (a.maj == b.maj && a.pat < b.pat)
def Ver.bump (v : Ver) : Ver := { v with pat := v.pat + 1 }
def Ver.max (a b : Ver) : Ver := if a.lt b then b else a
def Ver.show (v : Ver) : String := s!"v{v.maj}.0.{v.pat}"

structure Commit where
  sha : Nat
  parent : Option Nat
  ver : Ver            -- feature.xml / bundle version line at this commit
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

/-- A snapshot directory p2/snapshots/<sha>/ (the branch is ghost info: which branch built it). -/
structure Snap where
  sha : Nat
  br : Br
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

/-- gh-pages contents. `snaps` is ordered newest-first by `.created` (publish-p2-ghpages.sh:64). -/
structure Gh where
  snaps : List Snap
  latest : Option Snap          -- p2/snapshots/latest composite child
  rels : List (Ver × Nat)       -- p2/releases/<v>/ with the commit whose snapshot was copied
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

inductive Kind | snap (b : Br) | rel (b : Br)
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

def Kind.show : Kind → String
  | .snap b => s!"snapshot({b.name})" | .rel b => s!"release({b.name})"

/-- A workflow run. `pc` indexes the step list of its kind. -/
structure Run where
  kind : Kind
  pc : Nat := 0
  sha : Nat := 0
  fv : Ver := default
  next : Ver := default
  view : Option Gh := none      -- the run's local gh-pages checkout
  resumed : Bool := false       -- started by "Re-run failed jobs"
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

/-- One GitHub concurrency group: at most one running and one pending run. -/
structure Slot where
  running : Option Run := none
  pending : Option Run := none
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

/-- A failed release run whose create_tag job succeeded: "Re-run failed jobs" restarts
    the publish job with the saved outputs (tag, snapshot_sha). -/
structure FailedRec where
  br : Br
  next : Ver
  sha : Nat
  fv : Ver
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

structure St where
  commits : List Commit
  hMaster : Nat
  hMaint : Nat
  hFeat : Nat
  tags : List (Ver × Nat)
  gh : Gh
  ghRels : List Ver             -- GitHub release objects (gh release create)
  slots : List Slot
  failed : List FailedRec
  pushes : Nat
  -- ghost
  snapFail : Bool := false      -- some snapshot run failed (env or gate)
  pubRels : List Ver := []      -- every release version ever pushed into gh-pages
  deriving DecidableEq, BEq, Hashable, Repr, Inhabited

inductive GroupMode | single | perBranch
  deriving DecidableEq, Repr
inductive WriteMode | cas | regen | force
  deriving DecidableEq, Repr

structure Cfg where
  keep : Nat := 2
  maxPush : Nat := 2
  /-- fix A: publish the release repo first, push the tag last, `gh release create` idempotent;
      a later run resumes an untagged release repo (tags the commit it was published from) -/
  tagLast : Bool := false
  /-- fix B: only master snapshots move p2/snapshots/latest -/
  latestMasterOnly : Bool := false
  /-- fix C: `git tag --list 'v*' --merged HEAD` -/
  mergedTags : Bool := false
  /-- fix D: baseline = latest release of the branch's own major line -/
  lineBaseline : Bool := false
  /-- fix E: concurrency group per branch (+ one for releases) with regenerate-on-retry writes -/
  groups : GroupMode := .single
  write : WriteMode := .cas
  /-- fix F: cleanup never deletes the newest snapshot of a branch -/
  cleanupProtect : Bool := false
  /-- model manual snapshot dispatches of the topic branch (needed for P2 on the faithful model) -/
  featDispatch : Bool := true
  deriving Repr

/-! ## Helpers -/

def St.head (s : St) : Br → Nat
  | .master => s.hMaster | .maint => s.hMaint | .feat => s.hFeat

def St.setHead (s : St) (b : Br) (h : Nat) : St :=
  match b with
  | .master => { s with hMaster := h } | .maint => { s with hMaint := h } | .feat => { s with hFeat := h }

def St.verOf (s : St) (sha : Nat) : Ver :=
  match s.commits.find? (·.sha == sha) with
  | some c => c.ver | none => default

/-- ancestors-or-self of a commit (fuel = number of commits). -/
def ancestors (cs : List Commit) (sha : Nat) : List Nat :=
  go cs.length sha
where
  go : Nat → Nat → List Nat
  | 0, _ => []
  | n + 1, x => x :: match (cs.find? (·.sha == x)).bind (·.parent) with
      | some p => go n p
      | none => []

def maxVer? : List Ver → Option Ver
  | [] => none
  | v :: vs => some (vs.foldl Ver.max v)

/-- sorted insert/replace into the release list (canonical state form). -/
def insRel (v : Ver) (c : Nat) : List (Ver × Nat) → List (Ver × Nat)
  | [] => [(v, c)]
  | (w, d) :: r =>
    if w == v then (v, c) :: r
    else if v.lt w then (v, c) :: (w, d) :: r
    else (w, d) :: insRel v c r

def insTag := insRel

def insVer (v : Ver) : List Ver → List Ver
  | [] => [v]
  | w :: r => if w == v then w :: r else if v.lt w then v :: w :: r else w :: insVer v r

/-! ## gh-pages operations (publish-p2-ghpages.sh, cleanup-p2-snapshots.sh) -/

/-- snapshot mode, publish-p2-ghpages.sh:58-64,72: rm -rf + copy + new .created (moves to newest);
    latest composite always rewritten to this snapshot (line 72, unconditional). -/
def opPublishSnap (cfg : Cfg) (sha : Nat) (b : Br) (g : Gh) : Gh :=
  let sn : Snap := { sha, br := b }
  { g with
    snaps := sn :: g.snaps.filter (·.sha != sha)
    latest := if cfg.latestMasterOnly && b != .master then g.latest else some sn }

/-- release mode, publish-p2-ghpages.sh:52-57,69-70 (releases/latest = highest dir, derived). -/
def opPublishRel (v : Ver) (sha : Nat) (g : Gh) : Gh :=
  { g with rels := insRel v sha g.rels }

/-- cleanup-p2-snapshots.sh:15-26: keep the KEEP newest by .created, delete the rest. -/
def opCleanup (cfg : Cfg) (g : Gh) : Gh :=
  let kept := g.snaps.take cfg.keep
  let rest := g.snaps.drop cfg.keep
  let rest' := if cfg.cleanupProtect then
      -- fix F: also keep the newest snapshot of each branch
      rest.filter (fun s => g.snaps.find? (·.br == s.br) == some s)
    else []
  { g with snaps := kept ++ rest' }

/-- baseline the Tycho gate compares against (pom.xml:64, 250-261): releases/latest. -/
def baseline (cfg : Cfg) (b : Br) (g : Gh) : Option (Ver × Nat) :=
  let rs := if cfg.lineBaseline then g.rels.filter (·.1.maj == b.line) else g.rels
  match maxVer? (rs.map (·.1)) with
  | none => none
  | some v => (rs.find? (·.1 == v)).map id

/-- CompareWithBaselineMojo (tycho-p2-extras 5.0.4, bytecode 421-483 and 730-826):
    reactor < baseline -> "Version has moved backwards" (onIllegalVersion default=fail);
    same x.y.z but different build -> "Only qualifier changed"/"different content" -> fail. -/
def gatePasses (cfg : Cfg) (b : Br) (sha : Nat) (fv : Ver) (g : Gh) : Bool :=
  match baseline cfg b g with
  | none => true
  | some (bv, bsha) => !(fv.lt bv) && !(fv == bv && sha != bsha)

/-! ## Concurrency groups -/

def groupKey (cfg : Cfg) : Kind → Nat
  | k => match cfg.groups, k with
    | .single, _ => 0
    | .perBranch, .snap .master => 0
    | .perBranch, .snap .maint => 1
    | .perBranch, .snap .feat => 2
    | .perBranch, .rel _ => 3

def numSlots (cfg : Cfg) : Nat := match cfg.groups with | .single => 1 | .perBranch => 4

/-- GitHub semantics: a new run in a busy group becomes pending; an older pending run in the
    same group is cancelled (snapshot.yml:13-15, release.yml:21-23, cancel-in-progress: false). -/
def St.trigger (cfg : Cfg) (s : St) (r : Run) : St :=
  let k := groupKey cfg r.kind
  { s with slots := s.slots.modify k fun sl =>
      match sl.running with
      | none => { running := some r, pending := none }
      | some _ => { sl with pending := some r } }

/-! ## Step semantics -/

/-- Release steps. Read-only steps are merged into one atomic step (see NOTES.md, A3).
    `prepare` = release.yml:36-104 (checkout branch tip, LATEST tag + bump, gh-pages checkout,
                verify snapshot dir, verify feature.xml versions);
    `tag`     = release.yml:106-112; `publish` = release.yml:123-135 (gh-pages checkout +
                publish-p2-ghpages.sh release mode); `ghrel` = release.yml:137-148.
    Faithful: create_tag job = [prepare, tag], publish job = [publish, ghrel].
    Fix A: [prepare, publish, tag, ghrel]. -/
inductive RStep | prepare | tag | publish | ghrel
  deriving DecidableEq, Repr

def relSteps (cfg : Cfg) : List RStep :=
  if cfg.tagLast then [.prepare, .publish, .tag, .ghrel]
  else [.prepare, .tag, .publish, .ghrel]

/-- index of the first step of the publish job (for "Re-run failed jobs"). -/
def publishJobStart : Nat := 2

/-- Snapshot steps. `build` = snapshot.yml:24-99 (checkout branch tip, sha, mvn verify incl. the
    baseline gate, gh-pages checkout); `publish` = :113-117; `cleanup` = :119-122. -/
inductive SStep | build | publish | cleanup
  deriving DecidableEq, Repr

def snapSteps : List SStep := [.build, .publish, .cleanup]

/-- gh-pages write with the configured push semantics.
    cas   = faithful: `git push`, on rejection `git rebase origin/gh-pages`, which conflicts on the
            always-regenerated index.html -> set -e aborts (publish-p2-ghpages.sh:111-124).
    regen = fix E: on rejection refetch and re-run the whole script on the fresh branch.
    force = planted bug: push the locally computed tree regardless of the remote. -/
def writeGh (cfg : Cfg) (s : St) (view : Gh) (op : Gh → Gh) : Option Gh :=
  match cfg.write with
  | .cas => if s.gh == view then some (op view) else none
  | .regen => some (op s.gh)
  | .force => some (op view)

/-- remove the slot's running run and promote the pending one. -/
def St.finish (s : St) (k : Nat) : St :=
  { s with slots := s.slots.modify k fun sl => { running := sl.pending, pending := none } }

def St.setRun (s : St) (k : Nat) (r : Run) : St :=
  { s with slots := s.slots.modify k fun sl => { sl with running := some r } }

/-- the run fails (environmental or a failing check): bookkeeping for reruns. -/
def St.failRun (cfg : Cfg) (s : St) (k : Nat) (r : Run) : St :=
  let s := s.finish k
  match r.kind with
  | .snap _ => { s with snapFail := true }
  | .rel b =>
    if !cfg.tagLast && r.pc ≥ publishJobStart then
      let fr : FailedRec := { br := b, next := r.next, sha := r.sha, fv := r.fv }
      if s.failed.contains fr then s else { s with failed := s.failed ++ [fr] }
    else s

/-- successful end of a release run: clears its "Re-run failed jobs" record. -/
def St.finishRel (s : St) (k : Nat) (r : Run) : St :=
  let s := { s with failed := s.failed.filter (fun f => !(f.next == r.next && f.sha == r.sha)) }
  s.finish k

/-- Execute the current step of the running run in slot `k` (success path; `none` = gate/check
    failure, which the caller turns into a failed run). -/
def stepRun (cfg : Cfg) (s : St) (k : Nat) (r : Run) : Option St :=
  match r.kind with
  | .snap b =>
    match snapSteps[r.pc]? with
    | some .build =>
      -- snapshot.yml:24-27 ref = branch -> current tip; :34 sha; :67-72 mvn verify incl.
      -- compare-version-with-baselines against releases/latest; :92-99 gh-pages checkout
      let h := s.head b
      let fv := s.verOf h
      if gatePasses cfg b h fv s.gh then
        some (s.setRun k { r with pc := r.pc + 1, sha := h, fv,
                                  view := if cfg.write == WriteMode.regen then none else some s.gh })
      else none
    | some .publish =>    -- snapshot.yml:113-117 -> publish-p2-ghpages.sh snapshot mode
      let v := r.view.getD s.gh
      (writeGh cfg s v (opPublishSnap cfg r.sha b)).map fun g =>
        ({ s with gh := g }).setRun k
          { r with pc := r.pc + 1,
                   view := if cfg.write == WriteMode.regen then none else some (opPublishSnap cfg r.sha b v) }
    | some .cleanup =>    -- snapshot.yml:119-122 -> cleanup-p2-snapshots.sh (same local clone)
      let v := r.view.getD s.gh
      (writeGh cfg s v (opCleanup cfg)).map fun g => ({ s with gh := g }).finish k
    | none => some (s.finish k)
  | .rel b =>
    match (relSteps cfg)[r.pc]? with
    | some .prepare =>
      -- release.yml:36-39 checkout tip; :44-57 LATEST over all tags (or --merged, fix C) + bump;
      -- :74-85 snapshot dir must exist; :87-104 every feature.xml == next
      let h := s.head b
      let visible :=
        if cfg.mergedTags then s.tags.filter (fun t => (ancestors s.commits h).contains t.2) else s.tags
      let latest := (maxVer? (visible.map (·.1))).getD { maj := 0, pat := 0 }
      let r := { r with pc := r.pc + 1, sha := h, fv := s.verOf h, next := latest.bump }
      -- fix A: an untagged p2/releases/<next>/ left by a failed run is resumed: tag the commit it
      -- was published from (skip publishing), whatever the branch tip is now
      match (if cfg.tagLast then s.gh.rels.find? (·.1 == r.next) else none) with
      | some (_, c) => some (s.setRun k { r with pc := 2, sha := c, fv := r.next })
      | none =>
      if (s.gh.snaps.any (·.sha == r.sha)) && r.fv == r.next then some (s.setRun k r)
      else
        -- keep the computed version visible to P3 even though the run fails here
        none
    | some .tag =>        -- release.yml:106-112 git tag + push (fails if the tag exists)
      if s.tags.any (·.1 == r.next) then none
      else some (({ s with tags := insTag r.next r.sha s.tags }).setRun k { r with pc := r.pc + 1 })
    | some .publish =>    -- release.yml:123-135 fresh gh-pages checkout + publish-p2-ghpages.sh
                          -- release mode (cp -r fails under set -e if the snapshot dir is gone, :57)
      if !(s.gh.snaps.any (·.sha == r.sha)) then none
      else (writeGh cfg s s.gh (opPublishRel r.next r.sha)).map fun g =>
        ({ s with gh := g, pubRels := insVer r.next s.pubRels }).setRun k { r with pc := r.pc + 1 }
    | some .ghrel =>      -- release.yml:137-148 gh release create (errors if it exists)
      if s.ghRels.contains r.next then
        if cfg.tagLast then some (s.finishRel k r) else none
      else some (({ s with ghRels := insVer r.next s.ghRels }).finishRel k r)
    | none => some (s.finish k)

/-- P3 needs the version a release run computes even when `prepare` then fails: this is the value
    `prepare` would compute (release.yml:44-57). -/
def computedNext (cfg : Cfg) (s : St) (b : Br) : Ver :=
  let h := s.head b
  let visible :=
    if cfg.mergedTags then s.tags.filter (fun t => (ancestors s.commits h).contains t.2) else s.tags
  ((maxVer? (visible.map (·.1))).getD { maj := 0, pat := 0 }).bump

/-! ## Environment actions -/

inductive PushKind | change | bump
  deriving DecidableEq, Repr

/-- the developer bumps only after the current version was tagged (release process). -/
def canBump (s : St) (b : Br) : Bool :=
  s.tags.any (·.1 == s.verOf (s.head b))

def nextSha (s : St) : Nat := s.commits.length

def St.push (cfg : Cfg) (s : St) (b : Br) (pk : PushKind) : St :=
  let h := s.head b
  let v := s.verOf h
  let c : Commit := { sha := nextSha s, parent := some h, ver := if pk == PushKind.bump then v.bump else v }
  let s := ({ s with commits := s.commits ++ [c], pushes := s.pushes + 1 }).setHead b c.sha
  -- snapshot.yml:10-11 push trigger on master and v[0-9]* only
  if b == .feat then s else s.trigger cfg { kind := .snap b }

abbrev Label := String

def stepName (cfg : Cfg) (s : St) (r : Run) : String :=
  match r.kind with
  | .snap b => match snapSteps[r.pc]? with
    | some .build => s!"checkout tip (sha {s.head b}) + mvn verify with baseline gate"
    | some .publish => s!"publish snapshot {r.sha}"
    | some .cleanup => "cleanup old snapshots" | none => "end"
  | .rel b => match (relSteps cfg)[r.pc]? with
    | some .prepare => s!"checkout tip (sha {s.head b}), next = {(computedNext cfg s b).show}, verify snapshot + feature.xml"
    | some .tag => s!"create + push tag {r.next.show}"
    | some .publish => s!"publish p2/releases/{r.next.show} from snapshot {r.sha}"
    | some .ghrel => s!"gh release create {r.next.show}" | none => "end"

/-- Partial-order reduction (NOTES.md A3): step `pc` is executed atomically together with the next
    step when nothing another run can do in between changes the outcome; the "first half succeeded,
    second half failed" outcome is kept. Faithful single group: tag+nothing, publish+ghrel,
    publish+cleanup are fused. -/
def fused (cfg : Cfg) (r : Run) : Bool :=
  match r.kind with
  | .snap _ => snapSteps[r.pc]? == some SStep.publish
  | .rel _ => match (relSteps cfg)[r.pc]?, (relSteps cfg)[r.pc + 1]? with
    | some .publish, some .ghrel => true
    | some .tag, some .ghrel => true
    | _, _ => false

/-- env failure is only modelled where it differs from a check failure / not dispatching:
    everything except the read-only release `prepare` step. -/
def envFailHere (cfg : Cfg) (r : Run) : Bool :=
  match r.kind with
  | .snap _ => true
  | .rel _ => (relSteps cfg)[r.pc]? != some RStep.prepare

def runOutcomes (cfg : Cfg) (s : St) (k : Nat) (r : Run) : List (Label × St) := Id.run do
  let nm := stepName cfg s r
  let pre := r.kind.show
  let mut out : List (Label × St) := []
  if envFailHere cfg r then
    out := (s!"{pre}: {nm} fails (environment/flake)", s.failRun cfg k r) :: out
  match stepRun cfg s k r with
  | none => out := (s!"{pre}: {nm} FAILS (check)", s.failRun cfg k r) :: out
  | some s' =>
    if fused cfg r then
      match (s'.slots[k]?).bind (·.running) with
      | some r' =>
        let nm2 := stepName cfg s' r'
        out := (s!"{pre}: {nm} ok; {nm2} fails (environment/flake)", s'.failRun cfg k r') :: out
        match stepRun cfg s' k r' with
        | none => out := (s!"{pre}: {nm} ok; {nm2} FAILS (check)", s'.failRun cfg k r') :: out
        | some s'' => out := (s!"{pre}: {nm} ok; {nm2} ok", s'') :: out
      | none => out := (s!"{pre}: {nm} ok", s') :: out
    else
      out := (s!"{pre}: {nm} ok", s') :: out
  return out


def succs (cfg : Cfg) (s : St) : List (Label × St) := Id.run do
  let mut out : List (Label × St) := []
  -- developer pushes
  if s.pushes < cfg.maxPush then
    for b in [Br.master, .maint] do
      out := (s!"push {b.name} (change)", s.push cfg b .change) :: out
      if canBump s b then
        out := (s!"push {b.name} (bump feature/bundle versions)", s.push cfg b .bump) :: out
  -- manual dispatches (release.yml / snapshot.yml workflow_dispatch; also "Re-run all jobs")
  for b in [Br.master, .maint] do
    out := (s!"dispatch release branch={b.name} bump=patch", s.trigger cfg { kind := .rel b }) :: out
  for b in (if cfg.featDispatch then [Br.master, .maint, .feat] else [Br.master, .maint]) do
    out := (s!"dispatch snapshot branch={b.name}", s.trigger cfg { kind := .snap b }) :: out
  -- "Re-run failed jobs" of a release whose create_tag job succeeded
  for f in s.failed do
    out := (s!"re-run failed jobs of release {f.next.show} (publish job, sha {f.sha})",
      s.trigger cfg { kind := .rel f.br, pc := publishJobStart, sha := f.sha, fv := f.fv,
                      next := f.next, resumed := true }) :: out
  -- running runs: step or fail
  for k in List.range (numSlots cfg) do
    match (s.slots[k]?).bind (·.running) with
    | none => pure ()
    | some r =>
      for (l, t) in runOutcomes cfg s k r do
        out := (l, t) :: out
  return out.reverse

/-! ## Initial state (mirrors upstream today: releases 18.0/19.0 published, master at the next
    patch version with a snapshot, a maintenance branch cut from the 18.0 tag with a bump). -/

def init (cfg : Cfg) : St :=
  { commits := [
      { sha := 0, parent := none, ver := ⟨18, 0⟩ },     -- v18.0.0 (common history)
      { sha := 1, parent := some 0, ver := ⟨19, 0⟩ },   -- v19.0.0 (master)
      { sha := 2, parent := some 1, ver := ⟨19, 1⟩ },   -- master head
      { sha := 3, parent := some 0, ver := ⟨18, 1⟩ },   -- maintenance head (bumped to 18.0.1)
      { sha := 4, parent := some 2, ver := ⟨19, 1⟩ } ]  -- feature branch head
    hMaster := 2, hMaint := 3, hFeat := 4
    tags := [(⟨18, 0⟩, 0), (⟨19, 0⟩, 1)]
    gh := { snaps := [{ sha := 2, br := .master }], latest := some { sha := 2, br := .master },
            rels := [(⟨18, 0⟩, 0), (⟨19, 0⟩, 1)] }
    ghRels := [⟨18, 0⟩, ⟨19, 0⟩]
    slots := List.replicate (numSlots cfg) {}
    failed := []
    pushes := 0
    pubRels := [⟨18, 0⟩, ⟨19, 0⟩] }

/-! ## Configurations -/

def Cfg.faithful : Cfg := {}
def Cfg.fixed : Cfg :=
  { tagLast := true, latestMasterOnly := true, mergedTags := true, lineBaseline := true,
    groups := .perBranch, write := .regen, cleanupProtect := true, featDispatch := false }
/-- planted obvious bug: per-branch groups but a blind force push of the local tree. -/
def Cfg.planted : Cfg := { Cfg.fixed with write := .force }

end Pipeline
