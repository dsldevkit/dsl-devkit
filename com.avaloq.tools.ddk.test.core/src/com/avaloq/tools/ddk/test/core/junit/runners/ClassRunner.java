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
package com.avaloq.tools.ddk.test.core.junit.runners;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.avaloq.tools.ddk.test.core.AfterAll;
import com.avaloq.tools.ddk.test.core.BeforeAll;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.test.core.IntegrationTest;
import com.avaloq.tools.ddk.test.core.ModuleTest;
import com.avaloq.tools.ddk.test.core.MultipleTestProblems;
import com.avaloq.tools.ddk.test.core.PerformanceTest;
import com.avaloq.tools.ddk.test.core.Retry;
import com.avaloq.tools.ddk.test.core.SystemTest;
import com.avaloq.tools.ddk.test.core.UnitTest;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * A JUnit runner extending the {@link BlockJUnit4ClassRunner} with support for @BeforeAll and @AfterAll annotated methods. These methods will be run once for a
 * given test class before the first test method and after the last test method respectively.
 * <p>
 * <h1>Test Methods</h1> Considered are all those methods of the test class that are annotated with one (or more) of the following test annotations:
 * <ul>
 * <li>{@link Test}
 * <li>{@link UnitTest}
 * <li>{@link ModuleTest}
 * <li>{@link IntegrationTest}
 * <li>{@link SystemTest}
 * <li>{@link PerformanceTest}
 * <li>{@link BugTest}
 * </ul>
 * </p>
 * <p>
 * <h1>Execution Order</h1> The order of execution of the test methods is random by default. This can be changed by setting a specific sorter using the property
 * 'com.avaloq.test.sorter'. Available sorters:
 * <ul>
 * <li>alphanumeric
 * </ul>
 * </p>
 * <p>
 * <h1>Multiple Test Runs</h1> The number of runs for each test can be set using the system property {@value #PROPERTY_TEST_RUNS}. By default a test is run
 * exactly once, i.e. the number is set to 1.
 * </p>
 * <p>
 * The runner can be configured to retry a failing test. The system property {@value #PROPERTY_TEST_RETRIES} allows to specify how many times at most a failing
 * test shall be retried. By default a failing test is not retried, i.e. the number is set to 0. <em>Note</em>: Test retries are ignored if a value greater than
 * 1 has been set for {@value #PROPERTY_TEST_RUNS}, in which case the test will be run exactly the specified number of times, regardless of failures.
 * </p>
 * <p>
 * A test which succeeds at least once is regarded as successful. To make a test fail in the case where it failed at least once, set the system property
 * {@value #PROPERTY_UNSTABLE_FAIL} to {@code true} (default: {@code false}).
 * </p>
 */
@SuppressWarnings({"restriction", "deprecation"})
public class ClassRunner extends BlockJUnit4ClassRunner {
  /** The system property for the number of test runs. */
  public static final String PROPERTY_TEST_RUNS = "com.avaloq.test.runs"; //$NON-NLS-1$
  /** The system property for the number of times a failing test shall be retried. */
  public static final String PROPERTY_TEST_RETRIES = "com.avaloq.test.retries"; //$NON-NLS-1$
  /** The system property to specify whether unstable tests shall fail. */
  public static final String PROPERTY_UNSTABLE_FAIL = "com.avaloq.test.unstablefail"; //$NON-NLS-1$
  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(ClassRunner.class);
  private static final List<Class<? extends Annotation>> TEST_ANNOTATIONS = Lists.newArrayList(Test.class, UnitTest.class, ModuleTest.class, IntegrationTest.class, SystemTest.class, PerformanceTest.class, BugTest.class);
  private List<FrameworkMethod> expectedMethods;
  private int currentMethodIndex;
  private final int testRuns;
  private final int testRetries;
  private final boolean unstableFail;
  private Description description;
  private boolean descriptionOutdated = true;

  /**
   * Creates a new test class runner.
   *
   * @param klass
   *          target test class, must not be {@code null}
   * @throws InitializationError
   *           if the runner could not be initialized
   */
  public ClassRunner(final Class<?> klass) throws InitializationError {
    super(klass);
    SorterUtil.getInstance().initializeSorter(this);
    testRuns = Integer.parseInt(System.getProperty(PROPERTY_TEST_RUNS, "1")); //$NON-NLS-1$
    testRetries = Integer.parseInt(System.getProperty(PROPERTY_TEST_RETRIES, "0")); //$NON-NLS-1$
    unstableFail = Boolean.parseBoolean(System.getProperty(PROPERTY_UNSTABLE_FAIL, "false")); //$NON-NLS-1$
  }

  /**
   * Initializes this runner by initializing {@link #expectedMethods} with the list of methods which are expected to be called. This is then also checked by
   * {@link #methodBlock(FrameworkMethod)} and allows identifying the first and last methods correctly.
   */
  private void ensureInitialized() {
    if (expectedMethods == null) {
      try {
        final Method getChildrenMethod = ParentRunner.class.getDeclaredMethod("getFilteredChildren"); //$NON-NLS-1$
        getChildrenMethod.setAccessible(true);
        @SuppressWarnings("unchecked")
        final Collection<FrameworkMethod> testMethods = (Collection<FrameworkMethod>) getChildrenMethod.invoke(this);
        expectedMethods = ImmutableList.copyOf(Iterables.filter(testMethods, new Predicate<FrameworkMethod>() {
          @Override
          public boolean apply(final FrameworkMethod input) {
            return input.getAnnotation(Ignore.class) == null;
          }
        }));
        currentMethodIndex = 0;
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        throw new IllegalStateException(e);
      }
    }
  }

  @Override
  public Description getDescription() {
    if (descriptionOutdated) {
      description = super.getDescription();
      descriptionOutdated = false;
    }
    return description;
  }

  @Override
  protected void validateInstanceMethods(final List<Throwable> errors) {
    validatePublicVoidNoArgMethods(AfterAll.class, false, errors);
    validatePublicVoidNoArgMethods(BeforeAll.class, false, errors);

    super.validateInstanceMethods(errors);
  }

  @Override
  public void sort(final Sorter sorter) {
    super.sort(sorter);
    descriptionOutdated = true;
  }

  @Override
  public void filter(final Filter filter) throws NoTestsRemainException {
    super.filter(filter);
    descriptionOutdated = true;
  }

  @Override
  protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
    ensureInitialized();
    final boolean ignored = method.getAnnotation(Ignore.class) != null;
    if (!ignored) {
      Assert.assertEquals("Method " + method.getName() + " not equal", expectedMethods.get(currentMethodIndex++), method); //$NON-NLS-1$//$NON-NLS-2$
    }
    if (ignored || testRuns == 1 && testRetries == 0 && method.getAnnotation(Retry.class) == null) {
      super.runChild(method, notifier);
    } else {
      runRepeatedly(method, createNotifier(method, notifier));
    }
  }

  /**
   * Runs the test method repeatedly according to the number of runs or retries.
   *
   * @param method
   *          the {@link FrameworkMethod}, must not be {@code null}
   * @param eachNotifier
   *          the {@link EachTestNotifier}, must not be {@code null}
   */
  @SuppressWarnings("PMD.NPathComplexity")
  private void runRepeatedly(final FrameworkMethod method, final EachTestNotifier eachNotifier) {

    final Retry retryAnnotation = method.getAnnotation(Retry.class);
    final int retryAnnotationValue = retryAnnotation != null ? retryAnnotation.value() : 0;
    final int thisTestRetries = Math.max(retryAnnotationValue, testRetries);

    eachNotifier.fireTestStarted();
    try {
      final MultipleTestProblems problems = new MultipleTestProblems();
      final Collection<AssertionError> failures = Lists.newArrayList();
      final Collection<Throwable> errors = Lists.newArrayList();
      int run = 0;
      int succeeded = 0;
      while (run < testRuns || (testRuns == 1 && succeeded == 0 && run < thisTestRetries + 1)) {
        try {
          run++;
          methodBlock(method).evaluate();
          succeeded++;
        } catch (AssumptionViolatedException e) {
          throw e;
        } catch (AssertionError exception) {
          failures.add(exception);
          problems.addProblem(exception);
          // CHECKSTYLE:CHECK-OFF IllegalCatch // we want to catch all possible errors
        } catch (Throwable throwable) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          errors.add(throwable);
          problems.addProblem(throwable);
        }
      }
      final String testCase = getTestClass().getJavaClass().getSimpleName() + '.' + method.getName();
      if (run > 1) {
        logRepeatedTestResult(testCase, run, succeeded, failures.size(), errors.size());
      }
      if (succeeded == 0) {
        problems.assertEmpty();
      }
      if (problems.hasProblems()) {
        if (unstableFail) {
          problems.assertEmpty();
        } else {
          final StringWriter stringWriter = new StringWriter();
          problems.printStackTrace(new PrintWriter(stringWriter));
          LOGGER.info(stringWriter.toString());
        }
      }
    } catch (AssumptionViolatedException e) { // NOPMD ExceptionAsFlowControl
      eachNotifier.addFailedAssumption(e);
      // CHECKSTYLE:CHECK-OFF IllegalCatch // we want to catch all possible errors
    } catch (Throwable e) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      eachNotifier.addFailure(e);
    } finally {
      eachNotifier.fireTestFinished();
    }
  }

  /**
   * Logs the repeated test result.
   *
   * @param testCase
   *          the test case, must not be {@code null}
   * @param runs
   *          the number of runs
   * @param succeeded
   *          the number of succeeded runs
   * @param failures
   *          the number of failed runs
   * @param errors
   *          the number of errored runs
   */
  public void logRepeatedTestResult(final String testCase, final int runs, final int succeeded, final int failures, final int errors) {
    final StringBuilder testResult = new StringBuilder(testCase).append(" Repeated Test Result: "); //$NON-NLS-1$
    if (succeeded == runs) {
      testResult.append("SUCCESS"); //$NON-NLS-1$
    } else if (succeeded == 0) {
      testResult.append("FAILURE"); //$NON-NLS-1$
    } else {
      testResult.append("UNSTABLE"); //$NON-NLS-1$
    }
    testResult.append(" (").append(succeeded).append(" of ").append(runs).append(" succeeded"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    if (failures > 0) {
      testResult.append(", ").append(failures).append(" failed"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (errors > 0) {
      testResult.append(", ").append(errors).append(" errored"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    testResult.append(')');
    LOGGER.info(testResult.toString());
  }

  /**
   * Creates a notifier for each test.
   *
   * @param method
   *          the {@link FrameworkMethod}, must not be {@code null}
   * @param notifier
   *          the {@link RunNotifier}, must not be {@code null}
   * @return an instance of {@link EachTestNotifier}, never {@code null}
   */
  private EachTestNotifier createNotifier(final FrameworkMethod method, final RunNotifier notifier) {
    return new EachTestNotifier(notifier, describeChild(method));
  }

  /**
   * Adds any @BeforeAll methods to be run before the normal @Before annotated methods for the first test method only.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected Statement withBefores(final FrameworkMethod method, final Object target, final Statement stmt) {
    ensureInitialized();
    Statement statement = super.withBefores(method, target, stmt); // NOPMD.CloseResource
    if (method.equals(expectedMethods.get(0))) {
      // reverse BeforeAll method order to get a 'runs top to bottom' order
      final List<FrameworkMethod> befores = Lists.reverse(getTestClass().getAnnotatedMethods(BeforeAll.class));
      statement = befores.isEmpty() ? statement : new RunBefores(statement, befores, target);
    }
    return statement;
  }

  /**
   * Adds any @AfterAll methods to be run after the normal @After annotated methods for the last test method only.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected Statement withAfters(final FrameworkMethod method, final Object target, final Statement stmt) {
    ensureInitialized();
    Statement statement = super.withAfters(method, target, stmt); // NOPMD.CloseResource
    if (method.equals(Iterables.getLast(expectedMethods))) {
      final List<FrameworkMethod> afters = getTestClass().getAnnotatedMethods(AfterAll.class);
      statement = afters.isEmpty() ? statement : new RunAfters(statement, afters, target);
    }
    return statement;
  }

  @Override
  protected List<FrameworkMethod> computeTestMethods() {
    final Collection<FrameworkMethod> result = Sets.newHashSet();
    for (final Class<? extends Annotation> annotationClass : TEST_ANNOTATIONS) {
      result.addAll(getTestClass().getAnnotatedMethods(annotationClass));
    }
    return Lists.newArrayList(result);
  }
}
