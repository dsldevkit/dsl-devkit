import java.util.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import com.avaloq.tools.ddk.xtext.naming.*;
import com.avaloq.tools.ddk.xtext.resource.PatternAwareEObjectDescriptionLookUp;

/** Replays the Lean counterexamples on the real compiled classes (no Maven, plain JVM). */
public class TrieRepro {
  static int fails = 0;
  static void check(String id, Object actual, Object bugValue) {
    boolean reproduced = Objects.equals(actual, bugValue);
    System.out.println((reproduced ? "REPRODUCED " : "NOT-REPRO  ") + id + " -> " + actual);
    if (!reproduced) fails++;
  }
  static QualifiedName n(String... s) { return QualifiedName.create(s); }
  static QualifiedNamePattern p(String... s) { return QualifiedNamePattern.create(s); }
  static QualifiedNameSegmentTreeLookup<String> tree(boolean share, Object... kv) {
    QualifiedNameSegmentTreeLookup<String> t = new QualifiedNameSegmentTreeLookup<>(String.class, share);
    for (int i = 0; i < kv.length; i += 2) t.put((QualifiedName) kv[i], (String) kv[i + 1]);
    return t;
  }
  static TreeSetLookup<String> tsl(Object... kv) {
    TreeSetLookup<String> t = new TreeSetLookup<>();
    for (int i = 0; i < kv.length; i += 2) t.put((QualifiedName) kv[i], (String) kv[i + 1]);
    return t;
  }
  static List<String> sorted(Collection<String> c) { List<String> l = new ArrayList<>(c); Collections.sort(l); return l; }
  static IEObjectDescription d(String name) {
    EClass c = EcoreFactory.eINSTANCE.createEClass(); c.setName(name);
    return EObjectDescription.create(QualifiedName.create(name), c);
  }

