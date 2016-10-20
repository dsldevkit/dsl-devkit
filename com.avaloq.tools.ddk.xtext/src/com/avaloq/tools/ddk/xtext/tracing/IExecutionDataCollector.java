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
