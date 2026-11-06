#!/usr/bin/env bash
# Fails the build unless every concrete class declaring tests has a Tycho Surefire report.
# This catches a test bundle not packaged as eclipse-test-plugin, a class name Surefire
# does not include, and a module the reactor skipped.
set -euo pipefail

test_annotation='^[[:space:]]*@(Test|BugTest|ParameterizedTest|RepeatedTest|TestFactory|TestTemplate)([^[:alnum:]_]|$)'
expected=0
missing=0

while IFS= read -r source; do
  if grep -qE 'abstract[[:space:]]+class|@interface' "$source"; then
    continue
  fi
  expected=$((expected + 1))
  module=${source%%/*}
  class=${source#*/src/}
  class=${class%.java}
  class=${class//\//.}
  if [ ! -f "$module/target/surefire-reports/TEST-$class.xml" ]; then
    echo "No surefire report for $source"
    missing=$((missing + 1))
  fi
done < <(grep -rlE --include='*.java' "$test_annotation" com.avaloq.tools.ddk.*/src)

if [ "$expected" -eq 0 ]; then
  echo "::error::No test classes found. This check is likely broken."
  exit 1
fi
if [ "$missing" -gt 0 ]; then
  echo "::error::$missing of $expected test classes have no surefire report, so they did not run."
  exit 1
fi
echo "Found surefire reports for all $expected test classes."
