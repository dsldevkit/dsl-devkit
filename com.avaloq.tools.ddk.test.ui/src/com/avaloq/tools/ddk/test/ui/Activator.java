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
package com.avaloq.tools.ddk.test.ui;

import java.net.URL;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The Class Activator.
 */
@SuppressWarnings({"all", "PMD"})
public class Activator extends AbstractUIPlugin {

  // The shared instance.
  /** The plugin. */
  private static Activator plugin;

  @Override
  public void start(final BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(final BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance.
   *
   * @return the default
   */
  public static Activator getDefault() {
    return plugin;
  }

  /**
   * Gets the install url.
   *
   * @return the install URL
   */
  public static URL getInstallURL() {
    return getDefault().getBundle().getEntry("/");
  }
}
