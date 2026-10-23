--------------------------- MODULE AblateFix3 -----------------------
(***************************************************************************)
(* Model of ParallelResourceLoader.ParallelLoadOperation (PRL) consumed by *)
(* the cluster loop of MonitoredClusteringBuilderState (MCBS).            *)
(*                                                                         *)
(* PRL  = com.avaloq.tools.ddk.xtext.builder/.../resourceloader/           *)
(*        ParallelResourceLoader.java                                      *)
(* MCBS = com.avaloq.tools.ddk.xtext.builder/.../                          *)
(*        MonitoredClusteringBuilderState.java                             *)
(*                                                                         *)
(* Actors: the builder thread (one), NWorkers executor threads per load    *)
(* operation (one operation per cluster), and the environment (user        *)
(* cancellation, external interrupts of the builder thread, timeouts).     *)
(* ABLATION: FIX-3 undone. Derived from the FIXED COPY. Differences from ParallelLoader.tla, marked FIX-n:         *)
(*  FIX-1 PRL:220-221 decrement toProcess only when poll() returned a     *)
(*        result (a timeout no longer consumes a URI that is still        *)
(*        loading; the builder simply polls again).                       *)
(*  FIX-2 PRL:222-224 on InterruptedException restore the flag and throw  *)
(*        OperationCanceledException: an interrupt of the builder is      *)
(*        treated as a cancellation request instead of a "timeout".      *)
(*  FIX-3 PRL:365-371 publishLoadResult gives up once the operation was   *)
(*        cancelled (timed offer loop on a volatile 'cancelled' flag)     *)
(*        instead of an unbounded put() that relies on the interrupt.     *)
(***************************************************************************)
EXTENDS Integers, Sequences, FiniteSets

CONSTANTS
  URIs,                  \* all resources known to the build
  InitQueue,             \* resources queued for the first cluster
  NWorkers,              \* executor threads per load operation (nThreads)
  QCap,                  \* result queue: 0 = SynchronousQueue, -1 = LinkedBlockingQueue, k>0 = ArrayBlockingQueue(k)
  MaxOps,                \* bound on clusters (load operations)
  MaxBuilderInterrupts,  \* bound on external interrupts of the builder thread
  AllowCancel,           \* may the user cancel the build?
  LoadMaySwallowInterrupt \* may doLoadResource clear a worker's interrupt flag?

ASSUME /\ InitQueue \subseteq URIs /\ InitQueue # {}
       /\ NWorkers \in Nat \ {0}
       /\ QCap \in Nat \cup {-1}
       /\ MaxOps \in Nat \ {0}
       /\ MaxBuilderInterrupts \in Nat
       /\ AllowCancel \in BOOLEAN /\ LoadMaySwallowInterrupt \in BOOLEAN

Unbounded == -1                    \* cfg files cannot write -1: use QCap <- Unbounded
Ops == 1..MaxOps
W == 1..NWorkers
Outcomes == {"ok", "exc"}          \* resource loaded / Exception captured in the Triple (PRL:313-319)
Idle == [k |-> "idle"]
Terminal == {"done", "aborted"}

VARIABLES
  queue,      \* MCBS buildData.getURIQueue() (order is irrelevant: queue.remove(uri))
  remaining,  \* MCBS allRemainingURIs: candidates for queueAffectedResources
  op,         \* index of the current load operation
  toProcess,  \* PRL:155 counter of the current operation (builder thread only)
  pending,    \* per op: jobs submitted to the executor, not yet started
  wst,        \* per op, per worker: idle | [k:"load",u] | [k:"put",u,o]
  wintr,      \* per op, per worker: thread interrupt flag
  resQ,       \* per op: contents of the BlockingQueue (always <<>> for SynchronousQueue)
  cancelled,  \* per op: cancel() was called (shutdownNow)
  pc,         \* builder: loop | poll | catch | clusterEnd | done | aborted
  exc,        \* builder: exception being handled: "none" | "timeout" | a URI
  bintr,      \* builder thread interrupt flag
  nbintr,     \* number of external builder interrupts so far
  cancelReq   \* user cancellation (monitor / subProgress isCanceled)

vars == <<queue, remaining, op, toProcess, pending, wst, wintr, resQ, cancelled,
          pc, exc, bintr, nbintr, cancelReq>>

WState == {Idle} \cup [k : {"load"}, u : URIs] \cup [k : {"put"}, u : URIs, o : Outcomes]

