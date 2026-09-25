import Pipeline.Model

namespace Pipeline

/-- The checked configurations (Report prints them; Results proves their verdict vectors). -/
def configs : List (String × Cfg) :=
  [("faithful (code as written), push budget 2, keep 2", Cfg.faithful),
   ("faithful, push budget 1, keep 1", { Cfg.faithful with maxPush := 1, keep := 1 }),
   ("fixed (fixes A-F), push budget 1, keep 2", { Cfg.fixed with maxPush := 1 }),
   ("fixed (fixes A-F), push budget 1, keep 1", { Cfg.fixed with maxPush := 1, keep := 1 }),
   ("planted bug (fixed + blind force push), push budget 0", { Cfg.planted with maxPush := 0 }),
   ("faithful + fix D only (per-line baseline), push budget 1", { Cfg.faithful with lineBaseline := true, maxPush := 1 }),
   ("faithful + fix C only (--merged tags), push budget 1", { Cfg.faithful with mergedTags := true, maxPush := 1 }),
   ("faithful + fixes C+D, push budget 1", { Cfg.faithful with mergedTags := true, lineBaseline := true, maxPush := 1 }),
   ("faithful + fix A only (tag last + resume), push budget 2", { Cfg.faithful with tagLast := true }),
   ("fixes A-D,F without E (single group), push budget 2", { Cfg.fixed with groups := .single, write := .cas }),
   ("fixes A-D,F without E (single group), push budget 3, keep 1", { Cfg.fixed with groups := .single, write := .cas, maxPush := 3, keep := 1 })]

end Pipeline