  public static void main(String[] a) {
    // B1 top-level "*" / "**"
    check("B1 upper(*)", p("*").upperExclusive(), n("!"));
    check("B1 matches(*, b)", p("*").matches(n("b")), true);
    check("B1 tree get(*)", sorted(tree(false, n("b"), "b").get(p("*"), false)), List.of());
    check("B1 tsl get(*)", sorted(tsl(n("b"), "b").get(p("*"), false)), List.of());
    check("B1 tree get(**)", sorted(tree(false, n("b", "c"), "bc").get(p("**"), false)), List.of());
    // B2 '!' successor
    check("B2 tree get(a) returns 'a '", sorted(tree(false, n("a "), "a_").get(p("a"), false)), List.of("a_"));
    check("B2 tsl get(a) returns 'a '", sorted(tsl(n("a "), "a_").get(p("a"), false)), List.of("a_"));
    check("B2 matches(a, 'a ')", p("a").matches(n("a ")), false);
    check("B2 tree get(a.*) with a present", sorted(tree(false, n("a ", "x"), "a_x", n("a", "b"), "ab").get(p("a", "*"), false)), List.of("a_x", "ab"));
    check("B2 tree get(a.*) with a absent", sorted(tree(false, n("a ", "x"), "a_x").get(p("a", "*"), false)), List.of());
    check("B2 tsl get(a.*)", sorted(tsl(n("a ", "x"), "a_x").get(p("a", "*"), false)), List.of("a_x"));
    // B3 exact pattern matches longer names
    check("B3 matches(a, a.b)", p("a").matches(n("a", "b")), true);
    check("B3 tree get(a)", sorted(tree(false, n("a"), "a", n("a", "b"), "ab").get(p("a"), false)), List.of("a"));
    // B4 TreeSetLookup empty segment compare
    check("B4 tsl get((\"\",\" \"))", sorted(tsl(n("", ""), "ee").get(p("", " "), false)), List.of("ee"));
    check("B4 tree get((\"\",\" \"))", sorted(tree(false, n("", ""), "ee").get(p("", " "), false)), List.of());
    // B5 size drift
    QualifiedNameSegmentTreeLookup<String> t5 = tree(false, n("a"), "v", n("a"), "v");
    check("B5 size after put twice", t5.getStatistics().getEntries(), 2L);
    t5.remove(n("a"), "v");
    check("B5 size after remove", t5.getStatistics().getEntries(), 1L);
    check("B5 get after remove", t5.get(n("a")), null);
    // B6 getMappings blank segment
    check("B6 getMappings((' ','a'))", new ArrayList<>(tree(false, n(" ", "a"), "v").getMappings("v")), List.of(n("a")));
    check("B6 getMappings(('','a'))", new ArrayList<>(tree(false, n("", "a"), "v").getMappings("v")), List.of(n("a")));
    // B7 value sharing multiplicity
    check("B7 share=true get(a**)", sorted(tree(true, n("a"), "v", n("a", "b"), "v").get(p("a**"), false)), List.of("v"));
    check("B7 share=false get(a**)", sorted(tree(false, n("a"), "v", n("a", "b"), "v").get(p("a**"), false)), List.of("v", "v"));
    // B8 consumer
    PatternAwareEObjectDescriptionLookUp lu = new PatternAwareEObjectDescriptionLookUp(List.of(d("Foo"), d("FooBar")));
    List<String> cs = new ArrayList<>(); lu.getExportedObjects(EcorePackage.Literals.ECLASS, p("Foo*"), false).forEach(x -> cs.add(x.getName().toString()));
    List<String> ci = new ArrayList<>(); lu.getExportedObjects(EcorePackage.Literals.ECLASS, p("Foo*"), true).forEach(x -> ci.add(x.getName().toString()));
    check("B8 case-sensitive Foo*", sorted(cs), List.of());
    check("B8 case-insensitive Foo*", sorted(ci), List.of("Foo", "FooBar"));
    // B9 U+FFFF
    String max = String.valueOf(Character.MAX_VALUE);
    check("B9 upper(\\uFFFF*)", p(max + "*").upperExclusive(), n("\u0000"));
    String iae;
    try { tsl().get(p(max + "*"), false); iae = "no exception"; } catch (IllegalArgumentException e) { iae = "IAE: " + e.getMessage(); }
    check("B9 tsl get(\\uFFFF*)", iae, "IAE: fromKey > toKey");
    QualifiedNameSegmentTreeLookup<String> t9 = tree(false, n(max), "m");
    check("B9 tree get(pattern \\uFFFF)", sorted(t9.get(p(max), false)), List.of());
    check("B9 tree get(name \\uFFFF)", t9.get(n(max)), List.of("m"));
    // B10 globs
    check("B10 glob * matches b", QualifiedNamePattern.createFromGlobs("*").matches(n("b")), true);
    check("B10 tree glob *", sorted(tree(false, n("b"), "b").get(QualifiedNamePattern.createFromGlobs("*"), false)), List.of());
    check("B10 tree glob a*.b", sorted(tree(false, n("a", "b"), "ab").get(QualifiedNamePattern.createFromGlobs("a*", "b"), false)), List.of());
    check("B10 tsl glob a*.b", sorted(tsl(n("a", "b"), "ab").get(QualifiedNamePattern.createFromGlobs("a*", "b"), false)), List.of("ab"));
    check("B10 glob a* matches a.x", QualifiedNamePattern.createFromGlobs("a*").matches(n("a", "x")), true);
    check("B10 tree glob a* misses a.x", sorted(tree(false, n("a", "x"), "ax").get(QualifiedNamePattern.createFromGlobs("a*"), false)), List.of());
    check("B10 glob F* matches foo", QualifiedNamePattern.createFromGlobs("F*").matches(n("foo")), true);
    check("B10 tree glob F* misses foo", sorted(tree(false, n("foo"), "foo").get(QualifiedNamePattern.createFromGlobs("F*"), false)), List.of());
    String sioobe;
    try { QualifiedNamePattern.createFromGlobs("a", "").matches(n("a", "")); sioobe = "no exception"; } catch (StringIndexOutOfBoundsException e) { sioobe = "SIOOBE"; }
    check("B10 glob with empty last segment throws", sioobe, "SIOOBE");
    System.out.println(fails == 0 ? "ALL REPRODUCED" : fails + " NOT REPRODUCED");
  }
}
