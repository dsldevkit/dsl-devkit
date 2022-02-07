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
package com.avaloq.tools.ddk.checkcfg.ui.internal;

/**
 * Configures a custom Check Configuration plug-in activator.
 */
public class Activator extends CheckcfgActivator {

  /**
   * Gets the Check Configuration plug-in ID.
   *
   * @return the plugin ID
   */
  public static String getPluginId() {
    return CheckcfgActivator.getInstance().getBundle().getSymbolicName();
  }

}
