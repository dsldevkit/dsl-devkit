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
 * This class implements data access objects for the element type <code>&lt;rule&gt;</code> of
 * the plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>.
 * Element objects are created when the extension's XML contribution is parsed but before
 * any Java extension class is resolved and instantiated.
 *
 * @see ValidExtensionPointManager
 */
public final class RuleElement extends AbstractValidElementBase {

  private final Boolean optional;
  private final String name;
  private final String label;
  private final String description;
  private final String message;
  private final String severity;
  private final String evaluationMode;

  protected RuleElement(final IConfigurationElement ce) {
    super(ce);
    final String bOptional = getAttribute(ce, OPTIONAL, false);
    optional = bOptional == null ? null : Boolean.valueOf(bOptional);
    name = getAttribute(ce, NAME, false);
    label = getAttribute(ce, LABEL, false);
    description = getAttribute(ce, DESCRIPTION, true);
    message = getAttribute(ce, MESSAGE, false);
    severity = getAttribute(ce, SEVERITY, false);
    evaluationMode = getAttribute(ce, EVALUATION_MODE, false);
  }

  /**
   * True if the rule is optional, false if it is mandatory.
   *
   * @return extension's static <code>&lt;optional&gt;</code> property (from extension XML)
   */
  public Boolean isOptional() {
    return optional;
  }

  /**
   * Name of the rule.
   *
   * @return extension's static <code>&lt;name&gt;</code> property (from extension XML)
   */
  public String getName() {
    return name;
  }

  /**
   * Label for the rule.
   *
   * @return extension's static <code>&lt;label&gt;</code> property (from extension XML)
   */
  public String getLabel() {
    return label;
  }

  /**
   * Description of the rule.
   * This is an optional attribute; null is returned if not set.
   *
   * @return extension's static <code>&lt;description&gt;</code> property (from extension XML)
   *         or null
   */
  public String getDescription() {
    return description;
  }

  /**
   * Message for the rule.
   *
   * @return extension's static <code>&lt;message&gt;</code> property (from extension XML)
   */
  public String getMessage() {
    return message;
  }

  /**
   * Either error or warning.
   *
   * @return extension's static <code>&lt;severity&gt;</code> property (from extension XML)
   */
  public String getSeverity() {
    return severity;
  }

  /**
   * Either fast, normal or expensive.
   *
   * @return extension's static <code>&lt;evaluationMode&gt;</code> property (from extension XML)
   */
  public String getEvaluationMode() {
    return evaluationMode;
  }

  @Override
  protected AbstractValidElementBase createChildElement(final IConfigurationElement ce) {
    return null;
  }
}
