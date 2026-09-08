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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.build.IBinaryModelStore;
import com.avaloq.tools.ddk.xtext.resource.IDerivedObjectAssociationsStore;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.resource.extensions.NullResourceDescriptionsData;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;


/**
 * Null implementation of an Xtext target platform.
 */
public class NullXtextTargetPlatform implements IXtextTargetPlatform {

  @Override
  public IResourceDescriptionsData getIResourceDescriptionsData() {
    return new NullResourceDescriptionsData();
  }

  @Override
  public void open(final boolean clean, final IProgressMonitor monitor) {
    // Nothing to do
  }

  @Override
  public void close(final IProgressMonitor monitor) {
    // Nothing to do.
  }

  @Override
  public IIssueStore getIssueStore() {
    return new IIssueStore() {
      @Override
      public Multimap<URI, Issue> getIssues() {
        return ImmutableMultimap.of();
      }

      @Override
      public Iterable<Issue> getIssues(final URI uri) {
        return List.of();
      }

      @Override
      public void storeIssues(final URI forUri, final Iterable<Issue> issues) {
      }
    };
  }

  @Override
  public IDerivedObjectAssociationsStore getAssociationsStore() {
    return null;
  }

  @Override
  public IBinaryModelStore getBinaryModelStore() {
    return null;
  }

  @Override
  public Map<String, String> getMetadata(final Collection<String> keys, final IProgressMonitor monitor) {
    return Map.of();
  }

  @Override
  public void setMetadata(final Map<String, String> options) {
    // Nothing to do.
  }

}
