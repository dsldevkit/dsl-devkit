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
import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression
import com.avaloq.tools.ddk.xtext.export.generator.ExportModelTypeResolver
import com.google.common.collect.Iterables
import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.XbaseFactory
import org.eclipse.xtext.xbase.lib.BooleanExtensions

/**
 * Translates the custom {@link Expression} AST of the export expression DSL into equivalent Xbase
 * {@link XExpression} trees that can be compiled by the {@code XbaseCompiler} through the JVM model inferrer.
 * <p>
 * Translation happens against an {@link ExportTranslationContext} that provides the variables in scope and the
 * implicit ({@code this}) variable. Nodes that require resolving forms that are not yet supported return
 * {@code null}; the {@link ExportExpressionCompiler} is used as the string fallback that the export generators
 * splice into the inferred provider method bodies.
 */
class ExportExpressionTranslator {

  /** Provides access to the {@code org.eclipse.xtext.xbase.lib} operator methods that back binary operations. */
  @Inject extension TypeReferences

  /**
   * Translates the given expression into an equivalent {@link XExpression}.
   *
   * @param expression
   *          the source expression, may be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the translated {@link XExpression}, or {@code null} if the expression cannot (yet) be translated
   */
  def XExpression translate(Expression expression, ExportTranslationContext context) {
    if (expression === null) {
      return null
    }
    return expression.doTranslate(context)
  }

  /**
   * Resolves a factory expression of the form {@code Type.method(args)} to the fully qualified static Java method
   * {@code <type qualified name>.<method>} by linking the type reference against the classpath. This replaces the
   * legacy {@code .ext} based resolution: the declaring type is named directly in the export source rather than
   * indirected through an Xtend extension file.
   *
   * @param expression
   *          the factory expression, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve the type against the classpath, must not be {@code null}
   * @return the fully qualified static method ({@code type.method}), or {@code null} if the expression is not a
   *         type-qualified operation call or the type cannot be resolved
   */
  def String resolveFactoryMethod(Expression expression, EObject sourceElement) {
    if (!(expression instanceof OperationCall)) {
      return null
    }
    val call = expression as OperationCall
    if (!(call.target instanceof FeatureCall)) {
      return null
    }
    val typeReference = call.target as FeatureCall
    if (typeReference.name !== null || typeReference.type === null) {
      return null
    }
    val jvmType = findDeclaredType(typeReference.type.id.join('.'), sourceElement)
    if (jvmType instanceof JvmDeclaredType) {
      return jvmType.qualifiedName + '.' + call.name
    }
    null
  }

  /**
   * Tests whether the given expression can be extracted into a typed helper method, i.e. whether it can be both
   * translated into an {@link XExpression} and have its result type resolved. The check is performed against a
   * fresh trial context holding a single {@code ctx} variable of the given context type, so it can be used at code
   * generation time before the actual JVM operation (and its formal parameter) exist.
   *
   * @param expression
   *          the source expression to test, must not be {@code null}
   * @param contextType
   *          the EClass of the {@code ctx} context variable, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve types against the classpath, must not be {@code null}
   * @return {@code true} if the expression can be extracted into a typed helper method
   */
  def boolean canExtractAsValue(Expression expression, EClass contextType, EObject sourceElement) {
    val context = newTrialContext(contextType, sourceElement)
    translate(expression, context) !== null && resolveType(expression, context) !== null
  }

  /**
   * Tests whether the given expression can be extracted into a helper method whose result is a {@link String}, i.e.
   * whether it can be translated and its result type resolves to {@code java.lang.String}. Used for splice sites that
   * are overloaded on the argument type (such as a container query name) where extracting a non-{@code String} value
   * would select the wrong overload.
   *
   * @param expression
   *          the source expression to test, must not be {@code null}
   * @param contextType
   *          the EClass of the {@code ctx} context variable, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve types against the classpath, must not be {@code null}
   * @return {@code true} if the expression can be extracted as a {@code String} valued helper method
   */
  def boolean canExtractAsString(Expression expression, EClass contextType, EObject sourceElement) {
    val context = newTrialContext(contextType, sourceElement)
    if (translate(expression, context) === null) {
      return false
    }
    val resolved = resolveType(expression, context)
    resolved !== null && resolved.qualifiedName == String.name
  }

