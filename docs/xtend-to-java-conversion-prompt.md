# Xtend-to-Java Conversion Prompt

You are an expert Java and Xtend developer. Your task is to convert a single Xtend (.xtend) file into idiomatic Java (.java) targeting Java 21. The file belongs to the dsl-devkit project, an Eclipse/Xtext-based DSL development kit that uses Google Guice for dependency injection.

## INPUT

You will receive the complete contents of one `.xtend` file.

## OUTPUT

Return the complete, compilable `.java` file. The output must:
- Be valid Java 21 code
- Compile without errors in the context of the dsl-devkit project
- Preserve all original functionality exactly
- Be idiomatic Java (NOT a mechanical translation)
- Include all necessary imports

---

## DECISIONS

- **Target: Java 21** (pattern matching, switch expressions, text blocks, records)
- **Template strategy: `StringBuilder`** (pure Java, no Xtend runtime dependency)
- **No `var` keyword**: Always use explicit types (no Java 10 `var` / `final var`)
  - `val catalog = ...` -> `final CheckCatalog catalog = ...;` (NOT `final var catalog = ...;`)
  - `var skip = ...` -> `int skip = ...;` (NOT `var skip = ...;`)
  - All local variables, fields, and parameters must have explicit type declarations.

---

## CONVERSION RULES

Apply these rules systematically, in order. Every rule is mandatory.

### 1. FILE STRUCTURE AND BASICS

1.1. **Package declaration**: Keep identical. Add semicolon if missing.

1.2. **Imports**: Convert all Xtend imports to Java imports.
- `import com.foo.Bar` stays as `import com.foo.Bar;`
- `import static com.foo.Bar.*` stays as `import static com.foo.Bar.*;`
- `import static extension com.foo.Bar.*` becomes `import static com.foo.Bar.*;` (the `extension` keyword is dropped; see Rule 8 for call-site conversion)
- Remove imports for Xtend-specific types that are no longer needed (e.g., `org.eclipse.xtend2.lib.StringConcatenation` if you use StringBuilder instead)
- Add any new imports needed by the Java code (e.g., `java.util.List`, `java.util.ArrayList`, `java.util.stream.*`, `java.util.Objects`)
- Do NOT add wildcard imports. Use explicit imports.
- Remove unused imports.

1.3. **Class declaration**:
- Xtend classes are `public` by default. Add `public` explicitly.
- `class Foo extends Bar` becomes `public class Foo extends Bar`
- Xtend `interface` stays as `interface` (already public by default in Java too).
- Add `{` and `}` braces as normal Java.

1.4. **Semicolons**: Add semicolons to all statements. Xtend allows omitting them; Java requires them.

### 2. VARIABLE DECLARATIONS

2.1. **`val` (final local variable)**:
- Always use explicit types: `final ExplicitType name = expr;`
- Example: `val catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class)` becomes `final CheckCatalog catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class);`

2.2. **`var` (mutable local variable)**:
- Always use explicit types: `ExplicitType name = expr;`
- Example: `var skip = instance - 1` becomes `int skip = instance - 1;`

2.3. **`val` fields (class-level final fields)**:
- `val String FOO = "bar"` becomes `private final String FOO = "bar";`
- `val static Logger LOGGER = ...` becomes `private static final Logger LOGGER = ...;`
- Always add explicit visibility (`private` unless a different visibility is needed).

2.4. **`var` fields (class-level mutable fields)**:
- `var String name` becomes `private String name;`
- Add explicit visibility.

### 3. METHOD DECLARATIONS

3.1. **`def` methods**:
- `def` means `public` by default. Add explicit `public`.
- `def private`, `def protected`, `def package` keep their visibility.
- Add explicit return type. If the Xtend method omits the return type, infer it from the method body.
- Example: `def outputPath()` with body `'.settings'` becomes `public String outputPath()`

3.2. **`override` keyword**:
- Replace `override` with `@Override` annotation plus the appropriate visibility modifier.
- `override void doGenerate(...)` becomes:
  ```java
  @Override
  public void doGenerate(...) {
  ```
- `override protected doGenerate()` becomes:
  ```java
  @Override
  protected void doGenerate() {
  ```

3.3. **Return types and implicit returns**:
- Xtend methods return the value of the last expression. In Java, add explicit `return` statements.
- If a method's last expression is a value, wrap it in `return`.
- For `void` methods, no return is needed.
- Example: Xtend `def foo() { bar }` becomes Java `public SomeType foo() { return bar; }`

