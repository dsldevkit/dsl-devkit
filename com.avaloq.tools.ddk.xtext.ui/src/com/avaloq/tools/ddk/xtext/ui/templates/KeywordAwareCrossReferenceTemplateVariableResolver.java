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

package com.avaloq.tools.ddk.xtext.ui.templates;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * {@inheritDoc}
 * Keywords are prefixed with escape characters.
 */
@Singleton
public class KeywordAwareCrossReferenceTemplateVariableResolver extends CrossReferenceTemplateVariableResolver {

  @Inject
  private QualifiedNameValueConverter qualifiedNameValueConverter;

  /**
   * {@inheritDoc}
   * Keywords are prefixed with escape characters.
   */
  @Override
  public List<String> resolveValues(final TemplateVariable variable, final XtextTemplateContext castedContext) {
    final List<String> names = super.resolveValues(variable, castedContext);

    // Prefix keywords with escape characters
    return names.stream().map(value -> qualifiedNameValueConverter.toString(value)).collect(Collectors.toList());
  }

}
