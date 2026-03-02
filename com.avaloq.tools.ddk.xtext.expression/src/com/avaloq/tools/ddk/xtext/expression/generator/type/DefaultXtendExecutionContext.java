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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Local replacement for {@code org.eclipse.xtend.expression.ExecutionContextImpl}.
 * Default implementation of {@link XtendExecutionContext} with metamodel registration,
 * variable tracking, and extension management.
 */
@SuppressWarnings("nls")
public class DefaultXtendExecutionContext implements XtendExecutionContext {

  private final Map<String, XtendVariable> variables;
  private final List<XtendExtension> extensions;
  private final List<EmfRegistryMetaModel> metaModels;

  /**
   * Creates a new default execution context.
   */
  public DefaultXtendExecutionContext() {
    this(new LinkedHashMap<>(), new ArrayList<>(), new ArrayList<>());
  }

  /**
   * Creates a new default execution context with the given variables.
   *
   * @param variables
   *          the variables
   */
  public DefaultXtendExecutionContext(final Map<String, XtendVariable> variables) {
    this(variables, new ArrayList<>(), new ArrayList<>());
  }

  /**
   * Creates a new execution context with the given state.
   *
   * @param variables
   *          the variables
   * @param extensions
   *          the extensions
   * @param metaModels
   *          the meta models
   */
  protected DefaultXtendExecutionContext(final Map<String, XtendVariable> variables,
      final List<XtendExtension> extensions, final List<EmfRegistryMetaModel> metaModels) {
    this.variables = new LinkedHashMap<>(variables);
    this.extensions = new ArrayList<>(extensions);
    this.metaModels = new ArrayList<>(metaModels);
  }

  /**
   * Registers a metamodel for type resolution.
   *
   * @param metaModel
   *          the metamodel to register
   */
  public void registerMetaModel(final EmfRegistryMetaModel metaModel) {
    metaModels.add(metaModel);
  }

  /**
   * Adds an extension.
   *
   * @param extension
   *          the extension to add
   */
  public void addExtension(final XtendExtension extension) {
    extensions.add(extension);
  }

  @Override
  public XtendVariable getVariable(final String name) {
    return variables.get(name);
  }

  @Override
  public Map<String, XtendVariable> getGlobalVariables() {
    return Collections.unmodifiableMap(variables);
  }

  @Override
  public List<XtendExtension> getAllExtensions() {
    return Collections.unmodifiableList(extensions);
  }

  @Override
  public XtendType getObjectType() {
    return PrimitiveXtendType.OBJECT;
  }

  @Override
  public XtendType getTypeForName(final String name) {
    if (name == null) {
      return null;
    }
    // Check built-in types first
    XtendType builtIn = getBuiltInType(name);
    if (builtIn != null) {
      return builtIn;
    }
    // Then check registered meta models
    for (EmfRegistryMetaModel metaModel : metaModels) {
      XtendType type = metaModel.getTypeForName(name);
      if (type != null) {
        return type;
      }
    }
    return null;
  }

  /**
   * Returns the built-in type for the given name, or {@code null}.
   *
   * @param name
   *          the type name
   * @return the built-in type, or {@code null}
   */
  private static XtendType getBuiltInType(final String name) {
    switch (name) {
    case "Object":
      return PrimitiveXtendType.OBJECT;
    case "String":
      return PrimitiveXtendType.STRING;
    case "Boolean":
      return PrimitiveXtendType.BOOLEAN;
    case "Integer":
      return PrimitiveXtendType.INTEGER;
    case "Real":
      return PrimitiveXtendType.REAL;
    case "Collection":
      return PrimitiveXtendType.COLLECTION;
    case "List":
      return PrimitiveXtendType.LIST;
    case "Void":
      return PrimitiveXtendType.VOID;
    default:
      return null;
    }
  }

  @Override
  public XtendExecutionContext cloneWithVariable(final XtendVariable variable) {
    Map<String, XtendVariable> newVars = new LinkedHashMap<>(variables);
    newVars.put(variable.getName(), variable);
    return new DefaultXtendExecutionContext(newVars, extensions, metaModels);
  }
}
