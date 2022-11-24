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
package com.avaloq.tools.ddk.xtext.findrefs;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.xtext.findReferences.ReferenceFinder;
import org.eclipse.xtext.findReferences.TargetURIs;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * Reference finder implementation that makes use of the IResourceDescriptions2 interface.
 */
// CHECKSTYLE:CONSTANTS-OFF
@SuppressWarnings("restriction")
public class ReferenceFinder2 extends ReferenceFinder {
  private static final String FIND_REFERENCES_PROGRESS = "Find Xtext references"; //$NON-NLS-1$

  @Inject
  public ReferenceFinder2(final IResourceServiceProvider.Registry serviceProviderRegistry) {
    super(serviceProviderRegistry);
  }

  /** {@inheritDoc} */
  @Override
  public void findAllReferences(final TargetURIs targetURIs, final IResourceAccess resourceAccess, final IResourceDescriptions indexData, final Acceptor acceptor, final IProgressMonitor monitor) {
    final SubMonitor subMonitor = SubMonitor.convert(monitor, 10);
    if (!Iterables.isEmpty(targetURIs)) {
      if (indexData instanceof IResourceDescriptions2) {
        findReferences(targetURIs, Sets.newHashSet(targetURIs.getTargetResourceURIs()), resourceAccess, indexData, acceptor, subMonitor.newChild(1));
        findAllIndexedReferences(targetURIs, indexData, acceptor, subMonitor.newChild(9));
      } else {
        super.findAllReferences(targetURIs, resourceAccess, indexData, acceptor, subMonitor);
      }
    }
  }

  /**
   * Uses IResourceDescriptions2 to find all indexed references.
   *
   * @param targetURIs
   *          the URIs to find references to, must not be {@code null}
   * @param indexData
   *          index to use when finding references, must not be {@code null}
   * @param referenceAcceptor
   *          the reference acceptor, must not be {@code null}
   * @param subMonitor
   *          the progress monitor, can be {@code null}
   */
  protected void findAllIndexedReferences(final TargetURIs targetURIs, final IResourceDescriptions indexData, final Acceptor referenceAcceptor, final SubMonitor subMonitor) {
    IResourceDescriptions2 idx = (IResourceDescriptions2) indexData;
    List<IReferenceDescription> refs = uniqueReferences(Lists.newArrayList(idx.findReferencesToObjects(targetURIs.asSet())));
    final SubMonitor monitor = SubMonitor.convert(subMonitor, FIND_REFERENCES_PROGRESS, refs.size());
    for (IReferenceDescription desc : refs) {
      if (monitor.isCanceled()) {
        return;
      }
      referenceAcceptor.accept(desc);
      monitor.worked(1);
    }
    monitor.done();
  }

  /**
   * Omits repeated references, which may arise due to some uses of derived attributes in some DSLs.
   * Considers {@code IReferenceDescription}s with the same source URI to be redundant.
   * Only the first such occurrence in the list is retained, the rest are omitted.
   *
   * @param refs
   *          the original list of reference descriptions
   * @return a list of unique reference descriptions with respect to their source URI
   */
  protected List<IReferenceDescription> uniqueReferences(final List<IReferenceDescription> refs) {
    Set<String> addedRefs = Sets.newHashSetWithExpectedSize(refs.size());
    List<IReferenceDescription> noRepeats = Lists.newArrayListWithExpectedSize(refs.size());
    for (IReferenceDescription ref : refs) {
      String sourceURI = ref.getSourceEObjectUri().toString();
      if (!addedRefs.contains(sourceURI)) {
        addedRefs.add(sourceURI);
        noRepeats.add(ref);
      }
    }
    return noRepeats;
  }
}
