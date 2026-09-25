---------------------------- MODULE BinaryStorage ----------------------------
(***************************************************************************)
(* Binary-model storage in MonitoredClusteringBuilderState (MCBS) and its  *)
(* interaction with the cluster loop, the shared source-level URI set and  *)
(* the ParallelResourceLoader (PRL) threads of the current/next cluster.   *)
(*                                                                         *)
(* MCBS = com.avaloq.tools.ddk.xtext.builder/src/com/avaloq/tools/ddk/     *)
(*        xtext/builder/MonitoredClusteringBuilderState.java               *)
(* PRL  = .../xtext/builder/resourceloader/ParallelResourceLoader.java     *)
(*                                                                         *)
(* Actors: builder (main) thread; binary-storage ThreadPoolExecutor        *)
(* workers, one executor per "generation" (MCBS:871 creates a new one      *)
(* after every await); PRL loader jobs (one per queued URI).               *)
(*                                                                         *)
(* All Fix*/Plant* flags FALSE = the code as written.                      *)
(***************************************************************************)
EXTENDS Integers, Sequences, FiniteSets

CONSTANTS
  Clusters,        \* sequence of URI sets: Clusters[1] = toBeUpdated, Clusters[k+1] = affected by cluster k
  Deps,            \* [URIs -> SUBSET URIs]: resources whose load may be triggered when linking/loading u
  NStore,          \* BINARY_STORAGE_EXECUTOR_PARALLELISM (MCBS:124)
  QCap,            \* BINARY_STORAGE_EXECUTOR_QUEUE_CAPACITY (MCBS:125), >= 1
  MaxTimeouts,     \* bound on awaitTermination timeouts / interrupts of the builder (MCBS:837, 865)
  LoaderLoadsDeps, \* may a PRL job's load of u also load u's dependencies in its local resource set?
  AllowLoadFail,   \* may a PRL job return an exception (LoadOperationException(uri), MCBS:595-597)?
  AllowLinkFail,   \* may linking throw an Exception after addResource (outer catch, MCBS:585-613)?
  FixA,            \* drop the main-thread sources.remove (MCBS:656); rely on MCBS:754
  FixB,            \* sources set is thread-safe (every access atomic)
  FixC,            \* after shutdownNow, keep waiting until running store tasks have finished
  FixD,            \* do not store a resource whose processing threw (MCBS:654 skipped)
  PlantBug         \* planted: worker removes from sources BEFORE writing the binary

URIs == UNION {Clusters[i] : i \in 1..Len(Clusters)}
NC   == Len(Clusters)
Gens == 1..(NC + 1)              \* one executor per await (NC-1 between clusters + 1 final) + the fresh one
WIds == Gens \X (1..NStore)
NoURI  == "-"            \* placeholder for URI-typed variables
NoTask == <<"-", 0>>     \* placeholder for store-task variables (task = <<uri, cluster>>)
LDeps(u) == IF LoaderLoadsDeps THEN {u} \cup Deps[u] ELSE {u}   \* shouldLoadFromStorage(u) is always asked for u itself

ASSUME /\ NC >= 1 /\ NStore >= 1 /\ QCap >= 1 /\ MaxTimeouts \in Nat
       /\ \A u \in URIs : Deps[u] \subseteq URIs

VARIABLES
  \* builder thread
  mpc, k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
  \* shared state
  sources,   \* SourceLevelURICache.getSources(): a plain java.util.HashSet (Xtext SourceLevelURICache.<init>)
  inBuild,   \* URIs that are (re)built in this build (entered sources via install/queueAffected)
  binary,    \* on-disk binary per URI: "old" (previous build) | "none" | "partial" | "new"
  \* PRL loader jobs
  lpc, ltodo, lcur, lres, lacc,
  \* storage executors and workers
  est, eq, nstarted, wpc, wtask, wintr, wacc,
  \* history
  sst,       \* per URI store status: no | queued | running | ok | failed | dropped | discarded
  reads,     \* set of <<who, uri, binaryState, uriInBuild>>: binary actually opened
  detached   \* a store serialised a resource no longer in the builder's resource set

mainVars == <<mpc, k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc>>
loadVars == <<lpc, ltodo, lcur, lres, lacc>>
execVars == <<est, eq, nstarted>>
workVars == <<wpc, wtask, wintr, wacc>>
vars == <<mainVars, sources, inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

