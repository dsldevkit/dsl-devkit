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
package com.avaloq.tools.ddk.checkcfg.generator;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.interpreter.IExpressionInterpreter;

import com.avaloq.tools.ddk.check.generator.CheckPropertiesGenerator;
import com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper;
import com.avaloq.tools.ddk.check.runtime.configuration.LanguageSpecificCheckConfigurationStore;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * The Class CheckConfigurationPropertiesGenerator.
 */
public final class CheckConfigurationPropertiesGenerator {

  /** Class-wide logger. */
  private static final Logger LOG = Logger.getLogger(CheckConfigurationPropertiesGenerator.class);

  @Inject
  private IExpressionInterpreter interpreter;

  /**
   * Create a Properties object for a given configuration.
   * 
   * @param configuration
   *          the check configuration model instance
   * @return a properties object with all check configurations
   */
  public Properties convertToProperties(final CheckConfiguration configuration) {
    Properties properties = new Properties();
    for (ConfiguredLanguageValidator languageValidatorConfig : configuration.getLanguageValidatorConfigurations()) {
      final String language = languageValidatorConfig.getLanguage();
      for (ConfiguredCatalog catalog : languageValidatorConfig.getCatalogConfigurations()) {
        for (ConfiguredCheck check : catalog.getCheckConfigurations()) {
          if (check.getCheck() != null && !check.getCheck().eIsProxy()) {
            properties.put(LanguageSpecificCheckConfigurationStore.getLanguageSpecificKey(language, CheckPropertiesGenerator.checkSeverityKey(check.getCheck())), String.valueOf(severityValue(check)));
            for (ConfiguredParameter parameter : check.getParameterConfigurations()) {
              if (parameter.getParameter() != null && !parameter.getParameter().eIsProxy()) {
                String preferenceValue = evaluate(parameter.getNewValue());
                if (preferenceValue != null) {
                  properties.put(LanguageSpecificCheckConfigurationStore.getLanguageSpecificKey(language, CheckPropertiesGenerator.parameterSeverityKey(parameter.getParameter())), preferenceValue);
                } else {
                  LOG.error("Could not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
                }
              } else {
                LOG.warn("Did not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
              }
            }
          } else {
            LOG.warn("Did not configure check " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(check)));
          }
        }
      }
    }
    for (ConfiguredCatalog catalog : configuration.getLegacyCatalogConfigurations()) {
      for (ConfiguredCheck check : catalog.getCheckConfigurations()) {
        if (check.getCheck() != null && !check.getCheck().eIsProxy()) {
          properties.put(CheckPropertiesGenerator.checkSeverityKey(check.getCheck()), String.valueOf(severityValue(check)));
          for (ConfiguredParameter parameter : check.getParameterConfigurations()) {
            if (parameter.getParameter() != null && !parameter.getParameter().eIsProxy()) {
              String preferenceValue = evaluate(parameter.getNewValue());
              if (preferenceValue != null) {
                properties.put(CheckPropertiesGenerator.parameterSeverityKey(parameter.getParameter()), preferenceValue);
              } else {
                LOG.error("Could not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
              }
            } else {
              LOG.warn("Did not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
            }
          }
        } else {
          LOG.warn("Did not configure check " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(check)));
        }
      }
    }

    return properties;
  }

  /**
   * Returns the severity of a configured check, or that of the initial check if the default
   * severity is inherited. Care is taken of returning an integer value that is consistent with that of the Check.
   * 
   * @param check
   *          the configured check
   * @return the numeric value corresponding to the configured severity.
   */
  public static int severityValue(final ConfiguredCheck check) {
    SeverityKind severity = check.getSeverity();
    if (severity == SeverityKind.DEFAULT) {
      return check.getCheck().getDefaultSeverity().getValue();
    } else {
      return com.avaloq.tools.ddk.check.check.SeverityKind.getByName(check.getSeverity().getName().toLowerCase()).getValue();

    }
  }

  /**
   * Evaluate an expression (value of a ConfiguredParameter).
   * 
   * @param newValue
   *          the new value
   * @return the string
   */
  private String evaluate(final XExpression newValue) {
    if (newValue instanceof XListLiteral) {
      return evaluateList((XListLiteral) newValue);
    } else {
      Object evaluationResult = interpreter.evaluate(newValue).getResult();
      if (evaluationResult instanceof Boolean) {
        return ((Boolean) evaluationResult).toString();
      } else if (evaluationResult instanceof Integer) {
        return ((Integer) evaluationResult).toString();
      } else if (evaluationResult instanceof String) {
        // Note: control characters are no problem; java.util.Properties is smart enough to encode/decode those.
        return (String) evaluationResult;
      }
    }
    return null;
  }

  /**
   * Evaluates a list.
   * 
   * @param list
   *          to evaluate
   * @return the evaluated list.
   */
  private String evaluateList(final XListLiteral list) {
    Iterable<Object> evaluated = Iterables.transform(list.getElements(), new Function<XExpression, Object>() {
      public Object apply(final XExpression expr) {
        return interpreter.evaluate(expr).getResult();
      }
    });
    Object first = Iterables.getFirst(evaluated, null);
    if (first != null) {
      if (first instanceof Boolean) {
        return CheckPreferencesHelper.marshalBooleans(Iterables.filter(evaluated, Boolean.class));
      } else if (first instanceof Integer) {
        return CheckPreferencesHelper.marshalIntegers(Iterables.filter(evaluated, Integer.class));
      } else if (first instanceof String) {
        return CheckPreferencesHelper.marshalStrings(Iterables.filter(evaluated, String.class));
      }
    }
    return null;
  }

}

