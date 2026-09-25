------------------------------- MODULE MC_TQ -------------------------------
(* Curated universes for TrieQuery over the alphabet {' ', 'a', 'b'}.   *)
(* ' ' stands for every code unit below '!' (0x00-0x20).              *)
EXTENDS TrieQuery
E  == <<>>
S  == <<SP>>
A  == <<LA>>
AS == <<LA, SP>>
AA == <<LA, LA>>
AB == <<LA, LB>>
B  == <<LB>>
Seg1 == {E, S, A, AS, AA, AB, B}
QNames ==
  {<<x>> : x \in Seg1} \cup {<<A, x>> : x \in Seg1} \cup {<<x, A>> : x \in {E, S, AS, B}}
  \cup {<<A, A, A>>, <<A, B, A>>, <<A, AS, A>>, <<AS, A, A>>}
Lasts == {l \o w : l \in {E, S, A, B}, w \in {<<>>, ONESTAR, STARSTAR}}
QPatterns ==
  {[segs |-> q \o <<l>>, glob |-> FALSE] :
     q \in {<<>>, <<E>>, <<A>>, <<AS>>, <<A, A>>, <<A, B>>}, l \in Lasts}
=============================================================================
