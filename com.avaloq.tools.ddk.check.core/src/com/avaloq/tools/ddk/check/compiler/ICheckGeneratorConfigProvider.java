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

package com.avaloq.tools.ddk.check.compiler;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * Provides generator configurations specific to Check DSL.
 */
public interface ICheckGeneratorConfigProvider {

  /**
   * Gets the generator configuration.
   *
   * @param resource
   *          the context resource to detect generator preferences
   * @return the check generator configuration
   */
  CheckGeneratorConfig get(Resource resource);

}
