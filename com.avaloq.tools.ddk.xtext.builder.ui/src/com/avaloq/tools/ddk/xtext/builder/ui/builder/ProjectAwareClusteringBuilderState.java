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

package com.avaloq.tools.ddk.xtext.builder.ui.builder;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.clustering.ClusteringBuilderState;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * A {@link ClusteringBuilderState} that doesn't touch files outside of the project being build.
 * This avoids potential situations in java projects where Xtext resources on the classpath are rebuilt, causing
 * build loops and negatively impacting performance.
 */
@Singleton
public class ProjectAwareClusteringBuilderState extends ClusteringBuilderState {

  private static final Logger LOGGER = Logger.getLogger(ProjectAwareClusteringBuilderState.class);

  @Inject
  private IStorage2UriMapper mapper;

  @Override
  protected void updateMarkers(final Delta delta, final ResourceSet resourceSet, final IProgressMonitor monitor) throws OperationCanceledException {
    Pair<IStorage, IProject> pair = Iterables.getFirst(mapper.getStorages(delta.getUri()), null);
    IStorage storage = pair.getFirst();
    IProject project = pair.getSecond();
    if (project.getFullPath().isPrefixOf(storage.getFullPath())) {
      LOGGER.debug("Processed " + delta.getUri()); //$NON-NLS-1$
      super.updateMarkers(delta, resourceSet, monitor);
    } else {
      LOGGER.debug("Skipped " + delta.getUri()); //$NON-NLS-1$
    }
  }

}
