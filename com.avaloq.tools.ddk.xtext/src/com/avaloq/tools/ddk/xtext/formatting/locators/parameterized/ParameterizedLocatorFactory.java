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
package com.avaloq.tools.ddk.xtext.formatting.locators.parameterized;

import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.formatting.impl.FormattingConfig.IndentationLocatorEnd;
import org.eclipse.xtext.formatting.impl.FormattingConfig.IndentationLocatorStart;

import com.avaloq.tools.ddk.xtext.formatting.DdkFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;


/**
 * A factory providing all types of parameterized locators.
 */
// CHECKSTYLE:COUPLING-OFF
public final class ParameterizedLocatorFactory {
  // CHECKSTYLE:COUPLING-ON
  private ParameterizedLocatorFactory() {}

  /**
   * Creates the parameterized {@link ColumnLocator} that gets its column value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link ColumnLocator}
   */
  public static ColumnLocator createParameterizedColumnLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedSingleLineColumnLocator(config, calculator);
  }

  /**
   * Creates the parameterized conditional {@link ColumnLocator} that gets its column value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized {@link ColumnLocator}
   */
  public static ColumnLocator createParameterizedConditionalColumnLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalSingleLineColumnLocator(config, calculator, activator);
  }

  /**
   * Creates a parameterized {@link FixedLocator} that gets its column value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param fixed
   *          if the column has a fixed position
   * @param relative
   *          if the column is positioned relatively to the current indentation (default == absolute positioning).
   * @param nobreak
   *          if the column should start on the same line as the last row of the preceding multi-row column
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link FixedLocator}
   */
  public static FixedLocator createParameterizedColumnLocator(final DdkFormattingConfig config, final boolean fixed, final boolean relative, final boolean nobreak, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedColumnLocator(config, fixed, relative, nobreak, calculator);
  }

  /**
   * Creates a parameterized {@link FixedLocator} that gets its column value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param fixed
   *          if the column has a fixed position
   * @param relative
   *          if the column is positioned relatively to the current indentation (default == absolute positioning).
   * @param nobreak
   *          if the column should start on the same line as the last row of the preceding multi-row column
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized {@link FixedLocator}
   */
  public static FixedLocator createParameterizedConditionalColumnLocator(final DdkFormattingConfig config, final boolean fixed, final boolean relative, final boolean nobreak, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalColumnLocator(config, fixed, relative, nobreak, calculator, activator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorStart} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link IndentationLocatorStart}
   */
  public static IndentationLocatorStart createParameterizedIndentationStartLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedIndentationStartLocator(config, calculator);
  }

  /**
   * Creates the conditional parameterized {@link IndentationLocatorStart} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized conditional {@link IndentationLocatorStart}
   */
  public static IndentationLocatorStart createParameterizedConditionalIndentationStartLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalIndentationStartLocator(config, calculator, activator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorStart} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param beginElement
   *          the {@link AbstractElement} the indentation will start from
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link IndentationLocatorStart}
   */
  public static IndentationLocatorStart createParameterizedIndentationStartLocator(final DdkFormattingConfig config, final AbstractElement beginElement, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedIndentationStartLocator(config, beginElement, calculator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorStart} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param beginElement
   *          the {@link AbstractElement} the indentation will start from
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized conditional {@link IndentationLocatorStart}
   */
  public static IndentationLocatorStart createParameterizedConditionalIndentationStartLocator(final DdkFormattingConfig config, final AbstractElement beginElement, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalIndentationStartLocator(config, beginElement, calculator, activator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorEnd} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link IndentationLocatorEnd}
   */
  public static IndentationLocatorEnd createParameterizedIndentationEndLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedIndentationEndLocator(config, calculator);
  }

  /**
   * Creates the conditional parameterized {@link IndentationLocatorEnd} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized conditional {@link IndentationLocatorEnd}
   */
  public static IndentationLocatorEnd createParameterizedConditionalIndentationEndLocator(final DdkFormattingConfig config, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalIndentationEndLocator(config, calculator, activator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorEnd} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param endElement
   *          the {@link AbstractElement} the indentation will end at
   * @param calculator
   *          the calculator of the parameter
   * @return the new parameterized {@link IndentationLocatorEnd}
   */
  public static IndentationLocatorEnd createParameterizedIndentationEndLocator(final DdkFormattingConfig config, final AbstractElement endElement, final LocatorParameterCalculator<?> calculator) {
    return new ParameterizedIndentationEndLocator(config, endElement, calculator);
  }

  /**
   * Creates the parameterized {@link IndentationLocatorEnd} that gets its indentation value from the given {@link LocatorParameterCalculator}.
   *
   * @param config
   *          the formatting config, which the newly created locator should be added to
   * @param endElement
   *          the {@link AbstractElement} the indentation will end at
   * @param calculator
   *          the calculator of the parameter
   * @param activator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized conditional {@link IndentationLocatorEnd}
   */
  public static IndentationLocatorEnd createParameterizedConditionalIndentationEndLocator(final DdkFormattingConfig config, final AbstractElement endElement, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> activator) {
    return new ParameterizedConditionalIndentationEndLocator(config, endElement, calculator, activator);
  }

}
