/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.ImplicitlyNamed;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl#getChecks <em>Checks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryImpl extends DocumentedImplCustom implements Category
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
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected static final String LABEL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected String label = LABEL_EDEFAULT;

  /**
   * The cached value of the '{@link #getChecks() <em>Checks</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChecks()
   * @generated
   * @ordered
   */
  protected EList<Check> checks;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CategoryImpl()
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
    return CheckPackage.Literals.CATEGORY;
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
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CATEGORY__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLabel()
  {
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLabel(String newLabel)
  {
    String oldLabel = label;
    label = newLabel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CATEGORY__LABEL, oldLabel, label));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Check> getChecks()
  {
    if (checks == null)
    {
      checks = new EObjectContainmentEList<Check>(Check.class, this, CheckPackage.CATEGORY__CHECKS);
    }
    return checks;
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
      case CheckPackage.CATEGORY__CHECKS:
        return ((InternalEList<?>)getChecks()).basicRemove(otherEnd, msgs);
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
      case CheckPackage.CATEGORY__NAME:
        return getName();
      case CheckPackage.CATEGORY__ID:
        return getId();
      case CheckPackage.CATEGORY__LABEL:
        return getLabel();
      case CheckPackage.CATEGORY__CHECKS:
        return getChecks();
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
      case CheckPackage.CATEGORY__ID:
        setId((String)newValue);
        return;
      case CheckPackage.CATEGORY__LABEL:
        setLabel((String)newValue);
        return;
      case CheckPackage.CATEGORY__CHECKS:
        getChecks().clear();
        getChecks().addAll((Collection<? extends Check>)newValue);
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
      case CheckPackage.CATEGORY__ID:
        setId(ID_EDEFAULT);
        return;
      case CheckPackage.CATEGORY__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case CheckPackage.CATEGORY__CHECKS:
        getChecks().clear();
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
      case CheckPackage.CATEGORY__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
      case CheckPackage.CATEGORY__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case CheckPackage.CATEGORY__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case CheckPackage.CATEGORY__CHECKS:
        return checks != null && !checks.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == ImplicitlyNamed.class)
    {
      switch (derivedFeatureID)
      {
        case CheckPackage.CATEGORY__NAME: return CheckPackage.IMPLICITLY_NAMED__NAME;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == ImplicitlyNamed.class)
    {
      switch (baseFeatureID)
      {
        case CheckPackage.IMPLICITLY_NAMED__NAME: return CheckPackage.CATEGORY__NAME;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (id: ");
    result.append(id);
    result.append(", label: ");
    result.append(label);
    result.append(')');
    return result.toString();
  }

} //CategoryImpl
