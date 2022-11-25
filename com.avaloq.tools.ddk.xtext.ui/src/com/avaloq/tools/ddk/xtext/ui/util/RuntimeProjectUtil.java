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
package com.avaloq.tools.ddk.xtext.ui.util;

import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.Pair;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * Provides helper methods for BuilderParticipant subclasses.
 */
public final class RuntimeProjectUtil {

  private static final Logger LOGGER = LogManager.getLogger(RuntimeProjectUtil.class);

  /**
   * Private constructor for utility classes.
   */
  private RuntimeProjectUtil() {}

  /**
   * Returns the file {@link IFile} based on its {@link URI}.
   *
   * @param uri
   *          the URI of the resource for which an IFile is to be returned
   * @param mapper
   *          class returning e.g. set of storages {@link IStorage} matching given URI; injected by concrete BuilderParticipant
   * @return the file associated with given URI
   */
  public static IFile findFileStorage(final URI uri, final IStorage2UriMapper mapper) {
    Iterable<Pair<IStorage, IProject>> storages = mapper.getStorages(uri);
    try {
      Pair<IStorage, IProject> fileStorage = Iterables.find(storages, new Predicate<Pair<IStorage, IProject>>() {
        @Override
        public boolean apply(final Pair<IStorage, IProject> input) {
          IStorage storage = input.getFirst();
          if (storage instanceof IFile) {
            return true;
          }
          return false;
        }
      });

      return (IFile) fileStorage.getFirst();
    } catch (NoSuchElementException e) {
      LOGGER.debug("Cannot find file storage for " + uri); //$NON-NLS-1$
      return null;
    }
  }

  /**
   * Returns the project containing the file indicated by a URI.
   *
   * @param uri
   *          URI containing path from which the project name is extracted
   * @param mapper
   *          class returning e.g. set of storages {@link IStorage} matching given URI; injected by concrete BuilderParticipant
   * @return project {@link IProject} associated with given URI
   */
  public static IProject getProject(final URI uri, final IStorage2UriMapper mapper) {
    final IFile file = findFileStorage(uri, mapper);
    return file == null ? null : file.getProject();
  }

  /**
   * Extracts the project name from the given resource.
   *
   * @param resource
   *          specifies rules for generation; output (generated) files should be part of the same project that contains this resource
   * @param mapper
   *          used to obtain a file corresponding to the given resource
   * @return project name
   */
  public static String getPathProject(final Resource resource, final IStorage2UriMapper mapper) {
    StringBuilder builder = new StringBuilder();
    IFile storage = findFileStorage(resource.getURI(), mapper);
    if (storage != null) {
      java.net.URI absoluteURI = storage.getLocationURI();
      if (absoluteURI == null) {
        String workspacePath = storage.getWorkspace().getRoot().getLocationURI().getPath();
        builder.append(workspacePath);
      } else {
        String absolutePath = absoluteURI.getPath();
        String relativePath = resource.getURI().toPlatformString(true);
        String workspacePath = absolutePath.replace(relativePath, StringUtils.EMPTY);
        builder.append(workspacePath);
      }
      builder.append('/');
      builder.append(storage.getProject().getName());
      return builder.toString();
    } else {
      return null;
    }
  }

}
