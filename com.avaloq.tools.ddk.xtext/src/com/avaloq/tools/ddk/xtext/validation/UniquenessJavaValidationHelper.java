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

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.avaloq.tools.ddk.xtext.scoping.INameFunction;


/**
 * Defines utility methods for validating different flavors of uniqueness in an ECore Model.
 *
 * @param <T>
 *          type of the objects that can be duplicated in the context, subtype of EObject
 */
public class UniquenessJavaValidationHelper<T extends EObject> extends UniquenessValidationHelper<T> {

  private static final String ACCEPTOR_CAN_T_BE_NULL = "acceptor can't be null."; //$NON-NLS-1$

  private final ValidationMessageAcceptor acceptor;

  public UniquenessJavaValidationHelper(final INameFunction nameFunction, final ValidationMessageAcceptor acceptor) {
    super(nameFunction);
    this.acceptor = acceptor;
  }

  /**
   * Find duplicates in the given objects and mark them (on the given feature) as
   * warnings.
   *
   * @param possiblyDuplicateObjects
   *          an Iterable into which to look for duplicates
   * @param feature
   *          the feature of the duplicate object on which to anchor the marker
   */
  public void warnOnDuplicates(final Iterable<T> possiblyDuplicateObjects, final EStructuralFeature feature) {
    if (acceptor == null) {
      throw new IllegalArgumentException(ACCEPTOR_CAN_T_BE_NULL);
    }
    if (possiblyDuplicateObjects == null) {
      return; // NO_ERROR
    }

    Set<T> duplicateEObjects = findDuplicates(possiblyDuplicateObjects);

    for (final T duplicate : duplicateEObjects) {
      acceptor.acceptWarning(getMessage(duplicate), duplicate, feature, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, null);
    }
  }

  /**
   * Find duplicates in the given objects and mark them (on the given feature) as
   * errors.
   *
   * @param possiblyDuplicateObjects
   *          an Iterable into which to look for duplicates
   * @param feature
   *          the feature of the duplicate object on which to anchor the marker
   */
  public void errorOnDuplicates(final Iterable<T> possiblyDuplicateObjects, final EStructuralFeature feature) {
    if (acceptor == null) {
      throw new IllegalArgumentException(ACCEPTOR_CAN_T_BE_NULL);
    }
    if (possiblyDuplicateObjects == null) {
      return; // NO_ERROR
    }

    Set<T> duplicateEObjects = findDuplicates(possiblyDuplicateObjects);

    for (final T duplicate : duplicateEObjects) {
      acceptor.acceptError(getMessage(duplicate), duplicate, feature, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, null);
    }
  }

  /**
   * Hook to override the standard error/warning message provided by {@link #errorOnDuplicates(Iterable, int)} and {@link #warnOnDuplicates(Iterable, int)}.
   *
   * @param duplicate
   *          the duplicate object
   * @return the marker's message
   */
  public String getMessage(final T duplicate) {
    return "duplicate found " + getNameFunction().apply(duplicate); //$NON-NLS-1$
  }

}
