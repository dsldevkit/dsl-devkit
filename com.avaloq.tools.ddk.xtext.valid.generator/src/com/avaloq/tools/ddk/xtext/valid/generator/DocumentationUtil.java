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
package com.avaloq.tools.ddk.xtext.valid.generator;

import com.avaloq.tools.ddk.xtext.ui.validation.preferences.AbstractValidPreferencePage;


/**
 * Utility classes for the generation of Check Documentation.
 */
public class DocumentationUtil {

  protected DocumentationUtil() {
    // to keep Checkstyle happy..
  }

  /**
   * Returns a human-readable version of the given message in which all binding placeholders are replaced by "...".
   * 
   * @param message
   *          an error message
   * @return the human-readable version
   */
  public static String toPrintableMessage(final String message) {
    return AbstractValidPreferencePage.replace(message, "\\{[0-9]+\\}", "..."); //$NON-NLS-1$ //$NON-NLS-2$
  }

}

