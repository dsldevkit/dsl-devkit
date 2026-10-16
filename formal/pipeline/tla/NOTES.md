# Release / snapshot pipeline: TLA+ model

Code under study (commit `e784f416b`):
`.github/workflows/release.yml`, `.github/workflows/snapshot.yml`,
`.github/workflows/verify.yml` (only for the baseline gate),
`.github/scripts/publish-p2-ghpages.sh`, `.github/scripts/cleanup-p2-snapshots.sh`,
`ddk-parent/pom.xml` (`baseline.repo.url`, `snapshot.repo.url`, `compare-version-with-baselines`).

Files in this folder:

| File | What it is |
|---|---|
| `Pipeline.tla` | the model; `FIX=FALSE` is the code as written, `FIX=TRUE` applies the minimal fixes below, `PLANT` adds an obvious bug for the sanity check |
| `run.sh` | writes `cfg/<name>.cfg`, runs TLC (`-workers auto -deadlock`, metadir under `states/`), log in `logs/<name>.log` |
| `run_all.sh` | re-runs every check below (`--live` also runs the liveness checks) |
| `trace.py` | condenses a TLC counterexample to one line per step (action + changed variables) |
| `cfg/`, `logs/` | generated configs and TLC output of the reported runs |

## Model

State:

* **git**: commits as a sequence of `[branch, feature.xml version]`. Commit 1 is the fork point
  (tagged `v<2,0>`), `master` and a maintenance branch `maint` (a `v[0-9]*` branch) are linear
  on top of it. The initial master commit has features `<3,0>`, maint has `<2,1>`.
  Tags are `<<version, commit>>` pairs.
* **gh-pages**: `snaps` (sequence ordered by `.created`), `latest` (child of
  `p2/snapshots/latest`), `rels` (`p2/releases/<v>` with the commit it was copied from),
  `relLatest` (child of `p2/releases/latest`), `index` (snapshot links in `index.html`).
* **GitHub releases** (`ghRel`), **workflow runs** (slot -> `[wf, ref, bump, st, pc, job, sha, ver,
  local gh-pages checkout]`) and **concurrency groups** (`running`, `pending`).

Versions are `<<minor, patch>>`; the major is constant and `major` bumps behave exactly like
`minor` for every property here, so only `patch`/`minor` are dispatched.

Actions, at the granularity of YAML steps / script commands (each step is one atomic action;
adjacent steps were merged only where no other run can interleave and a failure between them is
equivalent to a failure of the next step):

* snapshot.yml: `S1` checkout (explicit `ref` -> branch tip at run time) + sha8, `S2` Maven build incl.
  the tycho baseline gate, `S3` checkout gh-pages, `S4` publish script (commit, push, fetch+rebase
  retry), `S5` cleanup script.
* release.yml: `T1` checkout (fetch-depth 0) + compute version, `T2` checkout gh-pages + verify snapshot,
  `T3` feature.xml check, `T4` tag + push (end of job `create_tag`); `P1` checkout tag + gh-pages,
  `P2` publish script (release mode), `P3` zip + `gh release create`, `P4` summary.
* environment: pushes to either branch (each triggers snapshot.yml), `workflow_dispatch` of either
  workflow on either branch with either bump, a failure at **any** running step, "Re-run failed
  jobs" (successful jobs keep their outputs), "Re-run all jobs", expiry of the re-run window.
* GitHub concurrency: one running and at most one pending run per group; a newly queued run
  cancels the pending one. Both workflows use the same group (`publish-ghpages-<repo>`).
