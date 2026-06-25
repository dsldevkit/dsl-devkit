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
package com.avaloq.tools.ddk.xtext.export.jvmmodel

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation
import com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression
import com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression
import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.Literal
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.util.Strings

/**
 * Compiles the custom {@link Expression} AST of the export expression DSL into equivalent Java source text.
 * <p>
 * This is the self-contained, {@code org.eclipse.xtend}-free replacement of the legacy
 * {@code CodeGenerationX}/{@code CompilationContext} expression compiler. It produces exactly the same Java
 * fragments as the legacy compiler did, but resolves types, variables and the implicit ({@code this}) receiver
 * through the shared {@link ExportTranslationContext} that the
 * {@link ExportExpressionTranslator} already uses, rather than through the classic Xtend type system.
 * <p>
 * The export generators splice the produced Java fragments into the body of the inferred provider methods. The
 * {@code .ext}/{@code JAVA} extension branches of the legacy compiler are intentionally dropped: export sources no
 * longer reference Xtend extension files.
 */
class ExportExpressionCompiler {

  /** Maps EMF model types to their generated Java instance class names. */
  @Inject GenModelUtilX genModelUtil

  /** Reuses the translator's type/variable/getter resolution so both stay consistent. */
  @Inject ExportExpressionTranslator translator

  //////////////////////////////////////////////////
  // ENTRY POINTS
  //////////////////////////////////////////////////
  /**
   * Tests whether the given expression can be compiled to Java by this compiler.
   *
   * @param expression
   *          the source expression, may be {@code null}
   * @param context
   *          the compilation context, must not be {@code null}
   * @return {@code true} if a non-{@code null} fragment without the {@code NOT COMPILABLE} marker can be produced
   */
  def boolean isCompilable(Expression expression, ExportTranslationContext context) {
    val expr = expression.javaExpression(context)
    expr !== null && !expr.contains('/* NOT COMPILABLE: ')
  }

  /**
   * Compiles the given expression into the equivalent Java source text.
   *
   * @param expression
   *          the source expression, may be {@code null}
   * @param context
   *          the compilation context, must not be {@code null}
   * @return the Java source text, never {@code null}
   */
  def dispatch String javaExpression(Void it, ExportTranslationContext ctx) {
    ''
  }

  def dispatch String javaExpression(Expression it, ExportTranslationContext ctx) {
    notCompilable
  }

  def private String notCompilable(Expression it) {
    '/* NOT COMPILABLE: Complex expressions like "' + serialize() + '" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */'
  }

