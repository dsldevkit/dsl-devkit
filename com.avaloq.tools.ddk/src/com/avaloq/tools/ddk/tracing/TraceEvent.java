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

import java.util.Arrays;


/**
 * Abstract implementation class of a trace event posted to a {@link TraceSet}. Every event has:
 * <ul>
 * <li>a {@link #getTrigger() trigger},</li>
 * <li>a {@link #getTime() creation timestamp},</li>
 * <li>a {@link #getThread() context thread},</li>
 * <li>and optional {@link #getData() data}.</li>
 * </ul>
 * <p>
 * Using the {@link Trigger} a hierarchy of events can be implied. But it is important that the event producer creates a "well-formed" stream of events, where
 * there are no hand over hand events and every {@link Trigger#STARTED STARTED} event will be matched by a corresponding {@link Trigger#ENDED ENDED} event.
 * <p>
 * Every concrete implementation class should declare a constructor with the parameters (in the given order): {@link Trigger}, {@link Object...}. This is a
 * requirement when using the utility methods {@link TraceSet#started(Class, Object...) started()}, {@link TraceSet#trace(Class, Object...) trace()}, and
 * {@link TraceSet#ended(Class, Object...) ended()}.
 */
// CHECKSTYLE:CHECK-OFF AbstractClassName
public abstract class TraceEvent {
  // CHECKSTYLE:CHECK-ON AbstractClassName

  /**
   * Event trigger with which a hierarchy of events can be created. A {@link #TRACE} event cannot have any "children" and every {@link #STARTED} event must be
   * matched by a corresponding {@link #ENDED} event.
   */
  public enum Trigger {
    TRACE,
    STARTED,
    ENDED
  }

  private final Trigger trigger;
  private final Object[] data;
  private final long time;
  private final Thread thread;

  /**
   * Creates a new instance of {@link TraceEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data
   */
  protected TraceEvent(final Trigger trigger, final Object... data) {
    this.trigger = trigger;
    this.data = data;
    this.time = System.nanoTime();
    this.thread = Thread.currentThread();
  }

  /**
   * Returns the trigger of this event.
   *
   * @return event trigger
   */
  public Trigger getTrigger() {
    return trigger;
  }

  /**
   * Returns the event data for this event.
   *
   * @return event data
   */
  @SuppressWarnings("PMD.MethodReturnsInternalArray")
  public Object[] getData() {
    return data;
  }

  /**
   * Returns the time (in nanoseconds, see {@link System#nanoTime()} this event was created. Note that this returned time has no relation to wall-clock time.
   *
   * @return creation time in nanoseconds
   */
  public long getTime() {
    return time;
  }

  /**
   * Returns the thread that created this event.
   *
   * @return originating thread
   */
  public Thread getThread() {
    return thread;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" + trigger + ", " + thread.getName() + ", " + time + ", " + Arrays.toString(data) + ')';
  }
}
