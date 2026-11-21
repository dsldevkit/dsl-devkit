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

package com.avaloq.tools.ddk.xtext.ui.editor.breakpoints;

import org.eclipse.debug.ui.actions.ToggleBreakpointAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;


/**
 * Installs the breakpoint actions of the vertical ruler on an editor of a language that supports breakpoints.
 * <p>
 * Double-clicking the ruler triggers the toggle action, and {@link BreakpointRulerMenu} shows all the actions in the ruler context menu.
 * </p>
 */
public class BreakpointRulerActionContributor implements IActionContributor {

  /** The editor action ID of the action enabling or disabling the breakpoint on the ruler line. */
  public static final String ENABLE_DISABLE_ACTION_ID = "com.avaloq.tools.ddk.xtext.ui.editor.breakpoints.EnableDisable"; //$NON-NLS-1$

  /** The editor action ID of the action opening the properties of the breakpoint on the ruler line. */
  public static final String PROPERTIES_ACTION_ID = "com.avaloq.tools.ddk.xtext.ui.editor.breakpoints.Properties"; //$NON-NLS-1$

  private ToggleBreakpointAction toggleAction;

  @Override
  public void contributeActions(final XtextEditor editor) {
    IVerticalRulerInfo rulerInfo = editor.getAdapter(IVerticalRulerInfo.class);
    toggleAction = new ToggleBreakpointAction(editor, null, rulerInfo);
    editor.setAction(ITextEditorActionConstants.RULER_DOUBLE_CLICK, toggleAction);
    editor.setAction(ENABLE_DISABLE_ACTION_ID, new EnableDisableBreakpointRulerAction(editor, rulerInfo));
    editor.setAction(PROPERTIES_ACTION_ID, new BreakpointPropertiesRulerAction(editor, rulerInfo));
  }

  @Override
  public void editorDisposed(final XtextEditor editor) {
    if (toggleAction != null) {
      toggleAction.dispose();
      toggleAction = null;
    }
  }
}
