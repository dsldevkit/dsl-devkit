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

import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.caching.CacheConfiguration;
import com.avaloq.tools.ddk.xtext.resource.CacheAdapter;


/**
 * A cache adapter for {@link IScope}s.
 */
public class ScopeCacheAdapter extends CacheAdapter<IScope> implements IResourceSetScopeCache {

  public ScopeCacheAdapter(final CacheConfiguration configuration) {
    super(configuration);
  }
}
