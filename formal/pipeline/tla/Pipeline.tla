------------------------------ MODULE Pipeline ------------------------------
(***************************************************************************)
(* Model of the DDK release / snapshot publishing pipeline:               *)
(*   .github/workflows/snapshot.yml, .github/workflows/release.yml,        *)
(*   .github/scripts/publish-p2-ghpages.sh,                                *)
(*   .github/scripts/cleanup-p2-snapshots.sh,                              *)
(*   ddk-parent/pom.xml (baseline.repo.url / snapshot.repo.url and the     *)
(*   tycho compare-version-with-baselines gate).                           *)
(*                                                                         *)
(* FIX = FALSE models the code as written.  FIX = TRUE applies the         *)
(* minimal fixes listed in NOTES.md.  PLANT injects an obvious bug for the *)
(* sanity check.  Versions are <<minor, patch>> (major is constant; a      *)
(* "major" bump behaves like "minor" for every property checked here).     *)
(***************************************************************************)
EXTENDS Naturals, Sequences, FiniteSets, TLC

CONSTANTS FIX,         \* BOOLEAN: apply the minimal fixes
          PLANT,       \* "none" | "relLatest" | "cleanup"
          KEEP,        \* snapshots kept by cleanup-p2-snapshots.sh (20 in the code)
          NSlots,      \* workflow-run slots
          MaxPush, MaxDispatch, MaxFail, MaxRerun, MaxExpire,
          CheckCalm,   \* allow the "calm" phase used by the liveness checks
          GoalBranch   \* branch the calm-phase maintainer tries to release

VARIABLES commits,   \* Seq of [br, fv]: branch and feature.xml version of each commit
          tip,       \* branch -> head commit
          tags,      \* set of <<version, commit>>  (git tags v*)
          gp,        \* remote gh-pages branch content
          ghRel,     \* versions that have a GitHub release (zip asset)
          runs,      \* slot -> workflow run
          queue,     \* concurrency group -> [running, pending]
          bud,       \* remaining environment budgets (pre-calm)
          calm,      \* calm phase: no more injected failures, maintainer acts helpfully
          goalVer,   \* version the calm maintainer wants to release on GoalBranch
          badVer,    \* history: a run computed a version not derived from its own line
          lostRun,   \* history: a pending run was cancelled by a run of another <<wf, ref>>
          futile,    \* history: a calm-phase "re-run failed jobs" failed again
          pubSnaps,  \* history: snapshots ever published
          evicted,   \* history: snapshots deleted by the cleanup policy
          pubRels,   \* history: release repos ever published
          repairTried \* calm maintainer: incomplete tags already re-dispatched once

vars == <<commits, tip, tags, gp, ghRel, runs, queue, bud, calm, goalVer,
          badVer, lostRun, futile, pubSnaps, evicted, pubRels, repairTried>>

Branches == {"master", "maint"}
Slots == 1..NSlots
MaxCommits == 3 + MaxPush + 1
NoVer == <<0, 0>>
Less(a, b) == a[1] < b[1] \/ (a[1] = b[1] /\ a[2] < b[2])
LEq(a, b) == a = b \/ Less(a, b)
MaxV(S) == IF S = {} THEN NoVer ELSE CHOOSE m \in S : \A x \in S : LEq(x, m)
Bump(v, k) == IF k = "patch" THEN <<v[1], v[2] + 1>> ELSE <<v[1] + 1, 0>>
Range(s) == {s[i] : i \in DOMAIN s}
LastK(s, k) == IF Len(s) <= k THEN s ELSE SubSeq(s, Len(s) - k + 1, Len(s))
TagVers(T) == {t[1] : t \in T}

\* Refs are records so that TLC never compares a string with an integer.
BrRef(b) == [kind |-> "br", br |-> b, c |-> 0]
CRef(c) == [kind |-> "c", br |-> "none", c |-> c]
AllRefs == {BrRef(b) : b \in Branches} \cup {CRef(c) : c \in 1..MaxCommits}
Resolve(ref) == IF ref.kind = "br" THEN tip[ref.br] ELSE ref.c

