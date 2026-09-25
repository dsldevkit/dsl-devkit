import FindRefs
open FindRefs

def cfg : Cfg := {}
#eval report "buggy (as written)" (Buggy.next .none cfg) (St.init cfg)
#eval report "patch: lost" (Buggy.next {lost := true} cfg) (St.init cfg)
#eval report "patch: lost+order+snap" (Buggy.next {lost := true, order := true, snap := true} cfg) (St.init cfg)
#eval report "patch: lost+order+snap+async" (Buggy.next {lost := true, order := true, snap := true, async := true} cfg) (St.init cfg)
#eval report "fixed" (Fixed.next false cfg) (St.init cfg)
#eval report "fixed + planted bug" (Fixed.next true cfg) (St.init cfg)
#eval report "fixed, larger bound nU=3 adds=4 resets=2 switches=3" (Fixed.next false {nU := 3, adds := 4, resets := 2, switches := 3}) (St.init {nU := 3, adds := 4, resets := 2, switches := 3})
