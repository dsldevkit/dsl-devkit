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

import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig;


/**
 * No Format locator.
 */
public class NoFormatLocator extends ElementLocator implements IExtendedLocator {

  /**
   * Creates a new locator.
   *
   * @param formattingConfig the formatting configuration
   */
  public NoFormatLocator(final FormattingConfig formattingConfig) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super();
  }

  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.OVERRIDE;
  }

}
