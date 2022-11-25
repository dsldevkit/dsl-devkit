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

/**
 * Interface for the predicate the dynamic matcher uses to determine if a menu item is still being loaded.
 */
public interface DynamicMenuPredicate {
  /**
   * Test if the given item is still waiting to be loaded into a dynamic menu.
   *
   * @param item
   *          the item to test.
   * @return {@code true} if the item is still waiting to be loaded into the menu, {@code false} otherwise.
   */
  boolean isWaitingItem(Object item);

  DynamicMenuPredicate ALWAYS_WAITING = (item) -> true;
  DynamicMenuPredicate NEVER_WAITING = (item) -> false;
}

/* Copyright (c) Avaloq Group AG */