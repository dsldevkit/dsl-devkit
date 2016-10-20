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
package com.avaloq.tools.ddk.xtext.formatting;

import com.google.inject.ImplementedBy;


/**
 * Interface describes a service that looks for formatting directives in the comments and executes them.
 */
@ImplementedBy(DefaultFormattingDirectiveExecutor.class)
public interface IFormattingDirectiveExecutor {

  /**
   * Extracts formatting directives and executes them on the given stream if there are any.
   *
   * @param commentText
   *          text of some comment node, must not be {@code null}
   * @param stream
   *          formatting stream, must not be {@code null}
   */
  void execute(String commentText, DdkFormattingConfigBasedStream stream);

}
