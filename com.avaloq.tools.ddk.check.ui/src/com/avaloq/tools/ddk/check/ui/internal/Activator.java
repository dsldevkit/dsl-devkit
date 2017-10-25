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
package com.avaloq.tools.ddk.check.ui.internal;

import com.avaloq.tools.ddk.xtext.ui.SharedUiModule;
import com.google.inject.Module;


/**
 * Plug-in Activator.
 */
public class Activator extends CheckActivator {

  @Override
  protected Module getSharedStateModule() {
    return new SharedUiModule();
  }

  /**
   * Gets the Check plug-in ID.
   *
   * @return the plugin ID
   */
  public static String getPluginId() {
    return CheckActivator.getInstance().getBundle().getSymbolicName();
  }

}
