/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delegation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl#getDelegate <em>Delegate</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl#getExternal <em>External</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScopeDelegationImpl extends ScopeExpressionImpl implements ScopeDelegation
{
  /**
   * The cached value of the '{@link #getDelegate() <em>Delegate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDelegate()
   * @generated
   * @ordered
   */
  protected Expression delegate;

  /**
   * The cached value of the '{@link #getExternal() <em>External</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternal()
   * @generated
   * @ordered
   */
  protected GlobalScopeExpression external;

  /**
   * The cached value of the '{@link #getScope() <em>Scope</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScope()
   * @generated
   * @ordered
   */
  protected ScopeDefinition scope;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeDelegationImpl()
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
    return ScopePackage.Literals.SCOPE_DELEGATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getDelegate()
  {
    return delegate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDelegate(Expression newDelegate, NotificationChain msgs)
  {
    Expression oldDelegate = delegate;
    delegate = newDelegate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DELEGATION__DELEGATE, oldDelegate, newDelegate);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDelegate(Expression newDelegate)
  {
    if (newDelegate != delegate)
    {
      NotificationChain msgs = null;
      if (delegate != null)
        msgs = ((InternalEObject)delegate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_DELEGATION__DELEGATE, null, msgs);
      if (newDelegate != null)
        msgs = ((InternalEObject)newDelegate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_DELEGATION__DELEGATE, null, msgs);
      msgs = basicSetDelegate(newDelegate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DELEGATION__DELEGATE, newDelegate, newDelegate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GlobalScopeExpression getExternal()
  {
    return external;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExternal(GlobalScopeExpression newExternal, NotificationChain msgs)
  {
    GlobalScopeExpression oldExternal = external;
    external = newExternal;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DELEGATION__EXTERNAL, oldExternal, newExternal);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExternal(GlobalScopeExpression newExternal)
  {
    if (newExternal != external)
    {
      NotificationChain msgs = null;
      if (external != null)
        msgs = ((InternalEObject)external).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_DELEGATION__EXTERNAL, null, msgs);
      if (newExternal != null)
        msgs = ((InternalEObject)newExternal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_DELEGATION__EXTERNAL, null, msgs);
      msgs = basicSetExternal(newExternal, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DELEGATION__EXTERNAL, newExternal, newExternal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeDefinition getScope()
  {
    if (scope != null && scope.eIsProxy())
    {
      InternalEObject oldScope = (InternalEObject)scope;
      scope = (ScopeDefinition)eResolveProxy(oldScope);
      if (scope != oldScope)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.SCOPE_DELEGATION__SCOPE, oldScope, scope));
      }
    }
    return scope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeDefinition basicGetScope()
  {
    return scope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScope(ScopeDefinition newScope)
  {
    ScopeDefinition oldScope = scope;
    scope = newScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DELEGATION__SCOPE, oldScope, scope));
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
      case ScopePackage.SCOPE_DELEGATION__DELEGATE:
        return basicSetDelegate(null, msgs);
      case ScopePackage.SCOPE_DELEGATION__EXTERNAL:
        return basicSetExternal(null, msgs);
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
      case ScopePackage.SCOPE_DELEGATION__DELEGATE:
        return getDelegate();
      case ScopePackage.SCOPE_DELEGATION__EXTERNAL:
        return getExternal();
      case ScopePackage.SCOPE_DELEGATION__SCOPE:
        if (resolve) return getScope();
        return basicGetScope();
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
      case ScopePackage.SCOPE_DELEGATION__DELEGATE:
        setDelegate((Expression)newValue);
        return;
      case ScopePackage.SCOPE_DELEGATION__EXTERNAL:
        setExternal((GlobalScopeExpression)newValue);
        return;
      case ScopePackage.SCOPE_DELEGATION__SCOPE:
        setScope((ScopeDefinition)newValue);
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
      case ScopePackage.SCOPE_DELEGATION__DELEGATE:
        setDelegate((Expression)null);
        return;
      case ScopePackage.SCOPE_DELEGATION__EXTERNAL:
        setExternal((GlobalScopeExpression)null);
        return;
      case ScopePackage.SCOPE_DELEGATION__SCOPE:
        setScope((ScopeDefinition)null);
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
      case ScopePackage.SCOPE_DELEGATION__DELEGATE:
        return delegate != null;
      case ScopePackage.SCOPE_DELEGATION__EXTERNAL:
        return external != null;
      case ScopePackage.SCOPE_DELEGATION__SCOPE:
        return scope != null;
    }
    return super.eIsSet(featureID);
  }

} //ScopeDelegationImpl
