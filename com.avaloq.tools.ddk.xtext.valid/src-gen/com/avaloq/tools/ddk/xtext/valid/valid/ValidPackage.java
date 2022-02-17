/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidFactory
 * @model kind="package"
 * @generated
 */
public interface ValidPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "valid";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.avaloq.com/tools/ddk/xtext/valid/Valid";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "valid";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ValidPackage eINSTANCE = com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl.init();

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidModelImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getValidModel()
   * @generated
   */
  int VALID_MODEL = 0;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALID_MODEL__IMPORTS = 0;

  /**
   * The feature id for the '<em><b>Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALID_MODEL__CATEGORIES = 1;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALID_MODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ImportImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 1;

  /**
   * The feature id for the '<em><b>Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__PACKAGE = 0;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.CategoryImpl <em>Category</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.CategoryImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getCategory()
   * @generated
   */
  int CATEGORY = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__NAME = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__LABEL = 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__RULES = 3;

  /**
   * The number of structural features of the '<em>Category</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl <em>Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getRule()
   * @generated
   */
  int RULE = 3;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__OPTIONAL = 0;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__CHECK_KIND = 1;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__SEVERITY = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__NAME = 3;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__LABEL = 4;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__DESCRIPTION = 5;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE__MESSAGE = 6;

  /**
   * The number of structural features of the '<em>Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.PredefinedRuleImpl <em>Predefined Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.PredefinedRuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getPredefinedRule()
   * @generated
   */
  int PREDEFINED_RULE = 4;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__OPTIONAL = RULE__OPTIONAL;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__CHECK_KIND = RULE__CHECK_KIND;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__SEVERITY = RULE__SEVERITY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__NAME = RULE__NAME;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__LABEL = RULE__LABEL;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__DESCRIPTION = RULE__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE__MESSAGE = RULE__MESSAGE;

  /**
   * The number of structural features of the '<em>Predefined Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDEFINED_RULE_FEATURE_COUNT = RULE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeRuleImpl <em>Native Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeRuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getNativeRule()
   * @generated
   */
  int NATIVE_RULE = 5;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__OPTIONAL = RULE__OPTIONAL;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__CHECK_KIND = RULE__CHECK_KIND;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__SEVERITY = RULE__SEVERITY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__NAME = RULE__NAME;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__LABEL = RULE__LABEL;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__DESCRIPTION = RULE__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__MESSAGE = RULE__MESSAGE;

  /**
   * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE__CONTEXTS = RULE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Native Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_RULE_FEATURE_COUNT = RULE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.SizeRuleImpl <em>Size Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.SizeRuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSizeRule()
   * @generated
   */
  int SIZE_RULE = 6;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__OPTIONAL = PREDEFINED_RULE__OPTIONAL;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__CHECK_KIND = PREDEFINED_RULE__CHECK_KIND;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__SEVERITY = PREDEFINED_RULE__SEVERITY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__NAME = PREDEFINED_RULE__NAME;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__LABEL = PREDEFINED_RULE__LABEL;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__DESCRIPTION = PREDEFINED_RULE__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__MESSAGE = PREDEFINED_RULE__MESSAGE;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__MIN = PREDEFINED_RULE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__MAX = PREDEFINED_RULE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE__CONTEXTS = PREDEFINED_RULE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Size Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_RULE_FEATURE_COUNT = PREDEFINED_RULE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl <em>Range Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getRangeRule()
   * @generated
   */
  int RANGE_RULE = 7;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__OPTIONAL = PREDEFINED_RULE__OPTIONAL;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__CHECK_KIND = PREDEFINED_RULE__CHECK_KIND;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__SEVERITY = PREDEFINED_RULE__SEVERITY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__NAME = PREDEFINED_RULE__NAME;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__LABEL = PREDEFINED_RULE__LABEL;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__DESCRIPTION = PREDEFINED_RULE__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__MESSAGE = PREDEFINED_RULE__MESSAGE;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__MIN = PREDEFINED_RULE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__MAX = PREDEFINED_RULE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE__CONTEXTS = PREDEFINED_RULE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Range Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANGE_RULE_FEATURE_COUNT = PREDEFINED_RULE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.UniqueRuleImpl <em>Unique Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.UniqueRuleImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getUniqueRule()
   * @generated
   */
  int UNIQUE_RULE = 8;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__OPTIONAL = PREDEFINED_RULE__OPTIONAL;

  /**
   * The feature id for the '<em><b>Check Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__CHECK_KIND = PREDEFINED_RULE__CHECK_KIND;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__SEVERITY = PREDEFINED_RULE__SEVERITY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__NAME = PREDEFINED_RULE__NAME;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__LABEL = PREDEFINED_RULE__LABEL;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__DESCRIPTION = PREDEFINED_RULE__DESCRIPTION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__MESSAGE = PREDEFINED_RULE__MESSAGE;

  /**
   * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE__CONTEXTS = PREDEFINED_RULE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Unique Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIQUE_RULE_FEATURE_COUNT = PREDEFINED_RULE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl <em>Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getContext()
   * @generated
   */
  int CONTEXT = 9;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT__CONTEXT_TYPE = 0;

  /**
   * The feature id for the '<em><b>Context Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT__CONTEXT_FEATURE = 1;

  /**
   * The number of structural features of the '<em>Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.SimpleContextImpl <em>Simple Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.SimpleContextImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSimpleContext()
   * @generated
   */
  int SIMPLE_CONTEXT = 10;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_CONTEXT__CONTEXT_TYPE = CONTEXT__CONTEXT_TYPE;

  /**
   * The feature id for the '<em><b>Context Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_CONTEXT__CONTEXT_FEATURE = CONTEXT__CONTEXT_FEATURE;

  /**
   * The number of structural features of the '<em>Simple Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_CONTEXT_FEATURE_COUNT = CONTEXT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl <em>Duplicate Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getDuplicateContext()
   * @generated
   */
  int DUPLICATE_CONTEXT = 11;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DUPLICATE_CONTEXT__CONTEXT_TYPE = CONTEXT__CONTEXT_TYPE;

  /**
   * The feature id for the '<em><b>Context Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DUPLICATE_CONTEXT__CONTEXT_FEATURE = CONTEXT__CONTEXT_FEATURE;

  /**
   * The feature id for the '<em><b>Marker Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DUPLICATE_CONTEXT__MARKER_TYPE = CONTEXT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Marker Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DUPLICATE_CONTEXT__MARKER_FEATURE = CONTEXT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Duplicate Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DUPLICATE_CONTEXT_FEATURE_COUNT = CONTEXT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl <em>Native Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getNativeContext()
   * @generated
   */
  int NATIVE_CONTEXT = 12;

  /**
   * The feature id for the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__CONTEXT_TYPE = CONTEXT__CONTEXT_TYPE;

  /**
   * The feature id for the '<em><b>Context Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__CONTEXT_FEATURE = CONTEXT__CONTEXT_FEATURE;

  /**
   * The feature id for the '<em><b>Named</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__NAMED = CONTEXT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Given Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__GIVEN_NAME = CONTEXT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Marker Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__MARKER_TYPE = CONTEXT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Marker Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__MARKER_FEATURE = CONTEXT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Quick Fixes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT__QUICK_FIXES = CONTEXT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Native Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_CONTEXT_FEATURE_COUNT = CONTEXT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl <em>Quick Fix</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getQuickFix()
   * @generated
   */
  int QUICK_FIX = 13;

  /**
   * The feature id for the '<em><b>Quick Fix Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUICK_FIX__QUICK_FIX_KIND = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUICK_FIX__NAME = 1;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUICK_FIX__LABEL = 2;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUICK_FIX__MESSAGE = 3;

  /**
   * The number of structural features of the '<em>Quick Fix</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUICK_FIX_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.CheckKind <em>Check Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.CheckKind
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getCheckKind()
   * @generated
   */
  int CHECK_KIND = 14;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind <em>Severity Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSeverityKind()
   * @generated
   */
  int SEVERITY_KIND = 15;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind <em>Quick Fix Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind
   * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getQuickFixKind()
   * @generated
   */
  int QUICK_FIX_KIND = 16;


  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidModel
   * @generated
   */
  EClass getValidModel();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getImports()
   * @see #getValidModel()
   * @generated
   */
  EReference getValidModel_Imports();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getCategories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Categories</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getCategories()
   * @see #getValidModel()
   * @generated
   */
  EReference getValidModel_Categories();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.Import#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Package</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Import#getPackage()
   * @see #getImport()
   * @generated
   */
  EReference getImport_Package();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Category</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category
   * @generated
   */
  EClass getCategory();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category#getName()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Name();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category#getLabel()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Label();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category#getDescription()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Description();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getRules <em>Rules</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Rules</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Category#getRules()
   * @see #getCategory()
   * @generated
   */
  EReference getCategory_Rules();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule <em>Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule
   * @generated
   */
  EClass getRule();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#isOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#isOptional()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Optional();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getCheckKind <em>Check Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Check Kind</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getCheckKind()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_CheckKind();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getSeverity <em>Severity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Severity</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getSeverity()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Severity();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getName()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Name();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getLabel()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Label();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getDescription()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Description();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Rule#getMessage()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Message();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule <em>Predefined Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Predefined Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule
   * @generated
   */
  EClass getPredefinedRule();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeRule <em>Native Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Native Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeRule
   * @generated
   */
  EClass getNativeRule();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeRule#getContexts <em>Contexts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contexts</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeRule#getContexts()
   * @see #getNativeRule()
   * @generated
   */
  EReference getNativeRule_Contexts();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.SizeRule <em>Size Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Size Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SizeRule
   * @generated
   */
  EClass getSizeRule();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getMin()
   * @see #getSizeRule()
   * @generated
   */
  EAttribute getSizeRule_Min();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getMax()
   * @see #getSizeRule()
   * @generated
   */
  EAttribute getSizeRule_Max();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getContexts <em>Contexts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contexts</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SizeRule#getContexts()
   * @see #getSizeRule()
   * @generated
   */
  EReference getSizeRule_Contexts();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule <em>Range Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Range Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.RangeRule
   * @generated
   */
  EClass getRangeRule();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMin()
   * @see #getRangeRule()
   * @generated
   */
  EAttribute getRangeRule_Min();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMax()
   * @see #getRangeRule()
   * @generated
   */
  EAttribute getRangeRule_Max();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getContexts <em>Contexts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contexts</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getContexts()
   * @see #getRangeRule()
   * @generated
   */
  EReference getRangeRule_Contexts();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule <em>Unique Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unique Rule</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule
   * @generated
   */
  EClass getUniqueRule();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule#getContexts <em>Contexts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contexts</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule#getContexts()
   * @see #getUniqueRule()
   * @generated
   */
  EReference getUniqueRule_Contexts();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Context
   * @generated
   */
  EClass getContext();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextType <em>Context Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextType()
   * @see #getContext()
   * @generated
   */
  EReference getContext_ContextType();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextFeature <em>Context Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Context Feature</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextFeature()
   * @see #getContext()
   * @generated
   */
  EReference getContext_ContextFeature();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext <em>Simple Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext
   * @generated
   */
  EClass getSimpleContext();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext <em>Duplicate Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Duplicate Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext
   * @generated
   */
  EClass getDuplicateContext();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerType <em>Marker Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Marker Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerType()
   * @see #getDuplicateContext()
   * @generated
   */
  EReference getDuplicateContext_MarkerType();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerFeature <em>Marker Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Marker Feature</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerFeature()
   * @see #getDuplicateContext()
   * @generated
   */
  EReference getDuplicateContext_MarkerFeature();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext <em>Native Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Native Context</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext
   * @generated
   */
  EClass getNativeContext();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#isNamed <em>Named</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Named</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#isNamed()
   * @see #getNativeContext()
   * @generated
   */
  EAttribute getNativeContext_Named();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getGivenName <em>Given Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Given Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getGivenName()
   * @see #getNativeContext()
   * @generated
   */
  EAttribute getNativeContext_GivenName();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerType <em>Marker Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Marker Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerType()
   * @see #getNativeContext()
   * @generated
   */
  EReference getNativeContext_MarkerType();

  /**
   * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerFeature <em>Marker Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Marker Feature</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerFeature()
   * @see #getNativeContext()
   * @generated
   */
  EReference getNativeContext_MarkerFeature();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getQuickFixes <em>Quick Fixes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Quick Fixes</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getQuickFixes()
   * @see #getNativeContext()
   * @generated
   */
  EReference getNativeContext_QuickFixes();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix <em>Quick Fix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Quick Fix</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix
   * @generated
   */
  EClass getQuickFix();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getQuickFixKind <em>Quick Fix Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Quick Fix Kind</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getQuickFixKind()
   * @see #getQuickFix()
   * @generated
   */
  EAttribute getQuickFix_QuickFixKind();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getName()
   * @see #getQuickFix()
   * @generated
   */
  EAttribute getQuickFix_Name();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getLabel()
   * @see #getQuickFix()
   * @generated
   */
  EAttribute getQuickFix_Label();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getMessage()
   * @see #getQuickFix()
   * @generated
   */
  EAttribute getQuickFix_Message();

  /**
   * Returns the meta object for enum '{@link com.avaloq.tools.ddk.xtext.valid.valid.CheckKind <em>Check Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Check Kind</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.CheckKind
   * @generated
   */
  EEnum getCheckKind();

  /**
   * Returns the meta object for enum '{@link com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind <em>Severity Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Severity Kind</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind
   * @generated
   */
  EEnum getSeverityKind();

  /**
   * Returns the meta object for enum '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind <em>Quick Fix Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Quick Fix Kind</em>'.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind
   * @generated
   */
  EEnum getQuickFixKind();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ValidFactory getValidFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidModelImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getValidModel()
     * @generated
     */
    EClass VALID_MODEL = eINSTANCE.getValidModel();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VALID_MODEL__IMPORTS = eINSTANCE.getValidModel_Imports();

    /**
     * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VALID_MODEL__CATEGORIES = eINSTANCE.getValidModel_Categories();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ImportImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPORT__PACKAGE = eINSTANCE.getImport_Package();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.CategoryImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getCategory()
     * @generated
     */
    EClass CATEGORY = eINSTANCE.getCategory();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__NAME = eINSTANCE.getCategory_Name();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__LABEL = eINSTANCE.getCategory_Label();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description();

    /**
     * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATEGORY__RULES = eINSTANCE.getCategory_Rules();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl <em>Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.RuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getRule()
     * @generated
     */
    EClass RULE = eINSTANCE.getRule();

    /**
     * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__OPTIONAL = eINSTANCE.getRule_Optional();

    /**
     * The meta object literal for the '<em><b>Check Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__CHECK_KIND = eINSTANCE.getRule_CheckKind();

    /**
     * The meta object literal for the '<em><b>Severity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__SEVERITY = eINSTANCE.getRule_Severity();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__NAME = eINSTANCE.getRule_Name();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__LABEL = eINSTANCE.getRule_Label();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__DESCRIPTION = eINSTANCE.getRule_Description();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RULE__MESSAGE = eINSTANCE.getRule_Message();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.PredefinedRuleImpl <em>Predefined Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.PredefinedRuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getPredefinedRule()
     * @generated
     */
    EClass PREDEFINED_RULE = eINSTANCE.getPredefinedRule();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeRuleImpl <em>Native Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeRuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getNativeRule()
     * @generated
     */
    EClass NATIVE_RULE = eINSTANCE.getNativeRule();

    /**
     * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NATIVE_RULE__CONTEXTS = eINSTANCE.getNativeRule_Contexts();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.SizeRuleImpl <em>Size Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.SizeRuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSizeRule()
     * @generated
     */
    EClass SIZE_RULE = eINSTANCE.getSizeRule();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_RULE__MIN = eINSTANCE.getSizeRule_Min();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_RULE__MAX = eINSTANCE.getSizeRule_Max();

    /**
     * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIZE_RULE__CONTEXTS = eINSTANCE.getSizeRule_Contexts();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl <em>Range Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getRangeRule()
     * @generated
     */
    EClass RANGE_RULE = eINSTANCE.getRangeRule();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RANGE_RULE__MIN = eINSTANCE.getRangeRule_Min();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RANGE_RULE__MAX = eINSTANCE.getRangeRule_Max();

    /**
     * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RANGE_RULE__CONTEXTS = eINSTANCE.getRangeRule_Contexts();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.UniqueRuleImpl <em>Unique Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.UniqueRuleImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getUniqueRule()
     * @generated
     */
    EClass UNIQUE_RULE = eINSTANCE.getUniqueRule();

    /**
     * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNIQUE_RULE__CONTEXTS = eINSTANCE.getUniqueRule_Contexts();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl <em>Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getContext()
     * @generated
     */
    EClass CONTEXT = eINSTANCE.getContext();

    /**
     * The meta object literal for the '<em><b>Context Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT__CONTEXT_TYPE = eINSTANCE.getContext_ContextType();

    /**
     * The meta object literal for the '<em><b>Context Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT__CONTEXT_FEATURE = eINSTANCE.getContext_ContextFeature();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.SimpleContextImpl <em>Simple Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.SimpleContextImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSimpleContext()
     * @generated
     */
    EClass SIMPLE_CONTEXT = eINSTANCE.getSimpleContext();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl <em>Duplicate Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getDuplicateContext()
     * @generated
     */
    EClass DUPLICATE_CONTEXT = eINSTANCE.getDuplicateContext();

    /**
     * The meta object literal for the '<em><b>Marker Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DUPLICATE_CONTEXT__MARKER_TYPE = eINSTANCE.getDuplicateContext_MarkerType();

    /**
     * The meta object literal for the '<em><b>Marker Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DUPLICATE_CONTEXT__MARKER_FEATURE = eINSTANCE.getDuplicateContext_MarkerFeature();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl <em>Native Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getNativeContext()
     * @generated
     */
    EClass NATIVE_CONTEXT = eINSTANCE.getNativeContext();

    /**
     * The meta object literal for the '<em><b>Named</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NATIVE_CONTEXT__NAMED = eINSTANCE.getNativeContext_Named();

    /**
     * The meta object literal for the '<em><b>Given Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NATIVE_CONTEXT__GIVEN_NAME = eINSTANCE.getNativeContext_GivenName();

    /**
     * The meta object literal for the '<em><b>Marker Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NATIVE_CONTEXT__MARKER_TYPE = eINSTANCE.getNativeContext_MarkerType();

    /**
     * The meta object literal for the '<em><b>Marker Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NATIVE_CONTEXT__MARKER_FEATURE = eINSTANCE.getNativeContext_MarkerFeature();

    /**
     * The meta object literal for the '<em><b>Quick Fixes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NATIVE_CONTEXT__QUICK_FIXES = eINSTANCE.getNativeContext_QuickFixes();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl <em>Quick Fix</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.QuickFixImpl
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getQuickFix()
     * @generated
     */
    EClass QUICK_FIX = eINSTANCE.getQuickFix();

    /**
     * The meta object literal for the '<em><b>Quick Fix Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUICK_FIX__QUICK_FIX_KIND = eINSTANCE.getQuickFix_QuickFixKind();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUICK_FIX__NAME = eINSTANCE.getQuickFix_Name();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUICK_FIX__LABEL = eINSTANCE.getQuickFix_Label();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUICK_FIX__MESSAGE = eINSTANCE.getQuickFix_Message();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.CheckKind <em>Check Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.CheckKind
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getCheckKind()
     * @generated
     */
    EEnum CHECK_KIND = eINSTANCE.getCheckKind();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind <em>Severity Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getSeverityKind()
     * @generated
     */
    EEnum SEVERITY_KIND = eINSTANCE.getSeverityKind();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind <em>Quick Fix Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind
     * @see com.avaloq.tools.ddk.xtext.valid.valid.impl.ValidPackageImpl#getQuickFixKind()
     * @generated
     */
    EEnum QUICK_FIX_KIND = eINSTANCE.getQuickFixKind();

  }

} //ValidPackage
