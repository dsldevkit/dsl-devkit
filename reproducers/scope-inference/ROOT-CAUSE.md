# Root cause and scope of the fix

## Confirmed regression

[PR #1405](https://github.com/dsldevkit/dsl-devkit/pull/1405), commit `2973c5b2f75af1029602b5ee4b4c771309a21e95`, moved Scope generation from `IGenerator2.doGenerate` into JVM model inference on **19 August 2026**. The affected code is present in DDK 19.1.

The inferrer eagerly rendered every provider method body before accepting either JVM type. Rendering a type dispatch body calls `GenModelUtilX.literalIdentifier(EClass)`, which requires an attached EPackage. A temporarily detached classifier or unresolved model reference therefore throws before either provider is accepted.

Xtext's `BatchLinkableResource.getContents()` can install derived state. Its `JvmModelAssociator` catches and logs inference exceptions; the resource can remain initialized without the missing provider types. Repeated root reads do not automatically repeat inference.

The reported stack enters this path from Export inference, grammar linking, and `Xtext2EcoreTransformer.removeGeneratedPackages()`. That transformer traverses other resources' contents while processing generated packages. Thus, a read during linking unexpectedly triggers Scope body compilation.

The standalone baseline test against the public 19.1 binaries produces the same DDK exception chain:

```text
GenModelUtilX.qualifiedPackageInterfaceName
GenModelUtilX.literalIdentifier
ScopeProviderGenerator.doGetScopeByTypeBody
ScopeJvmModelInferrer.infer
JvmModelAssociator.installDerivedState
BatchLinkableResource.getContents
```

It leaves one source root instead of the source root plus two provider types.

## Why delaying only the bodies is insufficient

Provider member declarations also depend on model resolution. The historical helper name includes the model package and classifier names. Inheritance merging additionally compares classifiers' package namespaces. Unavailable includes can otherwise silently contribute empty scope and injection collections.

An acceptor initializer is not, by itself, delayed beyond inference: Xtext normally executes it immediately. The fix registers actual demand-driven JVM member initialization after accepting both provider roots. This postpones model-dependent work during plain root traversal, but does not claim that member demand is a global linking-completion barrier.

## Fix

1. Accept both provider type roots without compiling Java bodies.
2. Initialize members on demand. Before inheritance traversal, check that included models and scope signatures are available. Keep an incomplete marker until member construction succeeds.
3. Render bodies only during Java emission, with fresh model-specific generators, the project resource loader, and restoration of the previous GenModel resource context.
4. If an early member query observed incomplete input, rebuild the derived model before generation traverses JVM types. If it is still incomplete, fail before writing any Java.
5. Preserve the existing resolved helper names and generation algorithms.

Regression tests cover plain root traversal, early member demand and repair, unresolved generation with no output, and include repair with inherited methods and injections. Tests run through the repository's existing central suite. The separate public test project runs the same scenarios against a selected p2 target.

Two independent adversarial Astra reviews challenged the diagnosis and recovery design. Their findings led to explicit late member initialization, incomplete-inference recovery, include checks before inheritance merging, and assertions of actual method declarations rather than method-name substrings alone.

## Limits

The reproducer intentionally stages unavailable model objects. It proves the DDK lifecycle failure and recovery behavior without private sources. It does not reproduce the entire downstream grammar/Export load sequence, prove that a previously valid target became unresolved because of DDK, or explain every hour of Windows build time. The model-availability trigger and Windows performance remain separate unproven questions.

No line-ending, Format, Check, Xtend, JDT, or antivirus change is included in this fix.
