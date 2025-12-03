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
package com.avaloq.tools.ddk.test.ui.swtbot;

import static com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotWizardUtil.selectItem;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;


/**
 * An SwtBot to manipulate a wizard.
 */
@SuppressWarnings("nls")
public class SwtWizardBot extends SwtWorkbenchBot {

  public static final int SWTBOT_TIMEOUT = 20000;

  public static final int UNDEFINED_COMBO_BOX_INDEX = -1;

  /** button labels. */
  public static final String FINISH = "Finish";
  public static final String BACK = "< Back";
  public static final String NEXT = "Next >";
  public static final String CANCEL = "Cancel";

  private static final Logger LOG = LogManager.getLogger("SwtWizardBot");

  /**
   * Close the wizard.
   */
  public void closeWizard() {
    SWTBotShell activeShell = activeShell();
    boolean wizardIsActive = isWizard(activeShell);
    assertTrue("Wizard is active on close", wizardIsActive);
    activeShell.close();
  }

  /**
   * Close all wizards.
   */
  public void closeAllWizards() {
    Arrays.stream(shells()).filter(SwtWizardBot::isWizard).forEach(wizardShell -> wizardShell.close());
  }

  /**
   * Checks that the given shell is a wizard shell.
   *
   * @param shell
   *          the shell, must not be {@code null}
   * @return {@code true} if the shell is a wizard shell, {@code false} otherwise
   */
  private static boolean isWizard(final SWTBotShell shell) {
    return syncExec(() -> shell.widget.getData() instanceof WizardDialog);
  }

  /**
   * Write some input in the field with the given label.
   *
   * @param fieldName
   *          the field in which the input is written
   * @param input
   *          an input
   */
  public void writeToTextField(final String fieldName, final String input) {
    SWTBotText text = textWithLabel(fieldName);
    text.setText(input);
  }

  /**
   * Makes a selection in the combo box with given label. Selects element of given index.
   *
   * @param boxLabel
   *          the box label
   * @param selectionIndex
   *          the selection index
   * @throws IndexOutOfBoundsException
   *           if the selection index is out of bounds
   */
  public void selectInComboBox(final String boxLabel, final int selectionIndex) {
    SWTBotCombo combo = comboBoxWithLabel(boxLabel);
    if (selectionIndex >= combo.itemCount()) {
      throw new IndexOutOfBoundsException(NLS.bind("Illegal selection provided, {0} items exist", combo.itemCount()));
    } else if (selectionIndex == UNDEFINED_COMBO_BOX_INDEX) {
      return; // Do nothing, nothing should be selected
    }
    combo.setSelection(selectionIndex);
  }

  /**
   * If available and enabled change to previous wizard page.
   */
  public void changeToPreviousPage() {
    if (button(BACK).isEnabled()) {
      clickButton(BACK);
    } else {
      LOG.error("the wizard page has no previous page.");
    }
  }

  /**
   * If available and enabled change to next wizard page.
   */
  public void changeToNextPage() {
    if (button(NEXT).isEnabled()) {
      clickButton(NEXT);
    } else {
      LOG.error("the wizard page has no next page.");
    }
  }

  /**
   * Open the New wizard of given name.
   *
   * @param wizardName
   *          name of the wizard to be activated
   */
  public void openNewWizard(final String wizardName) {
    menu("File").menu("New").menu("Other...").click();
    activateWizard(wizardName);
  }

  /**
   * Open the Export wizard of given name.
   *
   * @param wizardName
   *          name of the wizard to be activated
   */
  public void openExportWizard(final String wizardName) {
    menu("File").menu("Export...").click();
    activateWizard(wizardName);
  }

  /**
   * Activate the wizard of given name in the active shell.
   *
   * @param wizardName
   *          name of the wizard to be activated
   */
  private void activateWizard(final String wizardName) {
    assertTrue("A wizard dialog must be active", syncExec(() -> {
      SWTBotShell wizardShell = activeShell();
      return wizardShell.widget.getData() instanceof WizardDialog;
    }));
    assertTrue("Wizard '" + wizardName + "' does not exist.", syncExec(() -> {
      return selectItem(tree(), wizardName);
    }));
    clickButton(NEXT);
  }

}
