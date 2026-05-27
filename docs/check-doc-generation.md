# Generating Check catalog documentation

Generate browsable HTML docs for your Check catalogs by running a headless application against your bundle's `.check` sources. The application emits:

- `docs/index.html` — landing page listing every catalog (self-contained, opens in any browser).
- `docs/content/<Catalog>.html` — one styled page per catalog with severity badges and deep-link anchors.
- `docs/toc.xml`, `docs/contexts.xml` — Eclipse Help integration artifacts (skip if you only need the browser pages).

The styling is inline; no external CSS, JS or images. Dark mode follows the OS preference.

## Prerequisites

A Tycho-based project with a target platform file. Plain-Maven projects are not supported — the generator runs inside an Equinox runtime started by `tycho-eclipse-plugin:eclipse-run`.

## 1. Add dsl-devkit to your target platform

In your `.target` file, add the dsl-devkit p2 update site as a location and include `com.avaloq.tools.ddk.check.core`:

```xml
<location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="false" type="InstallableUnit">
  <repository location="https://dsldevkit.github.io/dsl-devkit/p2/releases/latest/"/>
  <unit id="com.avaloq.tools.ddk.check.core" version="0.0.0"/>
</location>
```

For snapshots, use `https://dsldevkit.github.io/dsl-devkit/p2/snapshots/latest/` instead. To pin to a specific version, use `p2/releases/<version>/` or `p2/snapshots/<sha>/`.

## 2. Add the profile to the consumer pom

In the bundle whose `.check` sources you want documented:

```xml
<profiles>
  <profile>
    <id>generateCheckDocs</id>
    <build>
      <plugins>
        <plugin>
          <groupId>org.eclipse.tycho</groupId>
          <artifactId>tycho-eclipse-plugin</artifactId>
          <executions>
            <execution>
              <id>generate-check-docs</id>
              <phase>generate-resources</phase>
              <goals><goal>eclipse-run</goal></goals>
              <configuration>
                <executionEnvironment>JavaSE-21</executionEnvironment>
                <applicationsArgs>
                  <args>-application</args>
                  <args>com.avaloq.tools.ddk.check.core.docApplication</args>
                  <args>${project.basedir}/src</args>
                  <args>${project.basedir}/docs</args>
                </applicationsArgs>
                <dependencies>
                  <dependency>
                    <artifactId>com.avaloq.tools.ddk.check.core</artifactId>
                    <type>eclipse-plugin</type>
                  </dependency>
                </dependencies>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </build>
  </profile>
</profiles>
```

The application takes two args: `<sourceDir>` (walked recursively for `*.check` files) and `<docsDir>` (written into).

No `<repositories>` block is needed — `eclipse-run` resolves against your project's target platform.

## 3. Invoke

```bash
mvn -PgenerateCheckDocs -DskipTests package
```

Then open `docs/index.html` in any browser.

## Troubleshooting

- **"Cannot resolve unit com.avaloq.tools.ddk.check.core"** — the dsl-devkit p2 site is not in your target platform. Re-resolve the target after adding it.
- **"No catalogs found"** — `<sourceDir>` does not contain any `.check` files. The application walks the directory recursively; check the path you passed as the first `<args>`.
- **"Application com.avaloq.tools.ddk.check.core.docApplication could not be found"** — the bundle is missing from the `<dependencies>` list of the `eclipse-run` execution (it is *not* enough to have it on the target platform; `eclipse-run` only installs what you list).
