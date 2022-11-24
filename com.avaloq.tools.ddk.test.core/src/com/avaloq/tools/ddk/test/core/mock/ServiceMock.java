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
package com.avaloq.tools.ddk.test.core.mock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;


/**
 * Provides mocking framework for services.
 */
public final class ServiceMock {
  /** Originally registered services, replaced by a mock and restored in the end. */
  private static Map<Class<?>, Object> originalServices = new HashMap<Class<?>, Object>();

  public static Map<Class<?>, Object> getOriginalServices() {
    return originalServices;
  }

  /**
   * Check if service is mocked.
   *
   * @param <T>
   *          Service class type
   * @param classToCheck
   *          Service Class to check if is mocked.
   * @return TRUE if mocked, FALSE otherwise.
   */
  public static <T> boolean isMocked(final Class<T> classToCheck) {
    return originalServices.containsKey(classToCheck);
  }

  /**
   * Asserts that all mocked services are unmocked.
   */
  public static void assertAllMocksRemoved() {
    if (!originalServices.keySet().isEmpty()) {
      Iterator<Class<?>> iterator = originalServices.keySet().iterator();
      while (iterator.hasNext()) {
        Class<?> clazz = iterator.next();
        Assert.fail("Service " + clazz.getName() + " is still mocked."); //$NON-NLS-1$//$NON-NLS-2$
      }
    }
  }

  private ServiceMock() {}
}
