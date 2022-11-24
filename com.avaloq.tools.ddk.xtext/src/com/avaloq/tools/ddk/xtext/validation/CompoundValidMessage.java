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
package com.avaloq.tools.ddk.xtext.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;


/**
 * The Class CompoundValidMessage.
 *
 * @param <E>
 *          the type of EObjects for which one can create that kind of messages
 */
public class CompoundValidMessage<E extends EObject> {

  /** The messages. */
  private final List<ValidMessage<E>> messages = new ArrayList<ValidMessage<E>>();

  /**
   * Instantiates a new compound valid message.
   */
  public CompoundValidMessage() {
    // Nothing to do, empty list...
  }

  /**
   * Instantiates a new compound valid message with an initial message.
   *
   * @param message
   *          the (bound) message
   * @param context
   *          the object on which the message is applicable
   */
  public CompoundValidMessage(final String message, final E context) {
    messages.add(new ValidMessage<E>(message, context));
  }

  /**
   * Adds a message to the list of messages.
   *
   * @param message
   *          the (bound) message
   * @param context
   *          the object on which the message is applicable
   */
  public void add(final String message, final E context) {
    messages.add(new ValidMessage<E>(message, context));
  }

  /**
   * Adds a message to the list.
   *
   * @param message
   *          the message
   */
  public void add(final ValidMessage<E> message) {
    messages.add(message);
  }

  /**
   * Gets the messages.
   *
   * @return the messages
   */
  public Iterable<ValidMessage<E>> getMessages() {
    return messages;
  }

}
