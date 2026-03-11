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
package com.avaloq.tools.ddk.xtext.expression.generator.type;

import java.util.Collections;
import java.util.List;


/**
 * A type for primitive/built-in types (String, Integer, Real, Boolean, Object, Collection, etc.).
 */
@SuppressWarnings("nls")
public class PrimitiveXtendType implements XtendType {

  /** The Object type. */
  public static final PrimitiveXtendType OBJECT = new PrimitiveXtendType("Object", Object.class);
  /** The String type. */
  public static final PrimitiveXtendType STRING = new PrimitiveXtendType("String", String.class);
  /** The Boolean type. */
  public static final PrimitiveXtendType BOOLEAN = new PrimitiveXtendType("Boolean", Boolean.class);
  /** The Integer type. */
  public static final PrimitiveXtendType INTEGER = new PrimitiveXtendType("Integer", Integer.class);
  /** The Real type. */
  public static final PrimitiveXtendType REAL = new PrimitiveXtendType("Real", Double.class);
  /** The Collection type. */
  public static final PrimitiveXtendType COLLECTION = new PrimitiveXtendType("Collection", java.util.Collection.class);
  /** The List type. */
  public static final PrimitiveXtendType LIST = new PrimitiveXtendType("List", java.util.List.class);
  /** The Void type. */
  public static final PrimitiveXtendType VOID = new PrimitiveXtendType("Void", Void.class);

  private final String name;
  private final Class<?> javaType;

  /**
   * Creates a new primitive type with the given name and Java class.
   *
   * @param name
   *          the type name
   * @param javaType
   *          the corresponding Java class
   */
  public PrimitiveXtendType(final String name, final Class<?> javaType) {
    this.name = name;
    this.javaType = javaType;
  }

  /**
   * Returns the Java class for this primitive type.
   *
   * @return the Java class
   */
  public Class<?> getJavaType() {
    return javaType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Object newInstance() {
    if (javaType == String.class) {
      return "";
    } else if (javaType == Boolean.class) {
      return Boolean.FALSE;
    } else if (javaType == Integer.class) {
      return 0;
    } else if (javaType == Double.class) {
      return 0.0;
    }
    try {
      return javaType.getDeclaredConstructor().newInstance();
      // CHECKSTYLE:OFF
    } catch (final Exception e) { // NOPMD
      return new Object();
    }
    // CHECKSTYLE:ON
  }

  @Override
  public List<XtendOperation> getAllOperations() {
    return Collections.emptyList();
  }

  @Override
  public boolean isAssignableFrom(final XtendType other) {
    if (this == OBJECT) {
      return true;
    }
    if (other instanceof PrimitiveXtendType) {
      PrimitiveXtendType otherPrimitive = (PrimitiveXtendType) other;
      if (javaType.isAssignableFrom(otherPrimitive.javaType)) {
        return true;
      }
      // Real is assignable from Integer (numeric promotion)
      if (this == REAL && other == INTEGER) {
        return true;
      }
    }
    return this.equals(other);
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj || obj instanceof PrimitiveXtendType && name.equals(((PrimitiveXtendType) obj).name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
