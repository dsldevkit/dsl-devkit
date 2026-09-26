/-
Model of `QualifiedNameSegmentTreeLookup` (QualifiedNameSegmentTreeLookup.java) and the parts of
`ArrayUtils` it uses.

* Node identity (`==` in `collectMatches`, `equals` in `visitChildren`, SegmentNode does not
  override `equals`) is modelled by the node's path from the root, which is unique in the trie.
* `Object[]` identity (needed for the value-sharing subclass, which deduplicates arrays in a
  `HashSet<Object[]>`, i.e. by identity) is modelled by an allocation id.
* `children == null` and "empty" are both `[]`; the Java code never leaves a non-null empty list
  (merge always inserts after allocating).
-/
import Trie.Pattern

namespace Trie

structure Arr where
  id : Nat
  elems : List Nat
deriving Repr, DecidableEq, Inhabited

inductive Node where
  | mk (seg : Seg) (vals : Option Arr) (kids : List Node)
deriving Repr, Inhabited

def Node.seg : Node → Seg | .mk s _ _ => s
def Node.vals : Node → Option Arr | .mk _ v _ => v
def Node.kids : Node → List Node | .mk _ _ k => k

/-! ### ArrayUtils (ArrayUtils.java) -/

/-- `ArrayUtils.find` (138-147). -/
def auFind (a : Option Arr) (v : Nat) : Int :=
  match a with
  | none => -1
  | some a => match a.elems.findIdx? (· == v) with | some i => i | none => -1

/-- `ArrayUtils.addAll` (77-93). Membership is tested against the ORIGINAL `array` (line 83).
`fresh` is the identity given to a newly allocated array. -/
def auAddAll (array values : Option Arr) (fresh : Nat) : Option Arr :=
  match array with
  | none => values                                      -- 78-79
  | some a =>
    if a.elems.isEmpty then values                      -- 78-79
    else
      let vs := (values.map Arr.elems).getD []
      let added := vs.filter (fun v => !(a.elems.contains v))
      if added.isEmpty then some a else some ⟨fresh, a.elems ++ added⟩

/-- `ArrayUtils.remove` (107-127). Returns the new array and whether it is a different reference. -/
def auRemove (array : Option Arr) (v : Nat) (fresh : Nat) : Option Arr × Bool :=
  match array with
  | none => (none, false)
  | some a =>
    let i := auFind array v
    if i == 0 && a.elems.length == 1 then (none, true)
    else if i ≥ 0 then (some ⟨fresh, a.elems.eraseIdx i.toNat⟩, true)
    else (array, false)

/-- `ArrayUtils.add` (46-61), used by TreeSetLookup.put. -/
def auAdd (array : Option Arr) (v : Nat) (fresh : Nat) : Option Arr :=
  match array with
  | none => some ⟨fresh, [v]⟩
  | some a => if a.elems.isEmpty then some ⟨fresh, [v]⟩
              else if a.elems.contains v then array else some ⟨fresh, a.elems ++ [v]⟩

/-- `Arrays.equals(Object[], Object[])`. -/
def arrEq : Option Arr → Option Arr → Bool
  | none, none => true
  | some a, some b => a.elems == b.elems
  | _, _ => false

/-! ### SegmentNode.binarySearch (320-338) -/

def bsLoop (segs : List Seg) (key : Seg) : Nat → Int → Int → Int
  | 0, low, _ => -(low + 1)
  | fuel + 1, low, high =>
    if low ≤ high then
      let mid := (low + high) / 2
      match strCmp (segs.getD mid.toNat []) key with
      | .lt => bsLoop segs key fuel (mid + 1) high
      | .gt => bsLoop segs key fuel low (mid - 1)
      | .eq => mid
    else -(low + 1)

def bsearch (kids : List Node) (key : Seg) : Int :=
  bsLoop (kids.map Node.seg) key (kids.length + 1) 0 (kids.length - 1)

/-! ### SegmentNode.find (97-121). Returns the found node's path and the node. -/

def find (n : Node) (path : QN) (rest : QN) (exact : Bool) : Option (QN × Node) :=
  match rest with
  | [] => none   -- unreachable: callers never pass an exhausted name
  | seg :: rest' =>
    let kids := n.kids
    if kids.isEmpty then none                                        -- 98-100
    else
      let last := rest'.isEmpty                                      -- 102
      let idx0 := bsearch kids seg                                   -- 103
      let idxO : Option Int :=
        if idx0 < 0 then (if exact || !last then none else some (-(idx0 + 1)))  -- 104-109
        else some idx0
      match idxO with
      | none => none
      | some idx =>
        if idx == kids.length then none                              -- 110-112
        else
          let c := kids.getD idx.toNat default
          if last then some (path ++ [c.seg], c)                     -- 113-115
          else
            let r := find c (path ++ [c.seg]) rest' exact            -- 116
            if r.isNone && !exact then                               -- 117-119
              if idx + 1 == kids.length then none
              else let c2 := kids.getD (idx + 1).toNat default; some (path ++ [c2.seg], c2)
            else r

