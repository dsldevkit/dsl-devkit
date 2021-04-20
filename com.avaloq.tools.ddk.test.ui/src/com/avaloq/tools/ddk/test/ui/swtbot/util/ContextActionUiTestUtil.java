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
package com.avaloq.tools.ddk.test.ui.swtbot.util;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.hamcrest.Matcher;

import com.google.common.collect.Lists;


/**
 * This helper is a workaround for a bug in SWTBot,
 * where the bot can't find a dynamically created context menu.
 */
public final class ContextActionUiTestUtil {
  private static final String ICE_CTX_LABEL_LOADING = "Loading..."; //$NON-NLS-1$
  private static final String ICE_ASYNC_VALIDATION_LABEL_CHECKING = "Checking ''{0}''"; //$NON-NLS-1$

  /**
   * Clicks the context menu matching the given labels.
   * When a context menu 'Compile' exists with the sub context menu 'All Invalids',
   * then the context menu 'All Invalids' can be clicked by giving the labels 'Compile' and 'All Invalids'.
   *
   * @param bot
   *          the {@link AbstractSWTBot} on which to infer the context menu
   * @param labels
   *          the labels on the context menus
   * @throw {@link WidgetNotFoundException} if the context menu could not be found
   */
  public static void clickContextMenu(final AbstractSWTBot<? extends Control> bot, final String... labels) {
    MenuItem menuItem = getContextMenuItem(bot, labels);
    if (menuItem == null) {
      long endTime = System.currentTimeMillis() + SWTBotPreferences.TIMEOUT;
      while (menuItem == null && System.currentTimeMillis() < endTime) {
        SWTUtils.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
        menuItem = getContextMenuItem(bot, labels);
      }
      if (menuItem == null) {
        throw new WidgetNotFoundException("Could not find menu: " + Arrays.asList(labels)); //$NON-NLS-1$
      }
    }
    waitUntilMenuItemIsReady(menuItem, labels[labels.length - 1]);
    click(menuItem);
  }

  /**
   * Returns the context menu item identified by the given labels.
   * When a context menu 'Compile' exists with the sub context menu 'All Invalids',
   * then the context menu 'All Invalids' is returned when giving the labels 'Compile' and 'All Invalids'.
   *
   * @param bot
   *          the {@link AbstractSWTBot} on which to infer the context menu
   * @param labels
   *          the labels on the context menus
   * @return the context menu item, {@code null} if the context menu item could not be found
   */
  private static MenuItem getContextMenuItem(final AbstractSWTBot<? extends Control> bot, final String... labels) {
    return UIThreadRunnable.syncExec(new WidgetResult<MenuItem>() {
      @Override
      public MenuItem run() {
        MenuItem menuItem = null;
        Control control = bot.widget;

        // MenuDetectEvent
        Event event = new Event();
        control.notifyListeners(SWT.MenuDetect, event);
        if (event.doit) {
          Menu menu = control.getMenu();
          for (String text : labels) {
            Matcher<Widget> matcher = allOf(instanceOf(MenuItem.class), menuItemWithText(text));
            menuItem = show(menu, matcher);
            if (menuItem != null) {
              menu = menuItem.getMenu();
            } else {
              hide(menu);
              break;
            }
          }
          return menuItem;
        } else {
          return null;
        }
      }
    });
  }

