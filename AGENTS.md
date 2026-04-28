# DSL DevKit - Agent Guidelines

This document helps AI coding agents work effectively with the DSL DevKit codebase.

## Project Overview

- **Type**: Multi-module Tycho/Eclipse/OSGi project
- **Purpose**: DSL development toolkit built on Xtext
- **Java**: 21+
- **Maven**
- **Tycho**
- **Xtext/Xtend**

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

```bash
# Full CI build (Linux - requires xvfb for UI tests)
xvfb-run mvn clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check -f ./ddk-parent/pom.xml --batch-mode --fail-at-end

# Windows build
mvn clean verify -f ./ddk-parent/pom.xml

# Quick build without tests
mvn clean verify -f ./ddk-parent/pom.xml -DskipTests

# Build specific module
mvn clean verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext

# Run only quality checks (after initial build)
mvn checkstyle:check pmd:check spotbugs:check -f ./ddk-parent/pom.xml
```

**Important**: Set `WORKSPACE` environment variable to project root before building:
```bash
export WORKSPACE=$(pwd)
```

## Quality Tools

### PMD
- **Ruleset**: `ddk-configuration/pmd/ruleset.xml`
- Excludes: `src-gen/`, `src-model/`, `xtend-gen/`

### Checkstyle
- **Config**: `ddk-configuration/checkstyle/avaloq.xml`
- **Test config**: `ddk-configuration/checkstyle/avaloq-test.xml`
- Only checks `src/` directory

### SpotBugs
- **Exclusions**: `ddk-configuration/findbugs/exclusion-filter.xml`
- Max rank: 15, Threshold: Low

## Testing

- **Framework**: JUnit 5 with Tycho Surefire
- **Timeout**: 30 minutes (1800 seconds)
- **Aggregator module**: `com.avaloq.tools.ddk.xtext.test`
- **UI tests**: Require virtual display (xvfb on Linux) or `-XstartOnFirstThread` (macOS)

### Aggregator pattern — important

The project runs **all tests through one aggregator module**, not per-`.test`-module. `ddk-parent/pom.xml` sets `<skip>true</skip>` on `tycho-surefire-plugin` globally; only `com.avaloq.tools.ddk.xtext.test` overrides it with its own full tycho-surefire configuration. Inside that module, `src/com/avaloq/tools/ddk/xtext/AllTests.java` is a JUnit 5 `@Suite` that `@SelectClasses` from ~14 per-module `*TestSuite` classes (`ExportTestSuite`, `CheckCoreTestSuite`, `TypeSystemTestSuite`, `CheckUiTestSuite`, etc.). Those other `.test` bundles are on `xtext.test`'s OSGi classpath via `Require-Bundle`, so their test classes get discovered and executed inside the single Eclipse runtime spun up for `xtext.test`.

**Consequences for agents:**

- Maven will emit `[INFO] Skipping tests` for every `.test` module except `xtext.test`. This is **correct**, not a bug. Don't "fix" it.
- Flipping `<skip>false</skip>` in another `.test` module will **fail** — those bundles don't carry the tycho-surefire configuration (application, target-platform extras, UI harness) needed to spin up their own test runtime. "Cannot resolve dependencies" is the typical symptom.
- Don't add per-module `tycho-surefire` configuration to individual `.test` poms. To register a new test, either add the test class to an existing `*TestSuite`, or create a new `*TestSuite` class and reference it from `AllTests.java`.
- Why the aggregator design: one Eclipse OSGi runtime startup (~5–10s) instead of N. With ~14 test suites, per-module execution would add ~70–140s of pure startup overhead per run for no functional benefit.
- Non-OSGi pure-POJO tests could theoretically run under plain Maven surefire without Tycho, but most tests depend on Xtext injectors, Eclipse resource APIs, or the UI workbench — so the aggregator is the right choice.

## Code Patterns

### Dependencies
- Use `META-INF/MANIFEST.MF` for OSGi bundle dependencies (not pom.xml)

### Generated Code
These directories contain generated code - do not edit manually:
- `src-gen/` - Xtext generated sources
- `src-model/` - EMF model generated sources
- `xtend-gen/` - Xtend transpiled Java sources

### Xtend
- `.xtend` files in `src/` compile to Java in `xtend-gen/`

## Common Tasks

### Adding a New Module
1. Create module directory with standard structure
2. Add `META-INF/MANIFEST.MF` for OSGi metadata
3. Add `build.properties` for Tycho
4. Add module to `ddk-parent/pom.xml` modules list
5. Follow naming convention: `com.avaloq.tools.ddk.<component>`

### Fixing PMD Violations
1. Check ruleset at `ddk-configuration/pmd/ruleset.xml`
2. Violations in generated code (`src-gen/`, `xtend-gen/`) are excluded
3. Run `mvn pmd:check -f ./ddk-parent/pom.xml` to verify fixes

### Fixing Checkstyle Violations
1. Check config at `ddk-configuration/checkstyle/avaloq.xml`
2. Run `mvn checkstyle:check -f ./ddk-parent/pom.xml` to verify fixes

### Running Specific Tests
```bash
# Run tests for a specific module
mvn verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.test

# On Linux, wrap with xvfb for UI tests
xvfb-run mvn verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.test
```

## CI/CD

- **Platform**: GitHub Actions
- **Workflow**: `.github/workflows/verify.yml`
- Triggers on: push to master, pull requests
