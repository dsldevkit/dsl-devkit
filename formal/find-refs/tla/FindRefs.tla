------------------------------ MODULE FindRefs ------------------------------
(***************************************************************************)
(* Model of FastReferenceSearchResultContentProvider (FRSRCP) together    *)
(* with the Xtext ReferenceSearchResult R it listens to, the search job   *)
(* thread, the SWT UI thread (UIJob + syncExec/asyncExec runnables + user *)
(* view switches) and the JFace TreeViewer's root items.                  *)
(*                                                                         *)
(* Line refs: FRSRCP = FastReferenceSearchResultContentProvider.java,      *)
(*            RSR    = xtext ReferenceSearchResult.java,                   *)
(*            RSVP   = xtext ReferenceSearchViewPage.java.                 *)
(*                                                                         *)
(* Fixes = {} is the code as written; each element enables one minimal   *)
(* fix (see NOTES.md). Planted = TRUE plants an obvious bug on top.      *)
(*                                                                         *)
(***************************************************************************)
EXTENDS Naturals, Sequences, FiniteSets, TLC

CONSTANTS Refs,       \* resource URI of each reference the search delivers, in order
          MaxRuns,    \* number of search runs (>1 = "Search Again", i.e. Reset on a live provider)
          MaxSwitch,  \* number of user view switches R->B / B->R (B = another, finished search)
          Fixes,      \* subset of AllFixes
          Planted     \* planted bug: the Added handler never schedules the UIUpdater

AllFixes == {"flag", "atomic", "dedupe", "snapshot", "reset", "plock", "detach"}
ASSUME Fixes \subseteq AllFixes /\ Planted \in BOOLEAN

Fix(x) == x \in Fixes

\* Defaults for FindRefs.cfg / FindRefsFixed.cfg (cfg files cannot hold sequences)
DefaultRefs == <<"u1", "u2">>
NoFixes     == {}
MinFixes    == {"flag", "dedupe", "snapshot", "reset", "plock", "detach"}
URIs  == {Refs[k] : k \in DOMAIN Refs}
Nil   == 0
RefUri(ref) == Refs[ref[2]]            \* a reference is <<epoch, index>>
Range(f) == {f[x] : x \in DOMAIN f}

VARIABLES
  \* --- ReferenceSearchResult R (Xtext) ---
  matching,     \* R.matchingReferences (ArrayList, unsynchronized)
  matchMod,     \* its modCount
  provReg,      \* provider \in R.listeners
  lockL,        \* monitor of R.listeners: "none" | "S" | "U"
  lockP,        \* (fix "plock") provider-private monitor: "none" | "S" | "U"
  \* --- provider state ---
  rootNodes,    \* URI -> node id (ConcurrentMap), Nil = absent
  batch,        \* batchAddNodes
  flag,         \* isUIUpdateScheduled
  nodes,        \* heap of ReferenceSearchViewTreeNode: [uri, ep, kids, gen]
  clears,       \* number of rootNodes.clear() so far (node.gen = clears at creation)
  \* --- Display / Jobs ---
  jobs,         \* number of pending UIUpdater executions
  syncPending,  \* Reset runnable posted by syncExec, not yet run
  asyncQ,       \* (fixed) posted asyncExec refresh runnables (epochs)
  \* --- viewer ---
  shown,        \* node ids that are root items of the TreeViewer
  view,         \* result the page shows: "R" | "B"
  provEpoch,    \* epoch of the last Reset / inputChanged the viewer has processed
  \* --- search thread ---
  pcS, iS, epoch, runs, sNode,
  \* --- UI thread ---
  pcU, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, cme, switches

vars == <<matching, matchMod, provReg, lockL, lockP, rootNodes, batch, flag, nodes, clears,
          jobs, syncPending, asyncQ, shown, view, provEpoch,
          pcS, iS, epoch, runs, sNode,
          pcU, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, cme, switches>>

EmptyMap == [u \in URIs |-> Nil]
RootSet(rn) == Range(rn) \ {Nil}
NewNode(u) == [uri |-> u, ep |-> epoch, kids |-> <<>>, gen |-> clears]
HasKid(n, ref) == \E j \in DOMAIN nodes[n].kids : nodes[n].kids[j] = ref
AddKid(ns, n, ref) ==   \* new DynamicReferenceSearchViewTreeNode(parent, ...) -> parent.addChild
  IF Fix("dedupe") /\ \E j \in DOMAIN ns[n].kids : ns[n].kids[j] = ref THEN ns  \* fix: dedupe
  ELSE [ns EXCEPT ![n].kids = Append(@, ref)]

Init ==
  /\ matching = <<>> /\ matchMod = 0
  /\ provReg = TRUE            \* page showed R (setInput) before the job was scheduled
  /\ lockL = "none" /\ lockP = "none"
  /\ rootNodes = EmptyMap /\ batch = <<>> /\ flag = FALSE /\ nodes = <<>> /\ clears = 0
  /\ jobs = 0 /\ syncPending = FALSE /\ asyncQ = <<>>
  /\ shown = {} /\ view = "R" /\ provEpoch = 0
  /\ pcS = "sched" /\ iS = 0 /\ epoch = 0 /\ runs = 1 /\ sNode = Nil
  /\ pcU = "idle" /\ uNonEmpty = FALSE /\ uCur = 0 /\ uExp = 0 /\ uSnap = <<>>
  /\ uRef = <<0, 0>> /\ uNode = Nil /\ cme = FALSE /\ switches = 0

UVars == <<pcU, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, lockP, clears>>
UVarsNoC == <<pcU, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, lockP>>
UVarsNoP == <<pcU, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, clears>>
SVars == <<pcS, iS, epoch, runs, sNode>>

(***************************************************************************)
(* Search job thread (InternalSearchJob -> ReferenceQuery.run).            *)
(***************************************************************************)
SJobStart ==  \* job picked up by a worker
  /\ pcS = "sched" /\ pcS' = "reset"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes,
                 jobs, syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode,
                 UVars, cme>>

SReset ==     \* RSR:104 matchingReferences.clear()
  /\ pcS = "reset"
  /\ matching' = <<>> /\ matchMod' = matchMod + 1 /\ epoch' = epoch + 1
  /\ pcS' = "rlock"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, iS, runs, sNode, UVars, cme>>

SResetLock == \* RSR:54 synchronized(listeners) -> FRSRCP:177 Reset handler
  /\ pcS = "rlock" /\ lockL = "none" /\ lockL' = "S"
  /\ IF provReg /\ ~Fix("reset")
       THEN /\ syncPending' = TRUE /\ pcS' = "rwait"   \* FRSRCP:178 syncExec (blocks)
            /\ UNCHANGED <<rootNodes, batch, asyncQ>>
     ELSE IF provReg /\ Fix("reset")
       THEN \* fix: clear model on the search thread, asyncExec only the viewer refresh
            /\ pcS' = "rclear" /\ UNCHANGED <<rootNodes, batch, asyncQ, syncPending>>
     ELSE /\ pcS' = "runlock" /\ UNCHANGED <<rootNodes, batch, asyncQ, syncPending>>
  /\ UNCHANGED <<matching, matchMod, provReg, flag, nodes, jobs, shown, view, provEpoch,
                 iS, epoch, runs, sNode, UVars, cme>>

SRClear ==    \* fix "reset": clear model on the search thread (under the provider lock if "plock"),
              \* and asyncExec only the viewer refresh
  /\ pcS = "rclear" /\ (Fix("plock") => lockP = "none")
  /\ rootNodes' = EmptyMap /\ clears' = clears + 1 /\ batch' = <<>> /\ asyncQ' = Append(asyncQ, epoch) /\ pcS' = "runlock"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, flag, nodes, jobs, syncPending, shown, view,
                 provEpoch, iS, epoch, runs, sNode, UVarsNoC, cme>>

SResetWait == \* syncExec returns once the UI thread ran the runnable
  /\ pcS = "rwait" /\ ~syncPending /\ pcS' = "runlock"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SResetUnlock ==
  /\ pcS = "runlock" /\ lockL' = "none"
  /\ iS' = 1 /\ pcS' = IF Len(Refs) >= 1 THEN "acc" ELSE "fin"
  /\ UNCHANGED <<matching, matchMod, provReg, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, epoch, runs, sNode, UVars, cme>>

SAccept ==    \* RSR:85 matchingReferences.add (outside the listeners lock)
  /\ pcS = "acc"
  /\ matching' = Append(matching, <<epoch, iS>>) /\ matchMod' = matchMod + 1
  /\ pcS' = "lock"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SLock ==      \* RSR:54-56 fireEvent(Added): lock, deliver to provider if registered
  /\ pcS = "lock" /\ lockL = "none" /\ lockL' = "S"
  /\ pcS' = IF provReg THEN (IF Fix("plock") THEN "plk" ELSE "get") ELSE "unlock"
  /\ UNCHANGED <<matching, matchMod, provReg, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SPLock ==     \* fix "plock": synchronized (providerLock) around the Added handler body
  /\ pcS = "plk" /\ lockP = "none" /\ lockP' = "S" /\ pcS' = "get"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVarsNoP, cme>>

SGet ==       \* FRSRCP:158-160 rootNodes.get; if null new node   (fix "atomic": computeIfAbsent; subsumed by "plock")
  /\ pcS = "get"
  /\ LET u == Refs[iS] IN
     IF rootNodes[u] # Nil
       THEN /\ sNode' = rootNodes[u] /\ pcS' = "child"
            /\ UNCHANGED <<nodes, rootNodes, batch>>
       ELSE /\ nodes' = Append(nodes, NewNode(u)) /\ sNode' = Len(nodes) + 1
            /\ IF Fix("atomic")
                 THEN /\ rootNodes' = [rootNodes EXCEPT ![u] = Len(nodes) + 1]
                      /\ batch' = Append(batch, Len(nodes) + 1) /\ pcS' = "child"
                 ELSE /\ pcS' = "put" /\ UNCHANGED <<rootNodes, batch>>
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, flag, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, iS, epoch, runs, UVars, cme>>

SPut ==       \* FRSRCP:161 rootNodes.put
  /\ pcS = "put" /\ rootNodes' = [rootNodes EXCEPT ![Refs[iS]] = sNode] /\ pcS' = "batch"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, batch, flag, nodes, jobs, syncPending,
                 asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SBatch ==     \* FRSRCP:162-164 batchAddNodes.add (under its own lock -> atomic)
  /\ pcS = "batch" /\ batch' = Append(batch, sNode) /\ pcS' = "child"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SChild ==     \* FRSRCP:137 new DynamicReferenceSearchViewTreeNode(resourceNode, ...) -> addChild
  /\ pcS = "child" /\ nodes' = AddKid(nodes, sNode, <<epoch, iS>>) /\ pcS' = "flag"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SFlag ==      \* FRSRCP:173 if (!isUIUpdateScheduled)
  /\ pcS = "flag"
  /\ pcS' = IF flag \/ Planted THEN "unlock" ELSE "setflag"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SSetFlag ==   \* FRSRCP:174
  /\ pcS = "setflag" /\ flag' = TRUE /\ pcS' = "sched2"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SSchedule ==  \* FRSRCP:175 new UIUpdater().schedule()
  /\ pcS = "sched2" /\ jobs' = jobs + 1 /\ pcS' = "unlock"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SUnlock ==    \* RSR:57 end of fireEvent
  /\ pcS = "unlock" /\ lockL' = "none" /\ lockP' = IF lockP = "S" THEN "none" ELSE lockP
  /\ IF iS < Len(Refs) THEN iS' = iS + 1 /\ pcS' = "acc" ELSE pcS' = "fin" /\ UNCHANGED iS
  /\ UNCHANGED <<matching, matchMod, provReg, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, epoch, runs, sNode, UVarsNoP, cme>>

SFinish ==    \* RSR:109 fireEvent(Finish): provider ignores it; lock taken and released
  /\ pcS = "fin" /\ lockL = "none" /\ pcS' = "done"
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, runs, sNode, UVars, cme>>

SearchStep == SJobStart \/ SReset \/ SResetLock \/ SResetWait \/ SResetUnlock \/ SAccept
              \/ SLock \/ SGet \/ SPut \/ SBatch \/ SChild \/ SFlag \/ SSetFlag \/ SSchedule \/ SUnlock \/ SFinish \/ SRClear \/ SPLock

(***************************************************************************)
(* UI thread. Only one runnable / event handler runs at a time; picks any  *)
(* pending one when idle.                                                  *)
(***************************************************************************)
Unchanged_S_R == UNCHANGED <<SVars, matching, matchMod, lockP, clears>>

UIRunSyncReset ==   \* FRSRCP:180-186 on the UI thread
  /\ pcU = "idle" /\ syncPending
  /\ shown' = {}    \* remove(input, rootNodes.values); rootNodes.clear; refresh -> getElements = {}
  /\ rootNodes' = EmptyMap /\ clears' = clears + 1 /\ syncPending' = FALSE /\ provEpoch' = epoch
  /\ UNCHANGED <<provReg, lockL, batch, flag, nodes, jobs, asyncQ, view, UVarsNoC, cme>>
  /\ UNCHANGED <<SVars, matching, matchMod, lockP>>

UIRunAsyncReset ==  \* (fixed only) asyncExec'd viewer.refresh after Reset
  /\ pcU = "idle" /\ asyncQ # <<>>
  /\ shown' = RootSet(rootNodes) /\ provEpoch' = Head(asyncQ) /\ asyncQ' = Tail(asyncQ)
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, view, UVars, cme>>
  /\ Unchanged_S_R

UIJobStart ==       \* UIJob -> Display.asyncExec(runInUIThread)
  /\ pcU = "idle" /\ jobs > 0 /\ jobs' = jobs - 1
  /\ pcU' = IF Fix("flag") THEN "u_clr" ELSE "u_drain"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, syncPending, asyncQ,
                 shown, view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UClear ==           \* (fixed) isUIUpdateScheduled = false first, as upstream Xtext does
  /\ pcU = "u_clr" /\ flag' = FALSE /\ pcU' = "u_drain"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UDrain ==           \* FRSRCP:207-215 copy+clear batch under lock, viewer.add each
  /\ pcU = "u_drain"
  /\ shown' = shown \cup Range(batch) /\ batch' = <<>> /\ pcU' = "u_refresh"
  /\ UNCHANGED <<provReg, lockL, rootNodes, flag, nodes, jobs, syncPending, asyncQ, view,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URefresh ==         \* FRSRCP:216 viewer.refresh() -> getElements() = rootNodes.values()
  /\ pcU = "u_refresh"
  /\ shown' = RootSet(rootNodes) /\ pcU' = IF Fix("flag") THEN "idle" ELSE "u_check"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UCheck ==           \* FRSRCP:217 !batchAddNodes.isEmpty() (unsynchronized read)
  /\ pcU = "u_check" /\ uNonEmpty' = (batch # <<>>) /\ pcU' = "u_end"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UEnd ==             \* FRSRCP:218 schedule(250) / 220 isUIUpdateScheduled = false
  /\ pcU = "u_end"
  /\ IF uNonEmpty THEN jobs' = jobs + 1 /\ UNCHANGED flag
                  ELSE flag' = FALSE /\ UNCHANGED jobs
  /\ pcU' = "idle"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, nodes, syncPending, asyncQ, shown, view,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

\* ---- user: "Search Again" on R (SearchAgainAction -> runQueryInBackground; addQuery is a no-op) ----
UserRerun ==
  /\ pcU = "idle" /\ view = "R" /\ pcS = "done" /\ runs < MaxRuns
  /\ pcS' = "sched" /\ runs' = runs + 1
  /\ UNCHANGED <<matching, matchMod, provReg, lockL, rootNodes, batch, flag, nodes, jobs,
                 syncPending, asyncQ, shown, view, provEpoch, iS, epoch, sNode, UVars, cme>>

\* ---- user: switch the view to another (finished) search B, e.g. from history ----
\* SearchView.internalShowSearchPage: page.setInput(null) ; page.setInput(B)
UserToB ==
  /\ pcU = "idle" /\ view = "R" /\ switches < MaxSwitch
  /\ pcU' = "b_disp" /\ switches' = switches + 1
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, cme>>
  /\ Unchanged_S_R

UBDisp ==           \* event dispatch / getUIState before touching R
  /\ pcU = "b_disp" /\ pcU' = "b_lbl"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UBLabel ==          \* RSVP:139 R.removeListener(labelUpdater): needs R.listeners monitor
  /\ pcU = "b_lbl" /\ lockL = "none"
  /\ pcU' = IF Fix("detach") THEN "b_rm" ELSE "b_clear"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UBClear ==          \* FRSRCP:111 rootNodes.clear()
  /\ pcU = "b_clear" /\ (Fix("plock") => lockP = "none") /\ rootNodes' = EmptyMap /\ clears' = clears + 1
  /\ pcU' = IF Fix("detach") THEN "b_ref" ELSE "b_rm"
  /\ UNCHANGED <<provReg, lockL, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ UNCHANGED <<SVars, matching, matchMod, lockP>>

UBRemove ==         \* FRSRCP:113 R.removeListener(this); fix: done before the clear
  /\ pcU = "b_rm" /\ lockL = "none" /\ provReg' = FALSE
  /\ pcU' = IF Fix("detach") THEN "b_clear" ELSE "b_ref"
  /\ UNCHANGED <<lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UBRefresh ==        \* B has no provider-side refs; viewer.setInput(B) ends with refresh()
  /\ pcU = "b_ref" /\ shown' = RootSet(rootNodes) /\ view' = "B" /\ pcU' = "idle"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

\* ---- user: switch back to R (possibly still running) ----
UserToR ==
  /\ pcU = "idle" /\ view = "B" /\ switches < MaxSwitch
  /\ pcU' = "r_disp" /\ switches' = switches + 1
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 shown, view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, cme>>
  /\ Unchanged_S_R

URDisp ==
  /\ pcU = "r_disp" /\ pcU' = "r_lbl"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URLabel ==          \* RSVP:144 R.addListener(labelUpdater)
  /\ pcU = "r_lbl" /\ lockL = "none" /\ pcU' = IF Fix("plock") THEN "r_add" ELSE "r_clear"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URClear ==          \* FRSRCP:111 (fix "plock": done after addListener, under the provider lock,
                    \* together with the snapshot of the matches)
  /\ pcU = "r_clear" /\ rootNodes' = EmptyMap /\ clears' = clears + 1 /\ provEpoch' = epoch
  /\ pcU' = IF Fix("plock") THEN "r_iter" ELSE "r_add"
  /\ uSnap' = IF Fix("plock") /\ Fix("snapshot") THEN matching ELSE uSnap
  /\ UNCHANGED <<provReg, lockL, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, uNonEmpty, uCur, uExp, uRef, uNode, switches, cme>>
  /\ UNCHANGED <<SVars, matching, matchMod, lockP>>

