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
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.persistence.ResourceStorageLoadable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator.Adapter;
import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceLoadStorageEvent;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.ListMultimap;
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

  private static final Splitter FRAGMENT_SEGMENT_SPLITTER = Splitter.on('/');
  private static final int SOURCE_BUFFER_CAPACITY = 0x10000; // 64 KiB

  private final boolean storeNodeModel;
  private final ITraceSet traceSet;

  public DirectLinkingResourceStorageLoadable(final InputStream in, final boolean storeNodeModel, final ITraceSet traceSet) {
    super(in, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
    this.traceSet = traceSet;
  }

  @Override
  protected void loadIntoResource(final StorageAwareResource resource) {
    traceSet.started(ResourceLoadStorageEvent.class, resource.getURI());
    try {
      super.loadIntoResource(resource);
      // CHECKSTYLE:OFF
    } catch (RuntimeException e) {
      // CHECKSTYLE:ON
      LOG.error("Error loading " + resource.getURI(), e); //$NON-NLS-1$
      throw e instanceof WrappedException ? e : new WrappedException(e); // NOPMD
    } finally {
      traceSet.ended(ResourceLoadStorageEvent.class);
    }
  }

  @Override
  protected void loadEntries(final StorageAwareResource resource, final ZipInputStream zipIn) {
    try {
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
    } catch (IOException e) {
      throw new WrappedException(e);
    }
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
   */
  protected void readNodeModel(final StorageAwareResource resource, final InputStream inputStream, final String content) {
    try {
      // if this is a synthetic resource (i.e. tests or so, don't load the node model)
      if (!resource.getResourceSet().getURIConverter().exists(resource.getURI(), resource.getResourceSet().getLoadOptions())) {
        LOG.info("Skipping loading node model for synthetic resource " + resource.getURI()); //$NON-NLS-1$
        return;
      }
      DeserializationConversionContext deserializationContext = new DeserializationConversionContext(resource, content);
      DataInputStream dataIn = new DataInputStream(inputStream);
      SerializableNodeModel serializableNodeModel = new SerializableNodeModel(resource);
      serializableNodeModel.readObjectData(dataIn, deserializationContext);
      resource.setParseResult(new ParseResult(resource.getContents().get(0), serializableNodeModel.root, deserializationContext.hasErrors()));
    } catch (IOException e) {
      LOG.error(e.getMessage(), e);
    }
  }

  /**
   * Reads the {@link InferredModelAssociator.Adapter#getSourceToInferredModelMap()} map.
   *
   * @param resource
   *          resource being deserialized, must not be {@code null}
   * @param stream
   *          zip input stream, never {@code null}
   * @see DirectLinkingResourceStorageWritable#writeAssociationsAdapter(StorageAwareResource, java.io.OutputStream)
   */
  protected void readAssociationsAdapter(final StorageAwareResource resource, final InputStream stream) {
    try {
      InferredModelAssociator.Adapter adapter = (Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class);
      if (adapter == null) {
        adapter = new InferredModelAssociator.Adapter();
        resource.eAdapters().add(adapter);
      }
      ListMultimap<EObject, EObject> destinationMap = adapter.getSourceToInferredModelMap();
      ObjectInputStream objIn = new ObjectInputStream(stream);
      @SuppressWarnings("unchecked")
      Map<String, Collection<String>> sourceToTargetMap = (Map<String, Collection<String>>) objIn.readObject();
      for (Map.Entry<String, Collection<String>> entry : sourceToTargetMap.entrySet()) {
        destinationMap.putAll(getEObject(entry.getKey(), resource), Collections2.transform(entry.getValue(), v -> getEObject(v, resource)));
      }
    } catch (ClassNotFoundException | IOException e) {
      throw new WrappedException(e);
    }
  }

  /**
   * Returns the EObject for the given URI string.
   *
   * @param str
   *          URI string, must not be {@code null}
   * @param context
   *          resource being deserialized, must not be {@code null}
   * @return corresponding EObject, never {@code null}
   * @see DirectLinkingResourceStorageWritable#getURIString(EObject, Resource)
   */
  @SuppressWarnings("unchecked")
  private static EObject getEObject(final String str, final Resource context) {
    if (str.charAt(0) == '!') {
      return context.getResourceSet().getEObject(URI.createURI(str.substring(1)), true);
    }
    Iterator<String> it = FRAGMENT_SEGMENT_SPLITTER.split(str).iterator();
    EObject eObject = context.getContents().get(Integer.parseInt(it.next()));
    while (it.hasNext()) {
      String segment = it.next();
      int dotIdx = segment.indexOf('.');
      EStructuralFeature feature = eObject.eClass().getEStructuralFeature(Integer.parseInt(dotIdx == -1 ? segment : segment.substring(0, dotIdx)));
      eObject = dotIdx == -1 ? (EObject) eObject.eGet(feature, false)
          : ((EList<EObject>) eObject.eGet(feature, false)).get(Integer.parseInt(segment.substring(dotIdx + 1)));
    }
    return eObject;
  }

}
