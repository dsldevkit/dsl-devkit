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

import org.eclipse.emf.ecore.EObject;


/**
 * Should be implemented for each parameter of any matcher of the format language (Xbase block expression) to calculate the value of a locator's parameter.
 *
 * @param <T>
 *          type of EObject
 */
public interface LocatorParameterCalculator<T extends EObject> {

  /**
   * Calculates the value of the parameter of a concrete locator (extending {@code AbstractFormattingConfig.ElementLocator}).
   *
   * @param object
   *          The object for which the parameter should be calculated
   * @param currentColumn
   *          the current column offset, can be {@code null}
   * @return the calculated parameter
   */
  int calculateParameter(T object, Integer currentColumn);
}
