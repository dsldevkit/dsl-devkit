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
package com.avaloq.tools.ddk.test.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.junit.Assert;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;


/**
 * The test plan containing the setup and test steps and the {@link TestEntityAction}s of a test.
 */
final class TestPlan {
  /** A {@link CompoundStep}, which is used to hold the setup steps for this {@link TestPlan}. */
  private final CompoundStep compoundSetupStep = new CompoundStep();
  /** A {@link CompoundStep}, which is used to hold the test steps for this {@link TestPlan}. */
  private final CompoundStep compoundTestStep = new CompoundStep();
  /** The {@link TestEntityAction}s of the test steps of this {@link TestPlan}. */
  private List<TestEntityAction> testEntityActions;

  /**
   * Instantiates a new {@link TestPlan} with empty {@link CompoundStep}s.
   */
  private TestPlan() {
  }

  /**
   * Instantiates a new {@link TestPlan} with the given test-steps and setup-steps.
   *
   * @param setupSteps
   *          the setup steps, must not be {@code null}
   * @param testSteps
   *          the test steps, must not be {@code null}
   */
  private TestPlan(final List<AbstractStep> setupSteps, final List<AbstractStep> testSteps) {
    compoundSetupStep.addSteps(setupSteps);
    compoundTestStep.addSteps(testSteps);
  }

  /**
   * Returns a new instance of {@link TestPlan} with empty {@link CompoundStep}s.
   *
   * @return a new test plan instance, never {@code null}
   */
  public static TestPlan create() {
    return new TestPlan();
  }

  /**
   * Adds a setup step to this {@link TestPlan} and asserts that it is not added after a test step.
   *
   * @param <T>
   *          the generic type
   * @param setupStep
   *          the setup step, must not be {@code null}
   * @return the newly added {@link AbstractStep}, never {@code null}
   */
  public <T extends AbstractStep> T addSetupStep(final T setupStep) {
    Assert.assertTrue("Must not add a setup step after adding a test step.", getCompoundTestStep().getSteps().isEmpty());
    getCompoundSetupStep().addStep(setupStep);
    return setupStep;
  }

  /**
   * Adds a test step to this {@link TestPlan}.
   *
   * @param <T>
   *          the generic type
   * @param testStep
   *          the test step, must not be {@code null}
   * @return the newly added {@link AbstractStep}, never {@code null}
   */
  public <T extends AbstractStep> T addTestStep(final T testStep) {
    getCompoundTestStep().addStep(testStep);
    return testStep;
  }

  /**
   * Returns the {@link TestEntityAction}s contained in the given {@link CompoundStep}.
   *
   * @param compoundStep
   *          the compound step, must not be {@code null}
   * @return the {@link TestEntityAction}s, never {@code null}
   */
  private List<TestEntityAction> getTestEntityActions(final CompoundStep compoundStep) {
    List<TestEntityAction> allTestEntityActions = new ArrayList<TestEntityAction>();
    for (final AbstractStep step : compoundStep.getSteps()) {
      if (step instanceof ITestEntityActionProvider) {
        allTestEntityActions.addAll(((ITestEntityActionProvider) step).getTestEntityActions());
      }
    }
    return allTestEntityActions;
  }

  /**
   * Returns all {@link TestEntityAction}s contained in the setup and test compound steps.
   *
   * @return all {@link TestEntityAction}s, never {@code null}
   */
  private List<TestEntityAction> getAllTestEntityActions() {
    if (testEntityActions == null) {
      testEntityActions = getTestEntityActions(compoundSetupStep);
      testEntityActions.addAll(getTestEntityActions(compoundTestStep));
    }
    return testEntityActions;
  }

