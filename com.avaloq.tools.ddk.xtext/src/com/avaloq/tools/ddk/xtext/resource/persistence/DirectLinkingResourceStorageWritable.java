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

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.resource.persistence.ResourceStorageWritable;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator;
import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator.Adapter;
import com.avaloq.tools.ddk.xtext.nodemodel.serialization.FixedSerializationConversionContext;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;


/**
 * Implementation of {@link ResourceStorageWritable} which is similar to Xbase's {@link org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageWritable}
 * in that it also supports serialized {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations model associations}. The differences to Xbase
 * are that this implementation is specific to the {@link com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations DDK implementation} and that the
 * source and target objects of a mapping don't need to be part of the resource containing the mapping itself. Also, the resource description is not persisted,
 * but instead the source text is also included so that it doesn't need to be loaded with an additional I/O call.
 */
public class DirectLinkingResourceStorageWritable extends ResourceStorageWritable {

  private static final Logger LOG = Logger.getLogger(DirectLinkingResourceStorageWritable.class);

  private final boolean storeNodeModel;

  public DirectLinkingResourceStorageWritable(final OutputStream out, final boolean storeNodeModel) {
    super(out, storeNodeModel);
    this.storeNodeModel = storeNodeModel;
  }

  @Override
  protected void writeEntries(final StorageAwareResource resource, final ZipOutputStream zipOut) {
    BufferedOutputStream bufferedOutput = new BufferedOutputStream(zipOut);
    try {
      zipOut.putNextEntry(new ZipEntry("emf-contents")); //$NON-NLS-1$
      try {
        writeContents(resource, bufferedOutput);
      } finally {
        bufferedOutput.flush();
        zipOut.closeEntry();
      }

      if (storeNodeModel) {
        zipOut.putNextEntry(new ZipEntry("source")); //$NON-NLS-1$
        try {
          InputStream stream = resource.getResourceSet().getURIConverter().createInputStream(resource.getURI());
          String encoding = resource.getEncoding();
          if (StandardCharsets.UTF_8.name().equals(encoding)) {
            ByteStreams.copy(stream, bufferedOutput);
            stream.close();
          } else {
            InputStreamReader in = new InputStreamReader(stream, encoding);
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

      zipOut.putNextEntry(new ZipEntry("associations")); //$NON-NLS-1$
      try {
        writeAssociationsAdapter(resource, bufferedOutput);
      } finally {
        bufferedOutput.flush();
        zipOut.closeEntry();
      }
    } catch (IOException e) {
      throw new WrappedException(e);
      // CHECKSTYLE:OFF
    } catch (RuntimeException e) {
      // CHECKSTYLE:ON
      LOG.error("Error storing " + resource.getURI(), e); //$NON-NLS-1$
      throw e instanceof WrappedException ? e : new WrappedException(e); // NOPMD
    }
  }

  @Override
  protected void writeNodeModel(final StorageAwareResource resource, final OutputStream outputStream) {
    try {
      DataOutputStream out = new DataOutputStream(outputStream);
      SerializableNodeModel serializableNodeModel = new SerializableNodeModel(resource);
      SerializationConversionContext conversionContext = new FixedSerializationConversionContext(resource);
      serializableNodeModel.writeObjectData(out, conversionContext);
      out.flush();
    } catch (IOException e) {
      throw new WrappedException(e);
    }
  }

  /**
   * Serializes the {@link InferredModelAssociator.Adapter#getSourceToInferredModelMap() source-to-inferred-element map} for the given resource.
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
    ObjectOutputStream objOut = new ObjectOutputStream(zipOut);
    try {
      // sourceToTarget
      Map<String, Collection<String>> sourceToTarget = Maps.newHashMap();
      if (adapter != null) {
        for (Entry<EObject, Collection<EObject>> entry : adapter.getSourceToInferredModelMap().asMap().entrySet()) {
          sourceToTarget.put(getURIString(entry.getKey(), resource), Sets.newHashSet(Collections2.filter(Collections2.transform(entry.getValue(), v -> getURIString(v, resource)), Objects::nonNull)));
        }
      }
      objOut.writeObject(sourceToTarget);
    } finally {
      objOut.flush();
    }
  }

  /**
   * Returns a string representation of the given object's URI. For objects contained by the given resource the object's {@link Resource#getURIFragment(EObject)
   * URI fragment} will be returned. For objects in other resources the {@link EcoreUtil#getURI(EObject) full URI} will be returned with an exclamation mark as
   * prefix.
   *
   * @param obj
   *          object to get URI string for
   * @param context
   *          resource being serialized, must not be {@code null}
   * @return URI string or {@code null} if the object is {@code null}, a lazy-linking proxy, or not a proxy but contained in a resource
   */
  protected static String getURIString(final EObject obj, final Resource context) {
    if (obj == null) {
      return null;
    }
    Resource resource = obj.eResource();
    if (resource == null) {
      if (obj.eIsProxy()) {
        URI proxyURI = ((InternalEObject) obj).eProxyURI();
        return proxyURI.fragment().startsWith(LazyURIEncoder.XTEXT_LINK) ? null : '!' + proxyURI.toString();
      }
      return null;
    }
    return resource == context ? getURIFragmentPath(obj, context) : '!' + resource.getURI().toString() + '#' + resource.getURIFragment(obj); // NOPMD
  }

  /**
   * Computes a short positional URI fragment path. These are more efficient than fragments returned by {@link org.eclipse.xtext.resource.IFragmentProvider}, as
   * the latter may contain name-based segments, which require a lookup to resolve.
   *
   * @param obj
   *          object to get URI fragment for, must not be {@code null}
   * @param resource
   *          resource containing object, must not be {@code null}
   * @return URI fragment path, where the segments encode the feature IDs and position in case of multi-valued features, never {@code null}
   */
  @SuppressWarnings("unchecked")
  private static String getURIFragmentPath(final EObject obj, final Resource resource) {
    List<CharSequence> segments = Lists.newArrayList();
    InternalEObject internalEObject = (InternalEObject) obj;
    for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer()) {
      EStructuralFeature feature = internalEObject.eContainingFeature();
      StringBuilder b = new StringBuilder();
      b.append(feature.getFeatureID());
      if (feature.isMany()) {
        b.append('.').append(((EList<EObject>) container.eGet(feature, false)).indexOf(internalEObject));
      }
      segments.add(b);
      internalEObject = container;
    }

    segments.add(Integer.toString(resource.getContents().indexOf(internalEObject)));
    return Joiner.on('/').join(Lists.reverse(segments));
  }

}
