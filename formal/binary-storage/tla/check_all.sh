#!/bin/sh
# Regenerates every configuration and runs TLC on it; prints one summary line per run.
# Runs taking a minute or more only run with FULL=1 (orig_live_M alone is ~9 min).
cd "$(dirname "$0")"
SAFE="TypeOK NotSourceOnlyWhenStored StoresAccounted LoaderNoPartialRead MainNoPartialRead NoStaleRead SetThreadSafe NoDetachedStore"
LIVE="BuildTerminates WorkersQuiesce StoreResolved"
WIT="WitnessDone WitnessSecondClust WitnessCallerRuns WitnessTimeout WitnessGoodRead WitnessStoreOk"
run() { # name size nstore qcap maxto ldeps lfail linkfail fixA fixB fixC fixD plant "inv" "props"
  n=$1; shift
  ./gen.sh "$n" "$@"
  out=$(./run.sh "cfg/$n.cfg" 2>&1)
  res=$(echo "$out" | grep -E "^Error: (Invariant|Temporal|Deadlock)|is violated" | head -1)
  [ -z "$res" ] && res=$(echo "$out" | grep -q "No error has been found" && echo "OK" || echo "$out" | grep -E "Error" | head -1)
  st=$(echo "$out" | grep -E "distinct states found" | tail -1 | sed -E 's/.* ([0-9]+) distinct states found.*/\1/')
  t=$(echo "$out" | grep -E "^Finished in" | sed -E 's/Finished in ([^ ]+).*/\1/')
  printf '%-28s %-58s %8s distinct %s\n' "$n" "$res" "$st" "$t"
}
# --- the code as written (all environment behaviours on) -----------------------------------------
for p in $SAFE; do run "orig_$p" S 1 1 1 TRUE TRUE TRUE FALSE FALSE FALSE FALSE FALSE "$p" ""; done
run orig_live       S 1 1 1 TRUE TRUE TRUE FALSE FALSE FALSE FALSE FALSE "" "$LIVE"
[ -n "${FULL:-}" ] && run orig_live_M     M 1 1 1 TRUE FALSE FALSE FALSE FALSE FALSE FALSE FALSE "" "$LIVE"
# happy environment: no timeouts, no failures, loaders do not load dependencies
for p in NotSourceOnlyWhenStored LoaderNoPartialRead MainNoPartialRead NoStaleRead SetThreadSafe NoDetachedStore; do
  run "happy_$p" M 2 1 0 FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE "$p" ""; done
# --- fixed model: FixA..FixD, every environment behaviour on, growing sizes ----------------------
run fixed_S   S 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" "$LIVE"
run fixed_M   M 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" "$LIVE"
[ -n "${FULL:-}" ] && run fixed_M2  M 2 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" "$LIVE"
[ -n "${FULL:-}" ] && run fixed_L   L 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" "$LIVE"
run fixed_L2  L 2 1 2 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" ""
[ -n "${FULL:-}" ] && run fixed_R   R 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$SAFE" "$LIVE"
# --- ablations: fixed model with one fix undone ----------------------------------------------------
run ablateA   M 1 1 1 TRUE TRUE TRUE FALSE TRUE TRUE TRUE FALSE "$SAFE" ""
run ablateB   M 1 1 1 TRUE TRUE TRUE TRUE FALSE TRUE TRUE FALSE "$SAFE" ""
run ablateC   M 1 1 1 TRUE TRUE TRUE TRUE TRUE FALSE TRUE FALSE "$SAFE" ""
run ablateD   M 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE FALSE FALSE "$SAFE" ""
# --- planted bug in the fixed model ------------------------------------------------------------------
run planted   S 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE "$SAFE" ""
# --- witnesses (each must be violated) -------------------------------------------------------------
for w in $WIT; do run "wit_orig_$w"  R 1 1 1 TRUE TRUE TRUE FALSE FALSE FALSE FALSE FALSE "$w" ""; done
for w in $WIT; do run "wit_fixed_$w" R 1 1 1 TRUE TRUE TRUE TRUE TRUE TRUE TRUE FALSE "$w" ""; done
