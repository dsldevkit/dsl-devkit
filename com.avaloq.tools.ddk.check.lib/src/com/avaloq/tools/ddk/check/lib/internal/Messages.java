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
package com.avaloq.tools.ddk.check.lib.internal;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.check.lib.internal.messages"; //$NON-NLS-1$
  public static String Index_NullQueryData;
  public static String Index_NullArgumentInQuery;
  public static String Index_NullKeyInEntry;
  public static String Index_NullQueryContext;
  public static String PerResourceCache_NotAnXtextResource;
  public static String PerResourceCache_NullContext;
  public static String PerResourceCache_NullKey;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
