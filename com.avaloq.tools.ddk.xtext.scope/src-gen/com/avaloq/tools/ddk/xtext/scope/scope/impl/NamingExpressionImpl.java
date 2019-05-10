/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Naming Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl#isExport <em>Export</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl#isFactory <em>Factory</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamingExpressionImpl extends MinimalEObjectImpl.Container implements NamingExpression
{
  /**
   * The default value of the '{@link #isExport() <em>Export</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExport()
   * @generated
   * @ordered
   */
  protected static final boolean EXPORT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isExport() <em>Export</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExport()
   * @generated
   * @ordered
   */
  protected boolean export = EXPORT_EDEFAULT;

  /**
   * The default value of the '{@link #isFactory() <em>Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFactory()
   * @generated
   * @ordered
   */
  protected static final boolean FACTORY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFactory() <em>Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFactory()
   * @generated
   * @ordered
   */
  protected boolean factory = FACTORY_EDEFAULT;

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected Expression expression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NamingExpressionImpl()
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
    return ScopePackage.Literals.NAMING_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isExport()
  {
    return export;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExport(boolean newExport)
  {
    boolean oldExport = export;
    export = newExport;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMING_EXPRESSION__EXPORT, oldExport, export));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFactory()
  {
    return factory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFactory(boolean newFactory)
  {
    boolean oldFactory = factory;
    factory = newFactory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMING_EXPRESSION__FACTORY, oldFactory, factory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs)
  {
    Expression oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.NAMING_EXPRESSION__EXPRESSION, oldExpression, newExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression(Expression newExpression)
  {
    if (newExpression != expression)
    {
      NotificationChain msgs = null;
      if (expression != null)
        msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.NAMING_EXPRESSION__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.NAMING_EXPRESSION__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMING_EXPRESSION__EXPRESSION, newExpression, newExpression));
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
      case ScopePackage.NAMING_EXPRESSION__EXPRESSION:
        return basicSetExpression(null, msgs);
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
      case ScopePackage.NAMING_EXPRESSION__EXPORT:
        return isExport();
      case ScopePackage.NAMING_EXPRESSION__FACTORY:
        return isFactory();
      case ScopePackage.NAMING_EXPRESSION__EXPRESSION:
        return getExpression();
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
      case ScopePackage.NAMING_EXPRESSION__EXPORT:
        setExport((Boolean)newValue);
        return;
      case ScopePackage.NAMING_EXPRESSION__FACTORY:
        setFactory((Boolean)newValue);
        return;
      case ScopePackage.NAMING_EXPRESSION__EXPRESSION:
        setExpression((Expression)newValue);
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
      case ScopePackage.NAMING_EXPRESSION__EXPORT:
        setExport(EXPORT_EDEFAULT);
        return;
      case ScopePackage.NAMING_EXPRESSION__FACTORY:
        setFactory(FACTORY_EDEFAULT);
        return;
      case ScopePackage.NAMING_EXPRESSION__EXPRESSION:
        setExpression((Expression)null);
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
      case ScopePackage.NAMING_EXPRESSION__EXPORT:
        return export != EXPORT_EDEFAULT;
      case ScopePackage.NAMING_EXPRESSION__FACTORY:
        return factory != FACTORY_EDEFAULT;
      case ScopePackage.NAMING_EXPRESSION__EXPRESSION:
        return expression != null;
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
    result.append(" (export: ");
    result.append(export);
    result.append(", factory: ");
    result.append(factory);
    result.append(')');
    return result.toString();
  }

} //NamingExpressionImpl
