/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.naming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNameSegmentTreeLookup;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.google.common.collect.ImmutableSet;


@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
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
    QualifiedName name1 = name("foo");
    URI value1 = uri(name1);
    lookup.put(name1, value1);
    QualifiedName name2 = name("bar");
    URI value2 = uri(name2);
    lookup.put(name2, value2);
    QualifiedName name3 = name("foo2");
    URI value3 = uri(name3);
    lookup.put(name3, value3);

    assertContentEquals(ImmutableSet.of(value1), lookup.get(pattern(name1), false));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern(name2), false));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern(name3), false));
  }

  @Test
  public void testTopLevelPatternWithWildcard() {
    QualifiedName name1 = name("foo");
    URI value1 = uri(name1);
    lookup.put(name1, value1);
    QualifiedName name2 = name("foo2");
    URI value2 = uri(name2);
    lookup.put(name2, value2);
    QualifiedName name3 = name("bar");
    URI value3 = uri(name3);
    lookup.put(name3, value3);

    assertContentEquals(ImmutableSet.of(value1, value2), lookup.get(pattern("f*"), true));
    assertContentEquals(ImmutableSet.of(value1, value2), lookup.get(pattern("foo*"), true));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern("b*"), true));
  }

  @Test
  public void testNestedPatternMatchesWithoutWildcard() {
    QualifiedName name1 = name("foo");
    URI value1 = uri(name1);
    lookup.put(name1, value1);
    QualifiedName name2 = name("foo.bar");
    URI value2 = uri(name2);
    lookup.put(name2, value2);
    QualifiedName name3 = name("foo2");
    URI value3 = uri(name3);
    lookup.put(name3, value3);

    assertContentEquals(ImmutableSet.of(value1), lookup.get(pattern(name1), true));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern(name2), true));
    assertContentEquals(ImmutableSet.of(value3), lookup.get(pattern(name3), true));
  }

  @Test
  public void testNestedPatternMatchesWithWildcard() {
    QualifiedName name1 = name("foo");
    URI value1 = uri(name1);
    lookup.put(name1, value1);
    QualifiedName name2 = name("foo.bar");
    URI value2 = uri(name2);
    lookup.put(name2, value2);
    QualifiedName name3 = name("foo.baz");
    URI value3 = uri(name3);
    lookup.put(name3, value3);
    QualifiedName name4 = name("foo.baz.bazz");
    URI value4 = uri(name4);
    lookup.put(name4, value4);
    QualifiedName name5 = name("foo2");
    URI value5 = uri(name5);
    lookup.put(name5, value5);

    assertContentEquals(ImmutableSet.of(value1, value5), lookup.get(pattern("f*"), true));
    assertContentEquals(ImmutableSet.of(value2, value3), lookup.get(pattern("foo.*"), true));
    assertContentEquals(ImmutableSet.of(value2, value3), lookup.get(pattern("foo.ba*"), true));
    assertContentEquals(ImmutableSet.of(value2), lookup.get(pattern("foo.bar*"), true));
  }

  @Test
  public void testNestedPatternMatchesWithRecursiveWildcard() {
    QualifiedName name1 = name("foo");
    URI value1 = uri(name1);
    lookup.put(name1, value1);
    QualifiedName name2 = name("foo.bar");
    URI value2 = uri(name2);
    lookup.put(name2, value2);
    QualifiedName name3 = name("foo.bar.baz");
    URI value3 = uri(name3);
    lookup.put(name3, value3);
    QualifiedName name4 = name("foo.bar.baz.quux");
    URI value4 = uri(name4);
    lookup.put(name4, value4);
    QualifiedName name5 = name("foo.foo");
    URI value5 = uri(name5);
    lookup.put(name5, value5);
    QualifiedName name6 = name("foo2");
    URI value6 = uri(name6);
    lookup.put(name6, value6);

    assertContentEquals(ImmutableSet.of(value2, value3, value4, value5), lookup.get(pattern("foo.**"), true));
    assertContentEquals(ImmutableSet.of(value2, value3, value4), lookup.get(pattern("foo.b**"), true));
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

  public void assertContentEquals(final Collection<?> expected, final Collection<?> actual) {
    assertEquals(ImmutableSet.copyOf(expected), ImmutableSet.copyOf(actual));
  }
}