  /**
   * Tests whether the given expression can be extracted into a typed helper method that, in addition to the primary
   * {@code ctx} variable, has the given extra variables in scope (for example a data match lambda's element
   * description). The check uses a fresh trial context so it can be used at code generation time.
   *
   * @param expression
   *          the source expression to test, must not be {@code null}
   * @param contextType
   *          the EClass of the {@code ctx} context variable, must not be {@code null}
   * @param extraVariables
   *          the extra variables as source-name to fully-qualified-type-name pairs, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve types against the classpath, must not be {@code null}
   * @return {@code true} if the expression can be extracted into a typed helper method
   */
  def boolean canExtractAsValue(Expression expression, EClass contextType, List<Pair<String, String>> extraVariables,
    EObject sourceElement) {
    val context = newTrialContext(contextType, sourceElement)
    for (extra : extraVariables) {
      context.putVariable(extra.key, newTrialParameter(extra.key, extra.value, sourceElement))
    }
    translate(expression, context) !== null && resolveType(expression, context) !== null
  }

  /**
   * Creates a trial translation context holding a single {@code ctx} variable of the given context type. The
   * context's type resolver resolves DSL type identifiers by their fully qualified name against the classpath.
   *
   * @param contextType
   *          the EClass of the {@code ctx} context variable, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve types against the classpath, must not be {@code null}
   * @return the trial context, never {@code null}
   */
  def private ExportTranslationContext newTrialContext(EClass contextType, EObject sourceElement) {
    val parameter = newTrialParameter('ctx', contextType.instanceClassName, sourceElement)
    val context = new ExportTranslationContext
    context.sourceElement = sourceElement
    context.putVariable('ctx', parameter)
    context.implicitVariable = parameter
    context.typeResolver = [Identifier identifier|findDeclaredType(identifier.id.join('.'), sourceElement)]
    context
  }

  /**
   * Creates a trial formal parameter with the given name and type, used to populate a trial translation context.
   *
   * @param name
   *          the parameter name, must not be {@code null}
   * @param typeName
   *          the fully qualified type name of the parameter, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve the type against the classpath, must not be {@code null}
   * @return the trial parameter, never {@code null}
   */
  def private JvmFormalParameter newTrialParameter(String name, String typeName, EObject sourceElement) {
    val parameter = TypesFactory.eINSTANCE.createJvmFormalParameter
    parameter.name = name
    val jvmType = findDeclaredType(typeName, sourceElement)
    if (jvmType !== null) {
      parameter.parameterType = createTypeRef(jvmType)
    }
    parameter
  }

  /**
   * Creates a translation context for rendering an expression to Java source text with the
   * {@link ExportExpressionCompiler}. The context binds the implicit ({@code this}) receiver to the given Java
   * variable name and EMF type, registers the implicit and any extra variables, and resolves DSL type identifiers
   * against the classpath. This replaces the legacy {@code CompilationContext.clone(...)} factories.
   *
   * @param implicitVariableName
   *          the Java variable name an unqualified {@code this} reference compiles to, must not be {@code null}
   * @param implicitType
   *          the EMF type of the implicit receiver, may be {@code null}
   * @param extraVariables
   *          extra in-scope variables as source-name to fully-qualified-type-name pairs, must not be {@code null}
   * @param sourceElement
   *          a model element used to resolve types against the classpath, must not be {@code null}
   * @return the compilation context, never {@code null}
   */
  def ExportTranslationContext newCompilationContext(String implicitVariableName, EClass implicitType,
    List<Pair<String, String>> extraVariables, EObject sourceElement) {
    val context = new ExportTranslationContext
    context.sourceElement = sourceElement
    context.implicitVariableName = implicitVariableName
    context.modelTypeResolver = ExportModelTypeResolver.forElement(sourceElement)
    if (implicitType !== null) {
      val parameter = newTrialParameter(implicitVariableName, implicitType.instanceClassName, sourceElement)
      context.implicitVariable = parameter
      context.putVariable(implicitVariableName, parameter)
    }
    for (extra : extraVariables) {
      context.putVariable(extra.key, newTrialParameter(extra.key, extra.value, sourceElement))
    }
    context.typeResolver = [Identifier identifier|findDeclaredType(identifier.id.join('.'), sourceElement)]
    context
  }

