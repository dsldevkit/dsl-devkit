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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.eclipse.xtext.naming.QualifiedName;
import org.junit.Test;

import com.avaloq.tools.ddk.test.core.BugTest;


@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
// CHECKSTYLE:CONSTANTS-OFF
public class QualifiedNamePatternTest {

  @Test
  public void testSimpleQualifiedNamePattern() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("foo*");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());
    assertMatch(pattern, "foo");
    assertMatch(pattern, "foof");
    assertNoMatch(pattern, "foo", "bar");
  }

  @Test
  public void testQualifiedPrefixNamePattern() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("foo", "*");
    assertEquals(QualifiedName.create("foo", ""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo!"), pattern.upperExclusive());
    assertMatch(pattern, "foo", "bar");
    assertNoMatch(pattern, "foo", "bar", "baz");
    assertNoMatch(pattern, "foo2", "bar");

    pattern = QualifiedNamePattern.create("foo", "bar*");
    assertEquals(QualifiedName.create("foo", "bar"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo", "bas"), pattern.upperExclusive());
  }

  @Test
  public void testRecursiveWildcardPattern() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("foo", "**");
    assertEquals(QualifiedName.create("foo", ""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo!"), pattern.upperExclusive());
    assertMatch(pattern, "foo", "bar");
    assertMatch(pattern, "foo", "bar", "baz");
    assertNoMatch(pattern, "foo2", "bar");
  }

  @Test
  public void testRecursiveWildcardPatternWithPrefix() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("foo", "b**");
    assertEquals(QualifiedName.create("foo", "b"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo", "c"), pattern.upperExclusive());
    assertMatch(pattern, "foo", "bar");
    assertMatch(pattern, "foo", "bar", "baz");
    assertNoMatch(pattern, "foo2", "bar");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecursiveWildcardPatternError() {
    QualifiedNamePattern.create("foo", "bar**baz");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecursiveWildcardPatternError2() {
    QualifiedNamePattern.create("foo", "**", "bar");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecursiveWildcardPatternError3() {
    QualifiedNamePattern.create("foo*bar");
  }

  @Test
  public void testAllPattern() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("*");
    assertEquals(QualifiedName.create(""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("!"), pattern.upperExclusive());
  }

  @Test
  public void testPatternWithoutWildcard() {
    QualifiedNamePattern pattern = QualifiedNamePattern.create("foo");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo!"), pattern.upperExclusive());
  }

  @Test
  public void testRegexpPatterns() {
    QualifiedNamePattern pattern = QualifiedNamePattern.createFromGlobs("*");
    assertEquals(QualifiedName.create(""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("!"), pattern.upperExclusive());
    assertMatch(pattern, "foo");
    assertMatch(pattern, "foo", "bar");

    pattern = QualifiedNamePattern.createFromGlobs("?");
    assertEquals(QualifiedName.create(""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("!"), pattern.upperExclusive());
    assertMatch(pattern, "f");
    assertNoMatch(pattern, "foo");
    assertNoMatch(pattern, "foo", "bar");

    pattern = QualifiedNamePattern.createFromGlobs("foo*");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());
    assertNoMatch(pattern, "f");
    assertMatch(pattern, "foo");
    assertMatch(pattern, "foo", "bar");

    pattern = QualifiedNamePattern.createFromGlobs("foo?");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());
    assertNoMatch(pattern, "foo");
    assertMatch(pattern, "foof");
    assertNoMatch(pattern, "foof", "bar");
    assertNoMatch(pattern, "foofy");

    pattern = QualifiedNamePattern.createFromGlobs("foo?bar*");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());
    assertNoMatch(pattern, "foo");
    assertMatch(pattern, "foofbar");
    assertMatch(pattern, "foofbar", "bar");
    assertMatch(pattern, "foofbarzzzz");

    pattern = QualifiedNamePattern.createFromGlobs("foo", "*");
    assertEquals(QualifiedName.create("foo", ""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("foo!"), pattern.upperExclusive());
    assertMatch(pattern, "foo", "");
    assertMatch(pattern, "foo", "bar");
    assertNoMatch(pattern, "foo");

    pattern = QualifiedNamePattern.createFromGlobs("foo*", "bar*");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());

    pattern = QualifiedNamePattern.createFromGlobs("foo*bar*");
    assertEquals(QualifiedName.create("foo"), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("fop"), pattern.upperExclusive());

    pattern = QualifiedNamePattern.createFromGlobs("?foo*bar*");
    assertEquals(QualifiedName.create(""), pattern.lowerInclusive());
    assertEquals(QualifiedName.create("!"), pattern.upperExclusive());
  }

  private void assertMatch(final QualifiedNamePattern pattern, final String... segments) {
    assertTrue(pattern.matches(QualifiedName.create(segments)));
  }

  private void assertNoMatch(final QualifiedNamePattern pattern, final String... segments) {
    assertFalse(pattern.matches(QualifiedName.create(segments)));
  }

  @BugTest("DSL-209")
  @Test
  @SuppressWarnings("PMD.UseAssertSameInsteadOfAssertTrue")
  // The comparator structure of ==, > and < should be clear in the tests.
  public void testComparison() {
    // basic wild cards
    assertTrue(0 > comparePattern(createPattern("foo", "abc*"), createName("foo", "abcd")));
    assertTrue(0 > comparePattern(createPattern("foo*"), createName("foo", "abcd")));
    assertTrue(0 < comparePattern(createPattern("foo", "abc*"), createName("foo"))); // foo.abc > foo => foo.abc* > foo
    // corner cases
    assertTrue(0 > comparePattern(createPattern("ab*"), createName("ab")));
    assertTrue(0 > comparePattern(createPattern("ab*"), createName("abc")));
    assertTrue(0 > comparePattern(createPattern("aa*"), createName("aba"))); // aa < ab => aa* < aba
    assertTrue(0 < comparePattern(createPattern("ab*"), createName("aaa"))); // ab > aa => ab* > aaa)
    // $
    assertTrue(0 < comparePattern(createPattern("foo", "xyz$*"), createName("foo", "xyz")));
    assertTrue(0 > comparePattern(createPattern("foo", "xya$*"), createName("foo", "xyz")));
    // sgn(compare(x,y)) = -sgn(compare(y,x)) using the preceding two cases
    assertTrue(0 > comparator.compare(createName("foo", "xyz"), createPattern("foo", "xyz$*")));
    assertTrue(0 < comparator.compare(createName("foo", "xyz"), createPattern("foo", "xya$*"))); // ditto
  }

  private final Comparator<QualifiedName> comparator = new QualifiedNamePattern.Comparator();

  private QualifiedNamePattern createPattern(final String... segments) {
    return QualifiedNamePattern.create(segments);
  }

  private QualifiedName createName(final String... segments) {
    return QualifiedName.create(segments);
  }

  private int comparePattern(final QualifiedNamePattern pattern, final QualifiedName name) {
    return comparator.compare(pattern, name);
  }

}
