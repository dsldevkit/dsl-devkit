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
package com.avaloq.tools.ddk.xtextspy.internal;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.avaloq.tools.ddk.xtextspy.XtextSpyModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "com.avaloq.tools.ddk.xtextspy"; //$NON-NLS-1$

  // The shared instance
  private static Activator plugin;

  private Injector injector;

  @Override
  @SuppressWarnings({"PMD.SignatureDeclareThrowsException"})
  public void start(final BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    injector = Guice.createInjector(getModule());
  }

  @Override
  @SuppressWarnings({"PMD.NullAssignment", "PMD.SignatureDeclareThrowsException"})
  public void stop(final BundleContext context) throws Exception {
    plugin = null;
    injector = null;
    super.stop(context);
  }

  public Injector getInjector() {
    return injector;
  }

  /**
   * Gets the Guice module.
   * 
   * @return XtextSpyModule
   */
  protected XtextSpyModule getModule() {
    return new XtextSpyModule(this);
  }

  /**
   * Returns the shared instance.
   * 
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given
   * plug-in relative path.
   * 
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(final String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }
}
