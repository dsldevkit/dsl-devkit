#!/usr/bin/env bash
set -euo pipefail
repo=$(cd "$(dirname "$0")/../../.." && pwd)
scratch=$(mktemp -d)
trap 'rm -rf "$scratch"' EXIT
mutant="$scratch/repo"
mutation() {
  local label=$1 file=$2 needle=$3 replacement=$4 case_filter=$5 content
  rm -rf "$mutant"
  mkdir -p "$mutant/.github/scripts" "$mutant/.github/workflows" "$mutant/.github/tests"
  cp -R "$repo/.github/tests/analysis" "$mutant/.github/tests/"
  cp "$repo/.github/scripts/sarif.sh" "$mutant/.github/scripts/"
  cp "$repo/.github/workflows/verify.yml" "$mutant/.github/workflows/"
  content=$(cat "$mutant/$file")
  if [[ "$content" != *"$needle"* ]]; then echo "Mutation anchor missing: $label"; exit 1; fi
  content=${content//"$needle"/"$replacement"}
  printf '%s\n' "$content" > "$mutant/$file"
  if CASE_FILTER="$case_filter" bash "$mutant/.github/tests/analysis/run.sh" > "$scratch/$label.log" 2>&1; then
    echo "FAIL: regression escaped detection: $label"; exit 1
  fi
  grep -q 'FAIL:' "$scratch/$label.log"
  echo "PASS: detected $label"
}
helper=.github/scripts/sarif.sh
workflow=.github/workflows/verify.yml
mutation execution-status "$helper" 'and .executionSuccessful == true' 'and true' spotbugs/execution-false
mutation error-notification "$helper" '.level != "error"' 'true' pmd/execution-error
mutation completion-metadata "$helper" 'and ($analyzer == "Checkstyle" or (.invocations | type == "array" and length > 0))' 'and true' spotbugs/missing-invocations
mutation cpd-parser "$helper" 'local report=$1 valid count' 'local report=$1 valid count; echo 0; return 0' cpd/truncated
mutation cpd-count "$helper" "'count(/*/*[local-name()=\"duplication\"])'" "'0'" cpd/minified
mutation uri-base-reference "$helper" '.uri = ($absolute | ltrimstr($root)) | del(.uriBaseId)' 'del(.uriBaseId)' spotbugs/locations-and-rules
mutation merged-validation "$workflow" 'validate_sarif .sarif-merged/spotbugs.sarif SpotBugs' ':' spotbugs/merged-execution-false
mutation maven-failure-status "$workflow" '--fail-at-end' '--fail-never' spotbugs/clean
