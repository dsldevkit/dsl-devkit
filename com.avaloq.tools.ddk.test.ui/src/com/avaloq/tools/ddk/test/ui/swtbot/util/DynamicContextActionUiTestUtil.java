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

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.utils.TableCollection;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;


/**
 * This helper is a workaround for a bug in SWTBot,
 * where the bot can't find a dynamically created context menu.
 */
public final class DynamicContextActionUiTestUtil {
  private static final Logger LOGGER = LogManager.getLogger(DynamicContextActionUiTestUtil.class);

  /**
   * Clicks the context menu matching the given labels.
   * When a context menu 'Compile' exists with the sub context menu 'All Invalids',
   * then the context menu 'All Invalids' can be clicked by giving the labels 'Compile' and 'All Invalids'.
   *
   * @param bot
   *          the {@link AbstractSWTBot} on which to infer the context menu
   * @param predicate
   *          the predicate to use for recognising dynamic menu items.
   * @param labels
   *          the labels on the context menus
   * @throw {@link WidgetNotFoundException} if the context menu could not be found
   */
  public static void clickContextMenu(final AbstractSWTBot<? extends Control> bot, final DynamicMenuPredicate predicate, final String... labels) {
    clickContextMenu(() -> {
    }, bot, predicate, labels);
  }

  /**
   * Clicks the context menu matching the given labels.
   * When a context menu 'Compile' exists with the sub context menu 'All Invalids',
   * then the context menu 'All Invalids' can be clicked by giving the labels 'Compile' and 'All Invalids'.
   *
   * @param setSelection
   *          method for setting the selection on which to activate the context menu.
   * @param bot
   *          the {@link AbstractSWTBot} on which to infer the context menu
   * @param predicate
   *          the predicate to use for recognising dynamic menu items.
   * @param labels
   *          the labels on the context menus
   * @throw {@link WidgetNotFoundException} if the context menu could not be found
   */
  public static void clickContextMenu(final Runnable setSelection, final AbstractSWTBot<? extends Control> bot, final DynamicMenuPredicate predicate, final String... labels) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("clickContextMenu(" + bot + " , " + Arrays.asList(labels) + ");"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    }
    try {
      setSelection.run();
      logSelection(bot);
      new SwtBotDynamicContextMenu(bot.contextMenu(), predicate).menu(labels).click();
      return;
    } catch (WidgetNotFoundException widgetNotFound) {
      LOGGER.debug("Menu item not found on first attempt.", widgetNotFound); //$NON-NLS-1$
    }

    // maybe the UI was unstable at the point we set the selection - include delays between events to allow UI to catch up.
    SWTUtils.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
    tryUnselect(bot);
    SWTUtils.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
    try {
      setSelection.run();
      SWTUtils.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
      logSelection(bot);
      new SwtBotDynamicContextMenu(bot.contextMenu(), predicate).menu(labels).click();
      return;
    } catch (WidgetNotFoundException widgetNotFound) {
      LOGGER.debug("Menu item not found on second attempt.", widgetNotFound); //$NON-NLS-1$
    }

    // maybe the item takes longer than the dynamic sub-menus to be added to the parent menu - give the item until TIMEOUT to appear.
    new SwtBotDynamicContextMenu(bot.contextMenu(), DynamicMenuPredicate.ALWAYS_WAITING).menu(labels).click();

    // no point in trying again unless/until there is another good reason.
  }

  private static void logSelection(final AbstractSWTBot<? extends Control> bot) {
    if (LOGGER.isDebugEnabled()) {
      TableCollection selection = tryGetSelection(bot);
      if (selection != null) {
        LOGGER.debug("setSelection(" + selection + ")"); // NOPMD GuardLogStatement //$NON-NLS-1$//$NON-NLS-2$
      }
    }
  }

  private static TableCollection tryGetSelection(final AbstractSWTBot<? extends Control> bot) {
    if (bot instanceof SWTBotTree) {
      return ((SWTBotTree) bot).selection();
    }
    if (bot instanceof SWTBotTable) {
      return ((SWTBotTable) bot).selection();
    }
    return null;
  }

  private static void tryUnselect(final AbstractSWTBot<? extends Control> bot) {
    if (bot instanceof SWTBotTree) {
      ((SWTBotTree) bot).unselect();
    }
    if (bot instanceof SWTBotTable) {
      ((SWTBotTable) bot).unselect();
    }
  }

  /**
   * Whether on the given {@link AbstractSWTBot} a context menu with the given labels is available.
   * When one wants to find out, whether a context menu 'Compile' with the sub context menu 'All Invalids' exists,
   * then one has to provide the labels 'Compile' and 'All Invalids'.
   *
   * @param widgetBot
   *          the bot representing the widget on which it should be checked, whether a the context menu
   *          with the given text is available
   * @param predicate
   *          the predicate to use for recognising dynamic menu items.
   * @param labels
   *          the labels on the context menus
   * @return {@code true} if on the given widget bot a context menu with the given text is available, else {@code false}
   */
  public static boolean hasContextMenuItem(final AbstractSWTBot<? extends Control> widgetBot, final DynamicMenuPredicate predicate, final String... labels) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("hasContextMenuItem(" + widgetBot + " , " + Arrays.asList(labels) + ");"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    }
    try {
      return new SwtBotDynamicContextMenu(widgetBot.contextMenu(), predicate).menu(labels) != null;
    } catch (WidgetNotFoundException widgetNotFound) {
      return false;
    }
  }

  /**
   * Whether the context menu with the given labels is enabled.
   *
   * @param widgetBot
   *          the bot representing the widget on which it should be checked, whether the context menu
   *          with the given labels is enabled
   * @param predicate
   *          the predicate to use for recognising dynamic menu items.
   * @param labels
   *          the labels on the context menus
   * @return {@code true} if the context menu is enabled, else {@code false}
   * @throw {@link WidgetNotFoundException} if the context menu could not be found
   */
  public static boolean isEnabled(final AbstractSWTBot<? extends Control> widgetBot, final DynamicMenuPredicate predicate, final String... labels) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("isEnabled(" + widgetBot + " , " + Arrays.asList(labels) + ");"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    }
    try {
      return new SwtBotDynamicContextMenu(widgetBot.contextMenu(), predicate).menu(labels).isEnabled();
    } catch (WidgetNotFoundException widgetNotFound) {
      throw new WidgetNotFoundException("Could not find menu: " + Arrays.asList(labels), widgetNotFound); //$NON-NLS-1$
    }
  }

  /**
   * Utility classes should not have a public or default constructor.
   */
  private DynamicContextActionUiTestUtil() {
  }
}
