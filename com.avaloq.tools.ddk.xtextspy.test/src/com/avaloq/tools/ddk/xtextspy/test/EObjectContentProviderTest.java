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
package com.avaloq.tools.ddk.xtextspy.test;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.inject.util.Modules.override;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.xtextspy.internal.Activator;
import com.avaloq.tools.ddk.xtextspy.EObjectContentProvider;
import com.avaloq.tools.ddk.xtextspy.EObjectContentProvider.AttributeValuePair;
import com.avaloq.tools.ddk.xtextspy.XtextElementSelectionListener;
import com.avaloq.tools.ddk.xtextspy.XtextSpyModule;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.inject.AbstractModule;


/**
 * Testing EObjectContentProvider.
 */
public class EObjectContentProviderTest extends AbstractXtextTests {

  /**
   * Module binding XtextElementSelectionListener to a specific instance.
   */
  public static class TestModule extends AbstractModule {
    private final XtextElementSelectionListener selectionListener;

    public TestModule(final XtextElementSelectionListener selectionListener) {
      super();
      this.selectionListener = selectionListener;
    }

    @Override
    protected void configure() {
      bind(XtextElementSelectionListener.class).toInstance(selectionListener);
    }
  }

  @SuppressWarnings("PMD.SingularField")
  private XtextElementSelectionListener mockSelectionListener;
  private EObjectContentProvider contentProvider;

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    mockSelectionListener = mock(XtextElementSelectionListener.class);
    with(override(new XtextSpyModule(Activator.getDefault())).with(new TestModule(mockSelectionListener)));
    contentProvider = get(EObjectContentProvider.class);
  }

  @Test
  public void hasChildren() {
    assertFalse("No children expected", contentProvider.hasChildren(new Object()));
  }

  @Test
  public void getParent() {
    assertNull("No parent expected", contentProvider.getParent(new Object()));
  }

  /**
   * Mocks XtextEditor and XtextElementSelectionListener to return an element selection that has:
   * - given EAttribute with a specific value (AttributeValuePair)
   * - given EStructuralFeature
   * - given EOperation.
   *
   * @param attributeValuePair
   *          EAttribute with a specific value
   * @param feature
   *          the EStructuralFeature
   * @param operation
   *          the EOperation
   * @return the EClass of the "selected" element
   */
  @SuppressWarnings("unchecked")
  private EClass mockSelectedElement(final AttributeValuePair attributeValuePair, final EStructuralFeature feature, final EOperation operation) {
    EClass mockSelectionEClass = mock(EClass.class);
    when(mockSelectionListener.getSelectedElementType()).thenReturn(mockSelectionEClass);
    // Mockups for returning AttributeValuePair
    URI elementUri = URI.createURI("");
    when(mockSelectionListener.getSelectedElementUri()).thenReturn(elementUri);
    XtextEditor mockEditor = mock(XtextEditor.class);
    when(mockSelectionListener.getEditor()).thenReturn(mockEditor);
    IXtextDocument mockDocument = mock(IXtextDocument.class);
    when(mockEditor.getDocument()).thenReturn(mockDocument);
    when(mockDocument.<ArrayList<AttributeValuePair>> readOnly(any(IUnitOfWork.class))).thenReturn(newArrayList(attributeValuePair));
    // Mockups for returning EOperation
    BasicEList<EOperation> mockEOperationsList = new BasicEList<EOperation>();
    mockEOperationsList.add(operation);
    when(mockSelectionEClass.getEAllOperations()).thenReturn(mockEOperationsList);
    // Mockups for returning EStructuralFeature
    BasicEList<EStructuralFeature> mockEStructuralFeatureList = new BasicEList<EStructuralFeature>();
    mockEStructuralFeatureList.add(feature);
    mockEStructuralFeatureList.add(attributeValuePair.getAttribute());
    when(mockSelectionEClass.getEAllStructuralFeatures()).thenReturn(mockEStructuralFeatureList);
    return mockSelectionEClass;
  }

  /**
   * Verify that getElements()/getChildren() returns EAttributes, EStructuralFeatures and EOperations for
   * a selected element.
   */
  @Test
  public void getElementsAndChildren() {
    EClass mockInput = mock(EClass.class);
    EOperation mockOperation = mock(EOperation.class);
    EStructuralFeature mockFeature = mock(EStructuralFeature.class);
    EAttribute mockAttribute = mock(EAttribute.class);
    Object attributeValue = new Object();
    AttributeValuePair attributeValuePair = new AttributeValuePair(mockAttribute, attributeValue);
    EClass mockSelectionEClass = mockSelectedElement(attributeValuePair, mockFeature, mockOperation);
    Object[] elements;

    elements = contentProvider.getElements(null);
    assertEquals("No elements expected", 0, elements.length);

    when(mockInput.isSuperTypeOf(eq(mockSelectionEClass))).thenReturn(false);
    elements = contentProvider.getElements(mockInput);
    assertEquals("No elements expected for incompatible EClass", 0, elements.length);

    when(mockInput.isSuperTypeOf(eq(mockSelectionEClass))).thenReturn(true);
    elements = contentProvider.getElements(mockInput);
    assertEquals("Expected elements for compatible EClass", 3, elements.length);
    assertSame("Content provider did not include expected attribute", attributeValuePair, Iterables.find(newArrayList(elements), Predicates.instanceOf(AttributeValuePair.class)));
    assertSame("Content provider did not include expected feature", mockFeature, Iterables.find(newArrayList(elements), Predicates.instanceOf(EStructuralFeature.class)));
    assertSame("Content provider did not include expected operation", mockOperation, Iterables.find(newArrayList(elements), Predicates.instanceOf(EOperation.class)));
  }
}
