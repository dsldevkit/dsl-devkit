# JVM model inferrer (JvmTypesBuilder)

Xtend model inferrers (`class X extends AbstractModelInferrer` with `def dispatch infer(...)`)
build Java types through the `JvmTypesBuilder` extension DSL. The migrated
`FormatJvmModelInferrer.java` (`com.avaloq.tools.ddk.xtext.format/src/com/avaloq/tools/ddk/xtext/format/jvmmodel/`)
is the canonical worked example — read it in full before migrating another inferrer.

## 10.1 Core mapping

| Xtend | Java |
|---|---|
| `@Inject extension JvmTypesBuilder` | `@Inject private JvmTypesBuilder jvmTypesBuilder;` — every extension call becomes explicit (`jvmTypesBuilder.toClass(...)`) |
| `def dispatch infer(X x, IJvmDeclaredTypeAcceptor acceptor, boolean preIndexingPhase)` | `_infer(final X x, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase)` + the dispatcher pattern ([`rules/09-misc-syntax.md`](./09-misc-syntax.md) §9.7) |
| `x.toClass(name)` | `jvmTypesBuilder.toClass(x, name)` |
| `acceptor.accept(cls, [ ... ])` | `acceptor.<JvmGenericType>accept(cls, initializer)` where `initializer` is a `Procedure1<JvmGenericType>` (see `FormatJvmModelInferrer.java:182-192`) |
| `members += x` / `superTypes += x` / `annotations += x` | `it.getMembers().add(x)` / `it.getSuperTypes().add(x)` / `it.getAnnotations().add(x)` |
| `x.toMethod(name, type) [ ... ]` | `jvmTypesBuilder.toMethod(x, name, type, initializer)` with a `Procedure1<JvmOperation>` (`:223-235`) |
| `x.toField(name, type) [ ... ]` / `x.toParameter(name, type)` | `jvmTypesBuilder.toField(x, name, type, initializer)` / `jvmTypesBuilder.toParameter(x, name, type)` (`:245`) |
| `typeRef(T)` / `typeRef(name)` | `_typeReferenceBuilder.typeRef(...)` — the protected field inherited from `AbstractModelInferrer` (`:202-204`); for lookups needing a context object use `typeReferences.getTypeForName(name, context)` (`:235`) |
| `documentation = '''...'''` | `jvmTypesBuilder.setDocumentation(it, "...".formatted(...))` (`:198`) |
| `static = true` / `visibility = PROTECTED` / `abstract = true` | `it.setStatic(true)` / `method.setVisibility(JvmVisibility.PROTECTED)` / `it.setAbstract(true)` (`:207,224`) |
| `initializer = expr` (on a field) | set inside the field's initializer `Procedure1` via the corresponding setter/`jvmTypesBuilder` call — read `xtend-gen/` for the exact form |

## 10.2 Method bodies

Xtend assigns bodies two ways; both become `jvmTypesBuilder.setBody(method, ...)`:

- `body = [append('''...''')]` (procedure form) → `Procedure1<ITreeAppendable>` that appends —
  the form the migrated file uses throughout:
  ```java
  final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
    appendable.append("return (%sGrammarAccess) super.getGrammarAccess();".formatted(...));
  };
  jvmTypesBuilder.setBody(method, body);
  ```
  (`FormatJvmModelInferrer.java:229-232`)
- `body = '''template'''` (template form) → the Xtend compiler emits the
  `StringConcatenationClient` overload of `setBody`. Either keep that overload (check
  `xtend-gen/`) or convert to the `Procedure1<ITreeAppendable>` form with the template text
  built per [`rules/04-templates.md`](./04-templates.md) — the appended STRING must stay
  byte-identical either way.

## 10.3 Gate notes specific to inferrers

- The inference closures are long by design; bracket the class with
  `// CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole`
  (see `FormatJvmModelInferrer.java:114`).
- Emitted Java source fragments are repeated literals — `// CHECKSTYLE:CONSTANTS-OFF` applies
  (same file, `:113`).
- `members += list.map(...).flatten.filterNull` chains: see
  [`references/xtend-library-replacements.md`](../references/xtend-library-replacements.md)
  for `flatten`/`filterNull` stream equivalents; the result feeds `getMembers().addAll(...)`.

## 10.4 Verification

An inferrer is a generator: its OUTPUT (the inferred JVM model, and through it the generated
Java) is the ground truth. Byte-verify emitted body/documentation strings against `xtend-gen/`
exactly as for any template (rules 04/§4.8); structural calls (`toClass`/`toMethod`/setters)
must match the `xtend-gen/` call sequence one-for-one.
