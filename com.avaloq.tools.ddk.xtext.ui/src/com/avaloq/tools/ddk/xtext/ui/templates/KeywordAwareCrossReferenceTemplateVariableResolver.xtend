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

package com.avaloq.tools.ddk.xtext.ui.templates

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.jface.text.templates.TemplateVariable
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter
import org.eclipse.xtext.ui.editor.templates.CrossReferenceTemplateVariableResolver
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext

/**
 * {@inheritDoc}
 *
 * Keywords are prefixed with escape characters.
 */
@Singleton
public class KeywordAwareCrossReferenceTemplateVariableResolver extends CrossReferenceTemplateVariableResolver {

  @Inject
  QualifiedNameValueConverter qualifiedNameValueConverter;

/**
 * {@inheritDoc}
 *
 * Keywords are prefixed with escape characters.
 */
  override resolveValues(TemplateVariable variable, XtextTemplateContext castedContext) {
    val names = super.resolveValues(variable, castedContext)

    // Prefix keywords with escape characters
    return names.map[qualifiedNameValueConverter.toString(it)]
  }

}
