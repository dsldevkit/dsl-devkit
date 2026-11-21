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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;


/**
 * Shows the actions installed by {@link BreakpointRulerActionContributor} in the ruler context menu, updated each time the menu opens.
 */
public class BreakpointRulerMenu extends CompoundContributionItem {

  private static final String[] ACTION_IDS = {ITextEditorActionConstants.RULER_DOUBLE_CLICK, BreakpointRulerActionContributor.ENABLE_DISABLE_ACTION_ID,
    BreakpointRulerActionContributor.PROPERTIES_ACTION_ID};

  @Override
  protected IContributionItem[] getContributionItems() {
    List<IContributionItem> items = new ArrayList<>(ACTION_IDS.length);
    ITextEditor editor = getActiveTextEditor();
    if (editor != null) {
      for (String actionId : ACTION_IDS) {
        IAction action = editor.getAction(actionId);
        if (action != null) {
          if (action instanceof IUpdate) {
            ((IUpdate) action).update();
          }
          items.add(new ActionContributionItem(action));
        }
      }
    }
    return items.toArray(new IContributionItem[0]);
  }

  private ITextEditor getActiveTextEditor() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IWorkbenchPage page = window != null ? window.getActivePage() : null;
    IEditorPart editor = page != null ? page.getActiveEditor() : null;
    return editor instanceof ITextEditor ? (ITextEditor) editor : null;
  }
}
