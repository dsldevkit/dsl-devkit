/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.core.test.util;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.EcoreUtil2;

@SuppressWarnings("all")
public class CheckTestUtil {
  /**
   * Gets the first instance of given type in given context object.
   */
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type) {
    return this.<T>getInstanceOf(context, type, null, null, 1);
  }
  
  /**
   * Gets any instance of given type containing a given structural feature with given value using a given context object.
   */
  public <T extends EObject> T getInstanceOf(final EObject context, final Class<T> type, final int instance) {
    return this.<T>getInstanceOf(context, type, null, null, instance);
  }
  
  /**
   * Gets the all instances of given type <code>type</code> having given value <code>value</code> on structural feature <code>feature</code>.
   */
  public <T extends EObject> Iterable<T> getAllInstancesOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) {
    final ArrayList<T> result = Lists.<T>newArrayList();
    List<T> _allContentsOfType = EcoreUtil2.<T>getAllContentsOfType(context, type);
    for (final T candidate : _allContentsOfType) {
      {
        Object valueOfFeature = candidate.eGet(feature);
        if (((!Objects.equal(valueOfFeature, null)) && valueOfFeature.equals(value))) {
          result.add(candidate);
        }
      }
    }
    return result;
  }
  
  /**
   * Gets the first instance of given type containing a given structural feature with given value using a given context object.
   */
  public <T extends EObject> T getFirstInstanceOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value) {
    return this.<T>getInstanceOf(context, type, feature, value, 1);
  }
  
  /**
   * Gets any instance of given type containing a given structural feature with given value using a given context object.
   */
  public <T extends EObject> T getInstanceOf(final EObject context, final Class<T> type, final EStructuralFeature feature, final Object value, final int instance) {
    int skip = (instance - 1);
    List<T> _allContentsOfType = EcoreUtil2.<T>getAllContentsOfType(context, type);
    for (final T candiadate : _allContentsOfType) {
      if (((Objects.equal(feature, null) && Objects.equal(value, null)) || candiadate.eGet(feature).equals(value))) {
        if ((skip == 0)) {
          return candiadate;
        } else {
          skip = (skip - 1);
        }
      }
    }
    return null;
  }
}
