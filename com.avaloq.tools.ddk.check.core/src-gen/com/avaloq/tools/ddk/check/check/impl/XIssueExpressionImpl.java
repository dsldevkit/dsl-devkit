/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.XIssueExpression;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.xbase.XExpression;

import org.eclipse.xtext.xbase.impl.XExpressionImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XIssue Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getCheck <em>Check</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getMarkerFeature <em>Marker Feature</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getMarkerObject <em>Marker Object</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getMarkerIndex <em>Marker Index</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getMessageParameters <em>Message Parameters</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getIssueCode <em>Issue Code</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl#getIssueData <em>Issue Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class XIssueExpressionImpl extends XExpressionImpl implements XIssueExpression
{
	/**
	 * The cached value of the '{@link #getCheck() <em>Check</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheck()
	 * @generated
	 * @ordered
	 */
	protected Check check;

	/**
	 * The cached value of the '{@link #getMarkerFeature() <em>Marker Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkerFeature()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature markerFeature;

	/**
	 * The cached value of the '{@link #getMarkerObject() <em>Marker Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkerObject()
	 * @generated
	 * @ordered
	 */
	protected XExpression markerObject;

	/**
	 * The cached value of the '{@link #getMarkerIndex() <em>Marker Index</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkerIndex()
	 * @generated
	 * @ordered
	 */
	protected XExpression markerIndex;

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected XExpression message;

	/**
	 * The cached value of the '{@link #getMessageParameters() <em>Message Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<XExpression> messageParameters;

	/**
	 * The default value of the '{@link #getIssueCode() <em>Issue Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueCode()
	 * @generated
	 * @ordered
	 */
	protected static final String ISSUE_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIssueCode() <em>Issue Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueCode()
	 * @generated
	 * @ordered
	 */
	protected String issueCode = ISSUE_CODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIssueData() <em>Issue Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIssueData()
	 * @generated
	 * @ordered
	 */
	protected EList<XExpression> issueData;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XIssueExpressionImpl()
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
		return CheckPackage.Literals.XISSUE_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Check getCheck()
	{
		if (check != null && check.eIsProxy())
		{
			InternalEObject oldCheck = (InternalEObject)check;
			check = (Check)eResolveProxy(oldCheck);
			if (check != oldCheck)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CheckPackage.XISSUE_EXPRESSION__CHECK, oldCheck, check));
			}
		}
		return check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Check basicGetCheck()
	{
		return check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCheck(Check newCheck)
	{
		Check oldCheck = check;
		check = newCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__CHECK, oldCheck, check));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EStructuralFeature getMarkerFeature()
	{
		if (markerFeature != null && markerFeature.eIsProxy())
		{
			InternalEObject oldMarkerFeature = (InternalEObject)markerFeature;
			markerFeature = (EStructuralFeature)eResolveProxy(oldMarkerFeature);
			if (markerFeature != oldMarkerFeature)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE, oldMarkerFeature, markerFeature));
			}
		}
		return markerFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetMarkerFeature()
	{
		return markerFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarkerFeature(EStructuralFeature newMarkerFeature)
	{
		EStructuralFeature oldMarkerFeature = markerFeature;
		markerFeature = newMarkerFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE, oldMarkerFeature, markerFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XExpression getMarkerObject()
	{
		return markerObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMarkerObject(XExpression newMarkerObject, NotificationChain msgs)
	{
		XExpression oldMarkerObject = markerObject;
		markerObject = newMarkerObject;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT, oldMarkerObject, newMarkerObject);
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
	public void setMarkerObject(XExpression newMarkerObject)
	{
		if (newMarkerObject != markerObject)
		{
			NotificationChain msgs = null;
			if (markerObject != null)
				msgs = ((InternalEObject)markerObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT, null, msgs);
			if (newMarkerObject != null)
				msgs = ((InternalEObject)newMarkerObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT, null, msgs);
			msgs = basicSetMarkerObject(newMarkerObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT, newMarkerObject, newMarkerObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XExpression getMarkerIndex()
	{
		return markerIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMarkerIndex(XExpression newMarkerIndex, NotificationChain msgs)
	{
		XExpression oldMarkerIndex = markerIndex;
		markerIndex = newMarkerIndex;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX, oldMarkerIndex, newMarkerIndex);
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
	public void setMarkerIndex(XExpression newMarkerIndex)
	{
		if (newMarkerIndex != markerIndex)
		{
			NotificationChain msgs = null;
			if (markerIndex != null)
				msgs = ((InternalEObject)markerIndex).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX, null, msgs);
			if (newMarkerIndex != null)
				msgs = ((InternalEObject)newMarkerIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX, null, msgs);
			msgs = basicSetMarkerIndex(newMarkerIndex, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX, newMarkerIndex, newMarkerIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XExpression getMessage()
	{
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessage(XExpression newMessage, NotificationChain msgs)
	{
		XExpression oldMessage = message;
		message = newMessage;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MESSAGE, oldMessage, newMessage);
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
	public void setMessage(XExpression newMessage)
	{
		if (newMessage != message)
		{
			NotificationChain msgs = null;
			if (message != null)
				msgs = ((InternalEObject)message).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MESSAGE, null, msgs);
			if (newMessage != null)
				msgs = ((InternalEObject)newMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CheckPackage.XISSUE_EXPRESSION__MESSAGE, null, msgs);
			msgs = basicSetMessage(newMessage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__MESSAGE, newMessage, newMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<XExpression> getMessageParameters()
	{
		if (messageParameters == null)
		{
			messageParameters = new EObjectContainmentEList<XExpression>(XExpression.class, this, CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS);
		}
		return messageParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIssueCode()
	{
		return issueCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIssueCode(String newIssueCode)
	{
		String oldIssueCode = issueCode;
		issueCode = newIssueCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.XISSUE_EXPRESSION__ISSUE_CODE, oldIssueCode, issueCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<XExpression> getIssueData()
	{
		if (issueData == null)
		{
			issueData = new EObjectContainmentEList<XExpression>(XExpression.class, this, CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA);
		}
		return issueData;
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
			case CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT:
				return basicSetMarkerObject(null, msgs);
			case CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX:
				return basicSetMarkerIndex(null, msgs);
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE:
				return basicSetMessage(null, msgs);
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS:
				return ((InternalEList<?>)getMessageParameters()).basicRemove(otherEnd, msgs);
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA:
				return ((InternalEList<?>)getIssueData()).basicRemove(otherEnd, msgs);
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
			case CheckPackage.XISSUE_EXPRESSION__CHECK:
				if (resolve) return getCheck();
				return basicGetCheck();
			case CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE:
				if (resolve) return getMarkerFeature();
				return basicGetMarkerFeature();
			case CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT:
				return getMarkerObject();
			case CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX:
				return getMarkerIndex();
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE:
				return getMessage();
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS:
				return getMessageParameters();
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_CODE:
				return getIssueCode();
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA:
				return getIssueData();
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
			case CheckPackage.XISSUE_EXPRESSION__CHECK:
				setCheck((Check)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE:
				setMarkerFeature((EStructuralFeature)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT:
				setMarkerObject((XExpression)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX:
				setMarkerIndex((XExpression)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE:
				setMessage((XExpression)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS:
				getMessageParameters().clear();
				getMessageParameters().addAll((Collection<? extends XExpression>)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_CODE:
				setIssueCode((String)newValue);
				return;
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA:
				getIssueData().clear();
				getIssueData().addAll((Collection<? extends XExpression>)newValue);
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
			case CheckPackage.XISSUE_EXPRESSION__CHECK:
				setCheck((Check)null);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE:
				setMarkerFeature((EStructuralFeature)null);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT:
				setMarkerObject((XExpression)null);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX:
				setMarkerIndex((XExpression)null);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE:
				setMessage((XExpression)null);
				return;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS:
				getMessageParameters().clear();
				return;
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_CODE:
				setIssueCode(ISSUE_CODE_EDEFAULT);
				return;
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA:
				getIssueData().clear();
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
			case CheckPackage.XISSUE_EXPRESSION__CHECK:
				return check != null;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_FEATURE:
				return markerFeature != null;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_OBJECT:
				return markerObject != null;
			case CheckPackage.XISSUE_EXPRESSION__MARKER_INDEX:
				return markerIndex != null;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE:
				return message != null;
			case CheckPackage.XISSUE_EXPRESSION__MESSAGE_PARAMETERS:
				return messageParameters != null && !messageParameters.isEmpty();
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_CODE:
				return ISSUE_CODE_EDEFAULT == null ? issueCode != null : !ISSUE_CODE_EDEFAULT.equals(issueCode);
			case CheckPackage.XISSUE_EXPRESSION__ISSUE_DATA:
				return issueData != null && !issueData.isEmpty();
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
		result.append(" (issueCode: ");
		result.append(issueCode);
		result.append(')');
		return result.toString();
	}

} //XIssueExpressionImpl
