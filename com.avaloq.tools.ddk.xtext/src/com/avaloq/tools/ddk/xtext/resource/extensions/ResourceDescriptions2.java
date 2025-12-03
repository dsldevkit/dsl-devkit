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

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;


/**
 * Wrapper to convert old-style {@link IResourceDescriptions} into new-style {@link IResourceDescriptions2}. The new operations are implemented as simple
 * filtered iterations over {@link IResourceDescriptions#getAllResourceDescriptions getAllResourceDescriptions}.
 */
public class ResourceDescriptions2 implements IResourceDescriptions2 {

  private final IResourceDescriptions delegate;

  public ResourceDescriptions2(final IResourceDescriptions data) {
    delegate = data;
  }

  public ResourceDescriptions2(final ResourceDescriptionsData data) {
    delegate = data;
  }

  @Override
  public Iterable<IResourceDescription> getAllResourceDescriptions() {
    return delegate.getAllResourceDescriptions();
  }

  @Override
  public IResourceDescription getResourceDescription(final URI normalizedURI) {
    return delegate.getResourceDescription(normalizedURI);
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects() {
    return delegate.getExportedObjects();
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
    return delegate.getExportedObjects(type, name, ignoreCase);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
    return delegate.getExportedObjectsByType(type);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
    return delegate.getExportedObjectsByObject(object);
  }

  @Override
  public Set<URI> getAllURIs() {
    return ResourceDescriptionsUtil.getAllURIs(delegate);
  }

  @Override
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return ResourceDescriptionsUtil.findReferencesToResources(delegate, targetResources, matchPolicy);
  }

  @Override
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    return ResourceDescriptionsUtil.findExactReferencingResources(delegate, targetObjects, matchPolicy);
  }

  @Override
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    return ResourceDescriptionsUtil.findReferencesToObjects(delegate, targetObjects);
  }

  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final boolean matchImportedNames) {
    return null;
  }

}
