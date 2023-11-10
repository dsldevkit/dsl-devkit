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
package com.avaloq.tools.ddk.xtext.formatting.locators.conditional;

import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.formatting.impl.FormattingConfig.IndentationLocatorEnd;
import org.eclipse.xtext.formatting.impl.FormattingConfig.IndentationLocatorStart;
import org.eclipse.xtext.formatting.impl.FormattingConfig.LinewrapLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig.NoLinewrapLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig.NoSpaceLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig.SpaceLocator;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.OffsetLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.RightPaddingLocator;


/**
 * A factory providing all types of conditional locators.
 */
// CHECKSTYLE:COUPLING-OFF
public final class ConditionalLocatorFactory {
  // CHECKSTYLE:COUPLING-ON
  private ConditionalLocatorFactory() {
  }

  /**
   * Creates a conditional {@link LinewrapLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param lines
   *          size of linewrap in lines
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional linewrap locator
   */
  public static LinewrapLocator createConditionalLinewrapLocator(final ExtendedFormattingConfig config, final int lines, final LocatorActivator<?> locatorActivator) {
    return new ConditionalLinewrapLocator(config, lines, locatorActivator);
  }

  /**
   * Creates a conditional {@link LinewrapLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param minWraps
   *          minimal number of new lines
   * @param defaultWraps
   *          default size of linewrap locator = number of new lines
   * @param maxWraps
   *          maximal number of new lines
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional linewrap locator
   */
  public static LinewrapLocator createConditionalLinewrapLocator(final ExtendedFormattingConfig config, final int minWraps, final int defaultWraps, final int maxWraps, final LocatorActivator<?> locatorActivator) {
    return new ConditionalLinewrapLocator(config, minWraps, defaultWraps, maxWraps, locatorActivator);
  }

  /**
   * Creates a conditional {@link NoLinewrapLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional no-linewrap locator
   */
  public static NoLinewrapLocator createConditionalNoLinewrapLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator) {
    return new ConditionalNoLinewrapLocator(config, locatorActivator);
  }

  /**
   * Creates a conditional {@link SpaceLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param space
   *          space string
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional space locator
   */
  public static SpaceLocator createConditionalSpaceLocator(final ExtendedFormattingConfig config, final String space, final LocatorActivator<?> locatorActivator) {
    return new ConditionalSpaceLocator(config, space, locatorActivator);
  }

  /**
   * Creates a conditional {@link NoSpaceLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional no-space locator
   */
  public static NoSpaceLocator createConditionalNoSpaceLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator) {
    return new ConditionalNoSpaceLocator(config, locatorActivator);
  }

  /**
   * Creates a conditional {@link NoFormat} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional locator
   */
  public static NoFormatLocator createConditionalNoFormatLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator) {
    return new ConditionalNoFormatLocator(config, locatorActivator);
  }

  /**
   * Creates a conditional {@link RightPaddingLocator right padding locator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param length
   *          the minimum length of a padded token
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional RightPaddingLocator
   */
  public static RightPaddingLocator createConditionalRightPaddingLocator(final ExtendedFormattingConfig config, final int length, final LocatorActivator<?> locatorActivator) {
    return new ConditionalRightPaddingLocator(config, length, locatorActivator);
  }

  /**
   * Creates a conditional {@link ColumnLocator} that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param column
   *          the column
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional column locator
   */
  public static ColumnLocator createConditionalColumnLocator(final ExtendedFormattingConfig config, final int column, final LocatorActivator<?> locatorActivator) {
    return new ConditionalSingleLineColumnLocator(config, column, locatorActivator);
  }

  /**
   * Creates a conditional {@link FixedLocator} for the given element that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param valueColumn
   *          the column
   * @param fixed
   *          if column has a fixed position (false == column may be shifted if the preceding column exceeds the {@code column} value)
   * @param relative
   *          if column is position relatively to the current indentation (default == absolute positioning).
   * @param nobreak
   *          if column should start in the same line as the last row of the preceding multi row column
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional column locator
   */
  public static FixedLocator createConditionalFixedLocator(final ExtendedFormattingConfig config, final int valueColumn, final boolean fixed, final boolean relative, final boolean nobreak, final LocatorActivator<?> locatorActivator) {
    return new ConditionalColumnLocator(config, valueColumn, fixed, relative, nobreak, locatorActivator);
  }

  /**
   * Creates a conditional {@link OffsetLocator} relative to line indentation that is activated depending on given locator activator.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param offset
   *          the offset
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional offset locator
   */
  public static OffsetLocator createConditionalOffsetLocator(final ExtendedFormattingConfig config, final int offset, final LocatorActivator<?> locatorActivator) {
    return new ConditionalSingleLineOffsetLocator(config, offset, locatorActivator);
  }

  /**
   * Creates the conditional {@link IndentationLocatorEnd}.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param endElement
   *          indentation closing element
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation end locator
   */
  public static IndentationLocatorEnd createConditionalIndentationEndLocator(final ExtendedFormattingConfig config, final AbstractElement endElement, final LocatorActivator<?> locatorActivator) {
    return new ConditionalIndentationEndLocator(config, endElement, locatorActivator);
  }

  /**
   * Creates a conditional {@link IndentationLocatorStart}.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param beginElement
   *          indentation opening element
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation start locator
   */
  public static IndentationLocatorStart createConditionalIndentationStartLocator(final ExtendedFormattingConfig config, final AbstractElement beginElement, final LocatorActivator<?> locatorActivator) {
    return new ConditionalIndentationStartLocator(config, beginElement, locatorActivator);
  }

  /**
   * Creates a conditional {@link IndentationLocatorEnd decreased indentation locator}.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation end locator
   */
  public static IndentationLocatorEnd createConditionalIndentationEndLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator) {
    return new ConditionalIndentationEndLocator(config, locatorActivator);
  }

  /**
   * Creates a conditional {@link IndentationLocatorEnd decreased indentation locator} with the given indentation.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param indentation
   *          the indentation of the locator
   * @return new conditional indentation end locator
   */
  public static IndentationLocatorEnd createConditionalIndentationEndLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator, final int indentation) {
    return new ConditionalIndentationEndLocator(config, locatorActivator, indentation);
  }

  /**
   * Creates a conditional {@link IndentationLocatorStart}.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation start locator
   */
  public static IndentationLocatorStart createConditionalIndentationStartLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator) {
    return new ConditionalIndentationStartLocator(config, locatorActivator);
  }

  /**
   * Creates a conditional {@link IndentationLocatorStart}.
   *
   * @param config
   *          a formatting config, which newly created locators should be added to
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param indentation
   *          the indentation of the locator
   * @return new conditional indentation start locator
   */
  public static IndentationLocatorStart createConditionalIndentationStartLocator(final ExtendedFormattingConfig config, final LocatorActivator<?> locatorActivator, final int indentation) {
    return new ConditionalIndentationStartLocator(config, locatorActivator, indentation);
  }
}
