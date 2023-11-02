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
package com.avaloq.tools.ddk.xtext.formatting;

import java.util.Set;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.parsetree.reconstr.IHiddenTokenHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import com.avaloq.tools.ddk.xtext.formatting.locators.ILinewrapLocator;


/**
 * The Class WrapEntry.
 * A WrapEntry is the header / first entry in an AcfLineEntry.
 */
public class WrapEntry {
  private int linewraps = -1;
  private int indent = -1;
  private final ExtendedLineEntry entry;
  private final IHiddenTokenHelper hiddenTokenHelper;
  private final ExtendedLine line;
  private int minLinewrap;
  private int maxLinewrap;

  /**
   * Instantiates a new wrap entry.
   *
   * @param hiddenTokenHelper
   *          the hidden token helper
   * @param line
   *          the line
   * @param entry
   *          the entry
   */
  public WrapEntry(final IHiddenTokenHelper hiddenTokenHelper, final ExtendedLine line, final ExtendedLineEntry entry) {
    this.entry = entry;
    this.hiddenTokenHelper = hiddenTokenHelper;
    this.line = line;
  }

  /**
   * Gets the minimum amounts of possible linewraps for this entry.
   *
   * @return the minimum linewraps
   */
  public int getMinLinewrap() {
    return minLinewrap;
  }

  /**
   * Gets the maximum amounts of possible linewraps for this entry.
   *
   * @return the maximum linewraps
   */
  public int getMaxLinewrap() {
    return maxLinewrap;
  }

  /**
   * Get the amount of indent.
   *
   * @return int - the amount of indent.
   */
  public int getIndent() {
    if (indent < 0) {
      indent = entry.getIndent();
    }
    return indent;
  }

  /**
   * Get the amount of indent.
   *
   * @return int - the amount of indent.
   */
  public String getIndentString() {
    return line.getIndentation(getIndent());
  }

  /**
   * Set the number of line wraps. This will overwrite any value implied by the locators.
   *
   * @param lines
   *          - the number of line wraps.
   */
  public void setLineWraps(final int lines) {
    this.linewraps = lines;
  }

  /**
   * Get the number of NLs / line wraps.
   *
   * @return int the number of NLs
   */
  public int getLineWraps() {
    if (linewraps < 0) {
      linewraps = combineLinewraps();
    }
    return linewraps;
  }

  /**
   * Get the line wraps.
   *
   * @return String of NLs
   */
  public String getLineWrapString() {
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < getLineWraps(); i++) {
      builder.append('\n');
    }
    return builder.toString();
  }

  /**
   * Get the combined white space - line wraps + indentation.
   *
   * @return NLs + indentation
   */
  public String getValue() {
    if (entry.getColumnIndent() != 0) {
      return getLineWrapString() + entry.getColumnIndentPadding() + getIndentString();
    }
    return getLineWrapString() + getIndentString();
  }

  /**
   * Combine all linewrap locators - the maximum values of any LinewrapLocator.
   *
   * @return new lines
   */
  protected int combineLinewraps() {
    maxLinewrap = 0;
    minLinewrap = 0;
    int defaultLinewrap = 0;
    final Set<ILinewrapLocator> locators = Sets.newLinkedHashSet(Iterables.filter(entry.getEntryLocators(), ILinewrapLocator.class));
    final ILinewrapLocator requiredLW = line.isFirstEntry(entry) ? line.getInitialLinewrapLocator() : null; // NOPMD NullAssignment
    if (requiredLW != null) {
      locators.add(requiredLW);
    }
    for (ILinewrapLocator locator : locators) {

      switch (locator.getPolicy()) {
      case OVERRIDE:
        return createWrap(locator.getMinWrap(), locator.getDefaultWrap(), locator.getMaxWrap());
      case ADDITIVE:
        minLinewrap += locator.getMinWrap();
        defaultLinewrap += locator.getDefaultWrap();
        maxLinewrap += locator.getMaxWrap();
        break;
      case COMBINED_MAXIMUM:
        minLinewrap = Math.max(minLinewrap, locator.getMinWrap());
        defaultLinewrap = Math.max(defaultLinewrap, locator.getDefaultWrap());
        maxLinewrap = Math.max(maxLinewrap, locator.getMaxWrap());
        break;
      default:
        break;
      }
    }
    return createWrap(minLinewrap, defaultLinewrap, maxLinewrap);
  }

  /**
   * Creates the wrap.
   *
   * @param minWrap
   *          the min wrap
   * @param defaultWrap
   *          the default wrap
   * @param maxWrap
   *          the max wrap
   * @return the string
   */
  protected int createWrap(final int minWrap, final int defaultWrap, final int maxWrap) {
    int def = 0;
    int min = Math.max(def, minWrap);
    def = Math.max(def, defaultWrap);
    int max = Math.max(def, maxWrap);
    if (min != max) {
      final int existing = entry.countExistingLeadingNewlines();
      if (existing >= 0) {
        def = existing;
      }
    }
    return Math.max(min, Math.min(def, max));
  }

  /**
   * Gets the rule for the white space.
   *
   * @return the rule
   */
  public AbstractRule getGrammarElement() {
    return hiddenTokenHelper.getWhitespaceRuleFor(entry.getHiddenTokenDefinition(), getValue());
  }

  /** {@inheritDoc} */
  public boolean isBreakable() {
    return false;
  }

  /**
   * Checks if line entry requires line wrapping.
   *
   * @return true, if line entry requires wrapping
   */
  public boolean isWrap() {
    return getValue().indexOf('\n') > -1;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append('"');
    if (getValue().lastIndexOf('\n') > -1) {
      for (int i = 0; i <= getValue().lastIndexOf('\n'); i++) {
        builder.append("\\n"); //$NON-NLS-1$
      }
    }
    for (int i = 0; i < entry.getIndent(); i++) {
      builder.append(' ');
    }
    builder.append('"');
    return builder.toString();
  }
}
