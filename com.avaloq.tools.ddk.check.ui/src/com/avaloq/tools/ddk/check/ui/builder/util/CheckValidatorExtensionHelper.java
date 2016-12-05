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
package com.avaloq.tools.ddk.check.ui.builder.util;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * The extension point utility class for Check validators. Intended to be used by the Check builder participant.
 */
public final class CheckValidatorExtensionHelper extends AbstractCheckExtensionHelper implements ICheckExtensionHelper {

  @Inject
  private CheckProjectUtil projectUtil;

  public static final String CHECK_EXTENSION_POINT_ID = "com.avaloq.tools.ddk.check.runtime.core.check";

  private static final String VALIDATOR_ELEMENT_TAG = "validator";
  private static final String CATALOG_ELEMENT_TAG = "catalog";

  /** {@inheritDoc} */
  public String getExtensionPointId() {
    return CHECK_EXTENSION_POINT_ID;
  }

  /** {@inheritDoc} */
  public String getExtensionPointName(final CheckCatalog catalog) {
    return "Check extension for " + catalog.getName();
  }

  /** {@inheritDoc} */
  public Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    IPluginElement element = extension.getModel().getFactory().createElement(extension);
    return Collections.singletonList(updateOnlyPluginElement(catalog, element));
  }

  /**
   * Updates the plugin element.
   * 
   * @param catalog
   *          the catalog
   * @param element
   *          the element
   * @return the i plugin element
   * @throws CoreException
   *           the core exception
   */
  private IPluginElement updateOnlyPluginElement(final CheckCatalog catalog, final IPluginElement element) throws CoreException {
    element.setName(VALIDATOR_ELEMENT_TAG);
    element.setAttribute(TARGET_CLASS_ELEMENT_TAG, getTargetClassName(catalog));
    element.setAttribute(LANGUAGE_ELEMENT_TAG, catalog.getGrammar().getName());
    element.setAttribute(CATALOG_ELEMENT_TAG, getCatalogResourceName(catalog));
    return element;
  }

  @Override
  protected void doUpdateExtension(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) throws CoreException {
    // The validator extension must define a validator plugin element
    if (Iterables.size(elements) == 1) {
      extension.setName(getExtensionPointName(catalog));
      updateOnlyPluginElement(catalog, Iterables.get(elements, 0));
    }
  }

  /**
   * Gets the catalog resource name. Is used to publish the actual model in the extension.
   * <p>
   * Must return a resource path, e.g. <code>ch/paranor/bank/DdicCatalog.check</code>.
   * </p>
   * 
   * @param catalog
   *          the catalog
   * @return the catalog resource name
   */
  private String getCatalogResourceName(final CheckCatalog catalog) {
    return projectUtil.getCatalogPluginPath(catalog);
  }

  /**
   * Gets the target class name based on the package path of given check catalog.
   * 
   * @param catalog
   *          the check catalog
   * @return the target class FQN
   */
  private String getTargetClassName(final CheckCatalog catalog) {
    return getFromServiceProvider(CheckGeneratorNaming.class, catalog).qualifiedValidatorClassName(catalog);
  }

  @Override
  public boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    // CHECKSTYLE:OFF
    // @Format-Off
    final boolean result = extension.getPoint().equals(CHECK_EXTENSION_POINT_ID) 
        && (!extensionNameMatches(extension, catalog)
        || Iterables.size(elements) != 1
        || !targetClassMatches(Iterables.get(elements, 0), getTargetClassName(catalog))
        || !languageNameMatches(Iterables.get(elements, 0), catalog.getGrammar().getName())
    );
    return result;
    // @Format-On
    // CHECKSTYLE:ON
  }

}

