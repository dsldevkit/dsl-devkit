#!/usr/bin/env bash
# Fast CVE check for the full reactor, including the Tycho target platform.
#
# Generates an aggregate CycloneDX SBOM (tycho-sbom maps p2 artifacts to their
# real Maven coordinates), extracts the pkg:maven purls and queries the OSV.dev
# batch API for known vulnerabilities. Advisories listed in
# .github/security/cve-ignores.json (with a reason) are filtered out.
#
# Self-tests, so a broken scan can never silently pass:
#  - a canary purl with well-known, never-withdrawn advisories is appended to
#    every batch; if OSV reports nothing for it the scan aborts,
#  - if the SBOM yields fewer Maven purls than expected the scan aborts
#    (guards against dependency-resolution regressions producing empty BOMs).
#
# osv-scanner is deliberately NOT used here: its CycloneDX ingestion (2.5.0)
# drops Maven groupIds and its offline mode silently reports zero findings.
#
# Usage: check-cves.sh [--skip-sbom]
#   --skip-sbom  reuse an existing ddk-parent/target/bom.json (fast local iteration)
#
# Environment:
#   CVE_SCAN_THREADS    Maven -T value for SBOM generation (default: 2C)
#   CVE_SCAN_MIN_PURLS  minimum expected pkg:maven purls (default: 100)
#   CVE_SCAN_CANARY     canary purl override (used by negative self-tests)
#
# Exit codes: 0 = clean, 1 = findings, 2 = infrastructure or self-test failure.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
BOM="${REPO_ROOT}/ddk-parent/target/bom.json"
IGNORES="${REPO_ROOT}/.github/security/cve-ignores.json"
OUT_DIR="${REPO_ROOT}/ddk-parent/target/cve-scan"
OSV_BATCH_API="https://api.osv.dev/v1/querybatch"
OSV_VULN_API="https://api.osv.dev/v1/vulns"
CANARY_PURL="${CVE_SCAN_CANARY:-pkg:maven/commons-collections/commons-collections@3.2.1}"
MIN_MAVEN_PURLS="${CVE_SCAN_MIN_PURLS:-100}"
THREADS="${CVE_SCAN_THREADS:-2C}"
BATCH_SIZE=500

for tool in jq curl mvn; do
  if ! command -v "${tool}" > /dev/null; then
    echo "ERROR: '${tool}' is required but not on PATH." >&2
    [ "${tool}" = "jq" ] && echo "  install: brew install jq (macOS) / apt-get install jq (Linux)" >&2
    exit 2
  fi
done

if [ "${1:-}" != "--skip-sbom" ]; then
  echo "Generating aggregate SBOM (Tycho target platform included)..."
  sbom_log="$(mktemp)"
  # dependency:resolve is required: Tycho only injects p2 dependencies into the
  # Maven model under full dependency resolution; without it the BOM contains
  # only the reactor's own modules.
  if ! mvn -f "${REPO_ROOT}/ddk-parent/pom.xml" -T "${THREADS}" --batch-mode --quiet \
      validate \
      org.apache.maven.plugins:maven-dependency-plugin:3.8.1:resolve \
      org.cyclonedx:cyclonedx-maven-plugin:2.9.1:makeBom \
      org.cyclonedx:cyclonedx-maven-plugin:2.9.1:makeAggregateBom > "${sbom_log}" 2>&1; then
    echo "ERROR: SBOM generation failed; last 30 lines:" >&2
    tail -30 "${sbom_log}" >&2
    rm -f "${sbom_log}"
    exit 2
  fi
  rm -f "${sbom_log}"
elif [ ! -f "${BOM}" ]; then
  echo "ERROR: --skip-sbom given but ${BOM} does not exist." >&2
  exit 2
fi

mkdir -p "${OUT_DIR}"

# pkg:maven purls, purl qualifiers (?type=jar) stripped; canary appended last.
jq -r '[.components[].purl // "" | select(startswith("pkg:maven")) | split("?")[0]] | unique | .[]' \
  "${BOM}" > "${OUT_DIR}/maven-purls.txt"
# pkg:p2 purls have no Maven identity and cannot be matched by OSV; they are
# recorded as the documented coverage gap (deep-scan tier territory).
jq -r '[.components[].purl // "" | select(startswith("pkg:p2"))] | unique | .[]' \
  "${BOM}" > "${OUT_DIR}/p2-purls.txt"

maven_count="$(wc -l < "${OUT_DIR}/maven-purls.txt" | tr -d ' ')"
p2_count="$(wc -l < "${OUT_DIR}/p2-purls.txt" | tr -d ' ')"
echo "SBOM: ${maven_count} Maven purls to scan, ${p2_count} p2-only components (see cve-scan/p2-purls.txt)."

