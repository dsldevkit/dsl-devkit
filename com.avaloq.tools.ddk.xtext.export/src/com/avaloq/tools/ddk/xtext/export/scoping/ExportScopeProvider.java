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
package com.avaloq.tools.ddk.xtext.export.scoping;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import com.avaloq.tools.ddk.xtext.export.export.DeclarationForType;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.scoping.EObjectDescriptions;
import com.avaloq.tools.ddk.xtext.scoping.EPackageScopeProvider;
import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * This class implements scoping for the export language.
 */
public class ExportScopeProvider extends AbstractExportScopeProvider {

  @Inject
  private EPackageScopeProvider ePackageScopeProvider;

  // Note: as with all scope providers, CHECKSTYLE doesn't like the naming scheme prescribed by xtext.
  // We therefore switch off the checkstyle method naming checks locally.

  /**
   * {@inheritDoc}
   * <p>
   * Dispatches the export-language specific cross-references to the corresponding declarative
   * {@code scope_*} methods. All other references (in particular Xbase feature and type references)
   * are delegated to the {@link org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider Xbase batch scope provider}.
   */
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    if (reference == ExportPackage.Literals.IMPORT__PACKAGE) {
      return scope_Import_package(context, reference);
    } else if (reference == ExportPackage.Literals.INTERFACE_NAVIGATION__REF) {
      final Interface interfaceContext = EcoreUtil2.getContainerOfType(context, Interface.class);
      if (interfaceContext != null) {
        return scope_InterfaceNavigation_ref(interfaceContext, reference);
      }
    } else if (EcorePackage.Literals.ECLASS.isSuperTypeOf(reference.getEReferenceType())) {
      final ExportModel exportModel = EcoreUtil2.getContainerOfType(context, ExportModel.class);
      if (exportModel != null) {
        return scope_EClass(exportModel, reference);
      }
    } else if (EcorePackage.Literals.EATTRIBUTE.isSuperTypeOf(reference.getEReferenceType())) {
      final DeclarationForType declarationForType = EcoreUtil2.getContainerOfType(context, DeclarationForType.class);
      if (declarationForType != null) {
        return scope_EAttribute(declarationForType, reference);
      }
    } else if (EcorePackage.Literals.ESTRUCTURAL_FEATURE.isSuperTypeOf(reference.getEReferenceType())) {
      final DeclarationForType declarationForType = EcoreUtil2.getContainerOfType(context, DeclarationForType.class);
      if (declarationForType != null) {
        return scope_EStructuralFeature(declarationForType, reference);
      }
    }
    return super.getScope(context, reference);
  }

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
  // CHECKSTYLE:OFF (MethodName)
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
    final String prefix = importedPackage.getPackageName();
    final Iterable<EClass> classes = Iterables.filter(importedPackage.getPackage().getEClassifiers(), EClass.class);
    final Iterable<IEObjectDescription> elements = EObjectDescriptions.<EClass> all(classes, NameFunctions.pair(NameFunctions.fromFeature(EcorePackage.Literals.ENAMED_ELEMENT__NAME), from -> QualifiedName.create(prefix, ((EClass) from).getName())));
    return new SimpleScope(parent, elements);
  }

  /**
   * Create a scope for any EClass reference, containing all the EClasses of the package the export section is for.
   *
   * @param context
   *          The Export
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF (MethodName)
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public IScope scope_EClass(final ExportModel context, final EReference reference) {
    // CHECKSTYLE:ON
    IScope result = IScope.NULLSCOPE;
    for (Import importedPackage : context.getImports()) {
      result = createEClassScope(result, importedPackage);
    }
    return result;
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
   * Create a scope of all the structural features of the type of an Export.
   *
   * @param context
   *          The FeatureList
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF (MethodName)
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public IScope scope_EStructuralFeature(final DeclarationForType context, final EReference reference) {
    // CHECKSTYLE:ON
    final EClass type = context.getType();
    if (type != null) {
      return createENamedElementScope(type.getEAllStructuralFeatures());
    }
    return IScope.NULLSCOPE;
  }

  /**
   * Create a scope of all the structural features of the type of an Export.
   *
   * @param context
   *          The FeatureList
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF (MethodName)
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public IScope scope_EAttribute(final DeclarationForType context, final EReference reference) {
    // CHECKSTYLE:ON
    final EClass type = context.getType();
    if (type != null) {
      return createENamedElementScope(type.getEAllAttributes());
    }
    return IScope.NULLSCOPE;
  }

  /**
   * Create a scope of all the EReferences of the type of a FingerPrint.
   *
   * @param context
   *          The FingerPrintNavigation
   * @param reference
   *          The EReference
   * @return The scope
   */
  // CHECKSTYLE:OFF (MethodName)
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public IScope scope_InterfaceNavigation_ref(final Interface context, final EReference reference) {
    // CHECKSTYLE:ON
    final EClass type = context.getType();
    if (type != null) {
      return createENamedElementScope(type.getEAllReferences());
    }
    return IScope.NULLSCOPE;
  }

}
