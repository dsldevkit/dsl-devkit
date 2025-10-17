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

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.junit.runners.SwtBotRecordingXtextTestRunner;
import com.avaloq.tools.ddk.xtext.ui.util.UiAssert;


/**
 * Base class for testing a running instance of {@link IEditorPart} at the UI-level. <br>
 * Does <i>not</i> open any editor before all tests run. <br>
 * Provides an instance of {@link SwtWorkbenchBot} via {@link #getBot()}. <br>
 * Provides an instance of {@link IEditorPart} via {@link #openEditor(org.eclipse.core.resources.IFile, String, boolean)} for opening an editor of arbitrary
 * kind based on input and editor
 * ID.<br>
 * Provides {@link #closeEditor(IEditorPart, boolean)} for closing the running editor instance.
 */
@RunWith(SwtBotRecordingXtextTestRunner.class)
public abstract class AbstractXtextUiTest extends AbstractXtextTest {

  private static final long EDITOR_ENABLED_TIMEOUT = 30000L;

  /**
   * Initialize the {@link org.eclipse.swtbot.swt.finder.SWTBot}.
   */
  public void initializeSWTBot() {
    getTestInformation().putTestObject(SwtWorkbenchBot.class, SwtBotUtil.initializeBot());
  }

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    initializeSWTBot();
    getBot().closeWelcomePage();
    getBot().resetActivePerspective();
    IEditorPart editor = openEditor(getTestSource().getUri(), true);
    SWTBotEditor swtBotEditor = getBot().editorByTitle(editor.getTitle());
    getTestInformation().putTestObject(SWTBotEclipseEditor.class, new AcfSwtBotEclipseEditor(swtBotEditor.getReference(), getBot()));
  }

  public SwtWorkbenchBot getBot() {
    return (SwtWorkbenchBot) getTestInformation().getTestObject(SwtWorkbenchBot.class);
  }

  @Override
  protected void afterAllTests() {
    final SWTBotEclipseEditor editor = getEditor();
    if (editor != null) {
      final IEditorPart editorPart = editor.getReference().getEditor(false);
      Object editorJobs = getTestUtil().getEditorJobFamily(editorPart);
      editor.close();
      if (editorJobs != null) {
        waitForJobsOfFamily(editorJobs);
      }
    }
    super.afterAllTests();
  }

  /**
   * Opens an {@link IEditorPart} for a provided {@link org.eclipse.emf.common.util.URI}.
   *
   * @param uri
   *          {@link org.eclipse.emf.common.util.URI} to open editor for
   * @param activate
   *          true if focus is to be set to the opened editor
   * @return {@link IEditorPart} created
   */
  private IEditorPart openEditor(final org.eclipse.emf.common.util.URI uri, final boolean activate) {
    UiAssert.isNotUiThread();
    final IEditorPart editorPart = UIThreadRunnable.syncExec(getBot().getDisplay(), new Result<IEditorPart>() {
      @Override
      public IEditorPart run() {
        IEditorPart editor = getXtextTestUtil().get(GlobalURIEditorOpener.class).open(uri, activate);
        editor.setFocus();
        return editor;
      }
    });

    waitForEditorJobs(editorPart);

    getBot().waitUntil(new DefaultCondition() {
      @Override
      public boolean test() {
        if (editorPart.getEditorSite() != null && editorPart.getEditorInput() != null) {
          IEditorInput input = editorPart.getEditorInput();
          if (input instanceof IFileEditorInput) {
            return !((IFileEditorInput) input).getFile().isReadOnly();
          }
        }
        return false;
      }

      @Override
      public String getFailureMessage() {
        return "Editor must be initialized."; //$NON-NLS-1$
      }
    }, EDITOR_ENABLED_TIMEOUT);

    return editorPart;
  }

  public SWTBotEclipseEditor getEditor() {
    return (SWTBotEclipseEditor) getTestInformation().getTestObject(SWTBotEclipseEditor.class);
  }

}
