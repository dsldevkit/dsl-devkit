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
package com.avaloq.tools.ddk.tracing;

import java.util.function.Supplier;

import com.google.inject.Singleton;


/**
 * Dummy implementation of {@link ITraceSet}.
 */
@Singleton
public class NullTraceSet implements ITraceSet {

  @Override
  public void configure(final TraceConfiguration configuration) {
    // Do nothing
  }

  @Override
  public <T extends TraceEvent> boolean isEnabled(final Class<T> eventClass) {
    return false;
  }

  @Override
  public <T extends TraceEvent> void started(final Class<T> eventClass, final Object... data) {
    // Do nothing
  }

  @Override
  public <T extends TraceEvent> void ended(final Class<T> eventClass, final Object... data) {
    // Do nothing
  }

  @Override
  public <T extends TraceEvent> void trace(final Class<T> eventClass, final Object... data) {
  }

  @Override
  public <T> T getIntermediateData(final Object key, final Supplier<T> initialValue) {
    return initialValue.get();
  }

  @Override
  public <T> T clearIntermediateData(final Object key) {
    return null;
  }

  @Override
  public void registerAsync(final Object handler) {
    // Do nothing
  }

  @Override
  public void unregisterAsync(final Object handler) {
    // Do nothing
  }

  @Override
  public void registerSync(final Object handler) {
    // Do nothing
  }

  @Override
  public void unregisterSync(final Object handler) {
    // Do nothing
  }

  @Override
  public <T extends TraceEvent> void post(final T event) {
    // Do nothing
  }

}
