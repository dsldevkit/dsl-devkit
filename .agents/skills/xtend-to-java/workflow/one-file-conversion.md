# One-file conversion workflow

Use this when converting a single `.xtend` to its `.java` counterpart.

## Steps

1. **Read the Xtend source in full.** Note its package, imports, and the constructs it uses.
2. **Build the module off the latest integration branch (`-T 3C`) to (re)generate `xtend-gen/`, then read that output in full.** Same package, same class name, `.java` extension. The freshly built `xtend-gen/` is the authoritative, current ground truth (and first build gate). Mandatory — no exceptions. If a pre-converted reference branch exists, also read its `.java` as a four-eyes cross-check (may be stale; reconcile divergences). See [`workflow/overview.md`](./overview.md) Step 3.
3. **Identify which rule files apply.** Template-heavy? Extension-heavy? Simple POJO? Load only those.
4. **Apply the binding decisions.** [`rules/00-decisions.md`](../rules/00-decisions.md) — Java 21, 4-tier string building, no `var`, explicit visibility, Java stdlib.
5. **Apply rules in order.** Walk [`rules/01-...`](../rules/01-imports-and-package.md) through [`rules/09-...`](../rules/09-misc-syntax.md) for the constructs you identified.
6. **Write the `.java` file.** Match `xtend-gen/` behavior exactly while using idiomatic Java.
7. **Run the validation checklist.** [`workflow/validation-checklist.md`](./validation-checklist.md). Every item must pass.
8. **Commit as two steps** — a pure `git mv` rename commit, then an in-place translate commit; see [`formatting-and-commit.md`](./formatting-and-commit.md) §Commit structure. For a single file the `git mv` IS the removal; do not delete+re-add.
9. **Verify the file compiles:**
   ```bash
   mvn -pl :<module> -am -DskipTests compile -f ./ddk-parent/pom.xml > mvn-output.txt 2>&1
   ```
10. **Run quality checks:**
    ```bash
    mvn -pl :<module> -am checkstyle:check pmd:check -f ./ddk-parent/pom.xml > mvn-output.txt 2>&1
    ```

## Reference

A worked end-to-end example is at [`examples/00-basic-generator.md`](../examples/00-basic-generator.md).