Init ==
  /\ mpc = "head" /\ k = 1 /\ queue = Clusters[1] /\ cur = NoURI
  /\ depsTodo = {} /\ mdep = NoURI /\ mainRS = {} /\ live = {}
  /\ toAdd = {} /\ qcur = NoURI /\ gen = 1 /\ aret = "none" /\ timeouts = 0 /\ macc = "none"
  /\ sources = Clusters[1]                     \* installSourceLevelURIs (MCBS:454, 1524-1536)
  /\ inBuild = Clusters[1]
  /\ binary \in [URIs -> {"old", "none"}]
  /\ lpc = [u \in URIs |-> IF u \in Clusters[1] THEN "run" ELSE "off"]   \* loadOperation.load(queue) MCBS:510
  /\ ltodo = [u \in URIs |-> IF u \in Clusters[1] THEN LDeps(u) ELSE {}]
  /\ lcur = [u \in URIs |-> NoURI] /\ lres = [u \in URIs |-> "none"] /\ lacc = [u \in URIs |-> "none"]
  /\ est = [g \in Gens |-> "running"] /\ eq = [g \in Gens |-> <<>>] /\ nstarted = [g \in Gens |-> 0]
  /\ wpc = [w \in WIds |-> "none"] /\ wtask = [w \in WIds |-> NoTask]
  /\ wintr = [w \in WIds |-> FALSE] /\ wacc = [w \in WIds |-> "none"]
  /\ sst = [u \in URIs |-> "no"] /\ reads = {} /\ detached = FALSE

-----------------------------------------------------------------------------
(* Builder thread                                                          *)

\* Inner loop head: next() returns a finished PRL job (MCBS:531-556).
MHead ==
  /\ mpc = "head"
  /\ IF queue = {}
     THEN /\ mpc' = "endCluster"
          /\ UNCHANGED <<queue, cur, depsTodo, mainRS, live, lpc>>
     ELSE \E u \in queue :
          /\ lpc[u] = "deliv"
          /\ lpc' = [lpc EXCEPT ![u] = "done"]
          /\ cur' = u
          /\ queue' = queue \ {u}                        \* MCBS:556 / MCBS:604
          /\ IF lres[u] = "fail"
             THEN \* LoadOperationException(uri): resource stays null, no store (MCBS:595-613, 654)
                  /\ mpc' = "rmsrc1"
                  /\ UNCHANGED <<depsTodo, mainRS, live>>
             ELSE /\ mainRS' = mainRS \cup {u}            \* addResource MCBS:553
                  /\ live' = live \cup {<<u, k>>}
                  /\ depsTodo' = Deps[u]
                  /\ mpc' = "link"
  /\ UNCHANGED <<k, mdep, toAdd, qcur, gen, aret, timeouts, macc, sources, inBuild, binary,
                 ltodo, lcur, lres, lacc, execVars, workVars, sst, reads, detached>>

\* resolveLazyCrossReferences (MCBS:569): loads dependencies into the builder's resource set.
Link ==
  /\ mpc = "link"
  /\ IF depsTodo = {}
     THEN \/ /\ mpc' = "submit" /\ UNCHANGED <<depsTodo, mdep, mainRS, live, macc>>
          \/ /\ AllowLinkFail                              \* exception after addResource:
             /\ live' = live \ {<<cur, k>>}                \* resourceSet.getResources().remove(resource) MCBS:608
             /\ mainRS' = mainRS \ {cur}
             /\ mpc' = IF FixD THEN "rmsrc1" ELSE "submit"   \* MCBS:654 still stores it
             /\ UNCHANGED <<depsTodo, mdep, macc>>
     ELSE \E d \in depsTodo :
          IF d \in mainRS
          THEN /\ depsTodo' = depsTodo \ {d}
               /\ UNCHANGED <<mpc, mdep, mainRS, live, macc>>
          ELSE /\ mdep' = d
               /\ macc' = IF FixB THEN "none" ELSE "r"   \* shouldLoadFromStorage: sources.contains(d)
               /\ mpc' = "linkChk"
               /\ UNCHANGED <<depsTodo, mainRS, live>>
  /\ UNCHANGED <<k, queue, cur, toAdd, qcur, gen, aret, timeouts, sources, inBuild, binary,
                 loadVars, execVars, workVars, sst, reads, detached>>

