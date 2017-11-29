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
package com.avaloq.tools.ddk.xtext.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.test.core.AfterAll;
import com.avaloq.tools.ddk.test.core.BeforeAll;
import com.avaloq.tools.ddk.test.core.BugTestAwareRule;
import com.avaloq.tools.ddk.test.core.IssueAwareRule;
import com.avaloq.tools.ddk.test.core.LoggingRule;
import com.avaloq.tools.ddk.test.core.junit.runners.ClassRunner;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.test.core.mock.ServiceMock;
import com.google.common.collect.ImmutableList;


/**
 * Provides a test class specific custom test framework for tests that run in the ACF environment.
 * All exceptions are wrapped and handed over to the JUnit framework.
 */
@RunWith(ClassRunner.class)
public abstract class AbstractTest {

  /**
   * Prefix for customer sources.
   * Consider also: com.avaloq.tools.asmd.testbase.TestUtil.CUSTR_PREFIX
   * The duplicated definition of the prefix must be harmonized based on harmonization of test plugins.
   */
  protected static final String CUSTOMER_SOURCE_PREFIX = "custr_";

  protected static final String PROJECT_NAME = "SDK";

  private static final String HIGH_LATENCY_PROPERTY = "com.avaloq.tools.hl.supported";

  private static Map<Class<? extends AbstractTest>, TestInformation> testInformationMap = new HashMap<Class<? extends AbstractTest>, TestInformation>();

  @Rule
  // CHECKSTYLE:CHECK-OFF Visibility MethodRules cannot be private
  public final LoggingRule watchman = LoggingRule.getInstance();
  // CHECKSTYLE:CHECK-ON Visibility

  /**
   * Enables support for unresolved bug tests.
   */
  @Rule
  // CHECKSTYLE:CHECK-OFF Visibility MethodRules cannot be private
  public BugTestAwareRule bugTestRule = BugTestAwareRule.getInstance();
  // CHECKSTYLE:CHECK-ON Visibility

  @Rule
  // CHECKSTYLE:CHECK-OFF VisibilityModifier
  public final IssueAwareRule issueRule = IssueAwareRule.getInstance();

  // CHECKSTYLE:CHECK-ON VisibilityModifier

  protected ITestProjectManager getTestProjectManager() {
    return getTestUtil().getTestProjectManager();
  }

  /**
   * Returns the URI for the given target source file.
   *
   * @param fullSourceName
   *          full source name
   * @return URI of source
   */
  public URI getTargetSourceUri(final String fullSourceName) {
    return getTestProjectManager().createTestSourceUri(fullSourceName);
  }

  /**
   * Returns a list of all kernel source file names that this test will require.
   * This can be overridden to extend or replace the returned list.
   *
   * @return list of all required kernel source file names
   */
  protected List<String> getRequiredSourceFileNames() {
    List<String> requiredSources = new LinkedList<String>();
    String testSourceFileName = getTestSourceFileName();
    if (testSourceFileName != null && testSourceFileName.length() > 0) {
      requiredSources.add(testSourceFileName);
    }
    return requiredSources;
  }

  /**
   * Registers all required sources for this test.
   * This method can be overridden to register other required source files.
   */
  protected void registerRequiredSources() {
    addSourcesToWorkspace(getRequiredSourceFileNames());
  }

  /**
   * Non-static instance set up before all tests.
   */
  @BeforeAll
  public final void setUp() {
    synchronized (testInformationMap) {
      if (!testInformationMap.containsKey(this.getClass())) {
        testInformationMap.put(this.getClass(), new TestInformation());
        beforeAllTests();
      }
    }
  }

  /**
   * Non-static instance tear down after all tests.
   */
  @AfterAll
  public final void tearDown() {
    synchronized (testInformationMap) {
      afterAllTests();
      ExtensionRegistryMock.assertUnMocked();
      ServiceMock.assertAllMocksRemoved();
      testInformationMap.remove(this.getClass());
    }
  }

  /**
   * This method prepares the test environment for a test. It is called by the JUnit framework before each test.
   * If it is run the first time, it calls the beforeClass method first. Do not call this method manually!
   * All exceptions are wrapped and handed over to the JUnit framework.
   */
  @Before
  public final void before() {
    beforeEachTest();
  }

  /**
   * This method cleans up the test environment after a test. It is called by the JUnit framework after each test.
   * If no more tests are to be run, it calls the afterClass method. Do not call this method manually!
   * All exceptions are wrapped and handed over to the JUnit framework.
   */
  @After
  public final void after() {
    afterEachTest();
  }

