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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the qualified names computed by {@link AbstractExportedNameProvider}.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class AbstractExportedNameProviderTest {

  private EPackage ePackage;
  private EClass eClass;
  private EAttribute eAttribute;
  private EClass annotatedClass;

  /**
   * Name provider qualifying named elements with their container names and counting the computations.
   */
  private static final class TestNameProvider extends AbstractExportedNameProvider {
    private int computations;

    @Override
    protected QualifiedName qualifiedName(final EObject obj) {
      computations++;
      if (obj instanceof ENamedElement named) {
        return qualifyWithContainerName(obj, named.getName());
      }
      return null;
    }
  }

  @BeforeEach
  public void setUp() {
    ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("pkg");
    eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Cls");
    ePackage.getEClassifiers().add(eClass);
    eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    eAttribute.setName("attr");
    eClass.getEStructuralFeatures().add(eAttribute);
    EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
    eClass.getEAnnotations().add(annotation);
    annotatedClass = EcoreFactory.eINSTANCE.createEClass();
    annotatedClass.setName("Inner");
    annotation.getContents().add(annotatedClass);
  }

  @Test
  public void testNestedQualifiedNames() {
    TestNameProvider provider = new TestNameProvider();
    assertEquals(QualifiedName.create("pkg"), provider.getFullyQualifiedName(ePackage));
    assertEquals(QualifiedName.create("pkg", "Cls"), provider.getFullyQualifiedName(eClass));
    assertEquals(QualifiedName.create("pkg", "Cls", "attr"), provider.getFullyQualifiedName(eAttribute));
    assertNull(provider.getFullyQualifiedName(eClass.getEAnnotations().get(0)));
  }

  @Test
  public void testUnnamedContainerIsSkipped() {
    TestNameProvider provider = new TestNameProvider();
    assertEquals(QualifiedName.create("pkg", "Cls"), provider.getContainerQualifiedName(annotatedClass));
    assertEquals(QualifiedName.create("pkg", "Cls", "Inner"), provider.getFullyQualifiedName(annotatedClass));
  }

  @Test
  public void testNamesAreComputedOnEachCall() {
    TestNameProvider provider = new TestNameProvider();
    assertEquals(QualifiedName.create("pkg"), provider.getFullyQualifiedName(ePackage));
    assertEquals(QualifiedName.create("pkg"), provider.getFullyQualifiedName(ePackage));
    assertEquals(2, provider.computations);
    assertEquals(QualifiedName.create("pkg", "Cls", "attr"), provider.getFullyQualifiedName(eAttribute));
    assertEquals(5, provider.computations);
  }

}