TypeOK ==
  /\ queue \subseteq URIs /\ remaining \subseteq URIs
  /\ op \in Ops /\ toProcess \in Int
  /\ pending \in [Ops -> SUBSET URIs]
  /\ wst \in [Ops -> [W -> WState]]
  /\ wintr \in [Ops -> [W -> BOOLEAN]]
  /\ cancelled \in [Ops -> BOOLEAN]
  /\ pc \in {"loop", "poll", "catch", "clusterEnd", "done", "aborted"}
  /\ exc \in {"none", "timeout"} \cup URIs
  /\ bintr \in BOOLEAN /\ cancelReq \in BOOLEAN

-----------------------------------------------------------------------------
(* Helpers *)

\* A result is ready for the builder's poll().
Available(e) ==
  IF QCap = 0 THEN \E w \in W : wst[e][w].k = "put"   \* a producer waits in SynchronousQueue.put
  ELSE resQ[e] # <<>>

\* Results of operation e that will still be delivered (if not cancelled).
InFlight(e) == {w \in W : wst[e][w].k \in {"load", "put"}}
Outstanding(e) == Cardinality(pending[e]) + Cardinality(InFlight(e)) + Len(resQ[e])
OutstandingURIs(e) == pending[e] \cup {wst[e][w].u : w \in InFlight(e)}
                      \cup {resQ[e][i][1] : i \in 1..Len(resQ[e])}

\* PRL:289-298 cancel(): toProcess = 0; executor.shutdownNow() drains queued
\* jobs and interrupts all pool threads.
CancelOpVars(e) ==
  /\ toProcess' = 0
  /\ cancelled' = [cancelled EXCEPT ![e] = TRUE]
  /\ pending' = [pending EXCEPT ![e] = {}]
  /\ wintr' = [wintr EXCEPT ![e] = [w \in W |-> TRUE]]

-----------------------------------------------------------------------------
Init ==
  /\ queue = InitQueue
  /\ remaining = URIs \ InitQueue
  /\ op = 1
  /\ toProcess = Cardinality(InitQueue)          \* PRL:272 load(): toProcess += uris.size()
  /\ pending = [e \in Ops |-> IF e = 1 THEN InitQueue ELSE {}]   \* PRL:280-284 startThreads()
  /\ wst = [e \in Ops |-> [w \in W |-> Idle]]
  /\ wintr = [e \in Ops |-> [w \in W |-> FALSE]]
  /\ resQ = [e \in Ops |-> <<>>]
  /\ cancelled = [e \in Ops |-> FALSE]
  /\ pc = "loop"
  /\ exc = "none"
  /\ bintr = FALSE
  /\ nbintr = 0
  /\ cancelReq = FALSE

-----------------------------------------------------------------------------
(* Builder thread *)

\* MCBS:530-537 inner loop head.
LoopHead ==
  /\ pc = "loop"
  /\ IF queue = {}
       THEN /\ pc' = "clusterEnd"
            /\ UNCHANGED <<toProcess, cancelled, pending, wintr>>
       ELSE IF cancelReq \/ ~(toProcess > 0)          \* subProgress.isCanceled() || !hasNext()
         THEN /\ CancelOpVars(op)                     \* MCBS:535 loadOperation.cancel()
              /\ pc' = "aborted"                      \* MCBS:536 throw OperationCanceledException
         ELSE /\ pc' = "poll"                         \* MCBS:553 loadOperation.next()
              /\ UNCHANGED <<toProcess, cancelled, pending, wintr>>
  /\ UNCHANGED <<queue, remaining, op, wst, resQ, exc, bintr, nbintr, cancelReq>>

\* PRL:220-221 poll() returns a result; toProcess--.
\* LinkedBlockingQueue/ArrayBlockingQueue.poll(timeout) acquire the lock
\* interruptibly, so with the flag set they throw even if an item is present;
\* SynchronousQueue.poll hands off from a waiting producer regardless.
PollReceive ==
  /\ pc = "poll"
  /\ Available(op)
  /\ (~bintr \/ QCap = 0)
  /\ toProcess' = toProcess - 1
  /\ \E u \in URIs, o \in Outcomes :
       /\ IF QCap = 0
            THEN \E w \in W :
                   /\ wst[op][w] = [k |-> "put", u |-> u, o |-> o]
                   /\ wst' = [wst EXCEPT ![op][w] = Idle]      \* producer released
                   /\ UNCHANGED resQ
            ELSE /\ Head(resQ[op]) = <<u, o>>
                 /\ resQ' = [resQ EXCEPT ![op] = Tail(@)]
                 /\ UNCHANGED wst
       /\ IF o = "ok"
            THEN /\ queue' = queue \ {u}                       \* MCBS:556 queue.remove(changedURI)
                 /\ pc' = "loop"                               \* link / validate: not modelled further
                 /\ UNCHANGED exc
            ELSE /\ pc' = "catch"                              \* PRL:236-241 LoadOperationException(uri, ..)
                 /\ exc' = u
                 /\ UNCHANGED queue
  /\ UNCHANGED <<remaining, op, pending, wintr, cancelled, bintr, nbintr, cancelReq>>

