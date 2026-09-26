/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.naming;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.resource.PatternAwareEObjectDescriptionLookUp;


/**
 * Failing-first regression tests for the counterexamples found by the Lean model in {@code formal/trie/lean} (see NOTES.md there, findings B1-B10). Each
 * test asserts the intended behaviour: a pattern lookup returns exactly the values whose names the pattern matches, as the reference
 * {@link TreeSetLookup} is meant to.
 */
@SuppressWarnings({"nls", "PMD.JUnitAssertionsShouldIncludeMessage"})
// CHECKSTYLE:CONSTANTS-OFF
public class QualifiedNameLookupFormalTest {

  private static final String MAX = String.valueOf(Character.MAX_VALUE);

  // B1: upperExclusive() of a single-segment "*" / "**" is "!", which excludes every name at or above '!'.
  @Disabled("Documents TRIE-2, see formal/BUGS.md; enable with the fix")
  @Test
  public void testTopLevelWildcardFindsSingleSegmentNames() {
    assertEquals(List.of("b"), sorted(tree(false, name("b"), "b").get(pattern("*"), false)));
    assertEquals(List.of("b"), sorted(treeSet(name("b"), "b").get(pattern("*"), false)));
  }

  @Disabled("Documents TRIE-2, see formal/BUGS.md; enable with the fix")
  @Test
  public void testTopLevelRecursiveWildcardFindsAllNames() {
    assertEquals(List.of("b", "bc"), sorted(tree(false, name("b"), "b", name("b", "c"), "bc").get(pattern("**"), false)));
  }

  // B2: '!' is not the successor of a segment; "a " (and any suffix below '!') falls inside [a, a!).
  @Disabled("Documents TRIE-3, see formal/BUGS.md; enable with the fix")
  @Test
  public void testExactPatternExcludesNameWithLowCharSuffix() {
    assertFalse(pattern("a").matches(name("a ")));
    assertEquals(List.of(), sorted(tree(false, name("a "), "a_").get(pattern("a"), false)));
    assertEquals(List.of(), sorted(treeSet(name("a "), "a_").get(pattern("a"), false)));
  }

  @Disabled("Documents TRIE-3, see formal/BUGS.md; enable with the fix")
  @Test
  public void testChildWildcardExcludesSiblingWithLowCharSuffix() {
    assertEquals(List.of("ab"), sorted(tree(false, name("a ", "x"), "a_x", name("a", "b"), "ab").get(pattern("a", "*"), false)));
    assertEquals(List.of(), sorted(treeSet(name("a ", "x"), "a_x").get(pattern("a", "*"), false)));
  }

  // B3: a wildcard-free pattern matches() longer names, while both lookups return only the exact name.
  @Disabled("Documents TRIE-4, see formal/BUGS.md; enable with the fix")
  @Test
  public void testExactPatternMatchesOnlyEqualLength() {
    assertEquals(List.of("a"), sorted(tree(false, name("a"), "a", name("a", "b"), "ab").get(pattern("a"), false)));
    assertFalse(pattern("a").matches(name("a", "b")));
  }

  // B4: QualifiedNamePattern.compareTo treats an empty pattern segment as smaller than anything (TreeSetLookup only).
  @Disabled("Documents TRIE-10, see formal/BUGS.md; enable with the fix")
  @Test
  public void testTreeSetLookupEmptySegmentPattern() {
    assertFalse(pattern("", " ").matches(name("", "")));
    assertEquals(List.of(), sorted(treeSet(name("", ""), "e").get(pattern("", " "), false)));
  }

  // B5: put() increments size even when the value is already mapped.
  @Disabled("Documents TRIE-5, see formal/BUGS.md; enable with the fix")
  @Test
  public void testSizeCountsMappingsOnce() {
    QualifiedNameSegmentTreeLookup<String> lookup = tree(false, name("a"), "v", name("a"), "v");
    assertEquals(1L, lookup.getStatistics().getEntries());
    lookup.remove(name("a"), "v");
    assertEquals(0L, lookup.getStatistics().getEntries());
  }

  // B6: getMappings() drops blank intermediate segments.
  @Disabled("Documents TRIE-7, see formal/BUGS.md; enable with the fix")
  @Test
  public void testGetMappingsKeepsBlankSegments() {
    assertEquals(List.of(name(" ", "a")), new ArrayList<>(tree(false, name(" ", "a"), "v").getMappings("v")));
    assertEquals(List.of(name("", "a")), new ArrayList<>(tree(false, name("", "a"), "v").getMappings("v")));
  }

