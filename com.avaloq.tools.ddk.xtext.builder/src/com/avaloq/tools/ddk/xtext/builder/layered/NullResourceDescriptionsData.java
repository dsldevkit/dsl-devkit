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

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.avaloq.tools.ddk.xtext.resource.extensions.DelegatingResourceDescriptionsData;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptionsData;


/**
 * A null version of {@link ResourceDescriptionsData}.
 */
public class NullResourceDescriptionsData extends DelegatingResourceDescriptionsData implements IResourceDescriptionsData {

  public NullResourceDescriptionsData() {
    super(new ResourceDescriptionsData(Collections.<IResourceDescription> emptyList()));
  }

  @Override
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return Collections.emptyList();
  }

  @Override
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    return Collections.emptyList();
  }

  @Override
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    return Collections.emptyList();
  }

  // TODO: raise exception on addResource()?
}