URAdd ==            \* FRSRCP:116 R.addListener(this)   (fix: snapshot matching under the same lock)
  /\ pcU = "r_add" /\ lockL = "none" /\ provReg' = TRUE
  /\ uSnap' = IF Fix("snapshot") /\ ~Fix("plock") THEN matching ELSE <<>>
  /\ pcU' = IF Fix("plock") THEN "r_plk" ELSE "r_iter"
  /\ UNCHANGED <<lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URPLock ==          \* fix "plock": synchronized (providerLock) { clear; snapshot; addReference* }
  /\ pcU = "r_plk" /\ lockP = "none" /\ lockP' = "U" /\ pcU' = "r_clear"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ UNCHANGED <<SVars, matching, matchMod, clears>>

URIter ==           \* FRSRCP:118 getMatchingReferences().iterator()
  /\ pcU = "r_iter" /\ uCur' = 0 /\ uExp' = matchMod /\ pcU' = "r_next"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URNext ==           \* ArrayList.Itr.hasNext (cursor != size) / next (modCount check)
  /\ pcU = "r_next"
  /\ LET src == IF Fix("snapshot") THEN uSnap ELSE matching IN
     IF uCur = Len(src)
       THEN pcU' = "r_ref" /\ UNCHANGED <<uCur, uRef, cme>>
     ELSE IF ~Fix("snapshot") /\ matchMod # uExp
       THEN cme' = TRUE /\ pcU' = "idle" /\ UNCHANGED <<uCur, uRef>>   \* CME escapes inputChanged
     ELSE uRef' = src[uCur + 1] /\ uCur' = uCur + 1 /\ pcU' = "r_get" /\ UNCHANGED cme
  /\ lockP' = IF pcU' # "r_get" /\ lockP = "U" THEN "none" ELSE lockP
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ, shown,
                 view, provEpoch, uNonEmpty, uExp, uSnap, uNode, switches>>
  /\ UNCHANGED <<SVars, matching, matchMod, clears>>

