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

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.resource.persistence.ResourceStorageWritable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator.Adapter;
import com.google.common.base.Ascii;
import com.google.common.io.CharStreams;


/**
 * Implementation of {@link ResourceStorageWritable} which is similar to Xbase's {@link org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageWritable}
 * in that it also supports serialized {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations}. The differences to Xbase
 * are that this implementation is specific to the {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations DDK implementation} and that the
 * source and target objects of a mapping don't need to be part of the resource containing the mapping itself. Also, the resource description is not persisted,
 * but instead the source text is also included so that it doesn't need to be loaded with an additional I/O call.
 */
public class DirectLinkingResourceStorageWritable extends ResourceStorageWritable {

  private final boolean storeNodeModel;
  private final boolean splitContents;

  public DirectLinkingResourceStorageWritable(final OutputStream out, final boolean storeNodeModel, final boolean splitContents) {
    super(out, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
    this.splitContents = splitContents;
  }

  @Override
  protected void writeEntries(final StorageAwareResource resource, final ZipOutputStream zipOut) {
    BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOut);
    try {
      // 1. resource contents
      if (!splitContents) {
        zipOut.putNextEntry(new ZipEntry("emf-contents")); //$NON-NLS-1$
        try {
          writeContents(resource, bufferedOutput);
        } finally {
          bufferedOutput.flush();
          zipOut.closeEntry();
        }
      } else {
        zipOut.putNextEntry(new ZipEntry("emf-ast")); //$NON-NLS-1$
        try {
          writeSelectedContents(resource, bufferedOutput, 0, 1);
        } finally {
          bufferedOutput.flush();
          zipOut.closeEntry();
        }
        zipOut.putNextEntry(new ZipEntry("emf-other-contents")); //$NON-NLS-1$
        try {
          writeSelectedContents(resource, bufferedOutput, 1, Integer.MAX_VALUE);
        } finally {
          bufferedOutput.flush();
          zipOut.closeEntry();
        }
      }

      // 2. associations adapter
      zipOut.putNextEntry(new ZipEntry("associations")); //$NON-NLS-1$
      try {
        writeAssociationsAdapter(resource, bufferedOutput);
      } finally {
        bufferedOutput.flush();
        zipOut.closeEntry();
      }

      // 3. node model
      if (storeNodeModel) {
        zipOut.putNextEntry(new ZipEntry("source")); //$NON-NLS-1$
        try {
          if (resource.getParseResult() != null) {
            StringReader in = new StringReader(resource.getParseResult().getRootNode().getText());
            OutputStreamWriter out = new OutputStreamWriter(bufferedOutput, StandardCharsets.UTF_8);
            CharStreams.copy(in, out);
            in.close();
            out.flush();
          }
        } finally {
          bufferedOutput.flush();
          zipOut.closeEntry();
        }

        zipOut.putNextEntry(new ZipEntry("node-model")); //$NON-NLS-1$
        try {
          writeNodeModel(resource, bufferedOutput);
        } finally {
          bufferedOutput.flush();
          zipOut.closeEntry();
        }
      }
    } catch (IOException e) {
      throw new WrappedException(e);
      // CHECKSTYLE:OFF
    } catch (RuntimeException e) {
      // CHECKSTYLE:ON
      throw e instanceof WrappedException ? e : new WrappedException(e); // NOPMD
    }
  }

