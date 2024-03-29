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
package com.avaloq.tools.ddk.xtext.export.validation;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.export.validation.messages"; //$NON-NLS-1$
  public static String ExportJavaValidator_DUPLICATE_INTERFACE;
  public static String ExportJavaValidator_DUPLICATE_EXPORT;
  public static String ExportJavaValidator_DUPLICATE_FEATURE_EXPORT;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
