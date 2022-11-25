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
package com.avaloq.tools.ddk.xtext.formatting.locators.conditional;

import org.eclipse.xtext.AbstractElement;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorEndFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;


/**
 * Conditional indentation end locator.
 */
public class ConditionalIndentationEndLocator extends IndentationLocatorEndFacade implements IConditionalLocator {

  private final LocatorActivator<?> locatorActivator;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ConditionalIndentationEndLocator(final ExtendedFormattingConfig formattingConfig, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig);
    this.locatorActivator = locatorActivator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ConditionalIndentationEndLocator(final ExtendedFormattingConfig formattingConfig, final AbstractElement ele, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, ele);
    this.locatorActivator = locatorActivator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ConditionalIndentationEndLocator(final ExtendedFormattingConfig formattingConfig, final LocatorActivator<?> locatorActivator, final int indentation) {
    super(formattingConfig, indentation);
    this.locatorActivator = locatorActivator;
  }

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public ConditionalIndentationEndLocator(final ExtendedFormattingConfig formattingConfig, final AbstractElement ele, final LocatorActivator<?> locatorActivator, final int indentation) {
    super(formattingConfig, indentation, ele);
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorActivator<?> getLocatorActivator() {
    return locatorActivator;
  }

}
