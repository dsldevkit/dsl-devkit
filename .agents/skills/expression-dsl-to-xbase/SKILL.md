---
name: expression-dsl-to-xbase
description: >
  Migrates an Xtext DSL that embeds the custom DDK "Expression" language (com.avaloq.tools.ddk.xtext.expression)
  off the legacy classic-Xtend (Xpand/Xtend1) expression compiler — CodeGenerationX / CompilationContext /
  ScopingGeneratorUtil execution context — and onto Xbase + a JvmModelInferrer, then removes the
  org.eclipse.xtend and org.eclipse.xtend.typesystem.emf Require-Bundle dependencies from the plugin.
  Use this skill whenever the user wants to: make a generator plugin "Xtend-free" / drop org.eclipse.xtend,
  convert a *Generator (IGenerator2) to extend JvmModelGenerator, replace CompilationContext / CodeGenerationX,
  port the scope or export generator to Xbase, or repeat the scope-plugin migration on another consumer of the
  Expression DSL. Triggers on phrases like "remove the xtend dependency", "make it use Xbase", "convert the
  generator to a JvmModelInferrer", "drop CompilationContext", "port export generator like we did scope".
globs:
  - "**/com.avaloq.tools.ddk.xtext.scope/**"
  - "**/com.avaloq.tools.ddk.xtext.export/**"
  - "**/com.avaloq.tools.ddk.xtext.expression/**"
  - "**/*.xtend"
  - "**/*.xtext"
  - "**/MANIFEST.MF"
---

# Expression-DSL → Xbase Migration Skill

> For general coding standards (copyright headers, Javadoc, import rules, naming), see `AGENTS.md` at the
> repository root. The EPL header used by these files is the 10-line
> `Copyright (c) 2016 Avaloq Group AG and others.` … `Avaloq Group AG - initial API and implementation` block.

## What this skill is for

The DDK ships a custom embedded expression language (`com.avaloq.tools.ddk.xtext.expression`, the **Expression
DSL** — a port of the old Xtend1/Xpand expression syntax). Grammars such as **Scope** (`Scope with
com.avaloq.tools.ddk.xtext.expression.Expression`) and **Export** inherit its rules. Their generators historically
compiled embedded expressions to Java **strings** through `expression.generator.CodeGenerationX` +
`CompilationContext`, whose type system is the **classic Xtend** runtime
(`org.eclipse.xtend.expression.*`, `org.eclipse.xtend.typesystem.emf.*`). That is the *only* reason those plugins
`Require-Bundle: org.eclipse.xtend, org.eclipse.xtend.typesystem.emf`.

This skill removes that legacy coupling by:

1. Making the embedded expression bodies **Xbase** (`XExpression`) so they can be compiled by the standard
   `XbaseCompiler` via a `JvmModelInferrer`, and
2. Providing an **Xtend-free Java-string fallback compiler** for the handful of Expression-DSL constructs that
   have no clean Xbase representation (string `+` concatenation, the arithmetic/relational operator overloads).

## Binding decisions (carried over from the scope migration — confirm they still hold)

These were agreed with the user during the scope plugin migration. Re-confirm before applying to a new plugin:

- **dsl-devkit ONLY.** Do **not** migrate the downstream `.scope` / `.export` / `.ext` source files — the
  user rewrites those (and regenerates) separately. The migration only has to keep *dsl-devkit* green.
- **Output need NOT be byte-identical.** The committed generated `*ScopeProvider.java` etc. may change shape
  (they become inferred JVM types emitted by `JvmModelGenerator`). Generator/golden tests are regenerated.
- **`.ext` (JAVA extension) support is dropped.** Scope/export sources no longer reference Xtend extension
  files. Remove the `.ext`-reading validation and the `isJavaExtensionCall` / `isExtension` /
  `getCalledJavaMethod` generator branches. Do **not** build an `.ext` reader.
