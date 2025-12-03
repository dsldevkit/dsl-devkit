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

import com.avaloq.tools.ddk.test.core.BugTest;


/**
 * This {@link InvocationInterceptor} implementation changes the behavior for unresolved bug tests.
 * <p>
 * The behavior for at test that is annotated with {@link BugTest(unresolved=true)} is the following:
 * <ul>
 * <li>Test evaluation <code>OK</code> results in <code>FAIL</code> ({@link AssertionError})</li>
 * <li>Test evaluation <code>FAIL</code> results in <code>OK</code></li>
 * <li>Test evaluation <code>ERROR</code> results in <code>ERROR</code></li>
 * </ul>
 * </p>
 * <p>
 * Example for a bug test:
 *
 * <pre>
 * public class TestClass {
 *
 *   &#064;Rule
 *   public BugTestAwareRule rule = BugTestAwareRule.getInstance();
 *
 *   &#064;org.junit.Test
 *   &#064;com.avaloq.tools.ddk.test.core.BugTest(value = &quot;BUG-42&quot;, unresolved = true)
 *   public void testMethod() {
 *     org.junit.Assert.fail();
 *   }
 * }
 * </pre>
 * </p>
 *
 * @see BugTest
 */
public final class BugTestAwareRule implements InvocationInterceptor {

  private static final String ERROR_TEST_MUST_FAIL = "The unresolved bug test must fail:"; //$NON-NLS-1$
  /** The singleton instance, or {@code null} if not cached. */
  private static BugTestAwareRule instance;
  private static final Object LOCK = new Object();

  /**
   * Creates a new instance of {@link BugTestAwareRule}.
   */
  private BugTestAwareRule() {
    // prevent instantiation
  }

  /**
   * Returns a shared singleton instance.
   *
   * @return a shared instance, never {@code null}
   */
  public static BugTestAwareRule getInstance() {
    synchronized (LOCK) {
      if (instance == null) {
        instance = new BugTestAwareRule();
      }
      return instance;
    }
  }

  @SuppressWarnings("nls")
  @Override
  public void interceptTestMethod(final Invocation<Void> invocation, final ReflectiveInvocationContext<Method> invocationContext, final ExtensionContext extensionContext) throws Throwable {
    BugTest bugTestAnnotation = extensionContext.getRequiredTestMethod().getAnnotation(BugTest.class);
    if (bugTestAnnotation == null && extensionContext.getRequiredTestClass() != null) {
      bugTestAnnotation = extensionContext.getRequiredTestClass().getAnnotation(BugTest.class);
    }
    if (bugTestAnnotation != null && bugTestAnnotation.unresolved()) {
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
