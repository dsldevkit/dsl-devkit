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

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;

import com.avaloq.tools.ddk.xtext.util.ZippedByteArrayInputStream;
import com.avaloq.tools.ddk.xtext.util.ZippedByteArrayOutputStream;
import com.google.common.collect.ImmutableMap;


/**
 * Handler for operations related to the binary EMF model of a resource.
 */
public class EMFModelHandler implements IBinaryModelHandler {

  // Predicted model size based on the average model size computed over the entire set of sources
  private static final int PREDICTED_MODEL_SIZE = 20 * 1024;

  private final Resource resource;

  public EMFModelHandler(final Resource resource) {
    this.resource = resource;
  }

  /** {@inheritDoc} */
  @Override
  public void insertModel(final byte[] modelData) throws IOException {
    ZippedByteArrayInputStream modelInputStream = new ZippedByteArrayInputStream(modelData);
    EObjectInputStream binaryStream = new EObjectInputStream(modelInputStream, ImmutableMap.of());
    binaryStream.loadResource(resource);
    modelInputStream.close();
  }

  /** {@inheritDoc} */
  @Override
  public byte[] extractModel() throws IOException {
    ZippedByteArrayOutputStream modelOutputStream = new ZippedByteArrayOutputStream(PREDICTED_MODEL_SIZE);
    EObjectOutputStream binaryStream = new EObjectOutputStream(modelOutputStream, ImmutableMap.of());
    binaryStream.saveResource(resource);
    modelOutputStream.close();
    return modelOutputStream.toByteArray();
  }

  /** {@inheritDoc} */
  @Override
  public void insertProxyModel() {
    throw new UnsupportedOperationException("Inserting proxy for EMF model is not supported."); //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public void removeProxyModel() {
    throw new UnsupportedOperationException("Removing proxy for EMF model is not supported."); //$NON-NLS-1$
  }
}
