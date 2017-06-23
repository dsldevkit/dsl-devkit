/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISubprogram</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#getISubprogram()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ISubprogram extends ICallable
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" type="com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameterList"
	 * @generated
	 */
	Iterable<? extends IFormalParameter> getParameters();

} // ISubprogram
