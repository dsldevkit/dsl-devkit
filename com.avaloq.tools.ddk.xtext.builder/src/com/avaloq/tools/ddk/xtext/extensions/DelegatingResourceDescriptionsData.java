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
package com.avaloq.tools.ddk.xtext.extensions;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.google.common.collect.Sets;


/**
 * A {@link ResourceDescriptionsData} that delegates all operations to another {@link ResourceDescriptionsData}.
 */
public class DelegatingResourceDescriptionsData extends AbstractResourceDescriptionsData implements IResourceDescriptionsData {

  private final ResourceDescriptionsData delegate;
  private final IResourceDescriptions2 wrapped;

  public DelegatingResourceDescriptionsData(final Iterable<IResourceDescription> descriptions) {
    this(new ResourceDescriptionsData(descriptions));
  }

  public DelegatingResourceDescriptionsData(final ResourceDescriptionsData resourceDescriptionsData) {
    super(null, null);
    delegate = resourceDescriptionsData;
    wrapped = new ResourceDescriptions2(delegate);
  }

  @Override
  public ResourceDescriptionsData copy() {
    return new DelegatingResourceDescriptionsData(delegate.copy());
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public Iterable<IResourceDescription> getAllResourceDescriptions() {
    return delegate.getAllResourceDescriptions();
  }

  @Override
  public IResourceDescription getResourceDescription(final URI uri) {
    return delegate.getResourceDescription(uri);
  }

  @Override
  public void removeDescription(final URI uri) {
    delegate.removeDescription(uri);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects() {
    return delegate.getExportedObjects();
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName qualifiedName, final boolean ignoreCase) {
    return delegate.getExportedObjects(type, qualifiedName, ignoreCase);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
    return delegate.getExportedObjectsByObject(object);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
    return delegate.getExportedObjectsByType(type);
  }

  @Override
  protected Iterable<IResourceDescription> getSelectables() {
    return delegate.getAllResourceDescriptions(); // delegate.getSelectables is not visible here?!
  }

  @Override
  public Set<URI> getAllURIs() {
    return delegate.getAllURIs();
  }

  @Override
  public void addDescription(final URI uri, final IResourceDescription newDescription) {
    delegate.addDescription(uri, newDescription);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return wrapped.findAllReferencingResources(targetResources, matchPolicy);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    return wrapped.findExactReferencingResources(targetObjects, matchPolicy);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    return wrapped.findReferencesToObjects(targetObjects);
  }

  /** {@inheritDoc} */
  @Override
  public void importData(final Iterable<IResourceDescription> descriptions) {
    for (IResourceDescription desc : descriptions) {
      delegate.addDescription(desc.getURI(), desc);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    for (URI uri : Sets.newHashSet(delegate.getAllURIs())) {
      delegate.removeDescription(uri);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void beginChanges() {
    // No-op.
  }

  /** {@inheritDoc} */
  @Override
  public void flushChanges() {
    // No-op.
  }

  /** {@inheritDoc} */
  @Override
  public void commitChanges() {
    // No-op.
  }

  /** {@inheritDoc} */
  @Override
  public void rollbackChanges() {
    // No-op.
  }

  protected ResourceDescriptionsData getDelegate() {
    return delegate;
  }

}
