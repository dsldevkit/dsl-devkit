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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import com.google.common.base.Predicate;


/**
 * Operations on ELists, supposed to offer operations similar to what is available in package {@code com.google.common.collect}.
 */
public final class ELists {

  /**
   * Filters an {@link EList} of type T to contain only elements matching the provided predicate.
   *
   * @param <T>
   *          list element type
   * @param unfiltered
   *          unfiltered list
   * @param predicate
   *          to apply
   * @return filtered list
   */
  public static <T> EList<T> filter(final EList<T> unfiltered, final Predicate<? super T> predicate) {
    if (unfiltered == null) {
      return ECollections.emptyEList();
    }
    if (predicate == null) {
      throw new IllegalArgumentException("predicate must not be null"); //$NON-NLS-1$
    }
    EList<T> filtered = new BasicEList<T>(unfiltered.size() / 2); // Initial guess: half the original size
    for (T t : unfiltered) {
      if (predicate.apply(t)) {
        filtered.add(t);
      }
    }
    return filtered;
  }

  /**
   * Converts an {@link EList} of type S to an {@link EList} T, where T is a subtype of S.
   *
   * @param <S>
   *          original list type, supertype of T
   * @param <T>
   *          target list type, subtype of S
   * @param from
   *          list to convert
   * @return converted list
   */
  @SuppressWarnings("unchecked")
  public static <S, T extends S> EList<T> convertToSubtype(final EList<S> from) {
    if (from == null) {
      return ECollections.emptyEList();
    }
    return (EList<T>) from;
  }

  /**
   * Converts an {@link EList} of type T to an {@link EList} S, where S is a supertype of T.
   *
   * @param <S>
   *          original list type, supertype of T
   * @param <T>
   *          target list type, subtype of S
   * @param from
   *          list to convert
   * @return converted list
   */
  @SuppressWarnings("unchecked")
  public static <S, T extends S> EList<S> convertToSupertype(final EList<T> from) {
    if (from == null) {
      return ECollections.emptyEList();
    }
    return (EList<S>) from;
  }

  /** Prevent instantiation. */
  private ELists() {
    // Do nothing.
  }

}
