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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.parsetree.reconstr.IHiddenTokenHelper;

import com.avaloq.tools.ddk.xtext.formatting.locators.ISpaceLocator;
import com.google.common.collect.Iterables;


/**
 * The Class SpaceEntry.
 * A SpaceEntry corresponds to a white space in an AcfLine.
 */
public class SpaceEntry {
  private String value;
  private final ExtendedLineEntry entry;
  private final IHiddenTokenHelper hiddenTokenHelper;
  private final ExtendedLine line;
  private AbstractRule grammarRule;

  /**
   * Instantiates a new space entry.
   *
   * @param hiddenTokenHelper
   *          the hidden token helper
   * @param line
   *          the line
   * @param entry
   *          the entry
   */
  public SpaceEntry(final IHiddenTokenHelper hiddenTokenHelper, final ExtendedLine line, final ExtendedLineEntry entry) {
    this.entry = entry;
    this.hiddenTokenHelper = hiddenTokenHelper;
    this.line = line;
  }

  /**
   * Gets the length of the value of the entry.
   *
   * @return the length
   */
  public int getLength() {
    return getValue().length();
  }

  /**
   * Gets the value of the entry.
   *
   * @return the value
   */
  public String getValue() {
    if (value == null) {
      value = combineSpaceLocators();
      if (entry.getGrammarElement() instanceof AbstractRule && line.getHiddenTokenHelper().isComment((AbstractRule) entry.getGrammarElement())) {
        value = entry.getPreservedWS();
      }
      if (getGrammarElement() == null) {
        // There is no WS rule applicable -> WS has semantics in the current context
        value = ""; //$NON-NLS-1$
      }
    }
    return value;
  }

  /**
   * Combine all space locators - the maximum length of any locator.
   *
   * @return blanks / spaces.
   */
  protected String combineSpaceLocators() {
    String space = null;
    for (ISpaceLocator locator : Iterables.filter(entry.getEntryLocators(), ISpaceLocator.class)) {
      switch (locator.getPolicy()) {
      case OVERRIDE:
        return locator.computeSpace(line, entry);
      case COMBINED_MAXIMUM:
        space = max(space, locator.computeSpace(line, entry));
        break;
      default:
        throw new IllegalArgumentException("illegal aggregation policy: " + locator.getPolicy()); //$NON-NLS-1$
      }
    }
    return space == null ? (line.isFirstEntry(entry) ? "" : " ") : space; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Retrieve the longest string of two.
   *
   * @param s1
   *          - String 1
   * @param s2
   *          - String 2
   * @return String - the longest string of s1 s2
   */
  private String max(final String s1, final String s2) {
    if (s1 != null && s2 != null && (s1.length() < s2.length())) {
      return s2;
    }
    return s1 == null ? s2 : s1;
  }

  /**
   * Gets the rule / grammar element for the white space.
   * In cases where white space has semantics no white space rule exists and hence this method will return NULL.
   *
   * @return the grammar element or null
   */
  public EObject getGrammarElement() {
    if (grammarRule == null) {
      grammarRule = hiddenTokenHelper.getWhitespaceRuleFor(entry.getHiddenTokenDefinition(), value == null ? " " : getValue()); //$NON-NLS-1$
    }
    return grammarRule;
  }

  /**
   * Creates padding / a white-space string of a specific length.
   *
   * @param length
   *          the length of the padding
   * @return String - a white-space string of specified length
   */
  public static String createPadding(final int length) {
    if (length < 0) {
      return "";
    }
    return " ".repeat(length); //$NON-NLS-1$
  }
}
