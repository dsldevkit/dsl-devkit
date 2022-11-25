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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Action
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.EnumLiteralDeclaration
import org.eclipse.xtext.EnumRule
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.UnorderedGroup
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming

import static extension org.eclipse.xtext.EcoreUtil2.*
import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil.*

/**
 * This implementation is strongly based on AntlrGrammarGenerator but with a different base class.
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
@Singleton
class AnnotationAwareAntlrGrammarGenerator extends AbstractAnnotationAwareAntlrGrammarGenerator {

  @Inject extension GrammarNaming naming

  @Accessors(PUBLIC_SETTER) String lexerSuperClassName = "";

  protected override getGrammarNaming() {
    naming
  }

  protected override compileParserImports(Grammar it, AntlrOptions options) '''

    import org.eclipse.xtext.*;
    import org.eclipse.xtext.parser.*;
    import org.eclipse.xtext.parser.impl.*;
    import org.eclipse.emf.ecore.util.EcoreUtil;
    import org.eclipse.emf.ecore.EObject;
    «IF !allEnumRules.empty»
    import org.eclipse.emf.common.util.Enumerator;
    «ENDIF»
    import «grammarNaming.getInternalParserSuperClass(it).name»;
    import org.eclipse.xtext.parser.antlr.XtextTokenStream;
    import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
    «IF !allParserRules.map[eAllContentsAsList].flatten.filter(UnorderedGroup).empty && options.backtrack»
    import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper.UnorderedGroupState;
    «ENDIF»
    import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
    import «grammarAccess.name»;
    «super.compileParserImports(it, options)»

  '''

  protected override compileParserMembers(Grammar it, AntlrOptions options) '''

    @«IF combinedGrammar»parser::«ENDIF»members {

    «IF options.backtrack»
    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

    «ENDIF»
        «compileParserMemberDeclarations("private")»

        public «internalParserClass.simpleName»(TokenStream input, «grammarAccess.simpleName» grammarAccess, ParserContext parserContext, «getSemanticPredicatesSimpleName()» predicates) {
            this(input);
            this.grammarAccess = grammarAccess;
            this.predicates = predicates;
            this.parserContext = parserContext;
            parserContext.setTokenStream(input);
            registerRules(grammarAccess.getGrammar());
        }

        «compileParserSetTokenStreamMethod»

        @Override
        protected String getFirstRuleName() {
          return "«allParserRules.head.originalElement.name»";
        }

        @Override
        protected «grammarAccess.simpleName» getGrammarAccess() {
          return grammarAccess;
        }

    }
  '''

  protected override compileRuleCatch(Grammar it, AntlrOptions options) '''

    @rulecatch {
        catch (RecognitionException re) {
            recover(input,re);
            appendSkippedTokens();
        }
    }
  '''

  override protected shouldBeSkipped(TerminalRule it, Grammar grammar) {
    false
  }

  protected override dispatch compileRule(ParserRule it, Grammar grammar, AntlrOptions options) '''
    «IF isValidEntryRule()»
      «compileEntryRule(grammar, options)»
    «ENDIF»

    «compileEBNF(options)»
  '''

  protected def String compileEntryRule(ParserRule it, Grammar grammar, AntlrOptions options) '''
    // Entry rule «originalElement.entryRuleName»
    «originalElement.entryRuleName» returns «compileEntryReturns(options)»«compileEntryInit(options)»:
      { «newCompositeNode» }
      iv_«originalElement.ruleName»=«ruleName»«defaultArgumentList»
      { $current=$iv_«ruleName».current«IF originalElement.datatypeRule».getText()«ENDIF»; }
      EOF;
    «compileEntryFinally(options)»
  '''

  protected def compileEntryReturns(ParserRule it, AntlrOptions options) {
    if (originalElement.datatypeRule)
      return '[String current=null]'
    else
      return '''[«currentType» current=null]'''
  }


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

  protected def compileReturns(AbstractRule it, AntlrOptions options) {
    switch it {
      EnumRule:
        '[Enumerator current=null]'
      ParserRule case originalElement.datatypeRule:
        '[AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]'
      ParserRule case originalElement.isEObjectFragmentRule:
        '''[«currentType» current=in_current]'''
      ParserRule:
        '''[«currentType» current=null]'''
      default:
        throw new IllegalStateException("Unexpected rule: " + it)
    }
  }

  protected override String _dataTypeEbnf2(Keyword it, boolean supportActions) {
    if (supportActions) '''
      kw=«super._dataTypeEbnf2(it, supportActions)»
      {
        $current.merge(kw);
        «newLeafNode("kw")»
      }
    '''
    else
      super._dataTypeEbnf2(it, supportActions)
  }

  protected override String _ebnf2(Action it, AntlrOptions options, boolean supportActions) {
    if (supportActions) '''
      «IF options.backtrack»
      {
        /* */
      }
      «ENDIF»
      {
        $current = forceCreateModelElement«IF feature !== null»And«setOrAdd.toFirstUpper»«ENDIF»(
          grammarAccess.«originalElement.grammarElementAccess»,
          $current);
      }
    '''
    else
      super._ebnf2(it, options, supportActions)
  }

  protected override String _ebnf2(Keyword it, AntlrOptions options, boolean supportActions) {
    if (!supportActions)
      super._ebnf2(it, options, supportActions)
    else if (assigned) '''
      «super._ebnf2(it, options, supportActions)»
      {
        «newLeafNode(containingAssignment.localVar(it))»
      }
    '''
    else '''
      «localVar»=«super._ebnf2(it, options, supportActions)»
      {
        «newLeafNode(localVar)»
      }
    '''
  }

  override protected _ebnf2(EnumLiteralDeclaration it, AntlrOptions options, boolean supportActions) {
    if (!supportActions)
      super._ebnf2(it, options, supportActions)
    else '''
      «localVar»=«super._ebnf2(it, options, supportActions)»
      {
        $current = grammarAccess.«grammarElementAccess(originalElement)».getEnumLiteral().getInstance();
        «newLeafNode(localVar)»
      }
    '''
  }

  protected override String crossrefEbnf(AbstractRule it, RuleCall call, CrossReference ref, boolean supportActions) {
    if (supportActions)
      switch it {
        EnumRule,
        ParserRule: '''
          {
            «ref.newCompositeNode»
          }
          «ruleName»«call.getArgumentList(isPassCurrentIntoFragment, !supportActions)»
          {
            afterParserOrEnumRuleCall();
          }
        '''
        TerminalRule: '''
          «ref.containingAssignment.localVar»=«ruleName»
          {
            «ref.newLeafNode(ref.containingAssignment.localVar)»
          }
        '''
        default:
          throw new IllegalStateException("crossrefEbnf is not supported for " + it)
      }
    else
      super.crossrefEbnf(it, call, ref, supportActions)
  }

  override protected _assignmentEbnf(AbstractElement it, Assignment assignment, AntlrOptions options, boolean supportActions) {
    if (supportActions) '''
      «assignment.localVar(it)»=«super._assignmentEbnf(it, assignment, options, supportActions)»
      {
        if ($current==null) {
          $current = «assignment.createModelElement»;
        }
        «assignment.setOrAdd»WithLastConsumed($current, "«assignment.feature»", «
            assignment.localVar(it)»«IF assignment.isBooleanAssignment» != null«ENDIF
            », «assignment.terminal.toStringLiteral»);
      }
    '''
    else
      super._assignmentEbnf(it, assignment, options, supportActions)
  }

  override protected isPassCurrentIntoFragment() {
    return true
  }

  protected def createModelElement(EObject grammarElement) '''
    createModelElement(grammarAccess.«grammarElement.containingParserRule.originalElement.grammarElementAccess»)'''

  protected def createModelElementForParent(EObject grammarElement) '''
    createModelElementForParent(grammarAccess.«grammarElement.containingParserRule.originalElement.grammarElementAccess»)'''

  protected def newCompositeNode(EObject it) '''newCompositeNode(grammarAccess.«originalElement.grammarElementAccess»);'''

  protected def newLeafNode(EObject it, String token) '''newLeafNode(«token», grammarAccess.«originalElement.grammarElementAccess»);'''

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
  * Inserts validating predicate only. Gated predicates will be inserted in alternatives if needed.
  */
  protected override String compileEBNF(AbstractRule it, AntlrOptions options) '''
    // Rule «originalElement.name»
    «ruleName»«compileInit(options)»:
      «IF it instanceof ParserRule && originalElement.datatypeRule»
        «IF hasValidatingPredicate»«generateValidatingPredicate»«ENDIF»
        «dataTypeEbnf(alternatives, true)»
      «ELSE»
        «ebnf(alternatives, options, true)»
      «ENDIF»
    ;
    «compileFinally(options)»
  '''

  protected  override String dataTypeEbnf(AbstractElement it, boolean supportActions) '''
    «IF mustBeParenthesized»(
      «IF hasNoBacktrackAnnotation»
        // Enclosing rule was annotated with @NoBacktrack
        options { backtrack=false; }:
      «ENDIF»
      «dataTypeEbnfPredicate»«dataTypeEbnf2(supportActions)»
    )«ELSE»«dataTypeEbnf2(supportActions)»«ENDIF»«cardinality»
  '''

  protected override String ebnf(AbstractElement it, AntlrOptions options, boolean supportActions) '''
    «IF mustBeParenthesized»(
      «IF hasNoBacktrackAnnotation»
        // Enclosing rule was annotated with @NoBacktrack
        options { backtrack=false; }:
      «ENDIF»
      «ebnfPredicate(options)»«ebnf2(options, supportActions)»
    )«ELSE»«ebnf2(options, supportActions)»«ENDIF»«cardinality»
  '''



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
              «assignment.localVar(it)»«IF assignment.isBooleanAssignment» != null«ENDIF»,
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
              «assignment.localVar(it)»«IF assignment.isBooleanAssignment» != null«ENDIF»,
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

  protected override compileLexerImports(Grammar it, AntlrOptions options) '''

    // Hack: Use our own Lexer superclass by means of import.
    // Currently there is no other way to specify the superclass for the lexer.
    «IF !lexerSuperClassName.empty»
      import «lexerSuperClassName»;
    «ELSE»
      import «grammarNaming.getLexerSuperClass(it)»;
    «ENDIF»
  '''
}