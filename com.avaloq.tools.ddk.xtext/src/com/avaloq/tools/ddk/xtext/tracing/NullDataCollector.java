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
package com.avaloq.tools.ddk.xtext.tracing;

import java.util.function.Supplier;

import com.google.inject.Singleton;


/**
 * Dummy implementation of IExecutionDataCollector.
 */
@Singleton
public class NullDataCollector implements IExecutionDataCollector {

  /** {@inheritDoc} */
  @Override
  public <T extends TraceEvent> void started(final Class<T> eventClass, final Object... data) {
    // Do nothing
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TraceEvent> void ended(final Class<T> eventClass, final Object... data) {
    // Do nothing
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TraceEvent> void trace(final Class<T> eventClass, final Object... data) {
    // Do nothing
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEnabled() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public void enable() {
    // Do nothing
  }

  /** {@inheritDoc} */
  @Override
  public void disable() {
    // Do nothing
  }

  /** {@inheritDoc} */
  @Override
  public void printReport() {
    // Do nothing
  }

  @Override
  public <T> T getIntermediateData(final Object key, final Supplier<T> initialValue) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T clearIntermediateData(final Object key) {
    throw new UnsupportedOperationException();
  }

}
