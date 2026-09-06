# Scope inference regression reproducer

This is a public, synthetic Eclipse plug-in test project for the Scope lifecycle regression introduced by [DDK PR #1405](https://github.com/dsldevkit/dsl-devkit/pull/1405), merged on 19 August 2026. It uses Ecore types and contains no downstream sources, workspace metadata, or machine-specific paths.

It reproduces the failure mechanism, not a multi-hour Windows build. The tests deliberately make a classifier or included Scope model temporarily unavailable, then exercise real Xtext resource loading and DDK generation. See [ROOT-CAUSE.md](ROOT-CAUSE.md) for the evidence and limits.

## Run against DDK 19.1

Prerequisites: JDK 21, Maven, Python 3.9 or newer, and network access to GitHub and the public Eclipse repositories. Use a fresh directory or workspace.

1. Extract the ZIP and open a terminal in this directory.
2. Run `python prepare.py` (or `python3 prepare.py` on macOS/Linux). This downloads the public DDK 19.1 release, verifies its SHA-256, and writes a local target definition. It does not clone a repository.
3. Run `mvn clean verify`.
4. Examine `target/surefire-reports`. Failures against 19.1 are expected; the tests express the corrected behavior.

If Python reports a certificate error, configure its trusted CA certificates. Do not disable certificate verification. Alternatively, download the [public release ZIP](https://github.com/dsldevkit/dsl-devkit/releases/download/v19.1.0/p2-update-site.zip), extract it, and run `python prepare.py --ddk-repository /path/to/extracted/repository`.

## Run against the fix

Build the fix checkout using `mvn verify -DskipTests -f ddk-parent/pom.xml`. Then, from this reproducer directory:

```text
python prepare.py --ddk-repository /path/to/fix-checkout/ddk-repository/target/repository
mvn clean verify
```

Replace the path with your local location; Windows paths can be quoted. The test project is unchanged between runs. The selected target is the only difference.

## Run in Eclipse

Import this directory using **Existing Projects into Workspace**. Run the preparation step first, open `reproducer.target`, and select **Set as Active Target Platform**. After resolution completes, run `Scope regression.launch` as a JUnit plug-in test. The launch uses a dedicated `.scope-reproducer-runtime` directory within the chosen workspace. Use a new workspace for this reproducer.

The Maven run is headless and needs no SWT startup option. For the interactive launch on macOS, add `-XstartOnFirstThread` to the JDK's default VM arguments, as usual for Eclipse PDE launches. Do not add that option on Windows or Linux.

## Correct behavior

- Reading resource roots preserves both provider types without compiling bodies against an unavailable model.
- Early member access does not permanently lose helpers: after the model becomes available, generation declares the expected helper method, with its historical name, and generates both provider files.
- An unresolved include later contributes its reference-scope helper and injected field.
- Generation while signatures remain unavailable fails explicitly and writes no Java; repairing the same resource permits generation.

The test checks actual inferred method declarations as well as generated text, so a dangling dispatch call alone cannot pass.

Local checks against the public 19.1 target produced four expected test failures; the same four tests passed against the fixed target. The four generated provider classes also compiled successfully with Java 21.

The fixture is designed for Windows, macOS, and Linux. Local validation is on macOS/JDK 21; Windows execution must still be confirmed on Ruben's machine. It does not benchmark antivirus or file-system performance and does not establish why a particular downstream model became unavailable.
