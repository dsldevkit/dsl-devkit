# Variable declarations

Always explicit types. Never `var`. See [`rules/00-decisions.md`](./00-decisions.md).

## 2.1 `val` — final local variable

```xtend
val catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class)
```
becomes
```java
final CheckCatalog catalog = EcoreUtil2.getContainerOfType(context, CheckCatalog.class);
```

## 2.2 `var` — mutable local variable

```xtend
var skip = instance - 1
```
becomes
```java
int skip = instance - 1;
```

## 2.3 `val` field (class-level final)

- `val String FOO = "bar"` → `private final String FOO = "bar";`
- `val static Logger LOGGER = ...` → `private static final Logger LOGGER = ...;`

Always add explicit visibility (`private` unless a wider scope is genuinely needed).

## 2.4 `var` field (class-level mutable)

- `var String name` → `private String name;`

Always add explicit visibility.

## 2.5 No leading underscore on non-dispatch fields

Non-dispatch private fields that got `_fieldName` from the converter: rename to `fieldName`.
The `_` prefix is reserved for dispatch methods only (see [`rules/09-misc-syntax.md`](./09-misc-syntax.md)).
