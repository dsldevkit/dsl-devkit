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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;


/**
 * Conditional no format locator.
 */
public class ConditionalNoFormatLocator extends NoFormatLocator implements IConditionalLocator {

  private final LocatorActivator<?> locatorActivator;

  public ConditionalNoFormatLocator(final FormattingConfig formattingConfig, final LocatorActivator<?> locatorActivator) {
    super(formattingConfig);
    this.locatorActivator = locatorActivator;
  }

  @Override
  public LocatorActivator<? extends EObject> getLocatorActivator() {
    return locatorActivator;
  }

}
