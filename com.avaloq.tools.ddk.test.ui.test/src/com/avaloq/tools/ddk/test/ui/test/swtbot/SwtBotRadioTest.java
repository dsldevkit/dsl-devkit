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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.test.ui.swtbot.SwtBotRadio;


/**
 * Provides test for the SwtBotRadio.
 * <p>
 * Uses a shell of its own instead of a preference page, so the test neither depends on the platform's menus nor changes workbench preferences. Its
 * buttons are looked up within that shell, as it need not be the active shell, e.g. when the workbench runs in the background on macOS.
 */
@SuppressWarnings("nls")
public class SwtBotRadioTest {

  private static final String SHELL_TITLE = "SwtBotRadioTest";

  private Shell shell;

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

    String getText() {
      return text;
    }
  }

  /**
   * Opens a shell with a group of radio buttons, the first one selected.
   */
  @BeforeEach
  public void openShell() {
    UIThreadRunnable.syncExec(() -> {
      shell = new Shell(Display.getDefault());
      shell.setText(SHELL_TITLE);
      shell.setLayout(new RowLayout(SWT.VERTICAL));
      for (final RadioWidgetInstance value : RadioWidgetInstance.values()) {
        Button radio = new Button(shell, SWT.RADIO);
        radio.setText(value.getText());
        radio.setSelection(value == RadioWidgetInstance.ALWAYS);
      }
      shell.pack();
      shell.open();
    });
  }

  /**
   * Disposes the shell opened for the test.
   */
  @AfterEach
  public void closeShell() {
    UIThreadRunnable.syncExec(() -> shell.dispose());
  }

  /**
   * Test if the method {@link com.avaloq.tools.ddk.test.ui.swtbot.SwtBotRadio#click()} works correctly.
   */
  @Test
  public void testSwtRadioButtonClick() {
    testRadioButtonClick(SwtBotRadio::new);
  }

  /**
   * Test if the method {@link org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio#click()} works correctly.
   */
  @Test
  public void testSWTRadioButtonClick() {
    testRadioButtonClick(SWTBotRadio::new);
  }

  /**
   * Selects a radio button with its {@link org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio#click()} method and asserts if it was actually selected.
   *
   * @param radioFactory
   *          creates the bot radio performing the click, must not be {@code null}
   */
  private void testRadioButtonClick(final Function<Button, ? extends SWTBotRadio> radioFactory) {
    SWTBot shellBot = new SWTBot(shell);
    RadioWidgetInstance radioToSelect = RadioWidgetInstance.PROMPT;
    assertTrue(shellBot.radio(RadioWidgetInstance.ALWAYS.getText()).isSelected(), "\"Always open\" must be selected initially.");
    assertFalse(shellBot.radio(radioToSelect.getText()).isSelected(), String.format("\"%s\" must not be selected initially.", radioToSelect.getText()));

    radioFactory.apply(shellBot.radio(radioToSelect.getText()).widget).click();
    assertTrue(shellBot.radio(radioToSelect.getText()).isSelected(), String.format("\"%s\" must be selected.", radioToSelect.getText()));
  }
}
