# Segment-trie lookup: Lean 4 model, checks, findings

Code under study (worktree HEAD `e784f416b`, production sources unmodified):
`QualifiedNameSegmentTreeLookup.java`, `QualifiedNamePattern.java`, `TreeSetLookup.java`,
`util/ArrayUtils.java` (all in `com.avaloq.tools.ddk.xtext/.../naming` or `.../util`) and
`resource/PatternAwareEObjectDescriptionLookUp.java`. Xtext `QualifiedName` semantics were taken from
the 2.43/2.44 bytecode (`javap -c`). `compareTo(QualifiedName, boolean)` is byte-identical in both versions.

Toolchain: `leanprover/lean4:v4.35.0-rc2`, no Mathlib, no dependencies, nothing downloaded.

## How to run

```sh
export PATH=$HOME/.elan/toolchains/leanprover--lean4---v4.35.0-rc2/bin:$PATH
lake build                          # model + fidelity #guards + all theorems + checker exe (~2-3 min)
./.lake/build/bin/trie-check 3 4    # stores <= 3 names, op sequences <= 4 (7m19s for all 13 variants)
./.lake/build/bin/trie-check 3 4 as-written   # a single variant
lake env lean Trie/Axioms.lean      # #print axioms
```

The Java cross-check uses plain `javac`/`java` against the already-built `target/classes` and the p2 jars
(no Maven). Classpath: `java-check/classpath*.txt`. Harnesses: `java-check/TrieRepro.java`,
`java-check/RunTests.java`. Outputs: `results/`.

## Model (1,669 lines of Lean)

| File | Content |
|---|---|
| `Trie/Basic.lean` | Java `char` = `Nat` (UTF-16 unit), `String` = `List Nat`; `String.compareTo`, Xtext `QualifiedName.compareTo`, `indexOf`/`lastIndexOf`/`regionMatches`/`isBlank`, `(char)(c+1)` with wrap-around |
| `Trie/Pattern.lean` | `QualifiedNamePattern`: verification, `matches` (plain and glob), `lowerInclusive`, `upperExclusive`, the custom `compareTo`, `Comparator`, `toLowerCase`, line by line |
| `Trie/Tree.lean` | `QualifiedNameSegmentTreeLookup`: `SegmentNode.find`, `collectMatches`, `visitChildren`, `merge` (plain and value-sharing), `binarySearch`, `getMappings`, `removeMappings`, `put`/`putAll`/`remove`/`get`, `size`; `ArrayUtils.add/addAll/remove/find` |
| `Trie/TreeSet.lean` | `TreeSetLookup` + `findNestedArrayMatches`, `TreeMap.subMap` including the `fromKey > toKey` exception |
| `Trie/Consumer.lean` | `PatternAwareEObjectDescriptionLookUp.getExportedObjects` and `getNameToObjectsLookup` |
| `Trie/Validate.lean` | Every assertion of `QualifiedNamePatternTest` and `QualifiedNameSegmentTreeLookupTest`, replayed as a build-time `#guard` |
| `Trie/Fixed.lean` | The minimal fixes F1-F10 as independent flags (fix ladder) |
| `Trie/Checks.lean`, `Trie/Domains.lean`, `Main.lean` | Exhaustive bounded checkers and domains |
| `Trie/Theorems.lean` | Concrete counterexamples, all-sizes proofs, bounded `native_decide` results |

Faithfulness devices:

- **Node identity.** `child == upper` in `collectMatches` and `stopOn.equals(child)` in `visitChildren` use reference identity (`SegmentNode` does not override `equals`). The model identifies a node by its path from the root, which is unique in a trie.
- **Array identity.** The value-sharing subclass collects arrays into a `HashSet<Object[]>`, which uses identity. The model gives every `Object[]` allocation an id and reproduces `Arrays.equals` sharing in `merge` (lines 436, 442).
- **Binary search.** The Java loop is transcribed as written, not replaced by a lookup.
- **`TreeMap.subMap`.** Modelled as the filter `cmp(k, lo) >= 0 && cmp(k, hi) < 0`. This is equivalent to TreeMap's ceiling-plus-successor iteration only when both predicates are monotone along key order, so P5 checks that exhaustively; it holds in every variant.
- **Dynamic type.** A `Key` records whether a name is a `QualifiedNamePattern`, because `lowerInclusive()` returns `this` for wildcard-free patterns and the Comparator dispatches on that.

