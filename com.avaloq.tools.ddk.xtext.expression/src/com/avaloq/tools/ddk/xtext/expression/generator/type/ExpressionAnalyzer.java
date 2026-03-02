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
package com.avaloq.tools.ddk.xtext.expression.generator.type;

import java.util.regex.Pattern;

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;


/**
 * Simple expression type analyzer that infers types from the Expression AST.
 * Replaces the functionality of {@code org.eclipse.xtend.expression.ExpressionFacade.analyze()}.
 */
@SuppressWarnings("nls")
public final class ExpressionAnalyzer {

  private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");
  private static final Pattern REAL_PATTERN = Pattern.compile("-?\\d+\\.\\d+");

  private ExpressionAnalyzer() {
    // Utility class
  }

  /**
   * Analyzes the given Expression AST node and returns its inferred type.
   *
   * @param expression
   *          the expression to analyze
   * @param context
   *          the execution context for type resolution
   * @return the inferred type
   */
  public static XtendType analyze(final Expression expression, final XtendExecutionContext context) {
    if (expression instanceof IntegerLiteral) {
      return PrimitiveXtendType.INTEGER;
    } else if (expression instanceof RealLiteral) {
      return PrimitiveXtendType.REAL;
    } else if (expression instanceof StringLiteral) {
      return PrimitiveXtendType.STRING;
    } else if (expression instanceof BooleanLiteral || expression instanceof BooleanOperation) {
      return PrimitiveXtendType.BOOLEAN;
    } else if (expression instanceof NullLiteral) {
      return PrimitiveXtendType.VOID;
    } else if (expression instanceof ListLiteral) {
      return PrimitiveXtendType.LIST;
    } else if (expression instanceof IfExpression) {
      return analyzeIfExpression((IfExpression) expression, context);
    } else if (expression instanceof OperationCall) {
      return analyzeOperationCall((OperationCall) expression, context);
    }
    return PrimitiveXtendType.OBJECT;
  }

  /**
   * Analyzes a string expression and returns its inferred type.
   * This is a simplified replacement for ExpressionFacade.analyze().
   *
   * @param expression
   *          the expression string
   * @return the inferred type
   */
  public static XtendType analyzeString(final String expression) {
    if (expression == null || expression.isEmpty()) {
      return PrimitiveXtendType.OBJECT;
    }
    String trimmed = expression.trim();

    // String literal
    if ((trimmed.startsWith("'") && trimmed.endsWith("'")) || (trimmed.startsWith("\"") && trimmed.endsWith("\""))) {
      return PrimitiveXtendType.STRING;
    }
    // Boolean literal
    if ("true".equals(trimmed) || "false".equals(trimmed)) {
      return PrimitiveXtendType.BOOLEAN;
    }
    // Null literal
    if ("null".equals(trimmed)) {
      return PrimitiveXtendType.VOID;
    }
    // Integer literal
    if (INTEGER_PATTERN.matcher(trimmed).matches()) {
      return PrimitiveXtendType.INTEGER;
    }
    // Real literal
    if (REAL_PATTERN.matcher(trimmed).matches()) {
      return PrimitiveXtendType.REAL;
    }
    // Compound expressions with operators
    if (containsRealOperand(trimmed)) {
      return PrimitiveXtendType.REAL;
    }
    if (containsArithmeticOperator(trimmed) && !containsStringOperand(trimmed)) {
      return PrimitiveXtendType.INTEGER;
    }
    if (containsStringOperand(trimmed)) {
      return PrimitiveXtendType.STRING;
    }

    return PrimitiveXtendType.OBJECT;
  }

  private static XtendType analyzeIfExpression(final IfExpression expr, final XtendExecutionContext context) {
    if (expr.getThenPart() != null) {
      return analyze(expr.getThenPart(), context);
    }
    return PrimitiveXtendType.OBJECT;
  }

  private static XtendType analyzeOperationCall(final OperationCall expr, final XtendExecutionContext context) {
    String name = expr.getName();
    if (name == null) {
      return PrimitiveXtendType.OBJECT;
    }
    // Arithmetic operators
    if ("+".equals(name) || "-".equals(name) || "*".equals(name) || "/".equals(name)) {
      if (expr.getParams() != null && !expr.getParams().isEmpty()) {
        boolean hasReal = false;
        boolean hasString = false;
        for (Expression param : expr.getParams()) {
          XtendType paramType = analyze(param, context);
          if (PrimitiveXtendType.REAL.equals(paramType)) {
            hasReal = true;
          }
          if (PrimitiveXtendType.STRING.equals(paramType)) {
            hasString = true;
          }
        }
        if ("+".equals(name) && hasString) {
          return PrimitiveXtendType.STRING;
        }
        return hasReal ? PrimitiveXtendType.REAL : PrimitiveXtendType.INTEGER;
      }
    }
    // Boolean operators
    if ("!".equals(name) || "&&".equals(name) || "||".equals(name)) {
      return PrimitiveXtendType.BOOLEAN;
    }
    // Comparison operators
    if ("==".equals(name) || "!=".equals(name) || ">".equals(name) || "<".equals(name)
        || ">=".equals(name) || "<=".equals(name)) {
      return PrimitiveXtendType.BOOLEAN;
    }
    return PrimitiveXtendType.OBJECT;
  }

  private static boolean containsRealOperand(final String expression) {
    return REAL_PATTERN.matcher(expression).find();
  }

  private static boolean containsArithmeticOperator(final String expression) {
    return expression.contains(" + ") || expression.contains(" - ") || expression.contains(" * ") || expression.contains(" / ");
  }

  private static boolean containsStringOperand(final String expression) {
    return expression.contains("'") || expression.contains("\"");
  }
}
