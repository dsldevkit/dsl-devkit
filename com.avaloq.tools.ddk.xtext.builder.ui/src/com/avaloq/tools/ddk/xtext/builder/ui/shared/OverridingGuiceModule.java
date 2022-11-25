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

package com.avaloq.tools.ddk.xtext.builder.ui.shared;

import java.util.Objects;
import java.util.stream.Stream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.service.AbstractGenericModule;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.util.Modules;


/**
 * This module slots into the org.eclipse.xtext.ui.shared.overridingGuiceModule extension point
 * to enable multiple overriding modules via com.avaloq.tools.ddk.xtext.builder.ui.overridingGuiceModule instead.
 */
public class OverridingGuiceModule extends AbstractGenericModule {

  private static final Logger LOGGER = LogManager.getLogger(OverridingGuiceModule.class);

  public static final String OVERRIDING_GUICE_MODULE_EXTENSION_POINT = "com.avaloq.tools.ddk.xtext.builder.ui.overridingGuiceModule"; //$NON-NLS-1$
  public static final String OVERRIDING_GUICE_MODULE_EXTENSION_CLASS = "class"; //$NON-NLS-1$
  public static final String OVERRIDING_GUICE_MODULE_EXTENSION_PRIORITY = "priority"; //$NON-NLS-1$

  @Override
  public void configure(final Binder binder) {
    Stream.of(Platform.getExtensionRegistry().getConfigurationElementsFor(OVERRIDING_GUICE_MODULE_EXTENSION_POINT)).sorted((a, b) -> {
      int aPriority = Integer.parseInt(a.getAttribute(OVERRIDING_GUICE_MODULE_EXTENSION_PRIORITY));
      int bPriority = Integer.parseInt(b.getAttribute(OVERRIDING_GUICE_MODULE_EXTENSION_PRIORITY));
      return aPriority - bPriority;
    }).map(element -> {
      try {
        return (Module) element.createExecutableExtension(OVERRIDING_GUICE_MODULE_EXTENSION_CLASS);
      } catch (CoreException e) {
        LOGGER.log(Level.ERROR, "Overriding guice module from " + element.getContributor() + " could not be instatiated and has been skipped.", e); //$NON-NLS-1$ //$NON-NLS-2$
        return null;
      }
    }).filter(Objects::nonNull).reduce((dummy) -> {
    }, (previous, next) -> {
      LOGGER.log(Level.DEBUG, "Overriding guice module with " + next.getClass().getName()); //$NON-NLS-1$
      return Modules.override(previous).with(next);
    }).configure(binder);
  }

}
