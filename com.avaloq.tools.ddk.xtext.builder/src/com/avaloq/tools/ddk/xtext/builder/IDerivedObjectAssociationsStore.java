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

package com.avaloq.tools.ddk.xtext.builder;

import java.util.Collection;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;


/**
 * Store API for associations used as an extension for platform.
 */
public interface IDerivedObjectAssociationsStore extends IDerivedObjectAssociationsAccess {

  /**
   * Iterates through a {@link Collection} of {@link URI}s, calling the {@link BiConsumer} {@link derivedObjectAssociations} on each URI.
   *
   * @param allURIs
   *          A collection of the URIs for which we wish to retrieve associations
   * @param derivedObjectAssociations
   *          The consumer function that receives URI associations as input
   */
  void forEach(final Collection<URI> allURIs, final BiConsumer<URI, DerivedObjectAssociations> derivedObjectAssociations);

}
