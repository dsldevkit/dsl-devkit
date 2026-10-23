------------------------------ MODULE TrieCore ------------------------------
(***************************************************************************)
(* QualifiedNameSegmentTreeLookup.SegmentNode, modelled line by line.       *)
(* A node is identified by its path (the QN of segments from the root);     *)
(* the root is <<>>.  Since children are never removed and never duplicate  *)
(* a segment, path identity coincides with Java object identity (used by    *)
(* child == upper and stopOn.equals(child)).  N is the set of non-root      *)
(* paths, V maps each path (and <<>>) to NULL or an array [id, el].        *)
(* Children are kept sorted by String.compareTo (merge inserts at the       *)
(* binarySearch insertion point), so Kids() sorts the child segments.      *)
(***************************************************************************)
EXTENDS QNBase

CONSTANT PLANT_TRIE   \* planted bug: last-segment loop starts one child too late

SENTINEL == << <<MAXC>> >>             \* init(): root.merge(["￿"], 0, null)

RECURSIVE SortSegs(_)
SortSegs(S) == IF S = {} THEN <<>>
               ELSE LET m == CHOOSE x \in S : \A y \in S : StrCmp(x, y) <= 0
                    IN <<m>> \o SortSegs(S \ {m})

Kids(N, p) ==
  LET segs == SortSegs({LastSeg(c) : c \in {c \in N : Len(c) = Len(p) + 1 /\ SubSeq(c, 1, Len(p)) = p}})
  IN [i \in 1..Len(segs) |-> Append(p, segs[i])]

\* binarySearch, :320-338 (1-based: found index, or insertion index 1..Len+1)
BinSearch(ks, seg) ==
  IF \E i \in 1..Len(ks) : LastSeg(ks[i]) = seg
  THEN [found |-> TRUE, idx |-> CHOOSE i \in 1..Len(ks) : LastSeg(ks[i]) = seg]
  ELSE [found |-> FALSE, idx |-> 1 + Cardinality({i \in 1..Len(ks) : StrCmp(LastSeg(ks[i]), seg) < 0})]

(* find(name, segIdx, exactMatch), :97-121.  i = segIdx (0-based). *)
RECURSIVE Find(_, _, _, _, _)
Find(N, p, name, i, exact) ==
  LET ks == Kids(N, p) IN
  IF ks = <<>> THEN NULL                                            \* :98-100
  ELSE LET seg  == name[i + 1]                                      \* :101
           last == Len(name) = i + 1                                \* :102
           bs   == BinSearch(ks, seg)                               \* :103
       IN IF ~bs.found /\ (exact \/ ~last) THEN NULL                \* :104-107
          ELSE IF bs.idx = Len(ks) + 1 THEN NULL                    \* :110-112
          ELSE IF last THEN ks[bs.idx]                              \* :113-115
          ELSE LET r == Find(N, ks[bs.idx], name, i + 1, exact)     \* :116
               IN IF r = NULL /\ ~exact                             \* :117-119
                  THEN (IF bs.idx + 1 = Len(ks) + 1 THEN NULL ELSE ks[bs.idx + 1])
                  ELSE r

(* visitChildren(visitor, stopOn), :223-237 -> [cont, vis] *)
RECURSIVE VC(_, _, _), VCLoop(_, _, _, _)
VC(N, p, stop) == VCLoop(N, Kids(N, p), 1, stop)                    \* :224-226 (no kids => TRUE)
VCLoop(N, ks, k, stop) ==
  IF k > Len(ks) THEN [cont |-> TRUE, vis |-> <<>>]                 \* :236
  ELSE IF ks[k] = stop THEN [cont |-> FALSE, vis |-> <<>>]          \* :228-230
  ELSE LET r == VC(N, ks[k], stop) IN                               \* :231-232
       IF ~r.cont THEN [cont |-> FALSE, vis |-> <<ks[k]>> \o r.vis] \* :233
       ELSE LET r2 == VCLoop(N, ks, k + 1, stop)
            IN [cont |-> r2.cont, vis |-> <<ks[k]>> \o r.vis \o r2.vis]

