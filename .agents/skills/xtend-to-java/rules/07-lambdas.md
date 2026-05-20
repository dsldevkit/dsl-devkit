# Lambdas

## 7.1 `[...]` → `(...) -> {...}`

- `[x | x.name]` → `x -> x.getName()` (parentheses optional for a single param)
- `[it | name]` → `it -> it.getName()` or a method reference
- `[ body ]` with no parameters but implicit `it` → `(it) -> { body }` if `it` is used, `() -> { body }` otherwise.
- Single-expression lambdas: no braces, no `return`.
- Multi-statement lambdas: braces and explicit `return`.
  ```java
  (x) -> { doSomething(); return x.getName(); }
  ```

## 7.2 The implicit `it` parameter

When a lambda uses properties/methods without a receiver, those reference the implicit `it` parameter.
**Name the parameter explicitly in Java.**

- `[name]` on a `Function1<Foo, String>` becomes `(Foo it) -> it.getName()` or `Foo::getName`.
- `checks.filter[name !== null]` becomes `checks.stream().filter(c -> c.getName() != null).toList()`.

## 7.3 Procedure (void) vs Function (returning)

Xtend uses the same `[...]` syntax for both. In Java, decide from context:

- Returns nothing → `Consumer<T>` / `BiConsumer<T,U>` / project-specific procedure type
- Returns a value → `Function<T,R>` / `BiFunction<T,U,R>` / `Predicate<T>` / etc.

Xtext-specific case — `Procedure1<IHiddenRegionFormatter>` lambdas like `[noSpace]` become:
```java
(IHiddenRegionFormatter it) -> { it.noSpace(); }
// or simply
IHiddenRegionFormatter::noSpace
```