- **Factory expressions become direct Xbase static calls.** `factory` must be written `Type.method(args)` in
  source (the FQN type is named explicitly); the generator resolves the type identifier to a
  `JvmDeclaredType.qualifiedName` and emits `<FQN>.<method>(scope, ctx, <typeOrRef>, originalResource, <args>)`.
  Bare `factory foo()` (no type) is no longer supported.
- **No runtime validation in the loop.** dsl-devkit has **no `.scope`/`.export` input files**, so the
  inferrer never actually runs during `mvn verify` — only **compilation** of the generators is checked. Runtime
  and generated-Java *import* fidelity are therefore **unverifiable** inside dsl-devkit. Treat "build is green"
  as "compiles", not "correct". The user validates real output by regenerating in Eclipse.

> ⚠️ **"Green but broken" trap.** Because the inferrer never runs in CI, a faithful port still needs careful
> by-hand comparison against the legacy generator output. Always diff your emitted-string logic against the
> legacy `CodeGenerationX` / `ExpressionExtensionsX` branch-for-branch.

## Target architecture (what "done" looks like)

Mirror the scope plugin's final shape:

| Concern | Legacy | New |
|---|---|---|
| Generator entry point | `*Generator` implements `IGenerator2` | `*JvmModelInferrer extends AbstractModelInferrer`; `*Generator` extends Xbase `JvmModelGenerator` (wired via the `.mwe2` fragment) |
| Provider classes | hand-templated `.java` strings | **inferred JVM types** (`toClass`/`toMethod`/`toField`); bodies attached as strings via `body = [append(text)]` |
| Embedded expression → Xbase | n/a | `*ExpressionTranslator` builds `XExpression` trees where the construct maps cleanly to Xbase + `xbase.lib` operators |
| Expression → Java string (fallback) | `CodeGenerationX` + `CompilationContext` (classic Xtend) | `*ExpressionCompiler` (Xtend-2, **no** classic-Xtend deps) — ports the string output of `CodeGenerationX`/`ExpressionExtensionsX` *exactly*, minus the `.ext` branches |
| Type/variable/`this` resolution | `CompilationContext` over classic-Xtend `TypeSystem` | `*TranslationContext` (variables, implicit param, implicit-var name) + the translator's JVM type resolution |
| Model-type name → Java class | `EmfRegistryMetaModel` over imported EPackages | `*ModelTypeResolver` (plain Java/EMF) over the model's imported `EPackage`s → `GenModelUtilX.instanceClassName` |
| Legacy execution context | `ScopingGeneratorUtil.getCompilationContext` + inner `ExecutionContextImpl`/`Resource` | **deleted** |
| Plugin deps | `org.eclipse.xtend`, `org.eclipse.xtend.typesystem.emf` | **removed from MANIFEST** |

### Why a *translator* AND a *string compiler*

String `+` concatenation is extremely common in these sources and has **no `operator_plus` in `xbase.lib`**
(Xbase special-cases `String +` inside its own compiler), so it **cannot** be pre-linked as an `XExpression`
tree. The relational-operator overload selection is similarly hard to do blind. So: translate to Xbase what
maps cleanly; fall back to the ported string compiler for `+`, unary/binary arithmetic, and the relational
operators. The fallback emits Java text directly (`a + b + c`, `(x) ? y : z`, `Iterables.filter(...)`, …).

### Model-type resolution — the critical correctness point

In real `.scope`/`.export` sources the type names in `(Cast)x`, `x.typeSelect(T)` and `T.isInstance(x)` are
**EMF model types** (e.g. `FormDef`, `ILogicalTable`, `intfdef::AlternatingGroup`), **not** dotted Java FQNs.
So `javaType(...)` MUST resolve them through the model's **imported EPackages** →
`GenModelUtilX.instanceClassName(classifier)`, exactly as the legacy `ScopingGeneratorUtil.registerMetaModels`
did. Resolving via `findDeclaredType` against the classpath would **fail** on `Entity`/`FormDef`/etc.

