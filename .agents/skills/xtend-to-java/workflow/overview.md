# Conversion workflow overview

This is the full end-to-end workflow for migrating Xtend files to Java in dsl-devkit.

## Step 0 — Establish scope

### Listing migration candidates

To get a deterministic overview of remaining Xtend work, run one of these commands from the repository root.

On Windows (PowerShell):

```powershell
Get-ChildItem -Recurse -Filter "*.xtend" -Path "." |
  Where-Object { $_.FullName -match "\\src\\" -and $_.FullName -notmatch "\\bin\\" } |
  Group-Object { ($_.FullName -split '\\src\\')[0] | Split-Path -Leaf } |
  ForEach-Object {
    $totalLines = ($_.Group | ForEach-Object { (Get-Content $_.FullName | Measure-Object -Line).Lines } | Measure-Object -Sum).Sum
    $type = if ($_.Name -match '\.test$') { 'test' } else { 'prod' }
    [PSCustomObject]@{ Module = $_.Name; Type = $type; Files = $_.Count; Lines = $totalLines }
  } |
  Sort-Object Type, Lines |
  Format-Table -AutoSize
```

On macOS / Linux (bash):

```bash
find . -name "*.xtend" -path "*/src/*" -not -path "*/bin/*" \
  | awk -F'/src/' '{print $1}' \
  | sort | uniq -c | sort -n
```

Both list per-module Xtend file counts so you can plan slice scope.

### Scoping the session

Before touching any files, establish:

1. **What files or modules are in scope for this session?**
   Collect the list of `.xtend` source files to migrate.

2. **What should the branch be called?**
   Convention: `migrate/xtend-to-java/<short-name>` (e.g., `migrate/xtend-to-java/check-core-test`).

3. **Is there an existing migration branch with pre-converted files?**
   If yes, you can pull already-converted `.java` files from it (Step 2 Option A).

Do not proceed until you have a concrete file list and a branch name.

---

## Step 1 — Cut the slice branch

