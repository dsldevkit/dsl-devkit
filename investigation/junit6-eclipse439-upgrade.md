# Step 4 — JUnit 6 + Eclipse 4.39 upgrade — investigation log

Branch: `feat/upgrade-junit6-eclipse439-step-4` (this branch).

> **This document is intended to be dropped before merging the JUnit 6 upgrade to master.** It captures the iteration history, dead ends, and rationale that produced the current target/pom shape — useful for reviewers and future maintainers, but not part of the deliverable surface. The commit that adds this file lives at the top of the branch as a capstone, with no other commits depending on it.
>
> To drop it cleanly:
>
> ```bash
> git reset --hard HEAD~1
> ```
>
> No conflicts will arise — the feat commit beneath it doesn't reference this file.

## Goal

Continue the staged plan after step 3 (which dropped JUnit 4 entirely from the build) by:
- Bumping Eclipse simrel `2024-12` (Platform 4.34) → `2026-03` (Platform 4.39)
- Bumping JUnit Jupiter 5.13.4 → 6.0.3 and JUnit Platform 1.13.4 → 6.0.3
- Switching Tycho test provider hint `junit5` → `junit6`
- Bumping orbit-aggregation `4.37.0` → `4.39.0` and the orbit deps that pin to 4.37-era versions back to 4.39-era (mockito 5.21.0, byte-buddy 1.18.5, objenesis 3.5.0, commons-lang3 3.20.0, commons-text 1.15.0, log4j 2.25.3)

User's stated requirement: *"What I do want is for all the tests to be JUnit 6"*. So `vintage-engine`-style coexistence with JUnit 5 is not acceptable — we need a JUnit-6-only runtime.

## Result

**Currently deferred.** The upgrade is structurally blocked by **dual-version JUnit pollution** in Eclipse 4.39's PDE chain. Four iterations attempted, all hit the same `ArrayStoreException` at test discovery — same architectural shape as PR #1292's silent-zero bug, just with different version pairs colliding.

This file documents what was tried, what we learned about the dependency graph, and the specific upstream pieces we'd need before this upgrade is feasible.

## Iterations attempted

All four ran via `mvn clean verify -f ./ddk-parent/pom.xml --batch-mode --fail-at-end -T 3C -Dtest.javaOptions="-XstartOnFirstThread --add-modules=jdk.incubator.vector" -Dosgi.os=macosx -Dosgi.ws=cocoa -Dosgi.arch=aarch64` on `feat/upgrade-junit6-eclipse439-step-4` based on step 3.

### Iteration A — base attempt

- Eclipse SDK location `eclipse/updates/4.34/` → `releases/2026-03/`
- Kept `pde.source.feature.group + jdt.source.feature.group + platform.feature.group + platform.source.feature.group`
- orbit-aggregation `release/4.37.0` → `release/4.39.0`
- All JUnit IUs `5.13.4 / 1.13.4` → `6.0.3 / 6.0.3`
- Dep version bumps (mockito etc.) back to 4.39-era
- `xtext.test/pom.xml`: `<providerHint>junit5</providerHint>` → `<providerHint>junit6</providerHint>`
- ssequenceNumber 28 → 29

**Outcome**: Target resolution failed.

```
Could not find "org.eclipse.pde.source.feature.group/0.0.0" in the repositories of the current location
```

The `*.source.feature.group` IUs were renamed in modern simrels.

### Iteration B — simplified Eclipse location

Replaced the 4-IU Eclipse list with `platform.feature.group + pde.feature.group` (the original investigation note from `investigation/junit-test-baseline.md` predicted this pattern from the reference target). PDE transitively brings JDT.

**Outcome**: Target resolution succeeds, compile passes, then test discovery fails:

```
java.lang.ArrayStoreException: org.junit.platform.engine.discovery.ClassSelector
	at org.apache.maven.surefire.junitplatform.TestPlanScannerFilter.accept(TestPlanScannerFilter.java:48)
	at org.apache.maven.surefire.api.util.DefaultScanResult.applyFilter(DefaultScanResult.java:87)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.scanClasspath(JUnitPlatformProvider.java:144)
	at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:124)
```

