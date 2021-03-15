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
package com.avaloq.tools.ddk.xtext.validation;

import java.text.MessageFormat;
import java.util.Map;
import java.util.stream.Collector;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent.Collector;
import com.google.common.base.Strings;
import com.google.inject.Inject;


/**
 * The Class AbstractDeclarativeValidValidator defines utility methods
 * to perform declarative checks. Basically, these are the same checks
 * as the AbsxtractDeclarativeValidator, except that they return an
 * error message instead of "raising" an error.
 */
public abstract class AbstractDeclarativeValidValidator extends AbstractDeclarativeValidator {

  /** The store into which to look for the preference items. */
  private ValidPreferenceStore validPreferenceStore;

  /** A constant that easily reads "no error" (much better than "null"). */
  public static final SimpleValidMessage NO_ERROR = null;

  private static final ValidGuardException VALID_GUARD_EXCEPTION = new ValidGuardException();

  @Inject
  private ITraceSet traceSet;

  /**
   * Message Parameters.
   * A convenience class to hold the parameters.
   */
  public static class MessageParameters {
    private final Object[] parameters;

    /**
     * The constructor.
     *
     * @param parameter
     *          the parameters
     */
    public MessageParameters(final Object... parameter) {
      this.parameters = parameter;
    }

    /**
     * Gets the parameters of the message.
     *
     * @return the message parameters
     */
    public Object[] getParameters() {
      return parameters; // NOPMD
    }
  }

  /**
   * Returns true if tracing is enabled.
   *
   * @return true if tracing is enabled
   */
  protected boolean isTraceEnabled() {
    return traceSet.isEnabled(ResourceValidationRuleSummaryEvent.class);
  }

  /**
   * To be called by subclasses to indicate that a given validation rule is about to be executed and that its execution time should be traced.
   *
   * @param rule
   *          rule to be executed
   * @return A collector if if tracing is enabled or {@code null} otherwise
   */
  protected Collector traceStart(final String rule) {
    if (traceSet.isEnabled(ResourceValidationRuleSummaryEvent.class)) {
      EObject object = getCurrentObject();
      ResourceValidationRuleSummaryEvent.Collector collector = getTraceCollector(object);
      if (collector != null) {
        collector.ruleStarted(rule, object);
        return collector;
      }
    }
    return null;
  }

  /**
   * To be called by subclasses after having executed a rule previously registered with {@link #traceStart(String)}.
   *
   * @param rule
   *          executed rule
   * @see #traceStart(String)
   */
  @Deprecated
  protected void traceEnd(final String rule) {
    if (traceSet.isEnabled(ResourceValidationRuleSummaryEvent.class)) {
      EObject object = getCurrentObject();
      ResourceValidationRuleSummaryEvent.Collector collector = getTraceCollector(object);
      if (collector != null) {
        collector.ruleEnded(rule, object);
      }
    }
  }

  /**
   * To be called by subclasses after having executed a rule previously registered with {@link #traceStart(String)}.
   *
   * @param collector
   *          the collector returned by traceStart, must not be {@code null}
   * @see #traceStart(String)
   */
  protected void traceEnd(final Collector collector) {
    ((ResourceValidationRuleSummaryEvent.Collector)collector).ruleEnded(rule, null);
  }

  /**
   * Returns the {@link ResourceValidationRuleSummaryEvent.Collector} to use for collecting trace data.
   *
   * @param object
   *          validated object to trace
   * @return trace data collector or {@code null} if tracing is not enabled
   */
  private ResourceValidationRuleSummaryEvent.Collector getTraceCollector(final EObject object) {
    Map<Object, Object> context = getContext();
    ResourceValidationRuleSummaryEvent.Collector collector = (Collector) context.get(ResourceValidationRuleSummaryEvent.Collector.class);
    if (collector == null) {
      collector = ResourceValidationRuleSummaryEvent.Collector.extractFromLoadOptions(object.eResource().getResourceSet());
      context.put(ResourceValidationRuleSummaryEvent.Collector.class, collector);
    }
    return collector;
  }

  /**
   * @return the preference store (based on Scopes, but without UI) for that validator.
   */
  protected ValidPreferenceStore getValidPreferenceStore() {
    // we use this pattern because we cannot create the ValidPreferenceStore inside the constructor,
    // because the languageName in getPreferenceStoreName may not be initialized yet if it is injected.
    if (validPreferenceStore == null) {
      validPreferenceStore = new ValidPreferenceStore(InstanceScope.INSTANCE, getPreferenceStoreName());
    }
    return validPreferenceStore;
  }

