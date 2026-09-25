# Release/snapshot pipeline: Lean 4 bounded model check

**Code under study:** `.github/workflows/{release,snapshot}.yml`, `.github/scripts/{publish-p2-ghpages,cleanup-p2-snapshots}.sh` and the baseline properties/gate in `ddk-parent/pom.xml` (lines 64-65, 250-261 and 387-393). `verify.yml` only matters because it uses the same baseline.

**Tool:** Lean 4 `v4.35.0-rc2`, no Mathlib, no dependencies. The checker is an explicit state machine with an exhaustive BFS to a fixpoint. Verdict vectors are proved with `native_decide`.

**Wall time:** about 70 min for the agent, including the model-size tuning. The checks themselves take about 65 s for `report` (all 11 configurations) and 27 s for `lake build Results` (10 theorems).

## Layout
| File | Lines | Content |
|---|---|---|
| `Pipeline/Model.lean` | 483 | State, steps (each annotated with file:line), fix flags, initial state |
| `Pipeline/Check.lean` | 196 | BFS, backward EF closure, properties, witnesses, shortest traces |
| `Pipeline/Configs.lean` | 19 | The 11 checked configurations |
| `Results.lean` | 62 | 10 `native_decide` theorems pinning each verdict vector |
| `Report.lean` | 29 | `lake exe report` prints stats and shortest traces |
| `report.txt` | | Output of the last run |

Reproduce with `lake build`, which also proves the theorems, then `.lake/build/bin/report` (add `--witness-traces` or `--only=N`).

## Abstract state
- **Commits** with parent and feature/bundle version (`maj.0.pat`).
- **Branches:** heads for `master` (19.x line), `v18.x` (the maintenance line, cut from the v18.0.0 tag) and `feature` (a topic branch that is only snapshot-dispatched).
- **Tags** `(version, commit)`.
- **gh-pages:**
  - `snaps`: the snapshot dirs, newest first (the `.created` order).
  - `latest`: the `snapshots/latest` child.
  - `rels`: the release dirs with the commit whose snapshot was copied. `releases/latest` is derived as the highest.
- **GitHub release objects.**
- **Concurrency groups:** each has a running slot and a pending slot. Runs carry a pc, their checked-out sha/version, the computed next version and their local gh-pages view.
- **"Re-run failed jobs" records:** failed release runs whose `create_tag` job succeeded.
- **Push budget** for developer pushes.

Dispatches and reruns are unbounded. The state space stays finite because versions are bounded by the pushes.

**Initial state:** mirrors upstream today. v18.0.0 and v19.0.0 are tagged and published. Master sits at 19.0.1 with a snapshot. The maintenance branch has been bumped to 18.0.1. The feature branch has one commit.

## Assumptions (and why they are faithful)
- **A1: environmental failures.** Any step may fail for environmental reasons (flaky test, push rejected three times, runner loss) with no effect. Only the read-only release `prepare` step has no separate env-failure edge, because its effect equals a check failure there.
- **A2: GitHub concurrency semantics.** A run in a busy group becomes pending, and a newer pending run cancels the older pending one (`cancel-in-progress: false` only protects the running one). `release.yml:21-23` and `snapshot.yml:13-15` share the group `publish-ghpages-${repo}`, and it is held for the whole run, both release jobs included.
- **A3: partial-order reduction.**
  - Read-only steps of a job are fused into one atomic step at checkout time: release `prepare` is `release.yml:36-104`, and snapshot `build` is `snapshot.yml:24-99`.
  - Publish+cleanup (snapshot) and publish+gh-release (release) are fused as well, keeping the "first half ok, second half failed" outcome.
  - Under the single group this is exact, since no other writer can interleave.
- **A4: every developer push changes bundle content.** The Tycho gate therefore rejects the same x.y.z with a different commit ("Only qualifier changed" / "different content") and rejects a lower x.y.z ("Version has moved backwards"). Both were confirmed from `CompareWithBaselineMojo` bytecode (tycho-p2-extras 5.0.4, `javap` offsets 421-483 and 730-826), and `onIllegalVersion` defaults to `fail` (plugin.xml). A developer bumps only after the current version is tagged, and only the patch component is bumped.
- **A5: faithful gh-pages push (`cas`).** The rebase in `publish-p2-ghpages.sh:117-124` conflicts on the always-regenerated `index.html`, so a stale push fails instead of merging. Under the single group this never triggers.
- **A6: snapshot checkout reads the tip at run time.** The snapshot run uses `ref: github.ref` (a branch), so it builds the tip when it runs, not the pushed sha (`snapshot.yml:26`).
- **A7: release dispatch targets.** Releases are dispatched only for `master` and `v18.x`, never for the topic branch.
- **Bounds:** push budget 0-3 and KEEP 1-2 (the real value is 20). Eviction needs KEEP+ newer snapshots, and the mechanism does not depend on K.