* tycho `compare-version-with-baselines` (pom.xml:247-263) against `p2/releases/latest`
  (pom.xml:64): fails if the build version is lower than the baseline ("Version has moved
  backwards", `onIllegalVersion` defaults to `fail`), or equal but built from another commit
  ("Only qualifier changed" / "same fully qualified version, but different content"). Checked in the
  tycho-p2-extras-plugin 5.0.4 bytecode (`CompareWithBaselineMojo.execute`, offsets 421-483 and
  730-822; `plugin.xml` default `onIllegalVersion=fail`).

Calm phase (for the liveness properties): at any point the environment can switch to *calm*: no
more injected failures, expiries or re-run-all, and a maintainer who knows the pipeline acts
sequentially and helpfully for one `GoalBranch`: (A) re-run the failed publish of a half-done
release once (first re-publishing its snapshot if it was evicted), (R) otherwise dispatch a new
release once on the branch of every half-done release (tag without repo / GitHub release, or repo
without tag), then (B) bump features to the next version of the branch's **own** line, (C) build
its snapshot, (D) dispatch the release. Runner steps are weakly fair in the calm phase.

Bounds (default `run.sh`): 1 push, 2 dispatches, 1 injected failure, 1 re-run, 1 expiry, 3 run
slots, `KEEP=1` (20 in the code: only relative order matters for the properties). One wider run uses
3 dispatches and 4 slots. All results are **bounded exhaustive model checking**, not proofs.

Assumptions / abstractions:

* commit content: any two commits differ, so rebuilding a different commit at an already-released
  version always trips the gate (the gate is per bundle in reality; this is the feature-level view).
* version policy: master only takes minor bumps, maint only patch bumps (so the lines are disjoint).
* `gh release create` for an existing release fails (GitHub API 422 `already_exists`). Not checked
  offline: that step's verdict is PLAUSIBLE.
* GitHub Actions semantics (documented, not in the repo): pending-run replacement in a concurrency
  group; `actions/checkout` with `fetch-depth: 0` fetches all tags; with an explicit `ref` it checks
  out the ref's tip at run time, not `github.sha`; re-runs only act on the latest attempt and
  "Re-run all jobs" recomputes job outputs.
* push conflicts: with the shared group no two runs push concurrently; any divergence of the remote
  from the local checkout is modelled as a rebase conflict (index.html is always rewritten) and
  therefore a failed step.

## Properties

| Property | Kind | Meaning |
|---|---|---|
| `TagHasRepo` | invariant | every tag has a `p2/releases/<v>` |
| `TagHasRepoOrLive` | invariant | ... or a run that can still publish it (running, pending, or failed but re-runnable) |
| `MaintOnOwnLine` | invariant | a maint release with a patch bump computes a 2.x version (targeted form of `OwnLineVersion`) |
| `LatestFromMaster` | invariant | `p2/snapshots/latest` points at a master commit |
| `OwnLineVersion` | invariant | a computed release version is the bump of the highest tag reachable from the released commit |
| `RelLatestIsMax` | invariant | `p2/releases/latest` is the highest release |
| `LatestExists` | invariant | `p2/snapshots/latest` points at an existing snapshot |
| `IndexNoDangling` | invariant | every snapshot link in index.html exists |
| `NoLostContent` | invariant | every published release still exists; every published snapshot exists or was evicted by the cleanup policy |
| `NoCrossRefCancel` | invariant | a queued run is only ever cancelled by a newer run of the same workflow and ref |
| `RerunProgress` | invariant | in the calm phase, re-running a failed half-done release never fails again |
| `LGoal` | liveness | `calm ~> Goal`: from any state, the next release of `GoalBranch`'s own line gets tagged, published and released |
| `LTags` | liveness | `calm ~> []AllTagsComplete`: every tag eventually has its repo and GitHub release |
| `W_*` | witness | expected to be violated: master release, maint release, snapshot eviction and the calm goal are reachable |

## Results

Code as written (`FIX=FALSE`):

| Check | Result | Distinct states | Depth | Time |
|---|---|---|---|---|
| `asis_TagHasRepo` | violated (transient by design, see F3) | 501,007* | 13 | 2 s |
| `asis_TagHasRepoOrLive` | **violated** (F3) | 760,763* | 14 | 3 s |
| `asis_LatestFromMaster` | **violated** (F2) | 17,271* | 8 | <1 s |
| `asis_OwnLineVersion` | **violated** (F1) | 982,209* | 16 | 5 s |
| `asis_MaintOnOwnLine` | **violated** (F1) | 980,014* | 16 | 5 s |
| `asis_NoCrossRefCancel` | **violated** (F4) | 154* | 5 | <1 s |
| `asis_RerunProgress` | **violated** (F5) | 2,903,588* | 22 | 12 s |
| `asis_RelLatestIsMax` | holds | 1,535,581 | 31 | 8 s |
| `asis_LatestExists` | holds | 1,535,581 | 31 | 7 s |
| `asis_IndexNoDangling` | holds | 1,535,581 | 31 | 6 s |
| `asis_NoLostContent` | holds | 1,535,581 | 31 | 7 s |
| `asis_W_MasterRelease` | witness reached | 972,894* | 17 | 3 s |
| `asis_W_MaintRelease` | witness reached | 1,003,683* | 17 | 3 s |
| `asis_W_Evicted` | witness reached | 27,461* | 9 | 1 s |

`*` = states explored when the violation was found (BFS, so the trace is a shortest one).

Fixed model (`FIX=TRUE`) and sanity:

| Check | Result | Distinct states | Depth | Time |
|---|---|---|---|---|
| `fix_safety` (all 9 invariants, calm goal master) | holds | 3,806,977 | 47 | 22 s |
| `fix_safety_maint` (all 9 invariants, calm goal maint) | holds | 3,790,953 | 47 | 23 s |
| `fix_safety_wide` (all 9 invariants, 3 dispatches, 4 slots) | holds | 177,172,131 | 37 | 18 min |
| `fix_MaintOnOwnLine` | holds | 1,860,471 | 29 | 11 s |
| `fix_relLatest_ctrl` (control for the plant, same bound) | holds | 4,681,933 | 31 | 30 s |
| `plant_relLatest` (RelLatestIsMax) | **caught** | 3,111,475* | 22 | 17 s |
| `plant_cleanup` (LatestExists) | **caught** | 15,996* | 9 | <1 s |
| `fix_W_MasterRelease` | witness reached | 524,094* | 14 | 3 s |
| `fix_W_MaintRelease` | witness reached | 533,216* | 15 | 3 s |
| `fix_W_Evicted` | witness reached | 24,681* | 9 | 1 s |
| `fix_W_CalmGoal_maint` | witness reached | 1,072,599* | 15 | 5 s |

Liveness (includes the calm phase, 5 runs, about 25 min):

| Check | Result | Distinct states | Time |
|---|---|---|---|
| `fix_live_master` (LGoal, LTags) | holds | 3,806,977 | 6 min 02 s |
| `fix_live_maint` (LGoal, LTags) | holds | 3,790,953 | 6 min 27 s |
| `asis_live_master` (LGoal) | holds | 3,115,442 | 3 min 11 s |
| `asis_live_maint` (LGoal) | **violated** | 3,113,324 | 2 min 40 s |
| `asis_live_tags` (LTags) | **violated** | 3,115,442 | 2 min 42 s |

## Findings

Line references are to the files at `e784f416b`.

### F1: a maintenance line cannot be released once master has released a higher version (CONFIRMED, high)

Violates `OwnLineVersion` and `LGoal` for `maint`; `LGoal` holds for master.

Two independent causes, both confirmed by reading the code:

1. `release.yml:44`, `LATEST=$(git tag --list 'v*' --sort=-version:refname | head -1)`, after a
   checkout with `fetch-depth: 0` (`release.yml:36-39`, which fetches every tag in the repository).
   The next version is derived from the highest tag in the whole repo, not the branch's own line.
2. The baseline gate compares every build against `p2/releases/latest` (pom.xml:64 and 247-263),
   which is the highest release overall (`publish-p2-ghpages.sh:69-70`). A maintenance build at a
   lower version fails with "Version has moved backwards" (`onIllegalVersion` defaults to `fail`), so
   `snapshot.yml:67-72` fails and no snapshot is published for the maintenance commit. The PR build in
   `verify.yml:79` fails in the same way for PRs against the maintenance branch.

Shortest trace violating `MaintOnOwnLine` (a maint/patch release must compute a 2.x version;
`logs/asis_MaintOnOwnLine.log`):

1. push to master (commit 4) -> snapshot run (`snapshot.yml:10-11`).
2. dispatch release master/minor (queued behind it: `release.yml:21-23`).
3-7. snapshot run `S1`-`S5`: build passes (3.0 > 2.0), `p2/snapshots/4` published, cleanup.
8. dispatch release maint/patch (pending).
9-12. master release: `T1` computes v3.0 (`release.yml:44-57`), snapshot exists (`:74-85`),
   features 3.0 (`:87-104`), tag v3.0 pushed (`:106-112`).
13. the master publish job fails (irrelevant here; it only lets the maint run start earlier).
14. maint release `T1`: `LATEST` = v3.0, patch -> **v3.1** instead of v2.1.

The BFS-shortest `OwnLineVersion` trace is the mirror image: a maint tag v2.1 exists, and a
master/patch release computes v2.2 from it instead of v2.1 (master's own line). Both come from the
same line 44.

`T3` then fails (maintenance features are 2.1), so the tag is not created: the effect is that the
maintenance line is stuck, not that a wrong tag is created. In the liveness counterexample
(`logs/asis_live_maint.log`) master releases v3.0, then the maintainer only needs a maint snapshot
for 2.1 and `S2` fails forever on the baseline gate (2.1 < 3.0): the lasso loops `StratC -> S1 -> S2
(fail)`. Even with a snapshot, `T1` would compute v3.1 and `T3` would reject it. There is no bump
choice that yields 2.1.x.

### F2: `p2/snapshots/latest` can point at a maintenance-branch (or any dispatched) build (CONFIRMED, medium)

Violates `LatestFromMaster`. Trace (`logs/asis_LatestFromMaster.log`, 6 states):

1. push to `maint` -> `snapshot.yml:10-11` triggers for `v[0-9]*` branches.
2-5. `S1`-`S4`: `publish-p2-ghpages.sh:72` writes the `latest` composite for every snapshot, whatever
   the ref. `p2/snapshots/latest` now points at the maintenance commit.

`workflow_dispatch` with any `branch` input (`snapshot.yml:4-9, 26`) does the same. Consumers of
`snapshots/latest` (README-style URL in `release.yml:172`, and `snapshot.repo.url` used as the
tycho-p2-plugin `baselineRepositories`, pom.xml:65 and 387-393) get a maintenance build. Before F1
blocks maintenance builds this happens on every maintenance push.

### F3: a tag without a release repository (CONFIRMED, high)

`TagHasRepo` fails trivially (the tag is pushed by job `create_tag`, the repository is published by
the next job, `release.yml:106-135`), so the relevant check is `TagHasRepoOrLive` and `LTags`.

Shortest trace (`logs/asis_TagHasRepoOrLive.log`, relevant steps):

1. push to master -> snapshot built and published (`S1`-`S5`).
2. dispatch release master/minor: `T1` v3.0, `T2`, `T3`, `T4` pushes tag v3.0 (`release.yml:111-112`).
3. the publish job fails at its first step (`release.yml:118-128`; any failure: runner, network, a
   push rejection in `publish-p2-ghpages.sh:117-126`).
4. maintainer clicks **Re-run all jobs**: `create_tag` recomputes `LATEST` = v3.0 -> v4.0, `T3`
   fails (features are 3.0). Only the latest attempt can be re-run, so the publish job for v3.0 can
   never run again: tag v3.0 has no `p2/releases/v3.0`, no GitHub release, and no run that can make one.

Other routes found by the liveness check (`logs/asis_live_tags.log`): the failed run passes the
re-run window (expiry); a **new** dispatch instead of a re-run computes the next version and fails at
the feature check. In every case the only way on is a manual `git push --delete` of the tag or
skipping the version (bumping features past it), which leaves the orphan tag forever.

"Re-run failed jobs" does recover if done in time and if `p2/snapshots/<sha>` still exists: the
publish job copies from the snapshot (`publish-p2-ghpages.sh:57`, `release.yml:142`) and the cleanup
(`cleanup-p2-snapshots.sh:15-25`, KEEP=20) may have evicted it; recovery then needs a snapshot
dispatch of the tag first (the maintainer strategy in the model does this).

### F4: a pending run is silently cancelled by a run of another ref or workflow (CONFIRMED on the YAML, medium)

Violates `NoCrossRefCancel`. `snapshot.yml:13-15` and `release.yml:21-23` share the group
`publish-ghpages-${{ github.repository }}` with `cancel-in-progress: false`, which only protects the
*running* run; GitHub replaces the pending run with the newest one. Trace (4 states):

1. push to master (snapshot run A starts).
2. dispatch snapshot master (B pending).
3. dispatch snapshot maint (C) -> B cancelled.

The same happens with a push to master while another publish runs followed by any maintenance push
(master's newest commit never gets a snapshot, so a release of master HEAD is refused by
`release.yml:81-84` until another master push or a manual dispatch), and with a manual release
dispatch that is pending when a push arrives (the release request is dropped). Recoverable by
re-dispatching, hence medium.

### F5: a publish-job re-run can never succeed once `gh release create` has run (PLAUSIBLE, low)

Violates `RerunProgress` (`logs/asis_RerunProgress.log`): the release is fully done (tag, repo,
GitHub release) and the job fails in `Summary` (`release.yml:152-165`) or loses its runner after
`gh release create` returned. "Re-run failed jobs" re-runs the whole publish job; `publish-p2-ghpages.sh`
is idempotent, but `gh release create` (`release.yml:145-148`) fails because the release exists, on
every re-run. Nothing is lost, the run just stays red. Verdict PLAUSIBLE: it rests on `gh release
create` rejecting an existing release, which was not checked offline. The same non-idempotence is
behind the "zip from a snapshot that was evicted" failure mode in F3.

### Properties that hold on the code as written (bounded)

`RelLatestIsMax`, `LatestExists`, `IndexNoDangling`, `NoLostContent` hold on the as-written model.
The shared concurrency group serialises every gh-pages writer, so no publish loses another's content;
the index and the cleanup use the same ordering (`sort -rn | head -20` vs `sort -n | head -n -20`,
which are exact reverses including the whole-line tie-break), so the index never links a deleted
snapshot; `latest` is always the newest `.created`, so cleanup keeps it. `asis_live_master` shows a
master release is always still possible, even with orphan tags (by skipping versions).

## Fixed model (minimal code-faithful fixes)

`FIX=TRUE` applies, together:

1. **Own-line version** (F1): `git tag --list 'v*' --merged HEAD` in `release.yml:44`.
2. **Own-line baseline** (F1): maintenance builds compare against their own line's latest release
   (e.g. publish `p2/releases/<major.minor>-latest` and override `baseline.repo.url` on the
   maintenance branch).
3. **Master-only latest** (F2): `publish-p2-ghpages.sh:72` writes `snapshots/latest` only for master.
4. **Cleanup protects latest** (found by the fixed model: once `latest` is not the newest snapshot,
   KEEP newer maintenance snapshots evict it; `LatestExists` failed): `cleanup-p2-snapshots.sh`
   excludes the child of `snapshots/latest`.
5. **Publish, then tag, then GitHub release, all idempotent and resumable** (F3, F5): one job:
   compute, verify, publish `p2/releases/<v>` (skip if present), push tag (skip if it already
   points at this commit), `gh release create` from `p2/releases/<v>` (skip if it exists). `T1`
   first resumes a half-done release reachable from HEAD (a repo without tag, or a tag without repo
   or GitHub release) instead of computing a new version.
6. **Re-runs pin the commit**: the release uses `github.sha` of the run (dispatch the workflow on the
   branch) instead of re-resolving the branch tip on a re-run. Found by the fixed model: without it a
   re-run of a finished release built a new version from a newer master commit and failed.
7. **Per-ref concurrency** (F4): groups keyed by workflow and ref; the publish scripts then have to
   re-fetch gh-pages and recompute their change on a rejected push instead of `git rebase` of a
   stale `index.html`/composite (modelled as an atomic recompute on the current remote).

Fixes 4 and 6 and the orphan-repo part of 5 were not in the first version of the fixed model; each
was added after TLC found a counterexample in the fixed model (the earlier logs were overwritten by
the final runs).

Sanity: the fixed model passes every invariant (both goal branches, and a wider bound with 3
dispatches and 4 slots) and both liveness properties for both branches. Planted bugs are caught:
`PLANT=relLatest` (`releases/latest` = the version just released instead of the highest) violates
`RelLatestIsMax` after a maintenance release following a master release; `PLANT=cleanup` (cleanup
keeps the oldest instead of the newest and does not protect `latest`) violates `LatestExists`. The
control run without the plant at the same bound passes. The witnesses show that a master release,
a maintenance release, snapshot eviction and the calm-phase goal are all reachable in both models.

## Limits

* Bounded: 1 push, 2-3 dispatches, 1 failure, 1 re-run, 1 expiry, 3-4 slots. Longer histories
  (several releases per line, several orphan tags) were not explored.
* The calm-phase maintainer is one fixed strategy; liveness holds "for this strategy", which is a
  witness that a recovery exists, not that every maintainer finds it.
* Content is abstracted to "commit identity"; qualifier details (jgit timestamps, `.qualifier`
  expansion), Maven/Tycho HTTP caching (`snapshot.yml:56-65`) and the artifact upload steps are not
  modelled.
* GitHub Actions and `gh` semantics are taken from their documentation, not from source.

Wall time: about 1 h 40 min in total (reading the code and writing the model about 30 min, TLC runs
about 60 min of machine time including three liveness batches, the rest trace review and fix
iterations). `run_all.sh` alone takes about 25 min (18 min of it is `fix_safety_wide`), plus about
25 min with `--live`.
