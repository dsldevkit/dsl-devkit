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
package com.avaloq.tools.ddk.check.generator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.google.inject.Guice;


/**
 * Generates keys and values used by issue codes and check configurations.
 */
public final class CheckPropertiesGenerator {

  private CheckPropertiesGenerator() {
    // no instantiation
  }

  /**
   * Gets the numeric severity value for a given severity name.
   *
   * @param severityName
   *          the severity name
   * @return the numeric severity value
   */
  public static int severityValue(final String severityName) {
    return SeverityKind.getByName(severityName.toLowerCase()).getValue(); // NOPMD
  }

  /**
   * Gets the severity key for a given check.
   *
   * @param check
   *          the check
   * @return the severity key
   */
  public static String checkSeverityKey(final Check check) {
    return issueCodeKeyPrefix(check).toUpperCase() + "$SEVERITY";
  }

  /**
   * Gets the parameter key for a given formal parameter.
   *
   * @param parameter
   *          the parameter
   * @param check
   *          the check the parameter applies to
   * @return the parameter key
   */
  public static String parameterKey(final FormalParameter parameter, final Check check) {
    return parameterKey(parameter.getName(), issueCodeKeyPrefix(check));
  }

  /**
   * Gets the parameter key for a given formal parameter.
   *
   * @param parameter
   *          the parameter
   * @param issue
   *          the issue expression the parameter applies to
   * @return the parameter key
   */
  public static String parameterKey(final FormalParameter parameter, final XIssueExpression issue) {
    return parameterKey(parameter.getName(), issueCodeKeyPrefix(issue));
  }

  /**
   * Gets the parameter key for a parameter and issue code.
   *
   * @param parameterName
   *          the parameter name
   * @param issueCode
   *          the issue code the parameter applies to
   * @return the parameter key
   */
  public static String parameterKey(final String parameterName, final String issueCode) {
    return (issueCode + '.' + parameterName).toUpperCase() + "$PARAMETER";
  }

  /**
   * Returns the the runtime issue code value as the key prefix for all object keys.
   *
   * @param object
   *          the issue expression
   * @return the key prefix, in this case the runtime issue code value
   */
  public static String issueCodeKeyPrefix(final EObject object) {
    CheckGeneratorExtensions helper = Guice.createInjector(new com.avaloq.tools.ddk.check.CheckRuntimeModule()).getInstance(CheckGeneratorExtensions.class);
    String issueName = helper.issueCode(object);
    return helper.issueCodeValue(EcoreUtil2.getContainerOfType(object, CheckCatalog.class), issueName);
  }

}
