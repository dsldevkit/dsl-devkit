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
package com.avaloq.tools.ddk.xtext.expression.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.generator.type.EClassXtendType;
import com.avaloq.tools.ddk.xtext.expression.generator.type.ExpressionAnalyzer;
import com.avaloq.tools.ddk.xtext.expression.generator.type.PrimitiveXtendType;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendExecutionContext;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendExtension;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendOperation;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendType;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendVariable;


/**
 * The CompilationContext is used by CodeGeneration.ext to resolve types, get information about local variables, etc. It is a
 * wrapper around {@link XtendExecutionContext}.
 * <p>
 * Note that many of these methods are called from Xtend and will thus not show up when doing a find references in Eclipse.
 */
@SuppressWarnings("nls")
public class CompilationContext {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(CompilationContext.class);

  /** Execution context. */
  private final XtendExecutionContext context;
  private final GenModelUtilX genModelUtil;
  /** The name of the Java variable the implicit "this" variable is bound to. */
  private final String implicitVariable;
  /** The type of the implicit "this" variable. */
  private XtendType implicitContextType;

  /**
   * Analyzes the given expression and returns the type of it.
   *
   * @param expression
   *          expression to analyze
   * @return type of expression
   */
  public XtendType analyze(final Expression expression) {
    return ExpressionAnalyzer.analyze(expression, context);
  }

  /**
   * Analyzes the given expression and returns the type of it.
   *
   * @param expression
   *          expression to analyze
   * @return type of expression
   */
  public XtendType analyze(final String expression) {
    return ExpressionAnalyzer.analyzeString(expression);
  }

  /**
   * Creates a new compilation context for the given execution context.
   * The name of the Java variable to bind "this" is set to "obj".
   *
   * @param context
   *          execution context to wrap
   * @param genModelUtil
   *          the gen model utility
   */
  public CompilationContext(final XtendExecutionContext context, final GenModelUtilX genModelUtil) {
    this.context = context;
    this.genModelUtil = genModelUtil;
    this.implicitVariable = "obj"; //$NON-NLS-1$
  }

  /**
   * Creates a new compilation context for the given execution context, implicit variable name, and context type.
   *
   * @param context
   *          execution context to wrap
   * @param genModelUtil
   *          the gen model utility
   * @param implicitVar
   *          name of the Java variable to bind "this" to
   * @param contextType
   *          type of the Java variable to bind "this" to
   */
  public CompilationContext(final XtendExecutionContext context, final GenModelUtilX genModelUtil, final String implicitVar, final XtendType contextType) {
    this.context = context;
    this.genModelUtil = genModelUtil;
    this.implicitVariable = implicitVar;
    this.implicitContextType = contextType != null ? contextType : context.getObjectType();
  }

  /**
   * Returns the execution context used by this compilation context.
   *
   * @return execution context
   */
  public XtendExecutionContext getExecutionContext() {
    return context;
  }

  /**
   * Creates a new compilation context with an additional string-typed variable.
   *
   * @param implicitVar
   *          name of the Java variable to bind "this" to
   * @param contextType
   *          type of the Java variable to bind "this" to
   * @param variable
   *          string typed variable to add
   * @return CompilationContext - CompilationContext
   */
  public CompilationContext cloneWithString(final String implicitVar, final EClass contextType, final String variable) {
    return new CompilationContext(context.cloneWithVariable(new XtendVariable(variable, "")), genModelUtil, implicitVar, contextType != null ? findType(contextType) //$NON-NLS-1$
        : this.implicitContextType);
  }

  /**
   * Creates a new compilation context with an additional typed variable.
   *
   * @param implicitVar
   *          name of the Java variable to bind "this" to
   * @param contextType
   *          type of the Java variable to bind "this" to
   * @param variable
   *          variable to add
   * @param type
   *          type of variable to add
   * @return CompilationContext - CompilationContext
   */
  public CompilationContext cloneWithVariable(final String implicitVar, final EClass contextType, final String variable, final String type) {
    return new CompilationContext(context.cloneWithVariable(new XtendVariable(variable, type)), genModelUtil, implicitVar, contextType != null
        ? findType(contextType)
        : this.implicitContextType);
  }