\* PRL:220-221,225-230 poll() times out (returns null): toProcess-- still runs,
\* then LoadOperationException(null, TimeoutException).
PollTimeout ==
  /\ pc = "poll"
  /\ ~bintr
  /\ ~Available(op)
  /\ pc' = "catch"                                  \* FIX-1: toProcess unchanged
  /\ exc' = "timeout"
  /\ UNCHANGED <<queue, remaining, op, toProcess, pending, wst, wintr, resQ, cancelled, bintr, nbintr, cancelReq>>

\* PRL:222-224 poll() throws InterruptedException: toProcess-- skipped, the
\* interrupt flag is restored, result == null -> "timeout" LoadOperationException.
PollInterrupted ==
  /\ pc = "poll"
  /\ bintr
  /\ (QCap # 0 \/ ~Available(op))
  /\ CancelOpVars(op)                               \* FIX-2: OperationCanceledException -> finally cancel()
  /\ pc' = "aborted"
  /\ UNCHANGED <<queue, remaining, op, wst, resQ, exc, bintr, nbintr, cancelReq>>

\* MCBS:585-613 catch: pollForCancellation (sleepUninterruptibly keeps the
\* interrupt flag), then drop the failed URI from the queue if known.
Catch ==
  /\ pc = "catch"
  /\ IF cancelReq
       THEN /\ CancelOpVars(op)                    \* MCBS:1345-1346 -> finally MCBS:679-680 cancel()
            /\ pc' = "aborted"
            /\ UNCHANGED queue
       ELSE /\ queue' = IF exc \in URIs THEN queue \ {exc} ELSE queue   \* MCBS:603-605
            /\ pc' = "loop"
            /\ UNCHANGED <<toProcess, cancelled, pending, wintr>>
  /\ exc' = "none"
  /\ UNCHANGED <<remaining, op, wst, resQ, bintr, nbintr, cancelReq>>

\* MCBS:660-669 end of cluster: cancel(), queueAffectedResources, new
\* operation if the queue is non-empty; otherwise leave the outer loop
\* (finally MCBS:679-680 cancels again, idempotent).
ClusterEnd ==
  /\ pc = "clusterEnd"
  /\ \E S \in SUBSET remaining :
       /\ (op = MaxOps => S = {})                  \* model bound on clusters
       /\ queue' = S
       /\ remaining' = remaining \ S
       /\ cancelled' = [cancelled EXCEPT ![op] = TRUE]
       /\ wintr' = [wintr EXCEPT ![op] = [w \in W |-> TRUE]]
       /\ IF S = {}
            THEN /\ pc' = "done"
                 /\ toProcess' = 0
                 /\ pending' = [pending EXCEPT ![op] = {}]
                 /\ UNCHANGED op
            ELSE /\ op' = op + 1
                 /\ toProcess' = Cardinality(S)     \* fresh operation, PRL:272
                 /\ pending' = [pending EXCEPT ![op] = {}, ![op + 1] = S]
                 /\ pc' = "loop"
  /\ UNCHANGED <<wst, resQ, exc, bintr, nbintr, cancelReq>>

-----------------------------------------------------------------------------
(* Executor threads of operation e *)

\* ThreadPoolExecutor hands a queued ResourceLoadJob to a free thread (any
\* order: the Sorter is abstracted away).
WStart(e, w) ==
  /\ wst[e][w] = Idle
  /\ \E u \in pending[e] :
       /\ pending' = [pending EXCEPT ![e] = @ \ {u}]
       /\ wst' = [wst EXCEPT ![e][w] = [k |-> "load", u |-> u]]
  /\ UNCHANGED <<queue, remaining, op, toProcess, wintr, resQ, cancelled, pc, exc, bintr, nbintr, cancelReq>>

\* PRL:307-322 loadResource: succeeds or captures a Throwable. Optionally the
\* load consumes a pending interrupt (e.g. a library calling Thread.interrupted()).
WFinishLoad(e, w) ==
  /\ wst[e][w].k = "load"
  /\ \E o \in Outcomes, clear \in (IF LoadMaySwallowInterrupt /\ wintr[e][w] THEN BOOLEAN ELSE {FALSE}) :
       /\ wst' = [wst EXCEPT ![e][w] = [k |-> "put", u |-> wst[e][w].u, o |-> o]]
       /\ wintr' = IF clear THEN [wintr EXCEPT ![e][w] = FALSE] ELSE wintr
  /\ UNCHANGED <<queue, remaining, op, toProcess, pending, resQ, cancelled, pc, exc, bintr, nbintr, cancelReq>>

\* PRL:365-371 publishLoadResult: resourceQueue.put(result). With the interrupt
\* flag set put() throws and the result is dropped. For SynchronousQueue the
\* successful handoff is PollReceive; the producer stays blocked until then.
WPut(e, w) ==
  /\ wst[e][w].k = "put"
  /\ IF wintr[e][w] \* ABLATED FIX-3
       THEN /\ wst' = [wst EXCEPT ![e][w] = Idle]
            /\ UNCHANGED resQ
       ELSE /\ QCap # 0
            /\ (QCap > 0 => Len(resQ[e]) < QCap)
            /\ resQ' = [resQ EXCEPT ![e] = Append(@, <<wst[e][w].u, wst[e][w].o>>)]
            /\ wst' = [wst EXCEPT ![e][w] = Idle]
  /\ UNCHANGED <<queue, remaining, op, toProcess, pending, wintr, cancelled, pc, exc, bintr, nbintr, cancelReq>>

-----------------------------------------------------------------------------
(* Environment *)

UserCancel ==
  /\ AllowCancel /\ ~cancelReq /\ pc \notin Terminal
  /\ cancelReq' = TRUE
  /\ UNCHANGED <<queue, remaining, op, toProcess, pending, wst, wintr, resQ, cancelled, pc, exc, bintr, nbintr>>

InterruptBuilder ==
  /\ nbintr < MaxBuilderInterrupts /\ ~bintr /\ pc \notin Terminal
  /\ bintr' = TRUE
  /\ nbintr' = nbintr + 1
  /\ UNCHANGED <<queue, remaining, op, toProcess, pending, wst, wintr, resQ, cancelled, pc, exc, cancelReq>>

-----------------------------------------------------------------------------
BuilderStep == LoopHead \/ PollReceive \/ PollTimeout \/ PollInterrupted \/ Catch \/ ClusterEnd
WorkerStep == \E e \in Ops, w \in W : WStart(e, w) \/ WFinishLoad(e, w) \/ WPut(e, w)

Next == BuilderStep \/ WorkerStep \/ UserCancel \/ InterruptBuilder

\* Every thread that can run eventually runs; a poll that keeps finding
\* nothing eventually times out. Cancellation and interrupts get no fairness.
Fairness ==
  /\ WF_vars(LoopHead) /\ WF_vars(PollReceive) /\ WF_vars(PollTimeout)
  /\ WF_vars(PollInterrupted) /\ WF_vars(Catch) /\ WF_vars(ClusterEnd)
  /\ \A e \in Ops, w \in W : WF_vars(WStart(e, w)) /\ WF_vars(WFinishLoad(e, w)) /\ WF_vars(WPut(e, w))

Spec == Init /\ [][Next]_vars /\ Fairness

-----------------------------------------------------------------------------
(* Properties *)

Active == pc \in {"loop", "poll", "catch"}

\* The counter matches the results the current operation will still deliver.
Bookkeeping == (Active /\ ~cancelled[op]) => toProcess = Outstanding(op)

\* Every URI still to be processed has a result on its way (the URI being
\* handled in catch is exempt; it is removed in the same step).
QueueCovered == (Active /\ ~cancelled[op]) =>
                  (queue \ {exc}) \subseteq OutstandingURIs(op)

\* The build is aborted only on genuine user cancellation.
AbortOnlyOnCancel == pc = "aborted" => cancelReq \/ bintr   \* FIX-2: interrupt = cancellation request

\* The build terminates: the queue is drained and the loop exits, or the user cancelled.
BuildCompletes == <>(pc = "done" \/ cancelReq \/ bintr)

\* Weaker: the builder thread leaves the loop at all (done or aborted).
BuilderStops == <>(pc \in Terminal)

\* No executor thread stays blocked forever.
WorkersQuiesce == <>[](\A e \in Ops, w \in W : wst[e][w] = Idle)
\* Reachability witnesses (each is expected to be VIOLATED; guards against vacuity).
WitnessNeverDone == pc # "done"
WitnessNeverSecondCluster == op = 1
WitnessNeverUserAbort == ~(pc = "aborted" /\ cancelReq)
WitnessNeverTwoLoading == Cardinality({w \in W : wst[op][w].k = "load"}) < 2
=============================================================================
