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

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.actions.RulerBreakpointAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.dialogs.PropertyDialogAction;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.IUpdate;

import com.avaloq.tools.ddk.xtext.ui.Messages;


/**
 * Opens the properties dialog of the breakpoint on the ruler line.
 */
final class BreakpointPropertiesRulerAction extends RulerBreakpointAction implements IUpdate {

  BreakpointPropertiesRulerAction(final ITextEditor editor, final IVerticalRulerInfo rulerInfo) {
    super(editor, rulerInfo);
    setText(Messages.BreakpointRulerMenu_PROPERTIES);
  }

  @Override
  public void run() {
    IBreakpoint breakpoint = getBreakpoint();
    if (breakpoint != null) {
      new PropertyDialogAction(getEditor().getEditorSite(), new FixedSelectionProvider(new StructuredSelection(breakpoint))).run();
    }
  }

  @Override
  public void update() {
    setEnabled(getBreakpoint() != null);
  }

  /**
   * Provides a selection that never changes.
   */
  private static final class FixedSelectionProvider implements ISelectionProvider {
    private final ISelection selection;

    FixedSelectionProvider(final ISelection selection) {
      this.selection = selection;
    }

    @Override
    public ISelection getSelection() {
      return selection;
    }

    @Override
    public void setSelection(final ISelection newSelection) {
      // The selection is fixed.
    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
      // The selection never changes.
    }

    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
      // The selection never changes.
    }
  }
}
