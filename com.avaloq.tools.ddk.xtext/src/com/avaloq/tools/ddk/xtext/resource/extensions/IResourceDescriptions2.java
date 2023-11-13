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
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.google.common.collect.ImmutableSet;


/**
 * Extension interface extending {@link IResourceDescriptions} with additional operations.
 */
public interface IResourceDescriptions2 extends IResourceDescriptions {

  /**
   * Policy to determine how references are to be matched.
   */
  enum ReferenceMatchPolicy {
    REFERENCES,
    IMPORTED_NAMES,
    UNRESOLVED_IMPORTED_NAMES,
    ALL_IMPORTED_NAMES,
    ALL;

    /**
     * Checks whether this policy includes the given other policy.
     *
     * @param policy
     *          other policy
     * @return true if this match policy includes the given policy
     */
    public boolean includes(final ReferenceMatchPolicy policy) {
      if (this == ALL || this == policy) {
        return true;
      } else if (this == ALL_IMPORTED_NAMES) {
        return policy == IMPORTED_NAMES || policy == UNRESOLVED_IMPORTED_NAMES;
      }
      return false;

    }
  }

  /**
   * Null implementation.
   */
  class NullImpl extends IResourceDescriptions.NullImpl implements IResourceDescriptions2 {

    @Override
    public Set<URI> getAllURIs() {
      return ImmutableSet.of();
    }

    @Override
    public Iterable<IResourceDescription> findAllReferencingResources(final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
      return ImmutableSet.of();
    }

    @Override
    public Iterable<IResourceDescription> findExactReferencingResources(final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
      return ImmutableSet.of();
    }

    @Override
    public Iterable<IReferenceDescription> findReferencesToObjects(final Set<URI> targetObjects) {
      return ImmutableSet.of();
    }
  }

  /**
   * Returns the set of all URIs in this resource descriptions.
   *
   * @return URI set
   */
  Set<URI> getAllURIs();

  /**
   * Find all {@link IResourceDescription}s of all resources containing cross-references to any of the resources. This may include any of the given resources
   * themselves.
   *
   * @param targetResources
   *          Target resources.
   * @param matchPolicy
   *          match policy applied to find matches
   * @return An {@link Iterable} of all {@link IResourceDescription}s that reference any of the resources.
   */
  Iterable<IResourceDescription> findAllReferencingResources(Set<IResourceDescription> targetResources, ReferenceMatchPolicy matchPolicy);

  /**
   * Find all {@link IResourceDescription}s of all resources containing cross-references to any of the objects. This may include any of the resources
   * containing the objects themselves.
   *
   * @param targetObjects
   *          Target objects.
   * @param matchPolicy
   *          match policy applied to find matches
   * @return An {@link Iterable} of all {@link IResourceDescription}s that reference any of the resources.
   */
  Iterable<IResourceDescription> findExactReferencingResources(Set<IEObjectDescription> targetObjects, ReferenceMatchPolicy matchPolicy);

  /**
   * Find all {@link IReferenceDescription}s of cross-references to a set of {@link org.eclipse.emf.ecore.EObject EObjects} identified by {@link URI}.
   *
   * @param targetObjects
   *          {@link URI} of the target objects
   * @return An {@link Iterable} of all {@link IReferenceDescription}s of all cross-references that reference the given objects.
   */
  Iterable<IReferenceDescription> findReferencesToObjects(Set<URI> targetObjects);

}
