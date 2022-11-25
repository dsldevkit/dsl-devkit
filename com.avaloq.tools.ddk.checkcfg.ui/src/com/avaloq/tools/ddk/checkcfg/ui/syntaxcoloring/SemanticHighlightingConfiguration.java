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
package com.avaloq.tools.ddk.checkcfg.ui.syntaxcoloring;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;


/**
 * Configures semantic highlighting for Check Configurations.
 */
public class SemanticHighlightingConfiguration extends DefaultHighlightingConfiguration {

  // NOTE: will never be called, is currently disabled (see UI module)

  public static final String DISABLED_VALUE_ID = "DisabledValue"; //$NON-NLS-1$

  private static final RGB RGB_DISABLED = new RGB(128, 128, 128);

  @Override
  public void configure(final IHighlightingConfigurationAcceptor acceptor) {
    super.configure(acceptor);
    acceptor.acceptDefaultHighlighting(DISABLED_VALUE_ID, Messages.SemanticHighlightingConfiguration_0, disabledValue());
  }

  /**
   * Gets a text style for disabled values (e.g. disabled Check).
   * 
   * @return the text style
   */
  public TextStyle disabledValue() {
    TextStyle textStyle = defaultTextStyle().copy();
    textStyle.setColor(RGB_DISABLED);
    return textStyle;
  }

}
