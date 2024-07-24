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

import java.lang.reflect.Method;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;


/**
 * A check catalog implementations that keeps track of validation state internally.
 */
public abstract class AbstractStatefulCheckImpl extends AbstractCheckImpl implements ValidationMessageAcceptorMixin {

  private final ThreadLocal<State> threadLocalState;

  private final ValidationMessageAcceptor messageAcceptor;

  public AbstractStatefulCheckImpl() {
    this.threadLocalState = new ThreadLocal<State>();
    this.messageAcceptor = this;
  }

  protected ThreadLocal<State> getThreadLocalState() {
    return threadLocalState;
  }

  public ValidationMessageAcceptor getMessageAcceptor() {
    return messageAcceptor;
  }

  // ////////////////////////////////////////////////
  // Copied and adapted from AbstractDeclarativeValidator below
  // ////////////////////////////////////////////////

  /**
   * The class holding the current state for a validation method being executed.
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
  }

  @Override
  public DiagnosticChain getChain() {
    return threadLocalState.get().chain;
  }

  @Override
  public CheckType getCurrentCheckType() {
    return threadLocalState.get().currentCheckType;
  }

  @Override
  public void setHasErrors() {
    threadLocalState.get().hasErrors = true;
  }

  protected EObject getCurrentObject() {
    return threadLocalState.get().currentObject;
  }

  protected Method getCurrentMethod() {
    return threadLocalState.get().currentMethod;
  }

  protected CheckMode getCheckMode() {
    return threadLocalState.get().checkMode;
  }

  protected Map<Object, Object> getContext() {
    return threadLocalState.get().context;
  }

}