3.4. **Method parameters**:
- Add `final` to parameters in methods that do not reassign them (follow the convention of the existing Java code in the project).
- `extension` parameters: see Rule 8.

### 4. TYPE REFERENCES

4.1. **`typeof(X)` to `X.class`**:
- Replace all `typeof(ClassName)` with `ClassName.class`.
- Example: `typeof(CheckCatalog)` becomes `CheckCatalog.class`

4.2. **Generic type syntax**: Xtend and Java use the same generic syntax. Keep as-is.

4.3. **Type casting**: `expr as Type` becomes `(Type) expr` or use pattern matching with `instanceof` (Java 21).

### 5. OPERATORS AND EXPRESSIONS

5.1. **Identity comparison**:
- `===` (identity equals) becomes `==` in Java.
- `!==` (identity not-equals) becomes `!=` in Java.
- `==` in Xtend is `.equals()`. Convert to `.equals()` or `Objects.equals()` in Java (use `Objects.equals()` when either operand could be null).

5.2. **Null-safe navigation `?.`**:
- `obj?.method()` becomes a null check. Use one of:
  - Ternary: `obj != null ? obj.method() : null`
  - If-statement for complex cases
  - For chained null-safe calls, nest the ternaries or use local variables:
    ```java
    // Xtend: resource?.URI
    // Java:
    final URI uri = resource != null ? resource.getURI() : null;
    ```
  - IMPORTANT: If the result of `?.` is used in a comparison against null (e.g., `resource?.URI !== null`), then use: `resource != null && resource.getURI() != null`

5.3. **Elvis operator `?:`**:
- `a ?: b` becomes `a != null ? a : b` (or `Objects.requireNonNullElse(a, b)` if appropriate).

5.4. **`=>` operator (with/apply)**:
- `obj => [ body ]` executes `body` with `obj` as `it`, then returns `obj`.
- Convert to:
  ```java
  { // inline block or extract to a method
    ExplicitType temp = obj;
    // body, replacing `it` references with `temp`
    // return temp; // if the result is used
  }
  ```
- For simple cases like `new Foo() => [bar = "baz"]`, convert to:
  ```java
  Foo foo = new Foo();
  foo.setBar("baz");
  // use foo
  ```

5.5. **String concatenation `+`**: Same in Java.

5.6. **Range operator `..`**: `0..n` becomes `IntStream.rangeClosed(0, n)` or a for-loop.

5.7. **Power operator `**`**: Use `Math.pow()`.

### 6. LAMBDA EXPRESSIONS

6.1. **Xtend `[...]` lambda to Java `(...) -> {...}`**:
- `[x | x.name]` becomes `(x) -> x.getName()` or `x -> x.getName()`
- `[it | name]` becomes `(it) -> it.getName()` or simply use a method reference
- `[ body ]` (no parameters, implicit `it`) becomes `(it) -> { body }` where `it` is used, or `() -> { body }` if `it` is not used.
- Single-expression lambdas do not need braces: `x -> x.getName()`
- Multi-statement lambdas need braces and explicit `return`: `(x) -> { doSomething(); return x.getName(); }`

6.2. **Lambda with `it` as implicit parameter**:
- When a lambda uses properties/methods without a receiver, these reference the implicit `it` parameter.
- `[name]` on a `Function1<Foo,String>` becomes `(Foo it) -> it.getName()` or `Foo::getName`
- Xtend: `checks.filter[name !== null]` becomes Java: `checks.stream().filter(c -> c.getName() != null).toList()`

6.3. **Procedure (void lambda) vs Function (returning lambda)**:
- Xtend uses the same `[...]` syntax for both.
- In Java, determine from context whether it is `Consumer<T>`, `Predicate<T>`, `Function<T,R>`, etc.
- For Xtext-specific cases: `Procedure1<IHiddenRegionFormatter>` lambdas like `[noSpace]` become `(IHiddenRegionFormatter it) -> { it.noSpace(); }` or `IHiddenRegionFormatter::noSpace`.

### 7. DISPATCH METHODS

This is one of the most complex Xtend features. Dispatch methods implement multiple dispatch (method selection based on runtime type of arguments).

7.1. **Pattern**: A set of `def dispatch` methods with the same name but different parameter types:
```xtend
def dispatch void format(CheckCatalog c, IFormattableDocument doc) { ... }
def dispatch void format(Category c, IFormattableDocument doc) { ... }
def dispatch void format(EObject obj, IFormattableDocument doc) { ... }
```

