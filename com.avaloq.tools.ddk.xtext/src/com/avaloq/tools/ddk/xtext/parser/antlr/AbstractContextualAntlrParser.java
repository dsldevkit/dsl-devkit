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
package com.avaloq.tools.ddk.xtext.parser.antlr;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.nodemodel.impl.AbstractNode;
import org.eclipse.xtext.nodemodel.impl.CompositeNode;
import org.eclipse.xtext.nodemodel.impl.NodeModelBuilder;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;

import com.avaloq.tools.ddk.xtext.delegation.ILanguageDelegate;
import com.avaloq.tools.ddk.xtext.parser.IResourceAwareParser;
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext.ParserContextProvider;
import com.google.inject.Inject;


/**
 * A customized context aware abstract ANTLR parser.
 */
public abstract class AbstractContextualAntlrParser extends AbstractAntlrParser implements IResourceAwareParser {

  @Inject(optional = true)
  private ILanguageDelegate delegate;

  @Inject
  private IGrammarAccess grammarAccess;

  @Inject
  private ParserContextProvider parserContextProvider;
  private String fileExtension;

  @Override
  public void setFileExtension(final String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public String getFileExtension() {
    return fileExtension;
  }

  /**
   * Creates the parser context.
   *
   * @return the {@link ParserContext}, never {@code null}
   */
  protected ParserContext createParserContext() {
    final ParserContext parserContext = parserContextProvider.get();
    if (getFileExtension() != null) {
      parserContext.putObject(new SourceFileType(getFileExtension()));
    }
    return parserContext;
  }

  @Override
  public IParseResult doParse(final Reader reader) {
    IParseResult parseResult = super.doParse(reader);
    if (parseResult.getRootASTElement() == null) {
      // Most of ASMD languages are not good with empty models, so create an empty root element
      AbstractRule rule = GrammarUtil.findRuleForName(grammarAccess.getGrammar(), getDefaultRuleName());
      EObject newRoot = getElementFactory().create(rule.getType().getClassifier());
      return new ParseResult(newRoot, parseResult.getRootNode(), true);
    }
    return parseResult;
  }

  @Override
  protected IParseResult doParse(final String ruleName, final CharStream in, final NodeModelBuilder nodeModelBuilder, final int initialLookAhead) {
    final IParseResult parseResult = super.doParse(ruleName, in, nodeModelBuilder, initialLookAhead);
    if (delegate == null || parseResult.hasSyntaxErrors()) {
      return parseResult;
    }
    // If delegation was potentially used, we need to check for syntax errors in replaced nodes
    boolean hasError = false;
    Iterator<AbstractNode> nodeIterator = ((CompositeNode) parseResult.getRootNode()).basicIterator();
    while (nodeIterator.hasNext()) {
      AbstractNode node = nodeIterator.next();
      if (node.getSyntaxErrorMessage() != null) {
        hasError = true;
        break;
      }
    }
    if (hasError) {
      return new ParseResult(parseResult.getRootASTElement(), parseResult.getRootNode(), true);
    }
    return parseResult;
  }

  /**
   * {@inheritDoc}.
   * For parser delegation it is critically important that the root node of the parsed sub-tree is not merged. Otherwise we do not have a proper new composite
   * node for replacement.
   */
  public IParseResult parseDelegate(final ParserRule rule, final Reader reader) {
    try {
      NodeModelBuilder builder = createNodeModelBuilder();
      builder.setForcedFirstGrammarElement(null);
      return doParse(rule.getName(), new ANTLRReaderStream(reader), builder, 0);
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }
}
