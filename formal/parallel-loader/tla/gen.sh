#!/bin/sh
# gen.sh NAME QCAP NWORKERS URIS INITQ MAXOPS MAXBINT CANCEL SWALLOW "INVARIANTS..." "PROPERTIES..."
cd "$(dirname "$0")"
{
  echo "CONSTANTS"
  echo "  URIs = {$4}"
  echo "  InitQueue = {$5}"
  echo "  NWorkers = $3"
  if [ "$2" = "-1" ]; then echo "  QCap <- Unbounded"; else echo "  QCap = $2"; fi
  echo "  MaxOps = $6"
  echo "  MaxBuilderInterrupts = $7"
  echo "  AllowCancel = $8"
  echo "  LoadMaySwallowInterrupt = $9"
  echo "SPECIFICATION Spec"
  echo "CHECK_DEADLOCK FALSE"
  [ -n "${10}" ] && echo "INVARIANTS ${10}"
  [ -n "${11}" ] && echo "PROPERTIES ${11}"
} > "cfg/$1.cfg"
