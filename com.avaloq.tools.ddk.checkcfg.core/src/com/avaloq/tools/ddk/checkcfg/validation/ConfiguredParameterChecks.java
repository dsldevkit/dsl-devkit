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
package com.avaloq.tools.ddk.checkcfg.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.validation.FormalParameterCheckBase;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.google.common.collect.Sets;

/**
 * Checkcfg formal parameter checks.
 */
@SuppressWarnings("nls")
public class ConfiguredParameterChecks extends FormalParameterCheckBase {

  private static final Map<String, Set<String>> ALLOWED_PROPERTY_VALUES = new HashMap<>();

  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * @param parameter to check.
   */
  @Check
  public void checkFormalParameterNumbersAreIntegers(final ConfiguredParameter parameter) {
    checkRightHandSideHasOnlyIntegralNumbers(parameter.getNewValue(), IssueCodes.FORMAL_PARAMETER_MUST_BE_INTEGER);
  }

  /**
   * Verifies that formal parameters defined via extension point only set values expected by the extension.
   * @param parameter to check.
   */
  @Check
  public void checkFormalParameterValuesAreValid(final ConfiguredParameter parameter) {
    final FormalParameter formalParameter = parameter.getParameter();
    final String name = formalParameter != null ? formalParameter.getName() : null;
    final String propertyName = name != null ? name.toLowerCase(Locale.ENGLISH) : null;
    if (propertyName != null) {
      Set<String> permitted = ALLOWED_PROPERTY_VALUES.get(propertyName);
      if (permitted == null) {
        final ICheckCfgPropertySpecification propertySpecification = CheckCfgUtil.getPropertySpecification(propertyName);
        String[] expected = propertySpecification != null ? propertySpecification.getExpectedValues() : null;
        if (expected == null) {
          expected = new String[0];
        }
        permitted = Sets.newHashSet(expected);
        ALLOWED_PROPERTY_VALUES.put(propertyName, permitted);
      }
      final XExpression value = parameter.getNewValue();
      if (!permitted.isEmpty() && value != null) {
        final List<XExpression> expressions = value instanceof XListLiteral xListLiteral ? xListLiteral.getElements()
            : Collections.singletonList(value);
        for (final XExpression expression : expressions) {
          if (!(expression instanceof XStringLiteral) || !permitted.contains(((XStringLiteral) expression).getValue().toLowerCase(Locale.ENGLISH))) {
            error("Not a meaningful value for %s".formatted(propertyName), expression, null, IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
          }
        }
      }
    }
  }
}
