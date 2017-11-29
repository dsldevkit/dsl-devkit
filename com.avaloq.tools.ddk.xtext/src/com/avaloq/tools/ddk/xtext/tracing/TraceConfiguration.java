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

import java.util.Arrays;

import com.google.common.collect.ImmutableSet;


/**
 * A trace configuration which configures what events will be traced by an {@link ITraceSet} instance.
 */
public interface TraceConfiguration {

  /**
   * Checks if tracing is enabled for a given event type.
   *
   * @param eventClass
   *          event type, must not be {@code null}
   * @return {@code true} if tracing is enabled for the given event type
   * @see ITraceSet#isEnabled(Class)
   */
  boolean isEnabled(Class<? extends TraceEvent> eventClass);

  /**
   * Creates a new trace configuration which disables <b>all</b> tracing.
   *
   * @return trace configuration, never {@code null}
   */
  static TraceConfiguration disableAll() {
    return c -> false;
  }

  /**
   * Creates a new trace configuration which enables <b>all</b> tracing.
   *
   * @return trace configuration, never {@code null}
   */
  static TraceConfiguration enableAll() {
    return c -> true;
  }

  /**
   * Creates a new trace configuration which enables all tracing <b>except</b> for the given event types.
   *
   * @param excludedTraceClasses
   *          event types to disable tracing for
   * @return trace configuration, never {@code null}
   */
  @SafeVarargs
  static TraceConfiguration enableAllExcept(final Class<? extends TraceEvent>... excludedTraceClasses) {
    ImmutableSet<Class<? extends TraceEvent>> excludedTraceClassSet = ImmutableSet.copyOf(Arrays.asList(excludedTraceClasses));
    return c -> !excludedTraceClassSet.contains(c);
  }

  /**
   * Creates a new trace configuration which enables tracing <b>only</b> for the given event types.
   *
   * @param includedTraceClasses
   *          event types to enable tracing for
   * @return trace configuration, never {@code null}
   */
  @SafeVarargs
  static TraceConfiguration enableOnly(final Class<? extends TraceEvent>... includedTraceClasses) {
    ImmutableSet<Class<? extends TraceEvent>> includedTraceClassSet = ImmutableSet.copyOf(Arrays.asList(includedTraceClasses));
    return c -> includedTraceClassSet.contains(c);
  }
}
