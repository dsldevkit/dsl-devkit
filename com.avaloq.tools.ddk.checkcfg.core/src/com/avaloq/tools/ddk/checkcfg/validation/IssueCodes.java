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
package com.avaloq.tools.ddk.checkcfg.validation;

/**
 * Issue codes used for the Check Configuration Java validator.
 */
public final class IssueCodes {
  private static final String ISSUE_CODE_PREFIX = "com.avaloq.tools.ddk.checkcfg.validation.IssueCodes.";

  public static final String DISABLED_CHECK_NOT_CONFIGURED = ISSUE_CODE_PREFIX + "configuration_for_disabled_check";
  public static final String REFERENCED_CHECK_NOT_CONFIGURED = ISSUE_CODE_PREFIX + "missing_configuration_for_check";
  public static final String FINAL_CATALOG_NOT_CONFIGURABLE = ISSUE_CODE_PREFIX + "final_catalog_not_configurable";
  public static final String FINAL_CHECK_NOT_CONFIGURABLE = ISSUE_CODE_PREFIX + "final_check_not_configurable";
  public static final String FORMAL_PARAMETER_MUST_BE_INTEGER = ISSUE_CODE_PREFIX + "formal_param_must_be_integer";
  public static final String CONFIGURED_PARAM_EQUALS_DEFAULT = ISSUE_CODE_PREFIX + "configured_param_equals_default";
  public static final String DUPLICATE_CATALOG_CONFIGURATION = ISSUE_CODE_PREFIX + "duplicate_catalog_configuration";
  public static final String DUPLICATE_CHECK_CONFIGURATION = ISSUE_CODE_PREFIX + "duplicate_check_configuration";
  public static final String UNKNOWN_LANGUAGE = ISSUE_CODE_PREFIX + "unknown_language";
  public static final String DUPLICATE_PARAMETER_CONFIGURATION = ISSUE_CODE_PREFIX + "duplicate_parameter_configuration";
  public static final String SEVERITY_NOT_ALLOWED = ISSUE_CODE_PREFIX + "severity_not_allowed";

  private IssueCodes() {
    // No instantiation.
  }

}

