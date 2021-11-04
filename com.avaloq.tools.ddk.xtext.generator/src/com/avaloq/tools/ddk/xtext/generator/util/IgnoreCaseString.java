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

package com.avaloq.tools.ddk.xtext.generator.util;

import com.google.common.base.Preconditions;


/**
 * Copied from {@code org.eclipse.xtext.generator.parser.antlr.ex.common.IgnoreCaseString}.
 * Could not use the original because of the protected constructor.
 */
public class IgnoreCaseString implements CharSequence {

  private final String value;

  /**
   * Creates a new instance of {@link IgnoreCaseString}.
   *
   * @param value
   *          String value
   */
  public IgnoreCaseString(final String value) {
    Preconditions.checkNotNull(value);
    this.value = value;
  }

  @Override
  public char charAt(final int index) {
    return value.charAt(index);
  }

  @Override
  public int length() {
    return value.length();
  }

  @Override
  public CharSequence subSequence(final int start, final int end) {
    return value.subSequence(start, end);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.length();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    IgnoreCaseString other = (IgnoreCaseString) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equalsIgnoreCase(other.value)) {
      return false;
    }
    return true;
  }
}

/* Copyright (c) Avaloq Evolution AG */