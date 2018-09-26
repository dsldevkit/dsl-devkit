/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.KeywordPair;
import com.avaloq.tools.ddk.xtext.format.format.Matcher;

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
 * An implementation of the model object '<em><b>Keyword Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.KeywordPairImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.KeywordPairImpl#getRight <em>Right</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.KeywordPairImpl#getLeftMatchers <em>Left Matchers</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.KeywordPairImpl#getRightMatchers <em>Right Matchers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KeywordPairImpl extends GrammarRuleDirectiveImpl implements KeywordPair
{
  /**
   * The default value of the '{@link #getLeft() <em>Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeft()
   * @generated
   * @ordered
   */
  protected static final String LEFT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLeft() <em>Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeft()
   * @generated
   * @ordered
   */
  protected String left = LEFT_EDEFAULT;

  /**
   * The default value of the '{@link #getRight() <em>Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRight()
   * @generated
   * @ordered
   */
  protected static final String RIGHT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRight() <em>Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRight()
   * @generated
   * @ordered
   */
  protected String right = RIGHT_EDEFAULT;

  /**
   * The cached value of the '{@link #getLeftMatchers() <em>Left Matchers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeftMatchers()
   * @generated
   * @ordered
   */
  protected EList<Matcher> leftMatchers;

  /**
   * The cached value of the '{@link #getRightMatchers() <em>Right Matchers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRightMatchers()
   * @generated
   * @ordered
   */
  protected EList<Matcher> rightMatchers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected KeywordPairImpl()
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
    return FormatPackage.Literals.KEYWORD_PAIR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLeft()
  {
    return left;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeft(String newLeft)
  {
    String oldLeft = left;
    left = newLeft;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.KEYWORD_PAIR__LEFT, oldLeft, left));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRight()
  {
    return right;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRight(String newRight)
  {
    String oldRight = right;
    right = newRight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.KEYWORD_PAIR__RIGHT, oldRight, right));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Matcher> getLeftMatchers()
  {
    if (leftMatchers == null)
    {
      leftMatchers = new EObjectContainmentEList<Matcher>(Matcher.class, this, FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS);
    }
    return leftMatchers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Matcher> getRightMatchers()
  {
    if (rightMatchers == null)
    {
      rightMatchers = new EObjectContainmentEList<Matcher>(Matcher.class, this, FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS);
    }
    return rightMatchers;
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
      case FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS:
        return ((InternalEList<?>)getLeftMatchers()).basicRemove(otherEnd, msgs);
      case FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS:
        return ((InternalEList<?>)getRightMatchers()).basicRemove(otherEnd, msgs);
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
      case FormatPackage.KEYWORD_PAIR__LEFT:
        return getLeft();
      case FormatPackage.KEYWORD_PAIR__RIGHT:
        return getRight();
      case FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS:
        return getLeftMatchers();
      case FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS:
        return getRightMatchers();
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
      case FormatPackage.KEYWORD_PAIR__LEFT:
        setLeft((String)newValue);
        return;
      case FormatPackage.KEYWORD_PAIR__RIGHT:
        setRight((String)newValue);
        return;
      case FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS:
        getLeftMatchers().clear();
        getLeftMatchers().addAll((Collection<? extends Matcher>)newValue);
        return;
      case FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS:
        getRightMatchers().clear();
        getRightMatchers().addAll((Collection<? extends Matcher>)newValue);
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
      case FormatPackage.KEYWORD_PAIR__LEFT:
        setLeft(LEFT_EDEFAULT);
        return;
      case FormatPackage.KEYWORD_PAIR__RIGHT:
        setRight(RIGHT_EDEFAULT);
        return;
      case FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS:
        getLeftMatchers().clear();
        return;
      case FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS:
        getRightMatchers().clear();
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
      case FormatPackage.KEYWORD_PAIR__LEFT:
        return LEFT_EDEFAULT == null ? left != null : !LEFT_EDEFAULT.equals(left);
      case FormatPackage.KEYWORD_PAIR__RIGHT:
        return RIGHT_EDEFAULT == null ? right != null : !RIGHT_EDEFAULT.equals(right);
      case FormatPackage.KEYWORD_PAIR__LEFT_MATCHERS:
        return leftMatchers != null && !leftMatchers.isEmpty();
      case FormatPackage.KEYWORD_PAIR__RIGHT_MATCHERS:
        return rightMatchers != null && !rightMatchers.isEmpty();
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
    result.append(" (left: ");
    result.append(left);
    result.append(", right: ");
    result.append(right);
    result.append(')');
    return result.toString();
  }

} //KeywordPairImpl
