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

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescription.Event;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.DescriptionAddingContainer;
import org.eclipse.xtext.resource.containers.IContainerState;
import org.eclipse.xtext.resource.containers.StateBasedContainerManager;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;
import com.avaloq.tools.ddk.xtext.resource.StateBasedContainerWithHandle;
import com.avaloq.tools.ddk.xtext.scoping.IDomain;
import com.google.inject.Inject;


/**
 * An {@link IContainer.Manager} implementation which caches the created containers. This is important for the performance of large containers (containers with
 * many resources).
 */
public class CachingStateBasedContainerManager extends StateBasedContainerManager {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(CachingStateBasedContainerManager.class);

  @Inject
  private IDomain.Mapper mapper;

  private final Map<IResourceDescriptions, Map<String, WeakReference<IContainer>>> descriptionsContainersCache = new WeakHashMap<IResourceDescriptions, Map<String, WeakReference<IContainer>>>();

  @Override
  protected synchronized IContainer createContainer(final String handle, final IResourceDescriptions resourceDescriptions) {
    final IResourceDescriptions descriptionsKey = resourceDescriptions instanceof CurrentDescriptions2.ResourceSetAware
        ? ((CurrentDescriptions2.ResourceSetAware) resourceDescriptions).getDelegate()
        : resourceDescriptions;

    Map<String, WeakReference<IContainer>> containersMap = descriptionsContainersCache.get(descriptionsKey);
    if (containersMap == null) {
      containersMap = new HashMap<String, WeakReference<IContainer>>();
      descriptionsContainersCache.put(descriptionsKey, containersMap);
    }

    WeakReference<IContainer> containerRef = containersMap.get(handle);
    IContainer container = containerRef != null ? containerRef.get() : null;
    if (container == null) {
      final IContainerState containerState = new ContainerState(handle, getStateProvider().get(descriptionsKey));
      container = new StateBasedContainerWithHandle(descriptionsKey, containerState, handle);
      new CacheInvalidator(descriptionsKey, handle, containerState);
      containerRef = new WeakReference<IContainer>(container);
      containersMap.put(handle, containerRef);
    }

    return container;
  }

  /**
   * Discards the caching of a given container. Called if the {@link IResourceDescriptions} signals a change of a resource in the given container.
   *
   * @param resourceDescriptions
   *          context IResourceDescriptions
   * @param handle
   *          container handle
   */
  private synchronized void discardCachedContainer(final IResourceDescriptions resourceDescriptions, final String handle) {
    descriptionsContainersCache.get(resourceDescriptions).remove(handle);
  }

  /**
   * Invalidates the container caching if a corresponding resource is changed.
   */
  private class CacheInvalidator implements IResourceDescription.Event.Listener {
    private final IResourceDescriptions resourceDescriptions;
    private final String handle;
    private final IContainerState containerState;

    CacheInvalidator(final IResourceDescriptions resourceDescriptions, final String handle, final IContainerState containerState) {
      this.resourceDescriptions = resourceDescriptions;
      this.handle = handle;
      this.containerState = containerState;
      if (resourceDescriptions instanceof IResourceDescription.Event.Source) {
        ((IResourceDescription.Event.Source) resourceDescriptions).addListener(this);
      }
    }

    /** {@inheritDoc} */
    @Override
    public void descriptionsChanged(final Event event) {
      for (Delta d : event.getDeltas()) {
        if (containerState.contains(d.getUri())) {
          discardCachedContainer(resourceDescriptions, handle);
          if (resourceDescriptions instanceof IResourceDescription.Event.Source) {
            ((IResourceDescription.Event.Source) resourceDescriptions).removeListener(this);
          }
          break;
        }
      }
    }
  }

  @Override
  public List<IContainer> getVisibleContainers(final IResourceDescription desc, final IResourceDescriptions resourceDescriptions) {
    String root = internalGetContainerHandle(desc, resourceDescriptions);
    if (root == null) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Cannot find IContainer for: " + desc.getURI()); //$NON-NLS-1$
      }
      return Collections.emptyList();
    }
    List<String> handles = getState(resourceDescriptions).getVisibleContainerHandles(root);
    return getContainersForHandlesAndResource(handles, desc, resourceDescriptions);
  }

  /**
   * Returns the containers for the given handles, where one of the containers will also include {@code desc} when appropriate.
   * 
   * @param handles
   *          handles to get containers for, must not be {@code null}
   * @param desc
   *          description to add, must not be {@code null}
   * @param resourceDescriptions
   *          resource descriptions, must not be {@code null}
   * @return list of containers, never {@code null}
   */
  protected List<IContainer> getContainersForHandlesAndResource(final List<String> handles, final IResourceDescription desc, final IResourceDescriptions resourceDescriptions) {
    List<IContainer> result = getVisibleContainers(handles, resourceDescriptions);
    if (!result.isEmpty()) {
      URI descURI = desc.getURI();
      for (int i = 0; i < result.size(); i++) {
        if (result.get(i).getResourceDescription(descURI) != null) {
          return result;
        }
      }
      // Do *not* add the context resource description itself if we're in the first phase of a build: 'desc' itself
      // may not be in any consistent state, and adding it to the containers may result in recursive invocations of
      // getEObjectDescriptions(), leading to a stack overflow since it may in turn invoke getVisibleContainers() again.
      if (desc instanceof DefaultResourceDescription) {
        DefaultResourceDescription d = (DefaultResourceDescription) desc;
        if (BuildPhases.isIndexing(d.getResource())) {
          return result;
        }
      }
      // the IResourceDescription was found nowhere, add it to the first one that matches the description's domain.
      IDomain descDomain = mapper.map(descURI);
      IContainer wrappedContainer = null;
      int index = 0;
      for (int i = 0; i < result.size(); i++) {
        IContainer container = result.get(i);
        IDomain containerDomain = mapper.map(container);
        if (containerDomain != null && containerDomain.equals(descDomain)) {
          wrappedContainer = new DescriptionAddingContainer(desc, container);
          result.set(index, wrappedContainer);
          return result;
        }
        index++;
      }
      // If we get here, we found no container with a matching domain. Add to the first, but use a DescriptionAddingContainer that
      // will add the description at the end.
      wrappedContainer = new DescriptionAtEndAddingContainer(desc, result.get(0));
      result.set(0, wrappedContainer);
    }
    return result;
  }

}
