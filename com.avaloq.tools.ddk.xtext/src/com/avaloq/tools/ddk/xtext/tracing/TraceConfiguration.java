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

import java.util.Map;

import com.google.common.collect.Maps;


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
    Map<Class<? extends TraceEvent>, Boolean> excludedTraceClassMap = Maps.newIdentityHashMap();
    for (Class<? extends TraceEvent> traceClass : excludedTraceClasses) {
      excludedTraceClassMap.put(traceClass, Boolean.TRUE);
    }
    return c -> !excludedTraceClassMap.containsKey(c);
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
    Map<Class<? extends TraceEvent>, Boolean> includedTraceClassMap = Maps.newIdentityHashMap();
    for (Class<? extends TraceEvent> traceClass : includedTraceClasses) {
      includedTraceClassMap.put(traceClass, Boolean.TRUE);
    }
    return includedTraceClassMap::containsKey;
  }

  /**
   * Creates a new trace configuration that is the combination of a list of other configurations.
   * Tracing is enabled for event types that are enabled in any of the given configurations.
   *
   * @param configurations
   *          the configurations to combine.
   * @return trace configuration, never {@code null}
   */
  @SafeVarargs
  static TraceConfiguration combine(final TraceConfiguration... configurations) {
    return c -> {
      for (TraceConfiguration config : configurations) {
        if (config.isEnabled(c)) {
          return true;
        }
      }
      return false;
    };
  }
}
