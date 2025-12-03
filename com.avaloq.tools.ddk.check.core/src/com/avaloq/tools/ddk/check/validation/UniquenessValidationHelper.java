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
package com.avaloq.tools.ddk.check.validation;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


//TODO move to the runtime plug-in
/**
 * Defines utility methods for validating different flavors of uniqueness in an ECore Model,
 * to be used directly with Xtext (in a Java Validator).
 * 
 * @param <T>
 *          type of the objects that can be duplicated in the context, subtype of EObject
 */
public class UniquenessValidationHelper<T extends EObject> {

  private final Function<T, QualifiedName> nameFunction;

  /**
   * Creates a Uniqueness validation helper.
   *
   * @param nameFunction
   *          a function that returns a representation of a T object that can be compared by means of equals
   * @throws IllegalArgumentException
   *           if nameFunction is null
   */
  public UniquenessValidationHelper(final Function<T, QualifiedName> nameFunction) {
    if (nameFunction == null) {
      throw new IllegalArgumentException("nameFunction can't be null"); //$NON-NLS-1$
    }
    this.nameFunction = nameFunction;
  }

  /**
   * Find and returns duplicate objects from a candidate list (iterable).
   * 
   * @param possiblyDuplicateObjects
   *          an Iterable into which to look for duplicates
   * @return a set of duplicate objects
   */
  public Set<T> findDuplicates(final Iterable<T> possiblyDuplicateObjects) {
    if (possiblyDuplicateObjects == null) {
      return Collections.<T> emptySet();
    }

    final Map<QualifiedName, T> nameToEObjectMap = Maps.newHashMap();
    final Set<T> duplicateEObjects = Sets.newHashSet();

    for (final T object : possiblyDuplicateObjects) {
      if (object.eIsProxy()) {
        continue;
      }
      final QualifiedName name = nameFunction.apply(object);
      if (name == null) {
        continue;
      }
      T oldObject = nameToEObjectMap.put(name, object);
      if (oldObject != null) {
        // Register both EObjects with name
        duplicateEObjects.add(object);
        duplicateEObjects.add(oldObject);
      }
    }
    return duplicateEObjects;
  }

  /**
   * Returns the nameFunction.
   * 
   * @return the name function
   */
  public Function<T, QualifiedName> getNameFunction() {
    return nameFunction;
  }

}
