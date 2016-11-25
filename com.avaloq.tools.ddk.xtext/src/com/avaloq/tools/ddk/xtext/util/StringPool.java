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

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * String pool which is used to avoid duplication of many equal strings.
 */
public class StringPool {

  private final Map<String, String> pool;

  public StringPool(final boolean useWeakReferences) {
    pool = useWeakReferences ? new WeakHashMap<String, String>() : new HashMap<String, String>();
  }

  /**
   * Retrieves the pooled string corresponding to the given string. If it doesn't already exist in the pool it is automatically added.
   * 
   * @param s
   *          string to get pooled string instance for
   * @return given string itself (if not already pooled)
   */
  public String get(final String s) {
    final String result = pool.get(s);
    if (result != null) {
      return result;
    }
    pool.put(s, s);
    return s;
  }
}

