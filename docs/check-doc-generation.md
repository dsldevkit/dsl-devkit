# Generating Check catalog documentation

Generate browsable HTML docs for your Check catalogs by running a headless application against your bundle's `.check` sources. The application emits:

- `docs/index.html` — landing page listing every catalog (self-contained, opens in any browser).
- `docs/content/<Catalog>.html` — one styled page per catalog with severity badges and deep-link anchors.
- `docs/toc.xml`, `docs/contexts.xml` — Eclipse Help integration artifacts (skip if you only need the browser pages).

The styling is inline; no external CSS, JS or images. Dark mode follows the OS preference.

## Regenerating these docs inside dsl-devkit

If you are working in this repository and want to regenerate the committed snapshot under
`com.avaloq.tools.ddk.check.test.runtime/docs/`, run this single command from the reactor root:

```bash
mvn -pl :com.avaloq.tools.ddk.check.test.runtime,:ddk-repository -am -PgenerateCheckDocs -DskipTests clean package
```

`check.core` is not published to an external p2 site during a local build, so the in-repo profile
resolves it from the **reactor** p2 site `ddk-repository/target/repository/`. That is why the command
also builds `:ddk-repository` (the `-am` / explicit module ensures it is built first) and why the
profile lists it first among its `eclipse-run` repositories. The steps below describe the flow for
**external** projects documenting their *own* catalogs, where `check.core` comes from the published p2
site instead.

Regenerating must leave the committed files unchanged. `CheckDocGenerationTest` (in
`com.avaloq.tools.ddk.check.core.test`) runs the application over copies of the two catalogs and compares
the result byte for byte with copies of the committed files, so generator drift fails the build. The
`toc.xml` and `contexts.xml` copies are the IDE builder's output, so both paths produce the same files.

## Prerequisites

A Tycho-based project with a target platform file. Plain-Maven projects are not supported — the generator runs inside an Equinox runtime started by `tycho-eclipse-plugin:eclipse-run`.

## 1. Add the profile to the consumer pom

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
                <repositories>
                  <repository>
                    <id>ddk</id>
                    <layout>p2</layout>
                    <url>https://dsldevkit.github.io/dsl-devkit/p2/releases/latest/</url>
                  </repository>
                  <repository>
                    <id>eclipse-release</id>
                    <layout>p2</layout>
                    <url>https://download.eclipse.org/releases/2026-06/</url>
                  </repository>
                  <repository>
                    <id>eclipse-emf</id>
                    <layout>p2</layout>
                    <url>https://download.eclipse.org/modeling/emf/emf/builds/release/2.39.0/</url>
                  </repository>
                  <repository>
                    <id>eclipse-xtext</id>
                    <layout>p2</layout>
                    <url>https://download.eclipse.org/modeling/tmf/xtext/updates/releases/2.43.0/</url>
                  </repository>
                  <repository>
                    <id>eclipse-mwe</id>
                    <layout>p2</layout>
                    <url>https://download.eclipse.org/modeling/emft/mwe/updates/releases/2.25.0/</url>
                  </repository>
                  <repository>
                    <id>eclipse-orbit</id>
                    <layout>p2</layout>
                    <url>https://download.eclipse.org/tools/orbit/simrel/orbit-aggregation/release/4.40.0</url>
                  </repository>
                </repositories>
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

The application takes two args: `<sourceDir>` (walked recursively for `*.check` files, skipping `target/` and
dot-prefixed directories) and `<docsDir>` (written into). It documents every catalog it finds, regardless of
the project's Check generator preferences, and deletes pages in `docs/content/` whose catalog no longer
exists. It fails the build without writing anything if a catalog has syntax errors, two catalogs in different packages
share a name (their pages would overwrite each other), or none is found.

`eclipse-run` installs only what its `<repositories>` provide; it does *not* read your build's target
platform. The DDK p2 site carries only the DDK bundles, so the Eclipse, EMF, Xtext, MWE and Orbit sites
are required too. Use the versions that match your DDK release: the `check.docgen.p2.*` properties in
`ddk-parent/pom.xml` at that release's tag list them. For snapshots, replace the DDK URL with
`https://dsldevkit.github.io/dsl-devkit/p2/snapshots/latest/`; to pin a version, use
`p2/releases/<version>/` or `p2/snapshots/<sha>/`.

## 2. Invoke

```bash
mvn -PgenerateCheckDocs -DskipTests package
```

Then open `docs/index.html` in any browser.

## 3. Register the Eclipse Help files (optional)

Skip this if you only need the browser pages. Otherwise, register `toc.xml` and `contexts.xml` in the
bundle's `plugin.xml`:

```xml
<extension point="org.eclipse.help.contexts">
  <contexts file="docs/contexts.xml"/>
</extension>
<extension point="org.eclipse.help.toc">
  <toc file="docs/toc.xml" primary="false"/>
</extension>
```

Add `docs/` to `bin.includes` in `build.properties`, and install `com.avaloq.tools.ddk.check.runtime.ui`
with your product: its table of contents provides the `checkdocumentation` anchor that `toc.xml` links to.

## Troubleshooting

- **"Cannot resolve ..." from `eclipse-run`** — one of the p2 sites above is missing from the execution's `<repositories>`, or its version does not match your DDK release.
- **"No catalogs found under ..."** — `<sourceDir>` contains no `.check` files outside `target/` and dot-prefixed directories. The application walks the directory recursively and fails the build in this case; check the path you passed as the first `<args>`.
- **"Application com.avaloq.tools.ddk.check.core.docApplication could not be found"** — the bundle is missing from the `<dependencies>` list of the `eclipse-run` execution (it is *not* enough to have it on the target platform; `eclipse-run` only installs what you list).
