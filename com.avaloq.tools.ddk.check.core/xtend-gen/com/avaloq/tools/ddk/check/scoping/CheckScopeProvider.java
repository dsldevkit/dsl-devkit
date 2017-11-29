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
package com.avaloq.tools.ddk.check.scoping;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.naming.CheckDeclarativeQualifiedNameProvider;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.MapBasedScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsBatchScopeProvider;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.typesystem.IBatchTypeResolver;
import org.eclipse.xtext.xbase.typesystem.IResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;

@SuppressWarnings("all")
public class CheckScopeProvider extends XbaseWithAnnotationsBatchScopeProvider {
  @Inject
  private CheckDeclarativeQualifiedNameProvider checkQualifiedNameProvider;
  
  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;
  
  @Inject
  private IBatchTypeResolver typeResolver;
  
  @Inject
  private IGlobalScopeProvider globalScopeProvider;
  
  @Inject
  private ResourceDescriptionsProvider descriptionsProvider;
  
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    IScope _xblockexpression = null;
    {
      final IScope res = this.scope(context, reference);
      IScope _xifexpression = null;
      boolean _notEquals = (!Objects.equal(res, null));
      if (_notEquals) {
        _xifexpression = res;
      } else {
        _xifexpression = super.getScope(context, reference);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected IScope _scope(final XIssueExpression context, final EReference reference) {
    boolean _equals = Objects.equal(reference, CheckPackage.Literals.XISSUE_EXPRESSION__MARKER_FEATURE);
    if (_equals) {
      JvmTypeReference _xifexpression = null;
      XExpression _markerObject = context.getMarkerObject();
      boolean _notEquals = (!Objects.equal(_markerObject, null));
      if (_notEquals) {
        XExpression _markerObject_1 = context.getMarkerObject();
        IResolvedTypes _resolveTypes = this.typeResolver.resolveTypes(_markerObject_1);
        XExpression _markerObject_2 = context.getMarkerObject();
        LightweightTypeReference _actualType = _resolveTypes.getActualType(_markerObject_2);
        _xifexpression = _actualType.toTypeReference();
      } else {
        Context _containerOfType = EcoreUtil2.<Context>getContainerOfType(context, Context.class);
        ContextVariable _contextVariable = _containerOfType.getContextVariable();
        _xifexpression = _contextVariable.getType();
      }
      JvmTypeReference jvmTypeRef = _xifexpression;
      boolean _notEquals_1 = (!Objects.equal(jvmTypeRef, null));
      if (_notEquals_1) {
        JvmType _type = jvmTypeRef.getType();
        final EClass eClass = this.classForJvmType(context, _type);
        boolean _notEquals_2 = (!Objects.equal(eClass, null));
        if (_notEquals_2) {
          EList<EStructuralFeature> features = eClass.getEAllStructuralFeatures();
          final Function<EStructuralFeature, IEObjectDescription> _function = (EStructuralFeature f) -> {
            String _name = f.getName();
            QualifiedName _create = QualifiedName.create(_name);
            return EObjectDescription.create(_create, f);
          };
          final Collection<IEObjectDescription> descriptions = Collections2.<EStructuralFeature, IEObjectDescription>transform(features, _function);
          return MapBasedScope.createScope(IScope.NULLSCOPE, descriptions);
        } else {
          return IScope.NULLSCOPE;
        }
      } else {
        return IScope.NULLSCOPE;
      }
    } else {
      boolean _equals_1 = Objects.equal(reference, CheckPackage.Literals.XISSUE_EXPRESSION__CHECK);
      if (_equals_1) {
        final CheckCatalog catalog = EcoreUtil2.<CheckCatalog>getContainerOfType(context, CheckCatalog.class);
        EList<Check> _allChecks = catalog.getAllChecks();
        final Function1<Check, Boolean> _function_1 = (Check c) -> {
          String _name = c.getName();
          return Boolean.valueOf((!Objects.equal(_name, null)));
        };
        Iterable<Check> _filter = IterableExtensions.<Check>filter(_allChecks, _function_1);
        final List<Check> checks = IterableExtensions.<Check>toList(_filter);
        final Function<Check, IEObjectDescription> _function_2 = (Check c) -> {
          String _name = c.getName();
          QualifiedName _create = QualifiedName.create(_name);
          return EObjectDescription.create(_create, c);
        };
        final Collection<IEObjectDescription> descriptions_1 = Collections2.<Check, IEObjectDescription>transform(checks, _function_2);
        IScope _xifexpression_1 = null;
        if (((!Objects.equal(catalog.getIncludedCatalogs(), null)) && (!catalog.getIncludedCatalogs().eIsProxy()))) {
          CheckCatalog _includedCatalogs = catalog.getIncludedCatalogs();
          _xifexpression_1 = this.getScope(_includedCatalogs, reference);
        } else {
          _xifexpression_1 = IScope.NULLSCOPE;
        }
        final IScope parentScope = _xifexpression_1;
        Predicate<IEObjectDescription> _notNull = Predicates.<IEObjectDescription>notNull();
        Iterable<IEObjectDescription> _filter_1 = Iterables.<IEObjectDescription>filter(descriptions_1, _notNull);
        return MapBasedScope.createScope(parentScope, _filter_1);
      }
    }
    return null;
  }
  
  protected IScope _scope(final CheckCatalog context, final EReference reference) {
    boolean _equals = Objects.equal(reference, CheckPackage.Literals.CHECK_CATALOG__GRAMMAR);
    if (_equals) {
      final IResourceServiceProvider.Registry reg = IResourceServiceProvider.Registry.INSTANCE;
      Map<String, Object> _extensionToFactoryMap = reg.getExtensionToFactoryMap();
      Set<String> _keySet = _extensionToFactoryMap.keySet();
      final Function<String, IEObjectDescription> _function = (String e) -> {
        Object _xblockexpression = null;
        {
          final URI dummyUri = URI.createURI(("foo:/foo." + e));
          Object _xtrycatchfinallyexpression = null;
          try {
            IResourceServiceProvider _resourceServiceProvider = reg.getResourceServiceProvider(dummyUri);
            IGrammarAccess _get = _resourceServiceProvider.<IGrammarAccess>get(IGrammarAccess.class);
            final Grammar g = _get.getGrammar();
            String _name = g.getName();
            QualifiedName _qualifiedName = this.qualifiedNameConverter.toQualifiedName(_name);
            return EObjectDescription.create(_qualifiedName, g);
          } catch (final Throwable _t) {
            if (_t instanceof Exception) {
              final Exception ex = (Exception)_t;
              _xtrycatchfinallyexpression = null;
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
          _xblockexpression = _xtrycatchfinallyexpression;
        }
        return ((IEObjectDescription)_xblockexpression);
      };
      final Collection<IEObjectDescription> descriptions = Collections2.<String, IEObjectDescription>transform(_keySet, _function);
      Predicate<IEObjectDescription> _notNull = Predicates.<IEObjectDescription>notNull();
      Iterable<IEObjectDescription> _filter = Iterables.<IEObjectDescription>filter(descriptions, _notNull);
      final IScope parentScope = MapBasedScope.createScope(IScope.NULLSCOPE, _filter);
      return parentScope;
    } else {
      boolean _equals_1 = Objects.equal(reference, CheckPackage.Literals.XISSUE_EXPRESSION__CHECK);
      if (_equals_1) {
        EList<Check> _allChecks = context.getAllChecks();
        final Function1<Check, IEObjectDescription> _function_1 = (Check c) -> {
          QualifiedName _fullyQualifiedName = this.checkQualifiedNameProvider.getFullyQualifiedName(c);
          return EObjectDescription.create(_fullyQualifiedName, c);
        };
        final List<IEObjectDescription> descriptions_1 = ListExtensions.<Check, IEObjectDescription>map(_allChecks, _function_1);
        IScope _scope = super.getScope(context, reference);
        return new SimpleScope(_scope, descriptions_1);
      }
    }
    return null;
  }
  
  protected IScope _scope(final EObject context, final EReference reference) {
    return null;
  }
  
  public EClass classForJvmType(final EObject context, final JvmType jvmType) {
    if (((!Objects.equal(jvmType, null)) && (!jvmType.eIsProxy()))) {
      final String qualifiedName = jvmType.getQualifiedName();
      int _lastIndexOf = qualifiedName.lastIndexOf(".");
      final String qualifiedPackageName = qualifiedName.substring(0, _lastIndexOf);
      int _lastIndexOf_1 = qualifiedPackageName.lastIndexOf(".");
      int _plus = (_lastIndexOf_1 + 1);
      final String packageName = qualifiedPackageName.substring(_plus);
      Resource _eResource = context.eResource();
      final EPackage ePackage = this.getEPackage(_eResource, packageName);
      boolean _notEquals = (!Objects.equal(ePackage, null));
      if (_notEquals) {
        EObject _resolve = EcoreUtil.resolve(ePackage, context);
        String _simpleName = jvmType.getSimpleName();
        final EClassifier eClassifier = ((EPackage) _resolve).getEClassifier(_simpleName);
        if ((eClassifier instanceof EClass)) {
          return ((EClass)eClassifier);
        }
      }
    }
    return null;
  }
  
  public EPackage getEPackage(final Resource context, final String name) {
    ResourceSet _resourceSet = context.getResourceSet();
    final EList<Resource> resources = _resourceSet.getResources();
    for (int i = 0; (i < resources.size()); i++) {
      {
        final Resource resource = resources.get(i);
        EList<EObject> _contents = resource.getContents();
        for (final EObject obj : _contents) {
          if (((obj instanceof EPackage) && Objects.equal(((EPackage) obj).getName(), name))) {
            return ((EPackage) obj);
          }
        }
      }
    }
    IScope _scope = this.globalScopeProvider.getScope(context, EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE, null);
    QualifiedName _create = QualifiedName.create(name);
    final IEObjectDescription desc = _scope.getSingleElement(_create);
    boolean _notEquals = (!Objects.equal(desc, null));
    if (_notEquals) {
      EObject _eObjectOrProxy = desc.getEObjectOrProxy();
      return ((EPackage) _eObjectOrProxy);
    }
    IResourceDescriptions _resourceDescriptions = this.descriptionsProvider.getResourceDescriptions(context);
    QualifiedName _create_1 = QualifiedName.create(name);
    Iterable<IEObjectDescription> _exportedObjects = _resourceDescriptions.getExportedObjects(EcorePackage.Literals.EPACKAGE, _create_1, false);
    final Iterator<IEObjectDescription> descs = _exportedObjects.iterator();
    boolean _hasNext = descs.hasNext();
    if (_hasNext) {
      IEObjectDescription _next = descs.next();
      EObject _eObjectOrProxy_1 = _next.getEObjectOrProxy();
      final EObject pkg = EcoreUtil.resolve(_eObjectOrProxy_1, context);
      boolean _eIsProxy = pkg.eIsProxy();
      boolean _not = (!_eIsProxy);
      if (_not) {
        return ((EPackage) pkg);
      }
    }
    Set<String> _keySet = EPackage.Registry.INSTANCE.keySet();
    HashSet<String> _newHashSet = Sets.<String>newHashSet(_keySet);
    for (final String nsUri : _newHashSet) {
      {
        final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsUri);
        String _name = ePackage.getName();
        boolean _equals = Objects.equal(_name, name);
        if (_equals) {
          return ePackage;
        }
      }
    }
    return null;
  }
  
  public IScope scope(final EObject context, final EReference reference) {
    if (context instanceof CheckCatalog) {
      return _scope((CheckCatalog)context, reference);
    } else if (context instanceof XIssueExpression) {
      return _scope((XIssueExpression)context, reference);
    } else if (context != null) {
      return _scope(context, reference);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(context, reference).toString());
    }
  }
}
