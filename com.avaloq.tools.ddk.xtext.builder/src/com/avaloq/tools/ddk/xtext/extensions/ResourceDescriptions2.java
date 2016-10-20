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
package com.avaloq.tools.ddk.xtext.extensions;

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

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.avaloq.tools.ddk.xtext.resource.extensions.ResourceDescriptionsUtil;


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
    if (data instanceof IResourceDescriptions) {
      delegate = data;
    } else {
      delegate = new IResourceDescriptions() {

        @Override
        public boolean isEmpty() {
          return data.isEmpty();
        }

        @Override
        public Iterable<IEObjectDescription> getExportedObjects() {
          return data.getExportedObjects();
        }

        @Override
        public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
          return data.getExportedObjects(type, name, ignoreCase);
        }

        @Override
        public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
          return data.getExportedObjectsByType(type);
        }

        @Override
        public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
          return data.getExportedObjectsByObject(object);
        }

        @Override
        public Iterable<IResourceDescription> getAllResourceDescriptions() {
          return data.getAllResourceDescriptions();
        }

        @Override
        public IResourceDescription getResourceDescription(final URI normalizedURI) {
          return data.getResourceDescription(normalizedURI);
        }
      };
    }
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IResourceDescription> getAllResourceDescriptions() {
    return delegate.getAllResourceDescriptions();
  }

  /** {@inheritDoc} */
  @Override
  public IResourceDescription getResourceDescription(final URI normalizedURI) {
    return delegate.getResourceDescription(normalizedURI);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getExportedObjects() {
    return delegate.getExportedObjects();
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
    return delegate.getExportedObjects(type, name, ignoreCase);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
    return delegate.getExportedObjectsByType(type);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
    return delegate.getExportedObjectsByObject(object);
  }

  /** {@inheritDoc} */
  @Override
  public Set<URI> getAllURIs() {
    return ResourceDescriptionsUtil.getAllURIs(delegate);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    return ResourceDescriptionsUtil.findReferencesToResources(delegate, targetResources, matchPolicy);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    return ResourceDescriptionsUtil.findExactReferencingResources(delegate, targetObjects, matchPolicy);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
    return ResourceDescriptionsUtil.findReferencesToObjects(delegate, targetObjects);
  }

  /** {@inheritDoc} */
  public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final boolean matchImportedNames) {
    // TODO Auto-generated method stub
    return null;
  }

}
