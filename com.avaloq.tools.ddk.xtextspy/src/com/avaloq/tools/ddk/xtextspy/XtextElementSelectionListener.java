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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Listens to Xtext element selections and notifies listeners.
 * Provides information about the element corresponding to the current selection:
 * - grammar rule
 * - semantic element URI
 * - grammar element
 * - type/EClass of semantic element
 */
@Singleton
public class XtextElementSelectionListener implements ISelectionListener, ISelectionProvider {

  private XtextEditor editor;
  private ISelection selection;
  @Inject
  private IEditorUtils editorUtils;

  private final ListenerList<ISelectionChangedListener> listenerList = new ListenerList<>();
  private final ISelection emptySelection = new ISelection() {
    @Override
    public boolean isEmpty() {
      return true;
    }
  };

  /** {@inheritDoc} */
  @Override
  public void selectionChanged(final IWorkbenchPart part, final ISelection sel) {
    editor = editorUtils.getActiveXtextEditor();
    selection = sel;
    fireSelectionChanged();
  }

  /**
   * Returns the ILeafNode at the current selection if it is a ITextSelection, null otherwise.
   *
   * @return ILeafNode or null
   */
  private ILeafNode nodeAtTextSelection() {
    if (editor != null && selection instanceof ITextSelection) {
      return editor.getDocument().readOnly(new IUnitOfWork<ILeafNode, XtextResource>() {
        @Override
        @SuppressWarnings({"PMD.SignatureDeclareThrowsException"})
        public ILeafNode exec(final XtextResource resource) throws Exception {
          return NodeModelUtils.findLeafNodeAtOffset(NodeModelUtils.getNode(resource.getContents().get(0)), ((ITextSelection) selection).getOffset());
        }
      });
    }
    return null;
  }

  /**
   * Notify all ISelectionChangedListener.
   */
  private void fireSelectionChanged() {
    SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    for (Object listener : listenerList.getListeners()) {
      ((ISelectionChangedListener) listener).selectionChanged(event);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void addSelectionChangedListener(final ISelectionChangedListener listener) {
    listenerList.add(listener);
  }

  /** {@inheritDoc} */
  @Override
  public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
    listenerList.remove(listener);
  }

  /** {@inheritDoc} */
  @Override
  public void setSelection(final ISelection selection) {
    throw new UnsupportedOperationException();
  }

  /** {@inheritDoc} */
  @Override
  public ISelection getSelection() {
    if (selection != null) {
      return selection;
    }
    return emptySelection;
  }

  /**
   * Gets the parser rule at current selection.
   *
   * @return AbstractRule or null if no selection or no active XtextEditor
   */
  public AbstractRule getRule() {
    INode node = nodeAtTextSelection();
    if (node != null && node.getGrammarElement() != null) {
      return GrammarUtil.containingRule(node.getGrammarElement());
    }
    return null;
  }

  /**
   * Gets the XtextEditor corresponding to the current selection.
   *
   * @return the editor or null
   */
  public XtextEditor getEditor() {
    return editor;
  }

  /**
   * Gets the offset of the current selection or null.
   *
   * @return Integer or null
   */
  @SuppressWarnings("PMD.NullAssignment")
  public Integer getOffset() {
    return selection instanceof ITextSelection ? ((ITextSelection) selection).getOffset() : null;
  }

  /**
   * Gets the GrammarElement at the current selection.
   *
   * @return GrammarElement or null if no text selection
   */
  public EObject getNodeGrammarElement() {
    ILeafNode node = nodeAtTextSelection();
    return node == null ? null : node.getGrammarElement();
  }

  /**
   * Gets the EClass of the semantic element currently selected.
   *
   * @return EClass or null
   */
  public EClass getSelectedElementType() {
    if (selection instanceof IStructuredSelection) {
      if (((IStructuredSelection) selection).getFirstElement() instanceof EObject) {
        // structured selection, e.g. GMFEditor
        EObject eObject = (EObject) ((IStructuredSelection) selection).getFirstElement();
        if (eObject.eResource() != null) {
          return eObject.eClass();
        }
      } else if (((IStructuredSelection) selection).getFirstElement() instanceof EObjectNode) {
        // selection in outline
        return ((EObjectNode) ((IStructuredSelection) selection).getFirstElement()).getEClass();
      }
    } else {
      ILeafNode node = nodeAtTextSelection();
      EObject semanticObject = NodeModelUtils.findActualSemanticObjectFor(node);
      if (semanticObject != null) {
        return semanticObject.eClass();
      }
    }
    return null;
  }

  /**
   * Gets the URI of the semantic element currently selected.
   *
   * @return URI or null
   */
  public URI getSelectedElementUri() {
    if (selection instanceof IStructuredSelection) {
      if (((IStructuredSelection) selection).getFirstElement() instanceof InternalEObject) {
        // structured selection, e.g. GMFEditor
        return EcoreUtil.getURI((EObject) ((IStructuredSelection) selection).getFirstElement());
      } else if (((IStructuredSelection) selection).getFirstElement() instanceof EObjectNode) {
        // selection in outline
        return ((EObjectNode) ((IStructuredSelection) selection).getFirstElement()).getEObjectURI();
      }
    } else {
      ILeafNode node = nodeAtTextSelection();
      EObject semanticObject = NodeModelUtils.findActualSemanticObjectFor(node);
      if (semanticObject != null) {
        return EcoreUtil.getURI(semanticObject);
      }
    }
    return null;
  }
}
