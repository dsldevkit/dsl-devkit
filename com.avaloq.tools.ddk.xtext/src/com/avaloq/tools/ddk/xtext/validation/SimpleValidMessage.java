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

import org.eclipse.emf.ecore.EObject;


/**
 * The Class SimpleValidMessage. Simple valid messages are messages that
 * reflect a single error (unlike CompoundValidMessage) and for which
 * no specific context EObject is given.
 */
public class SimpleValidMessage extends ValidMessage<EObject> {

  /**
   * Instantiates a new simple valid message.
   *
   * @param message
   *          the message
   */
  public SimpleValidMessage(final String message) {
    super(message, null);
  }

  /**
   * Instantiates a new simple valid message.
   *
   * @param message
   *          the message
   * @param issueData
   *          the issue data
   */
  public SimpleValidMessage(final String message, final String... issueData) {
    super(message, null, issueData);
  }

  /**
   * Instantiates a new simple valid message.
   *
   * @param message
   *          the message
   * @param offset
   *          the offset
   * @param length
   *          the length
   */
  public SimpleValidMessage(final String message, final int offset, final int length) {
    super(message, null, offset, length, (String[]) null);
  }

  /**
   * Instantiates a new simple valid message.
   *
   * @param message
   *          the message
   * @param offset
   *          the offset
   * @param length
   *          the length
   * @param issueData
   *          the issue data
   */
  public SimpleValidMessage(final String message, final int offset, final int length, final String... issueData) {
    super(message, null, offset, length, issueData);
  }

}
