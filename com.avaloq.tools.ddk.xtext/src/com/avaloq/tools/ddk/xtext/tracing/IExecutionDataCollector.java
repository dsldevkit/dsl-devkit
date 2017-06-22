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

import com.google.inject.ImplementedBy;


/**
 * Generic interface for services that collect execution data. This data can be then used for creating execution traces / statistics.
 * Essentially, this service forwards the events to the corresponding consumers that perform actual processing.
 * Implementations of this interface support synchronous notifications only.
 * <p>
 * By default the service is disabled and must be enabled explicitly.
 * <p>
 * The implementation must be thread-safe.
 * <p>
 */
@ImplementedBy(NullDataCollector.class)
public interface IExecutionDataCollector {

  /**
   * Signifies the data collector that the event of the given type has started.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void started(final Class<T> eventClass, final Object... data);

  /**
   * Signifies the data collector that the event of the given type has ended.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void ended(final Class<T> eventClass, final Object... data);

  /**
   * Signifies the data collector that the instantaneous event of the given type has happened.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void trace(final Class<T> eventClass, final Object... data);

  /**
   * Used to collect intermediate data which will typically later be used as payload for a posted event. The intermediate data is maintained separately for each
   * thread.
   *
   * @param key
   *          arbitrary key to associate intermediate data with, must not be {@code null}
   * @param initialValue
   *          supplier for initial value in case this method is called for the first time or for the first time since {@link #clearIntermediateData(Object)} was
   *          last called, must not be {@code null}
   * @param <T>
   *          type of intermediate data
   * @return intermediate data as initialized by a previous call to this method on this thread or otherwise as computed by {@code supplier}
   * @see #clearIntermediateData(Object)
   */
  <T> T getIntermediateData(Object key, Supplier<T> initialValue);

  /**
   * Returns the intermediate data associated with the current thread and given key and clears it from this collector.
   *
   * @param key
   *          key with which intermediate data is associated, must not be {@code null}
   * @param <T>
   *          type of intermediate data
   * @return intermediate data associated with current thread and given key or {@code null} if none
   */
  <T> T clearIntermediateData(Object key);

  /**
   * Checks if this collector is enabled.
   *
   * @return {@code true} if enabled
   * @see #enable()
   * @see #disable()
   */
  boolean isEnabled();

  /**
   * Enable the handling of events. After this method has been called all subsequently posted events will be dispatched to any registered handlers.
   */
  void enable();

  /**
   * Disable the handling of events. After this method has been called any subsequently occurred events will be ignored.
   */
  void disable();

  /**
   * Prints out a report about all the occurred events.
   */
  void printReport();
}
