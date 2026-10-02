# Static-analysis CI design: SARIF inline display + count-gate

This documents *why* the static-analysis CI is shaped the way it is, so the
mechanics (verified against the plugin source) don't have to be re-discovered.

## Goal

Fast, complete, **early** PMD + Checkstyle feedback — a violation should fail CI
in minutes, on its own check, not after the ~15-minute build. SpotBugs (the slow
analysis) runs in its own parallel lane so it never delays the fast checks. All
of PMD, Checkstyle, and SpotBugs surface **inline** on the PR via SARIF + GitHub
Code Scanning (no custom annotator).

## Job shape (`verify.yml`)

Five independent parallel jobs (no `needs:`); wall-clock = the slowest job.

| Job | Runs | Threads | Gate |
|---|---|---|---|
| `lint` | `compile` + `pmd:pmd` + `checkstyle:checkstyle` (SARIF) + `pmd:cpd-check` | `-T 2C` | validated SARIF result count + parsed CPD duplication count |
| `spotbugs` | `compile` + `spotbugs:spotbugs` (SARIF), `-Xmx4g` | `-T 2C` | jq count of SARIF results |
| `maven-verify` | `clean verify` (build + tests only) | none | Tycho-surefire |
| `analysis-regression` | report, merge, mutation and scope fixtures | none | extracted workflow blocks |
| `line-endings` | `git ls-files` check | n/a | shell |

`maven-verify` **no longer re-runs** the analysis goals (now owned by `lint` /
`spotbugs`). Analysis jobs use `-T 2C`; the test job does not (tests are not known
reliable under reactor parallelism).

## Report goals vs. check goals (code-verified at each plugin version tag)

| Goal | Kind | `@Execute` fork | Runs analysis? | Writes | Fails build? | SARIF-capable? |
|---|---|---|---|---|---|---|
| `pmd:pmd` | report | — | yes | `pmd.xml` (+ `pmd.sarif.json` w/ `-Dformat=…SarifRenderer`) | no | **yes** |
| `pmd:check` | check | `@Execute(goal=pmd)` | yes (forked) | `pmd.xml` | yes (`> maxAllowedViolations`, dflt 0; `failurePriority` dflt 5 = all) | no (wants `pmd.xml`) |
| `pmd:cpd` | report | — | yes | `cpd.xml` | no | no (no CPD SARIF renderer) |
| `pmd:cpd-check` | check | `@Execute(goal=cpd)` | yes (forked) | `cpd.xml` | yes | no |
| `checkstyle:checkstyle` | report | — | yes | `checkstyle-result.xml` (XML, or SARIF w/ `-Dcheckstyle.output.format=sarif`) | no | **yes** |
| `checkstyle:check` | check | **none** (self-contained) | yes (internal, source-based) | `checkstyle-result.xml` (XML) | yes (severity ≥ `violationSeverity`) | no |
| `spotbugs:spotbugs` | report | — | yes (bytecode) | `spotbugsXml.xml` (+ `spotbugsSarif.json` w/ `-Dspotbugs.sarifOutput=true`) | no | **yes** |
| `spotbugs:check` | check | `@Execute(goal=spotbugs)` | yes (forked) | `spotbugsXml.xml` | yes (`bugCount > 0`) | no |

The split is **observe vs. enforce**: report goals produce output (for the Maven
site / dashboards / SARIF consumers); analysis errors can still fail a report goal. Check goals bind to
`verify` and exist to fail the build. The `@Execute(goal=…)` on the PMD/CPD/SpotBugs
checks forks the report goal first (so `mvn pmd:check` is self-contained) — which
means they **re-run the analysis every time**. `checkstyle:check` is the lone
exception: no `@Execute`, runs Checkstyle internally in one pass.

## Maven reactor failure flags

| Flag | Reactor behavior | Multi-module report completeness | Suppresses violation failure? |
|---|---|---|---|
| `--fail-fast` (default) | halt at first failing module | downstream skipped → reports missing | no |
| `--fail-at-end` | build independent modules, fail at end | modules downstream of a failure still cascade-skipped → incomplete | no |
| `--fail-never` | suppresses every Maven failure | attempts remaining modules, but analysis can still fail to produce usable reports | yes |

## Report goals, preserved process status, and validated reports

