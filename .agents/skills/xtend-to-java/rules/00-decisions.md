# Binding decisions

These are non-negotiable project choices. Apply them to every conversion.

## Target: Java 21

Use the language features available: pattern matching for `instanceof`, switch expressions, text blocks, records.

## No `var` keyword — explicit types everywhere

Neither Xtend's `val`/`var` nor Java 10's `var`/`final var`. Every local variable, field, and parameter declares its type explicitly.

```java
// Good
final CheckCatalog catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class);
List<String> names = new ArrayList<>();
String result = computeResult();

// Bad — never
final var catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class);
var names = new ArrayList<>();
var result = computeResult();
```

## Explicit visibility on everything

Classes, methods, fields. Xtend defaults to `public` for classes and methods and to package-private for fields; Java conversion makes each one explicit.

## Java stdlib by default — Guava only where genuinely superior

Use Java standard library for collections and streams. Keep Guava only where it is genuinely more concise or has no clean Java equivalent:

| Use Java stdlib | Use Guava (exception) |
|---|---|
| `new ArrayList<>()` | `Iterables.filter(iter, Type.class)` — type-safe class filtering |
| `new HashSet<>()` | `Iterables.getFirst(iter, default)` — null-safe first element |
| `new HashMap<>()` | `Joiner.on(sep).skipNulls()` — null-skipping joins |
| `List.of(...)` / `Set.of(...)` / `Map.of(...)` | |
| `.stream().filter(...).toList()` | |
| `.stream().map(...).toList()` | |
| `String.join(sep, iter)` | |

**Do not rewrite existing Guava code** that you're not migrating. But do not propagate Guava into newly migrated files where Java stdlib suffices.

## String building — 4-tier decision tree

Choose the most readable Java idiom based on the template pattern (evaluate top-down, first match wins):

| # | Template pattern | Java idiom |
|---|---|---|
| 1 | No interpolation, single line | String literal |
| 2 | No interpolation, multi-line | Text block |
| 3 | Interpolation, no control flow | `.formatted()` (text block if multi-line, literal if single-line) |
| 4 | Control flow (`«IF»`, `«FOR»`) | `StringBuilder` with explicit `if`/`for` |

**`.formatted()` limitations — fall back to concatenation ONLY when:**
- The interpolated expression is glued to adjacent text with no whitespace/delimiter boundary, making `%s` ambiguous (e.g., `"pre" + expr + "suf"` where `"pre%ssuf"` is confusing)
- The template contains literal `%` characters (would need escaping as `%%`)

If the `%s` sits at a natural boundary (start of line, after a space, before a newline), **use `.formatted()`** — do NOT fall back to concatenation just because the expression is at the start or in the middle.

## Never `String.format()` — always `.formatted()`

```java
// Good
"Processing %s in %s".formatted(name, context);

// Bad
String.format("Processing %s in %s", name, context);
```

## Parameterized Log4j2 logging

Use `{}` placeholders. Never concatenation or `.formatted()` in log calls.

```java
private static final Logger LOGGER = LogManager.getLogger(MyClass.class);

// Good
LOGGER.info("Processing file: {}", path);

// Bad
LOGGER.info("Processing file: " + path);
LOGGER.info("Processing file: %s".formatted(path));
```

## `@SuppressWarnings("nls")` — conditional on project settings

Add at class level **only when the module's effective JDT settings have `nonExternalizedStringLiteral=warning`**.

To check: look at `<module>/.settings/org.eclipse.jdt.core.prefs`. If absent (most modules), check the project's shared settings at `ddk-configuration/.settings/org.eclipse.jdt.core.prefs`.

**Current DDK state:** all modules inherit `warning` from `ddk-configuration`, so all migrated classes currently need `@SuppressWarnings("nls")` at class level. If a test-specific settings profile is later added with `ignore` (as in ASMD), test classes would not need it.

```java
@SuppressWarnings("nls")
public class MyMigratedClass extends AbstractBase {
```

## Formatting conventions

- 2-space indentation (project convention).
- Opening brace on the same line.
- Spaces around operators.
- Blank line between methods.
- Keep the original method ordering from the Xtend file.
