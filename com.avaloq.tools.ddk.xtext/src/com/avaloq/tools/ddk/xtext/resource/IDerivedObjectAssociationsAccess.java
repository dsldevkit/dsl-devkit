/*******************************************************************************
 * Copyright (c) 2018 Avaloq Group AG and others.
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

import org.eclipse.emf.common.util.URI;


/**
 * Store API for associations between resources processed by the builder and the objects (i.e. sources)
 * derived by various providers (i.e. by builder participants) for those resources.
 * <p>
 * Read more about associations in {@see DerivedObjectAssociations}.
 * </p>
 */
public interface IDerivedObjectAssociationsAccess {
  /**
   * Update associated derived artifacts for the given generator and resource.
   *
   * @param resourceUri
   *          URI identifying the resource processed by the builder, the URI must be known to the Xtext index
   * @param generatorType
   *          the name of the generator that processed the resource
   * @param derivedObjectUris
   *          URIs identifying the generated objects
   */
  void registerAssociations(URI resourceUri, String generatorType, Set<String> derivedObjectUris);

  /**
   * Returns associations for objects derived for the given resource identified by a resource URI.
   *
   * @param resourceUri
   *          the resource URI, must not be {@code null}
   * @return the associations, never {@code null} for existing sources, or {@code null} if the passed URI is not known to the builder
   */
  DerivedObjectAssociations getAssociations(URI resourceUri);

}
