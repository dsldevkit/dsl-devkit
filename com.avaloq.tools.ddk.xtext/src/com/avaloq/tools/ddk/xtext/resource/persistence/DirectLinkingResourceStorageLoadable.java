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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.persistence.ResourceStorageLoadable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.resource.ResourceSetOptions;
import com.avaloq.tools.ddk.xtext.resource.persistence.ResourceLoadMode.Constituent;
import com.avaloq.tools.ddk.xtext.resource.persistence.ResourceLoadMode.Instruction;
import com.avaloq.tools.ddk.xtext.tracing.ResourceLoadStorageEvent;
import com.google.common.base.Ascii;
import com.google.common.io.CharStreams;


/**
 * Implementation of {@link ResourceStorageLoadable} which is similar to Xbase's {@link org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageLoadable}
 * in that it also supports serialized {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations}. The differences to Xbase
 * are that this implementation is specific to the {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations DDK implementation} and that the
 * source and target objects of a mapping don't need to be part of the resource containing the mapping itself. Also, the resource description is not persisted,
 * but instead the source text is also included so that it doesn't need to be loaded with an additional I/O call.
 * <p>
 * Additionally this implementation supports partial loading using a specified {@link Mode}.
 */
// CHECKSTYLE:COUPLING-OFF
public class DirectLinkingResourceStorageLoadable extends ResourceStorageLoadable {
  /*
   * Implementation is copied over from org.eclipse.xtext.resource.persistence.ResourceStorageLoadable
   * Differences:
   * - overrides 'loadFeatureValue' to add some error logging
   * - count the number of EObjects added to the Resource
   */
  private final class EObjectInputStreamExtension extends BinaryResourceImpl.EObjectInputStream {
    private static final Logger LOGGER = LogManager.getLogger(EObjectInputStreamExtension.class);

    private boolean recordObjects;

    private final Set<EObject> isContained = new HashSet<>();

    private EObjectInputStreamExtension(final InputStream inputStream, final Map<?, ?> options) throws IOException {
      super(inputStream, options);
    }

    @Override
    public int readCompressedInt() throws IOException {
      // HACK! null resource set, to avoid usage of resourceSet's package registry
      resourceSet = null;
      return super.readCompressedInt();
    }

    @Override
    public InternalEObject loadEObject() throws IOException {
      final InternalEObject result = super.loadEObject();
      handleLoadEObject(result, this);
      return result;
    }

    private InternalEObject loadContainedObject() throws IOException {
      int previousSize = eObjectList.size();

      InternalEObject eObject = loadEObject();

      int newSize = eObjectList.size();
      if (newSize > previousSize && eObjectList.get(previousSize) == eObject) { // NOPMD
        isContained.add(eObject);
      } else {
        recordObjects = false;
      }
      return eObject;
    }

    public void loadContainedEObjects(final InternalEList<InternalEObject> internalEObjects) throws IOException {
      // Read all the values into an array.
      //
      int size = readCompressedInt();
      InternalEObject[] values = allocateInternalEObjectArray(size);
      for (int i = 0; i < size; ++i) {
        values[i] = loadContainedObject(); // changed
      }
      int existingSize = internalEObjects.size();

      // If the list is empty, we need to add all the objects,
      // otherwise, the reference is bidirectional and the list is at least partially populated.
      //
      if (existingSize == 0) {
        internalEObjectList.setData(size, values);
        internalEObjects.addAllUnique(0, internalEObjectList);
      } else {
        InternalEObject[] existingValues = allocateInternalEObjectArray(existingSize);
        internalEObjects.basicToArray(existingValues);
        int[] indices = allocateIntArray(existingSize);
        int duplicateCount = 0;
        LOOP: for (int i = 0; i < size; ++i) {
          InternalEObject internalEObject = values[i];
          for (int j = 0, count = duplicateCount; j < existingSize; ++j) {
            InternalEObject existingInternalEObject = existingValues[j];
            if (existingInternalEObject == internalEObject) {
              if (duplicateCount != count) {
                internalEObjects.move(duplicateCount, count);
              }
              indices[duplicateCount] = i;
              ++duplicateCount;
              existingValues[j] = null;
              continue LOOP;
            } else if (existingInternalEObject != null) {
              ++count;
            }
          }

          values[i - duplicateCount] = internalEObject;
        }

        size -= existingSize;
        internalEObjectList.setData(size, values);
        internalEObjects.addAllUnique(0, internalEObjectList);
        for (int i = 0; i < existingSize; ++i) {
          int newPosition = indices[i];
          int oldPosition = size + i;
          if (newPosition != oldPosition) {
            internalEObjects.move(newPosition, oldPosition);
          }
        }
        recycle(existingValues);
        recycle(indices);
      }
      recycle(values);
    }

