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
package com.avaloq.tools.ddk.check.ui.highlighting;

import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.xbase.ui.highlighting.XbaseHighlightingCalculator;

import com.avaloq.tools.ddk.check.documentation.JavaDocCommentDocumentationProvider;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess;
import com.google.inject.Inject;


/**
 * An XbaseHighlightingCalculator-based implementation for the Check language.
 */
public class CheckHighlightingCalculator extends XbaseHighlightingCalculator {

  private static final String FEATURE_CALL_ID_RULE_NAME = "FeatureCallID";

  @Inject
  private JavaDocCommentDocumentationProvider commentProvider;

  @Inject
  private CheckGrammarAccess grammarAccess;

  @Override
  protected void highlightSpecialIdentifiers(final IHighlightedPositionAcceptor acceptor, final ICompositeNode root) {
    TerminalRule idRule = grammarAccess.getIDRule();

    for (ILeafNode leaf : root.getLeafNodes()) {
      if (commentProvider.isJavaDocComment(leaf)) {
        // not really a special identifier, but we don't want to iterate over the leaf nodes twice, do we?
        acceptor.addPosition(leaf.getOffset(), leaf.getLength(), CheckHighlightingConfiguration.JAVADOC_ID);
      } else if (!leaf.isHidden()) {
        if (leaf.getGrammarElement() instanceof Keyword) {
          // Check if it is a keyword used as an identifier.
          ParserRule rule = GrammarUtil.containingParserRule(leaf.getGrammarElement());
          if (FEATURE_CALL_ID_RULE_NAME.equals(rule.getName())) {
            acceptor.addPosition(leaf.getOffset(), leaf.getLength(), DefaultHighlightingConfiguration.DEFAULT_ID);
          }
        } else {
          highlightSpecialIdentifiers(leaf, acceptor, idRule);
        }
      }
    }
  }

}

