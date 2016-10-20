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
package com.avaloq.tools.ddk.xtext.format.jvmmodel

import com.google.common.collect.Iterables
import com.google.inject.Inject
import java.util.List
import java.util.Map
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.EnumRule
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.common.types.JvmAnnotationReference
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmMember
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.generator.Naming
import org.eclipse.xtext.generator.grammarAccess.GrammarAccess
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

import static com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil.*
import static org.eclipse.xtext.GrammarUtil.*

import static extension com.avaloq.tools.ddk.xtext.format.generator.FormatGeneratorUtil.*
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration
import com.avaloq.tools.ddk.xtext.format.format.Rule
import com.avaloq.tools.ddk.xtext.format.format.Matcher
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule
import com.avaloq.tools.ddk.xtext.format.format.IndentLocator
import com.avaloq.tools.ddk.xtext.format.format.ColumnLocator
import com.avaloq.tools.ddk.xtext.format.format.Constant
import com.avaloq.tools.ddk.xtext.format.format.MatcherList
import com.avaloq.tools.ddk.xtext.format.format.OffsetLocator
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup
import com.avaloq.tools.ddk.xtext.format.format.StringValue
import com.avaloq.tools.ddk.xtext.format.format.IntValue
import com.avaloq.tools.ddk.xtext.format.format.NoFormatLocator
import com.avaloq.tools.ddk.xtext.format.format.Locator
import com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator
import com.avaloq.tools.ddk.xtext.format.format.SpaceLocator
import com.avaloq.tools.ddk.xtext.format.format.RightPaddingLocator
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference
import com.avaloq.tools.ddk.xtext.format.format.KeywordPair
import com.avaloq.tools.ddk.xtext.format.format.SpecificDirective
import com.avaloq.tools.ddk.xtext.format.format.ContextFreeDirective
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator
import com.avaloq.tools.ddk.xtext.format.format.MatcherType
import org.eclipse.xtext.xtext.RuleNames
import com.avaloq.tools.ddk.xtext.formatting.DdkFormattingConfig
import com.avaloq.tools.ddk.xtext.formatting.AbstractDdkFormatter

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
class FormatJvmModelInferrer extends AbstractModelInferrer {
  @Inject extension Naming naming
  @Inject extension JvmTypesBuilder
  @Inject extension TypeReferences
  @Inject extension GrammarAccess grammarAccess
  @Inject TypesFactory typesFactory;
  @Inject XbaseCompiler xbaseCompiler;

  private static final String BASE_FORMATTER_CLASS_NAME = AbstractDdkFormatter.name
  private static final String BASE_FORMAT_CONFIG = DdkFormattingConfig.name

  private static final String METHOD_ACTIVATE= 'activate'
  private static final String METHOD_CALCULATE= 'calculateParameter'

  private static final String PARAMETER_CONFIG = 'config'
  private static final String PARAMETER_ELEMENTS = 'elements'
  private static final String PARAMETER_RULE = 'rule'
  private static final String PARAMETER_GRAMMAR_ACCESS = 'grammarAccess'
  private static final String PARAMETER_CONTEXT = 'context'
  private static final String PARAMETER_COLUMN = 'currentColumn'

  /**
   * The dispatch method {@code infer} is called for each instance of the
   * given element's type that is contained in a resource.
   *
   * @param element
   *      the model to create one or more {@link JvmDeclaredType declared types} from.
   * @param acceptor
   *      each created {@link JvmDeclaredType type} without a container should be passed to the acceptor in order
   *      get attached to the current resource. The acceptor's {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
   *      accept(..)} method takes the constructed empty type for the pre-indexing phase. This one is further initialized in the
   *      indexing phase using the closure you pass to the returned
   *      {@link IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1) initializeLater(..)}.
   * @param isPreIndexingPhase
   *      whether the method is called in a pre-indexing phase, i.e. when the global index is not yet fully updated. You must not
   *      rely on linking using the index if isPreIndexingPhase is {@code true}.
   */
  def dispatch void infer(FormatConfiguration format, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
    RuleNames.ensureAdapterInstalled(format.targetGrammar)
    if(!format.targetGrammar.eIsProxy) {
      acceptor.accept(
        format.toClass(getFormatterName(format.targetGrammar, "Abstract").toSimpleName), [
          inferClass(format, it)
          inferConstants(format, it)
          inferGetGrammarAccess(format, it)
          inferConfigureDdkFormatting(format, it)
          inferInit(format, it)
          inferRules(format, it)
          inferLocatorActivators(format, it)
        ]
      )
    }
  }

