#!/bin/sh
# Re-runs every model-checking configuration; prints verdict, distinct states, wall time.
cd "$(dirname "$0")"
W=${WORKERS:-1}
while read spec cfg expect; do
  [ -z "$spec" ] && continue
  s=$(date +%s)
  WORKERS=$W ./run.sh "$spec" "$cfg" >/dev/null 2>&1
  e=$(( $(date +%s) - s ))
  res=$(grep -Eo 'Invariant [A-Za-z0-9]+ is violated|invariant [A-Za-z0-9]+ failed|No error has been found' "out/$cfg.log" | head -1)
  st=$(grep -Eo '[0-9]+ distinct states (found|generated)' "out/$cfg.log" | tail -1)
  printf '%-13s %-14s expect=%-5s | %-45s | %-28s | %ss\n' "$spec" "$cfg" "$expect" "$res" "$st" "$e"
done <<LIST
PatternBounds pb_rs       FAIL
PatternBounds pb_rs2      PASS
PatternBounds pb_ts       FAIL
PatternBounds pb_ord      FAIL
PatternBounds pb_cnt1     FAIL
PatternBounds pbg_rs      FAIL
PatternBounds pbg_rs_fb   PASS
PatternBounds pbg_rs_fbA  FAIL
PatternBounds pbg_exc     FAIL
PatternBounds pb_fixed    PASS
PatternBounds pbg_fixed   PASS
PatternBounds pb_plant    FAIL
PatternBounds pb_wit      FAIL
MC_TQ         tq_spec     FAIL
MC_TQ         tq_star     FAIL
MC_TQ         tq_ref      FAIL
MC_TQ         tq_refspec  FAIL
MC_TQ         tq_exact    PASS
MC_TQ2        tq2_star    PASS
MC_TQ2        tq2_ref     FAIL
MC_TQ         tq_fixed    PASS
MC_TQ         tq_fixed_ref PASS
MC_TQ         tq_plant    FAIL
MC_TQ         tq_wit      FAIL
MC_Cons       co_cs       FAIL
MC_Cons       co_ci       FAIL
MC_Cons       co_ci_fb    PASS
MC_Cons       co_cs_fb    FAIL
MC_Cons       co_fixed    PASS
MC_Cons       co_wit      FAIL
MC_Ops        op_sizetree FAIL
MC_Ops        op_sizespec FAIL
MC_Ops        op_nodup    FAIL
MC_Ops        op_exact    FAIL
MC_Ops        op_exactref PASS
MC_Ops        op_map      FAIL
MC_Ops        op_share    FAIL
MC_Ops        op_copy     FAIL
MC_Ops        op_bagns    PASS
MC_Ops        op_fixed    PASS
MC_Ops        op_fixed_sh PASS
MC_Ops        op_plant    FAIL
MC_Ops        op_wit1     FAIL
MC_Ops        op_wit2     FAIL
LIST