/-! ### Visitor traversal -/

abbrev Acc := List (Option Arr)   -- sequence of `node.values` seen by the visitor

mutual
/-- `visitChildren(visitor, stopOn)` (223-237). -/
def visitChildren (n : Node) (path : QN) (stop : QN) (acc : Acc) : Bool × Acc :=
  match n with
  | .mk _ _ kids => visitList kids path stop acc
def visitList : List Node → QN → QN → Acc → Bool × Acc
  | [], _, _, acc => (true, acc)
  | c :: cs, path, stop, acc =>
    let cp := path ++ [c.seg]
    if cp == stop then (false, acc)                                  -- 228-230
    else
      let acc := acc ++ [c.vals]                                     -- 231
      match visitChildren c cp stop acc with                         -- 232
      | (false, acc) => (false, acc)
      | (true, acc) => visitList cs path stop acc
end

/-- Loop at lines 188-198 (last lower segment). The result of `visitChildren` is IGNORED (195). -/
def lastLoop (path : QN) (up : QN) (recursive : Bool) : List Node → Acc → Bool × Acc
  | [], acc => (true, acc)
  | c :: cs, acc =>
    let cp := path ++ [c.seg]
    if cp == up then (false, acc)                                    -- 190-192
    else
      let acc := acc ++ [c.vals]                                     -- 193
      let acc := if recursive then (visitChildren c cp up acc).2 else acc   -- 194-196
      lastLoop path up recursive cs acc

/-- Loop at lines 202-207 (siblings after the recursed child). -/
def sibLoop (path : QN) (up : QN) : List Node → Acc → Bool × Acc
  | [], acc => (true, acc)
  | c :: cs, acc =>
    let cp := path ++ [c.seg]
    if cp == up then (false, acc)
    else match visitChildren c cp up acc with
      | (false, acc) => (false, acc)
      | (true, acc) => sibLoop path up cs acc

/-- `collectMatches` (173-211). -/
def collect (n : Node) (path : QN) (lowerRest : QN) (upper : Option QN) (recursive : Bool)
    (acc : Acc) : Bool × Acc :=
  match upper with
  | none => (false, acc)                                             -- 174 (upper == null)
  | some up =>
    if n.kids.isEmpty || path == up then (false, acc)                -- 174
    else match lowerRest with
      | [] => (false, acc)  -- unreachable
      | seg :: rest =>
        let kids := n.kids
        let last := rest.isEmpty
        let idx0 := bsearch kids seg                                 -- 178
        let idxO : Option Int :=
          if idx0 < 0 && last then some (-(idx0 + 1))                -- 179-180
          else if idx0 < 0 then none                                 -- 181-182
          else some idx0
        match idxO with
        | none => (false, acc)
        | some idx =>
          if idx == kids.length then (true, acc)                     -- 184-186
          else if last then lastLoop path up recursive (kids.drop idx.toNat) acc   -- 187-198
          else
            let c := kids.getD idx.toNat default
            match collect c (path ++ [c.seg]) rest upper recursive acc with   -- 200
            | (true, acc) => sibLoop path up (kids.drop (idx.toNat + 1)) acc    -- 201-209
            | (false, acc) => (false, acc)

/-! ### merge (252-273) and ValueSharingSegmentNode.merge (425-446) -/

