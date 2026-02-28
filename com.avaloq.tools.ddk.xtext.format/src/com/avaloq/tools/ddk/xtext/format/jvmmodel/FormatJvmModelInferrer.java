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
package com.avaloq.tools.ddk.xtext.format.jvmmodel;

import static com.avaloq.tools.ddk.xtext.util.EObjectUtil.getFileLocation;
import static org.eclipse.xtext.GrammarUtil.getGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xtext.RuleNames;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;

import com.avaloq.tools.ddk.xtext.format.format.ColumnLocator;
import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.ContextFreeDirective;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRuleDirective;
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock;
import com.avaloq.tools.ddk.xtext.format.format.IndentLocator;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;
import com.avaloq.tools.ddk.xtext.format.format.KeywordPair;
import com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator;
import com.avaloq.tools.ddk.xtext.format.format.Locator;
import com.avaloq.tools.ddk.xtext.format.format.Matcher;
import com.avaloq.tools.ddk.xtext.format.format.MatcherList;
import com.avaloq.tools.ddk.xtext.format.format.MatcherType;
import com.avaloq.tools.ddk.xtext.format.format.NoFormatLocator;
import com.avaloq.tools.ddk.xtext.format.format.OffsetLocator;
import com.avaloq.tools.ddk.xtext.format.format.RightPaddingLocator;
import com.avaloq.tools.ddk.xtext.format.format.Rule;
import com.avaloq.tools.ddk.xtext.format.format.SpaceLocator;
import com.avaloq.tools.ddk.xtext.format.format.SpecificDirective;
import com.avaloq.tools.ddk.xtext.format.format.StringValue;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRuleDirective;
import com.avaloq.tools.ddk.xtext.format.generator.FormatGeneratorUtil;
import com.avaloq.tools.ddk.xtext.formatting.AbstractExtendedFormatter;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
public class FormatJvmModelInferrer extends AbstractModelInferrer {

  @Inject
  private JvmTypesBuilder _jvmTypesBuilder;

  @Inject
  private TypeReferences _typeReferences;

  @Inject
  private GrammarAccessExtensions grammarAccess;

  @Inject
  private TypesFactory typesFactory;

  @Inject
  private XbaseCompiler xbaseCompiler;

  private static final String BASE_FORMATTER_CLASS_NAME = AbstractExtendedFormatter.class.getName();

  private static final String BASE_FORMAT_CONFIG = ExtendedFormattingConfig.class.getName();

  private static final String METHOD_ACTIVATE = "activate";

  private static final String METHOD_CALCULATE = "calculateParameter";

  private static final String PARAMETER_CONFIG = "config";

  private static final String PARAMETER_ELEMENTS = "elements";

  private static final String PARAMETER_RULE = "rule";

  private static final String PARAMETER_GRAMMAR_ACCESS = "grammarAccess";

  private static final String PARAMETER_CONTEXT = "context";

  private static final String PARAMETER_COLUMN = "currentColumn";

  /**
   * The dispatch method {@code infer} is called for each instance of the
   * given element's type that is contained in a resource.
   *
   * @param element
   *      the model to create one or more {@link JvmDeclaredType declared types} from.
   * @param acceptor
   *      each created {@link JvmDeclaredType type} without a container should be passed to the acceptor in order
   *      get attached to the current resource. The acceptor's {@link IJvmDeclaredTypeAcceptor#accept(JvmDeclaredType,
   *      org.eclipse.xtext.xbase.lib.Procedures.Procedure1)} method takes the constructed empty type for the
   *      pre-indexing phase. This one is further initialized in the indexing phase using the passed closure.
   * @param isPreIndexingPhase
   *      whether the method is called in a pre-indexing phase, i.e. when the global index is not yet fully updated. You must not
   *      rely on linking using the index if isPreIndexingPhase is {@code true}.
   */
  protected void _infer(final FormatConfiguration format, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (isPreIndexingPhase) {
      return;
    }
    final Grammar context = format.getTargetGrammar();
    if (EcoreUtil.getAdapter(context.eAdapters(), RuleNames.class) == null) {
      final List<AbstractRule> allRules = GrammarUtil.allRules(context);
      for (final AbstractRule rule : allRules) {
        final Object adpt = EcoreUtil.getAdapter(rule.eAdapters(), RuleNames.class);
        if (adpt != null) {
          rule.eAdapters().remove(adpt);
        }
      }
      RuleNames.getRuleNames(context, true);
    }
    acceptor.<JvmGenericType>accept(
        _jvmTypesBuilder.toClass(format, Strings.lastToken(FormatGeneratorUtil.getFormatterName(format, "Abstract"), ".")), (JvmGenericType it) -> {
          inferClass(format, it);
          inferConstants(format, it);
          inferGetGrammarAccess(format, it);
          inferConfigureAcsFormatting(format, it);
          inferInit(format, it);
          inferRules(format, it);
          inferLocatorActivators(format, it);
        });
  }

  public void inferClass(final FormatConfiguration format, final JvmGenericType it) {
    Grammar targetGrammar = format.getTargetGrammar();
    String targetGrammarNameRaw = targetGrammar != null ? targetGrammar.getName() : null;
    String targetGrammarName = Strings.emptyIfNull(targetGrammarNameRaw);
    _jvmTypesBuilder.setDocumentation(it,
        "The abstract formatting configuration for " + Strings.skipLastToken(targetGrammarName, ".") + "." + Strings.lastToken(targetGrammarName, ".") + " as declared in " + Strings.lastToken(targetGrammarName, ".") + ".format.");
    if (format.getFormatterBaseClass() != null) {
      _jvmTypesBuilder.<JvmTypeReference>operator_add(it.getSuperTypes(),
          _typeReferenceBuilder.typeRef(format.getFormatterBaseClass().getPackageName() + "." + format.getFormatterBaseClass().getSimpleName()));
    } else {
      _jvmTypesBuilder.<JvmTypeReference>operator_add(it.getSuperTypes(),
          _typeReferenceBuilder.typeRef(BASE_FORMATTER_CLASS_NAME));
    }
    it.setPackageName(Strings.skipLastToken(FormatGeneratorUtil.getFormatterName(format, ""), "."));
    it.setAbstract(true);
  }

  public boolean inferConstants(final FormatConfiguration format, final JvmGenericType it) {
    if (!FormatGeneratorUtil.getAllConstants(format).isEmpty()) {
      return _jvmTypesBuilder.<JvmMember>operator_add(it.getMembers(),
          ListExtensions.<Constant, JvmMember>map(FormatGeneratorUtil.getAllConstants(format), (Constant c) -> createConstant(format, c)));
    }
    return false;
  }

