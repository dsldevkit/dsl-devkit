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
 * AbstractDdkFormatter to access the DdkFormating Stream and config.
 */
public abstract class AbstractDdkFormatter extends AbstractDeclarativeFormatter {

  @Inject
  private IFormattingDirectiveExecutor executor;

  /**
   * {@inheritDoc}
   */
  @Override
  public ITokenStream createFormatterStream(final String initalIndentation, final ITokenStream outputStream, final boolean preserveWhitespaces) {
    return new DdkFormattingConfigBasedStream(outputStream, initalIndentation, getConfig(), createMatcher(), getHiddenTokenHelper(), preserveWhitespaces, this);
  }

  @Override
  public ITokenStream createFormatterStream(final EObject context, final String indent, final ITokenStream out, final boolean preserveWhitespaces) {
    // TODO set context (see superclass)
    return createFormatterStream(indent, out, preserveWhitespaces);
  }

  @Override
  protected void configureFormatting(final FormattingConfig config) {
    configureDdkFormatting((DdkFormattingConfig) config);
  }

  /**
   * Finds and executes the formatting directives if there are any.
   *
   * @param text
   *          text of a comment node, must not be {@code null}
   * @param stream
   *          token stream to apply directives for, must not be {@code null}
   */
  public void executeFormattingDirectives(final String text, final DdkFormattingConfigBasedStream stream) {
    executor.execute(text, stream);
  }

  /**
   * Configure the formatting using DdkFormattingConfig and DdkFormattingConfigBasedStream.
   *
   * @param config
   *          - the DdkFormattingConfig provided by Guice
   */
  protected abstract void configureDdkFormatting(final DdkFormattingConfig config);

  /**
   * Get SL-comment rule.
   * This method is needed by {@link #configureComments(DdkFormattingConfig, TerminalRule, TerminalRule)}.
   *
   * @return TerminalRule - getGrammarAccess().getSL_COMMENTRule();
   */
  protected abstract TerminalRule getSLCommentRule();

  /**
   * Get ML-comment rule.
   * This method is needed by {@link #configureComments(DdkFormattingConfig, TerminalRule, TerminalRule)}.
   *
   * @return TerminalRule - getGrammarAccess().getML_COMMENTRule();
   */
  protected abstract TerminalRule getMLCommentRule();

  /**
   * {@inheritDoc}
   */
  @Override
  protected FormattingConfig createFormattingConfig() {
    return new DdkFormattingConfig(getGrammarAccess(), getHiddenTokenHelper(), getIndentInfo(), null);
  }

}
