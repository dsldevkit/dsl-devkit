/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.xtext.xbase.ui.builder.XbaseBuilderConfigurationBlock;

import com.avaloq.tools.ddk.xtext.ui.BuilderParticipantMasterSwitch;


/**
 * UI for configuring the Format compiler. Adds the workspace-wide DDK generation master switch on top of the stock options.
 */
@SuppressWarnings("restriction")
public class FormatBuilderConfigurationBlock extends XbaseBuilderConfigurationBlock {

  private BuilderParticipantMasterSwitch masterSwitch;

  @Override
  protected Control doCreateContents(final Composite parent) {
    if (getProject() != null) {
      return super.doCreateContents(parent); // project property page: the master switch is workspace-wide only
    }
    masterSwitch = new BuilderParticipantMasterSwitch(parent, super::doCreateContents);
    return masterSwitch.getControl();
  }

  @Override
  public boolean performOk() {
    final boolean rebuildRequired = masterSwitch != null && masterSwitch.save();
    final boolean result = super.performOk();
    if (rebuildRequired) {
      getBuildJob(getProject()).schedule(); // re-enabling generation: rebuild to catch up on changes made while it was off
    }
    return result;
  }

  @Override
  public void performDefaults() {
    if (masterSwitch != null) {
      masterSwitch.restoreDefaults();
    }
    super.performDefaults();
  }

}
