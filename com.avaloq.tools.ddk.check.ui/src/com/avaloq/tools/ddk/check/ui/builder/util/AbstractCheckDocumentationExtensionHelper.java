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
package com.avaloq.tools.ddk.check.ui.builder.util;

/**
 * Abstract class for documentation extension point utility classes.
 * Defines common elements of documentation extension point utility classes used by the Check builder participant.
 */
public abstract class AbstractCheckDocumentationExtensionHelper extends AbstractCheckExtensionHelper {

  public static final String GENERATE_DOCUMENTATION_EXTENSION_PREFERENCE = "generateDocumentationExtension";

  @Override
  protected String getExtensionEnablementPreferenceName() {
    return GENERATE_DOCUMENTATION_EXTENSION_PREFERENCE;
  }

}