URGet ==            \* addReference -> resourceNode: FRSRCP:158-160 (fix "atomic")
  /\ pcU = "r_get"
  /\ LET u == RefUri(uRef) IN
     IF rootNodes[u] # Nil
       THEN uNode' = rootNodes[u] /\ pcU' = "r_child" /\ UNCHANGED <<nodes, rootNodes, batch>>
       ELSE /\ nodes' = Append(nodes, [uri |-> u, ep |-> uRef[1], kids |-> <<>>, gen |-> clears])
            /\ uNode' = Len(nodes) + 1
            /\ IF Fix("atomic")
                 THEN /\ rootNodes' = [rootNodes EXCEPT ![u] = Len(nodes) + 1]
                      /\ batch' = Append(batch, Len(nodes) + 1) /\ pcU' = "r_child"
                 ELSE pcU' = "r_put" /\ UNCHANGED <<rootNodes, batch>>
  /\ UNCHANGED <<provReg, lockL, flag, jobs, syncPending, asyncQ, shown, view, provEpoch,
                 uNonEmpty, uCur, uExp, uSnap, uRef, switches, cme>>
  /\ Unchanged_S_R

URPut ==            \* FRSRCP:161
  /\ pcU = "r_put" /\ rootNodes' = [rootNodes EXCEPT ![RefUri(uRef)] = uNode] /\ pcU' = "r_batch"
  /\ UNCHANGED <<provReg, lockL, batch, flag, nodes, jobs, syncPending, asyncQ, shown, view,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URBatch ==          \* FRSRCP:162-164
  /\ pcU = "r_batch" /\ batch' = Append(batch, uNode) /\ pcU' = "r_child"
  /\ UNCHANGED <<provReg, lockL, rootNodes, flag, nodes, jobs, syncPending, asyncQ, shown, view,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URChild ==          \* FRSRCP:137
  /\ pcU = "r_child" /\ nodes' = AddKid(nodes, uNode, uRef) /\ pcU' = "r_next"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, jobs, syncPending, asyncQ, shown, view,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

