/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Int Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.IntValueImpl#getLiteral <em>Literal</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.IntValueImpl#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntValueImpl extends MinimalEObjectImpl.Container implements IntValue
{
  /**
   * The default value of the '{@link #getLiteral() <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLiteral()
   * @generated
   * @ordered
   */
  protected static final Integer LITERAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLiteral() <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLiteral()
   * @generated
   * @ordered
   */
  protected Integer literal = LITERAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected Constant reference;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IntValueImpl()
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
    return FormatPackage.Literals.INT_VALUE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Integer getLiteral()
  {
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLiteral(Integer newLiteral)
  {
    Integer oldLiteral = literal;
    literal = newLiteral;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.INT_VALUE__LITERAL, oldLiteral, literal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Constant getReference()
  {
    if (reference != null && reference.eIsProxy())
    {
      InternalEObject oldReference = (InternalEObject)reference;
      reference = (Constant)eResolveProxy(oldReference);
      if (reference != oldReference)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.INT_VALUE__REFERENCE, oldReference, reference));
      }
    }
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Constant basicGetReference()
  {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setReference(Constant newReference)
  {
    Constant oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.INT_VALUE__REFERENCE, oldReference, reference));
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
      case FormatPackage.INT_VALUE__LITERAL:
        return getLiteral();
      case FormatPackage.INT_VALUE__REFERENCE:
        if (resolve) return getReference();
        return basicGetReference();
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
      case FormatPackage.INT_VALUE__LITERAL:
        setLiteral((Integer)newValue);
        return;
      case FormatPackage.INT_VALUE__REFERENCE:
        setReference((Constant)newValue);
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
      case FormatPackage.INT_VALUE__LITERAL:
        setLiteral(LITERAL_EDEFAULT);
        return;
      case FormatPackage.INT_VALUE__REFERENCE:
        setReference((Constant)null);
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
      case FormatPackage.INT_VALUE__LITERAL:
        return LITERAL_EDEFAULT == null ? literal != null : !LITERAL_EDEFAULT.equals(literal);
      case FormatPackage.INT_VALUE__REFERENCE:
        return reference != null;
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
    result.append(" (literal: ");
    result.append(literal);
    result.append(')');
    return result.toString();
  }

} //IntValueImpl
