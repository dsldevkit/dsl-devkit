------------------------------- MODULE MC -------------------------------
(* Instantiation sizes for BinaryStorage (cfg files cannot write sequences/functions). *)
EXTENDS BinaryStorage
\* S: one resource per cluster; b depends on a.
ClustersS == << {"a"}, {"b"} >>
DepsS     == [u \in {"a", "b"} |-> IF u = "b" THEN {"a"} ELSE {}]
\* M: two resources in cluster 1 (a2 depends on a1), b depends on both.
ClustersM == << {"a1", "a2"}, {"b"} >>
DepsM     == [u \in {"a1", "a2", "b"} |-> CASE u = "a2" -> {"a1"} [] u = "b" -> {"a1", "a2"} [] OTHER -> {}]
\* L: three clusters.
ClustersL == << {"a1", "a2"}, {"b"}, {"c"} >>
DepsL     == [u \in {"a1", "a2", "b", "c"} |-> CASE u = "a2" -> {"a1"} [] u = "b" -> {"a1"} [] u = "c" -> {"b"} [] OTHER -> {}]
\* R: three resources in cluster 1 so that NStore=1, QCap=1 fills the executor (CallerRunsPolicy).
ClustersR == << {"a1", "a2", "a3"}, {"b"} >>
DepsR     == [u \in {"a1", "a2", "a3", "b"} |-> CASE u = "b" -> {"a1"} [] OTHER -> {}]
=============================================================================
