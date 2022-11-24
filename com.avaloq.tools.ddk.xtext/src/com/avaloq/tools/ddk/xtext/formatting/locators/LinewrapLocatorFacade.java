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
 * LinewrapLocator facade.
 */
public class LinewrapLocatorFacade extends FormattingConfig.LinewrapLocator implements ILinewrapLocator {

  /**
   * Creates a new linewrap locator.
   *
   * @param min
   *          the minimum number of line wraps
   * @param def
   *          the default number of line wraps
   * @param max
   *          the maximum number of line wraps
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public LinewrapLocatorFacade(final FormattingConfig formattingConfig, final int min, final int def, final int max) { // NOPMD CallSuperInConstructor - false
                                                                                                                       // positive by PMD
    formattingConfig.super(min, def, max);
  }

  /**
   * Creates a new linewrap locator.
   *
   * @param wrap
   *          the number of line wraps
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public LinewrapLocatorFacade(final FormattingConfig formattingConfig, final int wrap) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super(wrap);
  }

  /** {@inheritDoc} */
  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.COMBINED_MAXIMUM;
  }
}
