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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IReferenceDescription;


/**
 * A Reference Description that doesn't include indexInList. Saves place in memory and on disk.
 */
public final class DdkReferenceDescription implements IReferenceDescription, IDetachableDescription<IReferenceDescription> {

  private final URI sourceEObjectURI;
  private final URI containerEObjectURI;
  private final EReference eReference;
  private final int indexInList;
  private final URI targetEObjectUri;

  /**
   * Creates a new instance of {@link DdkReferenceDescription}.
   *
   * @param from
   * @param to
   * @param eReference
   * @param containerEObjectURI
   */
  public DdkReferenceDescription(final EObject from, final EObject to, final EReference eReference, final URI containerEObjectURI, final int indexInList) {
    this.sourceEObjectURI = EcoreUtil.getURI(from);
    this.targetEObjectUri = EcoreUtil.getURI(to);
    this.eReference = eReference;
    this.containerEObjectURI = containerEObjectURI;
    this.indexInList = indexInList;
  }

  /**
   * Creates a new instance of {@link DdkReferenceDescription}.
   *
   * @param sourceEObjectURI
   * @param targetEObjectUri
   * @param eReference
   * @param containerEObjectURI
   */
  public DdkReferenceDescription(final URI sourceEObjectURI, final URI targetEObjectUri, final EReference eReference, final URI containerEObjectURI, final int indexInList) {
    this.sourceEObjectURI = sourceEObjectURI;
    this.targetEObjectUri = targetEObjectUri;
    this.eReference = eReference;
    this.containerEObjectURI = containerEObjectURI;
    this.indexInList = indexInList;
  }

  /** {@inheritDoc} */
  @Override
  public int getIndexInList() {
    return indexInList;
  }

  /** {@inheritDoc} */
  @Override
  public URI getSourceEObjectUri() {
    return sourceEObjectURI;
  }

  /** {@inheritDoc} */
  @Override
  public URI getTargetEObjectUri() {
    return this.targetEObjectUri;
  }

  /** {@inheritDoc} */
  @Override
  public EReference getEReference() {
    return this.eReference;
  }

  /** {@inheritDoc} */
  @Override
  public URI getContainerEObjectURI() {
    return this.containerEObjectURI;
  }

  /** {@inheritDoc} */
  @Override
  public IReferenceDescription detach() {
    return this;
  }
}
