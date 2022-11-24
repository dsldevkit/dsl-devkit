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
package com.avaloq.tools.ddk.xtext.builder.layered;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.avaloq.tools.ddk.xtext.builder.IBinaryModelStore;
import com.avaloq.tools.ddk.xtext.builder.IDerivedObjectAssociationsStore;
import com.avaloq.tools.ddk.xtext.extensions.IResourceDescriptionsData;


/**
 * Null implementation of an Xtext target platform.
 */
public class NullXtextTargetPlatform implements IXtextTargetPlatform {

  /** {@inheritDoc} */
  @Override
  public IResourceDescriptionsData getIResourceDescriptionsData() {
    return new NullResourceDescriptionsData();
  }

  /** {@inheritDoc} */
  @Override
  public void open(final boolean clean, final IProgressMonitor monitor) {
    // Nothing to do
  }

  /** {@inheritDoc} */
  @Override
  public void close(final IProgressMonitor monitor) {
    // Nothing to do.
  }

  /** {@inheritDoc} */
  @Override
  public IIssueStore getIssueStore() {
    return null;
  }

  @Override
  public IDerivedObjectAssociationsStore getAssociationsStore() {
    return null;
  }

  @Override
  public IBinaryModelStore getBinaryModelStore() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, String> getMetadata(final Collection<String> keys, final IProgressMonitor monitor) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public void setMetadata(final Map<String, String> options) {
    // Nothing to do.
  }

}
