/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;
import com.avaloq.tools.ddk.xtext.format.format.MatcherList;
import com.avaloq.tools.ddk.xtext.format.format.SpecificDirective;

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
 * An implementation of the model object '<em><b>Specific Directive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.SpecificDirectiveImpl#getGrammarElements <em>Grammar Elements</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.SpecificDirectiveImpl#getMatcherList <em>Matcher List</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpecificDirectiveImpl extends GrammarRuleDirectiveImpl implements SpecificDirective
{
  /**
   * The cached value of the '{@link #getGrammarElements() <em>Grammar Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrammarElements()
   * @generated
   * @ordered
   */
  protected EList<GrammarElementReference> grammarElements;

  /**
   * The cached value of the '{@link #getMatcherList() <em>Matcher List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMatcherList()
   * @generated
   * @ordered
   */
  protected MatcherList matcherList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SpecificDirectiveImpl()
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
    return FormatPackage.Literals.SPECIFIC_DIRECTIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<GrammarElementReference> getGrammarElements()
  {
    if (grammarElements == null)
    {
      grammarElements = new EObjectContainmentEList<GrammarElementReference>(GrammarElementReference.class, this, FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS);
    }
    return grammarElements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public MatcherList getMatcherList()
  {
    return matcherList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMatcherList(MatcherList newMatcherList, NotificationChain msgs)
  {
    MatcherList oldMatcherList = matcherList;
    matcherList = newMatcherList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST, oldMatcherList, newMatcherList);
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
  public void setMatcherList(MatcherList newMatcherList)
  {
    if (newMatcherList != matcherList)
    {
      NotificationChain msgs = null;
      if (matcherList != null)
        msgs = ((InternalEObject)matcherList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST, null, msgs);
      if (newMatcherList != null)
        msgs = ((InternalEObject)newMatcherList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST, null, msgs);
      msgs = basicSetMatcherList(newMatcherList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST, newMatcherList, newMatcherList));
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
      case FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS:
        return ((InternalEList<?>)getGrammarElements()).basicRemove(otherEnd, msgs);
      case FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST:
        return basicSetMatcherList(null, msgs);
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
      case FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS:
        return getGrammarElements();
      case FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST:
        return getMatcherList();
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
      case FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS:
        getGrammarElements().clear();
        getGrammarElements().addAll((Collection<? extends GrammarElementReference>)newValue);
        return;
      case FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST:
        setMatcherList((MatcherList)newValue);
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
      case FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS:
        getGrammarElements().clear();
        return;
      case FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST:
        setMatcherList((MatcherList)null);
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
      case FormatPackage.SPECIFIC_DIRECTIVE__GRAMMAR_ELEMENTS:
        return grammarElements != null && !grammarElements.isEmpty();
      case FormatPackage.SPECIFIC_DIRECTIVE__MATCHER_LIST:
        return matcherList != null;
    }
    return super.eIsSet(featureID);
  }

} //SpecificDirectiveImpl
