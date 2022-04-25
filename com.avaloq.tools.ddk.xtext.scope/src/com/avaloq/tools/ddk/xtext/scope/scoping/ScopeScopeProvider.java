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
package com.avaloq.tools.ddk.xtext.scope.scoping;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import com.avaloq.tools.ddk.xtext.scope.ScopeUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;
import com.avaloq.tools.ddk.xtext.scoping.EObjectDescriptions;
import com.avaloq.tools.ddk.xtext.scoping.EPackageScopeProvider;
import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Scoping for the scoping language.
 */
public class ScopeScopeProvider extends AbstractDeclarativeScopeProvider {

  @Inject
  private EPackageScopeProvider ePackageScopeProvider;

  /**
   * Scope for {@link org.eclipse.emf.ecore.EPackage}. These are read from the
   * registry as well as from the {@link org.eclipse.xtext.Grammar Xtext
   * grammar} corresponding to the scope model (if any).
   *
   * @param context
   *          context scope DSL model
   * @param reference
   *          context reference
   * @return scope with all available {@link org.eclipse.emf.ecore.EPackage
   *         EPackages} with {@link org.eclipse.emf.ecore.EPackage#getNsURI()} as name
   */
  // CHECKSTYLE:OFF
  public IScope scope_Import_package(final EObject context, final EReference reference) {
    // CHECKSTYLE:ON
    return ePackageScopeProvider.scope_EPackage(context, reference);
  }

  /**
   * Create a scope containing the EClasses of an EPackage.
   *
   * @param parent
   *          The parent scope
   * @param importedPackage
   *          The Import of the package
   * @return The scope
   */
  private IScope createEClassScope(final IScope parent, final Import importedPackage) {
    final String prefix = ScopeUtil.getPackageName(importedPackage);
    final Iterable<EClass> classes = Iterables.filter(importedPackage.getPackage().getEClassifiers(), EClass.class);
    final Iterable<IEObjectDescription> elements = EObjectDescriptions.<EClass> all(classes, NameFunctions.pair(NameFunctions.fromFeature(EcorePackage.Literals.ENAMED_ELEMENT__NAME), new AbstractNameFunction() {
      @Override
      public QualifiedName apply(final EObject from) {
        return QualifiedName.create(prefix, ((EClass) from).getName());
      }
    }));
    return new SimpleScope(parent, elements);
  }

  /**
   * Create a scope for the given elements, using their "name" attribute for the name.
   *
   * @param elements
   *          The elements
   * @return The scope
   */
  private IScope createENamedElementScope(final Iterable<? extends ENamedElement> elements) {
    return new SimpleScope(IScope.NULLSCOPE, EObjectDescriptions.all(elements, EcorePackage.Literals.ENAMED_ELEMENT__NAME));
  }

  /**
   * Create a scope containing all EClasses of all visible imported EPackages.
   *
   * @param context
   *          scope model
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF
  public IScope scope_EClass(final ScopeModel context, final EReference reference) {
    // CHECKSTYLE:ON
    IScope result = IScope.NULLSCOPE;
    final Iterable<Import> importedPackages = Lists.reverse(context.getImports());
    for (final Import importedPackage : importedPackages) {
      result = createEClassScope(result, importedPackage);
    }
    return result;
  }

  /**
   * Create a scope containing all EReferences of a scope's context type.
   *
   * @param context
   *          The ScopeContext
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF
  public IScope scope_ScopeContext_reference(final ScopeContext context, final EReference reference) {
    // CHECKSTYLE:ON
    final EClass type = context.getContextType();
    if (type != null) {
      return createENamedElementScope(type.getEAllReferences());
    }
    return IScope.NULLSCOPE;
  }

  /**
   * Create a scope containing all EReferences of a scope definition's context type.
   *
   * @param context
   *          The ScopeContext
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF
  public IScope scope_ScopeDefinition_reference(final ScopeDefinition context, final EReference reference) {
    // CHECKSTYLE:ON
    final EClass type = context.getContextType();
    if (type != null) {
      return createENamedElementScope(type.getEAllReferences());
    }
    return IScope.NULLSCOPE;
  }

  /**
   * Create a scope that includes all "inherited" scope definitions.
   *
   * @param parent
   *          The parent scope
   * @param context
   *          The context ScopingSection
   * @return The scope
   */
  private IScope getIncludedScopes(final IScope parent, final ScopeModel context) {
    IScope result = parent;
    for (final ScopeModel include : context.getIncludedScopes()) {
      result = getIncludedScopes(result, include);
    }
    if (!context.getScopes().isEmpty()) {
      // Add our own scopes
      result = new SimpleScope(result, EObjectDescriptions.all(context.getScopes(), new AbstractNameFunction() {
        @Override
        public QualifiedName apply(final EObject from) {
          ScopeDefinition scopeDef = (ScopeDefinition) from;
          return QualifiedName.create(scopeDef.getName() != null ? scopeDef.getName() : "");
        }
      }));
    }
    return result;
  }

  /**
   * Create a scope containing all defined visible named scopes.
   *
   * @param context
   *          The ScopeDelegation
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF
  public IScope scope_ScopeDelegation_scope(final ScopeDelegation context, final EReference reference) {
    // CHECKSTYLE:ON
    return getIncludedScopes(IScope.NULLSCOPE, EcoreUtil2.getContainerOfType(context, ScopeModel.class));
  }

}
