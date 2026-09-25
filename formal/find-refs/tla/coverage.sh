#!/bin/sh
# Sanity: every action of the model is taken at least once (TLC -coverage), orig and fixed.
cd "$(dirname "$0")"
mkdir -p runs meta
for cfg in orig fixed; do
  if [ $cfg = orig ]; then f='{}'; else f='{"flag","dedupe","snapshot","reset","plock","detach"}'; fi
  name=MC_cov_$cfg
  printf -- "---- MODULE $name ----\nEXTENDS FindRefs\nRefsVal == <<\"u1\",\"u2\">>\nFixesVal == $f\n====\n" > runs/$name.tla
  printf "SPECIFICATION Spec\nCONSTANTS\n  Refs <- RefsVal\n  Fixes <- FixesVal\n  Planted = FALSE\n  MaxRuns = 2\n  MaxSwitch = 2\nINVARIANTS TypeOK\n" > runs/$name.cfg
  java -DTLA-Library="$PWD" -cp ../../.tools/tla2tools.jar tlc2.TLC -deadlock -coverage 60 -workers 1 \
    -metadir meta/$name -config runs/$name.cfg runs/$name.tla > runs/$name.out 2>&1
  echo "== $cfg: $(grep -E 'distinct states found' runs/$name.out)"
  echo "   actions never taken: $(grep -E '^<[A-Za-z]+ line' runs/$name.out | grep -E ': 0:[0-9]+$' | sed 's/^<\([A-Za-z]*\) .*/\1/' | tr '\n' ' ')"
done
