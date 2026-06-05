---
name: asmd-scope-ext-migration
description: >
  Adapts ASMD downstream source files (.scope and .export) to the Xbase-rebased DDK Expression grammar and
  removes the legacy classic-Xtend extension files (.ext). Use this skill whenever the user wants to: migrate
  ASMD .scope/.export sources after the dsl-devkit scope/export generator was ported to Xbase, delete the .ext
  files, rewire `extension a::b::C` declarations to Java helper classes, fix unresolved extension-function
  calls, convert `factory` expressions to the `Type.method(...)` form, or regenerate a DSL's scope/export
  providers in ASMD. Triggers on phrases like "remove the ext files", "migrate the scope files", "adapt the
  export files", "fix the extension calls in .scope", "port ASMD scoping off .ext". This is the DOWNSTREAM
  counterpart of the `expression-dsl-to-xbase` skill (which does the dsl-devkit generator side).
globs:
  - "**/*.scope"
  - "**/*.export"
  - "**/*.ext"
---

# ASMD `.scope` / `.export` Adaptation + `.ext` Removal Skill

> Companion to **`expression-dsl-to-xbase`** (the dsl-devkit generator-side migration). That skill makes the
> Scope/Export *generators* Xbase-based and drops `org.eclipse.xtend`. **This** skill fixes the ASMD *source*
> files so they compile against the new generator and removes the `.ext` files. Run the generator-side first
> (or consume a build of the new dsl-devkit scope/export plugins) before applying this skill.
>
> For ASMD coding standards, build commands, and module layout, see `AGENTS.md` at the ASMD repo root.

## The one fact that determines everything

The DDK **Expression grammar change was minimal**: only the base grammar moved
`with org.eclipse.xtext.common.Terminals` → `with org.eclipse.xtext.xbase.Xbase`. **The embedded expression
syntax is unchanged.** So the bodies of scope rules, `naming`, `find(...)`, `scopeof(...)`, the collection
operators (`select`/`collect`/`selectFirst`/`reject`/`exists`/`notExists`/`sortBy`/`forAll`), `typeSelect`,
the `->` chain, the `?:` / `if…then…else` conditionals, casts `(Type)x`, and `::`-qualified type names **all
stay exactly the same**.

**What changes is ONLY the extension mechanism**: the legacy `.ext` files (classic-Xtend `JAVA` delegation
stubs) are gone, and `extension a::b::C` now binds directly to a **Java class with `public static` methods**.

> ⚠️ Do **not** rewrite scope-rule bodies, naming sections, or collection operators. If you find yourself
> changing `select`/`typeSelect`/`find`/`scopeof` syntax, stop — that is not part of this migration.

## What a `.ext` file actually is

Every ASMD `.ext` file is a list of **pure delegation stubs** to `public static` Java methods:

```text
// AvqScriptScoping.ext  (file at .../avqscript/AvqScriptScoping.ext)
import avqscript;
import ecore;

ecore::EObject innermostDeclaringContainer(EObject this) :
  JAVA com.avaloq.tools.dsl.avqscript.scoping.AvqScriptScoping.innermostDeclaringContainer(org.eclipse.emf.ecore.EObject);

List[avqscript::Declaration] getTypeDeclarations(avqscript::ScriptPackage this) :
  JAVA com.avaloq.tools.dsl.avqscript.scoping.AvqScriptScoping.getTypeDeclarations(com.avaloq.tools.dsl.avqscript.avqscript.ScriptPackage);
```

So the `.ext` is an **indirection layer** between the source `extension X::Y::N` declaration and the real Java
helper class named in each `JAVA …` target. **The Java helper class already exists and already has the
`public static` methods** — the `.ext` adds nothing but the (now obsolete) classic-Xtend binding.

Note the package shift: the `.ext` is at `…dsl.avqscript.AvqScriptScoping` but its `JAVA` target lives in
`…dsl.avqscript.scoping.AvqScriptScoping` (a `scoping`/`export` sub-package). The repoint must use the **Java
class** package.

## How `extension` resolves in the new (Xbase) world

`extension a::b::C` is resolved by the translator as the **Java class** `a.b.C` (`findDeclaredType`). A call
`name(args)` or `target.name(args)` links to the **first `public static` `JvmOperation`** on that class where:

- `simpleName == name` (the source call name), and
- `parameters.size == argumentCount`, where **the receiver/target counts as the first argument**
  (`x.foo(a)` → 2 args; `foo(a)` → 1 arg; `foo()` → 0 args).

Consequences (the four mismatch classes to reconcile — see below): the Java method name must equal the source
call name; the first parameter must accept the receiver's type; and receiver-dropped stubs change the arg count.

## Migration procedure (per DSL `.core` module)

### 1. Inventory

For the DSL, list its `.scope` and `.export` files and every `extension …` line they declare, and the matching
`*.ext` files. A typical scope header:

```text
extension com::avaloq::tools::dsl::avqscript::AvqScriptScoping        // → AvqScriptScoping.ext
extension com::avaloq::tools::dsl::codetabdata::CodeTabDataScoping
extension com::avaloq::tools::dsl::common::^scoping::MetaModelScoping // foundation .ext
```

(`^scoping` just escapes the `scoping` keyword segment; the Java package segment is plain `scoping`.)

### 2. Find each `.ext`'s real Java helper class

Open the `.ext` and read the `JAVA <fqcn>.<method>(…)` targets. They almost always point at a single helper
class in a `.scoping` / `.export` sub-package, e.g. `com.avaloq.tools.dsl.avqscript.scoping.AvqScriptScoping`.

### 3. Repoint the `extension` declaration to the Java class

Change the `::`-qualified name from the `.ext` path to the **Java helper class**:

