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
package com.avaloq.tools.ddk.check.ui.hover;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverProvider;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.google.inject.Inject;


/**
 * A sad and empty implementation of an hover provider.
 */

// CHECKSTYLE:OFF
@SuppressWarnings("nls")
public class CheckHoverProvider extends XbaseHoverProvider {

  @Inject
  XbaseInterpreter interpreter;

  @Inject
  ILabelProvider labelProvider;

  @Inject
  CheckGeneratorExtensions generatorExtension;

  @Override
  protected String getFirstLine(final EObject object) {
    // TODO remove and instead implement IEObjectHoverDocumentationProvider
    if (object instanceof Check) {
      Check check = (Check) object;
      return "<b>" + check.getDefaultSeverity().getName() + " " + toHtml(labelProvider.getText(check)) + "</b>"
          + ((check.getId() == null) ? "" : " \"" + check.getLabel() + "\"");
    } else if (object instanceof CheckCatalog) {
      CheckCatalog catalog = (CheckCatalog) object;
      return "<b>" + toHtml(labelProvider.getText(catalog)) + "</b>" + ((catalog.getName() == null) ? "" : " \"" + catalog.getName() + "\"");
    }
    return "";
  }

  private String toHtml(final String plain) {
    return plain.replace("<", "&lt;").replace(">", "&gt;");
  }

  @Override
  protected String getHoverInfoAsHtml(final EObject object) {
    if (!hasHover(object) || object.eIsProxy()) { // TODO object will be proxy in CA when referencing CheckCatalogs!
      return null;
    }

    if (object instanceof Check) {
      Check check = (Check) object;
      // a dispatcher would be nice
      StringBuffer buffer = new StringBuffer();
      buffer.append(getFirstLine(check));
      appendSection(buffer, "Description", generatorExtension.formatDescription(check.getDescription()));
      appendSection(buffer, "Message", generatorExtension.replacePlaceholder(check.getMessage()));
      return buffer.toString();
    } else if (object instanceof CheckCatalog) {
      CheckCatalog catalog = (CheckCatalog) object;
      StringBuffer buffer = new StringBuffer();
      buffer.append(getFirstLine(catalog));
      appendSection(buffer, "Description", generatorExtension.formatDescription(catalog.getDescription()));
      return buffer.toString();
    }
    return super.getHoverInfoAsHtml(object);

  }

  private void appendSection(final StringBuffer buffer, final String title, final String content) {
    if (title != null && title.length() > 0) {
      buffer.append("<p><b>").append(title).append(":</b></p>");
    }
    if (content != null && content.length() > 0) {
      buffer.append("<p>").append(content).append("</p>");
    }
  }
}
