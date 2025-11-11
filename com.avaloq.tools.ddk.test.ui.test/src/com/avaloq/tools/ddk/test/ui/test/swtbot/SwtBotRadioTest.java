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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.avaloq.tools.ddk.test.core.Issue;
import com.avaloq.tools.ddk.test.core.jupiter.IssueAwareRule;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.util.PreferenceUtil;


/**
 * Provides test for the SwtBotRadio.
 */
@SuppressWarnings("nls")
public class SwtBotRadioTest {

  @RegisterExtension
  // CHECKSTYLE:CHECK-OFF VisibilityModifier
  public final IssueAwareRule rule = IssueAwareRule.getInstance();
  // CHECKSTYLE:CHECK-ON VisibilityModifier

  private static final String[] PREFERENCES_PATH = new String[] {"General", "Perspectives"};

  /**
   * Represents the different radio buttons and text attributes of the radio widget.
   */
  enum RadioWidgetInstance {
    ALWAYS("Always open"),
    NEVER("Never open"),
    PROMPT("Prompt");

    private final String text;

    RadioWidgetInstance(final String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }
  }

  /**
   * Test if the method {@link com.avaloq.tools.ddk.test.ui.swtbot.SwtBotRadio#click()} works correctly.
   */
  @Test
  public void testSwtRadioButtonClick() {
    SwtWorkbenchBot bot = new SwtWorkbenchBot();
    bot.resetWorkbench();
    testRadioButtonClick(bot);
  }

  /**
   * Test if the method {@link org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio#click()} works correctly.
   */
  @Test
  @Issue(value = "DSL-371", fixed = false)
  public void testSWTRadioButtonClick() {
    SWTWorkbenchBot bot = new SWTWorkbenchBot();
    bot.resetWorkbench();
    testRadioButtonClick(bot);
  }

  /**
   * Selects a radio button with its {@link org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio#click()} method and asserts if it was actually selected.
   *
   * @param bot
   *          the workbench bot which performs the click, must not be {@code null}
   */
  private void testRadioButtonClick(final SWTWorkbenchBot bot) {
    RadioWidgetInstance previouslySelectedRadio = null;
    RadioWidgetInstance radioToSelect = null;

    // Determine which radio button is already selected and which to select.
    PreferenceUtil.openPreferenceDialog(bot, PREFERENCES_PATH);
    for (final RadioWidgetInstance value : RadioWidgetInstance.values()) {
      if (bot.radio(value.getText()).isSelected()) {
        previouslySelectedRadio = value;
      } else {
        radioToSelect = value;
      }
    }

    // Assert that the buttons were found.
    assertNotNull(previouslySelectedRadio, "previouslySelectedRadio must not be null.");
    assertNotNull(radioToSelect, "radioToSelect must not be null.");

    // Select radio button and check if it was actually selected.
    bot.radio(radioToSelect.getText()).click();
    assertTrue(bot.radio(radioToSelect.getText()).isSelected(), String.format("\"%s\" must be selected.", radioToSelect.getText()));

    // Close and re-open preference page and check again.
    PreferenceUtil.closePreferenceDialog(bot);
    PreferenceUtil.openPreferenceDialog(bot, PREFERENCES_PATH);
    assertTrue(bot.radio(radioToSelect.getText()).isSelected(), String.format("\"%s\" must not be de-selected again.", radioToSelect.getText()));

    // Reset
    bot.radio(previouslySelectedRadio.getText()).click();
    PreferenceUtil.closePreferenceDialog(bot);
  }
}
