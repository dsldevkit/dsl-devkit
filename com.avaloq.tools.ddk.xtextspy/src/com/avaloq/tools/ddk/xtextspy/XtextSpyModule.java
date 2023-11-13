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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.PluginImageHelper;

import com.google.inject.Binder;
import com.google.inject.Module;


/**
 * Guice Module for XtextSpy.
 */
public class XtextSpyModule extends AbstractGenericModule implements Module {

  private final AbstractUIPlugin plugin;

  public XtextSpyModule(final AbstractUIPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Bind IEditorUtils.
   * 
   * @return the ASMDEditorUtils
   */
  public Class<? extends IEditorUtils> bindEditorUtils() {
    return ASMDEditorUtils.class;
  }

  /**
   * Bind ILabelProvider.
   * 
   * @return XtextSpyLabelProvider
   */
  public Class<? extends ILabelProvider> bindLabelProvider() {
    return XtextSpyLabelProvider.class;
  }

  /**
   * Bind IImageHelper.
   * 
   * @return PluginImageHelper
   */
  public Class<? extends IImageHelper> bindImageHelper() {
    return PluginImageHelper.class;
  }

  @Override
  public void configure(final Binder binder) {
    super.configure(binder);
    binder.bind(AbstractUIPlugin.class).toInstance(plugin);
  }

}
