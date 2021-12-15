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
package com.avaloq.tools.ddk.test.core;

/**
 * Listens for events of executing {@link AbstractTestStep}s.
 */
public interface TestStepListener {

  /**
   * Defines the execution state in which the {@link AbstractTestStep} is.
   */
  enum TestStepState {
    /** The {@link START} state represents the beginning of the step. */
    START,
    /** In the {@link CHECK_PRECONDITIONS} state the preconditions are checked (optional state between {@link START} and {@link RUN} states). */
    CHECK_PRECONDITIONS,
    /** In the {@link RUN} state the main logic is executed. */
    RUN,
    /** In the {@link CHECK_POSTCONDITIONS} state the postconditions are checked (optional state after {@link RUN}). */
    CHECK_POSTCONDITIONS,
    /** The {@link FAILED} state means the step contract (preconditions or postconditions) is not fulfilled. */
    FAILED,
    /** The {@link ERRORED} state means an unexpected execution error occurred. */
    ERRORED,
    /** The {@link END} state is always the last state. */
    END
  }

  /**
   * Indicates that the given {@link AbstractTestStep} has changed its running state.
   *
   * @param testStep
   *          the {@link AbstractTestStep} event source, must not be {@code null}
   * @param testStepState
   *          the {@link TestStepState} of the {@link AbstractTestStep}, must not be {@code null}
   * @param throwable
   *          the thrown {@link Throwable}, may be {@code null}
   */
  void stepStateChanged(AbstractTestStep testStep, TestStepState testStepState, Throwable throwable);
}

