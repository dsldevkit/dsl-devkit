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
package com.avaloq.tools.ddk.xtext.formatting.locators;

import org.eclipse.xtext.formatting.impl.FormattingConfig;


/**
 * NoLinewrapLocator facade.
 */
public class NoLinewrapLocatorFacade extends FormattingConfig.NoLinewrapLocator implements ILinewrapLocator {

  /**
   * Creates a new no line wrap locator.
   *
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public NoLinewrapLocatorFacade(final FormattingConfig formattingConfig) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super(0);
  }

  /** {@inheritDoc} */
  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.OVERRIDE;
  }
}
