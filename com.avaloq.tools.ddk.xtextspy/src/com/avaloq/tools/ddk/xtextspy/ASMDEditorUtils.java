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

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.editor.XtextEditor;


/**
 * Implementation of IEditorUtils that will *not* invoke object.getAdapter(XtextEditor.class), as that plays
 * havoc with the current ASMD TableEditor and ASMD WfdEditor.
 */
public class ASMDEditorUtils implements IEditorUtils {

  /** {@inheritDoc} */
  public XtextEditor getActiveXtextEditor() {
    IWorkbench workbench = PlatformUI.getWorkbench();
    IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
    if (workbenchWindow == null) {
      return null;
    }
    IWorkbenchPage activePage = workbenchWindow.getActivePage();
    if (activePage == null) {
      return null;
    }
    IEditorPart activeEditor = activePage.getActiveEditor();
    if (activeEditor instanceof XtextEditor) {
      return (XtextEditor) activeEditor;
      // return null;
    }
    // XtextEditor xtextEditor = (XtextEditor) activeEditor.getAdapter(XtextEditor.class);
    // return xtextEditor;
    return null;
  }

  /** {@inheritDoc} */
  public XtextEditor getXtextEditor(final IEditorPart openEditor) {
    if (openEditor instanceof XtextEditor) {
      return (XtextEditor) openEditor;
    }
    return null;
  }

}

