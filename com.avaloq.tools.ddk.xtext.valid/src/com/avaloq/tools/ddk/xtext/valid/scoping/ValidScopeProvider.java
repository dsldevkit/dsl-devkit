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
package com.avaloq.tools.ddk.xtext.valid.scoping;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import com.avaloq.tools.ddk.xtext.scoping.EObjectDescriptions;
import com.avaloq.tools.ddk.xtext.scoping.EPackageScopeProvider;
import com.avaloq.tools.ddk.xtext.valid.valid.Import;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * The Class ValidScopeProvider.
 */
public class ValidScopeProvider extends AbstractDeclarativeScopeProvider {

  @Inject
  private EPackageScopeProvider ePackageScopeProvider;

  /**
   * Gets the imported namespaces (i.e. the list of packages imported in the given model).
   *
   * @param validModel
   *          the valid model
   * @return the imported packages
   */
  private Set<EPackage> getImportedPackages(final ValidModel validModel) {
    final Set<EPackage> importedPackages = new HashSet<EPackage>();
    for (final Import importedPackage : validModel.getImports()) {
      final EPackage ePackage = importedPackage.getPackage();
      if (ePackage != null) {
        importedPackages.add(ePackage);
      }
    }
    return importedPackages;
  }

  /**
   * Scope for {@link EPackage}. These are read from the registry as well as from the {@link org.eclipse.xtext.Grammar Xtext
   * grammar} corresponding to the scope model (if any).
   *
   * @param context
   *          context scope DSL model
   * @param reference
   *          context reference
   * @return scope with all available {@link EPackage EPackages} with {@link EPackage#getNsURI()} as name
   */
  // CHECKSTYLE:OFF
  public IScope scope_EPackage(final ValidModel context, final EReference reference) {
    // CHECKSTYLE:ON
    return ePackageScopeProvider.scope_EPackage(context, reference);
  }

  /**
   * Scope provider for EClass (all classes in the imported models).
   *
   * @param validModel
   *          the valid model
   * @param reference
   *          context reference
   * @return the i scope
   */
  // CHECKSTYLE:OFF
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public IScope scope_EClass(final ValidModel validModel, final EReference reference) {
    // CHECKSTYLE:ON
    IScope result = IScope.NULLSCOPE;
    for (final EPackage ePackage : getImportedPackages(validModel)) {
      result = createEClassScope(result, ePackage.getEClassifiers());
    }
    return result;
  }

  /**
   * Scope provider for EStructuralFeature (all "attributes" of an EClas in a native context.
   *
   * @param nativeContext
   *          the valid model
   * @param reference
   *          context reference
   * @return the i scope
   */
  // CHECKSTYLE:OFF
  public IScope scope_EStructuralFeature(final NativeContext nativeContext, final EReference reference) {
    // CHECKSTYLE:ON
    switch (reference.getFeatureID()) {
    case ValidPackage.NATIVE_CONTEXT__CONTEXT_FEATURE:
      return createENamedElementScope(nativeContext.getContextType().getEAllStructuralFeatures());
    case ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE:
      return createENamedElementScope(nativeContext.getMarkerType().getEAllStructuralFeatures());
    default:
      return IScope.NULLSCOPE; // To keep Java happy. This should not happen.
    }
  }

  /**
   * Creates a scope in which the elements are referenced by their name.
   *
   * @param elements
   *          the elements
   * @return the i scope
   */
  private IScope createENamedElementScope(final Iterable<? extends ENamedElement> elements) {
    return new SimpleScope(IScope.NULLSCOPE, EObjectDescriptions.all(elements, EcorePackage.Literals.ENAMED_ELEMENT__NAME));
  }

  /**
   * Creates the Eclass scope provider (all EClasses from the parent classifiers, referenced by their fully qualified (::) names.
   *
   * @param parent
   *          the parent
   * @param classifiers
   *          the classifiers
   * @return the i scope
   */
  private IScope createEClassScope(final IScope parent, final Iterable<EClassifier> classifiers) {
    final Iterable<EClass> classes = Iterables.filter(classifiers, EClass.class);
    Iterable<IEObjectDescription> elements = EObjectDescriptions.all(classes, EcorePackage.Literals.ENAMED_ELEMENT__NAME);
    elements = Iterables.concat(elements, EObjectDescriptions.all(classes, from -> {
      final EClass param = (EClass) from;
      return QualifiedName.create(param.getEPackage().getNsPrefix(), param.getName());
    }));
    return new SimpleScope(parent, elements);
  }
}
