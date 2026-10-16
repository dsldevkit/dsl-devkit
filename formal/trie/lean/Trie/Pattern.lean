/-
Model of `QualifiedNamePattern` (QualifiedNamePattern.java).

Each function is a line-by-line transcription; comments cite the Java lines.
-/
import Trie.Basic

namespace Trie

/-- A pattern. `glob = true` models `createFromGlobs` (non-null `globRegexps`). -/
structure Pat where
  segs : QN
  glob : Bool := false
deriving Repr, DecidableEq, Inhabited

/-- A `QualifiedName` value that may or may not be a `QualifiedNamePattern` instance
(the dynamic type matters for `QualifiedNamePattern.Comparator` and `compareTo`). -/
structure Key where
  qn : QN
  isPat : Bool
deriving Repr, DecidableEq, Inhabited

def plain (q : QN) : Key := ⟨q, false⟩

/-- Pattern verification, QualifiedNamePattern.java:124-137 (constructor with `verify = true`). -/
def verifySegs (segs : QN) : Bool :=
  let n := segs.length
  (List.range n).all fun i =>
    let seg := segs.getD i []
    let w := indexOf STAR seg
    if w != -1 && i != n - 1 then false
    else if w != -1 then
      w == seg.length - 1 || (w == seg.length - 2 && charAt seg (w + 1) == STAR)
    else true

/-- `isRecursivePattern`, line 155-157. -/
def Pat.isRecursive (p : Pat) : Bool := endsWithStarStar (lastSeg p.segs)

/-! ### `matches` (lines 260-305), non-glob branch -/

/-- Lines 278-302: loop over pattern segments `i`, with `oc = other.getSegmentCount()`,
`pc = getSegmentCount()`. Returns the method result. -/
def matchLoop (pc oc : Nat) : List Seg → List Seg → Bool
  | [], _ => true                                            -- line 304
  | seg :: ps, others =>
    let w := indexOf STAR seg                                -- line 280
    if w == 0 then                                           -- line 281
      if seg == [STAR, STAR] then true                       -- line 282-283
      else if oc > pc then false                             -- line 284-285
      else matchLoop pc oc ps (others.drop 1)                -- line 287 continue
    else
      let otherSeg := others.headD []                        -- line 289
      let cont := fun (_ : Unit) =>
        if seg != otherSeg then false                        -- line 299-300
        else matchLoop pc oc ps (others.drop 1)
      if w != -1 then                                        -- line 290
        if seg.length > (w + 1).toNat && charAt seg (w + 1) == STAR then   -- line 291
          regionMatches0 seg otherSeg w                      -- line 292
        else if oc > pc then false                           -- line 293-294
        else if regionMatches0 seg otherSeg w then matchLoop pc oc ps (others.drop 1)  -- 295-296
        else cont ()
      else cont ()

/-! ### glob matching (Regexps.fromGlob(glob, ignoreCase = true)) -/

/-- `^glob$` as a Java regex with `*`→`.*`, `?`→`.`; `ci` = CASE_INSENSITIVE|UNICODE_CASE.
(`.` excludes line terminators; the model alphabet contains none.) -/
def globMatchC (ci : Bool) : Seg → Seg → Bool
  | [], s => s.isEmpty
  | g :: gs, s =>
    if g == STAR then
      -- `.*`: try every split
      (List.range (s.length + 1)).any fun k => globMatchC ci gs (s.drop k)
    else match s with
      | [] => false
      | c :: cs => (g == QMARK || (if ci then lowerC g == lowerC c else g == c)) && globMatchC ci gs cs
termination_by g s => (g.length, s.length)
decreasing_by all_goals simp_wf <;> omega

/-- `Regexps.fromGlob(glob)` = `fromGlob(glob, true)`: case-insensitive. -/
def globMatch : Seg → Seg → Bool := globMatchC true

/-- `matches(other)` for a plain (non-pattern) `other`. Lines 260-305.
Returns `none` when the Java code throws (glob with empty last segment: `charAt(-1)`). -/
def Pat.matchesQ (p : Pat) (other : Key) : Option Bool :=
  let pc := p.segs.length
  let oc := other.qn.length
  if oc < pc then some false                                   -- 261-262
  else if other.isPat then some false                          -- 263-264
  else if p.glob then                                          -- 266
    let ls := lastSeg p.segs
    if ls.isEmpty then none                                    -- 268: charAt(-1) throws
    else if ls.getLast! != STAR && oc > pc then some false     -- 268-269
    else some ((List.range pc).all fun i => globMatch (p.segs.getD i []) (other.qn.getD i []))
  else some (matchLoop pc oc p.segs other.qn)

