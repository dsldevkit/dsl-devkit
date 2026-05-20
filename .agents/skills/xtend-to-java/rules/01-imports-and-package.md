# Imports, package, class declaration, semicolons

## 1.1 Package

Keep identical. Add a trailing semicolon if missing.

## 1.2 Imports

- `import com.foo.Bar` stays as `import com.foo.Bar;`
- `import static com.foo.Bar.*` stays as `import static com.foo.Bar.*;`
- `import static extension com.foo.Bar.*` becomes `import static com.foo.Bar.*;` — the `extension` keyword is dropped at the import; see [`rules/06-extension-methods.md`](./06-extension-methods.md) for the call-site rewrite.
- **Remove** imports of Xtend-only types that are no longer needed (e.g. `org.eclipse.xtend2.lib.StringConcatenation` when switching to `StringBuilder`).
- **Add** any imports the Java code now needs: `java.util.List`, `java.util.ArrayList`, `java.util.stream.*`, `java.util.Objects`, etc.
- **No wildcard imports.** Every import is explicit. 20 explicit imports beats one wildcard.
- **Remove unused imports.**

## 1.3 Import order convention

Follow the project's Eclipse import order — **standard/framework first, project-specific second**:

```java
// Group 1: org.*, java.*, javax.*, junit.* (standard/framework)
import org.eclipse.xtext.testing.InjectWith;
import org.junit.jupiter.api.Test;
import java.util.List;

// Blank line separator

// Group 2: com.* (project-specific: com.avaloq.*, com.google.*, etc.)
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil;
import com.google.inject.Inject;
```

Within each group, imports are sorted alphabetically. There is always exactly one blank line between the two groups.

## 1.4 Class declaration

- Xtend classes are `public` by default. Make this explicit in Java.
  - `class Foo extends Bar` → `public class Foo extends Bar`
- Xtend `interface` stays as `interface` (already public by default in Java too).
- Add `{` / `}` braces as normal Java.

## 1.5 Semicolons

Add semicolons to all statements. Xtend allows omission; Java requires them.
