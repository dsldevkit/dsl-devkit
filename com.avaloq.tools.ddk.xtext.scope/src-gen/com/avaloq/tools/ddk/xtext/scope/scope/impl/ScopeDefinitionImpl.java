/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl#getTargetType <em>Target Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeDefinitionImpl extends MinimalEObjectImpl.Container implements ScopeDefinition
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getTargetType() <em>Target Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetType()
   * @generated
   * @ordered
   */
  protected EClass targetType;

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
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected EReference reference;

  /**
   * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRules()
   * @generated
   * @ordered
   */
  protected EList<ScopeRule> rules;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeDefinitionImpl()
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
    return ScopePackage.Literals.SCOPE_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DEFINITION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTargetType()
  {
    if (targetType != null && targetType.eIsProxy())
    {
      InternalEObject oldTargetType = (InternalEObject)targetType;
      targetType = (EClass)eResolveProxy(oldTargetType);
      if (targetType != oldTargetType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.SCOPE_DEFINITION__TARGET_TYPE, oldTargetType, targetType));
      }
    }
    return targetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetTargetType()
  {
    return targetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetType(EClass newTargetType)
  {
    EClass oldTargetType = targetType;
    targetType = newTargetType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DEFINITION__TARGET_TYPE, oldTargetType, targetType));
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE, oldContextType, contextType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE, oldContextType, contextType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReference()
  {
    if (reference != null && reference.eIsProxy())
    {
      InternalEObject oldReference = (InternalEObject)reference;
      reference = (EReference)eResolveProxy(oldReference);
      if (reference != oldReference)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScopePackage.SCOPE_DEFINITION__REFERENCE, oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference basicGetReference()
  {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReference(EReference newReference)
  {
    EReference oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_DEFINITION__REFERENCE, oldReference, reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScopeRule> getRules()
  {
    if (rules == null)
    {
      rules = new EObjectContainmentEList<ScopeRule>(ScopeRule.class, this, ScopePackage.SCOPE_DEFINITION__RULES);
    }
    return rules;
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
      case ScopePackage.SCOPE_DEFINITION__RULES:
        return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
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
      case ScopePackage.SCOPE_DEFINITION__NAME:
        return getName();
      case ScopePackage.SCOPE_DEFINITION__TARGET_TYPE:
        if (resolve) return getTargetType();
        return basicGetTargetType();
      case ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE:
        if (resolve) return getContextType();
        return basicGetContextType();
      case ScopePackage.SCOPE_DEFINITION__REFERENCE:
        if (resolve) return getReference();
        return basicGetReference();
      case ScopePackage.SCOPE_DEFINITION__RULES:
        return getRules();
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
      case ScopePackage.SCOPE_DEFINITION__NAME:
        setName((String)newValue);
        return;
      case ScopePackage.SCOPE_DEFINITION__TARGET_TYPE:
        setTargetType((EClass)newValue);
        return;
      case ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE:
        setContextType((EClass)newValue);
        return;
      case ScopePackage.SCOPE_DEFINITION__REFERENCE:
        setReference((EReference)newValue);
        return;
      case ScopePackage.SCOPE_DEFINITION__RULES:
        getRules().clear();
        getRules().addAll((Collection<? extends ScopeRule>)newValue);
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
      case ScopePackage.SCOPE_DEFINITION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ScopePackage.SCOPE_DEFINITION__TARGET_TYPE:
        setTargetType((EClass)null);
        return;
      case ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE:
        setContextType((EClass)null);
        return;
      case ScopePackage.SCOPE_DEFINITION__REFERENCE:
        setReference((EReference)null);
        return;
      case ScopePackage.SCOPE_DEFINITION__RULES:
        getRules().clear();
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
      case ScopePackage.SCOPE_DEFINITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ScopePackage.SCOPE_DEFINITION__TARGET_TYPE:
        return targetType != null;
      case ScopePackage.SCOPE_DEFINITION__CONTEXT_TYPE:
        return contextType != null;
      case ScopePackage.SCOPE_DEFINITION__REFERENCE:
        return reference != null;
      case ScopePackage.SCOPE_DEFINITION__RULES:
        return rules != null && !rules.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ScopeDefinitionImpl
