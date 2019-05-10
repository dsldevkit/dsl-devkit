/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import com.avaloq.tools.ddk.xtext.scope.scope.DataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Global Scope Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#isRecursivePrefix <em>Recursive Prefix</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#getData <em>Data</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl#getDomains <em>Domains</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GlobalScopeExpressionImpl extends NamedScopeExpressionImpl implements GlobalScopeExpression
{
  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected EClass type;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected Expression name;

  /**
   * The default value of the '{@link #isRecursivePrefix() <em>Recursive Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRecursivePrefix()
   * @generated
   * @ordered
   */
  protected static final boolean RECURSIVE_PREFIX_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRecursivePrefix() <em>Recursive Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRecursivePrefix()
   * @generated
   * @ordered
   */
  protected boolean recursivePrefix = RECURSIVE_PREFIX_EDEFAULT;

  /**
   * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrefix()
   * @generated
   * @ordered
   */
  protected Expression prefix;

  /**
   * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getData()
   * @generated
   * @ordered
   */
  protected EList<DataExpression> data;

  /**
   * The cached value of the '{@link #getDomains() <em>Domains</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDomains()
   * @generated
   * @ordered
   */
  protected EList<String> domains;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GlobalScopeExpressionImpl()
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
    return ScopePackage.Literals.GLOBAL_SCOPE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject)type;
      type = (EClass)eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(EClass newType)
  {
    EClass oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(Expression newName, NotificationChain msgs)
  {
    Expression oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(Expression newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRecursivePrefix()
  {
    return recursivePrefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecursivePrefix(boolean newRecursivePrefix)
  {
    boolean oldRecursivePrefix = recursivePrefix;
    recursivePrefix = newRecursivePrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX, oldRecursivePrefix, recursivePrefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getPrefix()
  {
    return prefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPrefix(Expression newPrefix, NotificationChain msgs)
  {
    Expression oldPrefix = prefix;
    prefix = newPrefix;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX, oldPrefix, newPrefix);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrefix(Expression newPrefix)
  {
    if (newPrefix != prefix)
    {
      NotificationChain msgs = null;
      if (prefix != null)
        msgs = ((InternalEObject)prefix).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX, null, msgs);
      if (newPrefix != null)
        msgs = ((InternalEObject)newPrefix).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX, null, msgs);
      msgs = basicSetPrefix(newPrefix, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX, newPrefix, newPrefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DataExpression> getData()
  {
    if (data == null)
    {
      data = new EObjectContainmentEList<DataExpression>(DataExpression.class, this, ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA);
    }
    return data;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getDomains()
  {
    if (domains == null)
    {
      domains = new EDataTypeEList<String>(String.class, this, ScopePackage.GLOBAL_SCOPE_EXPRESSION__DOMAINS);
    }
    return domains;
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
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME:
        return basicSetName(null, msgs);
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX:
        return basicSetPrefix(null, msgs);
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA:
        return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME:
        return getName();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX:
        return isRecursivePrefix();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX:
        return getPrefix();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA:
        return getData();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DOMAINS:
        return getDomains();
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
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE:
        setType((EClass)newValue);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME:
        setName((Expression)newValue);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX:
        setRecursivePrefix((Boolean)newValue);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX:
        setPrefix((Expression)newValue);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA:
        getData().clear();
        getData().addAll((Collection<? extends DataExpression>)newValue);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DOMAINS:
        getDomains().clear();
        getDomains().addAll((Collection<? extends String>)newValue);
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
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE:
        setType((EClass)null);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME:
        setName((Expression)null);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX:
        setRecursivePrefix(RECURSIVE_PREFIX_EDEFAULT);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX:
        setPrefix((Expression)null);
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA:
        getData().clear();
        return;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DOMAINS:
        getDomains().clear();
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
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__TYPE:
        return type != null;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__NAME:
        return name != null;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX:
        return recursivePrefix != RECURSIVE_PREFIX_EDEFAULT;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__PREFIX:
        return prefix != null;
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DATA:
        return data != null && !data.isEmpty();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION__DOMAINS:
        return domains != null && !domains.isEmpty();
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
    result.append(" (recursivePrefix: ");
    result.append(recursivePrefix);
    result.append(", domains: ");
    result.append(domains);
    result.append(')');
    return result.toString();
  }

} //GlobalScopeExpressionImpl
