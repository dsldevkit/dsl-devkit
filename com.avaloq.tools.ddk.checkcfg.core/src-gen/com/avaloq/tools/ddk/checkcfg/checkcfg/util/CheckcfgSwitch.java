/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.util;

import com.avaloq.tools.ddk.checkcfg.checkcfg.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
 * @generated
 */
public class CheckcfgSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CheckcfgPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CheckcfgSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = CheckcfgPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case CheckcfgPackage.CHECK_CONFIGURATION:
      {
        CheckConfiguration checkConfiguration = (CheckConfiguration)theEObject;
        T result = caseCheckConfiguration(checkConfiguration);
        if (result == null) result = caseConfigurableSection(checkConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR:
      {
        ConfiguredLanguageValidator configuredLanguageValidator = (ConfiguredLanguageValidator)theEObject;
        T result = caseConfiguredLanguageValidator(configuredLanguageValidator);
        if (result == null) result = caseConfigurableSection(configuredLanguageValidator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CheckcfgPackage.CONFIGURED_CATALOG:
      {
        ConfiguredCatalog configuredCatalog = (ConfiguredCatalog)theEObject;
        T result = caseConfiguredCatalog(configuredCatalog);
        if (result == null) result = caseConfigurableSection(configuredCatalog);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CheckcfgPackage.CONFIGURED_CHECK:
      {
        ConfiguredCheck configuredCheck = (ConfiguredCheck)theEObject;
        T result = caseConfiguredCheck(configuredCheck);
        if (result == null) result = caseConfigurableSection(configuredCheck);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CheckcfgPackage.CONFIGURED_PARAMETER:
      {
        ConfiguredParameter configuredParameter = (ConfiguredParameter)theEObject;
        T result = caseConfiguredParameter(configuredParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CheckcfgPackage.CONFIGURABLE_SECTION:
      {
        ConfigurableSection configurableSection = (ConfigurableSection)theEObject;
        T result = caseConfigurableSection(configurableSection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Check Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Check Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCheckConfiguration(CheckConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configured Language Validator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configured Language Validator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfiguredLanguageValidator(ConfiguredLanguageValidator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configured Catalog</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configured Catalog</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfiguredCatalog(ConfiguredCatalog object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configured Check</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configured Check</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfiguredCheck(ConfiguredCheck object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configured Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configured Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfiguredParameter(ConfiguredParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configurable Section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configurable Section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfigurableSection(ConfigurableSection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //CheckcfgSwitch
