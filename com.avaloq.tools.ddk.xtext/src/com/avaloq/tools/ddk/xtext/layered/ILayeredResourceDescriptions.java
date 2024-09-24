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
package com.avaloq.tools.ddk.xtext.layered;

import org.eclipse.xtext.resource.IResourceDescription;


/**
 * Layered resource descriptions.
 */
public interface ILayeredResourceDescriptions {

  /**
   * As {@link org.eclipse.xtext.resource.IResourceDescriptions#getAllResourceDescriptions() IResourceDescriptions.getAllResourceDescriptions()}, but returning
   * only the local (= logically upper layer) resource
   * descriptions.
   *
   * @return the resource descriptions
   */
  Iterable<IResourceDescription> getLocalResourceDescriptions();
}
