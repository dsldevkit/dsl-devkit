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

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * This {@link TestRule} implementation changes the behavior for unresolved bug tests.
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
 * 
 * </p>
 * 
 * @see BugTest
 */
public final class BugTestAwareRule implements TestRule {

  private static final String ERROR_TEST_MUST_FAIL = "The unresolved bug test must fail:";
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

  
  @Override
  public Statement apply(final Statement base, final Description description) {
    BugTest bugTestAnnotation = description.getAnnotation(BugTest.class);
    if (bugTestAnnotation == null && description.getTestClass() != null) {
      bugTestAnnotation = description.getTestClass().getAnnotation(BugTest.class);
    }
    if (bugTestAnnotation != null && bugTestAnnotation.unresolved()) {
      return StatementFactory.createResultInvertingStatement(ERROR_TEST_MUST_FAIL, base, description);

    } else {
      return base;
    }
  }
}