\* Ancestry: commit 1 is the fork point (tagged v<<2,0>>); each branch is linear.
Ancestors(c) == {d \in 1..Len(commits) : d = 1 \/ (commits[d].br = commits[c].br /\ d <= c)}
ReachTags(c) == {t \in tags : t[2] \in Ancestors(c)}

EmptyGp == [snaps |-> <<>>, latest |-> 0, rels |-> {}, relLatest |-> NoVer, index |-> {}]
FreeRun == [wf |-> "none", ref |-> BrRef("master"), bump |-> "none", st |-> "free",
            pc |-> "none", job |-> "none", sha |-> 0, ver |-> NoVer, base |-> EmptyGp,
            tried |-> FALSE, snapTried |-> FALSE]
NewSnap(ref) == [FreeRun EXCEPT !.wf = "snap", !.ref = ref, !.pc = "S1"]
NewRel(b, k) == [FreeRun EXCEPT !.wf = "rel", !.ref = BrRef(b), !.bump = k, !.pc = "T1", !.job = "tag"]

\* snapshot.yml:13-15 and release.yml:21-23 share ONE group
\* (publish-ghpages-${{ github.repository }}).  The fix uses one group per <<wf, ref>>.
Group(r) == IF ~FIX THEN <<"G", BrRef("master")>> ELSE <<r.wf, r.ref>>
AllGroups == IF ~FIX THEN {<<"G", BrRef("master")>>}
             ELSE {<<w, rf>> : w \in {"rel", "snap"}, rf \in AllRefs}
Key(r) == <<r.wf, r.ref>>

-----------------------------------------------------------------------------
(* GitHub Actions concurrency: one running, at most one pending; a newly   *)
(* queued run cancels the existing pending one (cancel-in-progress only    *)
(* protects the *running* run).                                            *)
Enq(rs, s, r) ==
  LET g == Group(r) IN
  IF queue[g].running = 0 THEN
       /\ runs' = [rs EXCEPT ![s] = [r EXCEPT !.st = "run"]]
       /\ queue' = [queue EXCEPT ![g].running = s]
       /\ UNCHANGED lostRun
  ELSE IF queue[g].pending = 0 THEN
       /\ runs' = [rs EXCEPT ![s] = [r EXCEPT !.st = "pend"]]
       /\ queue' = [queue EXCEPT ![g].pending = s]
       /\ UNCHANGED lostRun
  ELSE LET p == queue[g].pending IN
       /\ runs' = [rs EXCEPT ![s] = [r EXCEPT !.st = "pend"],
                             ![p] = [rs[p] EXCEPT !.st = "cancelled"]]
       /\ queue' = [queue EXCEPT ![g].pending = s]
       /\ lostRun' = (lostRun \/ Key(rs[p]) # Key(r))

Finish(s, rnew) ==
  LET g == Group(runs[s])
      p == queue[g].pending IN
  IF p = 0 THEN /\ runs' = [runs EXCEPT ![s] = rnew]
                /\ queue' = [queue EXCEPT ![g] = [running |-> 0, pending |-> 0]]
  ELSE /\ runs' = [runs EXCEPT ![s] = rnew, ![p] = [runs[p] EXCEPT !.st = "run"]]
       /\ queue' = [queue EXCEPT ![g] = [running |-> p, pending |-> 0]]

Adv(s, rnew) == runs' = [runs EXCEPT ![s] = rnew] /\ UNCHANGED queue

\* release.yml has two jobs: create_tag (T*) and publish (P*).
JobOf(pc) == IF ~FIX /\ pc \in {"P1", "P2", "P3", "P4"} THEN "pub" ELSE "tag"

FailRun(s) ==
  LET r == runs[s] IN
  /\ Finish(s, [r EXCEPT !.st = "failed", !.job = JobOf(r.pc)])
  /\ futile' = (futile \/ r.tried)

EnvVars == <<commits, tip, bud, calm, goalVer, repairTried>>
PageVars == <<gp, tags, ghRel, pubSnaps, evicted, pubRels, badVer>>

