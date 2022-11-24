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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Debug support for scoping. Represents a trace for in which scope an {@link IEObjectDescription} was created / found.
 */
public class ScopeTrace {

  private static final Map<IEObjectDescription, ScopeTrace> TRACES = new WeakHashMap<IEObjectDescription, ScopeTrace>();

  private final List<String> elements = Lists.newArrayList();

  /**
   * Adds a trace element to this scope trace.
   * 
   * @param traceElement
   *          trace to add
   */
  public void add(final String traceElement) {
    elements.add(0, traceElement);
  }

  public List<String> getFullTrace() {
    return ImmutableList.copyOf(elements);
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder(getClass().getName());
    builder.append('@');
    builder.append(Integer.toHexString(hashCode()));
    builder.append(" [");

    for (final Iterator<String> i = elements.iterator(); i.hasNext();) {
      builder.append(i.next());
      if (i.hasNext()) {
        builder.append(" >> ");
      }
    }

    builder.append(']');
    return builder.toString();
  }

  /**
   * Adds a trace element for the given {@link IEObjectDescription}.
   * 
   * @param desc
   *          element to add trace for
   * @param traceElement
   *          trace to add
   */
  public static void addTrace(final IEObjectDescription desc, final String traceElement) {
    ScopeTrace trace = TRACES.get(desc);
    if (trace == null) {
      trace = new ScopeTrace();
      TRACES.put(desc, trace);
    }
    trace.add(traceElement);
  }

  /**
   * Retrieves the trace for the given {@link IEObjectDescription}.
   * 
   * @param desc
   *          description to get trace for
   * @return trace
   */
  public static ScopeTrace getTrace(final IEObjectDescription desc) {
    return TRACES.get(desc);
  }

}
