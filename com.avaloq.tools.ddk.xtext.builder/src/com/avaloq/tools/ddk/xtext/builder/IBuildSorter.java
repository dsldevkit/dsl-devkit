/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.builder;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.google.common.collect.Lists;
import com.google.inject.ImplementedBy;


/**
 * Simple wrapper for Guice injection of a collection sorter in the xtext builder.
 */
@ImplementedBy(IBuildSorter.NullBuildSorter.class)
public interface IBuildSorter {

  /**
   * <p>
   * Input is the set of URIs of physically changed or new resources. The builder will try to write initial resource descriptions for these. If resources have
   * dependencies between themselves, it is beneficial to process them in an order determined by those dependencies. The builder is smart enough to dynamically
   * reorder resources, but such reordering occurs only if a needed reference cannot be resolved. If the uris can be sorted up front in some way that minimizes
   * such cases (i.e., sorting them such that all or most dependencies are honored, such that dependent resources come later), the build will run faster.
   * </p>
   * <p>
   * This operation is supposed to make a best-effort attempt to sort the uris. For instance, in some environments with a known set of languages, it may be
   * possible to know that resources of a language A may never have cross-links to some other language B, but B may reference A. In that case, an implementation
   * might want to sort all type A resources before all type B resources. Moreover, the uris can be further grouped into several separate groups, for example to
   * allow for strict dependence/ordering between them (e.g. all sources of type are processed before sources of type B).
   * </p>
   *
   * @param uris
   *          URIs of all physically changed or new resources to be considered in the build
   * @return A list of sorted lists of URIs, containing all the URIs from the input and no additional URIs.
   */
  List<List<URI>> sort(Collection<URI> uris);

  /**
   * A null implementation of a build sorter.
   */
  class NullBuildSorter implements IBuildSorter {

    /** {@inheritDoc} */
    @Override
    public List<List<URI>> sort(final Collection<URI> uris) {
      List<List<URI>> sortedCopy = Lists.newLinkedList();
      sortedCopy.add(Lists.newLinkedList(uris));// Make a copy
      return sortedCopy;
    }

  }
}
