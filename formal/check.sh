#!/usr/bin/env bash
# Local validation harness for formal/: TLC matrices vs golden verdicts, Lean builds and axioms, orphan-test check.
# Usage: formal/check.sh [--clean] [--full] [--update] [--only=<target>|orphans]
#   --clean   delete .lake/, states/ and TLC metadirs first
#   --full    also run the expensive TLC runs (tagged [full] in expected.txt)
#   --update  rewrite expected.txt / theorems.txt / orphans expected.txt from this run (review the diff!)
set -u
F="$(cd "$(dirname "$0")" && pwd)"
TARGETS="parallel-loader binary-storage find-refs trie pipeline"
JAR="$F/.tools/tla2tools.jar"
JAR_SHA=936a262061c914694dfd669a543be24573c45d5aa0ff20a8b96b23d01e050e88
LEAN_TC=leanprover/lean4:v4.35.0-rc2
OUT="$F/.check"
FULL= CLEAN= UPDATE= ONLY=
for a in "$@"; do
  case $a in
    --full) FULL=1 ;; --clean) CLEAN=1 ;; --update) UPDATE=1 ;; --only=*) ONLY=${a#--only=} ;;
    -h|--help) sed -n '2,7p' "$0" | sed 's/^# \{0,1\}//'; exit 0 ;;
    *) echo "unknown argument: $a" >&2; exit 2 ;;
  esac
done
case " $TARGETS orphans " in *" ${ONLY:-orphans} "*) ;; *) echo "unknown target: $ONLY" >&2; exit 2 ;; esac
[ -n "$ONLY" ] && TARGETS=${ONLY#orphans}
export FULL

if [ -n "$CLEAN" ]; then
  echo "cleaning .lake/, states/, TLC metadirs"
  find "$F" -type d \( -name .lake -o -name states -o -name 'meta-*' \) -prune -exec rm -rf {} +
  rm -rf "$F/find-refs/tla/meta" "$OUT"
fi
mkdir -p "$OUT"
T0=$(date +%s)
SUMMARY="$OUT/summary.txt"; : > "$SUMMARY"
result() { printf '%-28s %-4s %s\n' "$1" "$2" "$3" >> "$SUMMARY"; }

# ---------------------------------------------------------------- TLA+
# Maps a TLC log (stdin) to OK | VIOLATED:<Prop> | DEADLOCK | ERROR; temporal violations are unnamed by TLC.
VERDICT='
function verdict(s) {
  if (match(s, /Invariant [A-Za-z0-9_]+ is violated/)) return "VIOLATED:" substr(s, RSTART + 10, RLENGTH - 22)
  if (match(s, /Evaluating invariant [A-Za-z0-9_]+ failed/)) return "VIOLATED:" substr(s, RSTART + 21, RLENGTH - 28)
  if (s ~ /Temporal properties were violated/) return "VIOLATED:TEMPORAL"
  if (s ~ /Deadlock reached/) return "DEADLOCK"
  if (s ~ /No error has been found/) return "OK"
  return "ERROR"
}'
classify() { # name logfile
  if [ -f "$2" ]; then awk -v n="$1" "$VERDICT"' { b = b $0 "\n" } END { print n, verdict(b) }' "$2"; else echo "$1 ERROR"; fi
}

tla_matrix() { # target -> normalised "<run> <verdict>" lines on stdout
  local d="$F/$1/tla"
  case $1 in
    parallel-loader) sh "$d/check_all.sh" | awk "$VERDICT"' NF { print $1 ":" $2, verdict($0) }' ;;
    binary-storage) sh "$d/check_all.sh" | awk "$VERDICT"' NF { print $1, ($2 == "OK" ? "OK" : verdict($0)) }' ;;
    find-refs) bash "$d/matrix.sh" | sed -n 's/^== \([^ ]*\) .*/\1/p' | while read -r n; do classify "$n" "$d/runs/$n.out"; done ;;
    trie) sh "$d/runall.sh" | awk '{ print $2 }' | while read -r n; do classify "$n" "$d/out/$n.log"; done ;;
    pipeline) bash "$d/run_all.sh" ${FULL:+--live} | awk '{ print $1 }' | while read -r n; do classify "$n" "$d/logs/$n.log"; done ;;
  esac
}

tla_ok=1
if ! command -v java >/dev/null; then
  echo "java not found"; tla_ok=
elif [ ! -f "$JAR" ]; then
  echo "missing $JAR; fetch it with:"
  echo "  gh release download v1.7.4 -R tlaplus/tlaplus -p tla2tools.jar -D formal/.tools"
  echo "  expected SHA-256 $JAR_SHA"; tla_ok=
