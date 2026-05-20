# Known pitfalls

Consolidated table of common mistakes and their fixes. Review before and after every migration.

| Pitfall | What to do |
|---------|------------|
| **Template whitespace** | The #1 migration bug source. Xtend's `StringConcatenation` strips leading indentation relative to control structures. A template that *looks* like it has a leading newline and spaces often produces a flat string. The ONLY way to know is to read `xtend-gen/`. Never guess. |
| **"Trivial" files** | Small files are NOT safe to eyeball. A 5-line file with one template can have surprising whitespace. Read both references for every file. |
| **Extension method call sites** | `obj.helperMethod()` in Xtend becomes `helper.helperMethod(obj)` in Java. The `xtend-gen/` output shows how every extension call was resolved. |
| **Implicit `it` in lambdas** | Xtend lambdas with no declared parameter use an implicit `it`. In Java, name it explicitly. |
| **Implicit returns** | Xtend methods return the last expression. The compiler catches missing returns but not wrong ones. |
| **Property access vs getter** | Xtend `obj.name` may call `getName()`. In Java, write `obj.getName()` explicitly. Check `xtend-gen/` if unsure. |
| **`CoreException` handling** | Xtend silently wraps checked exceptions. Java doesn't. Add explicit `try/catch` — the `xtend-gen/` file shows what was generated. |
| **`val` leaks** | Never use `var`. Use the explicit type. |
| **Invented Javadoc** | Never add Javadoc that wasn't in the original. This is a migration, not a rewrite. |
| **Missing `@throws` tags** | When Java migration adds `throws` and method already has Javadoc, Checkstyle requires `@throws`. Add it; don't create Javadoc just for the tag. |
| **Duplicate string literals** | Checkstyle flags strings appearing 2+ times. In tests, extract to constants. In generators, use `CHECKSTYLE:CONSTANTS-OFF/ON`. |
| **`@Data` / `@Accessors`** | These generate code at compile time. The `xtend-gen/` output shows exactly what — copy equals/hashCode/toString/getters from there. |
| **`BasicEList` in generic code** | Needs explicit type parameter — `new BasicEList<X>()`. |
| **StringBuilder in `xtend-gen/`** | If `xtend-gen/` has `StringConcatenation` but Xtend has a template, that's the signal to use text block or `.formatted()` (tier 1–3) or `StringBuilder` (tier 4). |
| **Non-parameterized logging** | Xtend files often have `"msg" + x` in log calls. Fix to `{}` placeholders. |
| **PMD missing type-resolution** | Always `compile` before `pmd:check` or you'll miss `MissingOverride`, `LooseCoupling` etc. |
| **`--fail-at-end` hides failures** | Check the final BUILD line, not intermediate output. |
| **Dispatch method names** | Keep underscores. Suppress with `@SuppressWarnings`. Never rename. |
| **IDE save actions** | "Organize Imports" in Eclipse may trigger save actions that auto-convert string concatenation to text blocks. Auto-conversion produces wrong results. Review `git diff` after any IDE action. |
| **Import order** | The project uses `org.*`/`java.*` first, blank line, then `com.*` second. Wrong order causes diff churn. |
| **Eclipse CLI formatter** | Does NOT organize imports — only code formatting. Import order must be correct from the start. |
| **IllegalCatch** | Do not suppress `PMD.AvoidCatchingGenericException`. Replace `catch (Exception e)` with specific exceptions. |
| **Rollback** | If already pushed: `git revert -m 1 <sha>`. If local only: `git reset --hard HEAD~1`. After reverting, build and test. |
| **`ByteArrayInputStream.close()`** | It's a no-op. Safe to remove entirely. |
| **`==` in Xtend** | Xtend `==` is `.equals()`, not identity. Convert to `.equals()` or `Objects.equals()`. Only `===`/`!==` are identity. |
