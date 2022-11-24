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
package com.avaloq.tools.ddk.xtext.formatting;

/**
 * Empty implementation of the IFormattingDirectiveExecutor interface.
 * Can be used to switch the processing of directives for the particular given DSL off.
 */
public class NullFormattingDirectiveExecutor implements IFormattingDirectiveExecutor {

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final String commentText, final ExtendedFormattingConfigBasedStream stream) {
    // Do nothing
  }

}
