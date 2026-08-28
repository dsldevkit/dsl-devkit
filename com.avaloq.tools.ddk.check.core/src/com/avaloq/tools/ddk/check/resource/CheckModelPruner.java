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

package com.avaloq.tools.ddk.check.resource;

import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xtype.XComputedTypeReference;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;


/**
 * Creates a copy of a check catalog model that only contains the publicly visible API of the catalog, that is, everything a consumer of a persisted catalog
 * needs in order to reference its checks: the catalog itself, its categories, checks, formal parameters and the context variables the checks apply to.
 * <p>
 * Everything describing <em>how</em> a check is implemented is removed: the constraint expressions of the contexts, the catalog members, the
 * {@link com.avaloq.tools.ddk.check.check.Implementation implementations} and the import section. The inferred JVM model is not part of the copy either since
 * only the catalog itself is copied.
 * </p>
 */
public final class CheckModelPruner {

  private CheckModelPruner() {
    // Empty constructor to avoid instantiation.
  }

  /**
   * Creates a new, detached resource containing a copy of the given resource's check catalog from which all implementation details have been removed. The given
   * resource is not modified.
   *
   * @param resource
   *          the resource holding the check catalog to copy, must not be {@code null}
   * @return a detached resource containing the pruned copy of the catalog, or {@code null} if the given resource does not contain a check catalog
   */
  public static Resource createPrunedResource(final Resource resource) {
    if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof CheckCatalog)) {
      return null;
    }
    CheckCatalog catalog = (CheckCatalog) resource.getContents().get(0);
    // Computed type references can only be resolved as long as they are attached to their type provider, which is not copied.
    resolveComputedTypeReferences(catalog);

    EcoreUtil.Copier copier = new EcoreUtil.Copier(true, true);
    CheckCatalog prunedCatalog = (CheckCatalog) copier.copy(catalog);
    copier.copyReferences();

    Resource prunedResource = new ResourceImpl(resource.getURI());
    prunedResource.getContents().add(prunedCatalog);

    removeImplementation(prunedCatalog);
    // References to objects that were either removed above or that belong to the inferred JVM model would otherwise still be serialized.
    removeDanglingReferences(prunedCatalog, resource);
    return prunedResource;
  }

  /**
   * Forces the resolution of all computed type references of the given catalog so that their equivalents are available in a copy of the catalog.
   *
   * @param catalog
   *          the catalog to resolve the computed type references of, must not be {@code null}
   */
  private static void resolveComputedTypeReferences(final CheckCatalog catalog) {
    for (TreeIterator<EObject> contents = catalog.eAllContents(); contents.hasNext();) {
      EObject object = contents.next();
      if (object instanceof XComputedTypeReference) {
        ((XComputedTypeReference) object).getType();
      }
    }
  }

  /**
   * Removes everything describing the implementation of the checks of the given catalog.
   *
   * @param catalog
   *          the catalog to prune, must not be {@code null}
   */
  private static void removeImplementation(final CheckCatalog catalog) {
    catalog.setImports(null);
    catalog.getMembers().clear();
    catalog.getImplementations().clear();
    for (Check check : catalog.getAllChecks()) {
      for (Context context : check.getContexts()) {
        context.setConstraint(null);
      }
    }
  }

  /**
   * Unsets all cross references of the given object and its contents that no longer have a valid target, that is, that either still point to an object of the
   * given resource or to an object that is no longer contained in any resource because it has been removed.
   *
   * @param root
   *          the root of the object tree to clean up, must not be {@code null}
   * @param resource
   *          the resource whose objects must no longer be referenced, must not be {@code null}
   */
  private static void removeDanglingReferences(final EObject root, final Resource resource) {
    unsetDanglingReferences(root, resource);
    for (TreeIterator<EObject> contents = root.eAllContents(); contents.hasNext();) {
      unsetDanglingReferences(contents.next(), resource);
    }
  }

  /**
   * Unsets all cross references of the given object that no longer have a valid target.
   *
   * @param object
   *          the object to clean up, must not be {@code null}
   * @param resource
   *          the resource whose objects must no longer be referenced, must not be {@code null}
   */
  private static void unsetDanglingReferences(final EObject object, final Resource resource) {
    for (EReference reference : object.eClass().getEAllReferences()) {
      if (reference.isContainment() || reference.isContainer() || reference.isDerived() || !reference.isChangeable() || !object.eIsSet(reference)) {
        continue;
      }
      if (reference.isMany()) {
        ((Collection<?>) object.eGet(reference, false)).removeIf(value -> isDangling(value, resource));
      } else if (isDangling(object.eGet(reference, false), resource)) {
        object.eUnset(reference);
      }
    }
  }

  /**
   * Tests whether the given reference value can no longer be serialized, that is, whether it is an object of the given resource or an object that is no longer
   * contained in any resource. Proxies are never dangling: they are serialized as the URI they point to.
   *
   * @param value
   *          the value to test, may be {@code null}
   * @param resource
   *          the resource whose objects must no longer be referenced, must not be {@code null}
   * @return {@code true} if the value must not be serialized
   */
  private static boolean isDangling(final Object value, final Resource resource) {
    if (!(value instanceof EObject) || ((EObject) value).eIsProxy()) {
      return false;
    }
    Resource targetResource = ((EObject) value).eResource();
    return targetResource == resource || targetResource == null;
  }

}
