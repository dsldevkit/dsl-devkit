/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.validation

import com.avaloq.tools.ddk.check.validation.FormalParameterCheckBase
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import java.util.Collections
import java.util.Locale
import java.util.Map
import java.util.Set
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XStringLiteral

/**
 * Checkcfg formal parameter checks.
 */
class ConfiguredParameterChecks extends FormalParameterCheckBase {
  static Map<String, Set<String>> allowedPropertyValues = Maps.newHashMap()

  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * @param parameterto check.
   */
  @Check
  def checkFormalParameterNumbersAreIntegers(ConfiguredParameter parameter) {
    checkRightHandSideHasOnlyIntegralNumbers(parameter.getNewValue(), IssueCodes.FORMAL_PARAMETER_MUST_BE_INTEGER)
  }

  /**
   * Verifies that formal parameters defined via extension point only set values expected by the extension.
   * @param parameter to check.
   */
  @Check
  def checkFormalParameterValuesAreValid(ConfiguredParameter parameter) {
    val propertyName = parameter.parameter?.name?.toLowerCase(Locale.ENGLISH)
    if (propertyName !== null) {
      var permitted = allowedPropertyValues.get(propertyName)
      if (permitted === null) {
        val expected = CheckCfgUtil.getPropertySpecification(propertyName)?.expectedValues ?: newArrayOfSize(0)
        permitted = Sets.newHashSet(expected)
        allowedPropertyValues.put(propertyName, permitted)
      }
      val value = parameter.newValue
      if (!permitted.isNullOrEmpty && value !== null) {
        val expressions = if (value instanceof XListLiteral) value.elements else Collections.singletonList(value)
        for (expression : expressions) {
          if (!(expression instanceof XStringLiteral) || !permitted.contains((expression as XStringLiteral).value.toLowerCase(Locale.ENGLISH))) {
            error('''Not a meaningful value for «propertyName»''', expression, null, IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)
          }
        }
      }
    }
  }

}
