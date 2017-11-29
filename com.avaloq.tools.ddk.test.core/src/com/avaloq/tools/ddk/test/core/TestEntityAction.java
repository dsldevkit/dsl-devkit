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

import org.eclipse.core.runtime.Assert;


/**
 * Represents an action a test performs on a {@link ITestEntity}.
 */
public class TestEntityAction {

  // Argument names
  private static final String ENTITY_ACTION_ARGUMENT = "entityAction";
  private static final String TEST_ENTITY_ARGUMENT = "testEntity";

  private final ITestEntity testEntity;
  private final TestEntityOperation operation;

  /**
   * Creates an instance of {@link TestEntityAction}.
   *
   * @param testEntity
   *          the test entity, must not be {@code null}
   * @param entityAction
   *          the entity action, must not be {@code null}
   */
  public TestEntityAction(final ITestEntity testEntity, final TestEntityOperation entityAction) {
    Assert.isNotNull(testEntity, TEST_ENTITY_ARGUMENT);
    Assert.isNotNull(entityAction, ENTITY_ACTION_ARGUMENT);
    if (entityAction.equals(TestEntityOperation.DISPOSE)) {
      testEntity.dispose();
    }
    this.testEntity = testEntity;
    this.operation = entityAction;

  }

  /**
   * Contains the actions that can be performed on a {@link ITestEntity}.
   */
  public enum TestEntityOperation {
    NONE,
    CREATE,
    DISPOSE
  }

  /**
   * Checks if the {@link ITestEntity} of this {@link TestEntityAction} matches the one of the given {@link TestEntityAction}.
   *
   * @param testEntityAction
   *          the test entity action, must not be {@code null}
   * @return true, if the {@link ITestEntity}s of the two {@link TestEntityAction}s match
   */
  public boolean hasSameTestEntity(final TestEntityAction testEntityAction) {
    return getTestEntity().matches(testEntityAction.getTestEntity());
  }

  /**
   * Gets the {@link ITestEntity} of this {@link TestEntityAction}.
   *
   * @return the test entity, never {@code null}
   */
  public ITestEntity getTestEntity() {
    return testEntity;
  }

  /**
   * Gets the {@link TestEntityOperation} of this {@link TestEntityAction}.
   *
   * @return the operation
   */
  public TestEntityOperation getOperation() {
    return operation;
  }

}

