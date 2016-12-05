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
    return key(check).toUpperCase() + "$SEVERITY";
  }

  /**
   * Gets the parameter key for a given formal parameter.
   * 
   * @param param
   *          the parameter
   * @return the parameter severity key
   */
  public static String parameterSeverityKey(final FormalParameter param) {
    return key(param).toUpperCase() + "$PARAMETER";
  }

  /**
   * Gets the key for a given check.
   * 
   * @param check
   *          the check
   * @return the key for given check
   */
  public static String key(final Check check) {
    return keyPrefix(check) + '.' + check.getName();
  }

  /**
   * Gets the key for a given parameter.
   * 
   * @param param
   *          the parameter
   * @return the key
   */
  public static String key(final FormalParameter param) {
    Check check = (Check) param.eContainer();
    return keyPrefix(param) + '.' + check.getName() + '.' + param.getName();
  }

  /**
   * Returns the key prefix for all object keys.
   * 
   * @param object
   *          the object
   * @return the key prefix
   */
  private static String keyPrefix(final EObject object) {
    CheckCatalog catalog = EcoreUtil2.getContainerOfType(object, CheckCatalog.class);
    if (catalog == null || catalog.eIsProxy()) {
      return "UNDEFINED_CATALOG";
    } else {
      return catalog.getPackageName() + '.' + catalog.getName();
    }
  }

}

