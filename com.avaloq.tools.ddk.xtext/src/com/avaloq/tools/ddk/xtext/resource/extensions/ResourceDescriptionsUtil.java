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
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2.ReferenceMatchPolicy;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;


/**
 * Provides implementations of the {@code IResourceDescriptions2} operations.
 */
public final class ResourceDescriptionsUtil {

  private ResourceDescriptionsUtil() {
  }

  /**
   * Returns all URIs in the given resource descriptions object.
   *
   * @param descriptions
   *          {@link IResourceDescriptions} to get the URIs for
   * @return set of all URIs
   */
  public static Set<URI> getAllURIs(final IResourceDescriptions descriptions) {
    Set<URI> allURIs = Sets.newHashSetWithExpectedSize(Iterables.size(descriptions.getAllResourceDescriptions()));
    for (IResourceDescription desc : descriptions.getAllResourceDescriptions()) {
      allURIs.add(desc.getURI());
    }
    return allURIs;
  }

  /**
   * Find all {@link IResourceDescription}s of all resources containing cross-references to any of the objects.
   *
   * @param descriptions
   *          {@link IResourceDescriptions} to find the references in.
   * @param targetResources
   *          Target objects.
   * @param matchPolicy
   *          match policy
   * @return An {@link Iterable} of all {@link IResourceDescription}s that reference any of the objects.
   */
  public static Iterable<IResourceDescription> findReferencesToResources(final IResourceDescriptions descriptions, final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    if (targetResources.isEmpty()) {
      return ImmutableSet.of();
    }

    return findReferencesToResources(descriptions.getAllResourceDescriptions(), targetResources, matchPolicy);
  }

  /**
   * Find all {@link IResourceDescription}s of all resources containing cross-references to any of the objects.
   *
   * @param descriptions
   *          The {@link IResourceDescription}s to find the references in.
   * @param targetResources
   *          Target objects.
   * @param matchPolicy
   *          match policy
   * @return An {@link Iterable} of all {@link IResourceDescription}s that reference any of the objects.
   */
  @SuppressWarnings("PMD.NPathComplexity")
  public static Iterable<IResourceDescription> findReferencesToResources(final Iterable<IResourceDescription> descriptions, final Set<IResourceDescription> targetResources, final ReferenceMatchPolicy matchPolicy) {
    if (targetResources.isEmpty()) {
      return ImmutableSet.of();
    }

    final boolean matchNames = matchPolicy.includes(ReferenceMatchPolicy.IMPORTED_NAMES)
        || matchPolicy.includes(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES);
    final Set<URI> targetUris = Sets.newHashSetWithExpectedSize(targetResources.size());
    final Set<QualifiedName> exportedNames = Sets.newHashSet();
    for (IResourceDescription res : targetResources) {
      targetUris.add(res.getURI());
      if (matchNames) {
        for (IEObjectDescription obj : res.getExportedObjects()) {
          if (matchPolicy.includes(ReferenceMatchPolicy.IMPORTED_NAMES)) {
            exportedNames.add(obj.getName().toLowerCase());
          }
          if (matchPolicy.includes(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES)) {
            exportedNames.add(QualifiedNames.toUnresolvedName(obj.getName().toLowerCase()));
          }
        }
      }
    }

    return Iterables.filter(//
        Iterables.filter(descriptions, input -> !targetResources.contains(input)) //
        , input -> {
          if (matchNames) {
            for (QualifiedName name : input.getImportedNames()) {
              if (exportedNames.contains(name.toLowerCase())) { // NOPMD
                return true;
              }
            }
          }
          if (matchPolicy.includes(ReferenceMatchPolicy.REFERENCES)) {
            for (IReferenceDescription ref : input.getReferenceDescriptions()) {
              if (targetUris.contains(ref.getTargetEObjectUri().trimFragment())) {
                return true;
              }
            }
          }
          return false;
        });
  }

