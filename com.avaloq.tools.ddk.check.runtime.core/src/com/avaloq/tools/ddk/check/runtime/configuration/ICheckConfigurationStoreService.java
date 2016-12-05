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
package com.avaloq.tools.ddk.check.runtime.configuration;

import com.google.inject.ImplementedBy;


/**
 * Access point for retrieving a check configuration store instance. The configuration store defines
 * where preferences are looked up. Implementations of this class may provide different stores depending
 * on given context object in {@link #getCheckConfigurationStore(Object)}.
 */
@ImplementedBy(CheckConfigurationStoreService.class)
public interface ICheckConfigurationStoreService {

  String DEFAULT_CHECK_CONFIGURATION_NODE = "com.avaloq.tools.ddk.checkcfg.core"; //$NON-NLS-1$

  /**
   * Returns the configuration store that handle Check Configuration properties based on a given context.
   * Typical context objects are {@link org.eclipse.emf.common.util.URI URI}s and {@link org.eclipse.core.resources.IProject
   * IProject}s.
   * <p>
   * Returns {@code null} if no project is associated with given context object.
   * </p>
   * 
   * @param context
   *          the context to ask
   * @return a check configuration store
   */
  ICheckConfigurationStore getCheckConfigurationStore(Object context);

}

