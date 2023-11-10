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
package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;

import com.google.inject.ImplementedBy;


/**
 * An abstraction over {@link IContainer} which assigns resources to a domain.
 */
public interface IDomain {

  /**
   * Returns the name of this domain.
   *
   * @return domain name
   */
  String getName();

  /**
   * Maps a container or a URI to its domain.
   */
  @ImplementedBy(NullMapper.class)
  interface Mapper {

    /**
     * Determine the domain of a given container.
     *
     * @param container
     *          the container
     * @return The domain. May be null if no domain can be identified.
     */
    IDomain map(IContainer container);

    /**
     * Determine the domain of a given resource, denoted by its uri.
     *
     * @param uri
     *          The URI.
     * @return The Domain.
     */
    IDomain map(URI uri);
  }

  /**
   * A dummy implementation of the domain mapper interface.
   */
  class NullMapper implements Mapper {

    @Override
    public IDomain map(final URI uri) {
      return null;
    }

    @Override
    public IDomain map(final IContainer container) {
      return null;
    }

  }
}