  /**
   * Prepares the test class after the test class has been instantiated. This method can be used to setup the test class before any test is run.
   * Do not use JUnit annotation.
   * Exceptions are wrapped and handed over to the JUnit framework.
   */
  protected void beforeAllTests() {
    // check method annotations to ensure test framework policies
    System.setProperty(HIGH_LATENCY_PROPERTY, Boolean.FALSE.toString());
    enforceAnnotationPolicies();
    getTestProjectManager().setup(ImmutableList.<TestSource> of());
    registerRequiredSources();
    getTestProjectManager().build();
  }

  /**
   * After the last task has run but before the test class instance has been garbage collected, this method is called to clean up the test environment.
   * Do not use JUnit annotation.
   * All exceptions are wrapped and handed over to the JUnit framework.
   */
  protected void afterAllTests() {
    getTestProjectManager().teardown();
    System.setProperty(HIGH_LATENCY_PROPERTY, Boolean.TRUE.toString());
  }

  /**
   * Prepares for the next test. This method can be used to (re-)initialize the test environment before a (next) test is run. Resource allocations must be dealt
   * with in {@link afterEachTest}.
   * Do not use JUnit annotation.
   * All exceptions are wrapped and handed over to the JUnit framework.
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  protected void beforeEachTest() {
    // empty
  }

  /**
   * Called after each test to clean up initializations done in {@link beforeEachTest}.
   * Do not use JUnit annotation.
   * All exceptions are wrapped and handed over to the JUnit framework.
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  protected void afterEachTest() {
    // empty
  }

  /**
   * Registers a source that is required by the test class.
   * The source will be removed from the system when {@link afterAllTests} is called.
   * All exceptions are wrapped and handed over to the JUnit framework.
   *
   * @param sourceFileName
   *          the name of the file where the source is located, and where the content of the source shall be written to. This is the source name available
   *          during the test.
   */
  protected void addSourceToWorkspace(final String sourceFileName) {
    addSourceToWorkspace(sourceFileName, getResourceContent(sourceFileName));
  }

