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
package com.avaloq.tools.ddk.xtext.util;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * Utility methods for EMF {@link ResourceSet}.
 */
public final class EmfResourceSetUtil {

  /** Prevents instantiation. */
  private EmfResourceSetUtil() {}

  /**
   * Clear the contents of the given {@link ResourceSet} without sending any notifications to the {@link ResourceSet}'s adapters.
   *
   * @param resourceSet
   *          the {@link ResourceSet} to be cleared, must not be {@code null}
   */
  public static void clearResourceSetWithoutNotifications(final ResourceSet resourceSet) {
    boolean wasDeliver = resourceSet.eDeliver();
    try {
      resourceSet.eSetDeliver(false);
      clearResourceSet(resourceSet);
    } finally {
      resourceSet.eSetDeliver(wasDeliver);
    }
  }

  /**
   * Clear the contents of the given {@link ResourceSet}.
   *
   * @param resourceSet
   *          the {@link ResourceSet} to be cleared, must not be {@code null}
   */
  public static void clearResourceSet(final ResourceSet resourceSet) {
    if (resourceSet instanceof ResourceSetImpl) {
      Map<URI, Resource> uriMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();
      if (uriMap != null) {
        uriMap.clear();
      }
    }
    resourceSet.getResources().clear();
  }
}
