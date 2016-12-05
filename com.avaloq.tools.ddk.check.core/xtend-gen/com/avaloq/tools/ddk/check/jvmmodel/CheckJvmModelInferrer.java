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
package com.avaloq.tools.ddk.check.jvmmodel;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.Member;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.avaloq.tools.ddk.check.generator.CheckPropertiesGenerator;
import com.avaloq.tools.ddk.check.resource.CheckLocationInFileProvider;
import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.check.runtime.issue.AbstractIssue;
import com.avaloq.tools.ddk.check.runtime.issue.DefaultCheckImpl;
import com.avaloq.tools.ddk.check.runtime.issue.SeverityKind;
import com.avaloq.tools.ddk.check.runtime.validation.ComposedCheckValidator;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmAnnotationValue;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFeature;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.EObjectDiagnosticImpl;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class CheckJvmModelInferrer extends AbstractModelInferrer {
  @Inject
  private TypesFactory typesFactory;
  
  @Inject
  private CheckLocationInFileProvider locationInFileProvider;
  
  @Inject
  @Extension
  private CheckGeneratorExtensions _checkGeneratorExtensions;
  
  @Inject
  @Extension
  private CheckGeneratorNaming _checkGeneratorNaming;
  
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  protected void _infer(final CheckCatalog catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    String _qualifiedCatalogClassName = this._checkGeneratorNaming.qualifiedCatalogClassName(catalog);
    final JvmGenericType catalogClass = this._jvmTypesBuilder.toClass(catalog, _qualifiedCatalogClassName);
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      final JvmTypeReference parentType = this.checkedTypeRef(catalog, AbstractIssue.class);
      boolean _notEquals = (!Objects.equal(parentType, null));
      if (_notEquals) {
        EList<JvmTypeReference> _superTypes = it.getSuperTypes();
        this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, parentType);
      }
      final JvmTypeReference singletonTypeRef = this.checkedTypeRef(catalog, Singleton.class);
      boolean _notEquals_1 = (!Objects.equal(singletonTypeRef, null));
      if (_notEquals_1) {
        final JvmAnnotationReference singleton = this.typesFactory.createJvmAnnotationReference();
        final JvmType annotationType = singletonTypeRef.getType();
        singleton.setAnnotation(((JvmAnnotationType) annotationType));
        EList<JvmAnnotationReference> _annotations = it.getAnnotations();
        this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, singleton);
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Issues for ");
      String _name = catalog.getName();
      _builder.append(_name, "");
      _builder.append(".");
      this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
      EList<JvmMember> _members = it.getMembers();
      JvmTypeReference _checkedTypeRef = this.checkedTypeRef(catalog, ICheckConfigurationStoreService.class);
      Iterable<JvmField> _createInjectedField = this.createInjectedField(catalog, "checkConfigurationStoreService", _checkedTypeRef);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members, _createInjectedField);
      EList<JvmMember> _members_1 = it.getMembers();
      EList<Check> _allChecks = catalog.getAllChecks();
      final Function1<Check, Iterable<JvmMember>> _function_1 = (Check c) -> {
        return this.createIssue(catalog, c);
      };
      List<Iterable<JvmMember>> _map = ListExtensions.<Check, Iterable<JvmMember>>map(_allChecks, _function_1);
      Iterable<JvmMember> _flatten = Iterables.<JvmMember>concat(_map);
      Iterable<JvmMember> _filterNull = IterableExtensions.<JvmMember>filterNull(_flatten);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _filterNull);
    };
    acceptor.<JvmGenericType>accept(catalogClass, _function);
    String _qualifiedValidatorClassName = this._checkGeneratorNaming.qualifiedValidatorClassName(catalog);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(catalog, _qualifiedValidatorClassName);
    final Procedure1<JvmGenericType> _function_1 = (JvmGenericType it) -> {
      final JvmTypeReference parentType = this.checkedTypeRef(catalog, DefaultCheckImpl.class);
      boolean _notEquals = (!Objects.equal(parentType, null));
      if (_notEquals) {
        EList<JvmTypeReference> _superTypes = it.getSuperTypes();
        this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, parentType);
      }
      this.setCheckComposition(catalog, it);
      final CheckCatalog included = catalog.getIncludedCatalogs();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Validator for ");
      String _name = catalog.getName();
      _builder.append(_name, "");
      _builder.append(".");
      {
        boolean _and = false;
        boolean _notEquals_1 = (!Objects.equal(included, null));
        if (!_notEquals_1) {
          _and = false;
        } else {
          boolean _eIsProxy = included.eIsProxy();
          boolean _not = (!_eIsProxy);
          _and = _not;
        }
        if (_and) {
          _builder.append(" Includes validations from its parent catalog.");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("@see ");
          String _validatorClassName = this._checkGeneratorNaming.validatorClassName(included);
          _builder.append(_validatorClassName, "");
        }
      }
      this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
      EList<JvmMember> _members = it.getMembers();
      String _catalogInstanceName = this._checkGeneratorNaming.catalogInstanceName(catalog);
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(catalogClass);
      Iterable<JvmField> _createInjectedField = this.createInjectedField(catalog, _catalogInstanceName, _typeRef);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members, _createInjectedField);
      boolean _and_1 = false;
      boolean _notEquals_2 = (!Objects.equal(included, null));
      if (!_notEquals_2) {
        _and_1 = false;
      } else {
        boolean _eIsProxy_1 = included.eIsProxy();
        boolean _not_1 = (!_eIsProxy_1);
        _and_1 = _not_1;
      }
      if (_and_1) {
        EList<JvmMember> _members_1 = it.getMembers();
        String _catalogInstanceName_1 = this._checkGeneratorNaming.catalogInstanceName(included);
        String _qualifiedCatalogClassName_1 = this._checkGeneratorNaming.qualifiedCatalogClassName(included);
        JvmTypeReference _checkedTypeRef = this.checkedTypeRef(catalog, _qualifiedCatalogClassName_1);
        Iterable<JvmField> _createInjectedField_1 = this.createInjectedField(catalog, _catalogInstanceName_1, _checkedTypeRef);
        this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _createInjectedField_1);
      }
      EList<JvmMember> _members_2 = it.getMembers();
      EList<Member> _members_3 = catalog.getMembers();
      final Function1<Member, JvmField> _function_2 = (Member m) -> {
        String _name_1 = m.getName();
        JvmTypeReference _type = m.getType();
        final Procedure1<JvmField> _function_3 = (JvmField it_1) -> {
          XExpression _value = m.getValue();
          this._jvmTypesBuilder.setInitializer(it_1, _value);
          EList<XAnnotation> _annotations = m.getAnnotations();
          this._jvmTypesBuilder.addAnnotations(it_1, _annotations);
        };
        return this._jvmTypesBuilder.toField(m, _name_1, _type, _function_3);
      };
      List<JvmField> _map = ListExtensions.<Member, JvmField>map(_members_3, _function_2);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _map);
      EList<JvmMember> _members_4 = it.getMembers();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(String.class);
      final Procedure1<JvmOperation> _function_3 = (JvmOperation it_1) -> {
        final Procedure1<ITreeAppendable> _function_4 = (ITreeAppendable it_2) -> {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("return \"");
          String _packageName = catalog.getPackageName();
          _builder_1.append(_packageName, "");
          _builder_1.append(".");
          String _name_1 = catalog.getName();
          _builder_1.append(_name_1, "");
          _builder_1.append("\";");
          it_2.append(_builder_1);
        };
        this._jvmTypesBuilder.setBody(it_1, _function_4);
      };
      JvmOperation _method = this._jvmTypesBuilder.toMethod(catalog, "getQualifiedCatalogName", _typeRef_1, _function_3);
      this._jvmTypesBuilder.<JvmOperation>operator_add(_members_4, _method);
      EList<Check> _checks = catalog.getChecks();
      EList<Category> _categories = catalog.getCategories();
      final Function1<Category, EList<Check>> _function_4 = (Category cat) -> {
        return cat.getChecks();
      };
      List<EList<Check>> _map_1 = ListExtensions.<Category, EList<Check>>map(_categories, _function_4);
      Iterable<Check> _flatten = Iterables.<Check>concat(_map_1);
      final Iterable<Check> allChecks = Iterables.<Check>concat(_checks, _flatten);
      EList<JvmMember> _members_5 = it.getMembers();
      final Function1<Check, Iterable<JvmMember>> _function_5 = (Check chk) -> {
        return this.createCheck(chk);
      };
      Iterable<Iterable<JvmMember>> _map_2 = IterableExtensions.<Check, Iterable<JvmMember>>map(allChecks, _function_5);
      Iterable<JvmMember> _flatten_1 = Iterables.<JvmMember>concat(_map_2);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_5, _flatten_1);
      EList<JvmMember> _members_6 = it.getMembers();
      EList<Implementation> _implementations = catalog.getImplementations();
      final Function1<Implementation, JvmOperation> _function_6 = (Implementation impl) -> {
        Context _context = impl.getContext();
        return this.createCheckMethod(_context);
      };
      List<JvmOperation> _map_3 = ListExtensions.<Implementation, JvmOperation>map(_implementations, _function_6);
      Iterable<JvmOperation> _filterNull = IterableExtensions.<JvmOperation>filterNull(_map_3);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_6, _filterNull);
    };
    acceptor.<JvmGenericType>accept(_class, _function_1);
    String _qualifiedPreferenceInitializerClassName = this._checkGeneratorNaming.qualifiedPreferenceInitializerClassName(catalog);
    JvmGenericType _class_1 = this._jvmTypesBuilder.toClass(catalog, _qualifiedPreferenceInitializerClassName);
    final Procedure1<JvmGenericType> _function_2 = (JvmGenericType it) -> {
      final JvmTypeReference parentType = this.checkedTypeRef(catalog, AbstractPreferenceInitializer.class);
      boolean _notEquals = (!Objects.equal(parentType, null));
      if (_notEquals) {
        EList<JvmTypeReference> _superTypes = it.getSuperTypes();
        this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, parentType);
      }
      EList<JvmMember> _members = it.getMembers();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(String.class);
      final Procedure1<JvmField> _function_3 = (JvmField it_1) -> {
        it_1.setStatic(true);
        it_1.setFinal(true);
        final Procedure1<ITreeAppendable> _function_4 = (ITreeAppendable it_2) -> {
          String _bundleName = this._checkGeneratorExtensions.bundleName(catalog);
          String _plus = ("\"" + _bundleName);
          String _plus_1 = (_plus + "\"");
          it_2.append(_plus_1);
        };
        this._jvmTypesBuilder.setInitializer(it_1, _function_4);
      };
      JvmField _field = this._jvmTypesBuilder.toField(catalog, "RUNTIME_NODE_NAME", _typeRef, _function_3);
      this._jvmTypesBuilder.<JvmField>operator_add(_members, _field);
      EList<JvmMember> _members_1 = it.getMembers();
      Iterable<JvmMember> _createFormalParameterFields = this.createFormalParameterFields(catalog);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _createFormalParameterFields);
      EList<JvmMember> _members_2 = it.getMembers();
      Iterable<JvmMember> _createPreferenceInitializerMethods = this.createPreferenceInitializerMethods(catalog);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _createPreferenceInitializerMethods);
    };
    acceptor.<JvmGenericType>accept(_class_1, _function_2);
  }
  
  private void setCheckComposition(final CheckCatalog catalog, final JvmGenericType jvmType) {
    final CheckCatalog included = catalog.getIncludedCatalogs();
    boolean _or = false;
    boolean _equals = Objects.equal(included, null);
    if (_equals) {
      _or = true;
    } else {
      boolean _eIsProxy = included.eIsProxy();
      _or = _eIsProxy;
    }
    if (_or) {
      return;
    }
    final JvmAnnotationReference composedCheck = this.typesFactory.createJvmAnnotationReference();
    final JvmTypeReference annoTypeRef = this.checkedTypeRef(catalog, ComposedCheckValidator.class);
    boolean _equals_1 = Objects.equal(annoTypeRef, null);
    if (_equals_1) {
      return;
    }
    final JvmType annotationType = annoTypeRef.getType();
    composedCheck.setAnnotation(((JvmAnnotationType) annotationType));
    final XListLiteral rhs = XbaseFactory.eINSTANCE.createXListLiteral();
    final XTypeLiteral containedType = XbaseFactory.eINSTANCE.createXTypeLiteral();
    String _qualifiedValidatorClassName = this._checkGeneratorNaming.qualifiedValidatorClassName(included);
    final JvmTypeReference typeRef = this.checkedTypeRef(catalog, _qualifiedValidatorClassName);
    boolean _equals_2 = Objects.equal(typeRef, null);
    if (_equals_2) {
      return;
    }
    JvmType _type = typeRef.getType();
    containedType.setType(_type);
    EList<XExpression> _elements = rhs.getElements();
    this._jvmTypesBuilder.<XTypeLiteral>operator_add(_elements, containedType);
    final JvmAnnotationValue annotationValue = this._jvmTypesBuilder.toJvmAnnotationValue(rhs);
    Iterable<JvmFeature> _findAllFeaturesByName = ((JvmDeclaredType) annotationType).findAllFeaturesByName("validators");
    JvmFeature _head = IterableExtensions.<JvmFeature>head(_findAllFeaturesByName);
    annotationValue.setOperation(((JvmOperation) _head));
    EList<JvmAnnotationValue> _values = composedCheck.getValues();
    this._jvmTypesBuilder.<JvmAnnotationValue>operator_add(_values, annotationValue);
    EList<JvmAnnotationReference> _annotations = jvmType.getAnnotations();
    this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, composedCheck);
  }
  
  private Iterable<JvmField> createInjectedField(final CheckCatalog context, final String fieldName, final JvmTypeReference type) {
    boolean _equals = Objects.equal(type, null);
    if (_equals) {
      return Collections.<JvmField>emptyList();
    }
    final JvmField field = this.typesFactory.createJvmField();
    field.setSimpleName(fieldName);
    field.setVisibility(JvmVisibility.PRIVATE);
    JvmTypeReference _cloneWithProxies = this._jvmTypesBuilder.cloneWithProxies(type);
    field.setType(_cloneWithProxies);
    final JvmAnnotationReference injection = this.typesFactory.createJvmAnnotationReference();
    final JvmTypeReference annotationTypeRef = this.checkedTypeRef(context, Inject.class);
    boolean _notEquals = (!Objects.equal(annotationTypeRef, null));
    if (_notEquals) {
      final JvmType annotationType = annotationTypeRef.getType();
      injection.setAnnotation(((JvmAnnotationType) annotationType));
      EList<JvmAnnotationReference> _annotations = field.getAnnotations();
      this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, injection);
    }
    return Collections.<JvmField>singleton(field);
  }
  
  private Iterable<JvmMember> createCheck(final Check chk) {
    EList<FormalParameter> _formalParameters = chk.getFormalParameters();
    boolean _isEmpty = _formalParameters.isEmpty();
    if (_isEmpty) {
      EList<Context> _contexts = chk.getContexts();
      final Function1<Context, JvmMember> _function = (Context ctx) -> {
        JvmOperation _createCheckMethod = this.createCheckMethod(ctx);
        return ((JvmMember) _createCheckMethod);
      };
      return ListExtensions.<Context, JvmMember>map(_contexts, _function);
    } else {
      return this.createCheckWithParameters(chk);
    }
  }
  
  private Iterable<JvmMember> createCheckWithParameters(final Check chk) {
    final List<JvmMember> newMembers = Lists.<JvmMember>newArrayList();
    String _name = chk.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_firstUpper + "Class");
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      EList<JvmTypeReference> _superTypes = it.getSuperTypes();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(Object.class);
      this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
      it.setVisibility(JvmVisibility.PRIVATE);
      EList<JvmMember> _members = it.getMembers();
      EList<FormalParameter> _formalParameters = chk.getFormalParameters();
      final Function1<FormalParameter, Boolean> _function_1 = (FormalParameter f) -> {
        boolean _and = false;
        JvmTypeReference _type = f.getType();
        boolean _notEquals = (!Objects.equal(_type, null));
        if (!_notEquals) {
          _and = false;
        } else {
          String _name_1 = f.getName();
          boolean _notEquals_1 = (!Objects.equal(_name_1, null));
          _and = _notEquals_1;
        }
        return Boolean.valueOf(_and);
      };
      Iterable<FormalParameter> _filter = IterableExtensions.<FormalParameter>filter(_formalParameters, _function_1);
      final Function1<FormalParameter, JvmField> _function_2 = (FormalParameter f) -> {
        String _name_1 = f.getName();
        JvmTypeReference _type = f.getType();
        final Procedure1<JvmField> _function_3 = (JvmField it_1) -> {
          it_1.setFinal(true);
        };
        return this._jvmTypesBuilder.toField(f, _name_1, _type, _function_3);
      };
      Iterable<JvmField> _map = IterableExtensions.<FormalParameter, JvmField>map(_filter, _function_2);
      this._jvmTypesBuilder.<JvmMember>operator_add(_members, _map);
    };
    final JvmGenericType checkClass = this._jvmTypesBuilder.toClass(chk, _plus, _function);
    newMembers.add(checkClass);
    String _name_1 = chk.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_1);
    String _plus_1 = (_firstLower + "Impl");
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(checkClass);
    final Procedure1<JvmField> _function_1 = (JvmField it) -> {
      final Procedure1<ITreeAppendable> _function_2 = (ITreeAppendable it_1) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("new ");
        String _simpleName = checkClass.getSimpleName();
        _builder.append(_simpleName, "");
        _builder.append("()");
        it_1.append(_builder);
      };
      this._jvmTypesBuilder.setInitializer(it, _function_2);
    };
    JvmField _field = this._jvmTypesBuilder.toField(chk, _plus_1, _typeRef, _function_1);
    newMembers.add(_field);
    EList<Context> _contexts = chk.getContexts();
    final Function1<Context, JvmOperation> _function_2 = (Context ctx) -> {
      return this.createCheckCaller(ctx, chk);
    };
    List<JvmOperation> _map = ListExtensions.<Context, JvmOperation>map(_contexts, _function_2);
    Iterable<JvmOperation> _filterNull = IterableExtensions.<JvmOperation>filterNull(_map);
    Iterables.<JvmMember>addAll(newMembers, _filterNull);
    EList<JvmMember> _members = checkClass.getMembers();
    EList<Context> _contexts_1 = chk.getContexts();
    final Function1<Context, JvmOperation> _function_3 = (Context ctx) -> {
      return this.createCheckExecution(ctx);
    };
    List<JvmOperation> _map_1 = ListExtensions.<Context, JvmOperation>map(_contexts_1, _function_3);
    Iterable<JvmOperation> _filterNull_1 = IterableExtensions.<JvmOperation>filterNull(_map_1);
    this._jvmTypesBuilder.<JvmMember>operator_add(_members, _filterNull_1);
    return newMembers;
  }
  
  private JvmOperation createCheckExecution(final Context ctx) {
    JvmOperation _xblockexpression = null;
    {
      boolean _or = false;
      boolean _equals = Objects.equal(ctx, null);
      if (_equals) {
        _or = true;
      } else {
        ContextVariable _contextVariable = ctx.getContextVariable();
        boolean _equals_1 = Objects.equal(_contextVariable, null);
        _or = _equals_1;
      }
      if (_or) {
        return null;
      }
      ContextVariable _contextVariable_1 = ctx.getContextVariable();
      JvmTypeReference _type = _contextVariable_1.getType();
      String _simpleName = null;
      if (_type!=null) {
        _simpleName=_type.getSimpleName();
      }
      String _firstUpper = StringExtensions.toFirstUpper(_simpleName);
      final String functionName = ("run" + _firstUpper);
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        ContextVariable _contextVariable_2 = ctx.getContextVariable();
        String _xifexpression = null;
        ContextVariable _contextVariable_3 = ctx.getContextVariable();
        String _name = _contextVariable_3.getName();
        boolean _equals_2 = Objects.equal(_name, null);
        if (_equals_2) {
          _xifexpression = CheckConstants.IT;
        } else {
          ContextVariable _contextVariable_4 = ctx.getContextVariable();
          _xifexpression = _contextVariable_4.getName();
        }
        ContextVariable _contextVariable_5 = ctx.getContextVariable();
        JvmTypeReference _type_1 = _contextVariable_5.getType();
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(_contextVariable_2, _xifexpression, _type_1);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        XExpression _constraint = ctx.getConstraint();
        this._jvmTypesBuilder.setBody(it, _constraint);
      };
      _xblockexpression = this._jvmTypesBuilder.toMethod(ctx, functionName, _typeRef, _function);
    }
    return _xblockexpression;
  }
  
  private Iterable<JvmAnnotationReference> createCheckAnnotation(final Context ctx) {
    final JvmAnnotationReference check = this.typesFactory.createJvmAnnotationReference();
    final JvmTypeReference annotationTypeRef = this.checkedTypeRef(ctx, org.eclipse.xtext.validation.Check.class);
    boolean _equals = Objects.equal(annotationTypeRef, null);
    if (_equals) {
      return Collections.<JvmAnnotationReference>emptyList();
    }
    final JvmTypeReference checkTypeTypeRef = this.checkedTypeRef(ctx, CheckType.class);
    boolean _equals_1 = Objects.equal(checkTypeTypeRef, null);
    if (_equals_1) {
      return Collections.<JvmAnnotationReference>emptyList();
    }
    final JvmType annotationType = annotationTypeRef.getType();
    check.setAnnotation(((JvmAnnotationType) annotationType));
    final XFeatureCall featureCall = XbaseFactory.eINSTANCE.createXFeatureCall();
    JvmType _type = checkTypeTypeRef.getType();
    featureCall.setFeature(_type);
    featureCall.setTypeLiteral(true);
    final XMemberFeatureCall memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
    memberCall.setMemberCallTarget(featureCall);
    String name = this._checkGeneratorExtensions.checkType(ctx);
    final int i = name.lastIndexOf(".");
    if ((i >= 0)) {
      String _substring = name.substring((i + 1));
      name = _substring;
    }
    JvmType _type_1 = checkTypeTypeRef.getType();
    Iterable<JvmFeature> _findAllFeaturesByName = ((JvmDeclaredType) _type_1).findAllFeaturesByName(name);
    JvmFeature _head = IterableExtensions.<JvmFeature>head(_findAllFeaturesByName);
    memberCall.setFeature(_head);
    Resource _eResource = ctx.eResource();
    EList<EObject> _contents = _eResource.getContents();
    _contents.add(memberCall);
    EList<JvmAnnotationValue> _explicitValues = check.getExplicitValues();
    JvmAnnotationValue _jvmAnnotationValue = this._jvmTypesBuilder.toJvmAnnotationValue(memberCall);
    this._jvmTypesBuilder.<JvmAnnotationValue>operator_add(_explicitValues, _jvmAnnotationValue);
    return Collections.<JvmAnnotationReference>singletonList(check);
  }
  
  private JvmOperation createCheckCaller(final Context ctx, final Check chk) {
    JvmOperation _xblockexpression = null;
    {
      boolean _or = false;
      boolean _equals = Objects.equal(ctx, null);
      if (_equals) {
        _or = true;
      } else {
        ContextVariable _contextVariable = ctx.getContextVariable();
        boolean _equals_1 = Objects.equal(_contextVariable, null);
        _or = _equals_1;
      }
      if (_or) {
        return null;
      }
      String _name = chk.getName();
      String _firstLower = StringExtensions.toFirstLower(_name);
      ContextVariable _contextVariable_1 = ctx.getContextVariable();
      JvmTypeReference _type = _contextVariable_1.getType();
      String _simpleName = null;
      if (_type!=null) {
        _simpleName=_type.getSimpleName();
      }
      final String functionName = (_firstLower + _simpleName);
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        ContextVariable _contextVariable_2 = ctx.getContextVariable();
        ContextVariable _contextVariable_3 = ctx.getContextVariable();
        JvmTypeReference _type_1 = _contextVariable_3.getType();
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(_contextVariable_2, "context", _type_1);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmAnnotationReference> _annotations = it.getAnnotations();
        Iterable<JvmAnnotationReference> _createCheckAnnotation = this.createCheckAnnotation(ctx);
        this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _createCheckAnnotation);
        this._jvmTypesBuilder.setDocumentation(it, (functionName + "."));
        final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
          StringConcatenation _builder = new StringConcatenation();
          String _name_1 = chk.getName();
          String _firstLower_1 = StringExtensions.toFirstLower(_name_1);
          String _plus = (_firstLower_1 + "Impl");
          _builder.append(_plus, "");
          _builder.append(".run");
          ContextVariable _contextVariable_4 = ctx.getContextVariable();
          JvmTypeReference _type_2 = _contextVariable_4.getType();
          String _simpleName_1 = null;
          if (_type_2!=null) {
            _simpleName_1=_type_2.getSimpleName();
          }
          String _firstUpper = StringExtensions.toFirstUpper(_simpleName_1);
          _builder.append(_firstUpper, "");
          _builder.append("(context);");
          it_1.append(_builder);
        };
        this._jvmTypesBuilder.setBody(it, _function_1);
      };
      _xblockexpression = this._jvmTypesBuilder.toMethod(ctx, functionName, _typeRef, _function);
    }
    return _xblockexpression;
  }
  
  private JvmOperation createCheckMethod(final Context ctx) {
    JvmOperation _xblockexpression = null;
    {
      boolean _or = false;
      boolean _equals = Objects.equal(ctx, null);
      if (_equals) {
        _or = true;
      } else {
        ContextVariable _contextVariable = ctx.getContextVariable();
        boolean _equals_1 = Objects.equal(_contextVariable, null);
        _or = _equals_1;
      }
      if (_or) {
        return null;
      }
      String _switchResult = null;
      EObject _eContainer = ctx.eContainer();
      final EObject container = _eContainer;
      boolean _matched = false;
      if (!_matched) {
        if (container instanceof Check) {
          _matched=true;
          _switchResult = ((Check)container).getName();
        }
      }
      if (!_matched) {
        if (container instanceof Implementation) {
          _matched=true;
          _switchResult = ((Implementation)container).getName();
        }
      }
      String _firstLower = StringExtensions.toFirstLower(_switchResult);
      ContextVariable _contextVariable_1 = ctx.getContextVariable();
      JvmTypeReference _type = _contextVariable_1.getType();
      String _simpleName = null;
      if (_type!=null) {
        _simpleName=_type.getSimpleName();
      }
      final String functionName = (_firstLower + _simpleName);
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        ContextVariable _contextVariable_2 = ctx.getContextVariable();
        String _xifexpression = null;
        ContextVariable _contextVariable_3 = ctx.getContextVariable();
        String _name = _contextVariable_3.getName();
        boolean _equals_2 = Objects.equal(_name, null);
        if (_equals_2) {
          _xifexpression = CheckConstants.IT;
        } else {
          ContextVariable _contextVariable_4 = ctx.getContextVariable();
          _xifexpression = _contextVariable_4.getName();
        }
        ContextVariable _contextVariable_5 = ctx.getContextVariable();
        JvmTypeReference _type_1 = _contextVariable_5.getType();
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(_contextVariable_2, _xifexpression, _type_1);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmAnnotationReference> _annotations = it.getAnnotations();
        Iterable<JvmAnnotationReference> _createCheckAnnotation = this.createCheckAnnotation(ctx);
        this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _createCheckAnnotation);
        this._jvmTypesBuilder.setDocumentation(it, (functionName + "."));
        XExpression _constraint = ctx.getConstraint();
        this._jvmTypesBuilder.setBody(it, _constraint);
      };
      _xblockexpression = this._jvmTypesBuilder.toMethod(ctx, functionName, _typeRef, _function);
    }
    return _xblockexpression;
  }
  
  private Iterable<JvmMember> createIssue(final CheckCatalog catalog, final Check check) {
    final List<JvmMember> members = Lists.<JvmMember>newArrayList();
    EList<FormalParameter> _formalParameters = check.getFormalParameters();
    for (final FormalParameter parameter : _formalParameters) {
      {
        final JvmTypeReference returnType = parameter.getType();
        boolean _and = false;
        boolean _notEquals = (!Objects.equal(returnType, null));
        if (!_notEquals) {
          _and = false;
        } else {
          boolean _eIsProxy = returnType.eIsProxy();
          boolean _not = (!_eIsProxy);
          _and = _not;
        }
        if (_and) {
          final String returnName = returnType.getQualifiedName();
          String _switchResult = null;
          switch (returnName) {
            case "java.lang.Boolean":
              _switchResult = "getBoolean";
              break;
            case "boolean":
              _switchResult = "getBoolean";
              break;
            case "java.lang.Integer":
              _switchResult = "getInt";
              break;
            case "int":
              _switchResult = "getInt";
              break;
            case "java.util.List<java.lang.String>":
              _switchResult = "getStrings";
              break;
            case "java.util.List<java.lang.Boolean>":
              _switchResult = "getBooleans";
              break;
            case "java.util.List<java.lang.Integer>":
              _switchResult = "getIntegers";
              break;
            default:
              _switchResult = "getString";
              break;
          }
          final String operation = _switchResult;
          final String parameterKey = CheckPropertiesGenerator.parameterSeverityKey(parameter);
          String defaultName = "null";
          XExpression _right = parameter.getRight();
          boolean _notEquals_1 = (!Objects.equal(_right, null));
          if (_notEquals_1) {
            String _formalParameterGetterName = this._checkGeneratorNaming.formalParameterGetterName(parameter);
            String _splitCamelCase = this._checkGeneratorExtensions.splitCamelCase(_formalParameterGetterName);
            String _upperCase = _splitCamelCase.toUpperCase();
            String _plus = (_upperCase + "_DEFAULT");
            defaultName = _plus;
          }
          String _preferenceInitializerClassName = this._checkGeneratorNaming.preferenceInitializerClassName(catalog);
          String _plus_1 = (_preferenceInitializerClassName + ".");
          final String javaDefaultValue = (_plus_1 + defaultName);
          String _formalParameterGetterName_1 = this._checkGeneratorNaming.formalParameterGetterName(parameter);
          final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Gets the run-time value of formal parameter <em>");
            String _name = parameter.getName();
            _builder.append(_name, "");
            _builder.append("</em>. The value");
            _builder.newLineIfNotEmpty();
            _builder.append("returned is either the default as defined in the check definition, or the");
            _builder.newLine();
            _builder.append("configured value, if existing.");
            _builder.newLine();
            _builder.newLine();
            _builder.append("@param context");
            _builder.newLine();
            _builder.append("           ");
            _builder.append("the context object used to determine the current project in");
            _builder.newLine();
            _builder.append("           ");
            _builder.append("order to check if a configured value exists in a project scope");
            _builder.newLine();
            _builder.append("@return the run-time value of <em>");
            String _name_1 = parameter.getName();
            _builder.append(_name_1, "");
            _builder.append("</em>");
            this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
            final JvmTypeReference eObjectTypeRef = this.checkedTypeRef(parameter, EObject.class);
            boolean _notEquals_2 = (!Objects.equal(eObjectTypeRef, null));
            if (_notEquals_2) {
              EList<JvmFormalParameter> _parameters = it.getParameters();
              JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(parameter, "context", eObjectTypeRef);
              this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
            }
            final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append("return checkConfigurationStoreService.getCheckConfigurationStore(context).");
              _builder_1.append(operation, "");
              _builder_1.append("(\"");
              _builder_1.append(parameterKey, "");
              _builder_1.append("\", ");
              _builder_1.append(javaDefaultValue, "");
              _builder_1.append(");");
              it_1.append(_builder_1);
            };
            this._jvmTypesBuilder.setBody(it, _function_1);
          };
          JvmOperation _method = this._jvmTypesBuilder.toMethod(parameter, _formalParameterGetterName_1, returnType, _function);
          members.add(_method);
        }
      }
    }
    String _name = check.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = ("get" + _firstUpper);
    String _plus_1 = (_plus + "Message");
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(String.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Gets the message associated with a violation of this check.");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@param bindings");
      _builder.newLine();
      _builder.append("          ");
      _builder.append("the message bindings");
      _builder.newLine();
      _builder.append("@return the message associated with a violation of this check");
      this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
      it.setVarArgs(true);
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(Object.class);
      JvmTypeReference _addArrayTypeDimension = this._jvmTypesBuilder.addArrayTypeDimension(_typeRef_1);
      JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(check, "bindings", _addArrayTypeDimension);
      this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("return org.eclipse.osgi.util.NLS.bind(\"");
        String _message = check.getMessage();
        String _convertToJavaString = Strings.convertToJavaString(_message);
        _builder_1.append(_convertToJavaString, "");
        _builder_1.append("\", bindings);");
        it_1.append(_builder_1);
      };
      this._jvmTypesBuilder.setBody(it, _function_1);
    };
    JvmOperation _method = this._jvmTypesBuilder.toMethod(check, _plus_1, _typeRef, _function);
    members.add(_method);
    final JvmTypeReference severityType = this.checkedTypeRef(check, SeverityKind.class);
    boolean _notEquals = (!Objects.equal(severityType, null));
    if (_notEquals) {
      String _name_1 = check.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_2 = ("get" + _firstUpper_1);
      String _plus_3 = (_plus_2 + "SeverityKind");
      final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Gets the {@link SeverityKind severity kind} of check");
        _builder.newLine();
        _builder.append("<em>");
        String _label = check.getLabel();
        _builder.append(_label, "");
        _builder.append("</em>. The severity kind returned is either the");
        _builder.newLineIfNotEmpty();
        _builder.append("default ({@code ");
        com.avaloq.tools.ddk.check.check.SeverityKind _defaultSeverity = check.getDefaultSeverity();
        String _name_2 = _defaultSeverity.name();
        _builder.append(_name_2, "");
        _builder.append("}), as is set in the check definition, or the");
        _builder.newLineIfNotEmpty();
        _builder.append("configured value, if existing.");
        _builder.newLine();
        _builder.newLine();
        _builder.append("@param context");
        _builder.newLine();
        _builder.append("         ");
        _builder.append("the context object used to determine the current project in");
        _builder.newLine();
        _builder.append("         ");
        _builder.append("order to check if a configured value exists in a project scope");
        _builder.newLine();
        _builder.append("@return the severity kind of this check: returns the default (");
        com.avaloq.tools.ddk.check.check.SeverityKind _defaultSeverity_1 = check.getDefaultSeverity();
        String _name_3 = _defaultSeverity_1.name();
        _builder.append(_name_3, "");
        _builder.append(") if");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("no configuration for this check was found, else the configured");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("value looked up in the configuration store");
        this._jvmTypesBuilder.setDocumentation(it, _builder.toString());
        final JvmTypeReference eObjectTypeRef = this.checkedTypeRef(check, EObject.class);
        boolean _notEquals_1 = (!Objects.equal(eObjectTypeRef, null));
        if (_notEquals_1) {
          EList<JvmFormalParameter> _parameters = it.getParameters();
          JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(check, "context", eObjectTypeRef);
          this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        }
        final Procedure1<ITreeAppendable> _function_2 = (ITreeAppendable it_1) -> {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt(\"");
          String _checkSeverityKey = CheckPropertiesGenerator.checkSeverityKey(check);
          _builder_1.append(_checkSeverityKey, "");
          _builder_1.append("\", ");
          com.avaloq.tools.ddk.check.check.SeverityKind _defaultSeverity_2 = check.getDefaultSeverity();
          int _value = _defaultSeverity_2.getValue();
          _builder_1.append(_value, "");
          _builder_1.append(");");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("return SeverityKind.values()[result];");
          it_1.append(_builder_1);
        };
        this._jvmTypesBuilder.setBody(it, _function_2);
      };
      JvmOperation _method_1 = this._jvmTypesBuilder.toMethod(check, _plus_3, severityType, _function_1);
      members.add(_method_1);
    }
    return members;
  }
  
  private Iterable<JvmMember> createFormalParameterFields(final CheckCatalog catalog) {
    EList<Check> _checks = catalog.getChecks();
    EList<Category> _categories = catalog.getCategories();
    final Function1<Category, EList<Check>> _function = (Category cat) -> {
      return cat.getChecks();
    };
    List<EList<Check>> _map = ListExtensions.<Category, EList<Check>>map(_categories, _function);
    Iterable<Check> _flatten = Iterables.<Check>concat(_map);
    final Iterable<Check> allChecks = Iterables.<Check>concat(_checks, _flatten);
    final List<JvmMember> result = Lists.<JvmMember>newArrayList();
    for (final Check c : allChecks) {
      EList<FormalParameter> _formalParameters = c.getFormalParameters();
      for (final FormalParameter parameter : _formalParameters) {
        boolean _and = false;
        JvmTypeReference _type = parameter.getType();
        boolean _notEquals = (!Objects.equal(_type, null));
        if (!_notEquals) {
          _and = false;
        } else {
          XExpression _right = parameter.getRight();
          boolean _notEquals_1 = (!Objects.equal(_right, null));
          _and = _notEquals_1;
        }
        if (_and) {
          String _formalParameterGetterName = this._checkGeneratorNaming.formalParameterGetterName(parameter);
          String _splitCamelCase = this._checkGeneratorExtensions.splitCamelCase(_formalParameterGetterName);
          String _upperCase = _splitCamelCase.toUpperCase();
          final String defaultName = (_upperCase + "_DEFAULT");
          JvmTypeReference _type_1 = parameter.getType();
          final Procedure1<JvmField> _function_1 = (JvmField it) -> {
            it.setVisibility(JvmVisibility.PUBLIC);
            it.setFinal(true);
            it.setStatic(true);
            XExpression _right_1 = parameter.getRight();
            this._jvmTypesBuilder.setInitializer(it, _right_1);
          };
          JvmField _field = this._jvmTypesBuilder.toField(parameter, defaultName, _type_1, _function_1);
          result.add(_field);
        }
      }
    }
    return result;
  }
  
  private Iterable<JvmMember> createPreferenceInitializerMethods(final CheckCatalog catalog) {
    final JvmTypeReference prefStore = this.checkedTypeRef(catalog, IEclipsePreferences.class);
    final List<JvmMember> result = Lists.<JvmMember>newArrayList();
    boolean _notEquals = (!Objects.equal(prefStore, null));
    if (_notEquals) {
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
        final JvmTypeReference annotationTypeRef = this.checkedTypeRef(catalog, Override.class);
        boolean _notEquals_1 = (!Objects.equal(annotationTypeRef, null));
        if (_notEquals_1) {
          final JvmType annotationType = annotationTypeRef.getType();
          final JvmAnnotationReference overrideAnnotation = this.typesFactory.createJvmAnnotationReference();
          overrideAnnotation.setAnnotation(((JvmAnnotationType) annotationType));
          EList<JvmAnnotationReference> _annotations = it.getAnnotations();
          this._jvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, overrideAnnotation);
        }
        it.setVisibility(JvmVisibility.PUBLIC);
        final Procedure1<ITreeAppendable> _function_1 = (ITreeAppendable it_1) -> {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);");
          _builder.newLine();
          _builder.newLine();
          _builder.append("initializeSeverities(preferences);");
          _builder.newLine();
          _builder.append("initializeFormalParameters(preferences);");
          it_1.append(_builder);
        };
        this._jvmTypesBuilder.setBody(it, _function_1);
      };
      JvmOperation _method = this._jvmTypesBuilder.toMethod(catalog, "initializeDefaultPreferences", _typeRef, _function);
      result.add(_method);
      EList<Check> _checks = catalog.getChecks();
      EList<Category> _categories = catalog.getCategories();
      final Function1<Category, EList<Check>> _function_1 = (Category cat) -> {
        return cat.getChecks();
      };
      List<EList<Check>> _map = ListExtensions.<Category, EList<Check>>map(_categories, _function_1);
      Iterable<Check> _flatten = Iterables.<Check>concat(_map);
      final Iterable<Check> allChecks = Iterables.<Check>concat(_checks, _flatten);
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        EList<JvmFormalParameter> _parameters = it.getParameters();
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(catalog, "preferences", prefStore);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        final Procedure1<ITreeAppendable> _function_3 = (ITreeAppendable it_1) -> {
          StringConcatenation _builder = new StringConcatenation();
          {
            for(final Check c : allChecks) {
              _builder.newLineIfNotEmpty();
              _builder.append("preferences.putInt(\"");
              String _checkSeverityKey = CheckPropertiesGenerator.checkSeverityKey(c);
              _builder.append(_checkSeverityKey, "");
              _builder.append("\", ");
              com.avaloq.tools.ddk.check.check.SeverityKind _defaultSeverity = c.getDefaultSeverity();
              int _value = _defaultSeverity.getValue();
              _builder.append(_value, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          }
          it_1.append(_builder);
        };
        this._jvmTypesBuilder.setBody(it, _function_3);
      };
      JvmOperation _method_1 = this._jvmTypesBuilder.toMethod(catalog, "initializeSeverities", _typeRef_1, _function_2);
      result.add(_method_1);
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef("void");
      final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        EList<JvmFormalParameter> _parameters = it.getParameters();
        JvmTypeReference _cloneWithProxies = this._jvmTypesBuilder.cloneWithProxies(prefStore);
        JvmFormalParameter _parameter = this._jvmTypesBuilder.toParameter(catalog, "preferences", _cloneWithProxies);
        this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        final Procedure1<ITreeAppendable> _function_4 = (ITreeAppendable it_1) -> {
          for (final Check c : allChecks) {
            EList<FormalParameter> _formalParameters = c.getFormalParameters();
            for (final FormalParameter parameter : _formalParameters) {
              XExpression _right = parameter.getRight();
              boolean _notEquals_1 = (!Objects.equal(_right, null));
              if (_notEquals_1) {
                final String key = CheckPropertiesGenerator.parameterSeverityKey(parameter);
                String _formalParameterGetterName = this._checkGeneratorNaming.formalParameterGetterName(parameter);
                String _splitCamelCase = this._checkGeneratorExtensions.splitCamelCase(_formalParameterGetterName);
                String _upperCase = _splitCamelCase.toUpperCase();
                final String defaultFieldName = (_upperCase + "_DEFAULT");
                final JvmTypeReference jvmType = parameter.getType();
                final String typeName = jvmType.getQualifiedName();
                boolean _and = false;
                boolean _notEquals_2 = (!Objects.equal(typeName, null));
                if (!_notEquals_2) {
                  _and = false;
                } else {
                  boolean _startsWith = typeName.startsWith("java.util.List<");
                  _and = _startsWith;
                }
                if (_and) {
                  final EList<JvmTypeReference> args = ((JvmParameterizedTypeReference) jvmType).getArguments();
                  boolean _and_1 = false;
                  boolean _notEquals_3 = (!Objects.equal(args, null));
                  if (!_notEquals_3) {
                    _and_1 = false;
                  } else {
                    int _size = args.size();
                    boolean _equals = (_size == 1);
                    _and_1 = _equals;
                  }
                  if (_and_1) {
                    JvmTypeReference _head = IterableExtensions.<JvmTypeReference>head(args);
                    final String baseTypeName = _head.getSimpleName();
                    StringConcatenation _builder = new StringConcatenation();
                    _builder.append("preferences.put(\"");
                    _builder.append(key, "");
                    _builder.append("\", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshal");
                    _builder.append(baseTypeName, "");
                    _builder.append("s(");
                    _builder.append(defaultFieldName, "");
                    _builder.append("));");
                    _builder.newLineIfNotEmpty();
                    it_1.append(_builder);
                  } else {
                    StringConcatenation _builder_1 = new StringConcatenation();
                    _builder_1.append("// Found ");
                    _builder_1.append(key, "");
                    _builder_1.append(" with ");
                    _builder_1.append(typeName, "");
                    _builder_1.newLineIfNotEmpty();
                    it_1.append(_builder_1);
                  }
                } else {
                  String _switchResult = null;
                  switch (typeName) {
                    case "java.lang.Boolean":
                      _switchResult = "putBoolean";
                      break;
                    case "boolean":
                      _switchResult = "putBoolean";
                      break;
                    case "java.lang.Integer":
                      _switchResult = "putInt";
                      break;
                    case "int":
                      _switchResult = "putInt";
                      break;
                    default:
                      _switchResult = "put";
                      break;
                  }
                  final String operation = _switchResult;
                  StringConcatenation _builder_2 = new StringConcatenation();
                  _builder_2.append("preferences.");
                  _builder_2.append(operation, "");
                  _builder_2.append("(\"");
                  _builder_2.append(key, "");
                  _builder_2.append("\", ");
                  _builder_2.append(defaultFieldName, "");
                  _builder_2.append(");");
                  _builder_2.newLineIfNotEmpty();
                  it_1.append(_builder_2);
                }
              }
            }
          }
        };
        this._jvmTypesBuilder.setBody(it, _function_4);
      };
      JvmOperation _method_2 = this._jvmTypesBuilder.toMethod(catalog, "initializeFormalParameters", _typeRef_2, _function_3);
      result.add(_method_2);
    }
    return result;
  }
  
  private boolean createError(final String message, final EObject context, final EStructuralFeature feature) {
    boolean _xblockexpression = false;
    {
      final Resource rsc = context.eResource();
      boolean _xifexpression = false;
      boolean _notEquals = (!Objects.equal(rsc, null));
      if (_notEquals) {
        boolean _xblockexpression_1 = false;
        {
          EStructuralFeature f = feature;
          boolean _equals = Objects.equal(f, null);
          if (_equals) {
            EStructuralFeature _identifierFeature = this.locationInFileProvider.getIdentifierFeature(context);
            f = _identifierFeature;
          }
          EList<Resource.Diagnostic> _errors = rsc.getErrors();
          EObjectDiagnosticImpl _eObjectDiagnosticImpl = new EObjectDiagnosticImpl(Severity.ERROR, IssueCodes.INFERRER_ERROR, ("Check compiler: " + message), context, f, (-1), null);
          _xblockexpression_1 = this._jvmTypesBuilder.<EObjectDiagnosticImpl>operator_add(_errors, _eObjectDiagnosticImpl);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  private boolean createTypeNotFoundError(final String name, final EObject context) {
    return this.createError((("Type " + name) + " not found; check project setup (missing required bundle?)"), context, null);
  }
  
  private JvmTypeReference checkedTypeRef(final EObject context, final Class<?> clazz) {
    boolean _equals = Objects.equal(clazz, null);
    if (_equals) {
      this.createTypeNotFoundError("<unknown>", context);
      return null;
    }
    final JvmTypeReference result = this._typeReferenceBuilder.typeRef(clazz);
    boolean _or = false;
    boolean _equals_1 = Objects.equal(result, null);
    if (_equals_1) {
      _or = true;
    } else {
      JvmType _type = result.getType();
      boolean _equals_2 = Objects.equal(_type, null);
      _or = _equals_2;
    }
    if (_or) {
      String _name = clazz.getName();
      this.createTypeNotFoundError(_name, context);
      return null;
    }
    return result;
  }
  
  private JvmTypeReference checkedTypeRef(final EObject context, final String className) {
    final JvmTypeReference result = this._typeReferenceBuilder.typeRef(className);
    boolean _or = false;
    boolean _equals = Objects.equal(result, null);
    if (_equals) {
      _or = true;
    } else {
      JvmType _type = result.getType();
      boolean _equals_1 = Objects.equal(_type, null);
      _or = _equals_1;
    }
    if (_or) {
      this.createTypeNotFoundError(className, context);
      return null;
    }
    return result;
  }
  
  public void infer(final EObject catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    if (catalog instanceof CheckCatalog) {
      _infer((CheckCatalog)catalog, acceptor, preIndexingPhase);
      return;
    } else if (catalog != null) {
      _infer(catalog, acceptor, preIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(catalog, acceptor, preIndexingPhase).toString());
    }
  }
}