```diff
-extension com::avaloq::tools::dsl::avqscript::AvqScriptScoping
+extension com::avaloq::tools::dsl::avqscript::scoping::AvqScriptScoping
```

If one `.ext` delegated to **several** Java classes, add one `extension` line per class.

### 4. Delete the `.ext` file

Remove `*.ext` once nothing references it. Also remove any build/MWE2 wiring that registered it (older
`*.mwe2` / generator setup that listed extension files), and drop `org.eclipse.xtend*` /
`org.eclipse.internal.xtend` entries from the DSL's `META-INF/MANIFEST.MF` if present.

### 5. Reconcile the four mismatch classes the `.ext` was hiding

The `.ext` could silently rename/retype/re-arity a call. With it gone, the source call must match the Java
static method exactly. For each extension call that no longer resolves:

| Mismatch | Symptom in the `.ext` | Fix (prefer call-site; else add a `public static` overload to the helper) |
|---|---|---|
| **Name** | ext fn name ≠ `JAVA` method name | rename the call site to the Java method name, or add an alias `public static` method |
| **Receiver/param type** | `getExtendableScriptPackage(EObject this)` → `JAVA …(SuperCall)` | the Java first param must accept the call-site receiver type; add an overload if the actual receiver is broader |
| **Dropped receiver** | `getQualifiedNameFunction(EObject this)` → `JAVA …getQualifiedNameFunction()` (0-arg) | the new resolver counts the target as an arg, so `x.getQualifiedNameFunction()` (1 arg) won't match the 0-arg method — **drop the receiver**: `getQualifiedNameFunction()`; or add a 1-arg overload that ignores its arg |
| **No-receiver helpers** | `getPredefinedTypes()` / `getNumberType()` / `lastSegment()` (already 0-arg) | already written receiver-free in source (`context * = getPredefinedTypes();`) — these just need the repoint, no call-site change |

> **Do NOT add a Java helper for EMF-native getters.** A call like `this.getName()` that resolves to a real
> `EStructuralFeature` getter links through the normal getter-navigation path and needs no `extension`. Only
> functions that were *genuinely defined in the `.ext`* require the Java helper class. Check the model `.ecore`
> before assuming a call is an extension.

### 6. Convert `factory` expressions to the `Type.method(...)` form

`factory` now requires the **Java factory class named explicitly**:

```diff
-factory makeFooScope(args)
+factory com::avaloq::tools::dsl::foo::scoping::FooScoping.makeFooScope(args)
```

The generator emits `<FQN>.<method>(scope, ctx, <typeOrRef>, originalResource, <args>)`. The factory method
must be a `public static` method with that leading framework-parameter signature. Bare `factory foo()` (no
type) no longer resolves.

### 7. Ensure helper visibility

The repointed Java helper class must be **on the DSL `.core` classpath** (its bundle exported and required in
`META-INF/MANIFEST.MF`) and its methods **`public static`**. Foundation helpers
(`com.avaloq.tools.foundation.xtext.core.…scoping.MetaModelScoping`, etc.) get the identical treatment.

### 8. Regenerate + build + iterate

Regenerate the DSL's scope/export providers via its MWE2 workflow (the DDK `GenerateScope`/`GenerateExport`
flow), then build the `.core` module and fix any remaining unresolved extension calls:

```bash
mvn clean install -pl :com.avaloq.tools.dsl.<dsl>.core -am
```

Re-run after each batch of fixes. The new dsl-devkit scope/export plugins must be installed/available first.

## Worked example (AvqScript)

```diff
 scoping com.avaloq.tools.dsl.avqscript.AvqScript with com.avaloq.tools.dsl.avqbase.AvqBase
 …
-extension com::avaloq::tools::dsl::avqscript::AvqScriptScoping        // native helper operations
-extension com::avaloq::tools::dsl::codetabdata::CodeTabDataScoping    // global code table access
-extension com::avaloq::tools::dsl::common::^scoping::MetaModelScoping // pre-defined types and operations
+extension com::avaloq::tools::dsl::avqscript::scoping::AvqScriptScoping
+extension com::avaloq::tools::dsl::codetabdata::scoping::CodeTabDataScoping
+extension com::avaloq::tools::foundation::xtext::core::metamodel::scoping::MetaModelScoping
```

- `this.getTypeDeclarations()`, `this.innermostDeclaringContainer()`, `this.isClientAll()` resolve to the
  `public static` methods on `…avqscript.scoping.AvqScriptScoping` — repoint only.
- `getPredefinedTypes()` (already receiver-free) resolves to
  `…foundation.xtext.core.metamodel.scoping.MetaModelScoping.getPredefinedTypes()` — repoint only.
- `getQualifiedNameFunction()` was a receiver-dropped stub → keep it written **without** a receiver.
- Delete `AvqScriptScoping.ext`, `AvqScriptExport.ext`, `CodeTabDataScoping.ext`, foundation `MetaModelScoping.ext`.

## Scope & constraints

- **ASMD only.** This is the downstream rewrite the dsl-devkit migration deliberately left to ASMD.
- **Do not change expression/scope-rule syntax** — only the extension wiring, `.ext` removal, and `factory` form.
- Keep the EPL/Avaloq copyright headers on any Java helper you add or modify (see ASMD `AGENTS.md`).
- Migrate and verify **one DSL at a time**; build its `.core` module green before moving on.
- If an `.ext` ever contained real Xtend expression logic (not a plain `JAVA` stub), that logic must be **ported
  into a `public static` method** on the helper class — the source can only bind to compiled Java now. (All
  surveyed ASMD `.ext` files are pure `JAVA` stubs, but verify each before deleting.)
