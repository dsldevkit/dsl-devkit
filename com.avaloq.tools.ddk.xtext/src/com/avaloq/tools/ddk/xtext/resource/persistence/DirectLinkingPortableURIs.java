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

package com.avaloq.tools.ddk.xtext.resource.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.resource.persistence.PortableURIs;
import org.eclipse.xtext.resource.persistence.StorageAwareResource;

import com.google.inject.Inject;


/**
 * Subclass of {@link PortableURIs} which makes sure that URIs get serialized as-is (i.e. not made portable) under the assumption that the URIs are portable
 * already. {@link LazyURIEncoder#isCrossLinkFragment(org.eclipse.emf.ecore.resource.Resource, String) Lazy-linking URIs} however will be
 * {@link #resolve(StorageAwareResource, String) resolved} to {@code null} when deserialized.
 */
public class DirectLinkingPortableURIs extends PortableURIs {

  private static final String UNRESOLVED_LAZY_LINK = PORTABLE_SCHEME + "none"; //$NON-NLS-1$

  @Inject
  private LazyURIEncoder uriEncoder;

  @Override
  public URI toPortableURI(final StorageAwareResource sourceResource, final URI uri) {
    String fragment = uri.fragment();
    if (uriEncoder.isCrossLinkFragment(sourceResource, fragment)) {
      return uri.trimFragment().appendFragment(UNRESOLVED_LAZY_LINK);
    }
    return null;
  }

  @Override
  public EObject resolve(final StorageAwareResource resource, final String portableFragment) {
    if (portableFragment.equals(UNRESOLVED_LAZY_LINK)) {
      return null;
    }
    return super.resolve(resource, portableFragment);
  }
}
