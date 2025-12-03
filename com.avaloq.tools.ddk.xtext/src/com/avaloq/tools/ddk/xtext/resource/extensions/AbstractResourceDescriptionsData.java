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
package com.avaloq.tools.ddk.xtext.resource.extensions;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;


/**
 * Unfortunately, {@link ResourceDescriptionsData} does not implement {@link org.eclipse.xtext.resource.IResourceDescriptions IResourceDescriptions}...
 */
public abstract class AbstractResourceDescriptionsData extends ResourceDescriptionsData implements IResourceDescriptionsData {

  public AbstractResourceDescriptionsData() {
    this(Collections.<IResourceDescription> emptyList());
  }

  protected AbstractResourceDescriptionsData(final Iterable<IResourceDescription> resourceDescriptions) {
    super(resourceDescriptions);
  }

  protected AbstractResourceDescriptionsData(final Map<URI, IResourceDescription> resourceDescriptionMap, final Map<QualifiedName, Object> lookupMap) {
    super(resourceDescriptionMap, lookupMap);
  }

}
