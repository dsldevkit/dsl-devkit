---------------------------- MODULE PatternBounds ----------------------------
(***************************************************************************)
(* Pure pattern-level properties of QualifiedNamePattern: every name the    *)
(* pattern matches must lie in [lowerInclusive, upperExclusive), both as    *)
(* the trie sees the range (plain QualifiedName order) and as TreeMap sees  *)
(* it (QualifiedNamePattern.Comparator).  Each state is one (pattern, name) *)
(* pair, so a violation is reported with its witness.                        *)
(***************************************************************************)
EXTENDS QNBase

CONSTANTS CHARS,      \* name alphabet (code units)
          SEGLEN,     \* max segment length in names
          NAMELEN,    \* max segments in names
          PATLEN,     \* max segments in patterns
          PSEGLEN,    \* max literal length before the wildcard
          GLOBS,      \* TRUE: glob patterns (createFromGlobs), FALSE: create(...)
          PLANT       \* planted bug: upperExclusive := lowerInclusive (empty range)

Segs == Strs(CHARS, SEGLEN)
Names == QNs(Segs, NAMELEN)
PSegs == Strs(CHARS, PSEGLEN)
GlobSegs == Strs(CHARS \cup {STAR, QMARK}, PSEGLEN + 1)
Patterns == IF GLOBS THEN {[segs |-> q, glob |-> TRUE] : q \in QNs(GlobSegs, PATLEN)}
            ELSE Pats(PSegs, PSegs, PATLEN)

VARIABLES p, n
Init == p \in Patterns /\ n \in Names
Next == UNCHANGED <<p, n>>

Lo == LowerInclusive(p)
Up == IF PLANT THEN Lo.qn ELSE UpperExclusive(p)
M  == Matches(p, n)

\* Trie view: plain QualifiedName order (the trie walks lo segment by segment)
RangeSound == M => (QNCmp(Lo.qn, n) <= 0 /\ (Up = INF \/ QNCmp(n, Up) < 0))
\* TreeMap view (TreeSetLookup): subMap(lo, hi) keeps k iff compare(k,lo) >= 0 /\ compare(k,hi) < 0
RangeSoundTS == M => (Comparator(Plain(n), Lo) >= 0 /\ (Up = INF \/ Comparator(Plain(n), Plain(Up)) < 0))
\* TreeMap.subMap throws IllegalArgumentException("fromKey > toKey") otherwise
BoundsOrdered == Up = INF \/ (Comparator(Lo, Plain(Up)) <= 0 /\ QNCmp(Lo.qn, Up) <= 0)
\* Non-recursive lookups only return names with the pattern's segment count
CountFilterSound == (M /\ ~IsRecursive(p)) => Len(n) = Len(p.segs)
NoException == ~MatchThrows(p, n)
\* Same as RangeSound but ignoring the single-segment "*" / "**" patterns
TopAll == Len(p.segs) = 1 /\ LastSeg(p.segs) \in {ONESTAR, STARSTAR}
RangeSoundNonTop == ~TopAll => RangeSound

\* Witnesses (expected to be violated: good paths are reachable)
WitnessMatchMulti == ~(M /\ Len(n) > Len(p.segs))
WitnessMatch == ~(M /\ Len(n) = 2)
=============================================================================
