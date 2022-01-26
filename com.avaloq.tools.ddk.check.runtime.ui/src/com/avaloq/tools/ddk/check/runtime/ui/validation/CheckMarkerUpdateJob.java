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
package com.avaloq.tools.ddk.check.runtime.ui.validation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Set;

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
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Iterables;


/**
 * Revalidates a set of resources as a background {@link org.eclipse.core.runtime.jobs.Job job}.
 */
public class CheckMarkerUpdateJob extends Job {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(CheckMarkerUpdateJob.class);

  private final Set<URI> uris;

  private final Registry serviceProviderRegistry;

  /** The Validation Mode. */
  private final CheckMode checkMode;

  /**
   * Instantiates a new marker update job in which all sources identified by given
   * set of URIs are validated.
   *
   * @param name
   *          the name of the job, must not be {@code null}
   * @param uris
   *          the set of URIs to be validated, must not be {@code null}
   */
  public CheckMarkerUpdateJob(final String name, final Set<URI> uris) {
    super(name);

    this.serviceProviderRegistry = IResourceServiceProvider.Registry.INSTANCE;
    this.uris = uris;
    this.checkMode = CheckMode.ALL;
  }

  /**
   * Gets an {@link IFile} from the {@link IStorage2UriMapper} corresponding to given {@link URI}. If none
   * could be found, <code>null</code> is returned.
   *
   * @param storage2UriMapper
   *          the storage to URI mapper, may be {@code null}
   * @param fileUri
   *          the URI, must not be {@code null}
   * @return the file from the storage to URI mapper, or {@code null} if no match found
   */
  private IFile getFileFromStorageMapper(final IStorage2UriMapper storage2UriMapper, final URI fileUri) {
    if (storage2UriMapper == null) {
      return null; // Should not occur
    }

    for (Pair<IStorage, IProject> storage : storage2UriMapper.getStorages(fileUri)) {
      if (storage.getFirst() instanceof IFile) {
        return (IFile) storage.getFirst();
      }
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(MessageFormat.format("Could not find storage for URI {0}", fileUri.toString())); //$NON-NLS-1$
    }
    return null;
  }

