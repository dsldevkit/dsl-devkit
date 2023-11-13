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
package com.avaloq.tools.ddk.xtext.ui;

import org.eclipse.osgi.util.NLS;


/**
 * Internationalization: messages.
 */
public final class Messages extends NLS {
  private static final String BUNDLE_NAME = "om.avaloq.tools.ddk.xtext.ui"; //$NON-NLS-1$
  // CHECKSTYLE:OFF
  public static String AbstractAcfLabelProvider_NULL;
  public static String AbstractAcfLabelProvider_NAME_FALLBACK;

  // CHECKSTYLE:ON
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
