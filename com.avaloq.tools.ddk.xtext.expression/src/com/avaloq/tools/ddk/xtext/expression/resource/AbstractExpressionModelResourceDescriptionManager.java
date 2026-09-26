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
package com.avaloq.tools.ddk.xtext.expression.resource;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescription;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionManager;

import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;


/**
 * Resource description manager whose descriptions also import the Java types a model names but only resolves while its Java
 * is generated, so that a change to such a type re-queues the model.
 */
public abstract class AbstractExpressionModelResourceDescriptionManager extends XbaseResourceDescriptionManager {

  @Override
  protected IResourceDescription createResourceDescription(final Resource resource, final IDefaultResourceDescriptionStrategy strategy) {
    return new XbaseResourceDescription(resource, strategy, getCache(), typeResolver, nameConverter) {
      private Set<QualifiedName> importedNames;

      @Override
      public Iterable<QualifiedName> getImportedNames() {
        if (importedNames == null) {
          final Set<QualifiedName> names = new LinkedHashSet<>();
          super.getImportedNames().forEach(names::add);
          names.addAll(getNamedJavaTypes(resource));
          importedNames = Collections.unmodifiableSet(names);
        }
        return importedNames;
      }
    };
  }

  /**
   * Returns the names, as created by {@link #javaName(List)}, of the Java types the model in the given resource names.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return the names, never {@code null}
   */
  protected abstract Set<QualifiedName> getNamedJavaTypes(Resource resource);

  /**
   * Adds the names of the qualified types named in the expressions below the given element, such as casts and the receivers
   * of static calls.
   *
   * @param root
   *          the element whose contents are searched, must not be {@code null}
   * @param names
   *          the set to add the names to, must not be {@code null}
   */
  protected static void addExpressionTypeNames(final EObject root, final Set<QualifiedName> names) {
    root.eAllContents().forEachRemaining(content -> {
      if (content instanceof Identifier identifier && identifier.getId().size() > 1) {
        names.add(javaName(identifier.getId()));
      }
    });
  }

  /**
   * Returns the name of a Java type in the form in which changed Java types are reported: dot-separated segments without
   * escape characters, in lower case.
   *
   * @param segments
   *          the segments of the qualified type name, must not be {@code null}
   * @return the name, never {@code null}
   */
  protected static QualifiedName javaName(final List<String> segments) {
    return QualifiedName.create(segments.stream().map(segment -> segment.startsWith("^") ? segment.substring(1) : segment).toList()).toLowerCase(); //$NON-NLS-1$
  }
}
