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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.avaloq.tools.ddk.test.core.TestStepListener.TestStepState;


/**
 * Abstract class that represents a test step.
 */
public abstract class AbstractTestStep extends AbstractStep {
  /** Maps test step identifiers to {@link TestStepInformation}s. */
  private static final Map<String, TestStepInformation> INFORMATION_MAP = new HashMap<String, TestStepInformation>();

  private static final Set<TestStepListener> TEST_STEP_LISTENERS = new HashSet<TestStepListener>();

  /** Whether pre- and postconditions shall be checked. */
  private static boolean checkPreconditions = true;
  private static boolean checkPostconditions = true;

  /**
   * Registers the given {@link TestStepListener}. Registering an already registered {@link TestStepListener} has no effect.
   *
   * @param listener
   *          the {@link TestStepListener} to register, must not be {@code null}
   */
  public static void registerTestStepListener(final TestStepListener listener) {
    synchronized (TEST_STEP_LISTENERS) {
      TEST_STEP_LISTENERS.add(listener);
    }
  }

  /**
   * Removes the given {@link TestStepListener}, if it was registered.
   *
   * @param listener
   *          the {@link TestStepListener} to remove, must not be {@code null}
   */
  public static void removeTestStepListener(final TestStepListener listener) {
    synchronized (TEST_STEP_LISTENERS) {
      TEST_STEP_LISTENERS.remove(listener);
    }
  }

  /**
   * Updates the {@link TestStepState} of this {@link AbstractTestStep}.
   *
   * @param newTestStepState
   *          the new {@link TestStepState}, must not be {@code null}
   */
  protected void updateTestStepState(final TestStepState newTestStepState) {
    updateTestStepState(newTestStepState, null);
  }

  /**
   * Updates the {@link TestStepState} of this {@link AbstractTestStep}.
   *
   * @param newTestStepState
   *          the new {@link TestStepState}, must not be {@code null}
   * @param throwable
   *          the thrown {@link Throwable}, may be {@code null}
   */
  protected void updateTestStepState(final TestStepState newTestStepState, final Throwable throwable) {
    synchronized (TEST_STEP_LISTENERS) {
      for (final TestStepListener listener : TEST_STEP_LISTENERS) {
        listener.stepStateChanged(this, newTestStepState, throwable);
      }
    }
  }

  /**
   * Retrieves the {@link TestStepInformation} for this test step.
   *
   * @return the {@link TestStepInformation} for this test step, never {@code null}
   */
  public synchronized TestStepInformation getTestStepInformation() {
    TestStepInformation testStepInformation = INFORMATION_MAP.get(getIdentifier());
    if (testStepInformation == null) {
      String testStepIdentifier = getIdentifier();
      testStepInformation = new TestStepInformation(testStepIdentifier);
      INFORMATION_MAP.put(testStepIdentifier, testStepInformation);
    }
    return testStepInformation;
  }

  /** {@inheritDoc} */
  @Override
  public final void undo() {
    cleanUp();
  }

