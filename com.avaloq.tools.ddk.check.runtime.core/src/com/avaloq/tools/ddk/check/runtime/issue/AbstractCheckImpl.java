/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.runtime.issue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent.Collector;


/**
 * An abstract base class for check catalog implementations.
 */
public abstract class AbstractCheckImpl implements ICheckValidatorImpl, ValidationMessageAcceptor {
  private static final Logger LOGGER = LogManager.getLogger(AbstractCheckImpl.class);

  private final ThreadLocal<State> threadLocalState;

  private final ValidationMessageAcceptor messageAcceptor;

  public AbstractCheckImpl() {
    this.threadLocalState = new ThreadLocal<State>();
    this.messageAcceptor = this;
  }

  protected ThreadLocal<State> getThreadLocalState() {
    return threadLocalState;
  }

  public ValidationMessageAcceptor getMessageAcceptor() {
    return messageAcceptor;
  }

  /**
   * Logs a check method failure.
   *
   * @param rule
   *          the rule that failed
   * @param object
   *          the object for which it failed
   * @param e
   *          exception that was raised
   */
  protected void logCheckMethodFailure(final String rule, final EObject object, final Exception e) {
    final Throwable cause = e instanceof InvocationTargetException ? ((InvocationTargetException) e).getTargetException() : e;
    final Resource res = object.eResource();
    LOGGER.error("Permanently disabling check method " + rule + " for context " + object.getClass().getName() + " because of failure" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + (res != null ? " in " + res.getURI() : ""), cause); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * To be called by subclasses to indicate that a given validation rule is about to be executed and that its execution time should be traced.
   *
   * @param rule
   *          rule to be executed
   * @param object
   *          object against which validation will be executed
   * @param collector
   *          trace data collector or {@code null} if tracing is disabled
   * @see #traceEnd(String)
   */
  protected void traceStart(final String rule, final EObject object, final Collector collector) {
    if (collector != null) {
      collector.ruleStarted(rule, object);
    }
  }

  /**
   * To be called by subclasses after having executed a rule previously registered with {@link #traceStart(String)}.
   *
   * @param rule
   *          executed rule
   * @param object
   *          object against which validation was executed
   * @param collector
   *          trace data collector or {@code null} if tracing is disabled
   * @see #traceStart(String)
   */
  protected void traceEnd(final String rule, final EObject object, final Collector collector) {
    if (collector != null) {
      collector.ruleEnded(rule, object);
    }
  }

  // ////////////////////////////////////////////////
  // Copied and adapted from AbstractDeclarativeValidator below
  // ////////////////////////////////////////////////

  /**
   * The holding the current state for a validation method being executed.
   */
  protected static class State {
    // CHECKSTYLE:OFF
    public DiagnosticChain chain = null;
    public EObject currentObject = null;
    public Method currentMethod = null;
    public CheckMode checkMode = null;
    public CheckType currentCheckType = null;
    public boolean hasErrors = false;
    public Map<Object, Object> context;

    // CHECKSTYLE:ON
    public State() {
    }
  }

  protected EObject getCurrentObject() {
    return threadLocalState.get().currentObject;
  }

  protected Method getCurrentMethod() {
    return threadLocalState.get().currentMethod;
  }

  protected DiagnosticChain getChain() {
    return threadLocalState.get().chain;
  }

  protected CheckMode getCheckMode() {
    return threadLocalState.get().checkMode;
  }

  protected Map<Object, Object> getContext() {
    return threadLocalState.get().context;
  }

  // ////////////////////////////////////////////////////////
  // Implementation of the Validation message acceptor below
  // ////////////////////////////////////////////////////////

  @Override
  public void acceptError(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    State localState = threadLocalState.get();
    localState.hasErrors = true;
    localState.chain.add(createDiagnostic(Severity.ERROR, message, object, feature, index, code, issueData));
  }

  @Override
  public void acceptWarning(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    threadLocalState.get().chain.add(createDiagnostic(Severity.WARNING, message, object, feature, index, code, issueData));
  }

  @Override
  public void acceptInfo(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    threadLocalState.get().chain.add(createDiagnostic(Severity.INFO, message, object, feature, index, code, issueData));
  }

  @Override
  public void acceptError(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    State localState = threadLocalState.get();
    localState.hasErrors = true;
    localState.chain.add(createDiagnostic(Severity.ERROR, message, object, offset, length, code, issueData));
  }

  @Override
  public void acceptWarning(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    threadLocalState.get().chain.add(createDiagnostic(Severity.WARNING, message, object, offset, length, code, issueData));
  }

  @Override
  public void acceptInfo(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    threadLocalState.get().chain.add(createDiagnostic(Severity.INFO, message, object, offset, length, code, issueData));
  }

  /**
   * Creates a diagnostic for given parameters.
   *
   * @param severity
   *          the issue severity
   * @param message
   *          the issue message
   * @param object
   *          the context object
   * @param feature
   *          the structural feature on which to create a marker
   * @param index
   *          the index at which to create a marker
   * @param code
   *          the issue code
   * @param issueData
   *          the issue data
   * @return the diagnostic
   */
  protected Diagnostic createDiagnostic(final Severity severity, final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    int diagnosticSeverity = toDiagnosticSeverity(severity);
    return new FeatureBasedDiagnostic(diagnosticSeverity, message, object, feature, index, threadLocalState.get().currentCheckType, code, issueData);
  }

  /**
   * Creates a diagnostic for given parameters.
   *
   * @param severity
   *          the issue severity
   * @param message
   *          the issue message
   * @param object
   *          the context object
   * @param offset
   *          the offset of the marker
   * @param length
   *          the length of tokens to be marked by the issue
   * @param code
   *          the issue code
   * @param issueData
   *          the issue data
   * @return the diagnostic
   */
  protected Diagnostic createDiagnostic(final Severity severity, final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    int diagnosticSeverity = toDiagnosticSeverity(severity);
    return new CheckRangeBasedDiagnostic(diagnosticSeverity, message, object, offset, length, threadLocalState.get().currentCheckType, code, issueData);
  }

  /**
   * Gets a numeric value mapped to a given {@link Severity}. Severities are mapped to
   * <ul>
   * <li>{@link Diagnostic#ERROR}
   * <li>{@link Diagnostic#WARNING}
   * <li>{@link Diagnostic#INFO}
   * </ul>
   *
   * @param severity
   *          the issue severity
   * @return the numeric value representing a severity
   */
  protected static int toDiagnosticSeverity(final Severity severity) {
    return switch (severity) {
    case ERROR -> Diagnostic.ERROR;
    case WARNING -> Diagnostic.WARNING;
    case INFO -> Diagnostic.INFO;
    default -> throw new IllegalArgumentException("Unknown severity " + severity); //$NON-NLS-1$
    };
  }

}
