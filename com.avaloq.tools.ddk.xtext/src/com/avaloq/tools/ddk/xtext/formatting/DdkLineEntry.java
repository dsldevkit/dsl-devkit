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
package com.avaloq.tools.ddk.xtext.formatting;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;

import com.avaloq.tools.ddk.xtext.formatting.DdkFormattingConfigBasedStream.AbstractDdkLineEntry;
import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IDdkLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.ILinewrapLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.OffsetLocator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;


/**
 * This class overrides {@link org.eclipse.xtext.formatting.impl.FormattingConfigBasedStream.LineEntry} in order to support processing of other locators than
 * those defined by {@link org.eclipse.xtext.formatting.impl.FormattingConfig}.
 */
public class DdkLineEntry extends AbstractDdkLineEntry {
  private final boolean noFormat;
  private final Set<IDdkLocator> locators;
  private SpaceEntry leadingSpaceEntry;
  private int columnIndent;
  private int initialIndent;
  private final DdkFormattingConfigBasedStream formattingConfigBasedStream;

  // CHECKSTYLE:OFF - Checkstyle is disabled to suppress warning about excessive number of parameters
  public DdkLineEntry(final DdkFormattingConfigBasedStream formattingConfigBasedStream, final EObject grammarElement, // NOPMD - false positive
      final String value, final boolean isHidden, final Set<ElementLocator> beforeLocators, final String leadingWS, final int indent, final ParserRule hiddenTokenDefinition) {
    // CHECKSTYLE:ON
    formattingConfigBasedStream.super(grammarElement, value, isHidden, beforeLocators, leadingWS, indent, hiddenTokenDefinition);
    this.noFormat = formattingConfigBasedStream.isFormattingDisabled();
    this.formattingConfigBasedStream = formattingConfigBasedStream;
    this.locators = Sets.newHashSet(Iterables.filter(beforeLocators, IDdkLocator.class));
  }

  /**
   * Checks if entry is breakable.
   *
   * @return true, if is breakable
   */
  @Override
  public boolean isBreakable() {
    for (ILinewrapLocator loc : Iterables.filter(getEntryLocators(), ILinewrapLocator.class)) {
      if (loc.getMaxWrap() < 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if formatting is disabled for this {@link DdkLineEntry}.
   *
   * @return {@code true} if formatting is disabled, {@code false} otherwise
   */
  public boolean isFormattingDisabled() {
    return noFormat;
  }

  /**
   * Get the grammar element corresponding to this entry's value.
   *
   * @return EObject - the grammar element, typically an {@link org.eclipse.xtext.AbstractRule}
   */
  public EObject getGrammarElement() {
    return grammarElement;
  }

  protected ParserRule getHiddenTokenDefinition() {
    return hiddenTokenDefinition;
  }

  /**
   * Returns the global indentation that applies to this line entry. If column locators are used it is relative to the column locator {@link FixedLocator}.
   *
   * @return relative indentation
   */
  public int getIndent() {
    return getColumnIndent() == 0 ? indent : indent - initialIndent;
  }

  protected boolean isHidden() {
    return isHidden;
  }

  protected Set<IDdkLocator> getEntryLocators() {
    return locators;
  }

  /**
   * Counts the number of characters up to the first line break or all if there is no line break.
   *
   * @return number of characters
   */
  public int countCharactersInFirstLine() {
    int idx = getValue().indexOf('\n');
    return idx == -1 ? getLength() : idx;
  }

  @Override
  protected int countExistingLeadingNewlines() { // NOPMD UselessOverridingMethod
    return super.countExistingLeadingNewlines();
  }

  /**
   * Returns column indentation that applies to this line entry.
   *
   * @return
   *         column indentation
   */
  public int getColumnIndent() {
    return columnIndent;
  }

  /**
   * Creates a string of spaces corresponding to the column indentation.
   *
   * @return string of whitespaces
   */
  protected String getColumnIndentPadding() {
    if (getColumnIndent() == 0) {
      return ""; //$NON-NLS-1$
    }
    return SpaceEntry.createPadding(getColumnIndent());
  }

  /**
   * Checks of a line entry contains end of column locator.
   *
   * @return true if column contains end (closing) column locator, false otherwise
   */
  public boolean isFixedLocatorClosing() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof FixedLocator && ((FixedLocator) locator).getLeft() != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks of a line entry contains beginning of column locator.
   *
   * @return true if column contains beginning (opening) column locator, false otherwise
   */
  public boolean isFixedLocatorOpening() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof FixedLocator && ((FixedLocator) locator).getRight() != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a line entry has a fixed position i.e. in case of a column shift which occurs normally, in that case line break should occur.
   *
   * @return true if column is fixed, false otherwise
   */
  public boolean isFixed() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof FixedLocator && ((FixedLocator) locator).getRight() != null && (((FixedLocator) locator).isFixed())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a column to which a line entry should be aligned to is in respect to the absolute value or in respect to the current indentation (relative).
   *
   * @return true if column is specified with respect to the current indentation, false otherwise
   */
  public boolean isRelative() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof FixedLocator && ((FixedLocator) locator).getRight() != null && (((FixedLocator) locator).isRelative())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the column of a line entry that occurs after a multi-line-span column should begin in the new line or in the same line as the last row of the
   * multi row span column.
   *
   * @return true if the next column should start in the same line as the last row of the multi row span column
   */
  public boolean isNoBreak() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof FixedLocator && ((FixedLocator) locator).getRight() != null && (((FixedLocator) locator).isNoBreak())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Backward compatibility: Checks if a line entry formatting specification contains {@link ColumnLocator} or {@link OffsetLocator}.
   *
   * @return true if a line entry specification contains "old" implementation of column/offset locators
   */
  public boolean containsVariableLocator() {
    for (IDdkLocator locator : locators) {
      if (locator instanceof ColumnLocator || locator instanceof OffsetLocator) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get all SpaceEntries which affect this entry.
   *
   * @return String with all leading space entries concatenated
   */
  public String getLeadingWS() {
    return getLeadingSpaceEntry() == null ? " " : getLeadingSpaceEntry().getValue(); //$NON-NLS-1$
  }

  public String getPreservedWS() {
    return leadingWS;
  }

  /**
   * Gets the value of the entry.
   *
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * Gets the length of the value of the entry.
   *
   * @return the length
   */
  public int getLength() {
    return getValue().length();
  }

  public SpaceEntry getLeadingSpaceEntry() {
    return leadingSpaceEntry;
  }

  public void setLeadingSpaceEntry(final SpaceEntry spaceEntry) {
    leadingSpaceEntry = spaceEntry;
  }

  /**
   * Sets the column indent. If indent is greater than 0 i.e. column should be aligned with respect to the current indentation.
   *
   * @param column
   *          column indentation
   * @param indent
   *          current indentation
   */
  public void setColumnIndent(final int column, final int indent) {
    if (isRelative()) {
      this.columnIndent = column + indent * formattingConfigBasedStream.getFormattingConfig().getIndentationSpace().length();
    } else {
      this.columnIndent = column;
    }
  }

  /**
   * Sets the initial indent i.e. current global indentation when the column opening marker occured.
   *
   * @param initialIndent
   *          initial indent
   */
  public void setInitialIndent(final int initialIndent) {
    this.initialIndent = initialIndent;
  }

}