## Properties
| Id | Property | Kind |
|---|---|---|
| P1 | When idle, every tag has `p2/releases/<v>/` | safety (all reachable states) |
| P2 | `snapshots/latest` only points at a master build | safety |
| P3 | A release run's next version is in its own branch's major line | safety |
| P4 | No release repo ever pushed disappears (no lost content) | safety (ghost `pubRels`) |
| P5 | When idle and no snapshot run failed, master's head has a snapshot | safety |
| P6 | A tag and its release repo name the same commit | safety |
| P7 | `snapshots/latest` points at an existing dir | safety |
| R1 | From every reachable state, a state where every tag is published is reachable (no stuck state needing manual repair) | EF via backward fixpoint over the full graph |
| R2 | From every state with push budget left, a further master release is reachable | EF per tag level |
| W1-W6 | Master release, maintenance release, rerun completes a half-finished release, latest moves, maintenance snapshot published, second master release | reachability witnesses |

Everything is checked exhaustively on the bounded instance. It is bounded model checking, not an all-sizes proof. The `native_decide` theorems certify the exact verdict vector of each configuration.

## Stats
| Config | States | Edges | Depth | Time |
|---|---|---|---|---|
| faithful, budget 2, keep 2 | 254,276 | 2.28M | 39 | 1.5 s |
| faithful, budget 1, keep 1 | 16,991 | 142k | 28 | 0.07 s |
| fixed A-F, budget 1, keep 2 | 316,660 | 3.02M | 29 | 2.7 s |
| fixed A-F, budget 1, keep 1 | 286,825 | 2.74M | 29 | 2.5 s |
| planted (fixed + force push), budget 0 | 316,946 | 3.28M | 33 | 2.9 s |
| faithful + D only, budget 1 | 94,522 | 793k | 31 | 0.4 s |
| faithful + C only, budget 1 | 19,849 | 166k | 28 | 0.1 s |
| faithful + C+D, budget 1 | 1,629,536 | 15.4M | 43 | 11.9 s |
| faithful + A only, budget 2 | 42,351 | 310k | 27 | 0.2 s |
| fixes A-D,F without E (single group), budget 2 | 488,098 | 3.05M | 35 | 2.7 s |
| same, budget 3, keep 1 | 5,494,784 | 34.3M | 40 | 38 s |

The full fix with per-branch groups (E) at budget 2 exceeds 5M states, so it was not run. The E-less variant covers budget 2 and 3.

## Findings on the code as written
The shortest traces are in `report.txt`. Every violation was re-read against the real code.

### F1: a release tag can exist without a release repo, and it can become permanently stuck (P1, R1). CONFIRMED
**Trace (P1, 4 steps):**
1. Dispatch a release on master.
2. `prepare` computes v19.0.1 (`release.yml:44-57`); the snapshot and `feature.xml` checks pass (`:74-104`).
3. The tag is created and pushed (`:106-112`).
4. The publish job's `publish-p2-ghpages.sh` fails (`:130-135`), for example because the push to gh-pages is rejected three times (`publish-p2-ghpages.sh:117-126`).

The tag is now public, but `p2/releases/v19.0.1/` does not exist.

**Stuck (R1, 10 steps):** the same failure, followed by two newer snapshots (a master push and a manual feature snapshot). `cleanup-p2-snapshots.sh:15-26` evicts snapshot `2`, which is the tagged commit.

Afterwards no path leads back:
- **"Re-run failed jobs"** fails at `cp -r "p2/snapshots/$SNAPSHOT_SHA/."` (`publish-p2-ghpages.sh:57`) under `set -e`.
- **"Re-run all jobs" or a new dispatch** recomputes LATEST as v19.0.1 and so computes v19.0.2 (`release.yml:44,55`). That fails the `feature.xml` check (`:87-104`), or the tag push if a bump happens.

v19.0.1 stays dangling until someone repairs it by hand (delete the tag, or hand-publish).

With the real KEEP=20, twenty newer snapshots are needed. The rerun window also closes by itself, because GitHub's re-run limit is 30 days.

**Fix A:**
- Publish the release repo first, push the tag last, and make `gh release create` idempotent.
- `prepare` resumes an untagged `p2/releases/<next>/`: it tags the commit recorded for that repo instead of the tip.

**Model-found caveat:** without the resume step, fix A alone breaks R2.
1. The release repo is published, which moves `releases/latest`, the Tycho baseline.
2. The tag push fails.
3. A later master commit at the same version now fails the gate ("only qualifier changed").
4. A bump cannot help, because the next version is still derived as 19.0.1.

With the resume step, P1, R1 and R2 all hold (`fixA_only`, `fixes_without_E`).

### F2: `snapshots/latest` can point at a non-master build (P2). CONFIRMED
**Trace (3 steps):**
1. `workflow_dispatch` the snapshot with `branch=feature` (`snapshot.yml:4-9, 26`).
2. The build passes the gate: feature 19.0.1 is above baseline 19.0.0.
3. `publish-p2-ghpages.sh:72` rewrites `p2/snapshots/latest` unconditionally.

Pushes to `v[0-9]*` branches (`snapshot.yml:10-11`) would do the same, but today they are masked by F4. With fix D alone, the shortest trace becomes "push v18.x, then build, then publish".

