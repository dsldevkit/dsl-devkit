/*
 * Copyright (c) Avaloq Licence AG
 * Schwerzistrasse 6, 8807 Freienbach, Switzerland, http://www.avaloq.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.tools.ddk.test.ui.swtbot;

import java.util.List;
import java.util.function.Predicate;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


/**
 * SWTBot in https://bugs.eclipse.org/bugs/show_bug.cgi?id=475346 fixed the limbo shell.
 * But they've missed the "Quick Access" shell.
 */
class FixedDefaultWorkbench {

  /** @see org.eclipse.e4.ui.internal.workbench.swt.PartRenderingEngine.getLimboShell() */
  private static final String LIMBO_SHELL = "PartRenderingEngine's limbo"; //$NON-NLS-1$
  private static final String QUIK_ACCESS_SHELL = "Quick Access"; //$NON-NLS-1$

  /** The bot that may be used to drive the workbench. */
  private final SWTWorkbenchBot bot;

  /**
   * Creates an instance of the default workbench.
   *
   * @param bot
   *          the bot that can drive the workbench.
   */
  FixedDefaultWorkbench(final SWTWorkbenchBot bot) {
    this.bot = bot;
  }

  /**
   * Reset active perspective.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench resetActivePerspective() {
    UIThreadRunnable.syncExec(new VoidResult() {
      @Override
      public void run() {
        activePage().resetPerspective();
      }
    });
    return this;
  }

  /**
   * Reset workbench.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench resetWorkbench() {
    return closeAllShells().saveAllEditors().closeAllEditors();
  }

  /**
   * Close all shells.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench closeAllShells() {
    return closeShellsMatchingName(v -> true);
  }

  /**
   * Close all shells matching given name predicate.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench closeShellsMatchingName(final Predicate<String> predicate) {
    SWTBotShell[] shells = bot.shells();
    for (SWTBotShell shell : shells) {
      if (!isEclipseShell(shell) && !isLimboShell(shell) && !isQuickAccess(shell) && predicate.test(shell.getText())) {
        shell.close();
      }
    }
    return this;
  }

  /**
   * Save all editors.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench saveAllEditors() {
    List<? extends SWTBotEditor> editors = bot.editors();
    for (SWTBotEditor editor : editors) {
      editor.save();
    }
    return this;
  }

  /**
   * Close all editors.
   *
   * @return the fixed default workbench
   */
  FixedDefaultWorkbench closeAllEditors() {
    List<? extends SWTBotEditor> editors = bot.editors();
    for (SWTBotEditor editor : editors) {
      editor.close();
    }
    return this;
  }

  /**
   * Checks if eclipse shell.
   *
   * @param shell
   *          the shell
   * @return true, if eclipse shell
   */
  private boolean isEclipseShell(final SWTBotShell shell) {
    return getActiveWorkbenchWindowShell() == shell.widget;
  }

  /**
   * Checks if is limbo shell.
   *
   * @param shell
   *          the shell
   * @return true, if is limbo shell
   */
  private boolean isLimboShell(final SWTBotShell shell) {
    return shell.getText().equals(LIMBO_SHELL);
  }

  /**
   * Checks if is quick access shell.
   *
   * @param shell
   *          the shell
   * @return true, if is quick access shell
   */
  private boolean isQuickAccess(final SWTBotShell shell) {
    return shell.getText().equals(QUIK_ACCESS_SHELL);
  }

  private IWorkbenchWindow getActiveWorkbenchWindow() {
    return UIThreadRunnable.syncExec(bot.getDisplay(), new Result<IWorkbenchWindow>() {
      @Override
      public IWorkbenchWindow run() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      }
    });
  }

  private Widget getActiveWorkbenchWindowShell() {
    return getActiveWorkbenchWindow().getShell();
  }

  /**
   * Active page.
   *
   * @return the workbench page
   */
  private IWorkbenchPage activePage() {
    return getActiveWorkbenchWindow().getActivePage();
  }

}

/* Copyright (c) Avaloq Licence AG */