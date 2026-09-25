#!/bin/bash
# Full check matrix: original model per property, fixed model, ablations, planted bug, witnesses.
cd "$(dirname "$0")"
REFS=${REFS:-'<<"u1","u2">>'}; RUNS=${RUNS:-2}; SW=${SW:-2}
ALL='{"flag","dedupe","snapshot","reset","plock","detach"}'
PROPS="NoLostRoot NoLostRef OneRootPerUri OneRootCreated NoDupRef NoStale NoForeign NoCME NoDeadlock"
echo "### original code, one property per run (shortest trace each)"
for p in $PROPS; do NODL=1 ./run.sh orig '{}' FALSE "$REFS" $RUNS $SW $p; done
./run.sh orig '{}' FALSE "$REFS" $RUNS $SW TypeOK
echo "### fixed model, all properties + TLC deadlock check"
./run.sh fixed "$ALL" FALSE "$REFS" $RUNS $SW $PROPS
echo "### ablations: fixed minus one fix"
for f in flag dedupe snapshot reset plock detach; do
  set=$(echo "$ALL" | sed "s/\"$f\",\{0,1\}//; s/,}/}/")
  ./run.sh "no_$f" "$set" FALSE "$REFS" $RUNS $SW $PROPS
done
echo "### planted bug on the fixed model"
./run.sh planted "$ALL" TRUE "$REFS" $RUNS $SW $PROPS
echo "### witnesses (each must be VIOLATED = reachable)"
for w in W_AllShown W_RerunShown W_SwitchBack W_ResetRan; do
  NODL=1 ./run.sh wit_orig '{}' FALSE "$REFS" $RUNS $SW $w
  NODL=1 ./run.sh wit_fixed "$ALL" FALSE "$REFS" $RUNS $SW $w
done
NODL=1 ./run.sh wit_orig '{}' FALSE "$REFS" $RUNS $SW W_Rescheduled