`snapshot.repo.url` (`pom.xml:65`) is also the `tycho-p2-plugin` baseline (`pom.xml:387-393`; p2-metadata defaults `baselineMode=warn`, `baselineReplace=all`, from plugin.xml 5.0.4). A non-master latest therefore also feeds the artifact-replacement baseline of every build. The impact is limited to identical fully qualified versions, so that part is PLAUSIBLE and was not modelled.

**Fix B:** only update the latest composite when the built ref is master.

### F3: the next release version ignores the branch's own tag line (P3). CONFIRMED
**Trace (1 step):** dispatch a release with `branch=v18.x`. `release.yml:44` runs `git tag --list 'v*' --sort=-version:refname | head -1` over all tags (the clone uses `fetch-depth: 0`, `:39`), so the next version is v19.0.1 instead of v18.0.1. The `feature.xml` check (`:87-104`) then always fails on a maintenance branch.

**Fix C:** add `--merged HEAD` to the tag listing.

### F4: maintenance snapshots can never be built (W5 unreachable), so maintenance releases are impossible (W2 unreachable). CONFIRMED
- `baseline.repo.url` is `p2/releases/latest/` (`pom.xml:64`), the highest release overall (`publish-p2-ghpages.sh:69-70`), and the gate runs in every build (`pom.xml:250-261`).
- A v18.x build has feature 18.0.1 below baseline 19.0.0, so the gate reports "Version has moved backwards" and fails (bytecode 421-483; `onIllegalVersion` defaults to `fail` and the pom does not override it).
- So `snapshot.yml` fails on every push to a `v[0-9]*` branch, no snapshot exists, and the release check (`release.yml:74-85`) fails.

The model shows that the two blockers are independent:
- **D only** (per-line baseline): maintenance snapshots become reachable, but maintenance releases do not, because of F3.
- **C only:** neither becomes reachable.
- **C+D:** a maintenance release becomes reachable (theorems `fixD_only`, `fixC_only`, `fixCD`).

**Fix D:** a per-major-line `releases/latest-<major>` composite used as the baseline on maintenance branches, or a branch-specific `baseline.repo.url`.

### F5: a release dispatch (or any other run) cancels the pending snapshot of master's head (P5). CONFIRMED
This relies on documented GitHub concurrency semantics.

**Trace (5 steps):**
1. Release R1 is running.
2. A push to master queues snapshot S as pending.
3. A second release dispatch R2 replaces S in the pending slot, so S is cancelled (`release.yml:21-23` and `snapshot.yml:13-15` share one group).
4. R1 checks out the new tip, finds no snapshot and fails (`release.yml:74-85`).
5. R2 fails the same way.

Everything is now idle and no snapshot run failed, yet master's head has no snapshot. `snapshots/latest` lags until the next push or a manual snapshot dispatch, and releases of that head are blocked in the meantime. A push to the maintenance or topic branch has the same effect. It is recoverable by hand (dispatch a snapshot).

**Fix E:**
- Use a concurrency group per branch for snapshots and a separate one for releases.
- Make gh-pages writes regenerate on retry (fetch, reset, re-run the script) instead of `git rebase`, which conflicts.
- Add **fix F:** cleanup never deletes a branch's newest snapshot. Otherwise the per-branch groups let maintenance snapshots evict master's head snapshot and the `latest` target, which breaks P5 and P7. The model caught this on a first, mis-coded version of fix F.

### Properties that hold on the code as written
- **P4, no lost content:** the single concurrency group serializes every gh-pages writer, and a stale push would conflict on `index.html` rather than overwrite.
- **P6:** a tag and its release repo name the same commit.
- **P7:** `snapshots/latest` points at an existing dir.
- **R2:** master can always release again after a version bump.
- **W1, W3, W4, W6** are reachable. W3 means that "Re-run failed jobs" completes a half-finished release while the snapshot still exists.

### Not modelled, noted from reading the code
**PLAUSIBLE, low severity:**
- `git rev-parse --short=8` (`release.yml:63`, `snapshot.yml:34`) can grow past 8 characters when the object store makes it ambiguous. The snapshot dir name and the release lookup are computed in different clones at different times, so they could differ.
- `gh release create` (`release.yml:145`) is not idempotent. If it fails half-way, a rerun can error with "already exists".

## Sanity
- **Fixed model:** fixes A (with resume) through F pass all 7 safety properties and R1/R2, with all 6 witnesses reachable, at keep 1 and keep 2 (`fixed_verdicts`, `fixed_keep1_verdicts`). The E-less variant also passes everything except P5 at budgets 2 and 3.
- **Planted bug:** the fixed model with a blind force push of the locally computed tree is caught. P4 fails when a snapshot push computed from a stale view erases `releases/v19.0.1`, and P1/R1 fail with it (`planted_caught`).
- **Witnesses:** in the faithful model W1, W3, W4 and W6 are reachable. W2 and W5 are unreachable, and that is finding F4.
- **Discrimination:** each single-fix variant flips exactly the verdicts its fix targets.
