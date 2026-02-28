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

import java.util.ArrayList;
import java.util.List;

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

public class CodeGenerationX {

  private ExpressionExtensionsX expressionExtensionsX = new ExpressionExtensionsX();

  //////////////////////////////////////////////////
  // ENTRY POINTS
  //////////////////////////////////////////////////
  public boolean isCompilable(final Expression it, final CompilationContext ctx) {
    final String expr = javaExpression(it, ctx);
    return expr != null && !expr.contains("/* NOT COMPILABLE: ");
  }

  // dispatch javaExpression
  protected String _javaExpression(final Void it, final CompilationContext ctx) {
    return "";
  }

  protected String _javaExpression(final Expression it, final CompilationContext ctx) {
    return notCompilable(it);
  }

  public String notCompilable(final Expression it) {
    return "/* NOT COMPILABLE: Complex expressions like \"" + expressionExtensionsX.serialize(it) + "\" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */";
  }

  //////////////////////////////////////////////////
  // LITERALS
  //////////////////////////////////////////////////
  protected String _javaExpression(final StringLiteral it, final CompilationContext ctx) {
    return "\"" + javaEncode(it.getVal()) + "\"";
  }

  protected String _javaExpression(final BooleanLiteral it, final CompilationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final IntegerLiteral it, final CompilationContext ctx) {
    return Integer.toString(it.getVal());
  }

  protected String _javaExpression(final NullLiteral it, final CompilationContext ctx) {
    return "null";
  }

  protected String _javaExpression(final RealLiteral it, final CompilationContext ctx) {
    return it.getVal();
  }

  protected String _javaExpression(final ListLiteral it, final CompilationContext ctx) {
    if (it.getElements().isEmpty()) {
      return "java.util.Collections.<" + ctx.javaType(ctx.getRequiredType().getName()) + "> emptyList()";
    } else if (it.getElements().size() == 1) {
      return "java.util.Collections.singletonList(" + javaExpression(it.getElements().get(0), ctx) + ")";
    } else {
      final List<String> mapped = new ArrayList<>();
      for (final Expression e : it.getElements()) {
        mapped.add(javaExpression(e, ctx));
      }
      return "com.google.common.collect.Lists.newArrayList(" + join(", ", mapped) + ")";
    }
  }

  //////////////////////////////////////////////////
  // TYPES AND VARIABLES
  //////////////////////////////////////////////////
  protected String _javaExpression(final Identifier it, final CompilationContext ctx) {
    if (isThis(it)) {
      return ctx.getImplicitVariable();
    } else {
      return join("::", it.getId());
    }
  }

  public boolean isType(final FeatureCall it, final CompilationContext ctx) {
    return it.getName() == null && it.getType() != null && ctx.isType(javaExpression(it.getType(), ctx));
  }

  public boolean isVariable(final Expression it, final CompilationContext ctx) {
    return false;
  }

  public boolean isVariable(final FeatureCall it, final CompilationContext ctx) {
    return it.getTarget() == null && it.getName() == null && ctx.isVariable(javaExpression(it.getType(), ctx));
  }

  public String featureCallTarget(final FeatureCall it, final CompilationContext ctx) {
    if (it.getTarget() == null || isThisCall(it.getTarget())) {
      return ctx.getImplicitVariable();
    } else {
      return javaExpression(it.getTarget(), ctx);
    }
  }