LinkChk ==
  /\ mpc = "linkChk"
  /\ macc' = "none"
  /\ reads' = IF mdep \notin sources /\ binary[mdep] # "none"
              THEN reads \cup {<<"main", mdep, binary[mdep], mdep \in inBuild>>}
              ELSE reads
  /\ mainRS' = mainRS \cup {mdep}
  /\ depsTodo' = depsTodo \ {mdep}
  /\ mpc' = "link"
  /\ UNCHANGED <<k, queue, cur, mdep, live, toAdd, qcur, gen, aret, timeouts, sources, inBuild, binary,
                 loadVars, execVars, workVars, sst, detached>>

\* storeBinaryResource (MCBS:716-736) -> ThreadPoolExecutor.execute with CallerRunsPolicy.
Submit ==
  /\ mpc = "submit"
  /\ LET g == gen
         t == <<cur, k>>
         w == <<g, nstarted[g] + 1>>
     IN IF est[g] # "running"
        THEN \* CallerRunsPolicy.rejectedExecution: silently discards when shut down
             /\ sst' = [sst EXCEPT ![cur] = "discarded"]
             /\ mpc' = "rmsrc1"
             /\ UNCHANGED <<eq, nstarted, wpc, wtask>>
        ELSE IF nstarted[g] < NStore
        THEN \* workerCount < corePoolSize: addWorker(command, true)
             /\ nstarted' = [nstarted EXCEPT ![g] = @ + 1]
             /\ wpc' = [wpc EXCEPT ![w] = "ser"]
             /\ wtask' = [wtask EXCEPT ![w] = t]
             /\ sst' = [sst EXCEPT ![cur] = "running"]
             /\ mpc' = "rmsrc1"
             /\ UNCHANGED eq
        ELSE IF Len(eq[g]) < QCap
        THEN /\ eq' = [eq EXCEPT ![g] = Append(@, t)]
             /\ sst' = [sst EXCEPT ![cur] = "queued"]
             /\ mpc' = "rmsrc1"
             /\ UNCHANGED <<nstarted, wpc, wtask>>
        ELSE \* queue full: CallerRunsPolicy runs doStoreBinaryResource on the builder thread
             /\ sst' = [sst EXCEPT ![cur] = "running"]
             /\ mpc' = "crSer"
             /\ UNCHANGED <<eq, nstarted, wpc, wtask>>
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, est, wintr, wacc, reads, detached>>

\* doStoreBinaryResource on the builder thread (CallerRunsPolicy).
CrSer ==
  /\ mpc = "crSer"
  /\ detached' = (detached \/ <<cur, k>> \notin live)
  /\ mpc' = "crWrite"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, execVars, workVars, sst, reads>>

CrWrite ==
  /\ mpc = "crWrite"
  /\ \/ /\ binary' = [binary EXCEPT ![cur] = "partial"] /\ mpc' = "crWriteEnd"   \* fsa.generateFile starts
     \/ /\ binary' = [binary EXCEPT ![cur] = "none"] /\ mpc' = "crRm1"           \* errors: deleteStorage
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, loadVars, execVars, workVars, sst, reads, detached>>

CrWriteEnd ==
  /\ mpc = "crWriteEnd"
  /\ binary' = [binary EXCEPT ![cur] = "new"]
  /\ mpc' = "crRm1"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, loadVars, execVars, workVars, sst, reads, detached>>

CrRm1 ==   \* MCBS:754
  /\ mpc = "crRm1"
  /\ IF FixB
     THEN /\ sources' = sources \ {cur} /\ sst' = [sst EXCEPT ![cur] = "ok"] /\ mpc' = "rmsrc1"
          /\ UNCHANGED macc
     ELSE /\ macc' = "w" /\ mpc' = "crRm2" /\ UNCHANGED <<sources, sst>>
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts,
                 inBuild, binary, loadVars, execVars, workVars, reads, detached>>

CrRm2 ==
  /\ mpc = "crRm2"
  /\ sources' = sources \ {cur} /\ macc' = "none"
  /\ sst' = [sst EXCEPT ![cur] = "ok"]
  /\ mpc' = "rmsrc1"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts,
                 inBuild, binary, loadVars, execVars, workVars, reads, detached>>

