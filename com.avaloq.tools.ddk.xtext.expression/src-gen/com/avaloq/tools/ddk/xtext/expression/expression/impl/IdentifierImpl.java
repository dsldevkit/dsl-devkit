/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage;
import com.avaloq.tools.ddk.xtext.expression.expression.Identifier;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl#getCl <em>Cl</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl#getId1 <em>Id1</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentifierImpl extends SyntaxElementImpl implements Identifier
{
  /**
   * The default value of the '{@link #getCl() <em>Cl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCl()
   * @generated
   * @ordered
   */
  protected static final String CL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCl() <em>Cl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCl()
   * @generated
   * @ordered
   */
  protected String cl = CL_EDEFAULT;

  /**
   * The cached value of the '{@link #getId1() <em>Id1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId1()
   * @generated
   * @ordered
   */
  protected Identifier id1;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected EList<String> id;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IdentifierImpl()
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
    return ExpressionPackage.Literals.IDENTIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCl()
  {
    return cl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCl(String newCl)
  {
    String oldCl = cl;
    cl = newCl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.IDENTIFIER__CL, oldCl, cl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Identifier getId1()
  {
    return id1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetId1(Identifier newId1, NotificationChain msgs)
  {
    Identifier oldId1 = id1;
    id1 = newId1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpressionPackage.IDENTIFIER__ID1, oldId1, newId1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId1(Identifier newId1)
  {
    if (newId1 != id1)
    {
      NotificationChain msgs = null;
      if (id1 != null)
        msgs = ((InternalEObject)id1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.IDENTIFIER__ID1, null, msgs);
      if (newId1 != null)
        msgs = ((InternalEObject)newId1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExpressionPackage.IDENTIFIER__ID1, null, msgs);
      msgs = basicSetId1(newId1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ExpressionPackage.IDENTIFIER__ID1, newId1, newId1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getId()
  {
    if (id == null)
    {
      id = new EDataTypeEList<String>(String.class, this, ExpressionPackage.IDENTIFIER__ID);
    }
    return id;
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
      case ExpressionPackage.IDENTIFIER__ID1:
        return basicSetId1(null, msgs);
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
      case ExpressionPackage.IDENTIFIER__CL:
        return getCl();
      case ExpressionPackage.IDENTIFIER__ID1:
        return getId1();
      case ExpressionPackage.IDENTIFIER__ID:
        return getId();
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
      case ExpressionPackage.IDENTIFIER__CL:
        setCl((String)newValue);
        return;
      case ExpressionPackage.IDENTIFIER__ID1:
        setId1((Identifier)newValue);
        return;
      case ExpressionPackage.IDENTIFIER__ID:
        getId().clear();
        getId().addAll((Collection<? extends String>)newValue);
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
      case ExpressionPackage.IDENTIFIER__CL:
        setCl(CL_EDEFAULT);
        return;
      case ExpressionPackage.IDENTIFIER__ID1:
        setId1((Identifier)null);
        return;
      case ExpressionPackage.IDENTIFIER__ID:
        getId().clear();
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
      case ExpressionPackage.IDENTIFIER__CL:
        return CL_EDEFAULT == null ? cl != null : !CL_EDEFAULT.equals(cl);
      case ExpressionPackage.IDENTIFIER__ID1:
        return id1 != null;
      case ExpressionPackage.IDENTIFIER__ID:
        return id != null && !id.isEmpty();
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (cl: ");
    result.append(cl);
    result.append(", id: ");
    result.append(id);
    result.append(')');
    return result.toString();
  }

} //IdentifierImpl
