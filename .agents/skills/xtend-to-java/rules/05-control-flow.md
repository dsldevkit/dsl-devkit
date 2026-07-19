# Control flow

## 5.1 Switch as expression

```xtend
switch(x) {
    case "a": doA()
    case "b": doB()
    default: doDefault()
}
```
becomes a Java switch expression (preferred, Java 14+) or switch statement, depending on context.

## 5.2 Switch with type guards

```xtend
switch obj {
    CheckCatalog: obj.name
    Category case obj.name !== null: obj.label
    default: "unknown"
}
```
becomes
```java
if (obj instanceof CheckCatalog checkCatalog) {
    return checkCatalog.getName();
} else if (obj instanceof Category category && category.getName() != null) {
    return category.getLabel();
} else {
    return "unknown";
}
```

Use Java 21 pattern matching for `instanceof` wherever applicable.

## 5.3 Template control flow

Already covered in [`rules/04-templates.md`](./04-templates.md). Short reference:

- `«IF» / «ELSEIF» / «ELSE» / «ENDIF»` → `if` / `else if` / `else`
- `«FOR x : xs»` → `for (Type x : xs)`
- `«FOR x : xs SEPARATOR sep»` → `Collectors.joining(sep)` or a boolean separator-flag

## 5.4 If as expression

Xtend `if` is an expression that returns a value. Java needs either a ternary or extraction.

```xtend
val label = if (x !== null) x.name else "<none>"
```
becomes
```java
final String label = x != null ? x.getName() : "<none>";
```

For multi-line bodies, factor to a helper method or write `if`/`else` with an assignment in each branch.

## 5.5 Exception handling — preserve behaviour without broad catches

Xtend hides checked exceptions. Its generated Java may contain broad exception-handling scaffolding, but that is
**not a migration template**. Write an explicit Java exception contract using the narrowest types that compile:

- Keep every explicit Xtend `catch (SpecificException e)` as the same specific Java catch. Non-matching
  exceptions and errors already propagate unchanged without being caught.
- If an uncaught checked exception can be declared without violating an override or compatibility-sensitive
  public API, add that exact exception to the `throws` clause and let it propagate normally.
- If the method cannot declare it, do not choose a workaround mechanically. Obtain explicit review for the
  boundary strategy. When the project has an established unchecked counterpart (for example,
  `UncheckedIOException` or Xtext's `RuntimeIOException`), catch only the precise checked type at the smallest
  scope and preserve it as the cause.
- **Do not catch `Throwable`, `Exception`, or `RuntimeException` merely to copy `xtend-gen`.** A broad catch is
  permitted only when the invoked API itself declares that exact broad type, no narrower compiler-visible
  catch can compile, and preserving the public signature is required. Keep that exceptional catch as small
  as possible and justify the narrow `@SuppressWarnings("checkstyle:IllegalCatch")` at the site.
- Adding a checked `throws` clause to a compatibility-sensitive public API is an API change and requires explicit
  review.
- **Do not invent an arbitrary wrapper.** `new RuntimeException(e)` / `new IllegalStateException(e)` changes the
  exception contract and is not a neutral migration. Use an established domain-specific counterpart only after
  explicit review, and always preserve the caught exception as the cause. (A legitimate
  `throw new IllegalStateException("message")` for a genuinely bad state, with no caught cause, is unrelated.)
