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

/**
 * Manages information about a test step.
 */
public class TestStepInformation {
  /** The identifier of the test step this information is associated with. */
  private final String testStepIdentifier;
  /** Counter for how many times this step has been executed. */
  private int executeCount;

  protected TestStepInformation(final String testStepIdentifier) {
    this.testStepIdentifier = testStepIdentifier;
  }

  /**
   * Returns the number of times the test step has been executed.
   * 
   * @return the number of times the test step has been executed
   */
  public int getExecuteCount() {
    return executeCount;
  }

  /**
   * Returns the identifier of the test step this information is associated with.
   * 
   * @return the identifier of the test step this information is associated with
   */
  public String getTestStepIdentifier() {
    return testStepIdentifier;
  }

  /**
   * Increments the execute count.
   */
  final void incrementExecuteCount() {
    executeCount++;
  }
}
