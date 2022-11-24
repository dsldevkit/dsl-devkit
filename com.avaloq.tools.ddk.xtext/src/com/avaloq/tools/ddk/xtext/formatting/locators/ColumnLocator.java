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

import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedLine;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;
import com.avaloq.tools.ddk.xtext.formatting.SpaceEntry;


/**
 * Column locator with column padding. The ColumnLocator is used to make elements occupy space
 * to the right of a specific character position on a line.
 * If the cursor of a line, to which an entry is to be added, is to the left of the specified column,
 * padding must be added. The method {@link #computeSpace(ExtendedLine, ExtendedLineEntry)} will calculate the
 * necessary amount of padding required.
 */
public class ColumnLocator extends AbstractFormattingConfig.ElementLocator implements ISpaceLocator {
  private int column;

  /**
   * Creates a new column locator.
   *
   * @param column
   *          the column
   * @param formattingConfig
   *          the enclosing formatting config
   */
  public ColumnLocator(final FormattingConfig formattingConfig, final int column) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super();
    this.column = column;
  }

  public void setColumn(final int column) {
    this.column = column;
  }

  /**
   * Gets the padding for an entry. When the padding is prepended to the value of the entry,
   * the entry will be at the character position specified in the constructor.
   *
   * @param line
   *          the line containing entry / to which entry is to be added
   * @param entry
   *          the entry to be padded
   * @return the padding required to ensure entry will occupy space after the specified column
   */
  @Override
  public String computeSpace(final ExtendedLine line, final ExtendedLineEntry entry) {
    int length = column - line.getAbsoluteLineLength(entry);
    if (length < 1 && column > 0) {
      // unless column is 0 always create at least one character of padding
      length = 1;
    }
    return SpaceEntry.createPadding(length);
  }

  /** {@inheritDoc} */
  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.COMBINED_MAXIMUM;
  }

}
