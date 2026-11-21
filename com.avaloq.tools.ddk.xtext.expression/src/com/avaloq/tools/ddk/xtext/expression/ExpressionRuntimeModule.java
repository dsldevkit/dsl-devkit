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
import org.eclipse.xtext.formatting.ILineSeparatorInformation;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelInferrer;

import com.avaloq.tools.ddk.xtext.expression.conversion.ExpressionValueConverterService;
import com.avaloq.tools.ddk.xtext.expression.jvmmodel.ExpressionJvmModelInferrer;
import com.avaloq.tools.ddk.xtext.formatting.LfLineSeparatorInformation;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class ExpressionRuntimeModule extends AbstractExpressionRuntimeModule {

  /**
   * Binds the generated-file line separator to LF so code generation is deterministic
   * across platforms; headless builds otherwise fall back to the platform separator.
   *
   * @return the LF {@link ILineSeparatorInformation} implementation, never {@code null}
   */
  public Class<? extends ILineSeparatorInformation> bindILineSeparatorInformation() {
    return LfLineSeparatorInformation.class;
  }

  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return ExpressionValueConverterService.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
    return com.avaloq.tools.ddk.xtext.expression.formatting.ExpressionFormatter.class;
  }

  /**
   * Binds the Java JVM model inferrer, which the Xbase generator fragment does not bind with {@code generateXtendInferrer = false}.
   *
   * @return the {@link ExpressionJvmModelInferrer} class, never {@code null}
   */
  public Class<? extends IJvmModelInferrer> bindIJvmModelInferrer() {
    return ExpressionJvmModelInferrer.class;
  }
}