\* buildData.getSourceLevelURICache().getSources().remove(changedURI)  (MCBS:656)
RmSrc1 ==
  /\ mpc = "rmsrc1"
  /\ IF FixA
     THEN /\ mpc' = "head" /\ UNCHANGED <<sources, macc>>
     ELSE IF FixB
     THEN /\ sources' = sources \ {cur} /\ mpc' = "head" /\ UNCHANGED macc
     ELSE /\ macc' = "w" /\ mpc' = "rmsrc2" /\ UNCHANGED sources
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts,
                 inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

RmSrc2 ==
  /\ mpc = "rmsrc2"
  /\ sources' = sources \ {cur} /\ macc' = "none"
  /\ mpc' = "head"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts,
                 inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

\* Inner loop exit: loadOperation.cancel() (MCBS:661), then queueAffectedResources (MCBS:663).
EndCluster ==
  /\ mpc = "endCluster"
  /\ IF k < NC
     THEN /\ toAdd' = Clusters[k + 1] /\ mpc' = "qa"
     ELSE /\ mpc' = "exitLoop" /\ UNCHANGED toAdd
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

\* queueAffectedResources: buildData.queueURI(uri); sources.add(uri)  (MCBS:1296-1298, 1312-1314)
QA ==
  /\ mpc = "qa"
  /\ IF toAdd = {}
     THEN /\ mpc' = "newLoad" /\ UNCHANGED <<queue, qcur, macc, toAdd, sources, inBuild>>
     ELSE \E u \in toAdd :
          /\ queue' = queue \cup {u}
          /\ IF FixB
             THEN /\ sources' = sources \cup {u} /\ inBuild' = inBuild \cup {u}
                  /\ toAdd' = toAdd \ {u} /\ mpc' = "qa" /\ UNCHANGED <<qcur, macc>>
             ELSE /\ qcur' = u /\ macc' = "w" /\ mpc' = "qa2"
                  /\ UNCHANGED <<toAdd, sources, inBuild>>
  /\ UNCHANGED <<k, cur, depsTodo, mdep, mainRS, live, gen, aret, timeouts,
                 binary, loadVars, execVars, workVars, sst, reads, detached>>

QA2 ==
  /\ mpc = "qa2"
  /\ sources' = sources \cup {qcur} /\ inBuild' = inBuild \cup {qcur}
  /\ toAdd' = toAdd \ {qcur} /\ macc' = "none" /\ mpc' = "qa"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, qcur, gen, aret, timeouts,
                 binary, loadVars, execVars, workVars, sst, reads, detached>>

\* if (!queue.isEmpty()) { loadOperation = create(...); loadOperation.load(queue); }  (MCBS:667-670)
\* -- the next cluster's PRL jobs start BEFORE clearResourceSet awaits the storage executor (MCBS:672-674).
NewLoad ==
  /\ mpc = "newLoad"
  /\ IF queue # {}
     THEN /\ lpc' = [u \in URIs |-> IF u \in queue THEN "run" ELSE lpc[u]]
          /\ ltodo' = [u \in URIs |-> IF u \in queue THEN LDeps(u) ELSE ltodo[u]]
          /\ mpc' = "clear"
     ELSE /\ mpc' = "exitLoop" /\ UNCHANGED <<lpc, ltodo>>
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, lcur, lres, lacc, execVars, workVars, sst, reads, detached>>

Terminated(g) ==
  /\ est[g] # "running" /\ eq[g] = <<>>
  /\ \A i \in 1..NStore : wpc[<<g, i>>] \in {"none", "exit"}

\* awaitBinaryStorageExecutorTermination: from clearResourceSet (MCBS:1204) or finally (MCBS:684).
AwShut ==
  /\ mpc \in {"clear", "exitLoop"}
  /\ est' = [est EXCEPT ![gen] = IF @ = "running" THEN "shutdown" ELSE @]   \* shutdown() MCBS:826
  /\ aret' = mpc
  /\ mpc' = "awWait"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, timeouts, macc,
                 sources, inBuild, binary, loadVars, eq, nstarted, workVars, sst, reads, detached>>

AwOk ==   \* awaitTermination returns true (MCBS:837, 859)
  /\ mpc = "awWait"
  /\ Terminated(gen)
  /\ mpc' = "awDone"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

