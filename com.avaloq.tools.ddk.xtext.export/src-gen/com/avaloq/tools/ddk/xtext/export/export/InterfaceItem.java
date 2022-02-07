/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered <em>Unordered</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceItem()
 * @model
 * @generated
 */
public interface InterfaceItem extends EObject
{
  /**
   * Returns the value of the '<em><b>Unordered</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unordered</em>' attribute.
   * @see #isSetUnordered()
   * @see #unsetUnordered()
   * @see #setUnordered(boolean)
   * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceItem_Unordered()
   * @model default="false" unsettable="true"
   * @generated
   */
  boolean isUnordered();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unordered</em>' attribute.
   * @see #isSetUnordered()
   * @see #unsetUnordered()
   * @see #isUnordered()
   * @generated
   */
  void setUnordered(boolean value);

  /**
   * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetUnordered()
   * @see #isUnordered()
   * @see #setUnordered(boolean)
   * @generated
   */
  void unsetUnordered();

  /**
   * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered <em>Unordered</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Unordered</em>' attribute is set.
   * @see #unsetUnordered()
   * @see #isUnordered()
   * @see #setUnordered(boolean)
   * @generated
   */
  boolean isSetUnordered();

} // InterfaceItem
