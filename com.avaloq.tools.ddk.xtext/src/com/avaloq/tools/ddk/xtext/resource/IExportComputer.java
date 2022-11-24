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

package com.avaloq.tools.ddk.xtext.resource;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;


/**
 * An aggregation of API for export into Xtext index.
 * <p>
 * Common API for extension implementation (actual extension) and extensions service
 * (aggregation of all extensions which is used by a DSL to consume extensions).
 * </p>
 */
public interface IExportComputer {
  /**
   * Abstract method which computes the qualified name for a given object.
   *
   * @param object
   *          context object, must not be {@code null}
   * @return qualified name, may return {@code null}
   */
  QualifiedName qualifiedName(EObject object);

  /**
   * Returns the computed fingerprint for the given object.
   *
   * @param object
   *          the object, must not be {@code null}
   * @return the fingerprint, may return {@code null}
   */
  String computeFingerprint(EObject object);

  /**
   * Extension for fragment computation.
   * <p>
   * Implementations must return {@code false} if this extension is not meant to handle the EClass of the given object.
   * </p>
   *
   * @param object
   *          the object, must not be {@code null}
   * @param builder
   *          builder to append fragment segment to, must not be {@code null}
   * @return {@code true} if a fragment segment was appended to {@code builder}
   */
  boolean appendFragmentSegment(final EObject object, StringBuilder builder);

  /**
   * Returns additional exported classes by this extension.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return the set of {@link EClass}es, may be empty, never {@code null}
   */
  Set<EClass> getExportedEClasses(final Resource resource);

  /**
   * Retrieve qualified name and add it to the acceptor.
   * <p>
   * If at lease one of the extensions returns {@code true}, the content of the object will be processed. So return {@code true} if and only if this extension
   * will export contained objects.
   * </p>
   *
   * @param object
   *          object
   * @param acceptor
   *          Acceptor to store retrieved qualified names
   * @return true if this object's content should be processed
   */
  boolean createEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor);

}
