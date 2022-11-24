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
package com.avaloq.tools.ddk.xtext.expression.conversion;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;


/**
 * Value converter service for the Expression.xtext grammar which registers an ID value converter for "Identifier".
 */
public class ExpressionValueConverterService extends DefaultTerminalConverters {

  /**
   * Value converter for Identifier rule.
   * 
   * @return value converter
   */
  @ValueConverter(rule = "Identifier")
  public IValueConverter<String> convertIdentifier() {
    return ID();
  }

}
