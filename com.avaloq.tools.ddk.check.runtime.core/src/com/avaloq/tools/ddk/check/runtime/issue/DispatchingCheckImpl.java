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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractValidationMessageAcceptor;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.avaloq.tools.ddk.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent.Collector;
import com.google.inject.Inject;


/**
 * A base class for generated check catalog implementations that include
 * a dispatching {@code validate} method.
 */
@SuppressWarnings({"checkstyle:AbstractClassName"})
public abstract class DispatchingCheckImpl extends AbstractCheckImpl {

  @Inject
  private ITraceSet traceSet;

  private Boolean isTraceEnabled;

  private final DisabledMethodTracker disabledMethodTracker = new DisabledMethodTracker();

  /**
   * A tracker for disabled methods.
   * Concurrent access is allowed.
   */
  @SuppressWarnings("PMD.PublicMemberInNonPublicType") // Public methods needed for subclass access in other packages
  protected static class DisabledMethodTracker {
    private final Map<String, Boolean> disabled = new ConcurrentHashMap<>();

    public void setDisabled(final String string) {
      disabled.putIfAbsent(string, Boolean.TRUE);
    }

    public boolean isDisabled(final String string) {
      return disabled.getOrDefault(string, Boolean.FALSE);
    }
  }

  @Override
  public boolean validate(final EClass eClass, final EObject object, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    if (isTraceEnabled == null) {
      isTraceEnabled = traceSet.isEnabled(ResourceValidationRuleSummaryEvent.class);
    }
    final ResourceValidationRuleSummaryEvent.Collector eventCollector = isTraceEnabled
        ? ResourceValidationRuleSummaryEvent.Collector.extractFromLoadOptions(object.eResource().getResourceSet())
        : null;

    CheckMode checkMode = CheckMode.getCheckMode(context);

    State state = new State();
    state.chain = diagnostics;
    state.eventCollector = eventCollector;

    validate(checkMode, object, state);

    return !state.hasErrors;
  }

  /**
   * A validation state tracker and diagnostic collector.
   */
  public interface DiagnosticCollector extends ValidationMessageAcceptor {
    /**
     * Records the check type of the subsequently executed checks.
     *
     * @param checkType
     *          the check type
     */
    void setCurrentCheckType(CheckType checkType);

    /**
     * Gets the active resource validation rule summary event collector, if any.
     *
     * @return the collector, or {@code null}
     */
    ResourceValidationRuleSummaryEvent.Collector getEventCollector();
  }

  protected abstract void validate(CheckMode checkMode, EObject object, DiagnosticCollector diagnosticCollector);

  /**
   * Does one context method execution on one object.
   * <p>
   * A context name is anything suitably unique to identify a context method in disabledMethodTracker
   * or in a log error message or in an execution trace.
   *
   * @param contextName
   *          a unique-within catalog context method name
   * @param qContextName
   *          a catalog name qualified context method name
   * @param object
   *          the object to check
   * @param checkAction
   *          the check action to perform
   * @param diagnosticCollector
   *          a validation state tracker and diagnostic collector
   */
  @SuppressWarnings("checkstyle:IllegalCatch")
  protected void validate(final String contextName, final String qContextName, final EObject object, final Runnable checkAction, final DiagnosticCollector diagnosticCollector) {
    if (!disabledMethodTracker.isDisabled(contextName)) {
      Collector eventCollector = diagnosticCollector.getEventCollector();
      try {
        traceStart(qContextName, object, eventCollector);
        checkAction.run();
      } catch (Exception e) {
        // The method invoked is user-written code that may fail arbitrarily.
        // If that happens, we want to exclude this check from all future executions!
        handleContextMethodFailure(contextName, object, e);
      } finally {
        traceEnd(qContextName, object, eventCollector);
      }
    }
  }

  /**
   * Disables a method and logs its failure.
   * The specified context name should be something suitably unique to identify the code that failed to execute,
   * and it should also be something to readable to allow someone viewing the log to identify the faulty method.
   *
   * @param contextName
   *          a unique-within catalog context method name
   * @param object
   *          the context object
   * @param e
   *          the exception that was raised
   */
  protected void handleContextMethodFailure(final String contextName, final EObject object, final Exception e) {
    disabledMethodTracker.setDisabled(contextName);
    logCheckMethodFailure(getQualifiedCatalogName() + contextName, object, e);
  }

  /**
   * The class holding the current state for a validation method being executed.
   */
  @SuppressWarnings("PMD.PublicMemberInNonPublicType") // Public fields needed for subclass access in other packages
  protected static class State implements ValidationMessageAcceptorMixin, DiagnosticCollector {
    // CHECKSTYLE:OFF
    public DiagnosticChain chain;
    public CheckType currentCheckType;
    public boolean hasErrors;
    public ResourceValidationRuleSummaryEvent.Collector eventCollector;
    // CHECKSTYLE:ON

    @Override
    public DiagnosticChain getChain() {
      return chain;
    }

    @Override
    public CheckType getCurrentCheckType() {
      return currentCheckType;
    }

    @Override
    public void setHasErrors() {
      this.hasErrors = true;
    }

    @Override
    public void setCurrentCheckType(final CheckType checkType) {
      currentCheckType = checkType;
    }

    @Override
    public Collector getEventCollector() {
      return eventCollector;
    }
  }

  /**
   * An implementation of {@link DiagnosticCollector} that does nothing.
   * <p>
   * Provided as a base class for partial implementations, or for use as is in tests that
   * test some aspects of validation execution, but do not check or need diagnostic results.
   */
  public static class DiagnosticNonCollector extends AbstractValidationMessageAcceptor implements DiagnosticCollector {
    @Override
    public void setCurrentCheckType(final CheckType checkType) {
      // do nothing
    }

    @Override
    public Collector getEventCollector() {
      return null;
    }
  }

}
