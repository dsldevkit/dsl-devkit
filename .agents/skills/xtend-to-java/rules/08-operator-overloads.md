# Operators and expressions

## 8.1 Identity comparison

- `===` (Xtend identity-equals) → `==` (Java)
- `!==` (Xtend identity-not-equals) → `!=` (Java)
- `==` in Xtend is `.equals()` — convert to `.equals()` or `Objects.equals()` (use `Objects.equals()` when either operand could be null).
- **Applies to object/boxed operands.** `==`/`!=` between primitive-typed operands (int, long, short, byte, char, float, double, boolean) compile to Java `==`/`!=`, not `.equals()` — check operand types in `xtend-gen/` first. (Enums are NOT primitives: enum `==` follows the object form shown in `xtend-gen/`.)

## 8.2 Null-safe navigation `?.`

`obj?.method()` becomes an explicit null check:

- Ternary: `obj != null ? obj.method() : null`
- For chained null-safe calls, nest ternaries or use local variables:
  ```xtend
  resource?.URI
  ```
  →
  ```java
  final URI uri = resource != null ? resource.getURI() : null;
  ```
- If the `?.` result is compared against `null` (e.g., `resource?.URI !== null`), inline the AND: `resource != null && resource.getURI() != null`.

## 8.3 Elvis `?:`

`a ?: b` → `a != null ? a : b` (or `Objects.requireNonNullElse(a, b)` when the eager evaluation of `b` is fine).

## 8.4 `=>` (with/apply)

`obj => [ body ]` executes `body` with `obj` as `it`, then returns `obj`.

```xtend
new Foo() => [bar = "baz"]
```
becomes
```java
Foo foo = new Foo();
foo.setBar("baz");
// use foo
```

For non-trivial bodies, extract to a helper or rewrite as a sequence of statements.

## 8.5 String concatenation `+`

Same in Java.

## 8.6 Range `..`

`0..n` → `IntStream.rangeClosed(0, n)` or a `for`-loop, whichever fits the call site.

## 8.7 Power `**`

`a ** b` → `Math.pow(a, b)`.

## 8.8 Collection operators

- `list += element` → `list.add(element)`
- `list += otherList` → `list.addAll(otherList)`
- `list -= element` → `list.remove(element)`
- `map[key]` (Xtend bracket access) → `map.get(key)`
