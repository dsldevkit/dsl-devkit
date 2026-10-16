------------------------------- MODULE MC_Ops -------------------------------
EXTENDS TrieOps
a  == <<LA>>
b  == <<LB>>
sp == <<SP>>
ONames == {<<a>>, <<a, b>>, <<sp, b>>}
OVals == {"v1", "v2"}
OSeqs == {<<"v1", "v1">>, <<"v1", "v2">>}
OPats == {[segs |-> <<<<LA, STAR, STAR>>>>, glob |-> FALSE], [segs |-> <<a, ONESTAR>>, glob |-> FALSE]}
=============================================================================
