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
package com.avaloq.tools.ddk.check.scoping

import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.CheckPackage
import com.avaloq.tools.ddk.check.check.Context
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.avaloq.tools.ddk.check.naming.CheckDeclarativeQualifiedNameProvider
import com.google.common.base.Predicates
import com.google.common.collect.Collections2
import com.google.common.collect.Iterables
import com.google.common.collect.Sets
import com.google.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.IGrammarAccess
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.scoping.IGlobalScopeProvider
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.MapBasedScope
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsBatchScopeProvider
import org.eclipse.xtext.xbase.typesystem.IBatchTypeResolver

class CheckScopeProvider extends XbaseWithAnnotationsBatchScopeProvider {

  @Inject CheckDeclarativeQualifiedNameProvider checkQualifiedNameProvider
  @Inject IQualifiedNameConverter qualifiedNameConverter
  @Inject IBatchTypeResolver typeResolver;
  @Inject IGlobalScopeProvider globalScopeProvider
  @Inject ResourceDescriptionsProvider descriptionsProvider

  // Use dispatch definitions instead of a switch statement since
  // https://bugs.eclipse.org/bugs/show_bug.cgi?id=368263
  // will otherwise cause the builder to fail during linking.
  override IScope getScope(EObject context, EReference reference) {
    val res = scope(context, reference)
    if (res !== null) res else super.getScope(context, reference)
  }

  def dispatch IScope scope(XIssueExpression context, EReference reference) {
    if (reference == CheckPackage.Literals::XISSUE_EXPRESSION__MARKER_FEATURE) {
      var jvmTypeRef =
        if (context.markerObject !== null)
          typeResolver.resolveTypes(context.markerObject).getActualType(context.markerObject).toTypeReference
        else
          EcoreUtil2::getContainerOfType(context, typeof(Context)).contextVariable.type;

      if (jvmTypeRef !== null) {
        val eClass = context.classForJvmType(jvmTypeRef.type);
        if (eClass !== null) {
          var features = eClass.EAllStructuralFeatures
          val descriptions = Collections2::transform(features,  [f | EObjectDescription::create(QualifiedName::create(f.name), f)])
          return MapBasedScope::createScope(IScope::NULLSCOPE, descriptions);
        } else {
          return IScope::NULLSCOPE;
        }
      } else  {
        return IScope::NULLSCOPE;
      }
    } else if (reference == CheckPackage.Literals::XISSUE_EXPRESSION__CHECK) {
      // Make sure that only Checks of the current model can be referenced, and if the CheckCatalog includes
      // another CheckCatalog, then use that parent as parent scope

      val catalog = EcoreUtil2::getContainerOfType(context, typeof(CheckCatalog))
      val checks  = catalog.allChecks.filter(c|c.name !== null).toList

      val descriptions = Collections2::transform(checks, [c|EObjectDescription::create(QualifiedName::create(c.name), c)])
      // Determine the parent scope; use NULLSCOPE if no included CheckCatalog is defined (or if it cannot be resolved)
      val parentScope  = IScope::NULLSCOPE

      return MapBasedScope::createScope(parentScope, Iterables::filter(descriptions, Predicates::notNull));
    }
  }

  def dispatch IScope scope(CheckCatalog context, EReference reference) {
     if (reference == CheckPackage.Literals::CHECK_CATALOG__GRAMMAR) {
      val reg = IResourceServiceProvider.Registry::INSTANCE
      val descriptions = Collections2::transform(reg.extensionToFactoryMap.keySet,
        [e | {
          val dummyUri = URI::createURI("foo:/foo." + e)
          try {
            val g = reg.getResourceServiceProvider(dummyUri).get(typeof(IGrammarAccess)).grammar
            return EObjectDescription::create(qualifiedNameConverter.toQualifiedName(g.name), g)
          } catch (Exception ex) {}
        }]
      )
      // We look first in the workspace for a grammar and then in the registry for a registered grammar
      val parentScope = MapBasedScope::createScope(IScope::NULLSCOPE, Iterables::filter(descriptions, Predicates::notNull));
      return parentScope;
      //val grammarScope = new DelegatingScope(parentScope);
      //grammarScope.setDelegate(super.getScope(context, reference));
      //return grammarScope;
    } else if (reference == CheckPackage.Literals::XISSUE_EXPRESSION__CHECK) {
      val descriptions = context.allChecks.map(c|EObjectDescription::create(checkQualifiedNameProvider.getFullyQualifiedName(c), c))
      return new SimpleScope(super.getScope(context, reference), descriptions)
    }
  }

  // default implementation will throw an illegal argument exception
  def dispatch IScope scope(EObject context, EReference reference) {
    return null
  }

  def EClass classForJvmType(EObject context, JvmType jvmType) {
    if (jvmType !== null && !jvmType.eIsProxy) {
      val qualifiedName = jvmType.getQualifiedName();
      val qualifiedPackageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
      val packageName = qualifiedPackageName.substring(qualifiedPackageName.lastIndexOf(".") + 1);
      val ePackage = getEPackage(context.eResource, packageName);
      if (ePackage !== null) {
        val eClassifier = (EcoreUtil::resolve(ePackage, context) as EPackage).getEClassifier(jvmType.simpleName)
        if (eClassifier instanceof EClass) {
          return eClassifier
        }
      }
    }
    return null
  }

  def EPackage getEPackage(Resource context, String name) {
    // not using for-each loop, as it could result in a ConcurrentModificationException when a resource is demand-loaded
    val resources = context.resourceSet.resources
    for (var i = 0; i < resources.size; i++) {
      val resource = resources.get(i)
      for (obj : resource.contents) {
        if (obj instanceof EPackage && (obj as EPackage).name == name)
          return obj as EPackage
      }
    }
    val desc = globalScopeProvider.getScope(context, EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE, null).getSingleElement(QualifiedName.create(name))
    if (desc !== null) {
      return desc.EObjectOrProxy as EPackage
    }
    val descs = descriptionsProvider.getResourceDescriptions(context).getExportedObjects(EcorePackage.Literals.EPACKAGE, QualifiedName.create(name), false).iterator
    if (descs.hasNext) {
      val pkg = EcoreUtil.resolve(descs.next.EObjectOrProxy, context)
      // this filtering only appears to be necessary when executing the unit test BugAig830 in Jenkins (see https://jira.sys.net/browse/ACF-8758)
      if (!pkg.eIsProxy)
        return pkg as EPackage
    }
    for (nsUri : Sets.newHashSet(EPackage.Registry.INSTANCE.keySet)) {
      val ePackage = EPackage.Registry.INSTANCE.getEPackage(nsUri)
      if (ePackage.name == name) {
        return ePackage
      }
    }
    return null
  }

  //todo: scoping for the check implementation (e.g. the parameters are not visible)

  //todo: scope the allowed imports!


}

