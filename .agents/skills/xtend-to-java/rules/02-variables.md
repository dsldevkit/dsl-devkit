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

## 2.5 Field member names must match `[a-z][a-zA-Z0-9]*`

The Checkstyle `MemberName` rule (default pattern `[a-z][a-zA-Z0-9]*`) is enforced on all
non-constant instance and static-non-final fields. Every field name **must**:

- Start with a lowercase letter.
- Contain only ASCII letters and digits — **no underscores anywhere**.

### Leading underscore from Xtend extension fields

Xtend generates `_fieldName` for extension-injected fields. Strip the prefix:

```java
// Bad — Xtend-generated name
private MyHelper _myHelper;

// Good
private MyHelper myHelper;
```

### Underscores inside the name — convert to camelCase

Any underscore inside a name (rare in Xtend source but can appear when porting
older code) must be removed by camelCasing the following word:

| Original | Renamed |
|----------|---------|
| `my_helper` | `myHelper` |
| `some_long_name` | `someLongName` |
| `_some_field` | `someField` |

### Scope of this rule

Applies to **all non-constant member fields** — instance fields and non-final static
fields. It does **not** apply to:

- `static final` constants, which are validated by Checkstyle `ConstantName`
  (pattern `[A-Z][A-Z0-9]*(_[A-Z0-9]+)*`, e.g. `LOGGER`, `DEFAULT_SIZE`).
- Dispatch method parameters / local variables in dispatch methods (the `_` prefix
  on dispatch methods themselves is intentional — see
  [`rules/09-misc-syntax.md`](./09-misc-syntax.md)).
