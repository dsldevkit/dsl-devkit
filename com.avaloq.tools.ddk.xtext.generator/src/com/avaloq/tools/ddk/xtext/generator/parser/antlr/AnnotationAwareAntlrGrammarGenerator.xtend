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

import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.EnumRule
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenerator
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions

import static extension com.avaloq.tools.ddk.xtext.generator.parser.antlr.GrammarRuleAnnotations.*
import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil.*
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference

/**
 *
 * The following extension is supported:
 *
 *   A datatype grammar rule containing only one ID terminal rule can be annotated
 *   with @KeywordRule annotation provided a list of words so only these words can
 *   be accepted by this rule.
 *
 *   Example:
 *
 *   /**
 *     * @KeywordRule(visible, invisible)
 *     * /
 *   VisibleKind returns VisibleKind:
 *     ID
 *   ;
 *
 *   The above rule will accept only 'visible' and 'invisible' identifiers.
 *   This rule in ASMD is called a keyword rule because it is intended to replace
 *   usages of keywords which shall not be reserved words in the language.
 *   Reserved words are words that are not allowed to be used in identifiers.
 *
 *   The above example can therefore replace the following enumeration:
 *
 *   enum VisibleKind :
 *       VISIBLE   = "visible"
 *     | INVISIBLE = "invisible"
 *   ;
 *
 *   Please note that a corresponding value converter is needed.
 *
 *   Implementation remark:
 *     - This template will insert validating semantic predicates in the rule
 *     - If the rule is used from an alternative a gated semantic predicate will
 *       be used in the alternative
 *     - Error messages will be adjusted correspondingly
 */
class AnnotationAwareAntlrGrammarGenerator extends AntlrGrammarGenerator {

  protected override compileInit(AbstractRule it, AntlrOptions options) '''
    «IF it instanceof ParserRule»«getParameterList(!isPassCurrentIntoFragment, currentType)»«ENDIF» returns «compileReturns(options)»
    «IF hasNoBacktrackAnnotation»
      // Enclosing rule was annotated with @NoBacktrack
      options { backtrack=false; }
    «ENDIF»
    @init {
      enterRule();
      «compileInitHiddenTokens(options)»
      «compileInitUnorderedGroups(options)»
    }
    @after {
      leaveRule();
    }'''

 /**
  * Inserts validating predicate only. Gated predicates will be inserted in alternatives if needed.
  */
  protected  override String dataTypeEbnf(AbstractElement it, boolean supportActions) '''
    «IF hasSemanticPredicate»«generateSemanticPredicate»«ENDIF»
    «IF mustBeParenthesized»(
      «IF hasNoBacktrackAnnotation»
        // Enclosing rule was annotated with @NoBacktrack
        options { backtrack=false; }
      «ENDIF»
      «dataTypeEbnfPredicate»«dataTypeEbnf2(supportActions)»
    )«ELSE»«dataTypeEbnf2(supportActions)»«ENDIF»«cardinality»
  '''

  protected override String ebnf(AbstractElement it, AntlrOptions options, boolean supportActions) '''
    «IF mustBeParenthesized»(
      «IF hasNoBacktrackAnnotation»
        // Enclosing rule was annotated with @NoBacktrack
        options { backtrack=false; }
      «ENDIF»
      «ebnfPredicate(options)»«ebnf2(options, supportActions)»
    )«ELSE»«ebnf2(options, supportActions)»«ENDIF»«cardinality»
  '''

 /**
  * Add gated predicate, if necessary, before the action preceding the rule call.
  */
  protected override String _dataTypeEbnf2(RuleCall it, boolean supportActions) {
    if (supportActions)
      switch rule {
        EnumRule case assigned,
        ParserRule case assigned:
          super._dataTypeEbnf2(it, supportActions)
        EnumRule,
        ParserRule: '''
          «IF isGatedPredicateRequired»«generateGatedPredicate»«ENDIF»
          {
            «newCompositeNode»
          }
          «localVar»=«super._dataTypeEbnf2(it, supportActions)»«getArgumentList(isPassCurrentIntoFragment, !supportActions)»
          {
            $current.merge(«localVar»);
          }
          {
            afterParserOrEnumRuleCall();
          }
        '''
        TerminalRule: '''
          «localVar»=«super._dataTypeEbnf2(it, supportActions)»
          {
            $current.merge(«localVar»);
          }
          {
            «newLeafNode(localVar)»
          }
        '''
        default:
          super._dataTypeEbnf2(it, supportActions)
      }
    else
      super._dataTypeEbnf2(it, supportActions)
  }

