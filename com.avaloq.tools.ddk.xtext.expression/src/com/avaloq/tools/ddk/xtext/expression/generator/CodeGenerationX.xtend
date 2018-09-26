
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

package com.avaloq.tools.ddk.xtext.expression.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation
import com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression
import com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression
import com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression
import java.util.List
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.Literal

class CodeGenerationX {

  extension ExpressionExtensionsX = new ExpressionExtensionsX

  //////////////////////////////////////////////////
  // ENTRY POINTS
  //////////////////////////////////////////////////
  def boolean isCompilable(Expression it, CompilationContext ctx) {
    val expr = javaExpression(ctx)
    expr !== null && !expr.contains('/* NOT COMPILABLE: ')
  }

  def dispatch String javaExpression(Void it, CompilationContext ctx) {
    ''
  }

  def dispatch String javaExpression(Expression it, CompilationContext ctx) {
    notCompilable
  }

  def String notCompilable(Expression it) {
    '/* NOT COMPILABLE: Complex expressions like "' + serialize() + '" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */'
  }

  //////////////////////////////////////////////////
  // LITERALS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(StringLiteral it, CompilationContext ctx) {
    '"' + javaEncode(^val) + '"'
  }

  def dispatch String javaExpression(BooleanLiteral it, CompilationContext ctx) {
    ^val
  }

  def dispatch String javaExpression(IntegerLiteral it, CompilationContext ctx) {
    ^val.toString()
  }

  def dispatch String javaExpression(NullLiteral it, CompilationContext ctx) {
    "null"
  }

  def dispatch String javaExpression(RealLiteral it, CompilationContext ctx) {
    ^val.toString()
  }

  def dispatch String javaExpression(ListLiteral it, CompilationContext ctx) {
    if (elements.empty) {
      "java.util.Collections.<" + ctx.javaType(ctx.requiredType.name) + "> emptyList()"
    } else if (elements.size == 1) {
      "java.util.Collections.singletonList(" + elements.head.javaExpression(ctx) + ")"
    } else {
      "com.google.common.collect.Lists.newArrayList(" + ', '.join(elements.map[javaExpression(ctx)]) + ")"
    }
  }

  //////////////////////////////////////////////////
  // TYPES AND VARIABLES
  //////////////////////////////////////////////////
  def dispatch String javaExpression(Identifier it, CompilationContext ctx) {
    if (isThis()) ctx.implicitVariable else '::'.join(id)
  }

  def boolean isType(FeatureCall it, CompilationContext ctx) {
    name === null && type !== null && ctx.isType(type.javaExpression(ctx))
  }

  def boolean isVariable(Expression it, CompilationContext ctx) {
      false
  }

  def boolean isVariable(FeatureCall it, CompilationContext ctx) {
      target === null && name === null && ctx.isVariable(type.javaExpression(ctx))
  }

  def String featureCallTarget(FeatureCall it, CompilationContext ctx) {
    if (target === null || target.isThisCall())
      ctx.implicitVariable
    else
      target.javaExpression(ctx)
  }

  //////////////////////////////////////////////////
  // BOOLEAN OPERATIONS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(BooleanOperation it, CompilationContext ctx) {
    autoBracket(left.javaExpression(ctx) + ' ' + operator + ' ' + right.javaExpression(ctx), ctx)
  }

  //////////////////////////////////////////////////
  // COLLECTION OPERATIONS
  //////////////////////////////////////////////////
  // TODO finish
  def dispatch String javaExpression(CollectionExpression it, CompilationContext ctx) {
    if ('select' == name) {
      'com.google.common.collect.Iterables.filter(' + target.javaExpression(ctx) +
          ', new com.google.common.base.Predicate<Object>() { public boolean apply(Object ' +
          (if (^var !== null) ^var else 'e') + ') {return ' +
          exp.javaExpression(ctx) + ';} })'
    } else {
      notCompilable()
    }
  }

  def dispatch String javaExpression(TypeSelectExpression it, CompilationContext ctx) {
    if (isSimpleNavigation(ctx))
      'com.google.common.collect.Iterables.filter(' + target.javaExpression(ctx) + ', ' + ctx.javaType(type.javaExpression(ctx)) + '.class)'
    else notCompilable()
  }

  //////////////////////////////////////////////////
  // TYPE CAST
  //////////////////////////////////////////////////
  def dispatch String javaExpression(CastedExpression it, CompilationContext ctx) {
    '((' + ctx.javaType(type.javaExpression(ctx)) + ') ' + target.javaExpression(ctx) + ')'
  }

  //////////////////////////////////////////////////
  // IF EXPRESSIONS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(IfExpression it, CompilationContext ctx) {
    autoBracket(condition.javaExpression(ctx) + ' ? ' + thenPart.javaExpression(ctx) + ' : ' + elsePart.javaExpression(ctx), ctx)
  }

  //////////////////////////////////////////////////
  // FEATURE CALLS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(FeatureCall it, CompilationContext ctx) {
    if (isThisCall()) {
      ctx.implicitVariable
    } else if (isVariable(ctx)) {
      type.javaExpression(ctx)
    } else if (isType(ctx)) {
      ctx.javaType(type.javaExpression(ctx))
    } else if (isSimpleFeatureCall(ctx)) {
      featureCallTarget(ctx) + '.' + (if (calledFeature() == 'eContainer') 'eContainer' else (if (calledFeature() == 'isEmpty') 'isEmpty' else calledFeature().toFirstUpper().featureCallName())) + '()'
    } else if (isSimpleNavigation(ctx)) {
      notCompilable()
    } else {
      featureCallTarget(ctx) + '.' + (if (calledFeature() == 'eContainer') 'eContainer' else (if (calledFeature() == 'isEmpty') 'isEmpty' else calledFeature().toFirstUpper().featureCallName())) + '()'
    }
  }

  // TODO: actually, we should look at the feature and return "is" only if it has a boolean value... Can this be done??
  def String featureCallName(String it) {
    if (it.startsWith('^'))
      it.substring(1, it.length).toFirstUpper().featureCallName()
    else
     (if (it.startsWith('Is')) 'is' else 'get') + it
  }

  def boolean isSimpleFeatureCall(Expression it, CompilationContext ctx) {
    false
  }

  def boolean isSimpleFeatureCall(FeatureCall it, CompilationContext ctx) {
    eClass.name.contains('FeatureCall') && name === null && type.isFeature() && (target === null || target.isVariable(ctx) || target.isThisCall())
  }

  def dispatch boolean isSimpleNavigation(Expression it, CompilationContext ctx) {
    false
  }

  def dispatch boolean isSimpleNavigation(TypeSelectExpression it, CompilationContext ctx) {
    true
  }

  def dispatch boolean isSimpleNavigation(FeatureCall it, CompilationContext ctx) {
    name === null && type.isFeature() && (target === null || target.isVariable(ctx) || target.isThisCall() || target.isSimpleNavigation(ctx))
  }

  def dispatch String navigationRoot(Void it, CompilationContext ctx) {
    ''
  }

  def dispatch String navigationRoot(Expression it, CompilationContext ctx) {
    ''
  }

  def dispatch String navigationRoot(FeatureCall it, CompilationContext ctx) {
    if (target !== null) target.navigationRoot(ctx) else (if (isVariable(ctx)) javaExpression(ctx) else ctx.implicitVariable)
  }

  def dispatch List<String> navigations(Void it, CompilationContext ctx) {
    {}
  }

  def dispatch List<String> navigations(Expression it, CompilationContext ctx) {
    {}
  }

  def dispatch List<String> navigations(FeatureCall it, CompilationContext ctx) {
    val navs = target.navigations(ctx)
    if (navs.isEmpty && (isThisCall() || isVariable(ctx))) #[] else { navs.add(calledFeature()); navs }
  }

  def dispatch List<String> navigations(TypeSelectExpression it, CompilationContext ctx) {
    val navs = target.navigations(ctx)
    navs.add("typeSelect(" + type.qualifiedTypeName(ctx) + ")")
    navs
  }

  //////////////////////////////////////////////////
  // OPERATION CALLS
  //////////////////////////////////////////////////
  // TODO handle eClass()
  // TODO work out if 'this' should be added or not
  def dispatch String javaExpression(OperationCall it, CompilationContext ctx) {
    if ((target === null || target.isThisCall()) && ctx.targetHasOperation(it)) {
      (if (target !== null) target.javaExpression(ctx) + '.' else '') + name + '(' + ', '.join(params.map[javaExpression(ctx)]) + ')'
    } else if (isJavaExtensionCall(ctx)) {
      calledJavaMethod(ctx) + '(' + ', '.join((if (target !== null) { val l = newArrayList(target); l.addAll(params); l } else params).map[javaExpression(ctx)]) + ')'
    } else if (isArithmeticOperatorCall(ctx)) {
      autoBracket((' ' + name + ' ').join(params.map(e|e.javaExpression(ctx))), ctx)
    } else if (isSimpleConcatCall()) {
      (' + ').join(params.map(e|e.javaExpression(ctx)))
    } else if (isPrefixExpression(ctx)) {
      autoBracket(name + params.head.javaExpression(ctx), ctx)
    } else if ('first' == name && params.isEmpty && target !== null) {
      target.javaExpression(ctx) + '.get(0)'
    } else if ('isInstance' == name && params.size == 1 && target instanceof FeatureCall && (target as FeatureCall).isType(ctx)) {
      autoBracket(params.head.javaExpression(ctx) + ' instanceof ' + target.javaExpression(ctx), ctx)
    } else if ('eContainer' == name && params.isEmpty) {
      target.javaExpression(ctx) + '.eContainer()'
    } else if (ctx.isExtension(name)) {
      notCompilable()
    } else {
      (if (target !== null) target.javaExpression(ctx) + '.' else '') + name + '(' + (if (params.isEmpty) '' else ', '.join(params.map[javaExpression(ctx)])) + ')'
    }
  }

  def boolean isJavaExtensionCall(Expression it) {
    false
  }

  def boolean isJavaExtensionCall(OperationCall it, CompilationContext ctx) {
    name != 'isInstance' && isSimpleCall(ctx) && calledJavaMethod(ctx) !== null
  }

  def boolean isSimpleCall(OperationCall it, CompilationContext ctx) {
    (target === null || target.isCompilable(ctx)) && params.forall(p|p.isCompilable(ctx))
  }

  def String calledJavaMethod(OperationCall it, CompilationContext ctx) {
    ctx.calledJavaMethod(it)
  }

  def /*cached*/ String calledJavaMethod(CompilationContext it, OperationCall call) {
    getCalledJavaMethod(call)
  }

  //////////////////////////////////////////////////
  // EXPRESSION BRACKETING
  //////////////////////////////////////////////////
  def String autoBracket(Expression it, String javaCode, CompilationContext ctx) {
    if (requiresBracketing(ctx)) '(' + javaCode + ')' else javaCode
  }

  def dispatch boolean requiresBracketing(Expression it, CompilationContext ctx) {
    (isPrefixExpression(ctx) || isInfixExpression(ctx)) && eContainer() !== null && requiresBracketing(it, eContainer(), ctx)
  }

  def dispatch boolean requiresBracketing(Literal it, CompilationContext ctx) {
    false
  }

  def dispatch boolean requiresBracketing(Expression it, Object parent, CompilationContext ctx) {
    false
  }

  def dispatch boolean requiresBracketing(Expression it, Expression parent, CompilationContext ctx) {
    isPrefixExpression(ctx) && parent.isPrefixExpression(ctx) ||
    (isInfixExpression(ctx) && (parent.isPrefixExpression(ctx) || parent.isInfixExpression(ctx)))
  }

  def dispatch boolean requiresBracketing(OperationCall it, OperationCall parent, CompilationContext ctx) {
    isPrefixExpression(ctx) && parent.isPrefixExpression(ctx) ||
    (isInfixExpression(ctx) && (parent.isPrefixExpression(ctx) || (parent.isInfixExpression(ctx) && name != parent.name)))
  }

  def dispatch boolean requiresBracketing(BooleanOperation it, BooleanOperation parent, CompilationContext ctx) {
    operator != parent.operator
  }

  //////////////////////////////////////////////////
  // HELPER FUNCTIONS
  //////////////////////////////////////////////////
  def dispatch boolean isThisCall(Expression it) {
    false
  }

  def dispatch boolean isThisCall(FeatureCall it) {
    name === null && type.isThis()
  }

  def boolean isFeature(Identifier it) {
    id !== null && id.size == 1
  }

  def dispatch boolean isThis(Expression it) {
    false
  }

  def dispatch boolean isThis(Identifier it) {
    id !== null && id.size == 1 && id.head == "this"
  }

  def String qualifiedTypeName(Identifier it, CompilationContext ctx) {
    if (id.size == 2) id.get(0) + "::" + id.get(1) else ctx.qualifiedTypeName(id.head)
  }

  def /*cached*/ String qualifiedTypeName(CompilationContext it, String name) {
    getQualifiedTypeName(name)
  }

  def dispatch String javaEncode(Expression it) {
    javaEncode(serialize())
  }

  def dispatch String javaEncode(String it) {
    org.eclipse.xtext.util.Strings.convertToJavaString(it)
  }

  def String join(String it, List<String> strings) {
    if (strings.isEmpty) '' else internalJoin(strings)
  }

  private def String internalJoin(String it, List<String> strings) {
    org.eclipse.xtext.util.Strings.concat(it, strings)
  }

  def String join(String it, String strings) {
    strings
  }

  def String join(String it, Void strings) {
    ''
  }

}
