/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage
 * @generated
 */
public interface ValidFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ValidFactory eINSTANCE = com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  ValidModel createValidModel();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  Import createImport();

  /**
   * Returns a new object of class '<em>Category</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Category</em>'.
   * @generated
   */
  Category createCategory();

  /**
   * Returns a new object of class '<em>Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Rule</em>'.
   * @generated
   */
  Rule createRule();

  /**
   * Returns a new object of class '<em>Predefined Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Predefined Rule</em>'.
   * @generated
   */
  PredefinedRule createPredefinedRule();

  /**
   * Returns a new object of class '<em>Native Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Native Rule</em>'.
   * @generated
   */
  NativeRule createNativeRule();

  /**
   * Returns a new object of class '<em>Size Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Size Rule</em>'.
   * @generated
   */
  SizeRule createSizeRule();

  /**
   * Returns a new object of class '<em>Range Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Range Rule</em>'.
   * @generated
   */
  RangeRule createRangeRule();

  /**
   * Returns a new object of class '<em>Unique Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unique Rule</em>'.
   * @generated
   */
  UniqueRule createUniqueRule();

  /**
   * Returns a new object of class '<em>Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Context</em>'.
   * @generated
   */
  Context createContext();

  /**
   * Returns a new object of class '<em>Simple Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Context</em>'.
   * @generated
   */
  SimpleContext createSimpleContext();

  /**
   * Returns a new object of class '<em>Duplicate Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Duplicate Context</em>'.
   * @generated
   */
  DuplicateContext createDuplicateContext();

  /**
   * Returns a new object of class '<em>Native Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Native Context</em>'.
   * @generated
   */
  NativeContext createNativeContext();

  /**
   * Returns a new object of class '<em>Quick Fix</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Quick Fix</em>'.
   * @generated
   */
  QuickFix createQuickFix();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ValidPackage getValidPackage();

} //ValidFactory
