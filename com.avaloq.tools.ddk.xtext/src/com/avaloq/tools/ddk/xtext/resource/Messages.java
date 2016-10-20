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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.osgi.util.NLS;


// CHECKSTYLE:OFF
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.resource.messages"; //$NON-NLS-1$
  public static String AbstractSdkResourceDescription_OBJECT_DESCRIPTION_FAILURE;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    super();
  }
}
