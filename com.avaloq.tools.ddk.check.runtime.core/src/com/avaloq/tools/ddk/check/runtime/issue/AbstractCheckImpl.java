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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent.Collector;


/**
 * An abstract base class for check catalog implementations.
 */
public abstract class AbstractCheckImpl implements ICheckValidatorImpl {
  private static final Logger LOGGER = LogManager.getLogger(AbstractCheckImpl.class);

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

}
