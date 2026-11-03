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
package com.avaloq.tools.ddk.xtext.scoping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;


/**
 * Tests {@link ContainerQuery.Builder}.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class ContainerQueryBuilderTest {

  private static final EClass TYPE = EcorePackage.Literals.ECLASS;

  private final IDomain.Mapper domainMapper = new IDomain.NullMapper();

  @Test
  public void testNameParsesQualifiedNames() {
    assertEquals(QualifiedName.create("a", "b"), ContainerQuery.newBuilder(domainMapper, TYPE).name("a.b").getNamePattern());
    QualifiedName pattern = ContainerQuery.newBuilder(domainMapper, TYPE).name("a.b*").getNamePattern();
    assertInstanceOf(QualifiedNamePattern.class, pattern);
    assertEquals(QualifiedNamePattern.create(QualifiedName.create("a", "b*")), pattern);
    assertNull(ContainerQuery.newBuilder(domainMapper, TYPE).name("").getNamePattern());
  }

  @Test
  public void testCopyIsEqualAndIndependent() {
    ContainerQuery.Builder original = ContainerQuery.newBuilder(domainMapper, TYPE).name("a.b").data("key", "value").domains("d1", "d2");
    ContainerQuery.Builder copy = original.copy();

    assertSame(domainMapper, copy.getDomainMapper());
    assertSame(TYPE, copy.getType());
    assertEquals(original.getNamePattern(), copy.getNamePattern());
    assertEquals(Map.of("key", "value"), copy.getUserData());
    assertNotSame(original.getUserData(), copy.getUserData());
    assertEquals(List.of("d1", "d2"), copy.getDomains());
    assertNotSame(original.getDomains(), copy.getDomains());

    copy.data("other", "value").getDomains().add("d3");
    assertEquals(Map.of("key", "value"), original.getUserData());
    assertEquals(List.of("d1", "d2"), original.getDomains());
  }

  @Test
  public void testCopyWithoutUserDataOrDomains() {
    ContainerQuery.Builder copy = ContainerQuery.newBuilder(domainMapper, TYPE).copy();
    assertSame(TYPE, copy.getType());
    assertNull(copy.getNamePattern());
    assertNull(copy.getUserData());
    assertTrue(copy.getDomains().isEmpty());
  }

}
