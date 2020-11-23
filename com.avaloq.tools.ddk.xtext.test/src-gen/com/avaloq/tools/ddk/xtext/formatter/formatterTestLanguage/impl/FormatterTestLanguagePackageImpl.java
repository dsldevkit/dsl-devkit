/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Assign;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Datatypes;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Decl;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enum1;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enumeration;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguageFactory;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FqnObj;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FqnRef;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Meth;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Param;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Root;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Space;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHidden;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSub;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSubID;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSubSub;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestColumn;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestLinewrap;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestLinewrapMinMax;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestOffset;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestRightPadding;

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
public class FormatterTestLanguagePackageImpl extends EPackageImpl implements FormatterTestLanguagePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lineEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assignEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass methEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass paramEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass spaceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testLinewrapEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testLinewrapMinMaxEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testIndentationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testColumnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testOffsetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass testRightPaddingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fqnObjEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fqnRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumerationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suppressedHiddenEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suppressedHiddenSubEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suppressedHiddenSubSubEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass suppressedHiddenSubIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass datatypesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum enum1EEnum = null;

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
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private FormatterTestLanguagePackageImpl()
  {
    super(eNS_URI, FormatterTestLanguageFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link FormatterTestLanguagePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static FormatterTestLanguagePackage init()
  {
    if (isInited) return (FormatterTestLanguagePackage)EPackage.Registry.INSTANCE.getEPackage(FormatterTestLanguagePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredFormatterTestLanguagePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    FormatterTestLanguagePackageImpl theFormatterTestLanguagePackage = registeredFormatterTestLanguagePackage instanceof FormatterTestLanguagePackageImpl ? (FormatterTestLanguagePackageImpl)registeredFormatterTestLanguagePackage : new FormatterTestLanguagePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theFormatterTestLanguagePackage.createPackageContents();

    // Initialize created meta-data
    theFormatterTestLanguagePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theFormatterTestLanguagePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(FormatterTestLanguagePackage.eNS_URI, theFormatterTestLanguagePackage);
    return theFormatterTestLanguagePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRoot()
  {
    return rootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLine()
  {
    return lineEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDecl()
  {
    return declEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDecl_Type()
  {
    return (EAttribute)declEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDecl_Name()
  {
    return (EAttribute)declEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAssign()
  {
    return assignEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAssign_Var()
  {
    return (EAttribute)assignEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAssign_Op()
  {
    return (EAttribute)assignEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAssign_Val()
  {
    return (EAttribute)assignEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getMeth()
  {
    return methEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getMeth_Name()
  {
    return (EAttribute)methEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getMeth_Param()
  {
    return (EReference)methEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getParam()
  {
    return paramEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getParam_Name()
  {
    return (EAttribute)paramEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getParam_Type()
  {
    return (EAttribute)paramEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSpace()
  {
    return spaceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getSpace_Val()
  {
    return (EAttribute)spaceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestLinewrap()
  {
    return testLinewrapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTestLinewrap_Items()
  {
    return (EReference)testLinewrapEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestLinewrapMinMax()
  {
    return testLinewrapMinMaxEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTestLinewrapMinMax_Items()
  {
    return (EReference)testLinewrapMinMaxEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestIndentation()
  {
    return testIndentationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTestIndentation_Sub()
  {
    return (EReference)testIndentationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTestIndentation_Items()
  {
    return (EReference)testIndentationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestIndentation_Semi()
  {
    return (EAttribute)testIndentationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestColumn()
  {
    return testColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestColumn_Name()
  {
    return (EAttribute)testColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTestColumn_Items()
  {
    return (EReference)testColumnEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestOffset()
  {
    return testOffsetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestOffset_Value()
  {
    return (EAttribute)testOffsetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestOffset_First()
  {
    return (EAttribute)testOffsetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestOffset_Second()
  {
    return (EAttribute)testOffsetEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTestRightPadding()
  {
    return testRightPaddingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestRightPadding_P1()
  {
    return (EAttribute)testRightPaddingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTestRightPadding_P2()
  {
    return (EAttribute)testRightPaddingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFqnObj()
  {
    return fqnObjEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getFqnObj_Name()
  {
    return (EAttribute)fqnObjEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFqnRef()
  {
    return fqnRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFqnRef_Ref()
  {
    return (EReference)fqnRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEnumeration()
  {
    return enumerationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getEnumeration_Val()
  {
    return (EAttribute)enumerationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSuppressedHidden()
  {
    return suppressedHiddenEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getSuppressedHidden_Vals()
  {
    return (EReference)suppressedHiddenEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSuppressedHiddenSub()
  {
    return suppressedHiddenSubEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getSuppressedHiddenSub_Idval()
  {
    return (EAttribute)suppressedHiddenSubEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSuppressedHiddenSubSub()
  {
    return suppressedHiddenSubSubEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSuppressedHiddenSubID()
  {
    return suppressedHiddenSubIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDatatypes()
  {
    return datatypesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDatatypes_Val1()
  {
    return (EAttribute)datatypesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDatatypes_Val2()
  {
    return (EAttribute)datatypesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDatatypes_Val3()
  {
    return (EAttribute)datatypesEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getEnum1()
  {
    return enum1EEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public FormatterTestLanguageFactory getFormatterTestLanguageFactory()
  {
    return (FormatterTestLanguageFactory)getEFactoryInstance();
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
    rootEClass = createEClass(ROOT);

    lineEClass = createEClass(LINE);

    declEClass = createEClass(DECL);
    createEAttribute(declEClass, DECL__TYPE);
    createEAttribute(declEClass, DECL__NAME);

    assignEClass = createEClass(ASSIGN);
    createEAttribute(assignEClass, ASSIGN__VAR);
    createEAttribute(assignEClass, ASSIGN__OP);
    createEAttribute(assignEClass, ASSIGN__VAL);

    methEClass = createEClass(METH);
    createEAttribute(methEClass, METH__NAME);
    createEReference(methEClass, METH__PARAM);

    paramEClass = createEClass(PARAM);
    createEAttribute(paramEClass, PARAM__NAME);
    createEAttribute(paramEClass, PARAM__TYPE);

    spaceEClass = createEClass(SPACE);
    createEAttribute(spaceEClass, SPACE__VAL);

    testLinewrapEClass = createEClass(TEST_LINEWRAP);
    createEReference(testLinewrapEClass, TEST_LINEWRAP__ITEMS);

    testLinewrapMinMaxEClass = createEClass(TEST_LINEWRAP_MIN_MAX);
    createEReference(testLinewrapMinMaxEClass, TEST_LINEWRAP_MIN_MAX__ITEMS);

    testIndentationEClass = createEClass(TEST_INDENTATION);
    createEReference(testIndentationEClass, TEST_INDENTATION__SUB);
    createEReference(testIndentationEClass, TEST_INDENTATION__ITEMS);
    createEAttribute(testIndentationEClass, TEST_INDENTATION__SEMI);

    testColumnEClass = createEClass(TEST_COLUMN);
    createEAttribute(testColumnEClass, TEST_COLUMN__NAME);
    createEReference(testColumnEClass, TEST_COLUMN__ITEMS);

    testOffsetEClass = createEClass(TEST_OFFSET);
    createEAttribute(testOffsetEClass, TEST_OFFSET__VALUE);
    createEAttribute(testOffsetEClass, TEST_OFFSET__FIRST);
    createEAttribute(testOffsetEClass, TEST_OFFSET__SECOND);

    testRightPaddingEClass = createEClass(TEST_RIGHT_PADDING);
    createEAttribute(testRightPaddingEClass, TEST_RIGHT_PADDING__P1);
    createEAttribute(testRightPaddingEClass, TEST_RIGHT_PADDING__P2);

    fqnObjEClass = createEClass(FQN_OBJ);
    createEAttribute(fqnObjEClass, FQN_OBJ__NAME);

    fqnRefEClass = createEClass(FQN_REF);
    createEReference(fqnRefEClass, FQN_REF__REF);

    enumerationEClass = createEClass(ENUMERATION);
    createEAttribute(enumerationEClass, ENUMERATION__VAL);

    suppressedHiddenEClass = createEClass(SUPPRESSED_HIDDEN);
    createEReference(suppressedHiddenEClass, SUPPRESSED_HIDDEN__VALS);

    suppressedHiddenSubEClass = createEClass(SUPPRESSED_HIDDEN_SUB);
    createEAttribute(suppressedHiddenSubEClass, SUPPRESSED_HIDDEN_SUB__IDVAL);

    suppressedHiddenSubSubEClass = createEClass(SUPPRESSED_HIDDEN_SUB_SUB);

    suppressedHiddenSubIDEClass = createEClass(SUPPRESSED_HIDDEN_SUB_ID);

    datatypesEClass = createEClass(DATATYPES);
    createEAttribute(datatypesEClass, DATATYPES__VAL1);
    createEAttribute(datatypesEClass, DATATYPES__VAL2);
    createEAttribute(datatypesEClass, DATATYPES__VAL3);

    // Create enums
    enum1EEnum = createEEnum(ENUM1);
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

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    declEClass.getESuperTypes().add(this.getLine());
    assignEClass.getESuperTypes().add(this.getLine());
    methEClass.getESuperTypes().add(this.getLine());
    spaceEClass.getESuperTypes().add(this.getLine());
    testLinewrapEClass.getESuperTypes().add(this.getRoot());
    testLinewrapMinMaxEClass.getESuperTypes().add(this.getRoot());
    testIndentationEClass.getESuperTypes().add(this.getRoot());
    testColumnEClass.getESuperTypes().add(this.getRoot());
    testOffsetEClass.getESuperTypes().add(this.getRoot());
    testRightPaddingEClass.getESuperTypes().add(this.getRoot());
    fqnObjEClass.getESuperTypes().add(this.getLine());
    fqnRefEClass.getESuperTypes().add(this.getLine());
    enumerationEClass.getESuperTypes().add(this.getLine());
    suppressedHiddenEClass.getESuperTypes().add(this.getLine());
    suppressedHiddenSubSubEClass.getESuperTypes().add(this.getSuppressedHiddenSub());
    suppressedHiddenSubIDEClass.getESuperTypes().add(this.getSuppressedHiddenSub());
    datatypesEClass.getESuperTypes().add(this.getLine());

    // Initialize classes and features; add operations and parameters
    initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(lineEClass, Line.class, "Line", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(declEClass, Decl.class, "Decl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDecl_Type(), theEcorePackage.getEString(), "type", null, 0, -1, Decl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDecl_Name(), theEcorePackage.getEString(), "name", null, 0, -1, Decl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assignEClass, Assign.class, "Assign", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAssign_Var(), theEcorePackage.getEString(), "var", null, 0, 1, Assign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAssign_Op(), theEcorePackage.getEString(), "op", null, 0, 1, Assign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAssign_Val(), theEcorePackage.getEInt(), "val", null, 0, -1, Assign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(methEClass, Meth.class, "Meth", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMeth_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Meth.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMeth_Param(), this.getParam(), null, "param", null, 0, -1, Meth.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(paramEClass, Param.class, "Param", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParam_Name(), theEcorePackage.getEString(), "name", null, 0, -1, Param.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getParam_Type(), theEcorePackage.getEString(), "type", null, 0, -1, Param.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(spaceEClass, Space.class, "Space", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSpace_Val(), theEcorePackage.getEString(), "val", null, 0, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testLinewrapEClass, TestLinewrap.class, "TestLinewrap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTestLinewrap_Items(), this.getLine(), null, "items", null, 0, -1, TestLinewrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testLinewrapMinMaxEClass, TestLinewrapMinMax.class, "TestLinewrapMinMax", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTestLinewrapMinMax_Items(), this.getLine(), null, "items", null, 0, -1, TestLinewrapMinMax.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testIndentationEClass, TestIndentation.class, "TestIndentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTestIndentation_Sub(), this.getTestIndentation(), null, "sub", null, 0, -1, TestIndentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTestIndentation_Items(), this.getLine(), null, "items", null, 0, -1, TestIndentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTestIndentation_Semi(), theEcorePackage.getEBoolean(), "semi", null, 0, 1, TestIndentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testColumnEClass, TestColumn.class, "TestColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTestColumn_Name(), theEcorePackage.getEString(), "name", null, 0, 1, TestColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTestColumn_Items(), this.getLine(), null, "items", null, 0, -1, TestColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testOffsetEClass, TestOffset.class, "TestOffset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTestOffset_Value(), theEcorePackage.getEString(), "value", null, 0, 1, TestOffset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTestOffset_First(), theEcorePackage.getEString(), "first", null, 0, 1, TestOffset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTestOffset_Second(), theEcorePackage.getEString(), "second", null, 0, 1, TestOffset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(testRightPaddingEClass, TestRightPadding.class, "TestRightPadding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTestRightPadding_P1(), theEcorePackage.getEString(), "p1", null, 0, 1, TestRightPadding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTestRightPadding_P2(), theEcorePackage.getEString(), "p2", null, 0, 1, TestRightPadding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fqnObjEClass, FqnObj.class, "FqnObj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFqnObj_Name(), theEcorePackage.getEString(), "name", null, 0, 1, FqnObj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fqnRefEClass, FqnRef.class, "FqnRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFqnRef_Ref(), this.getFqnObj(), null, "ref", null, 0, 1, FqnRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEnumeration_Val(), this.getEnum1(), "val", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suppressedHiddenEClass, SuppressedHidden.class, "SuppressedHidden", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSuppressedHidden_Vals(), this.getSuppressedHiddenSub(), null, "vals", null, 0, -1, SuppressedHidden.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suppressedHiddenSubEClass, SuppressedHiddenSub.class, "SuppressedHiddenSub", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSuppressedHiddenSub_Idval(), theEcorePackage.getEString(), "idval", null, 0, 1, SuppressedHiddenSub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(suppressedHiddenSubSubEClass, SuppressedHiddenSubSub.class, "SuppressedHiddenSubSub", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(suppressedHiddenSubIDEClass, SuppressedHiddenSubID.class, "SuppressedHiddenSubID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(datatypesEClass, Datatypes.class, "Datatypes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDatatypes_Val1(), theEcorePackage.getEString(), "val1", null, 0, 1, Datatypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDatatypes_Val2(), theEcorePackage.getEString(), "val2", null, 0, 1, Datatypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDatatypes_Val3(), theEcorePackage.getEString(), "val3", null, 0, 1, Datatypes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(enum1EEnum, Enum1.class, "Enum1");
    addEEnumLiteral(enum1EEnum, Enum1.LIT1);
    addEEnumLiteral(enum1EEnum, Enum1.LIT2);
    addEEnumLiteral(enum1EEnum, Enum1.LIT3);

    // Create resource
    createResource(eNS_URI);
  }

} //FormatterTestLanguagePackageImpl