def Pat.matches (p : Pat) (n : QN) : Bool := (p.matchesQ (plain n)).getD false

/-! ### lowerInclusive (lines 312-345) -/

/-- Glob branch, lines 314-336. -/
def globLower (segs : QN) : QN :=
  let rec scan : Nat → List Seg → Option (Nat × Int)
    | _, [] => none
    | i, pat :: rest =>
      let anyIdx := indexOf STAR pat
      let oneIdx := indexOf QMARK pat
      if anyIdx != -1 || oneIdx != -1 then
        let w0 : Int := if anyIdx != -1 then anyIdx else 0
        let w := if oneIdx != -1 && (oneIdx < anyIdx || anyIdx == -1) then oneIdx else w0
        some (i, w)
      else scan (i + 1) rest
  -- firstWildcardSeg is initialised to 0 (line 315) and never -1, so line 335 is dead code.
  let (fws, wi) := (scan 0 segs).getD (0, 0)
  segs.take fws ++ [substr0 (segs.getD fws []) wi]

def Pat.lowerInclusive (p : Pat) : Key :=
  if p.glob then plain (globLower p.segs)
  else
    let ls := lastSeg p.segs
    let w := lastIndexOf STAR ls                                   -- 339
    if w == -1 then ⟨p.segs, true⟩                                 -- 340: `this` (a pattern!)
    else plain (dropLastQ p.segs ++ [substr0 ls (if endsWithStarStar ls then w - 1 else w)])  -- 341

/-! ### upperExclusive (lines 352-394) -/

def Pat.upperExclusive (p : Pat) : QN :=
  if p.glob then                                                   -- 354-368
    let li := (p.lowerInclusive).qn
    let ls := lastSeg li
    if ls.isEmpty then
      if li.length == 1 then [[BANG]]                              -- 359
      else
        let li' := dropLastQ li
        dropLastQ li' ++ [lastSeg li' ++ [BANG]]                   -- 361-363
    else
      let lci := ls.length - 1
      dropLastQ li ++ [ls.take lci ++ [charSucc (ls.getD lci 0)]]  -- 366-367
  else
    let ls := lastSeg p.segs
    let w := lastIndexOf STAR ls                                   -- 371
    if w == 0 || (w == 1 && ls == [STAR, STAR]) then               -- 372
      if p.segs.length == 1 then [[BANG]]                          -- 373-374
      else
        let ub := dropLastQ p.segs                                 -- 376
        let l2 := lastSeg ub
        if l2.length > 0 then dropLastQ ub ++ [l2 ++ [BANG]]       -- 378-379
        else dropLastQ ub ++ [[BANG]]                              -- 382
    else if w == -1 then dropLastQ p.segs ++ [ls ++ [BANG]]        -- 386
    else
      let lci : Int := if charAt ls (w - 1) == STAR then w - 2 else w - 1    -- 388
      dropLastQ p.segs ++ [substr0 ls lci ++ [charSucc (charAt ls lci)]]   -- 389

/-! ### compareTo(other, ignoreCase=false) for a pattern receiver and plain `other` (lines 210-250) -/

def patCmpLoop : List Seg → List Seg → Ordering
  | [], _ => .lt                                                   -- 249
  | _ :: _, [] => .gt                                              -- 219-220 (i == o2SegmentCount)
  | s1 :: r1, s2 :: r2 =>
    if s1.isEmpty then .lt                                         -- 224-225
    else if s1 == [STAR] then patCmpLoop r1 r2                     -- 226-227
    else
      let w := indexOf STAR s1
      if w == -1 then                                              -- 230-234
        match strCmp s1 s2 with
        | .eq => patCmpLoop r1 r2
        | o => o
      else if s2.length < w.toNat then strCmp (substr0 s1 w) s2    -- 236-238
      else match strCmp (substr0 s1 w) (substr0 s2 w) with         -- 240-245
        | .eq => patCmpLoop r1 r2
        | o => o

/-- `a.compareTo(b)` where the dynamic type of `a` decides dispatch. -/
def keyCompareTo (a b : Key) : Ordering :=
  if a.isPat && !b.isPat then patCmpLoop a.qn b.qn
  else qnCmp a.qn b.qn

/-- `QualifiedNamePattern.Comparator.compare` (lines 44-47). -/
def comparatorCmp (o1 o2 : Key) : Ordering :=
  if o1.isPat then keyCompareTo o1 o2 else (keyCompareTo o2 o1).swap

/-- `toLowerCase()` of a pattern (lines 179-193): segments lower-cased, stays a pattern,
glob regexps are shared (they are already case-insensitive). -/
def Pat.toLower (p : Pat) : Pat := { p with segs := lowerQ p.segs }

end Trie
