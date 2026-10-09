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
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
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
    final BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOut);

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

  /**
   * Writes a {@link CheckModelPruner pruned} copy of the resource's check catalog instead of the resource contents themselves, so that the persisted model only
   * exposes the public API of the catalog and neither the implementation of its checks nor the inferred JVM model.
   * <p>
   * The serialization itself replicates the base class implementation: cross resource references are written as portable URIs and the
   * {@link #beforeSaveEObject(InternalEObject, BinaryResourceImpl.EObjectOutputStream) before} and
   * {@link #handleSaveEObject(InternalEObject, BinaryResourceImpl.EObjectOutputStream) after} hooks are invoked for every object so that the stream stays
   * symmetric with the one expected by {@link CheckBatchLinkableResourceStorageLoadable}.
   * </p>
   */
  @Override
  protected void writeContents(final StorageAwareResource resource, final OutputStream outputStream) throws IOException {
    Resource prunedResource = CheckModelPruner.createPrunedResource(resource);
    if (prunedResource == null) {
      super.writeContents(resource, outputStream);
      return;
    }
    PrunedObjectOutputStream out = new PrunedObjectOutputStream(resource, outputStream, Collections.emptyMap());
    try {
      out.saveResource(prunedResource);
    } finally {
      out.flush();
    }
  }

  /**
   * Writes an empty associations adapter.
   * <p>
   * The associations map the source elements of the catalog to the elements of the inferred JVM model, neither of which is persisted anymore. The written data
   * is identical to what the base class writes for a resource without any associations, so that the format of the binary model remains unchanged.
   * </p>
   */
  @Override
  protected void writeAssociationsAdapter(final BatchLinkableResource resource, final OutputStream zipOut) throws IOException {
    try (ObjectOutputStream objOut = new ObjectOutputStream(zipOut) {
      @Override
      public void close() throws IOException {
        flush();
      }
    }) {
      objOut.writeObject(new LinkedHashMap<String, String>()); // logicalContainerMap
      objOut.writeObject(new LinkedHashMap<String, Set<String>>()); // sourceToTargetMap
      objOut.writeObject(new LinkedHashMap<String, Set<String>>()); // targetToSourceMap
    }
  }

  /**
   * An output stream writing the contents of a resource other than the one being persisted, while still resolving portable URIs and computing the additional
   * per-object data relative to the resource being persisted.
   */
  private class PrunedObjectOutputStream extends BinaryResourceImpl.EObjectOutputStream {

    private final StorageAwareResource sourceResource;

    PrunedObjectOutputStream(final StorageAwareResource sourceResource, final OutputStream outputStream, final Map<?, ?> options) throws IOException {
      super(outputStream, options);
      this.sourceResource = sourceResource;
    }

    @Override
    public void writeURI(final URI uri, final String fragment) throws IOException {
      URI fullURI = uri.appendFragment(fragment);
      URI portableURI = sourceResource.getPortableURIs().toPortableURI(sourceResource, fullURI);
      URI uriToWrite = portableURI == null ? fullURI : portableURI;
      super.writeURI(uriToWrite.trimFragment(), uriToWrite.fragment());
    }

    @Override
    public void saveEObject(final InternalEObject internalEObject, final BinaryResourceImpl.EObjectOutputStream.Check check) throws IOException {
      beforeSaveEObject(internalEObject, this);
      super.saveEObject(internalEObject, check);
      handleSaveEObject(internalEObject, this);
    }
  }
}
