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


/**
 * Local replacement for {@code org.eclipse.internal.xtend.xtend.ast.Extension}.
 * Represents an extension (helper function) in the execution context.
 */
public class XtendExtension {

  private final String name;
  private final String javaType;
  private final String javaMethodName;

  /**
   * Creates a new extension.
   *
   * @param name
   *          the extension name
   */
  public XtendExtension(final String name) {
    this(name, null, null);
  }

  /**
   * Creates a new Java-backed extension.
   *
   * @param name
   *          the extension name
   * @param javaType
   *          the fully qualified Java type, or {@code null}
   * @param javaMethodName
   *          the Java method name, or {@code null}
   */
  public XtendExtension(final String name, final String javaType, final String javaMethodName) {
    this.name = name;
    this.javaType = javaType;
    this.javaMethodName = javaMethodName;
  }

  /**
   * Returns the name of this extension.
   *
   * @return the extension name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns whether this is a Java-backed extension.
   *
   * @return {@code true} if this extension is backed by a Java method
   */
  public boolean isJavaExtension() {
    return javaType != null;
  }

  /**
   * Returns the Java type of this extension.
   *
   * @return the Java type, or {@code null}
   */
  public String getJavaType() {
    return javaType;
  }

  /**
   * Returns the Java method name of this extension.
   *
   * @return the Java method name, or {@code null}
   */
  public String getJavaMethodName() {
    return javaMethodName;
  }
}