\* awaitTermination times out (retryCount = 0 -> loop exits) or throws InterruptedException:
\* terminateBinaryStorageExecutor -> shutdownNow() (MCBS:838-867, 877-881).
AwTimeout ==
  /\ mpc = "awWait"
  /\ ~Terminated(gen)
  /\ timeouts < MaxTimeouts
  /\ timeouts' = timeouts + 1
  /\ est' = [est EXCEPT ![gen] = "stop"]
  /\ sst' = [u \in URIs |-> IF \E i \in 1..Len(eq[gen]) : eq[gen][i][1] = u THEN "dropped" ELSE sst[u]]
  /\ eq' = [eq EXCEPT ![gen] = <<>>]                         \* "{} tasks not processed"
  /\ wintr' = [w \in WIds |-> IF w[1] = gen /\ wpc[w] \notin {"none", "exit"} THEN TRUE ELSE wintr[w]]
  /\ mpc' = IF FixC THEN "awDrain" ELSE "awDone"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, macc,
                 sources, inBuild, binary, loadVars, nstarted, wpc, wtask, wacc, reads, detached>>

AwDrain ==  \* FixC only: wait for running tasks after shutdownNow
  /\ mpc = "awDrain"
  /\ Terminated(gen)
  /\ mpc' = "awDone"
  /\ UNCHANGED <<k, queue, cur, depsTodo, mdep, mainRS, live, toAdd, qcur, gen, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

\* binaryStorageExecutor = makeBinaryStorageExecutor() (MCBS:871); then clear the resource set (MCBS:1207)
AwDone ==
  /\ mpc = "awDone"
  /\ gen' = gen + 1
  /\ IF aret = "clear"
     THEN /\ mainRS' = {} /\ live' = {} /\ k' = k + 1 /\ mpc' = "head"
     ELSE /\ mpc' = "done" /\ UNCHANGED <<mainRS, live, k>>
  /\ UNCHANGED <<queue, cur, depsTodo, mdep, toAdd, qcur, aret, timeouts, macc,
                 sources, inBuild, binary, loadVars, execVars, workVars, sst, reads, detached>>

MainStep == MHead \/ Link \/ LinkChk \/ Submit \/ CrSer \/ CrWrite \/ CrWriteEnd \/ CrRm1 \/ CrRm2
            \/ RmSrc1 \/ RmSrc2 \/ EndCluster \/ QA \/ QA2 \/ NewLoad \/ AwShut \/ AwOk \/ AwDrain \/ AwDone

-----------------------------------------------------------------------------
(* Storage workers: doStoreBinaryResource (MCBS:746-769)                   *)

WTake(w) ==
  LET g == w[1] IN
  /\ wpc[w] = "idle"
  /\ IF est[g] = "stop" \/ (est[g] = "shutdown" /\ eq[g] = <<>>)
     THEN /\ wpc' = [wpc EXCEPT ![w] = "exit"] /\ UNCHANGED <<eq, wtask, sst>>
     ELSE /\ eq[g] # <<>>
          /\ wtask' = [wtask EXCEPT ![w] = Head(eq[g])]
          /\ eq' = [eq EXCEPT ![g] = Tail(@)]
          /\ sst' = [sst EXCEPT ![Head(eq[g])[1]] = "running"]
          /\ wpc' = [wpc EXCEPT ![w] = "ser"]
  /\ UNCHANGED <<mainVars, sources, inBuild, binary, loadVars, est, nstarted, wintr, wacc, reads, detached>>

\* createResourceStorageWritable(bout).writeResource(resource): serialises the EMF resource in memory
WSer(w) ==
  /\ wpc[w] = "ser"
  /\ detached' = (detached \/ wtask[w] \notin live)
  /\ wpc' = [wpc EXCEPT ![w] = IF PlantBug THEN "rm1" ELSE "write"]
  /\ UNCHANGED <<mainVars, sources, inBuild, binary, loadVars, execVars, wtask, wintr, wacc, sst, reads>>

WWrite(w) ==
  LET u == wtask[w][1] IN
  /\ wpc[w] = "write"
  /\ \/ /\ binary' = [binary EXCEPT ![u] = "partial"]       \* fsa.generateFile in progress
        /\ wpc' = [wpc EXCEPT ![w] = "wend"] /\ UNCHANGED sst
     \/ /\ binary' = [binary EXCEPT ![u] = "none"]          \* resource has errors: deleteStorage (DLRSF:72-76)
        /\ wpc' = [wpc EXCEPT ![w] = "rm1"] /\ UNCHANGED sst
  /\ UNCHANGED <<mainVars, sources, inBuild, loadVars, execVars, wtask, wintr, wacc, reads, detached>>

