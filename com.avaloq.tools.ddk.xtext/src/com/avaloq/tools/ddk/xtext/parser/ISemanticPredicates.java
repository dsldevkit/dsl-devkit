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
package com.avaloq.tools.ddk.xtext.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.antlr.runtime.Token;


/**
 * Provides messages to be displayed as diagnostic messages when predicate is violated.
 */
public interface ISemanticPredicates {

  /**
   * Returns message when predicate with the given name is violated.
   *
   * @param key
   *          Message key
   * @param token
   *          Affecting token in the input stream
   * @return Message
   */
  String getMessage(final String key, final Token token);

  /**
   * Default implementation of predicate violation. Searches with reflection method with the matching key.
   */
  class AbstractSemanticPredicates implements ISemanticPredicates {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(final String key, final Token token) {
      Method[] methods = this.getClass().getDeclaredMethods();
      String message = null;
      if (key != null) {
        for (Method method : methods) {
          if (key.equals(method.getName())) {
            try {
              message = (String) method.invoke(this, token);
              break;
            } catch (IllegalArgumentException e) {
              break;
            } catch (IllegalAccessException e) {
              break;
            } catch (InvocationTargetException e) {
              break;
            }
          }
        }
      }
      return message;
    }
  }

}