    @Override
    protected void loadFeatureValue(final InternalEObject internalEObject, final EStructuralFeatureData eStructuralFeatureData) throws IOException {
      try {
        if (recordObjects) {
          switch (eStructuralFeatureData.kind) {
          case EOBJECT_CONTAINMENT:
          case EOBJECT_CONTAINMENT_PROXY_RESOLVING: {
            internalEObject.eSet(eStructuralFeatureData.featureID, loadContainedObject());
            break;
          }
          case EOBJECT_CONTAINMENT_LIST:
          case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING: {
            @SuppressWarnings("unchecked")
            InternalEList<InternalEObject> internalEList = (InternalEList<InternalEObject>) internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
            loadContainedEObjects(internalEList);
            break;
          }
          default:
            super.loadFeatureValue(internalEObject, eStructuralFeatureData);
          }
        } else {
          super.loadFeatureValue(internalEObject, eStructuralFeatureData);
        }
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        StringBuilder infoMessage = new StringBuilder(100);
        // CHECKSTYLE:ON
        infoMessage.append("Failed to load feature's value. Owner: ").append(internalEObject.eClass()); //$NON-NLS-1$
        if (eStructuralFeatureData.eStructuralFeature != null) {
          infoMessage.append(", feature name: ").append(eStructuralFeatureData.eStructuralFeature.getName()); //$NON-NLS-1$
        }
        LOG.info(infoMessage);
        throw e;
      }
    }

    private void loadResource(final Resource resource, final ArrayList<EObject> firstContainerObjects) throws IOException { // NOPMD LooseCoupling
      this.resource = resource;
      resourceSet = resource.getResourceSet();
      URI uri = resource.getURI();
      if (uri != null && uri.isHierarchical() && !uri.isRelative()) {
        baseURI = uri;
      }
      boolean installDerivedState = ResourceSetOptions.installDerivedState(resourceSet); // must be read before readCompressedInt, see HACK comment inside
                                                                                         // readCompressedInt
      int size = readCompressedInt();
      if (!installDerivedState && size == 2) { // the InfererenceContainer is always in the second slot
        size--;
      }
      InternalEObject[] values = allocateInternalEObjectArray(size);
      for (int i = 0; i < size; ++i) {
        recordObjects = i == 0;
        values[i] = loadEObject();
        if (i == 0) {
          if (recordObjects) {
            isContained.add(values[i]);
            firstContainerObjects.ensureCapacity(isContained.size());
            for (InternalEObject eObject : eObjectList) {
              if (isContained.contains(eObject)) {
                firstContainerObjects.add(eObject);
              }
            }
          } else {
            LOGGER.info("Out of order feature while loading the proxy node model for " + resource.getURI()); //$NON-NLS-1$
          }
        }
      }
      internalEObjectList.setData(size, values);
      @SuppressWarnings("unchecked")
      InternalEList<InternalEObject> internalEObjects = (InternalEList<InternalEObject>) (InternalEList<?>) resource.getContents();
      internalEObjects.addAllUnique(internalEObjectList);
      recycle(values);
    }
  }

  // CHECKSTYLE:COUPLING-ON

  private static final Logger LOG = LogManager.getLogger(DirectLinkingResourceStorageLoadable.class);

  private static final int SOURCE_BUFFER_CAPACITY = 0x10000; // 64 KiB

  private final boolean loadNodeModel;
  private final boolean splitContents;

  private final ITraceSet traceSet;

  private ResourceLoadMode mode;

  private ArrayList<EObject> objects; // NOPMD LooseCoupling

  public DirectLinkingResourceStorageLoadable(final InputStream in, final boolean loadNodeModel, final boolean splitContents, final ITraceSet traceSet) {
    super(in, loadNodeModel);
    this.loadNodeModel = loadNodeModel;
    this.splitContents = splitContents;
    this.traceSet = traceSet;
  }

  @Override
  protected void loadIntoResource(final StorageAwareResource resource) throws IOException {
    ResourceLoadMode loadMode = ResourceLoadMode.get(resource);
    loadIntoResource(resource, loadMode);
  }

