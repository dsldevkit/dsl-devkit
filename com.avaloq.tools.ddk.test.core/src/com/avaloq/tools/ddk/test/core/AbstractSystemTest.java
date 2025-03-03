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

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.rules.Verifier;
import org.junit.runner.Description;


/**
 * Abstract base class for system tests.
 */
@SuppressWarnings("restriction")
public abstract class AbstractSystemTest implements TestStepListener {
  // The particular suppress warnings annotation is needed because we WANT to have a unique logger for each of the subclasses
  @SuppressWarnings("PMD.LoggerIsNotStaticFinal")
  private final Logger logger = LogManager.getLogger(getClass());
  private int stepCounter = 1;
  private final MultipleTestProblems multipleTestProblems = new MultipleTestProblems();

  /** The {@link TestPlan}, that holds all setup- and test-steps of the current test. */
  private final TestPlan testPlan = TestPlan.create();

  /** The {@link TestPlan}, that holds all setup- and test-steps, which are actually executed. */
  private TestPlan executedTestPlan = TestPlan.create();

  private static TestPlan previousTestPlan;
  private static boolean lastExecutedTestFailed;
  private static boolean lastExecutedTestWasSystemTest;
  private boolean executingSystemTest;

  /**
   * Indicates the current state of this {@link AbstractSystemTest}.
   */
  private enum TestRunState {
    SETUP,
    TEST,
    TEARDOWN
  }

  private TestRunState testRunState = TestRunState.SETUP;

  /**
   * Returns the logger for this test class.
   *
   * @return the logger for this test class.
   */
  protected Logger log() {
    return logger;
  }

  /**
   * Registers a junit {@link Verifier} rule, which is called after a test passed successfully, and checks additional possible error sources.
   */
  @Rule
  // CHECKSTYLE:OFF
  public Verifier abstractSystemTestVerifier = new Verifier() {
    // CHECKSTYLE:ON
    @Override
    protected void verify() {
      // check if any test problem occurred
      multipleTestProblems.assertEmpty();
    }
  };

  /**
   * Enables support for unresolved bug tests.
   * This declaration must textually be located after the {@code abstractSystemTestVerifier} above.
   * This influences the nesting of rules in the chain and bug test aware rule depends on exceptions thrown by the Verifier to succeed.
   */
  @Rule
  // CHECKSTYLE:OFF
  public BugTestAwareRule bugTestRule = BugTestAwareRule.getInstance();
  // CHECKSTYLE:ON

  @Rule
  // CHECKSTYLE:OFF
  public TestWatcher testWatchman = new TestWatcher() {
    // CHECKSTYLE:ON

    @Override
    public void starting(final Description description) {
      logger.info(description.getMethodName() + " started.");
    }

    @Override
    public void succeeded(final Description description) {
      if (multipleTestProblems.hasProblems()) {
        logger.info(description.getMethodName() + " failed.");
      } else {
        logger.info(description.getMethodName() + " succeeded.");
      }
    }

    @Override
    public void failed(final Throwable e, final Description description) {
      if (e instanceof AssumptionViolatedException) {
        logger.warn(description.getMethodName() + " skipped because of failing assumption: " + e.toString());
      } else {
        logger.warn(description.getMethodName() + " failed.");
      }
    }

    @Override
    public void finished(final Description description) {
    }
  };

  /**
   * Setup of the system test.
   * Implementations need to specify the setup steps in this method and also provide the @Before annotation.
   */
  @Before
  public void setUp() {
    AbstractTestStep.registerTestStepListener(this);
  }

  /**
   * Executes the test plan starting with the setup steps and subsequently executing the test steps.
   */
  protected final void executeTestPlan() {
    cleanUpPreviousTestPlan();
    // If current test is no system Test or first executed test, current test plan does not have to be changed
    if (!executingSystemTest || previousTestPlan == null || !lastExecutedTestWasSystemTest || lastExecutedTestFailed) {
      executedTestPlan = testPlan;
    } else {
      executedTestPlan = TestPlan.createExecutableTestPlan(testPlan, previousTestPlan);
    }
    previousTestPlan = testPlan;
    try {
      AbstractTestStep.setCheckPreconditions(true);
      AbstractTestStep.setCheckPostconditions(true);
      executedTestPlan.getCompoundSetupStep().run();
      AbstractTestStep.setCheckPreconditions(true);
      AbstractTestStep.setCheckPostconditions(true);
      testRunState = TestRunState.TEST;
      executedTestPlan.getCompoundTestStep().run();
      lastExecutedTestFailed = false;
      // CHECKSTYLE:OFF
    } catch (Throwable t) {
      // CHECKSTYLE:ON
      lastExecutedTestFailed = true;
      addTestProblem(t);
    } finally {
      lastExecutedTestWasSystemTest = executingSystemTest;
      testRunState = TestRunState.TEARDOWN; // cannot be put at start of tearDown(), because subclasses may override that method.
    }
  }

