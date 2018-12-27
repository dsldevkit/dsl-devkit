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
package com.avaloq.tools.ddk.xtext.parser.antlr;

import java.util.Map;

import org.antlr.runtime.TokenStream;

import com.google.common.collect.Maps;


/**
 * Compilation context for parser. Intended to be used by
 * <ul>
 * <li>preprocessor to store information about the source,</li>
 * <li>when parse and re-parse start they may updates the information,</li>
 * <li>semantic actions in parser may extend the information,</li>
 * <li>semantic predicates in parser may use the information stored for decisions.</li>
 * </ul>
 */
public class ParserContext {

  /**
   * Provider for {@link ParserContext}s.
   */
  public static class ParserContextProvider {
    /**
     * Returns a new instance of a {@link ParserContext}.
     *
     * @return a new instance of a {@link ParserContext}, never {@code null}
     */
    public ParserContext get() {
      return new ParserContext();
    }
  }

  private final Map<Class<? extends Object>, Object> contextObjects = Maps.newHashMap();

  private TokenStream tokenStream;

  /**
   * Put compilation context object.
   *
   * @param <T>
   *          Type of the compilation context object
   * @param object
   *          Compilation context
   */
  public <T> void putObject(final T object) {
    contextObjects.put(object.getClass(), object);
  }

  /**
   * Retrieve compilation context object.
   *
   * @param <T>
   *          Type of the compilation context object
   * @param clazz
   *          Class of the compilation context object
   * @return Compilation context object
   */
  @SuppressWarnings("unchecked")
  public <T> T getObject(final Class<T> clazz) {
    return (T) contextObjects.get(clazz);
  }

  /**
   * Set token stream. Called by the parser when parser is configured.
   *
   * @param input
   *          Token stream
   */
  public void setTokenStream(final TokenStream input) {
    if (input != null) {
      beforeParse(input);
    }
    this.tokenStream = input;
  }

  /**
   * Custom implementations may hook in here an analysis of the token stream before actually parsing the source.
   *
   * @param input
   *          Input token stream
   */
  protected void beforeParse(final TokenStream input) {
    // No implementation by default
  }

  /**
   * Returns token stream configured by parser. Never null if parser is properly configured.
   *
   * @return Input token stream
   */
  public TokenStream getInput() {
    return tokenStream;
  }

}
