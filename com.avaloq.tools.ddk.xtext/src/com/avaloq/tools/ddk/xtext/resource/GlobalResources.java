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

package com.avaloq.tools.ddk.xtext.resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * A singleton wrapper around a {@link org.eclipse.emf.ecore.resource.ResourceSet} containing {@link Resource resources} which must only be loaded once in a
 * JVM.
 * <p>
 * The idea is that other resource sets delegate the load operation to {@link #getResource(URI)} for all global resources.
 * <p>
 * Access the ressources is made threadsafe so that (suspected) lazy adding of ressources does not cause concurrent access errors with existing reads.
 */
public final class GlobalResources {

  /**
   * Specialized implementation which uses a {@link ResourceSetImpl.MappedResourceLocator} and also overrides
   * {@link ResourceSetImpl#delegatedGetResource(URI, boolean)} to not check {@link org.eclipse.emf.ecore.resource.ResourceSet#getPackageRegistry()}.
   */
  private static final class GlobalResourceSet extends ResourceSetImpl {
    GlobalResourceSet() {
      new ResourceSetImpl.MappedResourceLocator(this);
    }

    @Override
    protected Resource delegatedGetResource(final URI uri, final boolean loadOnDemand) {
      return null;
    }
  }

  public static final GlobalResources INSTANCE = new GlobalResources();

  private final ResourceSetImpl resourceSet = new GlobalResourceSet();

  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();

  private GlobalResources() {
    // private
  }

  /**
   * Adds the given resource to the set of global resources.
   *
   * @param resource
   *          resource to add, must not be {@code null}
   */
  public void addResource(final Resource resource) {
    writeLock.lock();
    try {
      resourceSet.getResources().add(resource);
    } finally {
      writeLock.unlock();
    }
  }

  /**
   * Retrieves the global resource with the given URI.
   *
   * @param uri
   *          URI, must not be {@code null}
   * @return corresponding resource or {@code null}
   */
  public Resource getResource(final URI uri) {
    readLock.lock();
    try {
      return resourceSet.getResource(uri, false);
    } finally {
      readLock.unlock();
    }
  }
}