7.2. **Conversion strategy**: Convert to individual `protected` methods prefixed with `_` (underscore) plus a public dispatcher method:

```java
protected void _format(CheckCatalog c, IFormattableDocument doc) { ... }
protected void _format(Category c, IFormattableDocument doc) { ... }
protected void _format(EObject obj, IFormattableDocument doc) { ... }

public void format(Object obj, IFormattableDocument doc) {
    if (obj instanceof CheckCatalog c) {
        _format(c, doc);
        return;
    } else if (obj instanceof Category c) {
        _format(c, doc);
        return;
    } else if (obj instanceof EObject e) {
        _format(e, doc);
        return;
    } else {
        throw new IllegalArgumentException("Unhandled parameter types: " + obj);
    }
}
```

7.3. **Important dispatch rules**:
- Order type checks from most specific to least specific.
- If a dispatch method has `override` keyword, add `@Override` to the dispatcher method, not the individual `_` methods.
- The dispatcher parameter type should be the common supertype (usually `Object` or `EObject`).
- If the parent class also has dispatch methods with the same name, the dispatcher must call `super._methodName()` for types not handled locally.
- Use Java 21 pattern matching for instanceof (`if (obj instanceof Foo f)`) in the dispatcher.

### 8. EXTENSION METHODS

8.1. **`@Inject extension ClassName fieldName`**:
- Convert to: `@Inject private ClassName fieldName;`
- At every call site where the extension's methods were called as `obj.extensionMethod(args)`, convert to `fieldName.extensionMethod(obj, args)`.
- If the extension field was used without a name (e.g., `@Inject extension CheckGeneratorNaming`), generate a field name following the convention: `_checkGeneratorNaming` (underscore + camelCase class name starting lowercase).

8.2. **`extension` method parameters** (e.g., `def foo(extension IFormattableDocument document)`):
- Drop the `extension` keyword from the parameter.
- At call sites within the method body, calls that were dispatched to the extension parameter need to be converted to explicit calls:
  - `prepend(checkcatalog)[noSpace]` becomes `document.prepend(checkcatalog, (IHiddenRegionFormatter it) -> it.noSpace())`

8.3. **`static extension` imports** (e.g., `import static extension com.foo.Bar.*`):
- Convert to `import static com.foo.Bar.*;`
- At call sites: `obj.staticExtensionMethod(args)` becomes `Bar.staticExtensionMethod(obj, args)`.
- Example: `import static extension org.eclipse.xtext.GrammarUtil.*` then `grammar.simpleName` becomes `GrammarUtil.getSimpleName(grammar)`.

8.4. **`extension` keyword on `val`/`var`** (e.g., `val extension naming = contentAssistNaming`):
- Drop `extension` keyword. Keep as a local variable.
- Convert call sites within scope to explicit calls on the variable.

### 9. TEMPLATE EXPRESSIONS (GUILLEMETS)

This is the MOST COMPLEX feature. Template expressions use triple single quotes and guillemets (French quotes).

9.1. **Simple templates** (no control flow, just interpolation):
- If the template is a single line or very short, use string concatenation or `String.format()`:
  ```xtend
  '''{predicates.«predicate.name»(parserContext)}?=>'''
  ```
  becomes:
  ```java
  "{predicates." + predicate.getName() + "(parserContext)}?=>"
  ```

9.2. **Multi-line templates generating code/text**: Use `StringBuilder`:
```xtend
def compile(CheckConfiguration config) {
    val properties = propertiesGenerator.convertToProperties(config);
    '''
    «FOR k:properties.keySet»
    «k»=«properties.get(k)»
    «ENDFOR»
    '''
}
```
becomes:
```java
public CharSequence compile(CheckConfiguration config) {
    final Properties properties = propertiesGenerator.convertToProperties(config);
    final StringBuilder builder = new StringBuilder();
    for (final String k : properties.keySet()) {
        builder.append(k).append("=").append(properties.get(k)).append("\n");
    }
    return builder;
}
```

