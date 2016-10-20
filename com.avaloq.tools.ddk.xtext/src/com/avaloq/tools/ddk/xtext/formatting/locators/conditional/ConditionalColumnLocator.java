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

import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;


/**
 * Conditional column locator.
 */
public class ConditionalColumnLocator extends FixedLocator implements IConditionalLocator {

  public ConditionalColumnLocator(final FormattingConfig formattingConfig, final int column, final boolean fixed, final boolean relative, final boolean nobreak, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig, column, fixed, relative, nobreak);
    this.locatorActivator = locatorActivator;
  }

  private final LocatorActivator<?> locatorActivator;

  @Override
  public LocatorActivator<?> getLocatorActivator() {
    return locatorActivator;
  }

}
