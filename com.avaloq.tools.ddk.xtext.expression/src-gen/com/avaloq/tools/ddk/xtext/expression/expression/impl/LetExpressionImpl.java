/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage;
import com.avaloq.tools.ddk.xtext.expression.expression.LetExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl#getVarExpr <em>Var Expr</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LetExpressionImpl extends ExpressionImpl implements LetExpression
{
  /**
   * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected static final String IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected String identifier = IDENTIFIER_EDEFAULT;

  /**
   * The cached value of the '{@link #getVarExpr() <em>Var Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarExpr()
   * @generated
   * @ordered
   */
  protected Expression varExpr;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected Expression target;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LetExpressionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ExpressionPackage.Literals.LET_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdentifier()
  {
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentifier(String newIdentifier)
  {
    String oldIdentifier = identifier;
    identifier = newIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.LET_EXPRESSION__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getVarExpr()
  {
    return varExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVarExpr(Expression newVarExpr, NotificationChain msgs)
  {
    Expression oldVarExpr = varExpr;
    varExpr = newVarExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.LET_EXPRESSION__VAR_EXPR, oldVarExpr, newVarExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarExpr(Expression newVarExpr)
  {
    if (newVarExpr != varExpr)
    {
      NotificationChain msgs = null;
      if (varExpr != null)
        msgs = ((InternalEObject)varExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.LET_EXPRESSION__VAR_EXPR, null, msgs);
      if (newVarExpr != null)
        msgs = ((InternalEObject)newVarExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.LET_EXPRESSION__VAR_EXPR, null, msgs);
      msgs = basicSetVarExpr(newVarExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.LET_EXPRESSION__VAR_EXPR, newVarExpr, newVarExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTarget(Expression newTarget, NotificationChain msgs)
  {
    Expression oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.LET_EXPRESSION__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(Expression newTarget)
  {
    if (newTarget != target)
    {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.LET_EXPRESSION__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.LET_EXPRESSION__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.LET_EXPRESSION__TARGET, newTarget, newTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ExpressionPackage.LET_EXPRESSION__VAR_EXPR:
        return basicSetVarExpr(null, msgs);
      case ExpressionPackage.LET_EXPRESSION__TARGET:
        return basicSetTarget(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ExpressionPackage.LET_EXPRESSION__IDENTIFIER:
        return getIdentifier();
      case ExpressionPackage.LET_EXPRESSION__VAR_EXPR:
        return getVarExpr();
      case ExpressionPackage.LET_EXPRESSION__TARGET:
        return getTarget();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ExpressionPackage.LET_EXPRESSION__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case ExpressionPackage.LET_EXPRESSION__VAR_EXPR:
        setVarExpr((Expression)newValue);
        return;
      case ExpressionPackage.LET_EXPRESSION__TARGET:
        setTarget((Expression)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ExpressionPackage.LET_EXPRESSION__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case ExpressionPackage.LET_EXPRESSION__VAR_EXPR:
        setVarExpr((Expression)null);
        return;
      case ExpressionPackage.LET_EXPRESSION__TARGET:
        setTarget((Expression)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ExpressionPackage.LET_EXPRESSION__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case ExpressionPackage.LET_EXPRESSION__VAR_EXPR:
        return varExpr != null;
      case ExpressionPackage.LET_EXPRESSION__TARGET:
        return target != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (identifier: ");
    result.append(identifier);
    result.append(')');
    return result.toString();
  }

} //LetExpressionImpl
