/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.ImplicitlyNamed;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implicitly Named</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.ImplicitlyNamedImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImplicitlyNamedImpl extends MinimalEObjectImpl.Container implements ImplicitlyNamed
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImplicitlyNamedImpl()
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
    return CheckPackage.Literals.IMPLICITLY_NAMED;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    // TODO: implement this method to return the 'Name' attribute
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
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
      case CheckPackage.IMPLICITLY_NAMED__NAME:
        return getName();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case CheckPackage.IMPLICITLY_NAMED__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
    }
    return super.eIsSet(featureID);
  }

} //ImplicitlyNamedImpl