else
  sha=$( (shasum -a 256 "$JAR" 2>/dev/null || sha256sum "$JAR") | cut -d' ' -f1)
  [ "$sha" = "$JAR_SHA" ] || { echo "tla2tools.jar SHA-256 mismatch: $sha (expected $JAR_SHA)"; tla_ok=; }
fi

tla_check() { # target
  local t=$1 gold="$F/$1/tla/expected.txt" got="$OUT/$1.tla.txt" s=$(date +%s)
  [ -n "$tla_ok" ] || { result "$t/tla" FAIL "TLC unavailable"; return; }
  echo "== $t/tla"
  tla_matrix "$t" > "$got"
  if [ -n "$UPDATE" ]; then
    # keep the [full] tags of runs that were skipped this time
    awk 'NR == FNR { seen[$1] = 1; print; next } $3 == "[full]" && !($1 in seen)' "$got" "$gold" 2>/dev/null > "$got.new"
    [ -n "$FULL" ] && [ -f "$gold" ] && awk 'NR == FNR { if ($3 == "[full]") full[$1] = 1; next } { print $0 (($1 in full) ? " [full]" : "") }' "$gold" "$got.new" > "$got.tag" && mv "$got.tag" "$got.new"
    mv "$got.new" "$gold"
  fi
  local want="$OUT/$t.tla.want"
  if [ -n "$FULL" ]; then awk 'NF && $1 !~ /^#/ { print $1, $2 }' "$gold"; else awk 'NF && $1 !~ /^#/ && $3 != "[full]" { print $1, $2 }' "$gold"; fi 2>/dev/null | sort > "$want"
  if diff <(sort "$got") "$want" > "$OUT/$t.tla.diff"; then
    result "$t/tla" PASS "$(wc -l < "$got" | tr -d ' ') runs, $(( $(date +%s) - s ))s"
  else
    sed 's/^/   /' "$OUT/$t.tla.diff"
    result "$t/tla" FAIL "golden mismatch (< got, > expected), $(( $(date +%s) - s ))s"
  fi
}

# ---------------------------------------------------------------- Lean
# Lists every user-written theorem of the package (declaration ranges exclude generated lemmas).
lean_probe() { # libs... -> Lean source on stdout
  echo "import Lean.Elab.Command"
  for l in "$@"; do echo "import $l"; done
  printf 'open Lean in\nrun_cmd do\n  let env ← getEnv\n  let roots : List Name := [%s]\n' "$(printf '`%s, ' "$@" | sed 's/, $//')"
  cat <<'EOF'
  for (n, ci) in env.constants.map₁.toList do
    if let .thmInfo _ := ci then
      unless n.isInternal do
        if let some idx := env.getModuleIdxFor? n then
          if roots.contains env.header.moduleNames[idx.toNat]!.getRoot then
            if (← findDeclarationRanges? n).isSome then IO.println s!"THM {n}"
EOF
}

