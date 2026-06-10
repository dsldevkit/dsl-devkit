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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Resolves the model type names that appear in export expressions (casts, {@code typeSelect} and {@code isInstance})
 * to their EMF {@link EClassifier} using the EPackages imported by the export model.
 * <p>
 * This reproduces, without the classic Xtend type system, the resolution that the legacy
 * {@code EmfRegistryMetaModel} performed: an unqualified name such as {@code Entity} is looked up across all
 * imported packages, while an aliased or package qualified name such as {@code alias::Entity} resolves the alias to
 * the imported package first. The list of visible EPackages is obtained exactly as the legacy generator did, namely
 * through the scope of the {@code IMPORT__PACKAGE} reference.
 */
public final class ExportModelTypeResolver {

  private final ExportModel model;
  private final List<EPackage> packages;

  /**
   * Creates a resolver for the given export model.
   *
   * @param model
   *          the export model whose imported packages provide the visible model types, must not be {@code null}
   */
  public ExportModelTypeResolver(final ExportModel model) {
    this.model = model;
    this.packages = Lists.newArrayList(Iterables.transform(
        EObjectUtil.getScopeProviderByEObject(model).getScope(model, ExportPackage.Literals.IMPORT__PACKAGE).getAllElements(),
        d -> (EPackage) EcoreUtil.resolve(d.getEObjectOrProxy(), model)));
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
    if (segments.size() == 1) {
      return findClassifier(segments.get(0));
    }
    final String alias = segments.get(0);
    final String typeName = segments.get(segments.size() - 1);
    for (final Import imp : model.getImports()) {
      if (alias.equals(imp.getName()) && imp.getPackage() != null) {
        return imp.getPackage().getEClassifier(typeName);
      }
    }
    for (final EPackage ePackage : packages) {
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
   * Finds the first classifier with the given (unqualified) name across all imported packages.
   *
   * @param name
   *          the unqualified classifier name, must not be {@code null}
   * @return the matching classifier, or {@code null} if none is found
   */
  private EClassifier findClassifier(final String name) {
    for (final EPackage ePackage : packages) {
      final EClassifier classifier = ePackage.getEClassifier(name);
      if (classifier != null) {
        return classifier;
      }
    }
    return null;
  }

  /**
   * Convenience factory that returns {@code null} when the given element is not an export model.
   *
   * @param element
   *          the model element an export expression originates from, may be {@code null}
   * @return a resolver for the containing export model, or {@code null} if none can be determined
   */
  public static ExportModelTypeResolver forElement(final EObject element) {
    final ExportModel exportModel = org.eclipse.xtext.EcoreUtil2.getContainerOfType(element, ExportModel.class);
    return exportModel == null ? null : new ExportModelTypeResolver(exportModel);
  }

}
