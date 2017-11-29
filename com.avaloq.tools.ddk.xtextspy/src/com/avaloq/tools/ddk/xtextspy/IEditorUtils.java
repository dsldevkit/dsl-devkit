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
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

import com.google.inject.ImplementedBy;


/**
 * Interface duplicating API of org.eclipse.xtext.ui.editor.utils.EditorUtils.
 * This is intended to allow for an implementation that will *not* invoke object.getAdapter(XtextEditor.class) as that plays
 * havoc with the current ASMD TableEditor and ASMD WfdEditor.
 */
@ImplementedBy(IEditorUtils.DefaultEditorUtils.class)
public interface IEditorUtils {

  /**
   * Gets the active XtextEditor.
   *
   * @return the active XtextEditor or null
   */
  XtextEditor getActiveXtextEditor();

  /**
   * Gets the XtextEditor associated with editor part.
   *
   * @param openEditor
   *          the editor part
   * @return the XtextEditor or null
   */
  XtextEditor getXtextEditor(IEditorPart openEditor);

  /**
   * Default implementation of IEditorUtils.
   * This is backed by {@link EditorUtils}.
   */
  class DefaultEditorUtils implements IEditorUtils {

    /** {@inheritDoc} */
    @Override
    public XtextEditor getActiveXtextEditor() {
      return EditorUtils.getActiveXtextEditor();
    }

    /** {@inheritDoc} */
    @Override
    public XtextEditor getXtextEditor(final IEditorPart openEditor) {
      return EditorUtils.getXtextEditor(openEditor);
    }

  }
}

