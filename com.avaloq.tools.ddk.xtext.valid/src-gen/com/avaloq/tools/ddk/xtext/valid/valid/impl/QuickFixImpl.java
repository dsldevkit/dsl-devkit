/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.QuickFix;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quick Fix</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl#getQuickFixKind <em>Quick Fix Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QuickFixImpl extends MinimalEObjectImpl.Container implements QuickFix
{
  /**
   * The default value of the '{@link #getQuickFixKind() <em>Quick Fix Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuickFixKind()
   * @generated
   * @ordered
   */
  protected static final QuickFixKind QUICK_FIX_KIND_EDEFAULT = QuickFixKind.SEMANTIC;

  /**
   * The cached value of the '{@link #getQuickFixKind() <em>Quick Fix Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuickFixKind()
   * @generated
   * @ordered
   */
  protected QuickFixKind quickFixKind = QUICK_FIX_KIND_EDEFAULT;

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
   * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected String message = MESSAGE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QuickFixImpl()
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
    return ValidPackage.Literals.QUICK_FIX;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public QuickFixKind getQuickFixKind()
  {
    return quickFixKind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setQuickFixKind(QuickFixKind newQuickFixKind)
  {
    QuickFixKind oldQuickFixKind = quickFixKind;
    quickFixKind = newQuickFixKind == null ? QUICK_FIX_KIND_EDEFAULT : newQuickFixKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.QUICK_FIX__QUICK_FIX_KIND, oldQuickFixKind, quickFixKind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.QUICK_FIX__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.QUICK_FIX__LABEL, oldLabel, label));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getMessage()
  {
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMessage(String newMessage)
  {
    String oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.QUICK_FIX__MESSAGE, oldMessage, message));
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
      case ValidPackage.QUICK_FIX__QUICK_FIX_KIND:
        return getQuickFixKind();
      case ValidPackage.QUICK_FIX__NAME:
        return getName();
      case ValidPackage.QUICK_FIX__LABEL:
        return getLabel();
      case ValidPackage.QUICK_FIX__MESSAGE:
        return getMessage();
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
      case ValidPackage.QUICK_FIX__QUICK_FIX_KIND:
        setQuickFixKind((QuickFixKind)newValue);
        return;
      case ValidPackage.QUICK_FIX__NAME:
        setName((String)newValue);
        return;
      case ValidPackage.QUICK_FIX__LABEL:
        setLabel((String)newValue);
        return;
      case ValidPackage.QUICK_FIX__MESSAGE:
        setMessage((String)newValue);
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
      case ValidPackage.QUICK_FIX__QUICK_FIX_KIND:
        setQuickFixKind(QUICK_FIX_KIND_EDEFAULT);
        return;
      case ValidPackage.QUICK_FIX__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ValidPackage.QUICK_FIX__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case ValidPackage.QUICK_FIX__MESSAGE:
        setMessage(MESSAGE_EDEFAULT);
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
      case ValidPackage.QUICK_FIX__QUICK_FIX_KIND:
        return quickFixKind != QUICK_FIX_KIND_EDEFAULT;
      case ValidPackage.QUICK_FIX__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ValidPackage.QUICK_FIX__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case ValidPackage.QUICK_FIX__MESSAGE:
        return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
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
    result.append(" (quickFixKind: ");
    result.append(quickFixKind);
    result.append(", name: ");
    result.append(name);
    result.append(", label: ");
    result.append(label);
    result.append(", message: ");
    result.append(message);
    result.append(')');
    return result.toString();
  }

} //QuickFixImpl
