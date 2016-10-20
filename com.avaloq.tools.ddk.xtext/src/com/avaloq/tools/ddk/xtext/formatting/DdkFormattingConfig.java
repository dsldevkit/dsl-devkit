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
package com.avaloq.tools.ddk.xtext.formatting; // NOPMD ExcessiveImports

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.formatting.IIndentationInformation;
import org.eclipse.xtext.formatting.ILineSeparatorInformation;
import org.eclipse.xtext.formatting.impl.FormattingConfig2;
import org.eclipse.xtext.parsetree.reconstr.IHiddenTokenHelper;

import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorEndFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorStartFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LinewrapLocatorFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoLinewrapLocatorFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoSpaceLocatorFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.OffsetLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.RightPaddingLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.SpaceLocatorFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.conditional.ConditionalLocatorFactory;
import com.avaloq.tools.ddk.xtext.formatting.locators.parameterized.ParameterizedLocatorFactory;


/**
 * The Class AcfFormattingConfig extends {@link AbstractDdkFormattingConfig} in order to introduce {@link NoFormatLocator}, custom {@link IndentationLocatorEnd}
 * and {@link IndentationLocatorStart} and parameterized and conditional locators.
 */
// CHECKSTYLE:COUPLING-OFF
public class DdkFormattingConfig extends FormattingConfig2 {
  // CHECKSTYLE:COUPLING-ON
  /**
   * Instantiates a new DdkFormattingConfig.
   *
   * @param grammarAccess
   *          the grammar access
   * @param hiddenTokenHelper
   *          the hidden token helper
   * @param indentInfo
   *          the indent info
   * @param lineSeparatorInfo
   *          the line separator info
   */
  public DdkFormattingConfig(final IGrammarAccess grammarAccess, final IHiddenTokenHelper hiddenTokenHelper, final IIndentationInformation indentInfo, final ILineSeparatorInformation lineSeparatorInfo) {
    super(grammarAccess, hiddenTokenHelper, indentInfo, lineSeparatorInfo);
  }

  public int getDefaultIndentation() {
    return ((IDdkIndentationInformation) indentInfo).getDefaultIndentation();
  }

  @Override
  public LinewrapLocator setLinewrap() {
    return setLinewrap(1);
  }

  @Override
  public LinewrapLocator setLinewrap(final int lines) {
    return new LinewrapLocatorFacade(this, lines);
  }

  @Override
  public LinewrapLocator setLinewrap(final int minWraps, final int defaultWraps, final int maxWraps) {
    return new LinewrapLocatorFacade(this, minWraps, defaultWraps, maxWraps);
  }

  @Override
  public NoLinewrapLocator setNoLinewrap() {
    return new NoLinewrapLocatorFacade(this);
  }

  @Override
  public SpaceLocator setSpace(final String space) {
    return new SpaceLocatorFacade(this, space);
  }

  @Override
  public NoSpaceLocator setNoSpace() {
    return new NoSpaceLocatorFacade(this);
  }

  /**
   * Sets the increased indentation without any predefined indentation (the default setting will be used).
   *
   * @return new indentation start locator facade
   */
  @Override
  public IndentationLocatorStart setIndentationIncrement() {
    return new IndentationLocatorStartFacade(this);
  }

  /**
   * Sets the decrease indentation without any predefined indentation (the default setting will be used).
   *
   * @return new indentation end locator facade
   */
  @Override
  public IndentationLocatorEnd setIndentationDecrement() {
    return new IndentationLocatorEndFacade(this);
  }

  // ******** Linewrap Setters ********//

  /**
   * Sets a conditional linewrap locator that is activated depending on given locator activator.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional linewrap locator
   */
  public LinewrapLocator setLinewrap(final LocatorActivator<?> locatorActivator) {
    return setLinewrap(1, locatorActivator);
  }

  /**
   * Sets a conditional linewrap locator that is activated depending on given locator activator.
   *
   * @param lines
   *          size of linewrap in lines
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional linewrap locator
   */
  public LinewrapLocator setLinewrap(final int lines, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalLinewrapLocator(this, lines, locatorActivator);
  }

  /**
   * Sets a conditional linewrap locator that is activated depending on given locator activator.
   *
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
  public LinewrapLocator setLinewrap(final int minWraps, final int defaultWraps, final int maxWraps, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalLinewrapLocator(this, minWraps, defaultWraps, maxWraps, locatorActivator);
  }

  /**
   * Sets a conditional no-linewrap locator that is activated depending on given locator activator.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional no-linewrap locator
   */
  public NoLinewrapLocator setNoLinewrap(final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalNoLinewrapLocator(this, locatorActivator);
  }

