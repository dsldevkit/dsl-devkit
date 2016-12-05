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

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ComposedChecks;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.SeverityRange;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.inject.Inject;


/**
 * Configures validations for the Check Configuration language.
 */
@ComposedChecks(validators = {ConfiguredParameterChecks.class})
public class CheckCfgJavaValidator extends AbstractCheckCfgJavaValidator {

  private static final Logger LOGGER = Logger.getLogger(CheckCfgJavaValidator.class);

  /** Used for evaluating expressions. */
  @Inject
  private XbaseInterpreter interpreter;

  @Inject
  private CheckCfgUtil checkCfgUtil;

  /** Defines the set of references for which type checking should be performed by {@link #getTypeConformanceCheckedReferences()}. */
  private final Set<EReference> checkTypeConformanceCheckedReferences = ImmutableSet.of(CheckcfgPackage.Literals.CONFIGURED_PARAMETER__NEW_VALUE);

  /**
   * Performs type checking on expressions.
   * <p>
   * {@inheritDoc}
   */
  public Set<EReference> getTypeConformanceCheckedReferences() {
    return checkTypeConformanceCheckedReferences;
  }

  /**
   * Checks that a {@link com.avaloq.tools.ddk.check.check.Check Check} which has been disabled in the
   * configuration does not have any configured properties.
   * 
   * @param configuredCheck
   *          the configured check
   */
  @Check
  public void checkDisabledCheckIsNotConfigured(final ConfiguredCheck configuredCheck) {
    if (configuredCheck.getSeverity().equals(SeverityKind.IGNORE) && !configuredCheck.getParameterConfigurations().isEmpty()) {
      warning(Messages.CheckCfgJavaValidator_DISABLED_CHECK_NOT_CONFIGURED, CheckcfgPackage.Literals.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS, IssueCodes.DISABLED_CHECK_NOT_CONFIGURED);
    }
  }

  /**
   * Checks if a {@link com.avaloq.tools.ddk.check.check.Check Check} is configured or if it is just referenced.
   * 
   * @param configuredCheck
   *          the configured check
   * @return {@code true}, if it is configured
   */
  private boolean isConfigured(final ConfiguredCheck configuredCheck) {
    return configuredCheck.getSeverity() != SeverityKind.DEFAULT || !configuredCheck.getParameterConfigurations().isEmpty();
  }

  /**
   * Checks that final catalogs are not configured.
   * 
   * @see {@link com.avaloq.tools.ddk.check.check.CheckCatalog#isFinal()}
   * @param catalog
   *          the configured catalog
   */
  @Check
  public void checkFinalCatalogNotConfigurable(final ConfiguredCatalog catalog) {
    if (catalog.getCatalog() != null && !catalog.getCatalog().eIsProxy() && catalog.getCatalog().isFinal()) {
      for (final ConfiguredCheck check : catalog.getCheckConfigurations()) {
        if (isConfigured(check)) {
          error(Messages.CheckCfgJavaValidator_FINAL_CATALOG_NOT_CONFIGURABLE, check, null, IssueCodes.FINAL_CATALOG_NOT_CONFIGURABLE);
        }
      }
    }
  }

  /**
   * Checks that a {@link com.avaloq.tools.ddk.check.check.Check Check} which has been marked as {@link com.avaloq.tools.ddk.check.check.Check#isFinal()
   * final} is not configured.
   * 
   * @param configuredCheck
   *          the configured check
   */
  @Check
  public void checkFinalCheckNotConfigurable(final ConfiguredCheck configuredCheck) {
    if (isConfigured(configuredCheck) && configuredCheck.getCheck() != null && !configuredCheck.getCheck().eIsProxy() && configuredCheck.getCheck().isFinal()) {
      error(Messages.CheckCfgJavaValidator_FINAL_CHECK_NOT_CONFIGURABLE, CheckcfgPackage.Literals.CONFIGURED_CHECK__CHECK, IssueCodes.FINAL_CHECK_NOT_CONFIGURABLE);
    }
  }

