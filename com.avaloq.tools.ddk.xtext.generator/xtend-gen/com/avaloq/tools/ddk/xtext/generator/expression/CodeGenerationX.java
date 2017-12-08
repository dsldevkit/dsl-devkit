/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.expression;

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
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionExtensionsX;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CodeGenerationX {
  @Extension
  private ExpressionExtensionsX _expressionExtensionsX = new ExpressionExtensionsX();
  
  public boolean isCompilable(final Expression it, final CompilationContext ctx) {
    boolean _xblockexpression = false;
    {
      final String expr = this.javaExpression(it, ctx);
      _xblockexpression = ((!Objects.equal(expr, null)) && (!expr.contains("/* NOT COMPILABLE: ")));
    }
    return _xblockexpression;
  }
  
  protected String _javaExpression(final Void it, final CompilationContext ctx) {
    return "";
  }
  
  protected String _javaExpression(final Expression it, final CompilationContext ctx) {
    return this.notCompilable(it);
  }
  
  public String notCompilable(final Expression it) {
    String _serialize = this._expressionExtensionsX.serialize(it);
    String _plus = ("/* NOT COMPILABLE: Complex expressions like \"" + _serialize);
    return (_plus + "\" cannot be translated to Java. Consider rewriting the expression or using a JAVA extension. */");
  }
  
  protected String _javaExpression(final StringLiteral it, final CompilationContext ctx) {
    String _val = it.getVal();
    String _javaEncode = this.javaEncode(_val);
    String _plus = ("\"" + _javaEncode);
    return (_plus + "\"");
  }
  
  protected String _javaExpression(final BooleanLiteral it, final CompilationContext ctx) {
    return it.getVal();
  }
  
  protected String _javaExpression(final IntegerLiteral it, final CompilationContext ctx) {
    int _val = it.getVal();
    return Integer.valueOf(_val).toString();
  }
  
  protected String _javaExpression(final NullLiteral it, final CompilationContext ctx) {
    return "null";
  }
  
  protected String _javaExpression(final RealLiteral it, final CompilationContext ctx) {
    String _val = it.getVal();
    return _val.toString();
  }
  
  protected String _javaExpression(final ListLiteral it, final CompilationContext ctx) {
    String _xifexpression = null;
    EList<Expression> _elements = it.getElements();
    boolean _isEmpty = _elements.isEmpty();
    if (_isEmpty) {
      Type _requiredType = ctx.getRequiredType();
      String _name = _requiredType.getName();
      String _javaType = ctx.javaType(_name);
      String _plus = ("java.util.Collections.<" + _javaType);
      _xifexpression = (_plus + "> emptyList()");
    } else {
      String _xifexpression_1 = null;
      EList<Expression> _elements_1 = it.getElements();
      int _size = _elements_1.size();
      boolean _equals = (_size == 1);
      if (_equals) {
        EList<Expression> _elements_2 = it.getElements();
        Expression _head = IterableExtensions.<Expression>head(_elements_2);
        String _javaExpression = this.javaExpression(_head, ctx);
        String _plus_1 = ("java.util.Collections.singletonList(" + _javaExpression);
        _xifexpression_1 = (_plus_1 + ")");
      } else {
        EList<Expression> _elements_3 = it.getElements();
        final Function1<Expression, String> _function = (Expression it_1) -> {
          return this.javaExpression(it_1, ctx);
        };
        List<String> _map = ListExtensions.<Expression, String>map(_elements_3, _function);
        String _join = this.join(", ", _map);
        String _plus_2 = ("com.google.common.collect.Lists.newArrayList(" + _join);
        _xifexpression_1 = (_plus_2 + ")");
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected String _javaExpression(final Identifier it, final CompilationContext ctx) {
    String _xifexpression = null;
    boolean _isThis = this.isThis(it);
    if (_isThis) {
      _xifexpression = ctx.getImplicitVariable();
    } else {
      EList<String> _id = it.getId();
      _xifexpression = this.join("::", _id);
    }
    return _xifexpression;
  }
  
  public boolean isType(final FeatureCall it, final CompilationContext ctx) {
    return ((Objects.equal(it.getName(), null) && (!Objects.equal(it.getType(), null))) && ctx.isType(this.javaExpression(it.getType(), ctx)));
  }
  
  public boolean isVariable(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  public boolean isVariable(final FeatureCall it, final CompilationContext ctx) {
    return ((Objects.equal(it.getTarget(), null) && Objects.equal(it.getName(), null)) && (ctx.isVariable(this.javaExpression(it.getType(), ctx))).booleanValue());
  }
  
  public String featureCallTarget(final FeatureCall it, final CompilationContext ctx) {
    String _xifexpression = null;
    if ((Objects.equal(it.getTarget(), null) || this.isThisCall(it.getTarget()))) {
      _xifexpression = ctx.getImplicitVariable();
    } else {
      Expression _target = it.getTarget();
      _xifexpression = this.javaExpression(_target, ctx);
    }
    return _xifexpression;
  }
  
  protected String _javaExpression(final BooleanOperation it, final CompilationContext ctx) {
    Expression _left = it.getLeft();
    String _javaExpression = this.javaExpression(_left, ctx);
    String _plus = (_javaExpression + " ");
    String _operator = it.getOperator();
    String _plus_1 = (_plus + _operator);
    String _plus_2 = (_plus_1 + " ");
    Expression _right = it.getRight();
    String _javaExpression_1 = this.javaExpression(_right, ctx);
    String _plus_3 = (_plus_2 + _javaExpression_1);
    return this.autoBracket(it, _plus_3, ctx);
  }
  
  protected String _javaExpression(final CollectionExpression it, final CompilationContext ctx) {
    String _xifexpression = null;
    String _name = it.getName();
    boolean _equals = Objects.equal("select", _name);
    if (_equals) {
      Expression _target = it.getTarget();
      String _javaExpression = this.javaExpression(_target, ctx);
      String _plus = ("com.google.common.collect.Iterables.filter(" + _javaExpression);
      String _plus_1 = (_plus + 
        ", new com.google.common.base.Predicate<Object>() { public boolean apply(Object ");
      String _xifexpression_1 = null;
      String _var = it.getVar();
      boolean _notEquals = (!Objects.equal(_var, null));
      if (_notEquals) {
        _xifexpression_1 = it.getVar();
      } else {
        _xifexpression_1 = "e";
      }
      String _plus_2 = (_plus_1 + _xifexpression_1);
      String _plus_3 = (_plus_2 + ") {return ");
      Expression _exp = it.getExp();
      String _javaExpression_1 = this.javaExpression(_exp, ctx);
      String _plus_4 = (_plus_3 + _javaExpression_1);
      _xifexpression = (_plus_4 + ";} })");
    } else {
      _xifexpression = this.notCompilable(it);
    }
    return _xifexpression;
  }
  
  protected String _javaExpression(final TypeSelectExpression it, final CompilationContext ctx) {
    String _xifexpression = null;
    boolean _isSimpleNavigation = this.isSimpleNavigation(it, ctx);
    if (_isSimpleNavigation) {
      Expression _target = it.getTarget();
      String _javaExpression = this.javaExpression(_target, ctx);
      String _plus = ("com.google.common.collect.Iterables.filter(" + _javaExpression);
      String _plus_1 = (_plus + ", ");
      Identifier _type = it.getType();
      String _javaExpression_1 = this.javaExpression(_type, ctx);
      String _javaType = ctx.javaType(_javaExpression_1);
      String _plus_2 = (_plus_1 + _javaType);
      _xifexpression = (_plus_2 + ".class)");
    } else {
      _xifexpression = this.notCompilable(it);
    }
    return _xifexpression;
  }
  
  protected String _javaExpression(final CastedExpression it, final CompilationContext ctx) {
    Identifier _type = it.getType();
    String _javaExpression = this.javaExpression(_type, ctx);
    String _javaType = ctx.javaType(_javaExpression);
    String _plus = ("((" + _javaType);
    String _plus_1 = (_plus + ") ");
    Expression _target = it.getTarget();
    String _javaExpression_1 = this.javaExpression(_target, ctx);
    String _plus_2 = (_plus_1 + _javaExpression_1);
    return (_plus_2 + ")");
  }
  
  protected String _javaExpression(final IfExpression it, final CompilationContext ctx) {
    Expression _condition = it.getCondition();
    String _javaExpression = this.javaExpression(_condition, ctx);
    String _plus = (_javaExpression + " ? ");
    Expression _thenPart = it.getThenPart();
    String _javaExpression_1 = this.javaExpression(_thenPart, ctx);
    String _plus_1 = (_plus + _javaExpression_1);
    String _plus_2 = (_plus_1 + " : ");
    Expression _elsePart = it.getElsePart();
    String _javaExpression_2 = this.javaExpression(_elsePart, ctx);
    String _plus_3 = (_plus_2 + _javaExpression_2);
    return this.autoBracket(it, _plus_3, ctx);
  }
  
  protected String _javaExpression(final FeatureCall it, final CompilationContext ctx) {
    String _xifexpression = null;
    boolean _isThisCall = this.isThisCall(it);
    if (_isThisCall) {
      _xifexpression = ctx.getImplicitVariable();
    } else {
      String _xifexpression_1 = null;
      boolean _isVariable = this.isVariable(it, ctx);
      if (_isVariable) {
        Identifier _type = it.getType();
        _xifexpression_1 = this.javaExpression(_type, ctx);
      } else {
        String _xifexpression_2 = null;
        boolean _isType = this.isType(it, ctx);
        if (_isType) {
          Identifier _type_1 = it.getType();
          String _javaExpression = this.javaExpression(_type_1, ctx);
          _xifexpression_2 = ctx.javaType(_javaExpression);
        } else {
          String _xifexpression_3 = null;
          boolean _isSimpleFeatureCall = this.isSimpleFeatureCall(it, ctx);
          if (_isSimpleFeatureCall) {
            String _featureCallTarget = this.featureCallTarget(it, ctx);
            String _plus = (_featureCallTarget + ".");
            String _xifexpression_4 = null;
            String _calledFeature = this._expressionExtensionsX.calledFeature(it);
            boolean _equals = Objects.equal(_calledFeature, "eContainer");
            if (_equals) {
              _xifexpression_4 = "eContainer";
            } else {
              String _xifexpression_5 = null;
              String _calledFeature_1 = this._expressionExtensionsX.calledFeature(it);
              boolean _equals_1 = Objects.equal(_calledFeature_1, "isEmpty");
              if (_equals_1) {
                _xifexpression_5 = "isEmpty";
              } else {
                String _calledFeature_2 = this._expressionExtensionsX.calledFeature(it);
                String _firstUpper = StringExtensions.toFirstUpper(_calledFeature_2);
                _xifexpression_5 = this.featureCallName(_firstUpper);
              }
              _xifexpression_4 = _xifexpression_5;
            }
            String _plus_1 = (_plus + _xifexpression_4);
            _xifexpression_3 = (_plus_1 + "()");
          } else {
            String _xifexpression_6 = null;
            boolean _isSimpleNavigation = this.isSimpleNavigation(it, ctx);
            if (_isSimpleNavigation) {
              _xifexpression_6 = this.notCompilable(it);
            } else {
              String _featureCallTarget_1 = this.featureCallTarget(it, ctx);
              String _plus_2 = (_featureCallTarget_1 + ".");
              String _xifexpression_7 = null;
              String _calledFeature_3 = this._expressionExtensionsX.calledFeature(it);
              boolean _equals_2 = Objects.equal(_calledFeature_3, "eContainer");
              if (_equals_2) {
                _xifexpression_7 = "eContainer";
              } else {
                String _xifexpression_8 = null;
                String _calledFeature_4 = this._expressionExtensionsX.calledFeature(it);
                boolean _equals_3 = Objects.equal(_calledFeature_4, "isEmpty");
                if (_equals_3) {
                  _xifexpression_8 = "isEmpty";
                } else {
                  String _calledFeature_5 = this._expressionExtensionsX.calledFeature(it);
                  String _firstUpper_1 = StringExtensions.toFirstUpper(_calledFeature_5);
                  _xifexpression_8 = this.featureCallName(_firstUpper_1);
                }
                _xifexpression_7 = _xifexpression_8;
              }
              String _plus_3 = (_plus_2 + _xifexpression_7);
              _xifexpression_6 = (_plus_3 + "()");
            }
            _xifexpression_3 = _xifexpression_6;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public String featureCallName(final String it) {
    String _xifexpression = null;
    boolean _startsWith = it.startsWith("^");
    if (_startsWith) {
      int _length = it.length();
      String _substring = it.substring(1, _length);
      String _firstUpper = StringExtensions.toFirstUpper(_substring);
      _xifexpression = this.featureCallName(_firstUpper);
    } else {
      String _xifexpression_1 = null;
      boolean _startsWith_1 = it.startsWith("Is");
      if (_startsWith_1) {
        _xifexpression_1 = "is";
      } else {
        _xifexpression_1 = "get";
      }
      _xifexpression = (_xifexpression_1 + it);
    }
    return _xifexpression;
  }
  
  public boolean isSimpleFeatureCall(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  public boolean isSimpleFeatureCall(final FeatureCall it, final CompilationContext ctx) {
    return (((it.eClass().getName().contains("FeatureCall") && Objects.equal(it.getName(), null)) && this.isFeature(it.getType())) && ((Objects.equal(it.getTarget(), null) || this.isVariable(it.getTarget(), ctx)) || this.isThisCall(it.getTarget())));
  }
  
  protected boolean _isSimpleNavigation(final Expression it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _isSimpleNavigation(final TypeSelectExpression it, final CompilationContext ctx) {
    return true;
  }
  
  protected boolean _isSimpleNavigation(final FeatureCall it, final CompilationContext ctx) {
    return ((Objects.equal(it.getName(), null) && this.isFeature(it.getType())) && (((Objects.equal(it.getTarget(), null) || this.isVariable(it.getTarget(), ctx)) || this.isThisCall(it.getTarget())) || this.isSimpleNavigation(it.getTarget(), ctx)));
  }
  
  protected String _navigationRoot(final Void it, final CompilationContext ctx) {
    return "";
  }
  
  protected String _navigationRoot(final Expression it, final CompilationContext ctx) {
    return "";
  }
  
  protected String _navigationRoot(final FeatureCall it, final CompilationContext ctx) {
    String _xifexpression = null;
    Expression _target = it.getTarget();
    boolean _notEquals = (!Objects.equal(_target, null));
    if (_notEquals) {
      Expression _target_1 = it.getTarget();
      _xifexpression = this.navigationRoot(_target_1, ctx);
    } else {
      String _xifexpression_1 = null;
      boolean _isVariable = this.isVariable(it, ctx);
      if (_isVariable) {
        _xifexpression_1 = this.javaExpression(it, ctx);
      } else {
        _xifexpression_1 = ctx.getImplicitVariable();
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected List<String> _navigations(final Void it, final CompilationContext ctx) {
    return null;
  }
  
  protected List<String> _navigations(final Expression it, final CompilationContext ctx) {
    return null;
  }
  
  protected List<String> _navigations(final FeatureCall it, final CompilationContext ctx) {
    List<String> _xblockexpression = null;
    {
      Expression _target = it.getTarget();
      final List<String> navs = this.navigations(_target, ctx);
      List<String> _xifexpression = null;
      if ((navs.isEmpty() && (this.isThisCall(it) || this.isVariable(it, ctx)))) {
        _xifexpression = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList());
      } else {
        List<String> _xblockexpression_1 = null;
        {
          String _calledFeature = this._expressionExtensionsX.calledFeature(it);
          navs.add(_calledFeature);
          _xblockexpression_1 = navs;
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected List<String> _navigations(final TypeSelectExpression it, final CompilationContext ctx) {
    List<String> _xblockexpression = null;
    {
      Expression _target = it.getTarget();
      final List<String> navs = this.navigations(_target, ctx);
      Identifier _type = it.getType();
      String _qualifiedTypeName = this.qualifiedTypeName(_type, ctx);
      String _plus = ("typeSelect(" + _qualifiedTypeName);
      String _plus_1 = (_plus + ")");
      navs.add(_plus_1);
      _xblockexpression = navs;
    }
    return _xblockexpression;
  }
  
  protected String _javaExpression(final OperationCall it, final CompilationContext ctx) {
    String _xifexpression = null;
    if (((Objects.equal(it.getTarget(), null) || this.isThisCall(it.getTarget())) && ctx.targetHasOperation(it))) {
      String _xifexpression_1 = null;
      Expression _target = it.getTarget();
      boolean _notEquals = (!Objects.equal(_target, null));
      if (_notEquals) {
        Expression _target_1 = it.getTarget();
        String _javaExpression = this.javaExpression(_target_1, ctx);
        _xifexpression_1 = (_javaExpression + ".");
      } else {
        _xifexpression_1 = "";
      }
      String _name = it.getName();
      String _plus = (_xifexpression_1 + _name);
      String _plus_1 = (_plus + "(");
      EList<Expression> _params = it.getParams();
      final Function1<Expression, String> _function = (Expression it_1) -> {
        return this.javaExpression(it_1, ctx);
      };
      List<String> _map = ListExtensions.<Expression, String>map(_params, _function);
      String _join = this.join(", ", _map);
      String _plus_2 = (_plus_1 + _join);
      _xifexpression = (_plus_2 + ")");
    } else {
      String _xifexpression_2 = null;
      boolean _isJavaExtensionCall = this.isJavaExtensionCall(it, ctx);
      if (_isJavaExtensionCall) {
        String _calledJavaMethod = this.calledJavaMethod(it, ctx);
        String _plus_3 = (_calledJavaMethod + "(");
        List<Expression> _xifexpression_3 = null;
        Expression _target_2 = it.getTarget();
        boolean _notEquals_1 = (!Objects.equal(_target_2, null));
        if (_notEquals_1) {
          ArrayList<Expression> _xblockexpression = null;
          {
            Expression _target_3 = it.getTarget();
            final ArrayList<Expression> l = CollectionLiterals.<Expression>newArrayList(_target_3);
            EList<Expression> _params_1 = it.getParams();
            l.addAll(_params_1);
            _xblockexpression = l;
          }
          _xifexpression_3 = _xblockexpression;
        } else {
          _xifexpression_3 = it.getParams();
        }
        final Function1<Expression, String> _function_1 = (Expression it_1) -> {
          return this.javaExpression(it_1, ctx);
        };
        List<String> _map_1 = ListExtensions.<Expression, String>map(_xifexpression_3, _function_1);
        String _join_1 = this.join(", ", _map_1);
        String _plus_4 = (_plus_3 + _join_1);
        _xifexpression_2 = (_plus_4 + ")");
      } else {
        String _xifexpression_4 = null;
        boolean _isArithmeticOperatorCall = this._expressionExtensionsX.isArithmeticOperatorCall(it, ctx);
        if (_isArithmeticOperatorCall) {
          String _name_1 = it.getName();
          String _plus_5 = (" " + _name_1);
          String _plus_6 = (_plus_5 + " ");
          EList<Expression> _params_1 = it.getParams();
          final Function1<Expression, String> _function_2 = (Expression e) -> {
            return this.javaExpression(e, ctx);
          };
          List<String> _map_2 = ListExtensions.<Expression, String>map(_params_1, _function_2);
          String _join_2 = this.join(_plus_6, _map_2);
          _xifexpression_4 = this.autoBracket(it, _join_2, ctx);
        } else {
          String _xifexpression_5 = null;
          boolean _isSimpleConcatCall = this._expressionExtensionsX.isSimpleConcatCall(it);
          if (_isSimpleConcatCall) {
            EList<Expression> _params_2 = it.getParams();
            final Function1<Expression, String> _function_3 = (Expression e) -> {
              return this.javaExpression(e, ctx);
            };
            List<String> _map_3 = ListExtensions.<Expression, String>map(_params_2, _function_3);
            _xifexpression_5 = this.join(" + ", _map_3);
          } else {
            String _xifexpression_6 = null;
            boolean _isPrefixExpression = this._expressionExtensionsX.isPrefixExpression(it, ctx);
            if (_isPrefixExpression) {
              String _name_2 = it.getName();
              EList<Expression> _params_3 = it.getParams();
              Expression _head = IterableExtensions.<Expression>head(_params_3);
              String _javaExpression_1 = this.javaExpression(_head, ctx);
              String _plus_7 = (_name_2 + _javaExpression_1);
              _xifexpression_6 = this.autoBracket(it, _plus_7, ctx);
            } else {
              String _xifexpression_7 = null;
              if (((Objects.equal("first", it.getName()) && it.getParams().isEmpty()) && (!Objects.equal(it.getTarget(), null)))) {
                Expression _target_3 = it.getTarget();
                String _javaExpression_2 = this.javaExpression(_target_3, ctx);
                _xifexpression_7 = (_javaExpression_2 + ".get(0)");
              } else {
                String _xifexpression_8 = null;
                String _name_3 = it.getName();
                boolean _equals = Objects.equal("isInstance", _name_3);
                if (_equals) {
                  EList<Expression> _params_4 = it.getParams();
                  Expression _head_1 = IterableExtensions.<Expression>head(_params_4);
                  String _javaExpression_3 = this.javaExpression(_head_1, ctx);
                  String _plus_8 = (_javaExpression_3 + " instanceof ");
                  Expression _target_4 = it.getTarget();
                  String _serialize = this._expressionExtensionsX.serialize(_target_4);
                  String _javaType = ctx.javaType(_serialize);
                  String _plus_9 = (_plus_8 + _javaType);
                  _xifexpression_8 = this.autoBracket(it, _plus_9, ctx);
                } else {
                  String _xifexpression_9 = null;
                  if ((Objects.equal("eContainer", it.getName()) && it.getParams().isEmpty())) {
                    Expression _target_5 = it.getTarget();
                    String _javaExpression_4 = this.javaExpression(_target_5, ctx);
                    _xifexpression_9 = (_javaExpression_4 + ".eContainer()");
                  } else {
                    String _xifexpression_10 = null;
                    String _name_4 = it.getName();
                    Boolean _isExtension = ctx.isExtension(_name_4);
                    if ((_isExtension).booleanValue()) {
                      _xifexpression_10 = this.notCompilable(it);
                    } else {
                      String _xifexpression_11 = null;
                      Expression _target_6 = it.getTarget();
                      boolean _notEquals_2 = (!Objects.equal(_target_6, null));
                      if (_notEquals_2) {
                        Expression _target_7 = it.getTarget();
                        String _javaExpression_5 = this.javaExpression(_target_7, ctx);
                        _xifexpression_11 = (_javaExpression_5 + ".");
                      } else {
                        _xifexpression_11 = "";
                      }
                      String _name_5 = it.getName();
                      String _plus_10 = (_xifexpression_11 + _name_5);
                      String _plus_11 = (_plus_10 + "(");
                      String _xifexpression_12 = null;
                      EList<Expression> _params_5 = it.getParams();
                      boolean _isEmpty = _params_5.isEmpty();
                      if (_isEmpty) {
                        _xifexpression_12 = "";
                      } else {
                        EList<Expression> _params_6 = it.getParams();
                        final Function1<Expression, String> _function_4 = (Expression it_1) -> {
                          return this.javaExpression(it_1, ctx);
                        };
                        List<String> _map_4 = ListExtensions.<Expression, String>map(_params_6, _function_4);
                        _xifexpression_12 = this.join(", ", _map_4);
                      }
                      String _plus_12 = (_plus_11 + _xifexpression_12);
                      _xifexpression_10 = (_plus_12 + ")");
                    }
                    _xifexpression_9 = _xifexpression_10;
                  }
                  _xifexpression_8 = _xifexpression_9;
                }
                _xifexpression_7 = _xifexpression_8;
              }
              _xifexpression_6 = _xifexpression_7;
            }
            _xifexpression_5 = _xifexpression_6;
          }
          _xifexpression_4 = _xifexpression_5;
        }
        _xifexpression_2 = _xifexpression_4;
      }
      _xifexpression = _xifexpression_2;
    }
    return _xifexpression;
  }
  
  public boolean isJavaExtensionCall(final Expression it) {
    return false;
  }
  
  public boolean isJavaExtensionCall(final OperationCall it, final CompilationContext ctx) {
    return (((!Objects.equal(it.getName(), "isInstance")) && this.isSimpleCall(it, ctx)) && (!Objects.equal(this.calledJavaMethod(it, ctx), null)));
  }
  
  public boolean isSimpleCall(final OperationCall it, final CompilationContext ctx) {
    return ((Objects.equal(it.getTarget(), null) || this.isCompilable(it.getTarget(), ctx)) && IterableExtensions.<Expression>forall(it.getParams(), ((Function1<Expression, Boolean>) (Expression p) -> {
      return Boolean.valueOf(this.isCompilable(p, ctx));
    })));
  }
  
  public String calledJavaMethod(final OperationCall it, final CompilationContext ctx) {
    return this.calledJavaMethod(ctx, it);
  }
  
  public String calledJavaMethod(final CompilationContext it, final OperationCall call) {
    return it.getCalledJavaMethod(call);
  }
  
  public String autoBracket(final Expression it, final String javaCode, final CompilationContext ctx) {
    String _xifexpression = null;
    boolean _requiresBracketing = this.requiresBracketing(it, ctx);
    if (_requiresBracketing) {
      _xifexpression = (("(" + javaCode) + ")");
    } else {
      _xifexpression = javaCode;
    }
    return _xifexpression;
  }
  
  protected boolean _requiresBracketing(final Expression it, final CompilationContext ctx) {
    return (((this._expressionExtensionsX.isPrefixExpression(it, ctx) || this._expressionExtensionsX.isInfixExpression(it, ctx)) && (!Objects.equal(it.eContainer(), null))) && this.requiresBracketing(it, it.eContainer(), ctx));
  }
  
  protected boolean _requiresBracketing(final Literal it, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _requiresBracketing(final Expression it, final Object parent, final CompilationContext ctx) {
    return false;
  }
  
  protected boolean _requiresBracketing(final Expression it, final Expression parent, final CompilationContext ctx) {
    return ((this._expressionExtensionsX.isPrefixExpression(it, ctx) && this._expressionExtensionsX.isPrefixExpression(parent, ctx)) || (this._expressionExtensionsX.isInfixExpression(it, ctx) && (this._expressionExtensionsX.isPrefixExpression(parent, ctx) || this._expressionExtensionsX.isInfixExpression(parent, ctx))));
  }
  
  protected boolean _requiresBracketing(final OperationCall it, final OperationCall parent, final CompilationContext ctx) {
    return ((this._expressionExtensionsX.isPrefixExpression(it, ctx) && this._expressionExtensionsX.isPrefixExpression(parent, ctx)) || (this._expressionExtensionsX.isInfixExpression(it, ctx) && (this._expressionExtensionsX.isPrefixExpression(parent, ctx) || (this._expressionExtensionsX.isInfixExpression(parent, ctx) && (!Objects.equal(it.getName(), parent.getName()))))));
  }
  
  protected boolean _requiresBracketing(final BooleanOperation it, final BooleanOperation parent, final CompilationContext ctx) {
    String _operator = it.getOperator();
    String _operator_1 = parent.getOperator();
    return (!Objects.equal(_operator, _operator_1));
  }
  
  protected boolean _isThisCall(final Expression it) {
    return false;
  }
  
  protected boolean _isThisCall(final FeatureCall it) {
    return (Objects.equal(it.getName(), null) && this.isThis(it.getType()));
  }
  
  public boolean isFeature(final Identifier it) {
    return ((!Objects.equal(it.getId(), null)) && (it.getId().size() == 1));
  }
  
  protected boolean _isThis(final Expression it) {
    return false;
  }
  
  protected boolean _isThis(final Identifier it) {
    return (((!Objects.equal(it.getId(), null)) && (it.getId().size() == 1)) && Objects.equal(IterableExtensions.<String>head(it.getId()), "this"));
  }
  
  public String qualifiedTypeName(final Identifier it, final CompilationContext ctx) {
    String _xifexpression = null;
    EList<String> _id = it.getId();
    int _size = _id.size();
    boolean _equals = (_size == 2);
    if (_equals) {
      EList<String> _id_1 = it.getId();
      String _get = _id_1.get(0);
      String _plus = (_get + "::");
      EList<String> _id_2 = it.getId();
      String _get_1 = _id_2.get(1);
      _xifexpression = (_plus + _get_1);
    } else {
      EList<String> _id_3 = it.getId();
      String _head = IterableExtensions.<String>head(_id_3);
      _xifexpression = this.qualifiedTypeName(ctx, _head);
    }
    return _xifexpression;
  }
  
  public String qualifiedTypeName(final CompilationContext it, final String name) {
    return it.getQualifiedTypeName(name);
  }
  
  protected String _javaEncode(final Expression it) {
    String _serialize = this._expressionExtensionsX.serialize(it);
    return this.javaEncode(_serialize);
  }
  
  protected String _javaEncode(final String it) {
    return Strings.convertToJavaString(it);
  }
  
  public String join(final String it, final List<String> strings) {
    String _xifexpression = null;
    boolean _isEmpty = strings.isEmpty();
    if (_isEmpty) {
      _xifexpression = "";
    } else {
      _xifexpression = this.internalJoin(it, strings);
    }
    return _xifexpression;
  }
  
  private String internalJoin(final String it, final List<String> strings) {
    return Strings.concat(it, strings);
  }
  
  public String join(final String it, final String strings) {
    return strings;
  }
  
  public String join(final String it, final Void strings) {
    return "";
  }
  
  public String javaExpression(final SyntaxElement it, final CompilationContext ctx) {
    if (it instanceof BooleanLiteral) {
      return _javaExpression((BooleanLiteral)it, ctx);
    } else if (it instanceof CollectionExpression) {
      return _javaExpression((CollectionExpression)it, ctx);
    } else if (it instanceof IntegerLiteral) {
      return _javaExpression((IntegerLiteral)it, ctx);
    } else if (it instanceof NullLiteral) {
      return _javaExpression((NullLiteral)it, ctx);
    } else if (it instanceof OperationCall) {
      return _javaExpression((OperationCall)it, ctx);
    } else if (it instanceof RealLiteral) {
      return _javaExpression((RealLiteral)it, ctx);
    } else if (it instanceof StringLiteral) {
      return _javaExpression((StringLiteral)it, ctx);
    } else if (it instanceof TypeSelectExpression) {
      return _javaExpression((TypeSelectExpression)it, ctx);
    } else if (it instanceof BooleanOperation) {
      return _javaExpression((BooleanOperation)it, ctx);
    } else if (it instanceof CastedExpression) {
      return _javaExpression((CastedExpression)it, ctx);
    } else if (it instanceof FeatureCall) {
      return _javaExpression((FeatureCall)it, ctx);
    } else if (it instanceof IfExpression) {
      return _javaExpression((IfExpression)it, ctx);
    } else if (it instanceof ListLiteral) {
      return _javaExpression((ListLiteral)it, ctx);
    } else if (it instanceof Expression) {
      return _javaExpression((Expression)it, ctx);
    } else if (it instanceof Identifier) {
      return _javaExpression((Identifier)it, ctx);
    } else if (it == null) {
      return _javaExpression((Void)null, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public boolean isSimpleNavigation(final Expression it, final CompilationContext ctx) {
    if (it instanceof TypeSelectExpression) {
      return _isSimpleNavigation((TypeSelectExpression)it, ctx);
    } else if (it instanceof FeatureCall) {
      return _isSimpleNavigation((FeatureCall)it, ctx);
    } else if (it != null) {
      return _isSimpleNavigation(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public String navigationRoot(final Expression it, final CompilationContext ctx) {
    if (it instanceof FeatureCall) {
      return _navigationRoot((FeatureCall)it, ctx);
    } else if (it != null) {
      return _navigationRoot(it, ctx);
    } else if (it == null) {
      return _navigationRoot((Void)null, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public List<String> navigations(final Expression it, final CompilationContext ctx) {
    if (it instanceof TypeSelectExpression) {
      return _navigations((TypeSelectExpression)it, ctx);
    } else if (it instanceof FeatureCall) {
      return _navigations((FeatureCall)it, ctx);
    } else if (it != null) {
      return _navigations(it, ctx);
    } else if (it == null) {
      return _navigations((Void)null, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public boolean requiresBracketing(final Expression it, final CompilationContext ctx) {
    if (it instanceof Literal) {
      return _requiresBracketing((Literal)it, ctx);
    } else if (it != null) {
      return _requiresBracketing(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, ctx).toString());
    }
  }
  
  public boolean requiresBracketing(final Expression it, final Object parent, final CompilationContext ctx) {
    if (it instanceof OperationCall
         && parent instanceof OperationCall) {
      return _requiresBracketing((OperationCall)it, (OperationCall)parent, ctx);
    } else if (it instanceof BooleanOperation
         && parent instanceof BooleanOperation) {
      return _requiresBracketing((BooleanOperation)it, (BooleanOperation)parent, ctx);
    } else if (it != null
         && parent instanceof Expression) {
      return _requiresBracketing(it, (Expression)parent, ctx);
    } else if (it != null
         && parent != null) {
      return _requiresBracketing(it, parent, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, parent, ctx).toString());
    }
  }
  
  public boolean isThisCall(final Expression it) {
    if (it instanceof FeatureCall) {
      return _isThisCall((FeatureCall)it);
    } else if (it != null) {
      return _isThisCall(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
  
  public boolean isThis(final SyntaxElement it) {
    if (it instanceof Expression) {
      return _isThis((Expression)it);
    } else if (it instanceof Identifier) {
      return _isThis((Identifier)it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
  
  public String javaEncode(final Object it) {
    if (it instanceof Expression) {
      return _javaEncode((Expression)it);
    } else if (it instanceof String) {
      return _javaEncode((String)it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
