/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.generator

import com.avaloq.tools.ddk.check.check.Category
import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.FormalParameter
import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName

import static com.avaloq.tools.ddk.check.runtime.CheckRuntimeConstants.ISSUE_CODES_CLASS_NAME_SUFFIX

class CheckGeneratorNaming {

  @Inject IQualifiedNameProvider nameProvider

  def static <T extends EObject> T parent(EObject object, Class<T> c) {
    EcoreUtil2::getContainerOfType(object, c)
  }

  // creates a pathName out of a qualified javaPackagename
  def String asPath(String javaPackageName) {
    if (javaPackageName !== null) javaPackageName.replace('.', '/') + "/" else ""
  }

  /* Gets the class name of the check validator. */
  def String validatorClassName(CheckCatalog c) {
    c.name + "CheckImpl"
  }

  /* Gets the fully qualified class name of the check validator. */
  def String qualifiedValidatorClassName(CheckCatalog c) {
    c.packageName + '.' + c.validatorClassName
  }

  /* Gets the file path of the check validator. */
  def String validatorFilePath(CheckCatalog c) {
    c.packageName.asPath + c.validatorClassName + ".java"
  }

  /* Gets the check catalog class name. */
  def String catalogClassName(CheckCatalog c) {
    c.name + "CheckCatalog"
  }

  /* Gets the qualified check catalog class name. */
  def String qualifiedCatalogClassName(CheckCatalog c) {
    c.packageName + '.' + c.catalogClassName
  }

  /* Gets the preference initializer class name. */
  def String preferenceInitializerClassName(CheckCatalog c) {
    c.name + "PreferenceInitializer"
  }

  /* Gets the qualified standalone setup class name. */
  def String qualifiedStandaloneSetupClassName(CheckCatalog c) {
    c.packageName + '.' + c.standaloneSetupClassName
  }

  /* Gets the standalone setup class name. */
  def String standaloneSetupClassName(CheckCatalog c) {
    c.name + "StandaloneSetup"
  }

  /* Gets the qualified preference initializer class name. */
  def String qualifiedPreferenceInitializerClassName(CheckCatalog c) {
    c.packageName + '.' + c.preferenceInitializerClassName
  }

  /* Gets the standalone setup class file path. */
  def String standaloneSetupPath(CheckCatalog c) {
    c.packageName.asPath + c.standaloneSetupClassName + ".java"
  }

  /* Gets the documentation file name. */
  def String docFileName(CheckCatalog c){
    c.name + ".html"
  }

  /* Gets the issue codes class name. */
  def static String issueCodesClassName(CheckCatalog c) {
    c.name + ISSUE_CODES_CLASS_NAME_SUFFIX
  }

  /* Gets the issue codes file path. */
  def String issueCodesFilePath(CheckCatalog c) {
    c.packageName.asPath  + c.issueCodesClassName + ".java"
  }

  /* Gets the quickfix provider class name. */
  def String quickfixClassName(CheckCatalog c) {
    c.name + "QuickfixProvider"
  }

  /* Gets the qualified quickfix provider class name. */
  def String qualifiedQuickfixClassName(CheckCatalog c) {
    c.packageName + '.' + c.quickfixClassName
  }

  /* Gets the quickfix provider file path. */
  def String quickfixFilePath(CheckCatalog c) {
    c.packageName.asPath + c.quickfixClassName + ".java"
  }

  /* Gets the full path to the check file, e.g. com/avaloq/MyChecks.check. */
  def String checkFilePath(CheckCatalog c) {
    c.packageName.asPath + c.name + ".check"
  }

  /* Gets the name of the getter method generated for a formal parameter. */
  def String formalParameterGetterName(FormalParameter p) {
    val check = p.eContainer as Check
    return "get"
      + check.name.toFirstUpper
      + "_"
      + p.name.toFirstUpper
  }

  /* Gets the name of the getter method generated for a field. */
  def String fieldGetterName(String fieldName) {
    "get" + fieldName.toFirstUpper
  }

  /* Check catalog instance name in the validator */
  def String catalogInstanceName(EObject object) {
    EcoreUtil2::getContainerOfType(object, typeof(CheckCatalog)).name.toFirstLower + "Catalog"
  }

  /* Check issue code to label map field name in the catalog */
  def String issueCodeToLabelMapFieldName() {
    "issueCodeToLabelMap"
  }

  /* Gets the name of the default validator class. */
  def String defaultValidatorClassName() {
    "DefaultCheckImpl"
  }

  /* Gets the fully qualified name of the default validator class. */
  def String qualifiedDefaultValidatorClassName() {
    "com.avaloq.tools.ddk.check.runtime.issue." + defaultValidatorClassName
  }

  /* Gets the prefix for the context id (used in contexts.xml) */
  def getContextIdPrefix(QualifiedName catalog){
    catalog.lastSegment.toString.toLowerCase + "_" // TODO make context id use fully qualified catalog names
  }

  /* Gets the full context id (used in contexts.xml) */
  def String getContextId(Check check) {
    val catalog = check.parent(typeof (CheckCatalog))
    nameProvider.apply(catalog).contextIdPrefix + check.label.replaceAll(" ", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase
  }

  /* Gets the full context id (used in contexts.xml) */
  def String getContextId(Category category) {
    val catalog = category.parent(typeof (CheckCatalog))
    nameProvider.apply(catalog).contextIdPrefix + category.label.replaceAll(" ", "").replaceAll("\"", "").replaceAll("'", "").toLowerCase
  }

}
