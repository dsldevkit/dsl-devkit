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
package com.avaloq.tools.ddk.check.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.google.inject.Inject;

import junit.framework.TestCase;


/**
 * Tests for com.avaloq.tools.ddk.check.validation.CheckJavaValidatorUtil.
 */

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
public class CheckJavaValidatorUtilTest extends TestCase {

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
  public void projectNameIsValid() {
    assertTrue(QUALIFIED_NAME, util.checkProjectName("project.name").isOK());
    assertTrue(NON_QUALIFIED_NAME, util.checkProjectName("projectname").isOK());
  }

  /**
   * Tests checkProjectName. The given values are invalid.
   */
  @Test
  public void projectNameIsInvalid() {
    assertTrue(NAME_IS_EMPTY, util.checkProjectName(EMPTY_STRING).matches(IStatus.ERROR));
    assertTrue(STARTS_WITH_UPPERCASE_LETTER, util.checkProjectName("Project.name").matches(IStatus.ERROR));
    assertTrue(STARTS_WITH_ILLEGAL_CHARACTER_DOT, util.checkProjectName(".projectname").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_DOLLAR, util.checkCatalogName("client$project").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_WHITESPACE, util.checkProjectName("project.name ").matches(IStatus.ERROR));
    assertTrue(CONTAINS_UPPERCASE_LETTER, util.checkProjectName("proJect.Name").matches(IStatus.ERROR));
    assertTrue(CONTAINS_DOUBLE_DOT, util.checkProjectName("project..name").matches(IStatus.ERROR));
  }

  /**
   * Tests checkPackageName. The given values are valid.
   */

  @Test
  public void packageNameIsValid() {
    assertTrue(QUALIFIED_NAME, util.checkPackageName("package.name").isOK());
    assertTrue(NON_QUALIFIED_NAME, util.checkPackageName("packagename").isOK());
  }

  /**
   * Tests checkPackageName. The given values are invalid.
   */

  @Test
  public void packageNameIsInvalid() {
    assertTrue(NAME_IS_EMPTY, util.checkPackageName(EMPTY_STRING).matches(IStatus.ERROR));
    assertTrue(STARTS_WITH_UPPERCASE_LETTER, util.checkPackageName("Package.name").matches(IStatus.ERROR));
    assertTrue(STARTS_WITH_ILLEGAL_CHARACTER_DOT, util.checkPackageName(".packagename").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_WHITESPACE, util.checkPackageName("packagename ").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_DOLLAR, util.checkCatalogName("client$package").matches(IStatus.ERROR));
    assertTrue(CONTAINS_UPPERCASE_LETTER, util.checkPackageName("package.Name").matches(IStatus.ERROR));
    assertTrue(CONTAINS_DOUBLE_DOT, util.checkPackageName("package..name").matches(IStatus.ERROR));
  }

  /**
   * Tests checkCatalogName. The given values are valid.
   */
  @Test
  public void catalogNameIsValid() {
    assertTrue(STARTS_WITH_UPPERCASE_LETTER, util.checkCatalogName("Catalogname").isOK());
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCatalogName("CatalogName").isOK());
  }

  /**
   * Tests checkCatalogName. The given values are invalid.
   */
  @Test
  public void catalogNameIsInvalid() {
    assertTrue(NAME_IS_EMPTY, util.checkCatalogName(EMPTY_STRING).matches(IStatus.ERROR));
    assertTrue(QUALIFIED_NAME, util.checkCatalogName("Catalog.Name").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER, util.checkCatalogName(",Catalogname").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_DOLLAR, util.checkCatalogName("Client$Catalog").matches(IStatus.ERROR));
  }

  /**
   * Tests checkCatalogName. The given values are discouraged.
   */
  @Test
  public void catalogNameIsDiscouraged() {
    assertTrue(STARTS_WITH_LOWER_CASE, util.checkCatalogName("catalogname").matches(IStatus.WARNING));
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCatalogName("catalogName").matches(IStatus.WARNING));
  }

  /**
   * Tests checkCheckName. The given values are valid.
   */
  @Test
  public void checkNameIsValid() {
    assertTrue(STARTS_WITH_UPPERCASE_LETTER, util.checkCheckName("Checkname").isOK());
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCheckName("CheckName").isOK());
  }

  /**
   * Tests checkCheckName. The given values are invalid.
   */
  @Test
  public void checkNameIsInvalid() {
    assertTrue(NAME_IS_EMPTY, util.checkCheckName(EMPTY_STRING).matches(IStatus.ERROR));
    assertTrue(QUALIFIED_NAME, util.checkCheckName("Check.name").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER, util.checkCheckName(",checkname").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_DOLLAR, util.checkCatalogName("client$check").matches(IStatus.ERROR));
  }

  /**
   * Tests checkCheckName. The given values are discouraged.
   */
  @Test
  public void checkNameIsDiscouraged() {
    assertTrue(STARTS_WITH_LOWER_CASE, util.checkCheckName("checkname").matches(IStatus.WARNING));
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCheckName("checkName").matches(IStatus.WARNING));

  }

  /**
   * Tests checkCategoryName. The given values are valid.
   */
  @Test
  public void categoryNameIsValid() {
    assertTrue(STARTS_WITH_UPPERCASE_LETTER, util.checkCategoryName("Categoryname").isOK());
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCategoryName("CategoryName").isOK());
  }

  /**
   * Tests checkCategoryName. The given values are invalid.
   */
  @Test
  public void categoryNameIsInvalid() {
    assertTrue(NAME_IS_EMPTY, util.checkCategoryName(EMPTY_STRING).matches(IStatus.ERROR));
    assertTrue(QUALIFIED_NAME, util.checkCategoryName("Category.name").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER, util.checkCategoryName("%categoryname").matches(IStatus.ERROR));
    assertTrue(CONTAINS_ILLEGAL_CHARACTER_DOLLAR, util.checkCatalogName("Client$Category").matches(IStatus.ERROR));
  }

  /**
   * Tests checkCategoryName. The given values are discouraged.
   */
  @Test
  public void categoryNameIsDiscouraged() {
    assertTrue(STARTS_WITH_LOWER_CASE, util.checkCategoryName("categoryname").matches(IStatus.WARNING));
    assertTrue(STARTS_WITH_AND_CONTAINS_UPPERCASE_LETTERS, util.checkCategoryName("categoryName").matches(IStatus.WARNING));
  }
}
