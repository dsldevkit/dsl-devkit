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
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableSet;


@SuppressWarnings({"nls", "unused", "PMD.JUnitAssertionsShouldIncludeMessage"})
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class QualifiedNameSegmentTreeLookupTest {

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
