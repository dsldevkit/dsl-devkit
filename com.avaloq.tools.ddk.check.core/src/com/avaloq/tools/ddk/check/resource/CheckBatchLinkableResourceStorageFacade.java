package com.avaloq.tools.ddk.check.resource;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccessExtension3;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageFacade;


@SuppressWarnings("restriction")
public class CheckBatchLinkableResourceStorageFacade extends BatchLinkableResourceStorageFacade {

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
}