  /**
   * Fallback for expression types that are not (yet) supported by the translator.
   *
   * @param it
   *          the source expression, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return {@code null}, indicating the expression cannot be translated yet
   */
  def dispatch protected XExpression doTranslate(Expression it, ExportTranslationContext context) {
    null
  }

  /**
   * Translates a string literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XStringLiteral}, never {@code null}
   */
  def dispatch protected XExpression doTranslate(StringLiteral it, ExportTranslationContext context) {
    val literal = XbaseFactory.eINSTANCE.createXStringLiteral
    literal.value = getVal()
    literal
  }

  /**
   * Translates a boolean literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XBooleanLiteral}, never {@code null}
   */
  def dispatch protected XExpression doTranslate(BooleanLiteral it, ExportTranslationContext context) {
    val literal = XbaseFactory.eINSTANCE.createXBooleanLiteral
    literal.isTrue = 'true' == getVal()
    literal
  }

  /**
   * Translates an integer literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNumberLiteral}, never {@code null}
   */
  def dispatch protected XExpression doTranslate(IntegerLiteral it, ExportTranslationContext context) {
    val literal = XbaseFactory.eINSTANCE.createXNumberLiteral
    literal.value = Integer.toString(getVal())
    literal
  }

  /**
   * Translates a real (floating point) literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNumberLiteral}, never {@code null}
   */
  def dispatch protected XExpression doTranslate(RealLiteral it, ExportTranslationContext context) {
    val literal = XbaseFactory.eINSTANCE.createXNumberLiteral
    literal.value = getVal()
    literal
  }

  /**
   * Translates a {@code null} literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNullLiteral}, never {@code null}
   */
  def dispatch protected XExpression doTranslate(NullLiteral it, ExportTranslationContext context) {
    XbaseFactory.eINSTANCE.createXNullLiteral
  }

  /**
   * Translates a list literal. Each element is translated recursively; if any element cannot be translated
   * the whole list literal is considered untranslatable.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XListLiteral}, or {@code null} if an element cannot be translated
   */
  def dispatch protected XExpression doTranslate(ListLiteral it, ExportTranslationContext context) {
    val literal = XbaseFactory.eINSTANCE.createXListLiteral
    for (element : elements) {
      val translated = element.translate(context)
      if (translated === null) {
        return null
      }
      literal.elements += translated
    }
    literal
  }

  /**
   * Translates a conditional expression (ternary {@code ? :} or {@code if}/{@code then}/{@code else}). When the
   * source has no else branch a {@link org.eclipse.xtext.xbase.XNullLiteral} is used as the else value.
   *
   * @param it
   *          the source conditional, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XIfExpression}, or {@code null} if a branch cannot be translated
   */
  def dispatch protected XExpression doTranslate(IfExpression it, ExportTranslationContext context) {
    val xIf = XbaseFactory.eINSTANCE.createXIfExpression
    val xCondition = condition.translate(context)
    val xThen = thenPart.translate(context)
    if (xCondition === null || xThen === null) {
      return null
    }
    xIf.^if = xCondition
    xIf.then = xThen
    if (elsePart !== null) {
      val xElse = elsePart.translate(context)
      if (xElse === null) {
        return null
      }
      xIf.^else = xElse
    } else {
      xIf.^else = XbaseFactory.eINSTANCE.createXNullLiteral
    }
    xIf
  }

