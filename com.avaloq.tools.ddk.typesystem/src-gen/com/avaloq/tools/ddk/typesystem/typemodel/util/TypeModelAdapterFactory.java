/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel.util;

import com.avaloq.tools.ddk.typesystem.typemodel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage
 * @generated
 */
public class TypeModelAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypeModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeModelAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = TypeModelPackage.eINSTANCE;
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
	protected TypeModelSwitch<Adapter> modelSwitch =
		new TypeModelSwitch<Adapter>()
		{
			@Override
			public Adapter caseIExpression(IExpression object)
			{
				return createIExpressionAdapter();
			}
			@Override
			public Adapter caseIType(IType object)
			{
				return createITypeAdapter();
			}
			@Override
			public Adapter caseINamedElement(INamedElement object)
			{
				return createINamedElementAdapter();
			}
			@Override
			public Adapter caseOverrideDeclaration(OverrideDeclaration object)
			{
				return createOverrideDeclarationAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object)
			{
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseICaseSensitiveNamedElement(ICaseSensitiveNamedElement object)
			{
				return createICaseSensitiveNamedElementAdapter();
			}
			@Override
			public Adapter caseINamedType(INamedType object)
			{
				return createINamedTypeAdapter();
			}
			@Override
			public Adapter caseNamedType(NamedType object)
			{
				return createNamedTypeAdapter();
			}
			@Override
			public Adapter caseIFormalParameter(IFormalParameter object)
			{
				return createIFormalParameterAdapter();
			}
			@Override
			public Adapter caseIActualParameter(IActualParameter object)
			{
				return createIActualParameterAdapter();
			}
			@Override
			public Adapter caseINamedActualParameter(INamedActualParameter object)
			{
				return createINamedActualParameterAdapter();
			}
			@Override
			public Adapter caseISubprogram(ISubprogram object)
			{
				return createISubprogramAdapter();
			}
			@Override
			public Adapter caseIProcedure(IProcedure object)
			{
				return createIProcedureAdapter();
			}
			@Override
			public Adapter caseIFunction(IFunction object)
			{
				return createIFunctionAdapter();
			}
			@Override
			public Adapter caseIFormalParameterList(Iterable<? extends IFormalParameter> object)
			{
				return createIFormalParameterListAdapter();
			}
			@Override
			public Adapter caseNamedFormalParameter(NamedFormalParameter object)
			{
				return createNamedFormalParameterAdapter();
			}
			@Override
			public Adapter caseINamedFormalParameter(INamedFormalParameter object)
			{
				return createINamedFormalParameterAdapter();
			}
			@Override
			public Adapter caseICallable(ICallable object)
			{
				return createICallableAdapter();
			}
			@Override
			public Adapter caseCallable(Callable object)
			{
				return createCallableAdapter();
			}
			@Override
			public Adapter caseIDeclaration(IDeclaration object)
			{
				return createIDeclarationAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IExpression <em>IExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IExpression
	 * @generated
	 */
	public Adapter createIExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IType
	 * @generated
	 */
	public Adapter createITypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedElement <em>INamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedElement
	 * @generated
	 */
	public Adapter createINamedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.OverrideDeclaration <em>Override Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.OverrideDeclaration
	 * @generated
	 */
	public Adapter createOverrideDeclarationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.ICaseSensitiveNamedElement <em>ICase Sensitive Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ICaseSensitiveNamedElement
	 * @generated
	 */
	public Adapter createICaseSensitiveNamedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedType <em>INamed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedType
	 * @generated
	 */
	public Adapter createINamedTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedType <em>Named Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedType
	 * @generated
	 */
	public Adapter createNamedTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter <em>IFormal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter
	 * @generated
	 */
	public Adapter createIFormalParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter <em>IActual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter
	 * @generated
	 */
	public Adapter createIActualParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter <em>INamed Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter
	 * @generated
	 */
	public Adapter createINamedActualParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram <em>ISubprogram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram
	 * @generated
	 */
	public Adapter createISubprogramAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IProcedure <em>IProcedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IProcedure
	 * @generated
	 */
	public Adapter createIProcedureAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFunction <em>IFunction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFunction
	 * @generated
	 */
	public Adapter createIFunctionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Iterable <em>IFormal Parameter List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Iterable
	 * @generated
	 */
	public Adapter createIFormalParameterListAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedFormalParameter <em>Named Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedFormalParameter
	 * @generated
	 */
	public Adapter createNamedFormalParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter <em>INamed Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter
	 * @generated
	 */
	public Adapter createINamedFormalParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.ICallable <em>ICallable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ICallable
	 * @generated
	 */
	public Adapter createICallableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.Callable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.Callable
	 * @generated
	 */
	public Adapter createCallableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IDeclaration <em>IDeclaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IDeclaration
	 * @generated
	 */
	public Adapter createIDeclarationAdapter()
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

} //TypeModelAdapterFactory
