------------------------------ MODULE TrieOps ------------------------------
(***************************************************************************)
(* Mutation side of QualifiedNameSegmentTreeLookup: put / putAll / remove / *)
(* removeMappings / clear / initializeFrom, with or without value sharing,  *)
(* against an abstract set-of-pairs spec and TreeSetLookup.  Arrays carry   *)
(* an identity (id) so that ValueSharingSegmentNode's Set<Object[]> and the *)
(* "newValues != node.values" checks are faithful.                          *)
(***************************************************************************)
EXTENDS TrieCore, TLC

CONSTANTS NAMES, VALS, PUTALL_SEQS, OPS_PATTERNS, MAXSTEPS,
          SHARE,          \* new QualifiedNameSegmentTreeLookup(clazz, shareValues)
          FIX_SIZE,       \* put/putAll count only mappings actually added
          FIX_ADDALL,     \* ArrayUtils.addAll dedups against the growing result
          FIX_SHARE,      \* ValueSharing matches: one array per visited node
          FIX_COPY,       \* initializeFrom deep-copies the tree
          FIX_MAPPINGS    \* getMappings keeps blank segments (skips only the root)

VARIABLES N, V, size, nid, pairs, ts, copied, csize, snapN, snapV, steps
vars == <<N, V, size, nid, pairs, ts, copied, csize, snapN, snapV, steps>>

Range(s) == {s[i] : i \in 1..Len(s)}
ElLen(a) == IF a = NULL THEN 0 ELSE Len(a.el)
RECURSIVE SumOver(_, _)
SumOver(f, S) == IF S = {} THEN 0 ELSE LET x == CHOOSE x \in S : TRUE IN f[x] + SumOver(f, S \ {x})
Count(Nn, Vv) == SumOver([x \in Nn |-> ElLen(Vv[x])], Nn)
Fresh == IF SHARE THEN nid ELSE 0             \* identities only matter with sharing
NextId == IF SHARE THEN nid + 1 ELSE nid

(* ---------------- ArrayUtils ---------------- *)
Find1(s, v) == IF \E i \in 1..Len(s) : s[i] = v THEN CHOOSE i \in 1..Len(s) : s[i] = v /\ \A j \in 1..(i-1) : s[j] # v ELSE 0
RECURSIVE Dedup(_)
Dedup(s) == IF s = <<>> THEN <<>> ELSE LET t == Dedup(SubSeq(s, 1, Len(s) - 1)) IN
            IF s[Len(s)] \in Range(t) THEN t ELSE Append(t, s[Len(s)])
RECURSIVE AddLoop(_, _, _)
AddLoop(orig, res, vs) ==                                   \* ArrayUtils.addAll :81-91
  IF vs = <<>> THEN res
  ELSE LET chk == IF FIX_ADDALL THEN res ELSE orig          \* :83 find(array, value) - original array
       IN AddLoop(orig, IF Find1(chk, Head(vs)) = 0 THEN Append(res, Head(vs)) ELSE res, Tail(vs))
AddAll(arr, newArr, id) ==
  IF arr = NULL \/ Len(arr.el) = 0
  THEN (IF FIX_ADDALL /\ Dedup(newArr.el) # newArr.el THEN [id |-> id, el |-> Dedup(newArr.el)] ELSE newArr)  \* :78-80
  ELSE LET r == AddLoop(arr.el, arr.el, newArr.el) IN
       IF r = arr.el THEN arr ELSE [id |-> id, el |-> r]
AddOne(s, v) == IF s = NULL THEN <<v>> ELSE IF v \in Range(s) THEN s ELSE Append(s, v)   \* ArrayUtils.add
\* ArrayUtils.remove on an array record; [arr, changed]
RemoveArr(arr, v, id) ==
  IF arr = NULL THEN [a |-> NULL, ch |-> FALSE]
  ELSE LET i == Find1(arr.el, v) IN
       IF i = 1 /\ Len(arr.el) = 1 THEN [a |-> NULL, ch |-> TRUE]
       ELSE IF i > 0 THEN [a |-> [id |-> id, el |-> SubSeq(arr.el, 1, i - 1) \o SubSeq(arr.el, i + 1, Len(arr.el))], ch |-> TRUE]
       ELSE [a |-> arr, ch |-> FALSE]
RemoveSeq(s, v) == IF s = NULL THEN NULL ELSE
                   LET i == Find1(s, v) IN
                   IF i = 1 /\ Len(s) = 1 THEN NULL ELSE IF i > 0 THEN SubSeq(s, 1, i - 1) \o SubSeq(s, i + 1, Len(s)) ELSE s
ArrEq(a, b) == (a = NULL /\ b = NULL) \/ (a # NULL /\ b # NULL /\ a.el = b.el)   \* Arrays.equals

(* ---------------- merge (put / putAll), :252-273 and :425-446 ---------------- *)
MergeV(n, newArr, id) ==
  LET par == SkipLast(n, 1)
      vp  == IF par \in DOMAIN V THEN V[par] ELSE NULL     \* receiver's own values (new node: null)
  IF_NEW == IF SHARE /\ ArrEq(vp, newArr) THEN vp ELSE newArr               \* :263 / :436
  IN IF n \notin N
     THEN [x \in N \cup Prefixes(n) \cup {<<>>} |-> IF x = n THEN IF_NEW ELSE IF x \in N \cup {<<>>} THEN V[x] ELSE NULL]
     ELSE LET tmp == AddAll(V[n], newArr, id)                               \* :268 / :441
          IN [V EXCEPT ![n] = IF SHARE /\ ArrEq(vp, tmp) THEN vp ELSE tmp]  \* :269 / :442

Init ==
  /\ N = {SENTINEL} /\ V = [x \in {<<>>, SENTINEL} |-> NULL]
  /\ size = 0 /\ nid = 1 /\ pairs = {} /\ ts = [n \in NAMES |-> NULL]
  /\ copied = FALSE /\ csize = 0 /\ snapN = {} /\ snapV = <<>> /\ steps = 0

Step == steps < MAXSTEPS /\ steps' = steps + 1

Put(n, v) ==                                                 \* :584-592
  LET arr == [id |-> Fresh, el |-> <<v>>]
      V2  == MergeV(n, arr, NextId)
  IN /\ V' = V2 /\ N' = N \cup Prefixes(n) /\ nid' = NextId + (IF SHARE THEN 1 ELSE 0)
     /\ size' = IF FIX_SIZE THEN size + ElLen(V2[n]) - (IF n \in N THEN ElLen(V[n]) ELSE 0) ELSE size + 1
     /\ pairs' = pairs \cup {<<n, v>>}
     /\ ts' = [ts EXCEPT ![n] = AddOne(ts[n], v)]             \* TreeSetLookup.put :109-111

PutAll(n, s) ==                                              \* :596-603
  LET arr == [id |-> Fresh, el |-> IF FIX_ADDALL THEN Dedup(s) ELSE s]   \* fix: distinct values (new node takes the array as is, :263)
      V2  == MergeV(n, arr, NextId)
      tsA == AddAll(IF ts[n] = NULL THEN NULL ELSE [id |-> 0, el |-> ts[n]], [id |-> 0, el |-> s], 0)
  IN /\ V' = V2 /\ N' = N \cup Prefixes(n) /\ nid' = NextId + (IF SHARE THEN 1 ELSE 0)
     /\ size' = IF FIX_SIZE THEN size + ElLen(V2[n]) - (IF n \in N THEN ElLen(V[n]) ELSE 0) ELSE size + Len(s)
     /\ pairs' = pairs \cup {<<n, v>> : v \in Range(s)}
     /\ ts' = [ts EXCEPT ![n] = tsA.el]                       \* TreeSetLookup.putAll :41-43

Remove(n, v) ==                                              \* :607-616
  LET node == Find(N, <<>>, n, 0, TRUE)
      r    == IF node = NULL THEN [a |-> NULL, ch |-> FALSE] ELSE RemoveArr(V[node], v, Fresh)
  IN /\ V' = IF r.ch THEN [V EXCEPT ![node] = r.a] ELSE V
     /\ size' = IF r.ch THEN size - 1 ELSE size
     /\ nid' = NextId /\ UNCHANGED N
     /\ pairs' = pairs \ {<<n, v>>}
     /\ ts' = [ts EXCEPT ![n] = RemoveSeq(ts[n], v)]          \* TreeSetLookup.remove :114-124

RemoveMappings(v) ==                                         \* :543-554 (root.accept visits every node)
  LET R == [x \in DOMAIN V |-> RemoveArr(V[x], v, <<Fresh, x>>)]   \* one new array per node
  IN /\ V' = [x \in DOMAIN V |-> R[x].a]
     /\ size' = size - Cardinality({x \in DOMAIN V : R[x].ch})
     /\ nid' = NextId /\ UNCHANGED N
     /\ pairs' = {pr \in pairs : pr[2] # v}
     /\ ts' = [n \in NAMES |-> RemoveSeq(ts[n], v)]

Clear ==                                                     \* :557-559 -> init() :515-522
  /\ N' = {SENTINEL} /\ V' = [x \in {<<>>, SENTINEL} |-> NULL] /\ size' = 0
  /\ pairs' = {} /\ ts' = [n \in NAMES |-> NULL] /\ UNCHANGED nid

\* other = new lookup; other.initializeFrom(this) :638-648 (other.size == 0)
Copy == /\ ~copied /\ copied' = TRUE /\ csize' = size
        /\ snapN' = N /\ snapV' = V
        /\ UNCHANGED <<N, V, size, nid, pairs, ts, steps>>

Next ==
  \/ Copy
  \/ /\ Step /\ UNCHANGED <<copied, csize, snapN, snapV>>
     /\ \/ \E n \in NAMES, v \in VALS : Put(n, v)
        \/ \E n \in NAMES, s \in PUTALL_SEQS : PutAll(n, s)
        \/ \E n \in NAMES, v \in VALS : Remove(n, v)
        \/ \E v \in VALS : RemoveMappings(v)
        \/ Clear

(* ---------------- properties ---------------- *)
SpecOf(n) == {pr[2] : pr \in {pr \in pairs : pr[1] = n}}
SizeTree == size = Count(N, V)                       \* statistics size = mappings held
SizeSpec == size = Cardinality(pairs)
NoDupValues == \A x \in DOMAIN V : V[x] # NULL => Len(V[x].el) = Cardinality(Range(V[x].el))
ExactGetSpec == \A n \in NAMES :
  LET g == ExactGet(N, V, n) IN IF g = NULL THEN SpecOf(n) = {} ELSE Range(g) = SpecOf(n)
ExactGetRef == \A n \in NAMES :
  LET g == ExactGet(N, V, n) IN IF g = NULL THEN ts[n] = NULL ELSE (ts[n] # NULL /\ Range(g) = Range(ts[n]) /\ Len(g) = Len(ts[n]))

\* getMappings, :290-309: a blank segment of an inner node is dropped (!segment.isBlank())
IsBlank(s) == \A i \in 1..Len(s) : s[i] = SP
Recon(x) == LET keep == {i \in 1..(Len(x) - 1) : FIX_MAPPINGS \/ ~IsBlank(x[i])}
                idx  == CHOOSE f \in [1..Cardinality(keep) -> keep] : \A i, j \in DOMAIN f : i < j => f[i] < f[j]
            IN [i \in 1..Cardinality(keep) |-> x[idx[i]]] \o <<x[Len(x)]>>
GetMappings(v) == {Recon(x) : x \in {x \in N : V[x] # NULL /\ v \in Range(V[x].el)}}
GetMappingsSpec == \A v \in VALS : Assert(GetMappings(v) = {pr[1] : pr \in {pr \in pairs : pr[2] = v}},
                                          <<"getMappings", v, GetMappings(v)>>)

\* get(pattern, excludeDuplicates = false) as a multiset, trie vs TreeSetLookup
Bag(l) == [v \in VALS |-> Cardinality({i \in 1..Len(l) : l[i] = v})]
TrieBag(p) ==
  LET vis == PatternVisits(N, p) IN
  IF SHARE /\ ~FIX_SHARE
  THEN LET arrs == SharedVals(V, vis) IN [v \in VALS |-> SumOver([a \in arrs |-> Bag(a.el)[v]], arrs)]
  ELSE Bag(ConcatVals(V, vis))
TSKeys(p) == {k \in NAMES : ts[k] # NULL /\ Comparator(Plain(k), LowerInclusive(p)) >= 0
                            /\ Comparator(Plain(k), Plain(UpperExclusive(p))) < 0
                            /\ (IsRecursive(p) \/ Len(k) = Len(p.segs))}
TSBag(p) == [v \in VALS |-> SumOver([k \in TSKeys(p) |-> Bag(ts[k])[v]], TSKeys(p))]
PatternBagRef == \A p \in OPS_PATTERNS : Assert(TrieBag(p) = TSBag(p), <<"pattern", p, "trie", TrieBag(p), "treeset", TSBag(p)>>)

\* the lookup initialised from this one (other.initializeFrom(this)) keeps a correct size
CopySize == copied => csize = (IF FIX_COPY THEN Count(snapN, snapV) ELSE Count(N, V))

\* Witnesses (expected violated)
WitnessShared == ~(\E x \in N : Len(x) = 2 /\ V[x] # NULL /\ V[SkipLast(x, 1)] = V[x])
WitnessRemoveAll == ~(steps >= 3 /\ size = 0 /\ Cardinality(N) > 2)
=============================================================================