  /**
   * Returns the name of the Java variable to bind "this" to.
   *
   * @return {@link #implicitVariable}
   */
  public String getImplicitVariable() {
    return implicitVariable;
  }

  /**
   * Returns the generic type to use for List expressions mapped to java.util.List<>.
   *
   * @return currently always returns "org.eclipse.emf.ecore.EObject"
   */
  public XtendType getRequiredType() {
    return findType("ecore::EObject"); //$NON-NLS-1$
  }

  /**
   * Checks whether the given string corresponds to a variable in this context.
   *
   * @param var
   *          string to check
   * @return true if the given string corresponds to a variable
   */
  public Boolean isVariable(final String var) {
    return context.getVariable(var) != null || context.getGlobalVariables().containsKey(var);
  }

  /**
   * Returns the type for the given EClass.
   *
   * @param eClass
   *          EClass to get corresponding type for
   * @return corresponding type
   */
  public XtendType findType(final EClass eClass) {
    return findType(eClass.getEPackage().getName() + "::" + eClass.getName()); //$NON-NLS-1$
  }

  /**
   * Returns the type with the given name.
   *
   * @param name
   *          (qualified) name of type to find
   * @return corresponding type
   */
  public XtendType findType(final String name) {
    return context.getTypeForName(name);
  }

  /**
   * Checks whether the given name corresponds to a type.
   *
   * @param name
   *          (qualified) name of type to find
   * @return true if the name corresponds to a type
   */
  public boolean isType(final String name) {
    return findType(name) != null;
  }

  /**
   * Returns the Java class name corresponding to the given type. This works both for builtin types, EMF types, and Java
   * types.
   *
   * @param name
   *          the name of the type (qualified or not)
   * @return the qualified Java class name
   */
  public String javaType(final String name) {
    XtendType type = findType(name);
    if (type == null) {
      LOGGER.warn("No type found for " + name);
      return name;
    }
    return javaType(type);
  }

  /**
   * Returns the Java class name corresponding to the given type. This works both for builtin types, EMF types, and Java
   * types.
   *
   * @param type
   *          the type
   * @return the qualified Java class name
   */
  public String javaType(final XtendType type) {
    if (type instanceof EClassXtendType) {
      EClass eClass = ((EClassXtendType) type).getEClass();
      return genModelUtil.instanceClassName(eClass);
    }
    if (type instanceof PrimitiveXtendType) {
      Class<?> clazz = ((PrimitiveXtendType) type).getJavaType();
      return "java.lang".equals(clazz.getPackage().getName()) ? clazz.getSimpleName() : clazz.getName(); //$NON-NLS-1$
    }
    Class<? extends Object> clazz = type.newInstance().getClass();
    return "java.lang".equals(clazz.getPackage().getName()) ? clazz.getSimpleName() : clazz.getName(); //$NON-NLS-1$
  }

  /**
   * Creates a new equivalent compilation context with the given implicit variable name.
   *
   * @param implicitVar
   *          name of implicit variable
   * @return new derived compilation context
   */
  public CompilationContext clone(final String implicitVar) {
    return new CompilationContext(context, genModelUtil, implicitVar, implicitContextType);
  }

  /**
   * Creates a new equivalent compilation context with the given implicit variable name and context type.
   *
   * @param implicitVar
   *          name of implicit variable
   * @param contextType
   *          type of implicit variable
   * @return new derived compilation context
   */
  public CompilationContext clone(final String implicitVar, final EClass contextType) {
    return new CompilationContext(context, genModelUtil, implicitVar, contextType != null ? findType(contextType) : this.implicitContextType);
  }

