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
package com.avaloq.tools.ddk.xtext.build;

import org.eclipse.xtext.resource.IResourceDescription;


/**
 * Factory for {@link org.eclipse.xtext.resource.IResourceDescription.Delta}s.
 */
public interface IResourceDescriptionDeltaFactory {

  /**
   * Factory method for resource description deltas.
   *
   * @param oldState
   *          Old resource description
   * @param newState
   *          New resource description
   * @return A delta describing this change. Either (but not both) of oldState and newState may be null,
   *         describing a new or a deleted resource.
   */
  IResourceDescription.Delta createDelta(IResourceDescription oldState, IResourceDescription newState);

}
