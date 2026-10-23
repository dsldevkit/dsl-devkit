/-!
Model of the binary-model storage path of `MonitoredClusteringBuilderState` (MCBS) and its
interaction with the cluster loop, the DDK `ParallelResourceLoader` (PRL) threads and the shared
`SourceLevelURICache.sources` set (a plain `java.util.HashSet`, Xtext SourceLevelURICache:14).

Instance: three URIs, all rebuilt in this build.
  a (0), c (1) : cluster 1 (initial queue, installed as sources by installSourceLevelURIs)
  b (2)        : cluster 2 (affected by a; added to sources by queueAffectedResources MCBS:1296)
  b depends on a: main-thread linking of b resolves a; optionally loading b also touches a.

Threads: main builder thread (tid 0), cluster-1 loader (tid 1), cluster-2 loader (tid 2),
storage workers (tid 10+i), across executor generations (MCBS:871 recreates the pool).
-/
namespace BinaryStorage

/-- State of the binary file `.<name>bin` on disk. `stale` = complete but from the previous build. -/
inductive Bin | none | stale | part | fresh
  deriving DecidableEq, Hashable, Repr, Inhabited

inductive Task | idle | queued | running | done | failed | dropped | discarded
  deriving DecidableEq, Hashable, Repr, Inhabited

/-- Phases of one `doStoreBinaryResource` (MCBS:740-765). -/
inductive Ph | ser | wr | wrote | rmB
  deriving DecidableEq, Hashable, Repr, Inhabited

structure Job where
  u : Nat
  ph : Ph
  intr : Bool          -- interrupted by shutdownNow
  deriving DecidableEq, Hashable, Repr, Inhabited

structure W where
  gen : Nat
  started : Bool
  job : Option Job
  deriving DecidableEq, Hashable, Repr, Inhabited

/-- An in-flight (non-atomic) operation on the shared HashSet. -/
structure Op where
  tid : Nat
  wr : Bool
  racy : Bool
  deriving DecidableEq, Hashable, Repr, Inhabited

/-- Loader micro-steps: contains() begin/end on the sources set, then publish the result. -/
inductive LA | chkB (x : Nat) | chkE (x : Nat) | pub (x : Nat)
  deriving DecidableEq, Hashable, Repr, Inhabited

/-- Main-thread program instructions. -/
inductive I
  | next (u : Nat) | store (u : Nat) | rmB (u : Nat) | rmE (u : Nat)
  | addB (u : Nat) | addE (u : Nat) | startL2 | shut | awaitT | waitActive | recreate | clear
  | resB (u : Nat) | resE (u : Nat) | done
  deriving DecidableEq, Hashable, Repr, Inhabited

def uA : Nat := 0
def uC : Nat := 1
def uB : Nat := 2

structure Cfg where
  nW : Nat := 1              -- BINARY_STORAGE_EXECUTOR_PARALLELISM (scaled down)
  qcap : Nat := 1            -- BINARY_STORAGE_EXECUTOR_QUEUE_CAPACITY (scaled down)
  timeout : Bool := true     -- awaitTermination may time out (1 min) or be interrupted
  ioFail : Bool := false     -- writeResource may throw IOException (swallowed by Xtext)
  touchDep : Bool := true    -- loading b in a loader thread may load a into the local set
  fixed : Bool := false      -- apply the candidate fix
  plant : Bool := false      -- planted bug (on top of `fixed`): remove from sources before writing
  initBin : Bin := .stale    -- previous build left a complete binary
  raceNondet : Bool := true  -- racing HashSet ops may miss a key / lose a write (false: only flag the race)
  deriving Repr

structure St where
  pc : Nat
  inl : Option Job           -- CallerRunsPolicy: main runs the store itself
  ops : List Op
  inSrc : List Bool
  bin : List Bin
  task : List Task
  rs : List Bool             -- resource currently in the main resource set
  loaded : List Bool         -- loader published the resource
  l1 : List LA
  l2 : List LA
  l2on : Bool
  gen : Nat
  shut : Bool
  queue : List Nat
  workers : List W
  reported : Nat             -- "{} tasks not processed" (MCBS:880)
  setRace : Bool
  partialLd : Bool
  partialMain : Bool
  staleLd : Bool
  staleMain : Bool
  deriving DecidableEq, Hashable, Repr, Inhabited

abbrev Act := String

def storeSeq (c : Cfg) (u : Nat) : List I :=
  if c.fixed then [.next u, .store u] else [.next u, .store u, .rmB u, .rmE u]

def awaitSeq (c : Cfg) : List I :=
  if c.fixed then [.shut, .awaitT, .waitActive, .recreate] else [.shut, .awaitT, .recreate]

