/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScopeFactoryImpl extends EFactoryImpl implements ScopeFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ScopeFactory init()
  {
    try
    {
      ScopeFactory theScopeFactory = (ScopeFactory)EPackage.Registry.INSTANCE.getEFactory(ScopePackage.eNS_URI);
      if (theScopeFactory != null)
      {
        return theScopeFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ScopeFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ScopePackage.SCOPE_MODEL: return createScopeModel();
      case ScopePackage.IMPORT: return createImport();
      case ScopePackage.EXTENSION: return createExtension();
      case ScopePackage.INJECTION: return createInjection();
      case ScopePackage.NAMING_SECTION: return createNamingSection();
      case ScopePackage.NAMING_DEFINITION: return createNamingDefinition();
      case ScopePackage.SCOPE_DEFINITION: return createScopeDefinition();
      case ScopePackage.SCOPE_RULE: return createScopeRule();
      case ScopePackage.SCOPE_CONTEXT: return createScopeContext();
      case ScopePackage.SCOPE_EXPRESSION: return createScopeExpression();
      case ScopePackage.FACTORY_EXPRESSION: return createFactoryExpression();
      case ScopePackage.SCOPE_DELEGATION: return createScopeDelegation();
      case ScopePackage.NAMED_SCOPE_EXPRESSION: return createNamedScopeExpression();
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION: return createGlobalScopeExpression();
      case ScopePackage.DATA_EXPRESSION: return createDataExpression();
      case ScopePackage.MATCH_DATA_EXPRESSION: return createMatchDataExpression();
      case ScopePackage.LAMBDA_DATA_EXPRESSION: return createLambdaDataExpression();
      case ScopePackage.SIMPLE_SCOPE_EXPRESSION: return createSimpleScopeExpression();
      case ScopePackage.NAMING: return createNaming();
      case ScopePackage.NAMING_EXPRESSION: return createNamingExpression();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ScopePackage.CASING:
        return createCasingFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ScopePackage.CASING:
        return convertCasingToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeModel createScopeModel()
  {
    ScopeModelImpl scopeModel = new ScopeModelImpl();
    return scopeModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Extension createExtension()
  {
    ExtensionImpl extension = new ExtensionImpl();
    return extension;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Injection createInjection()
  {
    InjectionImpl injection = new InjectionImpl();
    return injection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NamingSection createNamingSection()
  {
    NamingSectionImpl namingSection = new NamingSectionImpl();
    return namingSection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NamingDefinition createNamingDefinition()
  {
    NamingDefinitionImpl namingDefinition = new NamingDefinitionImpl();
    return namingDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeDefinition createScopeDefinition()
  {
    ScopeDefinitionImpl scopeDefinition = new ScopeDefinitionImpl();
    return scopeDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeRule createScopeRule()
  {
    ScopeRuleImpl scopeRule = new ScopeRuleImpl();
    return scopeRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeContext createScopeContext()
  {
    ScopeContextImpl scopeContext = new ScopeContextImpl();
    return scopeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeExpression createScopeExpression()
  {
    ScopeExpressionImpl scopeExpression = new ScopeExpressionImpl();
    return scopeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public FactoryExpression createFactoryExpression()
  {
    FactoryExpressionImpl factoryExpression = new FactoryExpressionImpl();
    return factoryExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopeDelegation createScopeDelegation()
  {
    ScopeDelegationImpl scopeDelegation = new ScopeDelegationImpl();
    return scopeDelegation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NamedScopeExpression createNamedScopeExpression()
  {
    NamedScopeExpressionImpl namedScopeExpression = new NamedScopeExpressionImpl();
    return namedScopeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public GlobalScopeExpression createGlobalScopeExpression()
  {
    GlobalScopeExpressionImpl globalScopeExpression = new GlobalScopeExpressionImpl();
    return globalScopeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DataExpression createDataExpression()
  {
    DataExpressionImpl dataExpression = new DataExpressionImpl();
    return dataExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public MatchDataExpression createMatchDataExpression()
  {
    MatchDataExpressionImpl matchDataExpression = new MatchDataExpressionImpl();
    return matchDataExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public LambdaDataExpression createLambdaDataExpression()
  {
    LambdaDataExpressionImpl lambdaDataExpression = new LambdaDataExpressionImpl();
    return lambdaDataExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SimpleScopeExpression createSimpleScopeExpression()
  {
    SimpleScopeExpressionImpl simpleScopeExpression = new SimpleScopeExpressionImpl();
    return simpleScopeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Naming createNaming()
  {
    NamingImpl naming = new NamingImpl();
    return naming;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NamingExpression createNamingExpression()
  {
    NamingExpressionImpl namingExpression = new NamingExpressionImpl();
    return namingExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Casing createCasingFromString(EDataType eDataType, String initialValue)
  {
    Casing result = Casing.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertCasingToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ScopePackage getScopePackage()
  {
    return (ScopePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ScopePackage getPackage()
  {
    return ScopePackage.eINSTANCE;
  }

} //ScopeFactoryImpl
