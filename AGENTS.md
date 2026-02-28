# DSL DevKit - Agent Guidelines

This document helps AI coding agents work effectively with the DSL DevKit codebase.

## Project Overview

- **Type**: Multi-module Tycho/Eclipse/OSGi project
- **Purpose**: DSL development toolkit built on Xtext
- **Java**: 21+
- **Maven**: 3.9+
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
- **Main test module**: `com.avaloq.tools.ddk.xtext.test`
- **UI tests**: Require virtual display (xvfb on Linux)

Tests are disabled by default and activated only in test bundles.

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

---

## Xtend-to-Java Migration (feature/xtend-to-java-migration branch)

> **TEMPORARY SECTION** — Remove this section and `docs/xtend-to-java-conversion-prompt.md` after the migration branch is merged to master. Check on every push.

### Overview

We are migrating all ~94 Xtend source files to idiomatic Java 21. Batch 1 (8 files in `check.core`) is complete. The remaining 86 files are tracked in [`docs/xtend-migration.md`](docs/xtend-migration.md).

### Key References

| Document | Purpose |
|----------|---------|
| [`docs/xtend-migration.md`](docs/xtend-migration.md) | Master checklist — per-file status, batch order, build cleanup tasks |
| [`docs/xtend-to-java-conversion-prompt.md`](docs/xtend-to-java-conversion-prompt.md) | Full conversion prompt with 18 sections of rules, checklist, and worked example |

### Migration Conventions

- **Java 21** target — use pattern matching, switch expressions where appropriate
- **`StringBuilder`** for Xtend template expressions (no Xtend runtime dependency)
- **No `var` keyword** — always use explicit types (`final ExplicitType`, not `final var`)
- **2-space indentation** to match project convention
- Preserve all comments, copyright headers, and method ordering

### Per-File Conversion Workflow

1. Read the `.xtend` source file
2. Read the corresponding `xtend-gen/*.java` file as reference
3. Apply the conversion prompt rules from `docs/xtend-to-java-conversion-prompt.md`
4. Write the `.java` file to `src/` (same package path as the `.xtend` file)
5. Delete the `.xtend` file
6. Delete the `xtend-gen/*.java` file
7. Update the checklist in `docs/xtend-migration.md`

### Verification After Each Batch

```bash
# Must pass
mvn clean compile -f ./ddk-parent/pom.xml --batch-mode

# Must pass (except known macOS UI test)
mvn clean verify -f ./ddk-parent/pom.xml --batch-mode --fail-at-end
```
