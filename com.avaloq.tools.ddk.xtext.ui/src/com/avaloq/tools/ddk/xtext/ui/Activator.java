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
package com.avaloq.tools.ddk.xtext.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

  /** The Constant PLUGIN_ID. */
  public static final String PLUGIN_ID = "com.avaloq.tools.ddk.xtext.ui"; //$NON-NLS-1$

  /** The shared instance. */
  private static Activator plugin;

  /** {@inheritDoc} */
  @Override
  public void start(final BundleContext context) throws Exception { // NOPMD (override)
    super.start(context);
    plugin = this;
  }

  /** {@inheritDoc} */
  @Override
  public void stop(final BundleContext context) throws Exception { // NOPMD (override)
    super.stop(context);
    plugin = null; // NOPMD:NullAssignment
  }

  /**
   * Returns the shared instance.
   *
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

}
