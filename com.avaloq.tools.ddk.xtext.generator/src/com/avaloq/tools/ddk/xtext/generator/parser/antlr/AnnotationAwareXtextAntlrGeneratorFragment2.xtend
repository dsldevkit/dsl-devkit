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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr

import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2
import com.google.inject.Inject
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming

class AnnotationAwareXtextAntlrGeneratorFragment2 extends XtextAntlrGeneratorFragment2 {

   @Inject AnnotationAwareAntlrGrammarGenerator productionGenerator
   @Inject GrammarNaming productionNaming

    protected override generateProductionGrammar() {
    val extension naming = productionNaming
    val fsa = projectConfig.runtime.srcGen

    productionGenerator.generate(grammar, options, fsa)

    runAntlr(grammar.parserGrammar, grammar.lexerGrammar, fsa)

    simplifyUnorderedGroupPredicatesIfRequired(grammar, fsa, grammar.internalParserClass)
    splitParserAndLexerIfEnabled(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeTokens(fsa, grammar.lexerGrammar.tokensFileName)
    suppressWarnings(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeLineDelimiters(fsa, grammar.internalParserClass, grammar.lexerClass)
  }

}
