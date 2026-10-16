#!/bin/sh
# Re-runs the whole matrix and prints one line per run: spec cfg result states time.
cd "$(dirname "$0")"
run() {
  s=$(date +%s)
  r=$(./run.sh "$1" "$2" 2>&1 | rg -o 'Invariant \w+ is violated|Temporal properties were violated|No error has been found|^\d+ distinct states found|^\d+ states generated, \d+ distinct' | tr '\n' ' ')
  printf '%-34s %-26s %s (%ss)\n' "$(basename "$1" .tla)" "$(basename "$2" .cfg)" "$r" "$(( $(date +%s) - s ))"
}
for c in cfg/bk_*.cfg cfg/qc_*.cfg cfg/abort_*.cfg cfg/live_*.cfg cfg/leak_*.cfg cfg/big_*.cfg; do run ParallelLoader.tla "$c"; done
for c in cfg/full_*.cfg; do run ParallelLoaderFixed.tla "$c"; done
for v in AblateFix1 AblateFix2 AblateFix3; do for c in cfg/full_s_*.cfg cfg/full_m_arr1.cfg; do run variants/$v.tla "$c"; done; done
for c in cfg/full_s_*.cfg cfg/planted_abort.cfg; do run variants/ParallelLoaderPlanted.tla "$c"; done
rm -rf states
