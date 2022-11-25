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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IContainerState;
import org.eclipse.xtext.resource.containers.StateBasedContainer;


/**
 * StateBasedContainer implementation which exposes the container handle it's associated with.
 */
public class StateBasedContainerWithHandle extends StateBasedContainer {

  private final String handle;

  public StateBasedContainerWithHandle(final IResourceDescriptions descriptions, final IContainerState state, final String handle) {
    super(descriptions, state);
    this.handle = handle;
  }

  public String getHandle() {
    return handle;
  }

  @Override
  public String toString() {
    return "StateBasedContainerWithHandle: " + handle; //$NON-NLS-1$
  }
}
