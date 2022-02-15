/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.util;

import com.avaloq.tools.ddk.checkcfg.checkcfg.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
 * @generated
 */
public class CheckcfgAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CheckcfgPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CheckcfgAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = CheckcfgPackage.eINSTANCE;
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
  protected CheckcfgSwitch<Adapter> modelSwitch =
    new CheckcfgSwitch<Adapter>()
    {
      @Override
      public Adapter caseCheckConfiguration(CheckConfiguration object)
      {
        return createCheckConfigurationAdapter();
      }
      @Override
      public Adapter caseConfiguredLanguageValidator(ConfiguredLanguageValidator object)
      {
        return createConfiguredLanguageValidatorAdapter();
      }
      @Override
      public Adapter caseConfiguredCatalog(ConfiguredCatalog object)
      {
        return createConfiguredCatalogAdapter();
      }
      @Override
      public Adapter caseConfiguredCheck(ConfiguredCheck object)
      {
        return createConfiguredCheckAdapter();
      }
      @Override
      public Adapter caseConfiguredParameter(ConfiguredParameter object)
      {
        return createConfiguredParameterAdapter();
      }
      @Override
      public Adapter caseConfigurableSection(ConfigurableSection object)
      {
        return createConfigurableSectionAdapter();
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
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration <em>Check Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
   * @generated
   */
  public Adapter createCheckConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator <em>Configured Language Validator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator
   * @generated
   */
  public Adapter createConfiguredLanguageValidatorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog <em>Configured Catalog</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog
   * @generated
   */
  public Adapter createConfiguredCatalogAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck <em>Configured Check</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck
   * @generated
   */
  public Adapter createConfiguredCheckAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter <em>Configured Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter
   * @generated
   */
  public Adapter createConfiguredParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection <em>Configurable Section</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection
   * @generated
   */
  public Adapter createConfigurableSectionAdapter()
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

} //CheckcfgAdapterFactory
