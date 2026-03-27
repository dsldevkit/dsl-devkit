/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.test.core.jupiter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.annotation.Nullable;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.opentest4j.TestAbortedException;


@SuppressWarnings("nls")
public abstract class AbstractTestWatchman implements TestWatcher, BeforeEachCallback {

  private final Logger logger = LogManager.getLogger(getClass());

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    logger.info(context.getDisplayName() + " started.");
  }

  @Override
  public abstract void testSuccessful(final ExtensionContext context);

  @Override
  public void testFailed(final ExtensionContext context, @Nullable final Throwable cause) {
    if (cause instanceof TestAbortedException) {
      logger.warn(context.getDisplayName() + " skipped because of failing assumption: " + cause.toString());
    } else {
      logger.warn(context.getDisplayName() + " failed.");
    }
  }

}