  /**
   * Creates a {@link TestPlan} which contains the previous test steps that need to be undone, before the current test can be executed.
   *
   * @param testPlan
   *          current {@link TestPlan}, must not be {@code null}
   * @param previousTestPlan
   *          previous {@link TestPlan}, may be {@code null}
   * @param systemTest
   *          whether a system test plan shall be created
   * @return test plan containing previous test steps that need to be undone, never {@code null}
   */
  public static TestPlan createUndoTestPlan(final TestPlan testPlan, final TestPlan previousTestPlan, final boolean systemTest) {
    if (systemTest) {
      // If the current and the previous test are system tests, compute the steps to undo.
      List<AbstractStep> setupStepsToUndo = computeStepsToUndo(previousTestPlan.getCompoundSetupStep().getSteps(), testPlan);
      List<AbstractStep> testStepsToUndo = computeStepsToUndo(previousTestPlan.getCompoundTestStep().getSteps(), testPlan);
      return new TestPlan(setupStepsToUndo, testStepsToUndo);
    }
    // else: If current test is not a System Test and the step before was, all steps have to be undone.
    List<AbstractStep> setupStepsToUndo = getAllUndoSteps(previousTestPlan.getCompoundSetupStep().getExecutedSteps());
    Collections.reverse(setupStepsToUndo);
    List<AbstractStep> testStepsToUndo = getAllUndoSteps(previousTestPlan.getCompoundTestStep().getExecutedSteps());
    Collections.reverse(setupStepsToUndo);
    return new TestPlan(setupStepsToUndo, testStepsToUndo);
  }

  /**
   * Returns a set containing all steps of a {@link TestPlan} that need a {@link ITestEntity} that is still available from a previous test.
   *
   * @param previousTestPlan
   *          the previous test plan, must not be {@code null}
   * @param testPlan
   *          the test plan, must not be {@code null}
   * @return a set of all steps with previously existing test entities, never {@code null}
   */
  public static Set<AbstractStep> getAllStepsWithPreExistingTestEntities(final TestPlan previousTestPlan, final TestPlan testPlan) {
    Set<AbstractStep> stepsWithPreExistingTestEntities = new HashSet<AbstractStep>();
    stepsWithPreExistingTestEntities.addAll(getStepsWithPreExistingEntities(testPlan, previousTestPlan.compoundSetupStep));
    stepsWithPreExistingTestEntities.addAll(getStepsWithPreExistingEntities(testPlan, previousTestPlan.compoundTestStep));
    return stepsWithPreExistingTestEntities;
  }

  /**
   * Returns a set containing all steps of a {@link CompoundStep} that need a {@link ITestEntity} that is still available from a previous test.
   *
   * @param testPlan
   *          the test plan, must not be {@code null}
   * @param compoundStep
   *          the compound step, must not be {@code null}
   * @return the steps with pre existing entities, never {@code null}
   */
  private static Set<AbstractStep> getStepsWithPreExistingEntities(final TestPlan testPlan, final CompoundStep compoundStep) {
    Set<AbstractStep> preExistingTestEntities = new HashSet<AbstractStep>();
    for (final AbstractStep step : compoundStep.getSteps()) {
      if (step instanceof ITestEntityActionProvider && testPlan.hasAllTestEntities((ITestEntityActionProvider) step)) {
        preExistingTestEntities.add(step);
      }
    }
    return preExistingTestEntities;
  }

  /**
   * Utility method that checks if the {@link TestPlan} contains all {@link ITestEntity}s of the given {@link ITestEntityActionProvider}.
   *
   * @param step
   *          the step, must not be {@code null}
   * @return {@code true}, if this {@link TestPlan} contains all {@link ITestEntity}s
   */
  private boolean hasAllTestEntities(final ITestEntityActionProvider step) {
    boolean hasAllEntites = false;
    for (TestEntityAction action : step.getTestEntityActions()) {
      if (hasTestEntity(action)) {
        hasAllEntites = true;
      } else {
        return false;
      }
    }
    return hasAllEntites;
  }

