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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CompoundElement;
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
import org.eclipse.xtext.common.types.JvmField;
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
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
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
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class FormatJvmModelInferrer extends AbstractModelInferrer {
  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are Java source fragments emitted by this generator, not nameable constants
  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole

  @Inject
  private JvmTypesBuilder jvmTypesBuilder;

  @Inject
  private TypeReferences typeReferences;

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
   * @param format
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
    final Adapter adapter = EcoreUtil.getAdapter(context.eAdapters(), RuleNames.class);
    if (adapter == null) {
      final List<AbstractRule> allRules = GrammarUtil.allRules(context);
      for (final AbstractRule rule : allRules) {
        final Adapter adpt = EcoreUtil.getAdapter(rule.eAdapters(), RuleNames.class);
        if (adpt != null) {
          rule.eAdapters().remove(adpt);
        }
      }
      RuleNames.getRuleNames(context, true);
    }
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      inferClass(format, it);
      inferConstants(format, it);
      inferGetGrammarAccess(format, it);
      inferConfigureAcsFormatting(format, it);
      inferInit(format, it);
      inferRules(format, it);
      inferLocatorActivators(format, it);
    };
    acceptor.<JvmGenericType> accept(
        jvmTypesBuilder.toClass(format, Strings.lastToken(FormatGeneratorUtil.getFormatterName(format, "Abstract"), ".")), initializer);
  }

  public void inferClass(final FormatConfiguration format, final JvmGenericType it) {
    final Grammar targetGrammar = format.getTargetGrammar();
    final String targetGrammarName = Strings.emptyIfNull(targetGrammar != null ? targetGrammar.getName() : null);
    jvmTypesBuilder.setDocumentation(it, "The abstract formatting configuration for %s.%s as declared in %s.format.".formatted(
        Strings.skipLastToken(targetGrammarName, "."), Strings.lastToken(targetGrammarName, "."), Strings.lastToken(targetGrammarName, ".")));
    if (format.getFormatterBaseClass() != null) {
      final JvmDeclaredType baseClass = format.getFormatterBaseClass();
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(baseClass.getPackageName() + "." + baseClass.getSimpleName()));
    } else {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(BASE_FORMATTER_CLASS_NAME));
    }
    it.setPackageName(Strings.skipLastToken(FormatGeneratorUtil.getFormatterName(format, ""), "."));
    it.setAbstract(true);
  }

  public void inferConstants(final FormatConfiguration format, final JvmGenericType it) {
    if (!FormatGeneratorUtil.getAllConstants(format).isEmpty()) {
      for (final Constant c : FormatGeneratorUtil.getAllConstants(format)) {
        it.getMembers().add(createConstant(format, c));
      }
    }
  }

  public String getFullyQualifiedName(final Grammar g) {
    return GrammarUtil.getNamespace(g) + ".services." + GrammarUtil.getSimpleName(g) + "GrammarAccess";
  }

  public void inferGetGrammarAccess(final FormatConfiguration format, final JvmGenericType it) {
    final Procedure1<JvmOperation> initializer = (final JvmOperation method) -> {
      method.setVisibility(JvmVisibility.PROTECTED);
      final JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format);
      if (overrideAnnotation != null) {
        method.getAnnotations().add(overrideAnnotation);
      }
      final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
        appendable.append("return (%sGrammarAccess) super.getGrammarAccess();".formatted(GrammarUtil.getSimpleName(format.getTargetGrammar())));
      };
      jvmTypesBuilder.setBody(method, body);
    };
    it.getMembers().add(jvmTypesBuilder.toMethod(format, "getGrammarAccess",
        typeReferences.getTypeForName(getFullyQualifiedName(format.getTargetGrammar()), format.getTargetGrammar()), initializer));
  }

  public void inferConfigureAcsFormatting(final FormatConfiguration format, final JvmGenericType it) {
    final Procedure1<JvmOperation> initializer = (final JvmOperation method) -> {
      method.setVisibility(JvmVisibility.PROTECTED);
      final JvmAnnotationReference overrideAnnotation = createOverrideAnnotation(format);
      if (overrideAnnotation != null) {
        method.getAnnotations().add(overrideAnnotation);
      }
      method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
        appendable.append("init(config, getGrammarAccess());");
      };
      jvmTypesBuilder.setBody(method, body);
    };
    it.getMembers().add(jvmTypesBuilder.toMethod(format, "configureAcsFormatting", _typeReferenceBuilder.typeRef("void"), initializer));
  }

  public void inferInit(final FormatConfiguration format, final JvmGenericType it) {
    final Procedure1<JvmOperation> initializer = (final JvmOperation method) -> {
      final Map<String, String> parameters = new LinkedHashMap<>();
      parameters.put(PARAMETER_CONFIG, "the format configuration");
      parameters.put(PARAMETER_GRAMMAR_ACCESS, "the grammar access for the grammar");
      jvmTypesBuilder.setDocumentation(method, generateJavaDoc("Calls all configXyz methods declared in this class.", parameters));
      method.setVisibility(JvmVisibility.PROTECTED);
      method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_GRAMMAR_ACCESS,
          _typeReferenceBuilder.typeRef(getFullyQualifiedName(format.getTargetGrammar()))));
      final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
        final List<String> rules = listConfigRules(format);
        for (int i = 0; i < rules.size(); i++) {
          if (i != 0) {
            appendable.newLine();
          }
          appendable.append(rules.get(i));
        }
      };
      jvmTypesBuilder.setBody(method, body);
    };
    it.getMembers().add(jvmTypesBuilder.toMethod(format, "init", _typeReferenceBuilder.typeRef("void"), initializer));
  }

  public void inferRules(final FormatConfiguration format, final JvmGenericType it) {
    jvmTypesBuilder.operator_add(it.getMembers(),
        Iterables.<JvmMember> concat(map(FormatGeneratorUtil.getParserRules(format), c -> createRule(format, c))));
    jvmTypesBuilder.operator_add(it.getMembers(),
        Iterables.<JvmMember> concat(map(FormatGeneratorUtil.getEnumRules(format), c -> createRule(format, c))));
    jvmTypesBuilder.operator_add(it.getMembers(),
        Iterables.<JvmMember> concat(map(FormatGeneratorUtil.getTerminalRules(format), c -> createRule(format, c))));
    if (FormatGeneratorUtil.getWildcardRule(format) != null) {
      final Procedure1<JvmOperation> initializer = (final JvmOperation method) -> {
        final Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put(PARAMETER_CONFIG, "the format configuration");
        parameters.put(PARAMETER_ELEMENTS, "the grammar access for the grammar");
        jvmTypesBuilder.setDocumentation(method, generateJavaDoc("Configuration for IGrammarAccess.findXyz() methods.", parameters));
        method.setVisibility(JvmVisibility.PROTECTED);
        method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
        method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_ELEMENTS,
            _typeReferenceBuilder.typeRef(getFullyQualifiedName(GrammarUtil.getGrammar(format.getTargetGrammar())))));
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
          final List<String> directives = new ArrayList<>();
          for (final WildcardRuleDirective d : FormatGeneratorUtil.getWildcardRule(format).getDirectives()) {
            directives.add(directive(d, getRuleName(FormatGeneratorUtil.getWildcardRule(format))).toString());
          }
          appendable.append(fixLastLine(String.join("", directives)));
        };
        jvmTypesBuilder.setBody(method, body);
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(format, "configFindElements", _typeReferenceBuilder.typeRef("void"), initializer));
    }
  }

  public void inferLocatorActivators(final FormatConfiguration format, final JvmGenericType it) {
    final List<Rule> rules = new LinkedList<>();
    Iterables.addAll(rules, Iterables.<Rule> concat(
        Iterables.concat(FormatGeneratorUtil.getParserRules(format), FormatGeneratorUtil.getTerminalRules(format)),
        FormatGeneratorUtil.getEnumRules(format)));
    rules.add(FormatGeneratorUtil.getWildcardRule(format));
    for (final Rule rule : rules) {
      final List<EObject> directives = new LinkedList<>();
      if (rule instanceof GrammarRule grammarRule) {
        Iterables.addAll(directives, grammarRule.getDirectives());
      } else if (rule instanceof WildcardRule wildcardRule) {
        Iterables.addAll(directives, wildcardRule.getDirectives());
      }
      for (final EObject directive : Iterables.filter(directives, Objects::nonNull)) {
        for (final Matcher matcher : collectMatchers(directive)) {
          if (matcher.getLocator() instanceof ColumnLocator columnLocator && columnLocator.getParameter() != null) {
            it.getMembers().add(createParameterCalculatorInnerClass(format, rule, directive, matcher, columnLocator.getParameter(), it));
          }
          if (matcher.getLocator() instanceof IndentLocator indentLocator && indentLocator.getParameter() != null) {
            it.getMembers().add(createParameterCalculatorInnerClass(format, rule, directive, matcher, indentLocator.getParameter(), it));
          }
          if (matcher.getCondition() != null) {
            it.getMembers().add(createLocatorActivatorInnerClass(format, rule, directive, matcher, it));
          }
        }
      }
    }
  }

  public JvmGenericType createLocatorActivatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final JvmGenericType type) {
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      it.getSuperTypes().add(getLocatorActivatorSuperType(format, rule));
      final Procedure1<JvmOperation> methodInitializer = (final JvmOperation method) -> {
        method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONTEXT,
            typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), format)));
        method.getParameters().add(createCurrenctColumnParameter());
        final Resource formatResource = format.eResource();
        final Resource matcherResource = matcher.eResource();
        if (!Objects.equals(formatResource, matcherResource)) {
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> xbaseCompiler.compile(matcher.getCondition(),
              appendable, getLocatorActivatorReturnType(format), null);
          jvmTypesBuilder.setBody(method, body);
        } else {
          jvmTypesBuilder.setBody(method, matcher.getCondition());
        }
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(matcher, METHOD_ACTIVATE, getLocatorActivatorReturnType(format), methodInitializer));
    };
    return jvmTypesBuilder.toClass(format, getLocatorActivatorName(rule, directive, matcher), initializer);
  }

  public JvmFormalParameter createCurrenctColumnParameter() {
    final JvmFormalParameter result = typesFactory.createJvmFormalParameter();
    result.setName(PARAMETER_COLUMN);
    result.setParameterType(_typeReferenceBuilder.typeRef(Integer.class));
    return result;
  }

  public JvmGenericType createParameterCalculatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final XExpression parameterCalculation, final JvmGenericType type) {
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      it.getSuperTypes().add(getParameterCalculatorSuperType(format, rule));
      final Procedure1<JvmOperation> methodInitializer = (final JvmOperation method) -> {
        method.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONTEXT,
            typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), format)));
        method.getParameters().add(createCurrenctColumnParameter());
        final Resource formatResource = format.eResource();
        final Resource matcherResource = matcher.eResource();
        if (!Objects.equals(formatResource, matcherResource)) {
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> xbaseCompiler.compile(parameterCalculation,
              appendable, getParameterCalculatorReturnType(format), null);
          jvmTypesBuilder.setBody(method, body);
        } else {
          jvmTypesBuilder.setBody(method, parameterCalculation);
        }
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(matcher, METHOD_CALCULATE, getParameterCalculatorReturnType(format), methodInitializer));
    };
    return jvmTypesBuilder.toClass(format, getParameterCalculatorName(rule, directive, matcher), initializer);
  }

  public List<String> listConfigRules(final FormatConfiguration format) {
    final List<String> configRules = new ArrayList<>();
    if (FormatGeneratorUtil.getWildcardRule(format) != null) {
      configRules.add("configFindElements(config, grammarAccess);");
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getParserRules(format)) {
      configRules.add("config%s(config, grammarAccess.%s);".formatted(rule.getTargetRule().getName(), grammarAccess.gaElementsAccessor(rule.getTargetRule())));
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getEnumRules(format)) {
      configRules.add("config%s(config, grammarAccess.%s);".formatted(rule.getTargetRule().getName(), grammarAccess.gaRuleAccessor(rule.getTargetRule())));
    }
    for (final GrammarRule rule : FormatGeneratorUtil.getTerminalRules(format)) {
      configRules.add("config%s(config, grammarAccess.%s);".formatted(rule.getTargetRule().getName(), grammarAccess.gaRuleAccessor(rule.getTargetRule())));
    }
    return configRules;
  }

  public String generateJavaDoc(final String description, final Map<String, String> parameters) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(description);
    builder.newLineIfNotEmpty();
    builder.newLine();
    for (final Map.Entry<String, String> parameter : parameters.entrySet()) {
      builder.append("@param ");
      builder.append(parameter.getKey());
      builder.newLineIfNotEmpty();
      builder.append("     ");
      builder.append("- ");
      builder.append(parameter.getValue(), "     ");
      builder.newLineIfNotEmpty();
    }
    return builder.toString();
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
      final Procedure1<JvmField> initializer = (final JvmField it) -> {
        jvmTypesBuilder.setDocumentation(it, locatorString(constant));
        it.setStatic(true);
        it.setFinal(true);
        it.setVisibility(JvmVisibility.PROTECTED);
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable.append("\"" + constant.getStringValue() + "\"");
        jvmTypesBuilder.setInitializer(it, body);
      };
      return jvmTypesBuilder.toField(constant, constant.getName(), typeReferences.getTypeForName("String", constant), initializer);
    } else if (constant.getIntValue() != null) {
      final Procedure1<JvmField> initializer = (final JvmField it) -> {
        jvmTypesBuilder.setDocumentation(it, locatorString(constant));
        it.setStatic(true);
        it.setFinal(true);
        it.setVisibility(JvmVisibility.PROTECTED);
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable.append(constant.getIntValue().toString());
        jvmTypesBuilder.setInitializer(it, body);
      };
      return jvmTypesBuilder.toField(constant, constant.getName(), typeReferences.getTypeForName("int", constant), initializer);
    }
    return null;
  }

  public List<Matcher> collectMatchers(final EObject directive) {
    final List<Matcher> matchers = new LinkedList<>();
    if (directive instanceof GroupBlock groupBlock) {
      if (groupBlock.getMatcherList() != null) {
        Iterables.addAll(matchers, groupBlock.getMatcherList().getMatchers());
      }
    } else if (directive instanceof SpecificDirective specificDirective) {
      if (specificDirective.getMatcherList() != null) {
        Iterables.addAll(matchers, specificDirective.getMatcherList().getMatchers());
      }
    } else if (directive instanceof ContextFreeDirective contextFreeDirective) {
      if (contextFreeDirective.getMatcherList() != null) {
        Iterables.addAll(matchers, contextFreeDirective.getMatcherList().getMatchers());
      }
    } else if (directive instanceof KeywordPair keywordPair) {
      if (keywordPair.getLeftMatchers() != null) {
        Iterables.addAll(matchers, keywordPair.getLeftMatchers());
      }
      if (keywordPair.getRightMatchers() != null) {
        Iterables.addAll(matchers, keywordPair.getRightMatchers());
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

  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorActivator.class, typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorActivator.class, typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    return _typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, typeReferences.getTypeForName(getGrammarElementNameFromSelf(rule), formatConfiguration));
  }

  protected String _getGrammarElementNameFromSelf(final GrammarRule rule) {
    final String originalRuleName = getRuleName(rule);
    String actualRuleName = originalRuleName;
    if (rule.getTargetRule() == null || rule.getTargetRule().getType() == null || rule.getTargetRule().getType().getClassifier() == null) {
      return actualRuleName;
    } else {
      final AbstractRule targetRule = rule.getTargetRule();
      final TypeRef type = targetRule != null ? targetRule.getType() : null;
      final EClassifier classifier = type != null ? type.getClassifier() : null;
      final String classifierName = classifier != null ? classifier.getName() : null;
      if (!Objects.equals(actualRuleName, classifierName)) {
        actualRuleName = rule.getTargetRule().getType().getClassifier().getName();
      }
    }
    final AbstractRule targetRule = rule.getTargetRule();
    final TypeRef type = targetRule != null ? targetRule.getType() : null;
    final AbstractMetamodelDeclaration metamodel = type != null ? type.getMetamodel() : null;
    if (metamodel == null) {
      return actualRuleName;
    } else {
      if (!Objects.equals(actualRuleName, originalRuleName)) {
        final boolean anyMatch = metamodel.getEPackage().getEClassifiers().stream()
            .anyMatch(it -> it instanceof EClass && it.getName().equalsIgnoreCase(originalRuleName));
        if (anyMatch) {
          actualRuleName = originalRuleName;
        }
      }
      final URI uri = EcoreUtil2.getURI(metamodel.getEPackage());
      final String metamodelPackage = uri != null ? uri.segment(1) : null;
      if (metamodelPackage == null) {
        return actualRuleName;
      }
      final EPackage ePackage = metamodel.getEPackage();
      final String ePackageName = ePackage != null ? ePackage.getName() : null;
      return metamodelPackage.substring(0, metamodelPackage.lastIndexOf(".core")) + "." + ePackageName + "." + actualRuleName;
    }
  }

  protected String _getGrammarElementNameFromSelf(final WildcardRule rule) {
    return EObject.class.getName();
  }

  public int getMatcherIndex(final Matcher matcher) {
    final MatcherList matcherList = EcoreUtil2.<MatcherList> getContainerOfType(matcher, MatcherList.class);
    return matcherList.getMatchers().indexOf(matcher);
  }

  public String getLocatorActivatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    return ("ActivatorFor" + getRuleName(rule) + getMatcherName(matcher, directive)).replace("Impl", "");
  }

  public String getLocatorActivatorName(final String partialName, final Matcher matcher) {
    return ("ActivatorFor" + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator())
        + toFirstUpper(matcher.getType().name().toLowerCase())).replace("Impl", "");
  }

  public String getParameterCalculatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    return ("ParameterCalculatorFor" + getRuleName(rule) + getMatcherName(matcher, directive)).replace("Impl", "");
  }

  public String getParameterCalculatorName(final String partialName, final Matcher matcher) {
    return ("ParameterCalculatorFor" + partialName + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator())
        + toFirstUpper(matcher.getType().name().toLowerCase())).replace("Impl", "");
  }

  protected String _getRuleName(final GrammarRule rule) {
    final AbstractRule targetRule = rule.getTargetRule();
    return targetRule != null ? targetRule.getName() : null;
  }

  protected String _getRuleName(final WildcardRule rule) {
    return "Wildcard";
  }

  protected String _getRuleName(final EObject rule) {
    return EObject.class.getSimpleName();
  }

  public String getMatcherName(final Matcher matcher, final EObject directive) {
    return getDirectiveName(directive) + getMatcherIndex(matcher) + getLocatorName(matcher.getLocator())
        + toFirstUpper(matcher.getType().name().toLowerCase());
  }

  public String getLocatorName(final EObject locator) {
    final Class<? extends EObject> locatorClass = locator != null ? locator.getClass() : null;
    final String simpleName = locatorClass != null ? locatorClass.getSimpleName() : null;
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

  protected String _getDirectiveName(final GroupBlock directive) {
    final GrammarRule grammarRule = EcoreUtil2.<GrammarRule> getContainerOfType(directive, GrammarRule.class);
    final List<GroupBlock> directives = new ArrayList<>();
    Iterables.addAll(directives, Iterables.filter(grammarRule.getDirectives(), GroupBlock.class));
    return "Group" + (directives.indexOf(directive) + 1);
  }

  protected String _getDirectiveName(final SpecificDirective directive) {
    final StringBuilder directiveName = new StringBuilder(64);
    for (final GrammarElementReference grammarElementReference : directive.getGrammarElements()) {
      if (grammarElementReference.getAssignment() != null) {
        directiveName.append(grammarAccess.gaElementAccessMethodName(grammarElementReference.getAssignment())
            .replaceFirst("get", "").replaceFirst("(?s)(.*)Assignment", "$1"));
      }
      if (grammarElementReference.getRuleCall() != null) {
        directiveName.append(toFirstUpper(grammarElementReference.getRuleCall().getRule().getName()));
      }
      if (grammarElementReference.getRule() != null) {
        directiveName.append(toFirstUpper(grammarElementReference.getRule().getName()));
      }
      if (grammarElementReference.getKeyword() != null) {
        directiveName.append(toFirstUpper(convertNonAlphaNumeric(grammarElementReference.getKeyword().getValue())));
      }
      if (grammarElementReference.getSelf() != null) {
        directiveName.append("Self");
      }
    }
    return directiveName.toString();
  }

  protected String _getDirectiveName(final ContextFreeDirective directive) {
    final StringBuilder directiveName = new StringBuilder(64);
    for (final GrammarElementLookup grammarElementLookup : directive.getGrammarElements()) {
      if (grammarElementLookup.getRule() != null) {
        directiveName.append(toFirstUpper(grammarElementLookup.getRule().getName()));
      }
      if (grammarElementLookup.getKeyword() != null) {
        directiveName.append(toFirstUpper(convertNonAlphaNumeric(grammarElementLookup.getKeyword())));
      }
    }
    return directiveName.toString();
  }

  protected String _getDirectiveName(final KeywordPair directive) {
    return convertNonAlphaNumeric(directive.getLeft()) + convertNonAlphaNumeric(directive.getRight());
  }

  protected String _getDirectiveName(final EObject directive) {
    return String.valueOf(directive.hashCode());
  }

  public Iterable<JvmMember> createRule(final FormatConfiguration format, final GrammarRule rule) {
    final List<JvmMember> members = new ArrayList<>();
    final Procedure1<JvmOperation> initializer = (final JvmOperation it) -> {
      it.setFinal(false);
      it.setVisibility(JvmVisibility.PROTECTED);
      it.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_CONFIG, _typeReferenceBuilder.typeRef(BASE_FORMAT_CONFIG)));
      final AbstractRule targetRule = rule.getTargetRule();
      if (targetRule instanceof ParserRule) {
        final String ruleName = getFullyQualifiedName(GrammarUtil.getGrammar(rule.getTargetRule())) + "$"
            + grammarAccess.gaRuleAccessorClassName(rule.getTargetRule());
        it.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_ELEMENTS, typeReferences.getTypeForName(ruleName, rule.getTargetRule())));
        final String description = "Configuration for %s.".formatted(rule.getTargetRule().getName());
        final String elementsDoc = "the grammar access for %s elements".formatted(rule.getTargetRule().getName());
        final Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put(PARAMETER_CONFIG, "the format configuration");
        parameters.put(PARAMETER_ELEMENTS, elementsDoc);
        jvmTypesBuilder.setDocumentation(it, generateJavaDoc(description, parameters));
      } else if (targetRule instanceof EnumRule) {
        it.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_RULE, typeReferences.getTypeForName(EnumRule.class.getName(), rule.getTargetRule())));
        final String description = "Configuration for %s.".formatted(rule.getTargetRule().getName());
        final String ruleDoc = "the enum rule for %s".formatted(rule.getTargetRule().getName());
        final Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put(PARAMETER_CONFIG, "the format configuration");
        parameters.put(PARAMETER_RULE, ruleDoc);
        jvmTypesBuilder.setDocumentation(it, generateJavaDoc(description, parameters));
      } else if (targetRule instanceof TerminalRule) {
        it.getParameters().add(jvmTypesBuilder.toParameter(format, PARAMETER_RULE, typeReferences.getTypeForName(TerminalRule.class.getName(), rule.getTargetRule())));
        final String description = "Configuration for %s.".formatted(rule.getTargetRule().getName());
        final String ruleDoc = "the terminal rule for %s".formatted(rule.getTargetRule().getName());
        final Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put(PARAMETER_CONFIG, "the format configuration");
        parameters.put(PARAMETER_RULE, ruleDoc);
        jvmTypesBuilder.setDocumentation(it, generateJavaDoc(description, parameters));
      }
      final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> {
        final List<String> directives = new ArrayList<>();
        for (final EObject d : rule.getDirectives()) {
          directives.add(directive(d, getRuleName(rule)).toString());
        }
        appendable.append(fixLastLine(String.join("", directives)));
      };
      jvmTypesBuilder.setBody(it, body);
    };
    members.add(jvmTypesBuilder.toMethod(format, "config" + rule.getTargetRule().getName(), _typeReferenceBuilder.typeRef("void"), initializer));
    return members;
  }

  public String fixLastLine(final String content) {
    if (content.endsWith("\r\n")) {
      return content.substring(0, content.length() - 2);
    } else {
      return content;
    }
  }

  protected CharSequence _directive(final SpecificDirective dir, final String partialName) {
    return matchReference(dir.getMatcherList(), dir.getGrammarElements(), partialName + getDirectiveName(dir));
  }

  protected CharSequence _directive(final ContextFreeDirective dir, final String partialName) {
    return matchLookup(dir.getMatcherList(), dir.getGrammarElements(), partialName + getDirectiveName(dir));
  }

  protected CharSequence _directive(final GroupBlock dir, final String partialName) {
    if (dir.getMatcherList() != null) {
      final List<CompoundElement> grammarElement = new ArrayList<>();
      grammarElement.add(dir.getGrammarElement());
      final EList<CompoundElement> grammarElements = new BasicEList<>(Collections.unmodifiableList(grammarElement));
      return matchReference(dir.getMatcherList(), grammarElements, partialName + getDirectiveName(dir));
    } else if (dir.getSubGroup() != null) {
      return directive(dir.getSubGroup(), partialName + getDirectiveName(dir));
    } else {
      StringConcatenation builder = new StringConcatenation();
      for (final GrammarRuleDirective d : dir.getDirectives()) {
        builder.append(directive(d, partialName + getDirectiveName(dir)));
      }
      return builder;
    }
  }

  protected CharSequence _directive(final KeywordPair dir, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("// ");
    builder.append(locatorString(dir));
    builder.newLineIfNotEmpty();
    builder.append("for (final org.eclipse.xtext.util.Pair<org.eclipse.xtext.Keyword, org.eclipse.xtext.Keyword> pair : elements.findKeywordPairs(\"");
    builder.append(dir.getLeft());
    builder.append("\", \"");
    builder.append(dir.getRight());
    builder.append("\")) {");
    builder.newLineIfNotEmpty();
    for (final Matcher matcher : dir.getLeftMatchers()) {
      builder.append(matchLookupPartial(matcher.getLocator(), matcher, "pair.getFirst()", partialName + getDirectiveName(dir)));
      builder.newLineIfNotEmpty();
    }
    for (final Matcher matcher : dir.getRightMatchers()) {
      builder.append(matchLookupPartial(matcher.getLocator(), matcher, "pair.getSecond()", partialName + getDirectiveName(dir)));
      builder.newLineIfNotEmpty();
    }
    builder.append("}");
    builder.newLine();
    return builder;
  }

  protected CharSequence _directive(final Object dir, final String partialName) {
    throw new UnsupportedOperationException("Unknown directive " + dir.getClass().getName());
  }

  public CharSequence matchLookup(final MatcherList matcherList, final EList<GrammarElementLookup> elements, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if (!elements.isEmpty()) {
      if (!Iterables.isEmpty(Iterables.filter(elements, e -> e.getRule() != null))) {
        builder.append("// ");
        builder.append(locatorString(matcherList));
        builder.newLineIfNotEmpty();
        builder.append("for (org.eclipse.xtext.RuleCall ruleCall : elements.findRuleCalls(");
        boolean hasElements = false;
        for (final GrammarElementLookup element : Iterables.filter(elements, e -> e.getRule() != null)) {
          if (!hasElements) {
            hasElements = true;
          } else {
            builder.appendImmediate(", ", "");
          }
          builder.append("elements.");
          builder.append(grammarAccess.gaRuleAccessor(element.getRule()));
        }
        builder.append(")) {");
        builder.newLineIfNotEmpty();
        for (final Matcher matcher : matcherList.getMatchers()) {
          builder.append("  ");
          builder.append(matchLookupPartial(matcher.getLocator(), matcher, "ruleCall", partialName), "  ");
          builder.newLineIfNotEmpty();
        }
        builder.append("}");
        builder.newLine();
      }
      if (!Iterables.isEmpty(Iterables.filter(elements, e -> e.getKeyword() != null))) {
        builder.append("// ");
        builder.append(locatorString(matcherList));
        builder.newLineIfNotEmpty();
        builder.append("for (org.eclipse.xtext.Keyword keyword : elements.findKeywords(");
        boolean hasElements = false;
        for (final GrammarElementLookup element : Iterables.filter(elements, e -> e.getKeyword() != null)) {
          if (!hasElements) {
            hasElements = true;
          } else {
            builder.appendImmediate(", ", "");
          }
          builder.append("\"");
          builder.append(element.getKeyword());
          builder.append("\"");
        }
        builder.append(")) {");
        builder.newLineIfNotEmpty();
        for (final Matcher matcher : matcherList.getMatchers()) {
          builder.append("  ");
          builder.append(matchLookupPartial(matcher.getLocator(), matcher, "keyword", partialName), "  ");
          builder.newLineIfNotEmpty();
        }
        builder.append("}");
        builder.newLine();
      }
    }
    return builder;
  }

  protected CharSequence _matchLookupPartial(final ColumnLocator columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if ("before".equals(matcher.getType().getLiteral())) {
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(columnLocator.getValue()));
      builder.append(", ");
      builder.append(columnLocator.isFixed());
      builder.append(", ");
      builder.append(columnLocator.isRelative());
      builder.append(", ");
      builder.append(columnLocator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").before(");
      builder.append(eobjectTypeName);
      builder.append(");  // ");
      builder.append(locatorString(columnLocator));
      builder.newLineIfNotEmpty();
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(columnLocator.getValue()));
      builder.append(", ");
      builder.append(columnLocator.isFixed());
      builder.append(", ");
      builder.append(columnLocator.isRelative());
      builder.append(", ");
      builder.append(columnLocator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").after(");
      builder.append(eobjectTypeName);
      builder.append(");  // ");
      builder.append(locatorString(columnLocator));
      builder.newLineIfNotEmpty();
    } else {
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(columnLocator.getValue()));
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").");
      builder.append(matcherType(matcher.getType()));
      builder.append("(");
      builder.append(eobjectTypeName);
      builder.append("); // ");
      builder.append(locatorString(columnLocator));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence _matchLookupPartial(final OffsetLocator offsetLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if ("before".equals(matcher.getType().getLiteral())) {
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(offsetLocator.getValue()));
      builder.append(", ");
      builder.append(offsetLocator.isFixed());
      builder.append(", true, ");
      builder.append(offsetLocator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").before(");
      builder.append(eobjectTypeName);
      builder.append(");  // ");
      builder.append(locatorString(offsetLocator));
      builder.newLineIfNotEmpty();
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(offsetLocator.getValue()));
      builder.append(", ");
      builder.append(offsetLocator.isFixed());
      builder.append(", true, ");
      builder.append(offsetLocator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").after(");
      builder.append(eobjectTypeName);
      builder.append(");  // ");
      builder.append(locatorString(offsetLocator));
      builder.newLineIfNotEmpty();
    } else {
      builder.append("config.setOffset(");
      builder.append(getValueOrConstant(offsetLocator.getValue()));
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").");
      builder.append(matcherType(matcher.getType()));
      builder.append("(");
      builder.append(eobjectTypeName);
      builder.append("); // ");
      builder.append(locatorString(offsetLocator));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence _matchLookupPartial(final EObject locator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("config.");
    builder.append(locator(matcher, matcher.getLocator(), partialName));
    builder.append(".");
    builder.append(matcherType(matcher.getType()));
    builder.append("(");
    builder.append(eobjectTypeName);
    builder.append(");");
    return builder;
  }

  public CharSequence matchReference(final MatcherList matcherList, final EList<? extends EObject> elements, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if (!elements.isEmpty()) {
      for (final Matcher matcher : matcherList.getMatchers()) {
        if (Boolean.TRUE.equals(FormatGeneratorUtil.isTwoArgumentMatcherType(matcher.getType()))) {
          builder.append(match(matcher, elements.get(0), elements.get(1), matcher.getLocator(), partialName));
          builder.newLineIfNotEmpty();
        } else {
          for (final EObject e : elements) {
            builder.append(match(matcher, e, matcher.getLocator(), partialName));
          }
          builder.newLineIfNotEmpty();
        }
      }
    }
    return builder;
  }

  public CharSequence match(final Matcher matcher, final EObject element1, final EObject element2, final EObject locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("config.");
    builder.append(locator(matcher, matcher.getLocator(), partialName));
    builder.append(".");
    builder.append(matcherType(matcher.getType()));
    builder.append("(");
    builder.append(elementAccess(element1));
    builder.append(", ");
    builder.append(elementAccess(element2));
    builder.append("); // ");
    builder.append(locatorString(matcher));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("config.");
    builder.append(locator(matcher, matcher.getLocator(), partialName));
    builder.append(".");
    builder.append(matcherType(matcher.getType()));
    builder.append("(");
    builder.append(elementAccess(element));
    builder.append("); // ");
    builder.append(locatorString(matcher));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final NoFormatLocator locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("config.");
    builder.append(locator(matcher, matcher.getLocator(), partialName));
    builder.append(".");
    builder.append(matcherType(matcher.getType()));
    builder.append("(");
    builder.append(elementAccess(element));
    builder.append("); // ");
    builder.append(locatorString(matcher));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final ColumnLocator locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if ("before".equals(matcher.getType().getLiteral())) {
      if (locator.getParameter() != null) {
        builder.append("config.setColumn(");
        builder.append(locator.isFixed());
        builder.append(", ");
        builder.append(locator.isRelative());
        builder.append(", ");
        builder.append(locator.isNobreak());
        builder.append(", new ");
        builder.append(getParameterCalculatorName(partialName, matcher));
        builder.append("()");
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").before(");
        builder.append(elementAccess(element));
        builder.append(");  // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
        builder.append("config.setColumn(");
        builder.append(locator.isFixed());
        builder.append(", ");
        builder.append(locator.isRelative());
        builder.append(", ");
        builder.append(locator.isNobreak());
        builder.append(", new ");
        builder.append(getParameterCalculatorName(partialName, matcher));
        builder.append("()");
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").after(");
        builder.append(elementAccess(element));
        builder.append(");  // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
      } else {
        builder.append("config.setColumn(");
        builder.append(getValueOrConstant(locator.getValue()));
        builder.append(", ");
        builder.append(locator.isFixed());
        builder.append(", ");
        builder.append(locator.isRelative());
        builder.append(", ");
        builder.append(locator.isNobreak());
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").before(");
        builder.append(elementAccess(element));
        builder.append(");  // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
        builder.append("config.setColumn(");
        builder.append(getValueOrConstant(locator.getValue()));
        builder.append(", ");
        builder.append(locator.isFixed());
        builder.append(", ");
        builder.append(locator.isRelative());
        builder.append(", ");
        builder.append(locator.isNobreak());
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").after(");
        builder.append(elementAccess(element));
        builder.append(");  // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
      }
    } else {
      if (locator.getParameter() != null) {
        builder.append("config.setColumn(new ");
        builder.append(getParameterCalculatorName(partialName, matcher));
        builder.append("()");
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").");
        builder.append(matcherType(matcher.getType()));
        builder.append("(");
        builder.append(elementAccess(element));
        builder.append("); // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
      } else {
        builder.append("config.setColumn(");
        builder.append(getValueOrConstant(locator.getValue()));
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
        builder.append(").");
        builder.append(matcherType(matcher.getType()));
        builder.append("(");
        builder.append(elementAccess(element));
        builder.append("); // ");
        builder.append(locatorString(matcher));
        builder.newLineIfNotEmpty();
      }
    }
    return builder;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final OffsetLocator locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if ("before".equals(matcher.getType().getLiteral())) {
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(locator.getValue()));
      builder.append(", ");
      builder.append(locator.isFixed());
      builder.append(", true, ");
      builder.append(locator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").before(");
      builder.append(elementAccess(element));
      builder.append(");  // ");
      builder.append(locatorString(matcher));
      builder.newLineIfNotEmpty();
      builder.append("config.setColumn(");
      builder.append(getValueOrConstant(locator.getValue()));
      builder.append(", ");
      builder.append(locator.isFixed());
      builder.append(", true, ");
      builder.append(locator.isNobreak());
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").after(");
      builder.append(elementAccess(element));
      builder.append(");  // ");
      builder.append(locatorString(matcher));
      builder.newLineIfNotEmpty();
    } else {
      builder.append("config.setOffset(");
      builder.append(getValueOrConstant(locator.getValue()));
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(").");
      builder.append(matcherType(matcher.getType()));
      builder.append("(");
      builder.append(elementAccess(element));
      builder.append("); // ");
      builder.append(locatorString(matcher));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence _match(final Matcher matcher, final EObject element, final IndentLocator locator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("config.");
    builder.append(locator(matcher, matcher.getLocator(), partialName));
    builder.append(".");
    builder.append(matcherType(matcher.getType()));
    builder.append("(");
    builder.append(elementAccess(element));
    builder.append("); // ");
    builder.append(locatorString(matcher));
    builder.newLineIfNotEmpty();
    return builder;
  }

  public String matcherType(final MatcherType matcherType) {
    return matcherType.getLiteral();
  }

  protected CharSequence _elementAccess(final GrammarElementLookup grammarElementLookup) {
    StringConcatenation builder = new StringConcatenation();
    if (grammarElementLookup.getRule() != null) {
      builder.append("elements.findRuleCalls(");
      builder.append(grammarAccess.gaElementsAccessor(grammarElementLookup.getRule()));
      builder.append(")");
    } else if (grammarElementLookup.getKeyword() != null) {
      builder.append("elements.findKeywords(\"");
      builder.append(grammarElementLookup.getKeyword());
      builder.append("\")");
    }
    return builder;
  }

  protected CharSequence _elementAccess(final GrammarElementReference grammarElementReference) {
    if (grammarElementReference.getRuleCall() != null) {
      return elementAccess(grammarElementReference.getRuleCall());
    } else if (grammarElementReference.getKeyword() != null) {
      return elementAccess(grammarElementReference.getKeyword());
    } else if (grammarElementReference.getAssignment() != null) {
      return elementAccess(grammarElementReference.getAssignment());
    } else if (grammarElementReference.getSelf() != null) {
      if (Boolean.TRUE.equals(FormatGeneratorUtil.containedByParserRule(grammarElementReference))) {
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
    return "getGrammarAccess().%s".formatted(grammarAccess.gaRuleAccessor(abstractRule));
  }

  protected CharSequence _elementAccess(final AbstractElement abstractElement) {
    return "elements.%s".formatted(grammarAccess.gaElementAccessor(abstractElement));
  }

  protected CharSequence _elementAccess(final Object object) {
    throw new UnsupportedOperationException("Unknown Xtext element " + object.getClass().getName());
  }

  protected CharSequence _locator(final Matcher matcher, final SpaceLocator spaceLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if (spaceLocator.isNoSpace()) {
      builder.append("setNoSpace(");
      if (matcher.getCondition() != null) {
        builder.append("new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(")");
    } else {
      builder.append("setSpace(");
      builder.append(getValueOrConstant(spaceLocator.getValue()));
      if (matcher.getCondition() != null) {
        builder.append(", new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(")");
    }
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final RightPaddingLocator rightPaddingLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("setRightPadding(");
    builder.append(getValueOrConstant(rightPaddingLocator.getValue()));
    if (matcher.getCondition() != null) {
      builder.append(", new ");
      builder.append(getLocatorActivatorName(partialName, matcher));
      builder.append("()");
    }
    builder.append(")");
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final LinewrapLocator linewrapLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if (linewrapLocator.isNoLinewrap()) {
      builder.append("setNoLinewrap(");
      if (matcher.getCondition() != null) {
        builder.append("new ");
        builder.append(getLocatorActivatorName(partialName, matcher));
        builder.append("()");
      }
      builder.append(")");
    } else {
      builder.append("setLinewrap(");
      if (linewrapLocator.getValue() != null) {
        builder.append(getValueOrConstant(linewrapLocator.getValue()));
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
      } else if (linewrapLocator.getMinimum() != null) {
        builder.append(getValueOrConstant(linewrapLocator.getMinimum()));
        builder.append(", ");
        builder.append(getValueOrConstant(linewrapLocator.getDefault()));
        builder.append(", ");
        builder.append(getValueOrConstant(linewrapLocator.getMaximum()));
        if (matcher.getCondition() != null) {
          builder.append(", new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
      } else {
        if (matcher.getCondition() != null) {
          builder.append("new ");
          builder.append(getLocatorActivatorName(partialName, matcher));
          builder.append("()");
        }
      }
      builder.append(")");
    }
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final ColumnLocator columnLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("setColumn(");
    builder.append(getValueOrConstant(columnLocator.getValue()));
    builder.append(", ");
    builder.append(columnLocator.isFixed());
    builder.append(", ");
    builder.append(columnLocator.isRelative());
    builder.append(", ");
    builder.append(columnLocator.isNobreak());
    if (matcher.getCondition() != null) {
      builder.append(", new ");
      builder.append(getLocatorActivatorName(partialName, matcher));
      builder.append("()");
    }
    builder.append(")");
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final OffsetLocator offsetLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("setColumn(");
    builder.append(getValueOrConstant(offsetLocator.getValue()));
    builder.append(", ");
    builder.append(offsetLocator.isFixed());
    builder.append(", true, ");
    builder.append(offsetLocator.isNobreak());
    if (matcher.getCondition() != null) {
      builder.append(", new ");
      builder.append(getLocatorActivatorName(partialName, matcher));
      builder.append("()");
    }
    builder.append(")");
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final IndentLocator indentLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    if (indentLocator.isIncrement()) {
      builder.append("setIndentationIncrement(");
    } else {
      builder.append("setIndentationDecrement(");
    }
    if (indentLocator.getValue() != null
        && (indentLocator.getValue().getReference() != null || indentLocator.getValue().getLiteral() >= 1)) {
      builder.append(getValueOrConstant(indentLocator.getValue()));
    } else if (indentLocator.getParameter() != null) {
      builder.append("new ");
      builder.append(getParameterCalculatorName(partialName, matcher));
      builder.append("()");
    }
    if (matcher.getCondition() != null) {
      if (indentLocator.getValue() != null || indentLocator.getParameter() != null) {
        builder.append(",");
      }
      builder.append(" new ");
      builder.append(getLocatorActivatorName(partialName, matcher));
      builder.append("()");
    }
    builder.append(")");
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final NoFormatLocator noFormatLocator, final String partialName) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("setNoFormat(");
    if (matcher.getCondition() != null) {
      builder.append("new ");
      builder.append(getLocatorActivatorName(partialName, matcher));
      builder.append("()");
    }
    builder.append(")");
    return builder;
  }

  protected CharSequence _locator(final Matcher matcher, final Locator locator, final String partialName) {
    throw new UnsupportedOperationException("Unknown locator " + locator.getClass().getName());
  }

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

  public String locatorString(final EObject object) {
    final String[] segments = EObjectUtil.getFileLocation(object).split("/");
    return segments.length == 0 ? null : segments[segments.length - 1];
  }

  @Override
  public void infer(final EObject format, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (format instanceof FormatConfiguration formatConfiguration) {
      _infer(formatConfiguration, acceptor, isPreIndexingPhase);
    } else if (format != null) {
      _infer(format, acceptor, isPreIndexingPhase);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(format, acceptor, isPreIndexingPhase));
    }
  }

  public JvmTypeReference getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(formatConfiguration, rule));
    }
  }

  public JvmTypeReference getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(formatConfiguration, rule));
    }
  }

  public String getGrammarElementNameFromSelf(final Rule rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getGrammarElementNameFromSelf(grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getGrammarElementNameFromSelf(wildcardRule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(rule));
    }
  }

  public String getRuleName(final EObject rule) {
    if (rule instanceof GrammarRule grammarRule) {
      return _getRuleName(grammarRule);
    } else if (rule instanceof WildcardRule wildcardRule) {
      return _getRuleName(wildcardRule);
    } else if (rule != null) {
      return _getRuleName(rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of());
    }
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
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of());
    }
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
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(dir, partialName));
    }
  }

  public CharSequence matchLookupPartial(final EObject columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    if (columnLocator instanceof ColumnLocator castColumnLocator) {
      return _matchLookupPartial(castColumnLocator, matcher, eobjectTypeName, partialName);
    } else if (columnLocator instanceof OffsetLocator offsetLocator) {
      return _matchLookupPartial(offsetLocator, matcher, eobjectTypeName, partialName);
    } else if (columnLocator != null) {
      return _matchLookupPartial(columnLocator, matcher, eobjectTypeName, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(columnLocator, matcher, eobjectTypeName, partialName));
    }
  }

  public CharSequence match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    if (locator instanceof ColumnLocator columnLocator) {
      return _match(matcher, element, columnLocator, partialName);
    } else if (locator instanceof IndentLocator indentLocator) {
      return _match(matcher, element, indentLocator, partialName);
    } else if (locator instanceof NoFormatLocator noFormatLocator) {
      return _match(matcher, element, noFormatLocator, partialName);
    } else if (locator instanceof OffsetLocator offsetLocator) {
      return _match(matcher, element, offsetLocator, partialName);
    } else if (locator != null) {
      return _match(matcher, element, locator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(matcher, element, locator, partialName));
    }
  }

  public CharSequence elementAccess(final Object grammarElementLookup) {
    if (grammarElementLookup instanceof GrammarElementLookup castGrammarElementLookup) {
      return _elementAccess(castGrammarElementLookup);
    } else if (grammarElementLookup instanceof GrammarElementReference grammarElementReference) {
      return _elementAccess(grammarElementReference);
    } else if (grammarElementLookup instanceof AbstractElement abstractElement) {
      return _elementAccess(abstractElement);
    } else if (grammarElementLookup instanceof AbstractRule abstractRule) {
      return _elementAccess(abstractRule);
    } else if (grammarElementLookup != null) {
      return _elementAccess(grammarElementLookup);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of());
    }
  }

  public CharSequence locator(final Matcher matcher, final Locator columnLocator, final String partialName) {
    if (columnLocator instanceof ColumnLocator castColumnLocator) {
      return _locator(matcher, castColumnLocator, partialName);
    } else if (columnLocator instanceof IndentLocator indentLocator) {
      return _locator(matcher, indentLocator, partialName);
    } else if (columnLocator instanceof LinewrapLocator linewrapLocator) {
      return _locator(matcher, linewrapLocator, partialName);
    } else if (columnLocator instanceof NoFormatLocator noFormatLocator) {
      return _locator(matcher, noFormatLocator, partialName);
    } else if (columnLocator instanceof OffsetLocator offsetLocator) {
      return _locator(matcher, offsetLocator, partialName);
    } else if (columnLocator instanceof RightPaddingLocator rightPaddingLocator) {
      return _locator(matcher, rightPaddingLocator, partialName);
    } else if (columnLocator instanceof SpaceLocator spaceLocator) {
      return _locator(matcher, spaceLocator, partialName);
    } else if (columnLocator != null) {
      return _locator(matcher, columnLocator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(matcher, columnLocator, partialName));
    }
  }

  public String getValueOrConstant(final EObject intValue) {
    if (intValue instanceof IntValue castIntValue) {
      return _getValueOrConstant(castIntValue);
    } else if (intValue instanceof StringValue stringValue) {
      return _getValueOrConstant(stringValue);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + List.of(intValue));
    }
  }

  private static <T, R> List<R> map(final List<T> source, final Function<T, R> mapper) {
    final List<R> result = new ArrayList<>();
    for (final T element : source) {
      result.add(mapper.apply(element));
    }
    return result;
  }

  private static String toFirstUpper(final String value) {
    return value == null || value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength
  // CHECKSTYLE:CONSTANTS-ON
}
