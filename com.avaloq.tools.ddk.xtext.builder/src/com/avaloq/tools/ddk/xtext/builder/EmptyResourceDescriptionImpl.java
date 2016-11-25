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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;


/**
 * An always empty resource description.
 */
public class EmptyResourceDescriptionImpl extends AbstractResourceDescription {

  private final URI uri;

  public EmptyResourceDescriptionImpl(final URI uri) {
    super();
    this.uri = uri;
  }

  public URI getURI() {
    return uri;
  }

  public Iterable<QualifiedName> getImportedNames() {
    return ImmutableSet.of();
  }

  public Iterable<IReferenceDescription> getReferenceDescriptions() {
    return ImmutableSet.of();
  }

  /** {@inheritDoc} */
  @Override
  protected List<IEObjectDescription> computeExportedObjects() {
    return ImmutableList.of();
  }

}

