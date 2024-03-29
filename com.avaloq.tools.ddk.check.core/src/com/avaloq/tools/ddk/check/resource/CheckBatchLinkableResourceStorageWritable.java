/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.resource;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.xtext.resource.persistence.StorageAwareResource;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;
import org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageWritable;


@SuppressWarnings("restriction")
/**
 * This class extends the functionality of BatchLinkableResourceStorageWritable and ResourceStorageWritable
 * to ensure that the file metadata of the check file binary models stays constant unconditionally,
 * independently of the time and date when the build was performed.
 * <p>
 * A more detailed description of this issue is given in the Javadoc {@link #writeEntries(String)}.
 */
public class CheckBatchLinkableResourceStorageWritable extends BatchLinkableResourceStorageWritable {
  private static final long CONSTANT_DATETIME_MILLIS = 0; // Arbitrary value
  private final boolean storeNodeModel;

  public CheckBatchLinkableResourceStorageWritable(final OutputStream out, final boolean storeNodeModel) {
    super(out, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
  }

  @SuppressWarnings("nls")
  @Override
  /**
   * Eclipse may occasionally regenerate binary models as part of the build process. When this happens,
   * the metadata associated with the ZIP compression format (and, therefore, the binary contents
   * of the generated file) will change, since it includes the date and time of the last modification.
   * This means that binary models with identical *contents* will be seen by Git as different.
   * <p>
   * To prevent this undesired behavior, this function override ensures that the "Last Modified Date & Time"
   * in the metadata of the binary model is kept constant (set to FIXED_LAST_MODIFIED_DATETIME).
   * <p>
   * To achieve this, we replicate the functionality of the super classes, but include calls to zipEntry.setLastModifiedTime(...)
   * <p>
   * For more information about ZIP headers, see https://en.wikipedia.org/wiki/ZIP_(file_format)#File_headers
   * For a bit-level description of each field, see https://users.cs.jmu.edu/buchhofp/forensics/formats/pkzip.html
   * This is a relevant SO question: https://stackoverflow.com/questions/26525936/java-creating-two-identical-zip-files-if-content-are-the-same
   */
  protected void writeEntries(final StorageAwareResource resource, final ZipOutputStream zipOut) throws IOException {
    ZipEntry zipEntry;

    // Adapted from the ResourceStorageWritable base class
    BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOut);

    zipEntry = new ZipEntry("emf-contents");
    zipEntry.setTime(CONSTANT_DATETIME_MILLIS); // Unique to this class
    zipOut.putNextEntry(zipEntry);
    try {
      writeContents(resource, bufferedOutput);
    } finally {
      bufferedOutput.flush();
      zipOut.closeEntry();
    }

    // "resource-description" entry would go here but is skipped in this class's implementation

    if (storeNodeModel) {
      zipEntry = new ZipEntry("node-model");
      zipEntry.setTime(CONSTANT_DATETIME_MILLIS); // Unique to this class
      zipOut.putNextEntry(zipEntry);
      try {
        writeNodeModel(resource, bufferedOutput);
      } finally {
        bufferedOutput.flush();
        zipOut.closeEntry();
      }
    }

    // Adapted from the BatchLinkableResourceStorageWritable base class
    if (resource instanceof BatchLinkableResource) {
      zipEntry = new ZipEntry("associations");
      zipEntry.setTime(CONSTANT_DATETIME_MILLIS); // Unique to this class
      zipOut.putNextEntry(zipEntry);
      BufferedOutputStream buffOut = new BufferedOutputStream(zipOut);
      try {
        writeAssociationsAdapter((BatchLinkableResource) resource, buffOut);
      } finally {
        buffOut.flush();
        zipOut.closeEntry();
      }
    }
  }
}
