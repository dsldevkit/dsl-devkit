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

import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.builder.clustering.CurrentDescriptions;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.avaloq.tools.ddk.xtext.builder.layered.NullResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.extensions.DelegatingResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.extensions.IResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.extensions.ResourceDescriptions2;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;


/**
 * Version of {@link CurrentDescriptions} supporting the new {@link IResourceDescriptions2} interface.
 */
public class CurrentDescriptions2 extends CurrentDescriptions implements IResourceDescriptions2 {

  private final IResourceDescriptionsData data;

  /**
   * Create a new index based on an old one.
   *
   * @param resourceSet
   *          The resource set
   * @param oldState
   *          The old index
   * @param initiallyDeleted
   *          URIs of resources physically deleted
   */
  public CurrentDescriptions2(final ResourceSet resourceSet, final ResourceDescriptionsData newData) {
    super(resourceSet, newData);
    if (newData instanceof IResourceDescriptionsData) {
      data = (IResourceDescriptionsData) newData;
    } else if (newData != null) {
      data = new DelegatingResourceDescriptionsData(newData);
    } else {
      data = new NullResourceDescriptionsData();
    }
  }

  @Override
  public Set<URI> getAllURIs() {
    return data.getAllURIs();
  }

  @Override
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return data.findAllReferencingResources(targetResources, matchPolicy);
  }

  @Override
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    return data.findExactReferencingResources(targetObjects, matchPolicy);
  }

  @Override
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    return data.findReferencesToObjects(targetObjects);
  }

  /**
   * Context-aware instance of our index.
   * FIXME: This is needed only because our ContainerManager needs access to the delegate as it uses it for its cache key. If we could do away with this, we
   * could remove this whole class and also its Guice binding in all languages. This class is used *only* when linking; it doesn't need to support the new
   * IResourceDEscriptions2 interface with the findReferences operations.
   */
  public static class ResourceSetAware extends CurrentDescriptions.ResourceSetAware implements IResourceDescriptions2 {

    /** Base index. */
    private IResourceDescriptions2 delegate;

    /**
     * Set the context.
     *
     * @param ctx
     *          The context
     */
    @Override
    public void setContext(final Notifier ctx) {
      super.setContext(ctx);
      final ResourceSet resourceSet = EcoreUtil2.getResourceSet(ctx);
      IResourceDescriptions adapter = (IResourceDescriptions) EcoreUtil2.getAdapter(resourceSet.eAdapters(), CurrentDescriptions.class);
      if (adapter instanceof IResourceDescriptions2) {
        delegate = (IResourceDescriptions2) adapter;
      } else if (adapter != null) {
        delegate = new ResourceDescriptions2(adapter);
      } else {
        delegate = new NullResourceDescriptionsData();
      }
    }

    @Override
    public IResourceDescriptions getDelegate() {
      return delegate;
    }

    @Override
    public Set<URI> getAllURIs() {
      return delegate.getAllURIs();
    }

    @Override
    public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
      return delegate.findAllReferencingResources(targetResources, matchPolicy);
    }

    @Override
    public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
      return delegate.findExactReferencingResources(targetObjects, matchPolicy);
    }

    @Override
    public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
      return delegate.findReferencesToObjects(targetObjects);
    }

  }

}
