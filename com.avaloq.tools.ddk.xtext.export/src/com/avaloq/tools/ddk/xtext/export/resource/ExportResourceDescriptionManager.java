/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.export.resource;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescription;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionManager;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Singleton;


/**
 * Resource description manager for export models which records the classes named in {@code extension} declarations as
 * dependencies of the model.
 * <p>
 * An extension is declared by name and only resolved while the model's Java is generated, so the inferred JVM model does
 * not reference it and it would otherwise be neither an imported name nor a reference of the model. A change to an
 * extension class then did not re-queue the model, and its generated code could stay stale. Adding the class names to the
 * imported names makes changes to them, whether reported by JDT or by the Xtext index for generated types, affect the
 * model.
 * </p>
 */
@Singleton
public class ExportResourceDescriptionManager extends XbaseResourceDescriptionManager {

  @Override
  protected IResourceDescription createResourceDescription(final Resource resource, final IDefaultResourceDescriptionStrategy strategy) {
    return new XbaseResourceDescription(resource, strategy, getCache(), typeResolver, nameConverter) {
      @Override
      public Iterable<QualifiedName> getImportedNames() {
        final Set<QualifiedName> extensionNames = extensionClassNames(resource);
        if (extensionNames.isEmpty()) {
          return super.getImportedNames();
        }
        final Set<QualifiedName> names = new LinkedHashSet<>();
        super.getImportedNames().forEach(names::add);
        names.addAll(extensionNames);
        return names;
      }
    };
  }

  /**
   * Returns the lower-cased qualified names of the classes named in the {@code extension} declarations of the export model
   * in the given resource, in the form imported names are matched against changed types.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return the names, never {@code null}
   */
  static Set<QualifiedName> extensionClassNames(final Resource resource) {
    if (resource.getContents().isEmpty()) {
      return ImmutableSet.of();
    }
    final EObject root = resource.getContents().get(0);
    if (!(root instanceof ExportModel model) || model.getExtensions().isEmpty()) {
      return ImmutableSet.of();
    }
    final Set<QualifiedName> names = new LinkedHashSet<>();
    for (final Extension declaration : model.getExtensions()) {
      final String extension = declaration.getExtension();
      if (extension != null && !extension.isBlank()) {
        names.add(toImportedName(extension));
      }
    }
    return names;
  }

  /**
   * Converts a {@code ::} delimited extension ID into the lower-cased qualified name of the Java class.
   *
   * @param extension
   *          the extension ID, must not be {@code null}
   * @return the imported name, never {@code null}
   */
  private static QualifiedName toImportedName(final String extension) {
    final String className = extension.replace("::", ".").replace("^", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    return QualifiedName.create(className.split("\\.")).toLowerCase(); //$NON-NLS-1$
  }
}
