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

import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.Assert;

import com.google.common.collect.Lists;


/**
 * Reusable class that represents a compound step.
 */
public class CompoundStep extends AbstractStep {
  private static final String ARGUMENT_THROWABLE = "throwable";
  private static final String ARGUMENT_STEP = "step";
  private static final String ARGUMENT_STEPS = "plannedSteps";
  // The particular suppress warnings annotation is needed because we WANT to have a unique logger for each of the subclasses
  @SuppressWarnings("PMD.LoggerIsNotStaticFinal")
  /** The logger, never {@code null}. */
  private final Logger logger = LogManager.getLogger(getClass());
  /** A list of {@link AbstractStep}s, which this {@link CompoundStep} consists of. */
  private final List<AbstractStep> plannedSteps = Lists.newLinkedList();
  /** Holds the so far executed {@link AbstractStep}s. */
  private final List<AbstractStep> executedSteps = Lists.newLinkedList();

  /**
   * Returns the list of {@link AbstractStep}s this {@link CompoundStep} has.
   *
   * @return the list of {@link AbstractStep}s this {@link CompoundStep} has, never {@code null}
   */
  public List<AbstractStep> getSteps() {
    return plannedSteps;
  }

  /** {@inheritDoc} */
  @Override
  public void run() {
    getExecutedSteps().clear();
    for (final AbstractStep step : getSteps()) {
      getExecutedSteps().add(step);
      step.run();
    }
  }

  /**
   * Runs the plannedSteps of this {@link CompoundStep}, making sure that any exception is ignored and the next step executed.
   * In the end however, all logged exceptions are reported (thrown as a {@link MultipleTestProblems} exception).
   */
  protected void runIgnoreAndContinue() {
    getExecutedSteps().clear();
    MultipleTestProblems problemsEncountered = new MultipleTestProblems();
    for (final AbstractStep step : getSteps()) {
      getExecutedSteps().add(step);
      try {
        if (step instanceof CompoundStep) {
          ((CompoundStep) step).runIgnoreAndContinue();
        } else {
          step.run();
        }
        // CHECKSTYLE:CHECK-OFF IllegalCatch
        // we want to exclude precondition violations, report the rest and continue with the undo operations.
      } catch (Throwable t) {
        // CHECKSTYLE:CHECK-ON IllegalCatch
        addUndoProblem(problemsEncountered, step, t);
      }
    }
    problemsEncountered.assertEmpty();
  }

  /**
   * Undoes everything this compound step created and left behind. Reports exceptions but keeps going and undoes all plannedSteps. {@inheritDoc}
   */
  @Override
  public void undo() {
    final MultipleTestProblems problemsEncountered = new MultipleTestProblems();
    if (getUndoStep() != NullStep.INSTANCE) {
      AbstractStep undoStep = getUndoStep();
      try {
        if (undoStep instanceof CompoundStep) {
          ((CompoundStep) undoStep).runIgnoreAndContinue();
        } else {
          undoStep.run();
        }
        // CHECKSTYLE:CHECK-OFF IllegalCatch
        // we want to exclude precondition violations, report the rest and continue with the undo operations.
      } catch (Throwable t) {
        // CHECKSTYLE:CHECK-ON IllegalCatch
        addUndoProblem(problemsEncountered, undoStep, t);
      }
    } else {
      ListIterator<AbstractStep> reverseIterator = getExecutedSteps().listIterator(getExecutedSteps().size());
      while (reverseIterator.hasPrevious()) {
        AbstractStep step = reverseIterator.previous();
        try {
          step.undo();
          // CHECKSTYLE:CHECK-OFF IllegalCatch
          // we want to exclude precondition violations, report the rest and continue with the undo operations.
        } catch (Throwable t) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          addUndoProblem(problemsEncountered, step, t);
        }
      }
    }
    problemsEncountered.assertEmpty();
  }

  /**
   * Adds the given {@link Throwable} to the given {@link MultipleTestProblems} instance.
   * <p>
   * <em>Note</em>: Precondition violations are ignored. Other problems are added and logged.
   * </p>
   *
   * @param multipleTestProblems
   *          the {@link MultipleTestProblems} which is accumulating the problems, must not be {@code null}
   * @param step
   *          the undo {@link AbstractStep}, must not be {@code null}
   * @param throwable
   *          the {@link Throwable} of the problem(s) encountered, must not be {@code null}
   */
  private void addUndoProblem(final MultipleTestProblems multipleTestProblems, final AbstractStep step, final Throwable throwable) {
    Assert.isNotNull(multipleTestProblems, "multipleTestProblems");
    Assert.isNotNull(step, ARGUMENT_STEP);
    Assert.isNotNull(throwable, ARGUMENT_THROWABLE);
    if (throwable instanceof PreconditionViolation) {
      // ignore precondition violations and continue with the undo operations.
    } else if (throwable instanceof MultipleTestProblems) {
      MultipleTestProblems caughtMultipleTestProblems = (MultipleTestProblems) throwable;
      for (Throwable problem : caughtMultipleTestProblems.getProblems()) {
        // discard all precondition violations and continue with the undo operations.
        if (!(problem instanceof PreconditionViolation)) {
          multipleTestProblems.addProblem(problem);
          logError(step, problem);
        }
      }
    } else {
      multipleTestProblems.addProblem(throwable);
      logError(step, throwable);
    }
  }

  /**
   * Logs an error occurred during the given {@link AbstractStep}.
   *
   * @param step
   *          the step where the error occurred, never {@code null}
   * @param throwable
   *          the problem occurred, never {@code null}
   */
  private void logError(final AbstractStep step, final Throwable throwable) {
    Assert.isNotNull(step, ARGUMENT_STEP);
    Assert.isNotNull(throwable, ARGUMENT_THROWABLE);
    logger.error("Error in " + step.getName() + ": " + throwable.getLocalizedMessage());
  }

  /**
   * Adds the given {@link AbstractStep} to this compound step.
   *
   * @param <T>
   *          the type of the {@link AbstractStep}
   * @param step
   *          the {@link AbstractStep} to add, must not be {@code null}
   * @return the newly added {@link AbstractStep}, never {@code null}
   */
  public <T extends AbstractStep> T addStep(final T step) {
    Assert.isNotNull(step, ARGUMENT_STEP);
    plannedSteps.add(step);
    return step;
  }

  /**
   * Adds the given {@link AbstractStep}s to this compound step.
   *
   * @param steps
   *          the steps to add, must not be {@code null}
   */
  public void addSteps(final List<AbstractStep> steps) {
    Assert.isNotNull(steps, ARGUMENT_STEPS);
    for (final AbstractStep step : steps) {
      if (step != null) {
        addStep(step);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public String getDescription() {
    return "A compound step.";
  }

  /** {@inheritDoc} */
  @Override
  public AbstractStep getUndoStep() {
    return NullStep.INSTANCE;
  }

  /**
   * Returns the {@link AbstractStep}s of this {@link CompoundStep} that have been executed.
   *
   * @return the executed plannedSteps, never {@code null}
   */
  public List<AbstractStep> getExecutedSteps() {
    return executedSteps;
  }
}

