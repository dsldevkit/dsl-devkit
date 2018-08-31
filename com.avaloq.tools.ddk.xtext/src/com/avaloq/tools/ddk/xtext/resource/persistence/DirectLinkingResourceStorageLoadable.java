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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.persistence.ResourceStorageLoadable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator.Adapter;
import com.avaloq.tools.ddk.xtext.nodemodel.serialization.FixedDeserializationConversionContext;
import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceLoadStorageEvent;
import com.google.common.base.Ascii;
import com.google.common.io.CharStreams;


/**
 * Implementation of {@link ResourceStorageLoadable} which is similar to Xbase's {@link org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageLoadable}
 * in that it also supports serialized {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations}. The differences to Xbase
 * are that this implementation is specific to the {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations DDK implementation} and that the
 * source and target objects of a mapping don't need to be part of the resource containing the mapping itself. Also, the resource description is not persisted,
 * but instead the source text is also included so that it doesn't need to be loaded with an additional I/O call.
 */
public class DirectLinkingResourceStorageLoadable extends ResourceStorageLoadable {

  private static final Logger LOG = Logger.getLogger(DirectLinkingResourceStorageLoadable.class);

  private static final int SOURCE_BUFFER_CAPACITY = 0x10000; // 64 KiB

  private final boolean storeNodeModel;
  private final ITraceSet traceSet;

  public DirectLinkingResourceStorageLoadable(final InputStream in, final boolean storeNodeModel, final ITraceSet traceSet) {
    super(in, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
    this.traceSet = traceSet;
  }

  @Override
  protected void loadIntoResource(final StorageAwareResource resource) throws IOException {
    traceSet.started(ResourceLoadStorageEvent.class, resource.getURI());
    try {
      super.loadIntoResource(resource);
      // CHECKSTYLE:OFF
    } catch (IOException | RuntimeException e) {
      // CHECKSTYLE:ON
      LOG.warn("Error loading " + resource.getURI() + " from binary storage", e); //$NON-NLS-1$ //$NON-NLS-2$
      if (e instanceof IOException) { // NOPMD
        throw e;
      }
      throw new IOException(e);
    } finally {
      traceSet.ended(ResourceLoadStorageEvent.class);
    }
  }

  @Override
  protected void loadEntries(final StorageAwareResource resource, final ZipInputStream zipIn) throws IOException {
    zipIn.getNextEntry();
    readContents(resource, new BufferedInputStream(zipIn));

    if (storeNodeModel) {
      zipIn.getNextEntry();
      StringBuilder out = new StringBuilder(SOURCE_BUFFER_CAPACITY);
      CharStreams.copy(new InputStreamReader(zipIn, StandardCharsets.UTF_8), out);
      String content = out.toString();

      zipIn.getNextEntry();
      readNodeModel(resource, new BufferedInputStream(zipIn), content);
    }

    zipIn.getNextEntry();
    readAssociationsAdapter(resource, new BufferedInputStream(zipIn));
  }

  /**
   * Read the node model from the given input stream.
   *
   * @param resource
   *          target resource, must not be {@code null}
   * @param inputStream
   *          input stream, must not be {@code null}
   * @param content
   *          corresponding source content as required by node model, must not be {@code null}
   * @throws IOException
   *           if an I/O exception occurred
   */
  protected void readNodeModel(final StorageAwareResource resource, final InputStream inputStream, final String content) throws IOException {
    DeserializationConversionContext deserializationContext = new FixedDeserializationConversionContext(resource, content);
    DataInputStream dataIn = new DataInputStream(inputStream);
    SerializableNodeModel serializableNodeModel = new SerializableNodeModel(resource);
    serializableNodeModel.readObjectData(dataIn, deserializationContext);
    resource.setParseResult(new ParseResult(resource.getContents().get(0), serializableNodeModel.root, deserializationContext.hasErrors()));
  }

  /**
   * Reads the {@link InferredModelAssociator.Adapter#getSourceToInferredModelMap()} map and the
   * {@link InferredModelAssociator.Adapter#getInferredModelToSourceMap()} map.
   *
   * @param resource
   *          resource being deserialized, must not be {@code null}
   * @param stream
   *          zip input stream, never {@code null}
   * @throws IOException
   *           if an I/O exception occurred
   * @see DirectLinkingResourceStorageWritable#writeAssociationsAdapter(StorageAwareResource, java.io.OutputStream)
   */
  protected void readAssociationsAdapter(final StorageAwareResource resource, final InputStream stream) throws IOException {
    DirectLinkingEObjectInputStream objIn = new DirectLinkingEObjectInputStream(stream, null);
    int size = objIn.readCompressedInt();
    if (size == 0) {
      return;
    }

    InferredModelAssociator.Adapter adapter = (Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class);
    if (adapter == null) {
      adapter = new InferredModelAssociator.Adapter();
      resource.eAdapters().add(adapter);
    }

    Map<EObject, Deque<EObject>> destinationMap = adapter.getSourceToInferredModelMap();
    for (int i = 0; i < size; i++) {
      mapListOfObjects(objIn, destinationMap, resource);
    }
    if (objIn.readByte() != Ascii.GS) {
      LOG.warn("Encountered unexpected data while loading " + resource.getURI()); //$NON-NLS-1$
      return;
    }

    destinationMap = adapter.getInferredModelToSourceMap();
    if (objIn.readBoolean()) {
      size = objIn.readCompressedInt();
      for (int i = 0; i < size; i++) {
        mapListOfObjects(objIn, destinationMap, resource);
      }
    } else {
      for (Map.Entry<EObject, Deque<EObject>> entry : adapter.getSourceToInferredModelMap().entrySet()) {
        EObject source = entry.getKey();
        for (EObject target : entry.getValue()) {
          Deque<EObject> singleton = new ArrayDeque<>(1);
          singleton.add(source);
          destinationMap.put(target, singleton);
        }
      }
    }
  }

  private void mapListOfObjects(final DirectLinkingEObjectInputStream objIn, final Map<EObject, Deque<EObject>> destinationMap, final StorageAwareResource resource) throws IOException {
    EObject from = objIn.readEObject(resource);
    Deque<EObject> to = readMappedEObjects(objIn, resource);
    if (from != null && !to.isEmpty()) {
      destinationMap.put(from, to);
    }
  }

  private Deque<EObject> readMappedEObjects(final DirectLinkingEObjectInputStream objIn, final StorageAwareResource resource) throws IOException {
    int collectionSize = objIn.readCompressedInt();
    Deque<EObject> deque = new ArrayDeque<>(collectionSize);
    for (int j = 0; j < collectionSize; j++) {
      EObject target = objIn.readEObject(resource);
      if (target != null) {
        deque.add(target);
      }
    }
    return deque;
  }

}
