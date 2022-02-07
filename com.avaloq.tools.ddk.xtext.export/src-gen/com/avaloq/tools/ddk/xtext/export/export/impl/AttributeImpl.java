/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.Attribute;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.AttributeImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeImpl extends MinimalEObjectImpl.Container implements Attribute
{
  /**
   * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttribute()
   * @generated
   * @ordered
   */
  protected EAttribute attribute;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeImpl()
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
    return ExportPackage.Literals.ATTRIBUTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAttribute()
  {
    if (attribute != null && attribute.eIsProxy())
    {
      InternalEObject oldAttribute = (InternalEObject)attribute;
      attribute = (EAttribute)eResolveProxy(oldAttribute);
      if (attribute != oldAttribute)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExportPackage.ATTRIBUTE__ATTRIBUTE, oldAttribute, attribute));
      }
    }
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute basicGetAttribute()
  {
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAttribute(EAttribute newAttribute)
  {
    EAttribute oldAttribute = attribute;
    attribute = newAttribute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.ATTRIBUTE__ATTRIBUTE, oldAttribute, attribute));
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
      case ExportPackage.ATTRIBUTE__ATTRIBUTE:
        if (resolve) return getAttribute();
        return basicGetAttribute();
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
      case ExportPackage.ATTRIBUTE__ATTRIBUTE:
        setAttribute((EAttribute)newValue);
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
      case ExportPackage.ATTRIBUTE__ATTRIBUTE:
        setAttribute((EAttribute)null);
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
      case ExportPackage.ATTRIBUTE__ATTRIBUTE:
        return attribute != null;
    }
    return super.eIsSet(featureID);
  }

} //AttributeImpl
