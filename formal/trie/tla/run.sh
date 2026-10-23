#!/bin/sh
# usage: run.sh <Spec> <cfg-name> ; output in out/<cfg-name>.log
cd "$(dirname "$0")"
java -XX:+UseParallelGC -cp ../../.tools/tla2tools.jar tlc2.TLC -workers ${WORKERS:-auto} -deadlock \
  -metadir "out/meta-$2" -config "cfg/$2.cfg" "$1" > "out/$2.log" 2>&1
tail -n 40 "out/$2.log"