-----------------------------------------------------------------------------
(* gh-pages transformations (publish-p2-ghpages.sh / cleanup-p2-snapshots) *)

\* Snapshot mode, publish-p2-ghpages.sh:59-64,72,76-109.  ".created" order is the
\* sequence order (republishing a sha moves it to the newest end).
SnapPub(g, c, ref) ==
  LET ns == Append(SelectSeq(g.snaps, LAMBDA y : y # c), c) IN
  [g EXCEPT !.snaps = ns,
            !.latest = IF FIX /\ ref # BrRef("master") THEN g.latest ELSE c,
            !.index = Range(LastK(ns, KEEP))]

\* Release mode, publish-p2-ghpages.sh:53-57,69-70 (HIGHEST = sort -V | head -1).
RelPub(g, v, c) ==
  LET nr == g.rels \cup {<<v, c>>} IN
  [g EXCEPT !.rels = nr,
            !.relLatest = IF PLANT = "relLatest" THEN v ELSE MaxV(TagVers(nr)),
            !.index = Range(LastK(g.snaps, KEEP))]

\* cleanup-p2-snapshots.sh:15-25: keep the KEEP newest by .created.
Kept(g) == IF PLANT = "cleanup" /\ Len(g.snaps) > KEEP THEN SubSeq(g.snaps, 1, KEEP)
           ELSE LastK(g.snaps, KEEP)
ToDel(g) == (Range(g.snaps) \ Range(Kept(g))) \ (IF FIX /\ PLANT # "cleanup" THEN {g.latest} ELSE {})
Clean(g) == [g EXCEPT !.snaps = SelectSeq(g.snaps, LAMBDA y : y \notin ToDel(g))]

\* tycho compare-version-with-baselines (pom.xml:247-263) against
\* baseline.repo.url = p2/releases/latest (pom.xml:64).  Fails with "Version has
\* moved backwards" when the build is lower than the baseline, and with "Only
\* qualifier changed"/"same version different content" when equal but rebuilt
\* from a different commit.  The fix compares against the own line's latest.
Gate(c, g) ==
  LET fv == commits[c].fv
      bl == IF FIX THEN MaxV({x[1] : x \in {y \in g.rels : y[2] \in Ancestors(c)}})
            ELSE g.relLatest
  IN bl = NoVer \/ Less(bl, fv) \/ (bl = fv /\ <<fv, c>> \in g.rels)

-----------------------------------------------------------------------------
(* snapshot.yml steps                                                      *)

\* snapshot.yml:24-34 checkout (explicit ref => branch tip at run time) + sha8
S1(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "snap" /\ r.pc = "S1"
  /\ Adv(s, [r EXCEPT !.sha = Resolve(r.ref), !.pc = "S2"])
  /\ UNCHANGED <<EnvVars, PageVars, lostRun, futile>>

\* snapshot.yml:67-90 mvn verify incl. baseline gate (fresh metadata, :56-65)
S2(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "snap" /\ r.pc = "S2"
  /\ IF Gate(r.sha, gp)
       THEN Adv(s, [r EXCEPT !.pc = "S3"]) /\ UNCHANGED futile
       ELSE FailRun(s)
  /\ UNCHANGED <<EnvVars, PageVars, lostRun>>

\* snapshot.yml:92-111 checkout gh-pages (local copy)
S3(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "snap" /\ r.pc = "S3"
  /\ Adv(s, [r EXCEPT !.base = gp, !.pc = "S4"])
  /\ UNCHANGED <<EnvVars, PageVars, lostRun, futile>>

\* snapshot.yml:113-117 -> publish-p2-ghpages.sh (commit, push, fetch+rebase retry)
S4(s) == LET r == runs[s]
             src == IF FIX THEN gp ELSE r.base
             new == SnapPub(src, r.sha, r.ref) IN
  /\ r.st = "run" /\ r.wf = "snap" /\ r.pc = "S4"
  /\ IF FIX \/ gp = r.base
       THEN /\ gp' = new
            /\ pubSnaps' = pubSnaps \cup {r.sha}
            /\ Adv(s, [r EXCEPT !.base = new, !.pc = "S5"])
            /\ UNCHANGED futile
       ELSE /\ FailRun(s)                 \* rebase conflict on index.html
            /\ UNCHANGED <<gp, pubSnaps>>
  /\ UNCHANGED <<EnvVars, tags, ghRel, evicted, pubRels, badVer, lostRun>>

\* snapshot.yml:119-122 -> cleanup-p2-snapshots.sh
S5(s) == LET r == runs[s]
             src == IF FIX THEN gp ELSE r.base
             new == Clean(src) IN
  /\ r.st = "run" /\ r.wf = "snap" /\ r.pc = "S5"
  /\ IF new = src
       THEN /\ Finish(s, [r EXCEPT !.st = "ok"])
            /\ UNCHANGED <<gp, evicted, futile>>
       ELSE IF FIX \/ gp = r.base
       THEN /\ gp' = new
            /\ evicted' = evicted \cup ToDel(src)
            /\ Finish(s, [r EXCEPT !.st = "ok"])
            /\ UNCHANGED futile
       ELSE /\ FailRun(s)
            /\ UNCHANGED <<gp, evicted>>
  /\ UNCHANGED <<EnvVars, tags, ghRel, pubSnaps, pubRels, badVer, lostRun>>

-----------------------------------------------------------------------------
(* release.yml steps                                                       *)

\* release.yml:36-63 checkout (fetch-depth 0 => ALL tags), compute version, sha8.
\* As written: LATEST = highest v* tag in the whole repository (line 44).
\* Fix: tags merged into HEAD; resume a half-done release first (a version already
\* tagged at HEAD, or a p2/releases/<v> of an ancestor commit that has no tag yet).
T1(s) == LET r == runs[s]
             c0 == IF FIX /\ r.sha # 0 THEN r.sha ELSE tip[r.ref.br]   \* fix: reruns pin github.sha
             here == {t \in tags : t[2] = c0}
             orphan == {x \in tags \cup gp.rels : x[2] \in Ancestors(c0)
                          /\ (x \notin tags \/ x \notin gp.rels \/ x[1] \notin ghRel)}
             resume == FIX /\ (here # {} \/ orphan # {})
             pick == IF here # {} THEN CHOOSE t \in here : TRUE ELSE CHOOSE x \in orphan : TRUE
             own == Bump(MaxV(TagVers(ReachTags(c0))), r.bump)
             c == IF resume THEN pick[2] ELSE c0
             v == IF resume THEN pick[1]
                  ELSE IF FIX THEN own
                  ELSE Bump(MaxV(TagVers(tags)), r.bump) IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "T1"
  /\ Adv(s, [r EXCEPT !.sha = c, !.ver = v, !.pc = "T2"])
  /\ badVer' = (badVer \/ (~resume /\ v # own))
  /\ UNCHANGED <<EnvVars, gp, tags, ghRel, pubSnaps, evicted, pubRels, lostRun, futile>>

\* release.yml:65-85 checkout gh-pages + verify snapshot dir exists
T2(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "T2"
  /\ IF r.sha \in Range(gp.snaps) \/ (FIX /\ <<r.ver, r.sha>> \in gp.rels)
       THEN Adv(s, [r EXCEPT !.base = gp, !.pc = "T3"]) /\ UNCHANGED futile
       ELSE FailRun(s)
  /\ UNCHANGED <<EnvVars, PageVars, lostRun>>

\* release.yml:87-104 every feature.xml version == tag version
T3(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "T3"
  /\ IF commits[r.sha].fv = r.ver
       THEN Adv(s, [r EXCEPT !.pc = IF FIX THEN "P2" ELSE "T4"]) /\ UNCHANGED futile
       ELSE FailRun(s)
  /\ UNCHANGED <<EnvVars, PageVars, lostRun>>

\* release.yml:106-112 git tag + push (as written: end of job create_tag).
\* Fix: runs after the release repository is published; idempotent on HEAD.
T4(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "T4"
  /\ IF FIX /\ <<r.ver, r.sha>> \in tags
       THEN Adv(s, [r EXCEPT !.pc = "P3"]) /\ UNCHANGED <<tags, futile>>
       ELSE IF r.ver \in TagVers(tags)
       THEN FailRun(s) /\ UNCHANGED tags
       ELSE /\ tags' = tags \cup {<<r.ver, r.sha>>}
            /\ Adv(s, [r EXCEPT !.pc = IF FIX THEN "P3" ELSE "P1", !.job = IF FIX THEN "tag" ELSE "pub"])
            /\ UNCHANGED futile
  /\ UNCHANGED <<EnvVars, gp, ghRel, pubSnaps, evicted, pubRels, badVer, lostRun>>

\* release.yml:118-128 publish job: checkout tag + gh-pages
P1(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "P1"
  /\ Adv(s, [r EXCEPT !.base = gp, !.pc = "P2"])
  /\ UNCHANGED <<EnvVars, PageVars, lostRun, futile>>

\* release.yml:130-135 -> publish-p2-ghpages.sh release mode (cp from snapshot, push)
P2(s) == LET r == runs[s]
             src == IF FIX THEN gp ELSE r.base
             new == RelPub(src, r.ver, r.sha)
             done == FIX /\ <<r.ver, r.sha>> \in gp.rels
             nxt == IF FIX THEN "T4" ELSE "P3" IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "P2"
  /\ IF done
       THEN Adv(s, [r EXCEPT !.pc = nxt]) /\ UNCHANGED <<gp, pubRels, futile>>
       ELSE IF r.sha \notin Range(src.snaps) \/ ~(FIX \/ gp = r.base)
       THEN FailRun(s) /\ UNCHANGED <<gp, pubRels>>      \* cp fails (set -e) / rebase conflict
       ELSE /\ gp' = new
            /\ pubRels' = pubRels \cup {<<r.ver, r.sha>>}
            /\ Adv(s, [r EXCEPT !.base = new, !.pc = nxt])
            /\ UNCHANGED futile
  /\ UNCHANGED <<EnvVars, tags, ghRel, pubSnaps, evicted, badVer, lostRun>>

\* release.yml:137-150 zip gh-pages/p2/snapshots/<sha> + gh release create.
\* Fix: zip p2/releases/<ver>; skip when the release already exists.
P3(s) == LET r == runs[s]
             srcOk == IF FIX THEN <<r.ver, r.sha>> \in gp.rels ELSE r.sha \in Range(r.base.snaps) IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "P3"
  /\ IF FIX /\ r.ver \in ghRel
       THEN Adv(s, [r EXCEPT !.pc = "P4"]) /\ UNCHANGED <<ghRel, futile>>
       ELSE IF ~srcOk \/ r.ver \in ghRel
       THEN FailRun(s) /\ UNCHANGED ghRel
       ELSE /\ ghRel' = ghRel \cup {r.ver}
            /\ Adv(s, [r EXCEPT !.pc = "P4"])
            /\ UNCHANGED futile
  /\ UNCHANGED <<EnvVars, gp, tags, pubSnaps, evicted, pubRels, badVer, lostRun>>

\* release.yml:152-165 summary
P4(s) == LET r == runs[s] IN
  /\ r.st = "run" /\ r.wf = "rel" /\ r.pc = "P4"
  /\ Finish(s, [r EXCEPT !.st = "ok"])
  /\ UNCHANGED <<EnvVars, PageVars, lostRun, futile>>

Step(s) == S1(s) \/ S2(s) \/ S3(s) \/ S4(s) \/ S5(s)
           \/ T1(s) \/ T2(s) \/ T3(s) \/ T4(s) \/ P1(s) \/ P2(s) \/ P3(s) \/ P4(s)

-----------------------------------------------------------------------------
(* Environment (pre-calm): pushes, dispatches, failures, re-runs, expiry   *)

FreeSlots == {s \in Slots : runs[s].st = "free"}
BumpFor(b) == IF b = "maint" THEN "patch" ELSE "minor"

\* push to master / v[0-9]* branch => snapshot.yml push event (snapshot.yml:10-11).
\* Policy assumption: master only takes minor bumps, maint only patch bumps.
Push(b, fv, s) ==
  LET n == Len(commits) + 1 IN
  /\ commits' = Append(commits, [br |-> b, fv |-> fv])
  /\ tip' = [tip EXCEPT ![b] = n]
  /\ Enq(runs, s, NewSnap(BrRef(b)))

EnvPush == \E b \in Branches, s \in FreeSlots :
  LET cur == commits[tip[b]].fv IN
  \E fv \in {cur, Bump(cur, BumpFor(b))} :
  /\ ~calm /\ bud.push > 0
  /\ Push(b, fv, s)
  /\ bud' = [bud EXCEPT !.push = @ - 1]
  /\ UNCHANGED <<tags, gp, ghRel, calm, goalVer, badVer, futile, pubSnaps, evicted, pubRels, repairTried>>

EnvDispatchSnap == \E b \in Branches, s \in FreeSlots :
  /\ ~calm /\ bud.disp > 0
  /\ Enq(runs, s, NewSnap(BrRef(b)))
  /\ bud' = [bud EXCEPT !.disp = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, futile>>

EnvDispatchRel == \E b \in Branches, k \in {"patch", "minor"}, s \in FreeSlots :
  /\ ~calm /\ bud.disp > 0
  /\ Enq(runs, s, NewRel(b, k))
  /\ bud' = [bud EXCEPT !.disp = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, futile>>

\* any step may fail (runner loss, network, transient push failure, ...)
EnvFail == \E s \in Slots :
  /\ ~calm /\ bud.fail > 0 /\ runs[s].st = "run"
  /\ FailRun(s)
  /\ bud' = [bud EXCEPT !.fail = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, lostRun>>

RestartPc(r) == IF r.wf = "snap" THEN "S1" ELSE IF r.job = "pub" THEN "P1" ELSE "T1"

\* "Re-run failed jobs": successful jobs keep their outputs (tag, snapshot_sha).
EnvRerunFailed == \E s \in Slots :
  LET r == runs[s] IN
  /\ ~calm /\ bud.rerun > 0 /\ r.st = "failed"
  /\ Enq(runs, s, [r EXCEPT !.pc = RestartPc(r)])
  /\ bud' = [bud EXCEPT !.rerun = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, futile>>

\* "Re-run all jobs": create_tag recomputes the version from scratch.
EnvRerunAll == \E s \in Slots :
  LET r == runs[s] IN
  /\ ~calm /\ ~FIX /\ bud.rerun > 0 /\ r.st = "failed" /\ r.wf = "rel" /\ r.job = "pub"
  /\ Enq(runs, s, [r EXCEPT !.pc = "T1", !.job = "tag", !.ver = NoVer])
  /\ bud' = [bud EXCEPT !.rerun = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, futile>>

\* a failed run can no longer be re-run (retention window elapsed)
EnvExpire == \E s \in Slots :
  /\ ~calm /\ bud.expire > 0 /\ runs[s].st = "failed"
  /\ runs' = [runs EXCEPT ![s].st = "expired"]
  /\ bud' = [bud EXCEPT !.expire = @ - 1]
  /\ UNCHANGED <<commits, tip, calm, goalVer, repairTried, PageVars, queue, lostRun, futile>>

-----------------------------------------------------------------------------
(* Calm phase: failures stop and a maintainer tries, sequentially and with  *)
(* knowledge of the pipeline, to (a) finish any half-done release and (b)   *)
(* ship the next release of GoalBranch's own line.                          *)

Desired(b) ==
  LET m == MaxV(TagVers(ReachTags(tip[b]))) IN Bump(m, BumpFor(b))

GoCalm ==
  /\ CheckCalm /\ ~calm
  /\ calm' = TRUE
  /\ goalVer' = Desired(GoalBranch)
  /\ UNCHANGED <<commits, tip, tags, gp, ghRel, runs, queue, bud, badVer,
                 lostRun, futile, pubSnaps, evicted, pubRels, repairTried>>

Quiescent == \A s \in Slots : runs[s].st \notin {"run", "pend"}
\* a failed release run that had already made durable progress (past its checks)
Eligible(r) == r.st = "failed" /\ r.wf = "rel" /\ ~r.tried
               /\ IF FIX THEN r.pc \in {"P2", "T4", "P3", "P4"} ELSE r.job = "pub"
Dead(r) == r.st \in {"ok", "cancelled", "expired", "free"} \/ (r.st = "failed" /\ ~Eligible(r))
RS == [s \in Slots |-> IF Dead(runs[s]) THEN FreeRun ELSE runs[s]]
FreeIn(rs) == CHOOSE s \in Slots : rs[s] = FreeRun /\ \A t \in Slots : rs[t] = FreeRun => s <= t
HasFree(rs) == \E s \in Slots : rs[s] = FreeRun

Goal == \E t \in tags : t[1] = goalVer /\ commits[t[2]].br = GoalBranch
                        /\ t \in gp.rels /\ t[1] \in ghRel

NeedSnap(r) == r.sha # 0 /\ r.sha \notin Range(gp.snaps)
               /\ (~FIX \/ <<r.ver, r.sha>> \notin gp.rels)

StratA == \E s \in Slots :
  LET r == runs[s] IN
  /\ calm /\ Quiescent /\ Eligible(r)
  /\ IF NeedSnap(r) /\ ~r.snapTried
       THEN LET rs == [RS EXCEPT ![s] = [r EXCEPT !.snapTried = TRUE]] IN
            /\ HasFree(rs)
            /\ Enq(rs, FreeIn(rs), NewSnap(CRef(r.sha)))
       ELSE Enq(RS, s, [r EXCEPT !.tried = TRUE, !.pc = RestartPc(r)])
  /\ UNCHANGED <<EnvVars, PageVars, futile>>

NoA == ~\E s \in Slots : Eligible(runs[s])

\* half-done releases: a tag without repo/GitHub release, or a repo without tag
Incomplete == {t \in tags \cup gp.rels : t \notin tags \/ t \notin gp.rels \/ t[1] \notin ghRel}

\* (a') no re-runnable run left for a half-done release: dispatch a new release on
\* the tag's branch once (the fixed workflow resumes it; as written it recomputes).
StratR == \E t \in Incomplete :
  /\ calm /\ Quiescent /\ NoA /\ HasFree(RS)
  /\ t \notin repairTried /\ commits[t[2]].br \in Branches
  /\ repairTried' = repairTried \cup {t}
  /\ Enq(RS, FreeIn(RS), NewRel(commits[t[2]].br, BumpFor(commits[t[2]].br)))
  /\ UNCHANGED <<commits, tip, bud, calm, goalVer, PageVars, futile>>

NoR == ~\E t \in Incomplete : t \notin repairTried /\ commits[t[2]].br \in Branches

StratB ==
  /\ calm /\ Quiescent /\ NoA /\ NoR /\ ~Goal /\ HasFree(RS)
  /\ commits[tip[GoalBranch]].fv # goalVer
  /\ LET n == Len(commits) + 1 IN
     /\ commits' = Append(commits, [br |-> GoalBranch, fv |-> goalVer])
     /\ tip' = [tip EXCEPT ![GoalBranch] = n]
     /\ Enq(RS, FreeIn(RS), NewSnap(BrRef(GoalBranch)))
  /\ UNCHANGED <<bud, calm, goalVer, repairTried, PageVars, futile>>

StratC ==
  /\ calm /\ Quiescent /\ NoA /\ NoR /\ ~Goal /\ HasFree(RS)
  /\ commits[tip[GoalBranch]].fv = goalVer
  /\ tip[GoalBranch] \notin Range(gp.snaps)
  /\ Enq(RS, FreeIn(RS), NewSnap(BrRef(GoalBranch)))
  /\ UNCHANGED <<EnvVars, PageVars, futile>>

StratD ==
  /\ calm /\ Quiescent /\ NoA /\ NoR /\ ~Goal /\ HasFree(RS)
  /\ commits[tip[GoalBranch]].fv = goalVer
  /\ tip[GoalBranch] \in Range(gp.snaps)
  /\ Enq(RS, FreeIn(RS), NewRel(GoalBranch, BumpFor(GoalBranch)))
  /\ UNCHANGED <<EnvVars, PageVars, futile>>

Strategy == StratA \/ StratR \/ StratB \/ StratC \/ StratD

-----------------------------------------------------------------------------
Init ==
  /\ commits = << [br |-> "base", fv |-> <<2, 0>>],
                  [br |-> "master", fv |-> <<3, 0>>],
                  [br |-> "maint", fv |-> <<2, 1>>] >>
  /\ tip = [b \in Branches |-> IF b = "master" THEN 2 ELSE 3]
  /\ tags = {<< <<2, 0>>, 1 >>}
  /\ gp = [snaps |-> <<1>>, latest |-> 1, rels |-> {<< <<2, 0>>, 1 >>},
           relLatest |-> <<2, 0>>, index |-> {1}]
  /\ ghRel = {<<2, 0>>}
  /\ runs = [s \in Slots |-> FreeRun]
  /\ queue = [g \in AllGroups |-> [running |-> 0, pending |-> 0]]
  /\ bud = [push |-> MaxPush, disp |-> MaxDispatch, fail |-> MaxFail,
            rerun |-> MaxRerun, expire |-> MaxExpire]
  /\ calm = FALSE /\ goalVer = NoVer
  /\ badVer = FALSE /\ lostRun = FALSE /\ futile = FALSE
  /\ pubSnaps = {} /\ evicted = {} /\ pubRels = {} /\ repairTried = {}

Env == EnvPush \/ EnvDispatchSnap \/ EnvDispatchRel \/ EnvFail \/ EnvRerunFailed
       \/ EnvRerunAll \/ EnvExpire \/ GoCalm

Next == Env \/ (\E s \in Slots : Step(s)) \/ Strategy

CalmNext == calm /\ ((\E s \in Slots : Step(s)) \/ Strategy)

Spec == Init /\ [][Next]_vars /\ WF_vars(CalmNext)

-----------------------------------------------------------------------------
(* Properties                                                              *)

TagHasRepo == \A t \in tags : t \in gp.rels
LiveFor(t) == \E s \in Slots :
  LET r == runs[s] IN
  /\ r.wf = "rel" /\ r.ver = t[1] /\ r.sha = t[2]
  /\ r.st \in {"run", "pend", "failed"}
  /\ (r.job = "pub" \/ r.pc \in {"P1", "P2", "P3", "P4"})
TagHasRepoOrLive == \A t \in tags : t \in gp.rels \/ LiveFor(t)
LatestFromMaster == gp.latest = 0 \/ commits[gp.latest].br \in {"base", "master"}
OwnLineVersion == ~badVer
\* narrower: a maint release run always computes a version on the 2.x line
MaintOnOwnLine == \A s \in Slots : runs[s].wf = "rel" /\ runs[s].ref = BrRef("maint") /\ runs[s].bump = "patch"
                   /\ runs[s].ver # NoVer => runs[s].ver[1] = 2
RelLatestIsMax == gp.relLatest = MaxV(TagVers(gp.rels))
LatestExists == gp.latest = 0 \/ gp.latest \in Range(gp.snaps)
IndexNoDangling == gp.index \subseteq Range(gp.snaps)
NoLostContent == /\ pubRels \subseteq gp.rels
                 /\ \A c \in pubSnaps : c \in Range(gp.snaps) \/ c \in evicted
NoCrossRefCancel == ~lostRun
RerunProgress == ~futile

AllTagsComplete == \A t \in tags : t \in gp.rels /\ t[1] \in ghRel
LGoal == calm ~> Goal
LTags == calm ~> []AllTagsComplete

\* Witnesses: each is expected to be VIOLATED (the good path is reachable).
W_MasterRelease == ~\E t \in tags : commits[t[2]].br = "master" /\ t \in gp.rels /\ t[1] \in ghRel
W_MaintRelease == ~\E t \in tags : commits[t[2]].br = "maint" /\ t \in gp.rels /\ t[1] \in ghRel
W_Evicted == evicted = {}
W_CalmGoal == ~(calm /\ Goal)
=============================================================================
