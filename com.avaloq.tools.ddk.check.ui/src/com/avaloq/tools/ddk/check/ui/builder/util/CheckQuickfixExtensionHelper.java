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

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * The extension point utility class for Check quickfixes. Intended to be used by the Check builder participant.
 */
public final class CheckQuickfixExtensionHelper extends AbstractCheckExtensionHelper {

  public static final String QUICKFIX_EXTENSION_POINT_ID = "com.avaloq.tools.ddk.check.runtime.core.checkquickfix";

  public static final String GENERATE_QUICKFIX_EXTENSION_PREFERENCE = "generateQuickFixExtension";

  private static final String TARGET_CLASS_ELEMENT_TAG = "targetClass";
  private static final String LANGUAGE_ELEMENT_TAG = "language";
  private static final String PROVIDER_ELEMENT_TAG = "provider";

  @Inject
  private CheckProjectHelper projectHelper;

  @Override
  protected String getExtensionEnablementPreferenceName() {
    return GENERATE_QUICKFIX_EXTENSION_PREFERENCE;
  }

  @Override
  public String getExtensionPointId() {
    return QUICKFIX_EXTENSION_POINT_ID;
  }

  @Override
  public String getExtensionPointName(final CheckCatalog catalog) {
    return "Check quickfix extension for " + catalog.getName();
  }

  @Override
  public Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    IPluginElement element = extension.getModel().getFactory().createElement(extension);
    return Collections.singletonList(updateOnlyPluginElement(catalog, element));
  }

  @Override
  protected boolean isExtensionEnabled(final IPluginModelBase base, final CheckCatalog catalog, final ExtensionType type, final String extensionId) {
    IProject project = base.getUnderlyingResource().getProject();
    return super.isExtensionEnabled(base, catalog, type, extensionId) && projectHelper.isJavaFilePresent(project, getTargetClassName(catalog));
  }

  /**
   * Updates the plugin element. The {@code name}, {@code target class} and {@code language} attributes of the plugin
   * extension are set to values based on given check catalog.
   *
   * @param catalog
   *          the catalog
   * @param element
   *          the element
   * @return the updated plugin element
   * @throws CoreException
   *           a core exception
   */
  private IPluginElement updateOnlyPluginElement(final CheckCatalog catalog, final IPluginElement element) throws CoreException {
    element.setName(PROVIDER_ELEMENT_TAG);
    element.setAttribute(TARGET_CLASS_ELEMENT_TAG, getTargetClassName(catalog));
    if (catalog.getGrammar() != null) {
      element.setAttribute(LANGUAGE_ELEMENT_TAG, catalog.getGrammar().getName());
    } else if (element.getAttribute(LANGUAGE_ELEMENT_TAG) != null) {
      element.setAttribute(LANGUAGE_ELEMENT_TAG, null);
    }
    return element;
  }

  /**
   * Updates {@code name}, {@code target class} and {@code language} of given extension to values
   * calculated using given check catalog.
   *
   * @param catalog
   *          the check catalog
   * @param extension
   *          the plugin extension
   * @param elements
   *          the elements
   * @throws CoreException
   *           a core exception
   */
  @Override
  protected void doUpdateExtension(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) throws CoreException {
    // The quickfix extension must define a provider plugin element
    if (Iterables.size(elements) == 1) {
      extension.setName(getExtensionPointName(catalog));
      updateOnlyPluginElement(catalog, Iterables.get(elements, 0));
    }
  }

  /**
   * Gets the target class name based on the package path of given check catalog.
   *
   * @param catalog
   *          the check catalog
   * @return the target class FQN
   */
  private String getTargetClassName(final CheckCatalog catalog) {
    return getFromServiceProvider(CheckGeneratorNaming.class, catalog).qualifiedQuickfixClassName(catalog);
  }

  @Override
  public boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    // CHECKSTYLE:OFF
    // @Format-Off
    final boolean result = QUICKFIX_EXTENSION_POINT_ID.equals(extension.getPoint())
        && (!extensionNameMatches(extension, catalog)
        || Iterables.size(elements) != 1
        || !targetClassMatches(Iterables.get(elements, 0), getTargetClassName(catalog))
        || catalog.getGrammar() == null && Iterables.get(elements, 0).getAttribute(LANGUAGE_ELEMENT_TAG) != null
        || catalog.getGrammar() != null && !languageNameMatches(Iterables.get(elements, 0), catalog.getGrammar().getName()));
    return result;
    // @Format-On
    // CHECKSTYLE:ON
  }

}
