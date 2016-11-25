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

import com.avaloq.tools.ddk.xtext.formatting.ExtendedLine;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;


/**
 * Interface common to all space locators which are handled by Acs formatting.
 */
public interface ISpaceLocator extends IExtendedLocator {

  /**
   * Compute the space string required by the locator.
   *
   * @param line
   *          the line containing entry / to which entry is to be added
   * @param entry
   *          the entry for which to create padding
   * @return the space required
   */
  String computeSpace(ExtendedLine line, ExtendedLineEntry entry);
}
