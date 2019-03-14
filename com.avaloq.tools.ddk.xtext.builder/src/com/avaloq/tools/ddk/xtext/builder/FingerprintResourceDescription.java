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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
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
    return new LightEObjectDescription(result, fingerprint);
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

  /**
   * The memory-efficient minimal implementation of the {@link IEObjectDescription}.
   * Essentially contains only the data necessary for the computation of invalidations and should be used in this context only.
   */
  private static class LightEObjectDescription implements IEObjectDescription {

    private static final String[] EMPTY_ARRAY = new String[0];

    private final EObject element;
    private final String fingerprint;
    private URI normalizedURI;

    LightEObjectDescription(final EObject element, final String fingerprint) {
      this.element = element;
      this.fingerprint = fingerprint;
    }

    @Override
    public QualifiedName getName() {
      throw new UnsupportedOperationException();
    }

    @Override
    public QualifiedName getQualifiedName() {
      throw new UnsupportedOperationException();
    }

    @Override
    public EObject getEObjectOrProxy() {
      return element;
    }

    @Override
    public URI getEObjectURI() {
      if (normalizedURI == null) {
        normalizedURI = normalize(element, EcoreUtil.getURI(element));
      }
      return normalizedURI;
    }

    @Override
    public EClass getEClass() {
      return element.eClass();
    }

    @Override
    public String getUserData(final String key) {
      if (IFingerprintComputer.OBJECT_FINGERPRINT.equals(key)) {
        return fingerprint;
      }
      return null;
    }

    @Override
    public String[] getUserDataKeys() {
      if (fingerprint != null) {
        return new String[] {IFingerprintComputer.OBJECT_FINGERPRINT};
      }
      return EMPTY_ARRAY; // NOPMD - we don't really expose anything here
    }

    private static URI normalize(final EObject element, final URI uri) {
      // Copied from EObjectDescription
      if (uri != null && !uri.isPlatform() && element != null && element.eResource() != null) {
        ResourceSet resourceSet = element.eResource().getResourceSet();
        if (resourceSet != null) {
          return resourceSet.getURIConverter().normalize(uri);
        }
      }
      return uri;
    }

  }

}