  /**
   * Checks that a configured check's severity is allowed. If referenced check has a {@link SeverityRange severity range} defined, it must be checked that
   * configured value is within defined range.
   * 
   * @param configuredCheck
   *          the configured check
   */
  @Check
  public void checkConfiguredSeverityAllowed(final ConfiguredCheck configuredCheck) {
    // @Format-Off
    if (isConfigured(configuredCheck) && configuredCheck.getCheck() != null
        && !configuredCheck.getCheck().eIsProxy() && configuredCheck.getCheck().getSeverityRange() != null) {
      final SeverityRange range = configuredCheck.getCheck().getSeverityRange();
      final com.avaloq.tools.ddk.check.check.SeverityKind configuredSeverity =
        com.avaloq.tools.ddk.check.check.SeverityKind.getByName(configuredCheck.getSeverity().getName());

      final com.avaloq.tools.ddk.check.check.SeverityKind minSeverity =
          com.avaloq.tools.ddk.check.check.SeverityKind.get(
              Math.min(range.getMinSeverity().getValue(), range.getMaxSeverity().getValue()));
      final com.avaloq.tools.ddk.check.check.SeverityKind maxSeverity =
          com.avaloq.tools.ddk.check.check.SeverityKind.get(
              Math.max(range.getMinSeverity().getValue(), range.getMaxSeverity().getValue()));

      List<String> issueCodes = Lists.newArrayList();
      if (configuredSeverity.compareTo(minSeverity) < 0 || configuredSeverity.compareTo(maxSeverity) > 0) {
        com.avaloq.tools.ddk.check.check.SeverityKind temp = minSeverity;
        while (temp != null && temp.compareTo(maxSeverity) <= 0) {
          issueCodes.add(temp.getName());
          temp = com.avaloq.tools.ddk.check.check.SeverityKind.get(temp.getValue()+1);
        }

        String[] codes = issueCodes.toArray(new String[issueCodes.size()]);
        error(Messages.CheckCfgJavaValidator_SEVERITY_NOT_ALLOWED, CheckcfgPackage.Literals.CONFIGURED_CHECK__SEVERITY,
            IssueCodes.SEVERITY_NOT_ALLOWED, issueCodes.isEmpty() ? null : codes); // NOPMD
      }
      // @Format-On
    }
  }

  /**
   * Checks whether a configured check's configuration equals the default. Emits an info if this is the case.
   * 
   * @param configuredCheck
   *          the configured check
   */
  @Check
  public void checkConfigurationEqualsDefault(final ConfiguredCheck configuredCheck) {
    final com.avaloq.tools.ddk.check.check.Check check = configuredCheck.getCheck();
    if (!isConfigured(configuredCheck) || check == null || check.eIsProxy()) {
      return; // only interesting if check configured and resolvable
    }

    Iterable<FormalParameter> formalParameters = check.getFormalParameters();
    for (final ConfiguredParameter configParam : configuredCheck.getParameterConfigurations()) {
      try {
        FormalParameter param = Iterables.find(formalParameters, new Predicate<FormalParameter>() {
          public boolean apply(final FormalParameter input) {
            return input == configParam.getParameter();
          }
        });
        if (parameterValuesEqual(configParam.getNewValue(), param.getRight())) {
          info(NLS.bind(Messages.CheckCfgJavaValidator_CONFIGURED_PARAM_EQUALS_DEFAULT, param.getName()), configParam, CheckcfgPackage.Literals.CONFIGURED_PARAMETER__NEW_VALUE, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.CONFIGURED_PARAM_EQUALS_DEFAULT);
        }
      } catch (NoSuchElementException e) {
        LOGGER.debug("Could not find referenced formal parameter");
      }
    }
  }

  /**
   * Checks if two parameter values equal. It is expected that passed expressions are parsed by the {@code XFormalParameterDefaultValueLiteral} grammar rule and
   * that their types are of the following:
   * <ul>
   * <li>{@link org.eclipse.xtext.xbase.XBooleanLiteral boolean}
   * <li>{@link org.eclipse.xtext.xbase.XIntLiteral int}
   * <li>{@link org.eclipse.xtext.xbase.XStringLiteral string}
   * </ul>
   * 
   * @param e1
   *          the expression associated with the first parameter value
   * @param e2
   *          the expression associated with the second parameter value
   * @return true, if successful
   */
  private boolean parameterValuesEqual(final XExpression e1, final XExpression e2) {
    try {
      return interpreter.evaluate(e1).getResult().equals(interpreter.evaluate(e2).getResult());
    } catch (IllegalStateException e) {
      return false; // happens when illegal values are entered, for instance when a template is inserted and the user is not finished entering correct values
    }
  }

  /**
   * Checks that within a Check Configuration all Catalog Configurations are unique, meaning that a referenced
   * Check Catalog can only be configured in one place.
   * 
   * @param configuration
   *          the configuration
   */
  @Check
  public void checkConfiguredCatalogUnique(final CheckConfiguration configuration) {
    if (configuration.getLegacyCatalogConfigurations().size() < 2) {
      return;
    }
    Predicate<ConfiguredCatalog> predicate = new Predicate<ConfiguredCatalog>() {
      public boolean apply(final ConfiguredCatalog configuredCatalog) {
        final CheckCatalog catalog = configuredCatalog.getCatalog();
        return catalog != null && !catalog.eIsProxy();
      }
    };
    Function<ConfiguredCatalog, String> function = new Function<ConfiguredCatalog, String>() {
      public String apply(final ConfiguredCatalog from) {
        return from.getCatalog().getName();
      }
    };
    for (final ConfiguredCatalog c : getDuplicates(predicate, function, configuration.getLegacyCatalogConfigurations())) {
      error(Messages.CheckCfgJavaValidator_DUPLICATE_CATALOG_CONFIGURATION, c, CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.DUPLICATE_CATALOG_CONFIGURATION);
    }

  }

