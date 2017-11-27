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

import java.util.HashMap;
import java.util.Map;


/**
 * Stores relevant information for a test class.
 */
public class TestInformation {
  private int remainingTestCount;
  private final Map<Class<?>, Object> testObjects = new HashMap<Class<?>, Object>();

  /**
   * Set the initial test count.
   * 
   * @param testCount
   *          the initial test count
   */
  void setInitialTestCount(final int testCount) {
    remainingTestCount = testCount;
  }

  int getRemainingTestCount() {
    return remainingTestCount;
  }

  /**
   * Decrement the remaining test count.
   */
  void decRemainingTestCount() {
    remainingTestCount--;
  }

  /**
   * Add an object that containes test relevant information that are required over multiple tests.
   * The object is accessible by the specified class.
   * 
   * @param objectClass
   *          the class of the test object
   * @param object
   *          the test object
   */
  public void putTestObject(final Class<?> objectClass, final Object object) {
    testObjects.put(objectClass, object);
  }

  /**
   * Retrieve a test object by specifying its class.
   * 
   * @param objectClass
   *          the class of the test object to get.
   * @return the test object with the specified class
   */
  public Object getTestObject(final Class<?> objectClass) {
    return testObjects.get(objectClass);
  }
}

