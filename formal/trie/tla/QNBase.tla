------------------------------- MODULE QNBase -------------------------------
(***************************************************************************)
(* Java strings, Xtext QualifiedName and DDK QualifiedNamePattern, modelled *)
(* line by line.  A string is a sequence of UTF-16 code units (Ints), a      *)
(* qualified name is a sequence of strings.                                  *)
(*                                                                           *)
(* FIX_BOUNDS / FIX_MATCH / FIX_GLOB switch on the minimal fixes; with all   *)
(* three FALSE every operator follows the code as written.                   *)
(***************************************************************************)
EXTENDS Integers, Sequences, FiniteSets

CONSTANTS FIX_BOUNDS, FIX_MATCH, FIX_GLOB, FIX_CMP,
          NULL, INF, EXC   \* model values: Java null, unbounded upper bound, thrown exception

NUL   == 0
SP    == 32     \* ' '  (stands for every char below '!')
BANG  == 33     \* '!'
STAR  == 42     \* '*'
QMARK == 63     \* '?'
UA    == 65     \* 'A'
LA    == 97     \* 'a'
LB    == 98     \* 'b'
MAXC  == 65535  \* Character.MAX_VALUE (sentinel segment)


Sign(x) == IF x < 0 THEN -1 ELSE IF x > 0 THEN 1 ELSE 0
Min(a, b) == IF a < b THEN a ELSE b

(* ---------------- java.lang.String ---------------- *)
RECURSIVE StrCmpR(_, _, _)
StrCmpR(s, t, i) ==
  IF i > Len(s) \/ i > Len(t) THEN Sign(Len(s) - Len(t))
  ELSE IF s[i] # t[i] THEN Sign(s[i] - t[i])
  ELSE StrCmpR(s, t, i + 1)
StrCmp(s, t) == StrCmpR(s, t, 1)                  \* String.compareTo (sign)

LowerC(c) == IF c >= 65 /\ c <= 90 THEN c + 32 ELSE c
LowerS(s) == [i \in 1..Len(s) |-> LowerC(s[i])]    \* String.toLowerCase (ASCII)
StrCmpCI(s, t) == StrCmp(LowerS(s), LowerS(t))