def merge (share : Bool) (n : Node) (rest : QN) (newVals : Option Arr) (fresh : Nat) : Node :=
  match n, rest with
  | n, [] => n   -- unreachable for non-empty names (Java: getSegment(0) throws on EMPTY)
  | .mk s v kids, seg :: rest' =>
    let idx0 := bsearch kids seg
    if idx0 < 0 then                                                 -- 258 / 431
      let ins := (-(idx0 + 1)).toNat
      let child :=
        if rest'.isEmpty then
          .mk seg (if share then (if arrEq v newVals then v else newVals) else newVals) []  -- 263 / 436
        else merge share (.mk seg none []) rest' newVals fresh       -- 266 / 439
      .mk s v (kids.insertIdx ins child)
    else
      let i := idx0.toNat
      let c := kids.getD i default
      if rest'.isEmpty then                                          -- 267-269 / 440-442
        let tmp := auAddAll c.vals newVals fresh
        let nv := if share then (if arrEq v tmp then v else tmp) else tmp
        .mk s v (kids.set i (.mk c.seg nv c.kids))
      else .mk s v (kids.set i (merge share c rest' newVals fresh))  -- 271 / 444

/-! ### removeMappings visitor (544-553) via `accept` (281-288), with fresh ids -/

mutual
def removeAll (n : Node) (v : Nat) (st : Nat × Int) : Node × (Nat × Int) :=
  match n with
  | .mk s vals kids =>
    let (nv, changed) := auRemove vals v st.1
    let st := if changed then (st.1 + 1, st.2 - 1) else st
    let (kids', st) := removeAllList kids v st
    (.mk s nv kids', st)
def removeAllList : List Node → Nat → Nat × Int → List Node × (Nat × Int)
  | [], _, st => ([], st)
  | c :: cs, v, st =>
    let (c', st) := removeAll c v st
    let (cs', st) := removeAllList cs v st
    (c' :: cs', st)
end

/-- Replace the node at `path` (relative to `n`'s children) using `f`. -/
def updateAt (f : Node → Node) : Node → QN → Node
  | n, [] => f n
  | .mk s v kids, seg :: rest =>
    .mk s v (kids.map fun c => if c.seg == seg then updateAt f c rest else c)

/-! ### getMappings (290-309) -/

mutual
def mappings (n : Node) (v : Nat) : List QN :=
  match n with
  | .mk seg vals kids =>
    let fromKids := (mappingsList kids v).map fun m => if !isBlank seg then seg :: m else m  -- 293-300
    fromKids ++ (if auFind vals v ≥ 0 then [[seg]] else [])        -- 302-306
def mappingsList : List Node → Nat → List QN
  | [], _ => []
  | c :: cs, v => mappings c v ++ mappingsList cs v
end

/-! ### The lookup object -/

structure Lookup where
  root : Node
  size : Int
  share : Bool
  nextId : Nat
deriving Inhabited

/-- Constructor + `init()` (506-522): dummy `"￿"` node with null values. -/
def Lookup.empty (share : Bool) : Lookup :=
  { root := merge share (.mk [] none []) [[CMAX]] none 0, size := 0, share, nextId := 1 }

/-- `put` (584-592). `size++` happens BEFORE merge (line 590). -/
def Lookup.put (t : Lookup) (name : QN) (v : Nat) : Lookup :=
  let arr : Option Arr := some ⟨t.nextId, [v]⟩
  { t with size := t.size + 1,
           root := merge t.share t.root name arr (t.nextId + 1),
           nextId := t.nextId + 2 }

/-- `putAll` (596-603). -/
def Lookup.putAll (t : Lookup) (name : QN) (vs : List Nat) : Lookup :=
  { t with size := t.size + vs.length,
           root := merge t.share t.root name (some ⟨t.nextId, vs⟩) (t.nextId + 1),
           nextId := t.nextId + 2 }

/-- `remove` (607-616). -/
def Lookup.remove (t : Lookup) (name : QN) (v : Nat) : Lookup :=
  match find t.root [] name true with
  | none => t
  | some (path, node) =>
    let (nv, changed) := auRemove node.vals v t.nextId
    if changed then
      { t with root := updateAt (fun n => .mk n.seg nv n.kids) t.root path,
               size := t.size - 1, nextId := t.nextId + 1 }
    else t

/-- `removeMappings` (543-554). -/
def Lookup.removeMappings (t : Lookup) (v : Nat) : Lookup :=
  let (r, (nid, sz)) := removeAll t.root v (t.nextId, t.size)
  { t with root := r, nextId := nid, size := sz }

/-- `get(QualifiedName)` (527-539). -/
def Lookup.get (t : Lookup) (name : QN) : Option (List Nat) :=
  if name.isEmpty then none
  else match find t.root [] name true with
    | some (_, n) => n.vals.map Arr.elems
    | none => none

/-- `matches` of SegmentNode (141-155) and ValueSharingSegmentNode (404-422): the multiset
of returned values (before `excludeDuplicates` set conversion). -/
def valuesOf (share : Bool) (acc : Acc) : List Nat :=
  let arrs := acc.filterMap id
  if share then
    -- HashSet<Object[]> keyed by identity
    let distinct := arrs.foldl (fun (l : List Arr) a => if l.any (·.id == a.id) then l else l ++ [a]) []
    distinct.flatMap Arr.elems
  else arrs.flatMap Arr.elems

/-- `get(QualifiedNamePattern, excludeDuplicates=false)` (562-564) as a multiset (List). -/
def Lookup.getPattern (t : Lookup) (p : Pat) : List Nat :=
  let upperNode := (find t.root [] p.upperExclusive false).map Prod.fst
  let (_, acc) := collect t.root [] p.lowerInclusive.qn upperNode p.isRecursive []
  valuesOf t.share acc

/-- `getMappings` (567-580). -/
def Lookup.getMappings (t : Lookup) (v : Nat) : List QN := mappings t.root v

-- Total number of stored (name, value) pairs (sum of array lengths).
mutual
def countVals (n : Node) : Nat :=
  match n with
  | .mk _ vals kids => (vals.map (·.elems.length)).getD 0 + countValsList kids
def countValsList : List Node → Nat
  | [] => 0
  | c :: cs => countVals c + countValsList cs
end

end Trie
