#!/usr/bin/env bash
#
# Scope SpotBugs to a pull request's changed modules.
#
# Default is RUN (analyze). On a PR this injects <spotbugs.skip>true</spotbugs.skip>
# into every UNCHANGED reactor module's pom, so spotbugs-maven-plugin skips the goal —
# and therefore the per-module JVM fork (SpotBugsMojo gates on `skip` before forking) —
# for those modules. The full-reactor compile is left intact (a changed module is still
# analysed with its complete aux-classpath). Master/snapshot builds run a full scan;
# this script is invoked on pull_request only.
#
# Why this and not -Dspotbugs.onlyAnalyze: onlyAnalyze is one clean flag, but SpotBugs
# applies its class screener too late (after the per-module fork + class scan), so it
# only trimmed ~17% of the goal vs ~88% for this per-module skip (measured on this
# reactor). A small upstream SpotBugs early-exit (skip the run when no application class
# matches the screener) would make onlyAnalyze competitive; if that ever lands, switch
# to onlyAnalyze and delete this script.
#
# Run from the repository root.  Usage: compute-spotbugs-skip.sh <base-sha>
set -euo pipefail
base="${1:?base sha required}"

changed=$(git diff --name-only --diff-filter=ACMR "${base}...HEAD")

# 1) A change to shared build/config can affect any module -> full scan (skip nothing).
#    ddk-configuration holds the analyzers' rulesets and filters (e.g. the SpotBugs
#    exclusion-filter), so a change there must re-scan everything, not skip silently.
#    ddk-target defines the target platform every module resolves against.
#    Fail safe: the worst case here is "analyse everything", never "analyse nothing".
while IFS= read -r f; do
  [ -n "$f" ] || continue
  case "$f" in
    pom.xml | ddk-parent/* | .mvn/* | *.target | ddk-target/* | .github/* | ddk-configuration/* | *[Ss]pot[Bb]ugs*[Ee]xclude*)
      echo "Build/config change ($f) -> full SpotBugs scan (no skips)."
      exit 0
      ;;
  esac
done <<EOF
${changed}
EOF

# 2) Changed top-level module directories (the reactor module == top-level dir here).
#    `|| true`: a PR touching only root files (e.g. README.md) has no '/' paths;
#    grep's no-match exit would otherwise kill the script under pipefail.
changed_mods=$(printf '%s\n' "${changed}" | { grep '/' || true; } | cut -d/ -f1 | sort -u)

# 3) Reactor module dirs from ddk-parent's <modules> (strip the leading ../).
#    ddk-parent is NOT in its own <modules>, so it can never be skip-injected — which
#    is what prevents an accidental inherited (global) skip.
module_dirs=$(grep -oE '<module>\.\./[^<]+</module>' ddk-parent/pom.xml \
  | sed -E 's#.*\.\./([^<]+)</module>#\1#')

# 4) Idempotently inject the skip property; handle poms with and without <properties>.
#    sed -i.bak + rm is portable across GNU (CI) and BSD (local) sed.
inject_skip() {
  local pom="$1/pom.xml"
  [ -f "$pom" ] || return 0
  if grep -q '<spotbugs\.skip>' "$pom"; then return 0; fi
  if grep -q '<properties>' "$pom"; then
    sed -i.bak 's#<properties>#<properties>\n    <spotbugs.skip>true</spotbugs.skip>#' "$pom"
  else
    sed -i.bak 's#</project>#  <properties>\n    <spotbugs.skip>true</spotbugs.skip>\n  </properties>\n</project>#' "$pom"
  fi
  rm -f "$pom.bak"
}

# 5) Skip every reactor module that was not touched by this PR.
kept=0
skipped=0
while IFS= read -r mod; do
  [ -n "$mod" ] || continue
  if printf '%s\n' "${changed_mods}" | grep -qx "$mod"; then
    kept=$((kept + 1))
  else
    inject_skip "$mod"
    skipped=$((skipped + 1))
  fi
done <<EOF
${module_dirs}
EOF

# The gate's presence check needs to distinguish "all modules skip-injected"
# (zero reports is the expected state) from "the analysis silently died".
if [ -n "${GITHUB_ENV:-}" ]; then
  echo "SPOTBUGS_KEPT=${kept}" >> "$GITHUB_ENV"
fi

echo "SpotBugs scope: scanning ${kept} changed module(s), skipping ${skipped} unchanged."
echo "Changed modules: ${changed_mods:-<none>}"
