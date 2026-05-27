# Miscellaneous syntax

## 9.1 Type references

- `typeof(X)` → `X.class`. Example: `typeof(CheckCatalog)` → `CheckCatalog.class`.
- Generic syntax: identical in Xtend and Java. Keep as-is.
- Type casting: `expr as Type` → `(Type) expr`, or Java 21 pattern matching with `instanceof`.

## 9.2 Collection literals

- `#["a", "b", "c"]` → `List.of("a", "b", "c")` (immutable) or `new ArrayList<>(List.of("a", "b", "c"))` (mutable).
- `#[]` (empty) → `List.of()` or `new ArrayList<>()`.
- `newArrayList(...)` → `new ArrayList<>(List.of(...))` or `new ArrayList<>()`.
- `#{"a", "b"}` → `Set.of("a", "b")` or `new HashSet<>(Set.of(...))`.
- `newHashSet(...)` → `new HashSet<>(Set.of(...))`.
- `newHashMap(...)` → `new HashMap<>(Map.of(...))`.

## 9.3 Collection extension methods

Most have direct Stream equivalents. See [`references/xtend-library-replacements.md`](../references/xtend-library-replacements.md).

**Exception — keep Guava where genuinely superior:**
- `Iterables.filter(iter, Type.class)` — type-safe class filtering (Java streams need `.filter(Foo.class::isInstance).map(Foo.class::cast)`)

## 9.4 `isNullOrEmpty`

- `StringExtensions.isNullOrEmpty(s)` or `s.isNullOrEmpty` → `s == null || s.isEmpty()`.

## 9.5 Property access syntax

Xtend allows getter/setter property access:
- `obj.name` → `obj.getName()` (when `name` is not a public field).
- `obj.name = value` → `obj.setName(value)`.
- `obj.isActive` → `obj.isActive()` (or `obj.getIsActive()`; check the actual class).
- `field.final` → `field.isFinal()`, `field.static` → `field.isStatic()`.

**Caveat**: not all dot-access is property access. If the receiver has an actual public field, keep field access. Check `xtend-gen/` if unsure.

## 9.6 Active annotations

### `@Data`

Generates `equals()`, `hashCode()`, `toString()`, and getters for final fields.

- Class with no superclass and no mutable state → convert to a Java **`record`**.
  ```java
  public record SemanticPredicate(String name, String message, String grammar, List<String> keywords) {}
  ```
- Otherwise, manually add: all-args constructor, getters, `equals()`, `hashCode()`, `toString()`.
  The `xtend-gen/` output shows exactly what was generated — copy from there.

### `@Accessors`

Generates getters (and setters for `var` fields).

- `@Accessors boolean foo` → `getFoo()` + `setFoo(boolean)`.
- `@Accessors(PUBLIC_SETTER) String bar` → public setter only.
- `@Accessors(PROTECTED_GETTER) Foo baz` → protected getter only.

Write the accessor methods explicitly with the specified visibility.

### `@FinalFieldsConstructor`

Generates a constructor taking all final fields as parameters. Write it manually.

### `@Tag`

Assigns sequential integers (starting at `TagCompilationParticipant.COUNTER_BASE = 10000`) to test
marker fields at Xtend compile time via `TagCompilationParticipant`.

Standard Java APT (`AbstractProcessor`) **cannot** replicate this — it can only generate new source
files, not modify initializers of existing fields. Use `TagExtension` instead: a JUnit 5
`BeforeEachCallback` in `com.avaloq.tools.ddk.xtext.test.core` that performs the same sequential
assignment at runtime via reflection.

**Migration steps:**

1. Declare each `@Tag` field as `private int` — **no `final`**, **no explicit initializer**:
   ```java
   @Tag
   private int MY_TAG;
   ```
2. Add `TagExtension.class` to `@ExtendWith` (alongside any existing extensions):
   ```java
   @ExtendWith({InjectionExtension.class, TagExtension.class})
   ```
3. Add the import:
   ```java
   import com.avaloq.tools.ddk.xtext.test.TagExtension;
   ```

**Why not `final`?** `TagExtension` uses `Field.setInt()` to write the value at runtime.
`Field.setInt()` on a `final` field throws `IllegalAccessException` even after `setAccessible(true)`
on Java 9+. Formatters and IDE save-actions routinely add `final` to `int` fields — always remove it
for `@Tag` fields.

