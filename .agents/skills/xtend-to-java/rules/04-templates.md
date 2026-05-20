# Template expressions (guillemets)

The most complex feature. Xtend templates use triple single quotes plus `«»` interpolation markers.

## Template whitespace — the #1 source of migration bugs

Xtend template expressions (`'''...'''`) have **smart whitespace handling** that is NOT obvious from
reading the Xtend source alone. You MUST check the `xtend-gen/` output to see the actual string values.

The rules (from the Xtend docs):

1. **Indentation relative to a control structure is stripped.** If a template starts with `'''` followed
   by a newline, the indentation on subsequent lines relative to that opening mark is removed.

2. **Lines containing only control structures (`«IF»`, `«FOR»`, `«ENDIF»`) produce no output.**

3. **Newlines in appended strings get the current indentation prepended.**

**What this means in practice:**
```xtend
def String example() {'''
    check configuration «name» {'''.toString}
```
produces `"check configuration testing {"` — **NOT** `"\n    check configuration testing {"`.

**The only reliable way to know what a template produces is to read the `xtend-gen/` output.**

## 4.1 String building — choose the right idiom

Apply the 4-tier decision tree from [`rules/00-decisions.md`](./00-decisions.md):

### Tier 1 — Static single line → string literal

```xtend
'''some static text'''
```
becomes
```java
"some static text"
```

### Tier 2 — Static multi-line → text block

```xtend
'''
line one
line two
'''
```
becomes
```java
"""
line one
line two
"""
```

### Tier 3 — Interpolation without control flow → `.formatted()`

```xtend
'''«type» «name»;'''
```
becomes
```java
"%s %s;".formatted(type, name)
```

Multi-line with interpolation:
```xtend
'''
package «packageName»;

public class «className» {
}
'''
```
becomes
```java
"""
package %s;

public class %s {
}
""".formatted(packageName, className)
```

**Fall back to concatenation ONLY when** `%s` is ambiguous or template has literal `%`:
```java
// OK — %s at natural boundary
"import %s;".formatted(imp)

// Fallback — %s glued to text with no boundary
"{predicates." + predicate.getName() + "(parserContext)}?=>"
```

### Tier 4 — Control flow → `StringBuilder`

```xtend
'''
«FOR k : properties.keySet»
«k»=«properties.get(k)»
«ENDFOR»
'''
```
becomes
```java
final StringBuilder builder = new StringBuilder();
for (final String k : properties.keySet()) {
  builder.append(k).append("=").append(properties.get(k)).append("\n");
}
return builder;
```

## 4.2 Template control flow patterns

- `«IF condition»...«ENDIF»` → `if (condition) { builder.append(...); }`
- `«IF condition»...«ELSE»...«ENDIF»` → `if`/`else`
- `«ELSEIF condition»` → `else if (condition)`
- `«FOR item : collection»...«ENDFOR»` → `for (Type item : collection) { builder.append(...); }`
- `«FOR item : collection SEPARATOR sep»...«ENDFOR»` → use `StringJoiner`, `Collectors.joining(sep)`, or a boolean flag
- `«val x = expr»` inside a template → declare as a local before the relevant `.append()`

### SEPARATOR pattern

Stream approach (when body is a simple expression):
```java
builder.append(collection.stream()
    .map(item -> item.getType() + " " + item.getName())
    .collect(Collectors.joining(", ")));
```

Boolean flag (when body is complex):
```java
boolean first = true;
for (final Argument a : args) {
  if (!first) {
    builder.append(", ");
  }
  builder.append(a.getType()).append(" ").append(a.getName());
  first = false;
}
```

## 4.3 Text block `\` escape — suppressing trailing newlines

Java text blocks always end with a trailing newline before the closing `"""`. When the original
string must NOT end with `\n`, use a **line continuation** `\` on the last content line:

```java
// String that ends with } not }\n
String s = """

    check configuration mdlc
      for com.avaloq.tools.dsl.labeldef.LabelDef {
        catalog com.avaloq.tools.dsl.labeldef.validation.LabelDefCoreChecks { }
      }\
""";
```

The `\` suppresses the line terminator. Place the closing `"""` at column 0 to prevent indent stripping.

**When to use this pattern:** check the `xtend-gen/` output. If the original string starts with `\n`
and does NOT end with `\n`, the `\` escape is the correct approach.

## 4.4 Return type

Methods that return templates should return `CharSequence` (matches `StringBuilder`).
Return `String` only if every caller treats it as a `String` (and `.toString()` the builder).

## 4.5 `«expression»` interpolation

Convert each `«expr»` into the corresponding Java expression:
- Inside `.formatted()`: as `%s` argument
- Inside `StringBuilder`: as `.append(expr)` call

Property access: `«catalog.name»` → `catalog.getName()` (see [`rules/09-misc-syntax.md`](./09-misc-syntax.md)).

## 4.6 StringBuilder sizing

PMD's `InsufficientStringBufferDeclaration` checks that the declared capacity is large enough for the appended content. Java's default of 16 is almost never enough and trips the rule; aggressive sizing also prevents the first internal resize.

Pick the next power of two above the expected length:

- Methods appending < 500 chars → `new StringBuilder(512)`
- Generator methods producing larger output → `new StringBuilder(2048)`
- For known sizes outside those ranges, size to the next power of two.

## 4.7 MultipleStringLiterals — Checkstyle

Checkstyle flags any string literal that appears 2+ times. Handle differently by context:

**In test classes:** extract to `private static final String` constants:
```java
private static final String TEST_ORA_USER = "TEST.ORA USER";
```

**In generator methods:** wrap the method body with suppression:
```java
// CHECKSTYLE:CONSTANTS-OFF
// ... method body with repeated keywords ...
// CHECKSTYLE:CONSTANTS-ON
```

Prefer constants over suppression whenever the repeated string has a meaningful name.
