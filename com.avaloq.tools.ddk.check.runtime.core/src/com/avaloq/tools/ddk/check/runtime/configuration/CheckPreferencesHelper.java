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
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Ascii;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Helper operations to marshal lists of things into and from single strings that can be stored in preferences.
 */
public final class CheckPreferencesHelper {

  private static final String SEPARATOR = new String(new byte[] {Ascii.SUB});

  private CheckPreferencesHelper() {
    // No instantiation
  }

  /**
   * Internal operation to marshal a list of strings.
   *
   * @param typeId
   *          identifying the real element type
   * @param values
   *          stringified element values
   * @return a single string
   */
  private static String marshal(final char typeId, final Iterable<String> values) {
    StringBuilder builder = null;
    for (String value : values) {
      if (value != null) {
        if (builder == null) {
          builder = new StringBuilder().append(typeId);
        } else {
          builder.append(SEPARATOR);
        }
        // Here, we *do* have to encode ourselves. We must escape embedded Ascii.SUBs, otherwise unmarshaling won't work.
        // If we replaced then by "\\u001A"; we'd then get back a string containing exactly that, but we couldn't reliably
        // distinguish that from a value that contained the literal character sequence \u001A. So we'd also have to escape
        // the backslash. All this gets more complex than I like, and while not terribly difficult is rather prone to get
        // wrong. So let's just URI-encode the whole thing, and URI-decode it upon unmarshaling.
        builder.append(URI.encodeSegment(value, false));
      }
    }
    if (builder != null) {
      return builder.toString();
    }
    return null;
  }

  /**
   * Internal operation to unmarshal a single string into a string array.
   *
   * @param marshaled
   *          as read from preferences
   * @param typeId
   *          of expected element type.
   * @return array of individual string representations of the elements.
   */
  private static String[] unmarshalArray(final String marshaled, final char typeId) {
    if (marshaled == null) {
      return new String[0];
    }
    String[] values = marshaled.split(SEPARATOR);
    if (values.length == 0) {
      return values;
    }
    if (values[0] == null || values[0].length() < 1) {
      return new String[0];
    }
    // Remove the type indicator from the first element, and type check:
    if (typeId != values[0].charAt(0)) {
      throw new IllegalStateException();
    }
    for (int i = 0, l = values.length; i < l; i++) {
      values[i] = URI.decode(i == 0 ? values[i].substring(1) : values[i]);
    }
    return values;
  }

  /**
   * Marshals an iterable of strings into a single string.
   *
   * @param values
   *          to marshal
   * @return marshaled value
   */
  public static String marshalStrings(final Iterable<String> values) {
    return marshal('S', values);
  }

  /**
   * Marshals an iterable of booleans into a single string.
   *
   * @param values
   *          to marshal
   * @return marshaled value
   */
  public static String marshalBooleans(final Iterable<Boolean> values) {
    return marshal('B', Iterables.transform(values, new Function<Boolean, String>() {
      @Override
      public String apply(final Boolean input) {
        return input.toString();
      }
    }));
  }

  /**
   * Marshals an iterable of integers into a single string.
   *
   * @param values
   *          to marshal
   * @return marshaled value
   */
  public static String marshalIntegers(final Iterable<Integer> values) {
    return marshal('I', Iterables.transform(values, new Function<Integer, String>() {
      @Override
      public String apply(final Integer input) {
        return input.toString();
      }
    }));
  }

  /**
   * Unmarshals a single string into a list of of strings.
   *
   * @param marshaled
   *          to umarshal
   * @return list of values
   */
  public static List<String> unmarshalStrings(final String marshaled) {
    String[] values = unmarshalArray(marshaled, 'S');
    if (values.length == 0) {
      return Collections.emptyList();
    }
    return Arrays.asList(values);
  }

  /**
   * Unmarshals a single string into a list of of integers.
   *
   * @param marshaled
   *          to umarshal
   * @return list of values
   */
  public static List<Integer> unmarshalIntegers(final String marshaled) {
    String[] values = unmarshalArray(marshaled, 'I');
    if (values.length == 0) {
      return Collections.emptyList();
    }
    List<Integer> result = Lists.newArrayListWithCapacity(values.length);
    for (String value : values) {
      result.add(Integer.valueOf(value));
    }
    return result;
  }

  /**
   * Unmarshals a single string into a list of of booleans.
   *
   * @param marshaled
   *          to umarshal
   * @return list of values
   */
  public static List<Boolean> unmarshalBooleans(final String marshaled) {
    String[] values = unmarshalArray(marshaled, 'B');
    if (values.length == 0) {
      return Collections.emptyList();
    }
    List<Boolean> result = Lists.newArrayListWithCapacity(values.length);
    for (String value : values) {
      result.add(Boolean.valueOf(value));
    }
    return result;
  }

}
