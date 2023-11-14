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

import org.eclipse.core.runtime.IConfigurationElement;


/**
 * This class implements data access objects for the element type <code>&lt;valid&gt;</code> of
 * the plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>.
 * Element objects are created when the extension's XML contribution is parsed but before
 * any Java extension class is resolved and instantiated.
 *
 * @see ValidExtensionPointManager
 */
public final class ValidElement extends AbstractValidElementBase {

  private final String language;

  protected ValidElement(final IConfigurationElement ce) {
    super(ce);
    language = getAttribute(ce, LANGUAGE, false);
  }

  /**
   * Name of the Xtext language for which these validations rules are defined.
   *
   * @return extension's static <code>&lt;language&gt;</code> property (from extension XML)
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Return all child elements of this element that conform to the hierarchy of the
   * XML schema that goes with this extension point.
   * <p>
   * Returns these kinds of objects:
   * <ul>
   * <li>{@link CategoryElement}</li>
   * </ul>
   * Their order is not specified here.
   *
   * @return the child elements of this element
   */
  @Override
  public AbstractValidElementBase[] getChildElements() { // NOPMD, adequate documentation not found in parent
    return super.getChildElements();
  }

  @Override
  protected AbstractValidElementBase createChildElement(final IConfigurationElement ce) {
    if (CATEGORY.equals(ce.getName())) {
      return new CategoryElement(ce);
    }
    return null;
  }
}
