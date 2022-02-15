/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceItem;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceItemImpl#isUnordered <em>Unordered</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceItemImpl extends MinimalEObjectImpl.Container implements InterfaceItem
{
  /**
   * The default value of the '{@link #isUnordered() <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnordered()
   * @generated
   * @ordered
   */
  protected static final boolean UNORDERED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isUnordered() <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnordered()
   * @generated
   * @ordered
   */
  protected boolean unordered = UNORDERED_EDEFAULT;

  /**
   * This is true if the Unordered attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean unorderedESet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InterfaceItemImpl()
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
    return ExportPackage.Literals.INTERFACE_ITEM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isUnordered()
  {
    return unordered;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setUnordered(boolean newUnordered)
  {
    boolean oldUnordered = unordered;
    unordered = newUnordered;
    boolean oldUnorderedESet = unorderedESet;
    unorderedESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.INTERFACE_ITEM__UNORDERED, oldUnordered, unordered, !oldUnorderedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void unsetUnordered()
  {
    boolean oldUnordered = unordered;
    boolean oldUnorderedESet = unorderedESet;
    unordered = UNORDERED_EDEFAULT;
    unorderedESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.INTERFACE_ITEM__UNORDERED, oldUnordered, UNORDERED_EDEFAULT, oldUnorderedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isSetUnordered()
  {
    return unorderedESet;
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
      case ExportPackage.INTERFACE_ITEM__UNORDERED:
        return isUnordered();
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
      case ExportPackage.INTERFACE_ITEM__UNORDERED:
        setUnordered((Boolean)newValue);
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
      case ExportPackage.INTERFACE_ITEM__UNORDERED:
        unsetUnordered();
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
      case ExportPackage.INTERFACE_ITEM__UNORDERED:
        return isSetUnordered();
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
    result.append(" (unordered: ");
    if (unorderedESet) result.append(unordered); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //InterfaceItemImpl
