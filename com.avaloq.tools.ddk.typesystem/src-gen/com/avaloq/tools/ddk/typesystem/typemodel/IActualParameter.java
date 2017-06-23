/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IActual Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#getIActualParameter()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IActualParameter extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an actual parameter is an expression. Languages that implement this interface
	 * may place additional restrictions on the allowed expression. For example, a language that
	 * supports reference semantics may require that the expression be a variable when the
	 * corresponding formal parameter requires it. This, of course, would also require that the
	 * language extend {@link IFormalParameter} to specify these semantics.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	IExpression getValue();

} // IActualParameter