- Build the EPackage list from
  `EObjectUtil.getScopeProviderByEObject(model).getScope(model, IMPORT__PACKAGE).getAllElements()` resolved to
  `EPackage[]`.
- Handle import **aliases**: `alias::Type` → `model.getImports()` match on `imp.getName()` →
  `imp.getPackage().getEClassifier(Type)`.
- A single-segment name searches every imported package's `getEClassifier(name)`.

## Step sequence (add new files alongside old, wire last, then delete)

Keep the build green at every increment. Build only the two plugins with `-am` (see recipe below).

1. **Survey** the target plugin: list every use of `CodeGenerationX`, `CompilationContext`,
   `ScopingGeneratorUtil`, and the `org.eclipse.xtend*` imports. Note each generator fallback site.
2. **Translator** — `*ExpressionTranslator.xtend`: dispatch over the Expression AST → `XExpression`. Cover
   literals, list/if, variable & `this` feature calls, boolean/equality/relational ops (link to `xbase.lib`),
   getter navigations, extension calls (`extension a::b::C` static method), casts/`typeSelect`/`isInstance`,
   receiver/implicit method calls. Return `null` for anything not yet handled.
3. **Translation context** — `*TranslationContext.xtend`: variables map, `implicitVariable`
   (`JvmFormalParameter`, drives type resolution) **and** `implicitVariableName` (`String`, the emitted `this`
   binding, e.g. `obj`/`ctx`/`UNEXPECTED_THIS`), `modelTypeResolver`. Add a
   `newCompilationContext(implicitVarName, implicitType, extraVars(name→FQN), sourceElement)` factory on the
   translator to replace the legacy `CompilationContext.clone(...)`/`cloneWithVariable(...)` calls.
4. **Model-type resolver** — `*ModelTypeResolver.java`: as above. `static forElement(EObject)` →
   `EcoreUtil2.getContainerOfType(element, <Model>.class)`.
5. **String compiler** — `*ExpressionCompiler.xtend`: port `CodeGenerationX` + `ExpressionExtensionsX` string
   output **exactly**, dropping the `.ext` branches. Inject `GenModelUtilX` as a **plain field** (not
   `extension`) and call `genModelUtil.instanceClassName(classifier)` explicitly. Resolve `javaType` via the
   model-type resolver first, else `ctx.resolveDslType` (strip a leading `java.lang.` for un-nested names).
   **Preserve the legacy `FeatureCall` 3-way tail**: `isSimpleFeatureCall`→getter, `isSimpleNavigation`→
   `notCompilable()`, else→getter (do NOT collapse these — the middle branch must stay `notCompilable()`).
6. **Wire the generators**: replace `@Inject extension CodeGenerationX` + `CompilationContext` field with
   `@Inject *ExpressionCompiler compiler` + `@Inject *ExpressionTranslator translator`; rewrite every fallback
   `compilationContext.clone(...).javaExpression(...)` to
   `compiler.javaExpression(expr, translator.newCompilationContext(...))`. Drop the `CompilationContext`
   `configure(...)` parameter.
7. **Inferrer + grammar**: replace the `IGenerator2` `*Generator` with `*JvmModelInferrer` (infer the provider
   types, attach string bodies; emit framework types **fully qualified** so the unit needs no imports). Make the
   embedded expression bodies Xbase in the grammar (`Expression.xtext` embeds Xbase; add an
   `ExpressionJvmModelInferrer`), switch the `.mwe2` generator fragment so the runtime module binds the inferrer
   + `JvmModelGenerator`, and **regenerate `src-gen`** via the MWE2 workflow.
8. **Delete legacy**: remove `ScopingGeneratorUtil.getCompilationContext` + its classic-Xtend inner classes
   (`*ExecutionContext`, `*Resource`) and any now-orphaned private helpers + imports; keep the still-used utility
   methods. Remove the `.ext` validation (`checkExtensions`) + its message key/field.
