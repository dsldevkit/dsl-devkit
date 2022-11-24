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
package com.avaloq.tools.ddk.check.ui.builder.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.check.check.CheckCatalog;


/**
 * Defines operations provided by check extension utility classes.
 */
public interface ICheckExtensionHelper {

  /**
   * Updates a given extension.
   * 
   * @param catalog
   *          the catalog
   * @param extension
   *          the extension
   * @throws CoreException
   *           the core exception
   */
  void updateExtension(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException;

  /**
   * Gets the extension point id.
   * 
   * @return the extension point id
   */
  String getExtensionPointId();

  /**
   * Gets the extension point name.
   * 
   * @param catalog
   *          the catalog
   * @return the extension point name
   */
  String getExtensionPointName(final CheckCatalog catalog);

  /**
   * Gets the plugin elements belonging to an extension of a given check catalog.
   * 
   * @param catalog
   *          the catalog
   * @param extension
   *          the extension
   * @return the plugin elements
   * @throws CoreException
   *           the core exception
   */
  Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException;

  /**
   * Adds an extension of given type to plugin base.
   * 
   * @param base
   *          the base
   * @param catalog
   *          the catalog
   * @param type
   *          the type
   * @param extensionId
   *          the extension id
   * @return the new plugin extension or {@code null} if no extension created
   * @throws CoreException
   *           the core exception
   */
  IPluginExtension addExtensionToPluginBase(final IPluginModelBase base, final CheckCatalog catalog, final ExtensionType type, final String extensionId) throws CoreException;

  /**
   * Removes the extension from plugin base. Called when a whole catalog has been removed.
   * 
   * @param base
   *          the base
   * @param extension
   *          the extension
   * @param obj
   *          the obj
   * @param type
   *          the type
   * @throws CoreException
   *           the core exception
   */
  void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final IEObjectDescription obj, final ExtensionType type) throws CoreException;

  /**
   * Removes the extension from plugin base. Called when a catalog has become invalid (no longer validates).
   * 
   * @param base
   *          the base
   * @param extension
   *          the extension
   * @param catalog
   *          the catalog
   * @param type
   *          the type
   * @throws CoreException
   *           the core exception
   */
  void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final CheckCatalog catalog, final ExtensionType type) throws CoreException;
}

