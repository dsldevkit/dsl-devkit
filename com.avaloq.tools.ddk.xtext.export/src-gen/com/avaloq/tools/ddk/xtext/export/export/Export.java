/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Export</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#isAllowLookup <em>Allow Lookup</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#getNaming <em>Naming</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique <em>Fragment Unique</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#getFragmentAttribute <em>Fragment Attribute</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint <em>Fingerprint</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint <em>Resource Fingerprint</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Export#getUserData <em>User Data</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport()
 * @model
 * @generated
 */
public interface Export extends DeclarationForType
{
	/**
	 * Returns the value of the '<em><b>Allow Lookup</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Lookup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Lookup</em>' attribute.
	 * @see #isSetAllowLookup()
	 * @see #unsetAllowLookup()
	 * @see #setAllowLookup(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_AllowLookup()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isAllowLookup();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isAllowLookup <em>Allow Lookup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Lookup</em>' attribute.
	 * @see #isSetAllowLookup()
	 * @see #unsetAllowLookup()
	 * @see #isAllowLookup()
	 * @generated
	 */
	void setAllowLookup(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isAllowLookup <em>Allow Lookup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllowLookup()
	 * @see #isAllowLookup()
	 * @see #setAllowLookup(boolean)
	 * @generated
	 */
	void unsetAllowLookup();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isAllowLookup <em>Allow Lookup</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Allow Lookup</em>' attribute is set.
	 * @see #unsetAllowLookup()
	 * @see #isAllowLookup()
	 * @see #setAllowLookup(boolean)
	 * @generated
	 */
	boolean isSetAllowLookup();

	/**
	 * Returns the value of the '<em><b>Qualified Name</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Name</em>' attribute.
	 * @see #isSetQualifiedName()
	 * @see #unsetQualifiedName()
	 * @see #setQualifiedName(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_QualifiedName()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isQualifiedName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualified Name</em>' attribute.
	 * @see #isSetQualifiedName()
	 * @see #unsetQualifiedName()
	 * @see #isQualifiedName()
	 * @generated
	 */
	void setQualifiedName(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetQualifiedName()
	 * @see #isQualifiedName()
	 * @see #setQualifiedName(boolean)
	 * @generated
	 */
	void unsetQualifiedName();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName <em>Qualified Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Qualified Name</em>' attribute is set.
	 * @see #unsetQualifiedName()
	 * @see #isQualifiedName()
	 * @see #setQualifiedName(boolean)
	 * @generated
	 */
	boolean isSetQualifiedName();

	/**
	 * Returns the value of the '<em><b>Naming</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Naming</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Naming</em>' containment reference.
	 * @see #setNaming(Expression)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_Naming()
	 * @model containment="true"
	 * @generated
	 */
	Expression getNaming();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getNaming <em>Naming</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Naming</em>' containment reference.
	 * @see #getNaming()
	 * @generated
	 */
	void setNaming(Expression value);

	/**
	 * Returns the value of the '<em><b>Fragment Unique</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment Unique</em>' attribute.
	 * @see #isSetFragmentUnique()
	 * @see #unsetFragmentUnique()
	 * @see #setFragmentUnique(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_FragmentUnique()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isFragmentUnique();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique <em>Fragment Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment Unique</em>' attribute.
	 * @see #isSetFragmentUnique()
	 * @see #unsetFragmentUnique()
	 * @see #isFragmentUnique()
	 * @generated
	 */
	void setFragmentUnique(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique <em>Fragment Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFragmentUnique()
	 * @see #isFragmentUnique()
	 * @see #setFragmentUnique(boolean)
	 * @generated
	 */
	void unsetFragmentUnique();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique <em>Fragment Unique</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fragment Unique</em>' attribute is set.
	 * @see #unsetFragmentUnique()
	 * @see #isFragmentUnique()
	 * @see #setFragmentUnique(boolean)
	 * @generated
	 */
	boolean isSetFragmentUnique();

	/**
	 * Returns the value of the '<em><b>Fragment Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment Attribute</em>' reference.
	 * @see #setFragmentAttribute(EAttribute)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_FragmentAttribute()
	 * @model
	 * @generated
	 */
	EAttribute getFragmentAttribute();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getFragmentAttribute <em>Fragment Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment Attribute</em>' reference.
	 * @see #getFragmentAttribute()
	 * @generated
	 */
	void setFragmentAttribute(EAttribute value);

	/**
	 * Returns the value of the '<em><b>Fingerprint</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fingerprint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fingerprint</em>' attribute.
	 * @see #isSetFingerprint()
	 * @see #unsetFingerprint()
	 * @see #setFingerprint(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_Fingerprint()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isFingerprint();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint <em>Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fingerprint</em>' attribute.
	 * @see #isSetFingerprint()
	 * @see #unsetFingerprint()
	 * @see #isFingerprint()
	 * @generated
	 */
	void setFingerprint(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint <em>Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFingerprint()
	 * @see #isFingerprint()
	 * @see #setFingerprint(boolean)
	 * @generated
	 */
	void unsetFingerprint();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint <em>Fingerprint</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fingerprint</em>' attribute is set.
	 * @see #unsetFingerprint()
	 * @see #isFingerprint()
	 * @see #setFingerprint(boolean)
	 * @generated
	 */
	boolean isSetFingerprint();

	/**
	 * Returns the value of the '<em><b>Resource Fingerprint</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Fingerprint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Fingerprint</em>' attribute.
	 * @see #isSetResourceFingerprint()
	 * @see #unsetResourceFingerprint()
	 * @see #setResourceFingerprint(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_ResourceFingerprint()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isResourceFingerprint();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint <em>Resource Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Fingerprint</em>' attribute.
	 * @see #isSetResourceFingerprint()
	 * @see #unsetResourceFingerprint()
	 * @see #isResourceFingerprint()
	 * @generated
	 */
	void setResourceFingerprint(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint <em>Resource Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResourceFingerprint()
	 * @see #isResourceFingerprint()
	 * @see #setResourceFingerprint(boolean)
	 * @generated
	 */
	void unsetResourceFingerprint();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint <em>Resource Fingerprint</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Resource Fingerprint</em>' attribute is set.
	 * @see #unsetResourceFingerprint()
	 * @see #isResourceFingerprint()
	 * @see #setResourceFingerprint(boolean)
	 * @generated
	 */
	boolean isSetResourceFingerprint();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>User Data</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.UserData}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Data</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExport_UserData()
	 * @model containment="true"
	 * @generated
	 */
	EList<UserData> getUserData();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<EAttribute> getEAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<EAttribute> getAllEAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EAttribute getNamingAttribute();

} // Export
