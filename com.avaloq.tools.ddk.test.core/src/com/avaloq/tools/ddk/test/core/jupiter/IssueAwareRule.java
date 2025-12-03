/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.test.core.jupiter;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import com.avaloq.tools.ddk.test.core.Issue;


/**
 * This {@link InvocationInterceptor} implementation changes the behavior for not fixed issues.
 * <p>
 * The behavior for at test that is annotated with {@link Issue(fixed = false)} is the following:
 * <ul>
 * <li>Test evaluation <code>OK</code> results in <code>FAIL</code> ({@link AssertionError})</li>
 * <li>Test evaluation <code>FAIL</code> results in <code>OK</code></li>
 * <li>Test evaluation <code>ERROR</code> results in <code>ERROR</code></li>
 * </ul>
 * </p>
 * <p>
 * Example for a issue test:
 *
 * <pre>
 * public class TestClass {
 *
 *   &#064;Rule
 *   public IssueAwareRule rule = IssueAwareRule.getInstance();
 *
 *   &#064;org.junit.Test
 *   &#064;com.avaloq.tools.ddk.test.core.Issue(value = &quot;ISSUE-42&quot;, fixed = false)
 *   public void testMethod() {
 *     org.junit.Assert.fail();
 *   }
 * }
 * </pre>
 * </p>
 *
 * @see Issue
 */
public final class IssueAwareRule implements InvocationInterceptor {

  private static final String ERROR_TEST_MUST_FAIL = "The issue test for a not fixed issue must fail:"; //$NON-NLS-1$
  /** The singleton instance, or {@code null} if not cached. */
  private static IssueAwareRule instance;
  private static Object lock = new Object();

  /**
   * Creates a new instance of {@link IssueAwareRule}.
   */
  private IssueAwareRule() {
    // prevent instantiation
  }

  /**
   * Returns a shared singleton instance.
   *
   * @return a shared instance, never {@code null}
   */
  public static IssueAwareRule getInstance() {
    synchronized (lock) {
      if (instance == null) {
        instance = new IssueAwareRule();
      }
      return instance;
    }
  }

  @SuppressWarnings("nls")
  @Override
  public void interceptTestMethod(final Invocation<Void> invocation, final ReflectiveInvocationContext<Method> invocationContext, final ExtensionContext extensionContext) throws Throwable {
    Issue issueAnnotation = extensionContext.getRequiredTestMethod().getAnnotation(Issue.class);
    if (issueAnnotation == null && extensionContext.getRequiredTestClass() != null) {
      issueAnnotation = extensionContext.getRequiredTestClass().getAnnotation(Issue.class);
    }
    if (issueAnnotation != null && issueAnnotation.fixed()) {
      try {
        invocation.proceed();
      } catch (AssertionError exception) {
        return;
      }
      String testCase = extensionContext.getRequiredTestClass().getSimpleName() + "." + extensionContext.getRequiredTestMethod().getName();
      throw new AssertionError(ERROR_TEST_MUST_FAIL + " " + testCase);
    } else {
      invocation.proceed();
    }
  }
}
