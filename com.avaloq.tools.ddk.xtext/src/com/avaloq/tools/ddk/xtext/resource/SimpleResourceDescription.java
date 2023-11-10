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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;


/**
 * A resource description implementation containing exported objects only.
 */
public class SimpleResourceDescription extends AbstractResourceDescription {

  private final URI uri;
  private final List<IEObjectDescription> exported;

  public SimpleResourceDescription(final URI uri, final List<IEObjectDescription> exportedObjects) {
    this.uri = uri;
    this.exported = exportedObjects;
  }

  @Override
  public URI getURI() {
    return uri;
  }

  @Override
  protected List<IEObjectDescription> computeExportedObjects() {
    return exported;
  }

  @Override
  protected EObjectDescriptionLookUp getLookUp() {
    if (lookup == null) {
      lookup = new PatternAwareEObjectDescriptionLookUp(computeExportedObjects());
    }
    return lookup;
  }

  
  @Override
  public Iterable<QualifiedName> getImportedNames() {
    return Collections.emptyList();
  }

  
  @Override
  public Iterable<IReferenceDescription> getReferenceDescriptions() {
    return Collections.emptyList();
  }

}