  //////////////////////////////////////////////////
  // BOOLEAN OPERATIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final BooleanOperation it, final CompilationContext ctx) {
    return autoBracket(it, javaExpression(it.getLeft(), ctx) + " " + it.getOperator() + " " + javaExpression(it.getRight(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // COLLECTION OPERATIONS
  //////////////////////////////////////////////////
  // TODO finish
  protected String _javaExpression(final CollectionExpression it, final CompilationContext ctx) {
    if ("select".equals(it.getName())) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx)
          + ", new com.google.common.base.Predicate<Object>() { public boolean apply(Object "
          + (it.getVar() != null ? it.getVar() : "e") + ") {return "
          + javaExpression(it.getExp(), ctx) + ";} })";
    } else {
      return notCompilable(it);
    }
  }

  protected String _javaExpression(final TypeSelectExpression it, final CompilationContext ctx) {
    if (isSimpleNavigation(it, ctx)) {
      return "com.google.common.collect.Iterables.filter(" + javaExpression(it.getTarget(), ctx) + ", " + ctx.javaType(javaExpression(it.getType(), ctx)) + ".class)";
    } else {
      return notCompilable(it);
    }
  }

  //////////////////////////////////////////////////
  // TYPE CAST
  //////////////////////////////////////////////////
  protected String _javaExpression(final CastedExpression it, final CompilationContext ctx) {
    return "((" + ctx.javaType(javaExpression(it.getType(), ctx)) + ") " + javaExpression(it.getTarget(), ctx) + ")";
  }

  //////////////////////////////////////////////////
  // IF EXPRESSIONS
  //////////////////////////////////////////////////
  protected String _javaExpression(final IfExpression it, final CompilationContext ctx) {
    return autoBracket(it, javaExpression(it.getCondition(), ctx) + " ? " + javaExpression(it.getThenPart(), ctx) + " : " + javaExpression(it.getElsePart(), ctx), ctx);
  }

  //////////////////////////////////////////////////
  // FEATURE CALLS
  //////////////////////////////////////////////////
  protected String _javaExpression(final FeatureCall it, final CompilationContext ctx) {
    if (isThisCall(it)) {
      return ctx.getImplicitVariable();
    } else if (isVariable(it, ctx)) {
      return javaExpression(it.getType(), ctx);
    } else if (isType(it, ctx)) {
      return ctx.javaType(javaExpression(it.getType(), ctx));
    } else if (isSimpleFeatureCall(it, ctx)) {
      final String cf = expressionExtensionsX.calledFeature(it);
      final String suffix;
      if ("eContainer".equals(cf)) {
        suffix = "eContainer";
      } else if ("isEmpty".equals(cf)) {
        suffix = "isEmpty";
      } else {
        suffix = featureCallName(toFirstUpper(cf));
      }
      return featureCallTarget(it, ctx) + "." + suffix + "()";
    } else if (isSimpleNavigation(it, ctx)) {
      return notCompilable(it);
    } else {
      final String cf = expressionExtensionsX.calledFeature(it);
      final String suffix;
      if ("eContainer".equals(cf)) {
        suffix = "eContainer";
      } else if ("isEmpty".equals(cf)) {
        suffix = "isEmpty";
      } else {
        suffix = featureCallName(toFirstUpper(cf));
      }
      return featureCallTarget(it, ctx) + "." + suffix + "()";
    }
  }

  // TODO: actually, we should look at the feature and return "is" only if it has a boolean value... Can this be done??
  public String featureCallName(final String it) {
    if (it.startsWith("^")) {
      return featureCallName(toFirstUpper(it.substring(1, it.length())));
    } else {
      return (it.startsWith("Is") ? "is" : "get") + it;
    }
  }

  public boolean isSimpleFeatureCall(final Expression it, final CompilationContext ctx) {
    return false;
  }

  public boolean isSimpleFeatureCall(final FeatureCall it, final CompilationContext ctx) {
    return it.eClass().getName().contains("FeatureCall") && it.getName() == null && isFeature(it.getType()) && (it.getTarget() == null || isVariable(it.getTarget(), ctx) || isThisCall(it.getTarget()));
  }

  // dispatch isSimpleNavigation
  protected boolean _isSimpleNavigation(final Expression it, final CompilationContext ctx) {
    return false;
  }

  protected boolean _isSimpleNavigation(final TypeSelectExpression it, final CompilationContext ctx) {
    return true;
  }

  protected boolean _isSimpleNavigation(final FeatureCall it, final CompilationContext ctx) {
    return it.getName() == null && isFeature(it.getType()) && (it.getTarget() == null || isVariable(it.getTarget(), ctx) || isThisCall(it.getTarget()) || isSimpleNavigation(it.getTarget(), ctx));
  }

  public boolean isSimpleNavigation(final Expression it, final CompilationContext ctx) {
    if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _isSimpleNavigation(typeSelectExpression, ctx);
    } else if (it instanceof FeatureCall featureCall) {
      return _isSimpleNavigation(featureCall, ctx);
    } else if (it != null) {
      return _isSimpleNavigation(it, ctx);
    } else {
      return false;
    }
  }

  // dispatch navigationRoot
  protected String _navigationRoot(final Void it, final CompilationContext ctx) {
    return "";
  }

  protected String _navigationRootExpression(final Expression it, final CompilationContext ctx) {
    return "";
  }

  protected String _navigationRoot(final FeatureCall it, final CompilationContext ctx) {
    if (it.getTarget() != null) {
      return navigationRoot(it.getTarget(), ctx);
    } else {
      if (isVariable(it, ctx)) {
        return javaExpression(it, ctx);
      } else {
        return ctx.getImplicitVariable();
      }
    }
  }