  /**
   * Creates a new equivalent compilation context with the given implicit variable name and context type and additional variable.
   *
   * @param implicitVar
   *          name of implicit variable
   * @param contextType
   *          type of implicit variable
   * @param variable
   *          name of additional bound Java variable
   * @return new derived compilation context
   */
  public CompilationContext clone(final String implicitVar, final EClass contextType, final String variable) {
    return clone(implicitVar, contextType, variable, null);
  }

  /**
   * Creates a new equivalent compilation context with the given implicit variable name and context type and additional variable.
   *
   * @param implicitVar
   *          name of implicit variable
   * @param contextType
   *          type of implicit variable
   * @param variable
   *          name of additional bound Java variable
   * @param variableType
   *          type of additional bound Java variable
   * @return new derived compilation context
   */
  public CompilationContext clone(final String implicitVar, final EClass contextType, final String variable, final EClass variableType) {
    return new CompilationContext(context.cloneWithVariable(new XtendVariable(variable, variableType == null ? new Object()
        : findType(variableType))), genModelUtil, implicitVar, contextType != null ? findType(contextType) : this.implicitContextType);
  }

  /**
   * Returns the qualified type name for the given unqualified type name.
   *
   * @param typeName
   *          unqualified (or qualified) type name
   * @return qualified type name of given type
   */
  public String getQualifiedTypeName(final String typeName) {
    final XtendType type = findType(typeName);

    try {
      if (type instanceof EClassXtendType) {
        EClass eClass = ((EClassXtendType) type).getEClass();
        return eClass.getEPackage().getName() + "::" + eClass.getName(); //$NON-NLS-1$
      }
      // CHECKSTYLE:OFF
    } catch (final Exception e) {
      LOGGER.warn(NLS.bind("Could not determine qualified type name for {0}", typeName), e); //$NON-NLS-1$
    }
    // CHECKSTYLE:ON

    return type != null ? type.getName() : typeName;
  }

  /**
   * Gets the eClass from a type.
   *
   * @param type
   *          the type
   * @return the eClass or NULL
   */
  public EClass getEClass(final XtendType type) {
    if (type instanceof EClassXtendType) {
      return ((EClassXtendType) type).getEClass();
    }
    return null;
  }

  /**
   * Gets the eClass from an object.
   *
   * @param type
   *          the type
   * @return the eClass or NULL
   */
  public EClass getEClass(final Object type) {
    if (type instanceof XtendType) {
      return getEClass((XtendType) type);
    }
    return null;
  }

  /**
   * Heuristically test if name of extension.
   *
   * @param name
   *          the name to test
   * @return true if name is an extension name
   */
  // TODO fix heuristic with proper type analysis
  public Boolean isExtension(final String name) {
    for (final XtendExtension e : context.getAllExtensions()) {
      if (e.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the called java method.
   *
   * @param expression
   *          the expression
   * @return the called java method or NULL
   */
  public String getCalledJavaMethod(final OperationCall expression) {
    try {
      for (final XtendExtension e : context.getAllExtensions()) {
        if (e.isJavaExtension() && e.getName().equals(expression.getName())) {
          return e.getJavaType() + "." + e.getJavaMethodName(); //$NON-NLS-1$
        }
      }
      // CHECKSTYLE:OFF
    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    // CHECKSTYLE:ON

    return null;
  }

  /**
   * Heuristically test if target has an operation.
   *
   * @param expression
   *          the operation
   * @return true if the implicit context has this operation
   */
  // TODO fix heuristic with proper type analysis
  public boolean targetHasOperation(final OperationCall expression) {
    if (implicitContextType == null) {
      return false;
    }

    for (final XtendOperation operation : implicitContextType.getAllOperations()) {
      if (operation.getName().equals(expression.getName()) && operation.getParameterTypes().size() == expression.getParams().size()) {
        return true;
      }
    }
    return false;
  }
}
