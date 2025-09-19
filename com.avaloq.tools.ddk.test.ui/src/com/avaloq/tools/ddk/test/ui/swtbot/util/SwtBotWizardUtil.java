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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;

import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;


/**
 * Utility class for selection methods in an eclipse widget for SWTBot testing.
 */
@SuppressWarnings("nls")
public final class SwtBotWizardUtil {

  private SwtBotWizardUtil() {
    // prevent class from being instantiated.
  }

  /**
   * Select a folder in a project.
   *
   * @param bot
   *          the bot
   * @param folderName
   *          the folder name
   */
  public static void selectProjectFolder(final SwtWorkbenchBot bot, final String folderName) {
    // try {
    // bot.viewById("org.eclipse.jdt.ui.PackageExplorer");
    // } catch (WidgetNotFoundException e) {
    // // If view not found, open it via menu
    // bot.menu("Window").menu("Show View").menu("Package Explorer").click();
    // }
    // SWTBotView packageExplorer = bot.viewById("org.eclipse.jdt.ui.PackageExplorer");
    SWTBotView packageExplorer = bot.viewByTitle("Project Explorer");
    packageExplorer.show();
    Composite comp = (Composite) packageExplorer.getWidget();
    final Tree tree = bot.widget(WidgetMatcherFactory.widgetOfType(Tree.class), comp);
    PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
      SWTBotTree botTree = new SWTBotTree(tree);
      Assert.assertTrue("folder was not found", selectItem(botTree, folderName));
    });
  }

  /**
   * Select a TreeItem with the given name in a tree.
   *
   * @param tree
   *          the tree in which the item is searched
   * @param name
   *          the name of the required item
   * @return true, if the item was selected
   */
  public static boolean selectItem(final SWTBotTree tree, final String name) {
    SWTBotTreeItem[] items = tree.getAllItems();
    for (SWTBotTreeItem item : items) {
      if (selectTreeItem(item, name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Select a tree item.
   *
   * @param treeItem
   *          the tree node
   * @param name
   *          the name of the item to look for
   * @return true, if item was found and selected
   */
  private static boolean selectTreeItem(final SWTBotTreeItem treeItem, final String name) {
    if (name.equals(treeItem.getText())) {
      treeItem.select();
      return true;
    }
    if (!treeItem.isExpanded()) {
      treeItem.expand();
    }
    for (SWTBotTreeItem item : treeItem.getItems()) {
      if (selectTreeItem(item, name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Is the button with the name enabled.
   *
   * @param bot
   *          the swt wizard bot, must not be {@code null}
   * @param buttonName
   *          the button to return the enablement of, must not be {@code null}
   * @return the enablement value of the button, default false
   */
  public static boolean isButtonEnabled(final SwtWorkbenchBot bot, final String buttonName) {
    return bot.button(buttonName).isEnabled();
  }

}
