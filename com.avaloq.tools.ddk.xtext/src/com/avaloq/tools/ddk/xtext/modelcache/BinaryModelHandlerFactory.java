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

import com.avaloq.tools.ddk.xtext.linking.IDdkLazyLinkingResource;


/**
 * A factory for creating binary model handlers for a given model type.
 */
public class BinaryModelHandlerFactory {

  /**
   * Gets a binary model handler for the given model type and resource.
   *
   * @param model
   *          the model type, must not be {@code null}
   * @param resource
   *          the resource associated with the model handler, must not be {@code null}
   * @return the model handler, never {@code null}
   */
  public IBinaryModelHandler createHandler(final ResourceModelType model, final IDdkLazyLinkingResource resource) {
    switch (model) {
    case EMF:
      return new EMFModelHandler(resource);
    case NODE:
      return new NodeModelHandler(resource);
    default:
      throw new UnsupportedOperationException("There is no model handler for the given Model type"); //$NON-NLS-1$
    }
  }
}