WWriteEnd(w) ==
  LET u == wtask[w][1] IN
  /\ wpc[w] = "wend"
  /\ \/ /\ binary' = [binary EXCEPT ![u] = "new"]
        /\ wpc' = [wpc EXCEPT ![w] = IF PlantBug THEN "fin" ELSE "rm1"] /\ UNCHANGED sst
     \/ /\ wintr[w]                                          \* interrupted write throws: catch deletes storage,
        /\ binary' = [binary EXCEPT ![u] = "none"]           \* rethrow, logged (DLRSF:77-79, MCBS:765-767)
        /\ sst' = [sst EXCEPT ![u] = "failed"]
        /\ wpc' = [wpc EXCEPT ![w] = "idle"]
  /\ UNCHANGED <<mainVars, sources, inBuild, loadVars, execVars, wtask, wintr, wacc, reads, detached>>

WRm1(w) ==   \* getSources().remove(resource.getURI())  (MCBS:754)
  LET u == wtask[w][1] IN
  /\ wpc[w] = "rm1"
  /\ IF FixB
     THEN /\ sources' = sources \ {u}
          /\ wpc' = [wpc EXCEPT ![w] = IF PlantBug THEN "write" ELSE "fin"]
          /\ UNCHANGED wacc
     ELSE /\ wacc' = [wacc EXCEPT ![w] = "w"] /\ wpc' = [wpc EXCEPT ![w] = "rm2"] /\ UNCHANGED sources
  /\ UNCHANGED <<mainVars, inBuild, binary, loadVars, execVars, wtask, wintr, sst, reads, detached>>

WRm2(w) ==
  LET u == wtask[w][1] IN
  /\ wpc[w] = "rm2"
  /\ sources' = sources \ {u} /\ wacc' = [wacc EXCEPT ![w] = "none"]
  /\ wpc' = [wpc EXCEPT ![w] = IF PlantBug THEN "write" ELSE "fin"]
  /\ UNCHANGED <<mainVars, inBuild, binary, loadVars, execVars, wtask, wintr, sst, reads, detached>>

WFin(w) ==
  /\ wpc[w] = "fin"
  /\ sst' = [sst EXCEPT ![wtask[w][1]] = "ok"]
  /\ wpc' = [wpc EXCEPT ![w] = "idle"]
  /\ UNCHANGED <<mainVars, sources, inBuild, binary, loadVars, execVars, wtask, wintr, wacc, reads, detached>>

WStep(w) == WTake(w) \/ WSer(w) \/ WWrite(w) \/ WWriteEnd(w) \/ WRm1(w) \/ WRm2(w) \/ WFin(w)

-----------------------------------------------------------------------------
(* PRL jobs: localResourceSet.getResource(uri, true) (PRL:344-349)         *)
(* StorageAwareResource.load -> shouldLoadFromStorage: sources.contains(d) *)
(* via the SourceLevelURIsAdapter installed WITHOUT copy (PRL:168-174).    *)

LRun(u) ==
  /\ lpc[u] = "run"
  /\ IF ltodo[u] = {}
     THEN /\ lres' = [lres EXCEPT ![u] = "ok"] \/ (AllowLoadFail /\ lres' = [lres EXCEPT ![u] = "fail"])
          /\ lpc' = [lpc EXCEPT ![u] = "deliv"]
          /\ UNCHANGED <<ltodo, lcur, lacc>>
     ELSE \E d \in ltodo[u] :
          /\ lcur' = [lcur EXCEPT ![u] = d]
          /\ lacc' = [lacc EXCEPT ![u] = IF FixB THEN "none" ELSE "r"]
          /\ lpc' = [lpc EXCEPT ![u] = "chk"]
          /\ UNCHANGED <<ltodo, lres>>
  /\ UNCHANGED <<mainVars, sources, inBuild, binary, execVars, workVars, sst, reads, detached>>

LChk(u) ==
  LET d == lcur[u] IN
  /\ lpc[u] = "chk"
  /\ lacc' = [lacc EXCEPT ![u] = "none"]
  /\ reads' = IF d \notin sources /\ binary[d] # "none"
              THEN reads \cup {<<"loader", d, binary[d], d \in inBuild>>}
              ELSE reads
  /\ ltodo' = [ltodo EXCEPT ![u] = @ \ {d}]
  /\ lpc' = [lpc EXCEPT ![u] = "run"]
  /\ UNCHANGED <<mainVars, sources, inBuild, binary, lcur, lres, execVars, workVars, sst, detached>>