  /**
   * Returns the undo step for this step.
   *
   * @return the undo step for this step, {@link NullStep} if no undo available, never {@code null}
   */
  @Override
  public AbstractStep getUndoStep() {
    return NullStep.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final void run() {
    getTestStepInformation().incrementExecuteCount();
    updateTestStepState(TestStepState.START);
    runBefore();
    updateTestStepState(TestStepState.RUN);
    try {
      runTestStep();
      runAfter();
    } catch (ContractViolation violation) {
      updateTestStepState(TestStepState.FAILED, violation);
      throw violation;
      // CHECKSTYLE:CHECK-OFF IllegalCatch // exception is still thrown
    } catch (final Error error) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      updateTestStepState(TestStepState.ERRORED, error);
      throw error;
      // CHECKSTYLE:CHECK-OFF IllegalCatch // exception is still thrown
    } catch (final RuntimeException exception) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      updateTestStepState(TestStepState.ERRORED, exception);
      throw exception;
    } finally {
      runFinally();
      updateTestStepState(TestStepState.END);
    }
  }

  /**
   * Performs the precondition checks before the step logic.
   */
  protected void runBefore() {
    if (isCheckPreconditions()) {
      try {
        updateTestStepState(TestStepState.CHECK_PRECONDITIONS);
        assertPreconditions();
        // CHECKSTYLE:CHECK-OFF IllegalCatch // exception is wrapped
      } catch (Throwable t) {
        // CHECKSTYLE:CHECK-ON IllegalCatch
        throw new PreconditionViolation(t);
      }
    }
  }

  /**
   * Prepares the results and performs the postcondition checks after the step logic.
   * This method is only executed if the step logic succeeded.
   */
  protected void runAfter() {
    try {
      returnResults();
      if (isCheckPostconditions()) {
        updateTestStepState(TestStepState.CHECK_POSTCONDITIONS);
        assertPostconditions();
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch // exception is wrapped
    } catch (final Throwable t) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      throw new PostconditionViolation(t);
    }
  }

  /**
   * Is executed at the end of this step even if an exception occurred.
   */
  protected abstract void runFinally();

  /**
   * Checks the preconditions that need to be satisfied before this step.
   */
  protected abstract void assertPreconditions();

  /**
   * Implementation of this test step. This method should only be called by {@link run}().
   */
  protected abstract void runTestStep();

  /**
   * Checks the postconditions that need to be satisfied after this step.
   */
  protected abstract void assertPostconditions();

  /**
   * Cleans up everything that this test step created and left behind.
   * Implementations need to first check if what they created is still existent
   * and to be removed. Should only be called by the {@link undo}() method.
   */
  protected abstract void cleanUp();

  /**
   * Enables or disables preconditions.
   *
   * @param checkPreconditions
   *          whether preconditions shall be checked
   */
  protected static void setCheckPreconditions(final boolean checkPreconditions) {
    AbstractTestStep.checkPreconditions = checkPreconditions;
  }

  /**
   * Enables or disables postconditions.
   *
   * @param checkPostconditions
   *          whether postconditions shall be checked
   */
  protected static void setCheckPostconditions(final boolean checkPostconditions) {
    AbstractTestStep.checkPostconditions = checkPostconditions;
  }

  /**
   * Returns {@code true} if preconditions should be checked.
   *
   * @return {@code true} if preconditions should be checked, {@code false} otherwise
   */
  protected static boolean isCheckPreconditions() {
    return checkPreconditions;
  }

  /**
   * Returns {@code true} if postconditions should be checked.
   *
   * @return {@code true} if postconditions should be checked, {@code false} otherwise
   */
  protected static boolean isCheckPostconditions() {
    return checkPostconditions;
  }

  /**
   * Called at the end of the step to generate the results of this step, if it has any. Steps that have results need to override this method.
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  protected void returnResults() {
    // by default, a step has no results.
  }

  /**
   * Returns the given value as a result of this {@link AbstractTestStep}.
   *
   * @param <T>
   *          the type of the result
   * @param result
   *          the result data
   * @param value
   *          the value of the result
   */
  protected <T> void returnResult(final StepResult<T> result, final T value) {
    result.setValue(value);
  }

  /**
   * Checks whether the preconditions of this {@link AbstractTestStep} are satisfied.
   *
   * @return {@code true} if this {@link AbstractTestStep} can safely be run, {@code false} otherwise
   */
  public boolean arePreconditionsSatisfied() {
    try {
      assertPreconditions();
      return true;
    } catch (final AssertionError error) {
      return false;
    }
  }

  /**
   * Checks whether the postconditions of this {@link AbstractTestStep} are satisfied.
   *
   * @return {@code true} if the postconditions of this {@link AbstractTestStep} are satisfied, {@code false} otherwise
   */
  public boolean arePostconditionsSatisfied() {
    try {
      assertPostconditions();
      return true;
    } catch (final AssertionError error) {
      return false;
    }
  }
}