  public String getFullyQualifiedName(final Grammar g) {
    return GrammarUtil.getNamespace(g) + ".services." + GrammarUtil.getSimpleName(g) + "GrammarAccess";
  }

  public boolean inferGetGrammarAccess(final FormatConfiguration format, final JvmGenericType it) {
    JvmOperation method = _jvmTypesBuilder.toMethod(format, "getGrammarAccess",
        _typeReferences.getTypeForName(getFullyQualifiedName(format.getTargetGrammar()), format.getTargetGrammar()), (JvmOperation it_1) -> {
          it_1.setVisibility(JvmVisibility.PROTECTED);
          final JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format);
          if (overrideAnnotation != null) {
            _jvmTypesBuilder.<JvmAnnotationReference>operator_add(it_1.getAnnotations(), overrideAnnotation);
          }
          _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("return (");
            sb.append(GrammarUtil.getSimpleName(format.getTargetGrammar()) + "GrammarAccess");
            sb.append(") super.getGrammarAccess();");
            it_2.append(sb);
          });
        });
    return _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
  }

  public boolean inferConfigureAcsFormatting(final FormatConfiguration format, final JvmGenericType it) {
    JvmOperation method = _jvmTypesBuilder.toMethod(format, "configureAcsFormatting", _typeReferenceBuilder.typeRef("void"), (JvmOperation it_1) -> {
      it_1.setVisibility(JvmVisibility.PROTECTED);
      final JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format);
      if (overrideAnnotation != null) {
        _jvmTypesBuilder.<JvmAnnotationReference>operator_add(it_1.getAnnotations(), overrideAnnotation);
      }
      _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
          _jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
        it_2.append("init(config, getGrammarAccess());");
      });
    });
    return _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
  }

  public boolean inferInit(final FormatConfiguration format, final JvmGenericType it) {
    JvmOperation method = _jvmTypesBuilder.toMethod(format, "init", _typeReferenceBuilder.typeRef("void"), (JvmOperation it_1) -> {
      Pair<String, String> mappedTo = Pair.<String, String>of(PARAMETER_CONFIG, "the format configuration");
      Pair<String, String> mappedTo_1 = Pair.<String, String>of(PARAMETER_GRAMMAR_ACCESS, "the grammar access for the grammar");
      _jvmTypesBuilder.setDocumentation(it_1, generateJavaDoc("Calls all configXyz methods declared in this class.", CollectionLiterals.<String, String>newLinkedHashMap(mappedTo, mappedTo_1)));
      it_1.setVisibility(JvmVisibility.PROTECTED);
      _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
          _jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
          _jvmTypesBuilder.toParameter(format, PARAMETER_GRAMMAR_ACCESS, _typeReferenceBuilder.typeRef(getFullyQualifiedName(format.getTargetGrammar()))));
      _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
        final ArrayList<String> rules = listConfigRules(format);
        int length = ((Object[]) Conversions.unwrapArray(rules, Object.class)).length;
        ExclusiveRange range = new ExclusiveRange(0, length, true);
        for (final Integer i : range) {
          if (i.intValue() != 0) {
            it_2.newLine();
          }
          it_2.append(rules.get(i.intValue()));
        }
      });
    });
    return _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
  }

  public boolean inferRules(final FormatConfiguration format, final JvmGenericType it) {
    _jvmTypesBuilder.<JvmMember>operator_add(it.getMembers(),
        Iterables.<JvmMember>concat(ListExtensions.<GrammarRule, Iterable<JvmMember>>map(FormatGeneratorUtil.getParserRules(format), (GrammarRule c) -> createRule(format, c))));
    _jvmTypesBuilder.<JvmMember>operator_add(it.getMembers(),
        Iterables.<JvmMember>concat(ListExtensions.<GrammarRule, Iterable<JvmMember>>map(FormatGeneratorUtil.getEnumRules(format), (GrammarRule c) -> createRule(format, c))));
    _jvmTypesBuilder.<JvmMember>operator_add(it.getMembers(),
        Iterables.<JvmMember>concat(ListExtensions.<GrammarRule, Iterable<JvmMember>>map(FormatGeneratorUtil.getTerminalRules(format), (GrammarRule c) -> createRule(format, c))));
    boolean result = false;
    if (FormatGeneratorUtil.getWildcardRule(format) != null) {
      JvmOperation method = _jvmTypesBuilder.toMethod(format, "configFindElements", _typeReferenceBuilder.typeRef("void"), (JvmOperation it_1) -> {
        Pair<String, String> mappedTo = Pair.<String, String>of(PARAMETER_CONFIG, "the format configuration");
        Pair<String, String> mappedTo_1 = Pair.<String, String>of(PARAMETER_ELEMENTS, "the grammar access for the grammar");
        _jvmTypesBuilder.setDocumentation(it_1, generateJavaDoc("Configuration for IGrammarAccess.findXyz() methods.", CollectionLiterals.<String, String>newLinkedHashMap(mappedTo, mappedTo_1)));
        it_1.setVisibility(JvmVisibility.PROTECTED);
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_ELEMENTS, _typeReferenceBuilder.typeRef(getFullyQualifiedName(getGrammar(format.getTargetGrammar())))));
        _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
          final List<String> directives = ListExtensions.<WildcardRuleDirective, String>map(
              FormatGeneratorUtil.getWildcardRule(format).getDirectives(),
              (WildcardRuleDirective d) -> directive(d, getRuleName(FormatGeneratorUtil.getWildcardRule(format))).toString());
          it_2.append(fixLastLine(IterableExtensions.join(directives)));
        });
      });
      result = _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
    }
    return result;
  }

  public void inferLocatorActivators(final FormatConfiguration format, final JvmGenericType it) {
    List<Rule> rules = new LinkedList<>();
    Iterables.<Rule>addAll(rules,
        Iterables.<Rule>concat(
            Iterables.<GrammarRule>concat(FormatGeneratorUtil.getParserRules(format), FormatGeneratorUtil.getTerminalRules(format)),
            FormatGeneratorUtil.getEnumRules(format)));
    rules.add(FormatGeneratorUtil.getWildcardRule(format));
    for (final Rule rule : rules) {
      List<EObject> directives = new LinkedList<>();
      if (rule instanceof GrammarRule grammarRule) {
        Iterables.<EObject>addAll(directives, grammarRule.getDirectives());
      } else if (rule instanceof WildcardRule wildcardRule) {
        Iterables.<EObject>addAll(directives, wildcardRule.getDirectives());
      }
      for (final EObject directive : IterableExtensions.<EObject>filterNull(directives)) {
        for (final Matcher matcher : collectMatchers(directive)) {
          if ((matcher.getLocator() instanceof ColumnLocator) && (((ColumnLocator) matcher.getLocator()).getParameter() != null)) {
            _jvmTypesBuilder.<JvmGenericType>operator_add(it.getMembers(),
                createParameterCalculatorInnerClass(format, rule, directive, matcher, ((ColumnLocator) matcher.getLocator()).getParameter(), it));
          }
          if ((matcher.getLocator() instanceof IndentLocator) && (((IndentLocator) matcher.getLocator()).getParameter() != null)) {
            _jvmTypesBuilder.<JvmGenericType>operator_add(it.getMembers(),
                createParameterCalculatorInnerClass(format, rule, directive, matcher, ((IndentLocator) matcher.getLocator()).getParameter(), it));
          }
          if (matcher.getCondition() != null) {
            _jvmTypesBuilder.<JvmGenericType>operator_add(it.getMembers(),
                createLocatorActivatorInnerClass(format, rule, directive, matcher, it));
          }
        }
      }
    }
  }

  public JvmGenericType createLocatorActivatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final JvmGenericType type) {
    return _jvmTypesBuilder.toClass(format, getLocatorActivatorName(rule, directive, matcher), (JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      _jvmTypesBuilder.<JvmTypeReference>operator_add(it.getSuperTypes(), getLocatorActivatorSuperType(format, rule));
      JvmOperation method = _jvmTypesBuilder.toMethod(matcher, METHOD_ACTIVATE, getLocatorActivatorReturnType(format), (JvmOperation it_1) -> {
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_CONTEXT, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), format)));
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(), createCurrenctColumnParameter());
        if (!Objects.equals(format.eResource(), matcher.eResource())) {
          _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
            xbaseCompiler.compile(matcher.getCondition(), it_2, getLocatorActivatorReturnType(format), null);
          });
        } else {
          _jvmTypesBuilder.setBody(it_1, matcher.getCondition());
        }
      });
      _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
    });
  }

  public JvmFormalParameter createCurrenctColumnParameter() {
    JvmFormalParameter result = typesFactory.createJvmFormalParameter();
    result.setName(PARAMETER_COLUMN);
    result.setParameterType(_typeReferenceBuilder.typeRef(Integer.class));
    return result;
  }

  public JvmGenericType createParameterCalculatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final XExpression parameterCalculation, final JvmGenericType type) {
    return _jvmTypesBuilder.toClass(format, getParameterCalculatorName(rule, directive, matcher), (JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      _jvmTypesBuilder.<JvmTypeReference>operator_add(it.getSuperTypes(), getParameterCalculatorSuperType(format, rule));
      JvmOperation method = _jvmTypesBuilder.toMethod(matcher, METHOD_CALCULATE, getParameterCalculatorReturnType(format), (JvmOperation it_1) -> {
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_CONTEXT, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), format)));
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it_1.getParameters(), createCurrenctColumnParameter());
        if (!Objects.equals(format.eResource(), matcher.eResource())) {
          _jvmTypesBuilder.setBody(it_1, (ITreeAppendable it_2) -> {
            xbaseCompiler.compile(parameterCalculation, it_2, getParameterCalculatorReturnType(format), null);
          });
        } else {
          _jvmTypesBuilder.setBody(it_1, parameterCalculation);
        }
      });
      _jvmTypesBuilder.<JvmOperation>operator_add(it.getMembers(), method);
    });
  }

  public ArrayList<String> listConfigRules(final FormatConfiguration format) {
    final ArrayList<String> configRules = CollectionLiterals.<String>newArrayList();
    if (FormatGeneratorUtil.getWildcardRule(format) != null) {
      configRules.add("configFindElements(config, grammarAccess);");
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getParserRules(format)) {
      configRules.add("config" + rule.getTargetRule().getName() + "(config, grammarAccess." + grammarAccess.gaElementsAccessor(rule.getTargetRule()) + ");");
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getEnumRules(format)) {
      configRules.add("config" + rule.getTargetRule().getName() + "(config, grammarAccess." + grammarAccess.gaRuleAccessor(rule.getTargetRule()) + ");");
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getTerminalRules(format)) {
      configRules.add("config" + rule.getTargetRule().getName() + "(config, grammarAccess." + grammarAccess.gaRuleAccessor(rule.getTargetRule()) + ");");
    }
    return configRules;
  }

  public String generateJavaDoc(final String description, final Map<String, String> parameters) {
    final StringBuilder sb = new StringBuilder();
    sb.append(description).append("\n");
    sb.append("\n");
    for (final Map.Entry<String, String> parameter : parameters.entrySet()) {
      sb.append("@param ").append(parameter.getKey()).append("\n");
      sb.append("     - ").append(parameter.getValue()).append("\n");
    }
    return sb.toString();
  }

  public JvmAnnotationReference createOverrideAnnotation(final FormatConfiguration format) {
    final JvmTypeReference annotationTypeRef = _typeReferenceBuilder.typeRef(Override.class);
    JvmAnnotationReference overrideAnnotation = null;
    if (annotationTypeRef != null) {
      final JvmType annotationType = annotationTypeRef.getType();
      overrideAnnotation = typesFactory.createJvmAnnotationReference();
      overrideAnnotation.setAnnotation((JvmAnnotationType) annotationType);
    }
    return overrideAnnotation;
  }

  public JvmMember createConstant(final FormatConfiguration configuration, final Constant constant) {
    if (constant.getStringValue() != null) {
      return _jvmTypesBuilder.toField(constant, constant.getName(), _typeReferences.getTypeForName("String", constant), (it) -> {
        _jvmTypesBuilder.setDocumentation(it, locatorString(constant));
        it.setStatic(true);
        it.setFinal(true);
        it.setVisibility(JvmVisibility.PROTECTED);
        _jvmTypesBuilder.setInitializer(it, (ITreeAppendable it_1) -> {
          it_1.append("\"" + constant.getStringValue() + "\"");
        });
      });
    } else if (constant.getIntValue() != null) {
      return _jvmTypesBuilder.toField(constant, constant.getName(), _typeReferences.getTypeForName("int", constant), (it) -> {
        _jvmTypesBuilder.setDocumentation(it, locatorString(constant));
        it.setStatic(true);
        it.setFinal(true);
        it.setVisibility(JvmVisibility.PROTECTED);
        _jvmTypesBuilder.setInitializer(it, (ITreeAppendable it_1) -> {
          it_1.append(constant.getIntValue().toString());
        });
      });
    }
    return null;
  }

  public List<Matcher> collectMatchers(final EObject directive) {
    List<Matcher> matchers = new LinkedList<>();
    if (directive instanceof GroupBlock groupBlock) {
      if (groupBlock.getMatcherList() != null) {
        Iterables.<Matcher>addAll(matchers, groupBlock.getMatcherList().getMatchers());
      }
    } else if (directive instanceof SpecificDirective specificDirective) {
      if (specificDirective.getMatcherList() != null) {
        Iterables.<Matcher>addAll(matchers, specificDirective.getMatcherList().getMatchers());
      }
    } else if (directive instanceof ContextFreeDirective contextFreeDirective) {
      if (contextFreeDirective.getMatcherList() != null) {
        Iterables.<Matcher>addAll(matchers, contextFreeDirective.getMatcherList().getMatchers());
      }
    } else if (directive instanceof KeywordPair keywordPair) {
      if (keywordPair.getLeftMatchers() != null) {
        Iterables.<Matcher>addAll(matchers, keywordPair.getLeftMatchers());
      }
      if (keywordPair.getRightMatchers() != null) {
        Iterables.<Matcher>addAll(matchers, keywordPair.getRightMatchers());
      }
    }
    return matchers;
  }

  public JvmTypeReference getLocatorActivatorReturnType(final FormatConfiguration formatConfiguration) {
    return _typeReferenceBuilder.typeRef(boolean.class);
  }

  public JvmTypeReference getParameterCalculatorReturnType(final FormatConfiguration formatConfiguration) {
    return _typeReferenceBuilder.typeRef(int.class);
  }

  // getLocatorActivatorSuperType dispatch
  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorActivator.class, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorActivator.class, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  public JvmTypeReference getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(formatConfiguration, rule).toString());
    }
  }

  // getParameterCalculatorSuperType dispatch
  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, _typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  public JvmTypeReference getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(formatConfiguration, rule).toString());
    }
  }

  // getGrammarElementNameFromSelf dispatch
  protected String _getGrammarElementNameFromSelf(final GrammarRule rule) {
    final String originalRuleName = getRuleName(rule);
    String actualRuleName = originalRuleName;
    if (rule.getTargetRule() == null || rule.getTargetRule().getType() == null || rule.getTargetRule().getType().getClassifier() == null) {
      return actualRuleName;
    } else {
      AbstractRule targetRule = rule.getTargetRule();
      TypeRef type = targetRule != null ? targetRule.getType() : null;
      EClassifier classifier = type != null ? type.getClassifier() : null;
      String name = classifier != null ? classifier.getName() : null;
      if (!Objects.equals(actualRuleName, name)) {
        actualRuleName = rule.getTargetRule().getType().getClassifier().getName();
      }
    }
    AbstractRule targetRule = rule.getTargetRule();
    TypeRef type = targetRule != null ? targetRule.getType() : null;
    AbstractMetamodelDeclaration metamodel = type != null ? type.getMetamodel() : null;
    if (metamodel == null) {
      return actualRuleName;
    } else {
      if (!Objects.equals(actualRuleName, originalRuleName)) {
        boolean anyMatch = metamodel.getEPackage().getEClassifiers().stream().anyMatch(
            (EClassifier it) -> (it instanceof EClass) && it.getName().equalsIgnoreCase(originalRuleName));
        if (anyMatch) {
          actualRuleName = originalRuleName;
        }
      }
      URI uri = EcoreUtil2.getURI(metamodel.getEPackage());
      String segment = uri != null ? uri.segment(1) : null;
      final String metamodelPackage = segment;
      if (metamodelPackage == null) {
        return actualRuleName;
      }
      EPackage ePackage = metamodel.getEPackage();
      String ePackageName = ePackage != null ? ePackage.getName() : null;
      return metamodelPackage.substring(0, metamodelPackage.lastIndexOf(".core")) + "." + ePackageName + "." + actualRuleName;
    }
  }

  protected String _getGrammarElementNameFromSelf(final WildcardRule rule) {
    return EObject.class.getName();
  }

  public String getGrammarElementNameFromSelf(final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getGrammarElementNameFromSelf(grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getGrammarElementNameFromSelf(wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(rule).toString());
    }
  }

  public int getMatcherIndex(final Matcher matcher) {
    final MatcherList matcherList = EcoreUtil2.<MatcherList>getContainerOfType(matcher, MatcherList.class);
    return matcherList.getMatchers().indexOf(matcher);
  }

  public String getLocatorActivatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    return ("ActivatorFor" + getRuleName(rule) + getMatcherName(matcher, directive)).replace("Impl", "");
  }

  public String getLocatorActivatorName(final String partialName, final Matcher matcher) {
    return ("ActivatorFor" + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator()) + StringExtensions.toFirstUpper(matcher.getType().name().toLowerCase())).replace("Impl", "");
  }

  public String getParameterCalculatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    return ("ParameterCalculatorFor" + getRuleName(rule) + getMatcherName(matcher, directive)).replace("Impl", "");
  }

  public String getParameterCalculatorName(final String partialName, final Matcher matcher) {
    return ("ParameterCalculatorFor" + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator()) + StringExtensions.toFirstUpper(matcher.getType().name().toLowerCase())).replace("Impl", "");
  }

  // getRuleName dispatch
  protected String _getRuleName(final GrammarRule rule) {
    AbstractRule targetRule = rule.getTargetRule();
    return targetRule != null ? targetRule.getName() : null;
  }

  protected String _getRuleName(final WildcardRule rule) {
    return "Wildcard";
  }

  protected String _getRuleName(final EObject rule) {
    return EObject.class.getSimpleName();
  }

  public String getRuleName(final EObject rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getRuleName(grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getRuleName(wildcardRule);
    } else if (rule != null) {
      return _getRuleName(rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(rule).toString());
    }
  }

  public String getMatcherName(final Matcher matcher, final EObject directive) {
    return getDirectiveName(directive) + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator()) + StringExtensions.toFirstUpper(matcher.getType().name().toLowerCase());
  }

  public String getLocatorName(final EObject locator) {
    Class<? extends EObject> clazz = locator != null ? locator.getClass() : null;
    String simpleName = clazz != null ? clazz.getSimpleName() : null;
    return simpleName != null ? simpleName : "";
  }

  public String convertNonAlphaNumeric(final String str) {
    final Pattern pattern = Pattern.compile("[\\W]");
    final java.util.regex.Matcher matcher = pattern.matcher(str);
    final StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, String.valueOf(Integer.toHexString(matcher.group().hashCode())));
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  // getDirectiveName dispatch
  protected String _getDirectiveName(final GroupBlock directive) {
    final GrammarRule grammarRule = EcoreUtil2.<GrammarRule>getContainerOfType(directive, GrammarRule.class);
    final ArrayList<Iterable<GroupBlock>> directives = CollectionLiterals.<Iterable<GroupBlock>>newArrayList(Iterables.<GroupBlock>filter(grammarRule.getDirectives(), GroupBlock.class));
    return "Group" + String.valueOf(directives.indexOf(directive) + 1);
  }

  protected String _getDirectiveName(final SpecificDirective directive) {
    String directiveName = "";
    for (final GrammarElementReference grammarElementReference : directive.getGrammarElements()) {
      if (grammarElementReference.getAssignment() != null) {
        directiveName = directiveName + grammarAccess.gaElementAccessMethodName(grammarElementReference.getAssignment()).replaceFirst("get", "").replaceFirst("(?s)(.*)" + "Assignment", "$1" + "");
      }
      if (grammarElementReference.getRuleCall() != null) {
        directiveName = directiveName + StringExtensions.toFirstUpper(grammarElementReference.getRuleCall().getRule().getName());
      }
      if (grammarElementReference.getRule() != null) {
        directiveName = directiveName + StringExtensions.toFirstUpper(grammarElementReference.getRule().getName());
      }
      if (grammarElementReference.getKeyword() != null) {
        directiveName = directiveName + StringExtensions.toFirstUpper(convertNonAlphaNumeric(grammarElementReference.getKeyword().getValue()));
      }
      if (grammarElementReference.getSelf() != null) {
        directiveName = directiveName + "Self";
      }
    }
    return directiveName;
  }

  protected String _getDirectiveName(final ContextFreeDirective directive) {
    String directiveName = "";
    for (final GrammarElementLookup grammarElementLookup : directive.getGrammarElements()) {
      if (grammarElementLookup.getRule() != null) {
        directiveName = directiveName + StringExtensions.toFirstUpper(grammarElementLookup.getRule().getName());
      }
      if (grammarElementLookup.getKeyword() != null) {
        directiveName = directiveName + StringExtensions.toFirstUpper(convertNonAlphaNumeric(grammarElementLookup.getKeyword()));
      }
    }
    return directiveName;
  }

  protected String _getDirectiveName(final KeywordPair directive) {
    return convertNonAlphaNumeric(directive.getLeft()) + convertNonAlphaNumeric(directive.getRight());
  }

  protected String _getDirectiveName(final EObject directive) {
    return String.valueOf(directive.hashCode());
  }

  public String getDirectiveName(final EObject directive) {
    if (directive instanceof ContextFreeDirective contextFreeDirective) {
      return _getDirectiveName(contextFreeDirective);
    } else if (directive instanceof KeywordPair keywordPair) {
      return _getDirectiveName(keywordPair);
    } else if (directive instanceof SpecificDirective specificDirective) {
      return _getDirectiveName(specificDirective);
    } else if (directive instanceof GroupBlock groupBlock) {
      return _getDirectiveName(groupBlock);
    } else if (directive != null) {
      return _getDirectiveName(directive);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(directive).toString());
    }
  }

  public Iterable<JvmMember> createRule(final FormatConfiguration format, final GrammarRule rule) {
    final List<JvmMember> members = CollectionLiterals.<JvmMember>newArrayList();
    JvmOperation method = _jvmTypesBuilder.toMethod(format, "config" + rule.getTargetRule().getName(), _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
      it.setFinal(false);
      it.setVisibility(JvmVisibility.PROTECTED);
      _jvmTypesBuilder.<JvmFormalParameter>operator_add(it.getParameters(),
          _jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      AbstractRule targetRule = rule.getTargetRule();
      if (targetRule instanceof ParserRule) {
        final String ruleName = getFullyQualifiedName(getGrammar(rule.getTargetRule())) + "$" + grammarAccess.gaRuleAccessorClassName(rule.getTargetRule());
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_ELEMENTS, _typeReferences.getTypeForName(ruleName, rule.getTargetRule())));
        _jvmTypesBuilder.setDocumentation(it, generateJavaDoc("Configuration for " + rule.getTargetRule().getName() + ".",
            CollectionLiterals.<String, String>newLinkedHashMap(
                Pair.<String, String>of(PARAMETER_CONFIG, "the format configuration"),
                Pair.<String, String>of(PARAMETER_ELEMENTS, "the grammar access for " + rule.getTargetRule().getName() + " elements"))));
      } else if (targetRule instanceof EnumRule) {
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_RULE, _typeReferences.getTypeForName(EnumRule.class.getName(), rule.getTargetRule())));
        _jvmTypesBuilder.setDocumentation(it, generateJavaDoc("Configuration for " + rule.getTargetRule().getName() + ".",
            CollectionLiterals.<String, String>newLinkedHashMap(
                Pair.<String, String>of(PARAMETER_CONFIG, "the format configuration"),
                Pair.<String, String>of(PARAMETER_RULE, "the enum rule for " + rule.getTargetRule().getName()))));
      } else if (targetRule instanceof TerminalRule) {
        _jvmTypesBuilder.<JvmFormalParameter>operator_add(it.getParameters(),
            _jvmTypesBuilder.toParameter(format, PARAMETER_RULE, _typeReferences.getTypeForName(TerminalRule.class.getName(), rule.getTargetRule())));
        _jvmTypesBuilder.setDocumentation(it, generateJavaDoc("Configuration for " + rule.getTargetRule().getName() + ".",
            CollectionLiterals.<String, String>newLinkedHashMap(
                Pair.<String, String>of(PARAMETER_CONFIG, "the format configuration"),
                Pair.<String, String>of(PARAMETER_RULE, "the terminal rule for " + rule.getTargetRule().getName()))));
      }
      _jvmTypesBuilder.setBody(it, (ITreeAppendable it_1) -> {
        final List<String> directives = ListExtensions.<EObject, String>map(rule.getDirectives(), (EObject d) -> directive(d, getRuleName(rule)).toString());
        it_1.append(fixLastLine(IterableExtensions.join(directives)));
      });
    });
    members.add(method);
    return members;
  }

  public String fixLastLine(final String content) {
    if (content.endsWith("\r\n")) {
      return content.substring(0, content.length() - 2);
    } else {
      return content;
    }
  }

  // directive dispatch
  protected CharSequence _directive(final SpecificDirective dir, final String partialName) {
    return matchReference(dir.getMatcherList(), dir.getGrammarElements(), partialName + getDirectiveName(dir));
  }

  protected CharSequence _directive(final ContextFreeDirective dir, final String partialName) {
    return matchLookup(dir.getMatcherList(), dir.getGrammarElements(), partialName + getDirectiveName(dir));
  }

  protected CharSequence _directive(final GroupBlock dir, final String partialName) {
    if (dir.getMatcherList() != null) {
      return matchReference(dir.getMatcherList(), new BasicEList<>(Collections.singletonList(dir.getGrammarElement())), partialName + getDirectiveName(dir));
    } else if (dir.getSubGroup() != null) {
      return directive(dir.getSubGroup(), partialName + getDirectiveName(dir));
    } else {
      final StringBuilder sb = new StringBuilder();
      for (final GrammarRuleDirective d : dir.getDirectives()) {
        sb.append(directive(d, partialName + getDirectiveName(dir)));
      }
      return sb;
    }
  }

  protected CharSequence _directive(final KeywordPair dir, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("// ").append(locatorString(dir)).append("\n");
    sb.append("for (final org.eclipse.xtext.util.Pair<org.eclipse.xtext.Keyword, org.eclipse.xtext.Keyword> pair : elements.findKeywordPairs(\"").append(dir.getLeft()).append("\", \"").append(dir.getRight()).append("\")) {\n");
    for (final Matcher matcher : dir.getLeftMatchers()) {
      sb.append(matchLookupPartial(matcher.getLocator(), matcher, "pair.getFirst()", partialName + getDirectiveName(dir)));
      sb.append("\n");
    }
    for (final Matcher matcher : dir.getRightMatchers()) {
      sb.append(matchLookupPartial(matcher.getLocator(), matcher, "pair.getSecond()", partialName + getDirectiveName(dir)));
      sb.append("\n");
    }
    sb.append("}\n");
    return sb;
  }

  protected CharSequence _directive(final Object dir, final String partialName) {
    throw new UnsupportedOperationException("Unknown directive " + dir.getClass().getName());
  }

  public CharSequence directive(final Object dir, final String partialName) {
    if (dir instanceof ContextFreeDirective contextFreeDirective) {
      return _directive(contextFreeDirective, partialName);
    } else if (dir instanceof KeywordPair keywordPair) {
      return _directive(keywordPair, partialName);
    } else if (dir instanceof SpecificDirective specificDirective) {
      return _directive(specificDirective, partialName);
    } else if (dir instanceof GroupBlock groupBlock) {
      return _directive(groupBlock, partialName);
    } else if (dir != null) {
      return _directive(dir, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(dir, partialName).toString());
    }
  }

  public CharSequence matchLookup(final MatcherList matcherList, final EList<GrammarElementLookup> elements, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (!elements.isEmpty()) {
      boolean hasRuleElements = elements.stream().anyMatch((GrammarElementLookup e) -> e.getRule() != null);
      if (hasRuleElements) {
        sb.append("// ").append(locatorString(matcherList)).append("\n");
        sb.append("for (org.eclipse.xtext.RuleCall ruleCall : elements.findRuleCalls(");
        boolean first = true;
        for (final GrammarElementLookup element : elements.stream().filter((GrammarElementLookup e) -> e.getRule() != null).toList()) {
          if (!first) {
            sb.append(", ");
          }
          first = false;
          sb.append("elements.").append(grammarAccess.gaRuleAccessor(element.getRule()));
        }
        sb.append(")) {\n");
        for (final Matcher matcher : matcherList.getMatchers()) {
          sb.append("  ").append(matchLookupPartial(matcher.getLocator(), matcher, "ruleCall", partialName)).append("\n");
        }
        sb.append("}\n");
      }
      boolean hasKeywordElements = elements.stream().anyMatch((GrammarElementLookup e) -> e.getKeyword() != null);
      if (hasKeywordElements) {
        sb.append("// ").append(locatorString(matcherList)).append("\n");
        sb.append("for (org.eclipse.xtext.Keyword keyword : elements.findKeywords(");
        boolean first2 = true;
        for (final GrammarElementLookup element : elements.stream().filter((GrammarElementLookup e) -> e.getKeyword() != null).toList()) {
          if (!first2) {
            sb.append(", ");
          }
          first2 = false;
          sb.append("\"").append(element.getKeyword()).append("\"");
        }
        sb.append(")) {\n");
        for (final Matcher matcher : matcherList.getMatchers()) {
          sb.append("  ").append(matchLookupPartial(matcher.getLocator(), matcher, "keyword", partialName)).append("\n");
        }
        sb.append("}\n");
      }
    }
    return sb;
  }

  // matchLookupPartial dispatch
  protected CharSequence _matchLookupPartial(final ColumnLocator columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (matcher.getType().getLiteral().compareTo("before") == 0) {
      sb.append("config.setColumn(").append(getValueOrConstant(columnLocator.getValue())).append(", ").append(columnLocator.isFixed()).append(", ").append(columnLocator.isRelative()).append(", ").append(columnLocator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").before(").append(eobjectTypeName).append(");  // ").append(locatorString(columnLocator)).append("\n");
      sb.append("config.setColumn(").append(getValueOrConstant(columnLocator.getValue())).append(", ").append(columnLocator.isFixed()).append(", ").append(columnLocator.isRelative()).append(", ").append(columnLocator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").after(").append(eobjectTypeName).append(");  // ").append(locatorString(columnLocator));
    } else {
      sb.append("config.setColumn(").append(getValueOrConstant(columnLocator.getValue()));
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").").append(matcherType(matcher.getType())).append("(").append(eobjectTypeName).append("); // ").append(locatorString(columnLocator));
    }
    return sb;
  }

  protected CharSequence _matchLookupPartial(final OffsetLocator offsetLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (matcher.getType().getLiteral().compareTo("before") == 0) {
      sb.append("config.setColumn(").append(getValueOrConstant(offsetLocator.getValue())).append(", ").append(offsetLocator.isFixed()).append(", true, ").append(offsetLocator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").before(").append(eobjectTypeName).append(");  // ").append(locatorString(offsetLocator)).append("\n");
      sb.append("config.setColumn(").append(getValueOrConstant(offsetLocator.getValue())).append(", ").append(offsetLocator.isFixed()).append(", true, ").append(offsetLocator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").after(").append(eobjectTypeName).append(");  // ").append(locatorString(offsetLocator));
    } else {
      sb.append("config.setOffset(").append(getValueOrConstant(offsetLocator.getValue()));
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").").append(matcherType(matcher.getType())).append("(").append(eobjectTypeName).append("); // ").append(locatorString(offsetLocator));
    }
    return sb;
  }

  protected CharSequence _matchLookupPartial(final EObject locator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("config.").append(locator(matcher, matcher.getLocator(), partialName)).append(".").append(matcherType(matcher.getType())).append("(").append(eobjectTypeName).append(");");
    return sb;
  }

  public CharSequence matchLookupPartial(final EObject columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    if (columnLocator instanceof ColumnLocator cl) {
      return _matchLookupPartial(cl, matcher, eobjectTypeName, partialName);
    } else if (columnLocator instanceof OffsetLocator ol) {
      return _matchLookupPartial(ol, matcher, eobjectTypeName, partialName);
    } else if (columnLocator != null) {
      return _matchLookupPartial(columnLocator, matcher, eobjectTypeName, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(columnLocator, matcher, eobjectTypeName, partialName).toString());
    }
  }

  public CharSequence matchReference(final MatcherList matcherList, final EList<? extends EObject> elements, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (!elements.isEmpty()) {
      for (final Matcher matcher : matcherList.getMatchers()) {
        if (FormatGeneratorUtil.isTwoArgumentMatcherType(matcher.getType()).booleanValue()) {
          sb.append(match(matcher, elements.get(0), elements.get(1), matcher.getLocator(), partialName));
        } else {
          for (final EObject e : elements) {
            sb.append(match(matcher, e, matcher.getLocator(), partialName));
          }
        }
      }
    }
    return sb;
  }

  public CharSequence match(final Matcher matcher, final EObject element1, final EObject element2, final EObject locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("config.").append(locator(matcher, matcher.getLocator(), partialName)).append(".").append(matcherType(matcher.getType())).append("(").append(elementAccess(element1)).append(", ").append(elementAccess(element2)).append("); // ").append(locatorString(matcher)).append("\n");
    return sb;
  }

  // match dispatch
  protected CharSequence _match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("config.").append(locator(matcher, matcher.getLocator(), partialName)).append(".").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
    return sb;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final NoFormatLocator locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("config.").append(locator(matcher, matcher.getLocator(), partialName)).append(".").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
    return sb;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final ColumnLocator locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (matcher.getType().getLiteral().compareTo("before") == 0) {
      if (locator.getParameter() != null) {
        sb.append("config.setColumn(").append(locator.isFixed()).append(", ").append(locator.isRelative()).append(", ").append(locator.isNobreak()).append(", new ").append(getParameterCalculatorName(partialName, matcher)).append("()");
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").before(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
        sb.append("config.setColumn(").append(locator.isFixed()).append(", ").append(locator.isRelative()).append(", ").append(locator.isNobreak()).append(", new ").append(getParameterCalculatorName(partialName, matcher)).append("()");
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").after(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
      } else {
        sb.append("config.setColumn(").append(getValueOrConstant(locator.getValue())).append(", ").append(locator.isFixed()).append(", ").append(locator.isRelative()).append(", ").append(locator.isNobreak());
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").before(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
        sb.append("config.setColumn(").append(getValueOrConstant(locator.getValue())).append(", ").append(locator.isFixed()).append(", ").append(locator.isRelative()).append(", ").append(locator.isNobreak());
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").after(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
      }
    } else {
      if (locator.getParameter() != null) {
        sb.append("config.setColumn(new ").append(getParameterCalculatorName(partialName, matcher)).append("()");
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
      } else {
        sb.append("config.setColumn(").append(getValueOrConstant(locator.getValue()));
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
        sb.append(").").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
      }
    }
    return sb;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final OffsetLocator locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (matcher.getType().getLiteral().compareTo("before") == 0) {
      sb.append("config.setColumn(").append(getValueOrConstant(locator.getValue())).append(", ").append(locator.isFixed()).append(", true, ").append(locator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").before(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
      sb.append("config.setColumn(").append(getValueOrConstant(locator.getValue())).append(", ").append(locator.isFixed()).append(", true, ").append(locator.isNobreak());
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").after(").append(elementAccess(element)).append(");  // ").append(locatorString(matcher)).append("\n");
    } else {
      sb.append("config.setOffset(").append(getValueOrConstant(locator.getValue()));
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(").").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
    }
    return sb;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final IndentLocator locator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("config.").append(locator(matcher, matcher.getLocator(), partialName)).append(".").append(matcherType(matcher.getType())).append("(").append(elementAccess(element)).append("); // ").append(locatorString(matcher)).append("\n");
    return sb;
  }

  public CharSequence match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    if (locator instanceof ColumnLocator cl) {
      return _match(matcher, element, cl, partialName);
    } else if (locator instanceof IndentLocator il) {
      return _match(matcher, element, il, partialName);
    } else if (locator instanceof NoFormatLocator nfl) {
      return _match(matcher, element, nfl, partialName);
    } else if (locator instanceof OffsetLocator ol) {
      return _match(matcher, element, ol, partialName);
    } else if (locator != null) {
      return _match(matcher, element, locator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(matcher, element, locator, partialName).toString());
    }
  }

  public String matcherType(final MatcherType matcherType) {
    return matcherType.getLiteral();
  }

  // elementAccess dispatch
  protected CharSequence _elementAccess(final GrammarElementLookup grammarElementLookup) {
    final StringBuilder sb = new StringBuilder();
    if (grammarElementLookup.getRule() != null) {
      sb.append("elements.findRuleCalls(").append(grammarAccess.gaElementsAccessor(grammarElementLookup.getRule())).append(")");
    } else if (grammarElementLookup.getKeyword() != null) {
      sb.append("elements.findKeywords(\"").append(grammarElementLookup.getKeyword()).append("\")");
    }
    return sb;
  }

  protected CharSequence _elementAccess(final GrammarElementReference grammarElementReference) {
    if (grammarElementReference.getRuleCall() != null) {
      return elementAccess(grammarElementReference.getRuleCall());
    } else if (grammarElementReference.getKeyword() != null) {
      return elementAccess(grammarElementReference.getKeyword());
    } else if (grammarElementReference.getAssignment() != null) {
      return elementAccess(grammarElementReference.getAssignment());
    } else if (grammarElementReference.getSelf() != null) {
      if (FormatGeneratorUtil.containedByParserRule(grammarElementReference).booleanValue()) {
        return "elements.getRule()";
      } else {
        return "rule";
      }
    } else if (grammarElementReference.getRule() != null) {
      return elementAccess(grammarElementReference.getRule());
    }
    return null;
  }

  protected CharSequence _elementAccess(final AbstractRule abstractRule) {
    return "getGrammarAccess()." + grammarAccess.gaRuleAccessor(abstractRule);
  }

  protected CharSequence _elementAccess(final AbstractElement abstractElement) {
    return "elements." + grammarAccess.gaElementAccessor(abstractElement);
  }

  protected CharSequence _elementAccess(final Object object) {
    throw new UnsupportedOperationException("Unknown Xtext element " + object.getClass().getName());
  }

  public CharSequence elementAccess(final Object grammarElementLookup) {
    if (grammarElementLookup instanceof GrammarElementLookup gel) {
      return _elementAccess(gel);
    } else if (grammarElementLookup instanceof GrammarElementReference ger) {
      return _elementAccess(ger);
    } else if (grammarElementLookup instanceof AbstractElement ae) {
      return _elementAccess(ae);
    } else if (grammarElementLookup instanceof AbstractRule ar) {
      return _elementAccess(ar);
    } else if (grammarElementLookup != null) {
      return _elementAccess(grammarElementLookup);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(grammarElementLookup).toString());
    }
  }

  // locator dispatch
  protected CharSequence _locator(final Matcher matcher, final SpaceLocator spaceLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (spaceLocator.isNoSpace()) {
      sb.append("setNoSpace(");
      if (matcher.getCondition() != null) {
        sb.append("new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(")");
    } else {
      sb.append("setSpace(").append(getValueOrConstant(spaceLocator.getValue()));
      if (matcher.getCondition() != null) {
        sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(")");
    }
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final RightPaddingLocator rightPaddingLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("setRightPadding(").append(getValueOrConstant(rightPaddingLocator.getValue()));
    if (matcher.getCondition() != null) {
      sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
    }
    sb.append(")");
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final LinewrapLocator linewrapLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (linewrapLocator.isNoLinewrap()) {
      sb.append("setNoLinewrap(");
      if (matcher.getCondition() != null) {
        sb.append("new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
      }
      sb.append(")");
    } else {
      sb.append("setLinewrap(");
      if (linewrapLocator.getValue() != null) {
        sb.append(getValueOrConstant(linewrapLocator.getValue()));
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
      } else if (linewrapLocator.getMinimum() != null) {
        sb.append(getValueOrConstant(linewrapLocator.getMinimum())).append(", ").append(getValueOrConstant(linewrapLocator.getDefault())).append(", ").append(getValueOrConstant(linewrapLocator.getMaximum()));
        if (matcher.getCondition() != null) {
          sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
      } else {
        if (matcher.getCondition() != null) {
          sb.append("new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
        }
      }
      sb.append(")");
    }
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final ColumnLocator columnLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("setColumn(").append(getValueOrConstant(columnLocator.getValue())).append(", ").append(columnLocator.isFixed()).append(", ").append(columnLocator.isRelative()).append(", ").append(columnLocator.isNobreak());
    if (matcher.getCondition() != null) {
      sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
    }
    sb.append(")");
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final OffsetLocator offsetLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("setColumn(").append(getValueOrConstant(offsetLocator.getValue())).append(", ").append(offsetLocator.isFixed()).append(", true, ").append(offsetLocator.isNobreak());
    if (matcher.getCondition() != null) {
      sb.append(", new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
    }
    sb.append(")");
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final IndentLocator indentLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    if (indentLocator.isIncrement()) {
      sb.append("setIndentationIncrement(");
    } else {
      sb.append("setIndentationDecrement(");
    }
    if (indentLocator.getValue() != null && (indentLocator.getValue().getReference() != null || indentLocator.getValue().getLiteral().intValue() >= 1)) {
      sb.append(getValueOrConstant(indentLocator.getValue()));
    } else if (indentLocator.getParameter() != null) {
      sb.append("new ").append(getParameterCalculatorName(partialName, matcher)).append("()");
    }
    if (matcher.getCondition() != null) {
      if (indentLocator.getValue() != null || indentLocator.getParameter() != null) {
        sb.append(",");
      }
      sb.append(" new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
    }
    sb.append(")");
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final NoFormatLocator noFormatLocator, final String partialName) {
    final StringBuilder sb = new StringBuilder();
    sb.append("setNoFormat(");
    if (matcher.getCondition() != null) {
      sb.append("new ").append(getLocatorActivatorName(partialName, matcher)).append("()");
    }
    sb.append(")");
    return sb;
  }

  protected CharSequence _locator(final Matcher matcher, final Locator locator, final String partialName) {
    throw new UnsupportedOperationException("Unknown locator " + locator.getClass().getName());
  }

  public CharSequence locator(final Matcher matcher, final Locator columnLocator, final String partialName) {
    if (columnLocator instanceof ColumnLocator cl) {
      return _locator(matcher, cl, partialName);
    } else if (columnLocator instanceof IndentLocator il) {
      return _locator(matcher, il, partialName);
    } else if (columnLocator instanceof LinewrapLocator ll) {
      return _locator(matcher, ll, partialName);
    } else if (columnLocator instanceof NoFormatLocator nfl) {
      return _locator(matcher, nfl, partialName);
    } else if (columnLocator instanceof OffsetLocator ol) {
      return _locator(matcher, ol, partialName);
    } else if (columnLocator instanceof RightPaddingLocator rpl) {
      return _locator(matcher, rpl, partialName);
    } else if (columnLocator instanceof SpaceLocator sl) {
      return _locator(matcher, sl, partialName);
    } else if (columnLocator != null) {
      return _locator(matcher, columnLocator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(matcher, columnLocator, partialName).toString());
    }
  }

  // getValueOrConstant dispatch
  protected String _getValueOrConstant(final StringValue stringValue) {
    if (stringValue.getLiteral() == null) {
      return stringValue.getReference().getName();
    } else {
      return "\"" + stringValue.getLiteral() + "\"";
    }
  }

  protected String _getValueOrConstant(final IntValue intValue) {
    if (intValue.getLiteral() == null) {
      return intValue.getReference().getName();
    } else {
      return intValue.getLiteral().toString();
    }
  }

  public String getValueOrConstant(final EObject intValue) {
    if (intValue instanceof IntValue iv) {
      return _getValueOrConstant(iv);
    } else if (intValue instanceof StringValue sv) {
      return _getValueOrConstant(sv);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(intValue).toString());
    }
  }

  public String locatorString(final EObject object) {
    return IterableExtensions.<String>lastOrNull((Iterable<String>) Conversions.doWrapArray(getFileLocation(object).split("/")));
  }

  public void infer(final EObject format, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (format instanceof FormatConfiguration formatConfiguration) {
      _infer(formatConfiguration, acceptor, isPreIndexingPhase);
      return;
    } else if (format != null) {
      _infer(format, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(format, acceptor, isPreIndexingPhase).toString());
    }
  }
}
