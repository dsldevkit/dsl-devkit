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
 * Interface common to all locators which are handled by extended formatting.
 */
public interface IExtendedLocator {

  /**
   * @see IExtendedLocator#getPrecedence().
   */
  enum AggregationPolicy {
    OVERRIDE,
    COMBINED_MAXIMUM,
    ADDITIVE
  };

  /**
   * Get the locator's aggregation policy. OVERRIDE locators (e.g. NoLinewrapLocatorFacade)
   * are not combined, whereas COMBINED_MAXIMUM locators are combined/aggregated, and ADDITIVE locators are added to previous locators.
   *
   * @see {@link com.avaloq.tools.ddk.xtext.formatting.SpaceEntry SpaceEntry} and {@link com.avaloq.tools.ddk.xtext.formatting.WrapEntry WrapEntry}
   * @return policy of locator
   */
  AggregationPolicy getPolicy();
}
