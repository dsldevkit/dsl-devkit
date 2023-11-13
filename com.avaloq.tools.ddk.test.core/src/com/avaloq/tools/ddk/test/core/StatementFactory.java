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

import org.junit.runner.Description;
import org.junit.runners.model.Statement;


/**
 * A factory for creating {@link Statement}s.
 */
public final class StatementFactory {

  /**
   * Instantiates a new statement factory.
   */
  private StatementFactory() {
    // Empty constructor to avoid instantiation.
  }

  /**
   * Creates a {@link Statement} object which inverts the behaviour of {@code base}.
   * <p>
   * If the {@code base} statement throws an {@link AssertionError}, the returned statement will catch it and succeed nevertheless. If the {@code base}
   * statement succeeds, the returned statement will throw an {@link AssertionError} with the custom error message {@code errorMessage}.
   * </p>
   * 
   * @param errorMessage
   *          the error message for the new {@link AssertionError}, must not be {@code null}
   * @param base
   *          the base statement which shall be inverted, must not be {@code null}
   * @param description
   *          the description of the test that will be run, must not be {@code null}
   * @return the inverted statement, never {@code null}
   */
  public static Statement createResultInvertingStatement(final String errorMessage, final Statement base, final Description description) {
    return new Statement() {
      @Override
      // CHECKSTYLE:CHECK-OFF IllegalThrowsCheck
      public void evaluate() throws Throwable {
        // CHECKSTYLE:CHECK-ON IllegalThrowsCheck
        try {
          base.evaluate();
        } catch (AssertionError exception) {
          return;
        }
        String testCase = description.getClassName() + "." + description.getMethodName();
        throw new AssertionError(errorMessage + " " + testCase);
      }
    };
  }
}