\* 0-based indexOf / lastIndexOf, -1 if absent
IndexOf(s, c) == IF \E i \in 1..Len(s) : s[i] = c
                 THEN (CHOOSE i \in 1..Len(s) : s[i] = c /\ \A j \in 1..(i-1) : s[j] # c) - 1
                 ELSE -1
LastIndexOf(s, c) == IF \E i \in 1..Len(s) : s[i] = c
                     THEN (CHOOSE i \in 1..Len(s) : s[i] = c /\ \A j \in (i+1)..Len(s) : s[j] # c) - 1
                     ELSE -1
Prefix(s, k) == SubSeq(s, 1, k)                   \* s.substring(0, k)
EndsWith(s, suf) == Len(s) >= Len(suf) /\ SubSeq(s, Len(s) - Len(suf) + 1, Len(s)) = suf
\* s.regionMatches(0, o, 0, len) for len >= 0
RegionMatches(s, o, len) == len <= Len(s) /\ len <= Len(o) /\ Prefix(s, len) = Prefix(o, len)
CharPlus1(c) == (c + 1) % 65536                   \* (char) (c + 1)

STARSTAR == <<STAR, STAR>>
ONESTAR  == <<STAR>>

(* ---------------- org.eclipse.xtext.naming.QualifiedName ---------------- *)
RECURSIVE QNCmpR(_, _, _)
QNCmpR(a, b, i) ==
  IF i > Min(Len(a), Len(b)) THEN Sign(Len(a) - Len(b))
  ELSE IF StrCmp(a[i], b[i]) # 0 THEN StrCmp(a[i], b[i])
  ELSE QNCmpR(a, b, i + 1)
QNCmp(a, b) == QNCmpR(a, b, 1)                    \* QualifiedName.compareTo (bytecode 2.44)
LastSeg(q) == q[Len(q)]
SkipLast(q, k) == SubSeq(q, 1, Len(q) - k)
LowerQN(q) == [i \in 1..Len(q) |-> LowerS(q[i])]  \* QualifiedName.toLowerCase

(* ---------------- QualifiedNamePattern ---------------- *)
(* A pattern is [segs |-> QN, glob |-> BOOLEAN].  Plain names are QNs.    *)
IsRecursive(p) == EndsWith(LastSeg(p.segs), STARSTAR)             \* :155-157

\* Glob matching as compiled by Regexps.fromGlob(glob, true):
\* '*' -> ".*", '?' -> ".", CASE_INSENSITIVE | UNICODE_CASE.
\* FIX_GLOB compiles case-sensitively (fromGlob(glob, false)).
GFold(c) == IF FIX_GLOB THEN c ELSE LowerC(c)
RECURSIVE GM(_, _, _, _)
GM(g, s, i, j) ==
  IF i > Len(g) THEN j > Len(s)
  ELSE IF g[i] = STAR THEN \E k \in j..(Len(s) + 1) : GM(g, s, i + 1, k)
  ELSE j <= Len(s) /\ (g[i] = QMARK \/ GFold(g[i]) = GFold(s[j])) /\ GM(g, s, i + 1, j + 1)
GlobMatch(g, s) == GM(g, s, 1, 1)


\* matches(other), :260-305.  A thrown exception is reported by MatchThrows
\* (and Matches is then FALSE).
RECURSIVE MatchLoop(_, _, _)
MatchLoop(p, o, i) ==                                              \* :278-303
  IF i > Len(p.segs) THEN (IF FIX_MATCH THEN Len(o) = Len(p.segs) ELSE TRUE)   \* :304
  ELSE LET seg == p.segs[i]
           w   == IndexOf(seg, STAR)
       IN IF w = 0 THEN
            IF seg = STARSTAR THEN TRUE                            \* :282-283
            ELSE IF Len(o) > Len(p.segs) THEN FALSE                \* :284-285
            ELSE MatchLoop(p, o, i + 1)                            \* :287
          ELSE LET oseg == o[i] IN
            IF w # -1 /\ Len(seg) > w + 1 /\ seg[w + 2] = STAR
              THEN RegionMatches(seg, oseg, w)                     \* :291-292
            ELSE IF w # -1 /\ Len(o) > Len(p.segs) THEN FALSE      \* :293-294
            ELSE IF w # -1 /\ RegionMatches(seg, oseg, w)
              THEN MatchLoop(p, o, i + 1)                          \* :295-296
            ELSE IF seg # oseg THEN FALSE                          \* :299-300
            ELSE MatchLoop(p, o, i + 1)
Matches(p, o) ==
  IF Len(o) < Len(p.segs) THEN FALSE                               \* :261-262
  ELSE IF p.glob THEN
    LET ls == LastSeg(p.segs) IN
    IF Len(ls) = 0 THEN FALSE                                      \* :268 charAt(-1) throws
    ELSE IF ls[Len(ls)] # STAR /\ Len(o) > Len(p.segs) THEN FALSE  \* :268-269
    ELSE \A i \in 1..Len(p.segs) : GlobMatch(p.segs[i], o[i])      \* :271-276
  ELSE MatchLoop(p, o, 1)
MatchThrows(p, o) == p.glob /\ Len(o) >= Len(p.segs) /\ Len(LastSeg(p.segs)) = 0   \* StringIndexOutOfBounds

(* lowerInclusive(), :312-345.  Result [qn, isPat]: isPat means the bound  *)
(* is the pattern object itself (its compareTo override is used by        *)
(* TreeMap), otherwise a plain QualifiedName.                             *)
GlobFirst(p) ==       \* :315-331 (firstWildcardSeg, wildcardIdx)
  LET hasW(s) == IndexOf(s, STAR) # -1 \/ IndexOf(s, QMARK) # -1
      W == {i \in 1..Len(p.segs) : hasW(p.segs[i])}
  IN IF W = {} THEN [f |-> IF FIX_GLOB THEN -1 ELSE 0, w |-> 0]
     ELSE LET i == CHOOSE i \in W : \A j \in W : i <= j
              s == p.segs[i]
              any == IndexOf(s, STAR)
              one == IndexOf(s, QMARK)
              w1 == IF any # -1 THEN any ELSE 0
              w2 == IF one # -1 /\ (one < any \/ any = -1) THEN one ELSE w1
          IN [f |-> i - 1, w |-> w2]
LowerInclusive(p) ==
  IF p.glob THEN
    LET g == GlobFirst(p) IN
    IF g.f # -1 THEN [qn |-> Append(SkipLast(p.segs, Len(p.segs) - g.f), Prefix(p.segs[g.f + 1], g.w)),
                      isPat |-> FALSE]                             \* :333
    ELSE [qn |-> p.segs, isPat |-> FALSE]                          \* FIX_GLOB: no wildcard => exact
  ELSE
    LET ls == LastSeg(p.segs)
        w  == LastIndexOf(ls, STAR)
    IN IF w = -1 THEN [qn |-> p.segs, isPat |-> TRUE]              \* :340 (this)
       ELSE [qn |-> Append(SkipLast(p.segs, 1), Prefix(ls, IF EndsWith(ls, STARSTAR) THEN w - 1 ELSE w)),
             isPat |-> FALSE]                                      \* :341

(* Fixed successor helpers: smallest string strictly greater than every    *)
(* string having s as prefix; <<>> when none exists (all MAXC).           *)
RECURSIVE StripMax(_)
StripMax(s) == IF Len(s) > 0 /\ s[Len(s)] = MAXC THEN StripMax(SubSeq(s, 1, Len(s) - 1)) ELSE s
PrefixSucc(s) == LET t == StripMax(s) IN
                 IF Len(t) = 0 THEN <<>> ELSE Append(SubSeq(t, 1, Len(t) - 1), t[Len(t)] + 1)
\* Smallest QN greater than q and all its extensions (q ++ anything).

QNSucc(q) == IF Len(q) = 0 THEN INF
             ELSE Append(SkipLast(q, 1), Append(LastSeg(q), NUL))
\* Smallest QN greater than every QN whose last segment starts with prefix s under parent q.
PrefixLevelSucc(q, s) == IF Len(PrefixSucc(s)) = 0 THEN QNSucc(q) ELSE Append(q, PrefixSucc(s))

(* upperExclusive(), :352-394 *)
UpperExclusive(p) ==
  IF p.glob THEN
    LET lo == LowerInclusive(p).qn
        ls == LastSeg(lo)
    IN IF FIX_BOUNDS THEN
         (IF Len(ls) = 0 THEN QNSucc(SkipLast(lo, 1)) ELSE PrefixLevelSucc(SkipLast(lo, 1), ls))
       ELSE IF Len(ls) = 0 THEN
         (IF Len(lo) = 1 THEN << <<BANG>> >>                                   \* :359
          ELSE LET li == SkipLast(lo, 1) IN Append(SkipLast(li, 1), Append(LastSeg(li), BANG)))  \* :361-363
       ELSE Append(SkipLast(lo, 1), Append(Prefix(ls, Len(ls) - 1), CharPlus1(ls[Len(ls)])))   \* :366-367
  ELSE
    LET ls == LastSeg(p.segs)
        w  == LastIndexOf(ls, STAR)
    IN IF w = 0 \/ (w = 1 /\ ls = STARSTAR) THEN
         IF FIX_BOUNDS THEN QNSucc(SkipLast(p.segs, 1))
         ELSE IF Len(p.segs) = 1 THEN << <<BANG>> >>                           \* :374
         ELSE LET ub == SkipLast(p.segs, 1) IN
              IF Len(LastSeg(ub)) > 0 THEN Append(SkipLast(ub, 1), Append(LastSeg(ub), BANG))   \* :379
              ELSE Append(SkipLast(ub, 1), <<BANG>>)                           \* :382
       ELSE IF w = -1 THEN
         IF FIX_BOUNDS THEN QNSucc(p.segs)
         ELSE Append(SkipLast(p.segs, 1), Append(ls, BANG))                    \* :386
       ELSE LET lci == IF ls[w] = STAR THEN w - 2 ELSE w - 1                   \* :388 (charAt(w-1), 0-based)
            IN IF FIX_BOUNDS THEN PrefixLevelSucc(SkipLast(p.segs, 1), Prefix(ls, lci + 1))
               ELSE Append(SkipLast(p.segs, 1), Append(Prefix(ls, lci), CharPlus1(ls[lci + 1])))  \* :389

(* QualifiedNamePattern.compareTo(other, false) against a plain QN, :210-250 *)
RECURSIVE PatCmpR(_, _, _)
PatCmpR(P, K, i) ==
  IF i > Len(P) THEN -1                                            \* :249
  ELSE IF i > Len(K) THEN 1                                        \* :219-221
  ELSE LET s1 == P[i]
           s2 == K[i]
           w  == IndexOf(s1, STAR)
       IN IF Len(s1) = 0 /\ ~FIX_CMP THEN -1                       \* :224-225 (FIX_CMP drops it)
          ELSE IF s1 = ONESTAR THEN PatCmpR(P, K, i + 1)           \* :226-227
          ELSE IF w = -1 THEN
            (IF StrCmp(s1, s2) # 0 THEN StrCmp(s1, s2) ELSE PatCmpR(P, K, i + 1))
          ELSE IF Len(s2) < w THEN StrCmp(Prefix(s1, w), s2)       \* :236-238
          ELSE IF StrCmp(Prefix(s1, w), Prefix(s2, w)) # 0 THEN StrCmp(Prefix(s1, w), Prefix(s2, w))
          ELSE PatCmpR(P, K, i + 1)

\* QualifiedNamePattern.Comparator.compare(o1, o2), :45-47; bounds are [qn, isPat]
CmpObj(a, b) == IF a.isPat THEN (IF b.isPat THEN QNCmp(a.qn, b.qn) ELSE PatCmpR(a.qn, b.qn, 1))
                ELSE QNCmp(a.qn, b.qn)
Comparator(o1, o2) == IF o1.isPat THEN CmpObj(o1, o2) ELSE -CmpObj(o2, o1)
Plain(q) == [qn |-> q, isPat |-> FALSE]

(* ---------------- universes ---------------- *)
Strs(A, n) == UNION {[1..k -> A] : k \in 0..n}
QNs(S, n) == UNION {[1..k -> S] : k \in 1..n}
\* Valid (create(...)) patterns: prefix segments from Pre, last segment is
\* s, s*, s** for s in Last.
Pats(Pre, Last, n) ==
  {[segs |-> Append(q, l), glob |-> FALSE] :
     q \in UNION {[1..k -> Pre] : k \in 0..(n - 1)},
     l \in {s : s \in Last} \cup {s \o ONESTAR : s \in Last} \cup {s \o STARSTAR : s \in Last}}
=============================================================================