  // ******** NoFormat Setters ********//

  /**
   * Sets a NoFormat locator.
   *
   * @return new NoFormat locator
   */
  public NoFormatLocator setNoFormat() {
    return new NoFormatLocator(this);
  }

  /**
   * Sets a conditional NoFormat locator that is activated depending on given locator activator.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional NoFormat locator
   */
  public NoFormatLocator setNoFormat(final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalNoFormatLocator(this, locatorActivator);
  }

  // ******** NoSpace Setters ********//

  /**
   * Sets a conditional space locator that is activated depending on given locator activator.
   *
   * @param space
   *          space string
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional space locator
   */
  public SpaceLocator setSpace(final String space, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalSpaceLocator(this, space, locatorActivator);
  }

  /**
   * Sets a conditional no-space locator that is activated depending on given locator activator.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional no-space locator
   */
  public NoSpaceLocator setNoSpace(final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalNoSpaceLocator(this, locatorActivator);
  }

  // ******** Right Padding Setters ********//

  /**
   * Sets a RightPaddingLocator.
   *
   * @param length
   *          the minimum length of a padded token
   * @return the RightPaddingLocator
   */
  public RightPaddingLocator setRightPadding(final int length) {
    return new RightPaddingLocator(this, length);
  }

  /**
   * Sets a conditional RightPaddingLocator that is activated depending on given locator activator.
   *
   * @param length
   *          the minimum length of a padded token
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional RightPaddingLocator
   */
  public RightPaddingLocator setRightPadding(final int length, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalRightPaddingLocator(this, length, locatorActivator);
  }

  // ******** Column Setters ********//

  /**
   * Sets the column.
   *
   * @param column
   *          the column
   * @return the column locator
   */
  public ColumnLocator setColumn(final int column) {
    return new ColumnLocator(this, column);
  }

  /**
   * Sets the conditional column locator that is activated depending on given locator activator.
   *
   * @param column
   *          the column
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional column locator
   */
  public ColumnLocator setColumn(final int column, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalColumnLocator(this, column, locatorActivator);
  }

  /**
   * Sets the parameterized column locator that gets the value of its parameter at runtime from the given calculator.
   *
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return the new parameterized column locator
   */
  public ColumnLocator setColumn(final LocatorParameterCalculator<?> calculator) {
    return ParameterizedLocatorFactory.createParameterizedColumnLocator(this, calculator);
  }

  /**
   * Sets the parameterized conditional column locator that gets the value of its parameter at runtime from the given calculator.
   *
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return the new parameterized column locator
   */
  public ColumnLocator setColumn(final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    return ParameterizedLocatorFactory.createParameterizedConditionalColumnLocator(this, calculator, locatorActivator);
  }

  /**
   * Sets a column locator for the given element.
   *
   * @param valueColumn
   *          the column
   * @param fixed
   *          if the column has a fixed position (false means that the column may be shifted if the preceding column exceeds this column's value)
   * @param relative
   *          if column is positioned relatively to the current indentation.
   * @param nobreak
   *          if column should start in the same line as the last row of the preceding multi row column
   * @return the column locator
   */
  public FixedLocator setColumn(final int valueColumn, final boolean fixed, final boolean relative, final boolean nobreak) {
    return new FixedLocator(this, valueColumn, fixed, relative, nobreak);
  }

  /**
   * Sets a conditional column locator for the given element that is activated depending on given locator activator.
   *
   * @param valueColumn
   *          the column
   * @param fixed
   *          if the column has a fixed position (false means that the column may be shifted if the preceding column exceeds this column's value)
   * @param relative
   *          if the column is positioned relatively to the current indentation.
   * @param nobreak
   *          if the column should start on the same line as the last row of the preceding multi-row column
   * @param locatorActivator
   *          the activator indicating when the conditional locator is active
   * @return new conditional column locator
   */
  public FixedLocator setColumn(final int valueColumn, final boolean fixed, final boolean relative, final boolean nobreak, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalFixedLocator(this, valueColumn, fixed, relative, nobreak, locatorActivator);
  }