/-- Main-thread program, in the order of MCBS.doUpdate L529-690 (bug) or the fixed order. -/
def prog (c : Cfg) : List I :=
  let cluster1 := storeSeq c uA ++ storeSeq c uC
  let qa : List I := if c.fixed then [.addB uB] else [.addB uB, .addE uB]
  let boundary :=
    if c.fixed then qa ++ awaitSeq c ++ [.clear, .startL2]
    else qa ++ [.startL2] ++ awaitSeq c ++ [.clear]
  let res : List I := if c.fixed then [.resB uA] else [.resB uA, .resE uA]
  let cluster2 := [.next uB] ++ res ++ (storeSeq c uB).drop 1
  cluster1 ++ boundary ++ cluster2 ++ awaitSeq c ++ [.done]

def loadSeq (c : Cfg) (x : Nat) : List LA :=
  if c.fixed then [.chkB x] else [.chkB x, .chkE x]

def init (c : Cfg) : St :=
  { pc := 0, inl := none, ops := [],
    inSrc := [true, true, false], bin := [c.initBin, c.initBin, c.initBin],
    task := [.idle, .idle, .idle], rs := [false, false, false], loaded := [false, false, false],
    l1 := loadSeq c uA ++ [.pub uA] ++ loadSeq c uC ++ [.pub uC],
    l2 := loadSeq c uB ++ (if c.touchDep then loadSeq c uA else []) ++ [.pub uB],
    l2on := false, gen := 0, shut := false, queue := [],
    workers := (List.range c.nW).map fun _ => { gen := 0, started := false, job := none },
    reported := 0, setRace := false, partialLd := false, partialMain := false,
    staleLd := false, staleMain := false }

/-! ### Shared-set operations (non-atomic unless the fix makes the set concurrent) -/

def opBegin (s : St) (tid : Nat) (wr : Bool) : St :=
  let clash (o : Op) := o.tid != tid && (o.wr || wr)
  let conflict := s.ops.any clash
  { s with ops := s.ops.map (fun o => if clash o then { o with racy := true } else o) ++ [⟨tid, wr, conflict⟩],
           setRace := s.setRace || conflict }

def opEnd (c : Cfg) (s : St) (tid : Nat) : Bool × St :=
  match s.ops.find? (·.tid == tid) with
  | some o => (o.racy && c.raceNondet, { s with ops := s.ops.filter (·.tid != tid) })
  | none => (false, s)

/-- Possible results of `contains(x)`; a read racing a structural modification (HashMap resize
    installs the new, empty table before transferring) may miss a present key. -/
def containsRes (s : St) (x : Nat) (racy : Bool) : List Bool :=
  if racy then (if s.inSrc[x]! then [true, false] else [false]) else [s.inSrc[x]!]

/-- Possible results of a write to the set: a racing write may be lost. -/
def writeRes (s : St) (x : Nat) (v : Bool) (racy : Bool) : List St :=
  let applied := { s with inSrc := s.inSrc.set x v }
  if racy && s.inSrc[x]! != v then [applied, s] else [applied]

/-- A load of `x` after `shouldLoadFromStorage` answered "is source" = `res`
    (ResourceStorageFacade:49-54 -> StorageAwareResource:82-88). -/
def readOutcome (s : St) (x : Nat) (isMain : Bool) (res : Bool) : St :=
  if res then s else
  match s.bin[x]! with
  | .part => if isMain then { s with partialMain := true } else { s with partialLd := true }
  | .stale => if isMain then { s with staleMain := true } else { s with staleLd := true }
  | _ => s

def setTask (s : St) (u : Nat) (t : Task) : St := { s with task := s.task.set u t }
def setBin (s : St) (u : Nat) (b : Bin) : St := { s with bin := s.bin.set u b }

/-! ### One storage job (`doStoreBinaryResource`) executed by thread `tid` -/

