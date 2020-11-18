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
package com.avaloq.tools.ddk.test.ui.test.logging;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avaloq.tools.ddk.test.core.util.ErrorLogListener;


/**
 * Tests the {@link ErrorLogListener}.
 */
public class ErrorLogListenerTest {
  private ErrorLogListener errorLogListener;

  /**
   * Sets up the {@link ErrorLogListener} under test.
   */
  @Before
  public void setUp() {
    errorLogListener = new ErrorLogListener();
    errorLogListener.register();
  }

  /**
   * Tears down the {@link ErrorLogListener} under test.
   */
  @After
  public void tearDown() {
    errorLogListener.unregister();
  }

  /**
   * Test that {@link NullPointerException}'s are ignored.
   *
   * @throws InterruptedException
   *           the exception that is thrown if the test job was interrupted
   */
  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testIgnoringExceptionLocations() throws InterruptedException {
    assertFalse("NullPointerException must not have been logged.", errorLogListener.isExceptionLogged(NullPointerException.class));
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

    assertTrue("NullPointerException must have been logged.", errorLogListener.isExceptionLogged(NullPointerException.class));
    assertFalse("NullPointerException must have been ignored.", errorLogListener.getLoggedExceptions().contains(NullPointerException.class));
  }
}