 /**
  * Add gated predicate, if necessary, before the action preceding the assignment.
  */
  protected override String _assignmentEbnf(RuleCall it, Assignment assignment, AntlrOptions options, boolean supportActions) {
    if (supportActions)
      switch rule {
        EnumRule,
        ParserRule: '''
          «IF isGatedPredicateRequired»«generateGatedPredicate»«ENDIF»
          {
            «newCompositeNode»
          }
          «assignment.localVar(it)»=«super._assignmentEbnf(it, assignment, options, supportActions)»
          {
            if ($current==null) {
              $current = «assignment.createModelElementForParent»;
            }
            «assignment.setOrAdd»(
              $current,
              "«assignment.feature»",
              «IF assignment.isBooleanAssignment»true«ELSE»«assignment.localVar(it)»«ENDIF»,
              «toStringLiteral»);
            afterParserOrEnumRuleCall();
          }
        '''
        TerminalRule: '''
          «assignment.localVar(it)»=«super._assignmentEbnf(it, assignment, options, supportActions)»
          {
            «newLeafNode(assignment.localVar(it))»
          }
          {
            if ($current==null) {
              $current = «assignment.createModelElement»;
            }
            «assignment.setOrAdd»WithLastConsumed(
              $current,
              "«assignment.feature»",
              «IF assignment.isBooleanAssignment»true«ELSE»«assignment.localVar(it)»«ENDIF»,
              «toStringLiteral»);
          }
        '''
        default:
          throw new IllegalStateException("assignmentEbnf is not supported for " + it)
      }
    else
      super._assignmentEbnf(it, assignment, options, supportActions)
  }

 /**
  * Add gated predicate, if necessary, before the action preceding the cross reference.
  */
  protected override _assignmentEbnf(CrossReference it, Assignment assignment, AntlrOptions options, boolean supportActions) {
    if (supportActions) '''
      «IF isGatedPredicateRequired»«generateGatedPredicate»«ENDIF»
      «IF options.backtrack»
      {
        /* */
      }
      «ENDIF»
      {
        if ($current==null) {
          $current = «assignment.createModelElement»;
        }
      }
      «super._assignmentEbnf(it, assignment, options, supportActions)»'''
    else
      super._assignmentEbnf(it, assignment, options, supportActions)
  }

 /**
  * Add gated predicate, if necessary, before the action preceding the rule call.
  */
  protected override String _ebnf2(RuleCall it, AntlrOptions options, boolean supportActions) {
    if (!supportActions)
      super._ebnf2(it, options, supportActions)
    else
      switch rule : rule {
        EnumRule case assigned,
        ParserRule case assigned:
          super._ebnf2(it, options, supportActions)
        EnumRule,
        ParserRule case rule.originalElement.datatypeRule: '''
          «IF isGatedPredicateRequired»«generateGatedPredicate»«ENDIF»
          «IF options.backtrack»
          {
            /* */
          }
          «ENDIF»
          {
            «newCompositeNode»
          }
          «super._ebnf2(it, options, supportActions)»
          {
            afterParserOrEnumRuleCall();
          }
        '''
        ParserRule: '''
          «IF isGatedPredicateRequired»«generateGatedPredicate»«ENDIF»
          «IF options.backtrack»
          {
            /* */
          }
          «ENDIF»
          {
            «IF isEObjectFragmentRuleCall»
              if ($current==null) {
                $current = «it.createModelElement»;
              }
            «ENDIF»
            «newCompositeNode»
          }
          «localVar»=«super._ebnf2(it, options, supportActions)»
          {
            $current = $«localVar».current;
            afterParserOrEnumRuleCall();
          }
        '''
        TerminalRule: '''
          «localVar»=«super._ebnf2(it, options, supportActions)»
          {
            «newLeafNode(localVar)»
          }
        '''
        default:
          super._ebnf2(it, options, supportActions)
      }
  }

}
