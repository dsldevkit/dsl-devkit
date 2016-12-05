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
package com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring.messages"; //$NON-NLS-1$

  public static String SemanticHighlightingConfiguration_0;
  public static String SemanticHighlightingConfiguration_1;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {} // NOPMD
}

