/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl#getContext <em>Context</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeRuleImpl extends MinimalEObjectImpl.Container implements ScopeRule
{
  /**
   * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
  protected ScopeContext context;

  /**
   * The cached value of the '{@link #getExprs() <em>Exprs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExprs()
   * @generated
   * @ordered
   */
  protected EList<ScopeExpression> exprs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeRuleImpl()
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
    return ScopePackage.Literals.SCOPE_RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeContext getContext()
  {
    return context;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContext(ScopeContext newContext, NotificationChain msgs)
  {
    ScopeContext oldContext = context;
    context = newContext;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_RULE__CONTEXT, oldContext, newContext);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setContext(ScopeContext newContext)
  {
    if (newContext != context)
    {
      NotificationChain msgs = null;
      if (context != null)
        msgs = ((InternalEObject)context).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_RULE__CONTEXT, null, msgs);
      if (newContext != null)
        msgs = ((InternalEObject)newContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_RULE__CONTEXT, null, msgs);
      msgs = basicSetContext(newContext, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_RULE__CONTEXT, newContext, newContext));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ScopeExpression> getExprs()
  {
    if (exprs == null)
    {
      exprs = new EObjectContainmentEList<ScopeExpression>(ScopeExpression.class, this, ScopePackage.SCOPE_RULE__EXPRS);
    }
    return exprs;
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
      case ScopePackage.SCOPE_RULE__CONTEXT:
        return basicSetContext(null, msgs);
      case ScopePackage.SCOPE_RULE__EXPRS:
        return ((InternalEList<?>)getExprs()).basicRemove(otherEnd, msgs);
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
      case ScopePackage.SCOPE_RULE__CONTEXT:
        return getContext();
      case ScopePackage.SCOPE_RULE__EXPRS:
        return getExprs();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ScopePackage.SCOPE_RULE__CONTEXT:
        setContext((ScopeContext)newValue);
        return;
      case ScopePackage.SCOPE_RULE__EXPRS:
        getExprs().clear();
        getExprs().addAll((Collection<? extends ScopeExpression>)newValue);
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
      case ScopePackage.SCOPE_RULE__CONTEXT:
        setContext((ScopeContext)null);
        return;
      case ScopePackage.SCOPE_RULE__EXPRS:
        getExprs().clear();
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
      case ScopePackage.SCOPE_RULE__CONTEXT:
        return context != null;
      case ScopePackage.SCOPE_RULE__EXPRS:
        return exprs != null && !exprs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ScopeRuleImpl
