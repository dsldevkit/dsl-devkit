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
package com.avaloq.tools.ddk.test.core;

/**
 * Represents a test entity that can be created, modified or disposed by a test-step and may be used across different tests.
 */
public interface ITestEntity {

  /**
   * Checks whether two {@link ITestEntity}s represent an object with the same characteristics.
   * 
   * @param testEntity
   *          the test entity, must not be {@code null}
   * @return true, if the two {@link ITestEntity}s represent an object with the same characteristics
   */
  boolean matches(ITestEntity testEntity);

  /**
   * Returns whether this {@link ITestEntity} is disposed by a {@link ITestEntityActionProvider} and must therefore not be used anymore.
   * 
   * @return {@code true} if the {@link ITestEntity} was disposed and must not be used anymore
   */
  boolean isDisposed();

  /**
   * Dispose the {@link ITestEntity} and indicate that it must not be used anymore.
   */
  void dispose();

}

