#!/usr/bin/env bash
# Deep CVE audit: OWASP dependency-check (NVD/CPE matching) over the full
# reactor including the resolved Tycho target platform. Complements the fast
# per-PR check (check-cves.sh): CPE matching covers Eclipse-native bundles and
# embedded code that have no Maven identity, at the price of a large NVD
# database and fuzzier matching (see dependency-check-suppressions.xml).
#
# Self-test: a canary jar with a well-known CVE is planted into the scan set.
# Its CVE is suppressed (scoped to the canary path) so it cannot fail the
# build, but it MUST appear in the report's suppressed section - proving the
# whole pipeline (scan, database, matching, suppression parsing) actually ran.
#
# No NVD API key is needed: the database is built from the dependency-check
# project's nightly NVD mirror (see nvdDatafeedUrl in ddk-parent/pom.xml).
# A full build from an empty data directory takes about a minute.
#
# Usage: check-cves-deep.sh [--no-update]
#   --no-update  skip the database update entirely (fast local iteration
#                against an existing data directory)
#
# Environment:
#   CVE_SCAN_THREADS      Maven -T value (default: 2C)
#   ODC_DATA_DIRECTORY    dependency-check database directory (default:
#                         ~/.dependency-check)
#
# Exit codes: 0 = clean, 1 = findings >= CVSS 7 (failBuildOnCVSS), 2 = infra
# or self-test failure.
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
REPORT="${REPO_ROOT}/ddk-parent/target/dependency-check-report.json"
CANARY_DIR="${REPO_ROOT}/ddk-parent/target/cve-canary"
CANARY_JAR="commons-collections-3.2.1.jar"
CANARY_URL="https://repo1.maven.org/maven2/commons-collections/commons-collections/3.2.1/${CANARY_JAR}"
CANARY_SHA1="761ea405b9b37ced573d2df0d1e3a4e0f9edc668"
CANARY_CVE="CVE-2015-6420"
THREADS="${CVE_SCAN_THREADS:-2C}"
DATA_DIR="${ODC_DATA_DIRECTORY:-${HOME}/.dependency-check}"

NO_UPDATE=false
[ "${1:-}" = "--no-update" ] && NO_UPDATE=true

for tool in jq curl mvn; do
  if ! command -v "${tool}" > /dev/null; then
    echo "ERROR: '${tool}' is required but not on PATH." >&2
    exit 2
  fi
done

echo "Planting canary jar..."
mkdir -p "${CANARY_DIR}"
if [ ! -f "${CANARY_DIR}/${CANARY_JAR}" ]; then
  curl -sSf --retry 3 --max-time 60 -o "${CANARY_DIR}/${CANARY_JAR}" "${CANARY_URL}"
fi
actual_sha1="$(shasum "${CANARY_DIR}/${CANARY_JAR}" | cut -d' ' -f1)"
if [ "${actual_sha1}" != "${CANARY_SHA1}" ]; then
  echo "ERROR: canary jar sha1 mismatch (${actual_sha1}) - refusing to scan." >&2
  exit 2
fi

extra_flags=()
[ "${NO_UPDATE}" = "true" ] && extra_flags+=("-DautoUpdate=false")

echo "Running dependency-check aggregate (builds/refreshes the NVD database from the nightly mirror, ~1 min from empty)..."
rm -f "${REPORT}"  # a stale report must never satisfy the canary gate
start="$(date +%s)"
scan_log="$(mktemp)"
# dependency:resolve is required: aggregate alone runs only on the root module
# and sees none of the Tycho-injected target-platform dependencies.
set +e
mvn -f "${REPO_ROOT}/ddk-parent/pom.xml" -T "${THREADS}" --batch-mode \
  validate \
  org.apache.maven.plugins:maven-dependency-plugin:3.8.1:resolve \
  org.owasp:dependency-check-maven:13.0.0:aggregate \
  -DdataDirectory="${DATA_DIR}" \
  "${extra_flags[@]+"${extra_flags[@]}"}" > "${scan_log}" 2>&1
mvn_exit=$?
set -e
elapsed="$(( $(date +%s) - start ))"

if [ ! -f "${REPORT}" ]; then
  echo "ERROR: no report at ${REPORT}; last 30 lines of the scan log:" >&2
  tail -30 "${scan_log}" >&2
  rm -f "${scan_log}"
  exit 2
fi
rm -f "${scan_log}"

# Canary self-test: the canary CVE is suppressed by design (so it cannot fail
# the build) but it must be present in the suppressed section of the report.
canary_hits="$(jq --arg cve "${CANARY_CVE}" \
  '[.dependencies[] | select(.fileName | test("commons-collections-3\\.2\\.1")) |
    (.suppressedVulnerabilities // [])[] | select(.name == $cve)] | length' "${REPORT}")"
if [ "${canary_hits}" -eq 0 ]; then
  echo "SCAN SELF-TEST FAILED: canary ${CANARY_CVE} not detected on ${CANARY_JAR}." >&2
  echo "The scan pipeline is broken - do not trust this report." >&2
  exit 2
fi
echo "Canary self-test passed (${CANARY_CVE} detected and suppressed as designed)."

scanned="$(jq '.dependencies | length' "${REPORT}")"
flagged="$(jq '[.dependencies[] | select(.vulnerabilities != null)] | length' "${REPORT}")"
suppressed="$(jq '[.dependencies[] | (.suppressedVulnerabilities // [])[]] | length' "${REPORT}")"
echo ""
echo "Deep scan: ${scanned} dependencies scanned in ${elapsed}s; ${flagged} with unsuppressed findings, ${suppressed} suppressed matches (see suppressions file)."

if [ "${flagged}" -gt 0 ]; then
  echo ""
  echo "Unsuppressed findings (build fails at CVSS >= 7):"
  jq -r '.dependencies[] | select(.vulnerabilities != null) |
    "  \(.fileName)\n    \([.vulnerabilities[] | "\(.name) (CVSS \(.cvssv3.baseScore // .cvssv2.score // "?"))"] | join(", "))"' "${REPORT}"
fi

if [ "${mvn_exit}" -ne 0 ]; then
  echo ""
  echo "FAILED: findings at or above the CVSS 7 gate. Fix the dependency (usually"
  echo "via ddk-target/ddk.target) or add a justified suppression to"
  echo ".github/security/dependency-check-suppressions.xml."
  exit 1
fi
echo "OK: no findings at or above the CVSS 7 gate."
exit 0
