# DSL DevKit - Agent Guidelines

This document helps AI coding agents work effectively with the DSL DevKit codebase.

## Project Overview

- **Type**: Multi-module Tycho/Eclipse/OSGi project
- **Purpose**: DSL development toolkit built on Xtext
- **Java**: 21+
- **Maven**
- **Tycho**
- **Xtext**

## Setup

After cloning, run:

```bash
./.agents/sync.sh
```

This creates a local mirror of `.agents/skills/` at `.claude/skills/` (gitignored) so Claude Code auto-discovers the project's skills. macOS/Linux use a symlink; Windows gets a recursive copy. Re-run after any pull that touches `.agents/skills/`.

## Key Directories

| Directory | Purpose |
|-----------|---------|
| `ddk-parent/` | Parent POM, build entry point |
| `ddk-configuration/` | Quality tool configs (PMD, Checkstyle, SpotBugs) |
| `ddk-target/` | Eclipse target platform definition |
| `ddk-repository/` | P2 update site |
| `com.avaloq.tools.ddk.*` | Source modules |
| `*.test` | Test modules |

## Build Commands

Use the Maven Wrapper (`./mvnw`, or `mvnw.cmd` on Windows). It pins Maven 3.9.x: Maven 3.10 cannot build this project yet (eclipse-tycho/tycho#6399).

```bash
# Full CI build (Linux - requires xvfb for UI tests)
xvfb-run ./mvnw clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check -f ./ddk-parent/pom.xml --batch-mode --fail-at-end

# Windows build
mvnw.cmd clean verify -f ./ddk-parent/pom.xml

# Quick build without tests
./mvnw clean verify -f ./ddk-parent/pom.xml -DskipTests

# Build specific module
./mvnw clean verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext

# Run only quality checks (after initial build)
./mvnw checkstyle:check pmd:check spotbugs:check -f ./ddk-parent/pom.xml
```

## Quality Tools

### PMD
- **Ruleset**: `ddk-configuration/pmd/ruleset.xml`
- Excludes: `src-gen/`, `src-model/`

### Checkstyle
- **Config**: `ddk-configuration/checkstyle/avaloq.xml`
- **Test config**: `ddk-configuration/checkstyle/avaloq-test.xml`
- Only checks `src/` directory

### SpotBugs
- **Exclusions**: `ddk-configuration/findbugs/exclusion-filter.xml`
- Max rank: 15, Threshold: Low

## Testing

- **Framework**: JUnit 5 with Tycho Surefire
- **Timeout**: 30 minutes (1800 seconds) per test module
- **UI tests**: Require virtual display (xvfb on Linux) or `-XstartOnFirstThread` (macOS)

### macOS developer setup

SWT on Cocoa requires the Display to be created on the main thread. UI tests
therefore need `-XstartOnFirstThread`. There are two contexts:

- **`mvn verify` / CI**: the `macosx` profile in `ddk-parent/pom.xml` (activated
  on `family=mac`) adds the flag automatically. No setup required.
- **Running launches from Eclipse**: add `-XstartOnFirstThread` to your JRE's
  default VM arguments — `Window > Preferences > Java > Installed JREs > [your
  JDK] > Edit > Default VM arguments`. PDE launches with `append.args=true`
  inherit this on macOS only. The flag is not hardcoded in the `.launch` files
  because the JVM on Windows and Linux rejects it as an "Unrecognized VM
  option". This matches Eclipse Platform's own UI test launches (which omit
  the flag and rely on the same JRE-default-args mechanism).

### One JUnit run per test module

Every test bundle (packaging `eclipse-test-plugin`) runs its own tests in its own Eclipse runtime. `tycho-surefire-plugin` is configured once in `ddk-parent/pom.xml`; individual test poms only set the properties below if they need a workbench. Surefire finds the tests by class name, so there are no test suites to maintain. Fragment test bundles work the same way, with their host in the runtime.

**Consequences for agents:**

