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
package com.avaloq.tools.ddk.xtext.test.model;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;


/**
 * Utility methods for extracting elements from an EMF model.
 */
@SuppressWarnings("PMD.NullAssignment")
public class ModelUtil {

  /**
   * Gets the all instances of given type <code>type</code>.
   * 
   * @param <T>
   *          the generic type
   * @param context
   *          the context
   * @param type
   *          the type
   * @return all matching instances
   */
  public <T extends EObject> Iterable<T> getAllInstancesOf(final EObject context, final Class<T> type) {
    return new Iterable<T>() {
      public Iterator<T> iterator() {
        return Iterators.filter(context.eAllContents(), type);
      }
    };
  }

  /**
   * Gets the first instance of the given type.
   * 
   * @param <T>
   *          the generic type
   * @param context
   *          the context object
   * @param type
   *          the type
   * @return the first instance found or {@code null} if none found matching given criteria
   */
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type) {
    Iterator<T> it = getAllInstancesOf(context, type).iterator();
    return it.hasNext() ? it.next() : null;
  }

  /**
   * Gets all instances of given type <code>type</code> having given value <code>value</code> on structural feature <code>feature</code>.
   * 
   * @param <T>
   *          the generic type
   * @param context
   *          the context
   * @param type
   *          the type
   * @param feature
   *          the feature
   * @param value
   *          the value
   * @return all matching instances
   * @throws AssertionError
   *           if the given feature doesn't match the given type
   */
  // CHECKSTYLE:OFF
  public <T extends EObject> Iterable<T> getAllInstancesOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) throws AssertionError {
    // CHECKSTYLE:ON
    return Iterables.filter(getAllInstancesOf(context, type), new Predicate<T>() {
      public boolean apply(final T input) {
        if (input.eClass().getEStructuralFeature(feature.getFeatureID()) != feature) {
          Assert.fail("Feature " + feature + " is not a feature of " + input.eClass());
        }
        final Object valueOfFeature = input.eGet(feature);
        return valueOfFeature != null && valueOfFeature.equals(value);
      }
    });
  }

  /**
   * Gets the first instance of given type containing a given structural feature with given value using a given context object.
   * 
   * @param <T>
   *          the generic type
   * @param context
   *          the context object
   * @param type
   *          the type
   * @param feature
   *          the structural feature
   * @param value
   *          the value
   * @return the first instance found or {@code null} if none found matching given criteria
   * @throws AssertionError
   *           if the given feature doesn't match the given type
   */
  // CHECKSTYLE:OFF
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) throws AssertionError {
    // CHECKSTYLE:ON
    Iterator<T> it = getAllInstancesOf(context, type, feature, value).iterator();
    return it.hasNext() ? it.next() : null;
  }

}