lean_check() { # target; prints details to stdout, one summary via result()
  local t=$1 d="$F/$1/lean" log="$OUT/$1.lean.log" s=$(date +%s) fail=
  local tc; tc=$(tr -d '[:space:]' < "$d/lean-toolchain")
  [ "$tc" = "$LEAN_TC" ] || { result "$t/lean" FAIL "toolchain $tc, want $LEAN_TC"; return; }
  elan toolchain list 2>/dev/null | grep -q "^$tc" || { result "$t/lean" FAIL "$tc not installed (no downloads)"; return; }
  (cd "$d" && lake build) > "$log" 2>&1 || { tail -20 "$log"; result "$t/lean" FAIL "lake build failed ($log)"; return; }
  # sorry/admit outside comments, or reported by the elaborator
  local holes; holes=$(fd -0 -e lean -E .lake . "$d" | xargs -0 perl -0777 -ne 's{/-.*?-/}{}gs; s{--[^\n]*}{}g; print "$ARGV\n" if /\b(sorry|admit)\b/')
  grep -q "declaration uses 'sorry'" "$log" && holes="$holes (build log)"
  [ -n "$holes" ] && { echo "sorry/admit in: $holes"; fail=1; }
  local libs; libs=$(awk '/^\[/ { sec = $0 } sec == "[[lean_lib]]" && /^name *=/ { gsub(/^name *= *"|"$/, ""); print }' "$d/lakefile.toml")
  local list="$d/theorems.txt" probe="$d/.lake/check_axioms.lean" pout="$OUT/$t.axioms.txt"
  lean_probe $libs > "$probe"
  (cd "$d" && lake env lean "$probe") 2>&1 | awk '/^THM / { print $2 }' | sort > "$OUT/$t.all"
  # #print axioms for every listed and every declared theorem
  { lean_probe $libs; { [ -f "$list" ] && awk 'NF && $1 !~ /^#/ { print $1 }' "$list"; cat "$OUT/$t.all"; } | sort -u | sed 's/^/#print axioms /'; } > "$probe"
  (cd "$d" && lake env lean "$probe") > "$pout" 2>&1
  # one line per theorem: <name> <kernel|native_decide|BAD:axiom>
  awk '
    function flush() { if (n == "") return
      k = "kernel"; split(ax, a, /[][, ]+/)
      for (i in a) if (a[i] != "" && a[i] !~ /^(propext|Classical\.choice|Quot\.sound)$/) {
        if (a[i] ~ /^(Lean\.ofReduceBool|Lean\.trustCompiler)$/ || a[i] ~ /\._native\.native_decide\.ax_[0-9_]+$/) { if (k == "kernel") k = "native_decide" }
        else { k = "BAD:" a[i]; break } }
      print n, k; n = "" }
    /^THM / { next }
    /^'\''/ { flush(); n = $0; sub(/^'\''/, "", n); sub(/'\''.*/, "", n); ax = $0; sub(/^[^:]*:/, "", ax)
              if ($0 ~ /does not depend on any axioms/) ax = ""; next }
    n != "" { ax = ax " " $0 }
    END { flush() }' "$pout" | sort > "$OUT/$t.got"
  [ -n "$UPDATE" ] && { echo "# <theorem> <kernel|native_decide>; checked by formal/check.sh via #print axioms"; cat "$OUT/$t.got"; } > "$list"
  grep -Eq ':[0-9]+:[0-9]+: error' "$pout" && { grep -E ': error' "$pout" | head -5; fail=1; }
  awk 'NF && $1 !~ /^#/ { print $1, $2 }' "$list" | sort > "$OUT/$t.want"
  if ! diff <(awk '{ print $1 }' "$OUT/$t.want") "$OUT/$t.all" > /dev/null; then
    echo "theorem list drift (< theorems.txt, > declared):"; diff <(awk '{ print $1 }' "$OUT/$t.want") "$OUT/$t.all" | grep '^[<>]'; fail=1
  fi
  if ! diff "$OUT/$t.got" "$OUT/$t.want" > "$OUT/$t.lean.diff"; then
    echo "axiom class mismatch (< actual, > theorems.txt):"; grep '^[<>]' "$OUT/$t.lean.diff"; fail=1
  fi
  awk '{ printf "   %-66s %s\n", $1, $2 }' "$OUT/$t.got"
  local nk nn; nk=$(grep -c ' kernel$' "$OUT/$t.got"); nn=$(grep -c ' native_decide$' "$OUT/$t.got")
  result "$t/lean" "$([ -n "$fail" ] && echo FAIL || echo PASS)" "$nk kernel + $nn native_decide theorems, $(( $(date +%s) - s ))s"
}

# ---------------------------------------------------------------- orphaned tests
orphans_check() { # runs on a snapshot of HEAD so uncommitted work in the tree does not change the verdict
  local o="$F/readonly/orphans" snap got="$OUT/orphans.txt" s=$(date +%s)
  snap=$(mktemp -d "${TMPDIR:-/tmp}/formal-check.XXXXXX")
  git -C "$(git -C "$F" rev-parse --show-toplevel)" archive HEAD | tar -x -C "$snap"
  "$o/check-test-reachability.sh" "$snap" > "$OUT/orphans.log" 2>&1; local rc=$?
  rm -rf "$snap"
  { echo "exit $rc"; awk -F'\t' '$1 ~ /UNREACHABLE$/ { print "UNREACHABLE", $3 }' "$OUT/orphans.log" | sort; } > "$got"
  "$o/selftest.sh" > "$OUT/orphans-selftest.log" 2>&1 && echo "selftest exit 0" >> "$got" || echo "selftest exit $?" >> "$got"
  [ -n "$UPDATE" ] && cp "$got" "$o/expected.txt"
  if diff "$got" "$o/expected.txt" > "$OUT/orphans.diff"; then
    result readonly/orphans PASS "exit $rc as expected, $(grep -c ^UNREACHABLE "$got") unreachable, $(( $(date +%s) - s ))s"
  else
    sed 's/^/   /' "$OUT/orphans.diff"; result readonly/orphans FAIL "outcome differs from expected.txt"
  fi
}

# Lean builds run in the background while TLC runs in the foreground.
( for t in $TARGETS; do lean_check "$t" > "$OUT/$t.lean.report" 2>&1; done ) &
LEAN_PID=$!
for t in $TARGETS; do tla_check "$t"; done
[ -z "$ONLY" ] || [ "$ONLY" = orphans ] && orphans_check
wait $LEAN_PID
for t in $TARGETS; do echo "== $t/lean"; cat "$OUT/$t.lean.report"; done

echo; echo "== summary ($(( $(date +%s) - T0 ))s${FULL:+, --full})"
sort "$SUMMARY"
! grep -q ' FAIL ' "$SUMMARY"
