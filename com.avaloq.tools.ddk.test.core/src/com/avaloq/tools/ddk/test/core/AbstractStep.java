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
 * An abstract step.
 */
public abstract class AbstractStep {
  /**
   * Returns the unique identifier for this step.
   * 
   * @return the unique identifier for this step
   */
  public final String getIdentifier() {
    return this.getClass().getCanonicalName();
  }

  /**
   * Returns the name of this step.
   * 
   * @return the name of this step
   */
  public String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * Returns a description of this step.
   * 
   * @return the description of this step
   */
  public abstract String getDescription();

  /**
   * Runs this step.
   */
  public abstract void run();

  /**
   * Undoes this step.
   */
  public void undo() {
    getUndoStep().run();
  }

  /**
   * Returns the undo step for this step.
   * 
   * @return the undo step for this step (never null)
   */
  public abstract AbstractStep getUndoStep();
}