  /**
   * Registers a kernel source that is required by the test class.
   * The source will be removed from the system when {@link afterAllTests} is called.
   * All exceptions are wrapped and handed over to the JUnit framework.
   *
   * @param sourceFileName
   *          the name of the file where the content of the source shall be written to. This is the source name available
   *          during the test.
   * @param sourceContent
   *          the content of the source that shall be written to the file in workspace.
   */
  protected void addKernelSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    addSourceToWorkspace(sourceFileName, sourceContent.toString());
  }

  /**
   * Registers a customer source that is required by the test class.
   * The source will be removed from the system when {@link afterAllTests} is called.
   * All exceptions are wrapped and handed over to the JUnit framework.
   *
   * @param sourceFileName
   *          the name of the file where the content of the source shall be written to. This is the source name available
   *          during the test.
   * @param sourceContent
   *          the content of the source that shall be written to the file in workspace.
   */
  protected void addCustomerSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    addSourceToWorkspace(CUSTOMER_SOURCE_PREFIX.concat(sourceFileName), sourceContent.toString());
  }

  /**
   * Registers a source that is required by the test class.
   * The source will be removed from the system when {@link afterAllTests} is called.
   * All exceptions are wrapped and handed over to the JUnit framework.
   *
   * @param sourceFileName
   *          the name of the file where the content of the source shall be written to. This is the source name available
   *          during the test.
   * @param sourceContent
   *          the content of the source that shall be written to the file in workspace.
   */
  private void addSourceToWorkspace(final String sourceFileName, final String sourceContent) {
    createTestSource(sourceFileName, sourceContent);
  }

  /**
   * Returns the string contents of the loaded resource with the given name.
   *
   * @param sourceFileName
   *          the file name
   * @return the string contents of the loaded resource
   */
  protected String getResourceContent(final String sourceFileName) {
    return TestSource.getResourceContent(this.getClass(), sourceFileName);
  }

  /**
   * Registers a set of sources that is required by the test class.
   * The sources will be removed from the system when {@link afterAllTests} is called.
   * All exceptions are wrapped and handed over to the JUnit framework.
   *
   * @param sourceFileNames
   *          the names of the files where the sources are located, and where the content of the sources shall be written to.
   */
  private void addSourcesToWorkspace(final List<String> sourceFileNames) {
    try {
      new WorkspaceModifyOperation() {
        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
          for (int i = 0; i < sourceFileNames.size(); i++) {
            addSourceToWorkspace(sourceFileNames.get(i));
          }
        }
      }.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      throw new WrappedException("failed adding sources to workspace", e);
    } catch (InterruptedException e) {
      throw new WrappedException("adding sources to workspace interrupted", e);
    }
  }

  protected Collection<? extends TestSource> getTestSources() {
    return getTestProjectManager().getTestSources();
  }

  /**
   * Returns the kernel {@link TestSource} for the given sourceFileName.
   *
   * @param sourceFileName
   *          the file name of the {@link TestSource}
   * @return the {@link TestSource} for the given sourceFileName
   */
  protected XtextTestSource getTestSource(final String sourceFileName) {
    return (XtextTestSource) getTestProjectManager().getTestSource(sourceFileName);
  }

  /**
   * Returns the kernel {@link TestSource} for this test class.
   *
   * @return the {@link TestSource} for this test class
   */
  protected TestSource getTestSource() {
    TestSource testSource = getTestProjectManager().getTestSource(getTestSourceFileName());
    // Assert.assertNotNull("TestSource for this test class can be found: " + getTestSourceFileName(), testSource);
    return testSource;
  }

  /**
   * Get the name of the main test source file.
   *
   * @return the file name of the main test source file
   */
  protected abstract String getTestSourceFileName();

  /**
   * The default implementation returns the name of the test class for the model name of the test source.
   * A test class needs to override this, if the name of the main test source model differs from the default.
   *
   * @return the name of the main test source model
   */
  protected String getTestSourceModelName() {
    return this.getClass().getSimpleName();
  }

  /**
   * Goes through all methods of this object and checks the annotations.
   * <p>
   * Methods with a {@link @BeforeClass} or {@link @AfterClass} annotation will cause an exception to be thrown. For that purpose, use the {@link
   * beforeAllTests()} and {@link afterAllTests()} methods only.
   * </p>
   */
  private void enforceAnnotationPolicies() {
    for (Method method : this.getClass().getMethods()) {
      // use this policy to not allow BeforeClass or AfterClass annotations.
      if (method.isAnnotationPresent(BeforeClass.class) || method.isAnnotationPresent(AfterClass.class)) {
        throw new IllegalJUnitAnnotation();
      }
      // use this policy to not allow Before or After annotations in subclasses.
      // if (!method.getDeclaringClass().equals(AbstractXtextTest.class) && (method.isAnnotationPresent(Before.class) ||
      // method.isAnnotationPresent(After.class))) {
      // throw new
      // IllegalJUnitAnnotation("Invalid annotation found. Before and After annotations are not permitted when using the AbstractXtextTest framework. Override
      // the methods 'before' and 'after' instead.");
      // }
    }
  }

  /**
   * Wait for validation jobs to finish.
   */
  protected void waitForValidation() {
    waitForJobsOfFamily(org.eclipse.xtext.ui.editor.validation.ValidationJob.XTEXT_VALIDATION_FAMILY);
  }

  /**
   * Wait for jobs of a given family to finish.
   *
   * @param family
   *          to wait for.
   */
  protected void waitForJobsOfFamily(final Object family) {
    getTestUtil().waitForJobsOfFamily(family);
  }

  /**
   * Wait for synchronization jobs on opening/closing the editor.
   *
   * @param editor
   *          editor part
   */
  protected void waitForEditorJobs(final IEditorPart editor) {
    getTestUtil().waitForEditorJobs(editor);
  }

  /**
   * Wait for jobs of a given family to appear. A {@code null} family will
   * cause this to wait for any job.
   *
   * @param family
   *          to wait for, may be {@code null}
   * @param timeout
   *          ms to wait for.
   */
  protected void waitForJobOfFamilyToAppear(final Object family, final long timeout) {
    final long timeLimit = System.currentTimeMillis() + timeout;
    do {
      if (Job.getJobManager().find(family).length > 0) {
        return;
      }
    } while (System.currentTimeMillis() < timeLimit);
  }

  /**
   * Returns the test information for the current test class.
   *
   * @return information for the current test class
   */
  protected TestInformation getTestInformation() {
    synchronized (testInformationMap) {
      return testInformationMap.get(this.getClass());
    }
  }

  /**
   * Create a test source for testing from an existing file.
   *
   * @param sourceFileName
   *          file name for source
   * @param content
   *          content of source
   * @return a new {@link TestSource} with the given parameters
   */

  protected TestSource createTestSource(final String sourceFileName, final String content) {
    TestSource testSource = new TestSource(sourceFileName, content);
    getTestProjectManager().addSourceToProject(testSource);
    return testSource;
  }

  /**
   * Get the test class utility for this test. The minimum functionality is given by
   * AbstractTestUtil, which does not require that any methods be overridden. Tests
   * that require more than this minimal functionality must override this method.
   * <p>
   * This method is expected to always return the same instance, even when invoked on different instances of the test class. This is because the associated
   * {@link ITestProjectManager} is stateful and required by {@link #beforeAllTests()}, {@link #afterAllTests()}, and {@link #getTestSources()}.
   *
   * @return the test class utility for this test.
   */
  protected abstract AbstractTestUtil getTestUtil();

}
