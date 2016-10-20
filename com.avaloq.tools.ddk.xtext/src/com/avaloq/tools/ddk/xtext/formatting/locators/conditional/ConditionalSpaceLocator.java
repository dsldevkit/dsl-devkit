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
package com.avaloq.tools.ddk.xtext.formatting.locators.conditional;

import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.SpaceLocatorFacade;


/**
 * Conditional space locator.
 */
public class ConditionalSpaceLocator extends SpaceLocatorFacade implements IConditionalLocator {

  private final LocatorActivator<?> locatorActivator;

  public ConditionalSpaceLocator(final FormattingConfig formattingConfig, final String space, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, space);
    this.locatorActivator = locatorActivator;
  }

  public ConditionalSpaceLocator(final FormattingConfig formattingConfig, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, ""); //$NON-NLS-1$
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorActivator<?> getLocatorActivator() {
    return locatorActivator;
  }

}
