/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.format.jvmmodel;

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
import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
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
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.grammarAccess.GrammarAccess;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class FormatJvmModelInferrer extends AbstractModelInferrer {
  @Inject
  @Extension
  private Naming naming;
  
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  @Extension
  private TypeReferences _typeReferences;
  
  @Inject
  @Extension
  private GrammarAccess grammarAccess;
  
  @Inject
  private TypesFactory typesFactory;
  
  @Inject
  private XbaseCompiler xbaseCompiler;
  
  private final static String BASE_FORMATTER_CLASS_NAME = AbstractExtendedFormatter.class.getName();
  
  private final static String BASE_FORMAT_CONFIG = ExtendedFormattingConfig.class.getName();
  
  private final static String METHOD_ACTIVATE = "activate";
  
  private final static String METHOD_CALCULATE = "calculateParameter";
  
  private final static String PARAMETER_CONFIG = "config";
  
  private final static String PARAMETER_ELEMENTS = "elements";
  
  private final static String PARAMETER_RULE = "rule";
  
  private final static String PARAMETER_GRAMMAR_ACCESS = "grammarAccess";
  
  private final static String PARAMETER_CONTEXT = "context";
  
  private final static String PARAMETER_COLUMN = "currentColumn";
  
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
    String _formatterName = FormatGeneratorUtil.getFormatterName(format, "Abstract");
    String _simpleName = this.naming.toSimpleName(_formatterName);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(format, _simpleName);
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      this.inferClass(format, it);
      this.inferConstants(format, it);
      this.inferGetGrammarAccess(format, it);
      this.inferConfigureAcsFormatting(format, it);
      this.inferInit(format, it);
      this.inferRules(format, it);
      this.inferLocatorActivators(format, it);
    };
    acceptor.<JvmGenericType>accept(_class, _function);
  }
  
  public void inferClass(final FormatConfiguration format, final JvmGenericType it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("The abstract formatting configuration for ");
    Grammar _targetGrammar = format.getTargetGrammar();
    String _name = null;
    if (_targetGrammar!=null) {
      _name=_targetGrammar.getName();
    }
    String _packageName = null;
    if (_name!=null) {
      _packageName=this.naming.toPackageName(_name);
    }
    _builder.append(_packageName, "");
    _builder.append(".");
    Grammar _targetGrammar_1 = format.getTargetGrammar();
    String _name_1 = null;
    if (_targetGrammar_1!=null) {
      _name_1=_targetGrammar_1.getName();
    }
    String _simpleName = null;
    if (_name_1!=null) {
      _simpleName=this.naming.toSimpleName(_name_1);
    }
    _builder.append(_simpleName, "");
    _builder.append(" as declared in ");
    Grammar _targetGrammar_2 = format.getTargetGrammar();
    String _name_2 = null;
    if (_targetGrammar_2!=null) {
      _name_2=_targetGrammar_2.getName();
    }
    String _simpleName_1 = null;
    if (_name_2!=null) {
      _simpleName_1=this.naming.toSimpleName(_name_2);
    }
    _builder.append(_simpleName_1, "");
    _builder.append(".format.");
    this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
    JvmDeclaredType _formatterBaseClass = format.getFormatterBaseClass();
    boolean _notEquals = (!Objects.equal(_formatterBaseClass, null));
    if (_notEquals) {
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      JvmDeclaredType _formatterBaseClass_1 = format.getFormatterBaseClass();
      String _packageName_1 = _formatterBaseClass_1.getPackageName();
      String _plus = (_packageName_1 + ".");
      JvmDeclaredType _formatterBaseClass_2 = format.getFormatterBaseClass();
      String _simpleName_2 = _formatterBaseClass_2.getSimpleName();
      String _plus_1 = (_plus + _simpleName_2);
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_plus_1);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
    } else {
      EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(FormatJvmModelInferrer.BASE_FORMATTER_CLASS_NAME);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes_1, _typeRef_1);
    }
    String _formatterName = FormatGeneratorUtil.getFormatterName(format, "");
    String _packageName_2 = this.naming.toPackageName(_formatterName);
    it.setPackageName(_packageName_2);
    it.setAbstract(true);
  }
  
  public boolean inferConstants(final FormatConfiguration format, final JvmGenericType it) {
    boolean _xifexpression = false;
    List<Constant> _allConstants = FormatGeneratorUtil.getAllConstants(format);
    boolean _isEmpty = _allConstants.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      EList<JvmMember> _members = it.getMembers();
      List<Constant> _allConstants_1 = FormatGeneratorUtil.getAllConstants(format);
      final Function1<Constant, JvmMember> _function = (Constant c) -> {
        return this.createConstant(format, c);
      };
      List<JvmMember> _map = ListExtensions.<Constant, JvmMember>map(_allConstants_1, _function);
      _xifexpression = this._jvmTypesBuilder.<JvmMember>operator_add(_members, _map);
    }
    return _xifexpression;
  }
  
  public boolean inferGetGrammarAccess(final FormatConfiguration format, final JvmGenericType it) {
    EList<JvmMember> _members = it.getMembers();
    Grammar _targetGrammar = format.getTargetGrammar();
    String _gaFQName = this.grammarAccess.gaFQName(_targetGrammar);
    Grammar _targetGrammar_1 = format.getTargetGrammar();
    JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_gaFQName, _targetGrammar_1);
    final Procedure1<JvmOperation> _function = (JvmOperation it_1) -> {
      it_1.setVisibility(JvmVisibility.PROTECTED);
      final JvmAnnotationReference overrideAnnotation = this.createOverrideAnnotation(format);
      boolean _notEquals = (!Objects.equal(overrideAnnotation, null));
      if (_notEquals) {
        EList<JvmAnnotationReference> _annotations = it_1.getAnnotations();
        this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, overrideAnnotation);
      }
      final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_2) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("return (");
        Grammar _targetGrammar_2 = format.getTargetGrammar();
        String _gaSimpleName = this.grammarAccess.gaSimpleName(_targetGrammar_2);
        _builder.append(_gaSimpleName, "");
        _builder.append(") super.getGrammarAccess();");
        it_2.append(_builder);
      };
      this._jvmTypesBuilder.setBody(it_1, _function_1);
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(format, "getGrammarAccess", _typeForName, _function);
    return this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
  }
  
  public boolean inferConfigureAcsFormatting(final FormatConfiguration format, final JvmGenericType it) {
    EList<JvmMember> _members = it.getMembers();
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
    final Procedure1<JvmOperation> _function = (JvmOperation it_1) -> {
      it_1.setVisibility(JvmVisibility.PROTECTED);
      final JvmAnnotationReference overrideAnnotation = this.createOverrideAnnotation(format);
      boolean _notEquals = (!Objects.equal(overrideAnnotation, null));
      if (_notEquals) {
        EList<JvmAnnotationReference> _annotations = it_1.getAnnotations();
        this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, overrideAnnotation);
      }
      EList<JvmFormalParameter> _parameters = it_1.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(FormatJvmModelInferrer.BASE_FORMAT_CONFIG);
      JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONFIG, _typeRef_1);
      this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_2) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("init(config, getGrammarAccess());");
        it_2.append(_builder);
      };
      this._jvmTypesBuilder.setBody(it_1, _function_1);
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(format, "configureAcsFormatting", _typeRef, _function);
    return this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
  }
  
  public boolean inferInit(final FormatConfiguration format, final JvmGenericType it) {
    EList<JvmMember> _members = it.getMembers();
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
    final Procedure1<JvmOperation> _function = (JvmOperation it_1) -> {
      Pair<String, String> _mappedTo = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_CONFIG, "the format configuration");
      Pair<String, String> _mappedTo_1 = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_GRAMMAR_ACCESS, "the grammar access for the grammar");
      LinkedHashMap<String, String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_mappedTo, _mappedTo_1);
      String _generateJavaDoc = this.generateJavaDoc("Calls all configXyz methods declared in this class.", _newLinkedHashMap);
      this._jvmTypesBuilder.setDocumentation(it_1, _generateJavaDoc);
      it_1.setVisibility(JvmVisibility.PROTECTED);
      EList<JvmFormalParameter> _parameters = it_1.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(FormatJvmModelInferrer.BASE_FORMAT_CONFIG);
      JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONFIG, _typeRef_1);
      this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
      Grammar _targetGrammar = format.getTargetGrammar();
      String _gaFQName = this.grammarAccess.gaFQName(_targetGrammar);
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_gaFQName);
      JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_GRAMMAR_ACCESS, _typeRef_2);
      this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_2) -> {
        final ArrayList<String> rules = this.listConfigRules(format);
        int _length = ((Object[])Conversions.unwrapArray(rules, Object.class)).length;
        ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
        for (final Integer i : _doubleDotLessThan) {
          {
            if (((i).intValue() != 0)) {
              it_2.newLine();
            }
            String _get = rules.get((i).intValue());
            it_2.append(_get);
          }
        }
      };
      this._jvmTypesBuilder.setBody(it_1, _function_1);
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(format, "init", _typeRef, _function);
    return this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
  }
  
  public boolean inferRules(final FormatConfiguration format, final JvmGenericType it) {
    boolean _xblockexpression = false;
    {
      EList<JvmMember> _members = it.getMembers();
      List<GrammarRule> _parserRules = FormatGeneratorUtil.getParserRules(format);
      final Function1<GrammarRule, Iterable<JvmMember>> _function = (GrammarRule c) -> {
        return this.createRule(format, c);
      };
      List<Iterable<JvmMember>> _map = ListExtensions.<GrammarRule, Iterable<JvmMember>>map(_parserRules, _function);
      Iterable<JvmMember> _flatten = Iterables.<JvmMember>concat(_map);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members, _flatten);
      EList<JvmMember> _members_1 = it.getMembers();
      List<GrammarRule> _enumRules = FormatGeneratorUtil.getEnumRules(format);
      final Function1<GrammarRule, Iterable<JvmMember>> _function_1 = (GrammarRule c) -> {
        return this.createRule(format, c);
      };
      List<Iterable<JvmMember>> _map_1 = ListExtensions.<GrammarRule, Iterable<JvmMember>>map(_enumRules, _function_1);
      Iterable<JvmMember> _flatten_1 = Iterables.<JvmMember>concat(_map_1);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _flatten_1);
      EList<JvmMember> _members_2 = it.getMembers();
      List<GrammarRule> _terminalRules = FormatGeneratorUtil.getTerminalRules(format);
      final Function1<GrammarRule, Iterable<JvmMember>> _function_2 = (GrammarRule c) -> {
        return this.createRule(format, c);
      };
      List<Iterable<JvmMember>> _map_2 = ListExtensions.<GrammarRule, Iterable<JvmMember>>map(_terminalRules, _function_2);
      Iterable<JvmMember> _flatten_2 = Iterables.<JvmMember>concat(_map_2);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _flatten_2);
      boolean _xifexpression = false;
      WildcardRule _wildcardRule = FormatGeneratorUtil.getWildcardRule(format);
      boolean _notEquals = (!Objects.equal(_wildcardRule, null));
      if (_notEquals) {
        EList<JvmMember> _members_3 = it.getMembers();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
        final Procedure1<JvmOperation> _function_3 = (JvmOperation it_1) -> {
          Pair<String, String> _mappedTo = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_CONFIG, "the format configuration");
          Pair<String, String> _mappedTo_1 = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_ELEMENTS, "the grammar access for the grammar");
          LinkedHashMap<String, String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_mappedTo, _mappedTo_1);
          String _generateJavaDoc = this.generateJavaDoc("Configuration for IGrammarAccess.findXyz() methods.", _newLinkedHashMap);
          this._jvmTypesBuilder.setDocumentation(it_1, _generateJavaDoc);
          it_1.setVisibility(JvmVisibility.PROTECTED);
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(FormatJvmModelInferrer.BASE_FORMAT_CONFIG);
          JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONFIG, _typeRef_1);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          Grammar _targetGrammar = format.getTargetGrammar();
          Grammar _grammar = GrammarUtil.getGrammar(_targetGrammar);
          String _gaFQName = this.grammarAccess.gaFQName(_grammar);
          JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_gaFQName);
          JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_ELEMENTS, _typeRef_2);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          final Procedure1<ITreeAppendable> _function_4 = (ITreeAppendable it_2) -> {
            WildcardRule _wildcardRule_1 = FormatGeneratorUtil.getWildcardRule(format);
            EList<WildcardRuleDirective> _directives = _wildcardRule_1.getDirectives();
            final Function1<WildcardRuleDirective, String> _function_5 = (WildcardRuleDirective d) -> {
              WildcardRule _wildcardRule_2 = FormatGeneratorUtil.getWildcardRule(format);
              String _ruleName = this.getRuleName(_wildcardRule_2);
              CharSequence _directive = this.directive(d, _ruleName);
              return _directive.toString();
            };
            final List<String> directives = ListExtensions.<WildcardRuleDirective, String>map(_directives, _function_5);
            String _join = IterableExtensions.join(directives);
            String _fixLastLine = this.fixLastLine(_join);
            it_2.append(_fixLastLine);
          };
          this._jvmTypesBuilder.setBody(it_1, _function_4);
        };
        JvmOperation _method = this._jvmTypesBuilder.toMethod(format, "configFindElements", _typeRef, _function_3);
        _xifexpression = this._jvmTypesBuilder.<JvmOperation>operator_add(_members_3, _method);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public void inferLocatorActivators(final FormatConfiguration format, final JvmGenericType it) {
    List<Rule> rules = CollectionLiterals.<Rule>newLinkedList();
    List<GrammarRule> _parserRules = FormatGeneratorUtil.getParserRules(format);
    List<GrammarRule> _terminalRules = FormatGeneratorUtil.getTerminalRules(format);
    Iterable<GrammarRule> _plus = Iterables.<GrammarRule>concat(_parserRules, _terminalRules);
    List<GrammarRule> _enumRules = FormatGeneratorUtil.getEnumRules(format);
    Iterable<Rule> _plus_1 = Iterables.<Rule>concat(_plus, _enumRules);
    Iterables.<Rule>addAll(rules, _plus_1);
    WildcardRule _wildcardRule = FormatGeneratorUtil.getWildcardRule(format);
    rules.add(_wildcardRule);
    for (final Rule rule : rules) {
      {
        List<EObject> directives = CollectionLiterals.<EObject>newLinkedList();
        boolean _matched = false;
        if (rule instanceof GrammarRule) {
          _matched=true;
          EList<EObject> _directives = ((GrammarRule)rule).getDirectives();
          Iterables.<EObject>addAll(directives, _directives);
        }
        if (!_matched) {
          if (rule instanceof WildcardRule) {
            _matched=true;
            EList<WildcardRuleDirective> _directives = ((WildcardRule)rule).getDirectives();
            Iterables.<EObject>addAll(directives, _directives);
          }
        }
        Iterable<EObject> _filterNull = IterableExtensions.<EObject>filterNull(directives);
        for (final EObject directive : _filterNull) {
          List<Matcher> _collectMatchers = this.collectMatchers(directive);
          for (final Matcher matcher : _collectMatchers) {
            {
              if (((matcher.getLocator() instanceof ColumnLocator) && (!Objects.equal(((ColumnLocator) matcher.getLocator()).getParameter(), null)))) {
                EList<JvmMember> _members = it.getMembers();
                Locator _locator = matcher.getLocator();
                XExpression _parameter = ((ColumnLocator) _locator).getParameter();
                JvmGenericType _createParameterCalculatorInnerClass = this.createParameterCalculatorInnerClass(format, rule, directive, matcher, _parameter, it);
                this._jvmTypesBuilder.<JvmGenericType>operator_add(_members, _createParameterCalculatorInnerClass);
              }
              if (((matcher.getLocator() instanceof IndentLocator) && (!Objects.equal(((IndentLocator) matcher.getLocator()).getParameter(), null)))) {
                EList<JvmMember> _members_1 = it.getMembers();
                Locator _locator_1 = matcher.getLocator();
                XExpression _parameter_1 = ((IndentLocator) _locator_1).getParameter();
                JvmGenericType _createParameterCalculatorInnerClass_1 = this.createParameterCalculatorInnerClass(format, rule, directive, matcher, _parameter_1, it);
                this._jvmTypesBuilder.<JvmGenericType>operator_add(_members_1, _createParameterCalculatorInnerClass_1);
              }
              XExpression _condition = matcher.getCondition();
              boolean _notEquals = (!Objects.equal(_condition, null));
              if (_notEquals) {
                EList<JvmMember> _members_2 = it.getMembers();
                JvmGenericType _createLocatorActivatorInnerClass = this.createLocatorActivatorInnerClass(format, rule, directive, matcher, it);
                this._jvmTypesBuilder.<JvmGenericType>operator_add(_members_2, _createLocatorActivatorInnerClass);
              }
            }
          }
        }
      }
    }
  }
  
  public JvmGenericType createLocatorActivatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final JvmGenericType type) {
    String _locatorActivatorName = this.getLocatorActivatorName(rule, directive, matcher);
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      JvmTypeReference _locatorActivatorSuperType = this.getLocatorActivatorSuperType(format, rule);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _locatorActivatorSuperType);
      EList<JvmMember> _members = it.getMembers();
      JvmTypeReference _locatorActivatorReturnType = this.getLocatorActivatorReturnType(format);
      final Procedure1<JvmOperation> _function_1 = (JvmOperation it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
        JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, format);
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONTEXT, _typeForName);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
        JvmFormalParameter _createCurrenctColumnParameter = this.createCurrenctColumnParameter();
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _createCurrenctColumnParameter);
        Resource _eResource = format.eResource();
        Resource _eResource_1 = matcher.eResource();
        boolean _notEquals = (!Objects.equal(_eResource, _eResource_1));
        if (_notEquals) {
          final Procedure1<ITreeAppendable> _function_2 = (ITreeAppendable it_2) -> {
            XExpression _condition = matcher.getCondition();
            JvmTypeReference _locatorActivatorReturnType_1 = this.getLocatorActivatorReturnType(format);
            this.xbaseCompiler.compile(_condition, it_2, _locatorActivatorReturnType_1, null);
          };
          this._jvmTypesBuilder.setBody(it_1, _function_2);
        } else {
          XExpression _condition = matcher.getCondition();
          this._jvmTypesBuilder.setBody(it_1, _condition);
        }
      };
      JvmOperation _method = this._jvmTypesBuilder.toMethod(matcher, FormatJvmModelInferrer.METHOD_ACTIVATE, _locatorActivatorReturnType, _function_1);
      this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
    };
    return this._jvmTypesBuilder.toClass(format, _locatorActivatorName, _function);
  }
  
  public JvmFormalParameter createCurrenctColumnParameter() {
    JvmFormalParameter result = this.typesFactory.createJvmFormalParameter();
    result.setName(FormatJvmModelInferrer.PARAMETER_COLUMN);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(Integer.class);
    result.setParameterType(_typeRef);
    return result;
  }
  
  public JvmGenericType createParameterCalculatorInnerClass(final FormatConfiguration format, final Rule rule, final EObject directive, final Matcher matcher, final XExpression parameterCalculation, final JvmGenericType type) {
    String _parameterCalculatorName = this.getParameterCalculatorName(rule, directive, matcher);
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      it.setStatic(true);
      it.setFinal(true);
      it.setVisibility(JvmVisibility.PROTECTED);
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      JvmTypeReference _parameterCalculatorSuperType = this.getParameterCalculatorSuperType(format, rule);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _parameterCalculatorSuperType);
      EList<JvmMember> _members = it.getMembers();
      JvmTypeReference _parameterCalculatorReturnType = this.getParameterCalculatorReturnType(format);
      final Procedure1<JvmOperation> _function_1 = (JvmOperation it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
        JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, format);
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONTEXT, _typeForName);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
        JvmFormalParameter _createCurrenctColumnParameter = this.createCurrenctColumnParameter();
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _createCurrenctColumnParameter);
        Resource _eResource = format.eResource();
        Resource _eResource_1 = matcher.eResource();
        boolean _notEquals = (!Objects.equal(_eResource, _eResource_1));
        if (_notEquals) {
          final Procedure1<ITreeAppendable> _function_2 = (ITreeAppendable it_2) -> {
            JvmTypeReference _parameterCalculatorReturnType_1 = this.getParameterCalculatorReturnType(format);
            this.xbaseCompiler.compile(parameterCalculation, it_2, _parameterCalculatorReturnType_1, null);
          };
          this._jvmTypesBuilder.setBody(it_1, _function_2);
        } else {
          this._jvmTypesBuilder.setBody(it_1, parameterCalculation);
        }
      };
      JvmOperation _method = this._jvmTypesBuilder.toMethod(matcher, FormatJvmModelInferrer.METHOD_CALCULATE, _parameterCalculatorReturnType, _function_1);
      this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
    };
    return this._jvmTypesBuilder.toClass(format, _parameterCalculatorName, _function);
  }
  
  public ArrayList<String> listConfigRules(final FormatConfiguration format) {
    ArrayList<String> _xblockexpression = null;
    {
      final ArrayList<String> configRules = CollectionLiterals.<String>newArrayList();
      WildcardRule _wildcardRule = FormatGeneratorUtil.getWildcardRule(format);
      boolean _notEquals = (!Objects.equal(_wildcardRule, null));
      if (_notEquals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("configFindElements(config, grammarAccess);");
        configRules.add(_builder.toString());
      }
      List<GrammarRule> _parserRules = FormatGeneratorUtil.getParserRules(format);
      for (final GrammarRule rule : _parserRules) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("config");
        AbstractRule _targetRule = rule.getTargetRule();
        String _name = _targetRule.getName();
        _builder_1.append(_name, "");
        _builder_1.append("(config, grammarAccess.");
        AbstractRule _targetRule_1 = rule.getTargetRule();
        String _gaElementsAccessor = this.grammarAccess.gaElementsAccessor(_targetRule_1);
        _builder_1.append(_gaElementsAccessor, "");
        _builder_1.append(");");
        configRules.add(_builder_1.toString());
      }
      List<GrammarRule> _enumRules = FormatGeneratorUtil.getEnumRules(format);
      for (final GrammarRule rule_1 : _enumRules) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("config");
        AbstractRule _targetRule_2 = rule_1.getTargetRule();
        String _name_1 = _targetRule_2.getName();
        _builder_2.append(_name_1, "");
        _builder_2.append("(config, grammarAccess.");
        AbstractRule _targetRule_3 = rule_1.getTargetRule();
        String _gaRuleAccessor = this.grammarAccess.gaRuleAccessor(_targetRule_3);
        _builder_2.append(_gaRuleAccessor, "");
        _builder_2.append(");");
        configRules.add(_builder_2.toString());
      }
      List<GrammarRule> _terminalRules = FormatGeneratorUtil.getTerminalRules(format);
      for (final GrammarRule rule_2 : _terminalRules) {
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("config");
        AbstractRule _targetRule_4 = rule_2.getTargetRule();
        String _name_2 = _targetRule_4.getName();
        _builder_3.append(_name_2, "");
        _builder_3.append("(config, grammarAccess.");
        AbstractRule _targetRule_5 = rule_2.getTargetRule();
        String _gaRuleAccessor_1 = this.grammarAccess.gaRuleAccessor(_targetRule_5);
        _builder_3.append(_gaRuleAccessor_1, "");
        _builder_3.append(");");
        configRules.add(_builder_3.toString());
      }
      _xblockexpression = configRules;
    }
    return _xblockexpression;
  }
  
  public String generateJavaDoc(final String description, final Map<String, String> parameters) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(description, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      Set<Map.Entry<String, String>> _entrySet = parameters.entrySet();
      for(final Map.Entry<String, String> parameter : _entrySet) {
        _builder.append("@param ");
        String _key = parameter.getKey();
        _builder.append(_key, "");
        _builder.newLineIfNotEmpty();
        _builder.append("     ");
        _builder.append("- ");
        String _value = parameter.getValue();
        _builder.append(_value, "     ");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
  
  public JvmAnnotationReference createOverrideAnnotation(final FormatConfiguration format) {
    JvmAnnotationReference _xblockexpression = null;
    {
      final JvmTypeReference annotationTypeRef = this._typeReferenceBuilder.typeRef(Override.class);
      JvmAnnotationReference overrideAnnotation = null;
      boolean _notEquals = (!Objects.equal(annotationTypeRef, null));
      if (_notEquals) {
        final JvmType annotationType = annotationTypeRef.getType();
        JvmAnnotationReference _createJvmAnnotationReference = this.typesFactory.createJvmAnnotationReference();
        overrideAnnotation = _createJvmAnnotationReference;
        overrideAnnotation.setAnnotation(((JvmAnnotationType) annotationType));
      }
      _xblockexpression = overrideAnnotation;
    }
    return _xblockexpression;
  }
  
  public JvmMember createConstant(final FormatConfiguration configuration, final Constant constant) {
    JvmField _switchResult = null;
    boolean _matched = false;
    String _stringValue = constant.getStringValue();
    boolean _notEquals = (!Objects.equal(_stringValue, null));
    if (_notEquals) {
      _matched=true;
      String _name = constant.getName();
      JvmTypeReference _typeForName = this._typeReferences.getTypeForName("String", constant);
      final Procedure1<JvmField> _function = (JvmField it) -> {
        String _locatorString = this.locatorString(constant);
        this._jvmTypesBuilder.setDocumentation(it, _locatorString);
        it.setStatic(true);
        it.setFinal(true);
        it.setVisibility(JvmVisibility.PROTECTED);
        final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
          String _stringValue_1 = constant.getStringValue();
          String _plus = ("\"" + _stringValue_1);
          String _plus_1 = (_plus + "\"");
          it_1.append(_plus_1);
        };
        this._jvmTypesBuilder.setInitializer(it, _function_1);
      };
      _switchResult = this._jvmTypesBuilder.toField(constant, _name, _typeForName, _function);
    }
    if (!_matched) {
      Integer _intValue = constant.getIntValue();
      boolean _notEquals_1 = (!Objects.equal(_intValue, null));
      if (_notEquals_1) {
        _matched=true;
        String _name_1 = constant.getName();
        JvmTypeReference _typeForName_1 = this._typeReferences.getTypeForName("int", constant);
        final Procedure1<JvmField> _function_1 = (JvmField it) -> {
          String _locatorString = this.locatorString(constant);
          this._jvmTypesBuilder.setDocumentation(it, _locatorString);
          it.setStatic(true);
          it.setFinal(true);
          it.setVisibility(JvmVisibility.PROTECTED);
          final Procedure1<ITreeAppendable> _function_2 = (ITreeAppendable it_1) -> {
            Integer _intValue_1 = constant.getIntValue();
            String _string = _intValue_1.toString();
            it_1.append(_string);
          };
          this._jvmTypesBuilder.setInitializer(it, _function_2);
        };
        _switchResult = this._jvmTypesBuilder.toField(constant, _name_1, _typeForName_1, _function_1);
      }
    }
    return _switchResult;
  }
  
  public List<Matcher> collectMatchers(final EObject directive) {
    List<Matcher> _xblockexpression = null;
    {
      List<Matcher> matchers = CollectionLiterals.<Matcher>newLinkedList();
      boolean _matched = false;
      if (directive instanceof GroupBlock) {
        _matched=true;
        MatcherList _matcherList = ((GroupBlock)directive).getMatcherList();
        boolean _notEquals = (!Objects.equal(_matcherList, null));
        if (_notEquals) {
          MatcherList _matcherList_1 = ((GroupBlock)directive).getMatcherList();
          EList<Matcher> _matchers = _matcherList_1.getMatchers();
          Iterables.<Matcher>addAll(matchers, _matchers);
        }
      }
      if (!_matched) {
        if (directive instanceof SpecificDirective) {
          _matched=true;
          MatcherList _matcherList = ((SpecificDirective)directive).getMatcherList();
          boolean _notEquals = (!Objects.equal(_matcherList, null));
          if (_notEquals) {
            MatcherList _matcherList_1 = ((SpecificDirective)directive).getMatcherList();
            EList<Matcher> _matchers = _matcherList_1.getMatchers();
            Iterables.<Matcher>addAll(matchers, _matchers);
          }
        }
      }
      if (!_matched) {
        if (directive instanceof ContextFreeDirective) {
          _matched=true;
          MatcherList _matcherList = ((ContextFreeDirective)directive).getMatcherList();
          boolean _notEquals = (!Objects.equal(_matcherList, null));
          if (_notEquals) {
            MatcherList _matcherList_1 = ((ContextFreeDirective)directive).getMatcherList();
            EList<Matcher> _matchers = _matcherList_1.getMatchers();
            Iterables.<Matcher>addAll(matchers, _matchers);
          }
        }
      }
      if (!_matched) {
        if (directive instanceof KeywordPair) {
          _matched=true;
          EList<Matcher> _leftMatchers = ((KeywordPair)directive).getLeftMatchers();
          boolean _notEquals = (!Objects.equal(_leftMatchers, null));
          if (_notEquals) {
            EList<Matcher> _leftMatchers_1 = ((KeywordPair)directive).getLeftMatchers();
            Iterables.<Matcher>addAll(matchers, _leftMatchers_1);
          }
          EList<Matcher> _rightMatchers = ((KeywordPair)directive).getRightMatchers();
          boolean _notEquals_1 = (!Objects.equal(_rightMatchers, null));
          if (_notEquals_1) {
            EList<Matcher> _rightMatchers_1 = ((KeywordPair)directive).getRightMatchers();
            Iterables.<Matcher>addAll(matchers, _rightMatchers_1);
          }
        }
      }
      _xblockexpression = matchers;
    }
    return _xblockexpression;
  }
  
  public JvmTypeReference getLocatorActivatorReturnType(final FormatConfiguration formatConfiguration) {
    return this._typeReferenceBuilder.typeRef(boolean.class);
  }
  
  public JvmTypeReference getParameterCalculatorReturnType(final FormatConfiguration formatConfiguration) {
    return this._typeReferenceBuilder.typeRef(int.class);
  }
  
  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
    JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, formatConfiguration);
    return this._typeReferenceBuilder.typeRef(LocatorActivator.class, _typeForName);
  }
  
  protected JvmTypeReference _getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
    JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, formatConfiguration);
    return this._typeReferenceBuilder.typeRef(LocatorActivator.class, _typeForName);
  }
  
  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final GrammarRule rule) {
    String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
    JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, formatConfiguration);
    return this._typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, _typeForName);
  }
  
  protected JvmTypeReference _getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final WildcardRule rule) {
    String _grammarElementNameFromSelf = this.getGrammarElementNameFromSelf(rule);
    JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_grammarElementNameFromSelf, formatConfiguration);
    return this._typeReferenceBuilder.typeRef(LocatorParameterCalculator.class, _typeForName);
  }
  
  protected String _getGrammarElementNameFromSelf(final GrammarRule rule) {
    final String originalRuleName = this.getRuleName(rule);
    String actualRuleName = originalRuleName;
    if ((((rule.getTargetRule() == null) || (rule.getTargetRule().getType() == null)) || (rule.getTargetRule().getType().getClassifier() == null))) {
      return actualRuleName;
    } else {
      AbstractRule _targetRule = rule.getTargetRule();
      TypeRef _type = null;
      if (_targetRule!=null) {
        _type=_targetRule.getType();
      }
      EClassifier _classifier = null;
      if (_type!=null) {
        _classifier=_type.getClassifier();
      }
      String _name = null;
      if (_classifier!=null) {
        _name=_classifier.getName();
      }
      boolean _notEquals = (!Objects.equal(actualRuleName, _name));
      if (_notEquals) {
        AbstractRule _targetRule_1 = rule.getTargetRule();
        TypeRef _type_1 = _targetRule_1.getType();
        EClassifier _classifier_1 = _type_1.getClassifier();
        String _name_1 = _classifier_1.getName();
        actualRuleName = _name_1;
      }
    }
    AbstractRule _targetRule_2 = rule.getTargetRule();
    TypeRef _type_2 = null;
    if (_targetRule_2!=null) {
      _type_2=_targetRule_2.getType();
    }
    AbstractMetamodelDeclaration _metamodel = null;
    if (_type_2!=null) {
      _metamodel=_type_2.getMetamodel();
    }
    AbstractMetamodelDeclaration metamodel = _metamodel;
    boolean _equals = Objects.equal(metamodel, null);
    if (_equals) {
      return actualRuleName;
    } else {
      boolean _notEquals_1 = (!Objects.equal(actualRuleName, originalRuleName));
      if (_notEquals_1) {
        EPackage _ePackage = metamodel.getEPackage();
        EList<EClassifier> _eClassifiers = _ePackage.getEClassifiers();
        Stream<EClassifier> _stream = _eClassifiers.stream();
        final Predicate<EClassifier> _function = (EClassifier it) -> {
          return ((it instanceof EClass) && it.getName().equalsIgnoreCase(originalRuleName));
        };
        boolean _anyMatch = _stream.anyMatch(_function);
        if (_anyMatch) {
          actualRuleName = originalRuleName;
        }
      }
      String _alias = metamodel.getAlias();
      boolean _equals_1 = Objects.equal(_alias, null);
      if (_equals_1) {
        Grammar _containerOfType = EcoreUtil2.<Grammar>getContainerOfType(metamodel, Grammar.class);
        String _name_2 = null;
        if (_containerOfType!=null) {
          _name_2=_containerOfType.getName();
        }
        String _lowerCase = null;
        if (_name_2!=null) {
          _lowerCase=_name_2.toLowerCase();
        }
        String _plus = (_lowerCase + ".");
        return (_plus + actualRuleName);
      } else {
        String _alias_1 = metamodel.getAlias();
        String _plus_1 = ("com.avaloq.tools.dsl." + _alias_1);
        String _plus_2 = (_plus_1 + ".");
        String _alias_2 = metamodel.getAlias();
        String _plus_3 = (_plus_2 + _alias_2);
        String _plus_4 = (_plus_3 + ".");
        return (_plus_4 + actualRuleName);
      }
    }
  }
  
  protected String _getGrammarElementNameFromSelf(final WildcardRule rule) {
    return EObject.class.getName();
  }
  
  public int getMatcherIndex(final Matcher matcher) {
    final MatcherList matcherList = EcoreUtil2.<MatcherList>getContainerOfType(matcher, MatcherList.class);
    EList<Matcher> _matchers = matcherList.getMatchers();
    return _matchers.indexOf(matcher);
  }
  
  public String getLocatorActivatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    String _ruleName = this.getRuleName(rule);
    String _plus = ("ActivatorFor" + _ruleName);
    String _matcherName = this.getMatcherName(matcher, directive);
    String _plus_1 = (_plus + _matcherName);
    return _plus_1.replace("Impl", "");
  }
  
  public String getLocatorActivatorName(final String partialName, final Matcher matcher) {
    int _matcherIndex = this.getMatcherIndex(matcher);
    String _plus = (("ActivatorFor" + partialName) + Integer.valueOf(_matcherIndex));
    Locator _locator = matcher.getLocator();
    String _locatorName = this.getLocatorName(_locator);
    String _plus_1 = (_plus + _locatorName);
    MatcherType _type = matcher.getType();
    String _name = _type.name();
    String _lowerCase = _name.toLowerCase();
    String _firstUpper = StringExtensions.toFirstUpper(_lowerCase);
    String _plus_2 = (_plus_1 + _firstUpper);
    return _plus_2.replace("Impl", "");
  }
  
  public String getParameterCalculatorName(final EObject rule, final EObject directive, final Matcher matcher) {
    String _ruleName = this.getRuleName(rule);
    String _plus = ("ParameterCalculatorFor" + _ruleName);
    String _matcherName = this.getMatcherName(matcher, directive);
    String _plus_1 = (_plus + _matcherName);
    return _plus_1.replace("Impl", "");
  }
  
  public String getParameterCalculatorName(final String partialName, final Matcher matcher) {
    int _matcherIndex = this.getMatcherIndex(matcher);
    String _plus = (("ParameterCalculatorFor" + partialName) + Integer.valueOf(_matcherIndex));
    Locator _locator = matcher.getLocator();
    String _locatorName = this.getLocatorName(_locator);
    String _plus_1 = (_plus + _locatorName);
    MatcherType _type = matcher.getType();
    String _name = _type.name();
    String _lowerCase = _name.toLowerCase();
    String _firstUpper = StringExtensions.toFirstUpper(_lowerCase);
    String _plus_2 = (_plus_1 + _firstUpper);
    return _plus_2.replace("Impl", "");
  }
  
  protected String _getRuleName(final GrammarRule rule) {
    AbstractRule _targetRule = rule.getTargetRule();
    String _name = null;
    if (_targetRule!=null) {
      _name=_targetRule.getName();
    }
    return _name;
  }
  
  protected String _getRuleName(final WildcardRule rule) {
    return "Wildcard";
  }
  
  protected String _getRuleName(final EObject rule) {
    return EObject.class.getSimpleName();
  }
  
  public String getMatcherName(final Matcher matcher, final EObject directive) {
    String _directiveName = this.getDirectiveName(directive);
    int _matcherIndex = this.getMatcherIndex(matcher);
    String _plus = (_directiveName + Integer.valueOf(_matcherIndex));
    Locator _locator = matcher.getLocator();
    String _locatorName = this.getLocatorName(_locator);
    String _plus_1 = (_plus + _locatorName);
    MatcherType _type = matcher.getType();
    String _name = _type.name();
    String _lowerCase = _name.toLowerCase();
    String _firstUpper = StringExtensions.toFirstUpper(_lowerCase);
    return (_plus_1 + _firstUpper);
  }
  
  public String getLocatorName(final EObject locator) {
    String _elvis = null;
    Class<? extends EObject> _class = null;
    if (locator!=null) {
      _class=locator.getClass();
    }
    String _simpleName = _class.getSimpleName();
    if (_simpleName != null) {
      _elvis = _simpleName;
    } else {
      _elvis = "";
    }
    return _elvis;
  }
  
  public String convertNonAlphaNumeric(final String str) {
    String _xblockexpression = null;
    {
      final Pattern pattern = Pattern.compile("[\\W]");
      final java.util.regex.Matcher matcher = pattern.matcher(str);
      final StringBuffer sb = new StringBuffer();
      while (matcher.find()) {
        String _group = matcher.group();
        int _hashCode = _group.hashCode();
        String _hexString = Integer.toHexString(_hashCode);
        String _valueOf = String.valueOf(_hexString);
        matcher.appendReplacement(sb, _valueOf);
      }
      matcher.appendTail(sb);
      _xblockexpression = sb.toString();
    }
    return _xblockexpression;
  }
  
  protected String _getDirectiveName(final GroupBlock directive) {
    String _xblockexpression = null;
    {
      final GrammarRule grammarRule = EcoreUtil2.<GrammarRule>getContainerOfType(directive, GrammarRule.class);
      EList<EObject> _directives = grammarRule.getDirectives();
      Iterable<GroupBlock> _filter = Iterables.<GroupBlock>filter(_directives, GroupBlock.class);
      final ArrayList<Iterable<GroupBlock>> directives = CollectionLiterals.<Iterable<GroupBlock>>newArrayList(_filter);
      int _indexOf = directives.indexOf(directive);
      int _plus = (_indexOf + 1);
      String _valueOf = String.valueOf(_plus);
      _xblockexpression = ("Group" + _valueOf);
    }
    return _xblockexpression;
  }
  
  protected String _getDirectiveName(final SpecificDirective directive) {
    String _xblockexpression = null;
    {
      String directiveName = "";
      EList<GrammarElementReference> _grammarElements = directive.getGrammarElements();
      for (final GrammarElementReference grammarElementReference : _grammarElements) {
        {
          Assignment _assignment = grammarElementReference.getAssignment();
          boolean _notEquals = (!Objects.equal(_assignment, null));
          if (_notEquals) {
            Assignment _assignment_1 = grammarElementReference.getAssignment();
            String _feature = _assignment_1.getFeature();
            String _firstUpper = StringExtensions.toFirstUpper(_feature);
            String _plus = (directiveName + _firstUpper);
            directiveName = _plus;
          }
          RuleCall _ruleCall = grammarElementReference.getRuleCall();
          boolean _notEquals_1 = (!Objects.equal(_ruleCall, null));
          if (_notEquals_1) {
            RuleCall _ruleCall_1 = grammarElementReference.getRuleCall();
            AbstractRule _rule = _ruleCall_1.getRule();
            String _name = _rule.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name);
            String _plus_1 = (directiveName + _firstUpper_1);
            directiveName = _plus_1;
          }
          AbstractRule _rule_1 = grammarElementReference.getRule();
          boolean _notEquals_2 = (!Objects.equal(_rule_1, null));
          if (_notEquals_2) {
            AbstractRule _rule_2 = grammarElementReference.getRule();
            String _name_1 = _rule_2.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_1);
            String _plus_2 = (directiveName + _firstUpper_2);
            directiveName = _plus_2;
          }
          Keyword _keyword = grammarElementReference.getKeyword();
          boolean _notEquals_3 = (!Objects.equal(_keyword, null));
          if (_notEquals_3) {
            Keyword _keyword_1 = grammarElementReference.getKeyword();
            String _value = _keyword_1.getValue();
            String _convertNonAlphaNumeric = this.convertNonAlphaNumeric(_value);
            String _firstUpper_3 = StringExtensions.toFirstUpper(_convertNonAlphaNumeric);
            String _plus_3 = (directiveName + _firstUpper_3);
            directiveName = _plus_3;
          }
          AbstractRule _self = grammarElementReference.getSelf();
          boolean _notEquals_4 = (!Objects.equal(_self, null));
          if (_notEquals_4) {
            directiveName = (directiveName + "Self");
          }
        }
      }
      _xblockexpression = directiveName;
    }
    return _xblockexpression;
  }
  
  protected String _getDirectiveName(final ContextFreeDirective directive) {
    String _xblockexpression = null;
    {
      String directiveName = "";
      EList<GrammarElementLookup> _grammarElements = directive.getGrammarElements();
      for (final GrammarElementLookup grammarElementLookup : _grammarElements) {
        {
          AbstractRule _rule = grammarElementLookup.getRule();
          boolean _notEquals = (!Objects.equal(_rule, null));
          if (_notEquals) {
            AbstractRule _rule_1 = grammarElementLookup.getRule();
            String _name = _rule_1.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            String _plus = (directiveName + _firstUpper);
            directiveName = _plus;
          }
          String _keyword = grammarElementLookup.getKeyword();
          boolean _notEquals_1 = (!Objects.equal(_keyword, null));
          if (_notEquals_1) {
            String _keyword_1 = grammarElementLookup.getKeyword();
            String _convertNonAlphaNumeric = this.convertNonAlphaNumeric(_keyword_1);
            String _firstUpper_1 = StringExtensions.toFirstUpper(_convertNonAlphaNumeric);
            String _plus_1 = (directiveName + _firstUpper_1);
            directiveName = _plus_1;
          }
        }
      }
      _xblockexpression = directiveName;
    }
    return _xblockexpression;
  }
  
  protected String _getDirectiveName(final KeywordPair directive) {
    String _left = directive.getLeft();
    String _convertNonAlphaNumeric = this.convertNonAlphaNumeric(_left);
    String _right = directive.getRight();
    String _convertNonAlphaNumeric_1 = this.convertNonAlphaNumeric(_right);
    return (_convertNonAlphaNumeric + _convertNonAlphaNumeric_1);
  }
  
  protected String _getDirectiveName(final EObject directive) {
    int _hashCode = directive.hashCode();
    return String.valueOf(_hashCode);
  }
  
  public Iterable<JvmMember> createRule(final FormatConfiguration format, final GrammarRule rule) {
    final List<JvmMember> members = CollectionLiterals.<JvmMember>newArrayList();
    AbstractRule _targetRule = rule.getTargetRule();
    String _name = _targetRule.getName();
    String _plus = ("config" + _name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      it.setFinal(false);
      it.setVisibility(JvmVisibility.PROTECTED);
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(FormatJvmModelInferrer.BASE_FORMAT_CONFIG);
      JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_CONFIG, _typeRef_1);
      this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      AbstractRule _targetRule_1 = rule.getTargetRule();
      boolean _matched = false;
      if (_targetRule_1 instanceof ParserRule) {
        _matched=true;
        AbstractRule _targetRule_2 = rule.getTargetRule();
        Grammar _grammar = GrammarUtil.getGrammar(_targetRule_2);
        String _gaFQName = this.grammarAccess.gaFQName(_grammar);
        String _plus_1 = (_gaFQName + "$");
        AbstractRule _targetRule_3 = rule.getTargetRule();
        String _gaRuleAccesorClassName = this.grammarAccess.gaRuleAccesorClassName(_targetRule_3);
        final String ruleName = (_plus_1 + _gaRuleAccesorClassName);
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        AbstractRule _targetRule_4 = rule.getTargetRule();
        JvmTypeReference _typeForName = this._typeReferences.getTypeForName(ruleName, _targetRule_4);
        JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_ELEMENTS, _typeForName);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Configuration for ");
        AbstractRule _targetRule_5 = rule.getTargetRule();
        String _name_1 = _targetRule_5.getName();
        _builder.append(_name_1, "");
        _builder.append(".");
        Pair<String, String> _mappedTo = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_CONFIG, "the format configuration");
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("the grammar access for ");
        AbstractRule _targetRule_6 = rule.getTargetRule();
        String _name_2 = _targetRule_6.getName();
        _builder_1.append(_name_2, "");
        _builder_1.append(" elements");
        Pair<String, String> _mappedTo_1 = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_ELEMENTS, _builder_1.toString());
        LinkedHashMap<String, String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_mappedTo, _mappedTo_1);
        String _generateJavaDoc = this.generateJavaDoc(_builder.toString(), _newLinkedHashMap);
        this._jvmTypesBuilder.setDocumentation(it, _generateJavaDoc);
      }
      if (!_matched) {
        if (_targetRule_1 instanceof EnumRule) {
          _matched=true;
          EList<JvmFormalParameter> _parameters_1 = it.getParameters();
          String _name_1 = EnumRule.class.getName();
          AbstractRule _targetRule_2 = rule.getTargetRule();
          JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_name_1, _targetRule_2);
          JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_RULE, _typeForName);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Configuration for ");
          AbstractRule _targetRule_3 = rule.getTargetRule();
          String _name_2 = _targetRule_3.getName();
          _builder.append(_name_2, "");
          _builder.append(".");
          Pair<String, String> _mappedTo = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_CONFIG, "the format configuration");
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("the enum rule for ");
          AbstractRule _targetRule_4 = rule.getTargetRule();
          String _name_3 = _targetRule_4.getName();
          _builder_1.append(_name_3, "");
          Pair<String, String> _mappedTo_1 = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_RULE, _builder_1.toString());
          LinkedHashMap<String, String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_mappedTo, _mappedTo_1);
          String _generateJavaDoc = this.generateJavaDoc(_builder.toString(), _newLinkedHashMap);
          this._jvmTypesBuilder.setDocumentation(it, _generateJavaDoc);
        }
      }
      if (!_matched) {
        if (_targetRule_1 instanceof TerminalRule) {
          _matched=true;
          EList<JvmFormalParameter> _parameters_1 = it.getParameters();
          String _name_1 = TerminalRule.class.getName();
          AbstractRule _targetRule_2 = rule.getTargetRule();
          JvmTypeReference _typeForName = this._typeReferences.getTypeForName(_name_1, _targetRule_2);
          JvmFormalParameter _parameter_1 = this._jvmTypesBuilder.toParameter(format, FormatJvmModelInferrer.PARAMETER_RULE, _typeForName);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Configuration for ");
          AbstractRule _targetRule_3 = rule.getTargetRule();
          String _name_2 = _targetRule_3.getName();
          _builder.append(_name_2, "");
          _builder.append(".");
          Pair<String, String> _mappedTo = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_CONFIG, "the format configuration");
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("the terminal rule for ");
          AbstractRule _targetRule_4 = rule.getTargetRule();
          String _name_3 = _targetRule_4.getName();
          _builder_1.append(_name_3, "");
          Pair<String, String> _mappedTo_1 = Pair.<String, String>of(FormatJvmModelInferrer.PARAMETER_RULE, _builder_1.toString());
          LinkedHashMap<String, String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_mappedTo, _mappedTo_1);
          String _generateJavaDoc = this.generateJavaDoc(_builder.toString(), _newLinkedHashMap);
          this._jvmTypesBuilder.setDocumentation(it, _generateJavaDoc);
        }
      }
      final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
        EList<EObject> _directives = rule.getDirectives();
        final Function1<EObject, String> _function_2 = (EObject d) -> {
          String _ruleName = this.getRuleName(rule);
          CharSequence _directive = this.directive(d, _ruleName);
          return _directive.toString();
        };
        final List<String> directives = ListExtensions.<EObject, String>map(_directives, _function_2);
        String _join = IterableExtensions.join(directives);
        String _fixLastLine = this.fixLastLine(_join);
        it_1.append(_fixLastLine);
      };
      this._jvmTypesBuilder.setBody(it, _function_1);
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(format, _plus, _typeRef, _function);
    members.add(_method);
    return members;
  }
  
  public String fixLastLine(final String content) {
    boolean _endsWith = content.endsWith("\r\n");
    if (_endsWith) {
      int _length = content.length();
      int _minus = (_length - 2);
      return content.substring(0, _minus);
    } else {
      return content;
    }
  }
  
  protected CharSequence _directive(final SpecificDirective dir, final String partialName) {
    MatcherList _matcherList = dir.getMatcherList();
    EList<GrammarElementReference> _grammarElements = dir.getGrammarElements();
    String _directiveName = this.getDirectiveName(dir);
    String _plus = (partialName + _directiveName);
    return this.matchReference(_matcherList, _grammarElements, _plus);
  }
  
  protected CharSequence _directive(final ContextFreeDirective dir, final String partialName) {
    MatcherList _matcherList = dir.getMatcherList();
    EList<GrammarElementLookup> _grammarElements = dir.getGrammarElements();
    String _directiveName = this.getDirectiveName(dir);
    String _plus = (partialName + _directiveName);
    return this.matchLookup(_matcherList, _grammarElements, _plus);
  }
  
  protected CharSequence _directive(final GroupBlock dir, final String partialName) {
    CharSequence _xifexpression = null;
    MatcherList _matcherList = dir.getMatcherList();
    boolean _notEquals = (!Objects.equal(_matcherList, null));
    if (_notEquals) {
      MatcherList _matcherList_1 = dir.getMatcherList();
      CompoundElement _grammarElement = dir.getGrammarElement();
      BasicEList<CompoundElement> _basicEList = new BasicEList<CompoundElement>(Collections.<CompoundElement>unmodifiableList(CollectionLiterals.<CompoundElement>newArrayList(_grammarElement)));
      String _directiveName = this.getDirectiveName(dir);
      String _plus = (partialName + _directiveName);
      _xifexpression = this.matchReference(_matcherList_1, _basicEList, _plus);
    } else {
      CharSequence _xifexpression_1 = null;
      GroupBlock _subGroup = dir.getSubGroup();
      boolean _notEquals_1 = (!Objects.equal(_subGroup, null));
      if (_notEquals_1) {
        GroupBlock _subGroup_1 = dir.getSubGroup();
        String _directiveName_1 = this.getDirectiveName(dir);
        String _plus_1 = (partialName + _directiveName_1);
        _xifexpression_1 = this.directive(_subGroup_1, _plus_1);
      } else {
        StringConcatenation _builder = new StringConcatenation();
        {
          EList<GrammarRuleDirective> _directives = dir.getDirectives();
          for(final GrammarRuleDirective d : _directives) {
            String _directiveName_2 = this.getDirectiveName(dir);
            String _plus_2 = (partialName + _directiveName_2);
            CharSequence _directive = this.directive(d, _plus_2);
            _builder.append(_directive, "");
          }
        }
        _xifexpression_1 = _builder;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected CharSequence _directive(final KeywordPair dir, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// ");
    String _locatorString = this.locatorString(dir);
    _builder.append(_locatorString, "");
    _builder.newLineIfNotEmpty();
    _builder.append("for (final org.eclipse.xtext.util.Pair<org.eclipse.xtext.Keyword, org.eclipse.xtext.Keyword> pair : elements.findKeywordPairs(\"");
    String _left = dir.getLeft();
    _builder.append(_left, "");
    _builder.append("\", \"");
    String _right = dir.getRight();
    _builder.append(_right, "");
    _builder.append("\")) {");
    _builder.newLineIfNotEmpty();
    {
      EList<Matcher> _leftMatchers = dir.getLeftMatchers();
      for(final Matcher matcher : _leftMatchers) {
        Locator _locator = matcher.getLocator();
        String _directiveName = this.getDirectiveName(dir);
        String _plus = (partialName + _directiveName);
        CharSequence _matchLookupPartial = this.matchLookupPartial(_locator, matcher, "pair.getFirst()", _plus);
        _builder.append(_matchLookupPartial, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Matcher> _rightMatchers = dir.getRightMatchers();
      for(final Matcher matcher_1 : _rightMatchers) {
        Locator _locator_1 = matcher_1.getLocator();
        String _directiveName_1 = this.getDirectiveName(dir);
        String _plus_1 = (partialName + _directiveName_1);
        CharSequence _matchLookupPartial_1 = this.matchLookupPartial(_locator_1, matcher_1, "pair.getSecond()", _plus_1);
        _builder.append(_matchLookupPartial_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _directive(final Object dir, final String partialName) {
    Class<?> _class = dir.getClass();
    String _name = _class.getName();
    String _plus = ("Unknown directive " + _name);
    throw new UnsupportedOperationException(_plus);
  }
  
  public CharSequence matchLookup(final MatcherList matcherList, final EList<GrammarElementLookup> elements, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = elements.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          final Function1<GrammarElementLookup, Boolean> _function = (GrammarElementLookup e) -> {
            AbstractRule _rule = e.getRule();
            return Boolean.valueOf((!Objects.equal(_rule, null)));
          };
          Iterable<GrammarElementLookup> _filter = IterableExtensions.<GrammarElementLookup>filter(elements, _function);
          boolean _isEmpty_1 = IterableExtensions.isEmpty(_filter);
          boolean _not_1 = (!_isEmpty_1);
          if (_not_1) {
            _builder.append("// ");
            String _locatorString = this.locatorString(matcherList);
            _builder.append(_locatorString, "");
            _builder.newLineIfNotEmpty();
            _builder.append("for (org.eclipse.xtext.RuleCall ruleCall : elements.findRuleCalls(");
            {
              final Function1<GrammarElementLookup, Boolean> _function_1 = (GrammarElementLookup e) -> {
                AbstractRule _rule = e.getRule();
                return Boolean.valueOf((!Objects.equal(_rule, null)));
              };
              Iterable<GrammarElementLookup> _filter_1 = IterableExtensions.<GrammarElementLookup>filter(elements, _function_1);
              boolean _hasElements = false;
              for(final GrammarElementLookup element : _filter_1) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                _builder.append("elements.");
                AbstractRule _rule = element.getRule();
                String _gaRuleAccessor = this.grammarAccess.gaRuleAccessor(_rule);
                _builder.append(_gaRuleAccessor, "");
              }
            }
            _builder.append(")) {");
            _builder.newLineIfNotEmpty();
            {
              EList<Matcher> _matchers = matcherList.getMatchers();
              for(final Matcher matcher : _matchers) {
                _builder.append("  ");
                Locator _locator = matcher.getLocator();
                CharSequence _matchLookupPartial = this.matchLookupPartial(_locator, matcher, "ruleCall", partialName);
                _builder.append(_matchLookupPartial, "  ");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("}");
            _builder.newLine();
          }
        }
        {
          final Function1<GrammarElementLookup, Boolean> _function_2 = (GrammarElementLookup e) -> {
            String _keyword = e.getKeyword();
            return Boolean.valueOf((!Objects.equal(_keyword, null)));
          };
          Iterable<GrammarElementLookup> _filter_2 = IterableExtensions.<GrammarElementLookup>filter(elements, _function_2);
          boolean _isEmpty_2 = IterableExtensions.isEmpty(_filter_2);
          boolean _not_2 = (!_isEmpty_2);
          if (_not_2) {
            _builder.append("// ");
            String _locatorString_1 = this.locatorString(matcherList);
            _builder.append(_locatorString_1, "");
            _builder.newLineIfNotEmpty();
            _builder.append("for (org.eclipse.xtext.Keyword keyword : elements.findKeywords(");
            {
              final Function1<GrammarElementLookup, Boolean> _function_3 = (GrammarElementLookup e) -> {
                String _keyword = e.getKeyword();
                return Boolean.valueOf((!Objects.equal(_keyword, null)));
              };
              Iterable<GrammarElementLookup> _filter_3 = IterableExtensions.<GrammarElementLookup>filter(elements, _function_3);
              boolean _hasElements_1 = false;
              for(final GrammarElementLookup element_1 : _filter_3) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                _builder.append("\"");
                String _keyword = element_1.getKeyword();
                _builder.append(_keyword, "");
                _builder.append("\"");
              }
            }
            _builder.append(")) {");
            _builder.newLineIfNotEmpty();
            {
              EList<Matcher> _matchers_1 = matcherList.getMatchers();
              for(final Matcher matcher_1 : _matchers_1) {
                _builder.append("  ");
                Locator _locator_1 = matcher_1.getLocator();
                CharSequence _matchLookupPartial_1 = this.matchLookupPartial(_locator_1, matcher_1, "keyword", partialName);
                _builder.append(_matchLookupPartial_1, "  ");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  protected CharSequence _matchLookupPartial(final ColumnLocator columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      MatcherType _type = matcher.getType();
      String _literal = _type.getLiteral();
      int _compareTo = _literal.compareTo("before");
      boolean _equals = (_compareTo == 0);
      if (_equals) {
        _builder.append("config.setColumn(");
        IntValue _value = columnLocator.getValue();
        String _valueOrConstant = this.getValueOrConstant(_value);
        _builder.append(_valueOrConstant, "");
        _builder.append(", ");
        boolean _isFixed = columnLocator.isFixed();
        _builder.append(_isFixed, "");
        _builder.append(", ");
        boolean _isRelative = columnLocator.isRelative();
        _builder.append(_isRelative, "");
        _builder.append(", ");
        boolean _isNobreak = columnLocator.isNobreak();
        _builder.append(_isNobreak, "");
        {
          XExpression _condition = matcher.getCondition();
          boolean _notEquals = (!Objects.equal(_condition, null));
          if (_notEquals) {
            _builder.append(", new ");
            String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName, "");
            _builder.append("()");
          }
        }
        _builder.append(").before(");
        _builder.append(eobjectTypeName, "");
        _builder.append(");  // ");
        String _locatorString = this.locatorString(columnLocator);
        _builder.append(_locatorString, "");
        _builder.newLineIfNotEmpty();
        _builder.append("config.setColumn(");
        IntValue _value_1 = columnLocator.getValue();
        String _valueOrConstant_1 = this.getValueOrConstant(_value_1);
        _builder.append(_valueOrConstant_1, "");
        _builder.append(", ");
        boolean _isFixed_1 = columnLocator.isFixed();
        _builder.append(_isFixed_1, "");
        _builder.append(", ");
        boolean _isRelative_1 = columnLocator.isRelative();
        _builder.append(_isRelative_1, "");
        _builder.append(", ");
        boolean _isNobreak_1 = columnLocator.isNobreak();
        _builder.append(_isNobreak_1, "");
        {
          XExpression _condition_1 = matcher.getCondition();
          boolean _notEquals_1 = (!Objects.equal(_condition_1, null));
          if (_notEquals_1) {
            _builder.append(", new ");
            String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_1, "");
            _builder.append("()");
          }
        }
        _builder.append(").after(");
        _builder.append(eobjectTypeName, "");
        _builder.append(");  // ");
        String _locatorString_1 = this.locatorString(columnLocator);
        _builder.append(_locatorString_1, "");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("config.setColumn(");
        IntValue _value_2 = columnLocator.getValue();
        String _valueOrConstant_2 = this.getValueOrConstant(_value_2);
        _builder.append(_valueOrConstant_2, "");
        {
          XExpression _condition_2 = matcher.getCondition();
          boolean _notEquals_2 = (!Objects.equal(_condition_2, null));
          if (_notEquals_2) {
            _builder.append(", new ");
            String _locatorActivatorName_2 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_2, "");
            _builder.append("()");
          }
        }
        _builder.append(").");
        MatcherType _type_1 = matcher.getType();
        String _matcherType = this.matcherType(_type_1);
        _builder.append(_matcherType, "");
        _builder.append("(");
        _builder.append(eobjectTypeName, "");
        _builder.append("); // ");
        String _locatorString_2 = this.locatorString(columnLocator);
        _builder.append(_locatorString_2, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _matchLookupPartial(final OffsetLocator offsetLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      MatcherType _type = matcher.getType();
      String _literal = _type.getLiteral();
      int _compareTo = _literal.compareTo("before");
      boolean _equals = (_compareTo == 0);
      if (_equals) {
        _builder.append("config.setColumn(");
        IntValue _value = offsetLocator.getValue();
        String _valueOrConstant = this.getValueOrConstant(_value);
        _builder.append(_valueOrConstant, "");
        _builder.append(", ");
        boolean _isFixed = offsetLocator.isFixed();
        _builder.append(_isFixed, "");
        _builder.append(", true, ");
        boolean _isNobreak = offsetLocator.isNobreak();
        _builder.append(_isNobreak, "");
        {
          XExpression _condition = matcher.getCondition();
          boolean _notEquals = (!Objects.equal(_condition, null));
          if (_notEquals) {
            _builder.append(", new ");
            String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName, "");
            _builder.append("()");
          }
        }
        _builder.append(").before(");
        _builder.append(eobjectTypeName, "");
        _builder.append(");  // ");
        String _locatorString = this.locatorString(offsetLocator);
        _builder.append(_locatorString, "");
        _builder.newLineIfNotEmpty();
        _builder.append("config.setColumn(");
        IntValue _value_1 = offsetLocator.getValue();
        String _valueOrConstant_1 = this.getValueOrConstant(_value_1);
        _builder.append(_valueOrConstant_1, "");
        _builder.append(", ");
        boolean _isFixed_1 = offsetLocator.isFixed();
        _builder.append(_isFixed_1, "");
        _builder.append(", true, ");
        boolean _isNobreak_1 = offsetLocator.isNobreak();
        _builder.append(_isNobreak_1, "");
        {
          XExpression _condition_1 = matcher.getCondition();
          boolean _notEquals_1 = (!Objects.equal(_condition_1, null));
          if (_notEquals_1) {
            _builder.append(", new ");
            String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_1, "");
            _builder.append("()");
          }
        }
        _builder.append(").after(");
        _builder.append(eobjectTypeName, "");
        _builder.append(");  // ");
        String _locatorString_1 = this.locatorString(offsetLocator);
        _builder.append(_locatorString_1, "");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("config.setOffset(");
        IntValue _value_2 = offsetLocator.getValue();
        String _valueOrConstant_2 = this.getValueOrConstant(_value_2);
        _builder.append(_valueOrConstant_2, "");
        {
          XExpression _condition_2 = matcher.getCondition();
          boolean _notEquals_2 = (!Objects.equal(_condition_2, null));
          if (_notEquals_2) {
            _builder.append(", new ");
            String _locatorActivatorName_2 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_2, "");
            _builder.append("()");
          }
        }
        _builder.append(").");
        MatcherType _type_1 = matcher.getType();
        String _matcherType = this.matcherType(_type_1);
        _builder.append(_matcherType, "");
        _builder.append("(");
        _builder.append(eobjectTypeName, "");
        _builder.append("); // ");
        String _locatorString_2 = this.locatorString(offsetLocator);
        _builder.append(_locatorString_2, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _matchLookupPartial(final EObject locator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("config.");
    Locator _locator = matcher.getLocator();
    CharSequence _locator_1 = this.locator(matcher, _locator, partialName);
    _builder.append(_locator_1, "");
    _builder.append(".");
    MatcherType _type = matcher.getType();
    String _matcherType = this.matcherType(_type);
    _builder.append(_matcherType, "");
    _builder.append("(");
    _builder.append(eobjectTypeName, "");
    _builder.append(");");
    return _builder;
  }
  
  public CharSequence matchReference(final MatcherList matcherList, final EList<? extends EObject> elements, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = elements.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          EList<Matcher> _matchers = matcherList.getMatchers();
          for(final Matcher matcher : _matchers) {
            {
              MatcherType _type = matcher.getType();
              Boolean _isTwoArgumentMatcherType = FormatGeneratorUtil.isTwoArgumentMatcherType(_type);
              if ((_isTwoArgumentMatcherType).booleanValue()) {
                EObject _get = elements.get(0);
                EObject _get_1 = elements.get(1);
                Locator _locator = matcher.getLocator();
                CharSequence _match = this.match(matcher, _get, _get_1, _locator, partialName);
                _builder.append(_match, "");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  for(final EObject e : elements) {
                    Locator _locator_1 = matcher.getLocator();
                    CharSequence _match_1 = this.match(matcher, e, _locator_1, partialName);
                    _builder.append(_match_1, "");
                  }
                }
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence match(final Matcher matcher, final EObject element1, final EObject element2, final EObject locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("config.");
    Locator _locator = matcher.getLocator();
    CharSequence _locator_1 = this.locator(matcher, _locator, partialName);
    _builder.append(_locator_1, "");
    _builder.append(".");
    MatcherType _type = matcher.getType();
    String _matcherType = this.matcherType(_type);
    _builder.append(_matcherType, "");
    _builder.append("(");
    CharSequence _elementAccess = this.elementAccess(element1);
    _builder.append(_elementAccess, "");
    _builder.append(", ");
    CharSequence _elementAccess_1 = this.elementAccess(element2);
    _builder.append(_elementAccess_1, "");
    _builder.append("); // ");
    String _locatorString = this.locatorString(matcher);
    _builder.append(_locatorString, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("config.");
    Locator _locator = matcher.getLocator();
    CharSequence _locator_1 = this.locator(matcher, _locator, partialName);
    _builder.append(_locator_1, "");
    _builder.append(".");
    MatcherType _type = matcher.getType();
    String _matcherType = this.matcherType(_type);
    _builder.append(_matcherType, "");
    _builder.append("(");
    CharSequence _elementAccess = this.elementAccess(element);
    _builder.append(_elementAccess, "");
    _builder.append("); // ");
    String _locatorString = this.locatorString(matcher);
    _builder.append(_locatorString, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _match(final Matcher matcher, final EObject element, final NoFormatLocator locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("config.");
    Locator _locator = matcher.getLocator();
    CharSequence _locator_1 = this.locator(matcher, _locator, partialName);
    _builder.append(_locator_1, "");
    _builder.append(".");
    MatcherType _type = matcher.getType();
    String _matcherType = this.matcherType(_type);
    _builder.append(_matcherType, "");
    _builder.append("(");
    CharSequence _elementAccess = this.elementAccess(element);
    _builder.append(_elementAccess, "");
    _builder.append("); // ");
    String _locatorString = this.locatorString(matcher);
    _builder.append(_locatorString, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _match(final Matcher matcher, final EObject element, final ColumnLocator locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      MatcherType _type = matcher.getType();
      String _literal = _type.getLiteral();
      int _compareTo = _literal.compareTo("before");
      boolean _equals = (_compareTo == 0);
      if (_equals) {
        {
          XExpression _parameter = locator.getParameter();
          boolean _notEquals = (!Objects.equal(_parameter, null));
          if (_notEquals) {
            _builder.append("config.setColumn(");
            boolean _isFixed = locator.isFixed();
            _builder.append(_isFixed, "");
            _builder.append(", ");
            boolean _isRelative = locator.isRelative();
            _builder.append(_isRelative, "");
            _builder.append(", ");
            boolean _isNobreak = locator.isNobreak();
            _builder.append(_isNobreak, "");
            _builder.append(", new ");
            String _parameterCalculatorName = this.getParameterCalculatorName(partialName, matcher);
            _builder.append(_parameterCalculatorName, "");
            _builder.append("()");
            {
              XExpression _condition = matcher.getCondition();
              boolean _notEquals_1 = (!Objects.equal(_condition, null));
              if (_notEquals_1) {
                _builder.append(", new ");
                String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName, "");
                _builder.append("()");
              }
            }
            _builder.append(").before(");
            CharSequence _elementAccess = this.elementAccess(element);
            _builder.append(_elementAccess, "");
            _builder.append(");  // ");
            String _locatorString = this.locatorString(matcher);
            _builder.append(_locatorString, "");
            _builder.newLineIfNotEmpty();
            _builder.append("config.setColumn(");
            boolean _isFixed_1 = locator.isFixed();
            _builder.append(_isFixed_1, "");
            _builder.append(", ");
            boolean _isRelative_1 = locator.isRelative();
            _builder.append(_isRelative_1, "");
            _builder.append(", ");
            boolean _isNobreak_1 = locator.isNobreak();
            _builder.append(_isNobreak_1, "");
            _builder.append(", new ");
            String _parameterCalculatorName_1 = this.getParameterCalculatorName(partialName, matcher);
            _builder.append(_parameterCalculatorName_1, "");
            _builder.append("()");
            {
              XExpression _condition_1 = matcher.getCondition();
              boolean _notEquals_2 = (!Objects.equal(_condition_1, null));
              if (_notEquals_2) {
                _builder.append(", new ");
                String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_1, "");
                _builder.append("()");
              }
            }
            _builder.append(").after(");
            CharSequence _elementAccess_1 = this.elementAccess(element);
            _builder.append(_elementAccess_1, "");
            _builder.append(");  // ");
            String _locatorString_1 = this.locatorString(matcher);
            _builder.append(_locatorString_1, "");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("config.setColumn(");
            IntValue _value = locator.getValue();
            String _valueOrConstant = this.getValueOrConstant(_value);
            _builder.append(_valueOrConstant, "");
            _builder.append(", ");
            boolean _isFixed_2 = locator.isFixed();
            _builder.append(_isFixed_2, "");
            _builder.append(", ");
            boolean _isRelative_2 = locator.isRelative();
            _builder.append(_isRelative_2, "");
            _builder.append(", ");
            boolean _isNobreak_2 = locator.isNobreak();
            _builder.append(_isNobreak_2, "");
            {
              XExpression _condition_2 = matcher.getCondition();
              boolean _notEquals_3 = (!Objects.equal(_condition_2, null));
              if (_notEquals_3) {
                _builder.append(", new ");
                String _locatorActivatorName_2 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_2, "");
                _builder.append("()");
              }
            }
            _builder.append(").before(");
            CharSequence _elementAccess_2 = this.elementAccess(element);
            _builder.append(_elementAccess_2, "");
            _builder.append(");  // ");
            String _locatorString_2 = this.locatorString(matcher);
            _builder.append(_locatorString_2, "");
            _builder.newLineIfNotEmpty();
            _builder.append("config.setColumn(");
            IntValue _value_1 = locator.getValue();
            String _valueOrConstant_1 = this.getValueOrConstant(_value_1);
            _builder.append(_valueOrConstant_1, "");
            _builder.append(", ");
            boolean _isFixed_3 = locator.isFixed();
            _builder.append(_isFixed_3, "");
            _builder.append(", ");
            boolean _isRelative_3 = locator.isRelative();
            _builder.append(_isRelative_3, "");
            _builder.append(", ");
            boolean _isNobreak_3 = locator.isNobreak();
            _builder.append(_isNobreak_3, "");
            {
              XExpression _condition_3 = matcher.getCondition();
              boolean _notEquals_4 = (!Objects.equal(_condition_3, null));
              if (_notEquals_4) {
                _builder.append(", new ");
                String _locatorActivatorName_3 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_3, "");
                _builder.append("()");
              }
            }
            _builder.append(").after(");
            CharSequence _elementAccess_3 = this.elementAccess(element);
            _builder.append(_elementAccess_3, "");
            _builder.append(");  // ");
            String _locatorString_3 = this.locatorString(matcher);
            _builder.append(_locatorString_3, "");
            _builder.newLineIfNotEmpty();
          }
        }
      } else {
        {
          XExpression _parameter_1 = locator.getParameter();
          boolean _notEquals_5 = (!Objects.equal(_parameter_1, null));
          if (_notEquals_5) {
            _builder.append("config.setColumn(new ");
            String _parameterCalculatorName_2 = this.getParameterCalculatorName(partialName, matcher);
            _builder.append(_parameterCalculatorName_2, "");
            _builder.append("()");
            {
              XExpression _condition_4 = matcher.getCondition();
              boolean _notEquals_6 = (!Objects.equal(_condition_4, null));
              if (_notEquals_6) {
                _builder.append(", new ");
                String _locatorActivatorName_4 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_4, "");
                _builder.append("()");
              }
            }
            _builder.append(").");
            MatcherType _type_1 = matcher.getType();
            String _matcherType = this.matcherType(_type_1);
            _builder.append(_matcherType, "");
            _builder.append("(");
            CharSequence _elementAccess_4 = this.elementAccess(element);
            _builder.append(_elementAccess_4, "");
            _builder.append("); // ");
            String _locatorString_4 = this.locatorString(matcher);
            _builder.append(_locatorString_4, "");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("config.setColumn(");
            IntValue _value_2 = locator.getValue();
            String _valueOrConstant_2 = this.getValueOrConstant(_value_2);
            _builder.append(_valueOrConstant_2, "");
            {
              XExpression _condition_5 = matcher.getCondition();
              boolean _notEquals_7 = (!Objects.equal(_condition_5, null));
              if (_notEquals_7) {
                _builder.append(", new ");
                String _locatorActivatorName_5 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_5, "");
                _builder.append("()");
              }
            }
            _builder.append(").");
            MatcherType _type_2 = matcher.getType();
            String _matcherType_1 = this.matcherType(_type_2);
            _builder.append(_matcherType_1, "");
            _builder.append("(");
            CharSequence _elementAccess_5 = this.elementAccess(element);
            _builder.append(_elementAccess_5, "");
            _builder.append("); // ");
            String _locatorString_5 = this.locatorString(matcher);
            _builder.append(_locatorString_5, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  protected CharSequence _match(final Matcher matcher, final EObject element, final OffsetLocator locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      MatcherType _type = matcher.getType();
      String _literal = _type.getLiteral();
      int _compareTo = _literal.compareTo("before");
      boolean _equals = (_compareTo == 0);
      if (_equals) {
        _builder.append("config.setColumn(");
        IntValue _value = locator.getValue();
        String _valueOrConstant = this.getValueOrConstant(_value);
        _builder.append(_valueOrConstant, "");
        _builder.append(", ");
        boolean _isFixed = locator.isFixed();
        _builder.append(_isFixed, "");
        _builder.append(", true, ");
        boolean _isNobreak = locator.isNobreak();
        _builder.append(_isNobreak, "");
        {
          XExpression _condition = matcher.getCondition();
          boolean _notEquals = (!Objects.equal(_condition, null));
          if (_notEquals) {
            _builder.append(", new ");
            String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName, "");
            _builder.append("()");
          }
        }
        _builder.append(").before(");
        CharSequence _elementAccess = this.elementAccess(element);
        _builder.append(_elementAccess, "");
        _builder.append(");  // ");
        String _locatorString = this.locatorString(matcher);
        _builder.append(_locatorString, "");
        _builder.newLineIfNotEmpty();
        _builder.append("config.setColumn(");
        IntValue _value_1 = locator.getValue();
        String _valueOrConstant_1 = this.getValueOrConstant(_value_1);
        _builder.append(_valueOrConstant_1, "");
        _builder.append(", ");
        boolean _isFixed_1 = locator.isFixed();
        _builder.append(_isFixed_1, "");
        _builder.append(", true, ");
        boolean _isNobreak_1 = locator.isNobreak();
        _builder.append(_isNobreak_1, "");
        {
          XExpression _condition_1 = matcher.getCondition();
          boolean _notEquals_1 = (!Objects.equal(_condition_1, null));
          if (_notEquals_1) {
            _builder.append(", new ");
            String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_1, "");
            _builder.append("()");
          }
        }
        _builder.append(").after(");
        CharSequence _elementAccess_1 = this.elementAccess(element);
        _builder.append(_elementAccess_1, "");
        _builder.append(");  // ");
        String _locatorString_1 = this.locatorString(matcher);
        _builder.append(_locatorString_1, "");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("config.setOffset(");
        IntValue _value_2 = locator.getValue();
        String _valueOrConstant_2 = this.getValueOrConstant(_value_2);
        _builder.append(_valueOrConstant_2, "");
        {
          XExpression _condition_2 = matcher.getCondition();
          boolean _notEquals_2 = (!Objects.equal(_condition_2, null));
          if (_notEquals_2) {
            _builder.append(", new ");
            String _locatorActivatorName_2 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_2, "");
            _builder.append("()");
          }
        }
        _builder.append(").");
        MatcherType _type_1 = matcher.getType();
        String _matcherType = this.matcherType(_type_1);
        _builder.append(_matcherType, "");
        _builder.append("(");
        CharSequence _elementAccess_2 = this.elementAccess(element);
        _builder.append(_elementAccess_2, "");
        _builder.append("); // ");
        String _locatorString_2 = this.locatorString(matcher);
        _builder.append(_locatorString_2, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _match(final Matcher matcher, final EObject element, final IndentLocator locator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("config.");
    Locator _locator = matcher.getLocator();
    CharSequence _locator_1 = this.locator(matcher, _locator, partialName);
    _builder.append(_locator_1, "");
    _builder.append(".");
    MatcherType _type = matcher.getType();
    String _matcherType = this.matcherType(_type);
    _builder.append(_matcherType, "");
    _builder.append("(");
    CharSequence _elementAccess = this.elementAccess(element);
    _builder.append(_elementAccess, "");
    _builder.append("); // ");
    String _locatorString = this.locatorString(matcher);
    _builder.append(_locatorString, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public String matcherType(final MatcherType matcherType) {
    return matcherType.getLiteral();
  }
  
  protected CharSequence _elementAccess(final GrammarElementLookup grammarElementLookup) {
    StringConcatenation _builder = new StringConcatenation();
    {
      AbstractRule _rule = grammarElementLookup.getRule();
      boolean _notEquals = (!Objects.equal(_rule, null));
      if (_notEquals) {
        _builder.append("elements.findRuleCalls(");
        AbstractRule _rule_1 = grammarElementLookup.getRule();
        String _gaElementsAccessor = this.grammarAccess.gaElementsAccessor(_rule_1);
        _builder.append(_gaElementsAccessor, "");
        _builder.append(")");
      } else {
        String _keyword = grammarElementLookup.getKeyword();
        boolean _notEquals_1 = (!Objects.equal(_keyword, null));
        if (_notEquals_1) {
          _builder.append("elements.findKeywords(\"");
          String _keyword_1 = grammarElementLookup.getKeyword();
          _builder.append(_keyword_1, "");
          _builder.append("\")");
        }
      }
    }
    return _builder;
  }
  
  protected CharSequence _elementAccess(final GrammarElementReference grammarElementReference) {
    CharSequence _xifexpression = null;
    RuleCall _ruleCall = grammarElementReference.getRuleCall();
    boolean _notEquals = (!Objects.equal(_ruleCall, null));
    if (_notEquals) {
      RuleCall _ruleCall_1 = grammarElementReference.getRuleCall();
      _xifexpression = this.elementAccess(_ruleCall_1);
    } else {
      CharSequence _xifexpression_1 = null;
      Keyword _keyword = grammarElementReference.getKeyword();
      boolean _notEquals_1 = (!Objects.equal(_keyword, null));
      if (_notEquals_1) {
        Keyword _keyword_1 = grammarElementReference.getKeyword();
        _xifexpression_1 = this.elementAccess(_keyword_1);
      } else {
        CharSequence _xifexpression_2 = null;
        Assignment _assignment = grammarElementReference.getAssignment();
        boolean _notEquals_2 = (!Objects.equal(_assignment, null));
        if (_notEquals_2) {
          Assignment _assignment_1 = grammarElementReference.getAssignment();
          _xifexpression_2 = this.elementAccess(_assignment_1);
        } else {
          CharSequence _xifexpression_3 = null;
          AbstractRule _self = grammarElementReference.getSelf();
          boolean _notEquals_3 = (!Objects.equal(_self, null));
          if (_notEquals_3) {
            String _xifexpression_4 = null;
            Boolean _containedByParserRule = FormatGeneratorUtil.containedByParserRule(grammarElementReference);
            if ((_containedByParserRule).booleanValue()) {
              _xifexpression_4 = "elements.getRule()";
            } else {
              _xifexpression_4 = "rule";
            }
            _xifexpression_3 = _xifexpression_4;
          } else {
            CharSequence _xifexpression_5 = null;
            AbstractRule _rule = grammarElementReference.getRule();
            boolean _notEquals_4 = (!Objects.equal(_rule, null));
            if (_notEquals_4) {
              AbstractRule _rule_1 = grammarElementReference.getRule();
              _xifexpression_5 = this.elementAccess(_rule_1);
            }
            _xifexpression_3 = _xifexpression_5;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected CharSequence _elementAccess(final AbstractRule abstractRule) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("getGrammarAccess().");
    String _gaRuleAccessor = this.grammarAccess.gaRuleAccessor(abstractRule);
    _builder.append(_gaRuleAccessor, "");
    return _builder;
  }
  
  protected CharSequence _elementAccess(final AbstractElement abstractElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("elements.");
    String _gaElementAccessor = this.grammarAccess.gaElementAccessor(abstractElement);
    _builder.append(_gaElementAccessor, "");
    return _builder;
  }
  
  protected CharSequence _elementAccess(final Object object) {
    Class<?> _class = object.getClass();
    String _name = _class.getName();
    String _plus = ("Unknown Xtext element " + _name);
    throw new UnsupportedOperationException(_plus);
  }
  
  protected CharSequence _locator(final Matcher matcher, final SpaceLocator spaceLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isNoSpace = spaceLocator.isNoSpace();
      if (_isNoSpace) {
        _builder.append("setNoSpace(");
        {
          XExpression _condition = matcher.getCondition();
          boolean _notEquals = (!Objects.equal(_condition, null));
          if (_notEquals) {
            _builder.append("new ");
            String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName, "");
            _builder.append("()");
          }
        }
        _builder.append(")");
      } else {
        _builder.append("setSpace(");
        StringValue _value = spaceLocator.getValue();
        String _valueOrConstant = this.getValueOrConstant(_value);
        _builder.append(_valueOrConstant, "");
        {
          XExpression _condition_1 = matcher.getCondition();
          boolean _notEquals_1 = (!Objects.equal(_condition_1, null));
          if (_notEquals_1) {
            _builder.append(", new ");
            String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName_1, "");
            _builder.append("()");
          }
        }
        _builder.append(")");
      }
    }
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final RightPaddingLocator rightPaddingLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("setRightPadding(");
    IntValue _value = rightPaddingLocator.getValue();
    String _valueOrConstant = this.getValueOrConstant(_value);
    _builder.append(_valueOrConstant, "");
    {
      XExpression _condition = matcher.getCondition();
      boolean _notEquals = (!Objects.equal(_condition, null));
      if (_notEquals) {
        _builder.append(", new ");
        String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
        _builder.append(_locatorActivatorName, "");
        _builder.append("()");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final LinewrapLocator linewrapLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isNoLinewrap = linewrapLocator.isNoLinewrap();
      if (_isNoLinewrap) {
        _builder.append("setNoLinewrap(");
        {
          XExpression _condition = matcher.getCondition();
          boolean _notEquals = (!Objects.equal(_condition, null));
          if (_notEquals) {
            _builder.append("new ");
            String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
            _builder.append(_locatorActivatorName, "");
            _builder.append("()");
          }
        }
        _builder.append(")");
      } else {
        _builder.append("setLinewrap(");
        {
          IntValue _value = linewrapLocator.getValue();
          boolean _notEquals_1 = (!Objects.equal(_value, null));
          if (_notEquals_1) {
            IntValue _value_1 = linewrapLocator.getValue();
            String _valueOrConstant = this.getValueOrConstant(_value_1);
            _builder.append(_valueOrConstant, "");
            {
              XExpression _condition_1 = matcher.getCondition();
              boolean _notEquals_2 = (!Objects.equal(_condition_1, null));
              if (_notEquals_2) {
                _builder.append(", new ");
                String _locatorActivatorName_1 = this.getLocatorActivatorName(partialName, matcher);
                _builder.append(_locatorActivatorName_1, "");
                _builder.append("()");
              }
            }
          } else {
            IntValue _minimum = linewrapLocator.getMinimum();
            boolean _notEquals_3 = (!Objects.equal(_minimum, null));
            if (_notEquals_3) {
              IntValue _minimum_1 = linewrapLocator.getMinimum();
              String _valueOrConstant_1 = this.getValueOrConstant(_minimum_1);
              _builder.append(_valueOrConstant_1, "");
              _builder.append(", ");
              IntValue _default = linewrapLocator.getDefault();
              String _valueOrConstant_2 = this.getValueOrConstant(_default);
              _builder.append(_valueOrConstant_2, "");
              _builder.append(", ");
              IntValue _maximum = linewrapLocator.getMaximum();
              String _valueOrConstant_3 = this.getValueOrConstant(_maximum);
              _builder.append(_valueOrConstant_3, "");
              {
                XExpression _condition_2 = matcher.getCondition();
                boolean _notEquals_4 = (!Objects.equal(_condition_2, null));
                if (_notEquals_4) {
                  _builder.append(", new ");
                  String _locatorActivatorName_2 = this.getLocatorActivatorName(partialName, matcher);
                  _builder.append(_locatorActivatorName_2, "");
                  _builder.append("()");
                }
              }
            } else {
              {
                XExpression _condition_3 = matcher.getCondition();
                boolean _notEquals_5 = (!Objects.equal(_condition_3, null));
                if (_notEquals_5) {
                  _builder.append("new ");
                  String _locatorActivatorName_3 = this.getLocatorActivatorName(partialName, matcher);
                  _builder.append(_locatorActivatorName_3, "");
                  _builder.append("()");
                }
              }
            }
          }
        }
        _builder.append(")");
      }
    }
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final ColumnLocator columnLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("setColumn(");
    IntValue _value = columnLocator.getValue();
    String _valueOrConstant = this.getValueOrConstant(_value);
    _builder.append(_valueOrConstant, "");
    _builder.append(", ");
    boolean _isFixed = columnLocator.isFixed();
    _builder.append(_isFixed, "");
    _builder.append(", ");
    boolean _isRelative = columnLocator.isRelative();
    _builder.append(_isRelative, "");
    _builder.append(", ");
    boolean _isNobreak = columnLocator.isNobreak();
    _builder.append(_isNobreak, "");
    {
      XExpression _condition = matcher.getCondition();
      boolean _notEquals = (!Objects.equal(_condition, null));
      if (_notEquals) {
        _builder.append(", new ");
        String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
        _builder.append(_locatorActivatorName, "");
        _builder.append("()");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final OffsetLocator offsetLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("setColumn(");
    IntValue _value = offsetLocator.getValue();
    String _valueOrConstant = this.getValueOrConstant(_value);
    _builder.append(_valueOrConstant, "");
    _builder.append(", ");
    boolean _isFixed = offsetLocator.isFixed();
    _builder.append(_isFixed, "");
    _builder.append(", true, ");
    boolean _isNobreak = offsetLocator.isNobreak();
    _builder.append(_isNobreak, "");
    {
      XExpression _condition = matcher.getCondition();
      boolean _notEquals = (!Objects.equal(_condition, null));
      if (_notEquals) {
        _builder.append(", new ");
        String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
        _builder.append(_locatorActivatorName, "");
        _builder.append("()");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final IndentLocator indentLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isIncrement = indentLocator.isIncrement();
      if (_isIncrement) {
        _builder.append("setIndentationIncrement(");
      } else {
        _builder.append("setIndentationDecrement(");
      }
    }
    {
      if (((!Objects.equal(indentLocator.getValue(), null)) && ((!Objects.equal(indentLocator.getValue().getReference(), null)) || ((indentLocator.getValue().getLiteral()).intValue() >= 1)))) {
        IntValue _value = indentLocator.getValue();
        String _valueOrConstant = this.getValueOrConstant(_value);
        _builder.append(_valueOrConstant, "");
      } else {
        XExpression _parameter = indentLocator.getParameter();
        boolean _notEquals = (!Objects.equal(_parameter, null));
        if (_notEquals) {
          _builder.append("new ");
          String _parameterCalculatorName = this.getParameterCalculatorName(partialName, matcher);
          _builder.append(_parameterCalculatorName, "");
          _builder.append("()");
        }
      }
    }
    {
      XExpression _condition = matcher.getCondition();
      boolean _notEquals_1 = (!Objects.equal(_condition, null));
      if (_notEquals_1) {
        {
          if (((!Objects.equal(indentLocator.getValue(), null)) || (!Objects.equal(indentLocator.getParameter(), null)))) {
            _builder.append(",");
          }
        }
        _builder.append(" new ");
        String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
        _builder.append(_locatorActivatorName, "");
        _builder.append("()");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final NoFormatLocator noFormatLocator, final String partialName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("setNoFormat(");
    {
      XExpression _condition = matcher.getCondition();
      boolean _notEquals = (!Objects.equal(_condition, null));
      if (_notEquals) {
        _builder.append("new ");
        String _locatorActivatorName = this.getLocatorActivatorName(partialName, matcher);
        _builder.append(_locatorActivatorName, "");
        _builder.append("()");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence _locator(final Matcher matcher, final Locator locator, final String partialName) {
    Class<? extends Locator> _class = locator.getClass();
    String _name = _class.getName();
    String _plus = ("Unknown locator " + _name);
    throw new UnsupportedOperationException(_plus);
  }
  
  protected String _getValueOrConstant(final StringValue stringValue) {
    String _xifexpression = null;
    String _literal = stringValue.getLiteral();
    boolean _equals = Objects.equal(_literal, null);
    if (_equals) {
      Constant _reference = stringValue.getReference();
      _xifexpression = _reference.getName();
    } else {
      String _literal_1 = stringValue.getLiteral();
      String _plus = ("\"" + _literal_1);
      _xifexpression = (_plus + "\"");
    }
    return _xifexpression;
  }
  
  protected String _getValueOrConstant(final IntValue intValue) {
    String _xifexpression = null;
    Integer _literal = intValue.getLiteral();
    boolean _equals = Objects.equal(_literal, null);
    if (_equals) {
      Constant _reference = intValue.getReference();
      _xifexpression = _reference.getName();
    } else {
      Integer _literal_1 = intValue.getLiteral();
      _xifexpression = _literal_1.toString();
    }
    return _xifexpression;
  }
  
  public String locatorString(final EObject object) {
    String _location = GeneratorUtil.getLocation(object);
    String[] _split = _location.split("/");
    return IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(_split)));
  }
  
  public void infer(final EObject format, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (format instanceof FormatConfiguration) {
      _infer((FormatConfiguration)format, acceptor, isPreIndexingPhase);
      return;
    } else if (format != null) {
      _infer(format, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(format, acceptor, isPreIndexingPhase).toString());
    }
  }
  
  public JvmTypeReference getLocatorActivatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, (GrammarRule)rule);
    } else if (rule instanceof WildcardRule) {
      return _getLocatorActivatorSuperType(formatConfiguration, (WildcardRule)rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(formatConfiguration, rule).toString());
    }
  }
  
  public JvmTypeReference getParameterCalculatorSuperType(final FormatConfiguration formatConfiguration, final Rule rule) {
    if (rule instanceof GrammarRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, (GrammarRule)rule);
    } else if (rule instanceof WildcardRule) {
      return _getParameterCalculatorSuperType(formatConfiguration, (WildcardRule)rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(formatConfiguration, rule).toString());
    }
  }
  
  public String getGrammarElementNameFromSelf(final Rule rule) {
    if (rule instanceof GrammarRule) {
      return _getGrammarElementNameFromSelf((GrammarRule)rule);
    } else if (rule instanceof WildcardRule) {
      return _getGrammarElementNameFromSelf((WildcardRule)rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(rule).toString());
    }
  }
  
  public String getRuleName(final EObject rule) {
    if (rule instanceof GrammarRule) {
      return _getRuleName((GrammarRule)rule);
    } else if (rule instanceof WildcardRule) {
      return _getRuleName((WildcardRule)rule);
    } else if (rule != null) {
      return _getRuleName(rule);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(rule).toString());
    }
  }
  
  public String getDirectiveName(final EObject directive) {
    if (directive instanceof ContextFreeDirective) {
      return _getDirectiveName((ContextFreeDirective)directive);
    } else if (directive instanceof KeywordPair) {
      return _getDirectiveName((KeywordPair)directive);
    } else if (directive instanceof SpecificDirective) {
      return _getDirectiveName((SpecificDirective)directive);
    } else if (directive instanceof GroupBlock) {
      return _getDirectiveName((GroupBlock)directive);
    } else if (directive != null) {
      return _getDirectiveName(directive);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(directive).toString());
    }
  }
  
  public CharSequence directive(final Object dir, final String partialName) {
    if (dir instanceof ContextFreeDirective) {
      return _directive((ContextFreeDirective)dir, partialName);
    } else if (dir instanceof KeywordPair) {
      return _directive((KeywordPair)dir, partialName);
    } else if (dir instanceof SpecificDirective) {
      return _directive((SpecificDirective)dir, partialName);
    } else if (dir instanceof GroupBlock) {
      return _directive((GroupBlock)dir, partialName);
    } else if (dir != null) {
      return _directive(dir, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(dir, partialName).toString());
    }
  }
  
  public CharSequence matchLookupPartial(final EObject columnLocator, final Matcher matcher, final String eobjectTypeName, final String partialName) {
    if (columnLocator instanceof ColumnLocator) {
      return _matchLookupPartial((ColumnLocator)columnLocator, matcher, eobjectTypeName, partialName);
    } else if (columnLocator instanceof OffsetLocator) {
      return _matchLookupPartial((OffsetLocator)columnLocator, matcher, eobjectTypeName, partialName);
    } else if (columnLocator != null) {
      return _matchLookupPartial(columnLocator, matcher, eobjectTypeName, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(columnLocator, matcher, eobjectTypeName, partialName).toString());
    }
  }
  
  public CharSequence match(final Matcher matcher, final EObject element, final Locator locator, final String partialName) {
    if (locator instanceof ColumnLocator) {
      return _match(matcher, element, (ColumnLocator)locator, partialName);
    } else if (locator instanceof IndentLocator) {
      return _match(matcher, element, (IndentLocator)locator, partialName);
    } else if (locator instanceof NoFormatLocator) {
      return _match(matcher, element, (NoFormatLocator)locator, partialName);
    } else if (locator instanceof OffsetLocator) {
      return _match(matcher, element, (OffsetLocator)locator, partialName);
    } else if (locator != null) {
      return _match(matcher, element, locator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(matcher, element, locator, partialName).toString());
    }
  }
  
  public CharSequence elementAccess(final Object grammarElementLookup) {
    if (grammarElementLookup instanceof GrammarElementLookup) {
      return _elementAccess((GrammarElementLookup)grammarElementLookup);
    } else if (grammarElementLookup instanceof GrammarElementReference) {
      return _elementAccess((GrammarElementReference)grammarElementLookup);
    } else if (grammarElementLookup instanceof AbstractElement) {
      return _elementAccess((AbstractElement)grammarElementLookup);
    } else if (grammarElementLookup instanceof AbstractRule) {
      return _elementAccess((AbstractRule)grammarElementLookup);
    } else if (grammarElementLookup != null) {
      return _elementAccess(grammarElementLookup);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(grammarElementLookup).toString());
    }
  }
  
  public CharSequence locator(final Matcher matcher, final Locator columnLocator, final String partialName) {
    if (columnLocator instanceof ColumnLocator) {
      return _locator(matcher, (ColumnLocator)columnLocator, partialName);
    } else if (columnLocator instanceof IndentLocator) {
      return _locator(matcher, (IndentLocator)columnLocator, partialName);
    } else if (columnLocator instanceof LinewrapLocator) {
      return _locator(matcher, (LinewrapLocator)columnLocator, partialName);
    } else if (columnLocator instanceof NoFormatLocator) {
      return _locator(matcher, (NoFormatLocator)columnLocator, partialName);
    } else if (columnLocator instanceof OffsetLocator) {
      return _locator(matcher, (OffsetLocator)columnLocator, partialName);
    } else if (columnLocator instanceof RightPaddingLocator) {
      return _locator(matcher, (RightPaddingLocator)columnLocator, partialName);
    } else if (columnLocator instanceof SpaceLocator) {
      return _locator(matcher, (SpaceLocator)columnLocator, partialName);
    } else if (columnLocator != null) {
      return _locator(matcher, columnLocator, partialName);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(matcher, columnLocator, partialName).toString());
    }
  }
  
  public String getValueOrConstant(final EObject intValue) {
    if (intValue instanceof IntValue) {
      return _getValueOrConstant((IntValue)intValue);
    } else if (intValue instanceof StringValue) {
      return _getValueOrConstant((StringValue)intValue);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(intValue).toString());
    }
  }
}
