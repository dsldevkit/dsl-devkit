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
package com.avaloq.tools.ddk.xtext.builder.resourceloader;

import org.eclipse.xtext.builder.resourceloader.IResourceLoader;
import org.eclipse.xtext.builder.resourceloader.ResourceLoaderProviders.AbstractResourceLoaderProvider;
import org.eclipse.xtext.builder.resourceloader.SerialResourceLoader;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;


/**
 * Helper class to define resource loaders.
 * TODO: Delete once https://bugs.eclipse.org/bugs/show_bug.cgi?id=360445 is solved.
 */
public final class ResourceLoaderProviders {

  private ResourceLoaderProviders() {
    // nothing
  }

  /**
   * Returns a provider for parallel loading and the given degree of parallelization.
   *
   * @param nrOfThreads
   *          degree
   * @return parallel resource loader
   */
  public static Provider<IResourceLoader> getParallelLoader(final int nrOfThreads) {
    int nProcessors = Runtime.getRuntime().availableProcessors();
    // CHECKSTYLE:CONSTANTS-OFF
    int nThreads = Math.max(2, Math.min(4, nProcessors));
    // CHECKSTYLE:CONSTANTS-ON
    return getParallelLoader(nThreads, 0);
  }

  /**
   * Returns a provider for parallel loading and the given degree of parallelization.
   *
   * @param nrOfThreads
   *          degree
   * @param bufferSize
   *          buffer of loader
   * @return parallel resource loader
   */
  public static Provider<IResourceLoader> getParallelLoader(final int nrOfThreads, final int bufferSize) {
    return new AbstractResourceLoaderProvider() {

      @Inject
      private Injector injector;

      @Override
      public IResourceLoader get() {
        ParallelResourceLoader resourceLoader = new ParallelResourceLoader(getResourceSetProvider(), getResourceSorter(), nrOfThreads, bufferSize);
        injector.injectMembers(resourceLoader);
        return resourceLoader;
      }
    };
  }

  public static Provider<IResourceLoader> getSerialLoader() {
    return new AbstractResourceLoaderProvider() {
      @Override
      public IResourceLoader get() {
        return new SerialResourceLoader(getResourceSetProvider(), getResourceSorter());
      }
    };
  }

}