  @Override
  protected void writeNodeModel(final StorageAwareResource resource, final OutputStream outputStream) {
    try {
      DataOutputStream out = new DataOutputStream(outputStream);
      SerializableNodeModel serializableNodeModel = new SerializableNodeModel(resource);
      SerializationConversionContext conversionContext = new ProxyAwareSerializationConversionContext(resource);
      serializableNodeModel.writeObjectData(out, conversionContext);
      out.flush();
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }

  /**
   * Serializes the {@link InferredModelAssociator.Adapter#getSourceToInferredModelMap() source-to-inferred-element map} and the
   * {@link InferredModelAssociator.Adapter#getInferredModelToSourceMap() inferred-element-to-source map} for the given resource.
   *
   * @param resource
   *          resource, must not be {@code null}
   * @param zipOut
   *          output stream to write to, must not be {@code null}
   * @throws IOException
   *           thrown when an IO error occurs
   */
  protected void writeAssociationsAdapter(final StorageAwareResource resource, final OutputStream zipOut) throws IOException {
    InferredModelAssociator.Adapter adapter = (Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class);
    DirectLinkingEObjectOutputStream objOut = new DirectLinkingEObjectOutputStream(zipOut, null);
    try {
      // sourceToTarget
      if (adapter != null) {
        objOut.writeCompressedInt(adapter.getSourceToInferredModelMap().size());
        for (Entry<EObject, Deque<EObject>> entry : adapter.getSourceToInferredModelMap().entrySet()) {
          writeMapping(entry, objOut, resource);
        }
        objOut.writeByte(Ascii.GS);

        // targetToSource
        boolean anyTargetWithMultipleSources = adapter.getInferredModelToSourceMap().values().stream().anyMatch(c -> c.size() > 1);
        objOut.writeBoolean(anyTargetWithMultipleSources);
        if (anyTargetWithMultipleSources) {
          objOut.writeCompressedInt(adapter.getInferredModelToSourceMap().size());
          for (Entry<EObject, Deque<EObject>> entry : adapter.getInferredModelToSourceMap().entrySet()) {
            writeMapping(entry, objOut, resource);
          }
        }
      } else {
        objOut.writeCompressedInt(0);
      }
    } finally {
      objOut.flush();
    }
  }

  private void writeMapping(final Entry<EObject, Deque<EObject>> entry, final DirectLinkingEObjectOutputStream objOut, final StorageAwareResource resource) throws IOException {
    objOut.writeEObjectURI(entry.getKey(), resource);
    objOut.writeCompressedInt(entry.getValue().size());
    for (EObject target : entry.getValue()) {
      objOut.writeEObjectURI(target, resource);
    }
  }

  private class SelectiveObjectOutputStream extends BinaryResourceImpl.EObjectOutputStream {
    private final StorageAwareResource storageAwareResource;

    SelectiveObjectOutputStream(final StorageAwareResource storageAwareResource, final OutputStream outputStream, final Map<?, ?> options) throws IOException {
      super(outputStream, options);
      this.storageAwareResource = storageAwareResource;
    }

    @Override
    public void writeURI(final URI uri, final String fragment) throws IOException {
      URI fullURI = uri.appendFragment(fragment);
      URI portableURI = storageAwareResource.getPortableURIs().toPortableURI(storageAwareResource, fullURI);
      URI uriToWrite = portableURI == null ? fullURI : portableURI;
      super.writeURI(uriToWrite.trimFragment(), uriToWrite.fragment());
    }

    @Override
    public void saveEObject(final InternalEObject internalEObject, final BinaryResourceImpl.EObjectOutputStream.Check check) throws IOException {
      beforeSaveEObject(internalEObject, this);
      super.saveEObject(internalEObject, check);
      handleSaveEObject(internalEObject, this);
    }

    void saveSelectedResourceContents(final Resource resource, final int startIndex, final int objCount) throws IOException {
      this.resource = resource;
      URI uri = resource.getURI();
      if (uri != null && uri.isHierarchical() && !uri.isRelative()) {
        baseURI = uri;
      }
      @SuppressWarnings("unchecked")
      InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>) (InternalEList<?>) resource.getContents();

      int writeCount = Math.min(objCount, internalEList.size() - startIndex);
      writeCompressedInt(writeCount);
      for (int i = 0; i < writeCount; ++i) {
        InternalEObject internalEObject = internalEList.get(i + startIndex);
        saveEObject(internalEObject, Check.CONTAINER);
      }
    }

  }

  protected void writeSelectedContents(final StorageAwareResource storageAwareResource, final OutputStream outputStream, final int startIndex, final int objCount) throws IOException {
    SelectiveObjectOutputStream out = new SelectiveObjectOutputStream(storageAwareResource, outputStream, Collections.emptyMap());
    try {
      out.saveSelectedResourceContents(storageAwareResource, startIndex, objCount);
    } finally {
      out.flush();
    }
  }
}