  /**
   * Utility implementation to find all exact references to a set of objects.
   *
   * @param descriptions
   *          context
   * @param targetObjects
   *          objects to find references to
   * @param matchPolicy
   *          match policy
   * @return all resources containing outgoing references to one of the objects
   */
  @SuppressWarnings("PMD.NPathComplexity")
  public static Iterable<IResourceDescription> findExactReferencingResources(final IResourceDescriptions descriptions, final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    if (targetObjects.isEmpty()) {
      return ImmutableSet.of();
    }

    return findExactReferencingResources(descriptions.getAllResourceDescriptions(), targetObjects, matchPolicy);
  }

  /**
   * Utility implementation to find all exact references to a set of objects.
   *
   * @param descriptions
   *          context
   * @param targetObjects
   *          objects to find references to
   * @param matchPolicy
   *          match policy
   * @return all resources containing outgoing references to one of the objects
   */
  @SuppressWarnings("PMD.NPathComplexity")
  public static Iterable<IResourceDescription> findExactReferencingResources(final Iterable<IResourceDescription> descriptions, final Set<IEObjectDescription> targetObjects, final ReferenceMatchPolicy matchPolicy) {
    if (targetObjects.isEmpty()) {
      return ImmutableSet.of();
    }

    final Set<URI> targetUris = Sets.newHashSetWithExpectedSize(targetObjects.size());
    final Set<QualifiedName> exportedNames = Sets.newHashSet();
    for (IEObjectDescription obj : targetObjects) {
      targetUris.add(obj.getEObjectURI());
      if (matchPolicy.includes(ReferenceMatchPolicy.IMPORTED_NAMES)) {
        exportedNames.add(obj.getName());
      }
      if (matchPolicy.includes(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES)) {
        exportedNames.add(QualifiedNames.toUnresolvedName(obj.getName()));
      }
    }

    return Iterables.filter(descriptions, input -> {
      if (matchPolicy.includes(ReferenceMatchPolicy.IMPORTED_NAMES) || matchPolicy.includes(ReferenceMatchPolicy.UNRESOLVED_IMPORTED_NAMES)) {
        for (QualifiedName name : input.getImportedNames()) {
          if (exportedNames.contains(name)) {
            return true;
          }
        }
      }
      if (matchPolicy.includes(ReferenceMatchPolicy.REFERENCES)) {
        for (IReferenceDescription ref : input.getReferenceDescriptions()) {
          if (targetUris.contains(ref.getTargetEObjectUri())) {
            return true;
          }
        }
      }
      return false;
    });
  }

  /**
   * Find all {@link IReferenceDescription}s of cross-references to a set of {@link org.eclipse.emf.ecore.EObject EObjects} identified by {@link URI}.
   *
   * @param descriptions
   *          {@link IResourceDescriptions} to find the references in.
   * @param targetObjects
   *          {@link URI} of the target object
   * @return An {@link Iterable} of all {@link IReferenceDescription}s of all cross-references that reference the given objects.
   */
  public static Iterable<IReferenceDescription> findReferencesToObjects(final IResourceDescriptions descriptions, final Set<URI> targetObjects) {
    return findReferencesToObjects(descriptions.getAllResourceDescriptions(), targetObjects);
  }

  /**
   * Find all {@link IReferenceDescription}s of cross-references to a set of {@link org.eclipse.emf.ecore.EObject EObjects} identified by {@link URI}.
   *
   * @param descriptions
   *          The {@link IReferenceDescription}s to find the references in.
   * @param targetObjects
   *          {@link URI} of the target object
   * @return An {@link Iterable} of all {@link IReferenceDescription}s of all cross-references that reference the given objects.
   */
  public static Iterable<IReferenceDescription> findReferencesToObjects(final Iterable<IResourceDescription> descriptions, final Set<URI> targetObjects) {
    return Iterables.concat(Iterables.transform(descriptions, from -> Iterables.filter(//
        from.getReferenceDescriptions(), input -> targetObjects.contains(input.getTargetEObjectUri()))));
  }

}