  /**
   * Returns all executed steps of the given {@link TestPlan}.
   *
   * @return all executed steps, never {@code null}
   */
  public Set<AbstractStep> getAllExecutedSteps() {
    Set<AbstractStep> executedSteps = new HashSet<AbstractStep>();
    executedSteps.addAll(getCompoundSetupStep().getExecutedSteps());
    executedSteps.addAll(getCompoundTestStep().getExecutedSteps());
    return executedSteps;
  }

  /**
   * Creates a {@link TestPlan} that contains only the {@link AbstractStep}s contained in the filter.
   *
   * @param testPlan
   *          the test plan, must not be {@code null}
   * @param filter
   *          the filter, must not be {@code null}
   * @return the new filtered test plan, never {@code null}
   */
  public static TestPlan createFilteredTestPlan(final TestPlan testPlan, final Collection<AbstractStep> filter) {
    List<AbstractStep> filteredSetupSteps = filterCompoundStep(filter, testPlan.getCompoundSetupStep());
    List<AbstractStep> filteredTestSteps = filterCompoundStep(filter, testPlan.getCompoundTestStep());
    return new TestPlan(filteredSetupSteps, filteredTestSteps);
  }

  /**
   * Creates a {@link TestPlan} where all the {@link AbstractStep}s of the {@link CompoundStep}s of the given {@link TestPlan} are put in reverse order.
   *
   * @param testPlan
   *          the test plan, must not be {@code null}
   * @return the test reverse test plan, never {@code null}
   */
  public static TestPlan createReverseTestPlan(final TestPlan testPlan) {
    List<AbstractStep> setupSteps = testPlan.getCompoundSetupStep().getSteps();
    Collections.reverse(setupSteps);
    List<AbstractStep> testSteps = testPlan.getCompoundTestStep().getSteps();
    Collections.reverse(testSteps);
    return new TestPlan(setupSteps, testSteps);
  }

  /**
   * Filters a {@link CompoundStep} and returns a list of {@link AbstractStep}s that are contained in the filter {@link Set}.
   *
   * @param filter
   *          the filter, must not be {@code null}
   * @param compoundStep
   *          the compound step, must not be {@code null}
   * @return the list of filtered {@link AbstractStep}s, never {@code null}
   */
  private static List<AbstractStep> filterCompoundStep(final Collection<AbstractStep> filter, final CompoundStep compoundStep) {
    List<AbstractStep> steps = new ArrayList<AbstractStep>();
    for (AbstractStep step : compoundStep.getSteps()) {
      if (filter.contains(step)) {
        steps.add(step);
      }
    }
    return steps;
  }

  /**
   * Creates a {@link TestPlan} that contains the undo-steps of all {@link AbstractStep}s of the given {@link TestPlan}.
   *
   * @param testPlan
   *          the test plan, must not be {@code null}
   * @return the test plan containing all undo-steps, never {@code null}
   */
  public static TestPlan createUndoStepsTestPlan(final TestPlan testPlan) {
    List<AbstractStep> setupUndoSteps = getAllUndoSteps(testPlan.getCompoundSetupStep().getSteps());
    List<AbstractStep> testUndoSteps = getAllUndoSteps(testPlan.getCompoundTestStep().getSteps());
    return new TestPlan(setupUndoSteps, testUndoSteps);
  }

  /**
   * Utility method that returns a list of all undo steps from a list of {@link AbstractStep}.
   *
   * @param stepsToUndo
   *          list of steps to undo, must not be {@code null}
   * @return list of undo steps in reverse order, never {@code null}
   */
  private static List<AbstractStep> getAllUndoSteps(final List<AbstractStep> stepsToUndo) {
    List<AbstractStep> undoSteps = new ArrayList<AbstractStep>();
    for (AbstractStep step : stepsToUndo) {
      if (!NullStep.INSTANCE.equals(step.getUndoStep())) {
        undoSteps.add(step.getUndoStep());
      }
    }
    return undoSteps;
  }

