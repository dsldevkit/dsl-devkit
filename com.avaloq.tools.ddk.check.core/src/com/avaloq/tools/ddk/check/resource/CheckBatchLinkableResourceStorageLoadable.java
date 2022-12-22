package com.avaloq.tools.ddk.check.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;
import org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageLoadable;


@SuppressWarnings("restriction")
public class CheckBatchLinkableResourceStorageLoadable extends BatchLinkableResourceStorageLoadable {
  private final boolean storeNodeModel;

  public CheckBatchLinkableResourceStorageLoadable(final InputStream in, final boolean storeNodeModel) {
    super(in, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
  }

  /** Override loadEntries to skip the reading of the "resource-description" entry in the binary model file. */
  @Override
  protected void loadEntries(final StorageAwareResource resource, final ZipInputStream zipIn) throws IOException {
    // Adapted from the ResourceStorageLoadable base class
    zipIn.getNextEntry();
    readContents(resource, new BufferedInputStream(zipIn));
    // Here we skip the call to readResourceDescription
    if (storeNodeModel) {
      zipIn.getNextEntry();
      readNodeModel(resource, new BufferedInputStream(zipIn));
    }

    // Preserved from the BatchLinkableResourceStorageLoadable base class
    readAssociationsAdapter((BatchLinkableResource) resource, zipIn);
  }

}