def jobSteps (c : Cfg) (s : St) (tid : Nat) (j : Job) : List (Act × St × Option Job) :=
  let u := j.u
  match j.ph with
  | .ser =>
    if !s.rs[u]! then
      [("store: resource detached, save throws -> deleteStorage (DLRSF:77-79, MCBS:765)",
        setTask (setBin s u .none) u .failed, none)]
    else if c.plant then
      [("PLANTED: sources.remove before write", setBin { s with inSrc := s.inSrc.set u false } u .part,
        some { j with ph := .wr })]
    else
      [("store: generateFile starts writing binary (ResourceStorageFacade:104)", setBin s u .part,
        some { j with ph := .wr })] ++
      (if c.ioFail then
        (if c.fixed then [("store: IOException, fixed: delete storage, keep in sources", setTask (setBin s u .none) u .failed, none)]
         else [("store: writeResource IOException swallowed, old binary kept (ResourceStorageFacade:97-103)", s,
                some { j with ph := .wrote })])
       else [])
  | .wr =>
    [("store: generateFile completes", setBin s u .fresh, some { j with ph := .wrote })] ++
    (if j.intr then [("store: interrupted write fails -> deleteStorage (DLRSF:77-79)",
                      setTask (setBin s u .none) u .failed, none)] else [])
  | .wrote =>
    if c.plant then [("store done", setTask s u .done, none)]
    else if c.fixed then
      [("store: sources.remove after complete write (concurrent set)",
        setTask { s with inSrc := s.inSrc.set u false } u .done, none)]
    else [("store: sources.remove begins (MCBS:754)", opBegin s tid true, some { j with ph := .rmB })]
  | .rmB =>
    let (racy, s') := opEnd c s tid
    (writeRes s' u false racy).map fun t => ("store: sources.remove ends (MCBS:754)", setTask t u .done, none)

/-! ### Successor function -/

def mainSteps (c : Cfg) (s : St) : List (Act × St) :=
  match s.inl with
  | some j =>
    (jobSteps c s 0 j).map fun (a, t, j') => ("main(CallerRuns) " ++ a, { t with inl := j' })
  | none =>
  let p := prog c
  let adv (t : St) : St := { t with pc := t.pc + 1 }
  match p[s.pc]? with
  | none => []
  | some ins =>
  match ins with
  | .done => []
  | .next u =>
    if s.loaded[u]! then [("main: loadOperation.next(), addResource (MCBS:537)", adv { s with rs := s.rs.set u true })]
    else []
  | .store u =>
    let s := adv s
    if s.shut then [("main: execute on shut-down pool, CallerRuns discards silently (MCBS:726)", setTask s u .discarded)]
    else match s.workers.findIdx? (fun w => w.gen == s.gen && !w.started) with
    | some i =>
      [("main: execute -> new core worker (MCBS:726)",
        setTask { s with workers := s.workers.set i { gen := s.gen, started := true, job := some ⟨u, .ser, false⟩ } } u .running)]
    | none =>
      if s.queue.length < c.qcap then
        [("main: execute -> queued (MCBS:726)", setTask { s with queue := s.queue ++ [u] } u .queued)]
      else
        [("main: queue full, CallerRunsPolicy runs store inline (MCBS:726)",
          setTask { s with inl := some ⟨u, .ser, false⟩ } u .running)]
  | .rmB _ => [("main: sources.remove(changedURI) begins (MCBS:656)", adv (opBegin s 0 true))]
  | .rmE u =>
    let (racy, s') := opEnd c s 0
    (writeRes s' u false racy).map fun t => ("main: sources.remove(changedURI) ends (MCBS:656)", adv t)
  | .addB u =>
    if c.fixed then [("main: queueAffectedResources sources.add (concurrent set)", adv { s with inSrc := s.inSrc.set u true })]
    else [("main: queueAffectedResources sources.add begins (MCBS:1296)", adv (opBegin s 0 true))]
  | .addE u =>
    let (racy, s') := opEnd c s 0
    (writeRes s' u true racy).map fun t => ("main: queueAffectedResources sources.add ends (MCBS:1296)", adv t)
  | .startL2 => [("main: next cluster loadOperation.load(queue) starts loaders (MCBS:668-669)", adv { s with l2on := true })]
  | .shut => [("main: binaryStorageExecutor.shutdown() (MCBS:826)", adv { s with shut := true })]
  | .awaitT =>
    let cur := s.workers.filter (·.gen == s.gen)
    let terminated := s.queue.isEmpty && cur.all (·.job.isNone)
    if terminated then [("main: awaitTermination -> true (MCBS:837)", adv s)]
    else if c.timeout then
      -- fix (d): the not-processed stores' outdated binaries are deleted
      let s1 := s.queue.foldl (fun t u => let t := setTask t u .dropped; if c.fixed then setBin t u .none else t) s
      [("main: awaitTermination times out/interrupted -> shutdownNow (MCBS:837,856,879)",
        adv { s1 with queue := [], reported := s.reported + s.queue.length,
                      workers := s.workers.map fun (w : W) =>
                        if w.gen == s.gen then { w with job := w.job.map fun j => { j with intr := true } } else w })]
    else []
  | .waitActive =>
    if (s.workers.filter (·.gen == s.gen)).all (·.job.isNone) then [("main(fixed): wait for active stores", adv s)] else []
  | .recreate =>
    [("main: binaryStorageExecutor = makeBinaryStorageExecutor() (MCBS:871)",
      adv { s with gen := s.gen + 1, shut := false,
                   workers := s.workers ++ (List.range c.nW).map fun _ => { gen := s.gen + 1, started := false, job := none } })]
  | .clear => [("main: clearResourceSet clears resource set (MCBS:1207)", adv { s with rs := [false, false, false] })]
  | .resB x =>
    if s.rs[x]! then [("main: dependency already in resource set", { s with pc := s.pc + (if c.fixed then 1 else 2) })]
    else if c.fixed then
      [("main: resolve dependency -> getResource(load=true), shouldLoadFromStorage",
        adv (readOutcome { s with rs := s.rs.set x true } x true s.inSrc[x]!))]
    else [("main: resolveLazyCrossReferences loads dependency; shouldLoadFromStorage contains() begins (MCBS:564, RSF:51)",
          adv (opBegin s 0 false))]
  | .resE x =>
    let (racy, s') := opEnd c s 0
    (containsRes s' x racy).map fun r =>
      ("main: contains() ends -> " ++ (if r then "parse source" else "load binary (StorageAwareResource:86)"),
       adv (readOutcome { s' with rs := s'.rs.set x true } x true r))

def loaderSteps (c : Cfg) (s : St) (tid : Nat) (l : List LA) (upd : St → List LA → St) : List (Act × St) :=
  let name := if tid == 1 then "loader1: " else "loader2: "
  match l with
  | [] => []
  | a :: rest =>
    match a with
    | .pub x => [(name ++ "publish loaded resource (PRL:323)", upd { s with loaded := s.loaded.set x true } rest)]
    | .chkB x =>
      if c.fixed then [(name ++ "load, shouldLoadFromStorage (concurrent set)", upd (readOutcome s x false s.inSrc[x]!) rest)]
      else [(name ++ s!"load uri#{x}: shouldLoadFromStorage contains() begins (PRL:174, RSF:51)", upd (opBegin s tid false) rest)]
    | .chkE x =>
      let (racy, s') := opEnd c s tid
      (containsRes s' x racy).map fun r =>
        (name ++ s!"contains(uri#{x}) ends -> " ++ (if r then "parse source" else "load binary"),
         upd (readOutcome s' x false r) rest)

def workerSteps (c : Cfg) (s : St) : List (Act × St) := Id.run do
  let mut out : List (Act × St) := []
  for i in [0:s.workers.length] do
    let w := s.workers[i]!
    match w.job with
    | some j =>
      for (a, t, j') in jobSteps c s (10 + i) j do
        out := (s!"worker{i}(gen{w.gen}): " ++ a, { t with workers := t.workers.set i { w with job := j' } }) :: out
    | none =>
      if w.gen == s.gen && w.started then
        match s.queue with
        | u :: q =>
          out := (s!"worker{i}: takes store of uri#{u} from queue",
                  setTask { s with queue := q, workers := s.workers.set i { w with job := some ⟨u, .ser, false⟩ } } u .running) :: out
        | [] => out := out
  return out.reverse

def succ (c : Cfg) (s : St) : List (Act × St) :=
  mainSteps c s ++ workerSteps c s ++
  loaderSteps c s 1 s.l1 (fun t l => { t with l1 := l }) ++
  (if s.l2on then loaderSteps c s 2 s.l2 (fun t l => { t with l2 := l }) else [])

def mainDone (c : Cfg) (s : St) : Bool := s.inl.isNone && (prog c)[s.pc]? == some .done

def terminal (c : Cfg) (s : St) : Bool :=
  mainDone c s && s.workers.all (·.job.isNone) && s.l1.isEmpty && (s.l2.isEmpty || !s.l2on)

/-! ### Properties -/

/-- P1: a rebuilt URI is binary-loadable (not in sources and a binary exists) only when complete. -/
def p1 (s : St) : Bool :=
  (List.range 3).all fun u => s.task[u]! == .idle || s.inSrc[u]! || s.bin[u]! == Bin.none || s.bin[u]! == Bin.fresh

/-- P2: when the build returns, every submitted store completed or was reported as not processed. -/
def p2 (c : Cfg) (s : St) : Bool :=
  !mainDone c s || s.task.all fun t => t == .idle || t == .done || t == .failed || t == .dropped

/-- P3: nobody reads a binary that is being written (partial) ... -/
def p3partial (s : St) : Bool := !s.partialLd && !s.partialMain
/-- ... nor an outdated binary of a URI rebuilt in this build. -/
def p3stale (s : St) : Bool := !s.staleLd && !s.staleMain
/-- P5: when everything has stopped, no rebuilt URI is left with an outdated binary on disk
    (later builds load every non-source URI from its binary). -/
def pEnd (c : Cfg) (s : St) : Bool :=
  !terminal c s || (List.range 3).all fun u => s.task[u]! == .idle || s.bin[u]! != .stale

/-- P4: no unsynchronised concurrent access to the non-thread-safe sources HashSet. -/
def p4 (s : St) : Bool := !s.setRace

end BinaryStorage
