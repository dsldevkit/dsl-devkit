#!/usr/bin/env bash

sarif_source_modules() {
  grep -oE '<module>\.\./[^<]+</module>' ddk-parent/pom.xml \
    | sed -E 's#.*\.\./([^<]+)</module>#\1#' \
    | while IFS= read -r module; do
        if [ -f "$module/META-INF/MANIFEST.MF" ] && [ -d "$module/src" ]; then
          echo "$module"
        fi
      done
}

validate_sarif() {
  local report=$1 analyzer=${2:?expected analyzer required}
  if ! jq -se --arg analyzer "$analyzer" '
    def valid_base($run; $seen):
      . as $id | type == "string" and ($seen | index($id) | not)
      and ($run.originalUriBaseIds[$id] | type == "object")
      and ($run.originalUriBaseIds[$id] |
        if has("uriBaseId") then .uriBaseId | valid_base($run; $seen + [$id])
        else (.uri | type == "string") end);
    def optional_array($key): (has($key) | not) or (.[$key] | type == "array");
    length == 1 and (.[0] |
      type == "object" and .version == "2.1.0"
      and (.runs | type == "array" and length > 0)
      and all(.runs[];
        (.tool.driver.name == $analyzer)
        and (.results | type == "array")
        and (. as $run | all(.. | objects | select(has("uriBaseId")); .uriBaseId | valid_base($run; [])))
        and optional_array("invocations")
        and ($analyzer == "Checkstyle" or (.invocations | type == "array" and length > 0))
        and all(.results[]?;
          type == "object" and (.message | type == "object"))
        and all(.invocations[]?;
          type == "object"
          and .executionSuccessful == true
          and optional_array("toolExecutionNotifications")
          and optional_array("toolConfigurationNotifications")
          and all(.toolExecutionNotifications[]?, .toolConfigurationNotifications[]?;
            type == "object" and .level != "error"))))
  ' "$report" >/dev/null; then
    echo "::error::${report} is missing, invalid SARIF, or reports unsuccessful analysis."
    return 1
  fi
}

merge_sarif() {
  local report=$1 output=$2 modules=$3 analyzer=${4:?expected analyzer required} module
  local inputs=()
  for module in $modules; do
    validate_sarif "${module}/target/${report}" "$analyzer" || return 1
    inputs+=("${module}/target/${report}")
  done
  if [ ${#inputs[@]} -eq 0 ]; then
    echo "::error::No expected modules supplied for ${report}."
    return 1
  fi
  mkdir -p "$(dirname "$output")"
  jq -s --arg root "${GITHUB_WORKSPACE:-$(pwd -P)}" '
    def file_path:
      (if startswith("file://localhost/") then ltrimstr("file://localhost")
       elif startswith("file:/") then sub("^file:/+"; "/")
       elif startswith("/") then . else error("Expected a file URI") end) as $path
      | reduce ($path | split("/"))[] as $part ([];
          if $part == "" or $part == "." then .
          elif $part == ".." then
            if length > 0 then .[:-1] else error("Source path escapes filesystem root") end
          else . + [$part] end)
      | "/" + join("/") + (if ($path | endswith("/")) and length > 0 then "/" else "" end);
    def resolve_uri($run; $root):
      . as $location
      | (if has("uriBaseId") then $run.originalUriBaseIds[.uriBaseId] | resolve_uri($run; $root)
         else $root end) as $base
      | ($location.uri // "") as $uri
      | if ($uri | startswith("/") or startswith("file:/")) then $uri | file_path
        elif ($uri | test("^[A-Za-z][A-Za-z0-9+.-]*:")) then error("Unsupported source URI scheme")
        elif $uri == "" then $base
        else (($base | sub("[^/]*$"; "")) + $uri) | file_path end;
    def repository_locations($root):
      . as $run | del(.originalUriBaseIds)
      | walk(if type == "object" and (has("uri") or has("uriBaseId")) then
          (resolve_uri($run; $root)) as $absolute
          | if ($absolute | startswith($root)) then
              .uri = ($absolute | ltrimstr($root)) | del(.uriBaseId)
            else error("Source location is outside the repository: " + $absolute) end
        else . end);
    def identical:
      unique | if length == 1 then .[0] else error("Conflicting SARIF metadata") end;
    def descriptors:
      group_by(.id) | map(identical);
    def compatible_run:
      if ((keys - ["tool", "results", "invocations", "originalUriBaseIds", "taxonomies"]) | length) > 0
        or any(.. | objects; has("index") or has("invocationIndex"))
      then error("Unsupported run metadata or indexed reference; update the merger") else . end;
    def resolve_rules:
      .tool.driver.rules as $rules
      | .results |= map(if has("ruleIndex") then
          .ruleIndex as $index
          | if ($index | type) != "number" or $index < 0 or ($index | floor) != $index
              or $rules[$index].id == null
              or (has("ruleId") and .ruleId != $rules[$index].id)
            then error("Invalid ruleIndex")
            else .ruleId = $rules[$index].id | del(.ruleIndex) end
        else . end);
    ($root | split("/") | map(@uri) | join("/") | rtrimstr("/") + "/") as $root_uri
    | [.[].runs[] | compatible_run | resolve_rules | repository_locations($root_uri)] as $runs
      | {
          "$schema": "https://json.schemastore.org/sarif-2.1.0.json",
          version: "2.1.0",
          runs: [{
            tool: ([$runs[].tool | del(.driver.rules)] | identical
              | .driver.rules = ([$runs[].tool.driver.rules[]?] | descriptors)),
            taxonomies: ([$runs[].taxonomies[]?] | group_by([.name, .guid])
              | map(. as $group | map(del(.taxa)) | identical
                  | .taxa = ([$group[].taxa[]?] | descriptors))),
            results: [$runs[].results[]?],
            invocations: [$runs[].invocations[]?]
          }]
        }
  ' "${inputs[@]}" > "$output"
}

cpd_count() {
  local report=$1 valid count
  if [ ! -s "$report" ]; then
    echo "::error::${report} is missing or empty." >&2
    return 1
  fi
  valid=$(xmllint --nonet --xpath \
    'boolean(/*[local-name()="pmd-cpd"] and not(//*[local-name()="error"]) and not(/*/*[local-name()!="duplication" and local-name()!="file"]))' \
    "$report") || return 1
  if [ "$valid" != "true" ]; then
    echo "::error::${report} is not a successful CPD report." >&2
    return 1
  fi
  count=$(xmllint --nonet --xpath 'count(/*/*[local-name()="duplication"])' "$report") || return 1
  printf '%s\n' "$count"
}
