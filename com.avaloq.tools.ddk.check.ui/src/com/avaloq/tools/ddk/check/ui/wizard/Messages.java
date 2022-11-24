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
package com.avaloq.tools.ddk.check.ui.wizard;

import org.eclipse.osgi.util.NLS;


/**
 * The messages used for the wizards.
 */
// CHECKSTYLE:OFF
public final class Messages extends NLS {

  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.check.ui.wizard.messages"; //$NON-NLS-1$

  public static String PROJECT_WIZARD_TITLE;
  public static String CATALOG_WIZARD_TITLE;
  public static String PROJECT_WIZARD_WINDOW_TITLE;
  public static String CATALOG_WIZARD_WINDOW_TITLE;

  public static String CATALOG_WIZARD_DESCRIPTION;
  public static String PROJECT_WIZARD_DESCRIPTION;

  public static String CATALOG_FIELD_NAME_LABEL;
  public static String GRAMMAR_FIELD_NAME_LABEL;
  public static String PROJECT_NAME_LABEL;
  public static String PACKAGE_NAME_LABEL;

  public static String CHOOSE_GRAMMAR_ID;
  public static String EXTENSION_MODIFICATION_READONLY;
  public static String NO_CHARSET_DEFINED;
  public static String NO_PROJECT_DEFINED;

  public static String PROJECT_WIZARD_COMPLETE;
  public static String CATALOG_WIZARD_COMPLETE;

  static {
    // Initialize the resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Prevents instantiation.
   */
  private Messages() { // NOPMD
    // Do nothing.
  }

}
// CHECKSTYLE:ON

