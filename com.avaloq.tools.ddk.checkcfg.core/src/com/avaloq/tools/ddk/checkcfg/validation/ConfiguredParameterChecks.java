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
package com.avaloq.tools.ddk.checkcfg.validation;

import org.eclipse.xtext.validation.Check;

import com.avaloq.tools.ddk.check.validation.FormalParameterCheckBase;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;


/**
 * Checkcfg formal parameter checks.
 */
public class ConfiguredParameterChecks extends FormalParameterCheckBase {

  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * 
   * @param parameter
   *          to check.
   */
  @Check
  public void checkFormalParameterNumbersAreIntegers(final ConfiguredParameter parameter) {
    checkRightHandSideHasOnlyIntegralNumbers(parameter.getNewValue(), IssueCodes.FORMAL_PARAMETER_MUST_BE_INTEGER);
  }

}

