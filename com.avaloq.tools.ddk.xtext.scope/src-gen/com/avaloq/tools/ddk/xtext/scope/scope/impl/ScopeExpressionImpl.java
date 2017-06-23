/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeExpressionImpl#getPrune <em>Prune</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScopeExpressionImpl extends MinimalEObjectImpl.Container implements ScopeExpression
{
  /**
   * The cached value of the '{@link #getPrune() <em>Prune</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrune()
   * @generated
   * @ordered
   */
  protected Expression prune;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeExpressionImpl()
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
    return ScopePackage.Literals.SCOPE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getPrune()
  {
    return prune;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPrune(Expression newPrune, NotificationChain msgs)
  {
    Expression oldPrune = prune;
    prune = newPrune;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_EXPRESSION__PRUNE, oldPrune, newPrune);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrune(Expression newPrune)
  {
    if (newPrune != prune)
    {
      NotificationChain msgs = null;
      if (prune != null)
        msgs = ((InternalEObject)prune).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_EXPRESSION__PRUNE, null, msgs);
      if (newPrune != null)
        msgs = ((InternalEObject)newPrune).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_EXPRESSION__PRUNE, null, msgs);
      msgs = basicSetPrune(newPrune, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_EXPRESSION__PRUNE, newPrune, newPrune));
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
      case ScopePackage.SCOPE_EXPRESSION__PRUNE:
        return basicSetPrune(null, msgs);
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
      case ScopePackage.SCOPE_EXPRESSION__PRUNE:
        return getPrune();
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
      case ScopePackage.SCOPE_EXPRESSION__PRUNE:
        setPrune((Expression)newValue);
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
      case ScopePackage.SCOPE_EXPRESSION__PRUNE:
        setPrune((Expression)null);
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
      case ScopePackage.SCOPE_EXPRESSION__PRUNE:
        return prune != null;
    }
    return super.eIsSet(featureID);
  }

} //ScopeExpressionImpl
