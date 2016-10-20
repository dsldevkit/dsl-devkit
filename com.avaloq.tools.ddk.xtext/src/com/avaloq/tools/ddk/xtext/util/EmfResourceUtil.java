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
package com.avaloq.tools.ddk.xtext.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * The Class EmfResourceUtil provides utility methods for EMF resources.
 */
public final class EmfResourceUtil {

  private static final Logger LOGGER = Logger.getLogger(EmfResourceUtil.class);

  /** Prevents instantiation. */
  private EmfResourceUtil() {}

  /**
   * Gets the file name from the given URI.
   *
   * @param uri
   *          the uri, may not be null
   * @return the the file name including file extension
   */
  public static String getFileName(final URI uri) {
    return URI.decode(uri.lastSegment());
  }

  /**
   * Gets the file name without file extension from the URI of the resource containing the given {@link org.eclipse.emf.ecore.EObject EObject} (provided the
   * owning object is contained in a file resource).
   *
   * @param object
   *          the object from which the containing resource is inspected, may not be null
   * @return the file name <em>excluding</em> file extension
   */
  public static String getFileNameWithoutExtension(final EObject object) {
    return getFileNameWithoutExtension(object.eIsProxy() ? ((InternalEObject) object).eProxyURI() : object.eResource().getURI());
  }

  /**
   * Gets the file name without file extension from the given URI.
   *
   * @param uri
   *          the uri, may not be null
   * @return the file name <em>excluding</em> file extension
   */
  public static String getFileNameWithoutExtension(final URI uri) {
    String fileName = uri.lastSegment();
    int dotIdx = fileName.lastIndexOf('.');
    return URI.decode(dotIdx == -1 ? fileName : fileName.substring(0, dotIdx));
  }

  /**
   * Gets the file extension from the given URI.
   *
   * @param uri
   *          the uri, may not be null
   * @return the file extension
   */
  public static String getFileExtension(final URI uri) {
    String fileName = uri.lastSegment();
    int dotIdx = fileName.lastIndexOf('.');
    return URI.decode(dotIdx == -1 ? "" : fileName.substring(dotIdx + 1)); //$NON-NLS-1$
  }

  /**
   * Load the given URI and return it's resource.
   *
   * @param uri
   *          URI to load, may not be {@code null}
   * @param resourceSet
   *          {@ResourceSet} context
   * @return {@Resource} loaded if it exists, empty resource otherwise
   */
  public static Resource loadURI(final URI uri, final ResourceSet resourceSet) {
    Resource resource = resourceSet.getResource(uri, false);
    if (resource == null) {
      resource = resourceSet.createResource(uri);
    }
    if (resource != null) {
      try {
        resource.load(resourceSet.getLoadOptions());
      } catch (IOException e) {
        LOGGER.warn("loadURI: Unable to load resource : " + resource.getURI().toString()); //$NON-NLS-1$
        return null;
      }
    }
    return resource;
  }
}
