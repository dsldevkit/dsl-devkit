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

import java.util.List;
import java.util.Map;


/**
 * Local replacement for {@code org.eclipse.xtend.expression.ExecutionContext}.
 * Provides type resolution, variable tracking, and extension management.
 */
public interface XtendExecutionContext {

  /**
   * Returns the variable with the given name, or {@code null} if not found.
   *
   * @param name
   *          the variable name
   * @return the variable, or {@code null}
   */
  XtendVariable getVariable(String name);

  /**
   * Returns all global variables.
   *
   * @return map of global variables
   */
  Map<String, XtendVariable> getGlobalVariables();

  /**
   * Returns all registered extensions.
   *
   * @return list of extensions
   */
  List<XtendExtension> getAllExtensions();

  /**
   * Returns the Object type.
   *
   * @return the Object type
   */
  XtendType getObjectType();

  /**
   * Returns the type for the given name.
   *
   * @param name
   *          the type name (e.g. "ecore::EObject", "String", "Integer")
   * @return the type, or {@code null}
   */
  XtendType getTypeForName(String name);

  /**
   * Creates a clone of this context with the given additional variable.
   *
   * @param variable
   *          the variable to add
   * @return a new execution context with the variable added
   */
  XtendExecutionContext cloneWithVariable(XtendVariable variable);
}
