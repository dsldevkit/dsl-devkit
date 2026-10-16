#!/usr/bin/env bash
# Read-only check: every JUnit test class must be reachable from the aggregator suite.
#
# Usage: check-test-reachability.sh [REPO_ROOT] [ROOT_SUITE_FQN]
#   REPO_ROOT       defaults to the git toplevel of this script's directory
#   ROOT_SUITE_FQN  defaults to com.avaloq.tools.ddk.xtext.AllTests (ddk-parent/pom.xml test.testClass)
#
# Model (mirrors what JUnit Platform Suite + Jupiter actually do):
#   * Start at ROOT_SUITE_FQN and follow @SelectClasses transitively (nested @Suite classes are run).
#   * A selected concrete class runs its own and inherited test methods; a selected abstract class runs nothing.
#   * A class is "test-bearing" if it or any superclass declares a method annotated with
#     @Test/@ParameterizedTest/@RepeatedTest/@TestFactory/@TestTemplate or a meta-annotation of these
#     that is declared in the repo (e.g. @BugTest).
#   * UNREACHABLE = concrete test-bearing class not selected, or abstract test-bearing class with
#     no selected concrete subclass.
# Exit: 0 when nothing is unreachable, 1 otherwise, 2 on usage/environment errors.
# Needs only bash (3.2 is fine), rg, awk, sort. No network, no writes outside $TMPDIR.
set -euo pipefail

here="$(cd "$(dirname "$0")" && pwd)"
root="${1:-$(git -C "$here" rev-parse --show-toplevel 2>/dev/null || true)}"
suite="${2:-com.avaloq.tools.ddk.xtext.AllTests}"
[ -n "$root" ] && [ -d "$root" ] || { echo "usage: $0 [REPO_ROOT] [ROOT_SUITE_FQN]" >&2; exit 2; }
command -v rg >/dev/null || { echo "rg not found" >&2; exit 2; }

work="$(mktemp -d "${TMPDIR:-/tmp}/reach.XXXXXX")"
trap 'rm -rf "$work"' EXIT

# Java sources in bundle source folders; skip build output, generated trees and this spike folder.
( cd "$root" && rg --files -t java \
    -g '!**/target/**' -g '!**/bin/**' -g '!formal/**' -g '!**/src-gen/**' -g '!**/xtend-gen/**' -g '!**/src-model/**' ) \
  | sort > "$work/files"
[ -s "$work/files" ] || { echo "no Java sources under $root" >&2; exit 2; }

