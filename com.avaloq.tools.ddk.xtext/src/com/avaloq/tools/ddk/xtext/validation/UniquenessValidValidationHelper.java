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
package com.avaloq.tools.ddk.xtext.validation;

import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import com.avaloq.tools.ddk.xtext.scoping.INameFunction;


/**
 * Defines utility methods for validating different flavors of uniqueness in an ECore Model
 * in the context of the Valid validation framework.
 *
 * @param <T>
 *          type of the objects that can be duplicated in the context, subtype of EObject
 */
public class UniquenessValidValidationHelper<T extends EObject> extends UniquenessValidationHelper<T> {

  static final String MESSAGE = "Duplicate {0} ''{1}''"; //$NON-NLS-1$

  public UniquenessValidValidationHelper(final INameFunction nameFunction) {
    super(nameFunction);
  }

  /**
   * Check whether the possiblyDuplicateObjects (in the given context) contain duplicates.
   *
   * @param context
   *          the context containing the possible duplicates.
   * @param possiblyDuplicateObjects
   *          a set of elements of type T, into which one must look for duplicate objects
   * @param message
   *          the duplicate error message, in which {0} is the type of T and {1} is the duplicate representation
   * @return a compound message (a set of simple messages)
   */
  public CompoundValidMessage<T> checkDuplicates(final EObject context, final Iterable<T> possiblyDuplicateObjects, final String message) {
    if (possiblyDuplicateObjects == null || context == null) {
      return null; // NO_ERROR
    }

    Set<T> duplicateEObjects = findDuplicates(possiblyDuplicateObjects);

    final CompoundValidMessage<T> messages = new CompoundValidMessage<T>();
    final Resource contextResource = context.eResource();
    for (final T duplicate : duplicateEObjects) {
      // emit a message for all duplicates in the same resource as the context (incl. the first element, not just the followers)
      if (Objects.equals(contextResource, duplicate.eResource())) {
        messages.add(NLS.bind(message, getNameFunction().apply(duplicate)), duplicate);
      }
    }

    return messages;
  }

}
