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
package com.avaloq.tools.ddk.test.ui.test.swtbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;


/**
 * Checks if the DE_CH keyboard layout works correctly by typing special characters in a test Eclipse editor.
 */
@SuppressWarnings("nls")
class DeChKeyboardLayoutTest {
  private static final String EXPECTED_RESULT = "¨üöä$,.-<!èéà£;:_>[]ö{},.-\\'^+\"*ç%&/()=?`¦@#°§¬|¢´~zyZY";

  /**
   * Tests com.avaloq.test.swtbot.DE_CH.
   */
  @Test
  void testDeChKeyboardLayout() {
    SWTBotPreferences.KEYBOARD_LAYOUT = "com.avaloq.test.swtbot.DE_CH";
    SWTBotPreferences.KEYBOARD_STRATEGY = "org.eclipse.swtbot.swt.finder.keyboard.MockKeyboardStrategy";
    SwtWorkbenchBot bot = new SwtWorkbenchBot();

    bot.closeWelcomePage();
    bot.menu("File").menu("New").menu("Untitled Text File").click();
    SWTBotEclipseEditor editor = bot.activeEditor().toTextEditor();
    editor.setFocus();
    editor.typeText(EXPECTED_RESULT);
    String actualResult = editor.getText();
    bot.closeAllEditors();

    assertEquals(EXPECTED_RESULT, actualResult, "Written and read characters must exactly match");
  }
}
