/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.expression;

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;
import com.avaloq.tools.ddk.xtext.expression.expression.IfExpression;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionExtensions;
import com.google.common.base.Objects;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ExpressionExtensionsX {
  protected String _serialize(final EObject it) {
    return ExpressionExtensions.serialize(it);
  }
  
  protected String _serialize(final Void it) {
    return null;
  }
  
  protected boolean _isEmptyList(final Expression it) {
    return false;
  }
  
  protected boolean _isEmptyList(final ListLiteral it) {
    EList<Expression> _elements = it.getElements();
    return _elements.isEmpty();
  }
  
  public boolean isSimpleConcatCall(final OperationCall it) {
    return (((Objects.equal(it.getName(), "+") && Objects.equal(it.getType(), null)) && Objects.equal(it.getTarget(), null)) && (!it.getParams().isEmpty()));
  }
  
  public boolean isNumber(final Expression it, final CompilationContext ctx) {
    Type _findType = ctx.findType("Real");
    Type _analyze = ctx.analyze(it);
    return _findType.isAssignableFrom(_analyze);
  }
  
  protected boolean _isArithmeticOperatorCall(final OperationCall it, final CompilationContext ctx) {
    return ((((Objects.equal(it.getType(), null) && Objects.equal(it.getTarget(), null)) && (it.getParams().size() > 1)) && (((Objects.equal(it.getName(), "+") || Objects.equal(it.getName(), "-")) || Objects.equal(it.getName(), "*")) || Objects.equal(it.getName(), "/"))) && IterableExtensions.<Expression>forall(it.getParams(), ((Function1<Expression, Boolean>) (Expression p) -> {
      return Boolean.valueOf(this.isNumber(p, ctx));
    })));
  }
  
  protected boolean _isArithmeticOperatorCall(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _isPrefixExpression(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _isPrefixExpression(final OperationCall it, final CompilationContext ctx) {
    return (((Objects.equal(it.getType(), null) && Objects.equal(it.getTarget(), null)) && (it.getParams().size() == 1)) && (Objects.equal(it.getName(), "-") || Objects.equal(it.getName(), "!")));
  }
  
  protected boolean _isInfixExpression(final Void it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _isInfixExpression(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _isInfixExpression(final OperationCall it, final CompilationContext ctx) {
    return (this.isArithmeticOperatorCall(it, ctx) || Objects.equal("isInstance", it.getName()));
  }
  
  protected boolean _isInfixExpression(final IfExpression it, final CompilationContext ctx) {
    return true;
  }
  
  protected boolean _isInfixExpression(final BooleanOperation it, final CompilationContext ctx) {
    return true;
  }
  
  public String calledFeature(final FeatureCall it) {
    Identifier _type = it.getType();
    EList<String> _id = _type.getId();
    return IterableExtensions.<String>head(_id);
  }
  
  public String serialize(final EObject it) {
    if (it != null) {
      return _serialize(it);
    } else if (it == null) {
      return _serialize((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
  
  public boolean isEmptyList(final Expression it) {
    if (it instanceof ListLiteral) {
      return _isEmptyList((ListLiteral)it);
    } else if (it != null) {
      return _isEmptyList(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
  
  public boolean isArithmeticOperatorCall(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall) {
      return _isArithmeticOperatorCall((OperationCall)it, ctx);
    } else if (it != null) {
      return _isArithmeticOperatorCall(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public boolean isPrefixExpression(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall) {
      return _isPrefixExpression((OperationCall)it, ctx);
    } else if (it != null) {
      return _isPrefixExpression(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public boolean isInfixExpression(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall) {
      return _isInfixExpression((OperationCall)it, ctx);
    } else if (it instanceof BooleanOperation) {
      return _isInfixExpression((BooleanOperation)it, ctx);
    } else if (it instanceof IfExpression) {
      return _isInfixExpression((IfExpression)it, ctx);
    } else if (it != null) {
      return _isInfixExpression(it, ctx);
    } else if (it == null) {
      return _isInfixExpression((Void)null, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
}
