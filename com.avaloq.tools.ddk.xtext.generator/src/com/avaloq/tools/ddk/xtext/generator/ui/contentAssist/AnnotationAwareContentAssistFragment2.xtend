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

package com.avaloq.tools.ddk.xtext.generator.ui.contentAssist

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend2.lib.StringConcatenationClient
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Alternatives
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.xtext.generator.ui.contentAssist.ContentAssistFragment2

import static extension org.eclipse.xtext.GrammarUtil.*
import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations

class AnnotationAwareContentAssistFragment2 extends ContentAssistFragment2 {

  @Inject
  FileAccessFactory fileAccessFactory

  @Inject
  extension GrammarRuleAnnotations annotations

    // generation of the 'Abstract...ProposalProvider'

  protected override generateGenJavaProposalProvider() {
    // excluded features are those that stem from inherited grammars,
    //  they are handled by the super grammars' proposal provider
    val excludedFqnFeatureNames = grammar.getFQFeatureNamesToExclude
    val processedNames = newHashSet()
    grammar.annotateGrammar
    // determine all assignments within the grammar that are not excluded and not handled yet
    val assignments = grammar.containedAssignments().fold(<Assignment>newArrayList()) [ candidates, assignment |
      val fqFeatureName = assignment.FQFeatureName
      if (!processedNames.contains(fqFeatureName) && !excludedFqnFeatureNames.contains(fqFeatureName)) {
        processedNames += fqFeatureName;
        candidates += assignment;
      }
      candidates
    ]

    // determine keyword rules
    val keywordRules = grammar.rules.fold(<AbstractRule>newArrayList()) [ candidates, rule |
      val fqFeatureName = rule.FQFeatureName
      if (!processedNames.contains(fqFeatureName) && !excludedFqnFeatureNames.contains(fqFeatureName)
        && rule.semanticPredicateAnnotation?.keywords !== null
      ) {
        processedNames += fqFeatureName;
        candidates += rule;
      }
      candidates
    ]

    // determine the (remaining) rules that are not excluded and not handled yet
    val remainingRules = grammar.rules.fold(<AbstractRule>newArrayList()) [candidates, rule |
      val fqnFeatureName = rule.FQFeatureName
      if (!processedNames.contains(fqnFeatureName) && !excludedFqnFeatureNames.contains(fqnFeatureName)) {
        processedNames += fqnFeatureName
        candidates += rule
      }
      candidates
    ]

    // take the non-abstract class signature for the src-gen class in case of !generateStub
    //  as proposalProviders of sub languages refer to 'grammar.proposalProviderClass',
    //  see 'getGenProposalProviderSuperClass(...)'
    val genClass =
      if (isGenerateStub) grammar.genProposalProviderClass else grammar.proposalProviderClass;

    fileAccessFactory.createGeneratedJavaFile(genClass) => [
      val superClass = grammar.genProposalProviderSuperClass

      typeComment = '''
        /**
         * Represents a generated, default implementation of superclass {@link «superClass»}.
         * Methods are dynamically dispatched on the first parameter, i.e., you can override them
         * with a more concrete subtype.
         */
      '''

      content = '''
        public «IF isGenerateStub»abstract «ENDIF»class «genClass.simpleName» extends «superClass» {

          «IF !assignments.empty»
            «FOR assignment : assignments»
              «assignment.handleAssignment»
            «ENDFOR»

          «ENDIF»
          «IF !keywordRules.empty»
            «FOR rule : keywordRules»
              public void complete«rule.FQFeatureName»(«EObject» model, «RuleCall» ruleCall, «
                  contentAssistContextClass» context, «ICompletionProposalAcceptorClass» acceptor) {
                «rule.handleKeywordRule»
              }
            «ENDFOR»

          «ENDIF»
          «FOR rule : remainingRules»
            public void complete«rule.FQFeatureName»(«EObject» model, «RuleCall» ruleCall, «
                contentAssistContextClass» context, «ICompletionProposalAcceptorClass» acceptor) {
              // subclasses may override
            }
          «ENDFOR»
        }
      '''
      writeTo(projectConfig.eclipsePlugin.srcGen)
    ]
  }

  private def StringConcatenationClient handleKeywordRule(AbstractRule rule) {
      '''
        «FOR keyword : rule.semanticPredicateAnnotation.keywords»
          acceptor.accept(createCompletionProposal("«keyword»", context));
        «ENDFOR»
      '''
  }

  private def StringConcatenationClient handleAssignment(Assignment assignment) {
    // determine all assignment within 'assignment's containing parser rule
    //  assigning the same feature, obtain their expected terminals, ...
    val terminals = assignment.containingParserRule.containedAssignments().filter[
      it.feature == assignment.feature
    ].map[
      terminal
    ].toList

    // ... and determine the types of those terminals
    val terminalTypes = terminals.map[ eClass ].toSet;

    '''
      public void complete«assignment.FQFeatureName»(«EObject» model, «Assignment» assignment, «
          contentAssistContextClass» context, «ICompletionProposalAcceptorClass» acceptor) {
        «IF terminalTypes.size > 1»
          «terminals.handleAssignmentOptions»
        «ELSE»
          «assignment.terminal.assignmentTerminal('''assignment.getTerminal()''')»
        «ENDIF»
      }
    '''
  }

  private def StringConcatenationClient handleAssignmentOptions(Iterable<AbstractElement> terminals) {
    val processedTerminals = newHashSet();

    // for each type of terminal occurring in 'terminals' ...
    val candidates = terminals.fold(<AbstractElement>newArrayList()) [ candidates, terminal |
      if (!processedTerminals.contains(terminal.eClass)) {
        processedTerminals += terminal.eClass
        candidates += terminal
      }
      candidates
    ]

    // ... generate an 'instanceof' clause
    '''
      «FOR terminal : candidates»
        if (assignment.getTerminal() instanceof «terminal.eClass.instanceClass») {
          «terminal.assignmentTerminal('''assignment.getTerminal()''')»
        }
      «ENDFOR»
    '''
  }

  private def dispatch StringConcatenationClient assignmentTerminal(AbstractElement element, StringConcatenationClient accessor) '''
    // subclasses may override
  '''

  private def dispatch StringConcatenationClient assignmentTerminal(CrossReference element, StringConcatenationClient accessor) '''
    lookupCrossReference(((«CrossReference»)«accessor»), context, acceptor);
  '''

  private def dispatch StringConcatenationClient assignmentTerminal(RuleCall element, StringConcatenationClient accessor) '''
    completeRuleCall(((«RuleCall»)«accessor»), context, acceptor);
  '''

  private def dispatch StringConcatenationClient assignmentTerminal(Alternatives alternatives, StringConcatenationClient accessor) '''
    «FOR pair : alternatives.elements.indexed»
      «pair.value.assignmentTerminal('''((«Alternatives»)«accessor»).getElements().get(«pair.key»)''')»
    «ENDFOR»
  '''

  // helper methods

  private def getContentAssistContextClass() {
    new TypeReference("org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext")
  }

  private def getICompletionProposalAcceptorClass() {
    new TypeReference("org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor")
  }

  def private getFQFeatureName(AbstractRule r) {
    "_" + r.name;
  }

  def private getFQFeatureName(Assignment a) {
    a.containingParserRule().name.toFirstUpper() + "_" + a.feature.toFirstUpper();
  }

}
