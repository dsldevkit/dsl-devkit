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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;

import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
import com.avaloq.tools.ddk.xtext.resource.PatternAwareEObjectDescriptionLookUp;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescription2;
import com.google.common.collect.ImmutableList;


/**
 * Lightweight resource description that contains only the data necessary for the invalidations.
 * That is, the resource URI and a list of exported objects' descriptions with their fingerprints.
 */
public class FingerprintResourceDescription extends AbstractResourceDescription implements IResourceDescription2 {

  private final URI uri;
  private final List<IEObjectDescription> exportedObjects;

  public FingerprintResourceDescription(final IResourceDescription original) {
    this.uri = original.getURI();
    ImmutableList.Builder<IEObjectDescription> builer = ImmutableList.builder();
    for (IEObjectDescription description : original.getExportedObjects()) {
      builer.add(createLightDescription(description));
    }
    this.exportedObjects = builer.build();
  }

  private static IEObjectDescription createLightDescription(final IEObjectDescription original) {
    InternalEObject result = (InternalEObject) EcoreUtil.create(original.getEClass());
    result.eSetProxyURI(original.getEObjectURI());
    String fingerprint = original.getUserData(IFingerprintComputer.OBJECT_FINGERPRINT);
    Map<String, String> data = fingerprint != null ? Collections.singletonMap(IFingerprintComputer.OBJECT_FINGERPRINT, fingerprint) : Collections.emptyMap();
    return EObjectDescription.create(original.getName(), result, data);
  }

  @Override
  public URI getURI() {
    return uri;
  }

  @Override
  protected List<IEObjectDescription> computeExportedObjects() {
    return exportedObjects;
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

  @Override
  public Map<QualifiedName, Set<EClass>> getImportedNamesTypes() {
    return Collections.emptyMap();
  }

}
