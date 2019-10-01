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

package com.avaloq.tools.ddk.xtext.resource.extensions;

import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;


public class ForwardingResourceDescriptionStrategyMap extends ForwardingMap<String, String> {

  private Map<String, String> delegate;

  protected void build(final ImmutableMap.Builder<String, String> builder) {
    // must be overridden by implementation
  }

  @Override
  public Map<String, String> delegate() {
    if (delegate == null) {
      ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
      build(builder);
      delegate = builder.build();
    }

    return delegate;
  }

}
