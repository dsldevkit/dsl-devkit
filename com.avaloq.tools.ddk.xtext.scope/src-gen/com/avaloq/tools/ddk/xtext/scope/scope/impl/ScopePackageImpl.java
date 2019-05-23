/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage;

import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.DataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.Extension;
import com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.Naming;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeFactory;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScopePackageImpl extends EPackageImpl implements ScopePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extensionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass injectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namingSectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namingDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeContextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass factoryExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopeDelegationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedScopeExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass globalScopeExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass matchDataExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lambdaDataExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleScopeExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namingExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum casingEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ScopePackageImpl()
  {
    super(eNS_URI, ScopeFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link ScopePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ScopePackage init()
  {
    if (isInited) return (ScopePackage)EPackage.Registry.INSTANCE.getEPackage(ScopePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredScopePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    ScopePackageImpl theScopePackage = registeredScopePackage instanceof ScopePackageImpl ? (ScopePackageImpl)registeredScopePackage : new ScopePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    ExpressionPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theScopePackage.createPackageContents();

    // Initialize created meta-data
    theScopePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theScopePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ScopePackage.eNS_URI, theScopePackage);
    return theScopePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeModel()
  {
    return scopeModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getScopeModel_Name()
  {
    return (EAttribute)scopeModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_IncludedScopes()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_Imports()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_Extensions()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_Injections()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_Naming()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeModel_Scopes()
  {
    return (EReference)scopeModelEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImport()
  {
    return importEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImport_Package()
  {
    return (EReference)importEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImport_Name()
  {
    return (EAttribute)importEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtension()
  {
    return extensionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtension_Extension()
  {
    return (EAttribute)extensionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInjection()
  {
    return injectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInjection_Type()
  {
    return (EAttribute)injectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInjection_Name()
  {
    return (EAttribute)injectionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamingSection()
  {
    return namingSectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamingSection_Casing()
  {
    return (EAttribute)namingSectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamingSection_Namings()
  {
    return (EReference)namingSectionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamingDefinition()
  {
    return namingDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamingDefinition_Type()
  {
    return (EReference)namingDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamingDefinition_Naming()
  {
    return (EReference)namingDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeDefinition()
  {
    return scopeDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getScopeDefinition_Name()
  {
    return (EAttribute)scopeDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDefinition_TargetType()
  {
    return (EReference)scopeDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDefinition_ContextType()
  {
    return (EReference)scopeDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDefinition_Reference()
  {
    return (EReference)scopeDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDefinition_Rules()
  {
    return (EReference)scopeDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeRule()
  {
    return scopeRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeRule_Context()
  {
    return (EReference)scopeRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeRule_Exprs()
  {
    return (EReference)scopeRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeContext()
  {
    return scopeContextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getScopeContext_Global()
  {
    return (EAttribute)scopeContextEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeContext_ContextType()
  {
    return (EReference)scopeContextEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeContext_Guard()
  {
    return (EReference)scopeContextEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeExpression()
  {
    return scopeExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFactoryExpression()
  {
    return factoryExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFactoryExpression_Expr()
  {
    return (EReference)factoryExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getScopeDelegation()
  {
    return scopeDelegationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDelegation_Delegate()
  {
    return (EReference)scopeDelegationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDelegation_External()
  {
    return (EReference)scopeDelegationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getScopeDelegation_Scope()
  {
    return (EReference)scopeDelegationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamedScopeExpression()
  {
    return namedScopeExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedScopeExpression_CaseDef()
  {
    return (EAttribute)namedScopeExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedScopeExpression_Casing()
  {
    return (EAttribute)namedScopeExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedScopeExpression_Naming()
  {
    return (EReference)namedScopeExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGlobalScopeExpression()
  {
    return globalScopeExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalScopeExpression_Type()
  {
    return (EReference)globalScopeExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalScopeExpression_Name()
  {
    return (EReference)globalScopeExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGlobalScopeExpression_RecursivePrefix()
  {
    return (EAttribute)globalScopeExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalScopeExpression_Prefix()
  {
    return (EReference)globalScopeExpressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGlobalScopeExpression_Data()
  {
    return (EReference)globalScopeExpressionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGlobalScopeExpression_Domains()
  {
    return (EAttribute)globalScopeExpressionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataExpression()
  {
    return dataExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataExpression_Value()
  {
    return (EReference)dataExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMatchDataExpression()
  {
    return matchDataExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMatchDataExpression_Key()
  {
    return (EAttribute)matchDataExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLambdaDataExpression()
  {
    return lambdaDataExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLambdaDataExpression_Desc()
  {
    return (EAttribute)lambdaDataExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimpleScopeExpression()
  {
    return simpleScopeExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSimpleScopeExpression_Expr()
  {
    return (EReference)simpleScopeExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNaming()
  {
    return namingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNaming_Names()
  {
    return (EReference)namingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamingExpression()
  {
    return namingExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamingExpression_Export()
  {
    return (EAttribute)namingExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamingExpression_Factory()
  {
    return (EAttribute)namingExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamingExpression_Expression()
  {
    return (EReference)namingExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getCasing()
  {
    return casingEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeFactory getScopeFactory()
  {
    return (ScopeFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    scopeModelEClass = createEClass(SCOPE_MODEL);
    createEAttribute(scopeModelEClass, SCOPE_MODEL__NAME);
    createEReference(scopeModelEClass, SCOPE_MODEL__INCLUDED_SCOPES);
    createEReference(scopeModelEClass, SCOPE_MODEL__IMPORTS);
    createEReference(scopeModelEClass, SCOPE_MODEL__EXTENSIONS);
    createEReference(scopeModelEClass, SCOPE_MODEL__INJECTIONS);
    createEReference(scopeModelEClass, SCOPE_MODEL__NAMING);
    createEReference(scopeModelEClass, SCOPE_MODEL__SCOPES);

    importEClass = createEClass(IMPORT);
    createEReference(importEClass, IMPORT__PACKAGE);
    createEAttribute(importEClass, IMPORT__NAME);

    extensionEClass = createEClass(EXTENSION);
    createEAttribute(extensionEClass, EXTENSION__EXTENSION);

    injectionEClass = createEClass(INJECTION);
    createEAttribute(injectionEClass, INJECTION__TYPE);
    createEAttribute(injectionEClass, INJECTION__NAME);

    namingSectionEClass = createEClass(NAMING_SECTION);
    createEAttribute(namingSectionEClass, NAMING_SECTION__CASING);
    createEReference(namingSectionEClass, NAMING_SECTION__NAMINGS);

    namingDefinitionEClass = createEClass(NAMING_DEFINITION);
    createEReference(namingDefinitionEClass, NAMING_DEFINITION__TYPE);
    createEReference(namingDefinitionEClass, NAMING_DEFINITION__NAMING);

    scopeDefinitionEClass = createEClass(SCOPE_DEFINITION);
    createEAttribute(scopeDefinitionEClass, SCOPE_DEFINITION__NAME);
    createEReference(scopeDefinitionEClass, SCOPE_DEFINITION__TARGET_TYPE);
    createEReference(scopeDefinitionEClass, SCOPE_DEFINITION__CONTEXT_TYPE);
    createEReference(scopeDefinitionEClass, SCOPE_DEFINITION__REFERENCE);
    createEReference(scopeDefinitionEClass, SCOPE_DEFINITION__RULES);

    scopeRuleEClass = createEClass(SCOPE_RULE);
    createEReference(scopeRuleEClass, SCOPE_RULE__CONTEXT);
    createEReference(scopeRuleEClass, SCOPE_RULE__EXPRS);

    scopeContextEClass = createEClass(SCOPE_CONTEXT);
    createEAttribute(scopeContextEClass, SCOPE_CONTEXT__GLOBAL);
    createEReference(scopeContextEClass, SCOPE_CONTEXT__CONTEXT_TYPE);
    createEReference(scopeContextEClass, SCOPE_CONTEXT__GUARD);

    scopeExpressionEClass = createEClass(SCOPE_EXPRESSION);

    factoryExpressionEClass = createEClass(FACTORY_EXPRESSION);
    createEReference(factoryExpressionEClass, FACTORY_EXPRESSION__EXPR);

    scopeDelegationEClass = createEClass(SCOPE_DELEGATION);
    createEReference(scopeDelegationEClass, SCOPE_DELEGATION__DELEGATE);
    createEReference(scopeDelegationEClass, SCOPE_DELEGATION__EXTERNAL);
    createEReference(scopeDelegationEClass, SCOPE_DELEGATION__SCOPE);

    namedScopeExpressionEClass = createEClass(NAMED_SCOPE_EXPRESSION);
    createEAttribute(namedScopeExpressionEClass, NAMED_SCOPE_EXPRESSION__CASE_DEF);
    createEAttribute(namedScopeExpressionEClass, NAMED_SCOPE_EXPRESSION__CASING);
    createEReference(namedScopeExpressionEClass, NAMED_SCOPE_EXPRESSION__NAMING);

    globalScopeExpressionEClass = createEClass(GLOBAL_SCOPE_EXPRESSION);
    createEReference(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__TYPE);
    createEReference(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__NAME);
    createEAttribute(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__RECURSIVE_PREFIX);
    createEReference(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__PREFIX);
    createEReference(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__DATA);
    createEAttribute(globalScopeExpressionEClass, GLOBAL_SCOPE_EXPRESSION__DOMAINS);

    dataExpressionEClass = createEClass(DATA_EXPRESSION);
    createEReference(dataExpressionEClass, DATA_EXPRESSION__VALUE);

    matchDataExpressionEClass = createEClass(MATCH_DATA_EXPRESSION);
    createEAttribute(matchDataExpressionEClass, MATCH_DATA_EXPRESSION__KEY);

    lambdaDataExpressionEClass = createEClass(LAMBDA_DATA_EXPRESSION);
    createEAttribute(lambdaDataExpressionEClass, LAMBDA_DATA_EXPRESSION__DESC);

    simpleScopeExpressionEClass = createEClass(SIMPLE_SCOPE_EXPRESSION);
    createEReference(simpleScopeExpressionEClass, SIMPLE_SCOPE_EXPRESSION__EXPR);

    namingEClass = createEClass(NAMING);
    createEReference(namingEClass, NAMING__NAMES);

    namingExpressionEClass = createEClass(NAMING_EXPRESSION);
    createEAttribute(namingExpressionEClass, NAMING_EXPRESSION__EXPORT);
    createEAttribute(namingExpressionEClass, NAMING_EXPRESSION__FACTORY);
    createEReference(namingExpressionEClass, NAMING_EXPRESSION__EXPRESSION);

    // Create enums
    casingEEnum = createEEnum(CASING);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
    ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    factoryExpressionEClass.getESuperTypes().add(this.getScopeExpression());
    scopeDelegationEClass.getESuperTypes().add(this.getScopeExpression());
    namedScopeExpressionEClass.getESuperTypes().add(this.getScopeExpression());
    globalScopeExpressionEClass.getESuperTypes().add(this.getNamedScopeExpression());
    matchDataExpressionEClass.getESuperTypes().add(this.getDataExpression());
    lambdaDataExpressionEClass.getESuperTypes().add(this.getDataExpression());
    simpleScopeExpressionEClass.getESuperTypes().add(this.getNamedScopeExpression());

    // Initialize classes and features; add operations and parameters
    initEClass(scopeModelEClass, ScopeModel.class, "ScopeModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getScopeModel_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_IncludedScopes(), this.getScopeModel(), null, "includedScopes", null, 0, -1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_Imports(), this.getImport(), null, "imports", null, 0, -1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_Extensions(), this.getExtension(), null, "extensions", null, 0, -1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_Injections(), this.getInjection(), null, "injections", null, 0, -1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_Naming(), this.getNamingSection(), null, "naming", null, 0, 1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeModel_Scopes(), this.getScopeDefinition(), null, "scopes", null, 0, -1, ScopeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImport_Package(), theEcorePackage.getEPackage(), null, "package", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImport_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extensionEClass, Extension.class, "Extension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExtension_Extension(), theEcorePackage.getEString(), "extension", null, 0, 1, Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(injectionEClass, Injection.class, "Injection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInjection_Type(), theEcorePackage.getEString(), "type", null, 0, 1, Injection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInjection_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Injection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namingSectionEClass, NamingSection.class, "NamingSection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamingSection_Casing(), this.getCasing(), "casing", null, 0, 1, NamingSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamingSection_Namings(), this.getNamingDefinition(), null, "namings", null, 0, -1, NamingSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namingDefinitionEClass, NamingDefinition.class, "NamingDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNamingDefinition_Type(), theEcorePackage.getEClass(), null, "type", null, 0, 1, NamingDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamingDefinition_Naming(), this.getNaming(), null, "naming", null, 0, 1, NamingDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(scopeDefinitionEClass, ScopeDefinition.class, "ScopeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getScopeDefinition_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ScopeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDefinition_TargetType(), theEcorePackage.getEClass(), null, "targetType", null, 0, 1, ScopeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDefinition_ContextType(), theEcorePackage.getEClass(), null, "contextType", null, 0, 1, ScopeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDefinition_Reference(), theEcorePackage.getEReference(), null, "reference", null, 0, 1, ScopeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDefinition_Rules(), this.getScopeRule(), null, "rules", null, 0, -1, ScopeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(scopeRuleEClass, ScopeRule.class, "ScopeRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getScopeRule_Context(), this.getScopeContext(), null, "context", null, 0, 1, ScopeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeRule_Exprs(), this.getScopeExpression(), null, "exprs", null, 0, -1, ScopeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(scopeContextEClass, ScopeContext.class, "ScopeContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getScopeContext_Global(), theEcorePackage.getEBoolean(), "global", null, 0, 1, ScopeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeContext_ContextType(), theEcorePackage.getEClass(), null, "contextType", null, 0, 1, ScopeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeContext_Guard(), theExpressionPackage.getExpression(), null, "guard", null, 0, 1, ScopeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(scopeExpressionEClass, ScopeExpression.class, "ScopeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(factoryExpressionEClass, FactoryExpression.class, "FactoryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFactoryExpression_Expr(), theExpressionPackage.getExpression(), null, "expr", null, 0, 1, FactoryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(scopeDelegationEClass, ScopeDelegation.class, "ScopeDelegation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getScopeDelegation_Delegate(), theExpressionPackage.getExpression(), null, "delegate", null, 0, 1, ScopeDelegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDelegation_External(), this.getGlobalScopeExpression(), null, "external", null, 0, 1, ScopeDelegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getScopeDelegation_Scope(), this.getScopeDefinition(), null, "scope", null, 0, 1, ScopeDelegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedScopeExpressionEClass, NamedScopeExpression.class, "NamedScopeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamedScopeExpression_CaseDef(), theEcorePackage.getEBoolean(), "caseDef", null, 0, 1, NamedScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamedScopeExpression_Casing(), this.getCasing(), "casing", null, 0, 1, NamedScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedScopeExpression_Naming(), this.getNaming(), null, "naming", null, 0, 1, NamedScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(globalScopeExpressionEClass, GlobalScopeExpression.class, "GlobalScopeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGlobalScopeExpression_Type(), theEcorePackage.getEClass(), null, "type", null, 0, 1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalScopeExpression_Name(), theExpressionPackage.getExpression(), null, "name", null, 0, 1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGlobalScopeExpression_RecursivePrefix(), theEcorePackage.getEBoolean(), "recursivePrefix", null, 0, 1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalScopeExpression_Prefix(), theExpressionPackage.getExpression(), null, "prefix", null, 0, 1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGlobalScopeExpression_Data(), this.getDataExpression(), null, "data", null, 0, -1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGlobalScopeExpression_Domains(), theEcorePackage.getEString(), "domains", null, 0, -1, GlobalScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataExpressionEClass, DataExpression.class, "DataExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDataExpression_Value(), theExpressionPackage.getExpression(), null, "value", null, 0, 1, DataExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(matchDataExpressionEClass, MatchDataExpression.class, "MatchDataExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMatchDataExpression_Key(), theEcorePackage.getEString(), "key", null, 0, 1, MatchDataExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(lambdaDataExpressionEClass, LambdaDataExpression.class, "LambdaDataExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLambdaDataExpression_Desc(), theEcorePackage.getEString(), "desc", null, 0, 1, LambdaDataExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simpleScopeExpressionEClass, SimpleScopeExpression.class, "SimpleScopeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSimpleScopeExpression_Expr(), theExpressionPackage.getExpression(), null, "expr", null, 0, 1, SimpleScopeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namingEClass, Naming.class, "Naming", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNaming_Names(), this.getNamingExpression(), null, "names", null, 0, -1, Naming.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namingExpressionEClass, NamingExpression.class, "NamingExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamingExpression_Export(), theEcorePackage.getEBoolean(), "export", null, 0, 1, NamingExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNamingExpression_Factory(), theEcorePackage.getEBoolean(), "factory", null, 0, 1, NamingExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamingExpression_Expression(), theExpressionPackage.getExpression(), null, "expression", null, 0, 1, NamingExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(casingEEnum, Casing.class, "Casing");
    addEEnumLiteral(casingEEnum, Casing.SENSITIVE);
    addEEnumLiteral(casingEEnum, Casing.INSENSITIVE);

    // Create resource
    createResource(eNS_URI);
  }

} //ScopePackageImpl
