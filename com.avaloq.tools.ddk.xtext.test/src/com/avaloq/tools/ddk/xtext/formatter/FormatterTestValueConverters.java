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
package com.avaloq.tools.ddk.xtext.formatter;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;


public class FormatterTestValueConverters extends DefaultTerminalConverters {

  @ValueConverter(rule = "FQN")
  // CHECKSTYLE:OFF
  public IValueConverter<String> FQN() { // NOPMD
    // CHECKSTYLE:ON
    return new AbstractNullSafeConverter<String>() {
      @Override
      protected String internalToValue(final String string, final INode node) {
        if (!string.equals(string.trim())) {
          throw new RuntimeException(); // NOPMD
        }
        StringBuffer b = new StringBuffer();
        for (ILeafNode l : node.getLeafNodes()) {
          if (!l.isHidden()) {
            b.append(l.getText());
          }
        }
        return b.toString();
      }

      @Override
      protected String internalToString(final String value) {
        return value;
      }
    };
  }
}
