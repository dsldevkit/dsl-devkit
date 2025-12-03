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

import com.avaloq.tools.ddk.xtext.formatting.ExtendedLine;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;
import com.avaloq.tools.ddk.xtext.formatting.SpaceEntry;


/**
 * The Class RightPaddingLocator is used to make elements occupy a minimum amount of space
 * on a line. It the amount of space an element occupies on its own (i.e. its length) is
 * insufficient padding is appended to it.
 */
public class RightPaddingLocator extends ElementLocator implements ISpaceLocator {
  /** The minimum space an element, to which this locator is applied, must occupy. */
  private final int length;

  /**
   * Instantiates a new right padding locator.
   *
   * @param formattingConfig
   *          the formatting configuration
   * @param length
   *          the minimum amount of space allocated for an entry
   * @see {@link #getPadding(ExtendedLineEntry)}
   */
  public RightPaddingLocator(final FormattingConfig formattingConfig, final int length) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super();
    this.length = length;
  }

  /**
   * Gets the padding for an entry. The padding, concatenated with the value of the entry
   * will have the minimum length specified in the constructor.
   *
   * @param line
   *          the line containing entry / to which entry is to be added
   * @param entry
   *          the entry for which to create padding
   * @return the padding required
   */
  @Override
  public String computeSpace(final ExtendedLine line, final ExtendedLineEntry entry) {
    if (entry != null) {
      ExtendedLineEntry precedingLineEntry = line.getPrecedingLineEntry(entry);
      if (precedingLineEntry != null) {
        return SpaceEntry.createPadding(Math.max(length - precedingLineEntry.getValue().length(), 1));
      }
    }
    return SpaceEntry.createPadding(Math.max(length, 1));
  }

  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.COMBINED_MAXIMUM;
  }
}
