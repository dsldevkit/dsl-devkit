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
package com.avaloq.tools.ddk.xtext.formatting.locators.parameterized;

import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IParametrizedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;


/**
 * Parameterized conditional single line column locator.
 *
 * @see ColumnLocator
 */
public class ParameterizedConditionalSingleLineColumnLocator extends ColumnLocator implements IParametrizedLocator, IConditionalLocator {

  private final LocatorParameterCalculator<?> calculator;
  private final LocatorActivator<?> locatorActivator;

  public ParameterizedConditionalSingleLineColumnLocator(final FormattingConfig formattingConfig, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, 1);
    this.calculator = calculator;
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorParameterCalculator<?> getLocatorParameterCalculator() {
    return calculator;
  }

  @Override
  public LocatorActivator<?> getLocatorActivator() {
    return locatorActivator;
  }

}
