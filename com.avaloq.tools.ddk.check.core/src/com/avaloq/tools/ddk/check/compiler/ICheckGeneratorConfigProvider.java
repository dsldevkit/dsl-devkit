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

import org.eclipse.emf.common.util.URI;

import com.google.inject.ImplementedBy;


/**
 * Provides generator configurations specific to Check DSL.
 */
@ImplementedBy(CheckGeneratorConfigProvider.class)
public interface ICheckGeneratorConfigProvider {

  /**
   * Gets the generator configuration.
   *
   * @param uri
   *          the uri to detect generator preferences, must not be {@code null}
   * @return the check generator configuration
   */
  CheckGeneratorConfig get(URI uri);

}