Same architectural failure as PR #1292's silent-zero, surfaced loudly because Tycho's `junit6` provider treats this as an error rather than a silent zero.

### Iteration C — explicit `junit-platform-launcher` in OSGi `<dependencies>`

Per JUnit 6 build-support docs (`https://docs.junit.org/6.0.3/running-tests/build-support.html`): "declare junit-platform-launcher explicitly to keep the launcher version aligned with the engines". Added inside `tycho-surefire-plugin`'s `<configuration><dependencies>` block:

```xml
<dependency>
  <type>p2-installable-unit</type>
  <artifactId>junit-platform-launcher</artifactId>
  <version>6.0.3</version>
</dependency>
```

**Outcome**: Same `ArrayStoreException`. The `<configuration><dependencies>` adds to the OSGi runtime, not the Maven plugin classpath. The OSGi runtime already had Platform 6.0.3; adding it again was a no-op.

### Iteration D — outer plugin Maven-classpath override

Added Maven-classpath plugin dependencies (outside `<configuration>`) to override Tycho's bundled `surefire-junit-platform`:

```xml
<plugin>
  <groupId>org.eclipse.tycho</groupId>
  <artifactId>tycho-surefire-plugin</artifactId>
  <version>${tycho.version}</version>
  <dependencies>
    <dependency>
      <groupId>org.apache.maven.surefire</groupId>
      <artifactId>surefire-junit-platform</artifactId>
      <version>3.5.4</version>
    </dependency>
    <dependency>
      <groupId>org.junit.platform</groupId>
      <artifactId>junit-platform-launcher</artifactId>
      <version>6.0.3</version>
    </dependency>
  </dependencies>
  <configuration>...</configuration>
</plugin>
```

**Outcome**: Same `ArrayStoreException`. The outer override doesn't take effect because Tycho 5.0.2's `tycho-surefire-plugin` doesn't load `surefire-junit-platform` directly — it loads its own bundled OSGi fragment `org.eclipse.tycho.surefire.junit6` (see Diagnosis below) which is a separate artifact.

## Diagnosis (verified)

After iteration B failed, I inspected the OSGi runtime's `config.ini` at `com.avaloq.tools.ddk.xtext.test/target/work/configuration/config.ini` and found **two complete copies of every JUnit Platform / Jupiter bundle in the runtime**:

```
junit-jupiter-api-5.14.3.jar              ← unwanted, transitively pulled
junit-jupiter-api-6.0.3.jar               ← what we asked for
junit-jupiter-engine-5.14.3.jar           ←
junit-jupiter-engine-6.0.3.jar            ←
junit-jupiter-migrationsupport-5.14.3.jar ← junit5-only
junit-jupiter-params-5.14.3.jar           ←
junit-jupiter-params-6.0.3.jar            ←
junit-platform-commons-1.14.3.jar         ←
junit-platform-commons-6.0.3.jar          ←
junit-platform-engine-1.14.3.jar          ← THIS exports ClassSelector
junit-platform-engine-6.0.3.jar           ← AND THIS exports ClassSelector
junit-platform-launcher-1.14.3.jar        ←
junit-platform-launcher-6.0.3.jar         ←
junit-platform-runner-1.14.3.jar          ← junit5-only
junit-platform-suite-api-1.14.3.jar       ←
junit-platform-suite-api-6.0.3.jar        ←
junit-platform-suite-commons-1.14.3.jar   ← junit5-only
junit-platform-suite-engine-1.14.3.jar    ←
junit-platform-suite-engine-6.0.3.jar     ←
junit-vintage-engine-5.14.3.jar           ← junit5-only
```

`org.junit.platform.engine.discovery.ClassSelector` therefore exists as two distinct `Class<?>` instances in the OSGi class space — one per JUnit Platform bundle. Tycho's JUnit 6 provider (running in OSGi context) creates a `Filter[]` whose component type is the 6.0.3 `Filter`, then receives discovery objects (e.g. a `ClassSelector`) bound to the 1.14.3 classloader. JVM rejects the array store → `ArrayStoreException`.

### Why the JUnit 5 family is in the runtime

Two contributors confirmed by examining bundle `MANIFEST.MF` files in `~/.m2/repository/p2/osgi/bundle/`:

