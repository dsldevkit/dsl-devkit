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
3. Heavy generators and dispatch-heavy code last: code generators, formatters, large exporters.

### Group by module

Batch files within the **same module** together where possible — they share Maven module context, test setup, and helper classes.

### Validation between batches

After every batch:

1. **Compile gate**: `mvn -pl :<target-module>,:<module> -am -DskipTests compile -f <parent-pom>` — must pass.
2. **Test gate**: `mvn verify -f <parent-pom> --batch-mode --fail-at-end` — must pass.
3. **Static analysis gate**: `mvn checkstyle:check pmd:check spotbugs:check -f <parent-pom>` — must pass.

Include the target-platform module in every `-pl` list — its artifact is not in `~/.m2`. Before the first
compile after the rename commit, delete the generated files under `<module>/xtend-gen/` except its
`.gitignore`, e.g. `find <module>/xtend-gen -mindepth 1 ! -name .gitignore -delete`; otherwise the stale
generated copy of the renamed class stays on the source path, collides with the new `.java` or hides
a class you have not translated yet, and the compile result no longer tells you anything.
Compare `xtend-gen/` trees with `diff -r -x '.*'` to skip `._trace` sidecars.

A red gate means you do not start the next batch. Diagnose first.

### Commit structure

**Two-step rename → translate per module** (see [`formatting-and-commit.md`](./formatting-and-commit.md)):
a pure `git mv` rename commit for all `.xtend` in the slice, an in-place translate commit, and —
when the module is fully off Xtend — an infrastructure-cleanup commit.

Commit message format: see [`formatting-and-commit.md`](./formatting-and-commit.md).
Example: `refactor: migrate Xtend to Java - com.example.mydsl.test (1/2: rename sources)`

### Rollback strategy

If a batch fails any validation gate:

1. **Do not force-commit.**
2. Revert the whole slice per [`formatting-and-commit.md`](./formatting-and-commit.md) §Rollback (a slice is 2-3 commits — plain `HEAD~1` strands the rename commit).
3. Diagnose the failure file-by-file.
4. **Pin** any individually-problematic file for separate investigation. Skip it; do the remaining files.
5. Re-attempt without the pinned files. Tackle pins as one-off conversions afterwards.

## Per-file mechanics

See [`workflow/one-file-conversion.md`](./one-file-conversion.md) for the per-file conversion steps.
