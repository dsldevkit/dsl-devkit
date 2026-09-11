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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.Strings;

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
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeModelTypeResolver;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

/**
 * Compiles the custom {@link Expression} AST of the scope/export expression DSL into equivalent Java source text.
 * <p>
 * This is the self-contained, {@code org.eclipse.xtend}-free replacement of the legacy
 * {@code CodeGenerationX}/{@code CompilationContext} expression compiler. It produces exactly the same Java
 * fragments as the legacy compiler did, but resolves types, variables and the implicit ({@code this}) receiver
 * through the shared {@link ScopeTranslationContext} that the
 * {@link ScopeExpressionTranslator} already uses, rather than through the classic Xtend type system.
 * <p>
 * The compiler is used as the fallback for expression forms that the Xbase based {@link ScopeExpressionTranslator}
 * does not turn into an {@link org.eclipse.xtext.xbase.XExpression} tree (for example string concatenation, the
 * unary/binary arithmetic operators and the relational operators). The {@code .ext}/{@code JAVA} extension
 * branches of the legacy compiler are intentionally dropped: scope sources no longer reference Xtend extension
 * files.
 */
@SuppressWarnings({"checkstyle:MethodName", "nls", "PMD.UnusedFormalParameter"})
public class ScopeExpressionCompiler {
  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are fragments of the emitted Java source, not nameable constants

  /** Maps EMF model types to their generated Java instance class names. */
  @Inject
  private GenModelUtilX genModelUtil;

  /** Reuses the translator's type/variable/getter resolution so both stay consistent. */
  @Inject
  private ScopeExpressionTranslator translator;

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
  public boolean isCompilable(final Expression expression, final ScopeTranslationContext context) {
    final String expr = javaExpression(expression, context);
    return expr != null && !expr.contains("/* NOT COMPILABLE: ");
  }

  /**
   * Compiles the given expression into the equivalent Java source text.
   *
   * @param it
   *          the source expression, may be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the Java source text, never {@code null}
   */
  protected String _javaExpression(final Void it, final ScopeTranslationContext ctx) {
    return "";
  }

  protected String _javaExpression(final Expression it, final ScopeTranslationContext ctx) {
    return notCompilable(it);
  }

  private String notCompilable(final Expression it) {
    return "/* NOT COMPILABLE: Complex expressions like \"" + serialize(it)
        + "\" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */";
  }

  //////////////////////////////////////////////////
  // LITERALS
  //////////////////////////////////////////////////
  protected String _javaExpression(final StringLiteral it, final ScopeTranslationContext ctx) {
    return "\"" + javaEncode(it.getVal()) + "\"";
  }

  protected String _javaExpression(final BooleanLiteral it, final ScopeTranslationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final IntegerLiteral it, final ScopeTranslationContext ctx) {
    return Integer.toString(it.getVal());
  }

  protected String _javaExpression(final NullLiteral it, final ScopeTranslationContext ctx) {
    return "null";
  }

