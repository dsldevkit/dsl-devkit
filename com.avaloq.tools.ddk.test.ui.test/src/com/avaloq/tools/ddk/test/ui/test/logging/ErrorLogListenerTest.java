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
package com.avaloq.tools.ddk.test.ui.test.logging;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.test.core.util.ErrorLogListener;


/**
 * Tests the {@link ErrorLogListener}.
 */
class ErrorLogListenerTest {
  private ErrorLogListener errorLogListener;

  /**
   * Sets up the {@link ErrorLogListener} under test.
   */
  @BeforeEach
  void setUp() {
    errorLogListener = new ErrorLogListener();
    errorLogListener.register();
  }

  /**
   * Tears down the {@link ErrorLogListener} under test.
   */
  @AfterEach
  void tearDown() {
    errorLogListener.unregister();
  }

  /**
   * Test that {@link NullPointerException}'s are ignored.
   *
   * @throws InterruptedException
   *           the exception that is thrown if the test job was interrupted
   */
  @Test
  @SuppressWarnings("nls")
  void testIgnoringExceptionLocations() throws InterruptedException {
    assertFalse(errorLogListener.isExceptionLogged(NullPointerException.class), "NullPointerException must not have been logged.");
    errorLogListener.ignoreException(NullPointerException.class, "com.avaloq.tools.ddk.test.core.util.ErrorLogListenerTest");

    final Job job = new Job("testIgnoringExceptionLocations") {

      @Override
      @SuppressWarnings("PMD.AvoidThrowingNullPointerException")
      protected IStatus run(final IProgressMonitor monitor) {
        // We really want to thrown a null pointer exception here because we test that such exceptions are ignored.
        throw new NullPointerException();
      }
    };
    job.schedule();
    job.join();

    assertTrue(errorLogListener.isExceptionLogged(NullPointerException.class), "NullPointerException must have been logged.");
  }
}
