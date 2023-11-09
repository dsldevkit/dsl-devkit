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
package com.avaloq.tools.ddk.xtext.test;

import java.lang.reflect.Method;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorPart;


/**
 * Test utility for ACF-Xtext-Tests.
 * The implementation of this utility abstracts from the ice Server and the ice context and makes it transparent.
 * Sources can be added on the fly using {@link addSourceToRepository} and are subsequently treated as if they were stored on ice.
 */
public abstract class AbstractTestUtil { // NOPMD we really do want default implementations here.

  private final ITestProjectManager testProjectManager = createTestProjectManager();

  /**
   * Get the test project manager.
   *
   * @return {@link ITestProjectManager}
   */
  protected ITestProjectManager getTestProjectManager() {
    return testProjectManager;
  }

  /**
   * Create a {@link ITestProjectManager}. The default implementation returns an instance of {@link XtextTestProjectManager}. Subclasses should override this
   * method if they need another implementation of {@link ITestProjectManager}.
   *
   * @return {@link ITestProjectManager} suitable for this class
   */
  protected ITestProjectManager createTestProjectManager() {
    return new XtextTestProjectManager();
  }

  /**
   * Wait for jobs of a given family to finish.
   *
   * @param family
   *          to wait for.
   */
  protected void waitForJobsOfFamily(final Object family) {
    boolean wasInterrupted;
    do {
      try {
        Job.getJobManager().join(family, null);
        wasInterrupted = false;
      } catch (OperationCanceledException e) {
        wasInterrupted = true;
      } catch (InterruptedException e) {
        wasInterrupted = true;
      }
    } while (wasInterrupted);
  }

  /**
   * Returns an object that represents job family of the given editor.
   *
   * @param editor
   *          editor instance, can be {@code null}
   * @return job family object or {@code null} if does not exist
   */
  public Object getEditorJobFamily(final IEditorPart editor) {
    if (editor == null) {
      return null;
    }
    Class<?> editorClass = editor.getClass();
    Object jobFamily = null;
    try {
      // Job family is represented by the edited source version
      Method method = editorClass.getMethod("getSourceVersion", (Class<?>[]) null);
      jobFamily = method.invoke(editor, (Object[]) null);
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      // Do nothing, return null
    }
    return jobFamily;
  }

  /**
   * Wait for synchronization jobs on opening/closing the editor.
   *
   * @param editor
   *          editor part
   */
  protected void waitForEditorJobs(final IEditorPart editor) {
    Object jobFamily = getEditorJobFamily(editor);
    if (jobFamily != null) {
      waitForJobsOfFamily(jobFamily);
    }
  }

}
