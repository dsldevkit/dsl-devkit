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
package com.avaloq.tools.ddk.xtext.ui.validation;

import org.eclipse.core.runtime.IConfigurationElement;


/**
 * This class implements data access objects for the element type <code>&lt;category&gt;</code> of
 * the plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>.
 * Element objects are created when the extension's XML contribution is parsed but before
 * any Java extension class is resolved and instantiated.
 *
 * @see ValidExtensionPointManager
 */
public final class CategoryElement extends AbstractValidElementBase {

  private final String name;
  private final String label;
  private final String description;

  protected CategoryElement(final IConfigurationElement ce) {
    super(ce);
    name = getAttribute(ce, NAME, false);
    label = getAttribute(ce, LABEL, false);
    description = getAttribute(ce, DESCRIPTION, true);
  }

  /**
   * Name of the category.
   *
   * @return extension's static <code>&lt;name&gt;</code> property (from extension XML)
   */
  public String getName() {
    return name;
  }

  /**
   * Label for the category.
   *
   * @return extension's static <code>&lt;label&gt;</code> property (from extension XML)
   */
  public String getLabel() {
    return label;
  }

  /**
   * Description of the category.
   * This is an optional attribute; null is returned if not set.
   *
   * @return extension's static <code>&lt;description&gt;</code> property (from extension XML)
   *         or null
   */
  public String getDescription() {
    return description;
  }

  /**
   * Return all child elements of this element that conform to the hierarchy of the
   * XML schema that goes with this extension point.
   * <p>
   * Returns these kinds of objects:
   * <ul>
   * <li>{@link RuleElement}</li>
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
    if (ce.getName().equals(RULE)) {
      return new RuleElement(ce);
    }
    return null;
  }
}
