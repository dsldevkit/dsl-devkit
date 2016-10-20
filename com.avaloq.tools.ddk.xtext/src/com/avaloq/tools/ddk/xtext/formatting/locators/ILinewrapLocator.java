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

/**
 * Interface common to all line wrapping locators which are handled by DDK formatting.
 */
public interface ILinewrapLocator extends IDdkLocator {

  /**
   * Get the default number of lines to wrap - see {@link org.eclipse.xtext.formatting.impl.FormattingConfig.LinewrapLocator#getDefaultWrap()}.
   *
   * @return the default number of lines to wrap
   */
  int getDefaultWrap();

  /**
   * Get the default number of lines to wrap - see {@link org.eclipse.xtext.formatting.impl.FormattingConfig.LinewrapLocator#getMaxWrap()}.
   *
   * @return the maximum number of lines to wrap
   */
  int getMaxWrap();

  /**
   * Get the default number of lines to wrap - see {@link org.eclipse.xtext.formatting.impl.FormattingConfig.LinewrapLocator#getMinWrap()}.
   *
   * @return the minimum number of lines to wrap
   */
  int getMinWrap();

}