(* collectMatches(lower, lowerIdx, upper, recursive, visitor), :173-211 -> [ret, vis] *)
RECURSIVE CM(_, _, _, _, _, _), LastLoop(_, _, _, _, _), SibLoop(_, _, _, _)
LastLoop(N, ks, k, upper, rec) ==                                   \* :188-198
  IF k > Len(ks) THEN [ret |-> TRUE, vis |-> <<>>]
  ELSE IF ks[k] = upper THEN [ret |-> FALSE, vis |-> <<>>]          \* :190-192
  ELSE LET sub == IF rec THEN VC(N, ks[k], upper).vis ELSE <<>>     \* :193-196 (result ignored)
           r2  == LastLoop(N, ks, k + 1, upper, rec)
       IN [ret |-> r2.ret, vis |-> <<ks[k]>> \o sub \o r2.vis]
SibLoop(N, ks, k, upper) ==                                         \* :202-207
  IF k > Len(ks) THEN [ret |-> TRUE, vis |-> <<>>]
  ELSE IF ks[k] = upper THEN [ret |-> FALSE, vis |-> <<>>]
  ELSE LET r == VC(N, ks[k], upper) IN                              \* child itself is NOT visited
       IF ~r.cont THEN [ret |-> FALSE, vis |-> r.vis]
       ELSE LET r2 == SibLoop(N, ks, k + 1, upper) IN [ret |-> r2.ret, vis |-> r.vis \o r2.vis]
CM(N, p, lower, i, upper, rec) ==
  LET ks == Kids(N, p) IN
  IF ks = <<>> \/ p = upper \/ upper = NULL THEN [ret |-> FALSE, vis |-> <<>>]   \* :174-176
  ELSE LET seg  == lower[i + 1]                                     \* :177
           last == Len(lower) = i + 1
           bs   == BinSearch(ks, seg)                               \* :178
       IN IF ~bs.found /\ ~last THEN [ret |-> FALSE, vis |-> <<>>]  \* :181-183
          ELSE IF bs.idx = Len(ks) + 1 THEN [ret |-> TRUE, vis |-> <<>>]  \* :184-186
          ELSE IF last THEN LastLoop(N, ks, IF PLANT_TRIE THEN bs.idx + 1 ELSE bs.idx, upper, rec)
          ELSE LET r == CM(N, ks[bs.idx], lower, i + 1, upper, rec) \* :200
               IN IF r.ret THEN LET r2 == SibLoop(N, ks, bs.idx + 1, upper)
                                IN [ret |-> r2.ret, vis |-> r.vis \o r2.vis]
                  ELSE r                                            \* :209

(* QualifiedNameSegmentTreeLookup.get(pattern, excl), :562-564: visited node list *)
UpperNode(N, p) ==
  LET u == UpperExclusive(p) IN
  IF u = INF THEN SENTINEL            \* fixed bounds: unbounded => stop at the sentinel
  ELSE Find(N, <<>>, u, 0, FALSE)
PatternVisits(N, p) == CM(N, <<>>, LowerInclusive(p).qn, 0, UpperNode(N, p), IsRecursive(p)).vis

\* SegmentNode.matches visitor (:143-152): list of values in visiting order
RECURSIVE ConcatVals(_, _)
ConcatVals(V, vis) == IF vis = <<>> THEN <<>>
                      ELSE (IF V[Head(vis)] = NULL THEN <<>> ELSE V[Head(vis)].el) \o ConcatVals(V, Tail(vis))
\* ValueSharingSegmentNode.matches (:404-422): distinct arrays by identity, then their values
SharedVals(V, vis) == {V[x] : x \in {x \in {vis[i] : i \in 1..Len(vis)} : V[x] # NULL}}

(* get(QualifiedName), :527-539 *)
ExactGet(N, V, q) ==
  IF Len(q) = 0 THEN NULL
  ELSE LET r == Find(N, <<>>, q, 0, TRUE) IN
       IF r # NULL /\ V[r] # NULL THEN V[r].el ELSE NULL

Prefixes(q) == {SubSeq(q, 1, k) : k \in 1..Len(q)}
=============================================================================
