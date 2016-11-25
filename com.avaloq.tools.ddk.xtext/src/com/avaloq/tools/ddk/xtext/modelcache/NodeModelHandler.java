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
package com.avaloq.tools.ddk.xtext.modelcache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.OnChangeEvictingCache;

import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;
import com.avaloq.tools.ddk.xtext.util.ZippedByteArrayInputStream;
import com.avaloq.tools.ddk.xtext.util.ZippedByteArrayOutputStream;


/**
 * Handler for operations related to the binary Node model of a resource.
 */
@SuppressWarnings("restriction")
// Node Model serialization is a restricted API of Xtext. Until Xtext provides a proper serialization API, we need to access its internal components.
public class NodeModelHandler implements IBinaryModelHandler {

  // Predicted model size based on the average model size computed over the entire set of sources
  private static final int PREDICTED_MODEL_SIZE = 20 * 1024;

  private final ILazyLinkingResource2 resource;

  public NodeModelHandler(final ILazyLinkingResource2 resource) {
    this.resource = resource;
  }

  /** {@inheritDoc} */
  @Override
  public void insertModel(final byte[] modelData) throws IOException {
    ZippedByteArrayInputStream modelInputStream = new ZippedByteArrayInputStream(modelData);
    String completeSourceContent = resource.getSourceText();
    DeserializationConversionContext deserializationContext = new DeserializationConversionContext((XtextResource) resource, completeSourceContent);
    SerializableNodeModel nodeModel = new SerializableNodeModel();
    nodeModel.readObjectData(new DataInputStream(modelInputStream), deserializationContext);
    EObject emfRootObject = resource.getContents().isEmpty() ? null : resource.getContents().get(0);
    resource.setParseResult(new ParseResult(emfRootObject, nodeModel.root, deserializationContext.hasErrors()), true);
    modelInputStream.close();
  }

  /** {@inheritDoc} */
  @Override
  public byte[] extractModel() throws IOException {
    ZippedByteArrayOutputStream modelOutputStream = new ZippedByteArrayOutputStream(PREDICTED_MODEL_SIZE);
    SerializableNodeModel nodeModel = new SerializableNodeModel((XtextResource) resource);
    SerializationConversionContext serializationContext = new SerializationConversionContext((XtextResource) resource);
    DataOutputStream dataOutputStream = new DataOutputStream(modelOutputStream);
    nodeModel.writeObjectData(dataOutputStream, serializationContext);
    dataOutputStream.close();
    return modelOutputStream.toByteArray();
  }

  /** {@inheritDoc} */
  @Override
  public void insertProxyModel() {
    // Node model can be accessed by:
    // 1. checking the adapters of an EObject
    ICompositeNode rootNode = null;
    TreeIterator<EObject> iterator = EcoreUtil.getAllProperContents(resource, false);
    while (iterator.hasNext()) {
      EObject eObject = iterator.next();
      LazyLoadingCompositeNode node = new LazyLoadingCompositeNode();
      eObject.eAdapters().add(node);
      if (rootNode == null) {
        rootNode = node;
      }
    }

    // 2. fetching the ParseResult instance of the Resource
    EObject emfRootObject = resource.getContents().isEmpty() ? null : resource.getContents().get(0);
    resource.setParseResult(new ParseResult(emfRootObject, rootNode, false));

    // Add cache now, otherwise it will trigger model inference later.
    IResourceScopeCache cache = resource.getCache();
    if (cache instanceof OnChangeEvictingCache) {
      ((OnChangeEvictingCache) cache).getOrCreate(resource);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void removeProxyModel() {
    TreeIterator<EObject> iterator = EcoreUtil.getAllProperContents(resource, false);
    while (iterator.hasNext()) {
      EObject eObject = iterator.next();
      Adapter lazyNode = EcoreUtil.getAdapter(eObject.eAdapters(), LazyLoadingCompositeNode.class);
      if (lazyNode != null) {
        eObject.eAdapters().remove(lazyNode);
      }
    }
  }

}