9. **Drop deps**: remove `org.eclipse.xtend` and `org.eclipse.xtend.typesystem.emf` from the plugin
   `META-INF/MANIFEST.MF`. (The `expression` plugin itself may keep them while `CompilationContext.java` still
   exists — that is a separate, later increment.)
10. **Build green** after each step; grep the whole plugin `src/**` for
    `org\.eclipse\.xtend\.(expression|typesystem)|org\.eclipse\.internal\.xtend|CompilationContext|EmfRegistryMetaModel|ExecutionContextImpl`
    to confirm only doc-comment mentions remain.

## Build / verify recipe (Windows / PowerShell)

Always prefix (the cwd drifts and multiple dotted `-D` props need the stop-parsing token `--%`):

```powershell
Set-Location D:\git\dsl-devkit
$env:JAVA_HOME = "D:\Java\jdk-21.0.10"
$env:Path = "D:\Programs\apache-maven-3.9.16\bin;$env:JAVA_HOME\bin;$env:Path"
mvn --% clean verify -f ./ddk-parent/pom.xml -pl :com.avaloq.tools.ddk.xtext.expression,:com.avaloq.tools.ddk.xtext.scope -am -DskipTests "-Dcheckstyle.skip=true" "-Dpmd.skip=true" "-Dspotbugs.skip=true" --batch-mode
```

- Pipe to a log + `Select-String` for `BUILD SUCCESS|BUILD FAILURE`; on failure grep
  `ERROR:|undefined|Type mismatch|ambiguous|incompatible`.
- Run the build **async** and wait for the completion notification (exit code is reported automatically).
  **Do not poll or `Start-Sleep`.** Builds take ~50–90 s.
- Swap the `-pl` list for the export plugin when migrating it.
- The build only checks **compilation**, not runtime/output (no `.scope` inputs). See the "green but broken"
  warning above.

## Xtend gotchas confirmed during this migration

- **Reserved words**: `val` → use `getVal()`/`^val`; also `^if`/`^else`/`^var`.
- **Lambda `it` shadowing**: inside `.exists[…]` / `.filter[…]` the implicit `it` rebinds. Hoist outer fields
  you need (e.g. `val operationName = name`, `val parameterCount = params.size`) **before** the lambda.
- **Leading-paren statement ambiguity**: a statement that starts with `(` right after another expression is
  parsed as a call on the previous line (`params.size(receiver…)`). Bind a `val` first, e.g.
  `val declaredType = receiverType as JvmDeclaredType` then `declaredType.allFeatures…`.
- **`@Inject extension` member shadowing**: an extension method can be shadowed by a same-named native member
  (e.g. `GenModelUtilX.instanceClassName(EClassifier)` lost to EMF's native `EClassifier.getInstanceClassName()`
  which returns `null` for generated EClasses). Inject as a **plain field** and call the method explicitly.
- `#[…]` is a `List`, `a -> b` is a `Pair`. `create_file` fails on existing files (edit instead). Same-package
  classes need no import.
- `vscode_listCodeUsages` can resolve relative paths against the **wrong root** in a multi-root workspace, and
  the Java reference provider may be unregistered ("No reference provider available"). Use `grep_search`
  instead to find callers.

## Reference: the scope migration (already landed)

Files added: `jvmmodel/ScopeJvmModelInferrer.xtend`, `jvmmodel/ScopeExpressionTranslator.xtend`,
`jvmmodel/ScopeExpressionCompiler.xtend`, `jvmmodel/ScopeTranslationContext.xtend`,
`jvmmodel/ScopeExpressionMethodRequest.xtend`, `generator/ScopeModelTypeResolver.java`, plus
`expression/jvmmodel/ExpressionJvmModelInferrer.xtend`. Deleted: `generator/ScopeGenerator.xtend` and
`ScopingGeneratorUtil.getCompilationContext` + its inner classes. MANIFEST dropped `org.eclipse.xtend` +
`org.eclipse.xtend.typesystem.emf`. The **export** plugin is the next target — same playbook.
