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
package com.avaloq.tools.ddk.check.validation;

import org.eclipse.xtext.validation.Check;

import com.avaloq.tools.ddk.check.check.FormalParameter;


/**
 * Checks for formal parameters.
 */
public class FormalParameterChecks extends FormalParameterCheckBase {

  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * 
   * @param parameter
   *          to check.
   */
  @Check
  public void checkFormalParameterNumbersAreIntegers(final FormalParameter parameter) {
    checkRightHandSideHasOnlyIntegralNumbers(parameter.getRight(), IssueCodes.FORMAL_PARAMETER_MUST_BE_INTEGER);
  }

}