LStep(u) == LRun(u) \/ LChk(u)

-----------------------------------------------------------------------------
Next == MainStep \/ AwTimeout \/ (\E w \in WIds : WStep(w)) \/ (\E u \in URIs : LStep(u))

Spec == Init /\ [][Next]_vars
        /\ WF_vars(MainStep)
        /\ \A w \in WIds : WF_vars(WStep(w))
        /\ \A u \in URIs : WF_vars(LStep(u))

-----------------------------------------------------------------------------
(* Properties                                                              *)

TypeOK ==
  /\ sources \subseteq URIs /\ inBuild \subseteq URIs
  /\ binary \in [URIs -> {"old", "none", "partial", "new"}]
  /\ sst \in [URIs -> {"no", "queued", "running", "ok", "failed", "dropped", "discarded"}]
  /\ gen \in Gens /\ k \in 1..NC

\* P1: a URI rebuilt in this build is "not in sources" (= binary-loadable) only once its store has run
\* to completion (binary "new", or deleted because of errors/failure) -- not queued, not dropped, not
\* skipped, not in the middle of serialising/writing.
Writing(u) ==
  \/ sst[u] = "queued"
  \/ \E w \in WIds : wtask[w][1] = u /\ wpc[w] \in {"ser", "write", "wend"}
  \/ cur = u /\ mpc \in {"crSer", "crWrite", "crWriteEnd"}
NotSourceOnlyWhenStored ==
  \A u \in inBuild : u \notin sources => (sst[u] \in {"running", "ok", "failed"} /\ ~Writing(u))

\* P2a: at the end of the build no store is silently lost or still queued.
StoresAccounted ==
  mpc = "done" => \A u \in URIs : sst[u] \notin {"queued", "discarded"}
\* P2b: every submitted store eventually completes or is reported as not processed.
StoreResolved ==
  \A u \in URIs : (sst[u] \in {"queued", "running"}) ~> (sst[u] \in {"ok", "failed", "dropped"})

\* P3: PRL jobs never open a binary that is still being written.
LoaderNoPartialRead == \A r \in reads : r[1] = "loader" => r[3] # "partial"
\* P3': same for the builder thread (linking).
MainNoPartialRead   == \A r \in reads : r[1] = "main" => r[3] # "partial"
\* P3'': nobody opens the previous build's binary of a resource that is rebuilt in this build.
NoStaleRead         == \A r \in reads : ~(r[3] = "old" /\ r[4])

\* P4: the (non-thread-safe) HashSet is never mutated concurrently with any other access.
NWriters  == (IF macc = "w" THEN 1 ELSE 0) + Cardinality({u \in URIs : lacc[u] = "w"}) + Cardinality({w \in WIds : wacc[w] = "w"})
NAccesses == (IF macc # "none" THEN 1 ELSE 0) + Cardinality({u \in URIs : lacc[u] # "none"}) + Cardinality({w \in WIds : wacc[w] # "none"})
SetThreadSafe == NWriters > 0 => NAccesses = 1

\* P4 refinements, to classify which overlaps exist.
NoReadDuringWrite    == NWriters > 0 => \A u \in URIs : lacc[u] = "none"          \* loader contains() vs a mutation
NoAddDuringRemove    == ~(mpc = "qa2" /\ \E w \in WIds : wacc[w] = "w")            \* main add (MCBS:1298) vs worker remove (MCBS:754)

\* P6: never serialise a resource that was removed from / cleared out of the builder's resource set.
NoDetachedStore == ~detached

\* P5: termination.
BuildTerminates == <>(mpc = "done")
WorkersQuiesce  == <>[](\A w \in WIds : wpc[w] \in {"none", "exit", "idle"})

\* Witnesses (should be VIOLATED: they show that the good/interesting paths are reachable).
WitnessDone        == mpc # "done"
WitnessSecondClust == k < 2
WitnessCallerRuns  == mpc # "crSer"
WitnessTimeout     == timeouts = 0
WitnessGoodRead    == \A r \in reads : r[3] # "new"
WitnessStoreOk     == \A u \in URIs : sst[u] # "ok"
=============================================================================
