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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.IOException;
import java.util.Deque;
import java.util.Map;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.resource.ResourceSetOptions;


/**
 * Proxy implementation of {@link InferredModelAssociator.Adapter}.
 */
final class ProxyModelAssociationsAdapter extends InferredModelAssociator.Adapter {

  private final StorageAwareResource resource;
  private boolean loaded;

  private ProxyModelAssociationsAdapter(final StorageAwareResource resource) {
    this.resource = resource;
  }

  /**
   * Installs a {@link ProxyModelAssociationsAdapter} unless the given resource already has an {@link InferredModelAssociator.Adapter}.
   *
   * @param resource
   *          resource to add adapter to, must not be {@code null}
   */
  static void install(final StorageAwareResource resource) {
    InferredModelAssociator.Adapter adapter = (InferredModelAssociator.Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class);
    if (adapter == null) {
      adapter = new ProxyModelAssociationsAdapter(resource);
      resource.eAdapters().add(adapter);
    }
  }

  @Override
  public Map<EObject, Deque<EObject>> getSourceToInferredModelMap() {
    ensureAssociationsLoaded();
    return ((InferredModelAssociator.Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class)).getSourceToInferredModelMap();
  }

  @Override
  public Map<EObject, Deque<EObject>> getInferredModelToSourceMap() {
    ensureAssociationsLoaded();
    return ((InferredModelAssociator.Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class)).getInferredModelToSourceMap();
  }

  private void ensureAssociationsLoaded() {
    if (loaded) {
      return;
    }
    synchronized (resource) {
      if (resource.eAdapters().remove((org.eclipse.emf.common.notify.Adapter) this)) {
        DirectLinkingResourceStorageLoadable loadable = (DirectLinkingResourceStorageLoadable) ((DirectLinkingResourceStorageFacade) resource.getResourceStorageFacade()).getOrCreateResourceStorageLoadable(resource);
        try {
          // the associations are mappings from AST model elements to inferred model elements (and back) so we cannot skip loading the model.
          ResourceSetOptions.setSkipModel(resource.getResourceSet(), false);
          loadable.loadIntoResource(resource, ResourceLoadMode.ONLY_ASSOCIATIONS);
        } catch (IOException e) {
          throw new WrappedException(e);
        }
      }
      loaded = true;
    }
  }
}