  def inferClass(FormatConfiguration format, JvmGenericType it) {
    documentation = '''The abstract formatting configuration for «format.targetGrammar?.name?.toPackageName».«format.targetGrammar?.name?.toSimpleName» as declared in «format.targetGrammar?.name?.toSimpleName».format.'''
    if(format.formatterBaseClass != null) {
      superTypes += typeRef(format.formatterBaseClass.packageName + '.' + format.formatterBaseClass.simpleName)
    } else {
      superTypes += typeRef(BASE_FORMATTER_CLASS_NAME)
    }
    packageName = getFormatterName(format.targetGrammar, "").toPackageName
    abstract = true
  }

  def inferConstants(FormatConfiguration format, JvmGenericType it) {
    if(!format.allConstants.isEmpty) {
      members += format.allConstants.map(c|createConstant(format, c));
    }
  }

  def inferGetGrammarAccess(FormatConfiguration format, JvmGenericType it) {
    members += format.toMethod('getGrammarAccess', format.targetGrammar.gaFQName.getTypeForName(format.targetGrammar)) [
      visibility = JvmVisibility::PROTECTED
      val JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format)
      if(overrideAnnotation != null) {
        annotations += overrideAnnotation;
      }
      body = [append('''return («format.targetGrammar.gaSimpleName») super.getGrammarAccess();''')]
    ]
  }

  def inferConfigureDdkFormatting(FormatConfiguration format, JvmGenericType it) {
    members += format.toMethod('configureDdkFormatting', typeRef('void')) [
      visibility = JvmVisibility::PROTECTED
      val JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format)
      if(overrideAnnotation != null) {
        annotations += overrideAnnotation;
      }
      parameters += format.toParameter(PARAMETER_CONFIG, typeRef(BASE_FORMAT_CONFIG))
      body = [append('''init(config, getGrammarAccess());''')]
    ]
  }

  def inferInit(FormatConfiguration format, JvmGenericType it) {
    members += format.toMethod('init', typeRef('void')) [
      documentation = generateJavaDoc('Calls all configXyz methods declared in this class.', newLinkedHashMap(
        PARAMETER_CONFIG         -> 'the format configuration',
        PARAMETER_GRAMMAR_ACCESS -> 'the grammar access for the grammar'
      ))
      visibility = JvmVisibility::PROTECTED
      parameters += format.toParameter(PARAMETER_CONFIG, typeRef(BASE_FORMAT_CONFIG))
      parameters += format.toParameter(PARAMETER_GRAMMAR_ACCESS, typeRef(format.targetGrammar.gaFQName))
      body = [
        val rules = listConfigRules(format)
        for (i : 0 ..< rules.length()) {
          if(i != 0) newLine
          append(rules.get(i))
        }
      ]
    ]
  }

  def inferRules(FormatConfiguration format, JvmGenericType it) {
    members += format.parserRules.map(c|createRule(format, c)).flatten;
    members += format.enumRules.map(c|createRule(format, c)).flatten;
    members += format.terminalRules.map(c|createRule(format, c)).flatten;
    if(format.wildcardRule != null) {
      members += format.toMethod('configFindElements', typeRef('void')) [
        documentation = generateJavaDoc('Configuration for IGrammarAccess.findXyz() methods.', newLinkedHashMap(
          PARAMETER_CONFIG   -> 'the format configuration',
          PARAMETER_ELEMENTS -> 'the grammar access for the grammar'
        ))
        visibility = JvmVisibility::PROTECTED
        parameters += format.toParameter(PARAMETER_CONFIG, typeRef(BASE_FORMAT_CONFIG))
        parameters += format.toParameter(PARAMETER_ELEMENTS, typeRef(getGrammar(format.targetGrammar).gaFQName))
        body = [
          val directives = format.wildcardRule.directives.map(d|directive(d, format.wildcardRule.getRuleName).toString)
          append(fixLastLine(directives.join))
        ]
      ]
    }
  }

  def inferLocatorActivators(FormatConfiguration format, JvmGenericType it) {
    var List<Rule> rules = newLinkedList
    rules += format.parserRules + format.terminalRules + format.enumRules
    rules += format.wildcardRule
    for (rule : rules) {
      var List<EObject> directives = newLinkedList
      switch rule {
        GrammarRule: directives += rule.directives
        WildcardRule: directives += rule.directives
      }
      for (directive : directives.filterNull) {
        for (matcher : collectMatchers(directive)) {
          if (matcher.locator instanceof ColumnLocator && (matcher.locator as ColumnLocator).parameter != null) {
            members += createParameterCalculatorInnerClass(format, rule, directive, matcher, (matcher.locator as ColumnLocator).parameter, it)
          }
          if (matcher.locator instanceof IndentLocator && (matcher.locator as IndentLocator).parameter != null) {
            members += createParameterCalculatorInnerClass(format, rule, directive, matcher, (matcher.locator as IndentLocator).parameter, it)
          }
          if (matcher.condition != null) {
            members += createLocatorActivatorInnerClass(format, rule, directive, matcher, it)
          }
        }
      }
    }
  }

  def createLocatorActivatorInnerClass(FormatConfiguration format, Rule rule, EObject directive, Matcher matcher, JvmGenericType type) {
    format.toClass(rule.getLocatorActivatorName(directive, matcher)) [
      static = true
      final = true
      visibility = JvmVisibility::PROTECTED
      superTypes += format.getLocatorActivatorSuperType(rule)
      members += matcher.toMethod(METHOD_ACTIVATE, format.getLocatorActivatorReturnType) [
        parameters += format.toParameter(PARAMETER_CONTEXT, getGrammarElementNameFromSelf(rule).getTypeForName(format))
        parameters += createCurrenctColumnParameter
        if(format.eResource != matcher.eResource) {
          body = [xbaseCompiler.compile(matcher.condition, it, format.getLocatorActivatorReturnType, null)]
        } else {
          body = matcher.condition
        }
      ]
    ]
  }

  def createCurrenctColumnParameter(){
    var result = typesFactory.createJvmFormalParameter()
    result.setName(PARAMETER_COLUMN)
    result.setParameterType(typeRef(typeof(Integer)))
    return result
  }

  def createParameterCalculatorInnerClass(FormatConfiguration format, Rule rule, EObject directive, Matcher matcher, XExpression parameterCalculation, JvmGenericType type) {
    format.toClass(rule.getParameterCalculatorName(directive, matcher)) [
      static = true
      final = true
      visibility = JvmVisibility::PROTECTED
      superTypes += format.getParameterCalculatorSuperType(rule)
      members += matcher.toMethod(METHOD_CALCULATE, format.parameterCalculatorReturnType) [
        parameters += format.toParameter(PARAMETER_CONTEXT, getGrammarElementNameFromSelf(rule).getTypeForName(format))
        parameters += createCurrenctColumnParameter
        if(format.eResource != matcher.eResource) {
          body = [xbaseCompiler.compile(parameterCalculation, it, format.parameterCalculatorReturnType, null)]
        } else {
          body = parameterCalculation
        }
      ]
    ]
  }

  def listConfigRules(FormatConfiguration format) {
    val configRules = newArrayList;
    if(format.wildcardRule != null) {
      configRules += '''configFindElements(config, grammarAccess);'''
    }
    for (rule : format.parserRules) {
      configRules += '''config«rule.targetRule.name»(config, grammarAccess.«rule.targetRule.gaElementsAccessor»);'''
    }
    for (rule : format.enumRules) {
      configRules += '''config«rule.targetRule.name»(config, grammarAccess.«rule.targetRule.gaRuleAccessor»);'''
    }
    for (rule : format.terminalRules) {
      configRules += '''config«rule.targetRule.name»(config, grammarAccess.«rule.targetRule.gaRuleAccessor»);'''
    }
    configRules
  }

  def generateJavaDoc(String description, Map<String, String> parameters) {
    '''
    «description»

    «FOR parameter : parameters.entrySet()»
    @param «parameter.key»
         - «parameter.value»
    «ENDFOR»
    '''.toString
  }

  def JvmAnnotationReference createOverrideAnnotation(FormatConfiguration format) {
    val annotationTypeRef = typeRef(typeof(Override));
    var JvmAnnotationReference overrideAnnotation = null
    if(annotationTypeRef != null) {
      val annotationType = annotationTypeRef.type;
      overrideAnnotation = typesFactory.createJvmAnnotationReference();
      overrideAnnotation.annotation = annotationType as JvmAnnotationType;
    }
    overrideAnnotation
  }

  def JvmMember createConstant(FormatConfiguration configuration, Constant constant) {
    switch constant {
      case constant.stringValue != null:
        constant.toField(constant.name, "String".getTypeForName(constant)) [
          documentation = locatorString(constant)
          static = true
          final = true
          visibility = JvmVisibility::PROTECTED
          initializer = [append('"' + constant.stringValue + '"')]
        ]
      case constant.intValue != null:
        constant.toField(constant.name, "int".getTypeForName(constant)) [
          documentation = locatorString(constant)
          static = true
          final = true
          visibility = JvmVisibility::PROTECTED
          initializer = [append(constant.intValue.toString)]
        ]
    }
  }

  def collectMatchers(EObject directive) {
    var List<Matcher> matchers = newLinkedList
    switch directive {
      GroupBlock: if(directive.matcherList != null) matchers += directive.matcherList.matchers
      SpecificDirective: if(directive.matcherList != null) matchers += directive.matcherList.matchers
      ContextFreeDirective: if(directive.matcherList != null) matchers += directive.matcherList.matchers
      KeywordPair: {
        if(directive.leftMatchers != null) matchers += directive.leftMatchers
        if(directive.rightMatchers != null) matchers += directive.rightMatchers
      }
    }
    matchers
  }

  def getLocatorActivatorReturnType(FormatConfiguration formatConfiguration) {
    typeRef(typeof(boolean))
  }

  def getParameterCalculatorReturnType(FormatConfiguration formatConfiguration) {
    typeRef(typeof(int))
  }

  // getLocatorActivatorSuperType dispatch
  def dispatch getLocatorActivatorSuperType(FormatConfiguration formatConfiguration, GrammarRule rule) {
    typeRef(typeof(LocatorActivator), getGrammarElementNameFromSelf(rule).getTypeForName(formatConfiguration))
  }
  def dispatch getLocatorActivatorSuperType(FormatConfiguration formatConfiguration, WildcardRule rule) {
    typeRef(typeof(LocatorActivator), getGrammarElementNameFromSelf(rule).getTypeForName(formatConfiguration))
  }

  def dispatch getParameterCalculatorSuperType(FormatConfiguration formatConfiguration, GrammarRule rule) {
    typeRef(typeof(LocatorParameterCalculator), getGrammarElementNameFromSelf(rule).getTypeForName(formatConfiguration))
  }
  def dispatch getParameterCalculatorSuperType(FormatConfiguration formatConfiguration, WildcardRule rule) {
    typeRef(typeof(LocatorParameterCalculator), getGrammarElementNameFromSelf(rule).getTypeForName(formatConfiguration))
  }

  // getGrammarElementNameFromSelf dispatch
  def dispatch String getGrammarElementNameFromSelf(GrammarRule rule) {
    var ruleName = rule.getRuleName
    if (ruleName != rule.targetRule?.type?.classifier?.name) {
      ruleName = rule.targetRule.type.classifier.name
    }
    var metamodel = rule.targetRule?.type?.metamodel
    if(metamodel == null) {
      return ruleName
    }
    else if(metamodel.alias == null) {
      return EcoreUtil2::getContainerOfType(metamodel, typeof(Grammar))?.name?.toLowerCase + '.' + ruleName
    } else {
      return 'com.avaloq.tools.dsl.' + metamodel.alias + '.' + metamodel.alias + '.' + ruleName
    }

  }
  def dispatch String getGrammarElementNameFromSelf(WildcardRule rule) {
    EObject.name
  }

  def int getMatcherIndex(Matcher matcher){
    val MatcherList matcherList=EcoreUtil2::getContainerOfType(matcher, typeof(MatcherList))
    return matcherList.matchers.indexOf(matcher);
  }

  def String getLocatorActivatorName(EObject rule, EObject directive, Matcher matcher) {
    ('ActivatorFor' + rule.getRuleName + matcher.getMatcherName(directive)).replace("Impl", "")
  }

  def String getLocatorActivatorName(String partialName, Matcher matcher) {
    ('ActivatorFor' + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.locator) + matcher.type.name().toLowerCase.toFirstUpper).replace("Impl", "")
  }

  def String getParameterCalculatorName(EObject rule, EObject directive, Matcher matcher) {
    ('ParameterCalculatorFor' + rule.getRuleName + matcher.getMatcherName(directive)).replace("Impl", "")
  }


  def String getParameterCalculatorName(String partialName, Matcher matcher) {
    ('ParameterCalculatorFor' + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.locator) + matcher.type.name().toLowerCase.toFirstUpper).replace("Impl", "")
  }

  // getRuleName dispatch
  def dispatch String getRuleName(GrammarRule rule)  { rule.targetRule?.name }
  def dispatch String getRuleName(WildcardRule rule) { "Wildcard" }
  def dispatch String getRuleName(EObject rule)      { EObject.simpleName}

  def String getMatcherName(Matcher matcher, EObject directive) {
    getDirectiveName(directive) + getMatcherIndex(matcher) + getLocatorName(matcher.locator) + matcher.type.name().toLowerCase.toFirstUpper
  }

  def String getLocatorName(EObject locator) {
    locator?.class.simpleName ?: ""
  }

  def convertNonAlphaNumeric(String str) {
    str.replaceAll("[\\W]|_", String.valueOf(str.hashCode))
  }

  // getDirectiveName dispatch
  def dispatch String getDirectiveName(GroupBlock directive) {
    val GrammarRule grammarRule = EcoreUtil2::getContainerOfType(directive, typeof(GrammarRule))
    val directives = newArrayList(Iterables.filter(grammarRule.directives, GroupBlock));
    "Group" + String.valueOf(directives.indexOf(directive) + 1)
  }
  def dispatch String getDirectiveName(SpecificDirective directive) {
    var directiveName = ''
    for (grammarElementReference : directive.grammarElements) {
      if(grammarElementReference.assignment != null) {
        directiveName = directiveName + grammarElementReference.assignment.feature.toFirstUpper
      }
      if(grammarElementReference.ruleCall != null) {
        directiveName = directiveName + grammarElementReference.ruleCall.rule.name.toFirstUpper
      }
      if(grammarElementReference.rule != null) {
        directiveName = directiveName + grammarElementReference.rule.name.toFirstUpper
      }
      if(grammarElementReference.keyword != null) {
        directiveName = directiveName + grammarElementReference.keyword.value.convertNonAlphaNumeric.toFirstUpper
      }
      if(grammarElementReference.self != null) {
        directiveName = directiveName + "Self"
      }
    }
    directiveName
  }
  def dispatch String getDirectiveName(ContextFreeDirective directive) {
    var directiveName = ''
    for (grammarElementLookup : directive.grammarElements) {
      if(grammarElementLookup.rule != null) {
        directiveName = directiveName + grammarElementLookup.rule.name.toFirstUpper
      }
      if(grammarElementLookup.keyword != null) {
        directiveName = directiveName + grammarElementLookup.keyword.convertNonAlphaNumeric.toFirstUpper
      }
    }
    directiveName
  }
  def dispatch String getDirectiveName(KeywordPair directive) {
    directive.left.convertNonAlphaNumeric + directive.right.convertNonAlphaNumeric
  }
  def dispatch String getDirectiveName(EObject directive) {
    String.valueOf(directive.hashCode)
  }

  def Iterable<JvmMember> createRule(FormatConfiguration format, GrammarRule rule) {
    val List<JvmMember> members = newArrayList
    members += format.toMethod('config' + rule.targetRule.name, typeRef('void')) [
      final = false
      visibility = JvmVisibility::PROTECTED
      parameters += format.toParameter(PARAMETER_CONFIG, typeRef(BASE_FORMAT_CONFIG))
      switch rule.targetRule {
        ParserRule: {
          val ruleName = (getGrammar(rule.targetRule).gaFQName + "$" + rule.targetRule.gaRuleAccesorClassName)
          parameters += format.toParameter(PARAMETER_ELEMENTS, ruleName.getTypeForName(rule.targetRule))
          documentation = generateJavaDoc('''Configuration for «rule.targetRule.name».''', newLinkedHashMap(
            PARAMETER_CONFIG   -> 'the format configuration',
            PARAMETER_ELEMENTS -> '''the grammar access for «rule.targetRule.name» elements'''
          ))
        }
        EnumRule: {
          parameters += format.toParameter(PARAMETER_RULE, EnumRule.name.getTypeForName(rule.targetRule))
          documentation = generateJavaDoc('''Configuration for «rule.targetRule.name».''', newLinkedHashMap(
            PARAMETER_CONFIG -> 'the format configuration',
            PARAMETER_RULE   -> '''the enum rule for «rule.targetRule.name»'''
          ))
        }
        TerminalRule: {
          parameters += format.toParameter(PARAMETER_RULE, TerminalRule.name.getTypeForName(rule.targetRule))
          documentation = generateJavaDoc('''Configuration for «rule.targetRule.name».''', newLinkedHashMap(
            PARAMETER_CONFIG -> 'the format configuration',
            PARAMETER_RULE   -> '''the terminal rule for «rule.targetRule.name»'''
          ))
        }
      }
      body = [
        val directives = rule.directives.map(d|directive(d, rule.getRuleName).toString)
        append(fixLastLine(directives.join))
      ]
    ]
    return members;
  }

  def fixLastLine(String content) {
    if(content.endsWith("\r\n")) {
      return content.substring(0, content.length - 2)
    } else {
      return content
    }
  }

  // directive dispatch
  def dispatch directive(SpecificDirective dir, String partialName) { matchReference(dir.matcherList, dir.grammarElements, partialName + getDirectiveName(dir)) }
  def dispatch directive(ContextFreeDirective dir, String partialName) { matchLookup(dir.matcherList, dir.grammarElements, partialName + getDirectiveName(dir)) }
  def dispatch CharSequence directive(GroupBlock dir, String partialName) {
    if(dir.matcherList != null) {
      matchReference(dir.matcherList, new BasicEList(#[dir.grammarElement]), partialName + getDirectiveName(dir))
    } else if (dir.subGroup != null) {
      directive(dir.subGroup, partialName+ getDirectiveName(dir))
    } else {
      '''«FOR d : dir.directives»«directive(d, partialName + getDirectiveName(dir))»«ENDFOR»'''
    }
  }
  def dispatch directive(KeywordPair dir, String partialName) '''
    // «locatorString(dir)»
    for (final org.eclipse.xtext.util.Pair<org.eclipse.xtext.Keyword, org.eclipse.xtext.Keyword> pair : elements.findKeywordPairs("«dir.left»", "«dir.right»")) {
    «FOR matcher : dir.leftMatchers»
      «matchLookupPartial(matcher.locator, matcher, "pair.getFirst()", partialName + getDirectiveName(dir))»
    «ENDFOR»
    «FOR matcher : dir.rightMatchers»
      «matchLookupPartial(matcher.locator, matcher, "pair.getSecond()", partialName + getDirectiveName(dir))»
    «ENDFOR»
    }
  '''
  def dispatch directive(Object dir, String partialName) {
    throw new UnsupportedOperationException("Unknown directive " + dir.class.name)
  }

  def matchLookup(MatcherList matcherList, EList<GrammarElementLookup> elements, String partialName) '''
    «IF !elements.isEmpty»
      «IF !elements.filter[e|e.rule != null].isEmpty»
        // «locatorString(matcherList)»
        for (org.eclipse.xtext.RuleCall ruleCall : elements.findRuleCalls(«FOR element : elements.filter(e|e.rule != null) SEPARATOR ', '»elements.«element.rule.gaRuleAccessor»«ENDFOR»)) {
          «FOR matcher : matcherList.matchers»
            «matchLookupPartial(matcher.locator, matcher, "ruleCall", partialName)»
          «ENDFOR»
        }
      «ENDIF»
      «IF !elements.filter(e|e.keyword != null).isEmpty»
        // «locatorString(matcherList)»
        for (org.eclipse.xtext.Keyword keyword : elements.findKeywords(«FOR element : elements.filter(e|e.keyword != null) SEPARATOR ', '»"«element.keyword»"«ENDFOR»)) {
          «FOR matcher : matcherList.matchers»
            «matchLookupPartial(matcher.locator, matcher, "keyword", partialName)»
          «ENDFOR»
        }
      «ENDIF»
    «ENDIF»
  '''

  // matchLookupPartial dispatch
  def dispatch matchLookupPartial(ColumnLocator columnLocator, Matcher matcher, String eobjectTypeName, String partialName) '''
    «IF matcher.type.literal.compareTo("before") == 0»
      config.setColumn(«columnLocator.value.getValueOrConstant», «columnLocator.fixed», «columnLocator.relative», «columnLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).before(«eobjectTypeName»);  // «locatorString(columnLocator)»
      config.setColumn(«columnLocator.value.getValueOrConstant», «columnLocator.fixed», «columnLocator.relative», «columnLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).after(«eobjectTypeName»);  // «locatorString(columnLocator)»
    «ELSE»
      config.setColumn(«columnLocator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).«matcherType(matcher.type)»(«eobjectTypeName»); // «locatorString(columnLocator)»
    «ENDIF»
  '''
  def dispatch matchLookupPartial(OffsetLocator offsetLocator, Matcher matcher, String eobjectTypeName, String partialName) '''
    «IF matcher.type.literal.compareTo("before") == 0»
      config.setColumn(«offsetLocator.value.getValueOrConstant», «offsetLocator.fixed», true, «offsetLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).before(«eobjectTypeName»);  // «locatorString(offsetLocator)»
      config.setColumn(«offsetLocator.value.getValueOrConstant», «offsetLocator.fixed», true, «offsetLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).after(«eobjectTypeName»);  // «locatorString(offsetLocator)»
    «ELSE»
      config.setOffset(«offsetLocator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).«matcherType(matcher.type)»(«eobjectTypeName»); // «locatorString(offsetLocator)»
    «ENDIF»
  '''
  def dispatch matchLookupPartial(EObject locator, Matcher matcher, String eobjectTypeName, String partialName) '''
    config.«locator(matcher, matcher.locator, partialName)».«matcherType(matcher.type)»(«eobjectTypeName»);'''

  def matchReference(MatcherList matcherList, EList<? extends EObject> elements, String partialName) '''
    «IF !elements.isEmpty»
      «FOR matcher : matcherList.matchers»
        «IF matcher.type.isTwoArgumentMatcherType»
          «match(matcher, elements.get(0), elements.get(1), matcher.locator, partialName)»
        «ELSE»
          «FOR e : elements»«match(matcher, e, matcher.locator, partialName)»«ENDFOR»
        «ENDIF»
      «ENDFOR»
    «ENDIF»
  '''

  def match(Matcher matcher, EObject element1, EObject element2, EObject locator, String partialName) '''
    config.«locator(matcher, matcher.locator, partialName)».«matcherType(matcher.type)»(«elementAccess(element1)», «elementAccess(element2)»); // «locatorString(matcher)»
  '''

  // match dispatch
  def dispatch match(Matcher matcher, EObject element, Locator locator, String partialName) '''
    config.«locator(matcher, matcher.locator, partialName)».«matcherType(matcher.type)»(«elementAccess(element)»); // «locatorString(matcher)»
  '''
  def dispatch match(Matcher matcher, EObject element, NoFormatLocator locator, String partialName) '''
    config.«locator(matcher, matcher.locator, partialName)».around(«elementAccess(element)»); // «locatorString(matcher)»
  '''
  def dispatch match(Matcher matcher, EObject element, ColumnLocator locator, String partialName) '''
    «IF matcher.type.literal.compareTo("before") == 0»
      «IF locator.parameter != null»
        config.setColumn(«locator.fixed», «locator.relative», «locator.nobreak», new «getParameterCalculatorName(partialName, matcher)»()«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).before(«elementAccess(element)»);  // «locatorString(matcher)»
        config.setColumn(«locator.fixed», «locator.relative», «locator.nobreak», new «getParameterCalculatorName(partialName, matcher)»()«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).after(«elementAccess(element)»);  // «locatorString(matcher)»
      «ELSE»
        config.setColumn(«locator.value.getValueOrConstant», «locator.fixed», «locator.relative», «locator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).before(«elementAccess(element)»);  // «locatorString(matcher)»
        config.setColumn(«locator.value.getValueOrConstant», «locator.fixed», «locator.relative», «locator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).after(«elementAccess(element)»);  // «locatorString(matcher)»
      «ENDIF»
    «ELSE»
      «IF locator.parameter != null»
        config.setColumn(new «getParameterCalculatorName(partialName, matcher)»()«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).«matcherType(matcher.type)»(«elementAccess(element)»); // «locatorString(matcher)»
      «ELSE»
        config.setColumn(«locator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).«matcherType(matcher.type)»(«elementAccess(element)»); // «locatorString(matcher)»
      «ENDIF»
    «ENDIF»
  '''
  def dispatch match(Matcher matcher, EObject element, OffsetLocator locator, String partialName) '''
    «IF matcher.type.literal.compareTo("before") == 0»
      config.setColumn(«locator.value.getValueOrConstant», «locator.fixed», true, «locator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).before(«elementAccess(element)»);  // «locatorString(matcher)»
      config.setColumn(«locator.value.getValueOrConstant», «locator.fixed», true, «locator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).after(«elementAccess(element)»);  // «locatorString(matcher)»
    «ELSE»
      config.setOffset(«locator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»).«matcherType(matcher.type)»(«elementAccess(element)»); // «locatorString(matcher)»
    «ENDIF»
  '''
  def dispatch match(Matcher matcher, EObject element, IndentLocator locator, String partialName) '''
    config.«locator(matcher, matcher.locator, partialName)».«matcherType(matcher.type)»(«elementAccess(element)»); // «locatorString(matcher)»
  '''

  def matcherType(MatcherType matcherType) { matcherType.literal }

  // elementAccess dispatch
  def dispatch elementAccess(GrammarElementLookup grammarElementLookup) '''
    «IF grammarElementLookup.rule != null»elements.findRuleCalls(«grammarElementLookup.rule.gaElementsAccessor»)«ELSEIF grammarElementLookup.keyword != null»elements.findKeywords("«grammarElementLookup.keyword»")«ENDIF»'''
  def dispatch CharSequence elementAccess(GrammarElementReference grammarElementReference) {
    if(grammarElementReference.ruleCall != null) {
      elementAccess(grammarElementReference.ruleCall)
    }
    else if(grammarElementReference.keyword != null) {
      elementAccess(grammarElementReference.keyword)
    }
    else if(grammarElementReference.assignment != null) {
      elementAccess(grammarElementReference.assignment)
    }
    else if(grammarElementReference.self != null) {
      if(grammarElementReference.containedByParserRule) {
        'elements.getRule()'
      }
      else {
        'rule'
      }
    } else if(grammarElementReference.rule != null) {
      elementAccess(grammarElementReference.rule)
    }
  }
  def dispatch elementAccess(AbstractRule abstractRule) '''
    getGrammarAccess().«abstractRule.gaRuleAccessor»'''
  def dispatch elementAccess(AbstractElement abstractElement) '''
    elements.«abstractElement.gaElementAccessor»'''
  def dispatch elementAccess(Object object) {
    throw new UnsupportedOperationException("Unknown Xtext element " + object.class.name)
  }

  // locator dispatch
  def dispatch locator(Matcher matcher, SpaceLocator spaceLocator, String partialName) '''
    «IF spaceLocator.noSpace»setNoSpace(«IF matcher.condition != null»new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)«ELSE»setSpace(«spaceLocator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)«ENDIF»'''
  def dispatch locator(Matcher matcher, RightPaddingLocator rightPaddingLocator, String partialName) '''
    setRightPadding(«rightPaddingLocator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)'''
  def dispatch locator(Matcher matcher, LinewrapLocator linewrapLocator, String partialName) '''
    «IF linewrapLocator.noLinewrap»setNoLinewrap(«IF matcher.condition != null»new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)«ELSE»setLinewrap(«IF linewrapLocator.value != null»«linewrapLocator.value.getValueOrConstant»«IF matcher.condition != null», new «getLocatorActivatorName(
      partialName, matcher)»()«ENDIF»«ELSEIF linewrapLocator.minimum != null»«linewrapLocator.minimum.getValueOrConstant», «linewrapLocator.^default.getValueOrConstant», «linewrapLocator.maximum.getValueOrConstant()»«IF matcher.condition!=null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»«ELSE»«IF matcher.
      condition != null»new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»«ENDIF»)«ENDIF»'''
  def dispatch locator(Matcher matcher, ColumnLocator columnLocator, String partialName) '''
    setColumn(«columnLocator.value.getValueOrConstant», «columnLocator.fixed», «columnLocator.relative», «columnLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)'''
  def dispatch locator(Matcher matcher, OffsetLocator offsetLocator, String partialName) '''
    setColumn(«offsetLocator.value.getValueOrConstant», «offsetLocator.fixed», true, «offsetLocator.nobreak»«IF matcher.condition != null», new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)'''
  def dispatch locator(Matcher matcher, IndentLocator indentLocator, String partialName) '''
    «IF indentLocator.increment»setIndentationIncrement(«ELSE»setIndentationDecrement(«
    ENDIF»«
    IF indentLocator.value != null && (indentLocator.value.reference != null || indentLocator.value.literal > 1)»«indentLocator.value.getValueOrConstant»«
    ELSEIF indentLocator.parameter != null»new «getParameterCalculatorName(partialName, matcher)»()«
    ENDIF»«
    IF matcher.condition != null»«IF indentLocator.value != null || indentLocator.parameter != null»,«ENDIF» new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)'''

  def dispatch locator(Matcher matcher, NoFormatLocator noFormatLocator, String partialName) '''
    setNoFormat(«IF matcher.condition != null»new «getLocatorActivatorName(partialName, matcher)»()«ENDIF»)'''
  def dispatch locator(Matcher matcher, Locator locator, String partialName) {
    throw new UnsupportedOperationException("Unknown locator " + locator.class.name)
  }

  // getValueOrConstant dispatch
  def dispatch getValueOrConstant(StringValue stringValue) {
    if(stringValue.literal == null) {
      stringValue.reference.name
    } else {
      '"' + stringValue.literal + '"'
    }
  }
  def dispatch getValueOrConstant(IntValue intValue) {
    if(intValue.literal == null) {
      intValue.reference.name
    } else {
      intValue.literal.toString()
    }
  }

  def locatorString(EObject object) {
    getLocation(object).split('/').last()
  }

}