  /**
   * Utility method that returns a list of steps steps of the previous test that need to be undone before executing the current test.
   *
   * @param previousSteps
   *          previous steps, must not be {@code null}
   * @param testPlan
   *          current test plan, must not be {@code null}
   * @return list of steps to be undone, never {@code null}
   */
  private static List<AbstractStep> computeStepsToUndo(final List<AbstractStep> previousSteps, final TestPlan testPlan) {
    List<AbstractStep> stepsToUndo = new ArrayList<AbstractStep>();
    ListIterator<AbstractStep> reverseIterator = previousSteps.listIterator(previousSteps.size());
    while (reverseIterator.hasPrevious()) {
      AbstractStep step = reverseIterator.previous();
      if (testPlan.isStepToUndo(step)) {
        stepsToUndo.add(step.getUndoStep());
      }
    }
    return stepsToUndo;
  }

  /**
   * Utility method that checks if is step to undo.
   *
   * @param step
   *          step, must not be {@code null}
   * @return true, if is step to undo
   */
  private boolean isStepToUndo(final AbstractStep step) {
    // Undo all steps that don't influence ITestEntities and have been executed
    if (!(step instanceof ITestEntityActionProvider)) {
      return true;
    }

    for (final TestEntityAction testEntityAction : ((ITestEntityActionProvider) step).getTestEntityActions()) {
      if (!hasTestEntity(testEntityAction)) { // Undo the step, if one TestEntity does not match.
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if this {@link TestPlan} contains the {@link ITestEntity} of the given {@link TestEntityAction}.
   *
   * @param testEntityAction
   *          the test entity action, must not be {@code null}
   * @return whether this {@link TestPlan} has {@link ITestEntity} of the given {@link TestEntityAction}
   */
  private boolean hasTestEntity(final TestEntityAction testEntityAction) {
    for (TestEntityAction action : getAllTestEntityActions()) {
      if (action.hasSameTestEntity(testEntityAction)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Creates a {@link TestPlan} with the steps that actually need to be executed for this test.
   *
   * @param testPlan
   *          current test plan, must not be {@code null}
   * @param previousTestPlan
   *          previous test plan, can be {@code null}
   * @return executable test plan, never {@code null}
   */
  public static TestPlan createExecutableTestPlan(final TestPlan testPlan, final TestPlan previousTestPlan) {
    final List<AbstractStep> setupStepsToExecute = Lists.newArrayList(Collections2.filter(testPlan.getCompoundSetupStep().getSteps(), input -> input != null
        && isStepToExecute(input, previousTestPlan)));
    return new TestPlan(setupStepsToExecute, testPlan.getCompoundTestStep().getSteps());
  }

  /**
   * Utility method that checks whether the given {@link AbstractStep} should be executed.
   *
   * @param setupStep
   *          the setup step, must not be {@code null}
   * @param previousTestPlan
   *          the previous test plan, must not be {@code null}
   * @return whether the given {@link AbstractStep} should be executed
   */
  private static boolean isStepToExecute(final AbstractStep setupStep, final TestPlan previousTestPlan) {
    if (setupStep instanceof ITestEntityActionProvider) {
      // all ITestEntites must match in order not to execute the test step
      for (final TestEntityAction action : ((ITestEntityActionProvider) setupStep).getTestEntityActions()) {
        if (!previousTestPlan.hasTestEntity(action)) {
          return true;
        }
      }
      return false;
    } // else: steps that don't involve ITestEntities must always be executed
    return true;
  }

  /**
   * Returns the compound setup step.
   *
   * @return the compound setup step, never {@code null}
   */
  public CompoundStep getCompoundSetupStep() {
    return compoundSetupStep;
  }

  /**
   * Returns the compound test step.
   *
   * @return the compound test step, never {@code null}
   */
  public CompoundStep getCompoundTestStep() {
    return compoundTestStep;
  }
}
