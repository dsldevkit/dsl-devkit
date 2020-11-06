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

import static com.google.inject.util.Modules.override;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.xtextspy.IEditorUtils;
import com.avaloq.tools.ddk.xtextspy.XtextElementSelectionListener;
import com.avaloq.tools.ddk.xtextspy.XtextSpyModule;
import com.avaloq.tools.ddk.xtextspy.internal.Activator;
import com.google.inject.AbstractModule;


/**
 * Testing of XtextElementSelectionListener.
 */
public class XtextElementSelectionListenerTest extends AbstractXtextTests {

  /**
   * Module binding IEditorUtils to a specific instance.
   */
  public static class TestModule extends AbstractModule {
    private final IEditorUtils editorUtils;

    public TestModule(final IEditorUtils editorUtils) {
      super();
      this.editorUtils = editorUtils;
    }

    @Override
    protected void configure() {
      bind(IEditorUtils.class).toInstance(editorUtils);
    }
  }

  private IEditorUtils mockEditorUtils;
  private XtextElementSelectionListener selectionListener;

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    mockEditorUtils = mock(IEditorUtils.class);
    with(override(new XtextSpyModule(Activator.getDefault())).with(new TestModule(mockEditorUtils)));
    selectionListener = get(XtextElementSelectionListener.class);
  }

  /**
   * Mocks an XtextEditor and instruments mockEditorUtils to return that.
   *
   * @return the mocked active XtextEditor
   */
  private XtextEditor mockActiveEditor() {
    XtextEditor mockEditor = mock(XtextEditor.class);
    when(mockEditorUtils.getActiveXtextEditor()).thenReturn(mockEditor);
    return mockEditor;
  }

  /**
   * Mocks a text selection and causes the (mocked) XtextEditor to return an ILeafNode on any read(IUnitOfWork).
   *
   * @return the mocked ILeafNode
   */
  @SuppressWarnings("unchecked")
  private ILeafNode mockNodeAtSelection(final XtextEditor mockEditor) {
    IXtextDocument mockDocument = mock(IXtextDocument.class);
    ILeafNode mockNode = mock(ILeafNode.class);
    when(mockEditor.getDocument()).thenReturn(mockDocument);
    when(mockDocument.<ILeafNode> priorityReadOnly(any(IUnitOfWork.class))).thenReturn(mockNode);
    when(mockDocument.<ILeafNode> readOnly(any(IUnitOfWork.class))).thenReturn(mockNode);
    // when(mockDocument.readOnly(any(IUnitOfWork<ILeafNode, XtextResource>.class))).thenReturn(mockLeafNode);
    selectionListener.selectionChanged(null, mock(ITextSelection.class));
    return mockNode;
  }

  /**
   * Setting selection is not supported.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void setSelection() {
    selectionListener.setSelection(new TextSelection(0, 0));
  }

  /**
   * Verify that selection is updated.
   */
  @Test
  public void getSelection() {
    ISelection mockSelection = mock(TextSelection.class);
    assertTrue("Expected empty selection", selectionListener.getSelection().isEmpty());
    selectionListener.selectionChanged(null, mockSelection);
    assertSame("Expected selection", mockSelection, selectionListener.getSelection());
  }

  /**
   * Verify that SelectionChangedListener are notified.
   */
  @Test
  public void fireSelectionChanged() {
    ISelectionChangedListener mockListener = mock(ISelectionChangedListener.class);
    selectionListener.addSelectionChangedListener(mockListener);
    ISelection mockSelection = mock(TextSelection.class);
    selectionListener.selectionChanged(null, mockSelection);
    verify(mockListener).selectionChanged(any(SelectionChangedEvent.class));
  }

  /**
   * Verify that getRule() returns null when no text selection exists in an editor.
   */
  @Test
  public void getNoRule() {
    assertNull("No rule expected for no selection", selectionListener.getRule());
    ISelection mockSelection = mock(TextSelection.class);
    selectionListener.selectionChanged(null, mockSelection);
    assertNull("No rule expected for no editor", selectionListener.getRule());
    mockActiveEditor();
    mockSelection = mock(ISelection.class);
    selectionListener.selectionChanged(null, mockSelection);
    assertNull("No rule expected for non text selection", selectionListener.getRule());
  }

  /**
   * Verify that AbstractRule is returned on getRule() if TextSelection and XtextEditor are given.
   */
  @Test
  public void getRule() {
    ILeafNode mockNode = mockNodeAtSelection(mockActiveEditor());
    AbstractRule mockRule = mock(AbstractRule.class);
    when(mockNode.getGrammarElement()).thenReturn(mockRule);
    selectionListener.selectionChanged(null, mock(TextSelection.class));
    assertSame("Expected mocked AbstractRule", mockRule, selectionListener.getRule());
  }

  /**
   * Verify that editor is updated on selection changes.
   */
  @Test
  public void getEditor() {
    assertNull("No editor expected", selectionListener.getEditor());
    XtextEditor mockEditor = mockActiveEditor();
    selectionListener.selectionChanged(null, null);
    assertSame("Expected same editor", mockEditor, selectionListener.getEditor());
    mockActiveEditor();
    selectionListener.selectionChanged(null, null);
    assertNotSame("Did not expect same editor", mockEditor, selectionListener.getEditor());
  }

  /**
   * Verify that selected node's grammar element is returned.
   */
  @Test
  public void getNodeGrammarElement() {
    assertNull("No selection, thus no GE expected", selectionListener.getNodeGrammarElement());
    ILeafNode mockNode = mockNodeAtSelection(mockActiveEditor());
    selectionListener.getNodeGrammarElement();
    verify(mockNode).getGrammarElement();
  }

  /**
   * Verify that an EClass can be determined for a text selection.
   */
  @Test
  public void getSelectedElementTypeForTextSelection() {
    ILeafNode mockNode = mockNodeAtSelection(mockActiveEditor());
    when(mockNode.hasDirectSemanticElement()).thenReturn(true);
    EObject mockEObject = mock(EObject.class);
    when(mockNode.getSemanticElement()).thenReturn(mockEObject);
    selectionListener.getSelectedElementType();
    verify(mockEObject).eClass();
  }

  /**
   * Verify that an EClass can be determined for a structured selection, provided selected EObject has a Resource.
   */
  @Test
  public void getSelectedElementTypeForStructuredSelection() {
    IStructuredSelection mockSelection = mock(IStructuredSelection.class);
    EObject mockEObject = mock(EObject.class);
    when(mockSelection.getFirstElement()).thenReturn(mockEObject);
    selectionListener.selectionChanged(null, mockSelection);
    assertNull("Null expected as selected object has no eResource", selectionListener.getSelectedElementType());
    verify(mockEObject).eResource();
    Resource mockResource = mock(Resource.class);
    when(mockEObject.eResource()).thenReturn(mockResource);
    selectionListener.getSelectedElementType();
    verify(mockEObject).eClass();
  }

  /**
   * Verify that an URI can be determined for a text selection.
   */
  @Test
  public void getSelectedElementUriForTextSelection() {
    ILeafNode mockNode = mockNodeAtSelection(mockActiveEditor());
    when(mockNode.hasDirectSemanticElement()).thenReturn(true);
    InternalEObject mockEObject = mock(InternalEObject.class);
    when(mockNode.getSemanticElement()).thenReturn(mockEObject);
    when(mockEObject.eProxyURI()).thenReturn(URI.createURI("")); // this prevents NPE in EcoreUtil
    selectionListener.getSelectedElementUri();
    verify(mockEObject).eProxyURI();
  }
}