  /**
   * Loads the binary storage into the given resource in the given {@link Mode}.
   *
   * @param resource
   *          resource to load into, must not be {@code null}
   * @param loadMode
   *          load mode, must not be {@code null}
   * @throws IOException
   *           if an I/O exception occurred
   */
  public void loadIntoResource(final StorageAwareResource resource, final ResourceLoadMode loadMode) throws IOException {
    if (loadMode.instruction(Constituent.RESOURCE) != Instruction.LOAD) {
      throw new IllegalArgumentException("Incompatible resource load mode: " + loadMode.instruction(Constituent.RESOURCE)); //$NON-NLS-1$
    }
    this.mode = loadMode;
    traceSet.started(ResourceLoadStorageEvent.class, resource.getURI(), loadMode);
    try {
      super.loadIntoResource(resource);
      // CHECKSTYLE:OFF
    } catch (IOException | RuntimeException e) {
      // CHECKSTYLE:ON
      LOG.info("Error loading " + resource.getURI() + " from binary storage", e); //$NON-NLS-1$ //$NON-NLS-2$
      resource.getContents();
      resource.eAdapters();
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
    // 1. resource contents
    zipIn.getNextEntry();
    if (!splitContents) {
      switch (mode.instruction(Constituent.CONTENT)) { // NOPMD ImplicitSwitchFallThrough
      case SKIP:
        break;
      case PROXY:
        LOG.warn("Proxying of resource contents is not supported: " + resource.getURI()); //$NON-NLS-1$
        // fall through
      case LOAD:
        readContents(resource, new NonLockingBufferInputStream(zipIn));
        break;
      }
    } else {
      switch (mode.instruction(Constituent.CONTENT)) { // NOPMD ImplicitSwitchFallThrough case SKIP:
      case SKIP:
        break;
      case PROXY:
        LOG.warn("Proxying of resource contents is not supported: " + resource.getURI()); //$NON-NLS-1$
        // fall through
      case LOAD:
        readContents(resource, new NonLockingBufferInputStream(zipIn));
        break;
      }

      zipIn.getNextEntry();
      switch (mode.instruction(Constituent.CONTENT)) { // NOPMD ImplicitSwitchFallThrough
      case SKIP:
        break;
      case PROXY:
        LOG.warn("Proxying of resource contents is not supported: " + resource.getURI()); //$NON-NLS-1$
        // fall through
      case LOAD:
        readContents(resource, new NonLockingBufferInputStream(zipIn));
        break;
      }
    }

    // 2. associations adapter
    zipIn.getNextEntry();
    switch (mode.instruction(Constituent.ASSOCIATIONS)) {
    case SKIP:
      break;
    case PROXY:
      ProxyModelAssociationsAdapter.install(resource);
      break;
    default:
      readAssociationsAdapter(resource, new NonLockingBufferInputStream(zipIn));
      break;
    }
    zipIn.getNextEntry();

    // 3. source
    String content = null;
    switch (mode.instruction(Constituent.SOURCE)) {
    case SKIP:
    case PROXY:
      if (mode.instruction(Constituent.NODE_MODEL) == Instruction.LOAD) {
        throw new IllegalArgumentException("Loading node model also requires loading source"); //$NON-NLS-1$
      }
      break;
    case LOAD:
      StringBuilder out = new StringBuilder(SOURCE_BUFFER_CAPACITY);
      CharStreams.copy(new InputStreamReader(zipIn, StandardCharsets.UTF_8), out);
      content = out.toString();
      break;
    }
    zipIn.getNextEntry();

    // 4. node model
    if (loadNodeModel) {
      switch (mode.instruction(Constituent.NODE_MODEL)) {
      case SKIP:
        break;
      case PROXY:
        ProxyCompositeNode.installProxyNodeModel(resource, objects);
        break;
      case LOAD:
        readNodeModel(resource, new NonLockingBufferInputStream(zipIn), content);
        break;
      }
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
   * @throws IOException
   *           if an I/O exception occurred
   */
  protected void readNodeModel(final StorageAwareResource resource, final InputStream inputStream, final String content) throws IOException {
    DeserializationConversionContext deserializationContext = new ProxyAwareDeserializationConversionContext(resource, content);
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
      resource.eAdapters().add(new InferredModelAssociator.EmptyAdapter());
      return;
    }

    InferredModelAssociator.Adapter adapter = (InferredModelAssociator.Adapter) EcoreUtil.getAdapter(resource.eAdapters(), InferredModelAssociator.Adapter.class);

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

  @Override
  protected void readContents(final StorageAwareResource resource, final InputStream inputStream) throws IOException {
    final EObjectInputStreamExtension in = new EObjectInputStreamExtension(inputStream, Collections.emptyMap());
    objects = new ArrayList<>();
    in.loadResource(resource, objects);
  }

}
