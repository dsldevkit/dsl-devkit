/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.validation;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.validation.FormalParameterCheckBase;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;

/**
 * Checkcfg formal parameter checks.
 */
public class ConfiguredParameterChecks extends FormalParameterCheckBase {

  private static Map<String, Set<String>> allowedPropertyValues = Maps.newHashMap();

  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * @param parameter the configured parameter to check.
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
    FormalParameter formalParam = parameter.getParameter();
    String name = formalParam != null ? formalParam.getName() : null;
    final String propertyName = name != null ? name.toLowerCase(Locale.ENGLISH) : null;
    if (propertyName != null) {
      Set<String> permitted = allowedPropertyValues.get(propertyName);
      if (permitted == null) {
        ICheckCfgPropertySpecification spec = CheckCfgUtil.getPropertySpecification(propertyName);
        String[] expected = spec != null ? spec.getExpectedValues() : null;
        if (expected == null) {
          expected = new String[0];
        }
        permitted = Sets.newHashSet(expected);
        allowedPropertyValues.put(propertyName, permitted);
      }
      final XExpression value = parameter.getNewValue();
      if (!permitted.isEmpty() && value != null) {
        List<XExpression> expressions;
        if (value instanceof XListLiteral xListLiteral) {
          expressions = xListLiteral.getElements();
        } else {
          expressions = Collections.singletonList(value);
        }
        for (XExpression expression : expressions) {
          if (!(expression instanceof XStringLiteral) || !permitted.contains(((XStringLiteral) expression).getValue().toLowerCase(Locale.ENGLISH))) {
            error("Not a meaningful value for " + propertyName, expression, null, IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
          }
        }
      }
    }
  }
}
