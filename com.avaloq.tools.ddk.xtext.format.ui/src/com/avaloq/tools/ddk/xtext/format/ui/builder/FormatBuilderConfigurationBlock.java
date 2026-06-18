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

package com.avaloq.tools.ddk.xtext.format.ui.builder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.xbase.ui.builder.XbaseBuilderConfigurationBlock;


/**
 * UI for configuring the Format compiler.
 */
@SuppressWarnings("restriction")
public class FormatBuilderConfigurationBlock extends XbaseBuilderConfigurationBlock {

  @Override
  protected void createGeneralSectionItems(final Composite composite) {
    super.createGeneralSectionItems(composite);
    addCheckBox(composite, "Disable the builder participant on workspace build (skip regenerating generated artifacts)", FormatBuilderPreferenceAccess.PREF_DISABLE_BUILDER_PARTICIPANT, BOOLEAN_VALUES, 0); //$NON-NLS-1$
  }

}
