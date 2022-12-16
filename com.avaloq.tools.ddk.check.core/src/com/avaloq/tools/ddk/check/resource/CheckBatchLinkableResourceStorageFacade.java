package com.avaloq.tools.ddk.check.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccessExtension3;
import org.eclipse.xtext.resource.persistence.ResourceStorageLoadable;
import org.eclipse.xtext.resource.persistence.ResourceStorageWritable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.util.RuntimeIOException;
import org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageFacade;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;


@SuppressWarnings("restriction")
public class CheckBatchLinkableResourceStorageFacade extends BatchLinkableResourceStorageFacade {

  /**
   * Returns a generated URI corresponding to the location on disk of
   * the binary model associated with the provided {@param resource}.
   *
   * @param resource
   *          the .check file resource whose binary model we are looking for
   * @return the URI of the binary model storage for the provided {@param resource}, if one exists.
   *         If not, this method returns null.
   */
  protected URI getBinaryStorageURI(final StorageAwareResource resource) {
    IModelLocation modelLocation = ((CheckBatchLinkableResource) resource).getModelLocation();
    if (modelLocation != null) {
      String parentFolder = URI.createURI(modelLocation.getCatalogUrl().toString()).trimSegments(1).toString();
      String resourceName = computeOutputPath(resource);
      return URI.createURI(parentFolder + "/" + resourceName); //$NON-NLS-1$
    }

    return null;
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
        ((IFileSystemAccess) fsa).deleteFile(computeOutputPath(resource));
      }
    } catch (Exception e) {
      ((IFileSystemAccess) fsa).deleteFile(computeOutputPath(resource));
      throw e;
    }
  }

  @Override
  public ResourceStorageWritable createResourceStorageWritable(final OutputStream out) {
    return new CheckBatchLinkableResourceStorageWritable(out, isStoreNodeModel());
  }

  @Override
  protected boolean doesStorageExist(final StorageAwareResource resource) {
    URI uri = getBinaryStorageURI(resource);
    ResourceSet resourceSet = resource.getResourceSet();

    return (uri != null && resourceSet.getURIConverter().exists(uri, null)) || super.doesStorageExist(resource);
  }

  @Override
  public ResourceStorageLoadable getOrCreateResourceStorageLoadable(final StorageAwareResource resource) {
    try {
      URI storageURI = getBinaryStorageURI(resource);
      if (storageURI == null) {
        return super.getOrCreateResourceStorageLoadable(resource);
      }

      if (resource.getResourceSet() != null) {
        URIConverter uriConverter = resource.getResourceSet().getURIConverter();
        if (uriConverter.exists(storageURI, Collections.emptyMap())) {
          return createResourceStorageLoadable(uriConverter.createInputStream(storageURI));
        }
      }

      return super.getOrCreateResourceStorageLoadable(resource);
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
  }
}
