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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Abstract class for extension point utility classes. Defines operations required by the Check builder participant.
 */
@SuppressWarnings("nls")
public abstract class AbstractCheckExtensionHelper implements ICheckExtensionHelper {

  protected static final String LANGUAGE_ELEMENT_TAG = "language";
  protected static final String TARGET_CLASS_ELEMENT_TAG = "targetClass";

  @Inject
  private CheckProjectHelper projectHelper;

  /**
   * Creates the extension. Note that the <code>id</code> attribute is optional and thus not created.
   *
   * @param catalog
   *          the catalog for which to crate the extension content.
   * @param extensionId
   *          the extension id, may be <code>null</code>
   * @param pluginModel
   *          the model
   * @return the plugin extension
   * @throws CoreException
   *           a core exception
   */
  protected IPluginExtension createExtension(final CheckCatalog catalog, final String extensionId, final IPluginModelBase pluginModel) throws CoreException {
    IPluginExtension newExtension = pluginModel.getFactory().createExtension();

    newExtension.setPoint(getExtensionPointId());
    if (extensionId != null) {
      newExtension.setId(extensionId);
    }
    if (getExtensionPointName(catalog) != null) {
      newExtension.setName(getExtensionPointName(catalog));
    }

    // Add contents to the extension
    for (final IPluginObject p : getElements(catalog, newExtension)) {
      newExtension.add(p);
    }

    return newExtension;
  }

  /**
   * Checks if the language name defined in given plugin extension element matches with given language.
   *
   * @param element
   *          the plugin extension element
   * @param expectedLanguageName
   *          the expected language name
   * @return {@code true} if the language name as defined in the plugin extension element is up to date
   */
  protected boolean languageNameMatches(final IPluginElement element, final String expectedLanguageName) {
    return element.getAttribute(LANGUAGE_ELEMENT_TAG) != null && Strings.equal(element.getAttribute(LANGUAGE_ELEMENT_TAG).getValue(), expectedLanguageName);
  }

  /**
   * Checks if the target class name defined in given plugin extension element matches with given target class name.
   *
   * @param element
   *          the plugin extension element
   * @param expectedTargetClassName
   *          the expected target class name
   * @return {@code true} if the target class name as defined in the plugin extension element is up to date
   */
  protected boolean targetClassMatches(final IPluginElement element, final String expectedTargetClassName) {
    return Strings.equal(element.getAttribute(TARGET_CLASS_ELEMENT_TAG).getValue(), expectedTargetClassName);
  }

  /**
   * Gets an instance from the service provider.
   *
   * @param <T>
   *          the generic type
   * @param type
   *          the instance type
   * @param object
   *          the instance
   * @return the instance
   */
  protected <T> T getFromServiceProvider(final Class<T> type, final EObject object) {
    URI uri = object.eIsProxy() ? org.eclipse.emf.ecore.util.EcoreUtil.getURI(object) : object.eResource().getURI();
    return getFromServiceProvider(type, uri);
  }

  /**
   * Gets an instance from the service provider with a given URI.
   *
   * @param <T>
   *          the generic type
   * @param type
   *          the instance type
   * @param uri
   *          the URI of the source object
   * @return the instance
   */
  protected <T> T getFromServiceProvider(final Class<T> type, final URI uri) {
    IResourceServiceProvider serviceProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(uri);
    return serviceProvider.get(type);
  }

  /**
   * Checks if is extension update required.
   *
   * @param catalog
   *          the catalog
   * @param extension
   *          the extension
   * @param elements
   *          the elements
   * @return true, if is extension update required {@link #targetClassMatches(IPluginElement, String)} are based on given check catalog and their values may
   *         have changed before the builder participant kicks
   *         in.
   */
  protected boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    return extension.getPoint().equals(getExtensionPointId()); // if points are different, given extension must not be updated
  }

  /**
   * Updates a given extension to values calculated using given check catalog.
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
  protected abstract void doUpdateExtension(CheckCatalog catalog, IPluginExtension extension, Iterable<IPluginElement> elements) throws CoreException;

  /**
   * Updates a given extension to values calculated using given check catalog.
   *
   * @param catalog
   *          the check catalog
   * @param extension
   *          the plugin extension
   * @throws CoreException
   *           a core exception
   */
  @Override
  public void updateExtension(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    Iterable<IPluginElement> elements = Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
    if (isExtensionUpdateRequired(catalog, extension, elements)) {
      doUpdateExtension(catalog, extension, elements);
    }
  }

  /**
   * Checks if the name of given plugin extension extension matches with that calculated based on given check catalog.
   *
   * @param extension
   *          the plugin extension
   * @param catalog
   *          the check catalog
   * @return {@code true} if the extension name as defined in the plugin extension is up to date
   */
  protected boolean extensionNameMatches(final IPluginExtension extension, final CheckCatalog catalog) {
    return Strings.equal(extension.getName(), getExtensionPointName(catalog));
  }

  @Override
  public IPluginExtension addExtensionToPluginBase(final IPluginModelBase base, final CheckCatalog catalog, final ExtensionType type, final String extensionId) throws CoreException {
    if (isExtensionEnabled(base, catalog, type, extensionId)) {
      IPluginExtension newExtension = createExtension(catalog, extensionId, base);
      base.getPluginBase().add(newExtension);
      return newExtension;
    }
    return null;
  }

  /**
   * Determines if the extension point should be enabled.
   *
   * @param base
   *          the base
   * @param catalog
   *          the catalog
   * @param type
   *          the type
   * @param extensionId
   *          the extension id
   * @return true, if the extension should be enabled
   */
  protected boolean isExtensionEnabled(final IPluginModelBase base, final CheckCatalog catalog, final ExtensionType type, final String extensionId) {
    if (type == ExtensionType.MARKERHELP) {
      return false; // disabled until https://bugs.eclipse.org/bugs/show_bug.cgi?id=369534 is fixed
    }
    String projectSetting = projectHelper.getProjectPreference(base.getUnderlyingResource().getProject(), getExtensionEnablementPreferenceName());
    return projectSetting == null || Boolean.parseBoolean(projectSetting);
  }

  /**
   * Must be implemented by subclasses to provide the name of the preference that enables or disables the extension point.
   *
   * @return the preference name, never {@code null}
   */
  protected abstract String getExtensionEnablementPreferenceName();

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final CheckCatalog catalog, final ExtensionType type) throws CoreException {
    base.getPluginBase().remove(extension); // remove the extension
  }

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final IEObjectDescription obj, final ExtensionType type) throws CoreException {
    base.getPluginBase().remove(extension); // remove the extension
  }

}
