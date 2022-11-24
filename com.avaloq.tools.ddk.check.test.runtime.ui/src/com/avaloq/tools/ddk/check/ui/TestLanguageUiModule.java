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
package com.avaloq.tools.ddk.check.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.avaloq.tools.ddk.check.runtime.configuration.CheckConfigurationStore;
import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStore;


/**
 * Use this class to register components to be used within the IDE.
 */
public class TestLanguageUiModule extends com.avaloq.tools.ddk.check.ui.AbstractTestLanguageUiModule {
  public TestLanguageUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }

  /**
   * Adds a binding to the context aware configuration store. This enables using the check configuration scope.
   * 
   * @return the check configuration store
   */
  public Class<? extends ICheckConfigurationStore> bindICheckConfigurationStore() {
    return CheckConfigurationStore.class;
  }
}

