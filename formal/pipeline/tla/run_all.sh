#!/usr/bin/env bash
# Re-runs every check reported in NOTES.md. Safety/witness/plant runs take ~5 min in total;
# pass --live to also run the five liveness checks (~25 min); FULL=1 adds fix_safety_wide (~18 min).
set -u
cd "$(dirname "$0")"
INV="TagHasRepo LatestFromMaster OwnLineVersion RelLatestIsMax LatestExists IndexNoDangling NoLostContent NoCrossRefCancel RerunProgress"
# code as written: one run per property so each counterexample is the shortest (BFS)
for p in TagHasRepo TagHasRepoOrLive LatestFromMaster OwnLineVersion RelLatestIsMax LatestExists IndexNoDangling NoLostContent NoCrossRefCancel; do
  ./run.sh "asis_$p" FALSE none FALSE master INVARIANT "$p"
done
./run.sh asis_RerunProgress FALSE none TRUE master INVARIANT RerunProgress
./run.sh asis_MaintOnOwnLine FALSE none FALSE master INVARIANT MaintOnOwnLine
./run.sh fix_MaintOnOwnLine TRUE none FALSE master INVARIANT MaintOnOwnLine
# fixed model: all invariants, both calm goals, and a wider dispatch/slot bound
./run.sh fix_safety TRUE none TRUE master INVARIANT "$INV"
./run.sh fix_safety_maint TRUE none TRUE maint INVARIANT "$INV"
[ -n "${FULL:-}" ] && ./run.sh fix_safety_wide TRUE none FALSE master INVARIANT "$INV" 1 3 1 1 1 4
# planted bugs (must be caught) + control for the first one
./run.sh plant_relLatest TRUE relLatest FALSE master INVARIANT RelLatestIsMax 1 3 0 0 0 4
./run.sh fix_relLatest_ctrl TRUE none FALSE master INVARIANT RelLatestIsMax 1 3 0 0 0 4
./run.sh plant_cleanup TRUE cleanup FALSE master INVARIANT LatestExists
# witnesses (must be violated = good path reachable)
for w in W_MasterRelease W_MaintRelease W_Evicted; do
  ./run.sh "asis_$w" FALSE none FALSE master INVARIANT "$w"
  ./run.sh "fix_$w" TRUE none FALSE master INVARIANT "$w"
done
./run.sh fix_W_CalmGoal_maint TRUE none TRUE maint INVARIANT W_CalmGoal
if [ "${1:-}" = "--live" ]; then
  ./run.sh fix_live_master TRUE none TRUE master PROPERTY "LGoal LTags"
  ./run.sh fix_live_maint TRUE none TRUE maint PROPERTY "LGoal LTags"
  ./run.sh asis_live_maint FALSE none TRUE maint PROPERTY LGoal
  ./run.sh asis_live_tags FALSE none TRUE master PROPERTY LTags
  ./run.sh asis_live_master FALSE none TRUE master PROPERTY LGoal
fi
