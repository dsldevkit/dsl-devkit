/-
Fidelity checks: the existing JUnit assertions (QualifiedNamePatternTest,
QualifiedNameSegmentTreeLookupTest, PatternAwareEObjectDescriptionLookUpTest) replayed on the
model with `#guard` (evaluated at build time). If the model diverged from the Java on the paths
those tests cover, the build would fail.
-/
import Trie.Consumer

namespace Trie.Validate
open Trie

def s (str : String) : Seg := str.toList.map Char.toNat
def q (strs : List String) : QN := strs.map s
def pat (strs : List String) : Pat := { segs := q strs }
def glob (strs : List String) : Pat := { segs := q strs, glob := true }
def dotted (str : String) : QN := (str.splitOn ".").map s

-- QualifiedNamePatternTest.testSimpleQualifiedNamePattern
#guard (pat ["foo*"]).lowerInclusive.qn == q ["foo"]
#guard (pat ["foo*"]).upperExclusive == q ["fop"]
#guard (pat ["foo*"]).matches (q ["foo"])
#guard (pat ["foo*"]).matches (q ["foof"])
#guard !(pat ["foo*"]).matches (q ["foo", "bar"])
-- testQualifiedPrefixNamePattern
#guard (pat ["foo", "*"]).lowerInclusive.qn == q ["foo", ""]
#guard (pat ["foo", "*"]).upperExclusive == q ["foo!"]
#guard (pat ["foo", "*"]).matches (q ["foo", "bar"])
#guard !(pat ["foo", "*"]).matches (q ["foo", "bar", "baz"])
#guard !(pat ["foo", "*"]).matches (q ["foo2", "bar"])
#guard (pat ["foo", "bar*"]).lowerInclusive.qn == q ["foo", "bar"]
#guard (pat ["foo", "bar*"]).upperExclusive == q ["foo", "bas"]
-- testRecursiveWildcardPattern
#guard (pat ["foo", "**"]).lowerInclusive.qn == q ["foo", ""]
#guard (pat ["foo", "**"]).upperExclusive == q ["foo!"]
#guard (pat ["foo", "**"]).matches (q ["foo", "bar"])
#guard (pat ["foo", "**"]).matches (q ["foo", "bar", "baz"])
#guard !(pat ["foo", "**"]).matches (q ["foo2", "bar"])
-- testRecursiveWildcardPatternWithPrefix
#guard (pat ["foo", "b**"]).lowerInclusive.qn == q ["foo", "b"]
#guard (pat ["foo", "b**"]).upperExclusive == q ["foo", "c"]
#guard (pat ["foo", "b**"]).matches (q ["foo", "bar"])
#guard (pat ["foo", "b**"]).matches (q ["foo", "bar", "baz"])
#guard !(pat ["foo", "b**"]).matches (q ["foo2", "bar"])
-- testRecursiveWildcardPatternError 1-3
#guard !verifySegs (q ["foo", "bar**baz"])
#guard !verifySegs (q ["foo", "**", "bar"])
#guard !verifySegs (q ["foo*bar"])
#guard verifySegs (q ["foo", "b**"]) && verifySegs (q ["*"]) && verifySegs (q ["a", "**"])
-- testAllPattern / testPatternWithoutWildcard
#guard (pat ["*"]).lowerInclusive.qn == q [""]
#guard (pat ["*"]).upperExclusive == q ["!"]
#guard (pat ["foo"]).lowerInclusive.qn == q ["foo"] && (pat ["foo"]).lowerInclusive.isPat
#guard (pat ["foo"]).upperExclusive == q ["foo!"]
-- testRegexpPatterns
#guard (glob ["*"]).lowerInclusive.qn == q [""] && (glob ["*"]).upperExclusive == q ["!"]
#guard (glob ["*"]).matches (q ["foo"]) && (glob ["*"]).matches (q ["foo", "bar"])
#guard (glob ["?"]).lowerInclusive.qn == q [""] && (glob ["?"]).upperExclusive == q ["!"]
#guard (glob ["?"]).matches (q ["f"]) && !(glob ["?"]).matches (q ["foo"]) && !(glob ["?"]).matches (q ["foo", "bar"])
#guard (glob ["foo*"]).lowerInclusive.qn == q ["foo"] && (glob ["foo*"]).upperExclusive == q ["fop"]
#guard !(glob ["foo*"]).matches (q ["f"]) && (glob ["foo*"]).matches (q ["foo"]) && (glob ["foo*"]).matches (q ["foo", "bar"])
#guard (glob ["foo?"]).lowerInclusive.qn == q ["foo"] && (glob ["foo?"]).upperExclusive == q ["fop"]
#guard !(glob ["foo?"]).matches (q ["foo"]) && (glob ["foo?"]).matches (q ["foof"])
#guard !(glob ["foo?"]).matches (q ["foof", "bar"]) && !(glob ["foo?"]).matches (q ["foofy"])
#guard (glob ["foo?bar*"]).lowerInclusive.qn == q ["foo"] && (glob ["foo?bar*"]).upperExclusive == q ["fop"]
#guard !(glob ["foo?bar*"]).matches (q ["foo"]) && (glob ["foo?bar*"]).matches (q ["foofbar"])
#guard (glob ["foo?bar*"]).matches (q ["foofbar", "bar"]) && (glob ["foo?bar*"]).matches (q ["foofbarzzzz"])
#guard (glob ["foo", "*"]).lowerInclusive.qn == q ["foo", ""] && (glob ["foo", "*"]).upperExclusive == q ["foo!"]
#guard (glob ["foo", "*"]).matches (q ["foo", ""]) && (glob ["foo", "*"]).matches (q ["foo", "bar"])
#guard !(glob ["foo", "*"]).matches (q ["foo"])
#guard (glob ["foo*", "bar*"]).lowerInclusive.qn == q ["foo"] && (glob ["foo*", "bar*"]).upperExclusive == q ["fop"]
#guard (glob ["foo*bar*"]).lowerInclusive.qn == q ["foo"] && (glob ["foo*bar*"]).upperExclusive == q ["fop"]
#guard (glob ["?foo*bar*"]).lowerInclusive.qn == q [""] && (glob ["?foo*bar*"]).upperExclusive == q ["!"]
-- testComparison (DSL-209); comparePattern = comparator.compare(pattern, name)
def cmpPN (p : List String) (n : List String) : Ordering := comparatorCmp ⟨q p, true⟩ (plain (q n))
#guard cmpPN ["foo", "abc*"] ["foo", "abcd"] == .lt
#guard cmpPN ["foo*"] ["foo", "abcd"] == .lt
#guard cmpPN ["foo", "abc*"] ["foo"] == .gt
#guard cmpPN ["ab*"] ["ab"] == .lt
#guard cmpPN ["ab*"] ["abc"] == .lt
#guard cmpPN ["aa*"] ["aba"] == .lt
#guard cmpPN ["ab*"] ["aaa"] == .gt
#guard cmpPN ["foo", "xyz$*"] ["foo", "xyz"] == .gt
#guard cmpPN ["foo", "xya$*"] ["foo", "xyz"] == .lt
#guard comparatorCmp (plain (q ["foo", "xyz"])) ⟨q ["foo", "xyz$*"], true⟩ == .lt
#guard comparatorCmp (plain (q ["foo", "xyz"])) ⟨q ["foo", "xya$*"], true⟩ == .gt