  public String navigationRoot(final Expression it, final CompilationContext ctx) {
    if (it instanceof FeatureCall featureCall) {
      return _navigationRoot(featureCall, ctx);
    } else if (it != null) {
      return _navigationRootExpression(it, ctx);
    } else {
      return _navigationRoot((Void) null, ctx);
    }
  }

  // dispatch navigations
  protected List<String> _navigationsVoid(final Void it, final CompilationContext ctx) {
    return new ArrayList<>();
  }

  protected List<String> _navigationsExpression(final Expression it, final CompilationContext ctx) {
    return new ArrayList<>();
  }

  protected List<String> _navigations(final FeatureCall it, final CompilationContext ctx) {
    final List<String> navs = navigations(it.getTarget(), ctx);
    if (navs.isEmpty() && (isThisCall(it) || isVariable(it, ctx))) {
      return List.of();
    } else {
      navs.add(expressionExtensionsX.calledFeature(it));
      return navs;
    }
  }

  protected List<String> _navigations(final TypeSelectExpression it, final CompilationContext ctx) {
    final List<String> navs = navigations(it.getTarget(), ctx);
    navs.add("typeSelect(" + qualifiedTypeName(it.getType(), ctx) + ")");
    return navs;
  }

  public List<String> navigations(final Expression it, final CompilationContext ctx) {
    if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _navigations(typeSelectExpression, ctx);
    } else if (it instanceof FeatureCall featureCall) {
      return _navigations(featureCall, ctx);
    } else if (it != null) {
      return _navigationsExpression(it, ctx);
    } else {
      return _navigationsVoid(null, ctx);
    }
  }

  //////////////////////////////////////////////////
  // OPERATION CALLS
  //////////////////////////////////////////////////
  // TODO handle eClass()
  // TODO work out if 'this' should be added or not
  protected String _javaExpression(final OperationCall it, final CompilationContext ctx) {
    if ((it.getTarget() == null || isThisCall(it.getTarget())) && ctx.targetHasOperation(it)) {
      final List<String> mapped = new ArrayList<>();
      for (final Expression p : it.getParams()) {
        mapped.add(javaExpression(p, ctx));
      }
      return (it.getTarget() != null ? javaExpression(it.getTarget(), ctx) + "." : "") + it.getName() + "(" + join(", ", mapped) + ")";
    } else if (isJavaExtensionCall(it, ctx)) {
      final List<Expression> allParams;
      if (it.getTarget() != null) {
        allParams = new ArrayList<>();
        allParams.add(it.getTarget());
        allParams.addAll(it.getParams());
      } else {
        allParams = it.getParams();
      }
      final List<String> mapped = new ArrayList<>();
      for (final Expression p : allParams) {
        mapped.add(javaExpression(p, ctx));
      }
      return calledJavaMethod(it, ctx) + "(" + join(", ", mapped) + ")";
    } else if (expressionExtensionsX.isArithmeticOperatorCall(it, ctx)) {
      final List<String> mapped = new ArrayList<>();
      for (final Expression e : it.getParams()) {
        mapped.add(javaExpression(e, ctx));
      }
      return autoBracket(it, join(" " + it.getName() + " ", mapped), ctx);
    } else if (expressionExtensionsX.isSimpleConcatCall(it)) {
      final List<String> mapped = new ArrayList<>();
      for (final Expression e : it.getParams()) {
        mapped.add(javaExpression(e, ctx));
      }
      return join(" + ", mapped);
    } else if (expressionExtensionsX.isPrefixExpression(it, ctx)) {
      return autoBracket(it, it.getName() + javaExpression(it.getParams().get(0), ctx), ctx);
    } else if ("first".equals(it.getName()) && it.getParams().isEmpty() && it.getTarget() != null) {
      return javaExpression(it.getTarget(), ctx) + ".get(0)";
    } else if ("isInstance".equals(it.getName()) && it.getParams().size() == 1 && it.getTarget() instanceof FeatureCall && isType((FeatureCall) it.getTarget(), ctx)) {
      return autoBracket(it, javaExpression(it.getParams().get(0), ctx) + " instanceof " + javaExpression(it.getTarget(), ctx), ctx);
    } else if ("eContainer".equals(it.getName()) && it.getParams().isEmpty()) {
      return javaExpression(it.getTarget(), ctx) + ".eContainer()";
    } else if (ctx.isExtension(it.getName())) {
      return notCompilable(it);
    } else {
      final List<String> mapped = new ArrayList<>();
      for (final Expression p : it.getParams()) {
        mapped.add(javaExpression(p, ctx));
      }
      return (it.getTarget() != null ? javaExpression(it.getTarget(), ctx) + "." : "") + it.getName() + "(" + (it.getParams().isEmpty() ? "" : join(", ", mapped)) + ")";
    }
  }

  public boolean isJavaExtensionCall(final Expression it) {
    return false;
  }

  public boolean isJavaExtensionCall(final OperationCall it, final CompilationContext ctx) {
    return !("isInstance".equals(it.getName())) && isSimpleCall(it, ctx) && calledJavaMethod(it, ctx) != null;
  }

  public boolean isSimpleCall(final OperationCall it, final CompilationContext ctx) {
    return (it.getTarget() == null || isCompilable(it.getTarget(), ctx)) && it.getParams().stream().allMatch(p -> isCompilable(p, ctx));
  }

  public String calledJavaMethod(final OperationCall it, final CompilationContext ctx) {
    return ctx.getCalledJavaMethod(it);
  }

  //////////////////////////////////////////////////
  // EXPRESSION BRACKETING
  //////////////////////////////////////////////////
  public String autoBracket(final Expression it, final String javaCode, final CompilationContext ctx) {
    if (requiresBracketing(it, ctx)) {
      return "(" + javaCode + ")";
    } else {
      return javaCode;
    }
  }

  // dispatch requiresBracketing (1 param: Expression, ctx)
  protected boolean _requiresBracketing(final Expression it, final CompilationContext ctx) {
    return (expressionExtensionsX.isPrefixExpression(it, ctx) || expressionExtensionsX.isInfixExpression(it, ctx)) && it.eContainer() != null && requiresBracketing(it, it.eContainer(), ctx);
  }

  protected boolean _requiresBracketing(final Literal it, final CompilationContext ctx) {
    return false;
  }

  public boolean requiresBracketing(final Expression it, final CompilationContext ctx) {
    if (it instanceof Literal literal) {
      return _requiresBracketing(literal, ctx);
    } else if (it != null) {
      return _requiresBracketing(it, ctx);
    } else {
      return false;
    }
  }

  // dispatch requiresBracketing (2 params: Expression, parent, ctx)
  protected boolean _requiresBracketingWithObject(final Expression it, final Object parent, final CompilationContext ctx) {
    return false;
  }

  protected boolean _requiresBracketingWithExpression(final Expression it, final Expression parent, final CompilationContext ctx) {
    return expressionExtensionsX.isPrefixExpression(it, ctx) && expressionExtensionsX.isPrefixExpression(parent, ctx)
        || (expressionExtensionsX.isInfixExpression(it, ctx) && (expressionExtensionsX.isPrefixExpression(parent, ctx) || expressionExtensionsX.isInfixExpression(parent, ctx)));
  }

  protected boolean _requiresBracketing(final OperationCall it, final OperationCall parent, final CompilationContext ctx) {
    return expressionExtensionsX.isPrefixExpression(it, ctx) && expressionExtensionsX.isPrefixExpression(parent, ctx)
        || (expressionExtensionsX.isInfixExpression(it, ctx) && (expressionExtensionsX.isPrefixExpression(parent, ctx) || (expressionExtensionsX.isInfixExpression(parent, ctx) && !it.getName().equals(parent.getName()))));
  }

  protected boolean _requiresBracketing(final BooleanOperation it, final BooleanOperation parent, final CompilationContext ctx) {
    return !it.getOperator().equals(parent.getOperator());
  }

  public boolean requiresBracketing(final Expression it, final Object parent, final CompilationContext ctx) {
    if (it instanceof OperationCall operationCall && parent instanceof OperationCall parentOp) {
      return _requiresBracketing(operationCall, parentOp, ctx);
    } else if (it instanceof BooleanOperation boolOp && parent instanceof BooleanOperation parentBool) {
      return _requiresBracketing(boolOp, parentBool, ctx);
    } else if (it instanceof Expression && parent instanceof Expression parentExpr) {
      return _requiresBracketingWithExpression(it, parentExpr, ctx);
    } else {
      return _requiresBracketingWithObject(it, parent, ctx);
    }
  }

  //////////////////////////////////////////////////
  // HELPER FUNCTIONS
  //////////////////////////////////////////////////
  // dispatch isThisCall
  protected boolean _isThisCall(final Expression it) {
    return false;
  }

  protected boolean _isThisCall(final FeatureCall it) {
    return it.getName() == null && isThis(it.getType());
  }

  public boolean isThisCall(final Expression it) {
    if (it instanceof FeatureCall featureCall) {
      return _isThisCall(featureCall);
    } else if (it != null) {
      return _isThisCall(it);
    } else {
      return false;
    }
  }

  public boolean isFeature(final Identifier it) {
    return it.getId() != null && it.getId().size() == 1;
  }

  // dispatch isThis
  protected boolean _isThis(final Expression it) {
    return false;
  }

  protected boolean _isThis(final Identifier it) {
    return it.getId() != null && it.getId().size() == 1 && "this".equals(it.getId().get(0));
  }

  public boolean isThis(final SyntaxElement it) {
    if (it instanceof Identifier identifier) {
      return _isThis(identifier);
    } else if (it instanceof Expression expression) {
      return _isThis(expression);
    } else {
      return false;
    }
  }

  public String qualifiedTypeName(final Identifier it, final CompilationContext ctx) {
    if (it.getId().size() == 2) {
      return it.getId().get(0) + "::" + it.getId().get(1);
    } else {
      return qualifiedTypeName(ctx, it.getId().get(0));
    }
  }

  public /*cached*/ String qualifiedTypeName(final CompilationContext it, final String name) {
    return it.getQualifiedTypeName(name);
  }

  // dispatch javaEncode
  protected String _javaEncode(final Expression it) {
    return javaEncode(expressionExtensionsX.serialize(it));
  }

  protected String _javaEncode(final String it) {
    return org.eclipse.xtext.util.Strings.convertToJavaString(it);
  }

  public String javaEncode(final Object it) {
    if (it instanceof String s) {
      return _javaEncode(s);
    } else if (it instanceof Expression expr) {
      return _javaEncode(expr);
    } else {
      return "";
    }
  }

  public String join(final String separator, final List<String> strings) {
    if (strings.isEmpty()) {
      return "";
    } else {
      return internalJoin(separator, strings);
    }
  }

  private String internalJoin(final String separator, final List<String> strings) {
    return org.eclipse.xtext.util.Strings.concat(separator, strings);
  }

  public String join(final String separator, final String strings) {
    return strings;
  }

  public String join(final String separator, final Void strings) {
    return "";
  }

  // Public dispatcher for javaExpression
  public String javaExpression(final SyntaxElement it, final CompilationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _javaExpression(operationCall, ctx);
    } else if (it instanceof CollectionExpression collectionExpression) {
      return _javaExpression(collectionExpression, ctx);
    } else if (it instanceof TypeSelectExpression typeSelectExpression) {
      return _javaExpression(typeSelectExpression, ctx);
    } else if (it instanceof FeatureCall featureCall) {
      return _javaExpression(featureCall, ctx);
    } else if (it instanceof BooleanOperation booleanOperation) {
      return _javaExpression(booleanOperation, ctx);
    } else if (it instanceof CastedExpression castedExpression) {
      return _javaExpression(castedExpression, ctx);
    } else if (it instanceof IfExpression ifExpression) {
      return _javaExpression(ifExpression, ctx);
    } else if (it instanceof StringLiteral stringLiteral) {
      return _javaExpression(stringLiteral, ctx);
    } else if (it instanceof BooleanLiteral booleanLiteral) {
      return _javaExpression(booleanLiteral, ctx);
    } else if (it instanceof IntegerLiteral integerLiteral) {
      return _javaExpression(integerLiteral, ctx);
    } else if (it instanceof NullLiteral nullLiteral) {
      return _javaExpression(nullLiteral, ctx);
    } else if (it instanceof RealLiteral realLiteral) {
      return _javaExpression(realLiteral, ctx);
    } else if (it instanceof ListLiteral listLiteral) {
      return _javaExpression(listLiteral, ctx);
    } else if (it instanceof Identifier identifier) {
      return _javaExpression(identifier, ctx);
    } else if (it instanceof Expression expression) {
      return _javaExpression(expression, ctx);
    } else if (it != null) {
      return "";
    } else {
      return _javaExpression((Void) null, ctx);
    }
  }

  private String toFirstUpper(final String s) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    return Character.toUpperCase(s.charAt(0)) + s.substring(1);
  }
}
