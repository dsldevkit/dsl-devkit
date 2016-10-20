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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Lists;


/**
 * The Class ValidMarkerUpdateJob.
 */
public class ValidMarkerUpdateJob extends Job {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(ValidMarkerUpdateJob.class);

  /** The file extensions. */
  private final String fileExtensions;

  /** The resource set. */
  private final ResourceSet resourceSet;

  /** The marker creator. */
  private final MarkerCreator markerCreator;

  /** The resource descriptions. */
  private final IResourceDescriptions resourceDescriptions;

  /** The resource service provider. */
  private final IResourceServiceProvider resourceServiceProvider;

  /** True if expensive validations are performed, too. */
  private final boolean performExpensiveValidation;

  /** The Validation Mode. */
  private final CheckMode checkMode;

  /** The storage to URI mapper. */
  private final IStorage2UriMapper storage2UriMapper;

  /**
   * Instantiates a new valid marker update job.
   *
   * @param name
   *          the name
   * @param fileExtensions
   *          the file extensions
   * @param resourceSet
   *          the resource set
   * @param markerCreator
   *          the marker creator
   * @param resourceDescriptions
   *          the resource descriptions
   * @param resourceServiceProvider
   *          the resource service provider
   * @param performExpensiveValidation
   *          true if expensive validation should be performed, false otherwise
   */
  public ValidMarkerUpdateJob(final String name, final String fileExtensions, final ResourceSet resourceSet, final MarkerCreator markerCreator, final IResourceDescriptions resourceDescriptions, final IResourceServiceProvider resourceServiceProvider, final boolean performExpensiveValidation, final IStorage2UriMapper storage2UriMapper) {
    super(name + " " + fileExtensions); //$NON-NLS-1$

    this.fileExtensions = fileExtensions;
    this.resourceSet = resourceSet;
    this.markerCreator = markerCreator;
    this.resourceDescriptions = resourceDescriptions;
    this.resourceServiceProvider = resourceServiceProvider;
    this.performExpensiveValidation = performExpensiveValidation;
    this.checkMode = performExpensiveValidation ? CheckMode.ALL : CheckMode.NORMAL_AND_FAST;
    this.storage2UriMapper = storage2UriMapper;
  }

  /**
   * Gets an {@link IFile} from the {@link IStorage2UriMapper} corresponding to given {@link URI}. If none
   * could be found, <code>null</code> is returned.
   *
   * @param uri
   *          the URI
   * @return the file from the storage to URI mapper or <code>null</code> if no match found
   */
  private IFile getFileFromStorageMapper(final URI uri) {
    if (storage2UriMapper == null) {
      return null; // Should not occur
    }

    for (Pair<IStorage, IProject> storage : storage2UriMapper.getStorages(uri)) {
      if (storage.getFirst() instanceof IFile) {
        return (IFile) storage.getFirst();
      }
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(MessageFormat.format("Could not find storage for URI {0}", uri.toString())); //$NON-NLS-1$
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  protected IStatus run(final IProgressMonitor monitor) {
    final IResourceValidator resourceValidator = resourceServiceProvider.getResourceValidator();

    // Object the list of resources
    final List<URI> uris = getResourceDescriptionURIs();

    // Let's start (number of task = number of resource * 2 (loading + validating))
    monitor.beginTask("", uris.size() * 2); //$NON-NLS-1$

    for (final URI uri : uris) {
      // Last chance to cancel before next validation
      if (monitor.isCanceled()) {
        return Status.CANCEL_STATUS;
      }

      // Get the file; only local files will be re-validated, derived files are ignored
      final IFile iFile = getFileFromStorageMapper(uri);
      if (iFile != null && !iFile.isDerived(IFile.CHECK_ANCESTORS)) {
        monitor.subTask("loading " + iFile.getName()); //$NON-NLS-1$

        // Load the corresponding resource
        boolean loaded = false;
        Resource eResource = null;
        try {
          eResource = resourceSet.getResource(uri, false);
          if ((eResource == null) || (eResource != null && !eResource.isLoaded())) {
            // if the resource does not exist in the resource set, or is not loaded yet
            // load it.
            eResource = resourceSet.getResource(uri, true);
            loaded = true;
          }
          monitor.worked(1);
          // CHECKSTYLE:OFF
        } catch (final RuntimeException e) {
          // CHECKSTYLE:ON
          LOGGER.error(MessageFormat.format("{0} could not be validated.", iFile.getName()), e); //$NON-NLS-1$
        } finally {
          if (eResource != null) {
            validate(resourceValidator, iFile, eResource, monitor);
            if (loaded) { // NOPMD
              // unload any resource that was previously loaded as part of this loop.
              eResource.unload();
            }
          }
        }
      }
    }

    monitor.done();

    return Status.OK_STATUS;
  }

  /**
   * Gets the resource description URIs.
   *
   * @return the resource description URIs
   */
  private List<URI> getResourceDescriptionURIs() {
    final List<URI> uris = Lists.<URI> newArrayList();
    final List<String> extensions = Lists.newArrayList(this.fileExtensions.toUpperCase().split("\\,")); //$NON-NLS-1$
    for (final IResourceDescription description : this.resourceDescriptions.getAllResourceDescriptions()) {
      final URI uri = description.getURI();
      if (extensions.contains(uri.fileExtension().toUpperCase())) {
        uris.add(uri);
      }
    }
    return uris;
  }

  /**
   * Validate the given resource and create the corresponding markers. The CheckMode is a constant calculated from the constructor
   * parameters.
   *
   * @param resourceValidator
   *          the resource validator (not null)
   * @param file
   *          the EFS file (not null)
   * @param resource
   *          the EMF resource (not null)
   * @param monitor
   *          the monitor (not null)
   */
  protected void validate(final IResourceValidator resourceValidator, final IFile file, final Resource resource, final IProgressMonitor monitor) {
    try {
      monitor.subTask("validating " + file.getName()); //$NON-NLS-1$

      final List<Issue> list = resourceValidator.validate(resource, checkMode, getCancelIndicator(monitor));
      if (list != null) {
        // resourceValidator.validate returns null if canceled (and not an empty list)
        file.deleteMarkers(MarkerTypes.FAST_VALIDATION, true, IResource.DEPTH_ZERO);
        file.deleteMarkers(MarkerTypes.NORMAL_VALIDATION, true, IResource.DEPTH_ZERO);
        if (performExpensiveValidation) {
          file.deleteMarkers(MarkerTypes.EXPENSIVE_VALIDATION, true, IResource.DEPTH_ZERO);
        }
        for (final Issue issue : list) {
          markerCreator.createMarker(issue, file, MarkerTypes.forCheckType(issue.getType()));
        }
      }
    } catch (final CoreException e) {
      LOGGER.error(e.getMessage(), e);
    } finally {
      monitor.worked(1);
    }
  }

  /**
   * Obtain a new cancel indicator for the given monitor.
   *
   * @param monitor
   *          the monitor, not null
   * @return a new cancel indicator
   */
  private CancelIndicator getCancelIndicator(final IProgressMonitor monitor) {
    return new CancelIndicator() {
      @Override
      public boolean isCanceled() {
        return monitor.isCanceled();
      }
    };
  }

}
