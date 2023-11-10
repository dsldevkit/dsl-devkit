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

import org.eclipse.xtext.formatting.IIndentationInformation;


/**
 * Custom {@link IIndentationInformation} to be able to define and get the default indentation length to be used when no explicit length is used in indentation
 * rules.
 */
public interface IIndentationInformationWithDefaults extends IIndentationInformation {

  /**
   * Gets the default indentation length (i.e. the total number of whitespace characters).
   * This is not necessarily related to the string returned by getIndentString().
   * getIndentString() returns the string used to indent a line one level while this method returns the amount of whitespace characters used in case no
   * indentation level is defined by a formatting rule.
   *
   * @return the default indentation
   */
  int getDefaultIndentation();
}
