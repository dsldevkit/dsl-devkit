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
package com.avaloq.tools.ddk.xtext.formatting.locators.parameterized;

import org.eclipse.xtext.AbstractElement;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.IParametrizedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorStartFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;


/**
 * Parameterized indentation locator facade.
 *
 * @see IndentationLocatorStartFacade
 */
public class ParameterizedIndentationStartLocator extends IndentationLocatorStartFacade implements IParametrizedLocator {

  private final LocatorParameterCalculator<?> calculator;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedIndentationStartLocator(final ExtendedFormattingConfig formattingConfig, final LocatorParameterCalculator<?> calculator) {
    super(formattingConfig);
    this.calculator = calculator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedIndentationStartLocator(final ExtendedFormattingConfig formattingConfig, final AbstractElement ele, final LocatorParameterCalculator<?> calculator) {
    super(formattingConfig, ele);
    this.calculator = calculator;
  }

  @Override
  public LocatorParameterCalculator<?> getLocatorParameterCalculator() {
    return calculator;
  }

}