if [ "${maven_count}" -lt "${MIN_MAVEN_PURLS}" ]; then
  echo "SCAN SELF-TEST FAILED: only ${maven_count} Maven purls in the SBOM (expected >= ${MIN_MAVEN_PURLS})." >&2
  echo "The SBOM is likely missing the target platform - do not trust this scan." >&2
  exit 2
fi

printf '%s\n' "${CANARY_PURL}" >> "${OUT_DIR}/maven-purls.txt"

# Query OSV in batches; results arrive in query order, so ordering is preserved.
: > "${OUT_DIR}/osv-results.jsonl"
total="$((maven_count + 1))"
offset=0
while [ "${offset}" -lt "${total}" ]; do
  jq -R -n --argjson from "${offset}" --argjson size "${BATCH_SIZE}" \
    '{queries: [inputs] | .[$from:($from + $size)] | map({package: {purl: .}})}' \
    < "${OUT_DIR}/maven-purls.txt" > "${OUT_DIR}/batch-request.json"
  if ! curl -sS --fail --retry 3 --retry-delay 2 --max-time 60 \
      -X POST -d @"${OUT_DIR}/batch-request.json" "${OSV_BATCH_API}" \
      | jq -c '.results[]' >> "${OUT_DIR}/osv-results.jsonl"; then
    echo "ERROR: OSV batch query failed (network or API error) - do not trust this scan." >&2
    exit 2
  fi
  offset="$((offset + BATCH_SIZE))"
done

result_count="$(wc -l < "${OUT_DIR}/osv-results.jsonl" | tr -d ' ')"
if [ "${result_count}" -ne "${total}" ]; then
  echo "ERROR: OSV returned ${result_count} results for ${total} queries - do not trust this scan." >&2
  exit 2
fi

# Canary self-test: the last result belongs to the canary purl and must carry
# at least one advisory, otherwise the scan pipeline is broken.
canary_vulns="$(tail -1 "${OUT_DIR}/osv-results.jsonl" | jq '[.vulns // []] | flatten | length')"
if [ "${canary_vulns}" -eq 0 ]; then
  echo "SCAN SELF-TEST FAILED: canary ${CANARY_PURL} returned no advisories." >&2
  echo "The OSV query pipeline is broken - do not trust this scan." >&2
  exit 2
fi
echo "Canary self-test passed (${canary_vulns} advisories on ${CANARY_PURL})."

# Join purls with their results (canary line dropped by sed).
paste -d '\t' "${OUT_DIR}/maven-purls.txt" "${OUT_DIR}/osv-results.jsonl" \
  | sed '$d' \
  | jq -R -s '
      [split("\n")[] | select(length > 0) | split("\t")
       | {purl: .[0], ids: [(.[1] | fromjson).vulns // [] | .[].id]}]
      | map(select(.ids | length > 0))
    ' > "${OUT_DIR}/findings-raw.json"

# Drop advisories listed in the ignore ledger (matching id, and purl prefix if given).
jq --slurpfile ignores "${IGNORES}" '
    map(. as $f | .ids = [$f.ids[] | . as $id
        | select(([$ignores[0].ignores[] | . as $e
                   | select($e.id == $id and (($e.purl == null) or ($f.purl | startswith($e.purl))))] | length) == 0)])
    | map(select(.ids | length > 0))
  ' "${OUT_DIR}/findings-raw.json" > "${OUT_DIR}/findings.json"

finding_count="$(jq 'length' "${OUT_DIR}/findings.json")"
if [ "${finding_count}" -eq 0 ]; then
  echo "OK: no known vulnerabilities in ${maven_count} scanned components (after documented ignores)."
  exit 0
fi

echo ""
echo "VULNERABILITIES FOUND in ${finding_count} component(s):"
echo ""
jq -r '.[] | "  \(.purl)\n    \(.ids | join(", "))"' "${OUT_DIR}/findings.json"
echo ""
echo "Advisory details:"
for id in $(jq -r '[.[].ids[]] | unique | .[]' "${OUT_DIR}/findings.json"); do
  summary="$(curl -sS --max-time 20 "${OSV_VULN_API}/${id}" | jq -r '.summary // .details // "(no summary)"' | head -1)"
  echo "  ${id}: ${summary}"
done
echo ""
echo "Fix the dependency (usually via ddk-target/ddk.target), or add a justified"
echo "entry to .github/security/cve-ignores.json (see that file for the format)."
exit 1
