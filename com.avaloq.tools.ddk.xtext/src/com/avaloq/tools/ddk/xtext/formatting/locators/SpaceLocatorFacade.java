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
 * SpaceLocator facade.
 */
public class SpaceLocatorFacade extends FormattingConfig.SpaceLocator implements ISpaceLocator {

  /**
   * Creates a new space locator.
   *
   * @param space
   *          the space
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public SpaceLocatorFacade(final FormattingConfig formattingConfig, final String space) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super(space);
  }

  /**
   * Creates a new space locator with empty space.
   *
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public SpaceLocatorFacade(final FormattingConfig formattingConfig) {
    this(formattingConfig, ""); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.COMBINED_MAXIMUM;
  }

  /**
   * Gets the padding for an entry. The padding will be the space specified in the constructor.
   *
   * @param line
   *          the line containing entry / to which entry is to be added
   * @param entry
   *          the entry for which to create padding
   * @return the padding required
   */
  @Override
  public String computeSpace(final ExtendedLine line, final ExtendedLineEntry entry) {
    return super.getSpace();
  }
}
