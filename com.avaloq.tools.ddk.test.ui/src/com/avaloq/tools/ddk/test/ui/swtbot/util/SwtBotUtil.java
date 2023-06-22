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

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;


/**
 * Utility for working with SwtBot.
 */
public final class SwtBotUtil {
  private static final int TIMEOUT = 10000;
  private static final int PLAYBACK_DELAY = 100;

  /**
   * Initialize the bot's preferences.
   */
  public static void initializeBotPreferences() {
    // NOTE: the keyboard layout must match the keyboard used for the OS.
    // The default keyboard layouts are: EN_US, MAC_EN_US, EN_GB, MAC_EN_GB, FR_FR, DE_DE.
    // TF-69: "com.avaloq.test.swtbot.DE_CH" is a custom keyboard layout, saved in the package "com.avaloq.test.swtbot"
    SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
    SWTBotPreferences.KEYBOARD_STRATEGY = System.getProperty("org.eclipse.swtbot.keyboard.strategy", "org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy");
    SWTBotPreferences.PLAYBACK_DELAY = PLAYBACK_DELAY;
    SWTBotPreferences.TIMEOUT = TIMEOUT;
  }

  /**
   * Initializes an {@link SwtWorkbenchBot}.
   *
   * @return initialized {@link SwtWorkbenchBot}
   */
  public static SwtWorkbenchBot initializeBot() {
    initializeBotPreferences();
    return new SwtWorkbenchBot();
  }

  private SwtBotUtil() {}
}