  /**
   * Sets a parameterized column locator that gets the value of its parameter at runtime from the given calculator.
   *
   * @param fixed
   *          if the column has a fixed position (false means that the column may be shifted if the preceding column exceeds this column's value)
   * @param relative
   *          if the column is positioned relatively to the current indentation.
   * @param nobreak
   *          if the column should start on the same line as the last row of the preceding multi-row column
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return the new parameterized column locator
   */
  public FixedLocator setColumn(final boolean fixed, final boolean relative, final boolean nobreak, final LocatorParameterCalculator<?> calculator) {
    return ParameterizedLocatorFactory.createParameterizedColumnLocator(this, fixed, relative, nobreak, calculator);
  }

  /**
   * Sets a parameterized conditional column locator that gets the value of its parameter at runtime from the given calculator.
   *
   * @param fixed
   *          if the column has a fixed position (false means that the column may be shifted if the preceding column exceeds this column's value)
   * @param relative
   *          if the column is positioned relatively to the current indentation.
   * @param nobreak
   *          if the column should start on the same line as the last row of the preceding multi-row column
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @param locatorActivator
   *          the activator indicating when the conditional locator is active
   * @return the new parameterized column locator
   */
  public FixedLocator setColumn(final boolean fixed, final boolean relative, final boolean nobreak, final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    return ParameterizedLocatorFactory.createParameterizedConditionalColumnLocator(this, fixed, relative, nobreak, calculator, locatorActivator);
  }

  /**
   * Sets the ColumnFormatting on an element, i.e.
   * line wrap before, column (as given), one indentation before the element,
   * reset indentation afterwards
   *
   * @param element
   *          the element
   * @param column
   *          the column
   */
  public void setAbsoluteColumnFormatting(final AbstractElement element, final int column) {
    setAbsoluteColumnFormatting(element, column, 1);
  }

  /**
   * Sets the ColumnFormatting on an element, i.e.
   * line wrap before, column (as given), a given number of indentation before the element,
   * reset indentation afterwards.
   *
   * @param element
   *          element to apply changes for
   * @param column
   *          column position after element
   * @param indentation
   *          the indentation before the element
   */
  public void setAbsoluteColumnFormatting(final AbstractElement element, final int column, final int indentation) {
    breakBeforeAndAlignAfterElement(element, 1, column);
    increaseIndentationAround(element, indentation);
  }

  // ******** Offset Setters ********//

  /**
   * Sets the offset / column relative to line indentation.
   *
   * @param offset
   *          the offset
   * @return the offset locator
   */
  public OffsetLocator setOffset(final int offset) {
    return new OffsetLocator(this, offset);
  }

  /**
   * Sets the conditional offset / column relative to line indentation that is activated depending on given locator activator.
   *
   * @param offset
   *          the offset
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional offset locator
   */
  public OffsetLocator setOffset(final int offset, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalOffsetLocator(this, offset, locatorActivator);
  }

  // ******** Indentation Setters ********//

  /**
   * Sets the conditional indentation between the beginElement and the endElement.
   *
   * @param beginElement
   *          indentation opening element
   * @param endElement
   *          indentation closing element
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   */
  public void setIndentation(final AbstractElement beginElement, final AbstractElement endElement, final LocatorActivator<?> locatorActivator) {
    ConditionalLocatorFactory.createConditionalIndentationStartLocator(this, beginElement, locatorActivator);
    ConditionalLocatorFactory.createConditionalIndentationEndLocator(this, endElement, locatorActivator);
  }

  /**
   * Sets the parameterized indentation between the beginElement and the endElement.
   *
   * @param beginElement
   *          indentation opening element
   * @param endElement
   *          indentation closing element
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   */
  public void setIndentation(final AbstractElement beginElement, final AbstractElement endElement, final LocatorParameterCalculator<?> calculator) {
    ParameterizedLocatorFactory.createParameterizedIndentationStartLocator(this, beginElement, calculator);
    ParameterizedLocatorFactory.createParameterizedIndentationEndLocator(this, endElement, calculator);
  }

  /**
   * Sets the conditional parameterized indentation between the beginElement and the endElement.
   *
   * @param beginElement
   *          indentation opening element
   * @param endElement
   *          indentation closing element
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   */
  public void setIndentation(final AbstractElement beginElement, final AbstractElement endElement, final LocatorActivator<?> locatorActivator, final LocatorParameterCalculator<?> calculator) {
    ParameterizedLocatorFactory.createParameterizedConditionalIndentationStartLocator(this, beginElement, calculator, locatorActivator);
    ParameterizedLocatorFactory.createParameterizedConditionalIndentationEndLocator(this, endElement, calculator, locatorActivator);
  }

