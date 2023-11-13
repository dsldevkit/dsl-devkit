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
package com.avaloq.tools.ddk.xtext.formatter;

import org.eclipse.xtext.formatting.IFormatter;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class FormatterTestLanguageRuntimeModule extends com.avaloq.tools.ddk.xtext.formatter.AbstractFormatterTestLanguageRuntimeModule {

  @Override
  public Class<? extends IFormatter> bindIFormatter() {
    return FormatterTestConfig.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.conversion.IValueConverterService> bindIValueConverterService() {
    return FormatterTestValueConverters.class;
  }

}