Always branch from **master** (or the project's main integration branch):

```bash
SLICE=migrate/xtend-to-java/<slice-name>
git fetch origin
git checkout -b "$SLICE" origin/master
```

For stacked multi-slice migrations, suffix the branch name with `-step-N`
(e.g. `migrate/xtend-to-java/check-core-step-1`, `...-step-2`) per the
project's stacked-PR convention.

---

## Step 2 — Obtain the converted Java files

### Option A — Pull converted files from a pre-converted branch

```bash
git checkout <migration-branch> -- <path1> <path2> ...
```

The converted files live at the same package path under `src/` with `.java` replacing `.xtend`.

### Option B — Convert from scratch

For each `.xtend` file, produce the Java equivalent by following Steps 3 and 4 below **in strict order**.
Do not write the Java file first and then vet it — read the references first, then write.

---

## Step 3 — Read BOTH references BEFORE writing any Java

> **HARD RULE — NO EXCEPTIONS, NO SHORTCUTS**
>
> You MUST read **both** the `.xtend` source AND the `xtend-gen/` Java output **in full**
> before writing a single line of migrated Java. This applies to **every file, every time** —
> even a 5-line utility class, even a file that "looks trivial", even if you think you
> already know what it does.
>
> **Why this is non-negotiable:** A 21-line Xtend file with a single template expression
> can produce completely different string output than what you'd guess from reading the
> Xtend source alone. Xtend's template whitespace rules are counter-intuitive — indentation
> is stripped, leading newlines are removed, and the only way to know the actual output is
> to read what the Xtend compiler produced in `xtend-gen/`.
>
> **There is no file small enough to skip this step.**
>
> **Always build for a CURRENT ground truth — do not rely on a stale reference alone.**
> `xtend-gen/` is gitignored and only exists after a build, so a fresh checkout has none.
> For **every** migration, build the module off the **latest integration branch** with `-T 3C`
> first to (re)generate `xtend-gen/`:
>
> ```bash
> mvn -f ./ddk-parent/pom.xml -pl :<module> -am -DskipTests -T 3C compile --batch-mode
> ```
>
> The freshly built `xtend-gen/` is the **authoritative** ground truth: it is the Xtend compiler's
> output for the `.xtend` *as it exists on the current branch*, so it is always up to date and
> Java-authoritative. This compile is also the first build gate.
>
> A pre-converted **reference branch's `.java`** (if one exists) is a valuable **four-eyes
> cross-check** — read it too and reconcile every divergence (either side may be wrong) — but it
> may be **stale** relative to the current branch, so it is NOT a substitute for the freshly built
> `xtend-gen/`. Only if a build is genuinely impossible does the reference `.java` become the
> fallback ground truth, and then note the staleness risk.

### 3a. Read the Xtend source — understand intent and structure

Read the original `.xtend` file **in full**. It tells you:
- The author's intent
- Which methods are dispatch methods
- Where extension methods, operator overloading, or `?.`/`?:` were used
- What the logical structure is

### 3b. Read the `xtend-gen/` output — verify exact behavior

Find the corresponding generated file under `xtend-gen/` (same package, same class name, `.java`).
**Read in full — no exceptions.** It tells you:
- The exact string output of every template expression (critical)
- Exactly which Java methods were generated for each dispatch variant
- The precise exception handling Xtend generated
- Which implicit null checks, type casts, and conversions Xtend inserted

### 3b-also. Read `src-gen/` for any generated supertype

`xtend-gen/` is the Xtend *compiler* output for the file's own logic. It is **not** the same as
`src-gen/`, the MWE2/Xtext *generator* output that holds the `Abstract<Lang><Layer>Module`,
`Abstract…Setup`, runtime/UI module bases, etc. that the migrated class `extends`/`override`s.

If the class extends or overrides a generated type, read that supertype under `src-gen/` (same
module). Unlike `xtend-gen/`, **`src-gen/` is committed**, so it is present without a build. It is
the authoritative source for:
- inherited **constructor signatures** (an `@FinalFieldsConstructor` must call the right `super(...)`)
- which methods are genuinely `@Override`s (vs new methods) and their **exact return types** — Xtend
  often infers a return type from the override target that you must restate explicitly in Java
- the binding methods a `*Module` is expected to provide

### 3c. Template whitespace — verify against `xtend-gen/`

Xtend's template whitespace rules:

1. **Indentation relative to a control structure is stripped.** If a template starts with `'''` followed
   by a newline, indentation on subsequent lines relative to the opening mark is removed.

2. **Lines containing only control structures produce no output.**

3. **Newlines in appended strings get the current indentation prepended.**

**The only reliable way to know what a template produces is to read the `xtend-gen/` output.**

### 3d. Only NOW write the Java

After reading both references, write Java that:
1. **Matches the `xtend-gen/` behavior exactly** for all string outputs, method signatures, and control flow
2. **Uses idiomatic Java** (text blocks, `.formatted()`, concatenation) instead of `StringConcatenation`
3. **Preserves the original class/member Javadoc exactly** — never invent. (The file copyright header is the exception: always normalise it to the Avaloq banner per [`formatting-and-commit.md`](./formatting-and-commit.md) — replacing any generated-stub or Javadoc-style header — which is not "inventing".)
4. **Follows the quality checklist** in [`workflow/validation-checklist.md`](./validation-checklist.md)

---

## Step 4 — Apply the quality checklist

See [`workflow/validation-checklist.md`](./validation-checklist.md) — all 30 rules must pass.

---

## Step 5 — Build and verify

Module-specific build first:
```bash
mvn -pl <module1>,<module2> -am verify -f ./ddk-parent/pom.xml > mvn-output.txt 2>&1
```

**PMD needs compiled classes** for type-resolution rules (`MissingOverride`, `UnnecessaryCast`,
`LooseCoupling`, `UseCollectionIsEmpty`). Always compile first:
```bash
mvn clean compile pmd:check -f ./ddk-parent/pom.xml -pl <modules> -am > mvn-output.txt 2>&1
```

Checkstyle works on source only:
```bash
mvn checkstyle:check -f ./ddk-parent/pom.xml -pl <modules> > mvn-output.txt 2>&1
```

Full CI-equivalent:
```bash
mvn clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check -f ./ddk-parent/pom.xml --batch-mode --fail-at-end > mvn-output.txt 2>&1
```

Always check the final `BUILD SUCCESS/FAILURE` line. With `--fail-at-end`, intermediate lines can
show `0 violations` while the build is still failing downstream.

---

## Step 6 — Update module infrastructure

When Xtend is fully removed from a module, update the module infrastructure.
See [`workflow/infrastructure-cleanup.md`](./infrastructure-cleanup.md).

---

## Step 7 — Format, commit, and push

See [`workflow/formatting-and-commit.md`](./formatting-and-commit.md).
