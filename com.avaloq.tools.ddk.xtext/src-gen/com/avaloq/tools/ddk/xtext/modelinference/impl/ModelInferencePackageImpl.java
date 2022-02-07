/**
 */
package com.avaloq.tools.ddk.xtext.modelinference.impl;

import com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer;
import com.avaloq.tools.ddk.xtext.modelinference.ModelInferenceFactory;
import com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelInferencePackageImpl extends EPackageImpl implements ModelInferencePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inferenceContainerEClass = null;

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
   * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ModelInferencePackageImpl()
  {
    super(eNS_URI, ModelInferenceFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ModelInferencePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ModelInferencePackage init()
  {
    if (isInited) return (ModelInferencePackage)EPackage.Registry.INSTANCE.getEPackage(ModelInferencePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredModelInferencePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    ModelInferencePackageImpl theModelInferencePackage = registeredModelInferencePackage instanceof ModelInferencePackageImpl ? (ModelInferencePackageImpl)registeredModelInferencePackage : new ModelInferencePackageImpl();

    isInited = true;

    // Create package meta-data objects
    theModelInferencePackage.createPackageContents();

    // Initialize created meta-data
    theModelInferencePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModelInferencePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModelInferencePackage.eNS_URI, theModelInferencePackage);
    return theModelInferencePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getInferenceContainer()
  {
    return inferenceContainerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getInferenceContainer_Contents()
  {
    return (EReference)inferenceContainerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getInferenceContainer_Fragments()
  {
    return (EAttribute)inferenceContainerEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EOperation getInferenceContainer__GetFragmentSegment__EObject()
  {
    return inferenceContainerEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EOperation getInferenceContainer__GetEObject__String()
  {
    return inferenceContainerEClass.getEOperations().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelInferenceFactory getModelInferenceFactory()
  {
    return (ModelInferenceFactory)getEFactoryInstance();
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
    inferenceContainerEClass = createEClass(INFERENCE_CONTAINER);
    createEReference(inferenceContainerEClass, INFERENCE_CONTAINER__CONTENTS);
    createEAttribute(inferenceContainerEClass, INFERENCE_CONTAINER__FRAGMENTS);
    createEOperation(inferenceContainerEClass, INFERENCE_CONTAINER___GET_FRAGMENT_SEGMENT__EOBJECT);
    createEOperation(inferenceContainerEClass, INFERENCE_CONTAINER___GET_EOBJECT__STRING);
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

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes, features, and operations; add parameters
    initEClass(inferenceContainerEClass, InferenceContainer.class, "InferenceContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInferenceContainer_Contents(), ecorePackage.getEObject(), null, "contents", null, 0, -1, InferenceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInferenceContainer_Fragments(), ecorePackage.getEString(), "fragments", null, 0, -1, InferenceContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    EOperation op = initEOperation(getInferenceContainer__GetFragmentSegment__EObject(), ecorePackage.getEString(), "getFragmentSegment", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEObject(), "object", 0, 1, IS_UNIQUE, IS_ORDERED);

    op = initEOperation(getInferenceContainer__GetEObject__String(), ecorePackage.getEObject(), "getEObject", 0, 1, IS_UNIQUE, IS_ORDERED);
    addEParameter(op, ecorePackage.getEString(), "fragmentSegment", 0, 1, IS_UNIQUE, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ModelInferencePackageImpl
