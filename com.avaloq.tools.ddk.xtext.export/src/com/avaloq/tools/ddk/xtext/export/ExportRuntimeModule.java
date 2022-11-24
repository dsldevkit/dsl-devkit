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
package com.avaloq.tools.ddk.xtext.export;

import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;

import com.avaloq.tools.ddk.xtext.export.conversion.ExportValueConverterService;
import com.avaloq.tools.ddk.xtext.export.generator.ExportOutputConfigurationProvider;
import com.avaloq.tools.ddk.xtext.export.naming.ExportQualifiedNameConverter;
import com.avaloq.tools.ddk.xtext.parser.FixedPartialParsingHelper;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ExportRuntimeModule extends com.avaloq.tools.ddk.xtext.export.AbstractExportRuntimeModule {

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<? extends org.eclipse.xtext.conversion.IValueConverterService> bindIValueConverterService() {
    return ExportValueConverterService.class;
  }

  /**
   * binds custom qualified name converter.
   *
   * @return implementation
   */
  public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
    return ExportQualifiedNameConverter.class;
  }

  /**
   * Workaround for Bug 416913. To be removed with DSL-596
   *
   * @return {@link FixedPartialParsingHelper}
   */
  @Override
  public Class<? extends IPartialParsingHelper> bindIPartialParserHelper() {
    return FixedPartialParsingHelper.class;
  }

  /**
   * Binds a custom output configuration provider.
   *
   * @return the format output configuration provider
   */
  public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return ExportOutputConfigurationProvider.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
    return com.avaloq.tools.ddk.xtext.export.formatting.ExportFormatter.class;
  }
}
