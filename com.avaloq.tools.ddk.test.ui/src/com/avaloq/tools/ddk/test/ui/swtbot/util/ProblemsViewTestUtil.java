/*******************************************************************************
 * Copyright (c) 2016-2018 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.test.ui.swtbot.util;

import static org.junit.Assert.fail;

import java.util.stream.Stream;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import com.avaloq.tools.ddk.test.ui.swtbot.CoreSwtbotTools;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;


/**
 * Utility class with handy methods for testing Problems view.
 */
public final class ProblemsViewTestUtil {
  public static final String PROBLEMS_VIEW = "org.eclipse.ui.views.ProblemView"; //$NON-NLS-1$
  public static final String SHOW = "&Show"; //$NON-NLS-1$
  public static final String ALL_ERRORS = "All Errors"; //$NON-NLS-1$
  public static final String SHOW_ALL = "&Show All"; //$NON-NLS-1$
  public static final String GROUP_BY = "&Group By"; //$NON-NLS-1$
  public static final String SEVERITY = "Severity"; //$NON-NLS-1$

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
    CoreSwtbotTools.openView(PROBLEMS_VIEW);
    bot.viewById(PROBLEMS_VIEW).show();
    bot.viewById(PROBLEMS_VIEW).setFocus();
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
    return bot.viewById(PROBLEMS_VIEW).bot().tree(0);
  }

  /**
   * Gets the task markers.
   *
   * @param bot
   *          the bot, must not be {@code null}
   * @return the task markers, never {@code null}
   */
  public static SWTBotTreeItem[] getTasks(final SwtWorkbenchBot bot) {
    showProblemsView(bot);

    return Stream.of(getMarkersTree(bot).getAllItems()).flatMap(item -> {
      if (!item.isExpanded()) {
        item.expand();
      }
      final SWTBotTreeItem[] subItems = item.getItems();
      if (subItems.length > 0) {
        // This is a category, e.g. Errors
        return Stream.of(subItems);
      } else {
        // This is a marker.
        return Stream.of(item);
      }
    }).toArray(SWTBotTreeItem[]::new);
  }

  /**
   * Waits and asserts expected count of errors and warnings.
   *
   * @param bot
   *          the bot, must not be {@code null}
   * @param expectedErrors
   *          the expected count of errors
   * @param expectedWarnings
   *          the expected count of warnings
   */
  public static void expectedErrorsWarnings(final SwtWorkbenchBot bot, final int expectedErrors, final int expectedWarnings) {
    showProblemsView(bot);
    groupBySeverity(bot);

    final DefaultCondition condition = new DefaultCondition() {
      private int numberOfErrors;
      private int numberOfWarnings;

      private int getNumberOf(final String groupTitle) {
        for (SWTBotTreeItem item : bot.tree(0).getAllItems()) {
          if (item.getText().contains(groupTitle)) {
            if (!item.isExpanded()) {
              item.expand();
            }
            return item.getItems().length;
          }
        }
        return 0;
      }

      @Override
      public boolean test() {
        // There will be two groups: Errors and Warnings
        numberOfErrors = getNumberOf("Errors"); //$NON-NLS-1$
        numberOfWarnings = getNumberOf("Warnings"); //$NON-NLS-1$
        return expectedErrors == numberOfErrors && expectedWarnings == numberOfWarnings;
      }

      @Override
      public String getFailureMessage() {
        return "Expected " + expectedErrors + " errors and " + expectedWarnings + " warning. Found: '" + numberOfErrors + "' errors and '" + numberOfWarnings //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            + "' warnings"; //$NON-NLS-1$
      }
    };
    try {
      bot.viewById(PROBLEMS_VIEW).bot().waitUntil(condition);
    } catch (TimeoutException e) {
      fail(condition.getFailureMessage());
    }
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
   * Groups by severity in Problems view.
   *
   * @param bot
   *          the bot, must not be {@code null}
   */
  public static void groupBySeverity(final SwtWorkbenchBot bot) {
    clickMenuItem(bot, GROUP_BY, SEVERITY);
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
    clickMenuItem(bot, SHOW, ALL_ERRORS);
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

}
