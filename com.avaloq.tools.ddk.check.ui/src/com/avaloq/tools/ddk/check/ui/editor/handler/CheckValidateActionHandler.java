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
package com.avaloq.tools.ddk.check.ui.editor.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextReadonlyEditorInput;
import org.eclipse.xtext.ui.editor.handler.ValidateActionHandler;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;


/**
 * Disables the <em>Validate</em> context menu action in read-only Check editor.
 * <p>
 * To be deleted once https://bugs.eclipse.org/bugs/show_bug.cgi?id=365848 is fixed.
 */
public class CheckValidateActionHandler extends ValidateActionHandler {

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
    if (xtextEditor != null && xtextEditor.getEditorInput() instanceof XtextReadonlyEditorInput) {
      return null;
    }
    return super.execute(event);
  }

}

