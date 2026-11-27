/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.builder;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.CancelIndicator;

import com.avaloq.tools.ddk.check.compiler.CheckGeneratorConfig;
import com.avaloq.tools.ddk.check.compiler.ICheckGeneratorConfigProvider;
import com.avaloq.tools.ddk.xtext.builder.ConditionalBuilderParticipant;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.inject.Inject;


/**
 * A Builder Participant for the Check Language, that takes care of the plugin extension registry.
 */
public class CheckBuilderParticipant extends ConditionalBuilderParticipant {

  private static final Logger LOGGER = LogManager.getLogger(CheckBuilderParticipant.class);

  @Inject
  private IResourceServiceProvider resourceServiceProvider;

  @Inject
  private CheckContextsGenerator contextsGenerator;

  @Inject
  private CheckTocGenerator tocGenerator;

  @Inject
  private CheckExtensionGenerator extensionGenerator;

  @Inject
  private IStorage2UriMapper mapper;

  @Inject
  private ICheckGeneratorConfigProvider generatorConfigProvider;

  private IProgressMonitor progressMonitor;

  @Override
  public void build(final IBuildContext context, final IProgressMonitor monitor) throws CoreException {
    if (!isEnabled(context)) {
      return;
    }
    this.progressMonitor = monitor;
    super.build(context, monitor);
    for (Delta delta : context.getDeltas()) {
      if (delta.getNew() == null) { // means that a catalog has been deleted
        handleDeletion(delta, context);
      }
    }
  }

  @Override
  protected void handleChangedContents(final Delta delta, final IBuildContext context, final EclipseResourceFileSystemAccess2 fileSystemAccess) throws CoreException {
    if (hasCorrectExtension(delta, resourceServiceProvider)) {
      generateFromSource(delta, context, fileSystemAccess);
      IProject project = RuntimeProjectUtil.getProject(delta.getUri(), mapper);
      if (project == null || !project.isAccessible() || RuntimeProjectUtil.getProject(delta.getUri(), mapper).isHidden()) {
        return; // do nothing, the catalog to be updated might be in a closed and potentially linked project
        // TODO find a better way to prevent updates of linked resources; file.isLinked() returns false!
      }
      try {
        extensionGenerator.changePluginXmlFile(context, delta, progressMonitor);
      } catch (CoreException e) {
        LOGGER.error(e.getMessage(), e);
      }
      CheckGeneratorConfig config = generatorConfigProvider.get(delta.getUri());
      // Generate docu-related files for SCA checks only
      if (config.doGenerateDocumentationForAllChecks() || !config.isGenerateLanguageInternalChecks()) {
        try {
          tocGenerator.updateTocModel(delta.getUri(), context);
        } catch (CoreException e) {
          LOGGER.error(e.getMessage(), e);
        }
        try {
          contextsGenerator.updateContextsFile(delta.getUri(), context);
        } catch (CoreException e) {
          LOGGER.error(e.getMessage(), e);
        }
      }
    }
  }

  /**
   * Stores and generates the catalog of the given delta from its source, since a catalog loaded from binary storage can neither be stored nor generated.
   *
   * @param delta
   *          the delta of the catalog
   * @param context
   *          the build context
   * @param fileSystemAccess
   *          the file system access
   * @throws CoreException
   *           if storing or generating fails
   */
  private void generateFromSource(final Delta delta, final IBuildContext context, final EclipseResourceFileSystemAccess2 fileSystemAccess) throws CoreException {
    URI uri = delta.getUri();
    ResourceSet resourceSet = context.getResourceSet();
    Resource resource = resourceSet.getResource(uri, false);
    if (resource instanceof StorageAwareResource storageAwareResource && storageAwareResource.isLoadedFromStorage()) {
      resource.unload();
    }
    SourceLevelURIsAdapter adapter = SourceLevelURIsAdapter.findInstalledAdapter(resourceSet);
    Set<URI> sourceLevelURIs = adapter == null ? null : adapter.getSourceLevelURIs();
    if (sourceLevelURIs == null || sourceLevelURIs.contains(uri)) {
      super.handleChangedContents(delta, context, fileSystemAccess);
      return;
    }
    Set<URI> withCatalog = new HashSet<>(sourceLevelURIs);
    withCatalog.add(uri);
    adapter.setSourceLevelURIs(withCatalog);
    try {
      super.handleChangedContents(delta, context, fileSystemAccess);
    } finally {
      adapter.setSourceLevelURIs(sourceLevelURIs); // the builder may hold the original set
    }
  }

  /**
   * Removes corresponding entries from plugin.xml.
   *
   * @param delta
   *          delta of deleted resource
   * @param context
   *          build context
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  protected void handleDeletion(final Delta delta, final IBuildContext context) {
    if (hasCorrectExtension(delta, resourceServiceProvider)) {
      try {
        extensionGenerator.handleCatalogDeletion(delta, progressMonitor);
      } catch (CoreException e) {
        LOGGER.error(e.getMessage(), e);
      }
      CheckGeneratorConfig config = generatorConfigProvider.get(delta.getUri());

      if (config.doGenerateDocumentationForAllChecks() || !config.isGenerateLanguageInternalChecks()) {
        try {
          tocGenerator.removeTopicFromTocFile(delta.getUri());
        } catch (CoreException e) {
          LOGGER.error(e.getMessage(), e);
        }
        try {
          contextsGenerator.removeContexts(delta);
        } catch (CoreException e) {
          LOGGER.error(e.getMessage(), e);
        }
      }
    }
  }

  @Override
  protected void saveResourceStorage(final Resource resource, final IFileSystemAccess access) {
    EcoreUtil2.resolveLazyCrossReferences(resource, CancelIndicator.NullImpl);
    super.saveResourceStorage(resource, access);
  }
}
