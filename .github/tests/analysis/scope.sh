#!/usr/bin/env bash
set -Eeuo pipefail
trap 'echo "FAIL: scope ${mode:-setup}/${scenario:-setup} line $LINENO"' ERR
repo=$(cd "$(dirname "$0")/../../.." && pwd)
scratch=$(mktemp -d)
trap 'rm -rf "$scratch"' EXIT
script=compute-spotbugs-skip.sh
modes=spotbugs
if [ ! -f "$repo/.github/scripts/$script" ] && [ ! -f "$repo/.github/scripts/compute-analysis-skip.sh" ]; then
  echo 'Scope tests: this head uses the full reactor.'
  exit 0
fi
if [ -f "$repo/.github/scripts/compute-analysis-skip.sh" ]; then
  script=compute-analysis-skip.sh
  modes='spotbugs lint'
fi
for mode in $modes; do
  prefix=$(printf '%s' "$mode" | tr '[:lower:]' '[:upper:]')
  for block in merge gate; do
    if [ "$block" = merge ]; then starts='Merge per-module'; else starts='Gate on'; fi
    JOB=$mode PREFIX=$starts yq -r '.jobs[strenv(JOB)].steps[] | select(.name | test("^" + strenv(PREFIX))) | .run' \
      "$repo/.github/workflows/verify.yml" > "$scratch/$block.sh"
  done
  for scenario in partial two-modules docs-only source-less mixed shared-config workflow deletion cross-module-move; do
    folder="$scratch/$mode-$scenario"
    git clone --quiet --no-checkout "$repo/.github/tests/analysis/fixtures/scope.bundle" "$folder"
    git -C "$folder" checkout --quiet --detach "origin/$scenario"
    base=$(git -C "$folder" rev-parse HEAD^)
    environment="$folder/environment"
    mkdir -p "$folder/.github/scripts"
    cp "$repo/.github/scripts/$script" "$repo/.github/scripts/sarif.sh" "$folder/.github/scripts/"
    unset SPOTBUGS_KEPT SPOTBUGS_EXPECT_REPORTS SPOTBUGS_SCOPE_ARGS LINT_KEPT LINT_EXPECT_REPORTS LINT_SCOPE_ARGS
    args=("$base")
    if [ "$script" = compute-analysis-skip.sh ]; then args+=("$mode"); fi
    for iteration in 1 2; do
      (cd "$folder" && GITHUB_ENV="$environment" bash ".github/scripts/$script" "${args[@]}") > "$scratch/scope.log" 2>&1
      git -C "$folder" diff > "$scratch/injection-$iteration.diff"
    done
    cmp "$scratch/injection-1.diff" "$scratch/injection-2.diff"
    if [ -f "$environment" ]; then
      while IFS= read -r assignment; do export "$assignment"; done < "$environment"
    fi
    case "$scenario" in
      partial|mixed|deletion) wanted='a' ;;
      two-modules|shared-config|workflow|cross-module-move) wanted='a b' ;;
      docs-only|source-less) wanted='' ;;
    esac
    key="${prefix}_KEPT"; kept=${!key:-all}
    key="${prefix}_EXPECT_REPORTS"; expected=${!key:-a b}
    if [ -z "$wanted" ]; then test "$kept" = 0; else test "$kept" != 0; test "$expected" = "$wanted"; fi
    full=false
    if [ "$scenario" = shared-config ] || [ "$scenario" = workflow ]; then full=true; fi
    props=spotbugs.skip
    if [ "$mode" = lint ]; then props='pmd.skip cpd.skip checkstyle.skip'; fi
    for module in a b brand feature ddk-target; do
      skip=true
      case "$scenario:$module" in
        partial:a|two-modules:a|two-modules:b|source-less:brand|mixed:a|mixed:brand|deletion:a|cross-module-move:a|cross-module-move:b) skip=false ;;
      esac
      if [ "$full" = true ]; then skip=false; fi
      for prop in $props; do
        count=$(grep -Fc "<$prop>true</$prop>" "$folder/$module/pom.xml" || true)
        if [ "$skip" = true ]; then test "$count" -eq 1; else test "$count" -eq 0; fi
      done
    done
    key="${prefix}_SCOPE_ARGS"; scope_args=${!key:-}
    if grep -q 'SCOPE_ARGS=' "$repo/.github/scripts/$script"; then
      if [ -n "$wanted" ] && [ "$full" = false ]; then
        case "$scenario" in
          mixed) selected='../a,../brand' ;;
          two-modules|cross-module-move) selected='../a,../b' ;;
          *) selected='../a' ;;
        esac
        test "$scope_args" = "-pl ../ddk-target,$selected -am"
      else test -z "$scope_args"; fi
    fi
    for module in $wanted; do
      mkdir -p "$folder/$module/target"
      cp "$repo/.github/tests/analysis/fixtures/spotbugs-clean.json" "$folder/$module/target/spotbugsSarif.json"
      cp "$repo/.github/tests/analysis/fixtures/pmd-clean.json" "$folder/$module/target/pmd.sarif.json"
      cp "$repo/.github/tests/analysis/fixtures/checkstyle-clean.json" "$folder/$module/target/checkstyle-result.xml"
      cp "$repo/.github/tests/analysis/fixtures/cpd-clean.xml" "$folder/$module/target/cpd.xml"
    done
    for block in merge gate; do (cd "$folder" && bash -e "$scratch/$block.sh") > "$scratch/$block.log" 2>&1; done
    if [ -z "$wanted" ]; then test ! -d "$folder/.sarif-merged"; fi
    if [ -n "$wanted" ]; then
      tools=spotbugs
      if [ "$mode" = lint ]; then tools='pmd checkstyle'; fi
      for tool in $tools; do
        case "$tool" in spotbugs) filename=spotbugsSarif.json ;; pmd) filename=pmd.sarif.json ;; checkstyle) filename=checkstyle-result.xml ;; esac
        for fault in missing invalid failed finding; do
          target="$folder/a/target/$filename"
          cp "$repo/.github/tests/analysis/fixtures/$tool-clean.json" "$target"
          case "$fault" in
            missing) rm "$target" ;;
            invalid) printf '{' > "$target" ;;
            failed) jq '.runs[0].invocations=[{executionSuccessful:false}]' "$target" > "$target.tmp"; mv "$target.tmp" "$target" ;;
            finding) jq '.runs[0].results=[{message:{text:"scoped finding"}}]' "$target" > "$target.tmp"; mv "$target.tmp" "$target" ;;
          esac
          for block in merge gate; do
            status=0
            (cd "$folder" && bash -e "$scratch/$block.sh") > "$scratch/$block.log" 2>&1 || status=$?
            if [ "$fault:$block" = finding:merge ]; then test "$status" -eq 0; else test "$status" -ne 0; fi
          done
        done
        cp "$repo/.github/tests/analysis/fixtures/$tool-clean.json" "$folder/a/target/$filename"
      done
    fi
    echo "PASS: scope $mode/$scenario"
  done
  unset SPOTBUGS_KEPT SPOTBUGS_EXPECT_REPORTS SPOTBUGS_SCOPE_ARGS LINT_KEPT LINT_EXPECT_REPORTS LINT_SCOPE_ARGS
  if (cd "$folder" && GITHUB_ENV="$scratch/invalid-base-env" bash ".github/scripts/$script" does-not-exist "$mode") > "$scratch/invalid-base.log" 2>&1; then
    echo 'FAIL: invalid base was accepted'; exit 1
  fi
  test ! -s "$scratch/invalid-base-env"
  echo "PASS: scope $mode/invalid-base"
done
