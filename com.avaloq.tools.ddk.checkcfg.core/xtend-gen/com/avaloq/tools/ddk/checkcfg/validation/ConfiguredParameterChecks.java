/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.validation;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.validation.FormalParameterCheckBase;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.validation.IssueCodes;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Checkcfg formal parameter checks.
 */
@SuppressWarnings("all")
public class ConfiguredParameterChecks extends FormalParameterCheckBase {
  private static Map<String, Set<String>> allowedPropertyValues = Maps.<String, Set<String>>newHashMap();
  
  /**
   * Verifies that numeric literals in the default values of formal parameters are all integral values.
   * @param parameterto check.
   */
  @Check
  public void checkFormalParameterNumbersAreIntegers(final ConfiguredParameter parameter) {
    XExpression _newValue = parameter.getNewValue();
    this.checkRightHandSideHasOnlyIntegralNumbers(_newValue, IssueCodes.FORMAL_PARAMETER_MUST_BE_INTEGER);
  }
  
  /**
   * Verifies that formal parameters defined via extension point only set values expected by the extension.
   * @param parameter to check.
   */
  @Check
  public void checkFormalParameterValuesAreValid(final ConfiguredParameter parameter) {
    FormalParameter _parameter = parameter.getParameter();
    String _name = null;
    if (_parameter!=null) {
      _name=_parameter.getName();
    }
    String _lowerCase = null;
    if (_name!=null) {
      _lowerCase=_name.toLowerCase(Locale.ENGLISH);
    }
    final String propertyName = _lowerCase;
    if ((propertyName != null)) {
      Set<String> permitted = ConfiguredParameterChecks.allowedPropertyValues.get(propertyName);
      if ((permitted == null)) {
        String[] _elvis = null;
        ICheckCfgPropertySpecification _propertySpecification = CheckCfgUtil.getPropertySpecification(propertyName);
        String[] _expectedValues = null;
        if (_propertySpecification!=null) {
          _expectedValues=_propertySpecification.getExpectedValues();
        }
        if (_expectedValues != null) {
          _elvis = _expectedValues;
        } else {
          String[] _newArrayOfSize = new String[0];
          _elvis = _newArrayOfSize;
        }
        final String[] expected = _elvis;
        HashSet<String> _newHashSet = Sets.<String>newHashSet(expected);
        permitted = _newHashSet;
        ConfiguredParameterChecks.allowedPropertyValues.put(propertyName, permitted);
      }
      final XExpression value = parameter.getNewValue();
      if (((!IterableExtensions.isNullOrEmpty(permitted)) && (value != null))) {
        List<XExpression> _xifexpression = null;
        if ((value instanceof XListLiteral)) {
          _xifexpression = ((XListLiteral)value).getElements();
        } else {
          _xifexpression = Collections.<XExpression>singletonList(value);
        }
        final List<XExpression> expressions = _xifexpression;
        for (final XExpression expression : expressions) {
          if (((!(expression instanceof XStringLiteral)) || (!permitted.contains(((XStringLiteral) expression).getValue().toLowerCase(Locale.ENGLISH))))) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Not a meaningful value for ");
            _builder.append(propertyName, "");
            this.error(_builder.toString(), expression, null, IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
          }
        }
      }
    }
  }
}
