---
name: xtend-to-java
description: >
  Migrates Xtend (.xtend) source files to idiomatic Java 21 using a repeatable slice-based process.
  Use this skill whenever the user wants to convert Xtend files to Java, port a group of Xtend files,
  work through the xtend-to-java migration checklist, clean up PMD/Checkstyle violations in migrated files,
  or verify that a migrated Java file faithfully preserves the behavior of its Xtend original.
  Also triggers for phrases like "convert this xtend", "migrate these files", "port to java", or "xtend migration".
globs:
  - "**/*.xtend"
  - "**/*.java"
---

# Xtend → Java Migration Skill

> For general coding standards (copyright headers, Javadoc, import rules, naming), see `AGENTS.md` at the repository root.

## Overview

This skill drives a slice-based Xtend-to-Java migration. A *slice* is a user-defined batch of files
migrated together, verified locally, and merged independently. The ground truth for behavior is always
the `xtend-gen/` output — what Xtend compiled to Java before the migration.

## Hard Rules — Non-Negotiable

> **Hard rule — read BOTH `.xtend` source AND a behavioural ground truth in full before writing ANY Java.**
> No exceptions regardless of file size. A 5-line file with one template expression can have
> surprising whitespace behavior. See [`workflow/overview.md`](./workflow/overview.md) for the full rationale.
>
> The ground truth is the `xtend-gen/` compiled output. **`xtend-gen/` is gitignored and only exists after a build** — if it is absent you must still obtain a ground truth before writing Java: either (a) build the module to generate `xtend-gen/`, or (b) if a pre-converted reference branch exists, read its `.java` (it was produced from `xtend-gen/` and encodes the same behavior). **Never write Java from the `.xtend` source alone.**
>
> Separately, if the class extends/overrides a **generated supertype** (`Abstract…Module`, `…Setup`, runtime/UI bases), read that supertype under `src-gen/` (committed, present without a build) — it is the authoritative source for inherited constructor signatures, real `@Override` targets, and Xtend-inferred return types. See [`workflow/overview.md`](./workflow/overview.md) §3b-also.

> **Template whitespace is the #1 source of migration bugs.**
> Xtend strips indentation relative to control structures. The only reliable way to know what
> a template produces is to read `xtend-gen/`. Never guess from Xtend source — always verify.

## When to invoke

Use this skill when:

- A user asks to convert an `.xtend` file (or several) to `.java`.
- A user asks to migrate a module off Xtend.
- You are touching an `.xtend` file with the intent to replace it (not just edit it).

Do **not** invoke for normal Xtend editing where the file is staying in Xtend.

## Decision tree

1. **One file or several?**
   - One file → [`workflow/one-file-conversion.md`](./workflow/one-file-conversion.md).
   - Several files → [`workflow/multi-file-batch.md`](./workflow/multi-file-batch.md) for batching strategy.
2. **Read the full workflow** in [`workflow/overview.md`](./workflow/overview.md) — Steps 0–7.
3. **Internalise the binding decisions** in [`rules/00-decisions.md`](./rules/00-decisions.md).
4. **Walk the rules** for the constructs that actually appear in your source file:
   - [`rules/01-imports-and-package.md`](./rules/01-imports-and-package.md)
   - [`rules/02-variables.md`](./rules/02-variables.md)
   - [`rules/03-methods.md`](./rules/03-methods.md)
   - [`rules/04-templates.md`](./rules/04-templates.md)
   - [`rules/05-control-flow.md`](./rules/05-control-flow.md)
   - [`rules/06-extension-methods.md`](./rules/06-extension-methods.md)
   - [`rules/07-lambdas.md`](./rules/07-lambdas.md)
   - [`rules/08-operator-overloads.md`](./rules/08-operator-overloads.md)
   - [`rules/09-misc-syntax.md`](./rules/09-misc-syntax.md)
5. **Look up Xtend stdlib calls** in [`references/xtend-library-replacements.md`](./references/xtend-library-replacements.md).
6. **Apply the quality checklist** in [`workflow/validation-checklist.md`](./workflow/validation-checklist.md) before declaring done.
7. **Handle module infrastructure** via [`workflow/infrastructure-cleanup.md`](./workflow/infrastructure-cleanup.md) when all Xtend is removed from a module.
8. **Format and commit** via [`workflow/formatting-and-commit.md`](./workflow/formatting-and-commit.md).
9. **Review known pitfalls** in [`workflow/known-pitfalls.md`](./workflow/known-pitfalls.md).

