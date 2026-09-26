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
package com.avaloq.tools.ddk.xtext.export.jvmmodel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBooleanLiteral;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.export.generator.ExportModelTypeResolver;
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation;
import com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * Translates the custom {@link Expression} AST of the export expression DSL into equivalent Xbase
 * {@link XExpression} trees that can be compiled by the {@code XbaseCompiler} through the JVM model inferrer.
 * <p>
 * Translation happens against an {@link ExportTranslationContext} that provides the variables in scope and the
 * implicit ({@code this}) variable. Nodes that require resolving forms that are not yet supported return
 * {@code null}; the {@link ExportExpressionCompiler} is used as the string fallback that the export generators
 * splice into the inferred provider method bodies.
 */
@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ExportExpressionTranslator {

  /** Separator between the segments of a qualified type name. */
  private static final String SEGMENT_SEPARATOR = ".";

  /** Provides access to the {@code org.eclipse.xtext.xbase.lib} operator methods that back binary operations. */
  @Inject
  private TypeReferences typeReferences;

  /**
   * Resolves an EMF type's Java instance class name. Called directly (rather than as {@code .instanceClassName})
   * because Xtend resolves the latter to {@link org.eclipse.emf.ecore.EClass#getInstanceClassName() EMF's accessor},
   * which returns {@code null} for EClasses whose Ecore model does not literally set the field; GenModelUtilX
   * falls back to the GenClass-derived qualified interface name.
   */
  @Inject
  private GenModelUtilX genModelUtil;

  /**
   * Translates the given expression into an equivalent {@link XExpression}.
   *
   * @param expression
   *          the source expression, may be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the translated {@link XExpression}, or {@code null} if the expression cannot (yet) be translated
   */
  public XExpression translate(final Expression expression, final ExportTranslationContext context) {
    if (expression == null) {
      return null;
    }
    return doTranslate(expression, context);
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
  public String resolveFactoryMethod(final Expression expression, final EObject sourceElement) {
    if (!(expression instanceof OperationCall call)) {
      return null;
    }
    if (!(call.getTarget() instanceof FeatureCall typeReference)) {
      return null;
    }
    if (typeReference.getName() != null || typeReference.getType() == null) {
      return null;
    }
    final JvmType jvmType = typeReferences.findDeclaredType(String.join(SEGMENT_SEPARATOR, typeReference.getType().getId()), sourceElement);
    if (jvmType instanceof JvmDeclaredType declaredType) {
      return declaredType.getQualifiedName() + "." + call.getName();
    }
    return null;
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
  public boolean canExtractAsValue(final Expression expression, final EClass contextType, final EObject sourceElement) {
    final ExportTranslationContext context = newTrialContext(contextType, sourceElement);
    return translate(expression, context) != null && resolveType(expression, context) != null;
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
  public boolean canExtractAsString(final Expression expression, final EClass contextType, final EObject sourceElement) {
    final ExportTranslationContext context = newTrialContext(contextType, sourceElement);
    if (translate(expression, context) == null) {
      return false;
    }
    final JvmType resolved = resolveType(expression, context);
    return resolved != null && Objects.equals(resolved.getQualifiedName(), String.class.getName());
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
  public boolean canExtractAsValue(final Expression expression, final EClass contextType, final List<Pair<String, String>> extraVariables,
      final EObject sourceElement) {
    final ExportTranslationContext context = newTrialContext(contextType, sourceElement);
    for (final Pair<String, String> extra : extraVariables) {
      context.putVariable(extra.getKey(), newTrialParameter(extra.getKey(), extra.getValue(), sourceElement));
    }
    return translate(expression, context) != null && resolveType(expression, context) != null;
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
  private ExportTranslationContext newTrialContext(final EClass contextType, final EObject sourceElement) {
    final JvmFormalParameter parameter = newTrialParameter("ctx", genModelUtil.instanceClassName(contextType), sourceElement);
    final ExportTranslationContext context = new ExportTranslationContext();
    context.setSourceElement(sourceElement);
    context.putVariable("ctx", parameter);
    context.setImplicitVariable(parameter);
    context.setTypeResolver((final Identifier identifier) -> typeReferences.findDeclaredType(String.join(SEGMENT_SEPARATOR, identifier.getId()), sourceElement));
    initExtensionClassNames(context, sourceElement);
    return context;
  }

  /**
   * Registers the extension classes declared through {@code extension a::b::C} by the export model containing the
   * given source element, so that calls to their {@code static} methods can be linked. Without this the extension
   * calls would neither be translatable to Xbase nor be qualified by the fallback
   * {@link ExportExpressionCompiler}.
   *
   * @param context
   *          the translation context to populate, must not be {@code null}
   * @param sourceElement
   *          the model element the translated expression originates from, must not be {@code null}
   */
  private void initExtensionClassNames(final ExportTranslationContext context, final EObject sourceElement) {
    final ExportModel model = EcoreUtil2.getContainerOfType(sourceElement, ExportModel.class);
    if (model != null) {
      for (final Extension declaration : model.getExtensions()) {
        context.addExtensionClassName(toJavaClassName(declaration.getExtension()));
      }
    }
  }

  /**
   * Converts a {@code ::} delimited qualified extension ID into the corresponding fully qualified Java class name.
   *
   * @param it
   *          the qualified extension ID, must not be {@code null}
   * @return the fully qualified Java class name, never {@code null}
   */
  private String toJavaClassName(final String it) {
    return it.replace("::", ".").replace("^", "");
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
  private JvmFormalParameter newTrialParameter(final String name, final String typeName, final EObject sourceElement) {
    final JvmFormalParameter parameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
    parameter.setName(name);
    final JvmType jvmType = typeReferences.findDeclaredType(typeName, sourceElement);
    if (jvmType != null) {
      parameter.setParameterType(typeReferences.createTypeRef(jvmType));
    }
    return parameter;
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
  public ExportTranslationContext newCompilationContext(final String implicitVariableName, final EClass implicitType,
      final List<Pair<String, String>> extraVariables, final EObject sourceElement) {
    final ExportTranslationContext context = new ExportTranslationContext();
    context.setSourceElement(sourceElement);
    context.setImplicitVariableName(implicitVariableName);
    context.setModelTypeResolver(ExportModelTypeResolver.forElement(sourceElement));
    if (implicitType != null) {
      final JvmFormalParameter parameter = newTrialParameter(implicitVariableName, genModelUtil.instanceClassName(implicitType), sourceElement);
      context.setImplicitVariable(parameter);
      context.putVariable(implicitVariableName, parameter);
    }
    for (final Pair<String, String> extra : extraVariables) {
      context.putVariable(extra.getKey(), newTrialParameter(extra.getKey(), extra.getValue(), sourceElement));
    }
    context.setTypeResolver((final Identifier identifier) -> typeReferences.findDeclaredType(String.join(SEGMENT_SEPARATOR, identifier.getId()), sourceElement));
    initExtensionClassNames(context, sourceElement);
    return context;
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
  protected XExpression _doTranslate(final Expression it, final ExportTranslationContext context) {
    return null;
  }

  /**
   * Translates a string literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XStringLiteral}, never {@code null}
   */
  protected XExpression _doTranslate(final StringLiteral it, final ExportTranslationContext context) {
    final XStringLiteral literal = XbaseFactory.eINSTANCE.createXStringLiteral();
    literal.setValue(it.getVal());
    return literal;
  }

  /**
   * Translates a boolean literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XBooleanLiteral}, never {@code null}
   */
  protected XExpression _doTranslate(final BooleanLiteral it, final ExportTranslationContext context) {
    final XBooleanLiteral literal = XbaseFactory.eINSTANCE.createXBooleanLiteral();
    literal.setIsTrue(Objects.equals("true", it.getVal()));
    return literal;
  }

  /**
   * Translates an integer literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNumberLiteral}, never {@code null}
   */
  protected XExpression _doTranslate(final IntegerLiteral it, final ExportTranslationContext context) {
    final XNumberLiteral literal = XbaseFactory.eINSTANCE.createXNumberLiteral();
    literal.setValue(Integer.toString(it.getVal()));
    return literal;
  }

  /**
   * Translates a real (floating point) literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNumberLiteral}, never {@code null}
   */
  protected XExpression _doTranslate(final RealLiteral it, final ExportTranslationContext context) {
    final XNumberLiteral literal = XbaseFactory.eINSTANCE.createXNumberLiteral();
    literal.setValue(it.getVal());
    return literal;
  }

  /**
   * Translates a {@code null} literal.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XNullLiteral}, never {@code null}
   */
  protected XExpression _doTranslate(final NullLiteral it, final ExportTranslationContext context) {
    return XbaseFactory.eINSTANCE.createXNullLiteral();
  }

  /**
   * Translates a list literal. Each element is translated recursively; if any element cannot be translated
   * the whole list literal is considered untranslatable.
   *
   * @param it
   *          the source literal, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XListLiteral}, or {@code null} if an element cannot be translated
   */
  protected XExpression _doTranslate(final ListLiteral it, final ExportTranslationContext context) {
    final XListLiteral literal = XbaseFactory.eINSTANCE.createXListLiteral();
    for (final Expression element : it.getElements()) {
      final XExpression translated = translate(element, context);
      if (translated == null) {
        return null;
      }
      literal.getElements().add(translated);
    }
    return literal;
  }

  /**
   * Translates a conditional expression (ternary {@code ? :} or {@code if}/{@code then}/{@code else}). When the
   * source has no else branch a {@link org.eclipse.xtext.xbase.XNullLiteral} is used as the else value.
   *
   * @param it
   *          the source conditional, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the {@link org.eclipse.xtext.xbase.XIfExpression}, or {@code null} if a branch cannot be translated
   */
  protected XExpression _doTranslate(final IfExpression it, final ExportTranslationContext context) {
    final XIfExpression xIf = XbaseFactory.eINSTANCE.createXIfExpression();
    final XExpression xCondition = translate(it.getCondition(), context);
    final XExpression xThen = translate(it.getThenPart(), context);
    if (xCondition == null || xThen == null) {
      return null;
    }
    xIf.setIf(xCondition);
    xIf.setThen(xThen);
    if (it.getElsePart() != null) {
      final XExpression xElse = translate(it.getElsePart(), context);
      if (xElse == null) {
        return null;
      }
      xIf.setElse(xElse);
    } else {
      xIf.setElse(XbaseFactory.eINSTANCE.createXNullLiteral());
    }
    return xIf;
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
  protected XExpression _doTranslate(final BooleanOperation it, final ExportTranslationContext context) {
    final XExpression xLeft = translate(it.getLeft(), context);
    final XExpression xRight = translate(it.getRight(), context);
    if (xLeft == null || xRight == null) {
      return null;
    }
    final String operator = it.getOperator();
    if (operator == null) {
      return null;
    }
    final EObject sourceElement = context.getSourceElement();
    return switch (operator) {
      case "||" -> toBinaryOperation(xLeft, xRight, BooleanExtensions.class, "operator_or", sourceElement);
      case "&&" -> toBinaryOperation(xLeft, xRight, BooleanExtensions.class, "operator_and", sourceElement);
      // Xbase equality is value-based, unlike the identity equality emitted by the legacy Java generator.
      case "==", "!=" -> null;
      default -> null;
    };
  }

  /**
   * Translates an operation call. In order, an {@code isInstance} check is mapped to an
   * {@link org.eclipse.xtext.xbase.XInstanceOfExpression}, then either a receiver method call or an extension call
   * is attempted. A call written without an explicit receiver ({@code foo(...)} or {@code this.foo(...)}) resolves
   * against the implicit receiver's own operations first; a call on an explicit receiver resolves against the
   * declared extension classes first, mirroring the legacy compiler which gave {@code JAVA} extensions precedence
   * over the receiver's own operations. Extension candidates are matched on their parameter types, so an extension
   * that merely shares its name and arity with a real operation of the receiver does not shadow it.
   *
   * @param it
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the resolved expression, or {@code null} if it cannot (yet) be translated
   */
  protected XExpression _doTranslate(final OperationCall it, final ExportTranslationContext context) {
    if (isImplicitReceiverCall(it)) {
      final XExpression instanceOf = translateInstanceOf(it, context);
      if (instanceOf != null) {
        return instanceOf;
      }
      final XExpression methodCall = translateMethodCall(it, context);
      return methodCall != null ? methodCall : translateExtensionCall(it, context);
    }
    final XExpression instanceOf = translateInstanceOf(it, context);
    if (instanceOf != null) {
      return instanceOf;
    }
    final XExpression extensionCall = translateExtensionCall(it, context);
    return extensionCall != null ? extensionCall : translateMethodCall(it, context);
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
  protected XExpression _doTranslate(final CastedExpression it, final ExportTranslationContext context) {
    final JvmType jvmType = context.resolveDslType(it.getType());
    if (jvmType == null) {
      return null;
    }
    final XExpression xTarget = translate(it.getTarget(), context);
    if (xTarget == null) {
      return null;
    }
    final XCastedExpression cast = XbaseFactory.eINSTANCE.createXCastedExpression();
    cast.setType(typeReferences.createTypeRef(jvmType));
    cast.setTarget(xTarget);
    return cast;
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
  protected XExpression _doTranslate(final TypeSelectExpression it, final ExportTranslationContext context) {
    final JvmType jvmType = context.resolveDslType(it.getType());
    if (jvmType == null) {
      return null;
    }
    final XExpression xTarget = translate(it.getTarget(), context);
    if (xTarget == null) {
      return null;
    }
    final JvmOperation filter = findIterablesFilterByClass(context.getSourceElement());
    if (filter == null) {
      return null;
    }
    final XFeatureCall call = XbaseFactory.eINSTANCE.createXFeatureCall();
    call.setFeature(filter);
    call.getFeatureCallArguments().add(xTarget);
    final XTypeLiteral typeLiteral = XbaseFactory.eINSTANCE.createXTypeLiteral();
    typeLiteral.setType(jvmType);
    call.getFeatureCallArguments().add(typeLiteral);
    return call;
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
  protected XExpression _doTranslate(final FeatureCall it, final ExportTranslationContext context) {
    if (isThisReference(it)) {
      return toFeatureCall(context.getImplicitVariable());
    }
    if (it.getTarget() == null && it.getName() == null && it.getType() != null && it.getType().getId().size() == 1) {
      final JvmFormalParameter parameter = context.getVariable(it.getType().getId().get(0));
      if (parameter != null) {
        return toFeatureCall(parameter);
      }
    }
    if (it.getName() == null && it.getType() != null && it.getType().getId().size() == 1) {
      return translateGetter(it, context);
    }
    return null;
  }

  /**
   * Tests whether the given feature call is an unqualified {@code this} reference.
   *
   * @param call
   *          the feature call, must not be {@code null}
   * @return {@code true} if the call refers to {@code this}
   */
  private boolean isThisReference(final FeatureCall call) {
    if (call.getName() != null || call.getTarget() != null || call.getType() == null) {
      return false;
    }
    return call.getType().getId().size() == 1 && Objects.equals("this", call.getType().getId().get(0));
  }

  /**
   * Creates an {@link org.eclipse.xtext.xbase.XFeatureCall} that references the given formal parameter.
   *
   * @param parameter
   *          the formal parameter to reference, may be {@code null}
   * @return the feature call, or {@code null} if the parameter is {@code null}
   */
  private XExpression toFeatureCall(final JvmFormalParameter parameter) {
    if (parameter == null) {
      return null;
    }
    final XFeatureCall featureCall = XbaseFactory.eINSTANCE.createXFeatureCall();
    featureCall.setFeature(parameter);
    return featureCall;
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
  private XExpression translateGetter(final FeatureCall it, final ExportTranslationContext context) {
    final JvmType receiverType = resolveType(it.getTarget(), context);
    if (receiverType == null) {
      return null;
    }
    final JvmOperation getter = findGetter(receiverType, head(it.getType().getId()));
    if (getter == null) {
      return null;
    }
    final XExpression receiver = it.getTarget() == null ? toFeatureCall(context.getImplicitVariable()) : translate(it.getTarget(), context);
    if (receiver == null) {
      return null;
    }
    final XMemberFeatureCall memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
    memberCall.setMemberCallTarget(receiver);
    memberCall.setFeature(getter);
    return memberCall;
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
  public JvmType resolveType(final Expression expression, final ExportTranslationContext context) {
    if (expression == null) {
      return typeOf(context.getImplicitVariable());
    }
    if (expression instanceof CastedExpression castedExpression) {
      return context.resolveDslType(castedExpression.getType());
    } else if (expression instanceof IntegerLiteral) {
      return typeReferences.findDeclaredType(Integer.class, expression);
    } else if (expression instanceof RealLiteral) {
      return typeReferences.findDeclaredType(Float.class, expression);
    } else if (expression instanceof StringLiteral) {
      return typeReferences.findDeclaredType(String.class, expression);
    } else if (expression instanceof TypeSelectExpression) {
      return typeReferences.findDeclaredType(Iterable.class, context.getSourceElement());
    } else if (expression instanceof ListLiteral) {
      return typeReferences.findDeclaredType(List.class, context.getSourceElement());
    } else if (expression instanceof OperationCall operationCall) {
      // Resolve with the same precedence as doTranslate: an explicit receiver lets the declared extensions win,
      // an implicit ("this") receiver resolves against its own operations first.
      final JvmType receiverType = resolveType(operationCall.getTarget(), context);
      final JvmOperation method = findMethod(receiverType, operationCall.getName(), operationCall.getParams().size());
      if (isImplicitReceiverCall(operationCall) && method != null) {
        return returnTypeOf(method);
      }
      final JvmType extensionReturnType = returnTypeOf(findExtensionOperation(operationCall, context));
      return extensionReturnType != null ? extensionReturnType : returnTypeOf(method);
    } else if (expression instanceof FeatureCall featureCall) {
      return resolveFeatureCallType(featureCall, context);
    } else {
      return null;
    }
  }

  /**
   * Resolves the static JVM type of a feature call.
   *
   * @param expression
   *          the source feature call, must not be {@code null}
   * @param context
   *          the translation context, must not be {@code null}
   * @return the resolved JVM type, or {@code null} if it cannot be determined
   */
  private JvmType resolveFeatureCallType(final FeatureCall expression, final ExportTranslationContext context) {
    if (isThisReference(expression)) {
      return typeOf(context.getImplicitVariable());
    }
    if (expression.getName() != null || expression.getType() == null || expression.getType().getId().size() != 1) {
      return null;
    }
    if (expression.getTarget() == null && context.getVariable(expression.getType().getId().get(0)) != null) {
      final JvmTypeReference parameterType = context.getVariable(expression.getType().getId().get(0)).getParameterType();
      return parameterType != null ? parameterType.getType() : null;
    }
    final JvmType receiverType = resolveType(expression.getTarget(), context);
    return receiverType != null ? returnTypeOf(findGetter(receiverType, head(expression.getType().getId()))) : null;
  }

  /**
   * Returns the declared type of the given formal parameter.
   *
   * @param parameter
   *          the formal parameter, may be {@code null}
   * @return the parameter type, or {@code null} if the parameter or its type reference is {@code null}
   */
  private JvmType typeOf(final JvmFormalParameter parameter) {
    final JvmTypeReference parameterType = parameter != null ? parameter.getParameterType() : null;
    return parameterType != null ? parameterType.getType() : null;
  }

  /**
   * Returns the declared return type of the given operation.
   *
   * @param operation
   *          the operation, may be {@code null}
   * @return the return type, or {@code null} if the operation or its return type reference is {@code null}
   */
  private JvmType returnTypeOf(final JvmOperation operation) {
    final JvmTypeReference returnType = operation != null ? operation.getReturnType() : null;
    return returnType != null ? returnType.getType() : null;
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
  private JvmOperation findGetter(final JvmType type, final String feature) {
    if (type instanceof JvmDeclaredType declaredType) {
      final List<String> candidates = getterCandidates(feature);
      for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
        if (operation.getParameters().isEmpty() && candidates.contains(operation.getSimpleName())) {
          return operation;
        }
      }
      return null;
    }
    return null;
  }

  /**
   * Computes the candidate getter method names for the given source feature name.
   *
   * @param feature
   *          the source feature name, must not be {@code null}
   * @return the list of candidate method names, never {@code null}
   */
  private List<String> getterCandidates(final String feature) {
    final String name = feature != null && feature.startsWith("^") ? feature.substring(1) : feature;
    final String upper = toFirstUpper(name);
    return Arrays.asList(name, "get" + upper, "is" + upper);
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
  private XExpression translateInstanceOf(final OperationCall it, final ExportTranslationContext context) {
    if (!Objects.equals(it.getName(), "isInstance") || it.getParams().size() != 1 || !(it.getTarget() instanceof FeatureCall typeReference)) {
      return null;
    }
    if (typeReference.getName() != null || typeReference.getType() == null) {
      return null;
    }
    final JvmType jvmType = context.resolveDslType(typeReference.getType());
    if (jvmType == null) {
      return null;
    }
    final XExpression xValue = translate(head(it.getParams()), context);
    if (xValue == null) {
      return null;
    }
    final XInstanceOfExpression instanceOf = XbaseFactory.eINSTANCE.createXInstanceOfExpression();
    instanceOf.setExpression(xValue);
    instanceOf.setType(typeReferences.createTypeRef(jvmType));
    return instanceOf;
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
  private XExpression translateMethodCall(final OperationCall it, final ExportTranslationContext context) {
    final JvmType receiverType = resolveType(it.getTarget(), context);
    if (receiverType == null) {
      return null;
    }
    final JvmOperation operation = findMethod(receiverType, it.getName(), it.getParams().size());
    if (operation == null) {
      return null;
    }
    final XExpression receiver = it.getTarget() == null ? toFeatureCall(context.getImplicitVariable()) : translate(it.getTarget(), context);
    if (receiver == null) {
      return null;
    }
    final XMemberFeatureCall memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
    memberCall.setMemberCallTarget(receiver);
    memberCall.setFeature(operation);
    memberCall.setExplicitOperationCall(true);
    for (final Expression param : it.getParams()) {
      final XExpression xParam = translate(param, context);
      if (xParam == null) {
        return null;
      }
      memberCall.getMemberCallArguments().add(xParam);
    }
    return memberCall;
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
  private JvmOperation findMethod(final JvmType type, final String methodName, final int parameterCount) {
    if (type instanceof JvmDeclaredType declaredType) {
      for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
        if (Objects.equals(operation.getSimpleName(), methodName) && operation.getParameters().size() == parameterCount) {
          return operation;
        }
      }
      return null;
    }
    return null;
  }

  /**
   * Finds the {@code com.google.common.collect.Iterables.filter(Iterable, Class)} operation used to back a
   * {@code typeSelect} navigation.
   *
   * @param context
   *          the source element used to resolve the type against the classpath, may be {@code null}
   * @return the {@code filter} operation, or {@code null} if it cannot be resolved
   */
  private JvmOperation findIterablesFilterByClass(final EObject context) {
    if (context == null) {
      return null;
    }
    final JvmType type = typeReferences.findDeclaredType(Iterables.class, context);
    if (type instanceof JvmDeclaredType declaredType) {
      for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
        if (operation.isStatic() && Objects.equals(operation.getSimpleName(), "filter") && operation.getParameters().size() == 2
            && Objects.equals(simpleNameOf(operation.getParameters().get(1).getParameterType()), "Class")) {
          return operation;
        }
      }
      return null;
    }
    return null;
  }

  /**
   * Returns the simple name of the type the given type reference points to.
   *
   * @param typeReference
   *          the type reference, may be {@code null}
   * @return the simple name, or {@code null} if the reference or its type is {@code null}
   */
  private String simpleNameOf(final JvmTypeReference typeReference) {
    final JvmType type = typeReference != null ? typeReference.getType() : null;
    return type != null ? type.getSimpleName() : null;
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
  private XExpression translateExtensionCall(final OperationCall it, final ExportTranslationContext context) {
    final JvmOperation operation = findExtensionOperation(it, context);
    if (operation == null) {
      return null;
    }
    final XFeatureCall featureCall = XbaseFactory.eINSTANCE.createXFeatureCall();
    featureCall.setFeature(operation);
    if (it.getTarget() != null) {
      final XExpression xTarget = translate(it.getTarget(), context);
      if (xTarget == null) {
        return null;
      }
      featureCall.getFeatureCallArguments().add(xTarget);
    }
    for (final Expression param : it.getParams()) {
      final XExpression xParam = translate(param, context);
      if (xParam == null) {
        return null;
      }
      featureCall.getFeatureCallArguments().add(xParam);
    }
    return featureCall;
  }

  /**
   * Tests whether the given operation call has no explicit receiver, i.e. it is written unqualified or on
   * {@code this}. Such calls resolve against the implicit receiver's own operations before any extension is
   * considered, mirroring the legacy compiler.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @return {@code true} if the call has no explicit receiver
   */
  public boolean isImplicitReceiverCall(final OperationCall it) {
    return it.getTarget() == null || it.getTarget() instanceof FeatureCall featureCall && isThisReference(featureCall);
  }

  /**
   * Finds the {@code static} extension method matching the given call on the given extension class.
   * <p>
   * As in the legacy generator, candidates are selected by name and argument count. In addition, when the call has
   * an explicit receiver whose type could be resolved, the first parameter must be related to that type. That extra
   * condition is what keeps an extension which merely shares its name and arity with a real operation of the
   * receiver from shadowing it.
   *
   * @param className
   *          the fully qualified name of the extension class, must not be {@code null}
   * @param call
   *          the source operation call, must not be {@code null}
   * @param receiverType
   *          the resolved type of the call's receiver, or {@code null} if there is none or it is unknown
   * @param context
   *          the source element used to resolve the type against the classpath, may be {@code null}
   * @return the matching {@code static} operation, or {@code null} if none is found
   */
  private JvmOperation findExtensionOperation(final String className, final OperationCall call, final JvmType receiverType,
      final EObject context) {
    if (context == null) {
      return null;
    }
    final String operationName = call.getName();
    final int argumentCount = (call.getTarget() != null ? 1 : 0) + call.getParams().size();
    final JvmType type = typeReferences.findDeclaredType(className, context);
    if (type instanceof JvmDeclaredType declaredType) {
      for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
        if (operation.isStatic() && Objects.equals(operation.getSimpleName(), operationName)
            && operation.getParameters().size() == argumentCount
            && isCompatible(typeOf(head(operation.getParameters())), receiverType)) {
          return operation;
        }
      }
      return null;
    }
    return null;
  }

  /**
   * Tests whether a value of the given argument type can be passed for a parameter of the given type. Unresolved
   * types and non declared (primitive, array, type parameter) types are accepted, as the translator cannot reason
   * about them. Both directions are accepted: the resolved receiver type of an export expression is often only an
   * approximation (a super type of what the extension declares, or vice versa), and the check only has to reject
   * clearly unrelated types.
   *
   * @param parameterType
   *          the declared parameter type, may be {@code null}
   * @param argumentType
   *          the resolved argument type, may be {@code null}
   * @return {@code true} if the argument is compatible with the parameter
   */
  private boolean isCompatible(final JvmType parameterType, final JvmType argumentType) {
    if (parameterType == null || argumentType == null || Objects.equals(parameterType.getQualifiedName(), Object.class.getName())
        || Objects.equals(argumentType.getQualifiedName(), Object.class.getName())) {
      return true;
    }
    if (Objects.equals(parameterType.getQualifiedName(), argumentType.getQualifiedName())) {
      return true;
    }
    if (argumentType instanceof JvmDeclaredType declaredArgumentType
        && isSubtypeOf(declaredArgumentType, parameterType.getQualifiedName(), new HashSet<>())) {
      return true;
    }
    if (parameterType instanceof JvmDeclaredType declaredParameterType) {
      return isSubtypeOf(declaredParameterType, argumentType.getQualifiedName(), new HashSet<>());
    }
    return true;
  }

  /**
   * Tests whether the given type has a (transitive) super type with the given qualified name.
   *
   * @param it
   *          the type to test, must not be {@code null}
   * @param qualifiedTypeName
   *          the qualified name of the super type to look for, must not be {@code null}
   * @param visited
   *          the qualified names already visited, guarding against cycles, must not be {@code null}
   * @return {@code true} if the type is a subtype of the named type
   */
  private boolean isSubtypeOf(final JvmDeclaredType it, final String qualifiedTypeName, final Set<String> visited) {
    if (!visited.add(it.getQualifiedName())) {
      return false;
    }
    for (final JvmTypeReference superTypeReference : it.getSuperTypes()) {
      final JvmType superType = superTypeReference != null ? superTypeReference.getType() : null;
      if (superType != null && Objects.equals(superType.getQualifiedName(), qualifiedTypeName)) {
        return true;
      }
      if (superType instanceof JvmDeclaredType declaredSuperType && isSubtypeOf(declaredSuperType, qualifiedTypeName, visited)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Finds the {@code static} extension method matching the given call on the extension classes declared by the
   * export model the expression originates from.
   *
   * @param call
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context holding the declared extension classes, must not be {@code null}
   * @return the matching {@code static} operation, or {@code null} if no declared extension class provides one
   */
  public JvmOperation findExtensionOperation(final OperationCall call, final ExportTranslationContext context) {
    final JvmType receiverType = call.getTarget() == null ? null : resolveType(call.getTarget(), context);
    for (final String className : context.getExtensionClassNames()) {
      final JvmOperation operation = findExtensionOperation(className, call, receiverType, context.getSourceElement());
      if (operation != null) {
        return operation;
      }
    }
    return null;
  }

  /**
   * Returns the fully qualified name of the registered extension class declaring a {@code static} method matching
   * the given call. Used by the fallback {@link ExportExpressionCompiler} to qualify extension calls that are
   * rendered as Java source text rather than translated to Xbase.
   *
   * @param call
   *          the source operation call, must not be {@code null}
   * @param context
   *          the translation context holding the declared extension classes, must not be {@code null}
   * @return the fully qualified extension class name, or {@code null} if no declared extension class matches
   */
  public String findExtensionClassName(final OperationCall call, final ExportTranslationContext context) {
    final JvmType receiverType = call.getTarget() == null ? null : resolveType(call.getTarget(), context);
    for (final String className : context.getExtensionClassNames()) {
      if (findExtensionOperation(className, call, receiverType, context.getSourceElement()) != null) {
        return className;
      }
    }
    return null;
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
  private XExpression toBinaryOperation(final XExpression left, final XExpression right, final Class<?> operatorClass,
      final String operatorName, final EObject context) {
    final JvmOperation operation = findOperator(operatorClass, operatorName, context);
    if (operation == null) {
      return null;
    }
    final XBinaryOperation binaryOperation = XbaseFactory.eINSTANCE.createXBinaryOperation();
    binaryOperation.setLeftOperand(left);
    binaryOperation.setRightOperand(right);
    binaryOperation.setFeature(operation);
    return binaryOperation;
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
  private JvmOperation findOperator(final Class<?> operatorClass, final String operatorName, final EObject context) {
    if (context == null) {
      return null;
    }
    final JvmType type = typeReferences.findDeclaredType(operatorClass, context);
    if (type instanceof JvmDeclaredType declaredType) {
      for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
        if (Objects.equals(operation.getSimpleName(), operatorName) && operation.getParameters().size() == 2) {
          return operation;
        }
      }
      return null;
    }
    return null;
  }

  /**
   * Returns the first element of the given list, or {@code null} if it is empty.
   *
   * @param <T>
   *          the element type
   * @param elements
   *          the list, must not be {@code null}
   * @return the first element, or {@code null} if the list is empty
   */
  private static <T> T head(final List<T> elements) {
    return elements.isEmpty() ? null : elements.get(0);
  }

  private static String toFirstUpper(final String value) {
    return value == null || value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }

  //////////////////////////////////////////////////
  // DISPATCHERS
  //////////////////////////////////////////////////
  protected XExpression doTranslate(final Expression it, final ExportTranslationContext context) {
    if (it instanceof BooleanLiteral booleanLiteral) {
      return _doTranslate(booleanLiteral, context);
    } else if (it instanceof IntegerLiteral integerLiteral) {
      return _doTranslate(integerLiteral, context);
    } else if (it instanceof NullLiteral nullLiteral) {
      return _doTranslate(nullLiteral, context);
    } else if (it instanceof OperationCall operationCall) {
      return _doTranslate(operationCall, context);
    } else if (it instanceof RealLiteral realLiteral) {
      return _doTranslate(realLiteral, context);
    } else if (it instanceof StringLiteral stringLiteral) {
      return _doTranslate(stringLiteral, context);
    } else if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _doTranslate(typeSelectExpression, context);
    } else if (it instanceof BooleanOperation booleanOperation) {
      return _doTranslate(booleanOperation, context);
    } else if (it instanceof CastedExpression castedExpression) {
      return _doTranslate(castedExpression, context);
    } else if (it instanceof FeatureCall featureCall) {
      return _doTranslate(featureCall, context);
    } else if (it instanceof IfExpression ifExpression) {
      return _doTranslate(ifExpression, context);
    } else if (it instanceof ListLiteral listLiteral) {
      return _doTranslate(listLiteral, context);
    } else if (it != null) {
      return _doTranslate(it, context);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, context));
    }
  }

}
