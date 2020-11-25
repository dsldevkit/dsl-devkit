/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef <em>Ref</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceExpression()
 * @model
 * @generated
 */
public interface InterfaceExpression extends InterfaceItem
{
	/**
	 * Returns the value of the '<em><b>Ref</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref</em>' attribute.
	 * @see #isSetRef()
	 * @see #unsetRef()
	 * @see #setRef(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceExpression_Ref()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isRef();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' attribute.
	 * @see #isSetRef()
	 * @see #unsetRef()
	 * @see #isRef()
	 * @generated
	 */
	void setRef(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRef()
	 * @see #isRef()
	 * @see #setRef(boolean)
	 * @generated
	 */
	void unsetRef();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef <em>Ref</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ref</em>' attribute is set.
	 * @see #unsetRef()
	 * @see #isRef()
	 * @see #setRef(boolean)
	 * @generated
	 */
	boolean isSetRef();

	/**
	 * Returns the value of the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr</em>' containment reference.
	 * @see #setExpr(Expression)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceExpression_Expr()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpr();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#getExpr <em>Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr</em>' containment reference.
	 * @see #getExpr()
	 * @generated
	 */
	void setExpr(Expression value);

} // InterfaceExpression
