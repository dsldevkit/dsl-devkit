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
package com.avaloq.tools.ddk.test.core;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


/**
 * A test watcher that logs the start and end of each test, as well as its success or failure.
 */
public final class LoggingRule extends TestWatcher {

  private static final Logger LOGGER = Logger.getLogger(LoggingRule.class);

  /** The singleton instance, or {@code null} if not cached. */
  private static LoggingRule instance;

  /**
   * Creates a new instance of {@link LoggingRule}.
   */
  private LoggingRule() {
    // prevent instantiation
  }

  /**
   * Returns a shared singleton instance.
   * 
   * @return a shared instance, never {@code null}
   */
  public static LoggingRule getInstance() {
    synchronized (LoggingRule.class) {
      if (instance == null) {
        instance = new LoggingRule();
      }
      return instance;
    }
  }

  /**
   * Returns the name of a test to be logged.
   * 
   * @param description
   *          the description, must not be {@code null}
   * @return the description name, never {@code null}
   */
  private String getDescriptionName(final Description description) {
    return description.getClassName() + '.' + description.getMethodName();
  }

  /** {@inheritDoc} */
  @Override
  public void starting(final Description description) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("STARTING: " + getDescriptionName(description));
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void finished(final Description description) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("FINISHED: " + getDescriptionName(description));
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void succeeded(final Description description) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("SUCCEEDED: " + getDescriptionName(description));
    }
  }

  @Override
  protected void failed(final Throwable e, final Description description) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("FAILED: " + getDescriptionName(description));
    }
  }
}

