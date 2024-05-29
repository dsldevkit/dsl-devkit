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

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.internal.xtend.xtend.ast.Extension;
import org.eclipse.internal.xtend.xtend.ast.JavaExtensionStatement;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtend.expression.ExecutionContext;
import org.eclipse.xtend.expression.ExpressionFacade;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.typesystem.Operation;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtend.typesystem.emf.EClassType;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.google.common.collect.Sets;


/**
 * The CompilationContext is used by CodeGeneration.ext to resolve types, get information about local variables, etc. It is a
 * wrapper around {@link ExecutionContext}.
 * <p>
 * Note that many of these methods are called from Xtend and will thus not show up when doing a find references in Eclipse.
 */
@SuppressWarnings("nls")
public class CompilationContext {

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(CompilationContext.class);

  /** Xtend execution context. */
  private final ExecutionContext context;
  private final GenModelUtilX genModelUtil;
  /** The name of the Java variable the implicit "this" variable is bound to. */
  private final String implicitVariable;
  /** The type of the implicit "this" variable. */
  private Type implicitContextType;

  /**
   * Analyzes the given expression and returns the type of it.
   *
   * @param expression
   *          expression to analyze
   * @return type of expression
   */
  public Type analyze(final Expression expression) {
    return analyze(ExpressionExtensions.serialize(expression));
  }

  /**
   * Analyzes the given expression and returns the type of it.
   *
   * @param expression
   *          expression to analyze
   * @return type of expression
   */
  public Type analyze(final String expression) {
    return new ExpressionFacade(context).analyze(expression, Sets.newHashSet());
  }

  /**
   * Creates a new compilation context for the given Xtend context.
   * The name of the Java variable to bind "this" is set to "obj".
   *
   * @param context
   *          xtend context to wrap
   * @param genModelUtil
   *          the gen model utility
   */
  public CompilationContext(final ExecutionContext context, final GenModelUtilX genModelUtil) {
    this.context = context;
    this.genModelUtil = genModelUtil;
    this.implicitVariable = "obj"; //$NON-NLS-1$
  }

  /**
   * Creates a new compilation context for the given Xtend context, implicit variable name, and context type.
   *
   * @param context
   *          xtend context to wrap
   * @param genModelUtil
   *          the gen model utility
   * @param implicitVar
   *          name of the Java variable to bind "this" to
   * @param contextType
   *          type of the Java variable to bind "this" to
   */
  public CompilationContext(final ExecutionContext context, final GenModelUtilX genModelUtil, final String implicitVar, final Type contextType) {
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
  public ExecutionContext getExecutionContext() {
    return context;
  }

  /**
   * Creates a new compilation context for the given Xtend context, implicit variable name, and context type.
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
    return new CompilationContext(context.cloneWithVariable(new Variable(variable, "")), genModelUtil, implicitVar, contextType != null ? findType(contextType) //$NON-NLS-1$
        : this.implicitContextType);
  }

  /**
   * Creates a new compilation context for the given Xtend context, implicit variable name, and context type.
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
    return new CompilationContext(context.cloneWithVariable(new Variable(variable, type)), genModelUtil, implicitVar, contextType != null
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
  public Type getRequiredType() {
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
   * Returns the Xtend type for the given EClass.
   *
   * @param eClass
   *          EClass to get corresponding Xtend type for
   * @return corresponding Xtend type
   */
  public Type findType(final EClass eClass) {
    return findType(eClass.getEPackage().getName() + "::" + eClass.getName()); //$NON-NLS-1$
  }

  /**
   * Returns the Xtend type with the given name.
   *
   * @param name
   *          (qualified) name of type to find
   * @return corresponding Xtend type
   */
  public Type findType(final String name) {
    return context.getTypeForName(name);
  }

  /**
   * Returns the Xtend type with the given name.
   *
   * @param name
   *          (qualified) name of type to find
   * @return corresponding Xtend type
   */
  public boolean isType(final String name) {
    return findType(name) != null;
  }

  /**
   * Returns the Java class name corresponding to the given Xtend type. This works both for builtin types, EMF types, and Java
   * types.
   *
   * @param name
   *          the name of the Xtend type (qualified or not)
   * @return the qualified Java class name
   */
  public String javaType(final String name) {
    Type type = findType(name);
    if (type == null) {
      LOGGER.warn("No type found for " + name);
      return name;
    }
    return javaType(type);
  }

  /**
   * Returns the Java class name corresponding to the given Xtend type. This works both for builtin types, EMF types, and Java
   * types.
   *
   * @param type
   *          the Xtend type (qualified or not)
   * @return the qualified Java class name
   */
  public String javaType(final Type type) {
    if (type instanceof EClassType) {
      EClass eClass = getEClass(type);
      return genModelUtil.instanceClassName(eClass);
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
    return new CompilationContext(context.cloneWithVariable(new Variable(variable, variableType == null ? new Object()
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
    final Type type = findType(typeName);

    try {
      if (type instanceof EClassType) {
        EClass eClass = getEClass(type);
        return eClass.getEPackage().getName() + "::" + eClass.getName(); //$NON-NLS-1$
      }
      // CHECKSTYLE:OFF
    } catch (final Exception e) {
      LOGGER.warn(NLS.bind("Could not determine qualified type name for {0}", typeName), e); //$NON-NLS-1$
    }
    // CHECKSTYLE:ON

    return type.getName();
  }

  /**
   * Gets the eClass.
   *
   * @param type
   *          the type
   * @return the eClass or NULL
   */
  public EClass getEClass(final Type type) {
    if (type instanceof EClassType) {
      try {
        Field field = EClassType.class.getDeclaredField("eClass");
        field.setAccessible(true);
        return (EClass) field.get(type);
        // CHECKSTYLE:OFF
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOGGER.error("Could not determine EClass for " + type, e);
      }
    }
    return null;
  }

  /**
   * Gets the eClass.
   *
   * @param type
   *          the type
   * @return the eClass or NULL
   */
  public EClass getEClass(final Object type) {
    if (type instanceof Type) {
      return getEClass((Type) type);
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
    for (final Extension e : context.getAllExtensions()) {
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
    // TODO ctx.getExtensionForTypes(expression.getName(), ...);
    try {
      for (final Extension e : context.getAllExtensions()) {
        if (e instanceof JavaExtensionStatement) {
          final JavaExtensionStatement je = (JavaExtensionStatement) e;
          if (je.getName().equals(expression.getName())) {
            return je.getJavaType() + "." + je.getJavaMethodName(); //$NON-NLS-1$
          }
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

    for (final Operation operation : implicitContextType.getAllOperations()) {
      if (operation.getName().equals(expression.getName()) && operation.getParameterTypes().size() == expression.getParams().size()) {
        return true;
      }
    }
    return false;
  }
}
