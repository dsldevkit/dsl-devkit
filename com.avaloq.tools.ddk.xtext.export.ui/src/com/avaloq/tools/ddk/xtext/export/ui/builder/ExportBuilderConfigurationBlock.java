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
package com.avaloq.tools.ddk.xtext.export.ui.builder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.builder.preferences.BuilderConfigurationBlock;


/**
 * UI for configuring the Export compiler.
 */
@SuppressWarnings("restriction")
public class ExportBuilderConfigurationBlock extends BuilderConfigurationBlock {

  @Override
  protected void createGeneralSectionItems(final Composite composite) {
    super.createGeneralSectionItems(composite);
    addCheckBox(composite, "Disable the builder participant on workspace build (skip regenerating generated artifacts)", ExportBuilderPreferenceAccess.PREF_DISABLE_BUILDER_PARTICIPANT, BOOLEAN_VALUES, 0); //$NON-NLS-1$
  }

}
