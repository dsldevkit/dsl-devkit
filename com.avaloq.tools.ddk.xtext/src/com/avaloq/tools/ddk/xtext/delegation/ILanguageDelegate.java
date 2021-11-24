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
package com.avaloq.tools.ddk.xtext.delegation;

import java.util.Set;

import org.antlr.runtime.Token;
import org.eclipse.xtext.nodemodel.INode;


/**
 * Helps detecting the context of the getting services for the delegated language.
 */
public interface ILanguageDelegate {

  /**
   * Delegation from one grammar could go to more than one language.
   */
  interface ILanguageReference {
    /**
     * Code for encoding tokens.
     *
     * @return code to prefix token type codes
     */
    int getCode();
  }

  /**
   * Defines language for no delegation.
   */
  enum DefaultLanguageReference implements ILanguageReference {

    /** The version supported by original parser. */
    DEFAULT;

    @Override
    public int getCode() {
      return 1;
    }

  }

  /**
   * Checks if this token delegates parsing to another language.
   *
   * @param tokenType
   *          type of token
   * @return Reference of the language, or {@code null} if not delegated
   */
  ILanguageReference getDelegatedLanguage(final int tokenType);

  /**
   * Checks if is delegate token type.
   *
   * @param type
   *          the token type, might be either original or a delegate
   * @return true, if the token type is an encoded delegate type
   */
  ILanguageReference findDelegatedLanguage(final int type);

  /**
   * Gets the original token type be decoding the delegate token type.
   *
   * @param delegate
   *          the delegate token type
   * @return the original token type
   */
  int getType(final int delegate);

  /**
   * Transform token into a delegate token.
   *
   * @param nextToken
   *          the next token
   * @param delegateLanguage
   *          the language to encode token for
   * @return the token with adjusted type code
   */
  Token delegateToken(final Token nextToken, ILanguageReference delegateLanguage);

  /**
   * Checks if is delegate rule.
   *
   * @param node
   *          the concrete syntax tree node, may be {@code null}
   * @return {@code null} if not delegated, {@link DefaultLanguageReference#DEFAULT} if language supports delegation to one language only
   */
  ILanguageReference findDelegatedLanguage(INode node);

  /**
   * Gets the single instance of ILanguageDelegate.
   *
   * @param <T>
   *          the generic type
   * @param language
   *          language reference to select delegated language
   * @param clazz
   *          the class
   * @return single instance of ILanguageDelegate
   */
  <T> T getInstance(ILanguageReference language, Class<T> clazz);

  /**
   * Gets the single instance of ILanguageDelegate.
   *
   * @param <T>
   *          the generic type
   * @param clazz
   *          the class
   * @return single instance of ILanguageDelegate
   */
  <T> Set<T> getAllInstances(Class<T> clazz);

}
