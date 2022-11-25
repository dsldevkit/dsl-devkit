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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IParametrizedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorStartFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;


/**
 * Parameterized conditional indentation start facade.
 *
 * @see IndentationLocatorStartFacade
 */
public class ParameterizedConditionalIndentationStartLocator extends IndentationLocatorStartFacade implements IParametrizedLocator, IConditionalLocator {

  private final LocatorParameterCalculator<?> calculator;
  private final LocatorActivator<?> locatorActivator;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedConditionalIndentationStartLocator(final ExtendedFormattingConfig formattingConfig, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig);
    this.calculator = calculator;
    this.locatorActivator = locatorActivator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedConditionalIndentationStartLocator(final ExtendedFormattingConfig formattingConfig, final AbstractElement ele, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, ele);
    this.calculator = calculator;
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorParameterCalculator<?> getLocatorParameterCalculator() {
    return calculator;
  }

  @Override
  public LocatorActivator<? extends EObject> getLocatorActivator() {
    return locatorActivator;
  }

}
