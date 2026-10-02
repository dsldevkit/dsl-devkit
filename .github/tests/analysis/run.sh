#!/usr/bin/env bash
set -Eeuo pipefail
trap 'echo "FAIL: ${tool:-harness}/${case:-setup} line $LINENO"' ERR
repo=$(cd "$(dirname "$0")/../../.." && pwd)
fixtures="$repo/.github/tests/analysis/fixtures"
for dependency in bash jq yq xmllint; do command -v "$dependency" >/dev/null; done
yq --version | grep -Eq 'version v?4\.'
scratch=$(mktemp -d)
trap 'rm -rf "$scratch"' EXIT
for job in lint spotbugs; do
  for block in merge gate; do
    if [ "$block" = merge ]; then prefix='Merge per-module'; else prefix='Gate on'; fi
    JOB=$job PREFIX=$prefix yq -r '.jobs[strenv(JOB)].steps[] | select(.name | test("^" + strenv(PREFIX))) | .run' \
      "$repo/.github/workflows/verify.yml" > "$scratch/$job-$block.sh"
    test -s "$scratch/$job-$block.sh"
    bash -n "$scratch/$job-$block.sh"
  done
done
# Every analyzer invocation must preserve a nonzero process exit status.
yq -o=json '.jobs' "$repo/.github/workflows/verify.yml" | jq -e '
  [.. | objects | .run? // empty | select(test("mvn .*--batch-mode"))] as $commands
  | ($commands | length >= 3) and all($commands[]; contains("--fail-at-end") and (contains("--fail-never") | not))' >/dev/null
passed=0
fresh() {
  folder="$scratch/case"
  rm -rf "$folder"
  mkdir -p "$folder/ddk-parent/target" "$folder/.github/scripts"
  cp "$repo/.github/scripts/sarif.sh" "$folder/.github/scripts/"
  printf '<modules>\n<module>../a</module>\n<module>../b</module>\n</modules>\n' > "$folder/ddk-parent/pom.xml"
  printf '<checkstyle/>\n' > "$folder/ddk-parent/target/checkstyle-result.xml"
  for module in a b; do
    mkdir -p "$folder/$module/target" "$folder/$module/src" "$folder/$module/META-INF"
    touch "$folder/$module/META-INF/MANIFEST.MF"
    cp "$fixtures/spotbugs-clean.json" "$folder/$module/target/spotbugsSarif.json"
    cp "$fixtures/pmd-clean.json" "$folder/$module/target/pmd.sarif.json"
    cp "$fixtures/checkstyle-clean.json" "$folder/$module/target/checkstyle-result.xml"
    printf '<pmd-cpd xmlns="https://pmd-code.org/schema/cpd-report"/>\n' > "$folder/$module/target/cpd.xml"
  done
}
edit() { jq "$1" "$target" > "$target.tmp"; mv "$target.tmp" "$target"; }
invoke() {
  local block=$1
  (cd "$folder" && GITHUB_WORKSPACE="${FIXTURE_ROOT:-$folder}" bash -e "$scratch/$job-$block.sh") > "$scratch/$block.log" 2>&1
}
expect() {
  local block=$1 wanted=$2 status=0
  invoke "$block" || status=$?
  if { [ "$wanted" = pass ] && [ "$status" -ne 0 ]; } || { [ "$wanted" = fail ] && [ "$status" -eq 0 ]; }; then
    echo "FAIL: $tool/$case $block expected $wanted, exit $status"
    cat "$scratch/$block.log"
    exit 1
  fi
}
for tool in spotbugs pmd checkstyle; do
  job=lint
  case "$tool" in
    spotbugs) job=spotbugs; filename=spotbugsSarif.json ;;
    pmd) filename=pmd.sarif.json ;;
    checkstyle) filename=checkstyle-result.xml ;;
  esac
  for case in clean finding execution-false execution-error configuration-error warning missing empty whitespace truncated \
    two-documents empty-runs missing-results null-results bad-message wrong-tool missing-invocations empty-invocations \
    null-invocations later-run-failure merged-truncated merged-missing merged-execution-false scoped-report-list real-error real-finding; do
    if [ -n "${CASE_FILTER:-}" ] && [ "$CASE_FILTER" != "$tool/$case" ]; then continue; fi
    unset FIXTURE_ROOT
    fresh
    target="$folder/b/target/$filename"
    wanted=fail; merge_wanted=fail
    case "$case" in
      clean) wanted=pass; merge_wanted=pass ;;
      finding) edit '.runs[0].results=[{ruleId:"fixture",message:{text:"Deliberate finding"}}]'; merge_wanted=pass ;;
      execution-false) edit '.runs[0].invocations=[{executionSuccessful:false}]' ;;
      execution-error) edit '.runs[0].invocations=[{executionSuccessful:true,toolExecutionNotifications:[{level:"error",message:{text:"error"}}]}]' ;;
      configuration-error) edit '.runs[0].invocations=[{executionSuccessful:true,toolConfigurationNotifications:[{level:"error",message:{text:"error"}}]}]' ;;
      warning) edit '.runs[0].invocations=[{executionSuccessful:true,toolExecutionNotifications:[{level:"warning",message:{text:"warning"}}]}]'; wanted=pass; merge_wanted=pass ;;
      missing) rm "$target" ;;
      empty) : > "$target" ;;
      whitespace) printf ' \n\t' > "$target" ;;
      truncated) printf '{"version":' > "$target" ;;
      two-documents) cat "$target" "$target" > "$target.tmp"; mv "$target.tmp" "$target" ;;
      empty-runs) edit '.runs=[]' ;;
      missing-results) edit 'del(.runs[0].results)' ;;
      null-results) edit '.runs[0].results=null' ;;
      bad-message) edit '.runs[0].results=[{message:null}]' ;;
      wrong-tool) edit '.runs[0].tool.driver.name="Other"' ;;
      missing-invocations) edit 'del(.runs[0].invocations)'; if [ "$tool" = checkstyle ]; then wanted=pass; merge_wanted=pass; fi ;;
      empty-invocations) edit '.runs[0].invocations=[]'; if [ "$tool" = checkstyle ]; then wanted=pass; merge_wanted=pass; fi ;;
      null-invocations) edit '.runs[0].invocations=null' ;;
      later-run-failure) edit '.runs += [.runs[0]] | .runs[1].invocations=[{executionSuccessful:false}]' ;;
      merged-truncated|merged-missing|merged-execution-false) merge_wanted=pass ;;
      scoped-report-list) printf '{' > "$target"; export SPOTBUGS_EXPECT_REPORTS=a LINT_EXPECT_REPORTS=a; wanted=pass; merge_wanted=pass ;;
      real-finding) if [ "$tool" != spotbugs ]; then continue; fi; cp "$fixtures/spotbugs-finding.json" "$target"; export FIXTURE_ROOT=/checkout; merge_wanted=pass ;;
      real-error) if [ "$tool" = checkstyle ]; then continue; fi; cp "$fixtures/$tool-error.json" "$target" ;;
    esac
    expect merge "$merge_wanted"
    if [ "$case" = merged-truncated ]; then printf '{' > "$folder/.sarif-merged/$tool.sarif"; fi
    if [ "$case" = merged-missing ]; then rm "$folder/.sarif-merged/$tool.sarif"; fi
    if [ "$case" = merged-execution-false ]; then
      target="$folder/.sarif-merged/$tool.sarif"
      edit '.runs[0].invocations=[{executionSuccessful:false}]'
    fi
    expect gate "$wanted"
    if [ "$case" = finding ]; then grep -q 'found violations' "$scratch/gate.log"; fi
    unset SPOTBUGS_EXPECT_REPORTS LINT_EXPECT_REPORTS
    passed=$((passed + 1))
    echo "PASS: $tool/$case"
  done
