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

  // CPD-OFF — incidental token overlap (DI field + trace/try idiom), not an extractable unit (#1339)
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

    /** Set after the first entry in {@link #disabled}, so that lookups can be skipped until a check has failed. */
    @SuppressWarnings("PMD.AvoidUsingVolatile")
    private volatile boolean anyDisabled;

    /**
     * Marks the given method as disabled.
     *
     * @param string
     *          the method identifier
     */
    public void setDisabled(final String string) {
      disabled.putIfAbsent(string, Boolean.TRUE);
      anyDisabled = true;
    }

    /**
     * Returns whether the given method is disabled.
     *
     * @param string
     *          the method identifier
     * @return {@code true} if disabled
     */
    public boolean isDisabled(final String string) {
      return anyDisabled && disabled.containsKey(string);
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
    // CPD-ON
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

  /**
   * Validates the given object.
   *
   * @param checkMode
   *          the check mode
   * @param object
   *          the object to validate
   * @param diagnosticCollector
   *          the diagnostic collector
   */
  protected abstract void validate(CheckMode checkMode, EObject object, DiagnosticCollector diagnosticCollector);

  /**
   * A context method of a catalog, executed by
   * {@link DispatchingCheckImpl#validate(String, String, EObject, Object, ContextMethod, DiagnosticCollector)}.
   * <p>
   * The catalog is passed as an argument, so that a generated catalog can pass a lambda that captures nothing and is therefore not allocated
   * for every check and object.
   *
   * @param <T>
   *          the type of the context variable
   */
  @FunctionalInterface
  public interface ContextMethod<T> {
    /**
     * Runs the context method.
     *
     * @param catalog
     *          the catalog implementation executing the context method
     * @param target
     *          the object to check, as the type of the context variable
     * @param diagnosticCollector
     *          a validation state tracker and diagnostic collector
     */
    void run(DispatchingCheckImpl catalog, T target, DiagnosticCollector diagnosticCollector);
  }

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
   * @deprecated kept for catalogs generated by older versions, which allocate a {@link Runnable} for every check and object; use
   *             {@link #validate(String, String, EObject, Object, ContextMethod, DiagnosticCollector)} instead
   */
  @Deprecated
  protected void validate(final String contextName, final String qContextName, final EObject object, final Runnable checkAction, final DiagnosticCollector diagnosticCollector) {
    validate(contextName, qContextName, object, object, (catalog, target, collector) -> checkAction.run(), diagnosticCollector);
  }

  /**
   * Does one context method execution on one object.
   * <p>
   * A context name is anything suitably unique to identify a context method in disabledMethodTracker
   * or in a log error message or in an execution trace.
   *
   * @param <T>
   *          the type of the context variable
   * @param contextName
   *          a unique-within catalog context method name
   * @param qContextName
   *          a catalog name qualified context method name
   * @param object
   *          the object to check
   * @param target
   *          the same object as the type of the context variable
   * @param contextMethod
   *          the context method to run on the target
   * @param diagnosticCollector
   *          a validation state tracker and diagnostic collector
   */
  @SuppressWarnings("checkstyle:IllegalCatch")
  protected <T> void validate(final String contextName, final String qContextName, final EObject object, final T target, final ContextMethod<T> contextMethod, final DiagnosticCollector diagnosticCollector) {
    if (!disabledMethodTracker.isDisabled(contextName)) {
      Collector eventCollector = diagnosticCollector.getEventCollector();
      try {
        // CPD-OFF — incidental token overlap (DI field + trace/try idiom), not an extractable unit (#1339)
        traceStart(qContextName, object, eventCollector);
        contextMethod.run(this, target, diagnosticCollector);
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
    // CPD-ON
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