  /**
   * Translates a boolean, equality or relational operation into an {@link org.eclipse.xtext.xbase.XBinaryOperation}.
   * The operator is linked to the corresponding {@code org.eclipse.xtext.xbase.lib} operator method so that the
   * {@code XbaseCompiler} can emit the equivalent Java code. Operators without a backing library method (currently
   * {@code implies} and the relational comparisons) are left untranslated for now.
   *
   * @param it
   *          the source operation, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XBinaryOperation}, or {@code null} if it cannot (yet) be translated
   */
  def dispatch protected XExpression doTranslate(BooleanOperation it, ExportTranslationContext context) {
    val xLeft = left.translate(context)
    val xRight = right.translate(context)
    if (xLeft === null || xRight === null) {
      return null
    }
    val sourceElement = context.sourceElement
    switch operator {
      case '||': toBinaryOperation(xLeft, xRight, BooleanExtensions, 'operator_or', sourceElement)
      case '&&': toBinaryOperation(xLeft, xRight, BooleanExtensions, 'operator_and', sourceElement)
      // ==/!= are left untranslated so they fall back to the string compiler, which emits Java reference
      // equality (a == b) - matching the legacy generator; Xbase's operator_equals would emit value equality.
      default: null
    }
  }

  /**
   * Translates an operation call. In order, an {@code isInstance} check is mapped to an
   * {@link org.eclipse.xtext.xbase.XInstanceOfExpression}, a receiver or implicit method call (which also covers
   * {@code eContainer}/{@code eClass} style operations) to an {@link org.eclipse.xtext.xbase.XMemberFeatureCall}
   * linked to the resolved method, and finally an extension call to a {@code static} extension method.
   * Arithmetic, concatenation and prefix operator calls are added in later increments.
   *
   * @param it
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the resolved expression, or {@code null} if it cannot (yet) be translated
   */
  def dispatch protected XExpression doTranslate(OperationCall it, ExportTranslationContext context) {
    it.translateInstanceOf(context) ?: it.translateMethodCall(context) ?: it.translateExtensionCall(context)
  }

  /**
   * Translates a type cast ({@code (Type) target}) into an {@link org.eclipse.xtext.xbase.XCastedExpression}.
   *
   * @param it
   *          the source cast, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the casted expression, or {@code null} if the type or target cannot be translated
   */
  def dispatch protected XExpression doTranslate(CastedExpression it, ExportTranslationContext context) {
    val jvmType = context.resolveDslType(type)
    if (jvmType === null) {
      return null
    }
    val xTarget = target.translate(context)
    if (xTarget === null) {
      return null
    }
    val cast = XbaseFactory.eINSTANCE.createXCastedExpression
    cast.type = createTypeRef(jvmType)
    cast.target = xTarget
    cast
  }

  /**
   * Translates a {@code typeSelect(Type)} navigation into a {@code com.google.common.collect.Iterables.filter}
   * call that keeps the elements assignable to the given type, mirroring the legacy code generation.
   *
   * @param it
   *          the source type select, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the filter feature call, or {@code null} if the type, target or filter method cannot be resolved
   */
  def dispatch protected XExpression doTranslate(TypeSelectExpression it, ExportTranslationContext context) {
    val jvmType = context.resolveDslType(type)
    if (jvmType === null) {
      return null
    }
    val xTarget = target.translate(context)
    if (xTarget === null) {
      return null
    }
    val filter = findIterablesFilterByClass(context.sourceElement)
    if (filter === null) {
      return null
    }
    val call = XbaseFactory.eINSTANCE.createXFeatureCall
    call.feature = filter
    call.featureCallArguments += xTarget
    val typeLiteral = XbaseFactory.eINSTANCE.createXTypeLiteral
    typeLiteral.type = jvmType
    call.featureCallArguments += typeLiteral
    call
  }

  /**
   * Translates a feature call. The supported cases are an unqualified {@code this} reference (mapped to the
   * implicit variable), a single-segment identifier that matches a variable in scope, and a getter navigation
   * ({@code receiver.feature}) which is resolved to the matching {@code getX()}/{@code isX()} operation on the
   * receiver's JVM type. Type references and operation calls are added in later increments.
   *
   * @param it
   *          the source feature call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the resolved feature call, or {@code null} if it cannot (yet) be translated
   */
  def dispatch protected XExpression doTranslate(FeatureCall it, ExportTranslationContext context) {
    if (it.isThisReference) {
      return context.implicitVariable.toFeatureCall
    }
    if (target === null && name === null && type !== null && type.id.size == 1) {
      val parameter = context.getVariable(type.id.head)
      if (parameter !== null) {
        return parameter.toFeatureCall
      }
    }
    if (name === null && type !== null && type.id.size == 1) {
      return it.translateGetter(context)
    }
    null
  }

