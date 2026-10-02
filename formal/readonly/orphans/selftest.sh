#!/usr/bin/env bash
# Self-test for check-test-reachability.sh on synthetic repos (written under $TMPDIR only).
# Case 1 must flag exactly C, AbstractOrphan, D (exit 1); case 2 selects them and must exit 0.
set -euo pipefail
here="$(cd "$(dirname "$0")" && pwd)"
chk="$here/check-test-reachability.sh"
t="$(mktemp -d "${TMPDIR:-/tmp}/reach-selftest.XXXXXX")"
trap 'rm -rf "$t"' EXIT
fail=0

mk() { mkdir -p "$(dirname "$1")"; cat > "$1"; }

build() {  # $1 = repo dir, $2 = extra selections for the root suite
  local r="$1" extra="$2"
  mk "$r/a.test/src/p/AllTests.java" <<EOF
package p;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import q.SubSuite;
/** Mentions @Test in Javadoc only. */
@Suite
@SelectClasses({
  SubSuite.class, // @Test in a comment
  ConcreteA.class,
  G.class$extra
})
public class AllTests {}
EOF
  mk "$r/b.test/src/q/SubSuite.java" <<'EOF'
package q;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import r.B;
@Suite
@SelectClasses(
  { B.class, Sub.class }
)
public class SubSuite {}
EOF
  mk "$r/a.test/src/p/ConcreteA.java" <<'EOF'
package p;
import org.junit.jupiter.api.Test;
public class ConcreteA { @Test void t() {} }
EOF
  mk "$r/b.test/src/r/B.java" <<'EOF'
package r;
import org.junit.jupiter.params.ParameterizedTest;
public class B {
  @ParameterizedTest
  void t(int x) {}
}
EOF
  mk "$r/lib/src/base/AbstractBase.java" <<'EOF'
package base;
import org.junit.jupiter.api.Test;
public abstract class AbstractBase<T> {
  @Test
  void inherited() {}
}
EOF
  mk "$r/b.test/src/q/Sub.java" <<'EOF'
package q;
import base.AbstractBase;
public class Sub
    extends AbstractBase<String> {
}
EOF
  mk "$r/a.test/src/p/C.java" <<'EOF'
package p;
import org.junit.jupiter.api.Test;
public class C {
  @Test
  void orphan() {}
}
EOF
  mk "$r/a.test/src/p/AbstractOrphan.java" <<'EOF'
package p;
import org.junit.jupiter.api.Test;
public abstract class AbstractOrphan { @Test void t() {} }
EOF
  mk "$r/lib/src/ann/MyBug.java" <<'EOF'
package ann;
import org.junit.jupiter.api.Test;
@Test
public @interface MyBug { String value(); }
EOF
  mk "$r/a.test/src/p/D.java" <<'EOF'
package p;
import ann.MyBug;
public class D {
  @MyBug("x")
  void viaMetaAnnotation() {}
}
EOF
  mk "$r/a.test/src/p/E.java" <<'EOF'
package p;
public class E {
  // @Test
  /* @Test */
  void notATest() {}
}
EOF
  mk "$r/a.test/src/p/G.java" <<'EOF'
package p;
public class G {}
EOF
}

# Case 1: orphans present
build "$t/r1" ""
set +e; out="$("$chk" "$t/r1" p.AllTests)"; rc=$?; set -e
got="$(printf '%s\n' "$out" | awk -F'\t' '$1=="  UNREACHABLE"{print $3}' | sort | tr '\n' ' ')"
want="p.AbstractOrphan p.C p.D "
[ "$rc" = 1 ] || { echo "FAIL case1 rc=$rc"; fail=1; }
[ "$got" = "$want" ] || { echo "FAIL case1 got [$got] want [$want]"; echo "$out"; fail=1; }
printf '%s\n' "$out" | rg -q 'selected-but-empty.*p\.G ' || { echo "FAIL case1: G not reported empty"; fail=1; }
printf '%s\n' "$out" | rg -q 'executed \(reachable\): 3$' || { echo "FAIL case1: executed != 3 (ConcreteA,B,Sub)"; echo "$out"; fail=1; }

# Case 2: orphans selected; abstract orphan covered through a selected concrete subclass
build "$t/r2" ", C.class, D.class, AbstractOrphanImpl.class"
mk "$t/r2/a.test/src/p/AbstractOrphanImpl.java" <<'EOF'
package p;
public class AbstractOrphanImpl extends AbstractOrphan {}
EOF
set +e; out2="$("$chk" "$t/r2" p.AllTests)"; rc2=$?; set -e
[ "$rc2" = 0 ] || { echo "FAIL case2 rc=$rc2"; echo "$out2"; fail=1; }

# Case 3: unknown root suite is a usage error, not a pass
set +e; "$chk" "$t/r1" p.Nope >/dev/null 2>&1; rc3=$?; set -e
[ "$rc3" = 2 ] || { echo "FAIL case3 rc=$rc3"; fail=1; }

[ "$fail" = 0 ] && echo "selftest: OK (3 cases)"
exit "$fail"
