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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IReferenceDescription;

import com.avaloq.tools.ddk.xtext.resource.ReferenceDescription;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;


/**
 * Adapter which registers implicit references between two resources. Note that both the source and target objects of these references don't actually exist.
 */
public class ImplicitReferencesAdapter extends AdapterImpl {

  /** Constant URI fragment used for both source and target object of reference. */
  private static final String IMPLICIT_FRAGMENT = URI.encodeFragment("implicit!", false); //$NON-NLS-1$

  /** Constant URI fragment used for both source and target object of reference. */
  public static final String INFERRED_FRAGMENT = URI.encodeFragment("inferred!", false); //$NON-NLS-1$

  private final Set<URI> inferenceDependencies = Sets.newHashSet();

  /**
   * Returns the adapter registered for a resource.
   *
   * @param resource
   *          resource to get adapter for
   * @return the registered adapter or <code>null</code>
   */
  public static ImplicitReferencesAdapter find(final Resource resource) {
    for (Adapter adapter : resource.eAdapters()) {
      if (adapter instanceof ImplicitReferencesAdapter) {
        return (ImplicitReferencesAdapter) adapter;
      }
    }
    return null;
  }

  /**
   * Returns the implicit references adapter for the given resource. It will be added first if it doesn't already exist.
   *
   * @param resource
   *          resource to get adapter for
   * @return the registered adapter
   */
  public static ImplicitReferencesAdapter findOrCreate(final Resource resource) {
    final ImplicitReferencesAdapter adapter = ImplicitReferencesAdapter.find(resource);
    if (adapter != null) {
      return adapter;
    }
    final ImplicitReferencesAdapter result = new ImplicitReferencesAdapter();
    resource.eAdapters().add(result);
    return result;
  }

  private final Set<URI> implicitReferences = Sets.newHashSet();

  /**
   * Adds an implicit reference to the given URI (object or resource URI).
   *
   * @param uri
   *          target URI of implicit reference
   */
  public void addImplicitReference(final URI uri) {
    implicitReferences.add(uri);
  }

  /**
   * Adds an inference dependency to the given URI (object or resource URI).
   *
   * @param resourceUri
   *          resource required in the inference of the target resource
   */
  public void addInferenceDependency(final URI resourceUri) {
    inferenceDependencies.add(resourceUri);
  }

  /**
   * Returns all inferred and implicit references registered with this adapter.
   *
   * @return implicit references
   */
  public Iterable<IReferenceDescription> getImplicitReferences() {
    final URI contextURI = ((Resource) getTarget()).getURI().appendFragment(IMPLICIT_FRAGMENT);
    final Set<IReferenceDescription> result = Sets.newHashSet(Iterables.transform(implicitReferences, new Function<URI, IReferenceDescription>() {
      @Override
      public IReferenceDescription apply(final URI target) {
        return new ReferenceDescription(contextURI, target.hasFragment() ? target
            : target.appendFragment(IMPLICIT_FRAGMENT), EcorePackage.Literals.EFACTORY__EPACKAGE, null, -1);
      }
    }));
    for (final URI uri : inferenceDependencies) {
      result.add(new ReferenceDescription(contextURI, uri.appendFragment(INFERRED_FRAGMENT), EcorePackage.Literals.EFACTORY__EPACKAGE, null, -1));
    }
    return result;
  }

  /**
   * Clears the registered implicit references.
   */
  public void clear() {
    implicitReferences.clear();
    inferenceDependencies.clear();
  }

}
