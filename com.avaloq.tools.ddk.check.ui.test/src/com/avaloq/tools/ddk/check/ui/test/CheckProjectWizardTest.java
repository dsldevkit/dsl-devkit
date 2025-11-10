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
package com.avaloq.tools.ddk.check.ui.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.util.CheckWizardTestUtil;
import com.avaloq.tools.ddk.check.ui.wizard.Messages;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot;


/**
 * The CheckProjectWizardTest tests the Check Project Wizard.
 */
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckProjectWizardTest {

  /** This is the name of the project wizard. It's the name SWTBot uses to look up the wizard. */
  private static final String CHECK_PROJECT_WIZARD_NAME = "Check Project";

  /** input strings for wizard testing. */
  private static final String CORRECT_PROJECT_NAME = "p.n";

  private SwtWizardBot wizard;

  /**
   * Start again the Check project wizard before every test.
   */
  @BeforeEach
  public void setUp() {
    wizard = new SwtWizardBot();
    wizard.openNewWizard(CHECK_PROJECT_WIZARD_NAME);
  }

  /**
   * Check if the project wizard is available.
   */
  @Test
  public void testCheckProjectWizardIsAvailable() {
    assertNotNull(wizard, "the project wizard was found");
    CheckWizardTestUtil.assertButtonsEnabled(false, true, false, wizard);
  }

  /**
   * Test if the buttons 'next', 'back' and 'finish' are correctly enabled/disabled.
   */
  @Test
  public void testProjectNameInvalid() {
    CheckWizardTestUtil.projectName(wizard, "", CheckWizardTestUtil.NEXT_DISABLED);
    CheckWizardTestUtil.projectName(wizard, ".project.name", CheckWizardTestUtil.NEXT_DISABLED);
    CheckWizardTestUtil.projectName(wizard, "Project.name", CheckWizardTestUtil.NEXT_DISABLED);
    CheckWizardTestUtil.projectName(wizard, "projectName", CheckWizardTestUtil.NEXT_DISABLED);
    CheckWizardTestUtil.projectName(wizard, "project. name", CheckWizardTestUtil.NEXT_DISABLED);
    CheckWizardTestUtil.projectName(wizard, "project..name", CheckWizardTestUtil.NEXT_DISABLED);
  }

  /**
   * Tests that the 'finish' button is not enabled in the project page.
   */
  @BugTest("AIG-490")
  public void testFinishButtonDisabledInProjectPage() {
    CheckWizardTestUtil.projectName(wizard, "valid.project.name", CheckWizardTestUtil.NEXT_ENABLED, CheckWizardTestUtil.FINISH_DISABLED);
  }

  /**
   * Test if the buttons 'next', 'back' and 'finish' are correctly enabled/disabled.
   */
  @Test
  public void testProjectNameValid() {
    CheckWizardTestUtil.projectName(wizard, "project.name", CheckWizardTestUtil.NEXT_ENABLED);
    CheckWizardTestUtil.projectName(wizard, "projectname", CheckWizardTestUtil.NEXT_ENABLED);
  }

  /**
   * Test if the buttons 'next', 'back' and 'finish' are correctly enabled/disabled.
   */
  @Test
  public void fieldValuesAfterPageChange() {
    wizard.writeToTextField(Messages.PROJECT_NAME_LABEL, CORRECT_PROJECT_NAME);
    wizard.changeToPreviousPage();
    wizard.changeToNextPage();
    assertEquals(CORRECT_PROJECT_NAME, wizard.textWithLabel(Messages.PROJECT_NAME_LABEL).getText(), "Project input stays unchanged");
    wizard.changeToNextPage();
    assertEquals(CORRECT_PROJECT_NAME, wizard.textWithLabel(Messages.PACKAGE_NAME_LABEL).getText(), "Initially, package name equals to project name");
  }

  /**
   * Tests that applying the next button changes the wizard page.
   */
  @BugTest("AIG-479")
  public void testNextButtonChangesPage() {
    wizard.writeToTextField(Messages.PROJECT_NAME_LABEL, "a.b");
    SWTBotShell projectPage = wizard.shell(Messages.PROJECT_WIZARD_WINDOW_TITLE);
    wizard.changeToNextPage();
    SWTBotShell catalogPage = wizard.shell(Messages.PROJECT_WIZARD_WINDOW_TITLE);
    assertNotSame(projectPage, catalogPage, "Next button changed page");
  }

  /**
   * Close the wizard after every test.
   */
  @AfterEach
  public void tearDown() {
    wizard.closeWizard();
  }

}
