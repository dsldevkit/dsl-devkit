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

package com.avaloq.tools.ddk.xtext.util;

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * Temporarily set whether to record unresolvable cross-references.
 */
public class RecordUnresolvableXrefs implements AutoCloseable {

  private static final Object KEY = RecordUnresolvableXrefs.class.getName() + ".MARK_UNRESOLVABLE_XREFS"; //$NON-NLS-1$

  private final Map<Object, Object> loadOptions;
  private final Object initialState;

  /**
   * Creates a new instance of {@link RecordUnresolvableXrefs}.
   *
   * @param resourceSet
   *          {@link ResourceSet} being compiled, must not be {@code null}
   * @param enabled
   *          whether to record unresolvable cross-references
   */
  public RecordUnresolvableXrefs(final ResourceSet resourceSet, final boolean enabled) {
    loadOptions = resourceSet.getLoadOptions();

    if (enabled) {
      initialState = loadOptions.put(KEY, Boolean.FALSE);
    } else {
      initialState = loadOptions.remove(KEY);
    }
  }

  @Override
  public void close() {
    if (initialState != null) {
      loadOptions.put(KEY, initialState);
    } else {
      loadOptions.remove(KEY);
    }
  }

  /**
   * Get whether unresolvable cross-references are being recorded.
   *
   * @param resourceSet
   *          {@link ResourceSet} being compiled, must not be {@code null}
   * @return whether unresolvable cross-references are being recorded
   */
  public static boolean isEnabled(final ResourceSet resourceSet) {
    return resourceSet.getLoadOptions().containsKey(KEY);
  }

  /**
   * Record that an unresolvable cross-reference was encountered.
   *
   * @param resourceSet
   *          {@link ResourceSet} being compiled, must not be {@code null}
   */
  public static void recordUnresolvableXref(final ResourceSet resourceSet) {
    if (isEnabled(resourceSet)) {
      resourceSet.getLoadOptions().put(KEY, Boolean.TRUE);
    }
  }

  /**
   * Get whether an unresolvable cross-reference has been recorded.
   *
   * @param resourceSet
   *          {@link ResourceSet} being compiled, must not be {@code null}
   * @return whether an unresolvable cross-reference has been recorded
   */
  public static boolean unresolvableXrefRecorded(final ResourceSet resourceSet) {
    return resourceSet.getLoadOptions().get(KEY) == Boolean.TRUE;
  }

}
