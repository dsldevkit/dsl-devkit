/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.CheckKind;
import com.avaloq.tools.ddk.xtext.valid.valid.Rule;
import com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getCheckKind <em>Check Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleImpl extends MinimalEObjectImpl.Container implements Rule
{
  /**
   * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptional()
   * @generated
   * @ordered
   */
  protected static final boolean OPTIONAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOptional()
   * @generated
   * @ordered
   */
  protected boolean optional = OPTIONAL_EDEFAULT;

  /**
   * The default value of the '{@link #getCheckKind() <em>Check Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCheckKind()
   * @generated
   * @ordered
   */
  protected static final CheckKind CHECK_KIND_EDEFAULT = CheckKind.FAST;

  /**
   * The cached value of the '{@link #getCheckKind() <em>Check Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCheckKind()
   * @generated
   * @ordered
   */
  protected CheckKind checkKind = CHECK_KIND_EDEFAULT;

  /**
   * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected static final SeverityKind SEVERITY_EDEFAULT = SeverityKind.ERROR;

  /**
   * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected SeverityKind severity = SEVERITY_EDEFAULT;

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
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

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
  protected RuleImpl()
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
    return ValidPackage.Literals.RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isOptional()
  {
    return optional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setOptional(boolean newOptional)
  {
    boolean oldOptional = optional;
    optional = newOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__OPTIONAL, oldOptional, optional));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public CheckKind getCheckKind()
  {
    return checkKind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCheckKind(CheckKind newCheckKind)
  {
    CheckKind oldCheckKind = checkKind;
    checkKind = newCheckKind == null ? CHECK_KIND_EDEFAULT : newCheckKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__CHECK_KIND, oldCheckKind, checkKind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SeverityKind getSeverity()
  {
    return severity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSeverity(SeverityKind newSeverity)
  {
    SeverityKind oldSeverity = severity;
    severity = newSeverity == null ? SEVERITY_EDEFAULT : newSeverity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__SEVERITY, oldSeverity, severity));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__LABEL, oldLabel, label));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDescription(String newDescription)
  {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__DESCRIPTION, oldDescription, description));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RULE__MESSAGE, oldMessage, message));
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
      case ValidPackage.RULE__OPTIONAL:
        return isOptional();
      case ValidPackage.RULE__CHECK_KIND:
        return getCheckKind();
      case ValidPackage.RULE__SEVERITY:
        return getSeverity();
      case ValidPackage.RULE__NAME:
        return getName();
      case ValidPackage.RULE__LABEL:
        return getLabel();
      case ValidPackage.RULE__DESCRIPTION:
        return getDescription();
      case ValidPackage.RULE__MESSAGE:
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
      case ValidPackage.RULE__OPTIONAL:
        setOptional((Boolean)newValue);
        return;
      case ValidPackage.RULE__CHECK_KIND:
        setCheckKind((CheckKind)newValue);
        return;
      case ValidPackage.RULE__SEVERITY:
        setSeverity((SeverityKind)newValue);
        return;
      case ValidPackage.RULE__NAME:
        setName((String)newValue);
        return;
      case ValidPackage.RULE__LABEL:
        setLabel((String)newValue);
        return;
      case ValidPackage.RULE__DESCRIPTION:
        setDescription((String)newValue);
        return;
      case ValidPackage.RULE__MESSAGE:
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
      case ValidPackage.RULE__OPTIONAL:
        setOptional(OPTIONAL_EDEFAULT);
        return;
      case ValidPackage.RULE__CHECK_KIND:
        setCheckKind(CHECK_KIND_EDEFAULT);
        return;
      case ValidPackage.RULE__SEVERITY:
        setSeverity(SEVERITY_EDEFAULT);
        return;
      case ValidPackage.RULE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ValidPackage.RULE__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case ValidPackage.RULE__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case ValidPackage.RULE__MESSAGE:
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
      case ValidPackage.RULE__OPTIONAL:
        return optional != OPTIONAL_EDEFAULT;
      case ValidPackage.RULE__CHECK_KIND:
        return checkKind != CHECK_KIND_EDEFAULT;
      case ValidPackage.RULE__SEVERITY:
        return severity != SEVERITY_EDEFAULT;
      case ValidPackage.RULE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ValidPackage.RULE__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case ValidPackage.RULE__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case ValidPackage.RULE__MESSAGE:
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
    result.append(" (optional: ");
    result.append(optional);
    result.append(", checkKind: ");
    result.append(checkKind);
    result.append(", severity: ");
    result.append(severity);
    result.append(", name: ");
    result.append(name);
    result.append(", label: ");
    result.append(label);
    result.append(", description: ");
    result.append(description);
    result.append(", message: ");
    result.append(message);
    result.append(')');
    return result.toString();
  }

} //RuleImpl
