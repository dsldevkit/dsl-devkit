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

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withLabel;
import static org.eclipse.ui.IPageLayout.ID_PROBLEM_VIEW;
import static org.hamcrest.Matchers.allOf;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swt.widgets.Table;

import com.avaloq.tools.ddk.test.ui.swtbot.CoreSwtbotTools;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.condition.WaitForEquals;


/**
 * Utility class with handy methods for testing Problems view.
 */
public final class ProblemsViewTestUtil {
  /** Maximum time to wait for asynchronous Problems view and Quick Fix dialog updates. */
  public static final long ASYNC_UPDATE_TIMEOUT = 30000;

  public static final String GROUP_BY = "&Group By"; //$NON-NLS-1$
  public static final String NONE = "&None"; //$NON-NLS-1$
  public static final String SHOW = "&Show"; //$NON-NLS-1$
  public static final String ALL_ERRORS_ON_WORKSPACE = "All Errors on Workspace"; //$NON-NLS-1$
  public static final String SHOW_ALL = "&Show All"; //$NON-NLS-1$
  public static final String QUICK_FIX_CONTEXT_MENU_ITEM_LABEL = "Quick Fix"; //$NON-NLS-1$
  public static final String QUICK_FIX_DIALOG_TEXT = "Quick Fix"; //$NON-NLS-1$
  public static final String SELECT_A_FIX_TABLE_LABEL = "Select a fix:"; //$NON-NLS-1$
  public static final String PROBLEMS_TABLE_LABEL = "Problems:"; //$NON-NLS-1$
  public static final String LOCATION_COLUMN_NAME = "Location"; //$NON-NLS-1$
  public static final String RESOURCE_COLUMN_NAME = "Resource"; //$NON-NLS-1$
  public static final String FINISH_BUTTON_LABEL = "Finish"; //$NON-NLS-1$

  /**
   * Prevents creating an instance.
   */
  private ProblemsViewTestUtil() {
    // do nothing
  }

  /**
   * Make problems view visible and set focus on it.
   *
   * @param bot
   *          the bot, must not be {@code null}
   */
  public static void showProblemsView(final SwtWorkbenchBot bot) {
    CoreSwtbotTools.openView(ID_PROBLEM_VIEW);
    bot.viewById(ID_PROBLEM_VIEW).show();
    bot.viewById(ID_PROBLEM_VIEW).setFocus();
  }

  /**
   * Gets the markers tree.
   *
   * @param bot
   *          the bot, must not be {@code null}
   * @return the markers tree, never {@code null}
   */
  public static SWTBotTree getMarkersTree(final SwtWorkbenchBot bot) {
    showProblemsView(bot);
    return bot.viewById(ID_PROBLEM_VIEW).bot().tree(0);
  }

  /**
   * Convenience method which clicks on the menu item found. See {@link DynamicViewMenu.click}.
   *
   * @param bot
   *          the bot, must not be {@code null}
   * @param menuPath
   *          the menu path, must not be {@code null} nor empty
   */
  public static void clickMenuItem(final SwtWorkbenchBot bot, final String... menuPath) {
    showProblemsView(bot);
    DynamicViewMenu.create(bot).click(menuPath);
  }

  /**
   * Groups by none in Problems view.
   *
   * @param bot
   *          the bot, must not be {@code null}
   */
  public static void groupByNone(final SwtWorkbenchBot bot) {
    clickMenuItem(bot, GROUP_BY, NONE);
  }

  /**
   * Show all errors in Problems view.
   *
   * @param bot
   *          the bot, must not be {@code null}
   */
  public static void showAllErrors(final SwtWorkbenchBot bot) {
    showProblemsView(bot);
    // Select "Show All" first, to avoid toggling "All Errors" off
    showAll(bot);
    clickMenuItem(bot, SHOW, ALL_ERRORS_ON_WORKSPACE);
  }

  /**
   * Show all markers in Problems view.
   *
   * @param bot
   *          the bot, must not be {@code null}
   */
  public static void showAll(final SwtWorkbenchBot bot) {
    showProblemsView(bot);
    clickMenuItem(bot, SHOW, SHOW_ALL);
  }

