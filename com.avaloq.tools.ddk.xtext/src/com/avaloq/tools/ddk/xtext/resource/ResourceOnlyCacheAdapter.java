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
package com.avaloq.tools.ddk.xtext.resource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.util.OnChangeEvictingCache.CacheAdapter;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;


/**
 * A {@link CacheAdapter} which adapts only its resource and none of the objects in it.
 * <p>
 * Its values are cleared explicitly (e.g. after installing or discarding the derived state) and on changes of the resource itself (root contents, load
 * state, URI), but not on changes of the objects in the resource. Avoids attaching an adapter to every object, and calling {@link Resource#getContents()},
 * which may load the resource or install its derived state.
 * </p>
 * <p>
 * {@link OnChangeEvictingCache} uses the cache adapter already on a resource, so it must be installed before any cache is used for the resource.
 * </p>
 */
public class ResourceOnlyCacheAdapter extends CacheAdapter {

  /**
   * Installs a {@link ResourceOnlyCacheAdapter} on the given resource unless it already has a {@link CacheAdapter}.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return the cache adapter of the resource, never {@code null}
   */
  public static CacheAdapter install(final Resource resource) {
    CacheAdapter adapter = (CacheAdapter) EcoreUtil.getAdapter(resource.eAdapters(), CacheAdapter.class);
    if (adapter == null) {
      ResourceOnlyCacheAdapter newAdapter = new ResourceOnlyCacheAdapter();
      resource.eAdapters().add(newAdapter);
      newAdapter.setResource(resource);
      adapter = newAdapter;
    }
    return adapter;
  }

  @Override
  protected void setTarget(final Resource target) {
    basicSetTarget(target);
  }

  @Override
  protected void unsetTarget(final Resource target) {
    basicUnsetTarget(target);
  }

  @Override
  protected void selfAdapt(final Notification notification) {
    // the objects of the resource are not adapted
  }

  /**
   * Adapter for a {@link ResourceSet} which installs a {@link ResourceOnlyCacheAdapter} on every resource added while the resource set is
   * {@link BuildPhases#isIndexing(Notifier) indexing}.
   */
  public static final class IndexingInstaller extends AdapterImpl {

    private IndexingInstaller() {
      // use addTo
    }

    /**
     * Adds an {@link IndexingInstaller} to the given resource set unless it already has one.
     *
     * @param resourceSet
     *          the resource set, must not be {@code null}
     */
    public static void addTo(final ResourceSet resourceSet) {
      if (EcoreUtil.getExistingAdapter(resourceSet, IndexingInstaller.class) == null) {
        resourceSet.eAdapters().add(new IndexingInstaller());
      }
    }

    @Override
    public boolean isAdapterForType(final Object type) {
      return type == IndexingInstaller.class;
    }

    @Override
    public void notifyChanged(final Notification notification) {
      if (notification.getFeatureID(ResourceSet.class) != ResourceSet.RESOURCE_SET__RESOURCES
          || !BuildPhases.isIndexing((Notifier) notification.getNotifier())) {
        return;
      }
      if (notification.getEventType() == Notification.ADD) {
        install((Resource) notification.getNewValue());
      } else if (notification.getEventType() == Notification.ADD_MANY) {
        for (Object resource : (Collection<?>) notification.getNewValue()) {
          install((Resource) resource);
        }
      }
    }
  }

}
