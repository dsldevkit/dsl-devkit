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
package com.avaloq.tools.ddk.xtext.valid.conversion;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;

import com.google.inject.Inject;


/**
 * Value converter service for the valid DSL.
 */
public class ValidValueConverterService extends DefaultTerminalConverters {

  @Inject
  private ExtensionIdValueConverter qualifiedIDConverter;

  /**
   * Value converter for qualified extension IDs which are delimited using "::".
   */
  private static final class ExtensionIdValueConverter extends QualifiedNameValueConverter {
    @Override
    protected String getNamespaceDelimiter() {
      return "::";
    }
  }

  /**
   * Value converter for Identifier rule.
   * 
   * @return value converter
   */
  @ValueConverter(rule = "QualifiedID")
  public IValueConverter<String> convertQualifiedID() {
    return qualifiedIDConverter;
  }

}

