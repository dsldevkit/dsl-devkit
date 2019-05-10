/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.Naming;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Scope Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl#isCaseDef <em>Case Def</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl#getCasing <em>Casing</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl#getNaming <em>Naming</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamedScopeExpressionImpl extends ScopeExpressionImpl implements NamedScopeExpression
{
  /**
   * The default value of the '{@link #isCaseDef() <em>Case Def</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCaseDef()
   * @generated
   * @ordered
   */
  protected static final boolean CASE_DEF_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCaseDef() <em>Case Def</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCaseDef()
   * @generated
   * @ordered
   */
  protected boolean caseDef = CASE_DEF_EDEFAULT;

  /**
   * The default value of the '{@link #getCasing() <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCasing()
   * @generated
   * @ordered
   */
  protected static final Casing CASING_EDEFAULT = Casing.SENSITIVE;

  /**
   * The cached value of the '{@link #getCasing() <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCasing()
   * @generated
   * @ordered
   */
  protected Casing casing = CASING_EDEFAULT;

  /**
   * The cached value of the '{@link #getNaming() <em>Naming</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNaming()
   * @generated
   * @ordered
   */
  protected Naming naming;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NamedScopeExpressionImpl()
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
    return ScopePackage.Literals.NAMED_SCOPE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCaseDef()
  {
    return caseDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCaseDef(boolean newCaseDef)
  {
    boolean oldCaseDef = caseDef;
    caseDef = newCaseDef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMED_SCOPE_EXPRESSION__CASE_DEF, oldCaseDef, caseDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Casing getCasing()
  {
    return casing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCasing(Casing newCasing)
  {
    Casing oldCasing = casing;
    casing = newCasing == null ? CASING_EDEFAULT : newCasing;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMED_SCOPE_EXPRESSION__CASING, oldCasing, casing));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Naming getNaming()
  {
    return naming;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNaming(Naming newNaming, NotificationChain msgs)
  {
    Naming oldNaming = naming;
    naming = newNaming;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING, oldNaming, newNaming);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNaming(Naming newNaming)
  {
    if (newNaming != naming)
    {
      NotificationChain msgs = null;
      if (naming != null)
        msgs = ((InternalEObject)naming).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING, null, msgs);
      if (newNaming != null)
        msgs = ((InternalEObject)newNaming).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING, null, msgs);
      msgs = basicSetNaming(newNaming, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING, newNaming, newNaming));
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
      case ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING:
        return basicSetNaming(null, msgs);
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
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASE_DEF:
        return isCaseDef();
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASING:
        return getCasing();
      case ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING:
        return getNaming();
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
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASE_DEF:
        setCaseDef((Boolean)newValue);
        return;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASING:
        setCasing((Casing)newValue);
        return;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING:
        setNaming((Naming)newValue);
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
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASE_DEF:
        setCaseDef(CASE_DEF_EDEFAULT);
        return;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASING:
        setCasing(CASING_EDEFAULT);
        return;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING:
        setNaming((Naming)null);
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
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASE_DEF:
        return caseDef != CASE_DEF_EDEFAULT;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__CASING:
        return casing != CASING_EDEFAULT;
      case ScopePackage.NAMED_SCOPE_EXPRESSION__NAMING:
        return naming != null;
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
    result.append(" (caseDef: ");
    result.append(caseDef);
    result.append(", casing: ");
    result.append(casing);
    result.append(')');
    return result.toString();
  }

} //NamedScopeExpressionImpl
