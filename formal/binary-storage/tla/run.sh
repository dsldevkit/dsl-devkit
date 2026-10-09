#!/bin/sh
# usage: ./run.sh cfg/X.cfg [extra TLC args]   (checks MC.tla, i.e. BinaryStorage with the size definitions)
cd "$(dirname "$0")"
cfg=$1; shift
exec java -XX:+UseParallelGC -cp ../../.tools/tla2tools.jar tlc2.TLC -workers auto \
  -metadir "states/$(basename "$cfg" .cfg)" -config "$cfg" "$@" MC.tla
