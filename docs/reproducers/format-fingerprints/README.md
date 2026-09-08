# Format dependency fingerprint reproducer

This fixture exercises the production `FormatResourceDescriptionStrategy` using
real parsed Format models. It is registered in `FormatTestSuite`, so the normal
DDK test aggregator runs it. No private project or installed Eclipse workspace is
required.

## Run

Use JDK 21 and Maven. A Git checkout is required by Tycho's version qualifier.
When using the downloadable source ZIP, extract it and initialize it first:

```sh
git init
git add .
git -c commit.gpgsign=false -c user.name=Reproducer -c user.email=reproducer@example.invalid commit -m "Initialize reproducer"
```

From the root of the checkout:

```sh
mvn integration-test -f ddk-parent/pom.xml --batch-mode --fail-at-end
```

The `integration-test` phase runs the aggregator without the later release-baseline
verification, which needs the original Git history. For a normal repository checkout,
use `verify` when validating a fix.

On Linux, prefix that command with `xvfb-run`. On macOS the Maven profile supplies
the required SWT main-thread option. All tests run in the existing
`com.avaloq.tools.ddk.xtext.test` aggregator; do not enable Surefire separately in
the Format test bundle.

The regression fixture is
`com.avaloq.tools.ddk.xtext.format.test/src/com/avaloq/tools/ddk/xtext/format/resource/FormatResourceDescriptionStrategyTest.java`.

It checks:

- byte-identical LF and CRLF reloads have identical exported descriptions;
- inherited changes reach a grandchild through an intermediate format with no
  local declarations;
- inserted comment lines and changed line endings invalidate inherited source
  references;
- formats without local declarations still expose configuration changes;
- cyclic inheritance cannot make fingerprint calculation recurse indefinitely.

The focused description tests deliberately connect parsed configurations directly
for inheritance cases, isolating fingerprint behavior from scope resolution.
They are not evidence of a complete Eclipse incremental-build lifecycle. The
separate workspace regression tests cover that lifecycle and compare incremental
Java/trace output to a full build.

## Expected failure on the original implementation

The original fingerprint contains `obj.eContainer().toString()`, including an EMF
object's Java identity. Identical reloads therefore export different fingerprint
names. Merely replacing identity with a URI is insufficient: inherited changes
must continue propagating through formats without local changes/declarations.

The original DDK 19.1 workspace experiment also showed that identical Format
rewrites reindexed both a base and its dependent and produced change events for
four unchanged generated Java/trace files. That experiment ran on macOS and
settled when idle. It did not reproduce the private Windows workspace's endless
build loop. This fix does not claim to solve every source of build churn.
