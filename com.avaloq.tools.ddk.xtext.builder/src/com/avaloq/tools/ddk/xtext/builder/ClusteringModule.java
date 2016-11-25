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

import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipse.xtext.service.SingletonBinding;

import com.avaloq.tools.ddk.xtext.builder.resourceloader.ResourceLoaderProviders;
import com.google.inject.Binder;
import com.google.inject.name.Names;


/**
 * Override parts of the xText default Guice module to use our own builder runtime with some optimizations.
 */
public class ClusteringModule extends AbstractGenericModule {

  /** Parallelization degree of 2 to 4. */
  private static final int PARALLEL_DEGREE = Math.max(2, Math.min(Runtime.getRuntime().availableProcessors(), 4));

  /**
   * Provide a default binding for the builder state.
   *
   * @return The class to instantiate for the builder state
   */
  public Class<? extends IBuilderState> bindIBuilderState() {
    return MonitoredClusteringBuilderState.class;
  }

  /**
   * Provide a default binding for the project builder.
   *
   * @return The class to instantiate for the project builder
   */
  public Class<? extends IncrementalProjectBuilder> bindIncrementalProjectBuilder() {
    return RebuildingXtextBuilder.class;
  }

  /**
   * Provide a default binding for the registry builder participant.
   *
   * @return The class to instantiate for the registry builder participant
   */
  @SingletonBinding
  public Class<? extends org.eclipse.xtext.builder.impl.RegistryBuilderParticipant> bindRegistryBuilderParticipant() {
    return RegistryBuilderParticipant.class;
  }

  /**
   * Configure resource loaders.
   *
   * @param binder
   *          binder
   */
  public void configureResourceLoaders(final Binder binder) {
    binder.bind(IResourceLoader.class).toProvider(ResourceLoaderProviders.getParallelLoader(PARALLEL_DEGREE));
    binder.bind(IResourceLoader.class).annotatedWith(Names.named(MonitoredClusteringBuilderState.RESOURCELOADER_GLOBAL_INDEX)).toProvider(ResourceLoaderProviders.getParallelLoader(PARALLEL_DEGREE));
    binder.bind(IResourceLoader.class).annotatedWith(Names.named(MonitoredClusteringBuilderState.RESOURCELOADER_CROSS_LINKING)).toProvider(ResourceLoaderProviders.getParallelLoader(PARALLEL_DEGREE));
  }
}
