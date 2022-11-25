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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.containers.IContainerState;


/**
 * Copy of the package visibility class {@link org.eclipse.xtext.resource.containers.ContainerState} in Xtext.
 */
class ContainerState implements IContainerState {

  private final String root;
  private final IAllContainersState globalState;

  protected ContainerState(final String root, final IAllContainersState globalState) {
    this.root = root;
    this.globalState = globalState;
  }

  /** {@inheritDoc} */
  public Collection<URI> getContents() {
    return globalState.getContainedURIs(root);
  }

  /** {@inheritDoc} */
  public boolean contains(final URI uri) {
    return getContents().contains(uri);
  }

  public boolean isEmpty() {
    return globalState.isEmpty(root);
  }

}