  protected String _javaExpression(final RealLiteral it, final ScopeTranslationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final ListLiteral it, final ScopeTranslationContext ctx) {
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
  protected String _javaExpression(final Identifier it, final ScopeTranslationContext ctx) {
    if (isThis(it)) {
      return ctx.getImplicitVariableName();
    }
    return join("::", it.getId());
  }

  private boolean isTypeRef(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (it.getName() != null || it.getType() == null) {
      return false;
    }
    final ScopeModelTypeResolver modelTypeResolver = ctx.getModelTypeResolver();
    final EClassifier classifier = modelTypeResolver != null ? modelTypeResolver.resolve(it.getType().getId()) : null;
    return classifier != null || ctx.resolveDslType(it.getType()) != null;
  }

  private boolean isVariableRef(final Expression it, final ScopeTranslationContext ctx) {
    return false;
  }

  private boolean isVariableRef(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (it.getTarget() != null || it.getName() != null || it.getType() == null) {
      return false;
    }
    final List<String> id = it.getType().getId();
    return id.size() == 1 && ctx.getVariable(id.get(0)) != null;
  }

  private String featureCallTarget(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (it.getTarget() == null || isThisCall(it.getTarget())) {
      return ctx.getImplicitVariableName();
    }
    return javaExpression(it.getTarget(), ctx);
  }

  //////////////////////////////////////////////////
  // BOOLEAN OPERATIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final BooleanOperation it, final ScopeTranslationContext ctx) {
    return autoBracket(it, javaExpression(it.getLeft(), ctx) + " " + it.getOperator() + " " + javaExpression(it.getRight(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // COLLECTION OPERATIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final CollectionExpression it, final ScopeTranslationContext ctx) {
    if (Objects.equals("select", it.getName())) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx)
          + ", new com.google.common.base.Predicate<Object>() { public boolean apply(Object "
          + (it.getVar() != null ? it.getVar() : "e") + ") {return "
          + javaExpression(it.getExp(), ctx) + ";} })";
    } else {
      return notCompilable(it);
    }
  }

  protected String _javaExpression(final TypeSelectExpression it, final ScopeTranslationContext ctx) {
    if (isSimpleNavigation(it, ctx)) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx) + ", " + javaType(ctx, it.getType()) + ".class)";
    } else {
      return notCompilable(it);
    }
  }

  //////////////////////////////////////////////////
  // TYPE CAST
  //////////////////////////////////////////////////
  protected String _javaExpression(final CastedExpression it, final ScopeTranslationContext ctx) {
    return "((" + javaType(ctx, it.getType()) + ") " + javaExpression(it.getTarget(), ctx) + ")";
  }

  //////////////////////////////////////////////////
  // IF EXPRESSIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final IfExpression it, final ScopeTranslationContext ctx) {
    return autoBracket(it, javaExpression(it.getCondition(), ctx) + " ? " + javaExpression(it.getThenPart(), ctx) + " : "
        + javaExpression(it.getElsePart(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // FEATURE CALLS
  //////////////////////////////////////////////////
  protected String _javaExpression(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (isThisCall(it)) {
      return ctx.getImplicitVariableName();
    } else if (isVariableRef(it, ctx)) {
      return javaExpression(it.getType(), ctx);
    } else if (isTypeRef(it, ctx)) {
      return javaType(ctx, it.getType());
    } else if (isSimpleFeatureCall(it, ctx)) {
      return featureCallTarget(it, ctx) + "." + accessorName(it) + "()";
    } else if (isSimpleNavigation(it, ctx)) {
      return notCompilable(it);
    } else {
      return featureCallTarget(it, ctx) + "." + accessorName(it) + "()";
    }
  }

  /**
   * Returns the name of the accessor a feature call compiles to: {@code eContainer} and {@code isEmpty} are called
   * literally, every other feature becomes its {@code getX()}/{@code isX()} accessor.
   *
   * @param it
   *          the feature call, must not be {@code null}
   * @return the accessor name, never {@code null}
   */
  private String accessorName(final FeatureCall it) {
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
  protected boolean _isSimpleFeatureCall(final Expression it, final ScopeTranslationContext ctx) {
    return false;
  }

  protected boolean _isSimpleFeatureCall(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (!it.eClass().getName().contains("FeatureCall") || it.getName() != null || !isFeature(it.getType())) {
      return false;
    }
    final Expression target = it.getTarget();
    return target == null || isVariableRef(target, ctx) || isThisCall(target);
  }

  protected boolean _isSimpleNavigation(final Expression it, final ScopeTranslationContext ctx) {
    return false;
  }

  protected boolean _isSimpleNavigation(final TypeSelectExpression it, final ScopeTranslationContext ctx) {
    return true;
  }

  protected boolean _isSimpleNavigation(final FeatureCall it, final ScopeTranslationContext ctx) {
    if (it.getName() != null || !isFeature(it.getType())) {
      return false;
    }
    final Expression target = it.getTarget();
    return target == null || isVariableRef(target, ctx) || isThisCall(target) || isSimpleNavigation(target, ctx);
  }

  //////////////////////////////////////////////////
  // OPERATION CALLS
  //////////////////////////////////////////////////
  protected String _javaExpression(final OperationCall it, final ScopeTranslationContext ctx) {
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
    } else if (isInstanceOfTypeCheck(it, ctx)) {
      return autoBracket(it, javaExpression(it.getParams().get(0), ctx) + " instanceof " + javaExpression(it.getTarget(), ctx), ctx);
    } else if (Objects.equals("eContainer", it.getName()) && it.getParams().isEmpty()) {
      return javaExpression(it.getTarget(), ctx) + ".eContainer()";
    } else {
      return (it.getTarget() != null ? javaExpression(it.getTarget(), ctx) + "." : "") + it.getName()
          + "(" + (it.getParams().isEmpty() ? "" : join(", ", javaExpressions(it.getParams(), ctx))) + ")";
    }
  }

  /**
   * Tests whether the given operation call is an {@code isInstance} check on a resolvable type reference, which
   * compiles to a Java {@code instanceof}.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return {@code true} if the call is an {@code isInstance} type check
   */
  private boolean isInstanceOfTypeCheck(final OperationCall it, final ScopeTranslationContext ctx) {
    if (!Objects.equals("isInstance", it.getName()) || it.getParams().size() != 1) {
      return false;
    }
    return it.getTarget() instanceof FeatureCall typeReference && isTypeRef(typeReference, ctx);
  }

  /**
   * Returns the fully qualified name of the extension class declared through {@code extension a::b::C} that provides
   * the {@code static} method this operation call refers to.
   * <p>
   * As in the legacy compiler, a declared extension takes precedence over an operation of the receiver's own type
   * (many model types declare an EOperation of the same name as the extension that refines it). Candidates are
   * matched on their parameter types so that an extension which merely shares its name and arity with a real
   * operation of the receiver (for instance {@code getTarget(SelectionSet)} next to {@code BdeField.getTarget()})
   * does not shadow it. Unqualified and {@code this} qualified calls are handled before this method is consulted.
   *
   * @param it
   *          the operation call, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the fully qualified extension class name, or {@code null} if no declared extension class matches
   */
  private String extensionClassName(final OperationCall it, final ScopeTranslationContext ctx) {
    if (Objects.equals("isInstance", it.getName())) {
      return null;
    }
    return translator.findExtensionClassName(it, ctx);
  }

  /**
   * Returns the arguments an extension call passes to the {@code static} extension method: the call's target (when
   * present) followed by the declared parameters. Matches the argument order used by
   * {@code ScopeExpressionTranslator.translateExtensionCall}.
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
  private boolean targetHasOperation(final OperationCall it, final ScopeTranslationContext ctx) {
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
  private String autoBracket(final Expression it, final String javaCode, final ScopeTranslationContext ctx) {
    if (requiresBracketing(it, ctx)) {
      return "(" + javaCode + ")";
    }
    return javaCode;
  }

  private boolean _requiresBracketing(final Expression it, final ScopeTranslationContext ctx) {
    if (!isPrefixExpression(it) && !isInfixExpression(it, ctx)) {
      return false;
    }
    return it.eContainer() != null && requiresBracketing(it, it.eContainer(), ctx);
  }

  private boolean _requiresBracketing(final Literal it, final ScopeTranslationContext ctx) {
    return false;
  }

  private boolean _requiresBracketing(final Expression it, final Object parent, final ScopeTranslationContext ctx) {
    return false;
  }

  private boolean _requiresBracketing(final Expression it, final Expression parent, final ScopeTranslationContext ctx) {
    final boolean bothPrefix = isPrefixExpression(it) && isPrefixExpression(parent);
    return bothPrefix || (isInfixExpression(it, ctx) && (isPrefixExpression(parent) || isInfixExpression(parent, ctx)));
  }

  private boolean _requiresBracketing(final OperationCall it, final OperationCall parent, final ScopeTranslationContext ctx) {
    if (isPrefixExpression(it) && isPrefixExpression(parent)) {
      return true;
    }
    if (!isInfixExpression(it, ctx)) {
      return false;
    }
    return isPrefixExpression(parent) || (isInfixExpression(parent, ctx) && !Objects.equals(it.getName(), parent.getName()));
  }

  private boolean _requiresBracketing(final BooleanOperation it, final BooleanOperation parent, final ScopeTranslationContext ctx) {
    return !Objects.equals(it.getOperator(), parent.getOperator());
  }

  //////////////////////////////////////////////////
  // OPERATOR CLASSIFICATION
  //////////////////////////////////////////////////
  private boolean isSimpleConcatCall(final OperationCall it) {
    if (!Objects.equals(it.getName(), "+") || it.getType() != null) {
      return false;
    }
    return it.getTarget() == null && !it.getParams().isEmpty();
  }

  private boolean isNumber(final Expression it, final ScopeTranslationContext ctx) {
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
      case "int", "long", "short", "byte", "double", "float", "java.lang.Integer", "java.lang.Long", "java.lang.Short",
          "java.lang.Byte", "java.lang.Double", "java.lang.Float", "java.lang.Number", "java.math.BigInteger",
          "java.math.BigDecimal" ->
        true;
      default -> false;
    };
  }

  private boolean _isArithmeticOperatorCall(final OperationCall it, final ScopeTranslationContext ctx) {
    if (it.getType() != null || it.getTarget() != null || it.getParams().size() <= 1) {
      return false;
    }
    final String operator = it.getName();
    final boolean arithmetic = Objects.equals(operator, "+") || Objects.equals(operator, "-")
        || Objects.equals(operator, "*") || Objects.equals(operator, "/");
    if (!arithmetic) {
      return false;
    }
    for (final Expression param : it.getParams()) {
      if (!isNumber(param, ctx)) {
        return false;
      }
    }
    return true;
  }

  private boolean _isArithmeticOperatorCall(final Expression it, final ScopeTranslationContext ctx) {
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

  private boolean _isInfixExpression(final Void it, final ScopeTranslationContext ctx) {
    return false;
  }

  private boolean _isInfixExpression(final Expression it, final ScopeTranslationContext ctx) {
    return false;
  }

  private boolean _isInfixExpression(final OperationCall it, final ScopeTranslationContext ctx) {
    return isArithmeticOperatorCall(it, ctx) || Objects.equals("isInstance", it.getName());
  }

  private boolean _isInfixExpression(final IfExpression it, final ScopeTranslationContext ctx) {
    return true;
  }

  private boolean _isInfixExpression(final BooleanOperation it, final ScopeTranslationContext ctx) {
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
  private String javaType(final ScopeTranslationContext ctx, final Identifier type) {
    final ScopeModelTypeResolver modelTypeResolver = ctx.getModelTypeResolver();
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
    return it.getType().getId().get(0);
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

  private String join(final String separator, final List<String> strings) {
    if (strings.isEmpty()) {
      return "";
    }
    return Strings.concat(separator, strings);
  }

  /**
   * Compiles each of the given expressions into its Java source text, preserving their order.
   *
   * @param expressions
   *          the source expressions, must not be {@code null}
   * @param ctx
   *          the compilation context, must not be {@code null}
   * @return the compiled Java fragments, never {@code null}
   */
  private List<String> javaExpressions(final List<Expression> expressions, final ScopeTranslationContext ctx) {
    final List<String> result = new ArrayList<>();
    for (final Expression expression : expressions) {
      result.add(javaExpression(expression, ctx));
    }
    return result;
  }

  private static String toFirstUpper(final String value) {
    return value == null || value.isEmpty() ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
  }

  //////////////////////////////////////////////////
  // DISPATCHERS
  //////////////////////////////////////////////////
  public String javaExpression(final SyntaxElement it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case BooleanLiteral literal -> _javaExpression(literal, ctx);
      case CollectionExpression expression -> _javaExpression(expression, ctx);
      case IntegerLiteral literal -> _javaExpression(literal, ctx);
      case NullLiteral literal -> _javaExpression(literal, ctx);
      case OperationCall call -> _javaExpression(call, ctx);
      case RealLiteral literal -> _javaExpression(literal, ctx);
      case StringLiteral literal -> _javaExpression(literal, ctx);
      case TypeSelectExpression expression -> _javaExpression(expression, ctx);
      case BooleanOperation operation -> _javaExpression(operation, ctx);
      case CastedExpression expression -> _javaExpression(expression, ctx);
      case FeatureCall call -> _javaExpression(call, ctx);
      case IfExpression expression -> _javaExpression(expression, ctx);
      case ListLiteral literal -> _javaExpression(literal, ctx);
      case Expression expression -> _javaExpression(expression, ctx);
      case Identifier identifier -> _javaExpression(identifier, ctx);
      case null -> _javaExpression((Void) null, ctx);
      default -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx).toString());
    };
  }

  public boolean isSimpleFeatureCall(final Expression it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case FeatureCall call -> _isSimpleFeatureCall(call, ctx);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx).toString());
      default -> _isSimpleFeatureCall(it, ctx);
    };
  }

  public boolean isSimpleNavigation(final Expression it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case TypeSelectExpression expression -> _isSimpleNavigation(expression, ctx);
      case FeatureCall call -> _isSimpleNavigation(call, ctx);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx).toString());
      default -> _isSimpleNavigation(it, ctx);
    };
  }

  private boolean requiresBracketing(final Expression it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case Literal literal -> _requiresBracketing(literal, ctx);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx).toString());
      default -> _requiresBracketing(it, ctx);
    };
  }

  private boolean requiresBracketing(final Expression it, final Object parent, final ScopeTranslationContext ctx) {
    if (it instanceof OperationCall call && parent instanceof OperationCall parentCall) {
      return _requiresBracketing(call, parentCall, ctx);
    } else if (it instanceof BooleanOperation operation && parent instanceof BooleanOperation parentOperation) {
      return _requiresBracketing(operation, parentOperation, ctx);
    } else if (it != null && parent instanceof Expression parentExpression) {
      return _requiresBracketing(it, parentExpression, ctx);
    } else if (it != null && parent != null) {
      return _requiresBracketing(it, parent, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, parent, ctx).toString());
    }
  }

  private boolean isArithmeticOperatorCall(final Expression it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case OperationCall call -> _isArithmeticOperatorCall(call, ctx);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, ctx).toString());
      default -> _isArithmeticOperatorCall(it, ctx);
    };
  }

  private boolean isPrefixExpression(final Expression it) {
    return switch (it) {
      case OperationCall call -> _isPrefixExpression(call);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
      default -> _isPrefixExpression(it);
    };
  }

  private boolean isInfixExpression(final Expression it, final ScopeTranslationContext ctx) {
    return switch (it) {
      case OperationCall call -> _isInfixExpression(call, ctx);
      case BooleanOperation operation -> _isInfixExpression(operation, ctx);
      case IfExpression expression -> _isInfixExpression(expression, ctx);
      case null -> _isInfixExpression((Void) null, ctx);
      default -> _isInfixExpression(it, ctx);
    };
  }

  private boolean isThisCall(final Expression it) {
    return switch (it) {
      case FeatureCall call -> _isThisCall(call);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
      default -> _isThisCall(it);
    };
  }

  private boolean isThis(final SyntaxElement it) {
    return switch (it) {
      case Expression expression -> _isThis(expression);
      case Identifier identifier -> _isThis(identifier);
      case null, default -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
    };
  }

  private String javaEncode(final Object it) {
    return switch (it) {
      case Expression expression -> _javaEncode(expression);
      case String string -> _javaEncode(string);
      case null, default -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
    };
  }
  // CHECKSTYLE:CONSTANTS-ON
}
