/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.util;

import com.avaloq.tools.ddk.xtext.valid.valid.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage
 * @generated
 */
public class ValidAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ValidPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValidAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ValidPackage.eINSTANCE;
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
  protected ValidSwitch<Adapter> modelSwitch =
    new ValidSwitch<Adapter>()
    {
      @Override
      public Adapter caseValidModel(ValidModel object)
      {
        return createValidModelAdapter();
      }
      @Override
      public Adapter caseImport(Import object)
      {
        return createImportAdapter();
      }
      @Override
      public Adapter caseCategory(Category object)
      {
        return createCategoryAdapter();
      }
      @Override
      public Adapter caseRule(Rule object)
      {
        return createRuleAdapter();
      }
      @Override
      public Adapter casePredefinedRule(PredefinedRule object)
      {
        return createPredefinedRuleAdapter();
      }
      @Override
      public Adapter caseNativeRule(NativeRule object)
      {
        return createNativeRuleAdapter();
      }
      @Override
      public Adapter caseSizeRule(SizeRule object)
      {
        return createSizeRuleAdapter();
      }
      @Override
      public Adapter caseRangeRule(RangeRule object)
      {
        return createRangeRuleAdapter();
      }
      @Override
      public Adapter caseUniqueRule(UniqueRule object)
      {
        return createUniqueRuleAdapter();
      }
      @Override
      public Adapter caseContext(Context object)
      {
        return createContextAdapter();
      }
      @Override
      public Adapter caseSimpleContext(SimpleContext object)
      {
        return createSimpleContextAdapter();
      }
      @Override
      public Adapter caseDuplicateContext(DuplicateContext object)
      {
        return createDuplicateContextAdapter();
      }
      @Override
      public Adapter caseNativeContext(NativeContext object)
      {
        return createNativeContextAdapter();
      }
      @Override
      public Adapter caseQuickFix(QuickFix object)
      {
        return createQuickFixAdapter();
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
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidModel
   * @generated
   */
  public Adapter createValidModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Import
   * @generated
   */
  public Adapter createImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category
   * @generated
   */
  public Adapter createCategoryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule <em>Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule
   * @generated
   */
  public Adapter createRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule <em>Predefined Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule
   * @generated
   */
  public Adapter createPredefinedRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeRule <em>Native Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeRule
   * @generated
   */
  public Adapter createNativeRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.SizeRule <em>Size Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SizeRule
   * @generated
   */
  public Adapter createSizeRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule <em>Range Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.RangeRule
   * @generated
   */
  public Adapter createRangeRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule <em>Unique Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule
   * @generated
   */
  public Adapter createUniqueRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Context
   * @generated
   */
  public Adapter createContextAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext <em>Simple Context</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext
   * @generated
   */
  public Adapter createSimpleContextAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext <em>Duplicate Context</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext
   * @generated
   */
  public Adapter createDuplicateContextAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext <em>Native Context</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext
   * @generated
   */
  public Adapter createNativeContextAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix <em>Quick Fix</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix
   * @generated
   */
  public Adapter createQuickFixAdapter()
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

} //ValidAdapterFactory