done
tool=cpd; job=lint
for case in clean finding minified multiple comment cdata missing empty truncated not-xml wrong-root error namespaced-error unexpected-child real-clean real-finding real-error; do
  if [ -n "${CASE_FILTER:-}" ] && [ "$CASE_FILTER" != "$tool/$case" ]; then continue; fi
  fresh
  target="$folder/b/target/cpd.xml"
  wanted=fail
  case "$case" in
    real-clean) cp "$fixtures/cpd-clean.xml" "$target"; wanted=pass ;;
    real-finding) cp "$fixtures/cpd-finding.xml" "$target" ;;
    real-error) cp "$fixtures/cpd-error.xml" "$target" ;;
    clean) wanted=pass ;;
    finding) printf '<pmd-cpd>\n<duplication lines="1" tokens="100"/>\n</pmd-cpd>' > "$target" ;;
    minified) printf '<pmd-cpd><duplication/></pmd-cpd>' > "$target" ;;
    multiple) printf '<pmd-cpd><duplication/><duplication/></pmd-cpd>' > "$target" ;;
    comment) printf '<pmd-cpd><!-- <duplication lines="1"/> --></pmd-cpd>' > "$target"; wanted=pass ;;
    cdata) printf '<pmd-cpd><![CDATA[<duplication lines="1"/>]]></pmd-cpd>' > "$target"; wanted=pass ;;
    missing) rm "$target" ;;
    empty) : > "$target" ;;
    truncated) printf '<pmd-cpd>\n' > "$target" ;;
    not-xml) printf 'analysis crashed\n' > "$target" ;;
    wrong-root) printf '<other/>' > "$target" ;;
    error) printf '<pmd-cpd><error filename="bad.java" msg="parse error"/></pmd-cpd>' > "$target" ;;
    namespaced-error) printf '<pmd-cpd xmlns="https://pmd-code.org/schema/cpd-report"><error/></pmd-cpd>' > "$target" ;;
    unexpected-child) printf '<pmd-cpd><failure/></pmd-cpd>' > "$target" ;;
  esac
  expect merge pass
  expect gate "$wanted"
  case "$case" in
    finding|minified) grep -q 'CPD duplications: 1' "$scratch/gate.log" ;;
    multiple) grep -q 'CPD duplications: 2' "$scratch/gate.log" ;;
  esac
  passed=$((passed + 1)); echo "PASS: cpd/$case"
