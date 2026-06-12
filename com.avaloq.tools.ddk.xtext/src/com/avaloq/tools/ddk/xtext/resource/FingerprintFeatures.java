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
package com.avaloq.tools.ddk.xtext.resource;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * Shared helpers for iterating over the contents of {@link EStructuralFeature}s when computing fingerprints.
 */
final class FingerprintFeatures {

  private FingerprintFeatures() {
    // utility class
  }

  /**
   * Return an Iterable containing all the contents of the given feature of the given object. If the
   * feature is not many-valued, the resulting iterable will have one element. If the feature is an EReference,
   * the iterable may contain proxies. The iterable may contain null values.
   *
   * @param <T>
   *          The Generic type of the objects in the iterable
   * @param obj
   *          The object
   * @param feature
   *          The feature
   * @return An iterable over all the contents of the feature of the object.
   */
  @SuppressWarnings("unchecked")
  static <T> Iterable<T> featureIterable(final EObject obj, final EStructuralFeature feature) {
    if (feature == null) {
      return Collections.emptyList();
    }
    if (feature.isMany()) {
      if (feature instanceof EAttribute || ((EReference) feature).isContainment()) {
        return (Iterable<T>) obj.eGet(feature);
      }
      return new Iterable<T>() {
        @Override
        public Iterator<T> iterator() {
          EList<T> list = (EList<T>) obj.eGet(feature);
          if (list instanceof InternalEList<T> internalList) {
            return internalList.basicIterator(); // Don't resolve
          } else {
            return list.iterator();
          }
        }
      };
    }
    return Collections.singletonList((T) obj.eGet(feature, false)); // Don't resolve
  }

}
