/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRuleDirective;
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock;
import com.avaloq.tools.ddk.xtext.format.format.MatcherList;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.CompoundElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GroupBlockImpl#getGrammarElement <em>Grammar Element</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GroupBlockImpl#getMatcherList <em>Matcher List</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GroupBlockImpl#getSubGroup <em>Sub Group</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.GroupBlockImpl#getDirectives <em>Directives</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupBlockImpl extends MinimalEObjectImpl.Container implements GroupBlock
{
  /**
   * The cached value of the '{@link #getGrammarElement() <em>Grammar Element</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrammarElement()
   * @generated
   * @ordered
   */
  protected CompoundElement grammarElement;

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
   * The cached value of the '{@link #getSubGroup() <em>Sub Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubGroup()
   * @generated
   * @ordered
   */
  protected GroupBlock subGroup;

  /**
   * The cached value of the '{@link #getDirectives() <em>Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirectives()
   * @generated
   * @ordered
   */
  protected EList<GrammarRuleDirective> directives;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupBlockImpl()
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
    return FormatPackage.Literals.GROUP_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundElement getGrammarElement()
  {
    if (grammarElement != null && grammarElement.eIsProxy())
    {
      InternalEObject oldGrammarElement = (InternalEObject)grammarElement;
      grammarElement = (CompoundElement)eResolveProxy(oldGrammarElement);
      if (grammarElement != oldGrammarElement)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT, oldGrammarElement, grammarElement));
      }
    }
    return grammarElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundElement basicGetGrammarElement()
  {
    return grammarElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGrammarElement(CompoundElement newGrammarElement)
  {
    CompoundElement oldGrammarElement = grammarElement;
    grammarElement = newGrammarElement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT, oldGrammarElement, grammarElement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.GROUP_BLOCK__MATCHER_LIST, oldMatcherList, newMatcherList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMatcherList(MatcherList newMatcherList)
  {
    if (newMatcherList != matcherList)
    {
      NotificationChain msgs = null;
      if (matcherList != null)
        msgs = ((InternalEObject)matcherList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.GROUP_BLOCK__MATCHER_LIST, null, msgs);
      if (newMatcherList != null)
        msgs = ((InternalEObject)newMatcherList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.GROUP_BLOCK__MATCHER_LIST, null, msgs);
      msgs = basicSetMatcherList(newMatcherList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GROUP_BLOCK__MATCHER_LIST, newMatcherList, newMatcherList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupBlock getSubGroup()
  {
    return subGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubGroup(GroupBlock newSubGroup, NotificationChain msgs)
  {
    GroupBlock oldSubGroup = subGroup;
    subGroup = newSubGroup;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.GROUP_BLOCK__SUB_GROUP, oldSubGroup, newSubGroup);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubGroup(GroupBlock newSubGroup)
  {
    if (newSubGroup != subGroup)
    {
      NotificationChain msgs = null;
      if (subGroup != null)
        msgs = ((InternalEObject)subGroup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.GROUP_BLOCK__SUB_GROUP, null, msgs);
      if (newSubGroup != null)
        msgs = ((InternalEObject)newSubGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.GROUP_BLOCK__SUB_GROUP, null, msgs);
      msgs = basicSetSubGroup(newSubGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.GROUP_BLOCK__SUB_GROUP, newSubGroup, newSubGroup));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GrammarRuleDirective> getDirectives()
  {
    if (directives == null)
    {
      directives = new EObjectContainmentEList<GrammarRuleDirective>(GrammarRuleDirective.class, this, FormatPackage.GROUP_BLOCK__DIRECTIVES);
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
      case FormatPackage.GROUP_BLOCK__MATCHER_LIST:
        return basicSetMatcherList(null, msgs);
      case FormatPackage.GROUP_BLOCK__SUB_GROUP:
        return basicSetSubGroup(null, msgs);
      case FormatPackage.GROUP_BLOCK__DIRECTIVES:
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
      case FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT:
        if (resolve) return getGrammarElement();
        return basicGetGrammarElement();
      case FormatPackage.GROUP_BLOCK__MATCHER_LIST:
        return getMatcherList();
      case FormatPackage.GROUP_BLOCK__SUB_GROUP:
        return getSubGroup();
      case FormatPackage.GROUP_BLOCK__DIRECTIVES:
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
      case FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT:
        setGrammarElement((CompoundElement)newValue);
        return;
      case FormatPackage.GROUP_BLOCK__MATCHER_LIST:
        setMatcherList((MatcherList)newValue);
        return;
      case FormatPackage.GROUP_BLOCK__SUB_GROUP:
        setSubGroup((GroupBlock)newValue);
        return;
      case FormatPackage.GROUP_BLOCK__DIRECTIVES:
        getDirectives().clear();
        getDirectives().addAll((Collection<? extends GrammarRuleDirective>)newValue);
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
      case FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT:
        setGrammarElement((CompoundElement)null);
        return;
      case FormatPackage.GROUP_BLOCK__MATCHER_LIST:
        setMatcherList((MatcherList)null);
        return;
      case FormatPackage.GROUP_BLOCK__SUB_GROUP:
        setSubGroup((GroupBlock)null);
        return;
      case FormatPackage.GROUP_BLOCK__DIRECTIVES:
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
      case FormatPackage.GROUP_BLOCK__GRAMMAR_ELEMENT:
        return grammarElement != null;
      case FormatPackage.GROUP_BLOCK__MATCHER_LIST:
        return matcherList != null;
      case FormatPackage.GROUP_BLOCK__SUB_GROUP:
        return subGroup != null;
      case FormatPackage.GROUP_BLOCK__DIRECTIVES:
        return directives != null && !directives.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //GroupBlockImpl