URRefresh ==        \* viewer.setInput(R) -> refresh()
  /\ pcU = "r_ref" /\ shown' = RootSet(rootNodes) /\ view' = "R" /\ pcU' = "idle"
  /\ UNCHANGED <<provReg, lockL, rootNodes, batch, flag, nodes, jobs, syncPending, asyncQ,
                 provEpoch, uNonEmpty, uCur, uExp, uSnap, uRef, uNode, switches, cme>>
  /\ Unchanged_S_R

UIStep == UIRunSyncReset \/ UIRunAsyncReset \/ UIJobStart \/ UClear \/ UDrain \/ URefresh
          \/ UCheck \/ UEnd \/ UserRerun
          \/ UserToB \/ UBDisp \/ UBLabel \/ UBClear \/ UBRemove \/ UBRefresh
          \/ UserToR \/ URDisp \/ URLabel \/ URClear \/ URAdd \/ URPLock \/ URIter \/ URNext
          \/ URGet \/ URPut \/ URBatch \/ URChild \/ URRefresh

Quiescent == pcS = "done" /\ pcU = "idle" /\ jobs = 0 /\ ~syncPending /\ asyncQ = <<>>

Terminated == Quiescent /\ UNCHANGED vars   \* legitimate end; anything else stuck is a deadlock

