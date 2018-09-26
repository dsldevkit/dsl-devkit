/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grammar Element Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarElementReferenceImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarElementReferenceImpl#getRuleCall <em>Rule Call</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarElementReferenceImpl#getSelf <em>Self</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarElementReferenceImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GrammarElementReferenceImpl#getKeyword <em>Keyword</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GrammarElementReferenceImpl extends MinimalEObjectImpl.Container implements GrammarElementReference
{
  /**
   * The cached value of the '{@link #getAssignment() <em>Assignment</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssignment()
   * @generated
   * @ordered
   */
  protected Assignment assignment;

  /**
   * The cached value of the '{@link #getRuleCall() <em>Rule Call</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuleCall()
   * @generated
   * @ordered
   */
  protected RuleCall ruleCall;

  /**
   * The cached value of the '{@link #getSelf() <em>Self</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelf()
   * @generated
   * @ordered
   */
  protected AbstractRule self;

  /**
   * The cached value of the '{@link #getRule() <em>Rule</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRule()
   * @generated
   * @ordered
   */
  protected AbstractRule rule;

  /**
   * The cached value of the '{@link #getKeyword() <em>Keyword</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyword()
   * @generated
   * @ordered
   */
  protected Keyword keyword;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GrammarElementReferenceImpl()
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
    return FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Assignment getAssignment()
  {
    if (assignment != null && assignment.eIsProxy())
    {
      InternalEObject oldAssignment = (InternalEObject)assignment;
      assignment = (Assignment)eResolveProxy(oldAssignment);
      if (assignment != oldAssignment)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT, oldAssignment, assignment));
      }
    }
    return assignment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Assignment basicGetAssignment()
  {
    return assignment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAssignment(Assignment newAssignment)
  {
    Assignment oldAssignment = assignment;
    assignment = newAssignment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT, oldAssignment, assignment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RuleCall getRuleCall()
  {
    if (ruleCall != null && ruleCall.eIsProxy())
    {
      InternalEObject oldRuleCall = (InternalEObject)ruleCall;
      ruleCall = (RuleCall)eResolveProxy(oldRuleCall);
      if (ruleCall != oldRuleCall)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL, oldRuleCall, ruleCall));
      }
    }
    return ruleCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RuleCall basicGetRuleCall()
  {
    return ruleCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRuleCall(RuleCall newRuleCall)
  {
    RuleCall oldRuleCall = ruleCall;
    ruleCall = newRuleCall;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL, oldRuleCall, ruleCall));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule getSelf()
  {
    if (self != null && self.eIsProxy())
    {
      InternalEObject oldSelf = (InternalEObject)self;
      self = (AbstractRule)eResolveProxy(oldSelf);
      if (self != oldSelf)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF, oldSelf, self));
      }
    }
    return self;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule basicGetSelf()
  {
    return self;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelf(AbstractRule newSelf)
  {
    AbstractRule oldSelf = self;
    self = newSelf;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF, oldSelf, self));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule getRule()
  {
    if (rule != null && rule.eIsProxy())
    {
      InternalEObject oldRule = (InternalEObject)rule;
      rule = (AbstractRule)eResolveProxy(oldRule);
      if (rule != oldRule)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE, oldRule, rule));
      }
    }
    return rule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractRule basicGetRule()
  {
    return rule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRule(AbstractRule newRule)
  {
    AbstractRule oldRule = rule;
    rule = newRule;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE, oldRule, rule));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Keyword getKeyword()
  {
    if (keyword != null && keyword.eIsProxy())
    {
      InternalEObject oldKeyword = (InternalEObject)keyword;
      keyword = (Keyword)eResolveProxy(oldKeyword);
      if (keyword != oldKeyword)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD, oldKeyword, keyword));
      }
    }
    return keyword;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Keyword basicGetKeyword()
  {
    return keyword;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKeyword(Keyword newKeyword)
  {
    Keyword oldKeyword = keyword;
    keyword = newKeyword;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD, oldKeyword, keyword));
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
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT:
        if (resolve) return getAssignment();
        return basicGetAssignment();
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL:
        if (resolve) return getRuleCall();
        return basicGetRuleCall();
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF:
        if (resolve) return getSelf();
        return basicGetSelf();
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE:
        if (resolve) return getRule();
        return basicGetRule();
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD:
        if (resolve) return getKeyword();
        return basicGetKeyword();
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
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT:
        setAssignment((Assignment)newValue);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL:
        setRuleCall((RuleCall)newValue);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF:
        setSelf((AbstractRule)newValue);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE:
        setRule((AbstractRule)newValue);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD:
        setKeyword((Keyword)newValue);
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
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT:
        setAssignment((Assignment)null);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL:
        setRuleCall((RuleCall)null);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF:
        setSelf((AbstractRule)null);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE:
        setRule((AbstractRule)null);
        return;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD:
        setKeyword((Keyword)null);
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
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT:
        return assignment != null;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL:
        return ruleCall != null;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__SELF:
        return self != null;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__RULE:
        return rule != null;
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE__KEYWORD:
        return keyword != null;
    }
    return super.eIsSet(featureID);
  }

} //GrammarElementReferenceImpl
