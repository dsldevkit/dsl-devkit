# Extension methods

Xtend's "extension" mechanism dispatches a method call on the receiver to a separate type. Java has no equivalent — every extension call site is rewritten to an explicit static or instance call.

**This is the #2 challenge after templates (81% of files use extensions).**

## 6.1 `@Inject extension ClassName fieldName`

Convert the field, then rewrite every call site:

- Field: `@Inject extension MyHelper helper` → `@Inject private MyHelper helper;`
- Call site: `obj.extensionMethod(args)` → `helper.extensionMethod(obj, args)` (the implicit receiver `obj` moves to the first parameter).
- If the extension field had no name (`@Inject extension CheckGeneratorNaming`), invent one following the convention: camelCase class name starting lowercase (`checkGeneratorNaming`).

Tip: The `xtend-gen/` output shows exactly how the Xtend compiler resolved every extension call — use it as the reference.

## 6.2 `extension` method parameters

A method with `def foo(extension IFormattableDocument document)` lets the body call `document`'s methods without a receiver.

- Drop the `extension` keyword from the parameter.
- At call sites within the method body, calls that dispatched to the extension parameter become explicit calls on the parameter:
  - `prepend(checkcatalog)[noSpace]` becomes `document.prepend(checkcatalog, (IHiddenRegionFormatter it) -> it.noSpace())`

## 6.3 `static extension` imports

```xtend
import static extension com.foo.Bar.*
```
becomes
```java
import static com.foo.Bar.*;
```

At every call site: `obj.staticExtensionMethod(args)` becomes `Bar.staticExtensionMethod(obj, args)`.

Example:
```xtend
import static extension org.eclipse.xtext.GrammarUtil.*
grammar.simpleName
```
becomes
```java
GrammarUtil.getSimpleName(grammar)
```

## 6.4 `extension` keyword on `val`/`var`

```xtend
val extension naming = contentAssistNaming
```
- Drop the `extension` keyword. Keep as a plain local variable.
- Rewrite call sites within scope to explicit calls on the variable.
