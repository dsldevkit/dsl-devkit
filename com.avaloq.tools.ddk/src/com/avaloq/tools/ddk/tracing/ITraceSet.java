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

import com.google.inject.ImplementedBy;


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
@ImplementedBy(NullTraceSet.class)
public interface ITraceSet {

  /**
   * Signifies the trace set that the event of the given type has started.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void started(Class<T> eventClass, Object... data);

  /**
   * Signifies the trace set that the event of the given type has ended.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void ended(Class<T> eventClass, Object... data);

  /**
   * Signifies the trace set that the instantaneous event of the given type has happened.
   *
   * @param eventClass
   *          event class
   * @param data
   *          data that the event holds
   * @param <T>
   *          type of the event
   */
  <T extends TraceEvent> void trace(Class<T> eventClass, Object... data);

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
   * Checks if tracing is enabled for a given event type.
   *
   * @param <T>
   *          generic event type
   * @param eventClass
   *          event type, must not be {@code null}
   * @return {@code true} if tracing is enabled for the given event type
   */
  <T extends TraceEvent> boolean isEnabled(Class<T> eventClass);

  /**
   * Configures this trace set to trace according to the given {@link TraceConfiguration}.
   *
   * @param configuration
   *          trace configuration, must not be {@code null}
   */
  void configure(TraceConfiguration configuration);

  /**
   * Registers a trace event handler for asynchronous notification. This means that the event producer will already have continued its processing at the time
   * the handlers are notified.
   *
   * @param handler
   *          event handler to register
   */
  void registerAsync(Object handler);

  /**
   * Unregisters the given event handler for asynchronous event notification. After this method has been called it will not be notified of any more events,
   * unless there are still some pending asynchronous event notifications.
   *
   * @param handler
   *          event handler to register
   */
  void unregisterAsync(Object handler);

  /**
   * Registers a trace event handler for synchronous notification. This means that the event producer will be <em>blocked</em> until all registered and
   * interested handlers have been notified.
   * <p>
   * If synchronous notification is not strictly required, the {@link #registerAsync(Object) asynchronous} registration method should be preferred.
   *
   * @param handler
   *          event handler to register
   */
  void registerSync(Object handler);

  /**
   * Unregisters the given event handler for synchronous event notification. After this method has been called it will not be notified of any more events.
   *
   * @param handler
   *          event handler to register
   */
  void unregisterSync(Object handler);

  /**
   * Posts the given event to all registered handlers.
   *
   * @param <T>
   *          event type
   * @param event
   *          event to post
   */
  <T extends TraceEvent> void post(T event);

}
