/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.util;

import com.avaloq.tools.ddk.xtext.scope.scope.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage
 * @generated
 */
public class ScopeAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ScopePackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ScopePackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeSwitch<Adapter> modelSwitch =
    new ScopeSwitch<Adapter>()
    {
      @Override
      public Adapter caseScopeModel(ScopeModel object)
      {
        return createScopeModelAdapter();
      }
      @Override
      public Adapter caseImport(Import object)
      {
        return createImportAdapter();
      }
      @Override
      public Adapter caseExtension(Extension object)
      {
        return createExtensionAdapter();
      }
      @Override
      public Adapter caseInjection(Injection object)
      {
        return createInjectionAdapter();
      }
      @Override
      public Adapter caseNamingSection(NamingSection object)
      {
        return createNamingSectionAdapter();
      }
      @Override
      public Adapter caseNamingDefinition(NamingDefinition object)
      {
        return createNamingDefinitionAdapter();
      }
      @Override
      public Adapter caseScopeDefinition(ScopeDefinition object)
      {
        return createScopeDefinitionAdapter();
      }
      @Override
      public Adapter caseScopeRule(ScopeRule object)
      {
        return createScopeRuleAdapter();
      }
      @Override
      public Adapter caseScopeContext(ScopeContext object)
      {
        return createScopeContextAdapter();
      }
      @Override
      public Adapter caseScopeExpression(ScopeExpression object)
      {
        return createScopeExpressionAdapter();
      }
      @Override
      public Adapter caseFactoryExpression(FactoryExpression object)
      {
        return createFactoryExpressionAdapter();
      }
      @Override
      public Adapter caseScopeDelegation(ScopeDelegation object)
      {
        return createScopeDelegationAdapter();
      }
      @Override
      public Adapter caseNamedScopeExpression(NamedScopeExpression object)
      {
        return createNamedScopeExpressionAdapter();
      }
      @Override
      public Adapter caseGlobalScopeExpression(GlobalScopeExpression object)
      {
        return createGlobalScopeExpressionAdapter();
      }
      @Override
      public Adapter caseDataExpression(DataExpression object)
      {
        return createDataExpressionAdapter();
      }
      @Override
      public Adapter caseMatchDataExpression(MatchDataExpression object)
      {
        return createMatchDataExpressionAdapter();
      }
      @Override
      public Adapter caseLambdaDataExpression(LambdaDataExpression object)
      {
        return createLambdaDataExpressionAdapter();
      }
      @Override
      public Adapter caseSimpleScopeExpression(SimpleScopeExpression object)
      {
        return createSimpleScopeExpressionAdapter();
      }
      @Override
      public Adapter caseNaming(Naming object)
      {
        return createNamingAdapter();
      }
      @Override
      public Adapter caseNamingExpression(NamingExpression object)
      {
        return createNamingExpressionAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
   * @generated
   */
  public Adapter createScopeModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Import
   * @generated
   */
  public Adapter createImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Extension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Extension
   * @generated
   */
  public Adapter createExtensionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection <em>Injection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Injection
   * @generated
   */
  public Adapter createInjectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection <em>Naming Section</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingSection
   * @generated
   */
  public Adapter createNamingSectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition <em>Naming Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition
   * @generated
   */
  public Adapter createNamingDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition
   * @generated
   */
  public Adapter createScopeDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule <em>Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule
   * @generated
   */
  public Adapter createScopeRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext
   * @generated
   */
  public Adapter createScopeContextAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression
   * @generated
   */
  public Adapter createScopeExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression <em>Factory Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression
   * @generated
   */
  public Adapter createFactoryExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation <em>Delegation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation
   * @generated
   */
  public Adapter createScopeDelegationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression <em>Named Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression
   * @generated
   */
  public Adapter createNamedScopeExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression <em>Global Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression
   * @generated
   */
  public Adapter createGlobalScopeExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.DataExpression <em>Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.DataExpression
   * @generated
   */
  public Adapter createDataExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression <em>Match Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression
   * @generated
   */
  public Adapter createMatchDataExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression <em>Lambda Data Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression
   * @generated
   */
  public Adapter createLambdaDataExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression <em>Simple Scope Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression
   * @generated
   */
  public Adapter createSimpleScopeExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.Naming <em>Naming</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Naming
   * @generated
   */
  public Adapter createNamingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression <em>Naming Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression
   * @generated
   */
  public Adapter createNamingExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ScopeAdapterFactory