  /**
   * Bulk-apply the common quickfix to multiple markers.
   * Autobuilding should be disabled, to avoid losing focus while selecting markers.
   *
   * @param bot
   *          the bot, must not be {@code null}
   * @param quickfixLabel
   *          label of the quickfix to apply, must not be {@code null}
   * @param markers
   *          markers to fix, must not be {@code null}
   */
  public static void bulkApplyQuickfix(final SwtWorkbenchBot bot, final String quickfixLabel, final SWTBotTreeItem... markers) {

    // Open the Quick Fix dialog
    final SWTBotTree markersTreeBot = getMarkersTree(bot);
    DynamicContextActionUiTestUtil.clickContextMenu(() -> {
      // Select markers. Keep retrying for a bounded time if focus is lost at an inopportune moment.
      bot.waitUntil(new WaitForEquals<>("Could not select all markers in the Problems view.", () -> markers.length, () -> {
        markersTreeBot.select(markers);
        return markersTreeBot.selectionCount();
      }), ASYNC_UPDATE_TIMEOUT);
    }, markersTreeBot, DynamicMenuPredicate.ALWAYS_WAITING, QUICK_FIX_CONTEXT_MENU_ITEM_LABEL);
    bot.waitUntil(Conditions.shellIsActive(QUICK_FIX_DIALOG_TEXT), ASYNC_UPDATE_TIMEOUT);
    final SWTBotShell quickFixShell = bot.shell(QUICK_FIX_DIALOG_TEXT);
    final SWTBot quickFixBot = quickFixShell.bot();

    // Select the quickfix
    final SWTBotTable selectFixTable = waitForTable(bot, quickFixShell, SELECT_A_FIX_TABLE_LABEL);
    bot.waitUntil(new WaitForEquals<>("Quick Fix dialog did not list the requested fix.", () -> true, () -> selectFixTable.containsItem(quickfixLabel)), ASYNC_UPDATE_TIMEOUT); //$NON-NLS-1$
    selectFixTable.select(quickfixLabel);

    // Selecting the fix repopulates the Problems table asynchronously; tick every expected row and retry until all are
    // checked, tolerating the transient table resizes that happen while the table is still being repopulated.
    final int locationColumnIndex = markersTreeBot.columns().indexOf(LOCATION_COLUMN_NAME);
    if (locationColumnIndex < 0) {
      throw new IllegalStateException("Problems view has no Location column."); //$NON-NLS-1$
    }
    final Set<String> markerLocations = Stream.of(markers).map(marker -> marker.cell(locationColumnIndex)).collect(Collectors.toSet());
    final SWTBotTable tableBot = waitForTable(bot, quickFixShell, PROBLEMS_TABLE_LABEL);
    bot.waitUntil(new WaitForEquals<>("Quick Fix dialog did not list all expected markers.", () -> markers.length, () -> checkMatchingRows(tableBot, markerLocations)), ASYNC_UPDATE_TIMEOUT); //$NON-NLS-1$

    bot.clickButton(quickFixBot.button(FINISH_BUTTON_LABEL), ASYNC_UPDATE_TIMEOUT);
    // Block until the wizard has actually closed, so callers do not save/build while the resolution is still being applied.
    bot.waitUntil(Conditions.shellCloses(quickFixShell), ASYNC_UPDATE_TIMEOUT);
  }

  private static SWTBotTable waitForTable(final SwtWorkbenchBot bot, final SWTBotShell shell, final String label) {
    final WaitForObjectCondition<Table> tableAppears = Conditions.waitForWidget(allOf(widgetOfType(Table.class), withLabel(label)), shell.widget);
    bot.waitUntil(tableAppears, ASYNC_UPDATE_TIMEOUT);
    return new SWTBotTable(tableAppears.get(0));
  }

  /*
   * Ticks every dialog "Problems:" row whose location is in wanted and returns how many such rows are now checked, or -1
   * while the table is mid-refresh so the caller's wait retries. Already-checked rows are skipped so a retried partial
   * pass fires no redundant check events (which would re-trigger the table repopulation we are waiting to settle).
   */
  private static int checkMatchingRows(final SWTBotTable tableBot, final Set<String> wanted) {
    try {
      int checked = 0;
      for (int row = 0; row < tableBot.rowCount(); ++row) {
        if (wanted.contains(tableBot.cell(row, LOCATION_COLUMN_NAME))) {
          final SWTBotTableItem item = tableBot.getTableItem(row);
          if (!item.isChecked()) {
            item.check();
          }
          if (item.isChecked()) {
            checked++;
          }
        }
      }
      return checked;
    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
      return -1; // table mid-refresh (row vanished); report "not ready" so the wait retries
    }
  }

}
