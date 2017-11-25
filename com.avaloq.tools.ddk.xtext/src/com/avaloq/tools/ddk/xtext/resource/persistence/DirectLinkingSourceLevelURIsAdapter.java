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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * Adaptation of Xtext's {@link org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter} which stores an {@link Collections#unmodifiableSet(Set)
 * unmodifiable view} of the set of resources to be regarded as "sources". As a consequence the caller (e.g. the
 * {@link com.avaloq.tools.ddk.xtext.builder.MonitoredClusteringBuilderState builder}) may modify that set without having to manually change this adapter.
 */
public class DirectLinkingSourceLevelURIsAdapter extends AdapterImpl {

  private Set<URI> sourceLevelURIs;

  @Override
  public boolean isAdapterForType(final Object type) {
    return type == DirectLinkingSourceLevelURIsAdapter.class;
  }

  public Collection<URI> getSourceLevelURIs() {
    return sourceLevelURIs;
  }

  /**
   * Installs a new {@link DirectLinkingSourceLevelURIsAdapter} adapter (if none already exists) and sets the given set of URIs as the source URIs. Note that
   * this method does <b>not</b> create a copy of the {@code uris} collection, so any changes will be reflected by {@link #getSourceLevelURIs()}.
   *
   * @param resourceSet
   *          resource set, must not be {@code null}
   * @param uris
   *          URI set, must not be {@code null}
   */
  public static void setSourceLevelUris(final ResourceSet resourceSet, final Set<URI> uris) {
    DirectLinkingSourceLevelURIsAdapter adapter = findInstalledAdapter(resourceSet);
    if (adapter == null) {
      adapter = new DirectLinkingSourceLevelURIsAdapter();
      resourceSet.eAdapters().add(adapter);
    }
    adapter.sourceLevelURIs = Collections.unmodifiableSet(uris);
  }

  /**
   * Finds an installed {@link DirectLinkingSourceLevelURIsAdapter} adapter for a given resource set.
   * 
   * @param resourceSet
   *          resource set, must not be {@code null}
   * @return installed adapter or {@code null} if none
   */
  public static DirectLinkingSourceLevelURIsAdapter findInstalledAdapter(final ResourceSet resourceSet) {
    return (DirectLinkingSourceLevelURIsAdapter) EcoreUtil.getAdapter(resourceSet.eAdapters(), DirectLinkingSourceLevelURIsAdapter.class);
  }

}
