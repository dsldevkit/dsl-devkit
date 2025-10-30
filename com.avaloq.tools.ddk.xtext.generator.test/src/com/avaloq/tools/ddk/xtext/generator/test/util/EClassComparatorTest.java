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
package com.avaloq.tools.ddk.xtext.generator.test.util;

import static org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS;
import static org.eclipse.emf.ecore.EcorePackage.Literals.ECLASSIFIER;
import static org.eclipse.emf.ecore.EcorePackage.Literals.EDATA_TYPE;
import static org.eclipse.emf.ecore.EcorePackage.Literals.EOBJECT;
import static org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.XtextPackage;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;


@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
class EClassComparatorTest {

  private final Function<EClass, EClass> mapping = Functions.<EClass> identity();

  @Test
  void testSorting() {
    List<EClass> sorted = EClassComparator.sortedGroups(Lists.newArrayList(ECLASS, EDATA_TYPE, EPACKAGE, ECLASSIFIER), mapping);
    assertEquals(Lists.newArrayList(ECLASS, EDATA_TYPE, EPACKAGE, ECLASSIFIER), sorted);

    sorted = EClassComparator.sortedGroups(Lists.newArrayList(ECLASS, EDATA_TYPE, ECLASSIFIER, EPACKAGE), mapping);
    assertEquals(Lists.newArrayList(ECLASS, EDATA_TYPE, EPACKAGE, ECLASSIFIER), sorted);

    sorted = EClassComparator.sortedGroups(Lists.newArrayList(ECLASSIFIER, ECLASS, EDATA_TYPE, EPACKAGE), mapping);
    assertEquals(Lists.newArrayList(ECLASS, EDATA_TYPE, EPACKAGE, ECLASSIFIER), sorted);

    sorted = EClassComparator.sortedGroups(Lists.newArrayList(ECLASSIFIER, EPACKAGE, ECLASS), mapping);
    assertEquals(Lists.newArrayList(EPACKAGE, ECLASS, ECLASSIFIER), sorted);
  }

  @Test
  void testSortingWithEObject() {
    List<EClass> sorted = EClassComparator.sortedGroups(Lists.newArrayList(EOBJECT, ECLASS), mapping);
    assertEquals(Lists.newArrayList(ECLASS, EOBJECT), sorted);

    sorted = EClassComparator.sortedGroups(Lists.newArrayList(ECLASS, EOBJECT), mapping);
    assertEquals(Lists.newArrayList(ECLASS, EOBJECT), sorted);
  }

  @Test
  void testSortingByEPackage() {
    ListMultimap<EPackage, EClass> sorted = EClassComparator.sortedEPackageGroups(Lists.newArrayList(EOBJECT, ECLASS), mapping);
    assertEquals(2, sorted.size());
    assertEquals(1, sorted.keySet().size());
    assertEquals(Lists.newArrayList(ECLASS, EOBJECT), sorted.get(EcorePackage.eINSTANCE));

    sorted = EClassComparator.sortedEPackageGroups(Lists.newArrayList(XtextPackage.Literals.ABSTRACT_ELEMENT, XtextPackage.Literals.ACTION, ECLASS), mapping);
    assertEquals(3, sorted.size());
    assertEquals(2, sorted.keySet().size());
    assertEquals(Lists.newArrayList(ECLASS), sorted.get(EcorePackage.eINSTANCE)); // NOPMD ConfusingArgumentToVarargsMethod
    assertEquals(Lists.newArrayList(XtextPackage.Literals.ACTION, XtextPackage.Literals.ABSTRACT_ELEMENT), sorted.get(XtextPackage.eINSTANCE));
  }

}
