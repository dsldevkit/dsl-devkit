/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl#isRef <em>Ref</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceExpressionImpl extends InterfaceItemImpl implements InterfaceExpression
{
	/**
	 * The default value of the '{@link #isRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRef()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REF_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRef() <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRef()
	 * @generated
	 * @ordered
	 */
	protected boolean ref = REF_EDEFAULT;

	/**
	 * This is true if the Ref attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean refESet;

	/**
	 * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpr()
	 * @generated
	 * @ordered
	 */
	protected Expression expr;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterfaceExpressionImpl()
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
		return ExportPackage.Literals.INTERFACE_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRef()
	{
		return ref;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRef(boolean newRef)
	{
		boolean oldRef = ref;
		ref = newRef;
		boolean oldRefESet = refESet;
		refESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.INTERFACE_EXPRESSION__REF, oldRef, ref, !oldRefESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetRef()
	{
		boolean oldRef = ref;
		boolean oldRefESet = refESet;
		ref = REF_EDEFAULT;
		refESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.INTERFACE_EXPRESSION__REF, oldRef, REF_EDEFAULT, oldRefESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetRef()
	{
		return refESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getExpr()
	{
		return expr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpr(Expression newExpr, NotificationChain msgs)
	{
		Expression oldExpr = expr;
		expr = newExpr;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExportPackage.INTERFACE_EXPRESSION__EXPR, oldExpr, newExpr);
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
	public void setExpr(Expression newExpr)
	{
		if (newExpr != expr)
		{
			NotificationChain msgs = null;
			if (expr != null)
				msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExportPackage.INTERFACE_EXPRESSION__EXPR, null, msgs);
			if (newExpr != null)
				msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExportPackage.INTERFACE_EXPRESSION__EXPR, null, msgs);
			msgs = basicSetExpr(newExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.INTERFACE_EXPRESSION__EXPR, newExpr, newExpr));
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
			case ExportPackage.INTERFACE_EXPRESSION__EXPR:
				return basicSetExpr(null, msgs);
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
			case ExportPackage.INTERFACE_EXPRESSION__REF:
				return isRef();
			case ExportPackage.INTERFACE_EXPRESSION__EXPR:
				return getExpr();
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
			case ExportPackage.INTERFACE_EXPRESSION__REF:
				setRef((Boolean)newValue);
				return;
			case ExportPackage.INTERFACE_EXPRESSION__EXPR:
				setExpr((Expression)newValue);
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
			case ExportPackage.INTERFACE_EXPRESSION__REF:
				unsetRef();
				return;
			case ExportPackage.INTERFACE_EXPRESSION__EXPR:
				setExpr((Expression)null);
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
			case ExportPackage.INTERFACE_EXPRESSION__REF:
				return isSetRef();
			case ExportPackage.INTERFACE_EXPRESSION__EXPR:
				return expr != null;
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
		result.append(" (ref: ");
		if (refESet) result.append(ref); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //InterfaceExpressionImpl
