#!/usr/bin/env bash
# Regenerates sources by running every tracked MWE2 workflow headless, without Eclipse, except those listed below.
# Build first, on JDK 21, and again after changing a grammar or generator:
#   ./mvnw install -DskipTests -f ddk-parent/pom.xml -pl ":ddk-target,$(.github/scripts/regenerate.sh --owners)" -am -Dtycho.baseline=disable -Dtycho.baseline.replace=none
# Arguments are passed to Maven, e.g. -Dmaven.repo.local=...; --owners prints the owning bundles as a -pl list, --list the workflows it runs.
set -euo pipefail

# Not standalone workflows.
EXCLUDE=(
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/Ddk.mwe2            # only chains the other workflows
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/CommonXbase.mwe2    # shared module of the language workflows
  com.avaloq.tools.ddk.check.test.runtime/src/com/avaloq/tools/ddk/check/generator/TestLanguageGeneratorMWE.mwe2  # needs targetDir and modelPath
)

# Workflows whose committed output still differs from what they generate, with the issue that fixes each.
KNOWN_DRIFT=(
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/GenerateExport.mwe2                 # #1671
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/GenerateFormat.mwe2                 # #1671
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/GenerateFormatterTestLanguage.mwe2  # #1671
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/GenerateCheck.mwe2                  # #1671
  com.avaloq.tools.ddk.workflow/src/com/avaloq/tools/ddk/workflow/GenerateCheckCfg.mwe2               # #1671
  com.avaloq.tools.ddk.sample.helloworld/src/com/avaloq/tools/ddk/sample/helloworld/GenerateHelloWorld.mwe2  # #1671
  com.avaloq.tools.ddk.check.test.runtime/src/com/avaloq/tools/ddk/check/GenerateTestLanguage.mwe2          # #1672
)

root=$(git rev-parse --show-toplevel)
cd "$root"

# <owning bundle>/<workflow path relative to that bundle>, for every tracked workflow not listed above.
skip=" ${EXCLUDE[*]} ${KNOWN_DRIFT[*]-} "
tracked=" $(git ls-files '*.mwe2' | tr '\n' ' ') "
for wf in ${skip}; do
  [[ $tracked == *" $wf "* ]] || { echo "regenerate.sh: listed workflow $wf is not tracked" >&2; exit 1; }
done
WORKFLOWS=()
while read -r wf; do
  [[ $skip == *" $wf "* ]] || WORKFLOWS+=("$wf")
done < <(git ls-files '*.mwe2')

bundles=()
for wf in "${WORKFLOWS[@]}"; do
  b=${wf%%/*}
  [[ " ${bundles[*]-} " == *" $b "* ]] || bundles+=("$b")
done

projects=$(printf ',:%s' "${bundles[@]}")
if [[ ${1-} == --owners ]]; then
  echo "${projects:1}"
  exit 0
fi
if [[ ${1-} == --list ]]; then
  printf '%s\n' "${WORKFLOWS[@]}"
  exit 0
fi

# All Maven runs happen before the workflows touch the tree (jgit.dirtyWorkingTree=error in CI).
tycho=$(sed -n 's:.*<tycho.version>\(.*\)</tycho.version>.*:\1:p' ddk-parent/pom.xml)
./mvnw -f ddk-parent/pom.xml --batch-mode -pl "${projects:1}" "$@" \
  "org.eclipse.tycho.extras:tycho-dependency-tools-plugin:${tycho}:list-dependencies"

# Classpath: owning bundle, third-party jars, DDK bundles from the reactor's target/classes, ANTLR patch.
for b in "${bundles[@]}"; do
  # Not in a command substitution, so set -e stops the script on a missing list or unbuilt bundle.
  {
    echo "$root/$b/target/classes"
    grep -v -e '\.source[-/_]' -e '-sources\.jar$' "$b/target/dependencies-list.txt" | while read -r jar; do
      bsn=$(basename "$(dirname "$(dirname "$jar")")")
      if [[ $bsn == com.avaloq.tools.ddk* ]]; then
        [[ -d "$root/$bsn/target/classes" ]] || { echo "regenerate.sh: $bsn is not built" >&2; exit 1; }
        echo "$root/$bsn/target/classes"
      else
        echo "$jar"
      fi
    done
    echo "$root/com.avaloq.tools.ddk.workflow/antlr-generator-3.2.0-patch.jar"
  } | awk '!seen[$0]++' | paste -sd: - > "$b/target/mwe2.cp"
  printf -- '-cp "%s"\n' "$(<"$b/target/mwe2.cp")" > "$b/target/mwe2.args"
done

failed=()
for wf in "${WORKFLOWS[@]}"; do
  b=${wf%%/*}
  echo "::group::${wf##*/}"
  # Workflows resolve paths against rootPath = "..", so they run from the owning bundle's directory.
  (cd "$root/$b" && "${JAVA_HOME:+$JAVA_HOME/bin/}java" -Xmx3g @target/mwe2.args org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher "${wf#*/}") || failed+=("$wf")
  echo "::endgroup::"
done

if (( ${#failed[@]} )); then
  printf 'regenerate.sh: workflow failed: %s\n' "${failed[@]}" >&2
  exit 1
fi
