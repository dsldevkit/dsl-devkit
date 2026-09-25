#!/bin/sh
# usage: [NODL=1] run.sh <label> <Fixes> <Planted> <Refs> <MaxRuns> <MaxSwitch> <INVARIANT...>
#   e.g. run.sh orig '{}' FALSE '<<"u1","u2">>' 2 2 NoLostRef
# Generates runs/<name>.tla/.cfg (MC wrapper: cfg files cannot hold sequences) and runs TLC.
# BFS => the first counterexample reported is a shortest one. NODL=1 disables TLC's deadlock check
# (the NoDeadlock invariant still catches the syncExec/listeners-lock deadlock).
cd "$(dirname "$0")"
label=$1; fixes=$2; planted=$3; refs=$4; runs=$5; sw=$6; shift 6
name="${NODL:+nodl_}MC_${label}_$(echo "$refs" | tr -dc 'a-z0-9')_r${runs}_s${sw}_$(echo "$*" | tr ' ' '_')"
mkdir -p runs meta
cat > "runs/$name.tla" <<EOT
---- MODULE $name ----
EXTENDS FindRefs
RefsVal == $refs
FixesVal == $fixes
====
EOT
{
  echo "SPECIFICATION Spec"
  echo "CONSTANTS"
  echo "  Refs <- RefsVal"
  echo "  Fixes <- FixesVal"
  echo "  Planted = $planted"
  echo "  MaxRuns = $runs"
  echo "  MaxSwitch = $sw"
  echo "INVARIANTS TypeOK"
  for i in "$@"; do echo "  $i"; done
} > "runs/$name.cfg"
start=$(date +%s)
java -XX:+UseParallelGC -DTLA-Library="$PWD" -cp ../../.tools/tla2tools.jar tlc2.TLC ${NODL:+-deadlock} -workers auto \
  -metadir "meta/$name" -config "runs/$name.cfg" "runs/$name.tla" > "runs/$name.out" 2>&1
echo "== $name ($(( $(date +%s) - start ))s)"
grep -E "Invariant .* is violated|Deadlock reached|Model checking completed|distinct states found|Error:|depth of the complete" "runs/$name.out" | grep -v 'behavior up to'
