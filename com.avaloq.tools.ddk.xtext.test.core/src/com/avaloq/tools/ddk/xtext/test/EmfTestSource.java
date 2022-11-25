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
package com.avaloq.tools.ddk.xtext.test;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.util.StringInputStream;


/**
 * EMF data container for all source relevant information.
 */
public class EmfTestSource extends TestSource {
  private final ResourceSet resourceSet;
  private Resource resource;

  /**
   * Creates a new instance of {@link EmfTestSource}.
   *
   * @param sourceFileName
   *          the source file name, must not be {@code null}
   * @param content
   *          the source content, must not be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   */
  public EmfTestSource(final String sourceFileName, final String content, final ResourceSet resourceSet) {
    super(sourceFileName, content);
    this.resourceSet = resourceSet;
  }

  /**
   * Returns the {@link ResourceSet}.
   *
   * @return the {@link ResoruceSet}, never {@code null}
   */
  protected ResourceSet getResourceSet() {
    return resourceSet;
  }

  /**
   * Returns the {@link Resource}.
   *
   * @return the {@link Resource}, or {@code null}
   */
  public Resource getResource() {
    if (!isLoaded()) {
      try {
        load();
      } catch (IOException ex) {
        // failed to load resource.
        resource = null; // NOPMD
      }
    }
    return resource;
  }

  /**
   * Internal method to return the current {@link #resource}.
   *
   * @return current {@link Resource}, or {@code null}
   */
  protected Resource basicGetResource() {
    return resource;
  }

  /**
   * Returns the semantic model of this source.
   *
   * @return the semantic model of this source, or {@code null}
   */
  public EObject getModel() {
    if (getResource() == null) {
      return null;
    }
    return getResource().getContents().get(0);
  }

  /**
   * Loads the resource of this source.
   *
   * @throws IOException
   *           if the resource could not be found using this test source uri.
   */
  public void load() throws IOException {
    StringInputStream instanceStream = new StringInputStream(getContent());
    resource = resourceSet.getResource(getUri(), false);
    if (resource == null) {
      resource = resourceSet.createResource(getUri());
      resourceSet.getResources().add(resource);
    } else {
      resource.unload();
    }
    resource.load(instanceStream, null);
    EcoreUtil.resolveAll(resource);
  }

  /**
   * A test source is loaded and its resource available.
   *
   * @return true if the resource for this test source is loaded.
   */
  public boolean isLoaded() {
    return resource != null && resource.isLoaded();
  }
}
