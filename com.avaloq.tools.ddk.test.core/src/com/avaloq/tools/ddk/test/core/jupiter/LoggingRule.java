/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.test.core.jupiter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


/**
 * A test watcher that logs the start and end of each test, as well as its success or failure.
 */
@SuppressWarnings("nls")
public final class LoggingRule implements TestWatcher, BeforeEachCallback, AfterEachCallback {

  private static final Logger LOGGER = LogManager.getLogger(LoggingRule.class);

  /** The singleton instance, or {@code null} if not cached. */
  private static LoggingRule instance;

  private static final Object LOCK = new Object();

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
    synchronized (LOCK) {
      if (instance == null) {
        instance = new LoggingRule();
      }
      return instance;
    }
  }

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("STARTING: " + getDescriptionName(context));
    }
  }

  /**
   * Returns the name of a test to be logged.
   *
   * @param description
   *          the description, must not be {@code null}
   * @return the description name, never {@code null}
   */
  private String getDescriptionName(final ExtensionContext context) {
    return context.getRequiredTestClass().getSimpleName() + '.' + context.getRequiredTestMethod().getName();
  }

  @Override
  public void testSuccessful(final ExtensionContext context) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("SUCCEEDED: " + getDescriptionName(context));
    }
  }

  @Override
  public void testFailed(final ExtensionContext context, final Throwable cause) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("FAILED: " + getDescriptionName(context));
    }
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("FINISHED: " + getDescriptionName(context));
    }
  }
}
