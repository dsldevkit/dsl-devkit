------------------------------ MODULE MC_TQ2 ------------------------------
(* Universes over {'a', 'b'} only (no code unit below '!'), deeper names. *)
EXTENDS TrieQuery
E  == <<>>
A  == <<LA>>
AA == <<LA, LA>>
AB == <<LA, LB>>
B  == <<LB>>
Seg1 == {E, A, AA, AB, B}
QNames ==
  {<<x>> : x \in Seg1} \cup {<<A, x>> : x \in Seg1} \cup {<<x, A>> : x \in {E, AA, B}}
  \cup {<<A, A, A>>, <<A, B, A>>, <<A, AA, A>>, <<AA, A, A>>, <<A, A, A, A>>}
Lasts == {l \o w : l \in {E, A, B}, w \in {<<>>, ONESTAR, STARSTAR}}
QPatterns ==
  {[segs |-> q \o <<l>>, glob |-> FALSE] :
     q \in {<<>>, <<E>>, <<A>>, <<AA>>, <<A, A>>, <<A, B>>}, l \in Lasts}
=============================================================================
