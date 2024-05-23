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
package com.avaloq.tools.ddk.xtext.ui.validation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

import com.google.common.base.Strings;


/**
 * This class implements extensions for the plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>.
 * Extension objects are created when the extension's XML contribution is parsed but before
 * the Java extension classes are resolved.
 *
 * @see AbstractValidElementBase
 * @see ValidExtensionPointManager
 */
public final class ValidExtension {

  private static final String FAILED_TO_LOAD_EXTENSION = "Failed to load {0} named '{1}' in (2}."; //$NON-NLS-1$

  private static final String XML_TOP_ELEMENT_NAME = "valid"; //$NON-NLS-1$

  private final IExtension extension;

  private ValidExtension(final IExtension e) {
    this.extension = e;
  }

  /**
   * @return the id that tells one extension instance from another
   */
  public String getExtensionId() {
    return extension.getSimpleIdentifier();
  }

  /**
   * @return the top-level elements of this extension point
   */
  public ValidElement[] getTopLevelElements() {
    ValidElement[] topLevelElements = null;
    if (topLevelElements == null) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      List<ValidElement> elements = new ArrayList<ValidElement>();
      for (IConfigurationElement ce : configurationElements) {
        if (XML_TOP_ELEMENT_NAME.equals(ce.getName())) {
          ValidElement e = new ValidElement(ce);
          elements.add(e);
        }
      }
      topLevelElements = elements.toArray(new ValidElement[elements.size()]);
    }
    return topLevelElements;
  }

  /**
   * Parses the extension's XML data and returns a proxy that now contains the static (=XML) attribute
   * values of the extension.
   *
   * @param extension
   *          the extension to parse
   * @return <i>null</i> on failure to parse
   */
  static ValidExtension parseExtension(final IExtension extension) {
    String sid = extension.getSimpleIdentifier();
    String name = extension.getLabel();
    String id = !Strings.isNullOrEmpty(sid.trim()) ? sid : (!Strings.isNullOrEmpty(name.trim()) ? name : "Unknown"); //$NON-NLS-1$
    ValidExtension validExtension = new ValidExtension(extension);
    try {
      validExtension.getTopLevelElements(); // force parsing of first level of element containment
      return validExtension;
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      final String message = MessageFormat.format(FAILED_TO_LOAD_EXTENSION, ValidExtension.class.getSimpleName(), id, extension.getNamespaceIdentifier());
      AbstractValidElementBase.logException(e, message);
      return null;
    }
  }
}
