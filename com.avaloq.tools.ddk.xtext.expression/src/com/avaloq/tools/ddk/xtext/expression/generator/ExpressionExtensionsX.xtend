/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.expression.generator

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation
import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import org.eclipse.emf.ecore.EObject

class ExpressionExtensionsX {

  def dispatch String serialize(EObject it) {
    ExpressionExtensions.serialize(it)
  }

  def dispatch String serialize(Void it) {
    null
  }

  def dispatch boolean isEmptyList(Expression it) {
    false
  }

  def dispatch boolean isEmptyList(ListLiteral it) {
    elements.isEmpty
  }

  def boolean isSimpleConcatCall(OperationCall it) {
    name == '+' && type === null && target === null && !params.isEmpty
  }

  def boolean isNumber(Expression it, CompilationContext ctx) {
    ctx.findType('Real').isAssignableFrom(ctx.analyze(it))
  }

  def dispatch boolean isArithmeticOperatorCall(OperationCall it, CompilationContext ctx) {
    type === null && target === null && params.size > 1 && (name == '+' || name == '-' || name == '*' || name == '/') && params.forall(p|p.isNumber(ctx))
  }

  def dispatch boolean isArithmeticOperatorCall(Expression it, CompilationContext ctx) {
    false
  }

  def dispatch boolean isPrefixExpression(Expression it, CompilationContext ctx) {
    false
  }

  def dispatch boolean isPrefixExpression(OperationCall it, CompilationContext ctx) {
    type === null && target === null && params.size == 1 && (name == '-' || name == '!')
  }

  def dispatch boolean isInfixExpression(Void it, CompilationContext ctx) {
    false
  }

  def dispatch boolean isInfixExpression(Expression it, CompilationContext ctx) {
    false
  }

  def dispatch boolean isInfixExpression(OperationCall it, CompilationContext ctx) {
    isArithmeticOperatorCall(ctx) || 'isInstance' == name
  }

  def dispatch boolean isInfixExpression(IfExpression it, CompilationContext ctx) {
    true
  }

  def dispatch boolean isInfixExpression(BooleanOperation it, CompilationContext ctx) {
    true
  }

  def String calledFeature(FeatureCall it) {
    type.id.head
  }

}