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
package com.avaloq.tools.ddk.xtext.export.conversion;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.xbase.conversion.XbaseValueConverterService;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Value converters for the export language.
 * <p>
 * The grammar inherits from Xbase, therefore this service must extend {@link XbaseValueConverterService}: the binding
 * contributed by {@code DefaultXbaseRuntimeModule} is overridden by the language's runtime module, and anything not
 * inherited from here would silently drop the converters for the Xbase rules.
 */
@SuppressWarnings("restriction")
@Singleton
public class ExportValueConverterService extends XbaseValueConverterService {

  @Inject
  private ExtensionIdValueConverter qualifiedIDConverter;

  /**
   * Value converter for qualified extension IDs which are delimited using "::".
   */
  private static final class ExtensionIdValueConverter extends QualifiedNameValueConverter {
    @Override
    protected String getNamespaceDelimiter() {
      return "::"; //$NON-NLS-1$
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