  // B7: with shareValues=true, a child sharing its parent's value array is reported once for excludeDuplicates=false.
  @Disabled("Documents TRIE-8, see formal/BUGS.md; enable with the fix")
  @Test
  public void testValueSharingKeepsMultiplicity() {
    assertEquals(List.of("v", "v"), sorted(tree(true, name("a"), "v", name("a", "b"), "v").get(pattern("a**"), false)));
  }

  // B8: PatternAwareEObjectDescriptionLookUp filters case-sensitive pattern queries with name.matches(name), which is always false.
  @Disabled("Documents TRIE-1, see formal/BUGS.md; enable with the fix")
  @Test
  public void testCaseSensitivePatternQuery() {
    PatternAwareEObjectDescriptionLookUp lookUp = new PatternAwareEObjectDescriptionLookUp(List.of(description("Foo"), description("FooBar"), description("foo")));
    List<String> result = new ArrayList<>();
    lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, pattern("Foo*"), false).forEach(d -> result.add(d.getName().toString()));
    assertEquals(List.of("Foo", "FooBar"), sorted(result));
  }

  // B9: (char) (c + 1) wraps for U+FFFF, and a stored "￿" coincides with the tree's sentinel node.
  @Disabled("Documents TRIE-9, see formal/BUGS.md; enable with the fix")
  @Test
  public void testMaxCharPattern() {
    assertDoesNotThrow(() -> new TreeSetLookup<String>().get(pattern(MAX + "*"), false));
    assertEquals(List.of("m"), sorted(tree(false, name(MAX), "m").get(pattern(MAX), false)));
  }

  // B10: glob patterns (createFromGlobs) miss matches: "!" upper bound, non-recursive walk of "*"-suffixed globs, case-insensitive regexps.
  @Disabled("Documents TRIE-12, TRIE-13, TRIE-14, see formal/BUGS.md; enable with the fix")
  @Test
  public void testGlobLookupsFindMatches() {
    assertEquals(List.of("b"), sorted(tree(false, name("b"), "b").get(QualifiedNamePattern.createFromGlobs("*"), false)));
    assertEquals(List.of("ax"), sorted(tree(false, name("a", "x"), "ax").get(QualifiedNamePattern.createFromGlobs("a*"), false)));
    // whatever the case semantics of globs, a lookup must return what matches() accepts
    QualifiedNamePattern upperCaseGlob = QualifiedNamePattern.createFromGlobs("F*");
    List<String> expected = upperCaseGlob.matches(name("foo")) ? List.of("foo") : List.of();
    assertEquals(expected, sorted(tree(false, name("foo"), "foo").get(upperCaseGlob, false)));
  }

  @Disabled("Documents TRIE-15, see formal/BUGS.md; enable with the fix")
  @Test
  public void testGlobWithEmptyLastSegment() {
    assertDoesNotThrow(() -> QualifiedNamePattern.createFromGlobs("a", "").matches(name("a", "")));
  }

  private static QualifiedName name(final String... segments) {
    return QualifiedName.create(segments);
  }

  private static QualifiedNamePattern pattern(final String... segments) {
    return QualifiedNamePattern.create(segments);
  }

  private static QualifiedNameSegmentTreeLookup<String> tree(final boolean shareValues, final Object... namesAndValues) {
    QualifiedNameSegmentTreeLookup<String> lookup = new QualifiedNameSegmentTreeLookup<>(String.class, shareValues);
    for (int i = 0; i < namesAndValues.length; i += 2) {
      lookup.put((QualifiedName) namesAndValues[i], (String) namesAndValues[i + 1]);
    }
    return lookup;
  }

  private static TreeSetLookup<String> treeSet(final Object... namesAndValues) {
    TreeSetLookup<String> lookup = new TreeSetLookup<>();
    for (int i = 0; i < namesAndValues.length; i += 2) {
      lookup.put((QualifiedName) namesAndValues[i], (String) namesAndValues[i + 1]);
    }
    return lookup;
  }

  private static List<String> sorted(final Collection<String> values) {
    List<String> result = new ArrayList<>(values);
    result.sort(null);
    return result;
  }

  private static IEObjectDescription description(final String name) {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName(name);
    return EObjectDescription.create(QualifiedName.create(name), eClass);
  }
}
