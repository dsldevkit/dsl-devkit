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
package com.avaloq.tools.ddk.checkcfg.validation;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.checkcfg.validation.messages"; //$NON-NLS-1$

  public static String CheckCfgJavaValidator_DISABLED_CHECK_NOT_CONFIGURED;
  public static String CheckCfgJavaValidator_REFERENCED_CHECK_NOT_CONFIGURED;
  public static String CheckCfgJavaValidator_FINAL_CATALOG_NOT_CONFIGURABLE;
  public static String CheckCfgJavaValidator_FINAL_CHECK_NOT_CONFIGURABLE;
  public static String CheckCfgJavaValidator_CONFIGURED_PARAM_EQUALS_DEFAULT;
  public static String CheckCfgJavaValidator_DUPLICATE_CATALOG_CONFIGURATION;
  public static String CheckCfgJavaValidator_DUPLICATE_CHECK_CONFIGURATION;
  public static String CheckCfgJavaValidator_DUPLICATE_PARAMETER_CONFIGURATION;
  public static String CheckCfgJavaValidator_SEVERITY_NOT_ALLOWED;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}

