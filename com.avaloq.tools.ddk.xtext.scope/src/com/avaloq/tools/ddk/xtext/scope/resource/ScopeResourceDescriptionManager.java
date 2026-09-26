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
package com.avaloq.tools.ddk.xtext.scope.resource;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.xtext.expression.resource.AbstractExpressionModelResourceDescriptionManager;
import com.avaloq.tools.ddk.xtext.scope.scope.Extension;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.inject.Singleton;


/**
 * Resource description manager for scope models, whose {@code extension} classes, injected types and expression types, and
 * those of the models they include, are dependencies.
 */
@Singleton
public class ScopeResourceDescriptionManager extends AbstractExpressionModelResourceDescriptionManager {

  @Override
  protected Set<QualifiedName> getNamedJavaTypes(final Resource resource) {
    if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof ScopeModel model)) {
      return Set.of();
    }
    final Set<QualifiedName> names = new LinkedHashSet<>();
    addNamedJavaTypes(model, new HashSet<>(), names);
    return names;
  }

  /**
   * Adds the Java types named by the given scope model and, transitively, by the models it includes.
   *
   * @param model
   *          the scope model, must not be {@code null}
   * @param visited
   *          the models already visited, which guards against include cycles, must not be {@code null}
   * @param names
   *          the set to add the names to, must not be {@code null}
   */
  private static void addNamedJavaTypes(final ScopeModel model, final Set<ScopeModel> visited, final Set<QualifiedName> names) {
    if (!visited.add(model)) {
      return;
    }
    for (final Extension declaration : model.getExtensions()) {
      if (declaration.getExtension() != null) {
        names.add(javaName(List.of(declaration.getExtension().split("::")))); //$NON-NLS-1$
      }
    }
    for (final Injection injection : model.getInjections()) {
      if (injection.getType() != null) {
        names.add(javaName(List.of(injection.getType().split("\\.")))); //$NON-NLS-1$
      }
    }
    addExpressionTypeNames(model, names);
    for (final ScopeModel included : model.getIncludedScopes()) {
      if (!included.eIsProxy()) {
        addNamedJavaTypes(included, visited, names);
      }
    }
  }
}