## Binding decisions cheat-sheet

Detail in [`rules/00-decisions.md`](./rules/00-decisions.md). Quick recall:

- Target **Java 21**.
- **String building**: literal → text block → `.formatted()` → `StringBuilder` (control flow only).
- **No `var` keyword.** Explicit types everywhere.
- **Explicit visibility** on classes, methods, fields.
- **Java stdlib by default** for collections and streams. Guava only where genuinely superior.
- **Never `String.format()`** — always `.formatted()`.
- **Parameterized Log4j2** — `{}` placeholders, never concatenation in loggers.

## Xtend → Java Syntax Reference

Use this table for quick mechanical transforms. Full details in the rule files.

### Declarations and types

| Xtend | Java |
|-------|------|
| `def methodName()` | `public ReturnType methodName()` (explicit access + return type) |
| `def private methodName()` | `private ReturnType methodName()` |
| `val x = expr` | `final ExplicitType x = expr;` (never `var`) |
| `var x = expr` | `ExplicitType x = expr;` |
| `typeof(MyClass)` | `MyClass.class` |
| `def dispatch method(Type1 x)` | Keep as `_method(Type1 x)` with `@SuppressWarnings` |
| `@Inject extension Foo _foo` (field) | `private Foo foo;` — strip `_` prefix; names must match `[a-z][a-zA-Z0-9]*` |
| Field `my_field` / `_my_field` | Rename to `myField` (camelCase, no underscores) |

### Operators and null handling

| Xtend | Java |
|-------|------|
| `obj?.method()` | `obj != null ? obj.method() : null` (or guard clause) |
| `x ?: default` | `x != null ? x : default` |
| `===` / `!==` (identity) | `==` / `!=` |
| `==` / `!=` (equality) | `.equals()` / `!Objects.equals(a, b)` |
| `a..b` (range) | `IntStream.rangeClosed(a, b)` |

### Lambdas and collections

| Xtend | Java |
|-------|------|
| `[param \| body]` | `(param) -> { return body; }` |
| `[body]` (implicit `it`) | `(it) -> { return body; }` — name the parameter explicitly |
| `list.filter[condition]` | `list.stream().filter(x -> condition).toList()` |
| `list.map[transform]` | `list.stream().map(x -> transform).toList()` |
| `list.forEach[action]` | `list.forEach(x -> action)` (or `for` loop) |
| `list.exists[condition]` | `list.stream().anyMatch(x -> condition)` |
| `list.forall[condition]` | `list.stream().allMatch(x -> condition)` |
| `list.findFirst[condition]` | `list.stream().filter(x -> condition).findFirst().orElse(null)` |
| `list.head` | `list.isEmpty() ? null : list.get(0)` |
| `list.tail` | `list.subList(1, list.size())` |
| `#[a, b, c]` (immutable list) | `List.of(a, b, c)` |
| `#{a, b, c}` (immutable set) | `Set.of(a, b, c)` |
| `newArrayList(...)` | `new ArrayList<>(List.of(...))` |
| `newHashMap(...)` | `new HashMap<>(Map.of(...))` |
| `list += element` | `list.add(element)` |
| `list += otherList` | `list.addAll(otherList)` |
| `list -= element` | `list.remove(element)` |

### Extension methods

| Xtend pattern | Java equivalent |
|---------------|-----------------|
| `obj.extensionMethod()` | `ExtensionClass.extensionMethod(obj)` or `helper.extensionMethod(obj)` |
| `@Inject extension MyHelper helper` | `@Inject private MyHelper helper;` then `helper.method(obj)` |
| `import static extension com.foo.Util.*` | `import com.foo.Util;` then `Util.method(obj)` |

### Property access

| Xtend | Java |
|-------|------|
| `obj.name` (property access) | `obj.getName()` |
| `obj.name = value` (property write) | `obj.setName(value)` |

### Template expressions

| Xtend | Java |
|-------|------|
| `'''static text'''` | `"static text"` or text block |
| `'''text «expr» more'''` | `"text %s more".formatted(expr)` or concatenation |
| `'''«IF cond»...«ENDIF»'''` | `StringBuilder` with explicit `if` |
| `'''«FOR item : list»...«ENDFOR»'''` | `StringBuilder` with `for` loop |

## Examples

Worked end-to-end examples:

- [`examples/00-basic-generator.md`](./examples/00-basic-generator.md)
- [`examples/01-template-with-for-loop.md`](./examples/01-template-with-for-loop.md)
