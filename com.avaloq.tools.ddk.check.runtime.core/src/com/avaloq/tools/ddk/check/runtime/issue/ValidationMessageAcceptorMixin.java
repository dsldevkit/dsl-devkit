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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;


/**
 * A validation message acceptor implemented in terms of a small set of operations
 * to access current validation state.
 */
public interface ValidationMessageAcceptorMixin extends ValidationMessageAcceptor {

  /**
   * Gets the current diagnostics accumulator.
   *
   * @return the accumulator, never {@code null}
   */
  DiagnosticChain getChain();

  /**
   * Gets the current check type.
   *
   * @return the type
   */
  CheckType getCurrentCheckType();

  /**
   * Flags the current context object as having had errors.
   */
  void setHasErrors();

  // ////////////////////////////////////////////////
  // Copied and adapted from AbstractDeclarativeValidator below
  // ////////////////////////////////////////////////

  // ////////////////////////////////////////////////////////
  // Implementation of the Validation message acceptor below
  // ////////////////////////////////////////////////////////

  @Override
  default void acceptError(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    setHasErrors();
    getChain().add(createDiagnostic(Severity.ERROR, message, object, feature, index, code, issueData));
  }

  @Override
  default void acceptWarning(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    getChain().add(createDiagnostic(Severity.WARNING, message, object, feature, index, code, issueData));
  }

  @Override
  default void acceptInfo(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    getChain().add(createDiagnostic(Severity.INFO, message, object, feature, index, code, issueData));
  }

  @Override
  default void acceptError(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    setHasErrors();
    getChain().add(createDiagnostic(Severity.ERROR, message, object, offset, length, code, issueData));
  }

  @Override
  default void acceptWarning(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    getChain().add(createDiagnostic(Severity.WARNING, message, object, offset, length, code, issueData));
  }

  @Override
  default void acceptInfo(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    getChain().add(createDiagnostic(Severity.INFO, message, object, offset, length, code, issueData));
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
  default Diagnostic createDiagnostic(final Severity severity, final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    int diagnosticSeverity = toDiagnosticSeverity(severity);
    return new FeatureBasedDiagnostic(diagnosticSeverity, message, object, feature, index, getCurrentCheckType(), code, issueData);
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
  default Diagnostic createDiagnostic(final Severity severity, final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    int diagnosticSeverity = toDiagnosticSeverity(severity);
    return new CheckRangeBasedDiagnostic(diagnosticSeverity, message, object, offset, length, getCurrentCheckType(), code, issueData);
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
   * @throws IllegalArgumentException
   *           if the severity is unknown
   */
  static int toDiagnosticSeverity(final Severity severity) {
    return switch (severity) {
    case ERROR -> Diagnostic.ERROR;
    case WARNING -> Diagnostic.WARNING;
    case INFO -> Diagnostic.INFO;
    default -> throw new IllegalArgumentException("Unknown severity " + severity); //$NON-NLS-1$
    };
  }

}
