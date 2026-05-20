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
