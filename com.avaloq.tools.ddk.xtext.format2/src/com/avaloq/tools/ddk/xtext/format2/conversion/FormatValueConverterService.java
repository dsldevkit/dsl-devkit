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
package com.avaloq.tools.ddk.xtext.format2.conversion;

import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.xbase.conversion.XbaseValueConverterService;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


/**
 * Value converters for Format.
 */
@Singleton
public class FormatValueConverterService extends XbaseValueConverterService implements IValueConverterService {

  @Inject
  private Injector injector;

  /**
   * This value converter is a IDValueConverter with special handling for parameters.
   */
  private final org.eclipse.xtext.conversion.impl.IDValueConverter parameterizedIdentifierValueConverter = new org.eclipse.xtext.conversion.impl.IDValueConverter() {
    @Override
    protected boolean mustEscape(final String value) {
      if (value != null && value.indexOf('(') > -1) {
        return super.mustEscape(value.substring(0, value.indexOf('(')));
      }
      return super.mustEscape(value);
    }

    /**
     * Do nothing,
     * super.assertTokens fails / raises exceptions for composite converters. {@inheritDoc}
     */
    @Override
    protected void assertTokens(final String value, final TokenSource tokenSource, final String escapedString) {
      // Do nothing
    }
  };

  /**
   * Value converter for Identifier.
   *
   * @return ID value converter
   */
  @ValueConverter(rule = "Identifier")
  public IValueConverter<String> identifier() {
    return ID();
  }

  /**
   * Value converter for ParameterizedIdentifier.
   *
   * @return ID value converter with special handling for parameters
   */
  @ValueConverter(rule = "ParameterizedIdentifier")
  public IValueConverter<String> parameterizedIdentifier() {
    injector.injectMembers(parameterizedIdentifierValueConverter);
    return parameterizedIdentifierValueConverter;
  }
}
