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
package com.avaloq.tools.ddk.check.ui.navigation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextReadonlyEditorInput;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;
import org.eclipse.xtext.util.TextRegion;

import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.google.inject.Inject;


/**
 * Disables hyperlinking in read-only Check editor. Currently disabled because of lacking Xtext support and both complicated and cost ineffective implementation
 * required.
 */
public class CheckHyperlinkHelper extends TypeAwareHyperlinkHelper {

  @Inject(optional = true)
  private IWorkbench workbench;

  @Inject
  private EObjectAtOffsetHelper eObjectAtOffsetHelper;

  @Override
  public void createHyperlinksByOffset(final XtextResource resource, final int offset, final IHyperlinkAcceptor acceptor) {
    IEditorPart activeEditor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if (activeEditor.getEditorInput() instanceof XtextReadonlyEditorInput) {
      INode crossRefNode = eObjectAtOffsetHelper.getCrossReferenceNode(resource, new TextRegion(offset, 0));
      if (crossRefNode == null) {
        return;
      }
      EObject crossLinkedEObject = eObjectAtOffsetHelper.getCrossReferencedElement(crossRefNode);
      if (crossLinkedEObject != null && crossLinkedEObject.eClass().getEPackage() != CheckPackage.eINSTANCE) {
        return;
      }
      // if EPackage of referenced object is CheckPackage, try to provide hyperlinks: works for included catalogs
    }
    super.createHyperlinksByOffset(resource, offset, acceptor);
  }
}

