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
| `acceptor.accept(cls, [ ... ])` | `acceptor.<JvmGenericType>accept(cls, initializer)` where `initializer` is a `Procedure1<JvmGenericType>` (see `FormatJvmModelInferrer._infer`) |
| `members += x` / `superTypes += x` / `annotations += x` | `it.getMembers().add(x)` / … — **only when `x` is provably non-null**; both `JvmTypesBuilder.operator_add` overloads skip nulls (single element and collection), so see §10.4 before translating any `+=` |
| `x.toMethod(name, type) [ ... ]` | `jvmTypesBuilder.toMethod(x, name, type, initializer)` with a `Procedure1<JvmOperation>` (see `FormatJvmModelInferrer.inferGetGrammarAccess`) |
| `x.toField(name, type) [ ... ]` / `x.toParameter(name, type)` | `jvmTypesBuilder.toField(x, name, type, initializer)` / `jvmTypesBuilder.toParameter(x, name, type)` |
| `typeRef(T)` / `typeRef(name)` | `_typeReferenceBuilder.typeRef(...)` — the protected field inherited from `AbstractModelInferrer`; for lookups needing a context object use `typeReferences.getTypeForName(name, context)` |
| `documentation = '''...'''` | `jvmTypesBuilder.setDocumentation(it, "...".formatted(...))` (see `FormatJvmModelInferrer.inferClass`) |
| `static = true` / `visibility = PROTECTED` / `abstract = true` | `it.setStatic(true)` / `method.setVisibility(JvmVisibility.PROTECTED)` / `it.setAbstract(true)` |
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
  (see `FormatJvmModelInferrer.inferGetGrammarAccess`)
- `body = '''template'''` (template form) → the Xtend compiler emits the
  `StringConcatenationClient` overload of `setBody`. Either keep that overload (check
  `xtend-gen/`) or convert to the `Procedure1<ITreeAppendable>` form with the template text
  built per [`rules/04-templates.md`](./04-templates.md) — the appended STRING must stay
  byte-identical either way.

## 10.3 Gate notes specific to inferrers

- The inference closures are long by design; bracket the class with
  `// CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole`
  (see the class-level suppression in `FormatJvmModelInferrer`).
- Emitted Java source fragments are repeated literals — `// CHECKSTYLE:CONSTANTS-OFF` applies
  (see the class-level suppression in `FormatJvmModelInferrer`).
- `members += list.map(...).flatten.filterNull` chains: see
  [`references/xtend-library-replacements.md`](../references/xtend-library-replacements.md)
  for `flatten`/`filterNull` stream equivalents; the result feeds the add — but read §10.4 first
  for the null-skip requirement, which is the most dangerous inferrer-migration trap.

## 10.4 ⚠ `operator_add` (`+=`) SKIPS nulls — plain `add`/`addAll` does NOT

**This is the highest-risk inferrer defect: it passes every static gate (PMD/Checkstyle/SpotBugs) and
every test that does not happen to feed a null — and then fails at runtime the moment one does.**
The failure is fast and loud, not silent: JVM model containment lists (`getMembers()` etc.) are EMF
`EObjectEList`s with `canContainNull() == false`, so a bare `.add(null)`/`.addAll(...)` throws
`IllegalArgumentException("The 'no null' constraint is violated")` **at the add call**. Xtend's `+=`
never produces that null add in the first place — that is the behaviour a faithful migration must keep.

`JvmTypesBuilder` provides **two** `operator_add` overloads, and **both skip nulls** (they also no-op on
a null list): `operator_add(EList, T)` is `if (list != null && element != null) list.add(element)`,
and the `Iterable` overload delegates to it per element. So the trap covers the single-element form too:
Xtend `members += toField(...)` silently skips a null factory result, while the doc-obvious
`it.getMembers().add(toField(...))` throws on it.

And the factories DO return null. In Xtext 2.43.0, named builders such as `toClass`, `toInterface`,
`toAnnotationType`, `toEnumerationType`, `toField`, `toMethod`, `toParameter`, and
`toEnumerationLiteral` guard their **source element and name**; `toConstructor` guards its source element;
and `toGetter` / `toSetter` guard the source element plus their accessor/field names. A null **type**
argument does not by itself trigger a null return in the field/method/parameter/accessor builders. Check the
exact overload used rather than treating this list as a substitute for source inspection. Any local helper
with a `return null` fall-through (a `switch`/`if` that doesn't match) is a trigger too.

So the faithful Java of any `+=` whose right-hand side can be null is a guarded add:

```java
// WRONG — throws IllegalArgumentException("The 'no null' constraint is violated") at the add
// the first time createConstant returns null (value-less constant):
for (final Constant c : constants) {
  it.getMembers().add(createConstant(format, c));
}

// RIGHT — reproduce operator_add's null-skip (either form):
for (final Constant c : constants) {
  final JvmMember member = createConstant(format, c);
  if (member != null) {
    it.getMembers().add(member);
  }
}
// or, matching the Xtend chain shape with the JDK stream equivalents
// (per references/xtend-library-replacements.md — no xbase.lib in migrated Java):
it.getMembers().addAll(constants.stream()
    .map(c -> createConstant(format, c))
    .filter(Objects::nonNull)
    .toList());
```

**Checklist for every `+=` site in a migrated inferrer — single element or collection:** can the producer
return null (nullable source element or name, or a `return null` branch)? If yes, there MUST be a null
guard / `Objects::nonNull` filter. A bare `add`/`addAll` over a null-capable producer is a faithfulness
regression.

> Real shipped example: `FormatJvmModelInferrer.inferConstants` used a bare add for
> `members += allConstants.map[createConstant]`, although `createConstant` returns null for a value-less
> constant. The guard now in that method and its regression test are the canonical fix; the defect escaped
> the gates because no earlier test supplied the null-producing input.

## 10.5 Verification

An inferrer is a generator: its OUTPUT (the inferred JVM model, and through it the generated
Java) is the ground truth. Byte-verify emitted body/documentation strings against `xtend-gen/`
exactly as for any template (rules 04/§4.8); structural calls (`toClass`/`toMethod`/setters)
must match the `xtend-gen/` call sequence one-for-one.