  /**
   * Tests whether the given feature call is an unqualified {@code this} reference.
   *
   * @param call
   *          the feature call, must not be {@code null}
   * @return {@code true} if the call refers to {@code this}
   */
  def private boolean isThisReference(FeatureCall call) {
    call.name === null && call.target === null && call.type !== null && call.type.id.size == 1 &&
      'this' == call.type.id.head
  }

  /**
   * Creates an {@link org.eclipse.xtext.xbase.XFeatureCall} that references the given formal parameter.
   *
   * @param parameter
   *          the formal parameter to reference, may be {@code null}
   * @return the feature call, or {@code null} if the parameter is {@code null}
   */
  def private XExpression toFeatureCall(JvmFormalParameter parameter) {
    if (parameter === null) {
      return null
    }
    val featureCall = XbaseFactory.eINSTANCE.createXFeatureCall
    featureCall.feature = parameter
    featureCall
  }

  /**
   * Translates a getter navigation ({@code receiver.feature}) into an {@link org.eclipse.xtext.xbase.XMemberFeatureCall}
   * linked to the resolved {@code getX()}/{@code isX()} operation. The receiver is the translated target, or the
   * implicit variable when there is no explicit target.
   *
   * @param it
   *          the source feature call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the member feature call, or {@code null} if the receiver type or getter cannot be resolved
   */
  def private XExpression translateGetter(FeatureCall it, ExportTranslationContext context) {
    val receiverType = resolveType(target, context)
    if (receiverType === null) {
      return null
    }
    val getter = findGetter(receiverType, type.id.head)
    if (getter === null) {
      return null
    }
    val receiver = if (target === null) context.implicitVariable.toFeatureCall else target.translate(context)
    if (receiver === null) {
      return null
    }
    val memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall
    memberCall.memberCallTarget = receiver
    memberCall.feature = getter
    memberCall
  }

  /**
   * Resolves the static JVM type of the given expression as far as needed to link getter navigations. Supported
   * expressions are the implicit variable (when {@code expression} is {@code null} or a {@code this} reference), a
   * variable in scope, and a getter navigation chain.
   *
   * @param expression
   *          the source expression whose type to resolve, may be {@code null} to denote the implicit variable
   * @param context
   *          the translation context, must not be {@code null}
   * @return the resolved JVM type, or {@code null} if it cannot be determined
   */
  def JvmType resolveType(Expression expression, ExportTranslationContext context) {
    if (expression === null) {
      return context.implicitVariable?.parameterType?.type
    }
    switch expression {
      CastedExpression:
        context.resolveDslType(expression.type)
      IntegerLiteral:
        findDeclaredType(Integer, expression)
      RealLiteral:
        findDeclaredType(Float, expression)
      StringLiteral:
        findDeclaredType(String, expression)
      TypeSelectExpression:
        findDeclaredType(Iterable, context.sourceElement)
      ListLiteral:
        findDeclaredType(List, context.sourceElement)
      OperationCall: {
        val receiverType = resolveType(expression.target, context)
        findMethod(receiverType, expression.name, expression.params.size)?.returnType?.type
      }
      FeatureCall: {
        if (expression.isThisReference) {
          context.implicitVariable?.parameterType?.type
        } else if (expression.target === null && expression.name === null && expression.type !== null &&
          expression.type.id.size == 1 && context.getVariable(expression.type.id.head) !== null) {
          context.getVariable(expression.type.id.head).parameterType?.type
        } else if (expression.name === null && expression.type !== null && expression.type.id.size == 1) {
          val receiverType = resolveType(expression.target, context)
          if (receiverType !== null) findGetter(receiverType, expression.type.id.head)?.returnType?.type else null
        } else {
          null
        }
      }
      default:
        null
    }
  }

  /**
   * Finds the no-argument getter operation for the given feature name on the given JVM type. The candidate method
   * names are the feature name itself (covering operations such as {@code eContainer} or {@code isEmpty}) as well as
   * the {@code getX} and {@code isX} accessor variants.
   *
   * @param type
   *          the receiver type, must not be {@code null}
   * @param feature
   *          the source feature name, must not be {@code null}
   * @return the matching getter operation, or {@code null} if none is found
   */
  def private JvmOperation findGetter(JvmType type, String feature) {
    if (type instanceof JvmDeclaredType) {
      val candidates = feature.getterCandidates
      return type.allFeatures.filter(JvmOperation).findFirst [
        parameters.empty && candidates.contains(simpleName)
      ]
    }
    null
  }

