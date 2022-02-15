/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Declaration For Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getDeclarationForType()
 * @model
 * @generated
 */
public interface DeclarationForType extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(EClass)
   * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getDeclarationForType_Type()
   * @model
   * @generated
   */
  EClass getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(EClass value);

  /**
   * Returns the value of the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' containment reference.
   * @see #setGuard(Expression)
   * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getDeclarationForType_Guard()
   * @model containment="true"
   * @generated
   */
  Expression getGuard();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getGuard <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' containment reference.
   * @see #getGuard()
   * @generated
   */
  void setGuard(Expression value);

} // DeclarationForType
