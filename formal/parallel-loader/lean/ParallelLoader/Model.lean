/-
State-machine model of one cluster of MonitoredClusteringBuilderState's linking loop
consuming a ParallelResourceLoader.ParallelLoadOperation.

Abbreviations used in comments:
  PRL  = com.avaloq.tools.ddk.xtext.builder/.../resourceloader/ParallelResourceLoader.java
  MCBS = com.avaloq.tools.ddk.xtext.builder/.../MonitoredClusteringBuilderState.java
  AQS  = JDK 21 java.util.concurrent queue semantics (ArrayBlockingQueue / LinkedBlockingQueue /
         SynchronousQueue poll(timeout) and put)

Scope: a single cluster (MCBS:524-661 with no follow-up queueAffectedResources work), distinct URIs,
every load result is a non-Error outcome (success or Exception: both take the same bookkeeping path,
toProcess-- then queue.remove(uri)). The `toBeDeleted` break (MCBS:557) and the Error rethrow (PRL:242)
are out of scope.
-/
namespace ParallelLoader

/-- Result queue flavour chosen from `queueSize` (PRL:160-166). -/
inductive QKind where
  | unbounded          -- queueSize = -1 : LinkedBlockingQueue
  | sync               -- queueSize =  0 : SynchronousQueue
  | bounded (k : Nat)  -- queueSize =  k : ArrayBlockingQueue(k)
  deriving DecidableEq, Repr, Hashable, Inhabited

/-- Which code is modelled. `original` is the code as written. -/
inductive Variant where
  | original
  | fixTimeout          -- PRL:220-221: decrement toProcess only when poll returned a result
  | fixInterrupt        -- MCBS:531: also leave the loop when the builder thread is interrupted
  | fixBoth
  | plantedNoIncrement  -- planted bug: PRL:272 never adds uris.size() to toProcess
  deriving DecidableEq, Repr

def Variant.fixesTimeout : Variant → Bool
  | .fixTimeout | .fixBoth => true
  | _ => false

def Variant.fixesInterrupt : Variant → Bool
  | .fixInterrupt | .fixBoth => true
  | _ => false

/-- Lifecycle of one ResourceLoadJob (one per URI). -/
inductive Job where
  | pending   -- submitted to the executor, not started (PRL:282)
  | loading   -- running loadResource (PRL:307-322)
  | putting   -- blocked in / about to call resourceQueue.put (PRL:367)
  | queued    -- result sits in resourceQueue
  | consumed  -- result taken by next()
  deriving DecidableEq, Repr, Hashable, Inhabited

/-- Builder program counter. -/
inductive Pc where
  | head     -- MCBS:530-531, top of the inner loop
  | poll     -- inside next(), PRL:220 resourceQueue.poll(waitTime)
  | done     -- queue drained, loop left normally (MCBS:524/530, 661)
  | aborted  -- OperationCanceledException thrown (MCBS:536 or pollForCancellation MCBS:587)
  deriving DecidableEq, Repr, Hashable, Inhabited

structure Cfg where
  n : Nat            -- URIs in the cluster
  threads : Nat      -- nThreads
  qk : QKind
  timeouts : Nat     -- budget of poll timeouts the environment may inject
  interrupts : Nat   -- budget of Thread.interrupt() on the builder thread
  variant : Variant := .original
  deriving Repr

structure St where
  jobs : List Job
  rq : List Nat           -- resourceQueue contents (FIFO of URI indices)
  toProcess : Int         -- ParallelLoadOperation.toProcess
  inQueue : List Bool     -- MCBS `queue` membership per URI
  pc : Pc
  intr : Bool             -- builder thread interrupt flag
  intrSeen : Bool         -- an interrupt was ever delivered (ghost)
  cancelReq : Bool        -- monitor.isCanceled() (ghost + real)
  timeoutsLeft : Nat
  intrLeft : Nat
  opCancelled : Bool      -- CheckedLoadOperation.isCancelled / executor.shutdownNow()
  deriving DecidableEq, Repr, Hashable, Inhabited

inductive Act where
  | start (i : Nat) | loaded (i : Nat) | put (i : Nat)
  | callNext | take (i : Nat) | pollTimeout | pollInterrupted
  | finish | abort
  | userCancel | interrupt
  deriving DecidableEq, Repr, Hashable, Inhabited

/-- Steps that the system takes on its own (no injected failure / user action). -/
def Act.isSystem : Act → Bool
  | .pollTimeout | .userCancel | .interrupt => false
  | _ => true

def Act.javaRef : Act → String
  | .start i => s!"start job {i}: PRL:282 executor.execute -> PRL:205-206 run -> PRL:307-310 loadResource"
  | .loaded i => s!"job {i} load returns: PRL:312-322"
  | .put i => s!"job {i} publishes: PRL:367 resourceQueue.put"
  | .callNext => "builder: MCBS:531 !isCanceled && hasNext (PRL:266 toProcess>0) -> MCBS:553 next() -> PRL:215"
  | .take i => s!"builder: PRL:220 poll returns result {i}, PRL:221 toProcess--; MCBS:553-556 queue.remove(uri {i})"
  | .pollTimeout => "builder: PRL:220 poll returns null after waitTime; PRL:221 toProcess-- ; PRL:225-230 throw LoadOperationException(TimeoutException); MCBS:585-593 caught+logged; MCBS:603 changedURI==null, queue untouched"
  | .pollInterrupted => "builder: PRL:220 poll throws InterruptedException (lockInterruptibly); PRL:222-223 re-interrupt, toProcess NOT decremented; PRL:225-230 throw Timeout LoadOperationException; MCBS:585-593 caught; queue untouched"
  | .finish => "builder: MCBS:530 queue empty -> MCBS:661 cancel -> MCBS:524 exit"
  | .abort => "builder: MCBS:531-536 (isCanceled || !hasNext) -> cancel -> throw OperationCanceledException"
  | .userCancel => "user: progress monitor cancelled"
  | .interrupt => "env: Thread.interrupt() on the builder thread"

