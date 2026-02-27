/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.generator;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import static com.avaloq.tools.ddk.check.runtime.CheckRuntimeConstants.ISSUE_CODES_CLASS_NAME_SUFFIX;

public class CheckGeneratorNaming {

  @Inject
  private IQualifiedNameProvider nameProvider;

  public static <T extends EObject> T parent(EObject object, Class<T> c) {
    return EcoreUtil2.getContainerOfType(object, c);
  }

  // creates a pathName out of a qualified javaPackagename
  public String asPath(String javaPackageName) {
    if (javaPackageName != null) {
      return javaPackageName.replace('.', '/') + "/";
    } else {
      return "";
    }
  }

  /* Gets the class name of the check validator. */
  public String validatorClassName(CheckCatalog c) {
    return c.getName() + "CheckImpl";
  }

  /* Gets the fully qualified class name of the check validator. */
  public String qualifiedValidatorClassName(CheckCatalog c) {
    return c.getPackageName() + "." + validatorClassName(c);
  }

  /* Gets the file path of the check validator. */
  public String validatorFilePath(CheckCatalog c) {
    return asPath(c.getPackageName()) + validatorClassName(c) + ".java";
  }

  /* Gets the check catalog class name. */
  public String catalogClassName(CheckCatalog c) {
    return c.getName() + "CheckCatalog";
  }

  /* Gets the qualified check catalog class name. */
  public String qualifiedCatalogClassName(CheckCatalog c) {
    return c.getPackageName() + "." + catalogClassName(c);
  }

  /* Gets the preference initializer class name. */
  public String preferenceInitializerClassName(CheckCatalog c) {
    return c.getName() + "PreferenceInitializer";
  }

  /* Gets the qualified standalone setup class name. */
  public String qualifiedStandaloneSetupClassName(CheckCatalog c) {
    return c.getPackageName() + "." + standaloneSetupClassName(c);
  }

  /* Gets the standalone setup class name. */
  public String standaloneSetupClassName(CheckCatalog c) {
    return c.getName() + "StandaloneSetup";
  }

  /* Gets the qualified preference initializer class name. */
  public String qualifiedPreferenceInitializerClassName(CheckCatalog c) {
    return c.getPackageName() + "." + preferenceInitializerClassName(c);
  }

  /* Gets the standalone setup class file path. */
  public String standaloneSetupPath(CheckCatalog c) {
    return asPath(c.getPackageName()) + standaloneSetupClassName(c) + ".java";
  }

  /* Gets the documentation file name. */
  public String docFileName(CheckCatalog c) {
    return c.getName() + ".html";
  }

  /* Gets the issue codes class name. */
  public static String issueCodesClassName(CheckCatalog c) {
    return c.getName() + ISSUE_CODES_CLASS_NAME_SUFFIX;
  }

  /* Gets the issue codes file path. */
  public String issueCodesFilePath(CheckCatalog c) {
    return asPath(c.getPackageName()) + issueCodesClassName(c) + ".java";
  }

  /* Gets the quickfix provider class name. */
  public String quickfixClassName(CheckCatalog c) {
    return c.getName() + "QuickfixProvider";
  }

  /* Gets the qualified quickfix provider class name. */
  public String qualifiedQuickfixClassName(CheckCatalog c) {
    return c.getPackageName() + "." + quickfixClassName(c);
  }

  /* Gets the quickfix provider file path. */
  public String quickfixFilePath(CheckCatalog c) {
    return asPath(c.getPackageName()) + quickfixClassName(c) + ".java";
  }

  /* Gets the full path to the check file, e.g. com/avaloq/MyChecks.check. */
  public String checkFilePath(CheckCatalog c) {
    return asPath(c.getPackageName()) + c.getName() + ".check";
  }

  /* Gets the name of the getter method generated for a formal parameter. */
  public String formalParameterGetterName(FormalParameter p) {
    Check check = (Check) p.eContainer();
    return "get"
      + StringExtensions.toFirstUpper(check.getName())
      + "_"
      + StringExtensions.toFirstUpper(p.getName());
  }

  /* Gets the name of the getter method generated for a field. */
  public String fieldGetterName(String fieldName) {
    return "get" + StringExtensions.toFirstUpper(fieldName);
  }

  /* Check catalog instance name in the validator */
  public String catalogInstanceName(EObject object) {
    return StringExtensions.toFirstLower(EcoreUtil2.getContainerOfType(object, CheckCatalog.class).getName()) + "Catalog";
  }

  /* Check issue code to label map field name in the catalog */
  public String issueCodeToLabelMapFieldName() {
    return "issueCodeToLabelMap";
  }

  /* Gets the name of the default validator class. */
  public String defaultValidatorClassName() {
    return "DispatchingCheckImpl";
  }

  /* Gets the fully qualified name of the default validator class. */
  public String qualifiedDefaultValidatorClassName() {
    return "com.avaloq.tools.ddk.check.runtime.issue." + defaultValidatorClassName();
  }

  /* Gets the prefix for the context id (used in contexts.xml) */
  public String getContextIdPrefix(QualifiedName catalog) {
    return catalog.getLastSegment().toString().toLowerCase() + "_"; // TODO make context id use fully qualified catalog names
  }

  /* Gets the full context id (used in contexts.xml) */
  public String getContextId(Check check) {
    CheckCatalog catalog = parent(check, CheckCatalog.class);
    return getContextIdPrefix(nameProvider.apply(catalog)) + check.getLabel().replaceAll(" ", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase();
  }

  /* Gets the full context id (used in contexts.xml) */
  public String getContextId(Category category) {
    CheckCatalog catalog = parent(category, CheckCatalog.class);
    return getContextIdPrefix(nameProvider.apply(catalog)) + category.getLabel().replaceAll(" ", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase();
  }
}
