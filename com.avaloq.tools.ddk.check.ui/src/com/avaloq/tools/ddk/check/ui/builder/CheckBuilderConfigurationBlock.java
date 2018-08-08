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

package com.avaloq.tools.ddk.check.ui.builder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.xbase.ui.builder.XbaseBuilderConfigurationBlock;


/**
 * UI for configuring Check compiler.
 */
@SuppressWarnings("restriction")
public class CheckBuilderConfigurationBlock extends XbaseBuilderConfigurationBlock {

  @Override
  protected void createGeneralSectionItems(final Composite composite) {
    super.createGeneralSectionItems(composite);
    addCheckBox(composite, "Generate as DSL internal checks (not SCA plugin)", CheckBuilderPreferenceAccess.PREF_GENERATE_LANGUAGE_INTERNAL_CHECKS, BOOLEAN_VALUES, 0); //$NON-NLS-1$
  }

}
