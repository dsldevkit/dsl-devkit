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
package com.avaloq.tools.ddk.xtext.expression.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.AdditiveExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.CastedExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.CollectionExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.CollectionTypeElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.InfixExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.ListLiteralElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.MultiplicativeExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.OperationCallElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.SimpleTypeElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.SwitchExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.TypeSelectExpressionElements;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess.UnaryExpressionElements;


/**
 * This class contains custom formatting description.
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class ExpressionFormatter extends AbstractDeclarativeFormatter {

  @Override
  protected void configureFormatting(final FormattingConfig config) {
    final ExpressionGrammarAccess grammarAccess = (ExpressionGrammarAccess) getGrammarAccess();
    configureFormatting(config, grammarAccess);
  }

  /**
   * Create a formatting configuration for the Expression language. This is a all-in-one-line configuration. This configuration is
   * intented for langauges such as the Valid language, which deal with Xtend expressions that should be concise and not interfere
   * with the rest of the text.
   * From a sub-language, this operation should be called directly as
   * configureFormatting(config, grammarAccess.getExpressionGrammarAccess());
   * 
   * @param config
   *          the configuration to specify
   * @param grammarAccess
   *          the ExpressionGrammarAccess, not the child grammar.
   */
  protected void configureFormatting(final FormattingConfig config, final ExpressionGrammarAccess grammarAccess) {
    final CastedExpressionElements castedExpressionElements = grammarAccess.getCastedExpressionAccess();
    config.setNoSpace().after(castedExpressionElements.getLeftParenthesisKeyword_0());
    config.setNoSpace().before(castedExpressionElements.getRightParenthesisKeyword_2());

    final SwitchExpressionElements switchExpressionElements = grammarAccess.getSwitchExpressionAccess();
    config.setNoSpace().after(switchExpressionElements.getLeftParenthesisKeyword_1_0());
    config.setNoSpace().before(switchExpressionElements.getRightParenthesisKeyword_1_2());

    final AdditiveExpressionElements additiveExpressionElements = grammarAccess.getAdditiveExpressionAccess();
    config.setNoSpace().around(additiveExpressionElements.getNameHyphenMinusKeyword_1_1_0_1());
    config.setNoSpace().around(additiveExpressionElements.getNamePlusSignKeyword_1_1_0_0());

    final MultiplicativeExpressionElements multiplicativeExpressionElements = grammarAccess.getMultiplicativeExpressionAccess();
    config.setNoSpace().around(multiplicativeExpressionElements.getNameAsteriskKeyword_1_1_0_0());
    config.setNoSpace().around(multiplicativeExpressionElements.getNameSolidusKeyword_1_1_0_1());

    final UnaryExpressionElements unaryExpressionElements = grammarAccess.getUnaryExpressionAccess();
    config.setNoSpace().after(unaryExpressionElements.getNameExclamationMarkKeyword_0_0_0());
    config.setNoSpace().after(unaryExpressionElements.getNameHyphenMinusKeyword_0_0_1());

    final InfixExpressionElements infixExpressionElements = grammarAccess.getInfixExpressionAccess();
    config.setNoSpace().before(infixExpressionElements.getCommaKeyword_1_0_4_1_0());
    config.setNoSpace().around(infixExpressionElements.getFullStopKeyword_1_0_1());
    config.setNoSpace().around(infixExpressionElements.getFullStopKeyword_1_1_1());
    config.setNoSpace().around(infixExpressionElements.getFullStopKeyword_1_2_1());
    config.setNoSpace().around(infixExpressionElements.getFullStopKeyword_1_3_1());
    config.setNoSpace().after(infixExpressionElements.getLeftParenthesisKeyword_1_0_3());
    config.setNoSpace().after(infixExpressionElements.getLeftParenthesisKeyword_1_2_3());
    config.setNoSpace().after(infixExpressionElements.getLeftParenthesisKeyword_1_3_3());
    config.setNoSpace().before(infixExpressionElements.getRightParenthesisKeyword_1_0_5());
    config.setNoSpace().before(infixExpressionElements.getRightParenthesisKeyword_1_2_5());
    config.setNoSpace().before(infixExpressionElements.getRightParenthesisKeyword_1_3_6());
    config.setNoSpace().around(infixExpressionElements.getVerticalLineKeyword_1_3_4_1());

    final OperationCallElements operationCallElements = grammarAccess.getOperationCallAccess();
    config.setNoSpace().after(operationCallElements.getLeftParenthesisKeyword_1());
    config.setNoSpace().before(operationCallElements.getRightParenthesisKeyword_3());
    config.setNoSpace().after(operationCallElements.getCommaKeyword_2_1_0());

    final ListLiteralElements listLiteralElements = grammarAccess.getListLiteralAccess();
    config.setNoSpace().after(listLiteralElements.getLeftCurlyBracketKeyword_1());
    config.setNoSpace().before(listLiteralElements.getRightCurlyBracketKeyword_3());
    config.setNoSpace().after(listLiteralElements.getCommaKeyword_2_1_0());

    final TypeSelectExpressionElements typeSelectExpressionElements = grammarAccess.getTypeSelectExpressionAccess();
    config.setNoSpace().after(typeSelectExpressionElements.getLeftParenthesisKeyword_1());
    config.setNoSpace().before(typeSelectExpressionElements.getRightParenthesisKeyword_3());

    final CollectionExpressionElements collectionExpressionElements = grammarAccess.getCollectionExpressionAccess();
    config.setNoSpace().after(collectionExpressionElements.getLeftParenthesisKeyword_1());
    config.setNoSpace().before(collectionExpressionElements.getRightParenthesisKeyword_4());

    final CollectionTypeElements collectionTypeElements = grammarAccess.getCollectionTypeAccess();
    config.setNoSpace().after(collectionTypeElements.getLeftSquareBracketKeyword_1());
    config.setNoSpace().after(collectionTypeElements.getRightSquareBracketKeyword_3());

    final SimpleTypeElements simpleTypeElements = grammarAccess.getSimpleTypeAccess();
    config.setNoSpace().around(simpleTypeElements.getColonColonKeyword_1_0());

  }
}
