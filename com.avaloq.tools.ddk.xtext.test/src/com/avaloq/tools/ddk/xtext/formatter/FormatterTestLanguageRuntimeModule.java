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
package com.avaloq.tools.ddk.xtext.formatter;

import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;

import com.avaloq.tools.ddk.xtext.parser.FixedPartialParsingHelper;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class FormatterTestLanguageRuntimeModule extends com.avaloq.tools.ddk.xtext.formatter.AbstractFormatterTestLanguageRuntimeModule {

  /** {@inheritDoc} */
  @Override
  public Class<? extends IFormatter> bindIFormatter() {
    return FormatterTestConfig.class;
  }

  /** {@inheritDoc} */
  @Override
  public Class<? extends org.eclipse.xtext.conversion.IValueConverterService> bindIValueConverterService() {
    return FormatterTestValueConverters.class;
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
}
