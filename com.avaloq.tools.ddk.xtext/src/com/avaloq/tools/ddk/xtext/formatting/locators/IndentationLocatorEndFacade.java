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
package com.avaloq.tools.ddk.xtext.formatting.locators;

import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;


/**
 * IndentationLocatorEndFacade used to customized the behavior of {@link IndentationLocatorEnd}.
 */
public class IndentationLocatorEndFacade extends FormattingConfig.IndentationLocatorEnd implements IIndentationLocator {

  private int indentation;

  public IndentationLocatorEndFacade(final ExtendedFormattingConfig formattingConfig) { // NOPMD CallSuperInConstructor - false positive by PMD
    formattingConfig.super();
    indentation = formattingConfig.getDefaultIndentation();
  }

  public IndentationLocatorEndFacade(final ExtendedFormattingConfig formattingConfig, final AbstractElement ele) { // NOPMD CallSuperInConstructor - false
    // positive by PMD
    formattingConfig.super(ele);
    indentation = formattingConfig.getDefaultIndentation();
  }

  public IndentationLocatorEndFacade(final ExtendedFormattingConfig formattingConfig, final int indentation) { // NOPMD CallSuperInConstructor - false
    // positive by PMD
    formattingConfig.super();
    this.indentation = indentation;
  }

  public IndentationLocatorEndFacade(final ExtendedFormattingConfig formattingConfig, final int indentation, final AbstractElement ele) { // NOPMD
    // CallSuperInConstructor
    // - false positive
    // by PMD
    formattingConfig.super(ele);
    this.indentation = indentation;
  }

  @Override
  public int getIndentation() {
    return indentation;
  }

  @Override
  public void setIndentation(final int indentation) {
    this.indentation = indentation;
  }

  @Override
  public AggregationPolicy getPolicy() {
    return AggregationPolicy.ADDITIVE;
  };
}
