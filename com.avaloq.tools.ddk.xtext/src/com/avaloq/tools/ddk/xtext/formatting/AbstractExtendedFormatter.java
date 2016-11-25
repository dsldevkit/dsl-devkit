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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;

import com.google.inject.Inject;


/**
 * AbstractExtendedFormatter to access the AcsFormating Stream and config. Adds support for comment handling and number of additional parameterized and conditional
 * locators.
 */
public abstract class AbstractExtendedFormatter extends AbstractDeclarativeFormatter {

  @Inject
  private IFormattingDirectiveExecutor executor;

  /**
   * {@inheritDoc}
   */
  @Override
  public ITokenStream createFormatterStream(final String initalIndentation, final ITokenStream outputStream, final boolean preserveWhitespaces) {
    return new ExtendedFormattingConfigBasedStream(outputStream, initalIndentation, getConfig(), createMatcher(), getHiddenTokenHelper(), preserveWhitespaces, this);
  }

  @Override
  public ITokenStream createFormatterStream(final EObject context, final String indent, final ITokenStream out, final boolean preserveWhitespaces) {
    // TODO set context (see superclass)
    return createFormatterStream(indent, out, preserveWhitespaces);
  }

  @Override
  protected void configureFormatting(final FormattingConfig config) {
    configureAcsFormatting((ExtendedFormattingConfig) config);
  }

  /**
   * Finds and executes the formatting directives if there are any.
   *
   * @param text
   *          text of a comment node, must not be {@code null}
   * @param stream
   *          token stream to apply directives for, must not be {@code null}
   */
  public void executeFormattingDirectives(final String text, final ExtendedFormattingConfigBasedStream stream) {
    executor.execute(text, stream);
  }

  /**
   * Configure the formatting using ExtendedFormattingConfig and ExtendedFormattingConfigBasedStream.
   *
   * @param config
   *          - the ExtendedFormattingConfig provided by Guice
   */
  protected abstract void configureAcsFormatting(final ExtendedFormattingConfig config);

  /**
   * Get SL-comment rule.
   * This method is needed by {@link #configureComments(ExtendedFormattingConfig, TerminalRule, TerminalRule)}.
   *
   * @return TerminalRule - getGrammarAccess().getSL_COMMENTRule();
   */
  protected abstract TerminalRule getSLCommentRule();

  /**
   * Get ML-comment rule.
   * This method is needed by {@link #configureComments(ExtendedFormattingConfig, TerminalRule, TerminalRule)}.
   *
   * @return TerminalRule - getGrammarAccess().getML_COMMENTRule();
   */
  protected abstract TerminalRule getMLCommentRule();

  /**
   * {@inheritDoc}
   */
  @Override
  protected FormattingConfig createFormattingConfig() {
    return new ExtendedFormattingConfig(getGrammarAccess(), getHiddenTokenHelper(), getIndentInfo(), null);
  }

}
