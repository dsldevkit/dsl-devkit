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
package com.avaloq.tools.ddk.xtext.formatting.locators;

import org.eclipse.xtext.formatting.impl.FormattingConfig;


/**
 * Locator used to reduce the number of linewraps by one.
 */
public class SubtractingLinewrapLocatorFacade extends LinewrapLocatorFacade {

  public SubtractingLinewrapLocatorFacade(final FormattingConfig formattingConfig) {
    super(formattingConfig, -1);
  }

  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.ADDITIVE;
  }
}
