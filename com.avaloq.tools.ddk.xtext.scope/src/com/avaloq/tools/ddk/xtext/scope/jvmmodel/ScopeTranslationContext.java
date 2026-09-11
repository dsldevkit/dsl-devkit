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
package com.avaloq.tools.ddk.xtext.scope.jvmmodel

import com.avaloq.tools.ddk.xtext.expression.expression.Identifier
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeModelTypeResolver
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.xbase.lib.Functions.Function1

/**
 * Carries the information needed while translating a scope/export expression AST into an Xbase
 * {@link org.eclipse.xtext.xbase.XExpression} tree.
 * <p>
 * It holds the variables that are in scope (mapping their source names to the {@link JvmFormalParameter}s of the
 * enclosing inferred operation) and the implicit variable that an unqualified ({@code this}) feature call refers to.
 * The source element the expression originates from is kept for JVM type resolution in later increments.
 */
class ScopeTranslationContext {

  val Map<String, JvmFormalParameter> variables = newLinkedHashMap

  val List<String> extensionClassNames = newArrayList

  Function1<? super Identifier, ? extends JvmType> typeResolver

  JvmFormalParameter implicitVariable

  String implicitVariableName

  ScopeModelTypeResolver modelTypeResolver

  EObject sourceElement

  /**
   * Registers a variable that is in scope for the translated expression.
   *
   * @param name
   *          the source name of the variable, must not be {@code null}
   * @param parameter
   *          the inferred formal parameter the variable resolves to, must not be {@code null}
   */
  def void putVariable(String name, JvmFormalParameter parameter) {
    variables.put(name, parameter)
  }

  /**
   * Returns the formal parameter a variable name resolves to.
   *
   * @param name
   *          the source name of the variable, must not be {@code null}
   * @return the matching formal parameter, or {@code null} if no variable with that name is in scope
   */
  def JvmFormalParameter getVariable(String name) {
    variables.get(name)
  }

  /**
   * Registers the fully qualified name of a Java class whose {@code static} methods provide extension operations
   * (declared in the scope model through {@code extension a::b::C}).
   *
   * @param className
   *          the fully qualified Java class name, must not be {@code null}
   */
  def void addExtensionClassName(String className) {
    extensionClassNames.add(className)
  }

  /**
   * Returns the fully qualified names of the Java classes whose {@code static} methods provide extension operations.
   *
   * @return the extension class names, never {@code null}
   */
  def List<String> getExtensionClassNames() {
    extensionClassNames
  }

  /**
   * Sets the resolver that maps a source DSL {@link Identifier type} to its corresponding JVM type. This decouples
   * the translator from the way types are resolved (a plain classpath lookup for Java types, or a gen-model based
   * lookup for EMF model types).
   *
   * @param resolver
   *          the type resolver, may be {@code null}
   */
  def void setTypeResolver(Function1<? super Identifier, ? extends JvmType> resolver) {
    this.typeResolver = resolver
  }

  /**
   * Resolves the JVM type a source DSL type identifier refers to.
   *
   * @param type
   *          the source type identifier, may be {@code null}
   * @return the resolved JVM type, or {@code null} if it cannot be resolved or no resolver is set
   */
  def JvmType resolveDslType(Identifier type) {
    if (typeResolver === null || type === null) null else typeResolver.apply(type)
  }

  /**
   * Returns the implicit variable that an unqualified ({@code this}) feature call refers to.
   *
   * @return the implicit variable, or {@code null} if none is set
   */
  def JvmFormalParameter getImplicitVariable() {
    implicitVariable
  }

  /**
   * Sets the implicit variable that an unqualified ({@code this}) feature call refers to.
   *
   * @param parameter
   *          the implicit variable, may be {@code null}
   */
  def void setImplicitVariable(JvmFormalParameter parameter) {
    this.implicitVariable = parameter
  }

  /**
   * Returns the name of the Java variable that an unqualified ({@code this}) reference compiles to when the
   * expression is rendered as Java source text by the {@link ScopeExpressionCompiler}.
   *
   * @return the implicit variable name, or {@code null} if none is set
   */
  def String getImplicitVariableName() {
    implicitVariableName
  }

  /**
   * Sets the name of the Java variable that an unqualified ({@code this}) reference compiles to when the expression
   * is rendered as Java source text by the {@link ScopeExpressionCompiler}.
   *
   * @param name
   *          the implicit variable name, may be {@code null}
   */
  def void setImplicitVariableName(String name) {
    this.implicitVariableName = name
  }

  /**
   * Returns the resolver used to map model type names in scope expressions to their EMF classifiers.
   *
   * @return the model type resolver, or {@code null} if none is set
   */
  def ScopeModelTypeResolver getModelTypeResolver() {
    modelTypeResolver
  }

  /**
   * Sets the resolver used to map model type names in scope expressions to their EMF classifiers.
   *
   * @param resolver
   *          the model type resolver, may be {@code null}
   */
  def void setModelTypeResolver(ScopeModelTypeResolver resolver) {
    this.modelTypeResolver = resolver
  }

  /**
   * Returns the source element the translated expression originates from.
   *
   * @return the source element, or {@code null} if none is set
   */
  def EObject getSourceElement() {
    sourceElement
  }

  /**
   * Sets the source element the translated expression originates from.
   *
   * @param element
   *          the source element, may be {@code null}
   */
  def void setSourceElement(EObject element) {
    this.sourceElement = element
  }

}