  /**
   * Checks that Check Configurations are unique. A Configured Catalog may only contain one configuration for each referenced Check.
   * 
   * @param configuration
   *          the configuration
   */
  @Check
  public void checkConfiguredCheckUnique(final ConfiguredCatalog configuration) {
    if (configuration.getCheckConfigurations().size() < 2) {
      return;
    }
    Predicate<ConfiguredCheck> predicate = new Predicate<ConfiguredCheck>() {
      public boolean apply(final ConfiguredCheck configuredCheck) {
        return configuredCheck.getCheck() != null && !configuredCheck.getCheck().eIsProxy();
      }
    };
    Function<ConfiguredCheck, String> function = new Function<ConfiguredCheck, String>() {
      public String apply(final ConfiguredCheck from) {
        return from.getCheck().getName();
      }
    };
    for (final ConfiguredCheck c : getDuplicates(predicate, function, configuration.getCheckConfigurations())) {
      error(Messages.CheckCfgJavaValidator_DUPLICATE_CHECK_CONFIGURATION, c, CheckcfgPackage.Literals.CONFIGURED_CHECK__CHECK, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.DUPLICATE_CHECK_CONFIGURATION);
    }

  }

  /**
   * Checks that language referenced in validator configuration exists.
   * 
   * @param validator
   *          the configured language validator
   */
  @Check
  public void checkConfiguredLanguageExists(final ConfiguredLanguageValidator validator) {
    if (!checkCfgUtil.getAllLanguages().contains(validator.getLanguage())) {
      error("Unknown language", validator, CheckcfgPackage.Literals.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.UNKNOWN_LANGUAGE);
    }
  }

  /**
   * Checks that a Configured Check has unique Configured Parameters.
   * 
   * @param configuredCheck
   *          the configured check
   */
  @Check
  public void checkConfiguredParameterUnique(final ConfiguredCheck configuredCheck) {
    if (configuredCheck.getParameterConfigurations().size() < 2) {
      return;
    }
    Predicate<ConfiguredParameter> predicate = new Predicate<ConfiguredParameter>() {
      public boolean apply(final ConfiguredParameter configuredParameter) {
        return configuredParameter.getParameter() != null && !configuredParameter.getParameter().eIsProxy();
      }
    };
    Function<ConfiguredParameter, String> function = new Function<ConfiguredParameter, String>() {
      public String apply(final ConfiguredParameter from) {
        return from.getParameter().getName();
      }
    };
    for (final ConfiguredParameter p : getDuplicates(predicate, function, configuredCheck.getParameterConfigurations())) {
      error(Messages.CheckCfgJavaValidator_DUPLICATE_PARAMETER_CONFIGURATION, p, CheckcfgPackage.Literals.CONFIGURED_PARAMETER__PARAMETER, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.DUPLICATE_PARAMETER_CONFIGURATION);
    }

  }

  /**
   * Gets duplicates of a given type based on a guard (predicate). A given function is used for converting an instance of type T
   * to a string which is used for checking for duplicates.
   * 
   * @param <T>
   *          the generic type
   * @param predicate
   *          the predicate acting as a guard
   * @param function
   *          returns a string for an instance of type T
   * @param elements
   *          the elements to be checked
   * @return the duplicates
   */
  private <T extends EObject> Iterable<T> getDuplicates(final Predicate<T> predicate, final Function<T, String> function, final Iterable<T> elements) {
    List<T> result = Lists.newArrayList();
    Multimap<String, T> multiMap = Multimaps.newMultimap(Maps.<String, Collection<T>> newHashMap(), new Supplier<Collection<T>>() {
      public Collection<T> get() {
        return Lists.<T> newArrayList();
      }
    });
    for (final T candidate : elements) {
      if (predicate.apply(candidate)) {
        multiMap.put(function.apply(candidate), candidate);
      }
    }
    for (String elem : multiMap.keySet()) {
      final Collection<T> duplicates = multiMap.get(elem);
      if (duplicates.size() > 1) {
        result.addAll(duplicates);
      }
    }

    return result;
  }
}