  /**
   * Sets the increased indentation with a defined indentation.
   *
   * @param indentation
   *          The indentation to set
   * @return new indentation start locator facade
   */
  public IndentationLocatorStart setIndentationIncrement(final int indentation) {
    return new IndentationLocatorStartFacade(this, indentation);
  }

  /**
   * Sets the conditional increased indentation.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation start locator
   */
  public IndentationLocatorStart setIndentationIncrement(final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalIndentationStartLocator(this, locatorActivator);
  }

  /**
   * Sets the conditional increased indentation with a given indentation.
   *
   * @param indentation
   *          the indentation to set
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation start locator
   */
  public IndentationLocatorStart setIndentationIncrement(final int indentation, final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalIndentationStartLocator(this, locatorActivator, indentation);
  }

  /**
   * Sets the parameterized increased indentation.
   *
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return new parameterized indentation start locator
   */
  public IndentationLocatorStart setIndentationIncrement(final LocatorParameterCalculator<?> calculator) {
    return ParameterizedLocatorFactory.createParameterizedIndentationStartLocator(this, calculator);
  }

  /**
   * Sets the conditional parameterized increased indentation.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return new parameterized conditional indentation start locator
   */
  public IndentationLocatorStart setIndentationIncrement(final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    return ParameterizedLocatorFactory.createParameterizedConditionalIndentationStartLocator(this, calculator, locatorActivator);
  }

  /**
   * Sets the decreased indentation with a defined indentation.
   *
   * @param indentation
   *          The indentation to set
   * @return new indentation end locator facade
   */
  public IndentationLocatorEnd setIndentationDecrement(final int indentation) {
    return new IndentationLocatorEndFacade(this, indentation);
  }

  /**
   * Sets the conditional decreased indentation.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @return new conditional indentation end locator
   */
  public IndentationLocatorEnd setIndentationDecrement(final LocatorActivator<?> locatorActivator) {
    return ConditionalLocatorFactory.createConditionalIndentationEndLocator(this, locatorActivator);
  }

  /**
   * Sets the conditional decreased indentation with a given indentation.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param indentation
   *          the indentation to set
   * @return new conditional indentation end locator
   */
  public IndentationLocatorEnd setIndentationDecrement(final LocatorActivator<?> locatorActivator, final int indentation) {
    return ConditionalLocatorFactory.createConditionalIndentationEndLocator(this, locatorActivator, indentation);
  }

  /**
   * Sets the parameterized decreased indentation.
   *
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return new parameterized indentation end locator
   */
  public IndentationLocatorEnd setIndentationDecrement(final LocatorParameterCalculator<?> calculator) {
    return ParameterizedLocatorFactory.createParameterizedIndentationEndLocator(this, calculator);
  }

  /**
   * Sets the conditional parameterized decreased indentation.
   *
   * @param locatorActivator
   *          activator indicating when the conditional locator is active
   * @param calculator
   *          the {@link LocatorParameterCalculator} that calculates the parameter
   * @return new parameterized conditional indentation end locator
   */
  public IndentationLocatorEnd setIndentationDecrement(final LocatorParameterCalculator<?> calculator, final LocatorActivator<?> locatorActivator) {
    return ParameterizedLocatorFactory.createParameterizedConditionalIndentationEndLocator(this, calculator, locatorActivator);
  }

  /**
   * Increase indentation level around the given grammar Element.
   *
   * @param grammarElement
   *          grammar Element below which the indentation level shall be increased
   * @param indentation
   *          the indentation to set
   */
  public void increaseIndentationAround(final EObject grammarElement, final int indentation) {
    setIndentationIncrement(indentation).before(grammarElement);
    setIndentationDecrement(indentation).after(grammarElement);
  }

  /**
   * Increase indentation level around the given grammar Element without any predefined indentation (the default setting will be used).
   *
   * @param grammarElement
   *          grammar Element below which the indentation level shall be increased
   */
  public void increaseIndentationAround(final EObject grammarElement) {
    setIndentationIncrement().before(grammarElement);
    setIndentationDecrement().after(grammarElement);
  }

  /**
   * Add line wraps before element and set column after element.
   *
   * @param element
   *          element to apply changes for
   * @param lineWrapBefore
   *          number of line wraps before the keyword
   * @param column
   *          column position after element
   */
  public void breakBeforeAndAlignAfterElement(final AbstractElement element, final int lineWrapBefore, final int column) {
    setLinewrap(lineWrapBefore).before(element);
    setColumn(column).after(element);
  }

}
