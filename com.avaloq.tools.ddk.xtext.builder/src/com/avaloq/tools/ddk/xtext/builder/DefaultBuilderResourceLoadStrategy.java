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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Determine when to stop loading resources during build.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CONSTANTS-OFF
public class DefaultBuilderResourceLoadStrategy implements IBuilderResourceLoadStrategy {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(DefaultBuilderResourceLoadStrategy.class);

  /** Default value for target free memory in megabytes. */
  private static final String TARGET_FREE_MEMORY_PROPERTY = "com.avaloq.tools.ddk.xtext.builder.targetFreeMemory"; //$NON-NLS-1$

  /** One megabyte. */
  private static final long ONE_MEGABYTE = 1 << 20;

  /** Default target memory value. This is 10% of 2GB, which was historically used as the default memory settings. */
  private static final long DEFAULT_TARGET_FREE_MEMORY = 200 * ONE_MEGABYTE;

  /** Cluster will always end if memory falls bellow this threshold. */
  private static final long MINIMUM_FREE_MEMORY = 50 * ONE_MEGABYTE;

  /** Attempt to cap cluster when memory reaches this limit, as long as we processed more than the minimum cluster size. */
  private static final long TARGET_FREE_MEMORY = getTargetFreeMemory();

  /** Cluster size. */
  @Inject(optional = true)
  @Named("org.eclipse.xtext.builder.clustering.ClusteringBuilderState.clusterSize")
  private int clusterSize = 20; // NOPMD Cannot be static because of the way Guice injection works.

  /** The Java runtime; needed to get access to memory usage data. */
  private static final Runtime RUNTIME = Runtime.getRuntime();

  /**
   * Gets the target free memory to use during the build.
   *
   * @return the target free memory from the system properties, or a default value if the property is not defined
   */
  private static long getTargetFreeMemory() {
    String targetFreeMemory = System.getProperty(TARGET_FREE_MEMORY_PROPERTY);
    return targetFreeMemory != null ? Long.parseLong(targetFreeMemory) * ONE_MEGABYTE : DEFAULT_TARGET_FREE_MEMORY;
  }

  // note that this setter is required as otherwise the clusterSize field will be marked final
  public void setClusterSize(final int clusterSize) {
    this.clusterSize = clusterSize;
  }

  /**
   * Calculates the number of fully loaded resources of a resource set.
   *
   * @param resourceSet
   *          the resource set
   * @return number of fully loaded resources
   */
  private int getFullyLoadedResources(final ResourceSet resourceSet) {
    final int loadedResources = resourceSet.getResources().size();
    int fullyLoaded = loadedResources;
    for (Resource resource : resourceSet.getResources()) {
      if (resource instanceof ILazyLinkingResource2) {
        ILazyLinkingResource2 lazyLinkingResource = (ILazyLinkingResource2) resource;
        if (!lazyLinkingResource.getModelManager().allModelsLoaded()) {
          fullyLoaded--;
        }
      }
    }
    return fullyLoaded;
  }

  /** {@inheritDoc} */
  @Override
  public boolean mayProcessAnotherResource(final ResourceSet resourceSet, final int alreadyProcessed) {
    if (alreadyProcessed == 0) {
      return true;
    }

    final long freeMemory = RUNTIME.freeMemory();
    if (freeMemory < MINIMUM_FREE_MEMORY) {
      return false;
    } else if (alreadyProcessed < clusterSize) {
      return true; // Process at least up to cluster size
    }

    if (freeMemory < TARGET_FREE_MEMORY) {
      // Running GC here might free memory, but would not significantly speed up processing.
      // We may use slightly smaller clusters than strictly necessary without GC, though.
      if (LOGGER.isInfoEnabled()) {
        final int loadedResources = resourceSet.getResources().size();
        final int fullyLoaded = getFullyLoadedResources(resourceSet);
        LOGGER.info("Cluster capped after " + alreadyProcessed + "/" + loadedResources + "/" + fullyLoaded + " resources; " + (freeMemory / ONE_MEGABYTE) + "/"
            + (RUNTIME.totalMemory() / ONE_MEGABYTE) + " memory");
      }
      return false;
    }
    return true; // Keep on loading resources into this resource set
  }
}