Assumptions and exclusions:

- Case mapping and whitespace are ASCII-only. The model alphabet has no non-ASCII letters, and nothing locale-dependent (such as Turkish dotted I) is modelled.
- Glob `.` never meets line terminators, because the alphabet has none.
- Not modelled: serialization (`read/writeExternal`), `initializeFrom`, concurrency, hit/miss counters, `excludeDuplicates = true`. The last is the set image of the modelled multiset.

Fidelity evidence:

1. All existing JUnit assertions hold on the model (`Validate.lean`, 73 `#guard`s).
2. **E0**: the ladder's base variant, with no fixes, is equal to the literal transcription on 1,276 stores × 423 patterns × 70 names. This check passes.
3. **Java harness** `TrieRepro`: all 38 counterexample assertions reproduce on the real classes (`results/java-repro.txt`).

## Properties and how each was checked

The **spec** is: `get(p)` returns exactly the stored values whose names `p.matches(...)`, as a multiset.
For globs, which are documented as candidates ("TODO implement strict matching"), only completeness is required.

| Id | Property | Method |
|---|---|---|
| P1 | every name `p` matches lies in `[lowerInclusive, upperExclusive)` under the Comparator | bounded exhaustive: 7,353 patterns × 2,235 names over the alphabet {␠, !, A, a, b, U+FFFF} |
| P1b | `compare(lower, upper) <= 0`, else `subMap` throws | same patterns, plus globs and U+FFFF patterns |
| P1c | range precision: in-range with the right segment count implies matched | same as P1 |
| P1g | P1 for globs | 72 globs × 2,235 names |
| P5 | `subMap` predicates are monotone (the modelling device above) | same as P1 |
| P2 | tree = spec; TreeSetLookup = spec; tree = TreeSetLookup; `shareValues` true vs false give the same multiset | all 20,876 stores of ≤ 3 names from a 50-name universe × 315 patterns |
| P2m | P2 around U+FFFF | 211 stores × 36 patterns |
| P2g | glob completeness for tree and for TreeSetLookup | 1,276 stores × 72 globs |
| P3 | after any `put`/`putAll`/`remove`/`removeMappings` sequence: `size` = number of stored pairs; `get(name)`, `getMappings(v)`, `get(pattern)` equal TreeSetLookup | BFS over all sequences of ≤ 4 of 22 operations (245,411 states), `shareValues` both ways |
| P4 | consumer: case-sensitive result = spec; case-insensitive result = spec on lower-cased names; case-sensitive ⊆ case-insensitive | 4,526 description sets × 42 arguments |

Each check returns the first counterexample in size order, which gives the shortest trace. Stores are ordered by (number of names, total size), and operation sequences are explored breadth-first.

All-sizes proofs (`Theorems.lean`, namespace `All`, no `sorry`):

- `bang_not_successor`: for every segment `a` and every char `c < '!'`, `[a]` < `[a ++ c]` < `[a ++ "!"]`. This is the root cause of B2 for all sizes.
- `nul_is_successor`: `a ++ "\u0000"` is the immediate successor of `a` in Java string order. This proves the F2 fix correct for all sizes.
- `topStar_unsound`: pattern `*` matches every single-segment name `c…`, yet every such name with `c > '!'` compares greater than the upper bound. This is B1 for all sizes.
- `exact_matches_extensions`: every wildcard-free pattern `matches` every one-segment extension of itself (B3).
- `addAll_present` and `put_size`: `addAll` returns the same array for a value already present, while `put` always does `size++` (B5).
- `consumer_cs_empty`: for **every** set of descriptions and **every** pattern, the case-sensitive query returns `[]` (B8).

Concrete counterexamples (`Cex`) are closed by kernel `decide`, except the glob ones (well-founded recursion), which use `native_decide`.
`Bounded` covers the fixed model passing, the planted bug being caught, the as-written model failing, and witnesses, all by `native_decide`.

`#print axioms` (`results/axioms.txt`):

- `Cex.*` via `decide`, and `All.*`: only `propext`, `Classical.choice`, `Quot.sound`. `bang_not_successor` and `nul_is_successor` need only `propext`.
- `native_decide` results (`Cex.glob_*`, `Bounded.*`): the above plus their `_native.native_decide.ax_*` axiom, which trusts the compiler.

## Sanity checks

