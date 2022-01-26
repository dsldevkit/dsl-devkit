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

import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.interpreter.IExpressionInterpreter;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckPropertiesGenerator;
import com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper;
import com.avaloq.tools.ddk.check.runtime.configuration.LanguageSpecificCheckConfigurationStore;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
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
    generatePropertiesForLanguageValidator(configuration, properties);
    generatePropertiesForLegacyCatalog(configuration, properties);
    return properties;
  }

  /**
   * Generate properties for single configured check.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param configuredCheck
   *          the configured check
   */
  private void generatePropertiesForConfiguredCheck(final Properties properties, final String language, final ConfiguredCheck configuredCheck) {
    if (configuredCheck.getCheck() == null || configuredCheck.getCheck().eIsProxy()) {
      LOG.warn("Did not configure check " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(configuredCheck)));
      return;
    }
    for (ConfiguredParameter parameter : configuredCheck.getParameterConfigurations()) {
      if (parameter.getParameter() == null || parameter.getParameter().eIsProxy()) {
        LOG.warn("Did not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
        continue;
      }
      String propertyValue = evaluateParameterValue(parameter.getNewValue());
      if (propertyValue == null) {
        LOG.error("Could not configure parameter " + NodeModelUtils.getTokenText(NodeModelUtils.getNode(parameter)));
        continue;
      }
      putProperty(properties, language, configuredCheck.getCheck(), parameter, propertyValue);
    }
    putCheckSeverity(properties, language, configuredCheck);
    putInheritedProperties(properties, language, configuredCheck);
  }

  /**
   * Adds the inherited properties.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param configuredCheck
   *          the configured check
   */
  private void putInheritedProperties(final Properties properties, final String language, final ConfiguredCheck configuredCheck) {
    putInheritedProperties(properties, language, configuredCheck.getCheck(), (ConfiguredCatalog) configuredCheck.eContainer(), configuredCheck.getParameterConfigurations());
  }

  /**
   * Adds the inherited properties.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param check
   *          the check to configure
   * @param parentCatalog
   *          the parent catalog configuration
   * @param configuredProperties
   *          the properties already configured for this check
   */
  private void putInheritedProperties(final Properties properties, final String language, final Check check, final ConfiguredCatalog parentCatalog, final EList<ConfiguredParameter> configuredProperties) {
    // this check needs to inherit any parameters defined in one of its parent levels (ConfigurableSections).
    // the values of the inferred parameters are taken from the innermost level.

    // @Format-Off
    Set<String> configuredPropertyNames = configuredProperties.stream()
                                          .map(property->property.getParameter())
                                          .filter(Objects::nonNull)
                                          .map(formalParameter->formalParameter.getName())
                                          .collect(Collectors.toSet());
    // @Format-On

    EObject parentSection = parentCatalog;
    while (parentSection != null) {
      if (parentSection instanceof ConfigurableSection) {
        EList<ConfiguredParameter> sectionProperties = ((ConfigurableSection) parentSection).getParameterConfigurations();
        for (ConfiguredParameter property : sectionProperties) {
          if (!configuredPropertyNames.contains(property.getParameter().getName())) {
            configuredPropertyNames.add(property.getParameter().getName());
            putProperty(properties, language, check, property, evaluateParameterValue(property.getNewValue()));
          }
        }
      }
      parentSection = parentSection.eContainer();
    }
  }

  /**
   * Put property value.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param check
   *          the check
   * @param parameter
   *          the parameter
   * @param preferenceValue
   *          the preference value
   */
  private void putProperty(final Properties properties, final String language, final Check check, final ConfiguredParameter parameter, final String preferenceValue) {
    // Make property values accessible via the generated accessor methods in check:
    if (language != null) {
      properties.put(LanguageSpecificCheckConfigurationStore.getLanguageSpecificKey(language, CheckPropertiesGenerator.parameterKey(parameter.getParameter(), check)), preferenceValue);
    } else {
      properties.put(CheckPropertiesGenerator.parameterKey(parameter.getParameter(), check), preferenceValue);
    }
    // Make property values accessible via issue code for everywhere else:
    putIssueProperties(properties, language, check, parameter.getParameter(), preferenceValue);
  }

  /**
   * Put property values for issues.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param check
   *          the check
   * @param parameter
   *          the parameter
   * @param preferenceValue
   *          the preference value
   */
  private void putIssueProperties(final Properties properties, final String language, final Check check, final FormalParameter parameter, final String preferenceValue) {
    StreamSupport.stream(EcoreUtil2.eAllContents(check).spliterator(), false).filter(e -> e instanceof XIssueExpression).map(e -> (XIssueExpression) e).forEach(issue -> {
      if (language != null) {
        properties.put(LanguageSpecificCheckConfigurationStore.getLanguageSpecificKey(language, CheckPropertiesGenerator.parameterKey(parameter, issue)), preferenceValue);
      } else {
        properties.put(CheckPropertiesGenerator.parameterKey(parameter, issue), preferenceValue);
      }
    });
  }

  /**
   * Put check severity property.
   *
   * @param properties
   *          the properties
   * @param language
   *          the language
   * @param configuredCheck
   *          the check
   */
  private void putCheckSeverity(final Properties properties, final String language, final ConfiguredCheck configuredCheck) {
    SeverityKind severity = configuredCheck.getSeverity();

    if (severity == SeverityKind.DEFAULT) {
      return; // Default value doesn't need to be stored.
    }

    String severityValue = String.valueOf(com.avaloq.tools.ddk.check.check.SeverityKind.getByName(configuredCheck.getSeverity().getName().toLowerCase(Locale.ENGLISH)).getValue());

    if (language != null) {
      properties.put(LanguageSpecificCheckConfigurationStore.getLanguageSpecificKey(language, CheckPropertiesGenerator.checkSeverityKey(configuredCheck.getCheck())), severityValue);
    } else {
      properties.put(CheckPropertiesGenerator.checkSeverityKey(configuredCheck.getCheck()), severityValue);
    }
  }

  /**
   * Generate properties for legacy catalog configuration.
   *
   * @param configuration
   *          the configuration
   * @param properties
   *          the properties
   */
  private void generatePropertiesForLegacyCatalog(final CheckConfiguration configuration, final Properties properties) {
    generatePropertiesForCatalogsInConfigurableSection(configuration, properties);
  }

  /**
   * Generate properties for language.
   *
   * @param configuration
   *          the configuration
   * @param properties
   *          the properties
   */
  private void generatePropertiesForLanguageValidator(final CheckConfiguration configuration, final Properties properties) {
    for (ConfiguredLanguageValidator languageValidatorConfig : configuration.getLanguageValidatorConfigurations()) {
      generatePropertiesForCatalogsInConfigurableSection(languageValidatorConfig, properties);
    }
  }

  /**
   * Generate properties for languages or legacy catalogs.
   *
   * @param section
   *          the section
   * @param properties
   *          the properties
   */
  private void generatePropertiesForCatalogsInConfigurableSection(final ConfigurableSection section, final Properties properties) {
    String language = null;
    EList<ConfiguredCatalog> configuredCatalogs = ECollections.emptyEList();

    if (section instanceof CheckConfiguration) {
      configuredCatalogs = ((CheckConfiguration) section).getLegacyCatalogConfigurations();
    } else if (section instanceof ConfiguredLanguageValidator) {
      language = ((ConfiguredLanguageValidator) section).getLanguage();
      configuredCatalogs = ((ConfiguredLanguageValidator) section).getCatalogConfigurations();
    }

    for (ConfiguredCatalog catalog : configuredCatalogs) {
      Set<Check> configuredChecks = Sets.newHashSet();
      for (ConfiguredCheck configuredCheck : catalog.getCheckConfigurations()) {
        generatePropertiesForConfiguredCheck(properties, language, configuredCheck);
        configuredChecks.add(configuredCheck.getCheck());
      }
      for (Check unconfiguredCheck : Sets.difference(Sets.newHashSet(catalog.getCatalog().getAllChecks()), configuredChecks)) {
        putInheritedProperties(properties, language, unconfiguredCheck, catalog, ECollections.emptyEList());
      }
    }
  }

  /**
   * Evaluate an expression (value of a ConfiguredParameter).
   *
   * @param newValue
   *          the new value
   * @return the string
   */
  private String evaluateParameterValue(final XExpression newValue) {
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
      @Override
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
