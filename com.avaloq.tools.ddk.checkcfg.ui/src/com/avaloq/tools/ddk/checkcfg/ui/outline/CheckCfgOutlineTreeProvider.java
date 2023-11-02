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
package com.avaloq.tools.ddk.checkcfg.ui.outline;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.label.StylerFactory;

import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring.SemanticHighlightingConfiguration;
import com.google.inject.Inject;


/**
 * Configures the Check Configuration default outline structure.
 */
public class CheckCfgOutlineTreeProvider extends DefaultOutlineTreeProvider {

  @Inject
  private SemanticHighlightingConfiguration semanticHighlightingConfiguration;

  @Inject
  private StylerFactory stylerFactory;

  // CHECKSTYLE:OFF

  /**
   * Gets the outline node text for a configured catalog. Tries to resolve linked catalog's <em>name</em>.
   * If the reference is not resolvable, an appropriate unresolved label is returned.
   * <p>
   * Uses configured highlighting configuration for unresolved values.
   * </p>
   * 
   * @param configuredCatalog
   *          the configured catalog
   * @return a styled string
   */
  // CHECKSTYLE:OFF
  protected Object _text(final ConfiguredCatalog configuredCatalog) {
    // CHECKSTYLE:ON
    StyledString ruleText;
    if (configuredCatalog.getCatalog() == null || configuredCatalog.getCatalog().eIsProxy()) {
      // Unresolved reference
      ruleText = new StyledString("<Unresolved catalog>", //
      stylerFactory.createXtextStyleAdapterStyler(semanticHighlightingConfiguration.errorTextStyle()));
    } else {
      // Default
      ruleText = new StyledString(safeName(configuredCatalog.getCatalog().getName()));
    }
    return ruleText;
  }

  /**
   * Gets the outline node text for a configured check. Tries to resolve linked check's <em>label</em>.
   * If the reference is not resolvable, an appropriate unresolved label is returned.
   * <p>
   * Uses configured highlighting configuration for disabled and unresolved values.
   * </p>
   * 
   * @param configuredCheck
   *          the configured check
   * @return a styled string
   */
  // CHECKSTYLE:OFF
  protected Object _text(final ConfiguredCheck configuredCheck) {
    // CHECKSTYLE:ON
    StyledString ruleText;
    if (configuredCheck.getCheck() == null || configuredCheck.getCheck().eIsProxy()) {
      // Unresolved reference
      ruleText = new StyledString("<Unresolved check>", //
      stylerFactory.createXtextStyleAdapterStyler(semanticHighlightingConfiguration.errorTextStyle()));
    } else if (configuredCheck.getSeverity().equals(SeverityKind.IGNORE)) {
      // Disabled
      ruleText = new StyledString(safeName(configuredCheck.getCheck().getLabel()), //
      stylerFactory.createXtextStyleAdapterStyler(semanticHighlightingConfiguration.disabledValue()));
    } else {
      // Default
      ruleText = new StyledString(safeName(configuredCheck.getCheck().getLabel()));
    }
    return ruleText;
  }

  /**
   * Returns a "safe name" for a string, being either given string itself or a non-null value. Does not return {@code null}.
   * 
   * @param s
   *          the string to be checked
   * @return the safe string
   */
  protected String safeName(final String s) {
    return (s == null) ? "<unnamed>" : s;
  }

  /**
   * A configured check shall be displayed as a leaf node in the outline view.
   * 
   * @param modelElement
   *          the model element
   * @return {@code true}
   */
  // CHECKSTYLE:OFF
  protected boolean _isLeaf(final ConfiguredCheck modelElement) {
    // CHECKSTYLE:ON
    return true;
  }

}

