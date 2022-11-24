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
package com.avaloq.tools.ddk.checkcfg.ui.quickfix;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.checkcfg.ui.quickfix.messages"; //$NON-NLS-1$

  public static String CheckCfgQuickfixProvider_CORRECT_SEVERITY_DESCN;
  public static String CheckCfgQuickfixProvider_CORRECT_SEVERITY_LABEL;
  public static String CheckCfgQuickfixProvider_CORRECT_SEVERITY_OF_FINAL_CHECK_DESCN;
  public static String CheckCfgQuickfixProvider_CORRECT_SEVERITY_OF_FINAL_CHECK_LABEL;
  public static String CheckCfgQuickfixProvider_REMOVE_CONFIGURED_PARAM_DESCN;
  public static String CheckCfgQuickfixProvider_REMOVE_CONFIGURED_PARAM_LABEL;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CATALOG_DESCN;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CATALOG_LABEL;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CHECK_DESCN;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CHECK_LABEL;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_PARAM_DESCN;
  public static String CheckCfgQuickfixProvider_REMOVE_DUPLICATE_PARAM_LABEL;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}