**Why not explicit initializers?** The extension assigns values in declaration order starting from
`COUNTER_BASE`. Hardcoded values are redundant and will silently drift out of sync when fields are
reordered or inserted.

**Field ordering matters.** `TagExtension` iterates `getDeclaredFields()` in source-declaration order
(guaranteed by HotSpot; JVM spec does not mandate it, but all production JVMs preserve it).
Inserting a new `@Tag` field in the middle shifts subsequent values — exactly as the Xtend active
annotation would have done.

## 9.7 Dispatch methods

Xtend's multi-dispatch (`def dispatch ...`) has no Java equivalent. Convert to: individual `protected` methods prefixed with `_` plus a single dispatcher method that does the type-test routing.

```xtend
def dispatch void format(CheckCatalog c, IFormattableDocument doc) { ... }
def dispatch void format(Category c, IFormattableDocument doc) { ... }
def dispatch void format(EObject obj, IFormattableDocument doc) { ... }
```
becomes
```java
@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class MyFormatter extends AbstractFormatter {

  protected void _format(CheckCatalog c, IFormattableDocument doc) { ... }
  protected void _format(Category c, IFormattableDocument doc) { ... }
  protected void _format(EObject obj, IFormattableDocument doc) { ... }

  public void format(Object obj, IFormattableDocument doc) {
    if (obj instanceof CheckCatalog c) {
      _format(c, doc); return;
    } else if (obj instanceof Category c) {
      _format(c, doc); return;
    } else if (obj instanceof EObject e) {
      _format(e, doc); return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + obj);
    }
  }
}
```

Rules:
- **Keep the `_` prefix** — the Xtext runtime resolves dispatch by name.
- **Suppress at class level**: `@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})`
- Order `instanceof` checks from most specific to least specific.
- If the original `dispatch` had `override`, add `@Override` to the **dispatcher**, not the `_` methods.
- The dispatcher parameter type should be the common supertype (often `Object` or `EObject`).
- If the parent class has dispatch methods with the same name, the dispatcher must call `super._methodName()` for types not handled locally.
- Use Java 21 pattern matching for `instanceof`.

## 9.8 Special patterns

- **`it` implicit parameter on methods**: when a method declares `Type it` as its first parameter, unqualified calls in the body refer to `it`. Rename the parameter (e.g. `exportModel`) and qualify every call accordingly (`exports` → `exportModel.getExports()`).
- **`this` vs receiver resolution**: in Xtend, an unqualified call may dispatch to `this`, to an `@Inject extension`, to a `static extension` import, or to `it`. Decide based on the type hierarchy and rewrite to one of:
  1. `this.method()` or just `method()` (own class / superclass)
  2. `field.method(obj)` (`@Inject extension` field)
  3. `ExtClass.method(obj)` (`static extension`)
  4. `it.method()` using the renamed parameter
- **Multiple return paths**: Xtend implicitly returns the last expression. Add explicit `return` on **every** non-void path.
- **`class` keyword as literal**: `SomeClass` used as a class literal → `SomeClass.class`.
- **Static method reference `::`**: `ClassName::methodName` → `ClassName.methodName()` (or keep as a Java method reference where the receiving API accepts one).
- **Pairs**: `key -> value` (in pair-construction context) → `Pair.of(key, value)` or `Map.entry(key, value)`.
- **`^keyword`** (escaped reserved word in Xtend): Drop the `^` — most Xtend escapes aren't Java keywords.

## 9.9 Guice DI

- `@Inject` fields stay as `@Inject`. Add `private` if missing.
- `@Inject ClassName fieldName` → `@Inject private ClassName fieldName;`
- For `@Inject extension`, see [`rules/06-extension-methods.md`](./06-extension-methods.md).

## 9.10 Comments and Javadoc

- Preserve **all** comments (Javadoc `/** */`, block `/* */`, line `//`) exactly.
- **Copy Javadoc from the Xtend source verbatim.** Never generate, guess, or infer Javadoc that was not in the original. Invented comments are misleading.
- **`@throws` tags**: Only add when (1) the method already has Javadoc AND (2) the migrated signature declares a `throws` clause. Do not add Javadoc just to host a `@throws` tag.
- Do **not** add `@SuppressWarnings("all")` — the Xtend compiler injects this into `xtend-gen/`; human-converted Java shouldn't have it.
