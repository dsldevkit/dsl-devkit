/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.builder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperation;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperationException;
import org.junit.jupiter.api.Test;


/**
 * Tests for {@link MonitoredClusteringBuilderState#isAbandonedByTimeout(WrappedException, LoadOperation)}.
 */
@SuppressWarnings("nls")
public class BuilderLoadTimeoutTest {

  private static final TimeoutException TIMEOUT = new TimeoutException("no result");

  @Test
  public void timeoutWithNothingLeftIsAbandonment() {
    assertTrue(MonitoredClusteringBuilderState.isAbandonedByTimeout(new LoadOperationException(null, TIMEOUT), operation(false)));
  }

  @Test
  public void timeoutWithLoadsLeftIsNotAbandonment() {
    assertFalse(MonitoredClusteringBuilderState.isAbandonedByTimeout(new LoadOperationException(null, TIMEOUT), operation(true)));
  }

  @Test
  public void loadFailureIsNotAbandonment() {
    LoadOperationException failure = new LoadOperationException(URI.createURI("platform:/resource/project/a.test"), new IOException("cannot read"));
    assertFalse(MonitoredClusteringBuilderState.isAbandonedByTimeout(failure, operation(false)));
  }

  @Test
  public void otherWrappedExceptionIsNotAbandonment() {
    assertFalse(MonitoredClusteringBuilderState.isAbandonedByTimeout(new WrappedException(TIMEOUT), operation(false)));
  }

  private static LoadOperation operation(final boolean hasNext) {
    LoadOperation operation = mock(LoadOperation.class);
    when(operation.hasNext()).thenReturn(hasNext);
    return operation;
  }

}
