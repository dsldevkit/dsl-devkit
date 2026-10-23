# QualifiedName trie / pattern lookup: TLA+ bounded cross-check

Blind study of `QualifiedNameSegmentTreeLookup`, `QualifiedNamePattern`, `TreeSetLookup`
and `PatternAwareEObjectDescriptionLookUp` (all under `com.avaloq.tools.ddk.xtext/src/...`).
The model follows the code as written; the minimal fixes are separate constant flags.
Checking is **bounded model checking with TLC (BFS)**. Nothing here is a proof.

## Files

| file | lines | what |
|---|---|---|
| `QNBase.tla` | 211 | Java `String.compareTo`, `toLowerCase`, `regionMatches`, `(char)(c+1)`; Xtext `QualifiedName.compareTo` (javap of xtext 2.44.0: segment-wise then count diff); `QualifiedNamePattern.matches/lowerInclusive/upperExclusive/compareTo/Comparator`, glob matching as `Regexps.fromGlob(glob, true)` compiles it. Fix flags `FIX_BOUNDS FIX_MATCH FIX_GLOB FIX_CMP`. |
| `TrieCore.tla` | 109 | `SegmentNode.find`, `collectMatches`, `visitChildren`, `binarySearch`, `get(name)`, `get(pattern)`, both visitors (plain and value-sharing). `PLANT_TRIE` = planted bug. |
| `PatternBounds.tla` | 50 | Pattern-level properties; one state per (pattern, name) pair. |
| `TrieQuery.tla`, `MC_TQ.tla`, `MC_TQ2.tla` | 55+20+17 | Insert names one by one; after each insert, compare every pattern's trie `get(pattern)` with the spec and with `TreeSetLookup`. |
| `Consumer.tla`, `MC_Cons.tla` | 50+14 | `PatternAwareEObjectDescriptionLookUp.getExportedObjects(type, name, ignoreCase)` over a lower-cased trie. `FIX_CONSUMER`. |
| `TrieOps.tla`, `MC_Ops.tla` | 165+10 | `put/putAll/remove/removeMappings/clear/initializeFrom`, with and without value sharing, against a set-of-pairs spec and `TreeSetLookup`. Arrays carry an identity. Flags `FIX_SIZE FIX_ADDALL FIX_SHARE FIX_COPY FIX_MAPPINGS`. |
| `cfg/*.cfg`, `run.sh`, `runall.sh`, `out/` | | 44 configurations; `./runall.sh` re-runs all of them; `out/SUMMARY.txt` holds the last matrix and `out/<cfg>.log` the traces. |

## Modelling assumptions

* Strings are sequences of UTF-16 code units. Case mapping is ASCII only (`A`→`a`), and only the sign of `compareTo` is kept.
* The alphabet is small: `' '` (0x20) stands for every code unit below `'!'`. Some runs also use `\0`, `'!'`, `'A'`, `'a'`, `'b'` and `￿`.
* Trie nodes are identified by their path. Children are never removed and never repeat a segment, so path equality equals Java object identity (`child == upper`, `stopOn.equals(child)`: `SegmentNode` does not override `equals`).
* `binarySearch` is abstracted to "found index, else insertion point". The children list is kept sorted because `merge` always inserts at the insertion point.
* `children == null` is modelled as "no children". Empty non-null lists are unreachable: `merge` always adds a child after creating the list, and `init()` re-adds the sentinel after `clear()`.
* In `TrieQuery` each stored name maps to itself as its value, as the existing tests do. `TrieOps` uses two values and duplicate-carrying `putAll` collections.
* `TreeSetLookup.get(pattern)` is modelled as a filter over the stored keys with `QualifiedNamePattern.Comparator`. This equals the TreeMap `subMap` walk because the pattern-vs-name comparison is a threshold on sorted keys; I checked that argument by hand for the empty-segment case. `subMap(lo, hi)` with `compare(lo, hi) > 0` throws IAE and is modelled as `EXC`.
* The consumer assumes the `EClass` filter always passes.
* Out of scope: concurrency (`hits/misses` counters, the volatile lazy init), serialization, glob patterns in the trie traversal (`createFromGlobs` has no production caller), and `put/remove` with an empty name (`getSegment(0)` throws; noted only).

## Properties