  /**
   * Assert that a string shall not be empty.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param string
   *          the string that shall not be empty
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertEmpty(final String message, final MessageParameters messageParameters, final String string) {
    if (!Strings.isNullOrEmpty(string.trim())) {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert that a string shall not be empty.
   *
   * @param message
   *          the error message
   * @param string
   *          the string that shall not be empty
   * @param issueData
   *          the issue data
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertEmpty(final String message, final String string, final String... issueData) {
    if (!Strings.isNullOrEmpty(string.trim())) {
      return new SimpleValidMessage(message);
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert that two objects must be equal.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param expected
   *          the expected object
   * @param actual
   *          the actual object
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertEquals(final String message, final MessageParameters messageParameters, final Object expected, final Object actual) {
    if (expected.equals(actual)) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    }
  }

  /**
   * Assert that two objects must be equal.
   *
   * @param message
   *          the error message
   * @param expected
   *          the expected
   * @param actual
   *          the actual
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertEquals(final String message, final Object expected, final Object actual) {
    if (expected.equals(actual)) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(message);
    }
  }

  /**
   * Assert false.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param executedPredicate
   *          the executed predicate
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertFalse(final String message, final MessageParameters messageParameters, final boolean executedPredicate) {
    if (executedPredicate) {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert false.
   *
   * @param message
   *          the error message
   * @param executedPredicate
   *          the executed predicate
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertFalse(final String message, final boolean executedPredicate) {
    if (executedPredicate) {
      return new SimpleValidMessage(message);
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not empty.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param string
   *          the string
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotEmpty(final String message, final MessageParameters messageParameters, final String string) {
    if (Strings.isNullOrEmpty(string.trim())) {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not empty.
   *
   * @param message
   *          the error message
   * @param string
   *          the string
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotEmpty(final String message, final String string) {
    if (Strings.isNullOrEmpty(string.trim())) {
      return new SimpleValidMessage(message);
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not equals.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param expected
   *          the expected
   * @param actual
   *          the actual
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotEquals(final String message, final MessageParameters messageParameters, final Object expected, final Object actual) {
    if (expected.equals(actual)) {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not equals.
   *
   * @param message
   *          the error message
   * @param expected
   *          the expected object
   * @param actual
   *          the actual object
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotEquals(final String message, final Object expected, final Object actual) {
    if (expected.equals(actual)) {
      return new SimpleValidMessage(message);
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not null.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param object
   *          the object that shall not be null
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotNull(final String message, final MessageParameters messageParameters, final Object object) {
    if (object == null) {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert not null.
   *
   * @param message
   *          the error message
   * @param object
   *          the object that shall not be null
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertNotNull(final String message, final Object object) {
    if (object == null) {
      return new SimpleValidMessage(message);
    } else {
      return NO_ERROR;
    }
  }

  /**
   * Assert null.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param object
   *          the object that shall be null
   * @return the error message if the assertion failed, null otherwise
   */
  protected SimpleValidMessage assertNull(final String message, final MessageParameters messageParameters, final Object object) {
    if (object == null) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    }
  }

  /**
   * Assert null.
   *
   * @param message
   *          the error message
   * @param object
   *          the object that shall be null
   * @return the error message if the assertion failed, null otherwise
   */
  protected SimpleValidMessage assertNull(final String message, final Object object) {
    if (object == null) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(message);
    }
  }

  /**
   * Assert true.
   *
   * @param message
   *          the error message
   * @param messageParameters
   *          the error message parameters
   * @param executedPredicate
   *          the predicate to evaluate
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertTrue(final String message, final MessageParameters messageParameters, final boolean executedPredicate) {
    if (executedPredicate) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(MessageFormat.format(message, messageParameters.getParameters()));
    }
  }

  /**
   * Assert true.
   *
   * @param message
   *          the error message
   * @param executedPredicate
   *          the predicate to evaluate
   * @return the error message if the assertion failed, NO_ERROR otherwise
   */
  protected SimpleValidMessage assertTrue(final String message, final boolean executedPredicate) {
    if (executedPredicate) {
      return NO_ERROR;
    } else {
      return new SimpleValidMessage(message);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @deprecated this pattern results in a performance impact - use condition statements, i.e. if-blocks, instead.
   */
  @Deprecated
  @Override
  protected void guard(final boolean guardExpression) {
    if (!guardExpression) {
      throw VALID_GUARD_EXCEPTION;
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void checkDone() {
    throw new ValidGuardException();
  }

  /** {@inheritDoc} */
  @Override
  public void acceptError(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    super.acceptError(StringEscapeUtils.unescapeXml(message), object, feature, index, code, issueData);
  }

  /** {@inheritDoc} */
  @Override
  public void acceptWarning(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    super.acceptWarning(StringEscapeUtils.unescapeXml(message), object, feature, index, code, issueData);
  }

  /**
   * The name of the preference store.
   *
   * @return the name of the preference store
   */
  protected abstract String getPreferenceStoreName();

  /**
   * Checks whether the rule corresponding to the given key is disabled.
   *
   * @param preferenceKey
   *          the preference key
   * @return true, if is disabled
   */
  protected boolean isDisabled(final String preferenceKey) {
    // remember: the preference is whether the key is disabled (true) or not (false)
    return getValidPreferenceStore().getBoolean(preferenceKey);
  }

}
