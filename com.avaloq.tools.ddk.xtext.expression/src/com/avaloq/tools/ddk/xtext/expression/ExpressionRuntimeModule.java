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
package com.avaloq.tools.ddk.xtext.expression;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;

import com.avaloq.tools.ddk.xtext.expression.conversion.ExpressionValueConverterService;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ExpressionRuntimeModule extends AbstractExpressionRuntimeModule {

  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return ExpressionValueConverterService.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
    return com.avaloq.tools.ddk.xtext.expression.formatting.ExpressionFormatter.class;
  }
}
