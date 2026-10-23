#!/bin/sh
# usage: ./run.sh Spec.tla Config.cfg  (runs from this directory)
cd "$(dirname "$0")"
spec=$1; cfg=$2
exec java -XX:+UseParallelGC -cp ../../.tools/tla2tools.jar tlc2.TLC -workers auto \
  -metadir "states/$(basename "$cfg" .cfg)" -config "$cfg" "$spec"
