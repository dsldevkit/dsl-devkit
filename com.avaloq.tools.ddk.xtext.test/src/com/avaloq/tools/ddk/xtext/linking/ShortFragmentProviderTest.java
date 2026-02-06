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
package com.avaloq.tools.ddk.xtext.linking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.xtext.test.AbstractTestUtil;


/**
 * Tests for {@code AbstractFragmentProvider}.
 */
@SuppressWarnings("nls")
@TestInstance(Lifecycle.PER_CLASS)
class ShortFragmentProviderTest {

  private static final String FRAGMENT_MUST_BE_EQUAL = "Fragment must be equal";
  private static AbstractTestUtil testUtil = new AbstractTestUtil() {
  };

  private static final class TestFallbackFragmentProvider implements IFragmentProvider.Fallback {

    @Override
    public String getFragment(final EObject obj) {
      return null;
    }

    @Override
    public EObject getEObject(final String fragment) {
      return null;
    }
  }

  private final ShortFragmentProvider fragmentProvider = new ShortFragmentProvider();
  private final TestFallbackFragmentProvider fragmentFallback = new TestFallbackFragmentProvider();

  private EPackage testPackage;
  private EClass testClass;
  private EReference testReference;
  private EReference testReference2;

  @BeforeEach
  void initialize() {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

    testClass = ecoreFactory.createEClass();

    testReference = ecoreFactory.createEReference();
    testReference.setEType(testClass);
    testReference.setContainment(true);
    testClass.getEStructuralFeatures().add(testReference);

    testReference2 = ecoreFactory.createEReference();
    testReference2.setEType(testClass);
    testReference2.setContainment(true);
    testClass.getEStructuralFeatures().add(testReference2);

    testPackage = ecoreFactory.createEPackage();
    testPackage.getEClassifiers().add(testClass);
    testPackage.setNsURI("http:///com.avaloq.tools.ddk.xtext.test");
    EPackage.Registry.INSTANCE.put(testPackage.getNsURI(), testPackage);
  }

  @AfterEach
  void cleanup() {
    EPackage.Registry.INSTANCE.remove(testPackage.getNsURI());
  }

  @BugTest(value = "DSL-601")
  void testLongFragment() {
    int reps = 100;
    EObject root = EcoreUtil.create(testClass);
    EObject parent = root;
    for (int i = 0; i < reps; i++) {
      EObject child = EcoreUtil.create(testClass);
      parent.eSet(testReference, child);
      parent = child;
    }

    ResourceImpl resource = new ResourceImpl();
    resource.getContents().add(root);

    String fragment = fragmentProvider.getFragment(parent, fragmentFallback);
    assertEquals("/0*" + (reps + 1), fragment, FRAGMENT_MUST_BE_EQUAL);

    assertEquals(parent, fragmentProvider.getEObject(resource, fragment, fragmentFallback), FRAGMENT_MUST_BE_EQUAL);
  }

  @Test
  void testLongFragment2() {
    int reps = 10;
    EObject root = EcoreUtil.create(testClass);
    EObject parent = root;
    for (int i = 0; i < reps; i++) {
      EObject child = EcoreUtil.create(testClass);
      parent.eSet(testReference, child);
      parent = child;
    }

    EObject child = EcoreUtil.create(testClass);
    parent.eSet(testReference2, child);
    parent = child;

    ResourceImpl resource = new ResourceImpl();
    resource.getContents().add(root);

    String fragment = fragmentProvider.getFragment(parent, fragmentFallback);
    assertEquals("/0*" + (reps + 1) + "/1", fragment, FRAGMENT_MUST_BE_EQUAL);

    assertEquals(parent, fragmentProvider.getEObject(resource, fragment, fragmentFallback), FRAGMENT_MUST_BE_EQUAL);
  }

  protected String getTestSourceFileName() {
    return null;
  }

  protected AbstractTestUtil getTestUtil() {
    return testUtil;
  }
}
