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
package com.avaloq.tools.ddk.xtext.formatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * Generic executor that detects and applies format-off\on directives.
 */
public class DefaultFormattingDirectiveExecutor implements IFormattingDirectiveExecutor {

  /** Matcher to find formatting directives in the comments. */
  private static final Matcher FORMAT_MATCHER = Pattern.compile("@format-(off|on)(\\s|\\*)", Pattern.CASE_INSENSITIVE).matcher(StringUtils.EMPTY); //$NON-NLS-1$
  private static final String OFF = "off"; //$NON-NLS-1$
  private static final String ON = "on"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final String commentText, final ExtendedFormattingConfigBasedStream stream) {
    FORMAT_MATCHER.reset(commentText);
    String formatCommand = null;
    while (FORMAT_MATCHER.find()) {
      // Looking for the last instance of '@format-xxx' directive in the comment
      formatCommand = FORMAT_MATCHER.group(1);
    }

    if (OFF.equalsIgnoreCase(formatCommand)) {
      stream.setFormattingActive(false);
    } else if (ON.equalsIgnoreCase(formatCommand)) {
      stream.setFormattingActive(true);
    }
  }

}
