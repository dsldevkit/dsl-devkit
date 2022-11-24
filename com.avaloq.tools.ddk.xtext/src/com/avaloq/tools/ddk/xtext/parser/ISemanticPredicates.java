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
package com.avaloq.tools.ddk.xtext.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;


/**
 * Provides interface to generated semantic predicate methods.
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
   * Test if the rule of the given name is a keyword rule.
   *
   * @param ruleName
   *          the name of the rule to test.
   * @return {@code true} if the named rule is a keyword rule, {@code false}.
   */
  boolean isKeywordRule(String ruleName);

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

    @Override
    public boolean isKeywordRule(final String ruleName) {
      // if the rule is a keyword, then we will have generated a method for handling its syntax message.
      String methodName = "get" + ruleName + "EnabledMessage"; //$NON-NLS-1$//$NON-NLS-2$
      for (Method method : this.getClass().getDeclaredMethods()) {
        if (methodName.equals(method.getName())) {
          return true;
        }
      }
      return false;
    }

    /**
     * Predicate for grammar rule DefaultFieldFormat.
     *
     * @param parserContext
     *          Input from Lexer
     * @param firstKeyword
     *          the current keyword parser is looking at
     * @param secondKeyword
     *          the keyword following the current keyword
     * @return {@code true} if the grammar rule is enabled, {@code false} otherwise
     */
    public static boolean isFollowedBy(final ParserContext parserContext, final String firstKeyword, final String secondKeyword) {
      TokenStream inputStream = parserContext.getInput();
      return firstKeyword.equalsIgnoreCase(inputStream.LT(1).getText()) && secondKeyword.equalsIgnoreCase(inputStream.LT(2).getText());
    }
  }
}
