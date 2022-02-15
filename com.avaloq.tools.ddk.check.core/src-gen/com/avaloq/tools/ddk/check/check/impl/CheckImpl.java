/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.ImplicitlyNamed;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.SeverityRange;
import com.avaloq.tools.ddk.check.check.TriggerKind;

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
 * An implementation of the model object '<em><b>Check</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getSeverityRange <em>Severity Range</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getDefaultSeverity <em>Default Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getGivenMessage <em>Given Message</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getContexts <em>Contexts</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckImpl extends DocumentedImplCustom implements Check
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
   * The cached value of the '{@link #getSeverityRange() <em>Severity Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSeverityRange()
   * @generated
   * @ordered
   */
  protected SeverityRange severityRange;

  /**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected static final boolean FINAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected boolean final_ = FINAL_EDEFAULT;

  /**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final TriggerKind KIND_EDEFAULT = TriggerKind.FAST;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected TriggerKind kind = KIND_EDEFAULT;

  /**
   * The default value of the '{@link #getDefaultSeverity() <em>Default Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultSeverity()
   * @generated
   * @ordered
   */
  protected static final SeverityKind DEFAULT_SEVERITY_EDEFAULT = SeverityKind.ERROR;

  /**
   * The cached value of the '{@link #getDefaultSeverity() <em>Default Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultSeverity()
   * @generated
   * @ordered
   */
  protected SeverityKind defaultSeverity = DEFAULT_SEVERITY_EDEFAULT;

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
   * The cached value of the '{@link #getFormalParameters() <em>Formal Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormalParameters()
   * @generated
   * @ordered
   */
  protected EList<FormalParameter> formalParameters;

  /**
   * The default value of the '{@link #getGivenMessage() <em>Given Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGivenMessage()
   * @generated
   * @ordered
   */
  protected static final String GIVEN_MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGivenMessage() <em>Given Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGivenMessage()
   * @generated
   * @ordered
   */
  protected String givenMessage = GIVEN_MESSAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getContexts() <em>Contexts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContexts()
   * @generated
   * @ordered
   */
  protected EList<Context> contexts;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CheckImpl()
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
    return CheckPackage.Literals.CHECK;
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
  public SeverityRange getSeverityRange()
  {
    return severityRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSeverityRange(SeverityRange newSeverityRange, NotificationChain msgs)
  {
    SeverityRange oldSeverityRange = severityRange;
    severityRange = newSeverityRange;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__SEVERITY_RANGE, oldSeverityRange, newSeverityRange);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSeverityRange(SeverityRange newSeverityRange)
  {
    if (newSeverityRange != severityRange)
    {
      NotificationChain msgs = null;
      if (severityRange != null)
        msgs = ((InternalEObject)severityRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CheckPackage.CHECK__SEVERITY_RANGE, null, msgs);
      if (newSeverityRange != null)
        msgs = ((InternalEObject)newSeverityRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CheckPackage.CHECK__SEVERITY_RANGE, null, msgs);
      msgs = basicSetSeverityRange(newSeverityRange, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__SEVERITY_RANGE, newSeverityRange, newSeverityRange));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isFinal()
  {
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setFinal(boolean newFinal)
  {
    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TriggerKind getKind()
  {
    return kind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setKind(TriggerKind newKind)
  {
    TriggerKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__KIND, oldKind, kind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SeverityKind getDefaultSeverity()
  {
    return defaultSeverity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDefaultSeverity(SeverityKind newDefaultSeverity)
  {
    SeverityKind oldDefaultSeverity = defaultSeverity;
    defaultSeverity = newDefaultSeverity == null ? DEFAULT_SEVERITY_EDEFAULT : newDefaultSeverity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__DEFAULT_SEVERITY, oldDefaultSeverity, defaultSeverity));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__LABEL, oldLabel, label));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<FormalParameter> getFormalParameters()
  {
    if (formalParameters == null)
    {
      formalParameters = new EObjectContainmentEList<FormalParameter>(FormalParameter.class, this, CheckPackage.CHECK__FORMAL_PARAMETERS);
    }
    return formalParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getGivenMessage()
  {
    return givenMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setGivenMessage(String newGivenMessage)
  {
    String oldGivenMessage = givenMessage;
    givenMessage = newGivenMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.CHECK__GIVEN_MESSAGE, oldGivenMessage, givenMessage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Context> getContexts()
  {
    if (contexts == null)
    {
      contexts = new EObjectContainmentEList<Context>(Context.class, this, CheckPackage.CHECK__CONTEXTS);
    }
    return contexts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getMessage()
  {
    // TODO: implement this method to return the 'Message' attribute
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
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
      case CheckPackage.CHECK__SEVERITY_RANGE:
        return basicSetSeverityRange(null, msgs);
      case CheckPackage.CHECK__FORMAL_PARAMETERS:
        return ((InternalEList<?>)getFormalParameters()).basicRemove(otherEnd, msgs);
      case CheckPackage.CHECK__CONTEXTS:
        return ((InternalEList<?>)getContexts()).basicRemove(otherEnd, msgs);
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
      case CheckPackage.CHECK__NAME:
        return getName();
      case CheckPackage.CHECK__SEVERITY_RANGE:
        return getSeverityRange();
      case CheckPackage.CHECK__FINAL:
        return isFinal();
      case CheckPackage.CHECK__KIND:
        return getKind();
      case CheckPackage.CHECK__DEFAULT_SEVERITY:
        return getDefaultSeverity();
      case CheckPackage.CHECK__ID:
        return getId();
      case CheckPackage.CHECK__LABEL:
        return getLabel();
      case CheckPackage.CHECK__FORMAL_PARAMETERS:
        return getFormalParameters();
      case CheckPackage.CHECK__GIVEN_MESSAGE:
        return getGivenMessage();
      case CheckPackage.CHECK__CONTEXTS:
        return getContexts();
      case CheckPackage.CHECK__MESSAGE:
        return getMessage();
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
      case CheckPackage.CHECK__SEVERITY_RANGE:
        setSeverityRange((SeverityRange)newValue);
        return;
      case CheckPackage.CHECK__FINAL:
        setFinal((Boolean)newValue);
        return;
      case CheckPackage.CHECK__KIND:
        setKind((TriggerKind)newValue);
        return;
      case CheckPackage.CHECK__DEFAULT_SEVERITY:
        setDefaultSeverity((SeverityKind)newValue);
        return;
      case CheckPackage.CHECK__ID:
        setId((String)newValue);
        return;
      case CheckPackage.CHECK__LABEL:
        setLabel((String)newValue);
        return;
      case CheckPackage.CHECK__FORMAL_PARAMETERS:
        getFormalParameters().clear();
        getFormalParameters().addAll((Collection<? extends FormalParameter>)newValue);
        return;
      case CheckPackage.CHECK__GIVEN_MESSAGE:
        setGivenMessage((String)newValue);
        return;
      case CheckPackage.CHECK__CONTEXTS:
        getContexts().clear();
        getContexts().addAll((Collection<? extends Context>)newValue);
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
      case CheckPackage.CHECK__SEVERITY_RANGE:
        setSeverityRange((SeverityRange)null);
        return;
      case CheckPackage.CHECK__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case CheckPackage.CHECK__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case CheckPackage.CHECK__DEFAULT_SEVERITY:
        setDefaultSeverity(DEFAULT_SEVERITY_EDEFAULT);
        return;
      case CheckPackage.CHECK__ID:
        setId(ID_EDEFAULT);
        return;
      case CheckPackage.CHECK__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case CheckPackage.CHECK__FORMAL_PARAMETERS:
        getFormalParameters().clear();
        return;
      case CheckPackage.CHECK__GIVEN_MESSAGE:
        setGivenMessage(GIVEN_MESSAGE_EDEFAULT);
        return;
      case CheckPackage.CHECK__CONTEXTS:
        getContexts().clear();
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
      case CheckPackage.CHECK__NAME:
        return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
      case CheckPackage.CHECK__SEVERITY_RANGE:
        return severityRange != null;
      case CheckPackage.CHECK__FINAL:
        return final_ != FINAL_EDEFAULT;
      case CheckPackage.CHECK__KIND:
        return kind != KIND_EDEFAULT;
      case CheckPackage.CHECK__DEFAULT_SEVERITY:
        return defaultSeverity != DEFAULT_SEVERITY_EDEFAULT;
      case CheckPackage.CHECK__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case CheckPackage.CHECK__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case CheckPackage.CHECK__FORMAL_PARAMETERS:
        return formalParameters != null && !formalParameters.isEmpty();
      case CheckPackage.CHECK__GIVEN_MESSAGE:
        return GIVEN_MESSAGE_EDEFAULT == null ? givenMessage != null : !GIVEN_MESSAGE_EDEFAULT.equals(givenMessage);
      case CheckPackage.CHECK__CONTEXTS:
        return contexts != null && !contexts.isEmpty();
      case CheckPackage.CHECK__MESSAGE:
        return MESSAGE_EDEFAULT == null ? getMessage() != null : !MESSAGE_EDEFAULT.equals(getMessage());
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
        case CheckPackage.CHECK__NAME: return CheckPackage.IMPLICITLY_NAMED__NAME;
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
        case CheckPackage.IMPLICITLY_NAMED__NAME: return CheckPackage.CHECK__NAME;
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
    result.append(" (final: ");
    result.append(final_);
    result.append(", kind: ");
    result.append(kind);
    result.append(", defaultSeverity: ");
    result.append(defaultSeverity);
    result.append(", id: ");
    result.append(id);
    result.append(", label: ");
    result.append(label);
    result.append(", givenMessage: ");
    result.append(givenMessage);
    result.append(')');
    return result.toString();
  }

} //CheckImpl
