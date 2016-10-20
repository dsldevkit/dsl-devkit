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


/**
 * Interface for binary model handlers.
 */
public interface IBinaryModelHandler {

  /**
   * Updates the content of the resource with the provided binary model.
   *
   * @param modelData
   *          the serialized model data, must not be {@code null}
   * @throws IOException
   *           when the manipulation of the given data fails
   */
  void insertModel(byte[] modelData) throws IOException;

  /**
   * Extracts the model of this resource into a binary format.
   *
   * @return the serialized Node model data, never {@code null}
   * @throws IOException
   *           when the manipulation of the serialization data fails
   */
  byte[] extractModel() throws IOException;

  /**
   * Creates proxy model that triggers lazy loading of real model when necessary.
   */
  void insertProxyModel();

  /**
   * Removes all proxy instances of the model.
   */
  void removeProxyModel();

}
