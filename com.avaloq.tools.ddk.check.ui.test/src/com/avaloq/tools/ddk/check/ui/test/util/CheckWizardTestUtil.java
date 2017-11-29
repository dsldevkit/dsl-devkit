/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.test.util;

import static com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot.BACK;
import static com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot.FINISH;
import static com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot.NEXT;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

import com.avaloq.tools.ddk.check.ui.wizard.Messages;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot;


/**
 * Util class for the Check project and Check catalog wizard tests.
 */
public final class CheckWizardTestUtil {

  // Next button disabled
  public static final boolean NEXT_DISABLED = false;
  // Next button enabled
  public static final boolean NEXT_ENABLED = true;
  // Back button enabled
  public static final boolean BACK_ENABLED = true;
  // Finish button disabled
  public static final boolean FINISH_DISABLED = false;
  // Finish button enabled
  public static final boolean FINISH_ENABLED = true;

  /** input strings for wizard testing. */
  private static final String VALID_CATALOG_NAME = "Cc";
  private static final String VALID_PACKAGE_NAME = "p.p";

  private CheckWizardTestUtil() {
    // Prevent class from being instantiated.
  }

  /**
   * Assert buttons are correctly enabled.
   *
   * @param nextIsEnabled
   *          is next button enabled
   * @param backIsEnabled
   *          is the back button enabled
   * @param finishIsEnabled
   *          is the finish button enabled
   * @param swtWizardBot
   *          the wizard
   */
  public static void assertButtonsEnabled(final boolean nextIsEnabled, final boolean backIsEnabled, final boolean finishIsEnabled, final SwtWizardBot swtWizardBot) {
    if (nextIsEnabled) {
      assertTrue("Next button is enabled", swtWizardBot.button(NEXT).isEnabled());
    } else {
      assertFalse("Next button is disabled", swtWizardBot.button(NEXT).isEnabled());
    }
    if (backIsEnabled) {
      assertTrue("Back button is enabled", swtWizardBot.button(BACK).isEnabled());
    } else {
      assertFalse("Back button is disabled", swtWizardBot.button(BACK).isEnabled());
    }
    if (finishIsEnabled) {
      assertTrue("Finish button is enabled", swtWizardBot.button(FINISH).isEnabled());
    } else {
      assertFalse("Finish button is disabled", swtWizardBot.button(FINISH).isEnabled());
    }
  }

  /**
   * Test if the buttons 'next', 'back' and 'finish' are correctly enabled/disabled depending on given project name.
   *
   * @param wizard
   *          the wizard
   * @param projectName
   *          the project name
   * @param nextButtonAssertion
   *          assertion about the 'next' button: {@code true} if expected to be enabled, else {@code false}
   */
  public static void projectName(final SwtWizardBot wizard, final String projectName, final Boolean nextButtonAssertion) {
    wizard.writeToTextField(Messages.PROJECT_NAME_LABEL, projectName);
    CheckWizardTestUtil.assertButtonsEnabled(nextButtonAssertion, BACK_ENABLED, FINISH_DISABLED, wizard);
  }

  /**
   * Test if the buttons 'next', 'back' and 'finish' are correctly enabled/disabled depending on given project name.
   *
   * @param wizard
   *          the wizard
   * @param projectName
   *          the project name
   * @param nextButtonAssertion
   *          assertion about the 'next' button: {@code true} if expected to be enabled, else {@code false}
   * @param finishButtonAssertion
   *          assertion about the 'next' button: {@code true} if expected to be enabled, else {@code false}
   */
  public static void projectName(final SwtWizardBot wizard, final String projectName, final Boolean nextButtonAssertion, final Boolean finishButtonAssertion) {
    wizard.writeToTextField(Messages.PROJECT_NAME_LABEL, projectName);
    CheckWizardTestUtil.assertButtonsEnabled(nextButtonAssertion, BACK_ENABLED, finishButtonAssertion, wizard);
  }

  /**
   * Test if the finish button is enabled if the package name is valid.
   *
   * @param wizard
   *          the wizard
   * @param packageName
   *          the package name
   * @param finishButtonAssertion
   *          boolean value indicating whether the finish button is expected to be enabled ({@code true}) or not ({@code false})
   */
  public static void packageName(final SwtWizardBot wizard, final String packageName, final Boolean finishButtonAssertion) {
    waitForGrammarFieldEnabled(wizard);
    wizard.selectInComboBox(Messages.GRAMMAR_FIELD_NAME_LABEL, 0);
    wizard.writeToTextField(Messages.CATALOG_FIELD_NAME_LABEL, VALID_CATALOG_NAME);
    wizard.writeToTextField(Messages.PACKAGE_NAME_LABEL, packageName);

    assertButtonsEnabled(NEXT_DISABLED, BACK_ENABLED, finishButtonAssertion, wizard);
  }

  /**
   * Test if the finish button is enabled if the catalog name is deprecated.
   *
   * @param wizard
   *          the wizard
   * @param catalogName
   *          the catalog name
   * @param finishButtonAssertion
   *          boolean value indicating whether the finish button is expected to be enabled ({@code true}) or not ({@code false})
   */
  public static void catalogName(final SwtWizardBot wizard, final String catalogName, final Boolean finishButtonAssertion) {
    waitForGrammarFieldEnabled(wizard);
    wizard.selectInComboBox(Messages.GRAMMAR_FIELD_NAME_LABEL, 0);
    wizard.writeToTextField(Messages.PACKAGE_NAME_LABEL, VALID_PACKAGE_NAME);
    wizard.writeToTextField(Messages.CATALOG_FIELD_NAME_LABEL, catalogName);

    assertButtonsEnabled(NEXT_DISABLED, BACK_ENABLED, finishButtonAssertion, wizard);
  }

  /**
   * Sets the grammar to the combo box entry at given index. Sets valid package and catalog names.
   *
   * @param wizard
   *          the wizard
   * @param grammarIndex
   *          the grammar index
   * @param finishButtonAssertion
   *          boolean value indicating whether the finish button is expected to be enabled ({@code true}) or not ({@code false})
   */
  public static void grammarName(final SwtWizardBot wizard, final int grammarIndex, final Boolean finishButtonAssertion) {
    waitForGrammarFieldEnabled(wizard);
    wizard.selectInComboBox(Messages.GRAMMAR_FIELD_NAME_LABEL, grammarIndex);
    wizard.writeToTextField(Messages.PACKAGE_NAME_LABEL, VALID_PACKAGE_NAME);
    wizard.writeToTextField(Messages.CATALOG_FIELD_NAME_LABEL, VALID_CATALOG_NAME);

    assertButtonsEnabled(NEXT_DISABLED, BACK_ENABLED, finishButtonAssertion, wizard);
  }

  /**
   * Waits until the grammar field combo box is enabled.
   *
   * @param wizard
   *          the wizard
   */
  public static void waitForGrammarFieldEnabled(final SwtWizardBot wizard) {
    boolean wizardIsActive = syncExec(new BoolResult() {
      @Override
      public Boolean run() {
        SWTBotShell wizardShell = wizard.activeShell();
        return wizardShell.widget.getData() instanceof WizardDialog;
      }
    });
    assertTrue("Wizard is active before waiting on grammar field", wizardIsActive);
    wizard.waitUntil(Conditions.widgetIsEnabled(wizard.comboBoxWithLabel(Messages.GRAMMAR_FIELD_NAME_LABEL)), 2 * SwtWizardBot.SWTBOT_TIMEOUT);
  }

}
