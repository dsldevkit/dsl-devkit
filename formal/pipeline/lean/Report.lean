import Pipeline
open Pipeline

def showV (g : Graph) (v : Verdict) (isWitness : Bool) (verbose : Bool) : IO Unit := do
  let tag := if isWitness then (if v.ok then "REACHABLE" else "UNREACHABLE")
             else (if v.ok then "holds" else "VIOLATED")
  IO.println s!"  [{tag}] {v.name}"
  if verbose then
    if let some i := v.witness then
      if !v.ok || isWitness then
        let t := trace g i
        IO.println s!"      shortest trace ({t.length} steps):"
        for (l, k) in t.zipIdx do
          IO.println s!"        {k+1}. {l}"

def main (args : List String) : IO Unit := do
  let verbose := !args.contains "--quiet"
  let only := (args.find? (·.startsWith "--only=")).map (·.drop 7 |>.toString.toNat!)
  for ((nm, cfg), ci) in configs.zipIdx do
    if only.isSome && only != some ci then continue
    let t0 ← IO.monoMsNow
    let (g, r) := analyze cfg
    let t1 ← IO.monoMsNow
    IO.println s!"=== {nm}: {r.states} states, {r.edges} edges, depth {r.maxDepth}, {t1 - t0} ms"
    for v in r.safety do showV g v false verbose
    showV g r.stuck false verbose
    showV g r.releaseLive false verbose
    for v in r.witnesses do showV g v true (verbose && args.contains "--witness-traces")
    IO.println s!"  bits = {verdictBits r}"