**1. `org.eclipse.jdt.junit5.runtime` 1.2.0** (current, ships in 2026-03 simrel via PDE feature group):

```
Require-Bundle: org.eclipse.jdt.junit.runtime [3.5.0,4.0.0),
  junit-jupiter-api [5.14.0,6.0.0),
  junit-jupiter-engine [5.14.0,6.0.0),
  junit-jupiter-migrationsupport [5.14.0,6.0.0),
  junit-jupiter-params [5.14.0,6.0.0),
  junit-vintage-engine [5.14.0,6.0.0),
  junit-platform-commons [1.14.0,2.0.0),
  junit-platform-engine [1.14.0,2.0.0),
  junit-platform-launcher [1.14.0,2.0.0),
  junit-platform-runner [1.14.0,2.0.0),
  junit-platform-suite-api [1.14.0,2.0.0),
  junit-platform-suite-commons [1.14.0,2.0.0),
  junit-platform-suite-engine [1.14.0,2.0.0)
```

This is the IDE-side JUnit 5 launcher. **It hard-pins JUnit 5.14.x and Platform 1.14.x with strict ranges that exclude 6.x.** It's pulled by `pde.feature.group`'s reference to JDT.

**2. `org.eclipse.jdt.junit6.runtime` 1.0.0** (current, also ships in 2026-03 simrel):

```
Require-Bundle: org.eclipse.jdt.junit.runtime [3.5.0,4.0.0),
  junit-jupiter-api [6.0.0,7.0.0),
  junit-jupiter-engine [6.0.0,7.0.0),
  junit-jupiter-params [6.0.0,7.0.0),
  org.opentest4j [1.3.0,2.0.0),
  junit-platform-commons [6.0.0,7.0.0),
  junit-platform-engine [6.0.0,7.0.0),
  junit-platform-launcher [6.0.0,7.0.0),
  junit-platform-suite-api [6.0.0,7.0.0),
  junit-platform-suite-engine [6.0.0,7.0.0)
```

This is the parallel JUnit 6 launcher. Pulled the same way.

**Both are referenced by PDE's feature group.** Tycho's planner installs both, transitively pulling the JUnit 5.14 and 6.0 families simultaneously.

### What we ruled OUT as blockers

