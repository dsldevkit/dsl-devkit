# Multi-file batch workflow

Use this when converting several `.xtend` files together.

## Batching principles

### Batch sizing by complexity

Bucket files by complexity, not raw line count:

| Bucket | Heuristic |
|---|---|
| **Trivial** | ≤30 lines, no complex features (no dispatch, no templates, no `@Data`/`@Accessors`) |
| **Easy** | ≤100 lines, basic features only (`val`, `override`, `@Inject`, simple templates) |
| **Medium** | 100–200 lines, **or** any file with templates + extension methods + switch |
| **Hard** | 200–400 lines with dispatch / complex templates / many extensions |
| **Very Hard** | 400+ lines and a combination of dispatch + templates + extensions |

Aim for ~10 files per batch when all Trivial/Easy. Drop the count as complexity climbs — a single Very Hard file may be its own batch.

### Leaf-bundle-first ordering

Process modules bottom-up:

1. Leaf modules first: test utilities, small standalone bundles.
2. Then mid-level: language cores, simple generators.
3. Heavy generators and dispatch-heavy code last: `xtext.generator`, `xtext.format`, large `xtext.export.*`.

### Group by module

Batch files within the **same module** together where possible — they share Maven module context, test setup, and helper classes.

### Validation between batches

After every batch:

1. **Compile gate**: `mvn -pl :<module> -am -DskipTests compile -f ./ddk-parent/pom.xml` — must pass.
2. **Test gate**: `mvn verify -f ./ddk-parent/pom.xml --batch-mode --fail-at-end` — must pass.
3. **Static analysis gate**: `mvn checkstyle:check pmd:check spotbugs:check -f ./ddk-parent/pom.xml` — must pass.

A red gate means you do not start the next batch. Diagnose first.

### Commit structure

**Two-step rename → translate per module** (see [`formatting-and-commit.md`](./formatting-and-commit.md)):
a pure `git mv` rename commit for all `.xtend` in the slice, an in-place translate commit, and —
when the module is fully off Xtend — an infrastructure-cleanup commit.

Commit message format:
```
refactor: migrate Xtend to Java - <plugin name> (1/2: rename sources)
refactor: migrate Xtend to Java - <plugin name> (2/2: translate to Java 21)
```
Example: `refactor: migrate Xtend to Java - com.avaloq.tools.ddk.check.core.test`

### Rollback strategy

If a batch fails any validation gate:

1. **Do not force-commit.**
2. Revert: `git reset --hard HEAD~1` (or `git revert -m 1 <sha>` if already pushed).
3. Diagnose the failure file-by-file.
4. **Pin** any individually-problematic file for separate investigation. Skip it; do the remaining files.
5. Re-attempt without the pinned files. Tackle pins as one-off conversions afterwards.

## Per-file mechanics

See [`workflow/one-file-conversion.md`](./one-file-conversion.md) for the per-file conversion steps.
