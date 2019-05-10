/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl#isGlobal <em>Global</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeContextImpl extends MinimalEObjectImpl.Container implements ScopeContext
{
  /**
   * The default value of the '{@link #isGlobal() <em>Global</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGlobal()
   * @generated
   * @ordered
   */
  protected static final boolean GLOBAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isGlobal() <em>Global</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGlobal()
   * @generated
   * @ordered
   */
  protected boolean global = GLOBAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getContextType() <em>Context Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContextType()
   * @generated
   * @ordered
   */
  protected EClass contextType;

  /**
   * The cached value of the '{@link #getGuard() <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGuard()
   * @generated
   * @ordered
   */
  protected Expression guard;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeContextImpl()
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
    return ScopePackage.Literals.SCOPE_CONTEXT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isGlobal()
  {
    return global;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGlobal(boolean newGlobal)
  {
    boolean oldGlobal = global;
    global = newGlobal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_CONTEXT__GLOBAL, oldGlobal, global));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContextType()
  {
    if (contextType != null && contextType.eIsProxy())
    {
      InternalEObject oldContextType = (InternalEObject)contextType;
      contextType = (EClass)eResolveProxy(oldContextType);
      if (contextType != oldContextType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE, oldContextType, contextType));
      }
    }
    return contextType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetContextType()
  {
    return contextType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContextType(EClass newContextType)
  {
    EClass oldContextType = contextType;
    contextType = newContextType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE, oldContextType, contextType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getGuard()
  {
    return guard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGuard(Expression newGuard, NotificationChain msgs)
  {
    Expression oldGuard = guard;
    guard = newGuard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_CONTEXT__GUARD, oldGuard, newGuard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGuard(Expression newGuard)
  {
    if (newGuard != guard)
    {
      NotificationChain msgs = null;
      if (guard != null)
        msgs = ((InternalEObject)guard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_CONTEXT__GUARD, null, msgs);
      if (newGuard != null)
        msgs = ((InternalEObject)newGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_CONTEXT__GUARD, null, msgs);
      msgs = basicSetGuard(newGuard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_CONTEXT__GUARD, newGuard, newGuard));
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
      case ScopePackage.SCOPE_CONTEXT__GUARD:
        return basicSetGuard(null, msgs);
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
      case ScopePackage.SCOPE_CONTEXT__GLOBAL:
        return isGlobal();
      case ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE:
        if (resolve) return getContextType();
        return basicGetContextType();
      case ScopePackage.SCOPE_CONTEXT__GUARD:
        return getGuard();
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
      case ScopePackage.SCOPE_CONTEXT__GLOBAL:
        setGlobal((Boolean)newValue);
        return;
      case ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE:
        setContextType((EClass)newValue);
        return;
      case ScopePackage.SCOPE_CONTEXT__GUARD:
        setGuard((Expression)newValue);
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
      case ScopePackage.SCOPE_CONTEXT__GLOBAL:
        setGlobal(GLOBAL_EDEFAULT);
        return;
      case ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE:
        setContextType((EClass)null);
        return;
      case ScopePackage.SCOPE_CONTEXT__GUARD:
        setGuard((Expression)null);
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
      case ScopePackage.SCOPE_CONTEXT__GLOBAL:
        return global != GLOBAL_EDEFAULT;
      case ScopePackage.SCOPE_CONTEXT__CONTEXT_TYPE:
        return contextType != null;
      case ScopePackage.SCOPE_CONTEXT__GUARD:
        return guard != null;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (global: ");
    result.append(global);
    result.append(')');
    return result.toString();
  }

} //ScopeContextImpl
