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
package com.avaloq.tools.ddk.xtext.test.ui;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.asyncExec;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.test.ui.junit.runners.SwtBotRecordingTestRunner;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractTest;
import com.avaloq.tools.ddk.xtext.ui.util.UiAssert;


/**
 * Base class for testing a running instance of {@link IEditorPart} at the UI-level. <br>
 * Does <i>not</i> open any editor before all tests run. <br>
 * Provides an instance of {@link SwtWorkbenchBot} via {@link #getBot()}. <br>
 * Provides an instance of {@link IEditorPart} via {@link #openEditor(IFile, String, boolean)} for opening an editor of arbitrary kind based on input and editor
 * ID.<br>
 * Provides {@link #closeEditor(IEditorPart, boolean)} for closing the running editor instance.
 */
@RunWith(SwtBotRecordingTestRunner.class)
public abstract class AbstractUiTest extends AbstractTest {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(AbstractUiTest.class);
  private static final int TEST_TIMEOUT = 600000; // 10 minutes timeout for each test

  /**
   * Enforces the junit {@link Timeout} rule for each UI test.
   */
  @Rule
  // CHECKSTYLE:CHECK-OFF VisibilityModifier
  public Timeout globalTimeout = Timeout.millis(TEST_TIMEOUT);

  // CHECKSTYLE:CHECK-ON VisibilityModifier

  /**
   * Initialize the {@link org.eclipse.swtbot.swt.finder.SWTBot}.
   */
  protected void initializeSWTBot() {
    getTestInformation().putTestObject(SwtWorkbenchBot.class, SwtBotUtil.initializeBot());
  }

  /**
   * The default implementation returns the name of the source model calling {@link getTestSourceModelName} and adds the default file extension for the grammar
   * of this test. A test class needs to override this, if the name of the main test source file differs from the default.
   *
   * @return the name of the main test source file
   */
  @Override
  protected String getTestSourceFileName() {
    return this.getTestSourceModelName() + "." + getFileExtension();
  }

  /** {@inheritDoc} */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    initializeSWTBot();
    getBot().closeWelcomePage();
  }

  /**
   * Get the file extension to use for test source files.
   *
   * @return the file extension.
   */
  protected abstract String getFileExtension();

  /**
   * Get an SWT Workbench bot.
   *
   * @return the bot
   */
  public SwtWorkbenchBot getBot() {
    return (SwtWorkbenchBot) getTestInformation().getTestObject(SwtWorkbenchBot.class);
  }

  /**
   * Attempts to open and return the {@link IEditorPart} with the given ID for the given input file.
   *
   * @param file
   *          the input file
   * @param editorId
   *          the editor ID
   * @param activate
   *          true to activate the editor, false otherwise
   * @return the editor part or null
   */
  protected IEditorPart openEditor(final IFile file, final String editorId, final boolean activate) {
    UiAssert.isNotUiThread();
    IEditorPart editor = UIThreadRunnable.syncExec(getBot().getDisplay(), new Result<IEditorPart>() {
      @Override
      public IEditorPart run() {
        try {
          IWorkbench workbench = PlatformUI.getWorkbench();
          IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
          final int matchFlags = IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT;
          final IEditorInput input = new FileEditorInput(file);
          IEditorPart editor = activePage.openEditor(input, editorId, activate, matchFlags);
          editor.setFocus();
          return editor;
        } catch (PartInitException e) {
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(e.getMessage(), e);
          }
        }
        return null;
      }
    });

    waitForEditorJobs(editor);
    return editor;

  }

  /**
   * Closes the given editor-part.
   *
   * @param editor
   *          the editor to close
   * @param save
   *          true if should save before close, false otherwise
   */
  protected void closeEditor(final IEditorPart editor, final boolean save) {
    UiAssert.isNotUiThread();
    Object editorJobs = getTestUtil().getEditorJobFamily(editor);
    UIThreadRunnable.syncExec(getBot().getDisplay(), new VoidResult() {
      @Override
      public void run() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editor, save);
      }
    });
    if (editorJobs != null) {
      waitForJobsOfFamily(editorJobs);
    }
  }

  /**
   * Opens the editor asynchronously.
   *
   * @param file
   *          the file
   * @param editorId
   *          the editor id
   * @param activate
   *          true to activate, false otherwise
   */
  protected void openEditorAsync(final IFile file, final String editorId, final boolean activate) {
    asyncExec(new VoidResult() {
      @Override
      public void run() {
        try {
          IWorkbench workbench = PlatformUI.getWorkbench();
          IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
          final int matchFlags = IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT;
          final IEditorInput input = new FileEditorInput(file);
          IEditorPart editor = activePage.openEditor(input, editorId, activate, matchFlags);
          editor.setFocus();
          waitForEditorJobs(editor);
        } catch (PartInitException e) {
          if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(e.getMessage(), e);
          }
        }
      }
    });
  }

  /**
   * @param editorId
   *          the editor id
   * @return true if the editor being tested is open, false otherwise
   */
  protected boolean isEditorOpen(final String editorId) {
    for (SWTBotEditor e : getBot().editors()) {
      if (e.getReference().getId().equals(editorId)) {
        return true;
      }
    }
    return false;
  }

}
