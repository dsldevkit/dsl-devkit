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
import org.eclipse.xtext.validation.CheckMode;

import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent;
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
    state.currentObject = object;
    state.checkMode = checkMode;
    state.context = context;

    /*
     * Some inherited methods rely on State to be set.
     */
    State instanceState = getThreadLocalState().get();
    if (instanceState != null && instanceState != state) {
      throw new IllegalStateException("State is already assigned."); //$NON-NLS-1$
    }
    boolean wasNull = instanceState == null;
    if (wasNull) {
      getThreadLocalState().set(state);
    }
    try {
      validate(checkMode, object, eventCollector);
    } finally {
      if (wasNull) {
        getThreadLocalState().set(null);
      }
    }

    return !state.hasErrors;
  }

  protected abstract void validate(CheckMode checkMode, EObject object, ResourceValidationRuleSummaryEvent.Collector eventCollector);

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
   * @param eventCollector
   *          an event collector for collecting validation events, may be {@code null}
   */
  @SuppressWarnings("checkstyle:IllegalCatch")
  protected void validate(final String contextName, final String qContextName, final EObject object, final Runnable checkAction, final ResourceValidationRuleSummaryEvent.Collector eventCollector) {
    if (!disabledMethodTracker.isDisabled(contextName)) {
      try {
        traceStart(qContextName, object, eventCollector);
        checkAction.run();
        // Yes, we really want to catch anything here. The method invoked is user-written code that may fail arbitrarily.
        // If that happens, we want to exclude this check from all future executions!
      } catch (Exception e) {
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
   * Returns the unqualified catalog name.
   *
   * @return the name
   */
  protected String getCatalogName() {
    String qName = getQualifiedCatalogName();
    int index = qName.lastIndexOf('.');
    return index == -1 ? qName : qName.substring(index + 1);
  }

}
