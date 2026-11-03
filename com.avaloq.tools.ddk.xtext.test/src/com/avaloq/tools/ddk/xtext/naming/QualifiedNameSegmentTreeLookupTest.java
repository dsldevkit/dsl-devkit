/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.naming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;


@SuppressWarnings({"nls", "unused", "PMD.JUnitAssertionsShouldIncludeMessage"})
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class QualifiedNameSegmentTreeLookupTest {
  private static final Logger LOGGER = LogManager.getLogger(QualifiedNameSegmentTreeLookupTest.class);

  private final QualifiedNameSegmentTreeLookup<URI> lookup = new QualifiedNameSegmentTreeLookup<URI>(URI.class, true);

  @Test
  public void testEmpty() {
    assertNull(lookup.get(QualifiedName.EMPTY));
  }

  @Test
  public void testExact() {
    QualifiedName name = name("foo");
    Collection<URI> values = Collections.singletonList(uri(name));
    lookup.putAll(name, values);
    assertContentEquals(values, lookup.get(name));

    name = name("foo.bar");
    values = Collections.singletonList(uri(name));
    lookup.putAll(name, values);
    assertContentEquals(values, lookup.get(name));

    name = name("bar");
    values = Collections.singletonList(uri(name));
    lookup.putAll(name, values);
    assertContentEquals(values, lookup.get(name));
  }

  @Test
  public void testTopLevelPatternWithoutWildcard() {
    URI value1 = put("foo");
    URI value2 = put("bar");
    URI value3 = put("foo2");

    assertContentEquals(ImmutableSet.of(value1), lookup.get(pattern("foo"), false));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern("bar"), false));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern("foo2"), false));
  }

  @Test
  public void testTopLevelPatternWithWildcard() {
    URI value1 = put("foo");
    URI value2 = put("foo2");
    URI value3 = put("bar");

    assertContentEquals(ImmutableSet.of(value1, value2), lookup.get(pattern("f*"), true));
    assertContentEquals(ImmutableSet.of(value1, value2), lookup.get(pattern("foo*"), true));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern("b*"), true));
  }

  @Test
  public void testNestedPatternMatchesWithoutWildcard() {
    URI value1 = put("foo");
    URI value2 = put("foo.bar");
    URI value3 = put("foo2");

    assertContentEquals(ImmutableSet.of(value1), lookup.get(pattern("foo"), true));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern("foo.bar"), true));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern("foo2"), true));
  }

  @Test
  public void testNestedPatternMatchesWithWildcard() {
    URI value1 = put("foo");
    URI value2 = put("foo.bar");
    URI value3 = put("foo.baz");
    URI value4 = put("foo.baz.bazz");
    URI value5 = put("foo2");

    assertContentEquals(ImmutableSet.of(value1, value5), lookup.get(pattern("f*"), true));
    assertContentEquals(ImmutableSet.of(value2, value3), lookup.get(pattern("foo.*"), true));
    assertContentEquals(ImmutableSet.of(value2, value3), lookup.get(pattern("foo.ba*"), true));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern("foo.bar*"), true));
  }

  @Test
  public void testNestedPatternMatchesWithRecursiveWildcard() {
    URI value1 = put("foo");
    URI value2 = put("foo.bar");
    URI value3 = put("foo.bar.baz");
    URI value4 = put("foo.bar.baz.quux");
    URI value5 = put("foo.foo");
    URI value6 = put("foo2");

    assertContentEquals(ImmutableSet.of(value2, value3, value4, value5), lookup.get(pattern("foo.**"), true));
    assertContentEquals(ImmutableSet.of(value2, value3, value4), lookup.get(pattern("foo.b**"), true));
  }

  @Test
  public void testUnmatchedNestedPattern() {
    URI value1 = put("foo");
    URI value2 = put("foo.bar");
    URI value3 = put("foo.bar.baz");
    URI value4 = put("foo.bar.baz.quux");
    URI value5 = put("foo.foo");
    URI value6 = put("foo2");

    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("e*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("g*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("foa.*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("fon.b*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("foo.c*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("foo.baq.b*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("foo.bar.a*"), true));
    assertContentEquals(ImmutableSet.of(), lookup.get(pattern("foo.bar.bazz*"), true));
  }

  @Test
  public void testOutOfOrderInsertion() {
    QualifiedName name1 = name("foo.bar");
    Collection<URI> value1 = Collections.singletonList(uri(name1));
    lookup.putAll(name1, value1);
    assertContentEquals(value1, lookup.get(name1));

    QualifiedName name2 = name("foo");
    Collection<URI> value2 = Collections.singletonList(uri(name2));
    lookup.putAll(name2, value2);
    assertContentEquals(value2, lookup.get(name2));
  }

  @Test
  public void testLoadStore() throws IOException, ClassNotFoundException {
    List<QualifiedName> nameList = List.of(name("foo"), name("foo.bar"), name("bar"));
    for (QualifiedName qn : nameList) {
      lookup.put(qn, uri(qn));
    }
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
    objectOutStream.writeObject(lookup);
    byte[] data = byteOutStream.toByteArray();

    ObjectInputStream inStream = new ObjectInputStream(new ByteArrayInputStream(data));
    Object readBack = inStream.readObject();

    assertEquals(readBack.getClass(), lookup.getClass());

    @SuppressWarnings("unchecked")
    QualifiedNameLookup<URI> readBackLookup = (QualifiedNameLookup<URI>) readBack;

    for (QualifiedName qn : nameList) {
      assertEquals(lookup.get(qn), readBackLookup.get(qn));
    }
  }

  @Test
  public void testGetMappings() {
    final QualifiedName a = name("A");
    final QualifiedName b = name("B");
    final QualifiedName c = name("A.C");
    final QualifiedName d = name("A.D");
    final QualifiedName e = name("B.E");
    final QualifiedName f = name("B.F");
    final QualifiedName g = name("A.C.G");
    final QualifiedName h = name("A.C.H");
    final QualifiedName i = name("A.D.I");
    final QualifiedName j = name("A.D.J");

    List<QualifiedName> nameList = List.of(a, b, c, d, e, f, g, h, i, j);
    for (QualifiedName qn : nameList) {
      lookup.put(qn, uri(qn));
    }

    URI value = URI.createURI("scheme:/host");

    lookup.put(c, value);
    lookup.put(g, value);
    lookup.put(h, value);
    lookup.put(f, value);
    lookup.put(b, value);

    Collection<QualifiedName> result = lookup.getMappings(value);
    Collection<QualifiedName> expected = List.of(c, g, h, f, b);

    assertContentEquals(expected, result);

    URI noSuchValue = URI.createURI("scheme:/anotherHost");
    expected = lookup.getMappings(noSuchValue);
    assertEquals(0, expected.size());
  }

  @Test
  public void testPatternMatchesWithSharedValues() {
    URI v1 = uri(name("v1"));
    URI v2 = uri(name("v2"));
    URI v3 = uri(name("v3"));
    for (boolean shareValues : new boolean[] {true, false}) {
      QualifiedNameSegmentTreeLookup<URI> tree = createTreeWithSharedValues(shareValues, v1, v2, v3);

      // depth-first order below foo: bam [v2], bar [v1, v2], bar.baz [v1, v2], bar.qux [v3], baz [v1, v2]
      List<URI> recursiveBelowFoo = shareValues ? List.of(v2, v1, v2, v3) : List.of(v2, v1, v2, v1, v2, v3, v1, v2);
      assertMatches(tree, "foo.**", recursiveBelowFoo);
      assertMatches(tree, "foo.*", shareValues ? List.of(v2, v1, v2) : List.of(v2, v1, v2, v1, v2));
      assertMatches(tree, "foo.b*", shareValues ? List.of(v2, v1, v2) : List.of(v2, v1, v2, v1, v2));
      assertMatches(tree, "foo.bar*", List.of(v1, v2));
      assertMatches(tree, "foo.bar.*", List.of(v1, v2, v3));
      assertMatches(tree, "foo.bar.**", List.of(v1, v2, v3));
      assertMatches(tree, "foo", List.of(v1, v2));
      assertMatches(tree, "f*", List.of(v1, v2, v1));
      List<URI> recursiveF = new ArrayList<>(List.of(v1, v2));
      recursiveF.addAll(shareValues ? List.of(v2, v3, v1) : List.of(v2, v1, v2, v1, v2, v3, v1, v2, v1));
      assertMatches(tree, "f**", recursiveF);
      assertMatches(tree, "g*", List.of());

      // foo.bar gets an array of its own, foo, foo.bar.baz and foo.baz still share theirs
      tree.remove(name("foo.bar"), v2);
      assertMatches(tree, "foo.**", shareValues ? List.of(v2, v1, v1, v2, v3) : List.of(v2, v1, v1, v2, v3, v1, v2));
      assertMatches(tree, "foo.*", List.of(v2, v1, v1, v2));

      // removing a value from every node replaces each array by a new one with equal contents
      tree.removeMappings(v2);
      assertMatches(tree, "foo.**", List.of(v1, v1, v3, v1));
      assertMatches(tree, "f**", List.of(v1, v1, v1, v3, v1, v1));
    }
  }

  // when sharing values, foo, foo.bar, foo.bar.baz and foo.baz share one value array
  private QualifiedNameSegmentTreeLookup<URI> createTreeWithSharedValues(final boolean shareValues, final URI v1, final URI v2, final URI v3) {
    QualifiedNameSegmentTreeLookup<URI> tree = new QualifiedNameSegmentTreeLookup<URI>(URI.class, shareValues);
    tree.putAll(name("foo"), Arrays.asList(v1, v2));
    tree.putAll(name("foo.bar"), Arrays.asList(v1, v2));
    tree.putAll(name("foo.bar.baz"), Arrays.asList(v1, v2));
    tree.put(name("foo.bar.qux"), v3);
    tree.putAll(name("foo.baz"), Arrays.asList(v1, v2));
    tree.put(name("foo.bam"), v2);
    tree.put(name("foo2"), v1);
    return tree;
  }

  /**
   * Asserts the result of a pattern lookup with and without duplicates.
   *
   * @param tree
   *          lookup to query, must not be {@code null}
   * @param pattern
   *          pattern to look up, must not be {@code null}
   * @param expected
   *          expected values including duplicates, must not be {@code null}
   */
  private void assertMatches(final QualifiedNameLookup<URI> tree, final String pattern, final List<URI> expected) {
    Collection<URI> withDuplicates = tree.get(pattern(pattern), false);
    assertInstanceOf(ArrayList.class, withDuplicates, pattern);
    assertEquals(ImmutableMultiset.copyOf(expected), ImmutableMultiset.copyOf(withDuplicates), pattern);

    Collection<URI> withoutDuplicates = tree.get(pattern(pattern), true);
    assertInstanceOf(HashSet.class, withoutDuplicates, pattern);
    Set<URI> expectedSet = new HashSet<>();
    expectedSet.addAll(expected);
    assertIterableEquals(expectedSet, withoutDuplicates, pattern);
  }

  private QualifiedName name(final String str) {
    return QualifiedNames.safeQualifiedName(str);
  }

  private QualifiedNamePattern pattern(final String str) {
    return pattern(name(str));
  }

  private QualifiedNamePattern pattern(final QualifiedName name) {
    return QualifiedNamePattern.create(name);
  }

  public URI uri(final QualifiedName name) {
    return URI.createURI("scheme:/" + name);
  }

  private URI put(final String name) {
    QualifiedName qname = name(name);
    URI value = uri(qname);
    lookup.put(qname, value);
    return value;
  }

  public void assertContentEquals(final Collection<?> expected, final Collection<?> actual) {
    assertEquals(ImmutableSet.copyOf(expected), ImmutableSet.copyOf(actual));
  }
}
