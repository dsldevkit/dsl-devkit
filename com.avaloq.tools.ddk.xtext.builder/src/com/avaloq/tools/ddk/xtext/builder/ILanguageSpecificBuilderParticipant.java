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
package com.avaloq.tools.ddk.xtext.builder;

import org.eclipse.xtext.builder.IXtextBuilderParticipant;


/**
 * A language specific {@link IXtextBuilderParticipant}.
 */
public interface ILanguageSpecificBuilderParticipant extends IXtextBuilderParticipant {
  /**
   * Returns the language id.
   *
   * @return the language id, never {@code null} or empty
   */
  String getLanguageId();
}
