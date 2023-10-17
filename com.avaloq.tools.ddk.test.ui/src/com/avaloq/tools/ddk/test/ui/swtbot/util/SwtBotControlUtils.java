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

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;


/**
 * Test Helper Class SwtBotControlUtils.
 */
public final class SwtBotControlUtils {

  private static final int THREAD_SLEEP_TIME = 100;
  private static final String TIMEOUT_MSG = "Sync with database was not successful";
  private static final int DEFAULT_TIMEOUT = 10000;

  private SwtBotControlUtils() {
    // Utility Class
  }

  /**
   * Wait for items witch get loaded.
   *
   * @param control
   *          the SWTBot control to search in
   * @throws Exception
   */
  public static void waitForItems(final AbstractSWTBotControl<?> control) {
    waitForItems(control, DEFAULT_TIMEOUT);
  }

  /**
   * Wait for items which get loaded.
   *
   * @param control
   *          the SWTBot control to search in
   * @param timeout
   *          how long should the system wait
   */
  public static void waitForItems(final AbstractSWTBotControl<?> control, final int timeout) {
    final long endTimeMillis = System.currentTimeMillis() + timeout;

    while (System.currentTimeMillis() < endTimeMillis) {
      if (getItemCount(control) > 0) {
        return;
      } else {
        try {
          Thread.sleep(THREAD_SLEEP_TIME);
        } catch (InterruptedException e) {
          // do nothing
        }
      }
    }
    throw new WrappedException(TIMEOUT_MSG, null);
  }

  /**
   * Wait for table items.
   *
   * @param table
   *          the table
   * @param timeout
   *          the timeout
   */

  @SuppressWarnings("PMD.ConfusingTernary")
  public static void waitForTableItems(final SWTBotTable table, final int timeout) {
    final long endTimeMillis = System.currentTimeMillis() + timeout;
    while (System.currentTimeMillis() < endTimeMillis) {
      if (table.getTableItem(0) != null) {
        return;
      } else {
        try {
          Thread.sleep(THREAD_SLEEP_TIME);
        } catch (InterruptedException e) {
          // do nothing
        }
      }
    }
  }

  /**
   * Gets the item count.
   *
   * @param control
   *          the control
   * @return the item count
   */
  private static int getItemCount(final AbstractSWTBotControl<?> control) {
    int itemCount;
    if (control instanceof SWTBotCCombo) {
      itemCount = ((SWTBotCCombo) control).itemCount();
    } else if (control instanceof SWTBotList) {
      itemCount = ((SWTBotList) control).itemCount();
    } else if (control instanceof SWTBotCombo) {
      itemCount = ((SWTBotCombo) control).itemCount();
    } else {
      throw new WrappedException("Control not supported", null);
    }
    return itemCount;
  }
}
