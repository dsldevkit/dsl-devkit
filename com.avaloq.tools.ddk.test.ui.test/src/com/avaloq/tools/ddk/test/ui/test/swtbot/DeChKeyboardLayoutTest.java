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

import org.eclipse.swtbot.swt.finder.keyboard.KeyboardLayout;
import org.junit.jupiter.api.Test;


/**
 * Checks that the custom DE_CH keyboard layout maps all characters of a Swiss German keyboard.
 * <p>
 * The layout is checked directly instead of by typing, as SWTBot fixes its keyboard layout globally on first use, which would affect all later tests.
 */
@SuppressWarnings("nls")
public class DeChKeyboardLayoutTest {
  private static final String DE_CH_LAYOUT = "com.avaloq.tools.ddk.test.ui.swtbot.DE_CH";
  private static final String CHARACTERS = "¨üöä$,.-<!èéà£;:_>[]ö{},.-\\'^+\"*ç%&/()=?`¦@#°§¬|¢´~zyZY";

  /**
   * Tests that every character has a keystroke in the DE_CH layout which maps back to the same character.
   */
  @Test
  public void testDeChKeyboardLayout() {
    KeyboardLayout layout = KeyboardLayout.getKeyboardLayout(DE_CH_LAYOUT);
    for (char ch : CHARACTERS.toCharArray()) {
      assertEquals(ch, layout.toCharacter(layout.keyStrokeFor(ch)), "Keystroke for '" + ch + "' must map back to the same character");
    }
  }
}
