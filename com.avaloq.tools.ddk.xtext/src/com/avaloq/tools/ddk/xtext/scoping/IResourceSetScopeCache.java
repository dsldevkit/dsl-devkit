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
package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.xtext.resource.IResourceSetCache;


/**
 * A cache for {@link IScope}s.
 */
public interface IResourceSetScopeCache extends IResourceSetCache<IScope> {}
