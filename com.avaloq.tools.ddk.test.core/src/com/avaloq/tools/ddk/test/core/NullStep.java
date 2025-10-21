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
 * A step that does nothing.
 */
public final class NullStep extends AbstractStep {
  public static final NullStep INSTANCE = new NullStep();

  private NullStep() {
  }

  @Override
  public String getDescription() {
    return "Does nothing."; //$NON-NLS-1$
  }

  @Override
  public void run() {
    // do nothing
  }

  @Override
  public void undo() {
    // do nothing
  }

  @Override
  public AbstractStep getUndoStep() {
    return INSTANCE;
  }
}
