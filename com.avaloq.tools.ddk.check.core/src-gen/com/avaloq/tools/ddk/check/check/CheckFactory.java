/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.check.check.CheckPackage
 * @generated
 */
public interface CheckFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CheckFactory eINSTANCE = com.avaloq.tools.ddk.check.check.impl.CheckFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Catalog</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Catalog</em>'.
   * @generated
   */
  CheckCatalog createCheckCatalog();

  /**
   * Returns a new object of class '<em>Documented</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Documented</em>'.
   * @generated
   */
  Documented createDocumented();

  /**
   * Returns a new object of class '<em>Implicitly Named</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Implicitly Named</em>'.
   * @generated
   */
  ImplicitlyNamed createImplicitlyNamed();

  /**
   * Returns a new object of class '<em>Category</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Category</em>'.
   * @generated
   */
  Category createCategory();

  /**
   * Returns a new object of class '<em>Check</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Check</em>'.
   * @generated
   */
  Check createCheck();

  /**
   * Returns a new object of class '<em>Severity Range</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Severity Range</em>'.
   * @generated
   */
  SeverityRange createSeverityRange();

  /**
   * Returns a new object of class '<em>Member</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Member</em>'.
   * @generated
   */
  Member createMember();

  /**
   * Returns a new object of class '<em>Implementation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Implementation</em>'.
   * @generated
   */
  Implementation createImplementation();

  /**
   * Returns a new object of class '<em>Formal Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Formal Parameter</em>'.
   * @generated
   */
  FormalParameter createFormalParameter();

  /**
   * Returns a new object of class '<em>Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Context</em>'.
   * @generated
   */
  Context createContext();

  /**
   * Returns a new object of class '<em>Context Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Context Variable</em>'.
   * @generated
   */
  ContextVariable createContextVariable();

  /**
   * Returns a new object of class '<em>XGuard Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XGuard Expression</em>'.
   * @generated
   */
  XGuardExpression createXGuardExpression();

  /**
   * Returns a new object of class '<em>XIssue Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XIssue Expression</em>'.
   * @generated
   */
  XIssueExpression createXIssueExpression();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  CheckPackage getCheckPackage();

} //CheckFactory