  /**
   * Computes the candidate getter method names for the given source feature name.
   *
   * @param feature
   *          the source feature name, must not be {@code null}
   * @return the list of candidate method names, never {@code null}
   */
  def private getterCandidates(String feature) {
    val name = if (feature.startsWith('^')) feature.substring(1) else feature
    val upper = name.toFirstUpper
    #[name, 'get' + upper, 'is' + upper]
  }

  /**
   * Translates an {@code isInstance} operation call ({@code Type.isInstance(value)}) into an
   * {@link org.eclipse.xtext.xbase.XInstanceOfExpression}.
   *
   * @param it
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the instance-of expression, or {@code null} if the call is not an {@code isInstance} type check or the
   *         type or value cannot be resolved
   */
  def private XExpression translateInstanceOf(OperationCall it, ExportTranslationContext context) {
    if (name != 'isInstance' || params.size != 1 || !(target instanceof FeatureCall)) {
      return null
    }
    val typeReference = target as FeatureCall
    if (typeReference.name !== null || typeReference.type === null) {
      return null
    }
    val jvmType = context.resolveDslType(typeReference.type)
    if (jvmType === null) {
      return null
    }
    val xValue = params.head.translate(context)
    if (xValue === null) {
      return null
    }
    val instanceOf = XbaseFactory.eINSTANCE.createXInstanceOfExpression
    instanceOf.expression = xValue
    instanceOf.type = createTypeRef(jvmType)
    instanceOf
  }

  /**
   * Translates a receiver or implicit method call ({@code receiver.method(args)} or {@code method(args)}) into an
   * {@link org.eclipse.xtext.xbase.XMemberFeatureCall} linked to the resolved method. The receiver is the translated
   * target, or the implicit variable when there is no explicit target.
   *
   * @param it
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the member feature call, or {@code null} if the receiver type, method or an argument cannot be resolved
   */
  def private XExpression translateMethodCall(OperationCall it, ExportTranslationContext context) {
    val receiverType = resolveType(target, context)
    if (receiverType === null) {
      return null
    }
    val operation = findMethod(receiverType, name, params.size)
    if (operation === null) {
      return null
    }
    val receiver = if (target === null) context.implicitVariable.toFeatureCall else target.translate(context)
    if (receiver === null) {
      return null
    }
    val memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall
    memberCall.memberCallTarget = receiver
    memberCall.feature = operation
    memberCall.explicitOperationCall = true
    for (param : params) {
      val xParam = param.translate(context)
      if (xParam === null) {
        return null
      }
      memberCall.memberCallArguments += xParam
    }
    memberCall
  }

  /**
   * Finds the method of the given name and parameter count on the given JVM type.
   *
   * @param type
   *          the receiver type, may be {@code null}
   * @param methodName
   *          the source method name, must not be {@code null}
   * @param parameterCount
   *          the number of arguments the call passes
   * @return the matching method, or {@code null} if none is found
   */
  def private JvmOperation findMethod(JvmType type, String methodName, int parameterCount) {
    if (type instanceof JvmDeclaredType) {
      return type.allFeatures.filter(JvmOperation).findFirst [
        simpleName == methodName && parameters.size == parameterCount
      ]
    }
    null
  }

  /**
   * Finds the {@code com.google.common.collect.Iterables.filter(Iterable, Class)} operation used to back a
   * {@code typeSelect} navigation.
   *
   * @param context
   *          the source element used to resolve the type against the classpath, may be {@code null}
   * @return the {@code filter} operation, or {@code null} if it cannot be resolved
   */
  def private JvmOperation findIterablesFilterByClass(EObject context) {
    if (context === null) {
      return null
    }
    val type = findDeclaredType(Iterables, context)
    if (type instanceof JvmDeclaredType) {
      return type.allFeatures.filter(JvmOperation).findFirst [
        isStatic && simpleName == 'filter' && parameters.size == 2 &&
          parameters.get(1).parameterType?.type?.simpleName == 'Class'
      ]
    }
    null
  }

