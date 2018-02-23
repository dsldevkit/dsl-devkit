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
package com.avaloq.tools.ddk.xtext.util;

import com.google.common.collect.ObjectArrays;


/**
 * Collects various utility operations on arrays.
 *
 * @param <T>
 *          generic element type
 */
public final class ArrayUtils<T> {

  private final Class<T> componentType;

  private ArrayUtils(final Class<T> componentType) {
    this.componentType = componentType;
  }

  /**
   * Creates a new instance of ArrayUtils for the given component type.
   *
   * @param <T>
   *          generic element type
   * @param componentType
   *          component type, must not be {@code null}
   * @return new ArrayUtils instance, never {@code null}
   */
  @SuppressWarnings("PMD.ShortMethodName")
  public static <T> ArrayUtils<T> of(final Class<T> componentType) {
    return new ArrayUtils<T>(componentType);
  }

  /**
   * Creates a new array of given length.
   *
   * @param length
   *          length of array to create
   * @return new array, never {@code null}
   */
  public T[] newArray(final int length) {
    return ObjectArrays.newArray(componentType, length);
  }

  /**
   * Adds a value to a given array if it isn't contained yet in the array.
   * The method is intended to be used on short arrays; the array is assumed
   * to be unordered.
   *
   * @param array
   *          to add the new value to; may be {@code null}
   * @param value
   *          to add; must not be {@code null}
   * @return An array containing all the original elements and also containing the new value.
   *         If the array already contained the new value, the return value is == identical
   *         to the array passed in.
   */
  public T[] add(final T[] array, final T value) {
    if (array == null || array.length == 0) {
      T[] result = ObjectArrays.newArray(componentType, 1);
      result[0] = value;
      return result;
    }
    int i = find(array, value);
    if (i < 0) {
      // Not found: add value
      T[] newArray = ObjectArrays.newArray(componentType, array.length + 1);
      System.arraycopy(array, 0, newArray, 0, array.length);
      newArray[array.length] = value;
      return newArray;
    }
    return array;
  }

  /**
   * Adds a value to a given array if it isn't contained yet in the array.
   * The method is intended to be used on short arrays; the array is assumed
   * to be unordered.
   *
   * @param array
   *          to add the new value to; may be {@code null}
   * @param values
   *          to add; must not be {@code null}
   * @return An array containing all the original elements and also containing the new value.
   *         If the array already contained the new value, the return value is == identical
   *         to the array passed in.
   */
  @SuppressWarnings(value="PMD.UseVarargs")
  public T[] addAll(final T[] array, final T[] values) {
    if (array == null || array.length == 0) {
      return values;
    }
    T[] result = array;
    for (T value : values) {
      int i = find(array, value);
      if (i < 0) {
        // Not found: add value
        T[] tmp = ObjectArrays.newArray(componentType, result.length + 1);
        System.arraycopy(result, 0, tmp, 0, result.length);
        tmp[result.length] = value;
        result = tmp;
      }
    }
    return result;
  }

  /**
   * Removes the first occurrence of a given value from an array, if it is present in the array.
   * The array is assumed to be unordered.
   *
   * @param array
   *          to remove the value from; may be {@code null}
   * @param value
   *          to remove; must not be {@code null}
   * @return an array not containing the first occurrence of value, but containing all other elements of
   *         the original array. If the original array does not contain the given value, the returned
   *         array is == identical to the array passed in.
   */
  public T[] remove(final T[] array, final T value) {
    if (array == null) {
      return null;
    }
    int i = find(array, value);
    if (i == 0 && array.length == 1) {
      return null;
    }
    if (i >= 0) {
      // Found it: remove value. i is guaranteed to be < array.length here.
      T[] newArray = ObjectArrays.newArray(componentType, array.length - 1);
      if (i > 0) {
        System.arraycopy(array, 0, newArray, 0, i);
      }
      if (i + 1 < array.length) {
        System.arraycopy(array, i + 1, newArray, i, array.length - i - 1);
      }
      return newArray;
    }
    return array;
  }

  /**
   * Determines the index of the first occurrence of a given value in an array.
   *
   * @param array
   *          to search; may be {@code null}
   * @param value
   *          to find; must not be {@code null}
   * @return the smallest index i; i >= 0 && i < array.length, such that value.equals(array[i]) == true, or -1 if there is no such value in the array.
   */
  public int find(final T[] array, final T value) {
    if (array == null) {
      return -1;
    }
    int i = 0;
    while (i < array.length && !value.equals(array[i])) {
      i++;
    }
    return (i == array.length) ? -1 : i;
  }
}
