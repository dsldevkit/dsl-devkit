/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.Category;
import com.avaloq.tools.ddk.xtext.valid.valid.CheckKind;
import com.avaloq.tools.ddk.xtext.valid.valid.Context;
import com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext;
import com.avaloq.tools.ddk.xtext.valid.valid.Import;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFix;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind;
import com.avaloq.tools.ddk.xtext.valid.valid.RangeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.Rule;
import com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind;
import com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext;
import com.avaloq.tools.ddk.xtext.valid.valid.SizeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidFactory;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

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
public class ValidPackageImpl extends EPackageImpl implements ValidPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass validModelEClass = null;

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
  private EClass categoryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ruleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass predefinedRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nativeRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sizeRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rangeRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uniqueRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass contextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleContextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass duplicateContextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nativeContextEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass quickFixEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum checkKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum severityKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum quickFixKindEEnum = null;

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
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ValidPackageImpl()
  {
    super(eNS_URI, ValidFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ValidPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ValidPackage init()
  {
    if (isInited) return (ValidPackage)EPackage.Registry.INSTANCE.getEPackage(ValidPackage.eNS_URI);

    // Obtain or create and register package
    ValidPackageImpl theValidPackage = (ValidPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ValidPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ValidPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theValidPackage.createPackageContents();

    // Initialize created meta-data
    theValidPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theValidPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ValidPackage.eNS_URI, theValidPackage);
    return theValidPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValidModel()
  {
    return validModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getValidModel_Imports()
  {
    return (EReference)validModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getValidModel_Categories()
  {
    return (EReference)validModelEClass.getEStructuralFeatures().get(1);
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
  public EClass getCategory()
  {
    return categoryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Name()
  {
    return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Label()
  {
    return (EAttribute)categoryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategory_Description()
  {
    return (EAttribute)categoryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCategory_Rules()
  {
    return (EReference)categoryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRule()
  {
    return ruleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Optional()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_CheckKind()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Severity()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Name()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Label()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Description()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRule_Message()
  {
    return (EAttribute)ruleEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPredefinedRule()
  {
    return predefinedRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNativeRule()
  {
    return nativeRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNativeRule_Contexts()
  {
    return (EReference)nativeRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSizeRule()
  {
    return sizeRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSizeRule_Min()
  {
    return (EAttribute)sizeRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSizeRule_Max()
  {
    return (EAttribute)sizeRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSizeRule_Contexts()
  {
    return (EReference)sizeRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRangeRule()
  {
    return rangeRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRangeRule_Min()
  {
    return (EAttribute)rangeRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRangeRule_Max()
  {
    return (EAttribute)rangeRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRangeRule_Contexts()
  {
    return (EReference)rangeRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUniqueRule()
  {
    return uniqueRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUniqueRule_Contexts()
  {
    return (EReference)uniqueRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContext()
  {
    return contextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getContext_ContextType()
  {
    return (EReference)contextEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getContext_ContextFeature()
  {
    return (EReference)contextEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimpleContext()
  {
    return simpleContextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDuplicateContext()
  {
    return duplicateContextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDuplicateContext_MarkerType()
  {
    return (EReference)duplicateContextEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDuplicateContext_MarkerFeature()
  {
    return (EReference)duplicateContextEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNativeContext()
  {
    return nativeContextEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNativeContext_Named()
  {
    return (EAttribute)nativeContextEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNativeContext_GivenName()
  {
    return (EAttribute)nativeContextEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNativeContext_MarkerType()
  {
    return (EReference)nativeContextEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNativeContext_MarkerFeature()
  {
    return (EReference)nativeContextEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNativeContext_QuickFixes()
  {
    return (EReference)nativeContextEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQuickFix()
  {
    return quickFixEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuickFix_QuickFixKind()
  {
    return (EAttribute)quickFixEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuickFix_Name()
  {
    return (EAttribute)quickFixEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuickFix_Label()
  {
    return (EAttribute)quickFixEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQuickFix_Message()
  {
    return (EAttribute)quickFixEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getCheckKind()
  {
    return checkKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getSeverityKind()
  {
    return severityKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getQuickFixKind()
  {
    return quickFixKindEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValidFactory getValidFactory()
  {
    return (ValidFactory)getEFactoryInstance();
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
    validModelEClass = createEClass(VALID_MODEL);
    createEReference(validModelEClass, VALID_MODEL__IMPORTS);
    createEReference(validModelEClass, VALID_MODEL__CATEGORIES);

    importEClass = createEClass(IMPORT);
    createEReference(importEClass, IMPORT__PACKAGE);

    categoryEClass = createEClass(CATEGORY);
    createEAttribute(categoryEClass, CATEGORY__NAME);
    createEAttribute(categoryEClass, CATEGORY__LABEL);
    createEAttribute(categoryEClass, CATEGORY__DESCRIPTION);
    createEReference(categoryEClass, CATEGORY__RULES);

    ruleEClass = createEClass(RULE);
    createEAttribute(ruleEClass, RULE__OPTIONAL);
    createEAttribute(ruleEClass, RULE__CHECK_KIND);
    createEAttribute(ruleEClass, RULE__SEVERITY);
    createEAttribute(ruleEClass, RULE__NAME);
    createEAttribute(ruleEClass, RULE__LABEL);
    createEAttribute(ruleEClass, RULE__DESCRIPTION);
    createEAttribute(ruleEClass, RULE__MESSAGE);

    predefinedRuleEClass = createEClass(PREDEFINED_RULE);

    nativeRuleEClass = createEClass(NATIVE_RULE);
    createEReference(nativeRuleEClass, NATIVE_RULE__CONTEXTS);

    sizeRuleEClass = createEClass(SIZE_RULE);
    createEAttribute(sizeRuleEClass, SIZE_RULE__MIN);
    createEAttribute(sizeRuleEClass, SIZE_RULE__MAX);
    createEReference(sizeRuleEClass, SIZE_RULE__CONTEXTS);

    rangeRuleEClass = createEClass(RANGE_RULE);
    createEAttribute(rangeRuleEClass, RANGE_RULE__MIN);
    createEAttribute(rangeRuleEClass, RANGE_RULE__MAX);
    createEReference(rangeRuleEClass, RANGE_RULE__CONTEXTS);

    uniqueRuleEClass = createEClass(UNIQUE_RULE);
    createEReference(uniqueRuleEClass, UNIQUE_RULE__CONTEXTS);

    contextEClass = createEClass(CONTEXT);
    createEReference(contextEClass, CONTEXT__CONTEXT_TYPE);
    createEReference(contextEClass, CONTEXT__CONTEXT_FEATURE);

    simpleContextEClass = createEClass(SIMPLE_CONTEXT);

    duplicateContextEClass = createEClass(DUPLICATE_CONTEXT);
    createEReference(duplicateContextEClass, DUPLICATE_CONTEXT__MARKER_TYPE);
    createEReference(duplicateContextEClass, DUPLICATE_CONTEXT__MARKER_FEATURE);

    nativeContextEClass = createEClass(NATIVE_CONTEXT);
    createEAttribute(nativeContextEClass, NATIVE_CONTEXT__NAMED);
    createEAttribute(nativeContextEClass, NATIVE_CONTEXT__GIVEN_NAME);
    createEReference(nativeContextEClass, NATIVE_CONTEXT__MARKER_TYPE);
    createEReference(nativeContextEClass, NATIVE_CONTEXT__MARKER_FEATURE);
    createEReference(nativeContextEClass, NATIVE_CONTEXT__QUICK_FIXES);

    quickFixEClass = createEClass(QUICK_FIX);
    createEAttribute(quickFixEClass, QUICK_FIX__QUICK_FIX_KIND);
    createEAttribute(quickFixEClass, QUICK_FIX__NAME);
    createEAttribute(quickFixEClass, QUICK_FIX__LABEL);
    createEAttribute(quickFixEClass, QUICK_FIX__MESSAGE);

    // Create enums
    checkKindEEnum = createEEnum(CHECK_KIND);
    severityKindEEnum = createEEnum(SEVERITY_KIND);
    quickFixKindEEnum = createEEnum(QUICK_FIX_KIND);
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
    predefinedRuleEClass.getESuperTypes().add(this.getRule());
    nativeRuleEClass.getESuperTypes().add(this.getRule());
    sizeRuleEClass.getESuperTypes().add(this.getPredefinedRule());
    rangeRuleEClass.getESuperTypes().add(this.getPredefinedRule());
    uniqueRuleEClass.getESuperTypes().add(this.getPredefinedRule());
    simpleContextEClass.getESuperTypes().add(this.getContext());
    duplicateContextEClass.getESuperTypes().add(this.getContext());
    nativeContextEClass.getESuperTypes().add(this.getContext());

    // Initialize classes and features; add operations and parameters
    initEClass(validModelEClass, ValidModel.class, "ValidModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getValidModel_Imports(), this.getImport(), null, "imports", null, 0, -1, ValidModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getValidModel_Categories(), this.getCategory(), null, "categories", null, 0, -1, ValidModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImport_Package(), theEcorePackage.getEPackage(), null, "package", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCategory_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCategory_Label(), theEcorePackage.getEString(), "label", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCategory_Description(), theEcorePackage.getEString(), "description", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCategory_Rules(), this.getRule(), null, "rules", null, 0, -1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ruleEClass, Rule.class, "Rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRule_Optional(), theEcorePackage.getEBoolean(), "optional", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_CheckKind(), this.getCheckKind(), "checkKind", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_Severity(), this.getSeverityKind(), "severity", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_Label(), theEcorePackage.getEString(), "label", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_Description(), theEcorePackage.getEString(), "description", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRule_Message(), theEcorePackage.getEString(), "message", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(predefinedRuleEClass, PredefinedRule.class, "PredefinedRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(nativeRuleEClass, NativeRule.class, "NativeRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNativeRule_Contexts(), this.getNativeContext(), null, "contexts", null, 0, -1, NativeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sizeRuleEClass, SizeRule.class, "SizeRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSizeRule_Min(), theEcorePackage.getEInt(), "min", null, 0, 1, SizeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSizeRule_Max(), theEcorePackage.getEInt(), "max", null, 0, 1, SizeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSizeRule_Contexts(), this.getSimpleContext(), null, "contexts", null, 0, -1, SizeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rangeRuleEClass, RangeRule.class, "RangeRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRangeRule_Min(), theEcorePackage.getEInt(), "min", null, 0, 1, RangeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRangeRule_Max(), theEcorePackage.getEInt(), "max", null, 0, 1, RangeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRangeRule_Contexts(), this.getSimpleContext(), null, "contexts", null, 0, -1, RangeRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(uniqueRuleEClass, UniqueRule.class, "UniqueRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getUniqueRule_Contexts(), this.getDuplicateContext(), null, "contexts", null, 0, -1, UniqueRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(contextEClass, Context.class, "Context", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getContext_ContextType(), theEcorePackage.getEClass(), null, "contextType", null, 0, 1, Context.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getContext_ContextFeature(), theEcorePackage.getEStructuralFeature(), null, "contextFeature", null, 0, 1, Context.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simpleContextEClass, SimpleContext.class, "SimpleContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(duplicateContextEClass, DuplicateContext.class, "DuplicateContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDuplicateContext_MarkerType(), theEcorePackage.getEClass(), null, "markerType", null, 0, 1, DuplicateContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDuplicateContext_MarkerFeature(), theEcorePackage.getEStructuralFeature(), null, "markerFeature", null, 0, 1, DuplicateContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nativeContextEClass, NativeContext.class, "NativeContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNativeContext_Named(), theEcorePackage.getEBoolean(), "named", null, 0, 1, NativeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNativeContext_GivenName(), theEcorePackage.getEString(), "givenName", null, 0, 1, NativeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNativeContext_MarkerType(), theEcorePackage.getEClass(), null, "markerType", null, 0, 1, NativeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNativeContext_MarkerFeature(), theEcorePackage.getEStructuralFeature(), null, "markerFeature", null, 0, 1, NativeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNativeContext_QuickFixes(), this.getQuickFix(), null, "quickFixes", null, 0, -1, NativeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(quickFixEClass, QuickFix.class, "QuickFix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getQuickFix_QuickFixKind(), this.getQuickFixKind(), "quickFixKind", null, 0, 1, QuickFix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuickFix_Name(), theEcorePackage.getEString(), "name", null, 0, 1, QuickFix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuickFix_Label(), theEcorePackage.getEString(), "label", null, 0, 1, QuickFix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getQuickFix_Message(), theEcorePackage.getEString(), "message", null, 0, 1, QuickFix.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(checkKindEEnum, CheckKind.class, "CheckKind");
    addEEnumLiteral(checkKindEEnum, CheckKind.FAST);
    addEEnumLiteral(checkKindEEnum, CheckKind.NORMAL);
    addEEnumLiteral(checkKindEEnum, CheckKind.EXPENSIVE);

    initEEnum(severityKindEEnum, SeverityKind.class, "SeverityKind");
    addEEnumLiteral(severityKindEEnum, SeverityKind.ERROR);
    addEEnumLiteral(severityKindEEnum, SeverityKind.WARNING);

    initEEnum(quickFixKindEEnum, QuickFixKind.class, "QuickFixKind");
    addEEnumLiteral(quickFixKindEEnum, QuickFixKind.SEMANTIC);
    addEEnumLiteral(quickFixKindEEnum, QuickFixKind.TEXTUAL);

    // Create resource
    createResource(eNS_URI);
  }

} //ValidPackageImpl
