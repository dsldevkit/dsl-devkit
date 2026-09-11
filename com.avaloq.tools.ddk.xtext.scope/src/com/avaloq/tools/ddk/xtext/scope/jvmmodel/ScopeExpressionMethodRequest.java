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
package com.avaloq.tools.ddk.xtext.scope.jvmmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

/**
 * Describes a private helper operation that the {@link ScopeJvmModelInferrer} has to contribute to the inferred
 * scope provider for an embedded scope/export expression.
 * <p>
 * The legacy generators emit Java for embedded expressions by splicing fragment strings (produced by the
 * {@code expression.generator.CodeGenerationX} compiler) into larger source templates. The Xbase based code
 * generation instead attaches a fully linked {@link org.eclipse.xtext.xbase.XExpression} as a whole method body,
 * which cannot be spliced into a string. Each embedded expression is therefore turned into a generated helper
 * method: the string template emits a call to {@link #getMethodName()} and this request carries everything the
 * inferrer needs to build that method. The {@link #getFallbackBody() fallback body} (the legacy string emission)
 * is used when the translator cannot (yet) translate the expression, so behaviour is preserved while coverage is
 * migrated incrementally.
 */
public class ScopeExpressionMethodRequest {

  private String methodName;

  private String returnTypeName;

  private String variableName;

  private String variableTypeName;

  private Expression expression;

  private String fallbackBody;

  private final List<Pair<String, String>> extraParameters = new ArrayList<>();

  /**
   * Returns the name of the helper method to generate.
   *
   * @return the method name, never {@code null}
   */
  public String getMethodName() {
    return this.methodName;
  }

  /**
   * Sets the name of the helper method to generate.
   *
   * @param methodName
   *          the method name, must not be {@code null}
   */
  public void setMethodName(final String methodName) {
    this.methodName = methodName;
  }

  /**
   * Returns the fully qualified name of the helper method's return type (or a Java primitive name such as
   * {@code boolean}).
   *
   * @return the return type name, never {@code null}
   */
  public String getReturnTypeName() {
    return this.returnTypeName;
  }

  /**
   * Sets the fully qualified name of the helper method's return type.
   *
   * @param returnTypeName
   *          the return type name, must not be {@code null}
   */
  public void setReturnTypeName(final String returnTypeName) {
    this.returnTypeName = returnTypeName;
  }

  /**
   * Returns the source name of the single context variable the expression is evaluated against.
   *
   * @return the variable name, never {@code null}
   */
  public String getVariableName() {
    return this.variableName;
  }

  /**
   * Sets the source name of the single context variable the expression is evaluated against.
   *
   * @param variableName
   *          the variable name, must not be {@code null}
   */
  public void setVariableName(final String variableName) {
    this.variableName = variableName;
  }

  /**
   * Returns the fully qualified name of the context variable's type.
   *
   * @return the variable type name, never {@code null}
   */
  public String getVariableTypeName() {
    return this.variableTypeName;
  }

  /**
   * Sets the fully qualified name of the context variable's type.
   *
   * @param variableTypeName
   *          the variable type name, must not be {@code null}
   */
  public void setVariableTypeName(final String variableTypeName) {
    this.variableTypeName = variableTypeName;
  }

  /**
   * Returns the source expression to translate into the helper method body.
   *
   * @return the source expression, never {@code null}
   */
  public Expression getExpression() {
    return this.expression;
  }

  /**
   * Sets the source expression to translate into the helper method body.
   *
   * @param expression
   *          the source expression, must not be {@code null}
   */
  public void setExpression(final Expression expression) {
    this.expression = expression;
  }

  /**
   * Returns the legacy method body (a complete Java statement sequence, including the {@code return}) used when the
   * translator cannot translate the expression.
   *
   * @return the fallback body, never {@code null}
   */
  public String getFallbackBody() {
    return this.fallbackBody;
  }

  /**
   * Sets the legacy method body used when the translator cannot translate the expression.
   *
   * @param fallbackBody
   *          the fallback body, must not be {@code null}
   */
  public void setFallbackBody(final String fallbackBody) {
    this.fallbackBody = fallbackBody;
  }

  /**
   * Returns the additional helper method parameters (beyond the primary context variable), each as a
   * source-name to fully-qualified-type-name pair. These cover expressions evaluated against more than one variable,
   * such as a data match lambda's element description.
   *
   * @return the extra parameters, never {@code null}
   */
  public List<Pair<String, String>> getExtraParameters() {
    return this.extraParameters;
  }

}
