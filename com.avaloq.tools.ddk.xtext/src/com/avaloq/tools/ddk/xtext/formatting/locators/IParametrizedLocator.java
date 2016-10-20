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
 * A parameterized locator. That is a locator that has a dynamic value that is calculated when formatting is executed.
 */
public interface IParametrizedLocator {

  /**
   * Returns the parameter calculator.
   *
   * @return the {@link LocatorParameterCalculator}
   */
  LocatorParameterCalculator<? extends EObject> getLocatorParameterCalculator();

}