- **Fixed model passes.** With all fixes F1-F10 and assumption A1 (no U+FFFF), every check passes at full bounds: P1/P1b/P1c/P1g/P5, P2 on 20,876 stores, P3 on 245,411 states, and P4.
- **Fix ladder.** Applying fixes one at a time removes exactly the counterexamples each targets (`results/full-run.txt`). This also attributes every counterexample to one root cause.
- **Planted bug caught.** Making the tree ignore `recursive` fails P2, P2m, P2g, P3 and P4 immediately: store `{("",""):0}` with pattern `**` returns `[]` instead of `[0]`.
- **Witnesses.** Good paths are reachable: 5,034 non-recursive and 5,956 recursive (store, pattern) pairs have a non-empty, correct tree result as written (7,222 recursive once fixed). Case-sensitive ⊆ case-insensitive holds throughout.
- **Real code.** Patched copies of the sources (`java-check/fixed-src`, identical to `fix-plan.patch`) compiled ahead of `target/classes`:
  - 12 of the 13 new tests pass; B9 stays open by design (see the fix plan).
  - The existing `QualifiedNameSegmentTreeLookupTest` (11 tests) and the untracked `PatternAwareEObjectDescriptionLookUpTest` (5 tests) pass.
  - 5 methods of `QualifiedNamePatternTest` fail because they assert the old `"!"` bounds (`results/junit-as-written-vs-fixed.txt`).

## Findings (all CONFIRMED on the real classes)

The model's counterexamples below were re-derived by re-reading the code and then executed on the compiled classes.
Line numbers refer to the file named in each finding.

### B1 (medium): a top-level `*` or `**` finds nothing at or above `"!"`

- **Trace (tree):**
  1. `put(("b"), v)`. Root children become `[b, ￿]` (`merge` 256-266; dummy from `init` 520).
  2. `get(pattern("*"))`. `lowerInclusive = ("")` (QualifiedNamePattern 340-341). `upperExclusive = ("!")` (372-374).
  3. `root.find(("!"), 0, false)`: binarySearch gives insertion point 0; it is the last segment, so it returns node `b` (97-114). **`b` is the upper marker.**
  4. `collectMatches(("")...)`: insertion point 0, last segment, loop at 188-191. The first child `b == upper`, so it returns false.
- **Result:** `[]`. `pattern("*").matches(("b"))` is true.
- **TreeSetLookup:** `subMap((""), ("!"))` is empty for the same reason (QualifiedNamePattern 413/421).
- **Scope:** the same happens for `**` (for example `("b","c")`) and for globs whose first segment has a wildcard (357-359).
- **Reachable from production:** `PrefixedContainerBasedScope` (line 69) builds `prefix.append("*"|"**")`, and an empty prefix gives exactly this pattern. `ContainerQuery.Builder.name("*")` (432) does too.
- **All sizes:** `All.topStar_unsound`.
- **Test:** `testTopLevelWildcardFindsSingleSegmentNames`, `testTopLevelRecursiveWildcardFindsAllNames`.

### B2 (low): `'!'` is not the successor of a segment

- **Where:** `upperExclusive` appends `'!'` (QualifiedNamePattern 363, 379, 382, 386).
- **Effect:** every name whose segment is the pattern's segment followed by a char below `'!'` (space, tab, control chars) falls inside the range.
- **Trace:**
  - `put(("a␠"))` then `get(pattern("a"))` gives `[a␠]`, from both lookups. `matches` is false.
  - For `a.*` the tree additionally visits every descendant of a sibling `a␠` in the post-recursion loop (200-207). So `("a␠","x")` is returned when `("a", …)` exists, and not returned when it does not. TreeSetLookup returns it in both cases.
- **All sizes:** `All.bang_not_successor`.
- **Test:** `testExactPatternExcludesNameWithLowCharSuffix`, `testChildWildcardExcludesSiblingWithLowCharSuffix`.

### B3 (low): a wildcard-free pattern `matches` longer names

- **Where:** `matches` (278-304) never compares segment counts when no segment has a wildcard (return at 304).
- **Effect:** `pattern("a").matches(("a","b"))` is true, while both lookups return only `("a")`, because the non-recursive path filters or walks by segment count. Lookup and `matches` disagree.
- **Consumer:** `ContainerBasedScope` (86, 116) filters with `matches`, so an exact-name query accepts longer lookup names.
- **All sizes:** `All.exact_matches_extensions`.
- **Test:** `testExactPatternMatchesOnlyEqualLength`.

