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
package com.avaloq.tools.ddk.xtext.linking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the parsing of the numeric parts of URI fragments by {@link AbstractFragmentProvider} and {@link ShortFragmentProvider}.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class FragmentIndexParsingTest {

  private static final int CHILDREN = 12;
  private static final int DEPTH = 12;

  private final ShortFragmentProvider fragmentProvider = new ShortFragmentProvider();
  private final EObject fallbackObject = EcoreFactory.eINSTANCE.createEObject();
  private final IFragmentProvider.Fallback fallback = new IFragmentProvider.Fallback() {
    @Override
    public String getFragment(final EObject obj) {
      return null;
    }

    @Override
    public EObject getEObject(final String fragment) {
      return fallbackObject;
    }
  };

  private EPackage testPackage;
  private EClass testClass;
  private EReference singleReference;
  private EReference manyReference;

  @BeforeEach
  public void setUp() {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
    testClass = ecoreFactory.createEClass();
    singleReference = ecoreFactory.createEReference();
    singleReference.setEType(testClass);
    singleReference.setContainment(true);
    testClass.getEStructuralFeatures().add(singleReference);
    manyReference = ecoreFactory.createEReference();
    manyReference.setEType(testClass);
    manyReference.setContainment(true);
    manyReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    testClass.getEStructuralFeatures().add(manyReference);
    testPackage = ecoreFactory.createEPackage();
    testPackage.getEClassifiers().add(testClass);
    testPackage.setNsURI("http:///com.avaloq.tools.ddk.xtext.test.fragmentindex");
    EPackage.Registry.INSTANCE.put(testPackage.getNsURI(), testPackage);
  }

  @AfterEach
  public void tearDown() {
    EPackage.Registry.INSTANCE.remove(testPackage.getNsURI());
  }

  @Test
  public void testParseUnsignedIntRange() {
    assertEquals(0, IndexParser.parse("0", 0, 1));
    assertEquals(12, IndexParser.parse("12#345", 0, 2));
    assertEquals(345, IndexParser.parse("12#345", 3, 6));
    assertEquals(1234, IndexParser.parse("/0*1234/5", 3, 7));
    assertEquals(Integer.MAX_VALUE, IndexParser.parse("x2147483647", 1, 11));
  }

  @Test
  public void testParseUnsignedIntMalformed() {
    assertThrows(NumberFormatException.class, () -> IndexParser.parse("12#", 3, 3));
    assertThrows(NumberFormatException.class, () -> IndexParser.parse("1x2", 0, 3));
    assertThrows(NumberFormatException.class, () -> IndexParser.parse("#-1", 1, 3));
    assertThrows(IndexOutOfBoundsException.class, () -> IndexParser.parse("12", 0, -1));
    assertThrows(IndexOutOfBoundsException.class, () -> IndexParser.parse("12", 3, 2));
  }

  @Test
  public void testMultiDigitIndicesRoundTrip() {
    EObject root = EcoreUtil.create(testClass);
    @SuppressWarnings("unchecked")
    List<EObject> children = (List<EObject>) root.eGet(manyReference);
    for (int i = 0; i < CHILDREN; i++) {
      children.add(EcoreUtil.create(testClass));
    }
    EObject leaf = children.get(CHILDREN - 1);
    for (int i = 0; i < DEPTH; i++) {
      EObject child = EcoreUtil.create(testClass);
      leaf.eSet(singleReference, child);
      leaf = child;
    }
    ResourceImpl resource = new ResourceImpl();
    resource.getContents().add(root);

    String fragment = fragmentProvider.getFragment(leaf, fallback);
    assertEquals("/0/1#11/0*12", fragment);
    assertSame(leaf, fragmentProvider.getEObject(resource, fragment, fallback));
    assertSame(children.get(0), fragmentProvider.getEObject(resource, "/0/1#0", fallback));
    assertSame(children.get(10), fragmentProvider.getEObject(resource, "/0/1#10", fallback));
  }

  @Test
  public void testMalformedIndices() {
    EObject root = EcoreUtil.create(testClass);
    ResourceImpl resource = new ResourceImpl();
    resource.getContents().add(root);

    assertNull(fragmentProvider.getEObject(resource, "/0/1#99", fallback));
    assertSame(fallbackObject, fragmentProvider.getEObject(resource, "/0/1#x", fallback));
    assertSame(fallbackObject, fragmentProvider.getEObject(resource, "/0/1#", fallback));
    assertSame(fallbackObject, fragmentProvider.getEObject(resource, "/0/x#0", fallback));
    assertSame(fallbackObject, fragmentProvider.getEObject(resource, "/0/0*x", fallback));
  }

  /**
   * Fragment provider exposing {@link AbstractFragmentProvider#parseUnsignedInt(CharSequence, int, int)} to the test.
   */
  private static final class IndexParser extends ShortFragmentProvider {
    static int parse(final CharSequence text, final int beginIndex, final int endIndex) {
      return parseUnsignedInt(text, beginIndex, endIndex);
    }
  }

}
