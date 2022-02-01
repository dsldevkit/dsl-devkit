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
package com.avaloq.tools.ddk.check.runtime.internal;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.emf.ecore.plugin.RegistryReader;

import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptorRegistry;
import com.google.common.collect.Lists;


/**
 * The abstract registry reader for check and check quickfix extension point registers.
 */
abstract class AbstractCheckRegistryReader extends RegistryReader {

  AbstractCheckRegistryReader(final IExtensionRegistry pluginRegistry, final String pluginID, final String extensionPointID) {
    super(pluginRegistry, pluginID, extensionPointID);
  }

  /**
   * Gets the top-level configuration element name for check or check quickfix extensions. Should be either of the following:
   * <ul>
   * <li>{@code provider}
   * <li>{@code validator}
   * </ul>
   *
   * @return the top-level element name
   */
  protected abstract String topLevelElementName();

  /**
   * Creates a new check implementation descriptor.
   *
   * @param element
   *          the configuration element
   * @param attClass
   *          the implementing class
   * @return the descriptor
   */
  protected abstract ICheckImplDescriptor newDescriptor(final IConfigurationElement element, final String attClass);

  /**
   * Get the registry for managing registered providers.
   *
   * @return
   *         registry
   */
  protected abstract ICheckImplDescriptorRegistry getRegistry();

  /**
   * Gets the logger.
   *
   * @return the logger
   */
  protected abstract Logger getLogger();

  /** {@inheritDoc} */
  @Override
  protected void logError(final IConfigurationElement element, final String text) {
    IExtension extension = element.getDeclaringExtension();
    getLogger().error("Plugin " + extension.getContributor().getName() + ", extension " + extension.getExtensionPointUniqueIdentifier()); //$NON-NLS-1$ //$NON-NLS-2$
    getLogger().error(text);
  }

  /**
   * Gets the value for a given key attribute in the language validator map.
   *
   * @param element
   *          the element
   * @return the value for key attribute
   */
  protected String getValueForKeyAttribute(final IConfigurationElement element) {
    return element.getAttribute(getKeyAttribute());
  }

  /**
   * Gets the key attribute for mappings in the language validator map. Should correspond to an Xtext Grammar name.
   *
   * @return the grammar name
   */
  protected String getKeyAttribute() {
    return "language"; //$NON-NLS-1$
  }

  /**
   * Gets the name of the plugin element attribute to be used when creating a new descriptor.
   *
   * @return the attribute
   */
  abstract String getAttribute();

  /** {@inheritDoc} */
  @Override
  protected boolean readElement(final IConfigurationElement element, final boolean add) {
    if (element.getName().equals(topLevelElementName())) {
      String languageIdentifier = getValueForKeyAttribute(element);
      if (languageIdentifier == null) {
        return false;
      } else if (element.getAttribute(getAttribute()) == null) {
        logMissingAttribute(element, getAttribute());
      } else if (add) {
        getRegistry().registerCatalogDescriptor(languageIdentifier, newDescriptor(element, getAttribute()));
        return true;
      } else {
        // Clean-up registry by removing invalid providers
        List<ICheckImplDescriptor> toRemove = Lists.newLinkedList();
        for (ICheckImplDescriptor o : getRegistry().getDescriptors(languageIdentifier)) {
          ICheckImplDescriptor descr = o;
          if (!descr.getElement().isValid()) {
            toRemove.add(descr);
          }
        }
        for (ICheckImplDescriptor descr : toRemove) {
          getRegistry().removeLanguageDescriptor(languageIdentifier, descr);
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Extract plug-in name (by cutting off class name).
   *
   * @param targetClass
   *          the target class as a qualified name
   * @return the name plug-in
   */
  protected String extractPluginName(final String targetClass) {
    return targetClass.substring(0, targetClass.lastIndexOf(".")); //$NON-NLS-1$ NOPMD
  }

}
