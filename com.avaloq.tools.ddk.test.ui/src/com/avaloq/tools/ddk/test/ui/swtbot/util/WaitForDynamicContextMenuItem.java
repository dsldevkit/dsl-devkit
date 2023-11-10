/*
 * Copyright (c) Avaloq Group AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Group AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Group AG.
 */

package com.avaloq.tools.ddk.test.ui.swtbot.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.waits.WaitForMenuItem;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


/**
 * Wrapper around {@link WaitForMenuItem} that knows to stop waiting if there are no more dynamic
 * items to wait for.
 */
public class WaitForDynamicContextMenuItem extends WaitForMenuItem {
  private static final Logger LOGGER = LogManager.getLogger(WaitForDynamicContextMenuItem.class);

  /**
   * Custom wrapper around a given {@link Matcher} that collects data about waiting dynamic menu items as
   * the items are being matched.
   */
  private static class DynamicContextMenuItemMatcher extends BaseMatcher<MenuItem> {
    private final Matcher<MenuItem> matcher;
    private final DynamicMenuPredicate predicate;
    private boolean seenWaitingItems;

    DynamicContextMenuItemMatcher(final Matcher<MenuItem> matcher, final DynamicMenuPredicate predicate) {
      this.matcher = matcher;
      this.predicate = predicate;
    }

    void startMatching() {
      seenWaitingItems = false;
    }

    boolean waitingMenuItemsSeen() {
      return seenWaitingItems;
    }

    @Override
    public boolean matches(final Object item) {
      if (predicate.isWaitingItem(item)) {
        seenWaitingItems = true;
      }
      return matcher.matches(item);
    }

    @Override
    public void describeTo(final Description description) {
      matcher.describeTo(description);
    }
  }

  private final DynamicContextMenuItemMatcher itemMatcher;
  private final Widget widget;

  public WaitForDynamicContextMenuItem(final SwtBotDynamicContextMenuItem menu, final Matcher<MenuItem> matcher, final boolean recursive, final int index) {
    super(menu, new DynamicContextMenuItemMatcher(matcher, menu.getPredicate()), recursive, index);
    itemMatcher = (DynamicContextMenuItemMatcher) this.matcher;
    this.widget = menu.widget;
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("MenuItem finder matching " + itemMatcher); //$NON-NLS-1$
    }
  }

  public WaitForDynamicContextMenuItem(final SwtBotDynamicContextMenu menu, final Matcher<MenuItem> matcher, final boolean recursive, final int index) {
    super(menu, new DynamicContextMenuItemMatcher(matcher, menu.getPredicate()), recursive, index);
    itemMatcher = (DynamicContextMenuItemMatcher) this.matcher;
    this.widget = menu.widget;
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("MenuItem finder matching " + itemMatcher); //$NON-NLS-1$
    }
  }

  @Override
  protected List<MenuItem> findMatches() {
    itemMatcher.startMatching();
    List<MenuItem> matches = super.findMatches();

    if (matches.isEmpty()) {
      // if we know there is no point waiting longer for menu items, we can 'match' the null menu item to terminate the search.
      if (!itemMatcher.waitingMenuItemsSeen()) {
        LOGGER.debug("MenuItem not found"); //$NON-NLS-1$
        return Collections.singletonList(null);
      } else {
        LOGGER.debug("MenuItem finder waiting on menu"); //$NON-NLS-1$
      }
    } else {
      LOGGER.debug("MenuItem found"); //$NON-NLS-1$
    }
    return matches;
  }

  /**
   * Get the menu for the widget. Only to be called from the UI thread.
   *
   * @return the widget menu.
   */
  private Menu getMenu() {
    return (widget instanceof Menu) ? (Menu) widget : ((MenuItem) widget).getMenu();
  }

  @Override
  public String getFailureMessage() {
    return "No menu item matched " + matcher + " in menu " + UIThreadRunnable.syncExec(new StringResult() { //$NON-NLS-1$ //$NON-NLS-2$

      @Override
      public String run() {
        Menu menu = getMenu();
        if (menu == null) {
          return "<null>"; //$NON-NLS-1$
        }
        return Arrays.asList(menu.getItems()).toString();
      }
    });
  }

  @Override
  public MenuItem get(final int index) {
    MenuItem item = super.get(index);
    if (item == null) {
      throw new WidgetNotFoundException(getFailureMessage());
    }
    return item;
  }
}

/* Copyright (c) Avaloq Group AG */