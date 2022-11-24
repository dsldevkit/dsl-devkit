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
package com.avaloq.tools.ddk.check.ui.templates;

import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;

import com.avaloq.tools.ddk.xtext.ui.templates.ResourceNameTemplateVariableResolver;


/**
 * Used for adding custom template variable resolvers.
 */
public class CheckTemplateContextType extends XtextTemplateContextType {

  /** {@inheritDoc} */
  @Override
  protected void addDefaultTemplateVariables() {
    super.addDefaultTemplateVariables();
    addResolver(new ResourceNameTemplateVariableResolver());
  }

}
