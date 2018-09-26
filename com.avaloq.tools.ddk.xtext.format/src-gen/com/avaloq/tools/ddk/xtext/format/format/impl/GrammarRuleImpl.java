/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grammar Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarRuleImpl#getTargetRule <em>Target Rule</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarRuleImpl#getDirectives <em>Directives</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GrammarRuleImpl extends RuleImpl implements GrammarRule
{
  /**
   * The cached value of the '{@link #getTargetRule() <em>Target Rule</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetRule()
   * @generated
   * @ordered
   */
  protected AbstractRule targetRule;

  /**
   * The cached value of the '{@link #getDirectives() <em>Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirectives()
   * @generated
   * @ordered
   */
  protected EList<EObject> directives;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GrammarRuleImpl()
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
    return FormatPackage.Literals.GRAMMAR_RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule getTargetRule()
  {
    if (targetRule != null && targetRule.eIsProxy())
    {
      InternalEObject oldTargetRule = (InternalEObject)targetRule;
      targetRule = (AbstractRule)eResolveProxy(oldTargetRule);
      if (targetRule != oldTargetRule)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_RULE__TARGET_RULE, oldTargetRule, targetRule));
      }
    }
    return targetRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule basicGetTargetRule()
  {
    return targetRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetRule(AbstractRule newTargetRule)
  {
    AbstractRule oldTargetRule = targetRule;
    targetRule = newTargetRule;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_RULE__TARGET_RULE, oldTargetRule, targetRule));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getDirectives()
  {
    if (directives == null)
    {
      directives = new EObjectContainmentEList<EObject>(EObject.class, this, FormatPackage.GRAMMAR_RULE__DIRECTIVES);
    }
    return directives;
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
      case FormatPackage.GRAMMAR_RULE__DIRECTIVES:
        return ((InternalEList<?>)getDirectives()).basicRemove(otherEnd, msgs);
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
      case FormatPackage.GRAMMAR_RULE__TARGET_RULE:
        if (resolve) return getTargetRule();
        return basicGetTargetRule();
      case FormatPackage.GRAMMAR_RULE__DIRECTIVES:
        return getDirectives();
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
      case FormatPackage.GRAMMAR_RULE__TARGET_RULE:
        setTargetRule((AbstractRule)newValue);
        return;
      case FormatPackage.GRAMMAR_RULE__DIRECTIVES:
        getDirectives().clear();
        getDirectives().addAll((Collection<? extends EObject>)newValue);
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
      case FormatPackage.GRAMMAR_RULE__TARGET_RULE:
        setTargetRule((AbstractRule)null);
        return;
      case FormatPackage.GRAMMAR_RULE__DIRECTIVES:
        getDirectives().clear();
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
      case FormatPackage.GRAMMAR_RULE__TARGET_RULE:
        return targetRule != null;
      case FormatPackage.GRAMMAR_RULE__DIRECTIVES:
        return directives != null && !directives.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //GrammarRuleImpl
