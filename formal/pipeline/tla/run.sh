#!/usr/bin/env bash
# Usage: run.sh NAME FIX PLANT CHECKCALM GOAL KIND(INVARIANT|PROPERTY) NAMES [push disp fail rerun expire slots keep]
# Writes cfg/NAME.cfg, runs TLC, log in logs/NAME.log.
set -uo pipefail
HERE="$(cd "$(dirname "$0")" && pwd)"
JAR="$HERE/../../.tools/tla2tools.jar"
NAME=$1; FIX=$2; PLANT=$3; CALM=$4; GOAL=$5; KIND=$6; PROPS=$7
PUSH=${8:-1}; DISP=${9:-2}; FAIL=${10:-1}; RERUN=${11:-1}; EXPIRE=${12:-1}; SLOTS=${13:-3}; KEEP=${14:-1}
mkdir -p "$HERE/cfg" "$HERE/logs" "$HERE/states"
CFG="$HERE/cfg/$NAME.cfg"
{
  echo "SPECIFICATION Spec"
  echo "CONSTANTS"
  echo "  FIX = $FIX"
  echo "  PLANT = \"$PLANT\""
  echo "  KEEP = $KEEP"
  echo "  NSlots = $SLOTS"
  echo "  MaxPush = $PUSH"
  echo "  MaxDispatch = $DISP"
  echo "  MaxFail = $FAIL"
  echo "  MaxRerun = $RERUN"
  echo "  MaxExpire = $EXPIRE"
  echo "  CheckCalm = $CALM"
  echo "  GoalBranch = \"$GOAL\""
  for p in $PROPS; do echo "$KIND $p"; done
} > "$CFG"
cd "$HERE"
START=$(date +%s)
java -XX:+UseParallelGC -cp "$JAR" tlc2.TLC -workers auto -deadlock \
  -metadir "$HERE/states/$NAME" -config "$CFG" Pipeline.tla > "logs/$NAME.log" 2>&1
RC=$?
END=$(date +%s)
echo "$NAME rc=$RC wall=$((END-START))s $(grep -E 'distinct states found|is violated|No error has been found|Error:' "logs/$NAME.log" | head -3 | tr '\n' ' ')"
rm -rf "$HERE/states/$NAME"