| property | where | meaning |
|---|---|---|
| `RangeSound` | PatternBounds | `matches(p,n)` ⇒ `lowerInclusive(p) ≤ n < upperExclusive(p)` (QualifiedName order, as the trie walks it) |
| `RangeSoundTS` | PatternBounds | the same bound, but under `QualifiedNamePattern.Comparator` (TreeMap's order) |
| `BoundsOrdered` | PatternBounds | `lower ≤ upper` (otherwise `TreeMap.subMap` throws) |
| `CountFilterSound` | PatternBounds | a non-recursive match has exactly the pattern's segment count (the filter `TreeSetLookup` relies on) |
| `NoException` | PatternBounds | `matches` never throws |
| `TrieEqSpec` (+`NonTop`/`Star`/`Superset` variants) | TrieQuery | trie `get(p)` = {stored n : `p.matches(n)`} |
| `TrieEqRef`, `RefEqSpec` | TrieQuery | trie = TreeSetLookup; TreeSetLookup = spec |
| `ExactGetOK`, `ExactGetSpec`, `ExactGetRef` | TrieQuery/Ops | `get(name)` = the values stored under that name |
| `ConsCS`, `ConsCI`, `ConsCSinCI` | Consumer | case-sensitive and case-insensitive results equal their spec, and a case-sensitive hit is also a case-insensitive hit |
| `SizeTree`, `SizeSpec` | Ops | `size` = mappings held in the tree = number of distinct (name, value) pairs |
| `NoDupValues` | Ops | no value appears twice in a node's array |
| `GetMappingsSpec` | Ops | `getMappings(v)` = the names mapped to v |
| `PatternBagRef` | Ops | `get(p, false)` has the same multiplicities as TreeSetLookup |
| `CopySize` | Ops | a lookup initialised via `initializeFrom` keeps a correct `size` |

Witnesses (each is expected to be violated, which shows a good path is reachable): `WitnessMatchMulti`, `WitnessRecursive`, `WitnessCSPattern`, `WitnessShared`, `WitnessRemoveAll`.

## Bounds and stats

The configuration matrix is in `out/SUMMARY.txt` (44 runs, 112 s wall with `-workers auto` on 14 cores). The full study took about 20 min of TLC time plus modelling.

| check | bound | distinct states | result |
|---|---|---|---|
| `pb_fixed` (non-glob, all 5 properties, all fixes) | 7-char alphabet incl. `\0 ' ' ! A a b ￿`, segs ≤2, names ≤2 segs, patterns ≤2 segs | 714,096 (p,n) pairs | pass, 13 s |
| `pbg_fixed` (glob) | alphabet `' ' A a ￿`, glob segs over `*`/`?` ≤2 | 874,104 pairs | pass, 19 s |
| `tq_fixed`, `tq_fixed_ref` | 22 names (1–3 segs), 72 patterns, ≤4 stored names | 9,109 | pass |
| `tq_fixed5` (spec + reference) | ≤5 stored names (all 35,443 subsets) | 35,443 | pass, 2 min 05 s |
| `tq2_star` (code as written, wildcard patterns other than top-level, no chars < `!`) | 19 names up to 4 segs, ≤4 stored | 4,048 | pass |
| `co_fixed` | 11 case-mixed names, 47 queries × 2 case modes, ≤4 stored | 562 | pass |
| `op_fixed` / `op_fixed_sh` | 3 names, 2 values, ≤4 ops (+ copy) | 8,256 / 50,488 | pass |
| `op_fixed5` / `op_fixed_sh5` | ≤5 ops | 31,048 / 395,137 | pass, 3 s / 29 s |

Sanity:

* **Fixed model passes.** Every fix flag on, all properties hold at the bounds above.
* **Planted bugs are caught.** `pb_plant` sets upper = lower. `tq_plant` and `op_plant` start the last-segment loop one child late. All three fail at depth 1–2.
* **Witnesses are reachable.** All five fail as expected.

## Findings

Each finding lists the shortest counterexample (BFS, `-workers 1`), the steps mapped to code, and a verdict from re-reading the code. Line numbers are in the file named.

### F1 (high): single-segment `*` / `**` never returns ordinary names. CONFIRMED
* Trace (`pb_rs`, `tq_spec`, `co_ci`): store `a`. `get(QualifiedNamePattern.create("*"))` returns `{}`, but `"*".matches(a)` is true.
* Steps:
  * `QualifiedNamePattern.java:338-341` gives lower = `[""]`.
  * `:372-374` gives upper = `["!"]`, and `testAllPattern` asserts exactly this.
  * `QualifiedNameSegmentTreeLookup.java:563` resolves upper with `find(["!"], 0, false)` (`:104-114`), which returns the first root child ≥ `"!"`, i.e. node `a`.
  * `collectMatches:188-192` then sees `child == upper` straight away and returns nothing.
* Every letter, digit, `_` and `$` sorts above `'!'` (0x21), so `*`/`**` only ever find names that start with a control char or a space. `TreeSetLookup` (`subMap([""], ["!"])`) behaves the same. The glob branch has the same bug (`:357-359`); a glob without a wildcard is also forced to lower `[""]` because `firstWildcardSeg` starts at 0 (`:315`), which leaves `:332` always true.
* Reach: `ContainerQuery.Builder.name("*")` (`ContainerQuery.java:432`), and any `PrefixedContainerBasedScope` built with an empty prefix (`:69`).
* Minimal fix, as modelled: an unbounded upper for this case. The trie maps it to the sentinel node, and TreeSetLookup would use a tail map.

### F2 (medium): case-sensitive pattern queries always return nothing. CONFIRMED
* Trace (`co_cs_fb`, bounds already fixed): store `A`. `getExportedObjects(type, create("*"), false)` returns `{}`, but `A` matches. Any pattern gives the same result.
* Steps: `PatternAwareEObjectDescriptionLookUp.java:53-55` fetches the candidates, then `:63` filters them with `((QualifiedNamePattern) name).matches(name)`. That tests the pattern against itself, and `QualifiedNamePattern.java:263` returns false whenever the argument is a `QualifiedNamePattern`.
* Present since the initial contribution (`git log -S`).
* Reach: `ContainerQuery.execute:300` with `doIgnoreCase = false` and a name pattern.
* Fix: `matches(input.getName())`. With that fix and F1's fix, `co_fixed` passes and `ConsCSinCI` holds.

### F3 (low): upper bounds append `'!'`, so names using chars 0x00–0x20 leak in. CONFIRMED
* Trace (`tq_star`): store `"".a` and `" ".a`. Pattern `"".*` returns both. Written with letters: pattern `a.*` returns `a .x`, and pattern `a` returns `a ` (`tq_spec`).
* Steps: `QualifiedNamePattern.java:379/382/386` (and `:363` for globs) build `seg + '!'`. The `'!'` is meant as "just after every name that starts with `seg`", but `seg + ' '` sits between `seg` and `seg + '!'`.
* `RangeSoundNonTop` passes, so no match is ever dropped. The problem is extra results that nobody filters out: the trie does not re-check `matches`, and the consumer's ignore-case path (`:62`) only checks the type.
* Minimal fix: append `'\0'` (the immediate successor). With it the trie equals the spec for every non-glob pattern (`tq_fixed`, `tq_fixed5`).

### F4 (low): trie and TreeSetLookup over-approximate differently in the F3 gap. CONFIRMED
* Trace (`tq_ref`): store `""` and `" "`. Pattern `"".**`: the trie returns `{}` and TreeSetLookup returns `{" "}`.
* Steps: the sibling loop `QualifiedNameSegmentTreeLookup.java:202-207` calls `child.visitChildren(...)`, which visits only the sibling's descendants (`:223-237`), never the sibling itself. TreeSetLookup takes the whole range.
* This is unreachable once F3 is fixed: no node lies strictly between `seg` and `seg+'\0'`. `tq2_star` confirms the trie is exact for non-top wildcard patterns when no char below `'!'` is present.

### F5 (low): a wildcard-free pattern matches longer names. CONFIRMED
* Trace (`pb_cnt1`, found as `[""]` vs `["",""]`; same shape as `create("a").matches(a.b)`): the result is `true`. Both lookups return only `a`, so `get(p)` ≠ {n : p.matches(n)}.
* Steps: the loop `QualifiedNamePattern.java:278-303` never compares segment counts when the pattern has no wildcard, and `:304` returns true. The lookups follow the intended exact semantics: `testNestedPatternMatchesWithoutWildcard` expects `pattern("foo")` → `foo` only.
* Fix: `return other.getSegmentCount() == getSegmentCount();` at `:304` (`FIX_MATCH`).

### F6 (low): statistics `size` over-counts. CONFIRMED
* Traces:
  * `op_sizetree`: `put(a,v1); put(a,v1)` gives size 2 for 1 mapping.
  * `op_sizespec`: `putAll(a,[v1,v1])` gives size 2.
* Steps: `QualifiedNameSegmentTreeLookup.java:590` does `size++` and `:601` does `size += values.size()`, both unconditionally. `merge` dedups (`ArrayUtils.addAll`) or stores the array as is (`:263`).
* Effect: `getStatistics()` is wrong. `initializeFrom` (`:639`) can also throw "must not initialize non-empty object" on a lookup that is actually empty, e.g. after `put(a,v); put(a,v); remove(a,v)`.
* Fix: add only what was actually added (`FIX_SIZE`).

### F7 (low): `putAll` stores duplicate values, so `remove` leaves the value mapped. CONFIRMED
* Trace (`op_exact`): `putAll(a,[v1,v1]); remove(a,v1)` leaves `get(a) = [v1]`, although the mapping was removed.
* Steps: when a new node is created it takes `newValues` unchanged (`QualifiedNameSegmentTreeLookup.java:263`/`:436`). `ArrayUtils.addAll:78-80` returns `values` unchanged, and `:83` compares against the original `array` rather than the growing `result`. `remove` (`:610`, `ArrayUtils.remove`) deletes only one occurrence. This contradicts `QualifiedNameLookup.putAll`: "added (unless already present)".
* `TreeSetLookup.putAll` shares `ArrayUtils.addAll`, so the reference has the same behaviour (`op_exactref` passes).
* Fix: dedup in `putAll` (the `:263` path) and check against `result` at `:83`.

### F8 (low): with `shareValues=true`, `get(pattern, false)` collapses multiplicities. CONFIRMED
* Trace (`op_share`): `put(a,v1); put(a.b,v1)` (the child now shares the parent's array, `:436`). `get(a**, false)` returns `[v1]`, while TreeSetLookup returns `[v1, v1]`.
* Steps: `QualifiedNameSegmentTreeLookup.java:406-420` collects `Set<Object[]>`, which dedups arrays by identity. So one array is counted once however many matched nodes hold it, while equal but distinct arrays are counted every time.
* `shareValues=true` has no production caller in this repo; the tests use it.

### F9 (low / PLAUSIBLE): `initializeFrom` aliases the source's mutable tree. PLAUSIBLE
* Trace (`op_copy`): `B.initializeFrom(A); A.put(a,v1)` makes `B.get(a)` see `v1` while `B.size` stays 0. `A.clear()` would also empty `B`.
* Steps: `QualifiedNameSegmentTreeLookup.java:645-646` copies `root` by reference. The javadoc says "shallow copy, values are shared", so whether this is a bug depends on the intended contract. There is no caller in this repo.

### F10 (low): `getMappings` drops blank inner segments. CONFIRMED
* Trace (`op_map`): `put([" ", "b"], v1)` gives `getMappings(v1) = [b]`.
* Steps: `QualifiedNameSegmentTreeLookup.java:295` skips every blank segment (`isBlank`: empty or whitespace) to leave out the root's `""`, and that also drops real `""`/`" "` segments of inner nodes. Fix: skip only the root.

### F11 (low): glob patterns match case-insensitively but range case-sensitively. CONFIRMED (test-only API)
* Trace (`pbg_rs_fbA`, bounds already fixed): `createFromGlobs("A*").matches(a)` is true, but the range is `["A","B")`.
* Steps: `QualifiedNamePattern.java:101` calls `Regexps.fromGlob(from)`, which is `fromGlob(glob, true)` (`Regexps.java:54`, CASE_INSENSITIVE). The bounds at `:312-368` use `String.compareTo`.
* The lookups also ignore glob semantics entirely (TreeSetLookup: "TODO implement strict matching for regexp patterns", `:409`). `createFromGlobs` has no production caller.

### F12 (low): glob `matches` throws on an empty last segment. CONFIRMED
* Trace (`pbg_exc`): `createFromGlobs("").matches([""])` throws `StringIndexOutOfBoundsException` from `lastSeg.charAt(-1)` (`QualifiedNamePattern.java:268`).

### F13 (low): a `￿` before the wildcard wraps the upper bound below the lower. CONFIRMED (edge)
* Trace (`pb_ord`): pattern `￿*` gives lower `￿` and upper `(char)0x10000 = \0`.
* Steps: `QualifiedNamePattern.java:389` (and `:367` for globs).
* Effect: `TreeMap.subMap` throws IAE. The trie never meets `upper` and walks to the end of the tree.
* The fixed model carries the prefix to the next position instead of wrapping.

### F14 (low): TreeSetLookup's comparator returns wrong results for patterns with an empty segment. CONFIRMED (reference impl only)
* Trace (`tq2_ref`, `tq_fixed_ref` before `FIX_CMP`): store `"".a`. Wildcard-free pattern `"".b`: TreeSetLookup returns `"".a`, the trie returns `{}` (correct).
* Steps: `QualifiedNamePattern.java:224-225` returns -1 as soon as a pattern segment is empty, so the lower bound sorts before every key. `TreeSetLookup` has no production user.
* Fix: drop that shortcut (`FIX_CMP`).

### Checked and found sound (at the bounds)
* `get(name)` exact lookup (`tq_exact`).
* Wildcard patterns other than top-level `*`/`**` on names without chars below `'!'` (`tq2_star`).
* `find(..., exactMatch=false)` returning `null` for a missing non-last segment (`:105-107`), which only happens when the lower path is also missing.
* `LastLoop` ignoring `visitChildren`'s result (`:195`): upper is never below a child at that depth.
* Remove and `removeMappings` size accounting, apart from F6.
