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


/**
 * Default dummy implementation of config provider.
 */
public class CheckGeneratorConfigProvider implements ICheckGeneratorConfigProvider {

  private static final CheckGeneratorConfig CONFIG_INSTANCE = new CheckGeneratorConfig();

  @Override
  public CheckGeneratorConfig get(final URI uri) {
    return CONFIG_INSTANCE;
  }

}
