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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.xtext.export.generator.ExportModelTypeResolver;
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation;
import com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.Literal;
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.SyntaxElement;
import com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

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
@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ExportExpressionCompiler {

  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are Java source fragments emitted by this compiler, not nameable constants

  /** Maps EMF model types to their generated Java instance class names. */
  @Inject
  private GenModelUtilX genModelUtil;

  /** Reuses the translator's type/variable/getter resolution so both stay consistent. */
  @Inject
  private ExportExpressionTranslator translator;

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
  public boolean isCompilable(final Expression expression, final ExportTranslationContext context) {
    final String expr = javaExpression(expression, context);
    return expr != null && !expr.contains("/* NOT COMPILABLE: ");
  }

  protected String _javaExpression(final Void it, final ExportTranslationContext ctx) {
    return "";
  }

  protected String _javaExpression(final Expression it, final ExportTranslationContext ctx) {
    return notCompilable(it);
  }

  private String notCompilable(final Expression it) {
    return "/* NOT COMPILABLE: Complex expressions like \"" + serialize(it)
        + "\" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */";
  }

  //////////////////////////////////////////////////
  // LITERALS
  //////////////////////////////////////////////////
  protected String _javaExpression(final StringLiteral it, final ExportTranslationContext ctx) {
    return "\"" + javaEncode(it.getVal()) + "\"";
  }

  protected String _javaExpression(final BooleanLiteral it, final ExportTranslationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final IntegerLiteral it, final ExportTranslationContext ctx) {
    return Integer.toString(it.getVal());
  }

  protected String _javaExpression(final NullLiteral it, final ExportTranslationContext ctx) {
    return "null";
  }

  protected String _javaExpression(final RealLiteral it, final ExportTranslationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final ListLiteral it, final ExportTranslationContext ctx) {
    if (it.getElements().isEmpty()) {
      return "java.util.Collections.<org.eclipse.emf.ecore.EObject> emptyList()";
    } else if (it.getElements().size() == 1) {
      return "java.util.Collections.singletonList(" + javaExpression(it.getElements().get(0), ctx) + ")";
    } else {
      return "com.google.common.collect.Lists.newArrayList(" + join(", ", javaExpressions(it.getElements(), ctx)) + ")";
    }
  }

  //////////////////////////////////////////////////
  // TYPES AND VARIABLES
  //////////////////////////////////////////////////
  protected String _javaExpression(final Identifier it, final ExportTranslationContext ctx) {
    if (isThis(it)) {
      return ctx.getImplicitVariableName();
    }
    return join("::", it.getId());
  }

  private boolean isTypeRef(final FeatureCall it, final ExportTranslationContext ctx) {
    if (it.getName() != null || it.getType() == null) {
      return false;
    }
    final ExportModelTypeResolver modelTypeResolver = ctx.getModelTypeResolver();
    final EClassifier classifier = modelTypeResolver != null ? modelTypeResolver.resolve(it.getType().getId()) : null;
    return classifier != null || ctx.resolveDslType(it.getType()) != null;
  }

  private boolean isVariableRef(final Expression it, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean isVariableRef(final FeatureCall it, final ExportTranslationContext ctx) {
    if (it.getTarget() != null || it.getName() != null || it.getType() == null) {
      return false;
    }
    return it.getType().getId().size() == 1 && ctx.getVariable(it.getType().getId().get(0)) != null;
  }

  private String featureCallTarget(final FeatureCall it, final ExportTranslationContext ctx) {
    if (it.getTarget() == null || isThisCall(it.getTarget())) {
      return ctx.getImplicitVariableName();
    }
    return javaExpression(it.getTarget(), ctx);
  }

  //////////////////////////////////////////////////
  // BOOLEAN OPERATIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final BooleanOperation it, final ExportTranslationContext ctx) {
    return autoBracket(it, javaExpression(it.getLeft(), ctx) + " " + it.getOperator() + " " + javaExpression(it.getRight(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // COLLECTION OPERATIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final CollectionExpression it, final ExportTranslationContext ctx) {
    if (Objects.equals("select", it.getName())) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx)
          + ", new com.google.common.base.Predicate<Object>() { public boolean apply(Object "
          + (it.getVar() != null ? it.getVar() : "e") + ") {return "
          + javaExpression(it.getExp(), ctx) + ";} })";
    }
    return notCompilable(it);
  }

  protected String _javaExpression(final TypeSelectExpression it, final ExportTranslationContext ctx) {
    if (isSimpleNavigation(it, ctx)) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx) + ", " + javaType(ctx, it.getType()) + ".class)";
    }
    return notCompilable(it);
  }

  //////////////////////////////////////////////////
  // TYPE CAST
  //////////////////////////////////////////////////
  protected String _javaExpression(final CastedExpression it, final ExportTranslationContext ctx) {
    return "((" + javaType(ctx, it.getType()) + ") " + javaExpression(it.getTarget(), ctx) + ")";
  }

  //////////////////////////////////////////////////
  // IF EXPRESSIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final IfExpression it, final ExportTranslationContext ctx) {
    return autoBracket(it,
        javaExpression(it.getCondition(), ctx) + " ? " + javaExpression(it.getThenPart(), ctx) + " : " + javaExpression(it.getElsePart(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // FEATURE CALLS
  //////////////////////////////////////////////////
  protected String _javaExpression(final FeatureCall it, final ExportTranslationContext ctx) {
    if (isThisCall(it)) {
      return ctx.getImplicitVariableName();
    } else if (isVariableRef(it, ctx)) {
      return javaExpression(it.getType(), ctx);
    } else if (isTypeRef(it, ctx)) {
      return javaType(ctx, it.getType());
    } else if (isSimpleFeatureCall(it, ctx)) {
      return featureCallTarget(it, ctx) + "." + featureAccessorName(it) + "()";
    } else if (isSimpleNavigation(it, ctx)) {
      return notCompilable(it);
    } else {
      return featureCallTarget(it, ctx) + "." + featureAccessorName(it) + "()";
    }
  }

  /**
   * Returns the name of the Java accessor a feature call compiles to.
   *
   * @param it
   *          the feature call, must not be {@code null}
   * @return the accessor name, never {@code null}
   */
  private String featureAccessorName(final FeatureCall it) {
    if (Objects.equals(calledFeature(it), "eContainer")) {
      return "eContainer";
    } else if (Objects.equals(calledFeature(it), "isEmpty")) {
      return "isEmpty";
    } else {
      return featureCallName(toFirstUpper(calledFeature(it)));
    }
  }

  private String featureCallName(final String it) {
    if (it.startsWith("^")) {
      return featureCallName(toFirstUpper(it.substring(1, it.length())));
    }
    return (it.startsWith("Is") ? "is" : "get") + it;
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
  protected boolean _isSimpleFeatureCall(final Expression it, final ExportTranslationContext ctx) {
    return false;
  }

  protected boolean _isSimpleFeatureCall(final FeatureCall it, final ExportTranslationContext ctx) {
    if (!it.eClass().getName().contains("FeatureCall") || it.getName() != null || !isFeature(it.getType())) {
      return false;
    }
    return it.getTarget() == null || isVariableRef(it.getTarget(), ctx) || isThisCall(it.getTarget());
  }

  protected boolean _isSimpleNavigation(final Expression it, final ExportTranslationContext ctx) {
    return false;
  }

  protected boolean _isSimpleNavigation(final TypeSelectExpression it, final ExportTranslationContext ctx) {
    return true;
  }

  protected boolean _isSimpleNavigation(final FeatureCall it, final ExportTranslationContext ctx) {
    if (it.getName() != null || !isFeature(it.getType())) {
      return false;
    }
    return it.getTarget() == null || isVariableRef(it.getTarget(), ctx) || isThisCall(it.getTarget()) || isSimpleNavigation(it.getTarget(), ctx);
  }

  //////////////////////////////////////////////////
  // OPERATION CALLS
  //////////////////////////////////////////////////
  protected String _javaExpression(final OperationCall it, final ExportTranslationContext ctx) {
    if ((it.getTarget() == null || isThisCall(it.getTarget())) && targetHasOperation(it, ctx)) {
      return (it.getTarget() != null ? javaExpression(it.getTarget(), ctx) + "." : "") + it.getName()
          + "(" + join(", ", javaExpressions(it.getParams(), ctx)) + ")";
    }
    final String extensionClass = extensionClassName(it, ctx);
    if (extensionClass != null) {
      return extensionClass + "." + it.getName() + "(" + join(", ", javaExpressions(extensionArguments(it), ctx)) + ")";
    }
    if (isArithmeticOperatorCall(it, ctx)) {
      return autoBracket(it, join(" " + it.getName() + " ", javaExpressions(it.getParams(), ctx)), ctx);
    } else if (isSimpleConcatCall(it)) {
      return join(" + ", javaExpressions(it.getParams(), ctx));
    } else if (isPrefixExpression(it)) {
      return autoBracket(it, it.getName() + javaExpression(it.getParams().get(0), ctx), ctx);
    } else if (Objects.equals("first", it.getName()) && it.getParams().isEmpty() && it.getTarget() != null) {
      return javaExpression(it.getTarget(), ctx) + ".get(0)";
    } else if (Objects.equals("isInstance", it.getName()) && it.getParams().size() == 1 && it.getTarget() instanceof FeatureCall typeReference
        && isTypeRef(typeReference, ctx)) {
      return autoBracket(it, javaExpression(it.getParams().get(0), ctx) + " instanceof " + javaExpression(it.getTarget(), ctx), ctx);
    } else if (Objects.equals("eContainer", it.getName()) && it.getParams().isEmpty()) {
      return javaExpression(it.getTarget(), ctx) + ".eContainer()";
    } else {
      return (it.getTarget() != null ? javaExpression(it.getTarget(), ctx) + "." : "") + it.getName()
          + "(" + (it.getParams().isEmpty() ? "" : join(", ", javaExpressions(it.getParams(), ctx))) + ")";
    }
  }

  /**
   * Returns the fully qualified name of the extension class declared through {@code extension a::b::C} that provides
   * the {@code static} method this operation call refers to.
   * <p>
   * As in the legacy compiler, a declared extension takes precedence over an operation of the receiver's own type
   * (many model types declare an EOperation of the same name as the extension that refines it). Candidates are
   * matched on their parameter types so that an extension which merely shares its name and arity with a real
   * operation of the receiver does not shadow it. Unqualified and {@code this} qualified calls are handled before
   * this method is consulted.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the fully qualified extension class name, or {@code null} if no declared extension class matches
   */
  private String extensionClassName(final OperationCall it, final ExportTranslationContext ctx) {
    if (Objects.equals("isInstance", it.getName())) {
      return null;
    }
    return translator.findExtensionClassName(it, ctx);
  }

  /**
   * Returns the arguments an extension call passes to the {@code static} extension method: the call's target (when
   * present) followed by the declared parameters. Matches the argument order used by
   * {@code ExportExpressionTranslator.translateExtensionCall}.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @return the argument expressions, never {@code null}
   */
  private List<Expression> extensionArguments(final OperationCall it) {
    final List<Expression> result = new ArrayList<>();
    if (it.getTarget() != null) {
      result.add(it.getTarget());
    }
    result.addAll(it.getParams());
    return result;
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
  private boolean targetHasOperation(final OperationCall it, final ExportTranslationContext ctx) {
    final JvmType receiverType = translator.resolveType(it.getTarget(), ctx);
    if (!(receiverType instanceof JvmDeclaredType declaredType)) {
      return false;
    }
    final String operationName = it.getName();
    final int parameterCount = it.getParams().size();
    for (final JvmOperation operation : Iterables.filter(declaredType.getAllFeatures(), JvmOperation.class)) {
      if (Objects.equals(operation.getSimpleName(), operationName) && operation.getParameters().size() == parameterCount) {
        return true;
      }
    }
    return false;
  }

  //////////////////////////////////////////////////
  // EXPRESSION BRACKETING
  //////////////////////////////////////////////////
  private String autoBracket(final Expression it, final String javaCode, final ExportTranslationContext ctx) {
    if (requiresBracketing(it, ctx)) {
      return "(" + javaCode + ")";
    }
    return javaCode;
  }

  private boolean _requiresBracketing(final Expression it, final ExportTranslationContext ctx) {
    return (isPrefixExpression(it) || isInfixExpression(it, ctx)) && it.eContainer() != null && requiresBracketing(it, it.eContainer(), ctx);
  }

  private boolean _requiresBracketing(final Literal it, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean _requiresBracketing(final Expression it, final Object parent, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean _requiresBracketing(final Expression it, final Expression parent, final ExportTranslationContext ctx) {
    final boolean bothPrefix = isPrefixExpression(it) && isPrefixExpression(parent);
    return bothPrefix || isInfixExpression(it, ctx) && (isPrefixExpression(parent) || isInfixExpression(parent, ctx));
  }

  private boolean _requiresBracketing(final OperationCall it, final OperationCall parent, final ExportTranslationContext ctx) {
    if (isPrefixExpression(it) && isPrefixExpression(parent)) {
      return true;
    }
    if (!isInfixExpression(it, ctx)) {
      return false;
    }
    return isPrefixExpression(parent) || isInfixExpression(parent, ctx) && !Objects.equals(it.getName(), parent.getName());
  }

  private boolean _requiresBracketing(final BooleanOperation it, final BooleanOperation parent, final ExportTranslationContext ctx) {
    return !Objects.equals(it.getOperator(), parent.getOperator());
  }

  //////////////////////////////////////////////////
  // OPERATOR CLASSIFICATION
  //////////////////////////////////////////////////
  private boolean isSimpleConcatCall(final OperationCall it) {
    return Objects.equals(it.getName(), "+") && it.getType() == null && it.getTarget() == null && !it.getParams().isEmpty();
  }

  private boolean isNumber(final Expression it, final ExportTranslationContext ctx) {
    if (isArithmeticOperatorCall(it, ctx)) {
      // Arithmetic calls have no resolvable JVM method but still produce numeric values.
      return true;
    }
    final JvmType type = translator.resolveType(it, ctx);
    return type != null && isNumeric(type);
  }

  private boolean isNumeric(final JvmType it) {
    final String name = it.getQualifiedName();
    if (name == null) {
      return false;
    }
    return switch (name) {
      case "int", "long", "short", "byte", "double", "float", "java.lang.Integer", "java.lang.Long", "java.lang.Short", "java.lang.Byte",
          "java.lang.Double", "java.lang.Float", "java.lang.Number", "java.math.BigInteger", "java.math.BigDecimal" ->
        true;
      default -> false;
    };
  }

  private boolean _isArithmeticOperatorCall(final OperationCall it, final ExportTranslationContext ctx) {
    if (it.getType() != null || it.getTarget() != null || it.getParams().size() <= 1) {
      return false;
    }
    return isArithmeticOperatorName(it.getName()) && it.getParams().stream().allMatch(param -> isNumber(param, ctx));
  }

  /**
   * Tests whether the given operation name is one of the arithmetic operators.
   *
   * @param name
   *          the operation name, may be {@code null}
   * @return {@code true} if the name is an arithmetic operator
   */
  private boolean isArithmeticOperatorName(final String name) {
    return Objects.equals(name, "+") || Objects.equals(name, "-") || Objects.equals(name, "*") || Objects.equals(name, "/");
  }

  private boolean _isArithmeticOperatorCall(final Expression it, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean _isPrefixExpression(final Expression it) {
    return false;
  }

  private boolean _isPrefixExpression(final OperationCall it) {
    if (it.getType() != null || it.getTarget() != null || it.getParams().size() != 1) {
      return false;
    }
    return Objects.equals(it.getName(), "-") || Objects.equals(it.getName(), "!");
  }

  private boolean _isInfixExpression(final Void it, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean _isInfixExpression(final Expression it, final ExportTranslationContext ctx) {
    return false;
  }

  private boolean _isInfixExpression(final OperationCall it, final ExportTranslationContext ctx) {
    return isArithmeticOperatorCall(it, ctx) || Objects.equals("isInstance", it.getName());
  }

  private boolean _isInfixExpression(final IfExpression it, final ExportTranslationContext ctx) {
    return true;
  }

  private boolean _isInfixExpression(final BooleanOperation it, final ExportTranslationContext ctx) {
    return true;
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
  private String javaType(final ExportTranslationContext ctx, final Identifier type) {
    final ExportModelTypeResolver modelTypeResolver = ctx.getModelTypeResolver();
    final EClassifier classifier = modelTypeResolver != null ? modelTypeResolver.resolve(type.getId()) : null;
    if (classifier != null) {
      return genModelUtil.instanceClassName(classifier);
    }
    final JvmType resolved = ctx.resolveDslType(type);
    if (resolved == null) {
      return join("::", type.getId());
    }
    final String name = resolved.getQualifiedName();
    if (name.startsWith("java.lang.") && !name.substring("java.lang.".length()).contains(".")) {
      return name.substring("java.lang.".length());
    }
    return name;
  }

  //////////////////////////////////////////////////
  // HELPER FUNCTIONS
  //////////////////////////////////////////////////
  private boolean _isThisCall(final Expression it) {
    return false;
  }

  private boolean _isThisCall(final FeatureCall it) {
    return it.getName() == null && isThis(it.getType());
  }

  private boolean isFeature(final Identifier it) {
    return it.getId() != null && it.getId().size() == 1;
  }

  private boolean _isThis(final Expression it) {
    return false;
  }

  private boolean _isThis(final Identifier it) {
    return it.getId() != null && it.getId().size() == 1 && Objects.equals(it.getId().get(0), "this");
  }

  private String calledFeature(final FeatureCall it) {
    return it.getType().getId().isEmpty() ? null : it.getType().getId().get(0);
  }

  private String serialize(final EObject it) {
    return ExpressionExtensions.serialize(it);
  }

  private String _javaEncode(final Expression it) {
    return javaEncode(serialize(it));
  }

  private String _javaEncode(final String it) {
    return Strings.convertToJavaString(it);
  }

  /**
   * Compiles each of the given expressions to its Java source text.
   *
   * @param expressions
   *          the source expressions, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the compiled fragments, never {@code null}
   */
  private List<String> javaExpressions(final List<Expression> expressions, final ExportTranslationContext ctx) {
    return expressions.stream().map(expression -> javaExpression(expression, ctx)).toList();
  }

  private String join(final String separator, final List<String> strings) {
    return strings.isEmpty() ? "" : Strings.concat(separator, strings);
  }

  private static String toFirstUpper(final String value) {
    return value == null || value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }

  //////////////////////////////////////////////////
  // DISPATCHERS
  //////////////////////////////////////////////////
  /**
   * Compiles the given expression into the equivalent Java source text.
   *
   * @param it
   *          the source expression, may be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the Java source text, never {@code null}
   * @throws IllegalArgumentException
   *           if no dispatch case handles the given argument types
   */
  public String javaExpression(final SyntaxElement it, final ExportTranslationContext ctx) {
    if (it instanceof BooleanLiteral booleanLiteral) {
      return _javaExpression(booleanLiteral, ctx);
    } else if (it instanceof CollectionExpression collectionExpression) {
      return _javaExpression(collectionExpression, ctx);
    } else if (it instanceof IntegerLiteral integerLiteral) {
      return _javaExpression(integerLiteral, ctx);
    } else if (it instanceof NullLiteral nullLiteral) {
      return _javaExpression(nullLiteral, ctx);
    } else if (it instanceof OperationCall operationCall) {
      return _javaExpression(operationCall, ctx);
    } else if (it instanceof RealLiteral realLiteral) {
      return _javaExpression(realLiteral, ctx);
    } else if (it instanceof StringLiteral stringLiteral) {
      return _javaExpression(stringLiteral, ctx);
    } else if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _javaExpression(typeSelectExpression, ctx);
    } else if (it instanceof BooleanOperation booleanOperation) {
      return _javaExpression(booleanOperation, ctx);
    } else if (it instanceof CastedExpression castedExpression) {
      return _javaExpression(castedExpression, ctx);
    } else if (it instanceof FeatureCall featureCall) {
      return _javaExpression(featureCall, ctx);
    } else if (it instanceof IfExpression ifExpression) {
      return _javaExpression(ifExpression, ctx);
    } else if (it instanceof ListLiteral listLiteral) {
      return _javaExpression(listLiteral, ctx);
    } else if (it instanceof Expression expression) {
      return _javaExpression(expression, ctx);
    } else if (it instanceof Identifier identifier) {
      return _javaExpression(identifier, ctx);
    } else if (it == null) {
      return _javaExpression((Void) null, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx));
    }
  }

  public boolean isSimpleFeatureCall(final Expression it, final ExportTranslationContext ctx) {
    if (it instanceof FeatureCall featureCall) {
      return _isSimpleFeatureCall(featureCall, ctx);
    } else if (it != null) {
      return _isSimpleFeatureCall(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx));
    }
  }

  public boolean isSimpleNavigation(final Expression it, final ExportTranslationContext ctx) {
    if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _isSimpleNavigation(typeSelectExpression, ctx);
    } else if (it instanceof FeatureCall featureCall) {
      return _isSimpleNavigation(featureCall, ctx);
    } else if (it != null) {
      return _isSimpleNavigation(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx));
    }
  }

  private boolean requiresBracketing(final Expression it, final ExportTranslationContext ctx) {
    if (it instanceof Literal literal) {
      return _requiresBracketing(literal, ctx);
    } else if (it != null) {
      return _requiresBracketing(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx));
    }
  }

  private boolean requiresBracketing(final Expression it, final Object parent, final ExportTranslationContext ctx) {
    if (it instanceof OperationCall operationCall && parent instanceof OperationCall parentCall) {
      return _requiresBracketing(operationCall, parentCall, ctx);
    } else if (it instanceof BooleanOperation booleanOperation && parent instanceof BooleanOperation parentOperation) {
      return _requiresBracketing(booleanOperation, parentOperation, ctx);
    } else if (it != null && parent instanceof Expression parentExpression) {
      return _requiresBracketing(it, parentExpression, ctx);
    } else if (it != null && parent != null) {
      return _requiresBracketing(it, parent, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, parent, ctx));
    }
  }

  private boolean isArithmeticOperatorCall(final Expression it, final ExportTranslationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _isArithmeticOperatorCall(operationCall, ctx);
    } else if (it != null) {
      return _isArithmeticOperatorCall(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx));
    }
  }

  private boolean isPrefixExpression(final Expression it) {
    if (it instanceof OperationCall operationCall) {
      return _isPrefixExpression(operationCall);
    } else if (it != null) {
      return _isPrefixExpression(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it));
    }
  }

  private boolean isInfixExpression(final Expression it, final ExportTranslationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _isInfixExpression(operationCall, ctx);
    } else if (it instanceof BooleanOperation booleanOperation) {
      return _isInfixExpression(booleanOperation, ctx);
    } else if (it instanceof IfExpression ifExpression) {
      return _isInfixExpression(ifExpression, ctx);
    } else if (it != null) {
      return _isInfixExpression(it, ctx);
    } else {
      return _isInfixExpression((Void) null, ctx);
    }
  }

  private boolean isThisCall(final Expression it) {
    if (it instanceof FeatureCall featureCall) {
      return _isThisCall(featureCall);
    } else if (it != null) {
      return _isThisCall(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it));
    }
  }

  private boolean isThis(final SyntaxElement it) {
    if (it instanceof Expression expression) {
      return _isThis(expression);
    } else if (it instanceof Identifier identifier) {
      return _isThis(identifier);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it));
    }
  }

  private String javaEncode(final Object it) {
    if (it instanceof Expression expression) {
      return _javaEncode(expression);
    } else if (it instanceof String string) {
      return _javaEncode(string);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it));
    }
  }

  // CHECKSTYLE:CONSTANTS-ON

}
