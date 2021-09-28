/*
 * Copyright (c) Avaloq Evolution AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Evolution AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Evolution AG.
 */

package com.avaloq.tools.ddk.test.ui.swtbot.util;

import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRootMenu;
import org.hamcrest.Matcher;


/**
 * Class for wrapping {@link SWTBotRootMenu}s so that they can wait on dynamically
 * contributed menu items.
 */
public class SwtBotDynamicContextMenu extends SWTBotRootMenu {
  private final DynamicMenuPredicate predicate;

  /**
   * Creates a new instance of {@link SwtBotDynamicContextMenu}.
   *
   * @param rootMenu
   *          the {@link SWTBotRootMenu} to wrap.
   * @param predicate
   *          the predicate to use for recognising dynamic menu items.
   */
  public SwtBotDynamicContextMenu(final SWTBotRootMenu rootMenu, final DynamicMenuPredicate predicate) {
    super(rootMenu.widget);
    this.predicate = predicate;
  }

  @Override
  public SWTBotMenu menu(final Matcher<MenuItem> matcher, final boolean recursive, final int index) {
    WaitForObjectCondition<MenuItem> waitForMenuItem = new WaitForDynamicContextMenuItem(this, matcher, recursive, index);
    new SWTBot().waitUntilWidgetAppears(waitForMenuItem);
    return new SwtBotDynamicContextMenuItem(waitForMenuItem.get(0), predicate, matcher);
  }

  /**
   * Get the predicate to use for recognising dynamic menu items.
   *
   * @return the dynamic menu predicate.
   */
  DynamicMenuPredicate getPredicate() {
    return predicate;
  }

}

/* Copyright (c) Avaloq Evolution AG */