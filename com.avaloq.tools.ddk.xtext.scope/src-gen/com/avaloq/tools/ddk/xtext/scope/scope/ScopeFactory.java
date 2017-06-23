/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage
 * @generated
 */
public interface ScopeFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ScopeFactory eINSTANCE = com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  ScopeModel createScopeModel();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  Import createImport();

  /**
   * Returns a new object of class '<em>Extension</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Extension</em>'.
   * @generated
   */
  Extension createExtension();

  /**
   * Returns a new object of class '<em>Naming Section</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Naming Section</em>'.
   * @generated
   */
  NamingSection createNamingSection();

  /**
   * Returns a new object of class '<em>Naming Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Naming Definition</em>'.
   * @generated
   */
  NamingDefinition createNamingDefinition();

  /**
   * Returns a new object of class '<em>Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Definition</em>'.
   * @generated
   */
  ScopeDefinition createScopeDefinition();

  /**
   * Returns a new object of class '<em>Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Rule</em>'.
   * @generated
   */
  ScopeRule createScopeRule();

  /**
   * Returns a new object of class '<em>Context</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Context</em>'.
   * @generated
   */
  ScopeContext createScopeContext();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  ScopeExpression createScopeExpression();

  /**
   * Returns a new object of class '<em>Factory Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Factory Expression</em>'.
   * @generated
   */
  FactoryExpression createFactoryExpression();

  /**
   * Returns a new object of class '<em>Delegation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Delegation</em>'.
   * @generated
   */
  ScopeDelegation createScopeDelegation();

  /**
   * Returns a new object of class '<em>Named Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Named Scope Expression</em>'.
   * @generated
   */
  NamedScopeExpression createNamedScopeExpression();

  /**
   * Returns a new object of class '<em>Global Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Global Scope Expression</em>'.
   * @generated
   */
  GlobalScopeExpression createGlobalScopeExpression();

  /**
   * Returns a new object of class '<em>Data Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Expression</em>'.
   * @generated
   */
  DataExpression createDataExpression();

  /**
   * Returns a new object of class '<em>Match Data Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Match Data Expression</em>'.
   * @generated
   */
  MatchDataExpression createMatchDataExpression();

  /**
   * Returns a new object of class '<em>Lambda Data Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Lambda Data Expression</em>'.
   * @generated
   */
  LambdaDataExpression createLambdaDataExpression();

  /**
   * Returns a new object of class '<em>Simple Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Scope Expression</em>'.
   * @generated
   */
  SimpleScopeExpression createSimpleScopeExpression();

  /**
   * Returns a new object of class '<em>Naming</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Naming</em>'.
   * @generated
   */
  Naming createNaming();

  /**
   * Returns a new object of class '<em>Naming Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Naming Expression</em>'.
   * @generated
   */
  NamingExpression createNamingExpression();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ScopePackage getScopePackage();

} //ScopeFactory
