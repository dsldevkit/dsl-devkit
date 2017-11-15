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
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.generator.expression.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.generator.util.EClassComparator;
import com.avaloq.tools.ddk.xtext.generator.util.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.Naming;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeNameProviderGenerator;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderX;
import com.avaloq.tools.ddk.xtext.scope.scope.DataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ScopeProviderGenerator {
  @Inject
  @Extension
  private CodeGenerationX _codeGenerationX;
  
  @Inject
  @Extension
  private ExpressionExtensionsX _expressionExtensionsX;
  
  @Inject
  @Extension
  private GeneratorUtilX _generatorUtilX;
  
  @Inject
  @Extension
  private Naming _naming;
  
  @Inject
  @Extension
  private ScopeProviderX _scopeProviderX;
  
  private ScopeNameProviderGenerator nameProviderGenerator;
  
  private CompilationContext compilationContext;
  
  @Extension
  private GenModelUtilX genModelUtil;
  
  public CharSequence generate(final ScopeModel it, final ScopeNameProviderGenerator nameProviderGenerator, final CompilationContext compilationContext, final GenModelUtilX genModelUtil) {
    CharSequence _xblockexpression = null;
    {
      this.nameProviderGenerator = nameProviderGenerator;
      this.compilationContext = compilationContext;
      this.genModelUtil = genModelUtil;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      String _scopeProvider = this._scopeProviderX.getScopeProvider(it);
      String _javaPackage = this._naming.toJavaPackage(_scopeProvider);
      _builder.append(_javaPackage, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import org.apache.log4j.Logger;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EClass;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EObject;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EPackage;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EReference;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.resource.Resource;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import org.eclipse.xtext.naming.QualifiedName;");
      _builder.newLine();
      _builder.append("import org.eclipse.xtext.resource.IEObjectDescription;");
      _builder.newLine();
      _builder.append("import org.eclipse.xtext.scoping.IScope;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractPolymorphicScopeProvider;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.IContextSupplier;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.INameFunction;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.google.common.base.Predicate;");
      _builder.newLine();
      _builder.append("import com.google.common.collect.Lists;");
      _builder.newLine();
      {
        if (((!Objects.equal(it, null)) && (!this._scopeProviderX.allInjections(it).isEmpty()))) {
          _builder.append("import com.google.inject.Inject;");
          _builder.newLine();
        }
      }
      _builder.newLine();
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLine();
      _builder.append("public class ");
      String _scopeProvider_1 = this._scopeProviderX.getScopeProvider(it);
      String _simpleName = this._naming.toSimpleName(_scopeProvider_1);
      _builder.append(_simpleName, "");
      _builder.append(" extends AbstractPolymorphicScopeProvider {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("  ");
      _builder.append("/** Class-wide logger. */");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("private static final Logger LOGGER = Logger.getLogger(");
      String _scopeProvider_2 = this._scopeProviderX.getScopeProvider(it);
      String _simpleName_1 = this._naming.toSimpleName(_scopeProvider_2);
      _builder.append(_simpleName_1, "  ");
      _builder.append(".class);");
      _builder.newLineIfNotEmpty();
      {
        boolean _notEquals = (!Objects.equal(it, null));
        if (_notEquals) {
          _builder.newLine();
          {
            List<Injection> _allInjections = this._scopeProviderX.allInjections(it);
            boolean _isEmpty = _allInjections.isEmpty();
            boolean _not = (!_isEmpty);
            if (_not) {
              {
                List<Injection> _allInjections_1 = this._scopeProviderX.allInjections(it);
                for(final Injection i : _allInjections_1) {
                  _builder.append("  ");
                  _builder.append("@Inject");
                  _builder.newLine();
                  _builder.append("  ");
                  _builder.append("private ");
                  String _type = i.getType();
                  _builder.append(_type, "  ");
                  _builder.append(" ");
                  String _name = i.getName();
                  _builder.append(_name, "  ");
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.newLine();
            }
          }
          _builder.append("  ");
          String _name_1 = it.getName();
          String _simpleName_2 = this._naming.toSimpleName(_name_1);
          CharSequence _scopeMethods = this.scopeMethods(it, _simpleName_2);
          _builder.append(_scopeMethods, "  ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence scopeMethods(final ScopeModel it, final String baseName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/** {@inheritDoc} */");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected IScope doGetScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {");
    _builder.newLine();
    _builder.append("  ");
    {
      List<ScopeDefinition> _allScopes = this._scopeProviderX.allScopes(it);
      final Function1<ScopeDefinition, Boolean> _function = (ScopeDefinition s) -> {
        EReference _reference = s.getReference();
        return Boolean.valueOf((!Objects.equal(_reference, null)));
      };
      Iterable<ScopeDefinition> _filter = IterableExtensions.<ScopeDefinition>filter(_allScopes, _function);
      final Function1<ScopeDefinition, String> _function_1 = (ScopeDefinition s) -> {
        return this._scopeProviderX.getScopeName(s);
      };
      Iterable<String> _map = IterableExtensions.<ScopeDefinition, String>map(_filter, _function_1);
      Set<String> _set = IterableExtensions.<String>toSet(_map);
      final Function1<String, String> _function_2 = (String n) -> {
        String _xifexpression = null;
        boolean _equals = Objects.equal(n, "scope");
        if (_equals) {
          _xifexpression = "";
        } else {
          _xifexpression = n;
        }
        return _xifexpression;
      };
      List<String> _sortBy = IterableExtensions.<String, String>sortBy(_set, _function_2);
      boolean _hasElements = false;
      for(final String name : _sortBy) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" else ", "  ");
        }
        _builder.append("if (\"");
        _builder.append(name, "  ");
        _builder.append("\".equals(scopeName)) {");
        _builder.newLineIfNotEmpty();
        {
          List<ScopeDefinition> _allScopes_1 = this._scopeProviderX.allScopes(it);
          final Function1<ScopeDefinition, Boolean> _function_3 = (ScopeDefinition s) -> {
            EReference _reference = s.getReference();
            return Boolean.valueOf((!Objects.equal(_reference, null)));
          };
          Iterable<ScopeDefinition> _filter_1 = IterableExtensions.<ScopeDefinition>filter(_allScopes_1, _function_3);
          final Function1<ScopeDefinition, Boolean> _function_4 = (ScopeDefinition s) -> {
            String _scopeName = this._scopeProviderX.getScopeName(s);
            return Boolean.valueOf(Objects.equal(_scopeName, name));
          };
          Iterable<ScopeDefinition> _filter_2 = IterableExtensions.<ScopeDefinition>filter(_filter_1, _function_4);
          for(final ScopeDefinition scope : _filter_2) {
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("if (reference == ");
            EReference _reference = scope.getReference();
            String _literalIdentifier = this.genModelUtil.literalIdentifier(_reference);
            _builder.append(_literalIdentifier, "    ");
            _builder.append(") return ");
            String _scopeMethodName = this._scopeProviderX.scopeMethodName(scope);
            _builder.append(_scopeMethodName, "    ");
            _builder.append("(context, reference, originalResource);");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("  ");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/** {@inheritDoc} */");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected IScope doGetScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {");
    _builder.newLine();
    _builder.append("  ");
    {
      List<ScopeDefinition> _allScopes_2 = this._scopeProviderX.allScopes(it);
      final Function1<ScopeDefinition, Boolean> _function_5 = (ScopeDefinition s) -> {
        EReference _reference_1 = s.getReference();
        return Boolean.valueOf(Objects.equal(_reference_1, null));
      };
      Iterable<ScopeDefinition> _filter_3 = IterableExtensions.<ScopeDefinition>filter(_allScopes_2, _function_5);
      final Function1<ScopeDefinition, String> _function_6 = (ScopeDefinition s) -> {
        return this._scopeProviderX.getScopeName(s);
      };
      Iterable<String> _map_1 = IterableExtensions.<ScopeDefinition, String>map(_filter_3, _function_6);
      Set<String> _set_1 = IterableExtensions.<String>toSet(_map_1);
      final Function1<String, String> _function_7 = (String n) -> {
        String _xifexpression = null;
        boolean _equals = Objects.equal(n, "scope");
        if (_equals) {
          _xifexpression = "";
        } else {
          _xifexpression = n;
        }
        return _xifexpression;
      };
      List<String> _sortBy_1 = IterableExtensions.<String, String>sortBy(_set_1, _function_7);
      boolean _hasElements_1 = false;
      for(final String name_1 : _sortBy_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(" else ", "  ");
        }
        _builder.append("if (\"");
        _builder.append(name_1, "  ");
        _builder.append("\".equals(scopeName)) {");
        _builder.newLineIfNotEmpty();
        {
          List<ScopeDefinition> _allScopes_3 = this._scopeProviderX.allScopes(it);
          final Function1<ScopeDefinition, Boolean> _function_8 = (ScopeDefinition s) -> {
            EReference _reference_1 = s.getReference();
            return Boolean.valueOf(Objects.equal(_reference_1, null));
          };
          Iterable<ScopeDefinition> _filter_4 = IterableExtensions.<ScopeDefinition>filter(_allScopes_3, _function_8);
          final Function1<ScopeDefinition, Boolean> _function_9 = (ScopeDefinition s) -> {
            String _scopeName = this._scopeProviderX.getScopeName(s);
            return Boolean.valueOf(Objects.equal(_scopeName, name_1));
          };
          Iterable<ScopeDefinition> _filter_5 = IterableExtensions.<ScopeDefinition>filter(_filter_4, _function_9);
          for(final ScopeDefinition scope_1 : _filter_5) {
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("if (type == ");
            EClass _targetType = scope_1.getTargetType();
            String _literalIdentifier_1 = this.genModelUtil.literalIdentifier(_targetType);
            _builder.append(_literalIdentifier_1, "    ");
            _builder.append(") return ");
            String _scopeMethodName_1 = this._scopeProviderX.scopeMethodName(scope_1);
            _builder.append(_scopeMethodName_1, "    ");
            _builder.append("(context, type, originalResource);");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("  ");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/** {@inheritDoc} */");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected boolean doGlobalCache(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("if (context.eContainer() == null) {");
    _builder.newLine();
    _builder.append("    ");
    {
      List<ScopeDefinition> _allScopes_4 = this._scopeProviderX.allScopes(it);
      final Function1<ScopeDefinition, Boolean> _function_10 = (ScopeDefinition s) -> {
        EReference _reference_1 = s.getReference();
        return Boolean.valueOf((!Objects.equal(_reference_1, null)));
      };
      Iterable<ScopeDefinition> _filter_6 = IterableExtensions.<ScopeDefinition>filter(_allScopes_4, _function_10);
      final Function1<ScopeDefinition, Boolean> _function_11 = (ScopeDefinition s) -> {
        List<ScopeRule> _allScopeRules = this._scopeProviderX.allScopeRules(s);
        final Function1<ScopeRule, Boolean> _function_12 = (ScopeRule r) -> {
          ScopeContext _context = r.getContext();
          return Boolean.valueOf(_context.isGlobal());
        };
        Iterable<ScopeRule> _filter_7 = IterableExtensions.<ScopeRule>filter(_allScopeRules, _function_12);
        int _size = IterableExtensions.size(_filter_7);
        return Boolean.valueOf((_size > 0));
      };
      Iterable<ScopeDefinition> _filter_7 = IterableExtensions.<ScopeDefinition>filter(_filter_6, _function_11);
      final Function1<ScopeDefinition, String> _function_12 = (ScopeDefinition s) -> {
        return this._scopeProviderX.getScopeName(s);
      };
      Iterable<String> _map_2 = IterableExtensions.<ScopeDefinition, String>map(_filter_7, _function_12);
      Set<String> _set_2 = IterableExtensions.<String>toSet(_map_2);
      final Function1<String, String> _function_13 = (String n) -> {
        String _xifexpression = null;
        boolean _equals = Objects.equal(n, "scope");
        if (_equals) {
          _xifexpression = "";
        } else {
          _xifexpression = n;
        }
        return _xifexpression;
      };
      List<String> _sortBy_2 = IterableExtensions.<String, String>sortBy(_set_2, _function_13);
      boolean _hasElements_2 = false;
      for(final String name_2 : _sortBy_2) {
        if (!_hasElements_2) {
          _hasElements_2 = true;
        } else {
          _builder.appendImmediate(" else ", "    ");
        }
        _builder.append("if (\"");
        _builder.append(name_2, "    ");
        _builder.append("\".equals(scopeName)) {");
        _builder.newLineIfNotEmpty();
        {
          List<ScopeDefinition> _allScopes_5 = this._scopeProviderX.allScopes(it);
          final Function1<ScopeDefinition, Boolean> _function_14 = (ScopeDefinition s) -> {
            EReference _reference_1 = s.getReference();
            return Boolean.valueOf((!Objects.equal(_reference_1, null)));
          };
          Iterable<ScopeDefinition> _filter_8 = IterableExtensions.<ScopeDefinition>filter(_allScopes_5, _function_14);
          final Function1<ScopeDefinition, Boolean> _function_15 = (ScopeDefinition s) -> {
            String _scopeName = this._scopeProviderX.getScopeName(s);
            return Boolean.valueOf(Objects.equal(_scopeName, name_2));
          };
          Iterable<ScopeDefinition> _filter_9 = IterableExtensions.<ScopeDefinition>filter(_filter_8, _function_15);
          for(final ScopeDefinition scope_2 : _filter_9) {
            _builder.append("    ");
            _builder.append("  ");
            List<ScopeRule> _allScopeRules = this._scopeProviderX.allScopeRules(scope_2);
            final Function1<ScopeRule, Boolean> _function_16 = (ScopeRule r) -> {
              ScopeContext _context = r.getContext();
              return Boolean.valueOf(_context.isGlobal());
            };
            final Iterable<ScopeRule> globalRules = IterableExtensions.<ScopeRule>filter(_allScopeRules, _function_16);
            _builder.newLineIfNotEmpty();
            {
              int _size = IterableExtensions.size(globalRules);
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                _builder.append("    ");
                _builder.append("  ");
                _builder.append("if (reference == ");
                EReference _reference_1 = scope_2.getReference();
                String _literalIdentifier_2 = this.genModelUtil.literalIdentifier(_reference_1);
                _builder.append(_literalIdentifier_2, "      ");
                _builder.append(") return true;");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("    ");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/** {@inheritDoc} */");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected boolean doGlobalCache(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("if (context.eContainer() == null) {");
    _builder.newLine();
    _builder.append("    ");
    {
      List<ScopeDefinition> _allScopes_6 = this._scopeProviderX.allScopes(it);
      final Function1<ScopeDefinition, Boolean> _function_17 = (ScopeDefinition s) -> {
        EReference _reference_2 = s.getReference();
        return Boolean.valueOf(Objects.equal(_reference_2, null));
      };
      Iterable<ScopeDefinition> _filter_10 = IterableExtensions.<ScopeDefinition>filter(_allScopes_6, _function_17);
      final Function1<ScopeDefinition, Boolean> _function_18 = (ScopeDefinition s) -> {
        List<ScopeRule> _allScopeRules_1 = this._scopeProviderX.allScopeRules(s);
        final Function1<ScopeRule, Boolean> _function_19 = (ScopeRule r) -> {
          ScopeContext _context = r.getContext();
          return Boolean.valueOf(_context.isGlobal());
        };
        Iterable<ScopeRule> _filter_11 = IterableExtensions.<ScopeRule>filter(_allScopeRules_1, _function_19);
        int _size_1 = IterableExtensions.size(_filter_11);
        return Boolean.valueOf((_size_1 > 0));
      };
      Iterable<ScopeDefinition> _filter_11 = IterableExtensions.<ScopeDefinition>filter(_filter_10, _function_18);
      final Function1<ScopeDefinition, String> _function_19 = (ScopeDefinition s) -> {
        return this._scopeProviderX.getScopeName(s);
      };
      Iterable<String> _map_3 = IterableExtensions.<ScopeDefinition, String>map(_filter_11, _function_19);
      Set<String> _set_3 = IterableExtensions.<String>toSet(_map_3);
      final Function1<String, String> _function_20 = (String n) -> {
        String _xifexpression = null;
        boolean _equals = Objects.equal(n, "scope");
        if (_equals) {
          _xifexpression = "";
        } else {
          _xifexpression = n;
        }
        return _xifexpression;
      };
      List<String> _sortBy_3 = IterableExtensions.<String, String>sortBy(_set_3, _function_20);
      boolean _hasElements_3 = false;
      for(final String name_3 : _sortBy_3) {
        if (!_hasElements_3) {
          _hasElements_3 = true;
        } else {
          _builder.appendImmediate(" else ", "    ");
        }
        _builder.append("if (\"");
        _builder.append(name_3, "    ");
        _builder.append("\".equals(scopeName)) {");
        _builder.newLineIfNotEmpty();
        {
          List<ScopeDefinition> _allScopes_7 = this._scopeProviderX.allScopes(it);
          final Function1<ScopeDefinition, Boolean> _function_21 = (ScopeDefinition s) -> {
            EReference _reference_2 = s.getReference();
            return Boolean.valueOf(Objects.equal(_reference_2, null));
          };
          Iterable<ScopeDefinition> _filter_12 = IterableExtensions.<ScopeDefinition>filter(_allScopes_7, _function_21);
          final Function1<ScopeDefinition, Boolean> _function_22 = (ScopeDefinition s) -> {
            String _scopeName = this._scopeProviderX.getScopeName(s);
            return Boolean.valueOf(Objects.equal(_scopeName, name_3));
          };
          Iterable<ScopeDefinition> _filter_13 = IterableExtensions.<ScopeDefinition>filter(_filter_12, _function_22);
          for(final ScopeDefinition scope_3 : _filter_13) {
            _builder.append("    ");
            _builder.append("  ");
            List<ScopeRule> _allScopeRules_1 = this._scopeProviderX.allScopeRules(scope_3);
            final Function1<ScopeRule, Boolean> _function_23 = (ScopeRule r) -> {
              ScopeContext _context = r.getContext();
              return Boolean.valueOf(_context.isGlobal());
            };
            final Iterable<ScopeRule> globalRules_1 = IterableExtensions.<ScopeRule>filter(_allScopeRules_1, _function_23);
            _builder.newLineIfNotEmpty();
            {
              int _size_1 = IterableExtensions.size(globalRules_1);
              boolean _greaterThan_1 = (_size_1 > 0);
              if (_greaterThan_1) {
                _builder.append("    ");
                _builder.append("  ");
                _builder.append("if (type == ");
                EClass _targetType_1 = scope_3.getTargetType();
                String _literalIdentifier_3 = this.genModelUtil.literalIdentifier(_targetType_1);
                _builder.append(_literalIdentifier_3, "      ");
                _builder.append(") return true;");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("    ");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      List<ScopeDefinition> _allScopes_8 = this._scopeProviderX.allScopes(it);
      for(final ScopeDefinition scope_4 : _allScopes_8) {
        _builder.append("protected IScope ");
        String _scopeMethodName_2 = this._scopeProviderX.scopeMethodName(scope_4);
        _builder.append(_scopeMethodName_2, "");
        _builder.append("(final EObject context, final ");
        {
          EReference _reference_2 = scope_4.getReference();
          boolean _notEquals = (!Objects.equal(_reference_2, null));
          if (_notEquals) {
            _builder.append("EReference ref");
          } else {
            _builder.append("EClass type");
          }
        }
        _builder.append(", final Resource originalResource) {");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        List<ScopeRule> _allScopeRules_2 = this._scopeProviderX.allScopeRules(scope_4);
        final Function1<ScopeRule, Boolean> _function_24 = (ScopeRule r) -> {
          ScopeContext _context = r.getContext();
          boolean _isGlobal = _context.isGlobal();
          return Boolean.valueOf((!_isGlobal));
        };
        Iterable<ScopeRule> _filter_14 = IterableExtensions.<ScopeRule>filter(_allScopeRules_2, _function_24);
        final List<ScopeRule> localRules = IterableExtensions.<ScopeRule>toList(_filter_14);
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        List<ScopeRule> _allScopeRules_3 = this._scopeProviderX.allScopeRules(scope_4);
        final Function1<ScopeRule, Boolean> _function_25 = (ScopeRule r) -> {
          ScopeContext _context = r.getContext();
          return Boolean.valueOf(_context.isGlobal());
        };
        Iterable<ScopeRule> _filter_15 = IterableExtensions.<ScopeRule>filter(_allScopeRules_3, _function_25);
        final List<ScopeRule> globalRules_2 = IterableExtensions.<ScopeRule>toList(_filter_15);
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        int _size_2 = globalRules_2.size();
        boolean _greaterThan_2 = (_size_2 > 1);
        if (_greaterThan_2) {
          throw new RuntimeException("only one global rule allowed");
        }
        _builder.newLineIfNotEmpty();
        {
          Set<ScopeRule> _filterUniqueRules = this._scopeProviderX.filterUniqueRules(localRules);
          List<ScopeRule> _sortedRules = this._scopeProviderX.sortedRules(_filterUniqueRules);
          for(final ScopeRule r : _sortedRules) {
            _builder.append("  ");
            String _location = this._generatorUtilX.location(r);
            String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
            _builder.append(_javaContributorComment, "  ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("if (");
            {
              ScopeContext _context = r.getContext();
              EClass _contextType = _context.getContextType();
              boolean _isEObjectType = EClassComparator.isEObjectType(_contextType);
              if (_isEObjectType) {
                _builder.append("true");
              } else {
                _builder.append("context instanceof ");
                ScopeContext _context_1 = r.getContext();
                EClass _contextType_1 = _context_1.getContextType();
                String _instanceClassName = this.genModelUtil.instanceClassName(_contextType_1);
                _builder.append(_instanceClassName, "  ");
              }
            }
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("final ");
            ScopeContext _context_2 = r.getContext();
            EClass _contextType_2 = _context_2.getContextType();
            String _instanceClassName_1 = this.genModelUtil.instanceClassName(_contextType_2);
            _builder.append(_instanceClassName_1, "    ");
            _builder.append(" ctx = (");
            ScopeContext _context_3 = r.getContext();
            EClass _contextType_3 = _context_3.getContextType();
            String _instanceClassName_2 = this.genModelUtil.instanceClassName(_contextType_3);
            _builder.append(_instanceClassName_2, "    ");
            _builder.append(") context;");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append(" ");
            final Function1<ScopeRule, Boolean> _function_26 = (ScopeRule r2) -> {
              return Boolean.valueOf(this._scopeProviderX.hasSameContext(r2, r));
            };
            Iterable<ScopeRule> _filter_16 = IterableExtensions.<ScopeRule>filter(localRules, _function_26);
            final List<ScopeRule> rulesForTypeAndContext = IterableExtensions.<ScopeRule>toList(_filter_16);
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            String _xifexpression = null;
            EReference _contextRef = this._scopeProviderX.contextRef(r);
            boolean _notEquals_1 = (!Objects.equal(_contextRef, null));
            if (_notEquals_1) {
              _xifexpression = "ref";
            } else {
              _xifexpression = "type";
            }
            ScopeContext _context_4 = r.getContext();
            EClass _contextType_4 = _context_4.getContextType();
            ScopeContext _context_5 = r.getContext();
            boolean _isGlobal = _context_5.isGlobal();
            CharSequence _scopeRuleBlock = this.scopeRuleBlock(rulesForTypeAndContext, it, _xifexpression, _contextType_4, Boolean.valueOf(_isGlobal));
            _builder.append(_scopeRuleBlock, "    ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
          }
        }
        {
          if (((!localRules.isEmpty()) || (!globalRules_2.isEmpty()))) {
            _builder.newLine();
            _builder.append("  ");
            _builder.append("final EObject eContainer = context.eContainer();");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("if (eContainer != null) {");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("return internalGetScope(");
            {
              boolean _isEmpty = localRules.isEmpty();
              boolean _not = (!_isEmpty);
              if (_not) {
                _builder.append("eContainer");
              } else {
                _builder.append("getRootObject(eContainer)");
              }
            }
            _builder.append(", ");
            {
              EReference _reference_3 = scope_4.getReference();
              boolean _notEquals_2 = (!Objects.equal(_reference_3, null));
              if (_notEquals_2) {
                _builder.append("ref");
              } else {
                _builder.append("type");
              }
            }
            _builder.append(", \"");
            String _scopeName = this._scopeProviderX.getScopeName(scope_4);
            _builder.append(_scopeName, "    ");
            _builder.append("\", originalResource);");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        }
        {
          boolean _isEmpty_1 = globalRules_2.isEmpty();
          boolean _not_1 = (!_isEmpty_1);
          if (_not_1) {
            _builder.append("  ");
            final ScopeRule r_1 = IterableExtensions.<ScopeRule>head(globalRules_2);
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            final List<ScopeRule> rulesForTypeAndContext_1 = Collections.<ScopeRule>unmodifiableList(CollectionLiterals.<ScopeRule>newArrayList(r_1));
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            String _location_1 = this._generatorUtilX.location(r_1);
            String _javaContributorComment_1 = this._generatorUtilX.javaContributorComment(_location_1);
            _builder.append(_javaContributorComment_1, "  ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("if (context.eResource() != null) {");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("final Resource ctx = context.eResource();");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            String _xifexpression_1 = null;
            EReference _contextRef_1 = this._scopeProviderX.contextRef(r_1);
            boolean _notEquals_3 = (!Objects.equal(_contextRef_1, null));
            if (_notEquals_3) {
              _xifexpression_1 = "ref";
            } else {
              _xifexpression_1 = "type";
            }
            ScopeContext _context_6 = r_1.getContext();
            EClass _contextType_5 = _context_6.getContextType();
            ScopeContext _context_7 = r_1.getContext();
            boolean _isGlobal_1 = _context_7.isGlobal();
            CharSequence _scopeRuleBlock_1 = this.scopeRuleBlock(rulesForTypeAndContext_1, it, _xifexpression_1, _contextType_5, Boolean.valueOf(_isGlobal_1));
            _builder.append(_scopeRuleBlock_1, "    ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        }
        _builder.append("  ");
        _builder.append("return null;");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence scopeRuleBlock(final List<ScopeRule> it, final ScopeModel model, final String typeOrRef, final EClass contextType, final Boolean isGlobal) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("IScope scope = IScope.NULLSCOPE;");
    _builder.newLine();
    _builder.append("try {");
    _builder.newLine();
    {
      final Function1<ScopeRule, Boolean> _function = (ScopeRule r) -> {
        ScopeContext _context = r.getContext();
        Expression _guard = _context.getGuard();
        return Boolean.valueOf((!Objects.equal(_guard, null)));
      };
      boolean _exists = IterableExtensions.<ScopeRule>exists(it, _function);
      if (_exists) {
        _builder.append("  ");
        {
          final Function1<ScopeRule, Integer> _function_1 = (ScopeRule r) -> {
            int _xifexpression = (int) 0;
            ScopeContext _context = r.getContext();
            Expression _guard = _context.getGuard();
            boolean _equals = Objects.equal(_guard, null);
            if (_equals) {
              _xifexpression = it.size();
            } else {
              _xifexpression = it.indexOf(r);
            }
            return Integer.valueOf(_xifexpression);
          };
          List<ScopeRule> _sortBy = IterableExtensions.<ScopeRule, Integer>sortBy(it, _function_1);
          boolean _hasElements = false;
          for(final ScopeRule r : _sortBy) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(" else ", "  ");
            }
            {
              ScopeContext _context = r.getContext();
              Expression _guard = _context.getGuard();
              boolean _notEquals = (!Objects.equal(_guard, null));
              if (_notEquals) {
                _builder.append("if (");
                ScopeContext _context_1 = r.getContext();
                Expression _guard_1 = _context_1.getGuard();
                EClass _scopeType = this._scopeProviderX.scopeType(r);
                CompilationContext _clone = this.compilationContext.clone("ctx", _scopeType);
                String _javaExpression = this._codeGenerationX.javaExpression(_guard_1, _clone);
                _builder.append(_javaExpression, "  ");
                _builder.append(") ");
              }
            }
            _builder.append("{");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            {
              int _size = it.size();
              boolean _greaterThan = (_size > 1);
              if (_greaterThan) {
                String _location = this._generatorUtilX.location(r);
                String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
                _builder.append(_javaContributorComment, "    ");
                _builder.newLineIfNotEmpty();
                _builder.append("  ");
                _builder.append("  ");
              }
            }
            {
              EList<ScopeExpression> _exprs = r.getExprs();
              ArrayList<ScopeExpression> _newArrayList = Lists.<ScopeExpression>newArrayList(_exprs);
              List<ScopeExpression> _reverse = ListExtensions.<ScopeExpression>reverse(_newArrayList);
              for(final ScopeExpression e : _reverse) {
                ScopeDefinition _scope = this._scopeProviderX.getScope(r);
                StringBuilder _scopes = this.scopes(e, model, typeOrRef, _scope, isGlobal);
                _builder.append(_scopes, "    ");
              }
            }
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
          }
        }
        {
          final Function1<ScopeRule, Boolean> _function_2 = (ScopeRule r_1) -> {
            ScopeContext _context_2 = r_1.getContext();
            Expression _guard_2 = _context_2.getGuard();
            return Boolean.valueOf(Objects.equal(_guard_2, null));
          };
          boolean _exists_1 = IterableExtensions.<ScopeRule>exists(it, _function_2);
          boolean _not = (!_exists_1);
          if (_not) {
            _builder.append(" else {");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("throw new UnsupportedOperationException(); // continue matching other definitions");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("}");
          }
        }
        _builder.newLineIfNotEmpty();
      } else {
        int _size_1 = it.size();
        boolean _equals = (_size_1 == 1);
        if (_equals) {
          _builder.append("  ");
          {
            ScopeRule _head = IterableExtensions.<ScopeRule>head(it);
            EList<ScopeExpression> _exprs_1 = _head.getExprs();
            ArrayList<ScopeExpression> _newArrayList_1 = Lists.<ScopeExpression>newArrayList(_exprs_1);
            List<ScopeExpression> _reverse_1 = ListExtensions.<ScopeExpression>reverse(_newArrayList_1);
            for(final ScopeExpression e_1 : _reverse_1) {
              ScopeRule _head_1 = IterableExtensions.<ScopeRule>head(it);
              ScopeDefinition _scope_1 = this._scopeProviderX.getScope(_head_1);
              StringBuilder _scopes_1 = this.scopes(e_1, model, typeOrRef, _scope_1, isGlobal);
              _builder.append(_scopes_1, "  ");
            }
          }
          _builder.newLineIfNotEmpty();
        } else {
          _builder.append("  ");
          final Function1<ScopeRule, String> _function_3 = (ScopeRule r_1) -> {
            return this._generatorUtilX.location(r_1);
          };
          List<String> _map = ListExtensions.<ScopeRule, String>map(it, _function_3);
          String _join = this._codeGenerationX.join(", ", _map);
          String _plus = ("scope context not unique for definitions: " + _join);
          this.error(_plus);
          _builder.newLineIfNotEmpty();
        }
      }
    }
    _builder.append("} catch (Exception e) {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("LOGGER.error(\"Error calculating scope for ");
    String _xifexpression = null;
    if ((isGlobal).booleanValue()) {
      _xifexpression = "Resource";
    } else {
      _xifexpression = contextType.getName();
    }
    _builder.append(_xifexpression, "  ");
    _builder.append(" (");
    ScopeRule _get = it.get(0);
    String _locatorString = this._scopeProviderX.locatorString(_get);
    _builder.append(_locatorString, "  ");
    _builder.append(")\", e);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("return scope;");
    _builder.newLine();
    return _builder;
  }
  
  public StringBuilder scopes(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder b = new StringBuilder();
    Expression _prune = it.getPrune();
    boolean _notEquals = (!Objects.equal(_prune, null));
    if (_notEquals) {
      CharSequence _pruning = this.pruning(it, model, scope);
      b.append(_pruning);
    }
    CharSequence _scopeExpression = this.scopeExpression(it, model, typeOrRef, scope, isGlobal);
    b.append(_scopeExpression);
    return b;
  }
  
  protected CharSequence _scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    String _string = it.toString();
    String _plus = ("Xpand called the wrong definition." + _string);
    String _location = this._generatorUtilX.location(it);
    String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
    String _plus_1 = (_plus + _javaContributorComment);
    this.error(_plus_1);
    return null;
  }
  
  protected CharSequence _scopeExpression(final FactoryExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder b = new StringBuilder();
    ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
    ScopeContext _context = _eContainer.getContext();
    EClass _contextType = _context.getContextType();
    final CompilationContext ctx = this.compilationContext.clone("ctx", _contextType);
    StringBuilder _append = b.append("scope = ");
    Expression _expr = it.getExpr();
    String _javaCall = this.javaCall(_expr, ctx);
    StringBuilder _append_1 = _append.append(_javaCall);
    StringBuilder _append_2 = _append_1.append("(scope, ctx, ");
    StringBuilder _append_3 = _append_2.append(typeOrRef);
    _append_3.append(", originalResource");
    if (((it.getExpr() instanceof OperationCall) && (!((OperationCall) it.getExpr()).getParams().isEmpty()))) {
      StringBuilder _append_4 = b.append(", ");
      Expression _expr_1 = it.getExpr();
      EList<Expression> _params = ((OperationCall) _expr_1).getParams();
      final Function1<Expression, String> _function = (Expression it_1) -> {
        return this._codeGenerationX.javaExpression(it_1, ctx);
      };
      List<String> _map = ListExtensions.<Expression, String>map(_params, _function);
      String _join = this._codeGenerationX.join(", ", _map);
      StringBuilder _append_5 = _append_4.append(_join);
      _append_5.append(");");
    }
    return b;
  }
  
  protected String _javaCall(final Expression it, final CompilationContext ctx) {
    String _string = it.toString();
    String _plus = ("cannot handle scope factory " + _string);
    this.error(_plus);
    return null;
  }
  
  protected String _javaCall(final OperationCall it, final CompilationContext ctx) {
    String _xifexpression = null;
    boolean _isJavaExtensionCall = this._codeGenerationX.isJavaExtensionCall(it, ctx);
    if (_isJavaExtensionCall) {
      _xifexpression = this._codeGenerationX.calledJavaMethod(it, ctx);
    } else {
      String _string = it.toString();
      String _plus = ("/* Error: cannot handle scope factory " + _string);
      _xifexpression = (_plus + " */");
    }
    return _xifexpression;
  }
  
  protected CharSequence _scopeExpression(final ScopeDelegation it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Expression _delegate = it.getDelegate();
      boolean _notEquals = (!Objects.equal(_delegate, null));
      if (_notEquals) {
        Expression _delegate_1 = it.getDelegate();
        final String delegateString = this._expressionExtensionsX.serialize(_delegate_1);
        _builder.newLineIfNotEmpty();
        {
          if ((((Objects.equal(delegateString, "this.eContainer()") || Objects.equal(delegateString, "this.eContainer")) || Objects.equal(delegateString, "eContainer()")) || Objects.equal(delegateString, "eContainer"))) {
            _builder.append("scope = newSameScope(\"");
            String _locatorString = this._scopeProviderX.locatorString(it);
            _builder.append(_locatorString, "");
            _builder.append("\", scope, ctx.eContainer()");
          } else {
            boolean _equals = Objects.equal(delegateString, "this");
            if (_equals) {
              _builder.newLineIfNotEmpty();
              _builder.append("scope = newSameScope(\"");
              String _locatorString_1 = this._scopeProviderX.locatorString(it);
              _builder.append(_locatorString_1, "");
              _builder.append("\", scope, ctx");
            } else {
              _builder.newLineIfNotEmpty();
              _builder.append("scope = newDelegateScope(\"");
              String _locatorString_2 = this._scopeProviderX.locatorString(it);
              _builder.append(_locatorString_2, "");
              _builder.append("\", scope, ");
              {
                if ((!(isGlobal).booleanValue())) {
                  _builder.append("() -> IContextSupplier.makeIterable(");
                  Expression _delegate_2 = it.getDelegate();
                  ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
                  ScopeContext _context = _eContainer.getContext();
                  EClass _contextType = _context.getContextType();
                  String _scopedElements = this.scopedElements(_delegate_2, model, _contextType, "ctx");
                  _builder.append(_scopedElements, "");
                  _builder.append(")");
                } else {
                  Expression _delegate_3 = it.getDelegate();
                  ScopeRule _eContainer_1 = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
                  ScopeContext _context_1 = _eContainer_1.getContext();
                  EClass _contextType_1 = _context_1.getContextType();
                  String _scopedElements_1 = this.scopedElements(_delegate_3, model, _contextType_1, "ctx");
                  _builder.append(_scopedElements_1, "");
                }
              }
            }
          }
        }
      } else {
        _builder.newLineIfNotEmpty();
        _builder.append("scope = newExternalDelegateScope(\"");
        String _locatorString_3 = this._scopeProviderX.locatorString(it);
        _builder.append(_locatorString_3, "");
        _builder.append("\", scope, ");
        GlobalScopeExpression _external = it.getExternal();
        CharSequence _query = this.query(_external, model, typeOrRef, scope);
        _builder.append(_query, "");
        _builder.append(".execute(ctx, originalResource)");
      }
    }
    _builder.append(", ");
    {
      if (((!Objects.equal(it.getScope(), null)) && (!Objects.equal(this._scopeProviderX.typeOrRef(it.getScope()), this._scopeProviderX.typeOrRef(this._scopeProviderX.getScope(it)))))) {
        ScopeDefinition _scope = it.getScope();
        ENamedElement _typeOrRef = this._scopeProviderX.typeOrRef(_scope);
        String _literalIdentifier = this.genModelUtil.literalIdentifier(_typeOrRef);
        _builder.append(_literalIdentifier, "");
      } else {
        _builder.append(typeOrRef, "");
      }
    }
    _builder.append(", \"");
    String _xifexpression = null;
    if (((!Objects.equal(it.getScope(), null)) && (!Objects.equal(it.getScope().getName(), null)))) {
      ScopeDefinition _scope_1 = it.getScope();
      _xifexpression = _scope_1.getName();
    } else {
      _xifexpression = "scope";
    }
    _builder.append(_xifexpression, "");
    _builder.append("\", originalResource);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _scopeExpression(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("scope = ");
    CharSequence _scopeExpressionPart = this.scopeExpressionPart(it, model, typeOrRef, scope);
    _builder.append(_scopeExpressionPart, "");
    CharSequence _scopeExpressionNaming = this.scopeExpressionNaming(it, model, typeOrRef, scope);
    _builder.append(_scopeExpressionNaming, "");
    String _scopeExpressionCasing = this.scopeExpressionCasing(it, model, typeOrRef, scope);
    _builder.append(_scopeExpressionCasing, "");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence _scopeExpression(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Expression _expr = it.getExpr();
      boolean _isEmptyList = this._expressionExtensionsX.isEmptyList(_expr);
      if (_isEmptyList) {
        _builder.append("// Empty scope from ");
        String _location = this._generatorUtilX.location(it);
        _builder.append(_location, "");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("scope = ");
        CharSequence _scopeExpressionPart = this.scopeExpressionPart(it, model, typeOrRef, scope);
        _builder.append(_scopeExpressionPart, "");
        CharSequence _scopeExpressionNaming = this.scopeExpressionNaming(it, model, typeOrRef, scope);
        _builder.append(_scopeExpressionNaming, "");
        String _scopeExpressionCasing = this.scopeExpressionCasing(it, model, typeOrRef, scope);
        _builder.append(_scopeExpressionCasing, "");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence _scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    String _string = it.toString();
    String _plus = ("Xtend called the wrong definition for scopeExpressionPart with this=" + _string);
    String _location = this._generatorUtilX.location(it);
    String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
    String _plus_1 = (_plus + _javaContributorComment);
    this.error(_plus_1);
    return null;
  }
  
  protected CharSequence _scopeExpressionPart(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    String _locatorString = this._scopeProviderX.locatorString(it);
    String _plus = ("newSimpleScope(\"" + _locatorString);
    String _plus_1 = (_plus + "\", scope, ");
    Expression _expr = it.getExpr();
    ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
    ScopeContext _context = _eContainer.getContext();
    EClass _contextType = _context.getContextType();
    String _scopedElements = this.scopedElements(_expr, model, _contextType, "ctx");
    String _plus_2 = (_plus_1 + _scopedElements);
    return (_plus_2 + ", ");
  }
  
  public CharSequence query(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("newQuery(");
    EClass _type = it.getType();
    String _literalIdentifier = this.genModelUtil.literalIdentifier(_type);
    _builder.append(_literalIdentifier, "");
    _builder.append(")");
    EList<DataExpression> _data = it.getData();
    final Iterable<MatchDataExpression> matchData = Iterables.<MatchDataExpression>filter(_data, MatchDataExpression.class);
    {
      Expression _name = it.getName();
      boolean _notEquals = (!Objects.equal(_name, null));
      if (_notEquals) {
        _builder.append(".name(");
        Expression _name_1 = it.getName();
        ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
        ScopeContext _context = _eContainer.getContext();
        EClass _contextType = _context.getContextType();
        String _doExpression = this.doExpression(_name_1, model, "ctx", _contextType);
        _builder.append(_doExpression, "");
        _builder.append(")");
      }
    }
    {
      boolean _isEmpty = IterableExtensions.isEmpty(matchData);
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          for(final MatchDataExpression d : matchData) {
            _builder.append(".data(\"");
            String _key = d.getKey();
            String _javaEncode = this._codeGenerationX.javaEncode(_key);
            _builder.append(_javaEncode, "");
            _builder.append("\", ");
            Expression _value = d.getValue();
            ScopeRule _eContainer_1 = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
            ScopeContext _context_1 = _eContainer_1.getContext();
            EClass _contextType_1 = _context_1.getContextType();
            String _doExpression_1 = this.doExpression(_value, model, "ctx", _contextType_1);
            _builder.append(_doExpression_1, "");
            _builder.append(")");
          }
        }
      }
    }
    {
      if (((!it.getDomains().isEmpty()) && (!Objects.equal(it.getDomains().get(0), "*")))) {
        _builder.append(".domains(");
        {
          EList<String> _domains = it.getDomains();
          boolean _hasElements = false;
          for(final String d_1 : _domains) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.append("\"");
            String _javaEncode_1 = this._codeGenerationX.javaEncode(d_1);
            _builder.append(_javaEncode_1, "");
            _builder.append("\"");
          }
        }
        _builder.append(")");
      }
    }
    return _builder;
  }
  
  protected CharSequence _scopeExpressionPart(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    StringConcatenation _builder = new StringConcatenation();
    EList<DataExpression> _data = it.getData();
    final Iterable<LambdaDataExpression> matchData = Iterables.<LambdaDataExpression>filter(_data, LambdaDataExpression.class);
    _builder.newLineIfNotEmpty();
    {
      if ((IterableExtensions.isEmpty(matchData) && Objects.equal(it.getPrefix(), null))) {
        _builder.append("newContainerScope(");
      } else {
        if ((IterableExtensions.isEmpty(matchData) && (!Objects.equal(it.getPrefix(), null)))) {
          _builder.append("newPrefixedContainerScope(");
        } else {
          _builder.append("newDataMatchScope(");
        }
      }
    }
    _builder.append("\"");
    String _locatorString = this._scopeProviderX.locatorString(it);
    _builder.append(_locatorString, "");
    _builder.append("\", scope, ctx, ");
    CharSequence _query = this.query(it, model, typeOrRef, scope);
    _builder.append(_query, "");
    _builder.append(", originalResource");
    {
      boolean _isEmpty = IterableExtensions.isEmpty(matchData);
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append(", //");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("Lists.<Predicate<IEObjectDescription>> newArrayList(");
        _builder.newLine();
        {
          boolean _hasElements = false;
          for(final LambdaDataExpression d : matchData) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "");
            }
            ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
            ScopeContext _context = _eContainer.getContext();
            EClass _contextType = _context.getContextType();
            String _desc = d.getDesc();
            final CompilationContext cc = this.compilationContext.cloneWithVariable("ctx", _contextType, _desc, "org::eclipse::xtext::resource::IEObjectDescription");
            _builder.newLineIfNotEmpty();
            _builder.append("    ");
            _builder.append("new Predicate<IEObjectDescription>() {");
            _builder.newLine();
            _builder.append("      ");
            _builder.append("public boolean apply (final IEObjectDescription ");
            String _desc_1 = d.getDesc();
            _builder.append(_desc_1, "      ");
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            {
              Expression _value = d.getValue();
              CompilationContext _clone = cc.clone("ctx");
              boolean _isCompilable = this._codeGenerationX.isCompilable(_value, _clone);
              if (_isCompilable) {
                _builder.append("return ");
                Expression _value_1 = d.getValue();
                CompilationContext _clone_1 = cc.clone("ctx");
                String _javaExpression = this._codeGenerationX.javaExpression(_value_1, _clone_1);
                _builder.append(_javaExpression, "");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("EXPRESSION_NOT_SUPPORTED(\"");
                String _serialize = this._expressionExtensionsX.serialize(it);
                _builder.append(_serialize, "");
                _builder.append("\");");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("      ");
            _builder.append("}");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("}");
          }
        }
        _builder.append(")");
      } else {
        Expression _prefix = it.getPrefix();
        boolean _notEquals = (!Objects.equal(_prefix, null));
        if (_notEquals) {
          _builder.append(", ");
          Expression _prefix_1 = it.getPrefix();
          ScopeRule _eContainer_1 = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
          ScopeContext _context_1 = _eContainer_1.getContext();
          EClass _contextType_1 = _context_1.getContextType();
          String _doExpression = this.doExpression(_prefix_1, model, "ctx", _contextType_1);
          _builder.append(_doExpression, "");
          _builder.append(", ");
          boolean _isRecursivePrefix = it.isRecursivePrefix();
          _builder.append(_isRecursivePrefix, "");
        }
      }
    }
    return _builder;
  }
  
  protected CharSequence _scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    String _string = it.toString();
    String _plus = ("Xtend called the wrong definition for scopeExpressionNaming with this=" + _string);
    String _location = this._generatorUtilX.location(it);
    String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
    String _plus_1 = (_plus + _javaContributorComment);
    this.error(_plus_1);
    return null;
  }
  
  protected CharSequence _scopeExpressionNaming(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
    ScopeContext _context = _eContainer.getContext();
    EClass _contextType = _context.getContextType();
    return this.name(it, model, typeOrRef, "ctx", _contextType);
  }
  
  protected CharSequence _scopeExpressionNaming(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    ScopeRule _eContainer = this._scopeProviderX.<ScopeRule>eContainer(it, ScopeRule.class);
    ScopeContext _context = _eContainer.getContext();
    EClass _contextType = _context.getContextType();
    CharSequence _name = this.name(it, model, typeOrRef, "ctx", _contextType);
    return (", " + _name);
  }
  
  public String scopeExpressionCasing(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    boolean _isCaseInsensitive = this._scopeProviderX.isCaseInsensitive(it);
    String _string = Boolean.valueOf(_isCaseInsensitive).toString();
    return (", " + _string);
  }
  
  public CharSequence pruning(final ScopeExpression it, final ScopeModel model, final ScopeDefinition scope) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("scope = newPruningScope(\"");
    String _locatorString = this._scopeProviderX.locatorString(it);
    _builder.append(_locatorString, "");
    _builder.append("\", scope, new Predicate<QualifiedName>() {");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    Expression _prune = it.getPrune();
    EObject _eContainer = it.eContainer();
    ScopeContext _context = ((ScopeRule) _eContainer).getContext();
    EClass _contextType = _context.getContextType();
    CharSequence _prunePredicate = this.prunePredicate(_prune, model, _contextType, "ctx");
    _builder.append(_prunePredicate, "  ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence prunePredicate(final Expression it, final ScopeModel model, final EClass type, final String object) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public boolean apply(QualifiedName name) {");
    _builder.newLine();
    {
      CompilationContext _cloneWithVariable = this.compilationContext.cloneWithVariable(object, type, "name", "org::eclipse::xtext::naming::QualifiedName");
      boolean _isCompilable = this._codeGenerationX.isCompilable(it, _cloneWithVariable);
      if (_isCompilable) {
        _builder.append("  ");
        _builder.append("return ");
        CompilationContext _cloneWithString = this.compilationContext.cloneWithString(object, type, "name");
        String _javaExpression = this._codeGenerationX.javaExpression(it, _cloneWithString);
        _builder.append(_javaExpression, "  ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("  ");
        _builder.append("EXPRESSION_NOT_SUPPORTED(\"");
        String _serialize = this._expressionExtensionsX.serialize(it);
        _builder.append(_serialize, "  ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String scopedElements(final Expression it, final ScopeModel model, final EClass type, final String object) {
    return this.doExpression(it, model, object, type);
  }
  
  public String doExpression(final Expression it, final ScopeModel model, final String object, final EClass type) {
    CompilationContext _clone = this.compilationContext.clone(object, type);
    return this._codeGenerationX.javaExpression(it, _clone);
  }
  
  public CharSequence name(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final String contextName, final EClass contextType) {
    CharSequence _xifexpression = null;
    com.avaloq.tools.ddk.xtext.scope.scope.Naming _naming = it.getNaming();
    boolean _notEquals = (!Objects.equal(_naming, null));
    if (_notEquals) {
      com.avaloq.tools.ddk.xtext.scope.scope.Naming _naming_1 = it.getNaming();
      _xifexpression = this.nameProviderGenerator.nameFunctions(_naming_1, model, contextName, contextType);
    } else {
      _xifexpression = (("getNameFunctions(" + typeOrRef) + ")");
    }
    return _xifexpression;
  }
  
  public void error(final String message) {
    throw new RuntimeException(message);
  }
  
  public CharSequence scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    if (it instanceof SimpleScopeExpression) {
      return _scopeExpression((SimpleScopeExpression)it, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof FactoryExpression) {
      return _scopeExpression((FactoryExpression)it, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof NamedScopeExpression) {
      return _scopeExpression((NamedScopeExpression)it, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof ScopeDelegation) {
      return _scopeExpression((ScopeDelegation)it, model, typeOrRef, scope, isGlobal);
    } else if (it != null) {
      return _scopeExpression(it, model, typeOrRef, scope, isGlobal);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, model, typeOrRef, scope, isGlobal).toString());
    }
  }
  
  public String javaCall(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall) {
      return _javaCall((OperationCall)it, ctx);
    } else if (it != null) {
      return _javaCall(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public CharSequence scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    if (it instanceof GlobalScopeExpression) {
      return _scopeExpressionPart((GlobalScopeExpression)it, model, typeOrRef, scope);
    } else if (it instanceof SimpleScopeExpression) {
      return _scopeExpressionPart((SimpleScopeExpression)it, model, typeOrRef, scope);
    } else if (it != null) {
      return _scopeExpressionPart(it, model, typeOrRef, scope);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, model, typeOrRef, scope).toString());
    }
  }
  
  public CharSequence scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    if (it instanceof GlobalScopeExpression) {
      return _scopeExpressionNaming((GlobalScopeExpression)it, model, typeOrRef, scope);
    } else if (it instanceof SimpleScopeExpression) {
      return _scopeExpressionNaming((SimpleScopeExpression)it, model, typeOrRef, scope);
    } else if (it != null) {
      return _scopeExpressionNaming(it, model, typeOrRef, scope);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, model, typeOrRef, scope).toString());
    }
  }
}
