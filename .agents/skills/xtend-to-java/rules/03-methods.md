# Method declarations

## 3.1 `def` methods

- `def` means `public` by default. Add explicit `public`.
- `def private`, `def protected`, `def package` keep their visibility.
- Add explicit return type. If the Xtend method omits the return type, infer it from the method body (or from the override target).

Example:
```xtend
def outputPath() { '.settings' }
```
becomes
```java
public String outputPath() {
  return ".settings";
}
```

## 3.2 `override` keyword

Replace `override` with `@Override` annotation plus an explicit visibility modifier.
**`@Override` on every override** — including interface method implementations.

```xtend
override void doGenerate(...) { ... }
```
becomes
```java
@Override
public void doGenerate(...) {
  ...
}
```

For `override protected doGenerate()`:
```java
@Override
protected void doGenerate() {
  ...
}
```

## 3.3 Return types and implicit returns

Xtend returns the value of the last expression. In Java, **add explicit `return` statements** for every non-void return path. For `void` methods, no return needed.

```xtend
def foo() { bar }
```
becomes
```java
public SomeType foo() {
  return bar;
}
```

## 3.4 Method parameters

- Add `final` to parameters that are not reassigned (matches the project's existing Java code convention).
- `extension` parameters: see [`rules/06-extension-methods.md`](./06-extension-methods.md).

## 3.5 Checked exceptions

Xtend doesn't enforce checked exceptions. Java does. Treat that mismatch as an API-design decision rather
than copying the compiler's workaround:

- keep explicit catches specific, and catch only the narrow checked types Java requires;
- declare the exact checked exception when the method is private/package-local or its inherited API permits it;
- when an override or compatibility-sensitive public API cannot declare the exception, stop and obtain explicit
  review for the boundary strategy (for example, an established project-specific unchecked exception), preserving
  the original exception as the cause.

Common cases are `CoreException` from Eclipse APIs and `IOException` from I/O. Never introduce an arbitrary
wrapper merely to make the migration compile. See [`rules/05-control-flow.md`](./05-control-flow.md) §5.5 and
the quality checklist. Never copy `xtend-gen`'s broad `catch (Throwable)` scaffold unless the invoked API itself
declares `Throwable` and no narrower catch can compile.