  /**
   * Undo the steps of the previous test, that are not needed by the current test.
   */
  private void cleanUpPreviousTestPlan() {
    // No clean up is necessary if one of the following is true:
    // - there was no previous test
    // - the last test failed (then everything is cleaned up in the previous test)
    // - the last test was not a "system test" (then the test cleans up by itself)
    if (previousTestPlan == null || lastExecutedTestFailed || !lastExecutedTestWasSystemTest) {
      return;
    }
    TestPlan undoTestPlan = TestPlan.createUndoTestPlan(testPlan, previousTestPlan, executingSystemTest);
    AbstractTestStep.setCheckPreconditions(true);
    AbstractTestStep.setCheckPostconditions(true);
    try {
      undoTestPlan.getCompoundTestStep().runIgnoreAndContinue();
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (Throwable t) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      // ignore problems during tear down
    }
    try {
      undoTestPlan.getCompoundSetupStep().runIgnoreAndContinue();
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (Throwable t) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      // ignore problems during tear down
    }

  }

  /**
   * Executes a system test plan.
   */
  protected final void executeSystemTestPlan() {
    executingSystemTest = true;
    executeTestPlan();
  }

  /**
   * Cleans up the workbench state after a test.
   * <p>
   * <em>Note:</em> Undoes all test and setup steps in the correct order, if the test is not marked as a System Test.
   * </p>
   */
  @After
  public void tearDown() {
    try {
      AbstractTestStep.setCheckPreconditions(true);
      AbstractTestStep.setCheckPostconditions(true);
      if (!executingSystemTest) {
        try {
          executedTestPlan.getCompoundTestStep().undo();
          // CHECKSTYLE:CHECK-OFF IllegalCatch
        } catch (Throwable t) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          // ignore problems during tear down
        }
        try {
          executedTestPlan.getCompoundSetupStep().undo();
          // CHECKSTYLE:CHECK-OFF IllegalCatch
        } catch (Throwable t) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          // ignore problems during tear down
        }
      } else if (lastExecutedTestFailed) {
        Set<AbstractStep> filter = executedTestPlan.getAllExecutedSteps();
        filter.addAll(TestPlan.getAllStepsWithPreExistingTestEntities(previousTestPlan, testPlan));
        TestPlan undoTestPlan = TestPlan.createUndoStepsTestPlan(TestPlan.createReverseTestPlan(TestPlan.createFilteredTestPlan(testPlan, filter)));
        try {
          undoTestPlan.getCompoundTestStep().runIgnoreAndContinue();
          // CHECKSTYLE:CHECK-OFF IllegalCatch
        } catch (Throwable t) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          // ignore problems during tear down
        }
        try {
          undoTestPlan.getCompoundSetupStep().runIgnoreAndContinue();
          // CHECKSTYLE:CHECK-OFF IllegalCatch
        } catch (Throwable t) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          // ignore problems during tear down
        }
      }
    } finally {
      AbstractTestStep.removeTestStepListener(this);
    }
  }

  /**
   * Adds a step as setup step. Setup steps are run before the test starts. Therefore, it is not possible to add a setup step after adding a test step.
   *
   * @param <T>
   *          the type of the {@link AbstractStep}
   * @param setupStep
   *          the step to append to the list of setup steps
   * @return the added {@link AbstractStep}
   */
  protected <T extends AbstractStep> T addSetupStep(final T setupStep) {
    return testPlan.addSetupStep(setupStep);
  }

  /**
   * Adds a step as test step.
   *
   * @param <T>
   *          the type of the {@link AbstractStep}
   * @param testStep
   *          the step to append to the list of test steps
   * @return the added {@link AbstractStep}
   */
  protected <T extends AbstractStep> T addTestStep(final T testStep) {
    return testPlan.addTestStep(testStep);
  }

  /**
   * Adds a new test problem.
   *
   * @param problem
   *          the new {@link Throwable} to add
   */
  protected void addTestProblem(final Throwable problem) {
    multipleTestProblems.addProblem(problem);
    logger.error("Error: " + problem.getLocalizedMessage());
  }

  /**
   * Adds a new test problem with a message.
   *
   * @param message
   *          the message of the problem
   */
  protected void addTestProblem(final String message) {
    addTestProblem(new AssertionError(message));
  }

  /**
   * Adds new test problems.
   *
   * @param problems
   *          the new {@link Throwable}s to add
   */
  protected void addTestProblems(final List<Throwable> problems) {
    for (Throwable problem : problems) {
      addTestProblem(problem);
    }
  }

  @Override
  public void stepStateChanged(final AbstractTestStep testStep, final TestStepState testStepState, final Throwable throwable) {
    switch (testStepState) {
    case START:
      switch (testRunState) {
      case SETUP:
        logger.info("Setup " + stepCounter++ + ": " + testStep.getName());
        break;
      case TEST:
        logger.info("Test " + stepCounter++ + ": " + testStep.getName());
        break;
      case TEARDOWN:
        logger.info("Teardown: " + testStep.getName());
        break;
      }
      break;
    case ERRORED:
      logger.error("ERRORED", throwable);
      break;
    case FAILED:
      logger.error("FAILED", throwable);
      break;
    default:
      break;
    }
  }

}