Each SARIF producer runs once with `--fail-at-end`. Its process status remains
part of the job outcome, including a failure after it has written a report.
Independent modules continue; downstream modules may be skipped after a failure.
The merge requires every expected report, so incomplete analysis stays red even
when other modules produced clean reports. This trades report completeness on a
broken build for dependable failure reporting.

The shared validator applies to SpotBugs, PMD and Checkstyle. It rejects malformed
JSON, unusable run/result structure, unsuccessful invocations and error execution
or configuration notifications. SpotBugs and PMD must include nonempty completion
metadata; Checkstyle's renderer legitimately omits it. Maven's nonzero exit status
therefore remains essential. CPD XML is parsed, checked for processing errors and
counted structurally, with Maven failures preserved too.


### Why not the `:check` goals
- They `@Execute`-fork a **second** analysis on top of the `pmd:pmd` we already run
  for SARIF — pure waste.
- The forked analysis is only correct with the full `compile` + classpath; run cheaply
  (`-Dtycho.mode=maven` / no compile) it loses the classpath and **false-positives**
  (e.g. PMD `InvalidLogMessageFormat` flags the SLF4J trailing-`Throwable` idiom because
  it can't resolve the last arg as a `Throwable`).
- `pmd:check` is **incompatible with `-Dformat=sarif`**: its fork writes `pmd.sarif.json`,
  but the check looks for `pmd.xml` → "unable to find report."

### count-gate == `:check` fidelity (for this project's config)
Counting **all** SARIF results equals what `:check` would fail on, because: PMD has no
`failurePriority` override (default 5 = all priorities), Checkstyle is globally
`severity=warning` with no info-level results, and SpotBugs uses `threshold=Low` with no
`failThreshold`/`maxAllowedViolations` override. Three config-drift caveats would make
the count-gate *stricter* than `:check` (over-fail, never under-fail), each guard-worthy:
1. PMD `pmd.failurePriority` set below 5.
2. A Checkstyle rule emitting `info`/`note` severity.
3. SpotBugs `failThreshold` or non-zero `maxAllowedViolations`.

## Two operational rules

- **A changed module must compile against its complete aux-classpath**, or PMD's
  type-resolving rules false-positive (the trailing-`Throwable` case). A bare `-pl <module>`
  subset breaks this, but `-pl <changed> -am` does not: `--also-make` restores the module's
  dependency closure, which compiles for the classpath even though those deps carry the
  analysis skip. Tycho adds each bundle's MANIFEST requirements (`Require-Bundle`,
  `Import-Package`) to the Maven project model, so `-am` pulls in reactor siblings required
  that way too. `ddk-target` is the one edge with no MANIFEST reference at all, so it is
  pinned explicitly into every scoped reactor (a cold cache without it fails loudly rather
  than resolving a stale target).
- **Merge SARIFs from SARIF files only.** Code Scanning accepts one run per category,
  so per-module SARIFs are merged (jq) before upload. The `ddk-parent` aggregator emits
  plain-XML `checkstyle-result.xml`; it is excluded by the expected source-module list.
  Every expected module report must validate; invalid reports are never filtered out.

## SARIF mechanics

- PMD: `-Dformat=net.sourceforge.pmd.renderers.SarifRenderer` → `pmd.sarif.json`.
- Checkstyle: `-Dcheckstyle.output.format=sarif` → SARIF content in `checkstyle-result.xml`.
- SpotBugs: `-Dspotbugs.sarifOutput=true` → `spotbugsSarif.json`.
- All emit SARIF 2.1.0. Merge per tool → `github/codeql-action/upload-sarif`
  (`permissions: security-events: write`). CPD has no SARIF renderer → parsed XML gate
  (no inline display for CPD; acceptable, duplications are rare).

## Merge preservation and regression checks

The merger resolves each run's URI bases and emits repository-relative source
URIs, including secondary locations. A real fork upload showed that preserving
custom base IDs alone made GitHub store paths outside the actual source tree. It resolves result rule indices before unioning
rule descriptors, retains taxonomy descriptors, and rejects conflicts or run
metadata outside the configured producers' supported profile. The supported
profile and fixture provenance are documented in `.github/tests/analysis/README.md`.

The fast regression job executes the actual workflow merge/gate blocks, checks
real producer fixtures and malformed reports, exercises real Git scope scenarios,
and proves that deliberate regressions are detected. Successful local fixtures
complement the real analyzer runs and GitHub upload checks; they do not establish
GitHub annotation rendering by themselves.