- **Tycho 5.0.2 itself**: it does ship a dedicated `org.eclipse.tycho.surefire.junit6-5.0.2.jar` OSGi fragment that imports `org.junit.platform.launcher`, `org.junit.jupiter.*`, `org.junit.platform.suite.*`. When the OSGi runtime has *only* JUnit 6.x, this fragment works correctly — it's what `<providerHint>junit6</providerHint>` resolves to. Tycho is not the blocker. (`https://central.sonatype.com/artifact/org.eclipse.tycho/org.eclipse.tycho.surefire.junit6`)
- **maven-surefire's bundled JUnit Platform 5.x classes**: these run on the Maven-side classpath, but Tycho's `tycho-surefire-plugin` for OSGi tests doesn't invoke the upstream maven-surefire-plugin classpath — it uses the `org.eclipse.tycho.surefire.junit6` OSGi fragment directly. So the upstream maven-surefire 3.5.x version pin (currently `<versions.junit5>5.12.2</versions.junit5>`) is not the blocker for OSGi-test workflows like ours.
- **Source-feature naming change** (iteration A's compile error): one-time fix — drop the `.source.feature.group` IUs, list `platform + pde` instead. Not the deep blocker.

### What IS the blocker

A coordinated upstream change in **either** of:

#### Path 1 — Eclipse PDE drops `jdt.junit5.runtime` from `pde.feature.group`

The `pde.feature.group` aggregator in the simrel includes both `jdt.junit5.runtime` and `jdt.junit6.runtime`. Both are needed for the IDE workflows ("Run As JUnit Test"), but for headless Tycho test execution we don't need either — Tycho's own runner does the launching. If Eclipse PDE separated these into optional features, or if there were a `pde.feature.group.junit6-only` variant, we'd be unblocked.

No public roadmap signal that Eclipse plans to do this — both bundles ship side-by-side as separate p2 IUs and the feature includes both. See `eclipse-jdt/eclipse.jdt.ui#2654` for related discussion of the JUnit 5/6 coexistence problem in the IDE.

#### Path 2 — Tycho `<filters>` reliably remove `jdt.junit5.runtime`

The `target-platform-configuration` plugin supports `<filters>`. In the original investigation (see `investigation/junit-test-baseline.md`, runs 30–32 / run-A-filters / run-A-exact / run-C-exact), filters did fire but produced `[WARNING] De-selecting bundles in a target definition file is not supported / Removed all units` and downstream resolution then failed because some other dependent of the filtered IU couldn't resolve. Filters in Tycho 5.0.2 are unreliable for this pattern.

A future Tycho with cleaner filter semantics (or a `<exclude>` mechanism instead of `<filter>`) would unblock without needing Eclipse upstream changes.

#### Path 3 — bypass PDE entirely

We could replace `pde.feature.group` in our target with a curated list of just the PDE bundles we actually use, omitting both `jdt.junit5.runtime` and `jdt.junit6.runtime`. The Tycho-Surefire JUnit 6 fragment supplies its own launcher — we don't need PDE's bridges for Tycho test runs.

This is the most promising near-term path. Sketch:

```xml
<location includeAllPlatforms="false" includeMode="planner" includeSource="true" type="InstallableUnit">
  <unit id="org.eclipse.platform.feature.group" version="0.0.0"/>
  <!-- Curated PDE bits, no junit5.runtime: -->
  <unit id="org.eclipse.pde.core" version="0.0.0"/>
  <unit id="org.eclipse.pde.ui" version="0.0.0"/>
  <unit id="org.eclipse.pde.junit.runtime" version="0.0.0"/>  <!-- still needed by tests -->
  <!-- ...etc., enumerating just what DDK actually uses -->
  <repository location="https://download.eclipse.org/releases/2026-03/"/>
</location>
```

The cost is enumerating PDE bundles by name (~20 bundles to pick), and re-validating the list every simrel bump. It also requires verifying that `pde.junit.runtime` itself doesn't transitively require `jdt.junit5.runtime`. Worth a future attempt.

### What about SWTBot?

SWTBot 4.3.0 (released June 11, 2025) was a candidate for unblocking the JUnit 6 path. Findings after inspecting `https://download.eclipse.org/technology/swtbot/releases/4.3.0/features/` and the corresponding bundle manifests:

- The features directory lists `org.eclipse.swtbot.junit5_4.3.0.202506021445.jar` — there's a `junit5` feature, **no `junit6` feature**.
- The `swtbot.junit5.feature.group` `feature.xml` requires `<import plugin="org.junit"/>` — the JUnit 4 bundle. We just dropped JUnit 4 from the target in step 3. Re-adding it for swtbot would regress that.
- The constituent plugins are more flexible:
  - `org.eclipse.swtbot.junit5_x` does `Import-Package: org.junit.jupiter.api.extension` (no version range) — this means it can in principle bind to either JUnit 5 or JUnit 6's `org.junit.jupiter.api.extension` package, depending on what's in the runtime. Theoretically OK with JUnit 6 only.
  - `org.eclipse.swtbot.eclipse.junit5.headless` does `Import-Package: org.junit.platform.commons.util,org.junit.platform.engine,org.junit.platform.engine.discovery,org.junit.platform.engine.reporting,org.junit.platform.engine.support.descriptor,org.junit.platform.launcher,org.junit.platform.launcher.core,org.junit` — also unversioned. Theoretically OK except for the `org.junit` (JUnit 4) requirement.

**So SWTBot's `junit5_x` and `eclipse.junit5.headless` plugins are mostly version-agnostic** — they'd resolve against JUnit 6 packages too. The only hard JUnit 4 requirement is via `org.junit` (the JUnit 4 bundle) referenced in the headless launcher, and via the feature.xml's `<import plugin="org.junit"/>`.

**Open experiment**: include the SWTBot `swtbot.junit5_x` and `swtbot.eclipse.junit5.headless` plugins **directly** as `<unit>` IUs (not via `swtbot.junit5.feature.group`), avoiding the feature's `org.junit` requirement, and see if they resolve against JUnit 6 in the runtime. If yes, we have a path that doesn't need a swtbot release update.

DDK doesn't actually use swtbot's JUnit 5 extension class (`SWTBotJunit5Extension`) — DDK has its own Jupiter extensions. We may not need any swtbot.junit5* contribution at all.

## Files we tried to change

(Reverted in this branch — the doc is the deliverable, not the code changes.)

- `ddk-target/ddk.target`: simrel bump, JUnit IU bumps, dep version bumps
- `com.avaloq.tools.ddk.xtext.test/pom.xml`: `<providerHint>junit5</providerHint>` → `<providerHint>junit6</providerHint>`, plus iterations C and D dependency overrides

## Suggested next steps when resuming

In rough order of likely-to-work + low-effort:

1. **Drop `swtbot.junit5.feature.group` from `ddk.target` entirely**. Verify locally that DDK builds without it (it might already be unused — the `SWTBotJunit5Extension` class is the only thing the feature provides on top of `swtbot.swt.finder`, and DDK uses its own extensions). If DDK builds without swtbot's JUnit 5 wrapper, we eliminate one JUnit 5.14 contributor.
2. **Filter `org.eclipse.jdt.junit5.runtime`** out of the target via Tycho `<filters>`. Even though earlier filter attempts were unreliable, this specific filter targets a single bundle, not a whole IU family — may behave better. If a downstream IU complains, find what depends on it (probably `pde.feature.group`) and consider Path 3 (replace PDE feature with curated bundles).
3. **Curated PDE bundle list** (Path 3 above). The most invasive but most reliable path. Requires care to re-add bundles when `pde.feature.group` evolves.
4. **Watch upstream**: track Tycho 5.0.3+ releases, Eclipse 4.40 (2026-06), and SWTBot 4.4 for any change to the JUnit 5/6 coexistence picture.

## Resolution (2026-05-01)

After re-opening the investigation, the curated-PDE-bundle approach was tested and a deeper upstream blocker surfaced. End state:

### What was actually wrong

Three layers of dual-version pollution had to be peeled back:

1. **Eclipse PDE umbrella feature** — `org.eclipse.pde.feature.group` transitively pulls *both* `org.eclipse.jdt.junit5.runtime` (strict-pinned to JUnit 5.14.x) and `org.eclipse.jdt.junit6.runtime`, dragging two JUnit families into the OSGi runtime. **Workaround**: replace `pde.feature.group` with a curated list of just the bundles DDK uses (`pde.core`, `pde.ui`, `pde.ua.core`, `pde.junit.runtime`, `jdt.junit.runtime`, `jdt.annotation`).
2. **orbit-aggregation 4.39.0 dual-shipping** — orbit ships JUnit at both 5.14.x and 6.0.x as parallel IUs. We initially added Tycho `<filters>` restricting JUnit IUs to `[6.0.0,7.0.0)` as belt-and-suspenders. A follow-up experiment on `experiment/junit6-minimal-target` showed those filters were redundant: with the curated PDE bundle list in place, nothing in scope drags JUnit 5.x in, so the planner picks only the 6.0.3 IUs we explicitly request. The filters were **removed** from this PR. Iteration matrix in the experiment branch:
   - **(curated bundles, with filters)** — green. Initial PR #1327 state.
   - **(`pde.feature.group`, with filters)** — fails: `Missing requirement: org.eclipse.jdt.junit5.runtime requires junit-jupiter-api [5.14.0,6.0.0)` (filters block what `pde.feature.group`'s mandatory `jdt.junit5.runtime` needs). Curated list IS load-bearing.
   - **(curated bundles, no filters)** — green. JUnit 6.0.3 only in OSGi runtime. Filters are NOT load-bearing when curated list is in place.
3. **The real upstream blocker — Xtext 2.42.0** — `org.eclipse.xtext.testing` 2.42.0 declares `Import-Package: org.junit.jupiter.api [5.1.0,6.0.0)`, hard-excluding JUnit 6. This was masked in iterations B–G because the JUnit 5.14.x family was being pulled in to satisfy this exact import. Once filters made 5.x unavailable, target resolution surfaced the constraint clearly:

   ```
   Missing requirement: org.eclipse.xtext.testing 2.42.0.v20260223-0608
     requires 'java.package; org.junit.jupiter.api [5.1.0,6.0.0)' but it could not be found
   ```

### Fix

`eclipse-xtext/xtext#3660` "Junit 6 support" was completed and merged for **Xtext 2.43.0**, scheduled as a stable release on **2026-05-25**. The 2.43.0.M2 milestone is available at `https://download.eclipse.org/modeling/tmf/xtext/updates/milestones/2.43.0.M2/` and lifts the JUnit 5 pin.

### Verified working configuration (2026-05-01, against Xtext 2.43.0.M2)

`mvn clean verify` produces `tests=357 failures=0 errors=0 skipped=2`, BUILD SUCCESS, with the OSGi runtime containing **only** JUnit 6.0.3 bundles (verified via `config.ini` inspection):

```
junit-jupiter-api-6.0.3.jar
junit-jupiter-engine-6.0.3.jar
junit-jupiter-params-6.0.3.jar
junit-platform-commons-6.0.3.jar
junit-platform-engine-6.0.3.jar
junit-platform-launcher-6.0.3.jar
junit-platform-suite-api-6.0.3.jar
junit-platform-suite-engine-6.0.3.jar
```

No 5.14.x, no 1.14.x, no `jdt.junit5.runtime`, no `jdt.junit6.runtime`, no `vintage-engine`.

The combination of changes that produced this state (kept in this PR's draft):
- `ddk-target/ddk.target`: simrel `releases/2026-03/`, curated PDE bundle list, dropped `swtbot.junit5.feature.group`, orbit-aggregation `4.39.0`, JUnit IUs at `6.0.3`, Xtext milestone `2.43.0.M2`, dep versions back to 4.39-era.
- `com.avaloq.tools.ddk.xtext.test/pom.xml`: `<providerHint>junit6</providerHint>`, replaced `<id>org.eclipse.pde</id>` `eclipse-feature` requirement with `<id>org.eclipse.pde.junit.runtime</id>` `eclipse-plugin` requirement. (No `<filters>` — the curated bundle list does the JUnit-version isolation.)

### Why we use a milestone in this branch

The standing project rule is *stable releases only* (no snapshots / milestones / RCs). That rule still holds. The exception here is narrowly justified: blocker fully understood, fix confirmed in Xtext 2.43, stable release date on the horizon (2026-05-25, ~24 days from now), this PR is draft and won't merge before the stable lands. Once 2.43.0 stable ships, switching the URL is a one-line change (`updates/milestones/2.43.0.M2/` → `updates/releases/2.43.0/`); rerun verify; promote PR out of draft.

## Reference data

- Tycho 5.0.2 release notes confirm "Add JUnit 6 provider support" feature: `https://github.com/eclipse-tycho/tycho/blob/tycho-5.0.x/RELEASE_NOTES.md`
- The `org.eclipse.tycho.surefire.junit6-5.0.2` OSGi fragment in the local Maven repo (`~/.m2/repository/org/eclipse/tycho/org.eclipse.tycho.surefire.junit6/5.0.2/org.eclipse.tycho.surefire.junit6-5.0.2.jar`) imports `org.junit.platform.launcher,org.junit.jupiter.api,org.junit.jupiter.engine,org.junit.platform.suite.api,org.junit.platform.suite.engine` and is a `Fragment-Host: org.eclipse.tycho.surefire.osgibooter`. This is what `<providerHint>junit6</providerHint>` resolves to.
- JUnit 6.0.3 build-support docs say "Maven Surefire Plugin version 3.5.4 is recommended for use with Jupiter 6.0.3" and recommend explicit `junit-platform-launcher` declaration: `https://docs.junit.org/6.0.3/running-tests/build-support.html`
- `apache/maven-surefire#3216` "Compatibility with Jupiter 6.0.0+" was closed *not planned* (`https://github.com/apache/maven-surefire/issues/3216`) — the user's specific approach didn't work, but the BOM-based workaround does.
- `eclipse-jdt/eclipse.jdt.ui#2654` discusses the JUnit 5/6 coexistence problem on the IDE side.
