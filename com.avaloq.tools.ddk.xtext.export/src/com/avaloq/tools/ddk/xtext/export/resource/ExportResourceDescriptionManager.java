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
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.expression.resource.AbstractExpressionModelResourceDescriptionManager;
import com.google.inject.Singleton;


/**
 * Resource description manager for export models, whose {@code extension} classes and expression types are dependencies.
 */
@Singleton
public class ExportResourceDescriptionManager extends AbstractExpressionModelResourceDescriptionManager {

  @Override
  protected Set<QualifiedName> getNamedJavaTypes(final Resource resource) {
    if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof ExportModel model)) {
      return Set.of();
    }
    final Set<QualifiedName> names = new LinkedHashSet<>();
    for (final Extension declaration : model.getExtensions()) {
      if (declaration.getExtension() != null) {
        names.add(javaName(List.of(declaration.getExtension().split("::")))); //$NON-NLS-1$
      }
    }
    addExpressionTypeNames(model, names);
    return names;
  }
}
