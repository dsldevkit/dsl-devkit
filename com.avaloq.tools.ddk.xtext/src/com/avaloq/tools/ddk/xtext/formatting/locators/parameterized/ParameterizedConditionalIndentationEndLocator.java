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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;

import com.avaloq.tools.ddk.xtext.formatting.DdkFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IParametrizedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorEndFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;


/**
 * Parameterized conditional indentation end locator facade.
 *
 * @see IndentationLocatorEndFacade
 */
public class ParameterizedConditionalIndentationEndLocator extends IndentationLocatorEndFacade implements IParametrizedLocator, IConditionalLocator {

  private final LocatorParameterCalculator<?> calculator;
  private final LocatorActivator<?> locatorActivator;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedConditionalIndentationEndLocator(final DdkFormattingConfig formattingConfig, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig);
    this.calculator = calculator;
    this.locatorActivator = locatorActivator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ParameterizedConditionalIndentationEndLocator(final DdkFormattingConfig formattingConfig, final AbstractElement ele, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
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