  /**
   * Gets the resource set. Note that not all sources must be part of the same resource set.
   *
   * @param storage2UriMapper
   *          the storage2 uri mapper, must not be {@code null}
   * @param uri
   *          the uri, must not be {@code null}
   * @return the resource set, may be {@code null}
   */
  private ResourceSet getResourceSet(final IStorage2UriMapper storage2UriMapper, final URI uri) {
    Iterable<Pair<IStorage, IProject>> storages = storage2UriMapper.getStorages(uri);
    if (!Iterables.isEmpty(storages)) {
      IProject project = Iterables.get(storages, 0).getSecond();
      XtextResourceSetProvider resourceSetProvider = this.serviceProviderRegistry.getResourceServiceProvider(uri).get(XtextResourceSetProvider.class);
      return resourceSetProvider.get(project);
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  protected IStatus run(final IProgressMonitor monitor) {

    // Let's start (number of task = number of resource * 2 (loading + validating))
    monitor.beginTask("", 2 * this.uris.size()); //$NON-NLS-1$

    for (final URI uri : this.uris) {
      // Last chance to cancel before next validation
      if (monitor.isCanceled()) {
        return Status.CANCEL_STATUS;
      }

      final IResourceServiceProvider serviceProvider = serviceProviderRegistry.getResourceServiceProvider(uri);
      if (serviceProvider == null) {
        // This may happen for non-Xtext resources in ice entities
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug(MessageFormat.format("Could not validate {0}: no resource service provider found", uri.toString())); //$NON-NLS-1$
        }
        continue; // Skip to next URI
      }

      final IResourceValidator resourceValidator = serviceProvider.getResourceValidator();
      final IStorage2UriMapper uriMapper = serviceProvider.get(IStorage2UriMapper.class);
      final MarkerCreator markerCreator = serviceProvider.get(MarkerCreator.class);

      // Get the file; only local files will be re-validated, derived files are ignored
      final IFile iFile = getFileFromStorageMapper(uriMapper, uri);
      if (iFile == null) {
        continue; // no storage mapping found for this URI
      }

      if (resourceValidator == null) {
        LOGGER.error(MessageFormat.format("Could not validate {0}: no resource validator found", iFile.getName())); //$NON-NLS-1$
      } else if (iFile != null) {
        monitor.subTask("loading " + iFile.getName()); //$NON-NLS-1$

        // Don't try to evaluate resource set before it has been checked that the storage provider contains a mapping
        // for current uri
        final ResourceSet resourceSet = getResourceSet(uriMapper, uri);

        // Load the corresponding resource
        boolean loaded = false;
        Resource eResource = null;
        try {
          eResource = resourceSet.getResource(uri, false);
          if (eResource == null || !eResource.isLoaded()) {
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
            validateAndCreateMarkers(resourceValidator, markerCreator, iFile, eResource, monitor);
            LOGGER.debug("Validated " + uri); //$NON-NLS-1$
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
   * Validate the given resource and create the corresponding markers. The CheckMode is a constant calculated from the constructor
   * parameters.
   *
   * @param resourceValidator
   *          the resource validator, must not be {@code null}
   * @param markerCreator
   *          the marker creator, may be {@code null}
   * @param file
   *          the EFS file, must not be {@code null}
   * @param resource
   *          the EMF resource, must not be {@code null}
   * @param monitor
   *          the monitor, must not be {@code null}
   */
  protected void validateAndCreateMarkers(final IResourceValidator resourceValidator, final MarkerCreator markerCreator, final IFile file, final Resource resource, final IProgressMonitor monitor) {
    try {
      monitor.subTask("validating " + file.getName()); //$NON-NLS-1$
      final Collection<Issue> issues = validateResource(resourceValidator, resource, monitor);
      createMarkers(markerCreator, file, issues);
    } catch (final CoreException e) {
      LOGGER.error(e.getMessage(), e);
    } finally {
      monitor.worked(1);
    }
  }

  /**
   * Creates the marker on a given file based on the collection of issues.
   *
   * @param markerCreator
   *          the marker creator, may be {@code null}
   * @param file
   *          the EFS file, must not be {@code null}
   * @param issues
   *          the collection of issues, must not be {@code null}
   * @throws CoreException
   */
  private void createMarkers(final MarkerCreator markerCreator, final IFile file, final Collection<Issue> issues) throws CoreException {
    file.deleteMarkers(MarkerTypes.FAST_VALIDATION, true, IResource.DEPTH_ZERO);
    file.deleteMarkers(MarkerTypes.NORMAL_VALIDATION, true, IResource.DEPTH_ZERO);
    file.deleteMarkers(MarkerTypes.EXPENSIVE_VALIDATION, true, IResource.DEPTH_ZERO);
    if (markerCreator != null) {
      for (final Issue issue : issues) {
        markerCreator.createMarker(issue, file, MarkerTypes.forCheckType(issue.getType()));
      }
    } else {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.error("Could not create markers. The marker creator is null."); //$NON-NLS-1$
      }
    }
  }

  /**
   * Validates the given resource and returns a collection of {@link Issue} based on validation.
   *
   * @param resourceValidator
   *          the resource validator, must not be {@code null}
   * @param resource
   *          the EMF resource, must not be {@code null}
   * @param monitor
   *          the monitor, must not be {@code null}
   * @return the collection of {@link Issue}, never {@code null}
   */
  protected Collection<Issue> validateResource(final IResourceValidator resourceValidator, final Resource resource, final IProgressMonitor monitor) {
    return resourceValidator.validate(resource, checkMode, getCancelIndicator(monitor));
  }

  /**
   * Obtain a new cancel indicator for the given monitor.
   *
   * @param monitor
   *          the monitor, must not be {@code null}
   * @return a new cancel indicator, never {@code null}
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
