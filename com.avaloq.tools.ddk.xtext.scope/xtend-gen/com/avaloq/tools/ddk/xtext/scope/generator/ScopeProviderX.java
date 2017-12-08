/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;
import com.avaloq.tools.ddk.xtext.generator.expression.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.Naming;
import com.avaloq.tools.ddk.xtext.scope.ScopeUtil;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopingGeneratorUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ScopeProviderX {
  @Inject
  @Extension
  private Naming _naming;
  
  @Inject
  @Extension
  private GeneratorUtilX _generatorUtilX;
  
  @Inject
  @Extension
  private CodeGenerationX _codeGenerationX;
  
  @Inject
  @Extension
  private ExpressionExtensionsX _expressionExtensionsX;
  
  /**
   * CODE GENERATION
   */
  public String getScopeProvider(final ScopeModel model) {
    String _name = model.getName();
    String _javaPackage = this._naming.toJavaPackage(_name);
    String _plus = (_javaPackage + ".scoping.");
    String _name_1 = model.getName();
    String _simpleName = this._naming.toSimpleName(_name_1);
    String _plus_1 = (_plus + _simpleName);
    return (_plus_1 + "ScopeProvider");
  }
  
  public String getScopeNameProvider(final ScopeModel model) {
    String _name = model.getName();
    String _javaPackage = this._naming.toJavaPackage(_name);
    String _plus = (_javaPackage + ".scoping.");
    String _name_1 = model.getName();
    String _simpleName = this._naming.toSimpleName(_name_1);
    String _plus_1 = (_plus + _simpleName);
    return (_plus_1 + "ScopeNameProvider");
  }
  
  public String scopeMethodName(final ScopeDefinition it) {
    String _scopeName = this.getScopeName(it);
    String _plus = (_scopeName + "_");
    String _xifexpression = null;
    EClass _targetType = it.getTargetType();
    boolean _notEquals = (!Objects.equal(_targetType, null));
    if (_notEquals) {
      EClass _targetType_1 = it.getTargetType();
      EPackage _ePackage = _targetType_1.getEPackage();
      String _name = _ePackage.getName();
      String _plus_1 = (_name + "_");
      EClass _targetType_2 = it.getTargetType();
      String _name_1 = _targetType_2.getName();
      _xifexpression = (_plus_1 + _name_1);
    } else {
      EClass _contextType = it.getContextType();
      EPackage _ePackage_1 = _contextType.getEPackage();
      String _name_2 = _ePackage_1.getName();
      String _plus_2 = (_name_2 + "_");
      EClass _contextType_1 = it.getContextType();
      String _name_3 = _contextType_1.getName();
      String _plus_3 = (_plus_2 + _name_3);
      String _plus_4 = (_plus_3 + "_");
      EReference _reference = it.getReference();
      String _name_4 = _reference.getName();
      _xifexpression = (_plus_4 + _name_4);
    }
    return (_plus + _xifexpression);
  }
  
  public CompilationContext compilationContext(final ScopeModel it) {
    return ScopingGeneratorUtil.getCompilationContext(it);
  }
  
  public String locatorString(final EObject it) {
    String _location = this._generatorUtilX.location(it);
    String[] _split = _location.split("/");
    String _last = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(_split)));
    return this._codeGenerationX.javaEncode(_last);
  }
  
  public String calledFeature(final FeatureCall it) {
    Identifier _type = it.getType();
    EList<String> _id = _type.getId();
    return IterableExtensions.<String>head(_id);
  }
  
  public EStructuralFeature feature(final FeatureCall it) {
    EClass _scopeType = this.scopeType(it);
    String _calledFeature = this.calledFeature(it);
    return _scopeType.getEStructuralFeature(_calledFeature);
  }
  
  /**
   * SCOPE RULES
   */
  public List<ScopeRule> allScopeRules(final Void it) {
    return CollectionLiterals.<ScopeRule>newArrayList();
  }
  
  public List<ScopeRule> allScopeRules(final ScopeDefinition it) {
    ScopeModel _model = this.getModel(it);
    return this.collectAllScopeRules(_model, it);
  }
  
  private List<ScopeRule> collectAllScopeRules(final ScopeModel it, final ScopeDefinition def) {
    List<ScopeRule> _xblockexpression = null;
    {
      EList<ScopeDefinition> _scopes = it.getScopes();
      final Function1<ScopeDefinition, Boolean> _function = (ScopeDefinition d) -> {
        return Boolean.valueOf(this.isEqual(d, def));
      };
      final Iterable<ScopeDefinition> d = IterableExtensions.<ScopeDefinition>filter(_scopes, _function);
      Iterable<ScopeRule> _xifexpression = null;
      boolean _equals = Objects.equal(d, null);
      if (_equals) {
        _xifexpression = CollectionLiterals.<ScopeRule>newArrayList();
      } else {
        final Function1<ScopeDefinition, EList<ScopeRule>> _function_1 = (ScopeDefinition it_1) -> {
          return it_1.getRules();
        };
        Iterable<EList<ScopeRule>> _map = IterableExtensions.<ScopeDefinition, EList<ScopeRule>>map(d, _function_1);
        _xifexpression = Iterables.<ScopeRule>concat(_map);
      }
      final Iterable<ScopeRule> myScopeRules = _xifexpression;
      List<ScopeRule> _xifexpression_1 = null;
      EList<ScopeModel> _includedScopes = it.getIncludedScopes();
      boolean _isEmpty = _includedScopes.isEmpty();
      if (_isEmpty) {
        _xifexpression_1 = CollectionLiterals.<ScopeRule>newArrayList();
      } else {
        EList<ScopeModel> _includedScopes_1 = it.getIncludedScopes();
        final Function1<ScopeModel, List<ScopeRule>> _function_2 = (ScopeModel it_1) -> {
          return this.collectAllScopeRules(it_1, def);
        };
        List<List<ScopeRule>> _map_1 = ListExtensions.<ScopeModel, List<ScopeRule>>map(_includedScopes_1, _function_2);
        Iterable<ScopeRule> _flatten = Iterables.<ScopeRule>concat(_map_1);
        _xifexpression_1 = IterableExtensions.<ScopeRule>toList(_flatten);
      }
      final List<ScopeRule> result = _xifexpression_1;
      Iterables.<ScopeRule>addAll(result, myScopeRules);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public List<ScopeRule> sortedRules(final Collection<ScopeRule> it) {
    return ScopingGeneratorUtil.sortedRules(it);
  }
  
  public Set<ScopeRule> filterUniqueRules(final List<ScopeRule> it) {
    final Function1<ScopeRule, ScopeRule> _function = (ScopeRule r) -> {
      final Function1<ScopeRule, Boolean> _function_1 = (ScopeRule r2) -> {
        return Boolean.valueOf(this.hasSameContext(r2, r));
      };
      return IterableExtensions.<ScopeRule>findFirst(it, _function_1);
    };
    List<ScopeRule> _map = ListExtensions.<ScopeRule, ScopeRule>map(it, _function);
    return IterableExtensions.<ScopeRule>toSet(_map);
  }
  
  protected boolean _isEqual(final ScopeRule a, final ScopeRule b) {
    return (this.hasSameContext(a, b) && Objects.equal(this._expressionExtensionsX.serialize(a.getContext().getGuard()), this._expressionExtensionsX.serialize(b.getContext().getGuard())));
  }
  
  public boolean hasSameContext(final ScopeRule a, final ScopeRule b) {
    String _ruleSignature = this.ruleSignature(a);
    String _ruleSignature_1 = this.ruleSignature(b);
    return Objects.equal(_ruleSignature, _ruleSignature_1);
  }
  
  private String ruleSignature(final ScopeRule s) {
    return ScopeUtil.getSignature(s);
  }
  
  /**
   * SCOPE DEFINITIONS
   */
  public List<ScopeDefinition> allScopes(final ScopeModel it) {
    List<ScopeDefinition> _xblockexpression = null;
    {
      final EList<ScopeDefinition> myScopes = it.getScopes();
      List<ScopeDefinition> _xifexpression = null;
      EList<ScopeModel> _includedScopes = it.getIncludedScopes();
      boolean _isEmpty = _includedScopes.isEmpty();
      if (_isEmpty) {
        _xifexpression = CollectionLiterals.<ScopeDefinition>newArrayList();
      } else {
        EList<ScopeModel> _includedScopes_1 = it.getIncludedScopes();
        final Function1<ScopeModel, List<ScopeDefinition>> _function = (ScopeModel it_1) -> {
          return this.allScopes(it_1);
        };
        List<List<ScopeDefinition>> _map = ListExtensions.<ScopeModel, List<ScopeDefinition>>map(_includedScopes_1, _function);
        Iterable<ScopeDefinition> _flatten = Iterables.<ScopeDefinition>concat(_map);
        _xifexpression = IterableExtensions.<ScopeDefinition>toList(_flatten);
      }
      final List<ScopeDefinition> result = _xifexpression;
      final Predicate<ScopeDefinition> _function_1 = (ScopeDefinition s) -> {
        return this.hasScope(myScopes, s);
      };
      result.removeIf(_function_1);
      result.addAll(myScopes);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public List<ScopeDefinition> allScopes(final Void it) {
    return CollectionLiterals.<ScopeDefinition>newArrayList();
  }
  
  public String getScopeName(final ScopeDefinition it) {
    String _xifexpression = null;
    String _name = it.getName();
    boolean _equals = Objects.equal(_name, null);
    if (_equals) {
      _xifexpression = "scope";
    } else {
      _xifexpression = it.getName();
    }
    return _xifexpression;
  }
  
  public boolean hasScope(final List<ScopeDefinition> list, final ScopeDefinition scope) {
    boolean _xifexpression = false;
    boolean _isEmpty = list.isEmpty();
    if (_isEmpty) {
      _xifexpression = false;
    } else {
      final Function1<ScopeDefinition, Boolean> _function = (ScopeDefinition s) -> {
        return Boolean.valueOf(this.isEqual(s, scope));
      };
      Iterable<ScopeDefinition> _filter = IterableExtensions.<ScopeDefinition>filter(list, _function);
      boolean _isEmpty_1 = IterableExtensions.isEmpty(_filter);
      _xifexpression = (!_isEmpty_1);
    }
    return _xifexpression;
  }
  
  protected boolean _isEqual(final ScopeDefinition a, final ScopeDefinition b) {
    return ((Objects.equal(this.getScopeName(a), this.getScopeName(b)) && this.isEqual(a.getTargetType(), b.getTargetType())) && this.isEqual(a.getReference(), b.getReference()));
  }
  
  /**
   * SCOPE TYPE
   */
  public EClass scopeType(final ScopeDefinition it) {
    EClass _xifexpression = null;
    EReference _reference = it.getReference();
    boolean _notEquals = (!Objects.equal(_reference, null));
    if (_notEquals) {
      EReference _reference_1 = it.getReference();
      _xifexpression = _reference_1.getEReferenceType();
    } else {
      _xifexpression = it.getTargetType();
    }
    return _xifexpression;
  }
  
  public EClass scopeType(final ScopeRule it) {
    ScopeDefinition _scope = this.getScope(it);
    return this.scopeType(_scope);
  }
  
  public EClass scopeType(final Expression it) {
    EClass _xifexpression = null;
    ScopeDefinition _scope = this.getScope(it);
    boolean _notEquals = (!Objects.equal(_scope, null));
    if (_notEquals) {
      ScopeDefinition _scope_1 = this.getScope(it);
      _xifexpression = this.scopeType(_scope_1);
    } else {
      NamingDefinition _namingDef = this.getNamingDef(it);
      _xifexpression = _namingDef.getType();
    }
    return _xifexpression;
  }
  
  public ENamedElement typeOrRef(final ScopeDefinition it) {
    ENamedElement _xifexpression = null;
    EReference _reference = it.getReference();
    boolean _notEquals = (!Objects.equal(_reference, null));
    if (_notEquals) {
      _xifexpression = it.getReference();
    } else {
      _xifexpression = it.getTargetType();
    }
    return _xifexpression;
  }
  
  public EReference contextRef(final ScopeRule it) {
    ScopeDefinition _scope = this.getScope(it);
    return _scope.getReference();
  }
  
  /**
   * Injections
   */
  public List<Injection> allInjections(final ScopeModel it) {
    List<Injection> _xblockexpression = null;
    {
      final EList<Injection> myInjections = it.getInjections();
      List<Injection> _xifexpression = null;
      EList<ScopeModel> _includedScopes = it.getIncludedScopes();
      boolean _isEmpty = _includedScopes.isEmpty();
      if (_isEmpty) {
        _xifexpression = CollectionLiterals.<Injection>newArrayList();
      } else {
        EList<ScopeModel> _includedScopes_1 = it.getIncludedScopes();
        final Function1<ScopeModel, List<Injection>> _function = (ScopeModel it_1) -> {
          return this.allInjections(it_1);
        };
        List<List<Injection>> _map = ListExtensions.<ScopeModel, List<Injection>>map(_includedScopes_1, _function);
        Iterable<Injection> _flatten = Iterables.<Injection>concat(_map);
        _xifexpression = IterableExtensions.<Injection>toList(_flatten);
      }
      final List<Injection> result = _xifexpression;
      final Predicate<Injection> _function_1 = (Injection i) -> {
        return this.hasInjection(myInjections, i);
      };
      result.removeIf(_function_1);
      result.addAll(myInjections);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public List<Injection> allInjections(final Void it) {
    return CollectionLiterals.<Injection>newArrayList();
  }
  
  public boolean hasInjection(final List<Injection> list, final Injection injection) {
    boolean _xifexpression = false;
    boolean _isEmpty = list.isEmpty();
    if (_isEmpty) {
      _xifexpression = false;
    } else {
      final Function1<Injection, Boolean> _function = (Injection i) -> {
        return Boolean.valueOf(this.isEqual(i, injection));
      };
      Iterable<Injection> _filter = IterableExtensions.<Injection>filter(list, _function);
      boolean _isEmpty_1 = IterableExtensions.isEmpty(_filter);
      _xifexpression = (!_isEmpty_1);
    }
    return _xifexpression;
  }
  
  protected boolean _isEqual(final Injection a, final Injection b) {
    return (Objects.equal(a.getType(), b.getType()) && Objects.equal(a.getName(), b.getName()));
  }
  
  /**
   * SCOPE EXPRESSIONS
   */
  public boolean isCaseInsensitive(final NamedScopeExpression it) {
    return ScopingGeneratorUtil.isCaseInsensitive(it);
  }
  
  /**
   * ECONTAINER
   */
  public ScopeModel getModel(final EObject it) {
    Resource _eResource = it.eResource();
    EList<EObject> _contents = _eResource.getContents();
    EObject _head = IterableExtensions.<EObject>head(_contents);
    return ((ScopeModel) _head);
  }
  
  public ScopeDefinition getScope(final EObject it) {
    return this.<ScopeDefinition>eContainer(it, ScopeDefinition.class);
  }
  
  public NamingDefinition getNamingDef(final EObject it) {
    return this.<NamingDefinition>eContainer(it, NamingDefinition.class);
  }
  
  public <T extends EObject> T eContainer(final EObject it, final Class<T> type) {
    T _xifexpression = null;
    boolean _equals = Objects.equal(it, null);
    if (_equals) {
      return null;
    } else {
      T _xifexpression_1 = null;
      boolean _isInstance = type.isInstance(it);
      if (_isInstance) {
        _xifexpression_1 = ((T) it);
      } else {
        EObject _eContainer = it.eContainer();
        _xifexpression_1 = this.<T>eContainer(_eContainer, type);
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  /**
   * ECORE
   */
  protected boolean _isEqual(final EClass a, final EClass b) {
    return (Objects.equal(a, b) || ((((!Objects.equal(a, null)) && (!Objects.equal(b, null))) && Objects.equal(a.getName(), b.getName())) && Objects.equal(a.getEPackage().getNsURI(), b.getEPackage().getNsURI())));
  }
  
  protected boolean _isEqual(final Void a, final Void b) {
    return true;
  }
  
  protected boolean _isEqual(final EObject a, final Void b) {
    return false;
  }
  
  protected boolean _isEqual(final Void a, final EObject b) {
    return false;
  }
  
  protected boolean _isEqual(final EReference a, final EReference b) {
    return (Objects.equal(a, b) || ((((!Objects.equal(a, null)) && (!Objects.equal(b, null))) && Objects.equal(a.getName(), b.getName())) && this.isEqual(a.getEContainingClass(), b.getEContainingClass())));
  }
  
  public boolean isEqual(final EObject a, final EObject b) {
    if (a instanceof EReference
         && b instanceof EReference) {
      return _isEqual((EReference)a, (EReference)b);
    } else if (a instanceof EClass
         && b instanceof EClass) {
      return _isEqual((EClass)a, (EClass)b);
    } else if (a instanceof Injection
         && b instanceof Injection) {
      return _isEqual((Injection)a, (Injection)b);
    } else if (a instanceof ScopeDefinition
         && b instanceof ScopeDefinition) {
      return _isEqual((ScopeDefinition)a, (ScopeDefinition)b);
    } else if (a instanceof ScopeRule
         && b instanceof ScopeRule) {
      return _isEqual((ScopeRule)a, (ScopeRule)b);
    } else if (a != null
         && b == null) {
      return _isEqual(a, (Void)null);
    } else if (a == null
         && b != null) {
      return _isEqual((Void)null, b);
    } else if (a == null
         && b == null) {
      return _isEqual((Void)null, (Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(a, b).toString());
    }
  }
}
