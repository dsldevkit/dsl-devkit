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
package com.avaloq.tools.ddk.test.ui.junit.runners;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.avaloq.tools.ddk.test.core.AbstractSystemTest;
import com.avaloq.tools.ddk.test.core.junit.runners.ClassRunner;
import com.avaloq.tools.ddk.test.ui.swtbot.CoreSwtbotTools;


/**
 * A {@link org.junit.runner.Runner} to use with swt bot tests featuring screenshot recording.
 */
// suppress warning restriction of org.junit.internal.runners.statements.InvokeMethod
@SuppressWarnings("restriction")
public class SwtBotRecordingTestRunner extends ClassRunner {
  /**
   * Used to mark a test class or method to define the recording interval in milliseconds to use for the {@link SwtBotRecordingTestRunner}.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.TYPE})
  public static @interface RecordingInterval {
    /**
     * Returns the recording interval value in milliseconds.
     *
     * @return the recording interval value in milliseconds
     */
    long value();
  }

  private final TestRunRecording testRunRecording;

  /**
   * Creates a new instance of {@link SwtBotRecordingTestRunner}.
   * Only called reflectively. Do not use programmatically.
   *
   * @param testClass
   *          the test class to run
   * @throws InitializationError
   *           if any initialization failed
   */
  public SwtBotRecordingTestRunner(final Class<? extends AbstractSystemTest> testClass) throws InitializationError {
    super(testClass);
    CoreSwtbotTools.initializePreferences();
    testRunRecording = new TestRunRecording(getTestClass().getJavaClass(), SWTBotPreferences.SCREENSHOTS_DIR);
    // TODO: add custom made {@link org.junit.runners.model.Statement} to check for exceptions during the test
    // (by default test failures are reported only after the teardown has taken place)
  }

  @Override
  public void run(final RunNotifier notifier) {
    try {
      notifier.removeListener(testRunRecording); // remove existing listeners that could be added by suite or class runners
      notifier.addListener(testRunRecording);
      super.run(notifier);
    } finally {
      notifier.removeListener(testRunRecording);
    }
  }

  @Override
  protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
    testRunRecording.setRecordingInterval(getRecordingInterval(method));
    super.runChild(method, notifier);
  }

  @Override
  protected Statement methodInvoker(final FrameworkMethod method, final Object test) {
    return new InvokeMethod(method, test) {
      @Override
      // CHECKSTYLE:CHECK-OFF IllegalThrow // inherited JUnit throw style
      public void evaluate() throws Throwable {
        // CHECKSTYLE:CHECK-ON IllegalThrow
        try {
          super.evaluate();
          // CHECKSTYLE:CHECK-OFF IllegalCatch // catching in order to act upon but then throwing the exception again
        } catch (final Throwable throwable) {
          // CHECKSTYLE:CHECK-ON IllegalCatch
          testRunRecording.methodInvokeFailure(throwable);
          throw throwable;
        }
      }
    };
  }

  /**
   * Computes the capture interval to use for recording the given test method.
   * Checks first the annotation {@link RecordingInterval} of the method,
   * then the annotations of the test class.
   * Falls back to the default setting if no annotations were found.
   *
   * @param method
   *          the method under test
   * @return the framerate to use for recording the given test method
   */
  private long getRecordingInterval(final FrameworkMethod method) {
    RecordingInterval recordingInterval = method.getAnnotation(RecordingInterval.class);
    if (recordingInterval != null) {
      return recordingInterval.value();
    }
    for (Annotation annotation : getTestClass().getAnnotations()) {
      if (annotation.annotationType().equals(RecordingInterval.class)) {
        return ((RecordingInterval) annotation).value();
      }
    }
    return TestRunRecording.DEFAULT_RECORDING_INTERVAL;
  }
}
