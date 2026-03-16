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
package com.avaloq.tools.ddk.check.scoping;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.naming.CheckDeclarativeQualifiedNameProvider;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
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
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.MapBasedScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsBatchScopeProvider;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.typesystem.IBatchTypeResolver;

@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
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

  // Use dispatch definitions instead of a switch statement since
  // https://bugs.eclipse.org/bugs/show_bug.cgi?id=368263
  // will otherwise cause the builder to fail during linking.
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    IScope res = scope(context, reference);
    if (res != null) {
      return res;
    } else {
      return super.getScope(context, reference);
    }
  }

  protected IScope _scope(final XIssueExpression context, final EReference reference) {
    if (reference == CheckPackage.Literals.XISSUE_EXPRESSION__MARKER_FEATURE) {
      JvmTypeReference jvmTypeRef;
      if (context.getMarkerObject() != null) {
        jvmTypeRef = typeResolver.resolveTypes(context.getMarkerObject()).getActualType(context.getMarkerObject()).toTypeReference();
      } else {
        jvmTypeRef = EcoreUtil2.<Context>getContainerOfType(context, Context.class).getContextVariable().getType();
      }

      if (jvmTypeRef != null) {
        EClass eClass = classForJvmType(context, jvmTypeRef.getType());
        if (eClass != null) {
          EList<EStructuralFeature> features = eClass.getEAllStructuralFeatures();
          Collection<IEObjectDescription> descriptions = Collections2.transform(features,
            (EStructuralFeature f) -> EObjectDescription.create(QualifiedName.create(f.getName()), f));
          return MapBasedScope.createScope(IScope.NULLSCOPE, descriptions);
        } else {
          return IScope.NULLSCOPE;
        }
      } else {
        return IScope.NULLSCOPE;
      }
    } else if (reference == CheckPackage.Literals.XISSUE_EXPRESSION__CHECK) {
      // Make sure that only Checks of the current model can be referenced, and if the CheckCatalog includes
      // another CheckCatalog, then use that parent as parent scope

      CheckCatalog catalog = EcoreUtil2.<CheckCatalog>getContainerOfType(context, CheckCatalog.class);
      List<Check> checks = IterableExtensions.<Check>toList(IterableExtensions.<Check>filter(catalog.getAllChecks(), c -> c.getName() != null));

      Collection<IEObjectDescription> descriptions = Collections2.transform(checks,
        (Check c) -> EObjectDescription.create(QualifiedName.create(c.getName()), c));
      // Determine the parent scope; use NULLSCOPE if no included CheckCatalog is defined (or if it cannot be resolved)
      IScope parentScope = IScope.NULLSCOPE;

      return MapBasedScope.createScope(parentScope, Iterables.filter(descriptions, Predicates.notNull()));
    }
    return null;
  }

  protected IScope _scope(final CheckCatalog context, final EReference reference) {
    if (reference == CheckPackage.Literals.CHECK_CATALOG__GRAMMAR) {
      IResourceServiceProvider.Registry reg = IResourceServiceProvider.Registry.INSTANCE;
      // CHECKSTYLE:CHECK-OFF IllegalCatch
      Collection<IEObjectDescription> descriptions = Collections2.transform(reg.getExtensionToFactoryMap().keySet(),
        (String e) -> {
          URI dummyUri = URI.createURI("foo:/foo." + e);
          try {
            Grammar g = reg.getResourceServiceProvider(dummyUri).get(IGrammarAccess.class).getGrammar();
            return EObjectDescription.create(qualifiedNameConverter.toQualifiedName(g.getName()), g);
          } catch (Exception ex) {
            return null;
          }
        });
      // CHECKSTYLE:CHECK-ON IllegalCatch
      // We look first in the workspace for a grammar and then in the registry for a registered grammar
      return MapBasedScope.createScope(IScope.NULLSCOPE, Iterables.filter(descriptions, Predicates.notNull()));
    } else if (reference == CheckPackage.Literals.XISSUE_EXPRESSION__CHECK) {
      List<IEObjectDescription> descriptions = ListExtensions.map(context.getAllChecks(),
        (Check c) -> EObjectDescription.create(checkQualifiedNameProvider.getFullyQualifiedName(c), c));
      return new SimpleScope(super.getScope(context, reference), descriptions);
    }
    return null;
  }

  // default implementation will throw an illegal argument exception
  protected IScope _scope(final EObject context, final EReference reference) {
    return null;
  }

  public IScope scope(final EObject context, final EReference reference) {
    if (context instanceof CheckCatalog) {
      return _scope((CheckCatalog) context, reference);
    } else if (context instanceof XIssueExpression) {
      return _scope((XIssueExpression) context, reference);
    } else if (context != null) {
      return _scope(context, reference);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(context, reference).toString());
    }
  }

  public EClass classForJvmType(final EObject context, final JvmType jvmType) {
    if (jvmType != null && !jvmType.eIsProxy()) {
      String qualifiedName = jvmType.getQualifiedName();
      String qualifiedPackageName = qualifiedName.substring(0, qualifiedName.lastIndexOf('.'));
      String packageName = qualifiedPackageName.substring(qualifiedPackageName.lastIndexOf('.') + 1);
      EPackage ePackage = getEPackage(context.eResource(), packageName);
      if (ePackage != null) {
        EClassifier eClassifier = ((EPackage) EcoreUtil.resolve(ePackage, context)).getEClassifier(jvmType.getSimpleName());
        if (eClassifier instanceof EClass) {
          return (EClass) eClassifier;
        }
      }
    }
    return null;
  }

  public EPackage getEPackage(final Resource context, final String name) {
    // not using for-each loop, as it could result in a ConcurrentModificationException when a resource is demand-loaded
    EList<Resource> resources = context.getResourceSet().getResources();
    for (int i = 0; i < resources.size(); i++) {
      Resource resource = resources.get(i);
      for (EObject obj : resource.getContents()) {
        if (obj instanceof EPackage && Objects.equals(((EPackage) obj).getName(), name)) {
          return (EPackage) obj;
        }
      }
    }
    IEObjectDescription desc = globalScopeProvider.getScope(context, EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE, null).getSingleElement(QualifiedName.create(name));
    if (desc != null) {
      return (EPackage) desc.getEObjectOrProxy();
    }
    Iterator<IEObjectDescription> descs = descriptionsProvider.getResourceDescriptions(context).getExportedObjects(EcorePackage.Literals.EPACKAGE, QualifiedName.create(name), false).iterator();
    if (descs.hasNext()) {
      EObject pkg = EcoreUtil.resolve(descs.next().getEObjectOrProxy(), context);
      // this filtering only appears to be necessary when executing the unit test BugAig830 in Jenkins (see https://jira.sys.net/browse/ACF-8758)
      if (!pkg.eIsProxy()) {
        return (EPackage) pkg;
      }
    }
    for (String nsUri : Sets.newHashSet(EPackage.Registry.INSTANCE.keySet())) {
      EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsUri);
      if (Objects.equals(ePackage.getName(), name)) {
        return ePackage;
      }
    }
    return null;
  }

  //todo: scoping for the check implementation (e.g. the parameters are not visible)

  //todo: scope the allowed imports!
}
