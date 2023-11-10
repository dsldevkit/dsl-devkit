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

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;


/**
 * Utility class for {@link SWTBotView}.
 */
public final class SwtBotViewUtil {
  private static final int TIMEOUT_FOR_VIEW_TO_LOAD = 20000;
  private static final String LOADING_VIEW_MESSAGE = "Loading...";

  /**
   * Wait until the contents of the given {@link SWTBotView} are loaded.
   *
   * @param view
   *          the view to be loaded
   */
  public static void waitUntilViewIsLoaded(final SWTBotView view) {
    view.bot().waitUntil(new DefaultCondition() {

      @Override
      public boolean test() {
        SWTBotTreeItem[] allItems = view.bot().tree().getAllItems();
        return allItems.length == 0 || !LOADING_VIEW_MESSAGE.equals(allItems[0].getText());
      }

      @Override
      public String getFailureMessage() {
        return "View must be loaded: " + view.getTitle();
      }

    }, TIMEOUT_FOR_VIEW_TO_LOAD);
  }

  /**
   * Utility classes should have a private constructor.
   */
  private SwtBotViewUtil() {
  }
}
