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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;


import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;


/**
 * Resolves the model type names that appear in scope expressions (casts, {@code typeSelect} and {@code isInstance})
 * to their EMF {@link EClassifier} using the EPackages imported by the scope model.
 * <p>
 * This reproduces, without the classic Xtend type system, the resolution that the legacy
 * {@code EmfRegistryMetaModel} performed. An aliased or package qualified name such as {@code alias::Entity}
 * resolves the alias against the imports first and then against the visible packages. An <em>unqualified</em> name
 * such as {@code Entity} is looked up in the packages the scope model (and the scope models it includes) actually
 * {@code import}s, in declaration order - this mirrors the classic Xtend {@code ExecutionContextImpl.getTypeForName}
 * which prefixed an unqualified name with each of the resource's imported namespaces in turn. Resolving an
 * unqualified name across <em>all</em> visible packages instead would pick an arbitrary (registry order dependent)
 * homonym, for example {@code plsql::CallStatement} where the model means {@code avqscript::CallStatement}.
 */
public final class ScopeModelTypeResolver {

  private final ScopeModel model;
  /** All EPackages visible to the scope model; used to resolve alias/package qualified names. */
  private final List<EPackage> visiblePackages;
  /** The EPackages the scope model and its included scope models import, in declaration order. */
  private final List<EPackage> importedPackages;

  /**
   * Creates a resolver for the given scope model.
   *
   * @param model
   *          the scope model whose imported packages provide the visible model types, must not be {@code null}
   */
  public ScopeModelTypeResolver(final ScopeModel model) {
    this.model = model;
    this.visiblePackages = StreamSupport.stream(EObjectUtil.getScopeProviderByEObject(model).getScope(model, ScopePackage.Literals.IMPORT__PACKAGE).getAllElements().spliterator(), false)//
        .map(d -> (EPackage) EcoreUtil.resolve(d.getEObjectOrProxy(), model)).collect(Collectors.toList());
    this.importedPackages = collectImportedPackages(model, visiblePackages);
  }

  /**
   * Collects the packages imported by the given scope model and, transitively, by the scope models it includes.
   * The model's own imports come first, mirroring the order in which the legacy generator handed the imported
   * namespaces to the classic Xtend execution context. Packages are de-duplicated by name, keeping the first
   * occurrence.
   *
   * @param root
   *          the scope model being generated, must not be {@code null}
   * @param visible
   *          all EPackages visible to the scope model, must not be {@code null}
   * @return the imported packages in resolution order, never {@code null}
   */
  private static List<EPackage> collectImportedPackages(final ScopeModel root, final List<EPackage> visible) {
    final List<EPackage> result = new ArrayList<>();
    final Set<String> seenNames = new LinkedHashSet<>();
    for (final ScopeModel scopeModel : allScopeModels(root)) {
      for (final Import imp : scopeModel.getImports()) {
        final EPackage imported = imp.getPackage();
        if (imported == null || imported.eIsProxy() || !seenNames.add(imported.getName())) {
          continue;
        }
        // The legacy resolution looked the namespace name up among all visible packages, so prefer that instance.
        result.add(visible.stream().filter(p -> imported.getName().equals(p.getName())).findFirst().orElse(imported));
      }
    }
    return result;
  }

  /**
   * Returns the given scope model plus all (transitively) included scope models, the given model first.
   *
   * @param root
   *          the scope model, must not be {@code null}
   * @return the ordered set of scope models, never {@code null}
   */
  private static Set<ScopeModel> allScopeModels(final ScopeModel root) {
    final Set<ScopeModel> result = new LinkedHashSet<>();
    collectScopeModels(root, result);
    return result;
  }

  /**
   * Adds the given scope model and all (transitively) included scope models to the given set.
   *
   * @param scopeModel
   *          the scope model, must not be {@code null}
   * @param result
   *          the set to add to, must not be {@code null}
   */
  private static void collectScopeModels(final ScopeModel scopeModel, final Set<ScopeModel> result) {
    if (result.add(scopeModel)) {
      for (final ScopeModel included : scopeModel.getIncludedScopes()) {
        if (included != null && !included.eIsProxy()) {
          collectScopeModels(included, result);
        }
      }
    }
  }

  /**
   * Resolves the given DSL type name segments to the matching model classifier.
   *
   * @param segments
   *          the {@code ::}-separated name segments of the type, must not be {@code null} or empty
   * @return the resolved classifier, or {@code null} if no imported package declares it
   */
  public EClassifier resolve(final List<String> segments) {
    if (segments == null || segments.isEmpty()) {
      return null;
    }
    String firstSegment = segments.getFirst();
    if (segments.size() == 1) {
      return findClassifier(firstSegment);
    }
    final String alias = firstSegment;
    final String typeName = segments.getLast();
    for (final Import imp : model.getImports()) {
      if (alias.equals(imp.getName()) && imp.getPackage() != null) {
        return imp.getPackage().getEClassifier(typeName);
      }
    }
    for (final EPackage ePackage : visiblePackages) {
      if (alias.equals(ePackage.getName())) {
        final EClassifier classifier = ePackage.getEClassifier(typeName);
        if (classifier != null) {
          return classifier;
        }
      }
    }
    return findClassifier(typeName);
  }

  /**
   * Finds the first classifier with the given (unqualified) name across the imported packages, in import order.
   *
   * @param name
   *          the unqualified classifier name, must not be {@code null}
   * @return the matching classifier, or {@code null} if none is found
   */
  private EClassifier findClassifier(final String name) {
    for (final EPackage ePackage : importedPackages) {
      final EClassifier classifier = ePackage.getEClassifier(name);
      if (classifier != null) {
        return classifier;
      }
    }
    return null;
  }

  /**
   * Convenience factory that returns {@code null} when the given element is not a scope model.
   *
   * @param element
   *          the model element a scope expression originates from, may be {@code null}
   * @return a resolver for the containing scope model, or {@code null} if none can be determined
   */
  public static ScopeModelTypeResolver forElement(final EObject element) {
    final ScopeModel scopeModel = EcoreUtil2.getContainerOfType(element, ScopeModel.class);
    return scopeModel == null ? null : new ScopeModelTypeResolver(scopeModel);
  }

}