  /**
   * Wait some time until the given menu item is ready to be clicked on, as sometimes the item validation is still in progress.
   *
   * @param menuItem
   *          the menu item to wait for.
   * @param expectedText
   *          the expected text for the item.
   */
  private static void waitUntilMenuItemIsReady(final MenuItem menuItem, final String expectedText) {
    Matcher<Widget> matcher = menuItemWithReadyText(expectedText);
    final long currentTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - currentTime < SWTBotPreferences.TIMEOUT) {
      final Boolean isReady = UIThreadRunnable.syncExec(new Result<Boolean>() {
        @Override
        public Boolean run() {
          return matcher.matches(menuItem);
        }
      });
      if (!isReady) {
        try {
          Thread.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
        } catch (final InterruptedException exception) {
          // ignore
        }
      } else {
        break;
      }
    }
  }

  private static Matcher<Widget> menuItemWithText(final String text) {
    return anyOf(menuItemWithReadyText(text), menuItemWithValidatingText(text));
  }

  private static Matcher<Widget> menuItemWithReadyText(final String text) {
    return withMnemonic(text);
  }

  private static Matcher<Widget> menuItemWithValidatingText(final String text) {
    return withMnemonic(NLS.bind(ICE_ASYNC_VALIDATION_LABEL_CHECKING, text));
  }

  /**
   * Shows the provided {@link Menu} and returns the {@link MenuItem} matching the given {@link Matcher} criteria.
   *
   * @param menu
   *          the {@link Menu} to show
   * @param matcher
   *          the {@link Matcher} to use to find the {@link MenuItem}
   * @return
   *         the {@link MenuItem} matching the given {@link Matcher} criteria
   */
  private static MenuItem show(final Menu menu, final Matcher<?> matcher) {
    if (menu != null) {
      menu.notifyListeners(SWT.Show, new Event());

      MenuItem item = null;
      final long start = System.currentTimeMillis();
      while ((item = getMenuItem(menu, matcher)) != null) {
        if (ICE_CTX_LABEL_LOADING.equals(item.getText())) {
          try {
            while (Display.getDefault().readAndDispatch()) {
              // Allow Lazy Loading items to modify the menu
            }
            Thread.sleep(SWTBotPreferences.PLAYBACK_DELAY);
          } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
          }
        } else {
          return item;
        }
        if (System.currentTimeMillis() - start > SWTBotPreferences.TIMEOUT) {
          return null;
        }
      }
      menu.notifyListeners(SWT.Hide, new Event());
      return item;
    }
    return null;
  }

  /**
   * Returns the menu item for the given matcher, or the lazy loading menu item if it is available.
   *
   * @param menu
   *          the menu
   * @param matcher
   *          the matcher
   * @return the menu item or {@code null} if none found
   */
  private static MenuItem getMenuItem(final Menu menu, final Matcher<?> matcher) {
    final MenuItem[] items = menu.getItems();
    for (final MenuItem menuItem : items) {
      if (matcher.matches(menuItem)) {
        return menuItem;
      } else if (ICE_CTX_LABEL_LOADING.equals(menuItem.getText())) {
        return menuItem;
      }
    }
    return null;
  }

  /**
   * Clicks on the {@link MenuItem}.
   *
   * @param menuItem
   *          the {@link MenuItem} to click on
   */
  private static void click(final MenuItem menuItem) {
    final Event event = new Event();
    event.time = (int) System.currentTimeMillis();
    event.widget = menuItem;
    event.display = menuItem.getDisplay();
    event.type = SWT.Selection;

    UIThreadRunnable.asyncExec(menuItem.getDisplay(), new VoidResult() {
      @Override
      public void run() {
        if (SWTUtils.hasStyle(menuItem, SWT.CHECK) || SWTUtils.hasStyle(menuItem, SWT.RADIO)) {
          menuItem.setSelection(!menuItem.getSelection());
        }
        menuItem.notifyListeners(SWT.Selection, event);
      }
    });
  }

  /**
   * Hides the {@link Menu}.
   *
   * @param menu
   *          the {@link Menu} to hide
   */
  private static void hide(final Menu menu) {
    menu.notifyListeners(SWT.Hide, new Event());
    if (menu.getParentMenu() != null) {
      hide(menu.getParentMenu());
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
   * @param labels
   *          the labels on the context menus
   * @return {@code true} if on the given widget bot a context menu with the given text is available, else {@code false}
   */
  public static boolean hasContextMenuItem(final AbstractSWTBot<? extends Control> widgetBot, final String... labels) {
    return getContextMenuItem(widgetBot, labels) != null;
  }

  /**
   * Returns the {@link SWTBotMenu}s available on the given widget bot.
   *
   * @param widgetBot
   *          the bot representing the widget, whose {@link SWTBotMenu}s should be returned
   * @return the {@link SWTBotMenu}s on the given widget bot
   */
  public static List<SWTBotMenu> getContextMenuItems(final AbstractSWTBot<? extends Control> widgetBot) {
    return UIThreadRunnable.syncExec(new Result<List<SWTBotMenu>>() {
      @Override
      public List<SWTBotMenu> run() {
        List<SWTBotMenu> menuItems = Lists.newArrayList();
        for (MenuItem menuItem : new ContextMenuFinder(widgetBot.widget).findMenus(widgetBot.widget.getShell(), WidgetMatcherFactory.widgetOfType(MenuItem.class), true)) {
          menuItems.add(new SWTBotMenu(menuItem));
        }
        return menuItems;
      }
    });
  }

  /**
   * Returns the disabled {@link SWTBotMenu}s on the given widget bot.
   *
   * @param widgetBot
   *          the bot representing the widget, whose disabled {@link SWTBotMenu}s should be returned
   * @return the disabled {@link SWTBotMenu}s on the given widget bot
   */
  public static List<SWTBotMenu> getDisabledContextMenuItems(final AbstractSWTBot<? extends Control> widgetBot) {
    return UIThreadRunnable.syncExec(new Result<List<SWTBotMenu>>() {
      @Override
      public List<SWTBotMenu> run() {
        List<SWTBotMenu> disabledMenuItems = Lists.newArrayList();
        for (MenuItem menuItem : new ContextMenuFinder(widgetBot.widget).findMenus(widgetBot.widget.getShell(), WidgetMatcherFactory.widgetOfType(MenuItem.class), true)) {
          if (!menuItem.isEnabled()) {
            disabledMenuItems.add(new SWTBotMenu(menuItem));
          }
        }
        return disabledMenuItems;
      }
    });
  }

  /**
   * Whether the context menu with the given labels is enabled.
   *
   * @param widgetBot
   *          the bot representing the widget on which it should be checked, whether the context menu
   *          with the given labels is enabled
   * @param labels
   *          the labels on the context menus
   * @return {@code true} if the context menu is enabled, else {@code false}
   * @throw {@link WidgetNotFoundException} if the context menu could not be found
   */
  public static boolean isEnabled(final AbstractSWTBot<? extends Control> widgetBot, final String... labels) {
    final MenuItem menuItem = getContextMenuItem(widgetBot, labels);
    if (menuItem == null) {
      throw new WidgetNotFoundException("Could not find menu: " + Arrays.asList(labels)); //$NON-NLS-1$
    }
    waitUntilMenuItemIsReady(menuItem, labels[labels.length - 1]);
    return UIThreadRunnable.syncExec(new Result<Boolean>() {
      @Override
      public Boolean run() {
        return menuItem.isEnabled();
      }
    });
  }

  /**
   * Utility classes should not have a public or default constructor.
   */
  private ContextActionUiTestUtil() {
  }
}
