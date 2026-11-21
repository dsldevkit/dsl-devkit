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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.RulerBreakpointAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

import com.avaloq.tools.ddk.xtext.ui.Messages;


/**
 * Enables or disables the breakpoint on the ruler line.
 */
final class EnableDisableBreakpointRulerAction extends RulerBreakpointAction implements IUpdate {

  EnableDisableBreakpointRulerAction(final ITextEditor editor, final IVerticalRulerInfo rulerInfo) {
    super(editor, rulerInfo);
  }

  @Override
  public void run() {
    IBreakpoint breakpoint = getBreakpoint();
    if (breakpoint != null) {
      try {
        breakpoint.setEnabled(!breakpoint.isEnabled());
      } catch (CoreException e) {
        StatusManager.getManager().handle(e.getStatus(), StatusManager.SHOW | StatusManager.LOG);
      }
    }
  }

  @Override
  public void update() {
    IBreakpoint breakpoint = getBreakpoint();
    setEnabled(breakpoint != null);
    setText(isDisabled(breakpoint) ? Messages.BreakpointRulerMenu_ENABLE : Messages.BreakpointRulerMenu_DISABLE);
  }

  private boolean isDisabled(final IBreakpoint breakpoint) {
    try {
      return breakpoint != null && !breakpoint.isEnabled();
    } catch (CoreException e) {
      StatusManager.getManager().handle(e.getStatus(), StatusManager.LOG);
      return false;
    }
  }
}
