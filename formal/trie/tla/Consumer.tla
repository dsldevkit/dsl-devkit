------------------------------ MODULE Consumer ------------------------------
(***************************************************************************)
(* PatternAwareEObjectDescriptionLookUp.getExportedObjects(type, name,      *)
(* ignoreCase), :49-66, over the trie built by getNameToObjectsLookup()     *)
(* (:93-117: put(description.getName().toLowerCase(), description)).        *)
(* A description is identified with its (case-preserving) name; the type    *)
(* filter is assumed to pass (EcoreUtil2.isAssignableFrom = TRUE).          *)
(***************************************************************************)
EXTENDS TrieCore, TLC

CONSTANTS DESCS,        \* universe of description names
          QUERIES,      \* [q |-> [segs, glob], pat |-> BOOLEAN]; pat = FALSE is a plain QualifiedName q.segs
          MAXN,
          FIX_CONSUMER  \* :63 matches(input.getName()) instead of matches(name)

VARIABLES stored

Init == stored = {}
Next == \E d \in DESCS \ stored : Cardinality(stored) < MAXN /\ stored' = stored \cup {d}

\* Trie keyed by lower-cased names; a node's values are the descriptions with that key
TN == UNION {Prefixes(LowerQN(d)) : d \in stored} \cup {SENTINEL}
TV == [x \in TN \cup {<<>>} |->
         IF \E d \in stored : LowerQN(d) = x THEN [id |-> x, set |-> {d \in stored : LowerQN(d) = x}] ELSE NULL]
Vals(vis) == UNION {TV[vis[i]].set : i \in {i \in 1..Len(vis) : TV[vis[i]] # NULL}}

LowerPat(p) == [segs |-> LowerQN(p.segs), glob |-> FALSE]      \* QualifiedNamePattern.toLowerCase :180-193

Impl(Q, ic) ==
  LET vals == IF Q.pat THEN Vals(PatternVisits(TN, LowerPat(Q.q)))              \* :55
              ELSE LET r == Find(TN, <<>>, LowerQN(Q.q.segs), 0, TRUE)               \* :57 get(lowerCase)
                   IN IF r # NULL /\ TV[r] # NULL THEN TV[r].set ELSE {}        \* :59-61
  IN IF ic THEN vals                                                            \* :62 type only
     ELSE IF Q.pat THEN {d \in vals : IF FIX_CONSUMER THEN Matches(Q.q, d)      \* :63
                                      ELSE FALSE}   \* ((QualifiedNamePattern) name).matches(name): :263 pattern arg => false
     ELSE {d \in vals : d = Q.q.segs}                                                \* :64

Spec(Q, ic) ==
  IF ic THEN (IF Q.pat THEN {d \in stored : Matches(LowerPat(Q.q), LowerQN(d))}
              ELSE {d \in stored : LowerQN(d) = LowerQN(Q.q.segs)})
  ELSE (IF Q.pat THEN {d \in stored : Matches(Q.q, d)} ELSE {d \in stored : d = Q.q.segs})

Chk(ok, Q, ic, l, r) == Assert(ok, <<"query", Q, "ignoreCase", ic, "impl", l, "expected", r>>)
ConsCS == \A Q \in QUERIES : Chk(Impl(Q, FALSE) = Spec(Q, FALSE), Q, FALSE, Impl(Q, FALSE), Spec(Q, FALSE))
ConsCI == \A Q \in QUERIES : Chk(Impl(Q, TRUE) = Spec(Q, TRUE), Q, TRUE, Impl(Q, TRUE), Spec(Q, TRUE))
\* Consistency: a case-sensitive hit is always a case-insensitive hit
ConsCSinCI == \A Q \in QUERIES : Chk(Impl(Q, FALSE) \subseteq Impl(Q, TRUE), Q, "CS within CI", Impl(Q, FALSE), Impl(Q, TRUE))
\* Witness (expected violated): a case-sensitive pattern query returning something
WitnessCSPattern == \A Q \in QUERIES : Q.pat => Impl(Q, FALSE) = {}
=============================================================================