done
# Exercise collisions, chained bases, encoded paths, secondary locations and different rule indices.
for tool in spotbugs pmd checkstyle; do
  if [ -n "${CASE_FILTER:-}" ] && [ "$CASE_FILTER" != "$tool/locations-and-rules" ]; then continue; fi
  fresh
  case "$tool" in
    spotbugs) job=spotbugs; filename=spotbugsSarif.json ;;
    pmd) job=lint; filename=pmd.sarif.json ;;
    checkstyle) job=lint; filename=checkstyle-result.xml ;;
  esac
  for module in a b; do
    target="$folder/$module/target/$filename"
    jq --arg module "$module" '
      .runs[0] |= (.originalUriBaseIds={ROOT:{uri:"file:///checkout/"},SRC:{uri:($module+"/src/"),uriBaseId:"ROOT"}}
      | .tool.driver.rules=[{id:$module,shortDescription:{text:$module}}]
      | .results=[{ruleId:$module,ruleIndex:0,level:"warning",message:{text:("finding-"+$module)},
        locations:[{physicalLocation:{artifactLocation:{uri:"Space%20Name.java",uriBaseId:"SRC"}}}],
        relatedLocations:[{id:1,physicalLocation:{artifactLocation:{uri:"Related.java",uriBaseId:"SRC"}}}]}])' "$target" > "$target.tmp"
    mv "$target.tmp" "$target"
  done
  case=locations-and-rules
  export FIXTURE_ROOT=/checkout
  expect merge pass
  expect gate fail
  jq -e '.runs[0] as $run | ($run.results | length==2)
    and ([$run.tool.driver.rules[].id] | sort == ["a","b"])
    and all($run.results[]; . as $result
      | .level=="warning" and .message.text==("finding-"+.ruleId)
      and (has("ruleIndex") | not)
      and all(.locations[], .relatedLocations[];
        .physicalLocation.artifactLocation as $loc
        | ($loc | has("uriBaseId") | not)
        and ($loc.uri == ($result.ruleId+"/src/Space%20Name.java")
          or $loc.uri == ($result.ruleId+"/src/Related.java"))))' \
    "$folder/.sarif-merged/$tool.sarif" >/dev/null
  unset FIXTURE_ROOT
  passed=$((passed + 1)); echo "PASS: $tool/$case"
done
# GitHub consumes repository-relative artifact URIs, not arbitrary URI-base identifiers.
for case in relative-uri absolute-uri file-uri dot-segments encoded-root outside-repository unknown-scheme; do
  tool=spotbugs; job=spotbugs
  if [ -n "${CASE_FILTER:-}" ] && [ "$CASE_FILTER" != "$tool/$case" ]; then continue; fi
  fresh
  export FIXTURE_ROOT=/checkout
  target="$folder/b/target/spotbugsSarif.json"
  wanted=pass
  expected='a/src/Example.java'
  case "$case" in
    relative-uri) uri='a/src/Example.java' ;;
    absolute-uri) uri='/checkout/a/src/Example.java' ;;
    file-uri) uri='file:/checkout/a/src/Example.java' ;;
    dot-segments) uri='file:///checkout/a/other/../src/./Example.java' ;;
    encoded-root) export FIXTURE_ROOT='/checkout space'; uri='file:///checkout%20space/a/src/Space%20Name.java'; expected='a/src/Space%20Name.java' ;;
    outside-repository) uri='file:///unrelated/src/Example.java'; wanted=fail ;;
    unknown-scheme) uri='https://example.invalid/Example.java'; wanted=fail ;;
  esac
  jq --arg uri "$uri" '.runs[0].results=[{message:{text:"location probe"},locations:[{physicalLocation:{artifactLocation:{uri:$uri}}}]}]' "$target" > "$target.tmp"
  mv "$target.tmp" "$target"
  expect merge "$wanted"
  if [ "$wanted" = pass ]; then
    expect gate fail
    jq -e --arg expected "$expected" '.runs[0].results[0].locations[0].physicalLocation.artifactLocation | .uri==$expected and (has("uriBaseId")|not)' "$folder/.sarif-merged/spotbugs.sarif" >/dev/null
  fi
  unset FIXTURE_ROOT
  passed=$((passed + 1)); echo "PASS: $tool/$case"