### B4 (low, TreeSetLookup only): `compareTo` treats an empty pattern segment as minimal

- **Where:** QualifiedNamePattern 224-225.
- **Why it matters:** for a wildcard-free pattern, `lowerInclusive()` is the pattern itself (340). `TreeMap.subMap` then compares with `-pattern.compareTo(key)` (Comparator 46).
- **Trace:** store `("","")`, pattern `("","␠")`. The key sorts after the lower bound and below `("","␠!")`, has 2 segments, and is returned. `matches` is false. The tree returns `[]` correctly.
- **Test:** `testTreeSetLookupEmptySegmentPattern`.

### B5 (low): `size` drifts

- **Where:** `put` does `size++` unconditionally (QualifiedNameSegmentTreeLookup 590). `merge`/`ArrayUtils.addAll` do not add a value that is already present (267-269, ArrayUtils 83-90).
- **Trace:** `put(a,v); put(a,v)` gives `size = 2` with 1 stored pair. `remove(a,v)` then gives `size = 1` with 0 pairs and `get(a) == null`.
- **Effect:** `getStatistics().getEntries()` is wrong, and `initializeFrom` (639) refuses a logically empty lookup.
- **All sizes:** `All.addAll_present` + `All.put_size`.
- **Test:** `testSizeCountsMappingsOnce`.

### B6 (low): `getMappings` drops blank intermediate segments

- **Where:** the `!segment.isBlank()` test (295) was meant to skip the root (segment `""`), but it also skips every node whose segment is empty or whitespace.
- **Trace:** `put((" ","a"),v)` or `put(("","a"),v)` makes `getMappings(v)` return `[("a")]`.
- **Test:** `testGetMappingsKeepsBlankSegments`.

### B7 (low): value sharing collapses multiplicity

- **Where:** `ValueSharingSegmentNode.matches` dedups arrays by identity (406). A child shares its parent's array when the contents are equal (436, 442).
- **Trace:** `shareValues=true`, `{a:v, a.b:v}`: `get(a**, false)` returns `[v]`. With `shareValues=false`, or TreeSetLookup, it returns `[v, v]`.
- **Test:** `testValueSharingKeepsMultiplicity`.

### B8 (high): case-sensitive pattern queries in `PatternAwareEObjectDescriptionLookUp` always return nothing

- **Where:** line 63 filters with `((QualifiedNamePattern) name).matches(name)`. `name` is a pattern, and `matches` returns false for any `QualifiedNamePattern` argument (QualifiedNamePattern 263-264).
- **Trace:** descriptions `Foo`, `FooBar`: `getExportedObjects(ECLASS, pattern("Foo*"), false)` returns `[]`. With `ignoreCase = true` it returns `[Foo, FooBar]`.
- **Reach:** every `ResourceDescription` built on this lookup (`SimpleResourceDescription`, `FixedCopiedResourceDescription`, `FingerprintResourceDescription`, `ResourceDescription2`), for any case-sensitive `ContainerQuery` with a name pattern (ContainerQuery 300).
- **All sizes:** `All.consumer_cs_empty`.
- **Test:** `testCaseSensitivePatternQuery`, and also the untracked `resource/PatternAwareEObjectDescriptionLookUpTest`.

### B9 (low): U+FFFF

- **Overflow:** `(char)(c+1)` wraps (389): `"￿*"` gets upper `"\u0000"`, below its lower bound. `TreeSetLookup.get` then throws `IllegalArgumentException: fromKey > toKey`.
- **Sentinel collision:** a stored name `"￿"` merges into the dummy sentinel node (520). `get(pattern("￿"))`:
  1. The upper bound `"￿!"` lies beyond the sentinel.
  2. `find` returns null.
  3. `collectMatches` returns false (174), so the lookup returns `[]`, even though `get(name)` finds the value.
- **Test:** `testMaxCharPattern`. The minimal patch does not fix it (see the fix plan).

### B10 (low; `createFromGlobs` has no production caller in this repo): glob lookups miss matches

- **Bounds:** the `"!"` upper bound (B1, 357-359).
- **Wrong level:** globs whose last segment ends with `*` match deeper names (268), but `isRecursivePattern` is false. So the tree walks the lower bound's level only, which is also wrong for a wildcard in an earlier segment. `("a*","b")` gives tree `[]` versus TreeSetLookup `[ab]`.
- **Case:** regexps are case-insensitive (`fromGlob(glob)` gives `ignoreCase = true`, line 101) while the bounds are case-sensitive. `F*` matches `foo`, but the lookup misses it.
- **Crash:** `createFromGlobs("a","").matches(...)` throws `StringIndexOutOfBoundsException` (`charAt(-1)`, 268).
- **Test:** `testGlobLookupsFindMatches`, `testGlobWithEmptyLastSegment`.

