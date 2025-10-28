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
package com.avaloq.tools.ddk.check.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.google.inject.Inject;


/**
 * Tests for com.avaloq.tools.ddk.check.validation.CheckJavaValidatorUtil.
 */

@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
class CheckJavaValidatorUtilTest {

  // assertion messages.
  private static final String STARTS_WITH_LOWER_CASE = "starts with lower case";

  private static final String CONTAINS_ILLEGAL_CHARACTER = "Contains illegal characters.(comma)";

  private static final String STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS = "Starts with and contains uppercase letters";

  private static final String CONTAINS_DOUBLE_DOT = "Contains double dot";

  private static final String CONTAINS_UPPERCASE_LETTER = "Contains uppercase letter";

  private static final String CONTAINS_ILLEGAL_CHARACTER_WHITESPACE = "Contains illegal character. (whitespace)";

  private static final String CONTAINS_ILLEGAL_CHARACTER_DOLLAR = "Contains illegal character. (dollar)";

  private static final String STARTS_WITH_ILLEGAL_CHARACTER_DOT = "Starts with illegal character. (dot)";

  private static final String STARTS_WITH_UPPERCASE_LETTER = "Starts with uppercase letter";

  private static final String NAME_IS_EMPTY = "Name is empty";

  private static final String NON_QUALIFIED_NAME = "non-qualified name";

  private static final String QUALIFIED_NAME = "Qualified name";

  private static final String EMPTY_STRING = "";

  @Inject
  private CheckJavaValidatorUtil util;

  /**
   * Tests checkProjectName. The given values are valid.
   */
  @Test
  void projectNameIsValid() {
    assertTrue(util.checkProjectName("project.name").isOK(), QUALIFIED_NAME);
    assertTrue(util.checkProjectName("projectname").isOK(), NON_QUALIFIED_NAME);
  }

  /**
   * Tests checkProjectName. The given values are invalid.
   */
  @Test
  void projectNameIsInvalid() {
    assertTrue(util.checkProjectName(EMPTY_STRING).matches(IStatus.ERROR), NAME_IS_EMPTY);
    assertTrue(util.checkProjectName("Project.name").matches(IStatus.ERROR), STARTS_WITH_UPPERCASE_LETTER);
    assertTrue(util.checkProjectName(".projectname").matches(IStatus.ERROR), STARTS_WITH_ILLEGAL_CHARACTER_DOT);
    assertTrue(util.checkCatalogName("client$project").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_DOLLAR);
    assertTrue(util.checkProjectName("project.name ").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_WHITESPACE);
    assertTrue(util.checkProjectName("proJect.Name").matches(IStatus.ERROR), CONTAINS_UPPERCASE_LETTER);
    assertTrue(util.checkProjectName("project..name").matches(IStatus.ERROR), CONTAINS_DOUBLE_DOT);
  }

  /**
   * Tests checkPackageName. The given values are valid.
   */

  @Test
  void packageNameIsValid() {
    assertTrue(util.checkPackageName("package.name").isOK(), QUALIFIED_NAME);
    assertTrue(util.checkPackageName("packagename").isOK(), NON_QUALIFIED_NAME);
  }

  /**
   * Tests checkPackageName. The given values are invalid.
   */

  @Test
  void packageNameIsInvalid() {
    assertTrue(util.checkPackageName(EMPTY_STRING).matches(IStatus.ERROR), NAME_IS_EMPTY);
    assertTrue(util.checkPackageName("Package.name").matches(IStatus.ERROR), STARTS_WITH_UPPERCASE_LETTER);
    assertTrue(util.checkPackageName(".packagename").matches(IStatus.ERROR), STARTS_WITH_ILLEGAL_CHARACTER_DOT);
    assertTrue(util.checkPackageName("packagename ").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_WHITESPACE);
    assertTrue(util.checkCatalogName("client$package").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_DOLLAR);
    assertTrue(util.checkPackageName("package.Name").matches(IStatus.ERROR), CONTAINS_UPPERCASE_LETTER);
    assertTrue(util.checkPackageName("package..name").matches(IStatus.ERROR), CONTAINS_DOUBLE_DOT);
  }

