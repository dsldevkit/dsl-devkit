/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.expression.generator;

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import org.eclipse.emf.ecore.EObject;

public class ExpressionExtensionsX {

  protected String _serialize(final EObject it) {
    return ExpressionExtensions.serialize(it);
  }

  protected String _serialize(final Void it) {
    return null;
  }

  public String serialize(final Object it) {
    if (it == null) {
      return _serialize((Void) null);
    } else if (it instanceof EObject eObject) {
      return _serialize(eObject);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  protected boolean _isEmptyList(final Expression it) {
    return false;
  }

  protected boolean _isEmptyList(final ListLiteral it) {
    return it.getElements().isEmpty();
  }

  public boolean isEmptyList(final Expression it) {
    if (it instanceof ListLiteral listLiteral) {
      return _isEmptyList(listLiteral);
    } else {
      return _isEmptyList(it);
    }
  }

  public boolean isSimpleConcatCall(final OperationCall it) {
    return "+".equals(it.getName()) && it.getType() == null && it.getTarget() == null && !it.getParams().isEmpty();
  }

  public boolean isNumber(final Expression it, final CompilationContext ctx) {
    return ctx.findType("Real").isAssignableFrom(ctx.analyze(it));
  }

  protected boolean _isArithmeticOperatorCall(final OperationCall it, final CompilationContext ctx) {
    return it.getType() == null && it.getTarget() == null && it.getParams().size() > 1
        && ("+".equals(it.getName()) || "-".equals(it.getName()) || "*".equals(it.getName()) || "/".equals(it.getName()))
        && it.getParams().stream().allMatch(p -> isNumber(p, ctx));
  }

  protected boolean _isArithmeticOperatorCall(final Expression it, final CompilationContext ctx) {
    return false;
  }

  public boolean isArithmeticOperatorCall(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _isArithmeticOperatorCall(operationCall, ctx);
    } else {
      return _isArithmeticOperatorCall(it, ctx);
    }
  }

  protected boolean _isPrefixExpression(final Expression it, final CompilationContext ctx) {
    return false;
  }

  protected boolean _isPrefixExpression(final OperationCall it, final CompilationContext ctx) {
    return it.getType() == null && it.getTarget() == null && it.getParams().size() == 1
        && ("-".equals(it.getName()) || "!".equals(it.getName()));
  }

  public boolean isPrefixExpression(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _isPrefixExpression(operationCall, ctx);
    } else {
      return _isPrefixExpression(it, ctx);
    }
  }

  protected boolean _isInfixExpression(final Void it, final CompilationContext ctx) {
    return false;
  }

  protected boolean _isInfixExpression(final Expression it, final CompilationContext ctx) {
    return false;
  }

  protected boolean _isInfixExpression(final OperationCall it, final CompilationContext ctx) {
    return isArithmeticOperatorCall(it, ctx) || "isInstance".equals(it.getName());
  }

  protected boolean _isInfixExpression(final IfExpression it, final CompilationContext ctx) {
    return true;
  }

  protected boolean _isInfixExpression(final BooleanOperation it, final CompilationContext ctx) {
    return true;
  }

  public boolean isInfixExpression(final Expression it, final CompilationContext ctx) {
    if (it == null) {
      return _isInfixExpression((Void) null, ctx);
    } else if (it instanceof BooleanOperation booleanOperation) {
      return _isInfixExpression(booleanOperation, ctx);
    } else if (it instanceof IfExpression ifExpression) {
      return _isInfixExpression(ifExpression, ctx);
    } else if (it instanceof OperationCall operationCall) {
      return _isInfixExpression(operationCall, ctx);
    } else {
      return _isInfixExpression(it, ctx);
    }
  }

  public String calledFeature(final FeatureCall it) {
    return it.getType().getId().get(0);
  }

}
