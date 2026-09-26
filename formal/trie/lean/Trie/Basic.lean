/-
Basic model of Java strings (UTF-16 code units) and Xtext `QualifiedName`.

A Java `char` is modelled as a `Nat` in `[0, 65535]`, a `String` as `List JChar`,
a `QualifiedName` as `List Seg`.
-/
namespace Trie

abbrev JChar := Nat
abbrev Seg := List JChar
abbrev QN := List Seg

def STAR : JChar := 42     -- '*'
def QMARK : JChar := 63    -- '?'
def BANG : JChar := 33     -- '!'
def CMAX : JChar := 65535  -- Character.MAX_VALUE

/-- `(char) (c + 1)` in Java: wraps at 65536. -/
def charSucc (c : JChar) : JChar := (c + 1) % 65536

/-- Sign of Java `String.compareTo` (lexicographic on UTF-16 units, proper prefix is smaller). -/
def strCmp : Seg → Seg → Ordering
  | [], [] => .eq
  | [], _ :: _ => .lt
  | _ :: _, [] => .gt
  | a :: as, b :: bs => if a < b then .lt else if b < a then .gt else strCmp as bs

/-- Sign of Xtext `QualifiedName.compareTo(other, false)` (javap: segment-wise `String.compareTo`
over `min(count)` segments, then `count1 - count2`). -/
def qnCmp : QN → QN → Ordering
  | [], [] => .eq
  | [], _ :: _ => .lt
  | _ :: _, [] => .gt
  | a :: as, b :: bs =>
    match strCmp a b with
    | .eq => qnCmp as bs
    | o => o

/-- `String.indexOf(char)`; `-1` when absent. -/
def indexOf (c : JChar) (s : Seg) : Int :=
  match s.findIdx? (· == c) with
  | some i => i
  | none => -1

/-- `String.lastIndexOf(char)`; `-1` when absent. -/
def lastIndexOf (c : JChar) (s : Seg) : Int :=
  match (s.reverse.findIdx? (· == c)) with
  | some i => (s.length - 1 - i : Nat)
  | none => -1

def endsWithStarStar (s : Seg) : Bool :=
  s.length ≥ 2 && s.getLast? == some STAR && s.dropLast.getLast? == some STAR

def charAt (s : Seg) (i : Int) : JChar := s.getD i.toNat 0

/-- `s.substring(0, k)` for `0 ≤ k ≤ length`. -/
def substr0 (s : Seg) (k : Int) : Seg := s.take k.toNat

/-- `seg.regionMatches(0, other, 0, len)` (case-sensitive). -/
def regionMatches0 (seg other : Seg) (len : Int) : Bool :=
  if len < 0 then true
  else if other.length < len.toNat || seg.length < len.toNat then false
  else seg.take len.toNat == other.take len.toNat

/-- ASCII-only `Character.isWhitespace` (the model alphabet contains no other whitespace). -/
def isWs (c : JChar) : Bool := c == 32 || (9 ≤ c && c ≤ 13) || (28 ≤ c && c ≤ 31)

/-- `String.isBlank`. -/
def isBlank (s : Seg) : Bool := s.all isWs

/-- ASCII-only `Character.toLowerCase` (model alphabet only has ASCII letters). -/
def lowerC (c : JChar) : JChar := if 65 ≤ c && c ≤ 90 then c + 32 else c

def lowerS (s : Seg) : Seg := s.map lowerC
def lowerQ (q : QN) : QN := q.map lowerS

def dropLastQ (q : QN) : QN := q.dropLast
def lastSeg (q : QN) : Seg := q.getLastD []

/-! Pretty printing for counterexamples. -/
def showChar (c : JChar) : String :=
  if c == 32 then "␠" else if c == 65535 then "\\uFFFF" else if c == 0 then "\\u0000"
  else if c < 32 then s!"\\u{c}" else (Char.ofNat c).toString

def showSeg (s : Seg) : String := String.join (s.map showChar)
def showQN (q : QN) : String :=
  "(" ++ ", ".intercalate (q.map (fun s => "\"" ++ showSeg s ++ "\"")) ++ ")"

end Trie