Next == SearchStep \/ UIStep \/ Terminated

Spec == Init /\ [][Next]_vars

(***************************************************************************)
(* Properties                                                              *)
(***************************************************************************)
TypeOK ==
  /\ lockL \in {"none", "S", "U"} /\ lockP \in {"none", "S", "U"} /\ view \in {"R", "B"} /\ flag \in BOOLEAN
  /\ \A u \in URIs : rootNodes[u] \in 0..Len(nodes)
  /\ shown \subseteq 1..Len(nodes)

Settled == Quiescent /\ view = "R" /\ ~cme

RefShown(ref) == \E n \in shown : HasKid(n, ref)

\* P1 no lost update: once quiet, every accepted reference (and its resource root) is visible
NoLostRoot   == Settled => \A k \in DOMAIN matching : \E n \in shown : nodes[n].uri = RefUri(matching[k])
NoLostRef    == Settled => \A k \in DOMAIN matching : RefShown(matching[k])

\* P2 one root per URI (viewer, whenever the UI thread is between events)
OneRootPerUri == pcU = "idle" => \A u \in URIs : Cardinality({n \in shown : nodes[n].uri = u}) <= 1
\* P2 at provider level: since the last rootNodes.clear() at most one root node was created per URI
OneRootCreated == \A u \in URIs :
  Cardinality({n \in 1..Len(nodes) : nodes[n].uri = u /\ nodes[n].gen = clears}) <= 1
