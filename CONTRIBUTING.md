# How to contribute

If you are new to GitHub, please read [About pull requests](https://help.github.com/articles/about-pull-requests/).

## Legal requirements

By providing a pull request 
* you agree that your contribution will be provided under [Eclipse Public License v1.0](http://www.eclipse.org/legal/epl-v10.html),
* you confirm that you authored the whole content of the change and you have the right to contribute it,
* and each contribution you submit complies with the commitments documented in the [Developer Certificate of Origin](https://developercertificate.org/),
* you agree that additional licenses may be added to this open source project including your contributions.

You will promptly notify project maintainers if you become aware of any facts or circumstances that would make the above commitments inaccurate in any way. 

## Security scanning

Every pull request is checked for dependencies with known vulnerabilities (the `cve-scan` workflow). To run the same check locally:

```
bash .github/scripts/check-cves.sh
```

It needs `jq`, `curl` and network access to the [OSV.dev](https://osv.dev) API (one small batched query), and takes well under a minute with a warm Maven repository. Pass `--skip-sbom` to re-scan without regenerating the SBOM.

When the check fails:

* prefer fixing the dependency, usually by updating `ddk-target/ddk.target`,
* if the advisory demonstrably does not affect shipped DDK artifacts (e.g. it is confined to test bundles), add an entry with a justification and a review date to `.github/security/cve-ignores.json`.

A failure reading `SCAN SELF-TEST FAILED` means the scan itself is broken or the OSV API is unreachable — the check fails loudly rather than passing silently. Re-run it; do not bypass it.

A second, non-blocking audit (the `cve-deep-scan` workflow) runs weekly with OWASP dependency-check, covering Eclipse-native bundles that have no Maven identity. Its reports land as workflow artifacts, failures open a `[cve-deep-scan] findings` issue, and its suppression rules live in `.github/security/dependency-check-suppressions.xml` (same policy: every rule needs a justification). No API key is needed: the vulnerability database is built from the dependency-check project's nightly NVD mirror in about a minute. Run it locally with `bash .github/scripts/check-cves-deep.sh` (or `--no-update` for fast iteration against an existing database).

## Guidelines for Pull Requests

* Provide a good pull request description
* With the exception of small cleanups and tiny fixes there must be an issue associated with the pull request
* Java code must be automatically formatted with the Eclipse auto format using the supplied rules
* Each pull request must be reviewed by at least one code reviewers who is not the author of the pull request

### Code reviewers

[@mrubanov](https://github.com/mrubanov), [@abrahamm87](https://github.com/abrahamm87), [@gghezzi](https://github.com/gghezzi), [@rmitin](https://github.com/rmitin), [@gregdyke](https://github.com/gregdyke), [@andrewL-avlq](https://github.com/andrewL-avlq), [@rubenporras](https://github.com/rubenporras).
