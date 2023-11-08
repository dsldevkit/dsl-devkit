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

package com.avaloq.tools.ddk.xtext.resource.extensions;

import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;


/**
 * The Class AbstractForwardingResourceDescriptionStrategyMap.
 */
public abstract class AbstractForwardingResourceDescriptionStrategyMap extends ForwardingMap<String, String> {

  private Map<String, String> delegateMap;

  /**
   * Implementations must use this method to associates keys with value by using the method {@code put} of the {@link ImmutableMap.Builder}.
   *
   * @param builder
   *          the builder
   */
  protected abstract void fill(ImmutableMap.Builder<String, String> builder);

  @Override
  public Map<String, String> delegate() {
    if (delegateMap == null) {
      ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
      fill(builder);
      delegateMap = builder.build();
    }

    return delegateMap;
  }

}
