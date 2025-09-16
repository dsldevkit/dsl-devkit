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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * The extension point utility class for preference initializers. Intended to be used by the Check builder participant.
 */
public final class CheckPreferencesExtensionHelper extends AbstractCheckExtensionHelper implements ICheckExtensionHelper {

  public static final String GENERATE_PREFERENCES_EXTENSION_PREFERENCE = "generatePreferencesExtension";

  private static final String CHECK_CONFIGURATION_PREFS = "com.avaloq.tools.targetdefinition.check.configuration.CheckConfigurationPreferences";
  // See also com.avaloq.tools.targetdefinition.ui.check.preferences.CheckConfigurationProjectScope.SCOPE
  private static final String CHECK_CONFIGURATION_SCOPE = "checkcfgproject";

  public static final String PREFERENCES_EXTENSION_POINT_ID = "org.eclipse.core.runtime.preferences";

  private static final String EXTENSION_NAME_PREFIX = "Preferences extension for ";
  private static final String SCOPE_ELEMENT_TAG = "scope";
  private static final String INITIALIZER_ELEMENT_TAG = "initializer";
  private static final String CLASS_ATTRIBUTE_TAG = "class";
  private static final String NAME_ATTRIBUTE_TAG = "name";

  @Override
  protected String getExtensionEnablementPreferenceName() {
    return GENERATE_PREFERENCES_EXTENSION_PREFERENCE;
  }

  /**
   * Updates the preferences extension with current values as provided by given check catalog.
   *
   * @param catalog
   *          the check catalog
   * @param extension
   *          the extension to be updated
   * @param elements
   *          the elements
   * @throws CoreException
   *           a core exception
   */
  @Override
  protected void doUpdateExtension(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) throws CoreException {
    // The preference extension must define an initializer and a scope plugin element
    extension.setName(getExtensionPointName(catalog));
    updateInitializerElement(catalog, Iterables.get(elements, 0));
    if (Iterables.size(elements) == 2) {
      updateScopeElement(Iterables.get(elements, 1));
    }
  }

  /**
   * Updates the initializer plug-in element.
   *
   * @param catalog
   *          the catalog
   * @param element
   *          the element
   * @return the i plugin element
   * @throws CoreException
   *           the core exception
   */
  private IPluginElement updateInitializerElement(final CheckCatalog catalog, final IPluginElement element) throws CoreException {
    element.setName(INITIALIZER_ELEMENT_TAG);
    element.setAttribute(CLASS_ATTRIBUTE_TAG, getTargetClassName(catalog));
    return element;
  }

  /**
   * Updates the scope plug-in element.
   *
   * @param element
   *          the element
   * @return the i plugin element
   * @throws CoreException
   *           the core exception
   */
  private IPluginElement updateScopeElement(final IPluginElement element) throws CoreException {
    element.setName(SCOPE_ELEMENT_TAG);
    element.setAttribute(NAME_ATTRIBUTE_TAG, CHECK_CONFIGURATION_SCOPE);
    element.setAttribute(CLASS_ATTRIBUTE_TAG, CHECK_CONFIGURATION_PREFS);
    return element;
  }

  /**
   * Checks if the initializer class value of given plugin extension element matches with that calculated using given check catalog.
   *
   * @param element
   *          the plugin extension element
   * @param initializerClassName
   *          the initializer class FQN
   * @return {@code true}, if given plugin extension element is up to date
   */
  private boolean initializerClassMatches(final IPluginElement element, final String initializerClassName) {
    return element != null && Strings.equal(element.getAttribute(CLASS_ATTRIBUTE_TAG).getValue(), initializerClassName);
  }

  /**
   * Checks if the name of given plugin extension matches with that calculated using given check catalog.
   *
   * @param extension
   *          the plugin extension
   * @param catalog
   *          the check catalog
   * @return {@code true}, if given plugin extension element is up to date
   */
  @Override
  protected boolean extensionNameMatches(final IPluginExtension extension, final CheckCatalog catalog) {
    return Strings.equal(extension.getName(), EXTENSION_NAME_PREFIX + catalog.getName());
  }

  @Override
  public Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    List<IPluginElement> result = Lists.newArrayList();
    result.add(updateInitializerElement(catalog, extension.getModel().getFactory().createElement(extension)));

    // if (CheckProjectUtil.typeExists(catalog, CHECK_CONFIGURATION_PREFS)) {
    // TODO should only use the check configuration scope if corresponding class is available
    result.add(updateScopeElement(extension.getModel().getFactory().createElement(extension)));
    // }

    return result;
  }

  @Override
  public String getExtensionPointId() {
    return PREFERENCES_EXTENSION_POINT_ID;
  }

  @Override
  public String getExtensionPointName(final CheckCatalog catalog) {
    return EXTENSION_NAME_PREFIX + catalog.getName();
  }

  /**
   * Gets the target class name based on the package path of given check catalog.
   *
   * @param catalog
   *          the check catalog
   * @return the target class FQN
   */
  private String getTargetClassName(final CheckCatalog catalog) {
    return getFromServiceProvider(CheckGeneratorNaming.class, catalog).qualifiedPreferenceInitializerClassName(catalog);
  }

  /**
   * Gets the initializer element.
   *
   * @param elements
   *          the elements
   * @return the initializer element
   */
  private IPluginElement getInitializerElement(final Iterable<IPluginElement> elements) {
    for (IPluginElement e : elements) {
      if (INITIALIZER_ELEMENT_TAG.equals(e.getName())) {
        return e;
      }
    }
    return null;
  }

  @Override
  public boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    // @Format-Off
    return PREFERENCES_EXTENSION_POINT_ID.equals(extension.getPoint())
        && (!extensionNameMatches(extension, catalog)
        || Iterables.size(elements) != 2
        || !initializerClassMatches(getInitializerElement(elements), getTargetClassName(catalog)));
    // @Format-On
  }

}
