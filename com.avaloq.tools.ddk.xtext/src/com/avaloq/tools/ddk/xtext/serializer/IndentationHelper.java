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
package com.avaloq.tools.ddk.xtext.serializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.xtext.nodemodel.ICompositeNode;


/**
 * Utility class to help determining indentation in node models.
 */
public final class IndentationHelper {
  private static final Pattern PATTERN = Pattern.compile("^[ \\t]+"); //$NON-NLS-1$

  private IndentationHelper() {
    //
  }

  /**
   * Gets the last indentation found before given offset.
   *
   * @param root
   *          the root
   * @param fromOffset
   *          the offset is expected to be normalized with respect to root, i.e. fromOffset 0 <=> root.getOffset().
   * @return the indentation or NULL
   */
  public static String getIndentation(final ICompositeNode root, final int fromOffset) {
    if (fromOffset <= 0) {
      return null;
    }
    String nodeText = root.getText();
    if (nodeText == null || fromOffset > nodeText.length()) {
      return null;
    }
    int lastNLIndex = nodeText.lastIndexOf('\n', fromOffset);
    StringBuffer result = new StringBuffer();
    Matcher m = PATTERN.matcher(nodeText.substring(lastNLIndex));
    if (m.find()) {
      result.append(m.group());
    }
    return result.toString();
  }

}
