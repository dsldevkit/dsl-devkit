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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;


/**
 * AbstractXtextEditorTest provides convenient setup and access functionality for tests that require an xtext editor.
 */
@SuppressWarnings("nls")
public abstract class AbstractXtextEditorTest extends AbstractXtextMarkerBasedTest {

  private static final String EDITOR_MUST_NOT_BE_DIRTY = "Editor must not be dirty - this indicates state carried over";
  private static final String EDITOR_HAS_NO_DOCUMENT = "Editor has no document";
  private static final String EDITOR_COULD_NOT_BE_OPENED_WITH_URI = "Editor could not be opened with URI: ";

  protected static final String CR_LF = "\r\n";
  protected static final String LF = "\n";

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    // For Xtend-based tests there is no default test source associated with the test class
    TestSource testSource = getTestSource();
    if (testSource != null) {
      openEditor(testSource);
    }
  }

  @Override
  protected void afterAllTests() {
    closeOpenEditor();
    super.afterAllTests();
  }

  @Override
  protected void beforeApplyAssertions(final XtextTestSource testSource) {
    super.beforeApplyAssertions(testSource);
    openEditor(testSource);
  }

  @Override
  protected void afterValidate() {
    closeOpenEditor();
    super.afterValidate();
  }

  /**
   * Opens the editor with the given test source.
   *
   * @param testSource
   *          the test source to open, not {@code null}
   */
  private void openEditor(final TestSource testSource) {
    // if openEditor returns NULL, then one possible cause might be that the Activator
    // has not been set correctly in the presentation plug-in MANIFEST of that grammar.
    XtextEditor editor = getXtextTestUtil().openEditor(testSource.getUri());
    assertNotNull(EDITOR_COULD_NOT_BE_OPENED_WITH_URI + testSource.getUri(), editor);
    getTestInformation().putTestObject(XtextEditor.class, editor);
    assertNotNull(EDITOR_HAS_NO_DOCUMENT, getDocument());
    assertFalse(EDITOR_MUST_NOT_BE_DIRTY, getEditor().isDirty());
  }

  /**
   * Open editor of the test source with a given file name.
   *
   * @param fileName
   *          file name of the source to open editor for, must not be {@code null}
   */
  protected void openEditor(final String fileName) {
    openEditor(getTestSource(fileName));
  }

  /**
   * Closes the currently open editor.
   */
  private void closeOpenEditor() {
    final XtextEditor editor = getEditor();
    if (editor != null) {
      closeEditor(editor, false);
    }
  }

  /**
   * Returns the editor.
   *
   * @return the editor
   */
  protected XtextEditor getEditor() {
    return (XtextEditor) getTestInformation().getTestObject(XtextEditor.class);
  }

  /**
   * Closes the given editor-part - contrary to {@link org.eclipse.ui.texteditor.AbstractTextEditor#close(boolean)} this call is blocking!
   *
   * @param editor
   *          the editor to close
   * @param save
   *          true if should save before close, false otherwise
   */
  protected void closeEditor(final IEditorPart editor, final boolean save) {
    Object editorJobs = getTestUtil().getEditorJobFamily(editor);
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
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
   * Returns the viewer.
   *
   * @return the viewer
   */
  protected XtextSourceViewer getViewer() {
    return (XtextSourceViewer) getEditor().getInternalSourceViewer();
  }

  /**
   * Returns the document.
   *
   * @return the document
   */
  protected IXtextDocument getDocument() {
    return getEditor().getDocument();
  }

  /**
   * Insert the given value at offset in {@link #getDocument()}.
   *
   * @param offset
   *          the offset
   * @param value
   *          the value
   */
  protected void insertAtOffset(final int offset, final String value) {
    PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
      @Override
      public void run() {
        // the modify() is invoked to ensure a (fast-only) re-validation of the document is triggered
        getDocument().modify(new IUnitOfWork<Void, XtextResource>() {
          @Override
          public java.lang.Void exec(final XtextResource state) {
            try {
              getDocument().replace(offset, 0, value);
            } catch (BadLocationException e) {
              throw new WrappedException("Could not insert \"" + value + "\" at affset " + offset, e);
            }
            return null;
          }
        });
      }
    });
  }

  /**
   * Test that the editor does not allow "Save as...".
   *
   * @deprecated Provide this test method in an appropriate test class for the editor under test, if the editor shall not allow "Save as...".
   */
  @Deprecated
  public void testSaveAsDisallowed() {
    final XtextEditor editor = getEditor();
    assertFalse("Editor must not allow 'Save as...'", editor.isSaveAsAllowed());
  }

  @Override
  protected void waitForValidation() {
    // Editor tests frequently work by modifying the document. We first need to wait for the reconciler to run, otherwise we may
    // actually get results from before a document change is reflected in the document's resource, leading to spurious errors.
    // Note that the XtextReconciler runs with a delay of 500ms.
    waitForJobsOfFamily(XtextReconciler.class.getName());
    super.waitForValidation();
  }
}
