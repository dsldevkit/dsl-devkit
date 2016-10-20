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

/**
 * A TraceSet is used to collect trace data in the form of {@link TraceEvent trace events}. The events are posted by <i>producers</i> using the
 * {@link #post(Object) post()} method or any of the convenience methods {@link #started(Class, Object...) started()}, {@link #ended(Class, Object...) ended()},
 * or {@link #trace(Class, Object...) trace()}. This will then dispatch the trace event to any <i>handlers</i> which have been registered for
 * {@link #registerSync(Object) synchronous} or {@link #registerAsync(Object) asynchronous} notification.
 * <p>
 * It is very important that the event producers post a "well-formed" stream of events, where every {@link TraceEvent.Trigger#STARTED STARTED} event is matched
 * by a corresponding {@link TraceEvent.Trigger#ENDED ENDED} event of the same type. This results in an implied hierarchical structure of events. It is
 * therefore recommended to post the events using a try-finally coding pattern. The TraceSet implementation is thread-safe and this "well-formedness" constraint
 * naturally applies to the individual thread only.
 * <p>
 * By default the TraceSet is {@link #disable() disabled} and will until explicitly {@link #enable() enabled} ignore all posted events. These events will thus
 * <em>not</em> be queued until the TraceSet is enabled.
 */
public interface ITraceSet extends IExecutionDataCollector {

  /**
   * Registers a trace event handler for asynchronous notification. This means that the event producer will already have continued its processing at the time
   * the handlers are notified.
   *
   * @param handler
   *          event handler to register
   */
  void registerAsync(final Object handler);

  /**
   * Unregisters the given event handler for asynchronous event notification. After this method has been called it will not be notified of any more events,
   * unless there are still some pending asynchronous event notifications.
   *
   * @param handler
   *          event handler to register
   */
  void unregisterAsync(final Object handler);

  /**
   * Registers a trace event handler for synchronous notification. This means that the event producer will be <em>blocked</em> until all registered and
   * interested handlers have been notified.
   * <p>
   * If synchronous notification is not strictly required, the {@link #registerAsync(Object) asynchronous} registration method should be preferred.
   *
   * @param handler
   *          event handler to register
   */
  void registerSync(final Object handler);

  /**
   * Unregisters the given event handler for synchronous event notification. After this method has been called it will not be notified of any more events.
   *
   * @param handler
   *          event handler to register
   */
  void unregisterSync(final Object handler);

  /**
   * Posts the given event to all registered handlers.
   *
   * @param <T>
   *          event type
   * @param event
   *          event to post
   */
  <T> void post(final T event);

}