  /**
   * Tests checkCatalogName. The given values are valid.
   */
  @Test
  void catalogNameIsValid() {
    assertTrue(util.checkCatalogName("Catalogname").isOK(), STARTS_WITH_UPPERCASE_LETTER);
    assertTrue(util.checkCatalogName("CatalogName").isOK(), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);
  }

  /**
   * Tests checkCatalogName. The given values are invalid.
   */
  @Test
  void catalogNameIsInvalid() {
    assertTrue(util.checkCatalogName(EMPTY_STRING).matches(IStatus.ERROR), NAME_IS_EMPTY);
    assertTrue(util.checkCatalogName("Catalog.Name").matches(IStatus.ERROR), QUALIFIED_NAME);
    assertTrue(util.checkCatalogName(",Catalogname").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER);
    assertTrue(util.checkCatalogName("Client$Catalog").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_DOLLAR);
  }

  /**
   * Tests checkCatalogName. The given values are discouraged.
   */
  @Test
  void catalogNameIsDiscouraged() {
    assertTrue(util.checkCatalogName("catalogname").matches(IStatus.WARNING), STARTS_WITH_LOWER_CASE);
    assertTrue(util.checkCatalogName("catalogName").matches(IStatus.WARNING), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);
  }

  /**
   * Tests checkCheckName. The given values are valid.
   */
  @Test
  void checkNameIsValid() {
    assertTrue(util.checkCheckName("Checkname").isOK(), STARTS_WITH_UPPERCASE_LETTER);
    assertTrue(util.checkCheckName("CheckName").isOK(), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);
  }

  /**
   * Tests checkCheckName. The given values are invalid.
   */
  @Test
  void checkNameIsInvalid() {
    assertTrue(util.checkCheckName(EMPTY_STRING).matches(IStatus.ERROR), NAME_IS_EMPTY);
    assertTrue(util.checkCheckName("Check.name").matches(IStatus.ERROR), QUALIFIED_NAME);
    assertTrue(util.checkCheckName(",checkname").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER);
    assertTrue(util.checkCatalogName("client$check").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_DOLLAR);
  }

  /**
   * Tests checkCheckName. The given values are discouraged.
   */
  @Test
  void checkNameIsDiscouraged() {
    assertTrue(util.checkCheckName("checkname").matches(IStatus.WARNING), STARTS_WITH_LOWER_CASE);
    assertTrue(util.checkCheckName("checkName").matches(IStatus.WARNING), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);

  }

  /**
   * Tests checkCategoryName. The given values are valid.
   */
  @Test
  void categoryNameIsValid() {
    assertTrue(util.checkCategoryName("Categoryname").isOK(), STARTS_WITH_UPPERCASE_LETTER);
    assertTrue(util.checkCategoryName("CategoryName").isOK(), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);
  }

  /**
   * Tests checkCategoryName. The given values are invalid.
   */
  @Test
  void categoryNameIsInvalid() {
    assertTrue(util.checkCategoryName(EMPTY_STRING).matches(IStatus.ERROR), NAME_IS_EMPTY);
    assertTrue(util.checkCategoryName("Category.name").matches(IStatus.ERROR), QUALIFIED_NAME);
    assertTrue(util.checkCategoryName("%categoryname").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER);
    assertTrue(util.checkCatalogName("Client$Category").matches(IStatus.ERROR), CONTAINS_ILLEGAL_CHARACTER_DOLLAR);
  }

  /**
   * Tests checkCategoryName. The given values are discouraged.
   */
  @Test
  void categoryNameIsDiscouraged() {
    assertTrue(util.checkCategoryName("categoryname").matches(IStatus.WARNING), STARTS_WITH_LOWER_CASE);
    assertTrue(util.checkCategoryName("categoryName").matches(IStatus.WARNING), STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS);
  }
}
