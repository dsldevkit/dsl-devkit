/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.test.ui.swtbot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;


/**
 * Utility providing access to tree items.
 */
public final class TreeItemUiTestUtil {

  /**
   * Tests if a tree contains items.
   *
   * @param bot
   *          to work with
   * @param tree
   *          to look for items in
   */
  public static void waitForTreeItem(final SWTWorkbenchBot bot, final SWTBotTree tree) {
    bot.waitUntilWidgetAppears(new WaitForTreeItem(tree.widget));
  }

  /**
   * Returns items from a tree.
   *
   * @param bot
   *          to work with
   * @param tree
   *          to look for items in
   * @return list of tree items
   */
  public static List<SWTBotTreeItem> treeItems(final SWTWorkbenchBot bot, final SWTBotTree tree) {
    waitForTreeItem(bot, tree);
    return new ArrayList<SWTBotTreeItem>(Arrays.asList(tree.getAllItems()));
  }

  /**
   * A condition checking if a {@link TreeItem} is visible.
   */
  private static class WaitForTreeItem extends WaitForObjectCondition<TreeItem> {
    private final Tree parent;

    WaitForTreeItem(final Tree parent) {
      super(WidgetMatcherFactory.widgetOfType(TreeItem.class));
      this.parent = parent;
    }

    @Override
    public String getFailureMessage() {
      return "Could not find tree item matching: " + this.matcher;
    }

    @Override
    protected List<TreeItem> findMatches() {
      return UIThreadRunnable.syncExec(new ListResult<TreeItem>() {
        @Override
        public List<TreeItem> run() {
          return new ArrayList<TreeItem>(Arrays.asList(parent.getItems()));
        }
      });
    }
  }

  private TreeItemUiTestUtil() {}
}

