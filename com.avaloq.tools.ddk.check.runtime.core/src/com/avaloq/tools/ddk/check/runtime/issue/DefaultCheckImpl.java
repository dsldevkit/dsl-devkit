/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckMode;

import com.avaloq.tools.ddk.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * The class DefaultCheckImpl serves as the default parent implementation for generated
 * issue classes.
 */
@SuppressWarnings({"checkstyle:AbstractClassName"})
public abstract class DefaultCheckImpl extends AbstractStatefulCheckImpl {

  private static final int VISITED_CLASSES_INIT_CAPACITY = 4;

  private static final Logger LOGGER = LogManager.getLogger(DefaultCheckImpl.class);

  private volatile Set<MethodWrapper> checkMethods; // NOPMD: may be modified by different threads; thread safety guaranteed by double-checked locking

  private final Map<Class<?>, List<MethodWrapper>> methodsForType = Maps.newHashMap();

  @Inject
  private ITraceSet traceSet;

  private Boolean isTraceEnabled;

  // ////////////////////////////////////////////////
  // Copied and adapted from AbstractDeclarativeValidator below
  // ////////////////////////////////////////////////

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

  private String ruleName(final MethodWrapper method) {
    // FIXME the method name is actually not the real issue code
    return method.instance.getClass().getSimpleName() + '.' + method.method.getName();
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

    for (MethodWrapper method : methods) {
      try {
        if (collector != null) {
          traceStart(ruleName(method), object, collector);
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
          traceEnd(ruleName(method), object, collector);
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
    final EObject object = internalState.currentObject;
    super.logCheckMethodFailure(ruleName(method), object, e);
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
      State instanceState = instance.getThreadLocalState().get();
      if (instanceState != null && instanceState != state) {
        throw new IllegalStateException("State is already assigned."); //$NON-NLS-1$
      }
      boolean wasNull = instanceState == null;
      if (wasNull) {
        instance.getThreadLocalState().set(state);
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
          method.invoke(instance, (Object) state.currentObject);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          LOGGER.error(e.getMessage(), e);
        }
      } finally {
        if (wasNull) {
          instance.getThreadLocalState().set(null);
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
    Class<?> clazz = instance.getClass();
    if (!visitedClasses.add(clazz)) {
      return;
    }
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getAnnotation(Check.class) != null && method.getParameterTypes().length == 1) {
        result.add(new MethodWrapper(instance, method));
      }
    }
  }

}
