/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.jupiter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor.Invocation;
import org.junit.jupiter.api.function.Executable;

import com.avaloq.tools.ddk.test.core.Issue;
import com.avaloq.tools.ddk.test.core.jupiter.IssueAwareRule;


/**
 * Tests that {@link IssueAwareRule} inverts the result of tests for not fixed issues only.
 */
@SuppressWarnings("nls")
public class IssueAwareRuleTest {

  private static final String NOT_FIXED = "notFixed";
  private static final String FIXED = "fixed";

  private static final Invocation<Void> PASSING = () -> null;
  private static final Invocation<Void> FAILING = () -> {
    throw new AssertionError("test failure");
  };
  private static final Invocation<Void> ERRONEOUS = () -> {
    throw new IllegalStateException("test error");
  };

  /**
   * Declares the annotated methods the rule reads.
   */
  private interface Fixtures {
    @Issue(value = "ISSUE-42", fixed = false)
    void notFixed();

    @Issue("ISSUE-42")
    void fixed();
  }

  /**
   * Creates the call of the rule for a test with the annotation of the given fixture.
   *
   * @param fixture
   *          the name of the {@link Fixtures} method, must not be {@code null}
   * @param test
   *          the test the rule intercepts, must not be {@code null}
   * @return the call of the rule, never {@code null}
   * @throws NoSuchMethodException
   *           if the fixture does not exist
   */
  private static Executable intercept(final String fixture, final Invocation<Void> test) throws NoSuchMethodException {
    ExtensionContext context = mock(ExtensionContext.class);
    when(context.getRequiredTestMethod()).thenReturn(Fixtures.class.getMethod(fixture));
    doReturn(Fixtures.class).when(context).getRequiredTestClass();
    return () -> IssueAwareRule.getInstance().interceptTestMethod(test, null, context);
  }

  @Test
  public void testNotFixedIssueWithFailingTestPasses() throws NoSuchMethodException {
    assertDoesNotThrow(intercept(NOT_FIXED, FAILING));
  }

  @Test
  public void testNotFixedIssueWithPassingTestFails() throws NoSuchMethodException {
    assertThrows(AssertionError.class, intercept(NOT_FIXED, PASSING));
  }

  @Test
  public void testNotFixedIssueWithErroneousTestReportsError() throws NoSuchMethodException {
    assertThrows(IllegalStateException.class, intercept(NOT_FIXED, ERRONEOUS));
  }

  @Test
  public void testFixedIssueWithPassingTestPasses() throws NoSuchMethodException {
    assertDoesNotThrow(intercept(FIXED, PASSING));
  }

  @Test
  public void testFixedIssueWithFailingTestFails() throws NoSuchMethodException {
    assertThrows(AssertionError.class, intercept(FIXED, FAILING));
  }
}
