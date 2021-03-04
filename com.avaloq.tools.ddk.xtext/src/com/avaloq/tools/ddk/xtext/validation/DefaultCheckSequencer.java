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
package com.avaloq.tools.ddk.xtext.validation;

/**
 * Default implementation of DefaultCheckSequencer. It will never break a sequence.
 */
public class DefaultCheckSequencer implements ICheckSequencer {

  /**
   * The singletone.
   */
  private static final DefaultCheckSequencer INSTANCE = new DefaultCheckSequencer();

  /**
   * The singletone instance for the default check sequencer.
   *
   * @return the singletone instance
   */
  public static DefaultCheckSequencer getInstance() {
    return INSTANCE;
  }

  /**
   * Default implementation for canContinue.
   *
   * @return always true
   */
  @Override
  public boolean canContinue() {
    return true;
  }

}
