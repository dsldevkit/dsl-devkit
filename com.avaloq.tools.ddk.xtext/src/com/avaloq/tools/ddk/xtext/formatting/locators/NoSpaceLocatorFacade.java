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

import com.avaloq.tools.ddk.xtext.formatting.ExtendedLine;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;


/**
 * NoSpaceLocator facade.
 */
public class NoSpaceLocatorFacade extends FormattingConfig.NoSpaceLocator implements ISpaceLocator {

  /**
   * Creates a new no space locator.
   *
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public NoSpaceLocatorFacade(final FormattingConfig formattingConfig) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super();
  }

  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.OVERRIDE;
  }

  /**
   * Gets the padding for an entry.
   *
   * @param line
   *          the line containing entry / to which entry is to be added
   * @param entry
   *          the entry for which to create padding
   * @return the empty string
   */
  @Override
  public String computeSpace(final ExtendedLine line, final ExtendedLineEntry entry) {
    return super.getSpace();
  }
}
