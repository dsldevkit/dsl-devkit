/**
 */
package com.avaloq.tools.ddk.xtext.export.export.util;

import com.avaloq.tools.ddk.xtext.export.export.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage
 * @generated
 */
public class ExportAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ExportPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExportAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ExportPackage.eINSTANCE;
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
  protected ExportSwitch<Adapter> modelSwitch =
    new ExportSwitch<Adapter>()
    {
      @Override
      public Adapter caseExportModel(ExportModel object)
      {
        return createExportModelAdapter();
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
      public Adapter caseDeclarationForType(DeclarationForType object)
      {
        return createDeclarationForTypeAdapter();
      }
      @Override
      public Adapter caseInterface(Interface object)
      {
        return createInterfaceAdapter();
      }
      @Override
      public Adapter caseInterfaceItem(InterfaceItem object)
      {
        return createInterfaceItemAdapter();
      }
      @Override
      public Adapter caseInterfaceField(InterfaceField object)
      {
        return createInterfaceFieldAdapter();
      }
      @Override
      public Adapter caseInterfaceNavigation(InterfaceNavigation object)
      {
        return createInterfaceNavigationAdapter();
      }
      @Override
      public Adapter caseInterfaceExpression(InterfaceExpression object)
      {
        return createInterfaceExpressionAdapter();
      }
      @Override
      public Adapter caseExport(Export object)
      {
        return createExportAdapter();
      }
      @Override
      public Adapter caseUserData(UserData object)
      {
        return createUserDataAdapter();
      }
      @Override
      public Adapter caseAttribute(Attribute object)
      {
        return createAttributeAdapter();
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
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel
   * @generated
   */
  public Adapter createExportModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.Import
   * @generated
   */
  public Adapter createImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.Extension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.Extension
   * @generated
   */
  public Adapter createExtensionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType <em>Declaration For Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.DeclarationForType
   * @generated
   */
  public Adapter createDeclarationForTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.Interface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.Interface
   * @generated
   */
  public Adapter createInterfaceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem <em>Interface Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceItem
   * @generated
   */
  public Adapter createInterfaceItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceField <em>Interface Field</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceField
   * @generated
   */
  public Adapter createInterfaceFieldAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation <em>Interface Navigation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation
   * @generated
   */
  public Adapter createInterfaceNavigationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression <em>Interface Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression
   * @generated
   */
  public Adapter createInterfaceExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.Export <em>Export</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.Export
   * @generated
   */
  public Adapter createExportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.UserData <em>User Data</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.UserData
   * @generated
   */
  public Adapter createUserDataAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.xtext.export.export.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.avaloq.tools.ddk.xtext.export.export.Attribute
   * @generated
   */
  public Adapter createAttributeAdapter()
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

} //ExportAdapterFactory
