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
package com.avaloq.tools.ddk.check.validation; // NOPMD

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.check.validation.messages"; //$NON-NLS-1$

  public static String CheckJavaValidator_CATALOG_NAME_IS_LOWER;
  public static String CheckJavaValidator_CHECK_IMPLICITLY_FINAL;
  public static String CheckJavaValidator_CIRCULAR_DEPENDENCY_IN_INCLUDED_CATALOGS;
  public static String CheckJavaValidator_CONTEXT_TYPE_IS_ECLASS;
  public static String CheckJavaValidator_CONTEXT_TYPES_UNIQUE;
  public static String CheckJavaValidator_DEFAULT_SEVERITY_NOT_IN_RANGE;
  public static String CheckJavaValidator_DOCUMENTATION_MISSING;
  public static String CheckJavaValidator_DUPLICATE_PARAMETER_DEFINITION;
  public static String CheckJavaValidator_EMPTY_CATALOG_NAME;
  public static String CheckJavaValidator_EMPTY_PACKAGE_NAME;
  public static String CheckJavaValidator_EMPTY_PROJECT_NAME;
  public static String CheckJavaValidator_GUARDS_COME_FIRST;
  public static String CheckJavaValidator_GUARDS_DEPRECATED;
  public static String CheckJavaValidator_ID_MISSING;
  public static String CheckJavaValidator_ILLEGAL_PACKAGE_NAME;
  public static String CheckJavaValidator_ILLEGAL_PROJECT_NAME;
  public static String CheckJavaValidator_ILLEGAL_SEVERITY_RANGE_ORDER;
  public static String CheckJavaValidator_IMPLICIT_ISSUED_CHECK;
  public static String CheckJavaValidator_INVALID_GRAMMAR_OF_INCLUDED_CATALOG;
  public static String CheckJavaValidator_ISSUE_REFERS_TO_CHECK;
  public static String CheckJavaValidator_ISSUE_REFERS_TO_IMPLICIT_CHECK;
  public static String CheckJavaValidator_ISSUE_REFERS_TO_EXPLICIT_CHECK;
  public static String CheckJavaValidator_ISSUED_BINDINGS;
  public static String CheckJavaValidator_MARKER_INDEX_MANY;
  public static String CheckJavaValidator_MISSING_ISSUE_EXPRESSION;
  public static String CheckJavaValidator_NO_EXPRESSIONS_IN_CONSTRAINT;
  public static String CheckJavaValidator_NO_GUARD_AFTER_ISSUE_IN_CONSTRAINT;
  public static String CheckJavaValidator_NO_RETURN_IN_CHECK_IMPL;
  public static String CheckJavaValidator_NO_SEVERITY_RANGE_FOR_FINAL_CHECK;
  public static String CheckJavaValidator_PACKAGE_NAME_IS_UPPER;
  public static String CheckJavaValidator_PROJECT_EXISTS;
  public static String CheckJavaValidator_PROJECT_NAME_IS_UPPER;

  public static String CheckJavaValidator_EMPTY_NAME;
  public static String CheckJavaValidator_ILLEGAL_START;
  public static String CheckJavaValidator_ENDS_WITH_DOT;
  public static String CheckJavaValidator_SEQUENCE_ERROR;
  public static String CheckJavaValidator_ILLEGAL_CHARACTER;
  public static String CheckJavaValidator_CONTAINS_UPPERCASE;
  public static String CheckJavaValidator_EXISTS;
  public static String CheckJavaValidator_STARTS_WITH_LOWERCASE_ERROR;
  public static String CheckJavaValidator_STARTS_WITH_LOWERCASE_WARNING;
  public static String CheckJavaValidator_WHITE_SPACE;

  public static String CheckJavaValidator_CHECK_NAME_STATUS;
  public static String CheckJavaValidator_CATALOG_NAME_STATUS;
  public static String CheckJavaValidator_PROJECT_NAME_STATUS;
  public static String CheckJavaValidator_PACKAGE_NAME_STATUS;
  public static String CheckJavaValidator_CATEGORY_NAME_STATUS;

  public static String CheckJavaValidator_FormalParameterAllowedBaseTypes;

  public static String CheckJavaValidator_FormalParameterAllowedListTypes;

  public static String CheckJavaValidator_FormalParameterType_Incompatibility;

  public static String ApiAccessChecks_IMPORT_NON_PUBLIC_API;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