done

# Metadata that cannot be combined without loss must fail explicitly.
for case in taxonomies conflicting-rules rule-index-only wrong-rule-index unsupported-run dangling-base cyclic-base; do
  tool=spotbugs; job=spotbugs
  if [ -n "${CASE_FILTER:-}" ] && [ "$CASE_FILTER" != "$tool/$case" ]; then continue; fi
  fresh
  target="$folder/b/target/spotbugsSarif.json"
  wanted=fail
  case "$case" in
    taxonomies)
      for module in a b; do
        target="$folder/$module/target/spotbugsSarif.json"
        jq --arg module "$module" '.runs[0].taxonomies=[{name:"CWE",guid:"shared",taxa:[{id:$module,shortDescription:{text:$module}}]}]' "$target" > "$target.tmp"
        mv "$target.tmp" "$target"
      done
      wanted=pass ;;
    conflicting-rules)
      for module in a b; do
        target="$folder/$module/target/spotbugsSarif.json"
        jq --arg module "$module" '.runs[0].tool.driver.rules=[{id:"shared",shortDescription:{text:$module}}]' "$target" > "$target.tmp"
        mv "$target.tmp" "$target"
      done ;;
    rule-index-only)
      edit '.runs[0] |= (.tool.driver.rules=[{id:"resolved"}] | .results=[{ruleIndex:0,message:{text:"indexed rule"}}])'
      wanted=pass ;;
    wrong-rule-index)
      edit '.runs[0] |= (.tool.driver.rules=[{id:"first"}] | .results=[{ruleId:"other",ruleIndex:0,message:{text:"mismatch"}}])' ;;
    unsupported-run) edit '.runs[0].artifacts=[{location:{uri:"src/Example.java"}}]' ;;
    dangling-base) edit '.runs[0].originalUriBaseIds={SRC:{uri:"src/",uriBaseId:"MISSING"}}' ;;
    cyclic-base) edit '.runs[0].originalUriBaseIds={SRC:{uri:"src/",uriBaseId:"SRC"}}' ;;
  esac
  expect merge "$wanted"
  if [ "$case" = taxonomies ]; then
    expect gate pass
    jq -e '.runs[0].taxonomies | length==1 and (.[0].taxa | map(.id) | sort==["a","b"])' "$folder/.sarif-merged/spotbugs.sarif" >/dev/null
  elif [ "$case" = rule-index-only ]; then
    expect gate fail
    jq -e '.runs[0].results[0] | .ruleId=="resolved" and (has("ruleIndex") | not)' "$folder/.sarif-merged/spotbugs.sarif" >/dev/null
  fi
  passed=$((passed + 1)); echo "PASS: $tool/$case"
done

# Maven failures remain failures even if usable reports already exist.
if [ -z "${CASE_FILTER:-}" ]; then
  fresh
  mkdir "$scratch/bin"
  cat > "$scratch/bin/mvn" <<'STUB'
#!/usr/bin/env bash
printf 'process reached\n' > "$PROCESS_MARKER"
exit 42
STUB
  chmod +x "$scratch/bin/mvn"
  for name in 'PMD + Checkstyle reports (SARIF)' 'CPD report (separate invocation — no SARIF support)' 'SpotBugs report (SARIF)'; do
    NAME="$name" yq -r '.jobs[].steps[] | select(.name == strenv(NAME)) | .run' \
      "$repo/.github/workflows/verify.yml" > "$scratch/process.sh"
    rm -f "$scratch/process-marker"
    status=0
    (cd "$folder" && PATH="$scratch/bin:$PATH" PROCESS_MARKER="$scratch/process-marker" bash -e "$scratch/process.sh") || status=$?
    test "$status" -eq 42
    test -s "$scratch/process-marker"
    passed=$((passed + 1)); echo "PASS: process failure/$name"
  done
fi
test "$passed" -gt 0
echo "$passed analysis regression cases passed."
