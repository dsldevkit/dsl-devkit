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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccessExtension3;
import org.eclipse.xtext.resource.persistence.ResourceStorageFacade;
import org.eclipse.xtext.resource.persistence.ResourceStorageLoadable;
import org.eclipse.xtext.resource.persistence.ResourceStorageWritable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.resource.persistence.ResourceLoadMode.Constituent;
import com.avaloq.tools.ddk.xtext.resource.persistence.ResourceLoadMode.Instruction;
import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Implementation of {@link org.eclipse.xtext.resource.persistence.IResourceStorageFacade} to store and load resources with contents, node model, and
 * {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations map}.
 */
public class DirectLinkingResourceStorageFacade extends ResourceStorageFacade {

  public static final String STORE_NODE_MODEL = "com.avaloq.tools.ddk.xtext.resource.persistence.DirectLinkingResourceStorageFacade.STORE_NODE_MODEL";

  @Inject(optional=true)
  @Named(value=STORE_NODE_MODEL)
 	private boolean storeNodeModel = true;

  @Inject
  private ITraceSet traceSet;

  @Override
  public boolean isStoreNodeModel() {
    return storeNodeModel;
  }

  @Override
  public boolean shouldLoadFromStorage(final StorageAwareResource resource) {
    if (ResourceLoadMode.get(resource).instruction(Constituent.RESOURCE) == Instruction.SKIP) {
      return false;
    }
    return super.shouldLoadFromStorage(resource);
  }

  /**
   * If the resource contains errors, any existing storage will be deleted.
   */
  @Override
  @SuppressWarnings({"checkstyle:IllegalCatch"})
  public void saveResource(final StorageAwareResource resource, final IFileSystemAccessExtension3 fsa) {
    try {
      if (resource.getErrors().isEmpty()) {
        super.saveResource(resource, fsa);
      } else {
        deleteStorage(resource.getURI(), (IFileSystemAccess) fsa);
      }
    } catch (Exception e) {
      deleteStorage(resource.getURI(), (IFileSystemAccess) fsa);
      throw e;
    }
  }

  /**
   * Deletes the binary storage for the resource with the given URI, if it exists.
   *
   * @param resourceUri
   *          URI of resource to delete binary storage for, must not be {@code null}
   * @param fsa
   *          file system access, must not be {@code null}
   */
  public void deleteStorage(final URI resourceUri, final IFileSystemAccess fsa) {
    fsa.deleteFile(computeOutputPath(resourceUri));
  }

  @Override
  protected final String computeOutputPath(final StorageAwareResource resource) {
    return computeOutputPath(resource.getURI());
  }

  /**
   * URI-based alternative to {@link #computeOutputPath(StorageAwareResource)}.
   *
   * @param resourceUri
   *          resource URI, must not be {@code null}
   * @return output path for given URI, never {@code null}
   */
  protected String computeOutputPath(final URI resourceUri) {
    URI srcContainerURI = getSourceContainerURI(resourceUri);
    URI uri = getBinaryStorageURI(resourceUri);
    return uri.deresolve(srcContainerURI, false, false, true).path();
  }

  @Override
  protected URI getSourceContainerURI(final StorageAwareResource resource) {
    return getSourceContainerURI(resource.getURI());
  }

  /**
   * URI-based alternative to {@link #getSourceContainerURI(StorageAwareResource)}.
   *
   * @param resourceUri
   *          resource URI, must not be {@code null}
   * @return source container URI for given URI, never {@code null}
   */
  protected URI getSourceContainerURI(final URI resourceUri) {
    return resourceUri.trimSegments(1).appendSegment(""); //$NON-NLS-1$
  }

  @Override
  public ResourceStorageLoadable createResourceStorageLoadable(final InputStream in) {
    return new DirectLinkingResourceStorageLoadable(in, isStoreNodeModel(), traceSet);
  }

  @Override
  public ResourceStorageWritable createResourceStorageWritable(final OutputStream out) {
    return new DirectLinkingResourceStorageWritable(out, isStoreNodeModel());
  }

}
