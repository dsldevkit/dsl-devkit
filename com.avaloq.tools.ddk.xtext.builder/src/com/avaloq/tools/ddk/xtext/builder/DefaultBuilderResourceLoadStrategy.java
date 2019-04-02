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
import org.eclipse.emf.ecore.resource.ResourceSet;

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

  /** Property for setting minimum free memory setting. */
  private static final String MIN_FREE_MEMORY_PROPERTY = "com.avaloq.tools.ddk.xtext.builder.minFreeMemory"; //$NON-NLS-1$

  /** Property for setting the maximal cluster size. */
  private static final String MAX_CLUSTER_SIZE_PROPERTY = "com.avaloq.tools.ddk.xtext.builder.maxClusterSize"; //$NON-NLS-1$

  /** Default value for the maximal cluster size. */
  private static final int DEFAULT_MAX_CLUSTER_SIZE = 50000;

  /** Maximal cluster size. */
  private static final int MAX_CLUSTER_SIZE = getMaximalClusterSize();

  /** One megabyte. */
  private static final long ONE_MEGABYTE = 1 << 20;

  /** Default target memory value. This is 10% of 2GB, which was historically used as the default memory settings. */
  private static final long DEFAULT_TARGET_FREE_MEMORY = 200 * ONE_MEGABYTE;

  /** Default value for MINIMUM_FREE_MEMORY. */
  private static final long DEFAULT_MIN_FREE_MEMORY = 100 * ONE_MEGABYTE;

  /** Cluster will always end if memory falls bellow this threshold. */
  private static final long MINIMUM_FREE_MEMORY = getMinimumFreeMemory();

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

  /**
   * Returns the value for the maximal cluster size.
   *
   * @return a value set by the corresponding system property, or a default value if the property is not set
   */
  private static int getMaximalClusterSize() {
    String maxClusterSize = System.getProperty(MAX_CLUSTER_SIZE_PROPERTY);
    return maxClusterSize != null ? Integer.parseInt(maxClusterSize) : DEFAULT_MAX_CLUSTER_SIZE;
  }

  /**
   * Returns the value for the minimum memory property..
   *
   * @return a value set by the corresponding system property, or a default value if the property is not set
   */
  private static long getMinimumFreeMemory() {
    String minimumMemory = System.getProperty(MIN_FREE_MEMORY_PROPERTY);
    return minimumMemory != null ? Long.parseLong(minimumMemory) : DEFAULT_MIN_FREE_MEMORY;
  }

  // note that this setter is required as otherwise the clusterSize field will be marked final
  public void setClusterSize(final int clusterSize) {
    this.clusterSize = clusterSize;
  }

  /** {@inheritDoc} */
  @Override
  public boolean mayProcessAnotherResource(final ResourceSet resourceSet, final int alreadyProcessed) {
    if (alreadyProcessed == 0) {
      return true;
    }

    if (MAX_CLUSTER_SIZE != 0 && alreadyProcessed >= MAX_CLUSTER_SIZE) {
      // Return false if we have reached some extreme size. MaxClusterSize == 0 means cluster size is not restricted (not a default setting!)
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("Cluster capped after reaching the maximal size of " + MAX_CLUSTER_SIZE + " resources. Current size: " + alreadyProcessed);
      }
      return false;
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
        LOGGER.info("Cluster capped after " + alreadyProcessed + "/" + loadedResources + " resources; " + (freeMemory / ONE_MEGABYTE) + "/"
            + (RUNTIME.totalMemory() / ONE_MEGABYTE) + " memory");
      }
      return false;
    }
    return true; // Keep on loading resources into this resource set
  }
}
