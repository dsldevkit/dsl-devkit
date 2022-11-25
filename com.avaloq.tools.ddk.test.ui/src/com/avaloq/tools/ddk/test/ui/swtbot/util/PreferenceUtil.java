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

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;

import com.avaloq.tools.ddk.test.ui.swtbot.CoreSwtbotTools;


/**
 * Provides utility methods to open the preference dialog.
 */
public final class PreferenceUtil {

  private static final String SHELL_PREFERENCES = "Preferences";

  /**
   * Creates a new instance of {@link PreferenceUtil}.
   */
  private PreferenceUtil() {
    // Empty constructor to avoid instantiation.
  }

  /**
   * Opens the preference dialog and unfolds the tree to the given path.
   *
   * @param bot
   *          the workbench bot which opens the dialog, must not be {@code null}.
   * @param preferencePath
   *          the path to the preference one wants to open, must not be {@code null} or empty.
   */
  public static void openPreferenceDialog(final SWTWorkbenchBot bot, final String... preferencePath) {
    bot.activeView();
    bot.menu("Window").menu(SHELL_PREFERENCES).click();
    bot.waitUntil(Conditions.shellIsActive(SHELL_PREFERENCES));
    CoreSwtbotTools.expandNode(bot.tree(0), preferencePath).select();
  }

  /**
   * Closes the preference dialog.
   *
   * @param bot
   *          the workbench bot which closes the dialog, must not be {@code null}.
   */
  public static void closePreferenceDialog(final SWTWorkbenchBot bot) {
    bot.shell(SHELL_PREFERENCES).activate();
    bot.waitUntil(Conditions.shellIsActive(SHELL_PREFERENCES));
    bot.button("OK").click();
  }
}