-- QualifiedNameSegmentTreeLookupTest (shareValues = true); values = insertion index
def mk (names : List String) : Lookup :=
  (names.zip (List.range names.length)).foldl (fun t (n, i) => t.put (dotted n) i) (Lookup.empty true)
def setOf (l : List Nat) : List Nat := (l.mergeSort (· ≤ ·)).eraseDups
def pget (t : Lookup) (p : String) : List Nat := setOf (t.getPattern { segs := dotted p })

#guard (Lookup.empty true).get [] == none
-- testExact / testOutOfOrderInsertion
#guard (mk ["foo", "foo.bar", "bar"]).get (dotted "foo.bar") == some [1]
#guard (mk ["foo.bar", "foo"]).get (dotted "foo") == some [1]
-- testTopLevelPatternWithoutWildcard
#guard pget (mk ["foo", "bar", "foo2"]) "foo" == [0] && pget (mk ["foo", "bar", "foo2"]) "bar" == [1]
#guard pget (mk ["foo", "bar", "foo2"]) "foo2" == [2]
-- testTopLevelPatternWithWildcard
#guard pget (mk ["foo", "foo2", "bar"]) "f*" == [0, 1] && pget (mk ["foo", "foo2", "bar"]) "foo*" == [0, 1]
#guard pget (mk ["foo", "foo2", "bar"]) "b*" == [2]
-- testNestedPatternMatchesWithoutWildcard
#guard pget (mk ["foo", "foo.bar", "foo2"]) "foo" == [0] && pget (mk ["foo", "foo.bar", "foo2"]) "foo.bar" == [1]
-- testNestedPatternMatchesWithWildcard
def t5 := mk ["foo", "foo.bar", "foo.baz", "foo.baz.bazz", "foo2"]
#guard pget t5 "f*" == [0, 4] && pget t5 "foo.*" == [1, 2] && pget t5 "foo.ba*" == [1, 2] && pget t5 "foo.bar*" == [1]
-- testNestedPatternMatchesWithRecursiveWildcard
def t6 := mk ["foo", "foo.bar", "foo.bar.baz", "foo.bar.baz.quux", "foo.foo", "foo2"]
#guard pget t6 "foo.**" == [1, 2, 3, 4] && pget t6 "foo.b**" == [1, 2, 3]
-- testUnmatchedNestedPattern
#guard ["e*", "g*", "foa.*", "fon.b*", "foo.c*", "foo.baq.b*", "foo.bar.a*", "foo.bar.bazz*"].all (pget t6 · == [])
-- testGetMappings (as a set)
def tm : Lookup :=
  let names := ["A", "B", "A.C", "A.D", "B.E", "B.F", "A.C.G", "A.C.H", "A.D.I", "A.D.J"]
  let t := mk names
  [("A.C"), ("A.C.G"), ("A.C.H"), ("B.F"), ("B")].foldl (fun t n => t.put (dotted n) 99) t
#guard (tm.getMappings 99).length == 5
#guard [dotted "A.C", dotted "A.C.G", dotted "A.C.H", dotted "B.F", dotted "B"].all (tm.getMappings 99).contains
#guard tm.getMappings 1234 == []

end Trie.Validate
