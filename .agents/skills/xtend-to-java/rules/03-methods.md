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

Xtend doesn't enforce checked exceptions. Java does. When the body calls APIs that throw checked exceptions:

- Add `throws ...` to the method signature, **or**
- Wrap in `try`/`catch`.
- Common cases: `CoreException` from Eclipse APIs, `IOException` from I/O.

Catch specific exceptions — never generic `Exception`. See the quality checklist for IllegalCatch.
