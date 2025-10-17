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
package com.avaloq.tools.ddk.check.ui.highlighting;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.xbase.ui.highlighting.XbaseHighlightingConfiguration;


/**
 * A Token Scanner that recognizes @SeverityRange tokens.
 */
@SuppressWarnings("nls")
public class CheckAntlrTokenToAttributeIdMapper extends DefaultAntlrTokenToAttributeIdMapper {
  // XbaseAntlrTokenToAttributeMapper is deprecated and empty anyway, so we may safely use the default mapper as super type here.

  @Override
  protected String calculateId(final String tokenName, final int tokenType) {
    if ("'SeverityRange'".equals(tokenName)) {
      return XbaseHighlightingConfiguration.ANNOTATION;
    } else if ("'@'".equals(tokenName)) {
      return XbaseHighlightingConfiguration.ANNOTATION;
    } else {
      return super.calculateId(tokenName, tokenType);
    }
  }
}
