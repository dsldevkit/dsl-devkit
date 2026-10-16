/-
The concrete bounded domains used by the executable and by the `native_decide` theorems.
-/
import Trie.Checks

namespace Trie.Domains
open Trie Trie.Checks

def s (str : String) : Seg := str.toList.map Char.toNat

/-- Alphabet for P1: a char below '!', '!', 'A', 'a', 'b', U+FFFF. -/
def alphaP1 : List JChar := [32, 33, 65, 97, 98, 65535]
def segsP1 : List Seg := allStrings alphaP1 2            -- 43 strings of length ≤ 2
def segsP1short : List Seg := allStrings alphaP1 1       -- 7 strings of length ≤ 1
def namesP1 : List QN := dedupL (namesOf segsP1 2 ++ namesOf segsP1short 3)
def patsP1 : List Pat := patsOf segsP1short 2 segsP1

/-- Universe for P2 (lookups). -/
def segsP2 : List Seg := [s "", s " ", s "a", s "b", s "a ", s "ab"]
def universeP2 : List QN := dedupL (namesOf segsP2 2 ++ namesOf [s "a", s "b"] 3)
def storesP2 (k : Nat) : List (List QN) := storesBySize universeP2 k
def patsP2 : List Pat := patsOf [s "", s " ", s "a", s "b"] 2 [s "", s " ", s "a", s "b", s "ab"]

/-- Universe around the U+FFFF sentinel. -/
def segsMax : List Seg := [s "a", [65535], [65535, 97], [97, 65535]]
def universeMax : List QN := namesOf segsMax 2
def patsMax : List Pat := patsOf [s "a", [65535]] 1 [s "", s "a", [65535], [97, 65535]]

/-- Globs. -/
def globsP5 : List Pat := globsOf [s "a", s "A", s "*", s "?", s "a*", s "?a", s "*a", s "b"] 2

/-- Operation sequences. -/
def namesP3 : List QN := [[s "a"], [s "a", s "b"], [s " "], [s " ", s "a"]]
def valsP3 : List Nat := [0, 1]
def opsP3 : List Op :=
  (namesP3.flatMap fun n => valsP3.flatMap fun v => [Op.put n v, Op.remove n v]) ++
  (valsP3.map Op.removeMappings) ++ (namesP3.map fun n => Op.putAll n [0, 1])
def patsP3 : List Pat := [[s "a", s "*"], [s "a", s "**"], [s "*"], [s "**"], [s "a"], [s "a*"], [s " *"], [s "a", s " *"]].map ({ segs := · })

/-- Consumer. -/
def segsP4 : List Seg := [s "a", s "A", s "b", s "aB", s "Ab"]
def descSetsP4 : List (List QN) := storesBySize (namesOf segsP4 2) 3
def argsP4 : List Arg :=
  (patsOf [s "a", s "A"] 1 [s "", s "a", s "A", s "b"]).map Arg.pat ++
  ((namesOf [s "a", s "A"] 2).map Arg.name)

end Trie.Domains