def Act.show : Act → String
  | .start i => s!"start({i})" | .loaded i => s!"loaded({i})" | .put i => s!"put({i})"
  | .callNext => "callNext" | .take i => s!"take({i})" | .pollTimeout => "pollTimeout"
  | .pollInterrupted => "pollInterrupted" | .finish => "finish" | .abort => "abort"
  | .userCancel => "userCancel" | .interrupt => "interrupt"

def countJ (p : Job → Bool) (l : List Job) : Nat := (l.filter p).length

def active (s : St) : Nat := countJ (fun j => j == .loading || j == .putting) s.jobs

/-- Results not yet handed to the builder: the actual outstanding work. -/
def outstanding (s : St) : Nat := countJ (fun j => j != .consumed) s.jobs

def terminal (s : St) : Bool := s.pc == .done || s.pc == .aborted

/-- Initial state right after MCBS:509-510 create + load(queue) (PRL:271-285). -/
def init (c : Cfg) : St where
  jobs := List.replicate c.n .pending
  rq := []
  toProcess := if c.variant == .plantedNoIncrement then 0 else c.n
  inQueue := List.replicate c.n true
  pc := .head
  intr := false
  intrSeen := false
  cancelReq := false
  timeoutsLeft := c.timeouts
  intrLeft := c.interrupts
  opCancelled := false

def canPut : QKind → Nat → Bool
  | .unbounded, _ => true
  | .sync, _ => false          -- hand-off happens only when the builder takes from a blocked putter
  | .bounded k, len => len < k

def workerSteps (c : Cfg) (s : St) : List (Act × St) :=
  if s.opCancelled then [] else
  let starts := match s.jobs.findIdx? (· == .pending) with
    | some i => if active s < c.threads then [(Act.start i, { s with jobs := s.jobs.set i .loading })] else []
    | none => []
  let idx := List.range c.n
  let loads := idx.filterMap fun i =>
    if s.jobs[i]? == some .loading then some (Act.loaded i, { s with jobs := s.jobs.set i .putting }) else none
  let puts := idx.filterMap fun i =>
    if s.jobs[i]? == some .putting && canPut c.qk s.rq.length then
      some (Act.put i, { s with jobs := s.jobs.set i .queued, rq := s.rq ++ [i] })
    else none
  starts ++ loads ++ puts

/-- Successful poll of result `i`: PRL:220-221 then MCBS:556 queue.remove. -/
def consume (s : St) (i : Nat) : St :=
  { s with jobs := s.jobs.set i .consumed, toProcess := s.toProcess - 1,
           inQueue := s.inQueue.set i false, pc := .head }

/-- Exception out of next(): MCBS:585-587 catch + pollForCancellation, else back to the loop head. -/
def afterExc (s : St) : St :=
  if s.cancelReq then { s with pc := .aborted, opCancelled := true } else { s with pc := .head }

def timedOut (c : Cfg) (s : St) : St :=
  let s := { s with timeoutsLeft := s.timeoutsLeft - 1 }
  afterExc (if c.variant.fixesTimeout then s else { s with toProcess := s.toProcess - 1 })

def builderSteps (c : Cfg) (s : St) : List (Act × St) :=
  match s.pc with
  | .done | .aborted => []
  | .head =>
    if s.inQueue.all (· == false) then [(.finish, { s with pc := .done, opCancelled := true })]
    else if s.cancelReq || (c.variant.fixesInterrupt && s.intr) || !(decide (s.toProcess > 0)) then
      [(.abort, { s with pc := .aborted, opCancelled := true })]
    else [(.callNext, { s with pc := .poll })]
  | .poll =>
    let timeout := if s.timeoutsLeft > 0 then [(Act.pollTimeout, timedOut c s)] else []
    match c.qk with
    | .sync =>
      -- SynchronousQueue.poll: xfer matches a blocked producer even when interrupted;
      -- otherwise an interrupted caller gets InterruptedException.
      let takes := (List.range c.n).filterMap fun i =>
        if s.jobs[i]? == some .putting then some (Act.take i, consume s i) else none
      if !takes.isEmpty then takes
      else if s.intr then [(.pollInterrupted, afterExc s)]
      else timeout
    | _ =>
      -- Array/LinkedBlockingQueue.poll: lockInterruptibly throws first if the flag is set.
      if s.intr then [(.pollInterrupted, afterExc s)]
      else match s.rq with
        | i :: rest => [(.take i, consume { s with rq := rest } i)]
        | [] => timeout

def envSteps (_c : Cfg) (s : St) : List (Act × St) :=
  if terminal s then [] else
  (if !s.cancelReq then [(Act.userCancel, { s with cancelReq := true })] else []) ++
  (if s.intrLeft > 0 && !s.intr then
     [(Act.interrupt, { s with intr := true, intrSeen := true, intrLeft := s.intrLeft - 1 })] else [])

def succ (c : Cfg) (s : St) : List (Act × St) :=
  builderSteps c s ++ workerSteps c s ++ envSteps c s

/-! Properties -/

/-- P1 bookkeeping: while the operation is live, toProcess equals the results still to be handed over. -/
def P1 (s : St) : Bool :=
  s.pc == .done || s.pc == .aborted || s.opCancelled || s.toProcess == (outstanding s : Int)

/-- P2 the build is aborted only on a genuine request (user cancel; leniently also a thread interrupt). -/
def P2 (s : St) : Bool := s.pc != .aborted || s.cancelReq || s.intrSeen

end ParallelLoader