  //////////////////////////////////////////////////
  // LITERALS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(StringLiteral it, ExportTranslationContext ctx) {
    '"' + javaEncode(getVal()) + '"'
  }

  def dispatch String javaExpression(BooleanLiteral it, ExportTranslationContext ctx) {
    getVal()
  }

  def dispatch String javaExpression(IntegerLiteral it, ExportTranslationContext ctx) {
    getVal().toString()
  }

  def dispatch String javaExpression(NullLiteral it, ExportTranslationContext ctx) {
    "null"
  }

  def dispatch String javaExpression(RealLiteral it, ExportTranslationContext ctx) {
    getVal().toString()
  }

  def dispatch String javaExpression(ListLiteral it, ExportTranslationContext ctx) {
    if (elements.empty) {
      "java.util.Collections.<org.eclipse.emf.ecore.EObject> emptyList()"
    } else if (elements.size == 1) {
      "java.util.Collections.singletonList(" + elements.head.javaExpression(ctx) + ")"
    } else {
      "com.google.common.collect.Lists.newArrayList(" + ', '.join(elements.map[javaExpression(ctx)]) + ")"
    }
  }

  //////////////////////////////////////////////////
  // TYPES AND VARIABLES
  //////////////////////////////////////////////////
  def dispatch String javaExpression(Identifier it, ExportTranslationContext ctx) {
    if (isThis()) ctx.implicitVariableName else '::'.join(id)
  }

  def private boolean isTypeRef(FeatureCall it, ExportTranslationContext ctx) {
    name === null && type !== null && (ctx.modelTypeResolver?.resolve(type.id) !== null || ctx.resolveDslType(type) !== null)
  }

  def private boolean isVariableRef(Expression it, ExportTranslationContext ctx) {
    false
  }

  def private boolean isVariableRef(FeatureCall it, ExportTranslationContext ctx) {
    target === null && name === null && type !== null && type.id.size == 1 && ctx.getVariable(type.id.head) !== null
  }

  def private String featureCallTarget(FeatureCall it, ExportTranslationContext ctx) {
    if (target === null || target.isThisCall())
      ctx.implicitVariableName
    else
      target.javaExpression(ctx)
  }

  //////////////////////////////////////////////////
  // BOOLEAN OPERATIONS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(BooleanOperation it, ExportTranslationContext ctx) {
    autoBracket(left.javaExpression(ctx) + ' ' + operator + ' ' + right.javaExpression(ctx), ctx)
  }

  //////////////////////////////////////////////////
  // COLLECTION OPERATIONS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(CollectionExpression it, ExportTranslationContext ctx) {
    if ('select' == name) {
      'com.google.common.collect.Iterables.filter(' + target.javaExpression(ctx) +
          ', new com.google.common.base.Predicate<Object>() { public boolean apply(Object ' +
          (if (getVar() !== null) getVar() else 'e') + ') {return ' +
          exp.javaExpression(ctx) + ';} })'
    } else {
      notCompilable()
    }
  }

  def dispatch String javaExpression(TypeSelectExpression it, ExportTranslationContext ctx) {
    if (isSimpleNavigation(ctx))
      'com.google.common.collect.Iterables.filter(' + target.javaExpression(ctx) + ', ' + ctx.javaType(type) + '.class)'
    else notCompilable()
  }

  //////////////////////////////////////////////////
  // TYPE CAST
  //////////////////////////////////////////////////
  def dispatch String javaExpression(CastedExpression it, ExportTranslationContext ctx) {
    '((' + ctx.javaType(type) + ') ' + target.javaExpression(ctx) + ')'
  }

  //////////////////////////////////////////////////
  // IF EXPRESSIONS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(IfExpression it, ExportTranslationContext ctx) {
    autoBracket(condition.javaExpression(ctx) + ' ? ' + thenPart.javaExpression(ctx) + ' : ' + elsePart.javaExpression(ctx), ctx)
  }

  //////////////////////////////////////////////////
  // FEATURE CALLS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(FeatureCall it, ExportTranslationContext ctx) {
    if (isThisCall()) {
      ctx.implicitVariableName
    } else if (isVariableRef(ctx)) {
      type.javaExpression(ctx)
    } else if (isTypeRef(ctx)) {
      ctx.javaType(type)
    } else if (isSimpleFeatureCall(ctx)) {
      featureCallTarget(ctx) + '.' + (if (calledFeature() == 'eContainer') 'eContainer' else (if (calledFeature() == 'isEmpty') 'isEmpty' else calledFeature().toFirstUpper().featureCallName())) + '()'
    } else if (isSimpleNavigation(ctx)) {
      notCompilable()
    } else {
      featureCallTarget(ctx) + '.' + (if (calledFeature() == 'eContainer') 'eContainer' else (if (calledFeature() == 'isEmpty') 'isEmpty' else calledFeature().toFirstUpper().featureCallName())) + '()'
    }
  }

  def private String featureCallName(String it) {
    if (it.startsWith('^'))
      it.substring(1, it.length).toFirstUpper().featureCallName()
    else
     (if (it.startsWith('Is')) 'is' else 'get') + it
  }

  /**
   * Tests whether the given feature call is a simple feature access on the implicit receiver or an in-scope
   * variable, i.e. one that compiles to a single {@code getX()}/{@code isX()} accessor. Replaces the legacy
   * {@code CodeGenerationX.isSimpleFeatureCall}.
   *
   * @param it
   *          the expression, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return {@code true} if the call is a simple feature access
   */
  def dispatch boolean isSimpleFeatureCall(Expression it, ExportTranslationContext ctx) {
    false
  }

  def dispatch boolean isSimpleFeatureCall(FeatureCall it, ExportTranslationContext ctx) {
    eClass.name.contains('FeatureCall') && name === null && type.isFeature() && (target === null || target.isVariableRef(ctx) || target.isThisCall())
  }

  def dispatch boolean isSimpleNavigation(Expression it, ExportTranslationContext ctx) {
    false
  }

  def dispatch boolean isSimpleNavigation(TypeSelectExpression it, ExportTranslationContext ctx) {
    true
  }

  def dispatch boolean isSimpleNavigation(FeatureCall it, ExportTranslationContext ctx) {
    name === null && type.isFeature() && (target === null || target.isVariableRef(ctx) || target.isThisCall() || target.isSimpleNavigation(ctx))
  }

  //////////////////////////////////////////////////
  // OPERATION CALLS
  //////////////////////////////////////////////////
  def dispatch String javaExpression(OperationCall it, ExportTranslationContext ctx) {
    if ((target === null || target.isThisCall()) && targetHasOperation(ctx)) {
      (if (target !== null) target.javaExpression(ctx) + '.' else '') + name + '(' + ', '.join(params.map[javaExpression(ctx)]) + ')'
    } else if (isArithmeticOperatorCall(ctx)) {
      autoBracket((' ' + name + ' ').join(params.map(e|e.javaExpression(ctx))), ctx)
    } else if (isSimpleConcatCall()) {
      (' + ').join(params.map(e|e.javaExpression(ctx)))
    } else if (isPrefixExpression()) {
      autoBracket(name + params.head.javaExpression(ctx), ctx)
    } else if ('first' == name && params.isEmpty && target !== null) {
      target.javaExpression(ctx) + '.get(0)'
    } else if ('isInstance' == name && params.size == 1 && target instanceof FeatureCall && (target as FeatureCall).isTypeRef(ctx)) {
      autoBracket(params.head.javaExpression(ctx) + ' instanceof ' + target.javaExpression(ctx), ctx)
    } else if ('eContainer' == name && params.isEmpty) {
      target.javaExpression(ctx) + '.eContainer()'
    } else {
      (if (target !== null) target.javaExpression(ctx) + '.' else '') + name + '(' + (if (params.isEmpty) '' else ', '.join(params.map[javaExpression(ctx)])) + ')'
    }
  }

  /**
   * Heuristically tests whether an unqualified or {@code this} qualified operation call refers to an operation
   * declared on the implicit receiver's type. Replaces the legacy {@code CompilationContext.targetHasOperation}.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return {@code true} if the implicit receiver type declares a matching operation
   */
  def private boolean targetHasOperation(OperationCall it, ExportTranslationContext ctx) {
    val receiverType = translator.resolveType(target, ctx)
    if (!(receiverType instanceof JvmDeclaredType)) {
      return false
    }
    val operationName = name
    val parameterCount = params.size
    val declaredType = receiverType as JvmDeclaredType
    declaredType.allFeatures.filter(JvmOperation).exists [
      simpleName == operationName && parameters.size == parameterCount
    ]
  }

  //////////////////////////////////////////////////
  // EXPRESSION BRACKETING
  //////////////////////////////////////////////////
  def private String autoBracket(Expression it, String javaCode, ExportTranslationContext ctx) {
    if (requiresBracketing(ctx)) '(' + javaCode + ')' else javaCode
  }

  def private dispatch boolean requiresBracketing(Expression it, ExportTranslationContext ctx) {
    (isPrefixExpression() || isInfixExpression(ctx)) && eContainer() !== null && requiresBracketing(it, eContainer(), ctx)
  }

  def private dispatch boolean requiresBracketing(Literal it, ExportTranslationContext ctx) {
    false
  }

  def private dispatch boolean requiresBracketing(Expression it, Object parent, ExportTranslationContext ctx) {
    false
  }

  def private dispatch boolean requiresBracketing(Expression it, Expression parent, ExportTranslationContext ctx) {
    isPrefixExpression() && parent.isPrefixExpression() ||
    (isInfixExpression(ctx) && (parent.isPrefixExpression() || parent.isInfixExpression(ctx)))
  }

  def private dispatch boolean requiresBracketing(OperationCall it, OperationCall parent, ExportTranslationContext ctx) {
    isPrefixExpression() && parent.isPrefixExpression() ||
    (isInfixExpression(ctx) && (parent.isPrefixExpression() || (parent.isInfixExpression(ctx) && name != parent.name)))
  }

  def private dispatch boolean requiresBracketing(BooleanOperation it, BooleanOperation parent, ExportTranslationContext ctx) {
    operator != parent.operator
  }

  //////////////////////////////////////////////////
  // OPERATOR CLASSIFICATION
  //////////////////////////////////////////////////
  def private boolean isSimpleConcatCall(OperationCall it) {
    name == '+' && type === null && target === null && !params.isEmpty
  }

  def private boolean isNumber(Expression it, ExportTranslationContext ctx) {
    if (isArithmeticOperatorCall(ctx)) {
      // a nested arithmetic operation (e.g. the (a + b) in (a + b) * c) is itself numeric; resolveType cannot see this
      return true
    }
    val type = translator.resolveType(it, ctx)
    type !== null && type.isNumeric
  }

  def private boolean isNumeric(JvmType it) {
    val name = qualifiedName
    switch name {
      case 'int',
      case 'long',
      case 'short',
      case 'byte',
      case 'double',
      case 'float',
      case 'java.lang.Integer',
      case 'java.lang.Long',
      case 'java.lang.Short',
      case 'java.lang.Byte',
      case 'java.lang.Double',
      case 'java.lang.Float',
      case 'java.lang.Number',
      case 'java.math.BigInteger',
      case 'java.math.BigDecimal':
        true
      default:
        false
    }
  }

  def private dispatch boolean isArithmeticOperatorCall(OperationCall it, ExportTranslationContext ctx) {
    type === null && target === null && params.size > 1 && (name == '+' || name == '-' || name == '*' || name == '/') && params.forall[isNumber(ctx)]
  }

  def private dispatch boolean isArithmeticOperatorCall(Expression it, ExportTranslationContext ctx) {
    false
  }

  def private dispatch boolean isPrefixExpression(Expression it) {
    false
  }

  def private dispatch boolean isPrefixExpression(OperationCall it) {
    type === null && target === null && params.size == 1 && (name == '-' || name == '!')
  }

  def private dispatch boolean isInfixExpression(Void it, ExportTranslationContext ctx) {
    false
  }

  def private dispatch boolean isInfixExpression(Expression it, ExportTranslationContext ctx) {
    false
  }

  def private dispatch boolean isInfixExpression(OperationCall it, ExportTranslationContext ctx) {
    isArithmeticOperatorCall(ctx) || 'isInstance' == name
  }

  def private dispatch boolean isInfixExpression(IfExpression it, ExportTranslationContext ctx) {
    true
  }

  def private dispatch boolean isInfixExpression(BooleanOperation it, ExportTranslationContext ctx) {
    true
  }

  //////////////////////////////////////////////////
  // TYPE RESOLUTION
  //////////////////////////////////////////////////
  /**
   * Resolves the Java class name of the given source DSL type identifier. Model types resolve through the imported
   * EPackages to their generated instance class name (exactly as the legacy {@code CompilationContext.javaType}
   * did through {@code genModelUtil.instanceClassName}); plain Java types resolve against the classpath. Replaces
   * the legacy {@code CompilationContext.javaType}.
   *
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @param type
   *          the source type identifier, must not be {@code null}
   * @return the qualified Java class name (or the joined identifier when the type cannot be resolved)
   */
  def private String javaType(ExportTranslationContext ctx, Identifier type) {
    val classifier = ctx.modelTypeResolver?.resolve(type.id)
    if (classifier !== null) {
      return genModelUtil.instanceClassName(classifier)
    }
    val resolved = ctx.resolveDslType(type)
    if (resolved === null) {
      return '::'.join(type.id)
    }
    val name = resolved.qualifiedName
    if (name.startsWith('java.lang.') && !name.substring('java.lang.'.length).contains('.')) {
      name.substring('java.lang.'.length)
    } else {
      name
    }
  }

  //////////////////////////////////////////////////
  // HELPER FUNCTIONS
  //////////////////////////////////////////////////
  def private dispatch boolean isThisCall(Expression it) {
    false
  }

  def private dispatch boolean isThisCall(FeatureCall it) {
    name === null && type.isThis()
  }

  def private boolean isFeature(Identifier it) {
    id !== null && id.size == 1
  }

  def private dispatch boolean isThis(Expression it) {
    false
  }

  def private dispatch boolean isThis(Identifier it) {
    id !== null && id.size == 1 && id.head == "this"
  }

  def private String calledFeature(FeatureCall it) {
    type.id.head
  }

  def private String serialize(EObject it) {
    ExpressionExtensions.serialize(it)
  }

  def private dispatch String javaEncode(Expression it) {
    javaEncode(serialize())
  }

  def private dispatch String javaEncode(String it) {
    Strings.convertToJavaString(it)
  }

  def private String join(String it, List<String> strings) {
    if (strings.isEmpty) '' else Strings.concat(it, strings)
  }

}
