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

import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoSpaceLocatorFacade;


/**
 * Conditional no space locator.
 */
public class ConditionalNoSpaceLocator extends NoSpaceLocatorFacade implements IConditionalLocator {
  private final LocatorActivator<?> locatorActivator;

  public ConditionalNoSpaceLocator(final FormattingConfig formattingConfig, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig);
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorActivator<?> getLocatorActivator() {
    return locatorActivator;
  }
}
