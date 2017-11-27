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
package com.avaloq.tools.ddk.xtext.test.ui.outline;

import static org.junit.Assert.assertNotNull;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotViewUtil;
import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextUiTest;


/**
 * An abstract base class to test the outline view on the UI level.
 */
public abstract class AbstractOutlineViewTest extends AbstractXtextUiTest {

  /**
   * Expands the given {@link SWTBotTreeItem} if it is not expanded already.
   *
   * @param treeItem
   *          the tree item to be expanded
   */
  protected void expandIfNotExpanded(final SWTBotTreeItem treeItem) {
    if (!treeItem.isExpanded()) {
      treeItem.expand();
    }
  }

  /**
   * Opens the outline view of the currently active editor.
   *
   * @return the {@link SWTBotView} representing the outline view
   * @throw WidgetNotFoundException if there is no active editor
   */
  // CHECKSTYLE:CONSTANTS-OFF
  protected SWTBotView openOutlineViewOfActiveEditor() {
    getBot().menu("Window").menu("Show View").menu("Outline").click();
    SWTBotView outlineView = getBot().viewById("org.eclipse.ui.views.ContentOutline");
    assertNotNull("outline view present", outlineView);

    SwtBotViewUtil.waitUntilViewIsLoaded(outlineView);
    outlineView.setFocus();

    return outlineView;
  }
  // CHECKSTYLE:CONSTANTS-ON

}
