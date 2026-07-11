# Example: basic generator conversion

A small generator with `@Inject extension`, `override`, null-safe navigation, `typeof`, template expression with `«FOR»` and `«IF»`, and a `static extension` import. Touches rules 01, 02, 03, 04, 06, 08, and 09.

## Xtend input

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

## Java output

```java
package com.example;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

@SuppressWarnings("nls")
public class MyGenerator {

  @Inject private MyHelper helper;

  @Override
  public void doGenerate(final Resource resource) {
    final URI uri = resource != null ? resource.getURI() : null;
    final Config config = getConfig(uri);
    for (final MyModel model : Iterables.filter(IteratorExtensions.toIterable(resource.getAllContents()), MyModel.class)) {
      helper.compile(model);
    }
  }

  public CharSequence compile(final MyModel model) {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("package ").append(model.getPackageName()).append(";\n");
    if (!(model.getImports() == null || model.getImports().isEmpty())) {
      builder.append("\n");
      for (final String imp : model.getImports()) {
        builder.append("import ").append(imp).append(";\n");
      }
    }
    builder.append("\npublic class ").append(model.getName()).append(" {\n}\n");
    return builder;
  }
}
```

## What changed

| Xtend construct | Java equivalent | Rule |
|---|---|---|
| `class MyGenerator` (implicit public) | `public class MyGenerator` | 01 |
| `@Inject extension MyHelper helper` | `@Inject private MyHelper helper;` + rewrite call sites | 06 |
| `override void doGenerate(...)` | `@Override public void doGenerate(...)` | 03 |
| `val config = getConfig(...)` | `final Config config = getConfig(...);` | 02 |
| `resource?.URI` | `resource != null ? resource.getURI() : null` | 08 |
| `typeof(MyModel)` | `MyModel.class` | 09 |
| `toIterable(resource.allContents).filter(typeof(MyModel))` | `Iterables.filter(IteratorExtensions.toIterable(resource.getAllContents()), MyModel.class)` | Guava exception (type-safe filtering) |
| `model.compile` (extension call) | `helper.compile(model)` | 06 |
| `def compile(MyModel it) '''...'''` | `public CharSequence compile(final MyModel model)` + StringBuilder | 04, 09.8 |
| `«packageName»` (property on `it`) | `model.getPackageName()` | 09.5 |
| `!imports.isNullOrEmpty` | `!(model.getImports() == null \|\| model.getImports().isEmpty())` | 09.4 |
| `«FOR imp : imports»` | `for (final String imp : model.getImports())` | 04 |
| `import static extension com.example.NamingExtensions.*` | Removed (calls rewritten) | 06 |

## Key decisions

- **Template → StringBuilder** (tier 4) because the template has `«IF»` and `«FOR»` control flow.
- **`it` parameter renamed** to `model` (descriptive name).
- **`Iterables.filter(iter, Type.class)`** kept as Guava — genuinely more concise for type-safe filtering.
- **Whitespace must be verified against `xtend-gen/`** — for a real migration, confirm the template output by reading the generated code (this example illustrates the shape).
