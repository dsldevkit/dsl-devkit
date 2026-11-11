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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.avaloq.tools.ddk.util.Strings;
import com.avaloq.tools.ddk.xtext.scoping.INameFunction;
import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;


/**
 * Tests {@link QualifiedNameConverter} and the name functions using it.
 */
@SuppressWarnings({"nls", "PMD.JUnitAssertionsShouldIncludeMessage"})
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class QualifiedNameConverterTest {

  private final QualifiedNameConverter converter = new QualifiedNameConverter();

  @Test
  public void testSingleSegment() {
    assertEquals(QualifiedName.create("foo"), converter.toQualifiedName("foo"));
    assertEquals(1, converter.toQualifiedName("foo").getSegmentCount());
    assertEquals(QualifiedName.create(" "), converter.toQualifiedName(" "));
    assertEquals(QualifiedName.create("foo::bar"), converter.toQualifiedName("foo::bar"));
  }

  @Test
  public void testSeveralSegments() {
    assertEquals(QualifiedName.create("foo", "bar"), converter.toQualifiedName("foo.bar"));
    assertEquals(QualifiedName.create("a", "b", "c", "d", "e"), converter.toQualifiedName("a.b.c.d.e"));
    assertEquals(QualifiedName.create("", "foo"), converter.toQualifiedName(".foo"));
    assertEquals(QualifiedName.create("foo", ""), converter.toQualifiedName("foo."));
    assertEquals(QualifiedName.create("", ""), converter.toQualifiedName("."));
  }

  @ParameterizedTest
  @ValueSource(strings = {"a", "foo", " ", "a.b", "a.b.c.d.e", "com.google.inject.Provider", ".", "..", "...", ".a", "..a", "a.", "a..", "a..b",
      "a...b", ".a.", "..a..b..", "foo::bar.baz"})
  public void testSameAsSplit(final String name) {
    QualifiedName expected = QualifiedName.create(Strings.split(name, '.'));
    QualifiedName actual = converter.toQualifiedName(name);
    assertEquals(expected, actual);
    assertEquals(expected.hashCode(), actual.hashCode());
    assertEquals(expected.getSegments(), actual.getSegments());
  }

  @Test
  public void testEmptySegments() {
    assertEquals(QualifiedName.create("a", "", "b"), converter.toQualifiedName("a..b"));
    assertEquals(QualifiedName.create("", "", "a", "", ""), converter.toQualifiedName("..a.."));
    assertEquals(QualifiedName.create("", "", ""), converter.toQualifiedName(".."));
  }

  @Test
  public void testEmpty() {
    assertThrows(IllegalArgumentException.class, () -> converter.toQualifiedName(""));
    assertThrows(IllegalArgumentException.class, () -> converter.toQualifiedName(null));
    assertNull(QualifiedNames.safeQualifiedName(""));
    assertNull(QualifiedNames.safeQualifiedName(null));
  }

  @Test
  public void testFeatureNameFunction() {
    INameFunction nameFunction = NameFunctions.fromFeature(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Foo");
    assertEquals(QualifiedName.create("Foo"), nameFunction.apply(eClass));
    eClass.setName("foo.Bar");
    assertEquals(QualifiedName.create("foo", "Bar"), nameFunction.apply(eClass));

    assertEquals(QualifiedName.create("Foo"), nameFunction.apply(EObjectDescription.create(QualifiedName.create("x"), eClass, Map.of("name", "Foo"))));
    assertEquals(QualifiedName.create("foo", "Bar"), nameFunction.apply(EObjectDescription.create(QualifiedName.create("x"), eClass, Map.of("name", "foo.Bar"))));
  }
}
