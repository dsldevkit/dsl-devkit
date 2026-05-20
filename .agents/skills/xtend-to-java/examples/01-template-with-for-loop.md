# Example: template with `«FOR»` and `«SEPARATOR»`

Demonstrates the `StringBuilder` conversion pattern for templates that emit repeated content with a separator. Touches rule 04 in detail.

## Xtend input

```xtend
def renderArgs(List<Argument> args) '''
  («FOR a : args SEPARATOR ", "»«a.type» «a.name»«ENDFOR»)
'''
```

## Java output — stream approach

When the body is a simple expression, use `Collectors.joining()`:

```java
public CharSequence renderArgs(final List<Argument> args) {
  final StringBuilder builder = new StringBuilder();
  builder.append("(");
  builder.append(args.stream()
      .map(a -> a.getType() + " " + a.getName())
      .collect(Collectors.joining(", ")));
  builder.append(")\n");
  return builder;
}
```

## Java output — boolean flag approach

When the body has multi-statement logic, use a flag:

```java
public CharSequence renderArgs(final List<Argument> args) {
  final StringBuilder builder = new StringBuilder();
  builder.append("(");
  boolean first = true;
  for (final Argument a : args) {
    if (!first) {
      builder.append(", ");
    }
    builder.append(a.getType()).append(" ").append(a.getName());
    first = false;
  }
  builder.append(")\n");
  return builder;
}
```

## What changed

- The `«FOR … SEPARATOR ", "»` block became either a stream with `Collectors.joining(", ")` or a boolean flag.
- The `«a.type»` and `«a.name»` interpolations became `a.getType()` and `a.getName()`.
- The trailing newline (implicit `\n` at end of template `'''`) is preserved by explicit `"\n"`.
- **Whitespace was verified against `xtend-gen/`** — the template starts with `(` directly (no leading newline because the first `'''` line has content after the opening mark).
