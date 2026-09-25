----------------------------- MODULE TrieQuery -----------------------------
(***************************************************************************)
(* Query-side check: names are inserted one at a time (put(name, name), as *)
(* in QualifiedNameSegmentTreeLookupTest); after every insertion, for every *)
(* pattern in the universe, get(pattern) on the trie is compared with the   *)
(* spec (stored names that pattern.matches) and with TreeSetLookup.         *)
(* BFS makes the first counterexample one with the fewest stored names.     *)
(***************************************************************************)
EXTENDS TrieCore, TLC

CONSTANTS NAMES,     \* universe of stored names
          PATTERNS,  \* universe of query patterns
          MAXN       \* max number of stored names

VARIABLES stored

Init == stored = {}
Next == \E n \in NAMES \ stored : Cardinality(stored) < MAXN /\ stored' = stored \cup {n}

\* Trie: nodes = all prefixes of stored names plus the sentinel; value of a
\* stored node = <<name>>; intermediate nodes have null values.
TN == UNION {Prefixes(n) : n \in stored} \cup {SENTINEL}
TV == [x \in TN \cup {<<>>} |-> IF x \in stored THEN [id |-> x, el |-> <<x>>] ELSE NULL]

TrieGet(p) == LET l == ConcatVals(TV, PatternVisits(TN, p)) IN {l[i] : i \in 1..Len(l)}
SpecGet(p) == {n \in stored : Matches(p, n)}

\* TreeSetLookup.get(pattern) -> QualifiedNamePattern.findNestedArrayMatches (:408-426)
TSGet(p) ==
  LET lo == LowerInclusive(p)
      hi == UpperExclusive(p)
  IN IF hi # INF /\ Comparator(lo, Plain(hi)) > 0 THEN EXC            \* TreeMap.subMap IAE
     ELSE {k \in stored : Comparator(Plain(k), lo) >= 0
                          /\ (hi = INF \/ Comparator(Plain(k), Plain(hi)) < 0)
                          /\ (IsRecursive(p) \/ Len(k) = Len(p.segs))}

\* Assert-wrapped check so TLC prints the offending pattern and both sides
Chk(ok, p, what, l, r) == Assert(ok, <<what, "pattern", p, "impl", l, "expected", r>>)

TopAll(p) == Len(p.segs) = 1 /\ LastSeg(p.segs) \in {ONESTAR, STARSTAR}

TrieEqSpec == \A p \in PATTERNS : Chk(TrieGet(p) = SpecGet(p), p, "trie/spec", TrieGet(p), SpecGet(p))
TrieEqSpecNonTop == \A p \in PATTERNS : ~TopAll(p) => Chk(TrieGet(p) = SpecGet(p), p, "trie/spec", TrieGet(p), SpecGet(p))
\* Over-approximation only (no match dropped)
TrieSupersetSpec == \A p \in PATTERNS : ~TopAll(p) => Chk(SpecGet(p) \subseteq TrieGet(p), p, "trie drops", TrieGet(p), SpecGet(p))
TrieEqRef == \A p \in PATTERNS : Chk(TrieGet(p) = TSGet(p), p, "trie/treeset", TrieGet(p), TSGet(p))
RefEqSpec == \A p \in PATTERNS : Chk(TSGet(p) = SpecGet(p), p, "treeset/spec", TSGet(p), SpecGet(p))
\* Excluding patterns without a wildcard (matches() prefix behaviour)
HasStar(p) == IndexOf(LastSeg(p.segs), STAR) # -1
TrieEqSpecStar == \A p \in PATTERNS : (~TopAll(p) /\ HasStar(p)) => Chk(TrieGet(p) = SpecGet(p), p, "trie/spec", TrieGet(p), SpecGet(p))
ExactGetOK == \A n \in NAMES : ExactGet(TN, TV, n) = IF n \in stored THEN <<n>> ELSE NULL

\* Witnesses (expected violated): recursive pattern returns >= 3 names
WitnessRecursive == \A p \in PATTERNS : IsRecursive(p) => Cardinality(TrieGet(p)) < 3
=============================================================================
