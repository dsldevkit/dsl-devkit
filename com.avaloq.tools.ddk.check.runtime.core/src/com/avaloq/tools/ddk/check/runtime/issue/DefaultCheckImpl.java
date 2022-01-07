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
package com.avaloq.tools.ddk.check.runtime.issue; //TODO rename package

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.FeatureBasedDiagnostic;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent.Collector;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * The class DefaultCheckImpl serves as the default parent implementation for generated
 * issue classes.
 */
// CHECKSTYLE:OFF
public abstract class DefaultCheckImpl implements ICheckValidatorImpl, ValidationMessageAcceptor {
  // CHECKSTYLE:ON

  private static final int VISITED_CLASSES_INIT_CAPACITY = 4;

  private static final Logger LOGGER = LogManager.getLogger(DefaultCheckImpl.class);

  private volatile Set<MethodWrapper> checkMethods; // NOPMD: may be modified by different threads; thread safety guaranteed by double-checked locking

  private final Map<Class<?>, List<MethodWrapper>> methodsForType = Maps.newHashMap();
  private final ThreadLocal<State> state;
  private final ValidationMessageAcceptor messageAcceptor;

  @Inject
  private ITraceSet traceSet;

  private Boolean isTraceEnabled;

  public DefaultCheckImpl() {
    this.state = new ThreadLocal<State>();
    this.messageAcceptor = this;
  }

  public ValidationMessageAcceptor getMessageAcceptor() {
    return messageAcceptor;
  }

  // ////////////////////////////////////////////////
  // Copied and adapted from AbstractDeclarativeValidator below
  // ////////////////////////////////////////////////

  /** {@inheritDoc} */
  @Override
  public boolean validate(final EClass eClass, final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    return internalValidate(eClass, eObject, diagnostics, context);
  }

  /**
   * Loads our internal list of check methods, if not yet loaded.
   */
  private void initCheckMethodCache() {
    if (checkMethods == null) {
      synchronized (this) {
        // CHECKSTYLE:OFF Double-checked locking
        if (checkMethods == null) {
          // CHECKSTYLE:ON
          Set<MethodWrapper> internalCheckMethods = Sets.newLinkedHashSet();
          internalCheckMethods.addAll(collectMethods());
          this.checkMethods = internalCheckMethods;
        }
      }
    }
  }

  /**
   * Executes all Check methods found.
   *
   * @param class1
   *          the class1
   * @param object
   *          the object
   * @param diagnostics
   *          the diagnostics
   * @param context
   *          the context
   * @return true, if successful
   */
  @SuppressWarnings("PMD.NPathComplexity")
  protected final boolean internalValidate(final EClass class1, final EObject object, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
    initCheckMethodCache();

    List<MethodWrapper> methods = methodsForType.computeIfAbsent(object.getClass(), clazz -> {
      List<MethodWrapper> result = Lists.newArrayList();
      for (MethodWrapper mw : checkMethods) {
        if (mw.isMatching(clazz)) {
          result.add(mw);
        }
      }
      return result;
    });

    if (methods.isEmpty()) {
      return true;
    }

    CheckMode checkMode = CheckMode.getCheckMode(context);
    State internalState = new State();
    internalState.chain = diagnostics;
    internalState.currentObject = object;
    internalState.checkMode = checkMode;
    internalState.context = context;

    if (isTraceEnabled == null) {
      isTraceEnabled = traceSet.isEnabled(ResourceValidationRuleSummaryEvent.class);
    }
    ResourceValidationRuleSummaryEvent.Collector collector = isTraceEnabled
        ? ResourceValidationRuleSummaryEvent.Collector.extractFromLoadOptions(object.eResource().getResourceSet())
        : null;

    List<MethodWrapper> erroneousMethods = null;

    for (int i = 0; i < methods.size(); i++) {
      MethodWrapper method = methods.get(i);
      // FIXME the method name is actually not the real issue code
      String ruleName = collector != null ? method.instance.getClass().getSimpleName() + '.' + method.method.getName() : null;
      try {
        if (collector != null) {
          traceStart(ruleName, object, collector);
        }
        method.invoke(internalState);
        // CHECKSTYLE:OFF Yes, we really want to catch anything here. The method invoked is user-written code that may fail arbitrarily.
        // If that happens, we want to exclude this check from all future executions! We catch Exception instead of InvocationTargetException
        // because we may also get NullPointerException or ExceptionInInitializerError here, and catching those separately would mean we had
        // to duplicate the logging and method removal.
      } catch (Exception e) {
        // CHECKSTYLE:ON
        logCheckMethodFailure(method, internalState, e);
        if (erroneousMethods == null) {
          erroneousMethods = Lists.newArrayList();
        }
        erroneousMethods.add(method);
      } finally {
        if (collector != null) {
          traceEnd(ruleName, object, collector);
        }
      }
    }

    if (erroneousMethods != null) {
      methodsForType.get(object.getClass()).removeAll(erroneousMethods);
    }

    return !internalState.hasErrors;
  }

