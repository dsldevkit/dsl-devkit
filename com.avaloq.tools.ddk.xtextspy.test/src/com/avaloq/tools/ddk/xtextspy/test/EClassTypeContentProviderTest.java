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
package com.avaloq.tools.ddk.xtextspy.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.xtextspy.EClassNode;
import com.avaloq.tools.ddk.xtextspy.EClassTypeContentProvider;
import com.avaloq.tools.ddk.xtextspy.XtextSpyModule;
import com.avaloq.tools.ddk.xtextspy.internal.Activator;
import com.google.common.collect.Lists;


/**
 * Testing EClassTypeContentProvider.
 */
@SuppressWarnings({"deprecation", "removal"})
public class EClassTypeContentProviderTest extends AbstractXtextTests {

  private static final String NO_CHILDREN_EXPECTED = "No children expected";

  private EClassTypeContentProvider contentProvider;

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    with(new XtextSpyModule(Activator.getDefault()));
    contentProvider = get(EClassTypeContentProvider.class);
  }

  /**
   * Verify that hasChildren() delegates to {@link EClassNode}.
   */
  @Test
  public void hasChildren() {
    assertFalse(NO_CHILDREN_EXPECTED, contentProvider.hasChildren(null));
    assertFalse(NO_CHILDREN_EXPECTED, contentProvider.hasChildren(new Object()));
    EClassNode mockNode = mock(EClassNode.class);
    contentProvider.hasChildren(mockNode);
    verify(mockNode).hasChildren();
  }

  /**
   * Verify that getChildren() delegates to {@link EClassNode}.
   */
  @Test
  public void getChildren() {
    assertEquals(NO_CHILDREN_EXPECTED, 0, contentProvider.getChildren(null).length);
    assertEquals(NO_CHILDREN_EXPECTED, 0, contentProvider.getChildren(new Object()).length);
    EClassNode mockNode = mock(EClassNode.class);
    when(mockNode.getChildren()).thenReturn(Lists.<EClassNode> newArrayList());
    contentProvider.getChildren(mockNode);
    verify(mockNode).getChildren();
  }

  /**
   * Verify that getParent() delegates to {@link EClassNode}.
   */
  @Test
  public void getParent() {
    assertNull("No parent expected for null", contentProvider.getParent(null));
    assertNull("No parent expected for Object", contentProvider.getParent(new Object()));
    EClassNode mockNode = mock(EClassNode.class);
    contentProvider.getParent(mockNode);
    verify(mockNode).getParent();
  }

  /**
   * Verify that getElements() instantiates {@link EClassNode}.
   */
  @Test
  public void getElements() {
    assertEquals("Empty list expected for null", 0, contentProvider.getElements(null).length);
    assertEquals("Empty list expected for Object", 0, contentProvider.getElements(new Object()).length);

    EClass mockEClass = mock(EClass.class);
    Object[] elements = contentProvider.getElements(mockEClass);
    assertEquals("Elements expected for EClass", 1, elements.length);
    assertEquals("EClassNode expected for EClass", mockEClass, ((EClassNode) elements[0]).getEClass());

    EObject mockEObject = mock(EObject.class);
    when(mockEObject.eClass()).thenReturn(mockEClass);
    elements = contentProvider.getElements(mockEObject);
    assertEquals("Elements expected for EObject", 1, elements.length);
    assertEquals("EClassNode expected for EObject", mockEClass, ((EClassNode) elements[0]).getEClass());
  }

}
