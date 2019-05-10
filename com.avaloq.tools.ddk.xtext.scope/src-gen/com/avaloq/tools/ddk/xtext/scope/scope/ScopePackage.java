/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeFactory
 * @model kind="package"
 * @generated
 */
public interface ScopePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "scope";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.avaloq.com/tools/ddk/xtext/Scope";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "scope";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ScopePackage eINSTANCE = com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl.init();

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeModel()
   * @generated
   */
  int SCOPE_MODEL = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__NAME = 0;

  /**
   * The feature id for the '<em><b>Included Scopes</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__INCLUDED_SCOPES = 1;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__IMPORTS = 2;

  /**
   * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__EXTENSIONS = 3;

  /**
   * The feature id for the '<em><b>Injections</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__INJECTIONS = 4;

  /**
   * The feature id for the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__NAMING = 5;

  /**
   * The feature id for the '<em><b>Scopes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL__SCOPES = 6;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_MODEL_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ImportImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getImport()
   * @generated
   */
  int IMPORT = 1;

  /**
   * The feature id for the '<em><b>Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__PACKAGE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__NAME = 1;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ExtensionImpl <em>Extension</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ExtensionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getExtension()
   * @generated
   */
  int EXTENSION = 2;

  /**
   * The feature id for the '<em><b>Extension</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENSION__EXTENSION = 0;

  /**
   * The number of structural features of the '<em>Extension</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.InjectionImpl <em>Injection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.InjectionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getInjection()
   * @generated
   */
  int INJECTION = 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INJECTION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INJECTION__NAME = 1;

  /**
   * The number of structural features of the '<em>Injection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INJECTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl <em>Naming Section</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingSection()
   * @generated
   */
  int NAMING_SECTION = 4;

  /**
   * The feature id for the '<em><b>Casing</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_SECTION__CASING = 0;

  /**
   * The feature id for the '<em><b>Namings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_SECTION__NAMINGS = 1;

  /**
   * The number of structural features of the '<em>Naming Section</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_SECTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingDefinitionImpl <em>Naming Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingDefinitionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingDefinition()
   * @generated
   */
  int NAMING_DEFINITION = 5;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_DEFINITION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_DEFINITION__NAMING = 1;

  /**
   * The number of structural features of the '<em>Naming Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_DEFINITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl <em>Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeDefinition()
   * @generated
   */
  int SCOPE_DEFINITION = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Target Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION__TARGET_TYPE = 1;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION__CONTEXT_TYPE = 2;

  /**
   * The feature id for the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION__REFERENCE = 3;

  /**
   * The feature id for the '<em><b>Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION__RULES = 4;

  /**
   * The number of structural features of the '<em>Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DEFINITION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl <em>Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeRule()
   * @generated
   */
  int SCOPE_RULE = 7;

  /**
   * The feature id for the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_RULE__CONTEXT = 0;

  /**
   * The feature id for the '<em><b>Exprs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_RULE__EXPRS = 1;

  /**
   * The number of structural features of the '<em>Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_RULE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl <em>Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeContext()
   * @generated
   */
  int SCOPE_CONTEXT = 8;

  /**
   * The feature id for the '<em><b>Global</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_CONTEXT__GLOBAL = 0;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_CONTEXT__CONTEXT_TYPE = 1;

  /**
   * The feature id for the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_CONTEXT__GUARD = 2;

  /**
   * The number of structural features of the '<em>Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_CONTEXT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeExpression()
   * @generated
   */
  int SCOPE_EXPRESSION = 9;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.FactoryExpressionImpl <em>Factory Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.FactoryExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getFactoryExpression()
   * @generated
   */
  int FACTORY_EXPRESSION = 10;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FACTORY_EXPRESSION__EXPR = SCOPE_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Factory Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FACTORY_EXPRESSION_FEATURE_COUNT = SCOPE_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl <em>Delegation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeDelegation()
   * @generated
   */
  int SCOPE_DELEGATION = 11;

  /**
   * The feature id for the '<em><b>Delegate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DELEGATION__DELEGATE = SCOPE_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>External</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DELEGATION__EXTERNAL = SCOPE_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Scope</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DELEGATION__SCOPE = SCOPE_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Delegation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCOPE_DELEGATION_FEATURE_COUNT = SCOPE_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl <em>Named Scope Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamedScopeExpression()
   * @generated
   */
  int NAMED_SCOPE_EXPRESSION = 12;

  /**
   * The feature id for the '<em><b>Case Def</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SCOPE_EXPRESSION__CASE_DEF = SCOPE_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Casing</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SCOPE_EXPRESSION__CASING = SCOPE_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SCOPE_EXPRESSION__NAMING = SCOPE_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Named Scope Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SCOPE_EXPRESSION_FEATURE_COUNT = SCOPE_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl <em>Global Scope Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getGlobalScopeExpression()
   * @generated
   */
  int GLOBAL_SCOPE_EXPRESSION = 13;

  /**
   * The feature id for the '<em><b>Case Def</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__CASE_DEF = NAMED_SCOPE_EXPRESSION__CASE_DEF;

  /**
   * The feature id for the '<em><b>Casing</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__CASING = NAMED_SCOPE_EXPRESSION__CASING;

  /**
   * The feature id for the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__NAMING = NAMED_SCOPE_EXPRESSION__NAMING;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__TYPE = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__NAME = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Recursive Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Prefix</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__PREFIX = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Data</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__DATA = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Domains</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION__DOMAINS = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Global Scope Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_SCOPE_EXPRESSION_FEATURE_COUNT = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.DataExpressionImpl <em>Data Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.DataExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getDataExpression()
   * @generated
   */
  int DATA_EXPRESSION = 14;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_EXPRESSION__VALUE = 0;

  /**
   * The number of structural features of the '<em>Data Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.MatchDataExpressionImpl <em>Match Data Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.MatchDataExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getMatchDataExpression()
   * @generated
   */
  int MATCH_DATA_EXPRESSION = 15;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATCH_DATA_EXPRESSION__VALUE = DATA_EXPRESSION__VALUE;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATCH_DATA_EXPRESSION__KEY = DATA_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Match Data Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATCH_DATA_EXPRESSION_FEATURE_COUNT = DATA_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.LambdaDataExpressionImpl <em>Lambda Data Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.LambdaDataExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getLambdaDataExpression()
   * @generated
   */
  int LAMBDA_DATA_EXPRESSION = 16;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAMBDA_DATA_EXPRESSION__VALUE = DATA_EXPRESSION__VALUE;

  /**
   * The feature id for the '<em><b>Desc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAMBDA_DATA_EXPRESSION__DESC = DATA_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Lambda Data Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAMBDA_DATA_EXPRESSION_FEATURE_COUNT = DATA_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.SimpleScopeExpressionImpl <em>Simple Scope Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.SimpleScopeExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getSimpleScopeExpression()
   * @generated
   */
  int SIMPLE_SCOPE_EXPRESSION = 17;

  /**
   * The feature id for the '<em><b>Case Def</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_SCOPE_EXPRESSION__CASE_DEF = NAMED_SCOPE_EXPRESSION__CASE_DEF;

  /**
   * The feature id for the '<em><b>Casing</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_SCOPE_EXPRESSION__CASING = NAMED_SCOPE_EXPRESSION__CASING;

  /**
   * The feature id for the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_SCOPE_EXPRESSION__NAMING = NAMED_SCOPE_EXPRESSION__NAMING;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_SCOPE_EXPRESSION__EXPR = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Simple Scope Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_SCOPE_EXPRESSION_FEATURE_COUNT = NAMED_SCOPE_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingImpl <em>Naming</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNaming()
   * @generated
   */
  int NAMING = 18;

  /**
   * The feature id for the '<em><b>Names</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING__NAMES = 0;

  /**
   * The number of structural features of the '<em>Naming</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl <em>Naming Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingExpression()
   * @generated
   */
  int NAMING_EXPRESSION = 19;

  /**
   * The feature id for the '<em><b>Export</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_EXPRESSION__EXPORT = 0;

  /**
   * The feature id for the '<em><b>Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_EXPRESSION__FACTORY = 1;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_EXPRESSION__EXPRESSION = 2;

  /**
   * The number of structural features of the '<em>Naming Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMING_EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.Casing <em>Casing</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getCasing()
   * @generated
   */
  int CASING = 20;


  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
   * @generated
   */
  EClass getScopeModel();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getName()
   * @see #getScopeModel()
   * @generated
   */
  EAttribute getScopeModel_Name();

  /**
   * Returns the meta object for the reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getIncludedScopes <em>Included Scopes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Included Scopes</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getIncludedScopes()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_IncludedScopes();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getImports()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_Imports();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getExtensions <em>Extensions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Extensions</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getExtensions()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_Extensions();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getInjections <em>Injections</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Injections</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getInjections()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_Injections();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getNaming <em>Naming</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Naming</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getNaming()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_Naming();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getScopes <em>Scopes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Scopes</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getScopes()
   * @see #getScopeModel()
   * @generated
   */
  EReference getScopeModel_Scopes();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.Import#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Package</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Import#getPackage()
   * @see #getImport()
   * @generated
   */
  EReference getImport_Package();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.Import#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Import#getName()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_Name();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Extension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extension</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Extension
   * @generated
   */
  EClass getExtension();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.Extension#getExtension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Extension</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Extension#getExtension()
   * @see #getExtension()
   * @generated
   */
  EAttribute getExtension_Extension();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection <em>Injection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Injection</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Injection
   * @generated
   */
  EClass getInjection();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Injection#getType()
   * @see #getInjection()
   * @generated
   */
  EAttribute getInjection_Type();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Injection#getName()
   * @see #getInjection()
   * @generated
   */
  EAttribute getInjection_Name();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection <em>Naming Section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Naming Section</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingSection
   * @generated
   */
  EClass getNamingSection();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getCasing <em>Casing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Casing</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getCasing()
   * @see #getNamingSection()
   * @generated
   */
  EAttribute getNamingSection_Casing();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getNamings <em>Namings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Namings</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getNamings()
   * @see #getNamingSection()
   * @generated
   */
  EReference getNamingSection_Namings();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition <em>Naming Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Naming Definition</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition
   * @generated
   */
  EClass getNamingDefinition();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getType()
   * @see #getNamingDefinition()
   * @generated
   */
  EReference getNamingDefinition_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getNaming <em>Naming</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Naming</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getNaming()
   * @see #getNamingDefinition()
   * @generated
   */
  EReference getNamingDefinition_Naming();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Definition</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition
   * @generated
   */
  EClass getScopeDefinition();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getName()
   * @see #getScopeDefinition()
   * @generated
   */
  EAttribute getScopeDefinition_Name();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getTargetType <em>Target Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getTargetType()
   * @see #getScopeDefinition()
   * @generated
   */
  EReference getScopeDefinition_TargetType();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getContextType <em>Context Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getContextType()
   * @see #getScopeDefinition()
   * @generated
   */
  EReference getScopeDefinition_ContextType();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reference</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getReference()
   * @see #getScopeDefinition()
   * @generated
   */
  EReference getScopeDefinition_Reference();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getRules <em>Rules</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Rules</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getRules()
   * @see #getScopeDefinition()
   * @generated
   */
  EReference getScopeDefinition_Rules();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule <em>Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule
   * @generated
   */
  EClass getScopeRule();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getContext()
   * @see #getScopeRule()
   * @generated
   */
  EReference getScopeRule_Context();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getExprs <em>Exprs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exprs</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getExprs()
   * @see #getScopeRule()
   * @generated
   */
  EReference getScopeRule_Exprs();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext
   * @generated
   */
  EClass getScopeContext();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#isGlobal <em>Global</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Global</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#isGlobal()
   * @see #getScopeContext()
   * @generated
   */
  EAttribute getScopeContext_Global();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getContextType <em>Context Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getContextType()
   * @see #getScopeContext()
   * @generated
   */
  EReference getScopeContext_ContextType();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getGuard <em>Guard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Guard</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getGuard()
   * @see #getScopeContext()
   * @generated
   */
  EReference getScopeContext_Guard();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression
   * @generated
   */
  EClass getScopeExpression();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression <em>Factory Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Factory Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression
   * @generated
   */
  EClass getFactoryExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression#getExpr()
   * @see #getFactoryExpression()
   * @generated
   */
  EReference getFactoryExpression_Expr();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation <em>Delegation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Delegation</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation
   * @generated
   */
  EClass getScopeDelegation();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getDelegate <em>Delegate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Delegate</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getDelegate()
   * @see #getScopeDelegation()
   * @generated
   */
  EReference getScopeDelegation_Delegate();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getExternal <em>External</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>External</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getExternal()
   * @see #getScopeDelegation()
   * @generated
   */
  EReference getScopeDelegation_External();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Scope</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getScope()
   * @see #getScopeDelegation()
   * @generated
   */
  EReference getScopeDelegation_Scope();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression <em>Named Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Scope Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression
   * @generated
   */
  EClass getNamedScopeExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#isCaseDef <em>Case Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Case Def</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#isCaseDef()
   * @see #getNamedScopeExpression()
   * @generated
   */
  EAttribute getNamedScopeExpression_CaseDef();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getCasing <em>Casing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Casing</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getCasing()
   * @see #getNamedScopeExpression()
   * @generated
   */
  EAttribute getNamedScopeExpression_Casing();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getNaming <em>Naming</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Naming</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getNaming()
   * @see #getNamedScopeExpression()
   * @generated
   */
  EReference getNamedScopeExpression_Naming();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression <em>Global Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Global Scope Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression
   * @generated
   */
  EClass getGlobalScopeExpression();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getType()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EReference getGlobalScopeExpression_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getName()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EReference getGlobalScopeExpression_Name();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#isRecursivePrefix <em>Recursive Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Recursive Prefix</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#isRecursivePrefix()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EAttribute getGlobalScopeExpression_RecursivePrefix();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getPrefix <em>Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Prefix</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getPrefix()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EReference getGlobalScopeExpression_Prefix();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getData <em>Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Data</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getData()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EReference getGlobalScopeExpression_Data();

  /**
   * Returns the meta object for the attribute list '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getDomains <em>Domains</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Domains</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getDomains()
   * @see #getGlobalScopeExpression()
   * @generated
   */
  EAttribute getGlobalScopeExpression_Domains();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.DataExpression <em>Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.DataExpression
   * @generated
   */
  EClass getDataExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.DataExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.DataExpression#getValue()
   * @see #getDataExpression()
   * @generated
   */
  EReference getDataExpression_Value();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression <em>Match Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Match Data Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression
   * @generated
   */
  EClass getMatchDataExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression#getKey()
   * @see #getMatchDataExpression()
   * @generated
   */
  EAttribute getMatchDataExpression_Key();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression <em>Lambda Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lambda Data Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression
   * @generated
   */
  EClass getLambdaDataExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression#getDesc <em>Desc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Desc</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression#getDesc()
   * @see #getLambdaDataExpression()
   * @generated
   */
  EAttribute getLambdaDataExpression_Desc();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression <em>Simple Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Scope Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression
   * @generated
   */
  EClass getSimpleScopeExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression#getExpr()
   * @see #getSimpleScopeExpression()
   * @generated
   */
  EReference getSimpleScopeExpression_Expr();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Naming <em>Naming</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Naming</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Naming
   * @generated
   */
  EClass getNaming();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.scope.scope.Naming#getNames <em>Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Names</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Naming#getNames()
   * @see #getNaming()
   * @generated
   */
  EReference getNaming_Names();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression <em>Naming Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Naming Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression
   * @generated
   */
  EClass getNamingExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isExport <em>Export</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Export</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isExport()
   * @see #getNamingExpression()
   * @generated
   */
  EAttribute getNamingExpression_Export();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isFactory <em>Factory</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Factory</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isFactory()
   * @see #getNamingExpression()
   * @generated
   */
  EAttribute getNamingExpression_Factory();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#getExpression()
   * @see #getNamingExpression()
   * @generated
   */
  EReference getNamingExpression_Expression();

  /**
   * Returns the meta object for enum '{@link com.avaloq.tools.ddk.xtext.scope.scope.Casing <em>Casing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Casing</em>'.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @generated
   */
  EEnum getCasing();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ScopeFactory getScopeFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeModel()
     * @generated
     */
    EClass SCOPE_MODEL = eINSTANCE.getScopeModel();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SCOPE_MODEL__NAME = eINSTANCE.getScopeModel_Name();

    /**
     * The meta object literal for the '<em><b>Included Scopes</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__INCLUDED_SCOPES = eINSTANCE.getScopeModel_IncludedScopes();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__IMPORTS = eINSTANCE.getScopeModel_Imports();

    /**
     * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__EXTENSIONS = eINSTANCE.getScopeModel_Extensions();

    /**
     * The meta object literal for the '<em><b>Injections</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__INJECTIONS = eINSTANCE.getScopeModel_Injections();

    /**
     * The meta object literal for the '<em><b>Naming</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__NAMING = eINSTANCE.getScopeModel_Naming();

    /**
     * The meta object literal for the '<em><b>Scopes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_MODEL__SCOPES = eINSTANCE.getScopeModel_Scopes();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ImportImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPORT__PACKAGE = eINSTANCE.getImport_Package();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__NAME = eINSTANCE.getImport_Name();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ExtensionImpl <em>Extension</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ExtensionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getExtension()
     * @generated
     */
    EClass EXTENSION = eINSTANCE.getExtension();

    /**
     * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENSION__EXTENSION = eINSTANCE.getExtension_Extension();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.InjectionImpl <em>Injection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.InjectionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getInjection()
     * @generated
     */
    EClass INJECTION = eINSTANCE.getInjection();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INJECTION__TYPE = eINSTANCE.getInjection_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INJECTION__NAME = eINSTANCE.getInjection_Name();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl <em>Naming Section</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingSection()
     * @generated
     */
    EClass NAMING_SECTION = eINSTANCE.getNamingSection();

    /**
     * The meta object literal for the '<em><b>Casing</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMING_SECTION__CASING = eINSTANCE.getNamingSection_Casing();

    /**
     * The meta object literal for the '<em><b>Namings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMING_SECTION__NAMINGS = eINSTANCE.getNamingSection_Namings();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingDefinitionImpl <em>Naming Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingDefinitionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingDefinition()
     * @generated
     */
    EClass NAMING_DEFINITION = eINSTANCE.getNamingDefinition();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMING_DEFINITION__TYPE = eINSTANCE.getNamingDefinition_Type();

    /**
     * The meta object literal for the '<em><b>Naming</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMING_DEFINITION__NAMING = eINSTANCE.getNamingDefinition_Naming();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl <em>Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDefinitionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeDefinition()
     * @generated
     */
    EClass SCOPE_DEFINITION = eINSTANCE.getScopeDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SCOPE_DEFINITION__NAME = eINSTANCE.getScopeDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Target Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DEFINITION__TARGET_TYPE = eINSTANCE.getScopeDefinition_TargetType();

    /**
     * The meta object literal for the '<em><b>Context Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DEFINITION__CONTEXT_TYPE = eINSTANCE.getScopeDefinition_ContextType();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DEFINITION__REFERENCE = eINSTANCE.getScopeDefinition_Reference();

    /**
     * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DEFINITION__RULES = eINSTANCE.getScopeDefinition_Rules();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl <em>Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeRuleImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeRule()
     * @generated
     */
    EClass SCOPE_RULE = eINSTANCE.getScopeRule();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_RULE__CONTEXT = eINSTANCE.getScopeRule_Context();

    /**
     * The meta object literal for the '<em><b>Exprs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_RULE__EXPRS = eINSTANCE.getScopeRule_Exprs();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl <em>Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeContextImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeContext()
     * @generated
     */
    EClass SCOPE_CONTEXT = eINSTANCE.getScopeContext();

    /**
     * The meta object literal for the '<em><b>Global</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SCOPE_CONTEXT__GLOBAL = eINSTANCE.getScopeContext_Global();

    /**
     * The meta object literal for the '<em><b>Context Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_CONTEXT__CONTEXT_TYPE = eINSTANCE.getScopeContext_ContextType();

    /**
     * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_CONTEXT__GUARD = eINSTANCE.getScopeContext_Guard();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeExpression()
     * @generated
     */
    EClass SCOPE_EXPRESSION = eINSTANCE.getScopeExpression();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.FactoryExpressionImpl <em>Factory Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.FactoryExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getFactoryExpression()
     * @generated
     */
    EClass FACTORY_EXPRESSION = eINSTANCE.getFactoryExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FACTORY_EXPRESSION__EXPR = eINSTANCE.getFactoryExpression_Expr();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl <em>Delegation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeDelegationImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getScopeDelegation()
     * @generated
     */
    EClass SCOPE_DELEGATION = eINSTANCE.getScopeDelegation();

    /**
     * The meta object literal for the '<em><b>Delegate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DELEGATION__DELEGATE = eINSTANCE.getScopeDelegation_Delegate();

    /**
     * The meta object literal for the '<em><b>External</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DELEGATION__EXTERNAL = eINSTANCE.getScopeDelegation_External();

    /**
     * The meta object literal for the '<em><b>Scope</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCOPE_DELEGATION__SCOPE = eINSTANCE.getScopeDelegation_Scope();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl <em>Named Scope Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamedScopeExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamedScopeExpression()
     * @generated
     */
    EClass NAMED_SCOPE_EXPRESSION = eINSTANCE.getNamedScopeExpression();

    /**
     * The meta object literal for the '<em><b>Case Def</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_SCOPE_EXPRESSION__CASE_DEF = eINSTANCE.getNamedScopeExpression_CaseDef();

    /**
     * The meta object literal for the '<em><b>Casing</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_SCOPE_EXPRESSION__CASING = eINSTANCE.getNamedScopeExpression_Casing();

    /**
     * The meta object literal for the '<em><b>Naming</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_SCOPE_EXPRESSION__NAMING = eINSTANCE.getNamedScopeExpression_Naming();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl <em>Global Scope Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.GlobalScopeExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getGlobalScopeExpression()
     * @generated
     */
    EClass GLOBAL_SCOPE_EXPRESSION = eINSTANCE.getGlobalScopeExpression();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_SCOPE_EXPRESSION__TYPE = eINSTANCE.getGlobalScopeExpression_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_SCOPE_EXPRESSION__NAME = eINSTANCE.getGlobalScopeExpression_Name();

    /**
     * The meta object literal for the '<em><b>Recursive Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX = eINSTANCE.getGlobalScopeExpression_RecursivePrefix();

    /**
     * The meta object literal for the '<em><b>Prefix</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_SCOPE_EXPRESSION__PREFIX = eINSTANCE.getGlobalScopeExpression_Prefix();

    /**
     * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GLOBAL_SCOPE_EXPRESSION__DATA = eINSTANCE.getGlobalScopeExpression_Data();

    /**
     * The meta object literal for the '<em><b>Domains</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_SCOPE_EXPRESSION__DOMAINS = eINSTANCE.getGlobalScopeExpression_Domains();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.DataExpressionImpl <em>Data Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.DataExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getDataExpression()
     * @generated
     */
    EClass DATA_EXPRESSION = eINSTANCE.getDataExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_EXPRESSION__VALUE = eINSTANCE.getDataExpression_Value();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.MatchDataExpressionImpl <em>Match Data Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.MatchDataExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getMatchDataExpression()
     * @generated
     */
    EClass MATCH_DATA_EXPRESSION = eINSTANCE.getMatchDataExpression();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MATCH_DATA_EXPRESSION__KEY = eINSTANCE.getMatchDataExpression_Key();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.LambdaDataExpressionImpl <em>Lambda Data Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.LambdaDataExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getLambdaDataExpression()
     * @generated
     */
    EClass LAMBDA_DATA_EXPRESSION = eINSTANCE.getLambdaDataExpression();

    /**
     * The meta object literal for the '<em><b>Desc</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LAMBDA_DATA_EXPRESSION__DESC = eINSTANCE.getLambdaDataExpression_Desc();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.SimpleScopeExpressionImpl <em>Simple Scope Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.SimpleScopeExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getSimpleScopeExpression()
     * @generated
     */
    EClass SIMPLE_SCOPE_EXPRESSION = eINSTANCE.getSimpleScopeExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMPLE_SCOPE_EXPRESSION__EXPR = eINSTANCE.getSimpleScopeExpression_Expr();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingImpl <em>Naming</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNaming()
     * @generated
     */
    EClass NAMING = eINSTANCE.getNaming();

    /**
     * The meta object literal for the '<em><b>Names</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMING__NAMES = eINSTANCE.getNaming_Names();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl <em>Naming Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getNamingExpression()
     * @generated
     */
    EClass NAMING_EXPRESSION = eINSTANCE.getNamingExpression();

    /**
     * The meta object literal for the '<em><b>Export</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMING_EXPRESSION__EXPORT = eINSTANCE.getNamingExpression_Export();

    /**
     * The meta object literal for the '<em><b>Factory</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMING_EXPRESSION__FACTORY = eINSTANCE.getNamingExpression_Factory();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMING_EXPRESSION__EXPRESSION = eINSTANCE.getNamingExpression_Expression();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.scope.scope.Casing <em>Casing</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
     * @see com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopePackageImpl#getCasing()
     * @generated
     */
    EEnum CASING = eINSTANCE.getCasing();

  }

} //ScopePackage
