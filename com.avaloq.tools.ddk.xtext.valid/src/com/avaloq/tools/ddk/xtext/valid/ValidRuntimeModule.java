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
package com.avaloq.tools.ddk.xtext.valid;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.avaloq.tools.ddk.xtext.valid.conversion.ValidValueConverterService;
import com.avaloq.tools.ddk.xtext.valid.naming.ValidQualifiedNameConverter;
import com.avaloq.tools.ddk.xtext.valid.scoping.ValidScopeProvider;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ValidRuntimeModule extends com.avaloq.tools.ddk.xtext.valid.AbstractValidRuntimeModule {

  @Override
  public Class<? extends IScopeProvider> bindIScopeProvider() {
    return ValidScopeProvider.class;
  }

  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return ValidValueConverterService.class;
  }

  /**
   * binds custom qualified name converter.
   *
   * @return implementation
   */
  public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
    return ValidQualifiedNameConverter.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
    return com.avaloq.tools.ddk.xtext.valid.formatting.ValidFormatter.class;
  }
}