- A new test needs no registration: name the class `*Test`, `*Tests`, `Test*` or `*TestCase`. Classes with other names are not run; CI fails if a concrete class declaring tests has no report (`.github/scripts/check-surefire-reports.sh`).
- A new test bundle needs packaging `eclipse-test-plugin` and an entry in the `ddk-parent/pom.xml` modules list. With packaging `eclipse-plugin` its tests do not run.
- A module's runtime holds only the dependency closure of its manifest. If a test needs a bundle at runtime, for example a language's UI bundle, declare it in that test bundle's `META-INF/MANIFEST.MF`.
- Test classes run in alphabetical order within a module and must not depend on running after other classes.
- Test modules run headless by default, which is Tycho's default: no workbench starts, so `PlatformUI.getWorkbench()` and any Guice injector that needs `IWorkbench` fail. A module that needs a workbench sets `tycho.surefire.useUIHarness` to `true` and `test.reactorConcurrencyLevel` to `1` in its pom's `<properties>`.
- With `-T`, headless modules run in parallel while modules with a workbench take turns (`test.reactorConcurrencyLevel`). `CheckQuickfixTest` has failed intermittently on macOS when workbenches overlapped; rerun serially before debugging such a failure.
- A failed module's tests leave their log in `<module>/target/work/data/.metadata/.log`. CI archives these logs for failed builds.
- To run the tests of one UI bundle from Eclipse, use its launch configuration (e.g. `com.avaloq.tools.ddk.check.ui.test/CheckUiTests.launch`), which runs all tests of the project via JUnit discovery in Eclipse.

## Code Patterns

### Dependencies
- Use `META-INF/MANIFEST.MF` for OSGi bundle dependencies (not pom.xml)

### Generated Code
These directories contain generated code - do not edit manually:
- `src-gen/` - Xtext generated sources
- `src-model/` - EMF model generated sources

## Common Tasks

### Adding a New Module
1. Create module directory with standard structure
2. Add `META-INF/MANIFEST.MF` for OSGi metadata
3. Add `build.properties` for Tycho
4. Add module to `ddk-parent/pom.xml` modules list
5. Follow naming convention: `com.avaloq.tools.ddk.<component>`

### Fixing PMD Violations
1. Check ruleset at `ddk-configuration/pmd/ruleset.xml`
2. Violations in generated code (`src-gen/`) are excluded
3. Run `./mvnw pmd:check -f ./ddk-parent/pom.xml` to verify fixes

### Fixing Checkstyle Violations
1. Check config at `ddk-configuration/checkstyle/avaloq.xml`
2. Run `./mvnw checkstyle:check -f ./ddk-parent/pom.xml` to verify fixes

### Running Specific Tests
```bash
# Run tests for a specific module
./mvnw verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.test

# On Linux, wrap with xvfb for UI tests
xvfb-run ./mvnw verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.test
```

### Bumping Versions
CI fails with "Only qualifier changed" when a published bundle or feature differs from the last release (`p2/releases/latest`) but keeps its version.
1. Changing a published bundle (one included in the features, incl. `test.core`, `test.ui`, `xtext.test.core`): find the last release with `git tag --list 'v*' --merged HEAD --sort=-version:refname | head -1` and compare, `git show <tag>:<bundle>/META-INF/MANIFEST.MF`. Same version → bump the patch in `META-INF/MANIFEST.MF` (`x.y.z.qualifier`) and `pom.xml` (`x.y.z-SNAPSHOT`). Already higher → leave it.
2. If both features still carry the last release's version, bump them to the next patch (e.g. 19.3.0 → 19.3.1) in `com.avaloq.tools.ddk.feature` and `com.avaloq.tools.ddk.runtime.feature` (`feature.xml`, `pom.xml`) and both entries in `ddk-repository/category.xml`.
3. No bump for changes outside published bundles (`ddk-parent`, workflows, other `*.test` modules).
4. Verify through the features:
```bash
./mvnw -f ./ddk-parent/pom.xml -pl :ddk-target,:com.avaloq.tools.ddk.runtime.feature,:com.avaloq.tools.ddk.feature,:ddk-repository -am -T 3C -DskipTests verify
```

## CI/CD

- **Platform**: GitHub Actions
- **Workflows**: `.github/workflows/verify.yml` (pull requests and the merge queue), `snapshot.yml` (pushes to `master`, publishes the p2 snapshot), `prepare-release.yml` and `release.yml` (manual)
- **Merging**: PRs merge through the merge queue on `master`.
- **Releases**: run the `release` workflow (`version_bump`: patch/minor/major). It tags and republishes the head's snapshot, and fails unless every `feature.xml` already has the release version. If the features do not have it yet, first run the `prepare-release` workflow with the same inputs: it sets both features, their poms and `category.xml` to the release version and opens a PR (e.g. "build: prepare DDK release 19.3.0"). The `verify` runs of a PR opened by GitHub Actions wait for approval ("Approve workflows to run" in the PR).
