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
# to onlyAnalyze and delete this script (tracked in #1455 / spotbugs/spotbugs#3796).
#
# On top of the skips, the changed reactor modules are exported as SPOTBUGS_SCOPE_ARGS
# ("-pl <changed> -am") so the lane builds only those modules plus their upstream
# dependencies instead of the full reactor. The -am-pulled unchanged dependencies still
# carry the injected skip: they compile (complete aux-classpath) but are not analysed.
#
# Run from the repository root.  Usage: compute-spotbugs-skip.sh <base-sha>
set -euo pipefail
base="${1:?base sha required}"

changed=$(git diff --name-only --diff-filter=ACMR "${base}...HEAD")

# Reactor module dirs from ddk-parent's <modules> (strip the leading ../), and the
# subset that bears sources. ddk-parent is NOT in its own <modules>, so it can never be
# skip-injected — which prevents an accidental inherited (global) skip. Both lists are
# computed up front because the full-scan early-exit below needs the source-bearing set
# for its report-presence gate.
module_dirs=$(grep -oE '<module>\.\./[^<]+</module>' ddk-parent/pom.xml \
  | sed -E 's#.*\.\./([^<]+)</module>#\1#')
all_source_modules=""
while IFS= read -r mod; do
  [ -n "$mod" ] || continue
  if [ -f "${mod}/META-INF/MANIFEST.MF" ] && [ -d "${mod}/src" ]; then
    all_source_modules="${all_source_modules:+${all_source_modules} }${mod}"
  fi
done <<EOF
${module_dirs}
EOF

# 1) A change to shared build/config can affect any module -> full scan (skip nothing).
#    ddk-configuration holds the analyzers' rulesets and filters (e.g. the SpotBugs
#    exclusion-filter), so a change there must re-scan everything, not skip silently.
#    ddk-target defines the target platform every module resolves against.
#    Fail safe: the worst case here is "analyse everything", never "analyse nothing".
while IFS= read -r f; do
  [ -n "$f" ] || continue
  case "$f" in
    pom.xml | ddk-parent/* | .mvn/* | *.target | ddk-target/* | .github/* | ddk-configuration/* | *[Ss]pot[Bb]ugs*[Ee]xclude*)
      # KEPT must be set on every path: in a workflow `if:` an unset env var is
      # null, which coerces to 0 and compares EQUAL to '0' — wrongly skipping
      # the build. "all" marks the full-scan case (only "0" relaxes the gate).
      # EXPECT_REPORTS lists every source-bearing module so the gate verifies a
      # full scan analysed all of them (not just >=1) — a mojo death swallowed by
      # --fail-never can't pass as long as one sibling reported.
      if [ -n "${GITHUB_ENV:-}" ]; then
        echo "SPOTBUGS_KEPT=all" >> "$GITHUB_ENV"
        echo "SPOTBUGS_EXPECT_REPORTS=${all_source_modules}" >> "$GITHUB_ENV"
      fi
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

# 3) Idempotently inject the skip property; handle poms with and without <properties>.
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

# 4) Skip every reactor module that was not touched by this PR. Kept modules with a
#    bundle MANIFEST are expected to produce an analysis report — the gate checks this
#    so a swallowed resolution/compile failure can never pass as "nothing to scan".
kept=0
skipped=0
kept_pl=""
expect_reports=""
while IFS= read -r mod; do
  [ -n "$mod" ] || continue
  if printf '%s\n' "${changed_mods}" | grep -qx "$mod"; then
    kept=$((kept + 1))
    kept_pl="${kept_pl:+${kept_pl},}../${mod}"
    # Only bundles with sources reliably emit a report (a source-less bundle,
    # e.g. pure branding, has nothing for the analyzer to write a SARIF about).
    if [ -f "${mod}/META-INF/MANIFEST.MF" ] && [ -d "${mod}/src" ]; then
      expect_reports="${expect_reports:+${expect_reports} }${mod}"
    fi
  else
    inject_skip "$mod"
    skipped=$((skipped + 1))
  fi
done <<EOF
${module_dirs}
EOF

# The gate's presence check needs to distinguish "all modules skip-injected"
# (zero reports is the expected state) from "the analysis silently died".
# If the only changed modules are source-less (feature / target / repository — nothing
# any analyzer can report), fold into the docs-only no-op: export KEPT=0 so the lane
# skips the Maven step, gate, and upload instead of failing the merged-report presence
# check on output that could never exist.
if [ "$kept" -eq 0 ] || [ -z "$expect_reports" ]; then
  effective_kept=0
else
  effective_kept=$kept
fi
if [ -n "${GITHUB_ENV:-}" ]; then
  echo "SPOTBUGS_KEPT=${effective_kept}" >> "$GITHUB_ENV"
fi

# 5) Scope the reactor to the changed modules + their upstream dependencies. ddk-target
#    is always kept in the -pl list: the target-definition artifact is referenced by
#    target-platform-configuration, not by any MANIFEST, so -am never pulls it — without
#    it in the reactor Tycho falls back to a local-repository copy, which fails on a
#    cold cache and can silently resolve a stale target definition on a warm one.
#    With no analysable changed module (docs-only, or source-less-only) no scope args
#    are exported; the workflow skips the Maven step entirely on SPOTBUGS_KEPT=0.
if [ "$effective_kept" -gt 0 ] && [ -n "${GITHUB_ENV:-}" ]; then
  echo "SPOTBUGS_SCOPE_ARGS=-pl ../ddk-target,${kept_pl} -am" >> "$GITHUB_ENV"
  echo "SPOTBUGS_EXPECT_REPORTS=${expect_reports}" >> "$GITHUB_ENV"
fi

echo "SpotBugs scope: scanning ${effective_kept} changed module(s), skipping ${skipped} unchanged."
echo "Changed modules: ${changed_mods:-<none>}"
if [ "$kept" -gt 0 ] && [ "$effective_kept" -eq 0 ]; then
  echo "Only source-less modules changed (no analysable sources) -> no-op (KEPT=0)."
fi
if [ "$effective_kept" -gt 0 ]; then
  echo "Reactor scope args: -pl ../ddk-target,${kept_pl} -am"
else
  echo "Reactor scope args: <full reactor>"
fi