9.3. **Template control flow**:
- `«IF condition»...«ENDIF»` becomes `if (condition) { builder.append(...); }`
- `«IF condition»...«ELSE»...«ENDIF»` becomes `if-else`
- `«ELSEIF condition»` becomes `else if (condition)`
- `«FOR item : collection»...«ENDFOR»` becomes `for (Type item : collection) { builder.append(...); }`
- `«FOR item : collection SEPARATOR sep»...«ENDFOR»` -- use a boolean flag or `String.join()` or `Collectors.joining()`:
  ```java
  builder.append(collection.stream()
      .map(item -> /* expression */)
      .collect(Collectors.joining(sep)));
  ```
- `«val x = expr»` inside a template is a local variable declaration. Declare it before use.

9.4. **Template indentation**:
- Xtend templates preserve indentation relative to the insertion point. In the Java conversion, you do NOT need to replicate this Xtend-specific whitespace behavior exactly. Instead:
  - For code generators producing source code: use explicit `\n` and string indentation as appropriate.
  - For simple cases, inline `\n` in the StringBuilder appends.
  - For complex generators, consider creating a helper method or using a `StringJoiner`.

9.5. **Return type**: Methods returning template expressions should return `CharSequence` (to match Xtend's `StringConcatenation` return type, which implements `CharSequence`). Alternatively, return `String` if all callers use it as `String` (add `.toString()` call on the `StringBuilder`).

9.6. **`«expression»` interpolation**: Convert `«expr»` to the corresponding Java expression inside a `.append()` call.
- `«catalog.name»` becomes `.append(catalog.getName())`
- `«IF grammar !== null»GRAMMAR_NAME,«ENDIF»` becomes:
  ```java
  if (grammar != null) {
      builder.append("GRAMMAR_NAME,");
  }
  ```

### 10. COLLECTION LITERALS AND OPERATIONS

10.1. **`#[]` (list literal)**:
- `#["a", "b", "c"]` becomes `List.of("a", "b", "c")` (immutable) or `new ArrayList<>(List.of("a", "b", "c"))` (mutable).
- Empty: `#[]` becomes `List.of()` or `new ArrayList<>()`.
- `newArrayList` becomes `new ArrayList<>()` or `new ArrayList<>(...)`.
- `newArrayList("a", "b")` becomes `new ArrayList<>(List.of("a", "b"))` or `Lists.newArrayList("a", "b")` (if Google Guava is available, which it is in this project).

10.2. **`#{}` (set literal)**:
- `#{"a", "b"}` becomes `Set.of("a", "b")` or `new HashSet<>(Set.of("a", "b"))`.
- `newHashSet` becomes `new HashSet<>()` or `Sets.newHashSet(...)`.

10.3. **Collection operations** (Xtend extension methods on Iterable/Collection):
- `.map[expr]` becomes `.stream().map(x -> expr).toList()` or `.stream().map(x -> expr).collect(Collectors.toList())`
- `.filter[expr]` becomes `.stream().filter(x -> expr).toList()`
- `.filter(Type)` becomes `.stream().filter(Type.class::isInstance).map(Type.class::cast).toList()` OR use Guava `Iterables.filter(collection, Type.class)`
- `.exists[expr]` becomes `.stream().anyMatch(x -> expr)`
- `.forall[expr]` becomes `.stream().allMatch(x -> expr)`
- `.findFirst[expr]` becomes `.stream().filter(x -> expr).findFirst().orElse(null)`
- `.head` becomes `.get(0)` (for List) or `.iterator().next()` (for Iterable), with null safety if needed
- `.tail` becomes `.subList(1, list.size())` or `.stream().skip(1).toList()`
- `.toList` becomes `.stream().toList()` or `new ArrayList<>(iterable)` or `IterableExtensions.toList(iterable)`
- `.toSet` becomes `new HashSet<>(collection)` or `.stream().collect(Collectors.toSet())`
- `.flatten` becomes `.stream().flatMap(Collection::stream).toList()`
- `.sortBy[expr]` becomes `.stream().sorted(Comparator.comparing(x -> expr)).toList()`
- `.sort` becomes `.stream().sorted().toList()` or `Collections.sort(list)` for in-place
- `.join(',')` becomes `String.join(",", collection)` or `.stream().collect(Collectors.joining(","))`
- `.indexed` becomes use `IntStream.range(0, list.size())` with index access, or keep Guava if present
- `.reverse` becomes `Collections.reverse(new ArrayList<>(list))` or use `Lists.reverse(list)` (Guava)
- `.isEmpty` / `.empty` becomes `.isEmpty()`
- `.size` becomes `.size()`
- `.forEach[action]` becomes `.forEach(x -> action)` (Java Iterable.forEach or Stream.forEach)
- `.filterNull` becomes `.stream().filter(Objects::nonNull).toList()`

10.4. **`isNullOrEmpty`**:
- `StringExtensions.isNullOrEmpty(s)` or `s.isNullOrEmpty` becomes `s == null || s.isEmpty()`
- Or use a utility method if the project has one.

10.5. **`toIterable(iterator)`**: `IteratorExtensions.toIterable(resource.getAllContents())` -- keep this call as-is since it is an Xtext utility, OR convert to: `() -> resource.getAllContents()` (creating an Iterable from Iterator).

10.6. **`Iterables.filter(iterable, Class)`**: Keep this Guava call as-is -- it is idiomatic in Eclipse/Xtext projects.

10.7. **Operator overloading on collections**:
- `list += element` becomes `list.add(element)`
- `list += otherList` becomes `list.addAll(otherList)`
- `list -= element` becomes `list.remove(element)`
- `map.get(key)` -- same in Java (Xtend allows `map[key]` syntax which becomes `map.get(key)`)

### 11. PROPERTY ACCESS SYNTAX

11.1. Xtend allows property-style access for getters/setters:
- `obj.name` may mean `obj.getName()` -- convert to explicit getter call
- `obj.name = value` may mean `obj.setName(value)` -- convert to explicit setter call
- `obj.isActive` may mean `obj.isActive()` or `obj.getIsActive()` -- determine from context

11.2. Boolean property access:
- `field.final` means `field.isFinal()`
- `field.static` means `field.isStatic()`

11.3. **IMPORTANT**: Not all dot-access is property access. If the object actually has a public field, keep field access. Determine from the types involved.

### 12. SWITCH EXPRESSIONS

12.1. **Basic switch**:
```xtend
switch(x) {
    case "a": doA()
    case "b": doB()
    default: doDefault()
}
```
becomes Java switch expression or statement depending on context.

12.2. **Switch with type guards**:
```xtend
switch obj {
    CheckCatalog: obj.name
    Category case obj.name !== null: obj.label
    default: "unknown"
}
```
becomes:
```java
if (obj instanceof CheckCatalog checkCatalog) {
    return checkCatalog.getName();
} else if (obj instanceof Category category && category.getName() != null) {
    return category.getLabel();
} else {
    return "unknown";
}
```

12.3. Use Java 21 pattern matching for instanceof where applicable.

### 13. ACTIVE ANNOTATIONS

13.1. **`@Data`**: This generates `equals()`, `hashCode()`, `toString()`, and getters for all fields (which are final). Convert to a Java `record` if the class has no mutable state and no superclass. Otherwise, manually add:
- All-args constructor
- Getter methods for each field
- `equals()`, `hashCode()`, `toString()`
- Or use `@Override` of these methods if the class extends something.

For inner static classes annotated with `@Data` that have `val` fields and no superclass (like this project's `NoBacktrack`, `SemanticPredicate`, `GrammarAnnotations`), prefer Java records:
```java
public record SemanticPredicate(String name, String message, String grammar, List<String> keywords) {}
```

13.2. **`@Accessors`**: Generates getters (and setters for `var` fields).
- `@Accessors boolean foo` generates `getFoo()` and `setFoo(boolean)`.
- `@Accessors(PUBLIC_SETTER) String bar` generates only a public setter.
- `@Accessors(PROTECTED_GETTER) Foo baz` generates only a protected getter.
- Convert by manually writing the getter/setter methods with the specified visibility.

13.3. **`@FinalFieldsConstructor`**: Generates a constructor taking all final fields as parameters. Manually write the constructor.

### 14. SPECIAL XTEND PATTERNS

14.1. **`it` implicit parameter**:
- When a method declares `Type it` as its first parameter (e.g., `def generate(ExportModel it, ...)`), all unqualified method/property calls in the body refer to `it`.
- Convert: Add the parameter with a proper name (e.g., `exportModel`) and qualify all calls:
  - `exports` becomes `exportModel.getExports()`
  - `grammar` becomes `exportModel.getGrammar()` (if it's a property of ExportModel)
  - `extension` becomes `exportModel.isExtension()`

14.2. **`this` vs receiver**: In Xtend, method calls without a receiver may go to `this`, an extension, or `it`. You must determine which based on the type hierarchy. Check:
  1. Is it a method on the current class or its superclass? -> `this.method()` or just `method()`
  2. Is it an extension method from an `@Inject extension` field? -> `field.method(obj)`
  3. Is it a static extension method? -> `ExtClass.method(obj)`
  4. Is it on the `it` implicit receiver? -> `it.method()` using the renamed parameter

14.3. **Multiple return statements**: Xtend methods implicitly return the last expression. You MUST add explicit `return` for ALL non-void return paths.

14.4. **`class` keyword access**: In Xtend, `SomeClass` by itself in certain contexts refers to the class literal. In Java, use `SomeClass.class`.

14.5. **Static method access with `::`**: `ClassName::methodName` or `ClassName::FIELD` becomes `ClassName.methodName()` or `ClassName.FIELD` in Java.

14.6. **Pairs**: `key -> value` becomes `Pair.of(key, value)` or `Map.entry(key, value)` depending on context.

### 15. GUICE DEPENDENCY INJECTION

15.1. **`@Inject` fields**: Keep as-is. These are standard Guice annotations.
- `@Inject ClassName fieldName` becomes `@Inject private ClassName fieldName;`
- Add `private` visibility if not already present.
- If the field was an `extension`, see Rule 8.

15.2. **`@Inject extension`**: See Rule 8.1.

### 16. COMMENTS AND DOCUMENTATION

16.1. **Preserve ALL comments**: Copy Javadoc (`/** */`), block comments (`/* */`), and line comments (`//`) exactly as they appear.

16.2. **Copyright headers**: Keep the exact copyright header from the original file.

16.3. **`@SuppressWarnings("all")`**: The Xtend compiler adds this. Do NOT add it to the converted Java file unless it was explicitly in the Xtend source.

### 17. XTEND LIBRARY REPLACEMENTS

Replace Xtend runtime library calls with Java standard library or Guava equivalents:

| Xtend Library Call | Java Replacement |
|---|---|
| `IterableExtensions.map(iter, fn)` | `iter.stream().map(fn).toList()` |
| `IterableExtensions.filter(iter, fn)` | `iter.stream().filter(fn).toList()` |
| `IterableExtensions.toList(iter)` | `Lists.newArrayList(iter)` or stream |
| `IterableExtensions.toSet(iter)` | `Sets.newHashSet(iter)` or stream |
| `IterableExtensions.head(iter)` | `iter.iterator().next()` with null check, or `Iterables.getFirst(iter, null)` |
| `IterableExtensions.join(iter, sep)` | `String.join(sep, iter)` or `Joiner.on(sep).join(iter)` |
| `IterableExtensions.exists(iter, fn)` | `iter.stream().anyMatch(fn)` |
| `IterableExtensions.forall(iter, fn)` | `iter.stream().allMatch(fn)` |
| `IterableExtensions.findFirst(iter, fn)` | `iter.stream().filter(fn).findFirst().orElse(null)` |
| `IterableExtensions.sortBy(iter, fn)` | `iter.stream().sorted(Comparator.comparing(fn)).toList()` |
| `IterableExtensions.sort(iter)` | `iter.stream().sorted().toList()` |
| `IterableExtensions.isEmpty(iter)` | `!iter.iterator().hasNext()` or `Iterables.isEmpty(iter)` |
| `IterableExtensions.toMap(iter, keyFn, valFn)` | `iter.stream().collect(Collectors.toMap(keyFn, valFn))` |
| `IteratorExtensions.toIterable(iter)` | Keep as utility or wrap: `(Iterable<T>) () -> iter` |
| `StringExtensions.isNullOrEmpty(s)` | `s == null \|\| s.isEmpty()` |
| `CollectionLiterals.newArrayList(...)` | `new ArrayList<>(List.of(...))` or `Lists.newArrayList(...)` |
| `CollectionLiterals.newHashSet(...)` | `new HashSet<>(Set.of(...))` or `Sets.newHashSet(...)` |
| `CollectionLiterals.newHashMap(...)` | `new HashMap<>(Map.of(...))` or `Maps.newHashMap()` |
| `ObjectExtensions.operator_doubleArrow(obj, fn)` | inline (see Rule 5.4) |
| `Functions.Function1` | `java.util.function.Function` |
| `Procedures.Procedure1` | `java.util.function.Consumer` |

**IMPORTANT**: If the existing Java code in the project uses Guava (which this project does extensively), prefer Guava utilities over Java streams for consistency. For example, prefer `Iterables.filter(iter, Type.class)` over `iter.stream().filter(...)`.

### 18. FORMATTING AND STYLE

18.1. Use standard Java formatting:
- 2-space indentation (to match this project's convention)
- Opening brace on same line
- Spaces around operators
- Blank line between methods

18.2. Keep the original method ordering from the Xtend file.

18.3. Use `this.` qualifier only when needed for disambiguation (e.g., with injected fields sharing names with parameters).

### 19. CHECKED EXCEPTIONS

19.1. Xtend does not enforce checked exceptions. When converting to Java, methods that call APIs that throw checked exceptions need proper handling:
- Add `throws` declarations to the method signature, OR
- Wrap in try-catch blocks
- Check the actual APIs being called to determine which checked exceptions need handling
- Example: `CoreException` from Eclipse APIs, `IOException` from I/O operations

---

## CHECKLIST

Before returning the converted file, verify:

- [ ] All `val` converted to `final ExplicitType` (no `var` keyword)
- [ ] All `var` converted to `ExplicitType` (no `var` keyword)
- [ ] All `def` converted to proper Java method with visibility, return type, and `return` statements
- [ ] All `override` converted to `@Override` annotation
- [ ] All `typeof(X)` converted to `X.class`
- [ ] All `===` / `!==` converted to `==` / `!=`
- [ ] All `?.` null-safe navigation converted to null checks
- [ ] All `[...]` lambdas converted to `(...) -> {...}`
- [ ] All `dispatch` methods converted to dispatcher pattern
- [ ] All `extension` methods converted to explicit calls
- [ ] All `static extension` imports converted to static calls
- [ ] All template expressions `'''...«»...'''` converted to StringBuilder
- [ ] All `«IF»/«FOR»/«SEPARATOR»` converted to Java control flow
- [ ] All `#[]` / `#{}` collection literals converted
- [ ] All `=>` operator usages converted
- [ ] All `@Data` / `@Accessors` active annotations expanded
- [ ] All property access (`.name`) converted to getter/setter calls (`.getName()`)
- [ ] All `isNullOrEmpty` and Xtend library calls replaced
- [ ] All `+=` on collections converted to `.add()` / `.addAll()`
- [ ] All `::` static access converted to `.`
- [ ] Semicolons added to all statements
- [ ] Explicit visibility modifiers on all classes, methods, fields
- [ ] All imports updated (Xtend-specific removed, Java ones added)
- [ ] All comments and Javadoc preserved
- [ ] Copyright header preserved exactly
- [ ] No `@SuppressWarnings("all")` added (unless in original source)
- [ ] Checked exceptions properly handled (throws or try-catch)
- [ ] File compiles as valid Java 21

---

## EXAMPLE CONVERSION

### Xtend Input:
```xtend
package com.example

import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import static org.eclipse.xtext.xbase.lib.IteratorExtensions.*
import static extension com.example.NamingExtensions.*

class MyGenerator {
  @Inject extension MyHelper helper

  override void doGenerate(Resource resource) {
    val config = getConfig(resource?.URI)
    for (model : toIterable(resource.allContents).filter(typeof(MyModel))) {
      model.compile
    }
  }

  def compile(MyModel it) '''
    package «packageName»;
    «IF !imports.isNullOrEmpty»

    «FOR imp : imports»
    import «imp»;
    «ENDFOR»
    «ENDIF»

    public class «name» {
    }
  '''
}
```

### Java Output:
```java
package com.example;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

import com.google.common.collect.Iterables;

public class MyGenerator {

  @Inject
  private MyHelper helper;

  @Override
  public void doGenerate(final Resource resource) {
    final URI uri = resource != null ? resource.getURI() : null;
    final MyConfig config = getConfig(uri);
    for (final MyModel model : Iterables.filter(IteratorExtensions.toIterable(resource.getAllContents()), MyModel.class)) {
      compile(model);
    }
  }

  public CharSequence compile(final MyModel model) {
    final StringBuilder builder = new StringBuilder();
    builder.append("package ").append(model.getPackageName()).append(";\n");
    if (!(model.getImports() == null || model.getImports().isEmpty())) {
      builder.append("\n");
      for (final String imp : model.getImports()) {
        builder.append("import ").append(imp).append(";\n");
      }
    }
    builder.append("\n");
    builder.append("public class ").append(NamingExtensions.getName(model)).append(" {\n");
    builder.append("}\n");
    return builder;
  }
}
```

---

Now convert the following Xtend file to idiomatic Java following ALL the rules above:
