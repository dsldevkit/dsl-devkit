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
package com.avaloq.tools.ddk.test.ui.swtbot;

import static org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferenceConstants;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import com.avaloq.tools.ddk.test.core.util.Reflect;
import com.avaloq.tools.ddk.test.ui.swtbot.condition.WaitForTable;
import com.avaloq.tools.ddk.test.ui.swtbot.condition.WaitForTree;


/**
 * Test Helper Class CoreSwtbotTools.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("PMD.ExcessiveImports")
public final class CoreSwtbotTools {
  private static final String ARGUMENT_NODES = "nodes";
  private static final String ARGUMENT_NODE = "node";
  private static final String ARGUMENT_VIEW = "view";
  private static final String ARGUMENT_ITEM = "item";
  private static final String ARGUMENT_TABLE = "table";
  private static final String ARGUMENT_TREE = "tree";
  private static final String ARGUMENT_BOT = "bot";
  // CHECKSTYLE:ON
  private static final int TIMEOUT_FOR_NODE_TO_COLLAPSE_EXPAND = 3000;
  private static final int KEYBOARD_TYPE_INTERVAL = 50;
  private static final int BOT_WIDGET_TIMEOUT = 20000;
  private static final int BOT_PLAYBACK_DELAY = 100;
  private static final int MOUSE_POSITION_Y = 5;
  private static final int MOUSE_POSITION_X = 5;
  private static final String PROPERTY_COM_AVALOQ_TEST_WORKBENCHFOCUSPOLICY = "com.avaloq.test.workbenchfocuspolicy";
  private static WorkbenchFocusPolicy workbenchFocusPolicy = WorkbenchFocusPolicy.REFOCUS;
  private static boolean preferencesInitialized;

  private CoreSwtbotTools() {
    // Utility Class
  }

  /**
   * Sets common SWTBot preferences.
   */
  public static void initializePreferences() {
    // -Dorg.eclipse.swtbot.playback.delay=2
    SWTBotPreferences.PLAYBACK_DELAY = BOT_PLAYBACK_DELAY;

    // System.setProperty("org.eclipse.swtbot.keyboardLayout", "EN_US");
    SWTBotPreferences.KEYBOARD_LAYOUT = "org.eclipse.swtbot.swt.finder.keyboard.EN_US";

    // SWTBot Keyboard strategies
    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy";

    // keyboard type interval
    SWTBotPreferences.TYPE_INTERVAL = KEYBOARD_TYPE_INTERVAL;

    // Waiting for Widgets
    SWTBotPreferences.TIMEOUT = BOT_WIDGET_TIMEOUT;

    // screenshot directory
    SWTBotPreferences.SCREENSHOTS_DIR = System.getProperty(SWTBotPreferenceConstants.KEY_SCREENSHOTS_DIR, "target/screenshots");

    // test window focus policy
    workbenchFocusPolicy = WorkbenchFocusPolicy.valueOf(System.getProperty(PROPERTY_COM_AVALOQ_TEST_WORKBENCHFOCUSPOLICY, WorkbenchFocusPolicy.REFOCUS.toString()));

    preferencesInitialized = true;
  }

  /**
   * The policy for how to react if the test window lost focus.
   */
  public enum WorkbenchFocusPolicy {
    REFOCUS,
    WAIT
  }

  /**
   * Returns the policy for how to react if the test window lost focus.
   *
   * @return the workbench focus policy, never {@code null}
   */
  public static WorkbenchFocusPolicy getWorkbenchFocusPolicy() {
    if (!preferencesInitialized) {
      initializePreferences();
    }
    return workbenchFocusPolicy;
  }

  /**
   * Enforces the workbench focus policy.
   * <p>
   * <em>Note</em>: Depending on {@link CoreSwtbotTools#getWorkbenchFocusPolicy()}, this method waits until the window under test has focus before returning, or
   * else re-sets the focus on the window.
   * </p>
   *
   * @param bot
   *          the {@link SwtWorkbenchBot} for which to check the focus, must not be {@code null}
   */
  public static void enforceWorkbenchFocusPolicy(final SwtWorkbenchBot bot) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    if (bot.getFocusedWidget() == null) {
      if (WorkbenchFocusPolicy.WAIT == getWorkbenchFocusPolicy()) {
        bot.waitUntilFocused();
      } else if (WorkbenchFocusPolicy.REFOCUS == getWorkbenchFocusPolicy()) {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
          /** {@inheritDoc} */
          @Override
          public void run() {
            final Shell[] shells = PlatformUI.getWorkbench().getDisplay().getShells();
            if (shells.length > 0) {
              shells[0].forceActive();
            }
          }
        });
      }
    }
  }

  /**
   * Performs some weird initialization steps needed to run the tests.
   *
   * @param bot
   *          to work with, must not be {@code null}
   */
  public static void initializeWorkbench(final SwtWorkbenchBot bot) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    // Move mouse outside client area (to prevent problems with context menus)
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        try {
          final Robot robot = new Robot();
          robot.mouseMove(MOUSE_POSITION_X, MOUSE_POSITION_Y);
        } catch (SWTException | AWTException wte) {
          throw new WrappedException("Error during initialisation SWT mouse", wte);
        }
      }
    });
    // The welcome page must be closed before resetting the workbench to avoid trashing the UI:
    bot.closeWelcomePage();
    bot.resetActivePerspective();
  }

  /**
   * Takes a screenshot.
   *
   * @param bot
   *          the {@link SWTBot} factory instance to use, must not be {@code null}
   * @param prefix
   *          base name of file, may be {@code null} only if useCaller is {@code true}
   * @param useCaller
   *          if {@code true}, append the name of the calling method to the prefix
   */
  public static void captureScreenshot(final SWTBot bot, final String prefix, final boolean useCaller) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    if (prefix == null && !useCaller) {
      throw new IllegalArgumentException();
    }
    final StringBuilder sBuilder = new StringBuilder("screenshots/");
    if (prefix != null) {
      sBuilder.append(prefix);
    }
    if (useCaller) {
      sBuilder.append(Reflect.getCallingMethod());
    }
    sBuilder.append(".jpeg");
    bot.captureScreenshot(sBuilder.toString());
  }

  /**
   * Returns {@code true} if the SWTBot finds the specified window.
   *
   * @param bot
   *          the {@link SWTWorkbenchBot}, must not be {@code null}
   * @param windowName
   *          the name of the window to search for, must not be {@code null}
   * @return {@code true} if a window was found, {@code false} otherwise
   */
  public static boolean checkOpenWindow(final SWTWorkbenchBot bot, final String windowName) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(windowName, "windowName");
    Boolean windowFound = false;
    try {
      final SWTBotShell shell = bot.shell(windowName);
      shell.isActive();
      windowFound = true;
    } catch (WidgetNotFoundException exception) {
      throw new WrappedException("Error during searching for window", exception);
    }
    return windowFound;
  }

  /**
   * Switching to a new Perspective.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param perspective
   *          the new perspective to open, must not be {@code null}
   */
  public static void switchPerspective(final SWTWorkbenchBot bot, final String perspective) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(perspective, "perspective");
    // Change the perspective via the Open Perspective dialog
    bot.menu("Window").menu("Open Perspective").menu("Other...").click();
    final SWTBotShell shell = bot.shell("Open Perspective");
    shell.activate();

    // select the dialog
    bot.table().select(perspective);
    bot.button("OK").click();
  }

  /**
   * Switching to the default Avaloq perspective and resets it.
   */
  public static void switchAndResetPerspective() {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

      @Override
      public void run() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = null;
        try {
          page = workbench.showPerspective("com.avaloq.ice.perspectives.Development", window);
        } catch (final WorkbenchException exception) {
          // Try customer perspective
          try {
            page = workbench.showPerspective("com.avaloq.ice.perspectives.Development", window);
          } catch (final WorkbenchException second) {
            // Both perspectives are missing
            throw new AssertionFailedException("Could not switch to Avaloq Perspective: " + exception.getLocalizedMessage());
          }
        }
        if (page != null) {
          page.resetPerspective();
        }
      }
    });
  }

  /**
   * Tests if a tree contains items.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param tree
   *          to look for items in, must not be {@code null}
   */
  public static void waitForTreeItem(final SWTWorkbenchBot bot, final SWTBotTree tree) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(tree, ARGUMENT_TREE);
    bot.waitUntilWidgetAppears(new WaitForTree(tree.widget));
  }

  /**
   * Tests if a table contains items.
   *
   * @param bot
   *          the {@link SWTWorkbenchBot}, must not be {@code null}
   * @param table
   *          the {@link SWTBotTable}, must not be {@code null}
   */
  public static void waitForTableItem(final SWTWorkbenchBot bot, final SWTBotTable table) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(table, ARGUMENT_TABLE);
    bot.waitUntilWidgetAppears(new WaitForTable(table.widget));
  }

  /**
   * Returns items from a tree.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param tree
   *          to look for items in, must not be {@code null}
   * @return list of tree items, never {@code null}
   */
  public static List<SWTBotTreeItem> treeItems(final SWTWorkbenchBot bot, final SWTBotTree tree) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(tree, ARGUMENT_TREE);
    waitForTreeItem(bot, tree);
    return new ArrayList<SWTBotTreeItem>(Arrays.asList(tree.getAllItems()));
  }

  /**
   * Returns items from a table.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param table
   *          to look for items in, must not be {@code null}
   * @return list of table items, never {@code null}
   */
  public static List<SWTBotTableItem> tableItems(final SWTWorkbenchBot bot, final SWTBotTable table) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(table, ARGUMENT_TABLE);
    waitForTableItem(bot, table);
    List<SWTBotTableItem> items = null;
    for (int i = 0; i < table.rowCount(); i++) {
      items = new ArrayList<SWTBotTableItem>(Arrays.asList(table.getTableItem(i)));
    }
    return items;
  }

  /**
   * Get text content from the clipboard.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @return clipboard text content, or {@code null} if no text data is available
   */
  public static String getClipboardContent(final SWTWorkbenchBot bot) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    return UIThreadRunnable.syncExec(new Result<String>() {
      @Override
      public String run() {
        final Clipboard clipboard = new Clipboard(bot.getDisplay());
        return (String) clipboard.getContents(TextTransfer.getInstance());
      }
    });
  }

  /**
   * Expansion of items in a SWTBotTree. Will try collapse and expand if there are no child nodes.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param item
   *          to wait for, must not be {@code null}
   * @return {@code true} if expanded, {@code false} otherwise
   */
  public static boolean waitForItem(final SWTWorkbenchBot bot, final SWTBotTreeItem item) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(item, ARGUMENT_ITEM);
    item.select();
    safeBlockingExpand(bot, item);
    if (item.getItems().length == 0) {
      safeBlockingCollapse(bot, item);
      safeBlockingExpand(bot, item);
    }
    return item.getItems().length > 0;
  }

  /**
   * Open view.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param view
   *          the name of the view, must not be {@code null}
   */
  public static void openView(final SWTWorkbenchBot bot, final String view) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(view, ARGUMENT_VIEW);
    openView(bot, "Avaloq", view);
  }

  /**
   * Open view.
   *
   * @param id
   *          the view id, must not be {@code null}
   */
  public static void openView(final String id) {
    Assert.isNotNull(id, "id");
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

      @Override
      public void run() {
        try {
          PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(id);
        } catch (final PartInitException exception) {
          throw new AssertionFailedException("Could not open change view: " + exception.getLocalizedMessage());
        }
      }
    });
  }

  /**
   * Open view.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param category
   *          the category, must not be {@code null}
   * @param view
   *          the name of the view, must not be {@code null}
   */
  public static void openView(final SWTWorkbenchBot bot, final String category, final String view) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(category, "category");
    Assert.isNotNull(view, ARGUMENT_VIEW);
    bot.menu("Window").menu("Show View").menu("Other...").click();
    bot.shell("Show View").activate();
    final SWTBotTree tree = bot.tree();

    for (SWTBotTreeItem item : tree.getAllItems()) {
      if (category.equals(item.getText())) {
        CoreSwtbotTools.waitForItem(bot, item);
        final SWTBotTreeItem[] node = item.getItems();

        for (SWTBotTreeItem swtBotTreeItem : node) {
          if (view.equals(swtBotTreeItem.getText())) {
            swtBotTreeItem.select();
          }
        }
      }
    }
    assertTrue("View or Category found", bot.button().isEnabled());
    bot.button("OK").click();
  }

  /**
   * Find tree item.
   * <p>
   * <em>Note</em>: Throws an AssertionError if the item could not be found.
   * </p>
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param tree
   *          to search in, must not be {@code null}
   * @param item
   *          to search, must not be {@code null}
   * @return the {@link SWTBotTreeItem}, never {@code null}
   */
  public static SWTBotTreeItem findTreeItem(final SWTWorkbenchBot bot, final SWTBotTree tree, final String item) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(tree, "tree");
    Assert.isNotNull(item, ARGUMENT_ITEM);
    int itemCount = 0;
    boolean itemFound = false;
    SWTBotTreeItem botTreeItem = null;
    CoreSwtbotTools.waitForTreeItem(bot, tree);

    do {
      for (SWTBotTreeItem treeItem : tree.getAllItems()) {
        itemCount = treeItem.rowCount();
        if (item.equals(treeItem.getText())) {
          itemFound = true;
          botTreeItem = treeItem;
          break;
        } else {
          for (SWTBotTreeItem treeNode : tree.getAllItems()) {
            CoreSwtbotTools.waitForItem(bot, treeNode);
            itemCount = treeNode.rowCount();
          }
        }
      }
    } while (itemCount > 0);

    assertTrue("Searching TreeItem", itemFound);

    return botTreeItem;

  }

  /**
   * Open a specific Avaloq Prefrences Page.
   *
   * @param bot
   *          to work with, must not be {@code null}
   * @param section
   *          the name of the desired page (e.g. 'Database'), must not be {@code null}
   */
  public static void openAvaloqPreferencesSection(final SWTWorkbenchBot bot, final String section) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(section, "section");
    bot.menu("Window").menu("Preferences").click();
    final SWTBotShell shell = bot.shell("Preferences");
    shell.activate();
    final SWTBotTreeItem item = bot.tree().getTreeItem("Avaloq");
    CoreSwtbotTools.waitForItem(bot, item);
    CoreSwtbotTools.expandNode(bot.tree(), "Avaloq").select(section);
  }

  /**
   * Returns the full text of the first CcomboItem found with the given prefix.
   * <p>
   * <em>Note</em>: Throws an AssertionError if the item could not be found.
   * </p>
   *
   * @param ccombo
   *          the ccomboBox to search in, must not be {@code null}
   * @param prefix
   *          the prefix of the item to search for, must not be {@code null}
   * @return the full name of the item as string, never {@code null}
   */
  public static String getCcomboItemText(final SWTBotCCombo ccombo, final String prefix) {
    Assert.isNotNull(ccombo, "ccombo");
    Assert.isNotNull(prefix, "prefix");
    for (String ccomboItem : ccombo.items()) {
      if (ccomboItem.startsWith(prefix)) {
        return ccomboItem;
      }
    }
    throw new AssertionFailedException(NLS.bind("Must have a CcomboItem named ''{0}''.", prefix));
  }

  /**
   * Waits until the node collapses.
   *
   * @param bot
   *          bot to work with, must not be {@code null}
   * @param node
   *          node to wait for, must not be {@code null}
   */
  public static void safeBlockingCollapse(final SWTWorkbenchBot bot, final SWTBotTreeItem node) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(node, ARGUMENT_NODE);
    if (node.isExpanded()) {
      node.collapse();
      try {
        bot.waitUntil(new DefaultCondition() {

          @Override
          @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
          public boolean test() {
            return !node.isExpanded();
          }

          @Override
          public String getFailureMessage() {
            return "Timeout for node to collapse";
          }
        }, TIMEOUT_FOR_NODE_TO_COLLAPSE_EXPAND);
      } catch (TimeoutException e) {
        // Try one last time and do not wait anymore
        node.collapse();
      }
    }
  }

  /**
   * Waits until the node expands.
   *
   * @param bot
   *          bot to work with, must not be {@code null}
   * @param node
   *          node to wait for, must not be {@code null}
   */
  public static void safeBlockingExpand(final SWTWorkbenchBot bot, final SWTBotTreeItem node) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(node, ARGUMENT_NODE);
    if (!node.isExpanded()) {
      node.expand();
      try {
        bot.waitUntil(new DefaultCondition() {

          @Override
          @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
          public boolean test() {
            return node.isExpanded();
          }

          @Override
          public String getFailureMessage() {
            return "Timeout for node to expand";
          }
        }, TIMEOUT_FOR_NODE_TO_COLLAPSE_EXPAND);

      } catch (TimeoutException e) {
        // Try one last time and do not wait anymore
        node.expand();
      }
    }
  }

  /**
   * Attempts to expand all nodes along the path specified by the node array parameter.
   * The method is copied from SWTBotTree with an additional check if the node is already expanded.
   *
   * @param bot
   *          tree bot, must not be {@code null}
   * @param nodes
   *          node path to expand, must not be {@code null} or empty
   * @return the last tree item that was expanded, or {@code null} if no item was found
   */
  public static SWTBotTreeItem expandNode(final SWTBotTree bot, final String... nodes) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(nodes, ARGUMENT_NODES);
    assertArgumentIsNotEmpty(nodes, ARGUMENT_NODES);
    new SWTBot().waitUntil(widgetIsEnabled(bot));
    SWTBotTreeItem item = bot.getTreeItem(nodes[0]);
    if (!item.isExpanded()) {
      item.expand();
    }

    final List<String> asList = new ArrayList<String>(Arrays.asList(nodes));
    asList.remove(0);
    if (!asList.isEmpty()) {
      item = expandNode(item, asList.toArray(new String[asList.size()]));
    }

    return item;
  }

  /**
   * Expands the node matching the given node texts.
   * The method is copied from SWTBotTreeItem with an additional check if the node is already expanded.
   *
   * @param bot
   *          tree item bot, must not be {@code null}
   * @param nodes
   *          the text on the node, must not be {@code null} or empty
   * @return the last tree node that was expanded, never {@code null}
   */
  public static SWTBotTreeItem expandNode(final SWTBotTreeItem bot, final String... nodes) {
    Assert.isNotNull(bot, ARGUMENT_BOT);
    Assert.isNotNull(nodes, ARGUMENT_NODES);
    assertArgumentIsNotEmpty(nodes, ARGUMENT_NODES);
    SWTBotTreeItem item = bot;
    for (String node : nodes) {
      item = item.getNode(node);
      if (!item.isExpanded()) {
        item.expand();
      }
    }
    return item;
  }

  /**
   * Ensures that the specified argument is not <code>null</code> and
   * contains at least one element. Additionally, ensures that each element of
   * the array is not <code>null</code>.
   *
   * @param array
   *          the parameter to check
   * @param name
   *          the name of the parameter
   * @throws ArgumentAssertionFailedException
   *           if the array is empty (or <code>null</code>), or at least
   *           one array element is <code>null</code>.
   */
  private static void assertArgumentIsNotEmpty(final Object[] array, final String name) {
    Assert.isNotNull(array, name);
    if (array.length == 0) {
      throw new IllegalArgumentException("Contract for argument '" + name + "' failed: Array parameter must not be empty."); //$NON-NLS-1$
    } else {
      for (Object element : array) {
        if (element == null) {
          throw new IllegalArgumentException("Contract for argument '" + name + "' failed: Array parameter must contain only values that are not null."); //$NON-NLS-1$
        }
      }
    }
  }
}