\* P2' each reference appears once
NoDupRef == Settled =>
  \A n1, n2 \in shown : \A j1 \in DOMAIN nodes[n1].kids : \A j2 \in DOMAIN nodes[n2].kids :
     (nodes[n1].kids[j1] = nodes[n2].kids[j2]) => (n1 = n2 /\ j1 = j2)

\* P3 after a Reset (or re-input) no node from before it is shown, and R's nodes never leak into B
NoStale   == pcU = "idle" => \A n \in shown : nodes[n].ep >= provEpoch
NoForeign == (pcU = "idle" /\ view = "B") => shown = {}

\* Extra: no ConcurrentModificationException in inputChanged, no UI/search deadlock
NoCME == ~cme
NoDeadlock == ~(pcS = "rwait" /\ syncPending /\ lockL = "S" /\ pcU \in {"b_lbl", "b_rm", "r_lbl", "r_add"})

(***************************************************************************)
(* Witnesses (expected to be VIOLATED: they prove good paths are reachable) *)
(***************************************************************************)
W_AllShown     == ~(Settled /\ Len(matching) = Len(Refs) /\ \A k \in DOMAIN matching : RefShown(matching[k]))
W_RerunShown   == ~(Settled /\ runs = 2 /\ Len(matching) = Len(Refs) /\ \A k \in DOMAIN matching : RefShown(matching[k]))
W_SwitchBack   == ~(Settled /\ switches = 2 /\ Len(matching) = Len(Refs) /\ \A k \in DOMAIN matching : RefShown(matching[k]))
W_Rescheduled  == ~(pcU = "u_end" /\ uNonEmpty)
W_ResetRan     == ~(provEpoch = 2 /\ pcU = "idle")
=============================================================================
