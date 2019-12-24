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

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.internal.events.ResourceDeltaFactory;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.watson.ElementTree;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.BuildType;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.builder.impl.Messages;
import org.eclipse.xtext.builder.impl.QueuedBuildData;
import org.eclipse.xtext.builder.impl.RegistryBuilderParticipant;
import org.eclipse.xtext.builder.impl.ToBeBuilt;
import org.eclipse.xtext.builder.impl.ToBeBuiltComputer;
import org.eclipse.xtext.builder.impl.XtextBuilder;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * Xtext builder. Modifications:
 * <ul>
 * <li>Doesn't start builds when the workbench is already shutting down.</li>
 * <li>Doesn't start builds for empty change sets.</li>
 * <li>Introduces method getToBeBuiltComputer().</li>
 * <li>Fix for rebuilding generated sources (xtext bugzilla https://bugs.eclipse.org/bugs/show_bug.cgi?id=452399).</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class RebuildingXtextBuilder extends XtextBuilder {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(RebuildingXtextBuilder.class);

  @Inject
  private ToBeBuiltComputer toBeBuiltComputer;

  @Inject
  private IBuilderState builderState;

  @Inject
  private QueuedBuildData queuedBuildData;

  @Inject
  private RegistryBuilderParticipant participant;

  private int rebuilds;

  protected ToBeBuiltComputer getToBeBuiltComputer() {
    return toBeBuiltComputer;
  }

  @Override
  protected void incrementalBuild(final IResourceDelta delta, final IProgressMonitor monitor) throws CoreException {
    final SubMonitor progress = SubMonitor.convert(monitor, Messages.XtextBuilder_CollectingResources, 2);
    progress.subTask(Messages.XtextBuilder_CollectingResources);

    final ToBeBuilt toBeBuilt = new ToBeBuilt();
    IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
      @Override
      public boolean visit(final IResourceDelta delta) throws CoreException {
        if (progress.isCanceled()) {
          throw new OperationCanceledException();
        }
        if (delta.getResource() instanceof IProject) {
          return delta.getResource() == getProject();
        }
        if (delta.getResource() instanceof IStorage) {
          if (delta.getKind() == IResourceDelta.REMOVED) {
            return getToBeBuiltComputer().removeStorage(null, toBeBuilt, (IStorage) delta.getResource());
          } else if (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED) {
            return getToBeBuiltComputer().updateStorage(null, toBeBuilt, (IStorage) delta.getResource());
          }
        }
        return true;
      }
    };
    delta.accept(visitor);
    if (progress.isCanceled()) {
      throw new OperationCanceledException();
    }
    progress.worked(1);
    doBuild(toBeBuilt, progress.newChild(1), BuildType.INCREMENTAL);
  }

  @Override
  protected void fullBuild(final IProgressMonitor monitor, final boolean isRecoveryBuild) throws CoreException {
    SubMonitor progress = SubMonitor.convert(monitor, 2);

    IProject project = getProject();
    ToBeBuilt toBeBuilt = isRecoveryBuild ? getToBeBuiltComputer().updateProjectNewResourcesOnly(project, progress.newChild(1))
        : getToBeBuiltComputer().updateProject(project, progress.newChild(1));
    doBuild(toBeBuilt, progress.newChild(1), isRecoveryBuild ? BuildType.RECOVERY : BuildType.FULL);
  }

  @Override
  protected void clean(final IProgressMonitor monitor) throws CoreException {
    SubMonitor progress = SubMonitor.convert(monitor, 2);
    try {
      ToBeBuilt toBeBuilt = getToBeBuiltComputer().removeProject(getProject(), progress.newChild(1));
      doClean(toBeBuilt, progress.newChild(1));
    } finally {
      if (monitor != null) {
        monitor.done();
      }
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings("rawtypes")
  @Override
  protected IProject[] build(final int kind, final Map args, final IProgressMonitor monitor) throws CoreException { // NOPMD (copied
    rebuilds = 0;
    if (PlatformUI.getWorkbench().isClosing()) {
      // We don't trigger builds while the workbench is closing because it leads to Guice ProvisionException(s),
      // if individual plug-ins have already been stopped.
      throw new OperationCanceledException("Workbench is closing.");//$NON-NLS-1$
      // ... thus eclipse will know when it starts again that a full build is required.
    }
    return super.build(kind, args, monitor);
  }

  /**
   * Like the super implementation, except the build is not triggered if
   * both toBeDeleted and toBeUpdated are empty. {@inheritDoc}
   */
  @SuppressWarnings("nls")
  @Override
  protected void doBuild(final ToBeBuilt toBeBuilt, final IProgressMonitor monitor, final BuildType type) throws CoreException {
    if (toBeBuilt.getToBeDeleted().size() != 0 || toBeBuilt.getToBeUpdated().size() != 0) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Starting " + type.name() + " build:" + "\ndeleted(" + toBeBuilt.getToBeDeleted().size() + ")=" + toBeBuilt.getToBeDeleted().toString()
            + "\nupdated(" + toBeBuilt.getToBeUpdated().size() + ")=" + toBeBuilt.getToBeUpdated().toString());
      }

      SubMonitor progress = SubMonitor.convert(monitor, 1 + 1 + 1); // build + participant + rebuild

      ResourceSet resourceSet = getResourceSetProvider().get(getProject());
      resourceSet.getLoadOptions().put(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE, Boolean.TRUE);
      if (resourceSet instanceof ResourceSetImpl && ((ResourceSetImpl) resourceSet).getURIResourceMap() == null) {
        ((ResourceSetImpl) resourceSet).setURIResourceMap(Maps.<URI, Resource> newHashMap());
      }
      BuildData buildData = new BuildData(getProject().getName(), resourceSet, toBeBuilt, queuedBuildData);
      ImmutableList<Delta> deltas = builderState.update(buildData, progress.newChild(1));
      if (participant != null) {
        final BuildContext buildContext = new BuildContext(this, resourceSet, deltas, type);
        // remember the current workspace tree
        final ElementTree oldTree = ((Workspace) ResourcesPlugin.getWorkspace()).getElementTree();
        oldTree.immutable();
        participant.build(buildContext, progress.newChild(1));
        if (buildContext.isRebuildRequired() && rebuilds++ <= 2) {
          final ElementTree newTree = ((Workspace) ResourcesPlugin.getWorkspace()).getElementTree();
          newTree.immutable();
          final ResourceDelta generatedDelta = ResourceDeltaFactory.computeDelta((Workspace) ResourcesPlugin.getWorkspace(), oldTree, newTree, getProject().getFullPath(), -1);
          // rebuild the generated delta
          ResourcesPlugin.getWorkspace().checkpoint(false);
          incrementalBuild(generatedDelta, progress.newChild(1));
        }
      } else {
        progress.worked(2);
      }
      resourceSet.getResources().clear();
      resourceSet.eAdapters().clear();
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Build done.");
      }
    } else if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Ignoring empty " + type.name() + " build.");
    }
  }
}
