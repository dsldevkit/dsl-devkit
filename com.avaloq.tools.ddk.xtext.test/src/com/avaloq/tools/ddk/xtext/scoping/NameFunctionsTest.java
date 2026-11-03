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
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;


/**
 * Tests the name functions created by {@link NameFunctions#fromFeatures(org.eclipse.emf.ecore.EStructuralFeature...)}.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class NameFunctionsTest {

  @Test
  public void testFromFeaturesReturnsSameFunctionsOnEachIteration() {
    Iterable<INameFunction> functions = NameFunctions.fromFeatures(EcorePackage.Literals.ENAMED_ELEMENT__NAME, EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME);
    List<INameFunction> first = ImmutableList.copyOf(functions);
    List<INameFunction> second = ImmutableList.copyOf(functions);
    assertEquals(2, first.size());
    assertSame(first.get(0), second.get(0));
    assertSame(first.get(1), second.get(1));
  }

  @Test
  public void testFromFeaturesComputesFeatureNames() {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Foo");
    eClass.setInstanceClassName("a.b.Foo");
    List<INameFunction> functions = ImmutableList.copyOf(NameFunctions.fromFeatures(EcorePackage.Literals.ENAMED_ELEMENT__NAME, EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME));
    assertEquals(QualifiedName.create("Foo"), functions.get(0).apply(eClass));
    assertEquals(QualifiedName.create("a", "b", "Foo"), functions.get(1).apply(eClass));
    assertEquals("NameFunction(Feature:" + EcorePackage.Literals.ENAMED_ELEMENT__NAME + ')', functions.get(0).toString());
  }

}