### Observations (not modelled, PLAUSIBLE)

- `initializeFrom` (645-646) shares `root` itself, not just the values. A later `put`/`remove` on either lookup mutates both, while their `size` fields diverge. There is no caller in this repo.
- The double-checked locking in `PatternAwareEObjectDescriptionLookUp` (95-98) re-tests the local `localMap`, which is always null inside the lock. So racing threads each rebuild the lookup. This is benign: last write wins and the results are equivalent.
- `put(QualifiedName.EMPTY, v)` does `size++` and then throws in `merge` (`getSegment(0)`).

## Fix plan (`fix-plan.patch`, validated on patched copies)

| Fix | Change | Addresses |
|---|---|---|
| F1 | single-segment `*`/`**`/glob upper bound `String.valueOf(Character.MAX_VALUE)` (the tree's own sentinel) instead of `"!"` | B1, part of B10 |
| F2 | append `'\u0000'` instead of `'!'` (proved to be the exact successor, `All.nul_is_successor`) | B2 |
| F3 | `matches`, non-glob branch: `return other.getSegmentCount() == getSegmentCount();` | B3 |
| F4 | `compareTo`: drop the `seg1.length() == 0 → -1` special case | B4 |
| F5 | `put`/`putAll`: increment `size` only for values not already mapped | B5 |
| F6 | `getMappings`: iterate from the root's children and always prepend the segment | B6 |
| F7 | value sharing: identity-dedup arrays only when `excludeDuplicates` | B7 |
| F8 | `PatternAwareEObjectDescriptionLookUp` line 63: `.matches(input.getName())` | B8 |
| F9 | `isRecursivePattern()` is true for globs (candidate superset) | B10 |
| F10 | `Regexps.fromGlob(from, false)`, so the regexps match the case-sensitive bounds | B10 |
| — | glob `matches`: `!lastSeg.endsWith("*")` instead of `charAt(length-1)` | B10 crash |

Tests that must change together with F1/F2, because they assert the buggy bounds:

- `QualifiedNamePatternTest`: `testQualifiedPrefixNamePattern`, `testRecursiveWildcardPattern`, `testPatternWithoutWildcard`, `testAllPattern`, `testRegexpPatterns`.
- In each, expect `"foo\u0000"` instead of `"foo!"`, and `"￿"` instead of `"!"`.

B9 needs a design decision rather than a one-liner. Options:

- (a) Carry-aware successor: drop trailing U+FFFF chars before incrementing, or fall back to the parent segment's successor, or to "unbounded".
- (b) Represent "unbounded" explicitly: `tailMap` in TreeSetLookup, and in the tree an upper marker that is never a real node, instead of `find` returning null.
- (c) Document U+FFFF as reserved. The tree's dummy already assumes this.

The fixed model verifies everything else under assumption A1 (no U+FFFF).

## Stats and wall time

- **Lean:** `lake build` about 2-3 min. Most of that is the kernel `decide` counterexamples and the `native_decide` bounded theorems.
- **Checker:** `trie-check 3 4` runs all 13 variants in 7m19s (365 s CPU), about 30 s per variant. The largest single check is P2 share-vs-plain at about 26 s over 6.6 M queries.
- **Java:** harness and reflective JUnit runs take under 5 s each.
- **Total** agent wall time is about 40 min (20:11 to 20:50 local), including the 7m19s full run.

## Files

- **Model and proofs:** `Trie/*.lean`, `Main.lean`, `lakefile.toml`, `lean-toolchain`.
- **Results:** `results/full-run.txt`, `results/axioms.txt`, `results/java-repro.txt`, `results/formal-test-as-written.txt`, `results/junit-as-written-vs-fixed.txt`.
- **Fix plan:** `fix-plan.patch` and `java-check/fixed-src/`.
- **New failing-first test:** `com.avaloq.tools.ddk.xtext.test/src/com/avaloq/tools/ddk/xtext/naming/QualifiedNameLookupFormalTest.java` (13 tests; all fail on current code; not registered in any suite).
