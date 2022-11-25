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
package com.avaloq.tools.ddk.check.core.test.util

import com.google.common.collect.Lists
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2

class CheckTestUtil {

  /*
   * Gets the first instance of given type in given context object.
   */
	def <T extends EObject> T getFirstInstanceOf(EObject context, Class<T> type) {
    return getInstanceOf(context, type, null, null, 1)
  }

  /*
   * Gets any instance of given type containing a given structural feature with given value using a given context object.
   */
  def <T extends EObject> T getInstanceOf(EObject context, Class<T> type, int instance) {
    return getInstanceOf(context, type, null, null, instance)
  }

  /*
   * Gets the all instances of given type <code>type</code> having given value <code>value</code> on structural feature <code>feature</code>.
   */
  def <T extends EObject> Iterable<T> getAllInstancesOf(EObject context, Class<T> type, EStructuralFeature feature, Object value) {
    val result = Lists::<T>newArrayList
    for (candidate : EcoreUtil2::getAllContentsOfType(context, type)) {
      var valueOfFeature = candidate.eGet(feature);
      if (valueOfFeature !== null && valueOfFeature.equals(value)) {
        result.add(candidate);
      }
    }
    return result;
  }

  /*
   * Gets the first instance of given type containing a given structural feature with given value using a given context object.
   */
  def <T extends EObject> T getFirstInstanceOf(EObject context, Class<T> type, EStructuralFeature feature, Object value) {
    return getInstanceOf(context, type, feature, value, 1);
  }

  /*
   * Gets any instance of given type containing a given structural feature with given value using a given context object.
   */
  def <T extends EObject> T getInstanceOf(EObject context, Class<T> type, EStructuralFeature feature, Object value, int instance) {
    var skip = instance - 1;
    for (candiadate : EcoreUtil2::getAllContentsOfType(context, type)) {
      if ((feature === null && value === null) || candiadate.eGet(feature).equals(value)) {
        if (skip == 0) {
          return candiadate;
        } else {
          skip = skip-1
        }
      }
    }
    return null;
  }

}
