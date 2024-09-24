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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.builder.builderState.PersistedStateProvider;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.avaloq.tools.ddk.xtext.builder.IBinaryModelStore;
import com.avaloq.tools.ddk.xtext.resource.IDerivedObjectAssociationsStore;
import com.avaloq.tools.ddk.xtext.resource.extensions.DelegatingResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptionsData;
import com.google.inject.Inject;


/**
 * Default implementation implements the legacy mechanism through a {@link org.eclipse.xtext.builder.builderState.PersistedStateProvider PersistedStateProvider}
 * .
 */
public class DefaultXtextTargetPlatform implements IXtextTargetPlatform {

  @Inject
  private PersistedStateProvider stateLoader;

  private volatile IResourceDescriptionsData index; // NOPMD: volatile

  @Override
  public IResourceDescriptionsData getIResourceDescriptionsData() {
    if (index == null) {
      index = new DelegatingResourceDescriptionsData(new ResourceDescriptionsData(stateLoader.load()));
    }
    return index;
  }

  @Override
  public void open(final boolean clean, final IProgressMonitor monitor) throws IOException {
    // nothing to do
  }

  @Override
  public void close(final IProgressMonitor monitor) {
    // nothing to do
  }

  @Override
  public IIssueStore getIssueStore() {
    // No issue store needed; Eclipse stores markers by itself.
    return null;
  }

  @Override
  public IDerivedObjectAssociationsStore getAssociationsStore() {
    // Association store for default platform is not implemented yet
    return null;
  }

  @Override
  public Map<String, String> getMetadata(final Collection<String> keys, final IProgressMonitor monitor) {
    return Collections.emptyMap();
  }

  @Override
  public void setMetadata(final Map<String, String> options) {
    // nothing to do
  }

  @Override
  public IBinaryModelStore getBinaryModelStore() {
    return null;
  }
}
