# Xtend library replacements

When a converted file calls Xtend's runtime library, replace the call with a Java stdlib equivalent.
Use Guava **only** where it is genuinely more concise (marked with ★).

| Xtend library call | Java replacement |
|---|---|
| `IterableExtensions.map(iter, fn)` | `iter.stream().map(fn).toList()` |
| `IterableExtensions.filter(iter, fn)` | `iter.stream().filter(fn).toList()` |
| `IterableExtensions.filter(iter, Type.class)` | ★ `Iterables.filter(iter, Type.class)` (Guava — type-safe, no cast needed) |
| `IterableExtensions.toList(iter)` | `StreamSupport.stream(iter.spliterator(), false).toList()` or loop |
| `IterableExtensions.toSet(iter)` | `StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toSet())` |
| `IterableExtensions.head(iter)` | ★ `Iterables.getFirst(iter, null)` (Guava — null-safe one-liner) |
| `IterableExtensions.join(iter, sep)` | `String.join(sep, iter)` (if `Iterable<String>`) or `StreamSupport.stream(...).map(Object::toString).collect(Collectors.joining(sep))` |
| `IterableExtensions.join(iter, sep, fn)` | `iter.stream().map(fn).collect(Collectors.joining(sep))` |
| `IterableExtensions.exists(iter, fn)` | `iter.stream().anyMatch(fn)` |
| `IterableExtensions.forall(iter, fn)` | `iter.stream().allMatch(fn)` |
| `IterableExtensions.findFirst(iter, fn)` | `iter.stream().filter(fn).findFirst().orElse(null)` |
| `IterableExtensions.sortBy(iter, fn)` | `iter.stream().sorted(Comparator.comparing(fn)).toList()` |
| `IterableExtensions.sort(iter)` | `iter.stream().sorted().toList()` |
| `IterableExtensions.isEmpty(iter)` | `!iter.iterator().hasNext()` |
| `IterableExtensions.size(iter)` | `(int) StreamSupport.stream(iter.spliterator(), false).count()` or loop |
| `IterableExtensions.toMap(iter, keyFn, valFn)` | `iter.stream().collect(Collectors.toMap(keyFn, valFn))` |
| `IterableExtensions.filterNull(iter)` | `iter.stream().filter(Objects::nonNull).toList()` |
| `IterableExtensions.flatten(iter)` | `iter.stream().flatMap(Collection::stream).toList()` (inner is Collection; for a general Iterable use `flatMap(i -> StreamSupport.stream(i.spliterator(), false))`) |
| `IteratorExtensions.toIterable(iter)` | `(Iterable<T>) () -> iter` (keep as-is for Xtext patterns) |
| `StringExtensions.isNullOrEmpty(s)` | `s == null \|\| s.isEmpty()` |
| `StringExtensions.toFirstUpper(s)` | `Character.toUpperCase(s.charAt(0)) + s.substring(1)` |
| `StringExtensions.toFirstLower(s)` | `Character.toLowerCase(s.charAt(0)) + s.substring(1)` |
| `CollectionLiterals.newArrayList(...)` | `new ArrayList<>(List.of(...))` |
| `CollectionLiterals.newHashSet(...)` | `new HashSet<>(Set.of(...))` |
| `CollectionLiterals.newHashMap(...)` | `new HashMap<>(Map.of(...))` |
| `ObjectExtensions.operator_doubleArrow(obj, fn)` | Inline — see [`rules/08-operator-overloads.md`](../rules/08-operator-overloads.md) §8.4 |
| `Functions.Function1` | `java.util.function.Function` |
| `Procedures.Procedure1` | `java.util.function.Consumer` |

## Guava utilities to keep as-is (do not rewrite)

These are already Guava or Xtext-idiomatic patterns that should stay:

- `Iterables.filter(iter, Type.class)` — type-safe class filtering
- `Iterables.getFirst(iter, default)` — null-safe first element
- `Joiner.on(sep).skipNulls()` — null-skipping joins (no Java stdlib equivalent)
- `IteratorExtensions.toIterable(resource.getAllContents())` — standard Xtext pattern

## When in doubt

Check the `xtend-gen/` output to see what the Xtend compiler actually generated.
The generated code shows the exact static method calls Xtend resolved to.
