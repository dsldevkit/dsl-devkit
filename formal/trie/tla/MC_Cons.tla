------------------------------ MODULE MC_Cons ------------------------------
(* Universes for Consumer over {'A', 'a', 'b'}. *)
EXTENDS Consumer
E  == <<>>
a  == <<LA>>
UA_ == <<UA>>
b  == <<LB>>
ab == <<LA, LB>>
Ab == <<UA, LB>>
CDescs == {<<x>> : x \in {a, UA_, b, ab, Ab}} \cup {<<p, x>> : p \in {a, UA_}, x \in {a, UA_, b}}
CPats == {[segs |-> q \o <<l \o w>>, glob |-> FALSE] :
            q \in {<<>>, <<a>>, <<UA_>>}, l \in {E, a, UA_, b}, w \in {<<>>, ONESTAR, STARSTAR}}
CQueries == {[q |-> p, pat |-> TRUE] : p \in CPats} \cup {[q |-> [segs |-> d, glob |-> FALSE], pat |-> FALSE] : d \in CDescs}
=============================================================================