  /**
   * Logs a check method failure.
   *
   * @param method
   *          that failed
   * @param internalState
   *          for which it failed
   * @param e
   *          exception that was raised
   */
  private void logCheckMethodFailure(final MethodWrapper method, final State internalState, final Exception e) {
    final Throwable cause = e instanceof InvocationTargetException ? ((InvocationTargetException) e).getTargetException() : e;
    final String ruleName = method.instance.getClass().getSimpleName() + '.' + method.method.getName();
    final EObject object = internalState.currentObject;
    final Resource res = object.eResource();
    LOGGER.error("Permanently disabling check method " + ruleName + " for context " + object.getClass().getName() + " because of failure" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
  private void traceStart(final String rule, final EObject object, final Collector collector) {
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
  private void traceEnd(final String rule, final EObject object, final Collector collector) {
    if (collector != null) {
      collector.ruleEnded(rule, object);
    }
  }

  /**
   * The Class MethodWrapper.
   */
  private static class MethodWrapper {
    private final Method method;
    private final String s;
    private final DefaultCheckImpl instance;

    MethodWrapper(final DefaultCheckImpl instance, final Method m) {
      this.instance = instance;
      this.method = m;
      this.s = m.getName() + ":" + m.getParameterTypes()[0].getName(); //$NON-NLS-1$
    }

    @Override
    public int hashCode() {
      return s.hashCode() ^ instance.hashCode();
    }

    /**
     * Checks if a given formal parameter type is assignable from current method's first formal parameter type.
     *
     * @param param
     *          the parameter class
     * @return true, if given class is assignable from current method's first formal parameter type
     */
    public boolean isMatching(final Class<?> param) {
      return method.getParameterTypes()[0].isAssignableFrom(param);
    }

    /**
     * Invokes a method using reflection.
     *
     * @param state
     *          the state
     * @throws InvocationTargetException
     *           if the method called throws an exception
     */
    public void invoke(final State state) throws InvocationTargetException {
      if (instance.state.get() != null && instance.state.get() != state) {
        throw new IllegalStateException("State is already assigned."); //$NON-NLS-1$
      }
      boolean wasNull = instance.state.get() == null;
      if (wasNull) {
        instance.state.set(state);
      }
      try {
        Check annotation = method.getAnnotation(Check.class);
        if (!state.checkMode.shouldCheck(annotation.value())) {
          return;
        }
        try {
          state.currentMethod = method;
          state.currentCheckType = annotation.value();
          method.setAccessible(true);
          method.invoke(instance, state.currentObject);
        } catch (IllegalArgumentException e) {
          LOGGER.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
          LOGGER.error(e.getMessage(), e);
        }
      } finally {
        if (wasNull) {
          instance.state.set(null);
        }
      }
    }

    @Override
    public boolean equals(final Object obj) {
      if (!(obj instanceof MethodWrapper)) {
        return false;
      }
      MethodWrapper mw = (MethodWrapper) obj;
      return s.equals(mw.s) && instance == mw.instance;
    }
  }

  /**
   * Collect all method wrappers.
   *
   * @return the list of method wrappers
   */
  private List<MethodWrapper> collectMethods() {
    List<MethodWrapper> result = new ArrayList<MethodWrapper>();
    Set<Class<?>> visitedClasses = new HashSet<Class<?>>(VISITED_CLASSES_INIT_CAPACITY);
    collectMethods(this, visitedClasses, result);
    return result;
  }

  /**
   * Collects methods method wrappers and stores them in given collection.
   *
   * @param instance
   *          the instance
   * @param visitedClasses
   *          the visited classes
   * @param result
   *          the collection in which to store method wrappers found
   */
  private void collectMethods(final DefaultCheckImpl instance, final Collection<Class<?>> visitedClasses, final Collection<MethodWrapper> result) {
    if (visitedClasses.contains(instance.getClass())) {
      return;
    }
    collectMethodsImpl(instance, visitedClasses, result);
  }

  /**
   * Internally collects methods in a given instance of {@link DefaultCheckImpl}. A given instance class is only analyzed once.
   *
   * @param instance
   *          the instance of {@link DefaultCheckImpl}
   * @param visitedClasses
   *          the visited classes
   * @param result
   *          the collection in which to store method wrappers found
   */
  private void collectMethodsImpl(final DefaultCheckImpl instance, final Collection<Class<?>> visitedClasses, final Collection<MethodWrapper> result) {
    if (!visitedClasses.add(instance.getClass())) {
      return;
    }
    Method[] methods = instance.getClass().getDeclaredMethods();
    for (Method method : methods) {
      if (method.getAnnotation(Check.class) != null && method.getParameterTypes().length == 1) {
        result.add(new MethodWrapper(instance, method));
      }
    }
  }

  /**
   * The holding the current state for a validation method being executed.
   */
  public static class State {
    // CHECKSTYLE:OFF
    public DiagnosticChain chain = null;
    public EObject currentObject = null;
    public Method currentMethod = null;
    public CheckMode checkMode = null;
    public CheckType currentCheckType = null;
    public boolean hasErrors = false;
    public Map<Object, Object> context;
    // CHECKSTYLE:ON
  }

  protected EObject getCurrentObject() {
    return state.get().currentObject;
  }

  protected Method getCurrentMethod() {
    return state.get().currentMethod;
  }

  protected DiagnosticChain getChain() {
    return state.get().chain;
  }

  protected CheckMode getCheckMode() {
    return state.get().checkMode;
  }

  protected Map<Object, Object> getContext() {
    return state.get().context;
  }

  /**
   * The inner Class StateAccess provides access to the {@link State} of the validator being handled.
   */
  public static final class StateAccess {

    private final DefaultCheckImpl validator;

    private StateAccess(final DefaultCheckImpl validator) {
      this.validator = validator;
    }

    /**
     * Gets the validator's state.
     *
     * @return the state
     */
    public State getState() {
      State result = validator.state.get();
      if (result == null) {
        result = new State();
        validator.state.set(result);
      }
      return result;
    }
  }

  // ////////////////////////////////////////////////////////
  // Implementation of the Validation message acceptor below
  // ////////////////////////////////////////////////////////

  /** {@inheritDoc} */
  @Override
  public void acceptError(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    this.state.get().hasErrors = true;
    state.get().chain.add(createDiagnostic(Severity.ERROR, message, object, feature, index, code, issueData));
  }

  /** {@inheritDoc} */
  @Override
  public void acceptWarning(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    state.get().chain.add(createDiagnostic(Severity.WARNING, message, object, feature, index, code, issueData));
  }

  /** {@inheritDoc} */
  @Override
  public void acceptInfo(final String message, final EObject object, final EStructuralFeature feature, final int index, final String code, final String... issueData) {
    state.get().chain.add(createDiagnostic(Severity.INFO, message, object, feature, index, code, issueData));
  }

  /** {@inheritDoc} */
  @Override
  public void acceptError(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    this.state.get().hasErrors = true;
    state.get().chain.add(createDiagnostic(Severity.ERROR, message, object, offset, length, code, issueData));
  }

  /** {@inheritDoc} */
  @Override
  public void acceptWarning(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    state.get().chain.add(createDiagnostic(Severity.WARNING, message, object, offset, length, code, issueData));
  }

  /** {@inheritDoc} */
  @Override
  public void acceptInfo(final String message, final EObject object, final int offset, final int length, final String code, final String... issueData) {
    state.get().chain.add(createDiagnostic(Severity.INFO, message, object, offset, length, code, issueData));
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
    return new FeatureBasedDiagnostic(diagnosticSeverity, message, object, feature, index, state.get().currentCheckType, code, issueData);
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
    return new CheckRangeBasedDiagnostic(diagnosticSeverity, message, object, offset, length, state.get().currentCheckType, code, issueData);
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
  protected int toDiagnosticSeverity(final Severity severity) {
    int diagnosticSeverity = -1;
    switch (severity) {
    case ERROR:
      diagnosticSeverity = Diagnostic.ERROR;
      break;
    case WARNING:
      diagnosticSeverity = Diagnostic.WARNING;
      break;
    case INFO:
      diagnosticSeverity = Diagnostic.INFO;
      break;
    default:
      throw new IllegalArgumentException("Unknow severity " + severity); //$NON-NLS-1$
    }
    return diagnosticSeverity;
  }

}
