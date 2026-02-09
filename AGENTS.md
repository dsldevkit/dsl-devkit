# DSL DevKit - Agent Guidelines

This document helps AI coding agents work effectively with the DSL DevKit codebase.

## Project Overview

- **Type**: Multi-module Tycho/Eclipse/OSGi project (~65 modules)
- **Purpose**: DSL development toolkit built on Xtext
- **Java**: 21
- **Maven**: 3.9+
- **Tycho**: 5.0.2
- **Xtext/Xtend**: 2.41.0

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

# Quick build without quality checks
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

### PMD (7.21.0)
- **Ruleset**: `ddk-configuration/pmd/ruleset.xml`
- **Plugin**: 3.28.0
- Excludes: `src-gen/`, `src-model/`, `xtend-gen/`

### Checkstyle (13.0.0)
- **Config**: `ddk-configuration/checkstyle/avaloq.xml`
- **Test config**: `ddk-configuration/checkstyle/avaloq-test.xml`
- Only checks `src/` directory

### SpotBugs (4.9.8)
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
- Use `MANIFEST.MF` for OSGi bundle dependencies (not pom.xml)
- Bundle dependencies defined in `META-INF/MANIFEST.MF`

### Generated Code
These directories contain generated code - do not edit manually:
- `src-gen/` - Xtext generated sources
- `src-model/` - EMF model generated sources
- `xtend-gen/` - Xtend transpiled Java sources

### Xtend
- `.xtend` files in `src/` compile to Java in `xtend-gen/`
- Xtend version: 2.41.0

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
- **Runner**: Ubuntu 24.04
- **Workflow**: `.github/workflows/verify.yml`
- Triggers on: push to master, pull requests
