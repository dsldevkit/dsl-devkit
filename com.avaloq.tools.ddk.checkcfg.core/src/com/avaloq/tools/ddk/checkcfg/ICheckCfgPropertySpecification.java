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

package com.avaloq.tools.ddk.checkcfg;

/** Provides metadata for check configuration properties specified via the extension point "property". */
public interface ICheckCfgPropertySpecification {

  String[] NOTHING = new String[] {};

  /** The property type. */
  enum PropertyType {
    NUMBER,
    BOOLEAN,
    STRING,
    NUMBERS,
    BOOLEANS,
    STRINGS
  }

  /**
   * Specifies the name/identifier of the property.
   *
   * @return the name/identifier of the property, never {@code null}
   */
  String getName();

  /**
   * Specifies the type of the property from one of the supported {@link PropertyType}s.
   *
   * @return the type, never {@code null}
   */
  PropertyType getType();

  /**
   * Provides a description of the parameter's use, permitted values, etc. in a form appropriate for a tool tip.
   *
   * @return the description, never {@code null}
   */
  String getInfo();

  /**
   * Specifies the expected meaningful values for the property in {@link String} form. CheckCfg will offer these as suggestions for Content Assist and enforce
   * usage in validations. If {@link #NOTHING NOTHING} is specified, no suggestions will be provided in Content Assist, and any value will be permitted.
   *
   * @return the expected values as a {@link String} array, never {@code null}
   */
  String[] getExpectedValues();

}