  /**
   * Translates an extension operation call into an {@link org.eclipse.xtext.xbase.XFeatureCall} that invokes the
   * matching {@code static} extension method. The call's target (when present) is prepended to the declared
   * parameters to form the argument list.
   *
   * @param it
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the feature call, or {@code null} if no matching extension method exists or an argument cannot be translated
   */
  def private XExpression translateExtensionCall(OperationCall it, ExportTranslationContext context) {
    val argumentCount = (if (target !== null) 1 else 0) + params.size
    val operation = context.extensionClassNames.map [ className |
      findExtensionOperation(className, name, argumentCount, context.sourceElement)
    ].filterNull.head
    if (operation === null) {
      return null
    }
    val featureCall = XbaseFactory.eINSTANCE.createXFeatureCall
    featureCall.feature = operation
    if (target !== null) {
      val xTarget = target.translate(context)
      if (xTarget === null) {
        return null
      }
      featureCall.featureCallArguments += xTarget
    }
    for (param : params) {
      val xParam = param.translate(context)
      if (xParam === null) {
        return null
      }
      featureCall.featureCallArguments += xParam
    }
    featureCall
  }

  /**
   * Finds the {@code static} extension method of the given name and argument count on the given extension class.
   *
   * @param className
   *          the fully qualified name of the extension class, must not be {@code null}
   * @param operationName
   *          the source operation name, must not be {@code null}
   * @param argumentCount
   *          the number of arguments the call passes
   * @param context
   *          the source element used to resolve the type against the classpath, may be {@code null}
   * @return the matching {@code static} operation, or {@code null} if none is found
   */
  def private JvmOperation findExtensionOperation(String className, String operationName, int argumentCount,
    EObject context) {
    if (context === null) {
      return null
    }
    val type = findDeclaredType(className, context)
    if (type instanceof JvmDeclaredType) {
      return type.allFeatures.filter(JvmOperation).findFirst [
        isStatic && simpleName == operationName && parameters.size == argumentCount
      ]
    }
    null
  }

  /**
   * Builds an {@link org.eclipse.xtext.xbase.XBinaryOperation} linked to the given {@code org.eclipse.xtext.xbase.lib}
   * operator method.
   *
   * @param left
   *          the already translated left operand, must not be {@code null}
   * @param right
   *          the already translated right operand, must not be {@code null}
   * @param operatorClass
   *          the {@code org.eclipse.xtext.xbase.lib} class declaring the operator method, must not be {@code null}
   * @param operatorName
   *          the simple name of the operator method, must not be {@code null}
   * @param context
   *          the source element used to resolve the operator type, may be {@code null}
   * @return the binary operation, or {@code null} if the operator method cannot be resolved
   */
  def private XExpression toBinaryOperation(XExpression left, XExpression right, Class<?> operatorClass,
    String operatorName, EObject context) {
    val operation = findOperator(operatorClass, operatorName, context)
    if (operation === null) {
      return null
    }
    val binaryOperation = XbaseFactory.eINSTANCE.createXBinaryOperation
    binaryOperation.leftOperand = left
    binaryOperation.rightOperand = right
    binaryOperation.feature = operation
    binaryOperation
  }

  /**
   * Resolves the two-argument operator method of the given name on the given {@code org.eclipse.xtext.xbase.lib} class.
   *
   * @param operatorClass
   *          the class declaring the operator method, must not be {@code null}
   * @param operatorName
   *          the simple name of the operator method, must not be {@code null}
   * @param context
   *          the source element used to resolve the type against the classpath, may be {@code null}
   * @return the operator {@link JvmOperation}, or {@code null} if it cannot be resolved
   */
  def private JvmOperation findOperator(Class<?> operatorClass, String operatorName, EObject context) {
    if (context === null) {
      return null
    }
    val type = findDeclaredType(operatorClass, context)
    if (type instanceof JvmDeclaredType) {
      return type.allFeatures.filter(JvmOperation).findFirst [
        simpleName == operatorName && parameters.size == 2
      ]
    }
    null
  }

}