# Pass 1: per-file facts.
#   A <fqn> <simple>                     annotation type declared with a test meta-annotation (direct)
#   M <fqn> <simple> <annSimpleName>     annotation type meta-annotated with <ann> (resolved later)
#   C <fqn> <path> <abstract> <kind> <superToken> <isSuite>
#   U <fqn> <annSimpleName>              annotation used on a member/type line (candidate test marker)
#   I <fqn> <importFqn>
#   S <fqn> <selectedToken>
( cd "$root" && xargs awk '
function flush() {
  if (fqn == "") return
  printf "C\t%s\t%s\t%d\t%s\t%s\t%d\n", fqn, path, isAbs, kind, sup, isSuite
}
function ident_after(s, kw,   i, t) {
  i = index(s, kw); if (i == 0) return ""
  t = substr(s, i + length(kw)); sub(/^[ \t]+/, "", t)
  sub(/[^A-Za-z0-9_.].*$/, "", t); return t
}
FNR == 1 {
  flush()
  path = FILENAME; simple = FILENAME; sub(/^.*\//, "", simple); sub(/\.java$/, "", simple)
  pkg = ""; fqn = ""; isAbs = 0; kind = ""; sup = ""; isSuite = 0
  inBlock = 0; inSel = 0; depth = 0; inDecl = 0; decl = ""; pendingAnn = ""
}
{
  line = $0
  # strip block comments (line granularity is enough for annotations/imports)
  if (inBlock) { if (line ~ /\*\//) { sub(/^.*\*\//, "", line); inBlock = 0 } else next }
  while (line ~ /\/\*/) {
    if (line ~ /\/\*.*\*\//) { sub(/\/\*[^*]*\*+([^\/*][^*]*\*+)*\//, "", line) }
    else { sub(/\/\*.*$/, "", line); inBlock = 1 }
  }
  sub(/\/\/.*$/, "", line)
  if (line ~ /^[ \t]*$/) next

  if (pkg == "" && line ~ /^[ \t]*package[ \t]/) {
    pkg = line; sub(/^[ \t]*package[ \t]+/, "", pkg); sub(/[ \t]*;.*$/, "", pkg)
    fqn = pkg "." simple
  }
  if (fqn == "") fqn = simple
  if (line ~ /^[ \t]*import[ \t]+[A-Za-z]/ && line !~ /import[ \t]+static/) {
    imp = line; sub(/^[ \t]*import[ \t]+/, "", imp); sub(/[ \t]*;.*$/, "", imp)
    printf "I\t%s\t%s\n", fqn, imp
  }
  if (line ~ /(^|[^A-Za-z0-9_])@Suite([^A-Za-z0-9_.]|$)/ || line ~ /@org\.junit\.platform\.suite\.api\.Suite([^A-Za-z0-9_]|$)/) isSuite = 1

  # @SelectClasses({...}) or @SelectClasses(X.class), possibly multi-line
  if (!inSel && line ~ /@SelectClasses[ \t]*\(/) { inSel = 1; depth = 0; line = substr(line, index(line, "@SelectClasses")) }
  if (inSel) {
    rest = line
    while (match(rest, /[A-Za-z_][A-Za-z0-9_.]*[ \t]*\.[ \t]*class([^A-Za-z0-9_]|$)/)) {
      tok = substr(rest, RSTART, RLENGTH); sub(/[ \t]*\.[ \t]*class.*$/, "", tok)
      printf "S\t%s\t%s\n", fqn, tok
      rest = substr(rest, RSTART + RLENGTH)
    }
    o = gsub(/\(/, "(", line); c = gsub(/\)/, ")", line); depth += o - c
    if (depth <= 0) inSel = 0
  }

  # annotation usages (for test-marker detection); record every @Name on the line
  rest = line
  while (match(rest, /@[A-Za-z_][A-Za-z0-9_.]*/)) {
    a = substr(rest, RSTART + 1, RLENGTH - 1); sub(/^.*\./, "", a)
    if (a != "interface") printf "U\t%s\t%s\n", fqn, a
    rest = substr(rest, RSTART + RLENGTH)
  }

  # top-level type declaration (first one named like the file)
  if (kind == "" && !inDecl) {
    re = "(^|[ \t])(class|interface|enum|record|@interface)[ \t]+" simple "([^A-Za-z0-9_]|$)"
    if (line ~ re) { inDecl = 1; decl = "" }
  }
  if (inDecl) {
    decl = decl " " line
    if (line ~ /\{/) {
      inDecl = 0
      d = decl; sub(/\{.*$/, "", d)
      if (d ~ /@interface[ \t]/) kind = "annotation"
      else if (d ~ /(^|[ \t])interface[ \t]/) kind = "interface"
      else if (d ~ /(^|[ \t])enum[ \t]/) kind = "enum"
      else if (d ~ /(^|[ \t])record[ \t]/) kind = "record"
      else kind = "class"
      if (d ~ /(^|[ \t])abstract[ \t]/) isAbs = 1
      if (kind == "class") { t = d; gsub(/<[^<>]*>/, "", t); gsub(/<[^<>]*>/, "", t); sup = ident_after(t, " extends ") }
      if (sup == "") sup = "-"
    }
  }
}
END { flush() }
' < "$work/files" ) > "$work/facts"

[ -s "$work/facts" ] || { echo "fact extraction failed" >&2; exit 2; }

# Pass 2: resolve names, compute test markers, test-bearing classes, reachability, report.
awk -F'\t' -v ROOT="$suite" -v REPO="$root" '
function resolve(from, tok,   p, s, cand) {
  if (tok in classPath) return tok                       # already fully qualified
  if ((from SUBSEP tok) in imp) return imp[from, tok]
  p = pkgOf[from]
  cand = (p == "" ? tok : p "." tok)
  if (cand in classPath) return cand
  # nested reference Outer.Inner or unknown: keep as-is (reported as unresolved)
  return tok
}
function isMarker(from, a,   f) {
  if (a in builtin) return 1
  f = resolve(from, a)
  return (f in markerFqn)
}
$1 == "C" { classPath[$2] = $3; isAbs[$2] = $4; kind[$2] = $5; supTok[$2] = $6; isSuite[$2] = $7
            p = $2; if (p ~ /\./) sub(/\.[^.]*$/, "", p); else p = ""; pkgOf[$2] = p; next }
$1 == "I" { s = $3; sub(/^.*\./, "", s); imp[$2, s] = $3; next }
$1 == "S" { nsel[$2]++; sel[$2, nsel[$2]] = $3; next }
$1 == "U" { nu[$2]++; used[$2, nu[$2]] = $3; next }
END {
  builtin["Test"]; builtin["ParameterizedTest"]; builtin["RepeatedTest"]; builtin["TestFactory"]; builtin["TestTemplate"]
  # repo-declared annotation types meta-annotated with a test marker (fixpoint over meta-annotations)
  changed = 1
  while (changed) {
    changed = 0
    for (c in kind) if (kind[c] == "annotation" && !(c in markerFqn))
      for (i = 1; i <= nu[c]; i++) if (isMarker(c, used[c, i])) { markerFqn[c] = 1; changed = 1; break }
  }
  # direct test methods (annotation types themselves are not test classes)
  for (c in kind) if (kind[c] == "class") {
    for (i = 1; i <= nu[c]; i++) if (isMarker(c, used[c, i])) { direct[c] = 1; break }
    sup[c] = (supTok[c] == "-" ? "" : resolve(c, supTok[c]))
  }
  # test-bearing = self or any ancestor has direct test methods
  for (c in kind) if (kind[c] == "class") {
    x = c; n = 0
    while (x != "" && (x in kind) && n < 64) { if (x in direct) { bearing[c] = 1; break } ; x = sup[x]; n++ }
  }
  # reachability over @SelectClasses
  if (!(ROOT in classPath)) { printf "ERROR: root suite %s not found under %s\n", ROOT, REPO > "/dev/stderr"; exit 2 }
  q[1] = ROOT; qh = 1; qt = 1; seen[ROOT] = 1
  while (qh <= qt) {
    c = q[qh++]
    for (i = 1; i <= nsel[c]; i++) {
      t = resolve(c, sel[c, i])
      if (!(t in classPath)) { unresolved[c " -> " sel[c, i]] = 1; continue }
      if (!(t in seen)) { seen[t] = 1; q[++qt] = t; via[t] = c }
    }
  }
  # executed concrete classes and their ancestors
  for (c in seen) if (kind[c] == "class" && !isAbs[c] && (c in bearing)) {
    executed[c] = 1
    x = sup[c]; n = 0
    while (x != "" && (x in kind) && n < 64) { coveredAncestor[x] = 1; x = sup[x]; n++ }
  }
  nUn = 0
  for (c in bearing) {
    if (c in executed) continue
    if (isAbs[c] && (c in coveredAncestor)) continue
    why = isAbs[c] ? "abstract; no selected concrete subclass" : ((c in seen) ? "selected but abstract" : "not selected by any reachable suite")
    line = sprintf("%s\t%s\t%s", classPath[c], c, why)
    un[++nUn] = line
  }
  # informational: selected classes that run nothing, unreachable suites, unresolved selections
  nInfo = 0
  for (c in seen) if (c != ROOT && !(c in bearing) && !isSuite[c]) info[++nInfo] = sprintf("selected-but-empty\t%s\t%s (selected by %s)", classPath[c], c, via[c])
  for (c in isSuite) if (isSuite[c] && !(c in seen)) info[++nInfo] = sprintf("unreachable-suite\t%s\t%s", classPath[c], c)
  for (u in unresolved) info[++nInfo] = sprintf("unresolved-selection\t-\t%s", u)

  nBear = 0; for (c in bearing) nBear++
  nExec = 0; for (c in executed) nExec++
  nMark = 0; markers = ""; for (m in markerFqn) { nMark++; markers = markers " @" m }
  printf "root suite          : %s\n", ROOT
  printf "test markers        : @Test @ParameterizedTest @RepeatedTest @TestFactory @TestTemplate%s\n", markers
  printf "test-bearing classes: %d\n", nBear
  printf "executed (reachable): %d\n", nExec
  printf "UNREACHABLE         : %d\n", nUn
  for (i = 1; i <= nUn; i++) print "  UNREACHABLE\t" un[i] | "sort"
  close("sort")
  for (i = 1; i <= nInfo; i++) print "  INFO\t" info[i] | "sort"
  close("sort")
  exit (nUn > 0 ? 1 : 0)
}
' "$work/facts"
