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
package com.avaloq.tools.ddk.typesystem.typemodel.impl;

/**
 * Implements custom methods.
 */
public class NamedElementImplCustom extends NamedElementImpl {

  public static final String UNRESOLVED_NAME = "<unresolved>"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   * <p>
   * getName() required for unresolved references because the Xtext linker returns an instance of this class.
   * </p>
   */
  @Override
  public String getName() {
    return UNRESOLVED_NAME;
  }
}

