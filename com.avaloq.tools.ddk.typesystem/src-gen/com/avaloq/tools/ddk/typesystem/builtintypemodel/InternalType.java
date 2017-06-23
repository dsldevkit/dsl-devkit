/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel;

import com.avaloq.tools.ddk.typesystem.typemodel.INamedType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Internal Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 *  Internal types are used by the type system to handle certain cases such as errors, referenced elements that
 *  may (incorrectly) appear in an expression but have no type (e.g., procedures) and situations in which it 
 *  cannot infer the type of an expression because of incomplete information. These types may not be used as 
 *  the type of any element. That is, languages may not allow users to declare an element having one of these types.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage#getInternalType()
 * @model
 * @generated
 */
public interface InternalType extends INamedType
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage#getInternalType_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // InternalType
