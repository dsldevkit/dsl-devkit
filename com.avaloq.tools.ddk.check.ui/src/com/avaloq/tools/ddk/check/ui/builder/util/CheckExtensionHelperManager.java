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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


/**
 * Class that manages all check extension helpers, classes which create and maintain check catalog extensions.
 */
@Singleton
public class CheckExtensionHelperManager {

  private final Map<ExtensionType, ICheckExtensionHelper> helpers = Maps.newHashMapWithExpectedSize(5);

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private CheckProjectHelper projectUtil;

  @Inject
  public CheckExtensionHelperManager(final Injector injector) {
    final CheckValidatorExtensionHelper validatorHelper = new CheckValidatorExtensionHelper();
    final CheckContextsExtensionHelper contextsHelper = new CheckContextsExtensionHelper();
    final CheckMarkerHelpExtensionHelper markerHelpHelper = new CheckMarkerHelpExtensionHelper();
    final CheckPreferencesExtensionHelper preferencesHelper = new CheckPreferencesExtensionHelper();
    final CheckQuickfixExtensionHelper quickfixHelper = new CheckQuickfixExtensionHelper();
    final CheckTocExtensionHelper tocHelper = new CheckTocExtensionHelper();

    injector.injectMembers(validatorHelper);
    injector.injectMembers(contextsHelper);
    injector.injectMembers(markerHelpHelper);
    injector.injectMembers(preferencesHelper);
    injector.injectMembers(quickfixHelper);
    injector.injectMembers(tocHelper);

    helpers.put(ExtensionType.CONTEXTS, contextsHelper);
    helpers.put(ExtensionType.MARKERHELP, markerHelpHelper);
    helpers.put(ExtensionType.PREFERENCE_INITIALIZER, preferencesHelper);
    helpers.put(ExtensionType.QUICKFIX, quickfixHelper);
    helpers.put(ExtensionType.TOC, tocHelper);
    helpers.put(ExtensionType.VALIDATOR, validatorHelper);
  }

  private Iterable<ICheckExtensionHelper> getExtensionHelpers() {
    return helpers.values();
  }

  /**
   * Gets the extension helper.
   *
   * @param type
   *          the type
   * @return the extension helper
   */
  private ICheckExtensionHelper getExtensionHelper(final ExtensionType type) {
    return helpers.get(type);
  }

  /**
   * Gets the ID for the extension to be created in the {@code plugin.xml} file. The extension consists
   * of catalog package as a prefix and ends with the name of the catalog, all in lower case
   * letters. This means that multiple resources with equal name may exist, but only when not in the same
   * package. Another consequence is that name comparison of resources is case <em>insensitive</em>.
   * <p>
   * Note that every extension point supported has unique extension IDs. The following rules apply:
   * <ul>
   * <li>extensions for the validator point have a ".validator" suffix
   * <li>extensions for the quickfix point have a ".quickfix" suffix
   * <li>extensions for the preference initializer point have a ".preference" suffix
   * </ul>
   * If this operation is called using {@link ExtensionType#ALL}, an ID without this last segment consisting of "." followed by the suffix is returned. Note
   * also that {@link #findExtensions(IPluginModelBase, String, ExtensionType)} with {@link ExtensionType#ALL} matches all extensions for a given catalog.
   * </p>
   * <p>
   * Returns {@code null} if none could be determined.
   * </p>
   *
   * @param catalogName
   *          qualified check catalog name
   * @param typeId
   *          the type id
   * @return the extension ID for current Catalog or {@code null}
   */
  private String getExtensionId(final QualifiedName catalogName, final ExtensionType typeId) {
    QualifiedName result = catalogName;
    switch (typeId) {
    case VALIDATOR:
      result = catalogName.append("validator");
      break;
    case QUICKFIX:
      result = catalogName.append("quickfix");
      break;
    case PREFERENCE_INITIALIZER:
      result = catalogName.append("preference");
      break;
    case TOC:
      return null; // no ID
    case CONTEXTS:
      return null; // no ID
    case MARKERHELP:
      return null; // no ID
    default: // ALL
      break;
    }
    return result.toString().toLowerCase();
  }

  /**
   * Returns the {@link ExtensionType} for a given plugin extension.
   *
   * @param extension
   *          the extension
   * @return the extension type
   */
  private ExtensionType getExtensionType(final IPluginExtension extension) {
    String point = extension.getPoint();
    if (point.equals(CheckValidatorExtensionHelper.CHECK_EXTENSION_POINT_ID)) {
      return ExtensionType.VALIDATOR;
    } else if (point.equals(CheckPreferencesExtensionHelper.PREFERENCES_EXTENSION_POINT_ID)) {
      return ExtensionType.PREFERENCE_INITIALIZER;
    } else if (point.equals(CheckQuickfixExtensionHelper.QUICKFIX_EXTENSION_POINT_ID)) {
      return ExtensionType.QUICKFIX;
    } else if (point.equals(CheckTocExtensionHelper.TOC_EXTENSION_POINT_ID)) {
      return ExtensionType.TOC;
    } else if (point.equals(CheckContextsExtensionHelper.CONTEXTS_EXTENSION_POINT_ID)) {
      return ExtensionType.CONTEXTS;
    } else if (point.equals(CheckMarkerHelpExtensionHelper.MARKERHELP_EXTENSION_POINT_ID)) {
      return ExtensionType.MARKERHELP;
    }
    return null;
  }

  /**
   * Finds extensions of given {@link ExtensionType extension type} in given {@link IPluginModelBase plugin model}.
   * If {@code type} is {@link ExtensionType#ALL}, all extensions which may be mapped to {@link ExtensionType} are
   * returned. (They must have an ID pattern as defined in {@link #getExtensionId(CheckCatalog, ExtensionType)}).
   *
   * @param pluginModel
   *          the plugin in which to find the extensions
   * @param catalogName
   *          qualified catalog name
   * @param type
   *          the extension type to be looked up
   * @return a collection of extensions in the plugin.xml that match given extension type
   */
  private Collection<IPluginExtension> findExtensions(final IPluginModelBase pluginModel, final QualifiedName catalogName, final ExtensionType type) {
    IPluginExtension[] pluginExtensions = pluginModel.getPluginBase().getExtensions();
    final String extensionId = getExtensionId(catalogName, type);
    final String point = getExtensionHelper(type) == null ? null : getExtensionHelper(type).getExtensionPointId();

    return Collections2.filter(Arrays.asList(pluginExtensions), new Predicate<IPluginExtension>() {
      @Override
      public boolean apply(final IPluginExtension extension) {
        final String currentExtensionId = extension.getId();
        if (type == ExtensionType.ALL) {
          if (currentExtensionId == null) {
            return getAllExtensionPointIds().contains(extension.getPoint());
          } else {
            final int pos = currentExtensionId.lastIndexOf('.');
            return pos != -1 && getAllExtensionPointIds().contains(extension.getPoint()) && Strings.equal(currentExtensionId.substring(0, pos), extensionId);
          }
        } else {
          return Strings.equal(currentExtensionId, extensionId) && Strings.equal(extension.getPoint(), point);
        }
      }
    });
  }

  /**
   * Returns all Check extensions in the given plug-in model.
   *
   * @param pluginModel
   *          plug-in model, must not be {@code null}
   * @return all Check extensions
   * @see #getAllExtensionPointIds()
   */
  private Collection<IPluginExtension> findAllExtensions(final IPluginModelBase pluginModel) {
    Set<String> allExtensionPointIds = getAllExtensionPointIds();
    IPluginExtension[] pluginExtensions = pluginModel.getPluginBase().getExtensions();

    return Collections2.filter(Arrays.asList(pluginExtensions), new Predicate<IPluginExtension>() {
      @Override
      public boolean apply(final IPluginExtension extension) {
        return allExtensionPointIds.contains(extension.getPoint());
      }
    });
  }

  /**
   * Gets all extension point IDs.
   *
   * @return all extension point IDs
   */
  private Set<String> getAllExtensionPointIds() {
    return Sets.newHashSet(Iterables.transform(getExtensionHelpers(), new Function<ICheckExtensionHelper, String>() {
      @Override
      public String apply(final ICheckExtensionHelper input) {
        return input.getExtensionPointId();
      }
    }));
  }

  /**
   * Finds extension point types used in a given set of extensions. Returns a non-null list of extension types.
   *
   * @param extensions
   *          the extensions
   * @return the non-{@code null} list of {@link ExtensionType extension types}
   */
  private Collection<ExtensionType> findExtensionTypes(final Collection<IPluginExtension> extensions) {
    return Collections2.filter(Collections2.transform(extensions, new Function<IPluginExtension, ExtensionType>() {
      @Override
      public ExtensionType apply(final IPluginExtension from) {
        return getExtensionType(from);
      }
    }), Predicates.notNull());
  }

  /**
   * Update check catalog extensions if necessary.
   *
   * @param catalog
   *          the catalog
   * @param pluginModel
   *          the plugin model
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  public void updateExtensions(final CheckCatalog catalog, final IPluginModelBase pluginModel, final IProgressMonitor monitor) throws CoreException {
    QualifiedName catalogName = nameProvider.apply(catalog);
    Collection<IPluginExtension> catalogExtensions = findExtensions(pluginModel, catalogName, ExtensionType.ALL);

    // Update extensions as appropriate
    for (IPluginExtension extension : catalogExtensions) {
      for (ICheckExtensionHelper helper : getExtensionHelpers()) { // TODO getExtensionHelper using extension.getPoint() would make this more efficient
        helper.updateExtension(catalog, extension);
      }
    }
  }

  /**
   * Add check catalog extensions if not already existing.
   *
   * @param catalog
   *          the catalog
   * @param pluginModel
   *          the plugin model
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  public void addExtensions(final CheckCatalog catalog, final IPluginModelBase pluginModel, final IProgressMonitor monitor) throws CoreException {
    QualifiedName catalogName = nameProvider.apply(catalog);
    Collection<IPluginExtension> catalogExtensions = findExtensions(pluginModel, catalogName, ExtensionType.ALL);
    Iterable<ExtensionType> registeredExtensionTypes = findExtensionTypes(catalogExtensions);

    for (ExtensionType type : ExtensionType.values()) {
      ICheckExtensionHelper helper = getExtensionHelper(type);
      if (helper == null) {
        continue;
      }
      if (!Iterables.contains(registeredExtensionTypes, type)) {
        helper.addExtensionToPluginBase(pluginModel, catalog, type, getExtensionId(nameProvider.apply(catalog), type));
      }
    }
  }

  /**
   * Remove check catalog extensions as a catalog is removed.
   *
   * @param obj
   *          the catalog
   * @param pluginModel
   *          the plugin model
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  public void removeExtensions(final IEObjectDescription obj, final IPluginModelBase pluginModel, final IProgressMonitor monitor) throws CoreException {
    for (IPluginExtension extension : findExtensions(pluginModel, obj.getName(), ExtensionType.ALL)) {
      final ExtensionType extensionType = getExtensionType(extension);
      getExtensionHelper(extensionType).removeExtensionFromPluginBase(pluginModel, extension, obj, extensionType);
    }
  }

  /**
   * Removes the extensions. Is called when the catalog still exists (it has not been deleted) and plugin extensions which no longer correspond
   * to the model exist. This is the case if the model becomes invalid. Extensions are then removed from the plugin model.
   *
   * @param catalog
   *          the catalog
   * @param pluginModel
   *          the plugin model
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  public void removeExtensions(final CheckCatalog catalog, final IPluginModelBase pluginModel, final IProgressMonitor monitor) throws CoreException {
    QualifiedName name = nameConverter.toQualifiedName(projectUtil.getCatalogQualifiedName(catalog));
    for (IPluginExtension extension : findExtensions(pluginModel, name, ExtensionType.ALL)) {
      final ExtensionType extensionType = getExtensionType(extension);
      getExtensionHelper(extensionType).removeExtensionFromPluginBase(pluginModel, extension, catalog, extensionType);
    }
  }

  /**
   * Sorts the plug-in extensions alphabetically by extension point ID and then by extension ID (and name in case the IDs are equal or null).
   *
   * @param pluginModel
   *          the plugin model
   * @param monitor
   *          the monitor
   * @throws CoreException
   *           the core exception
   */
  public void sortAllExtensions(final IPluginModelBase pluginModel, final IProgressMonitor monitor) throws CoreException {
    Ordering<IPluginExtension> ordering = Ordering.from((a, b) -> ComparisonChain.start().compare(a.getPoint(), b.getPoint()).compare(a.getId(), b.getId(), Ordering.natural().nullsLast()).compare(a.getName(), b.getName(), Ordering.natural().nullsLast()).result());
    List<IPluginExtension> catalogExtensions = Lists.newArrayList(findAllExtensions(pluginModel));
    List<IPluginExtension> orderedExtensions = ordering.sortedCopy(catalogExtensions);
    if (catalogExtensions.equals(orderedExtensions)) {
      return;
    }
    for (int i = 0; i < orderedExtensions.size(); i++) {
      IPluginExtension expected = orderedExtensions.get(i);
      IPluginExtension actual = catalogExtensions.get(i);
      if (!Objects.equals(actual, expected)) {
        // IExtensions#swap() doesn't work; see https://bugs.eclipse.org/bugs/show_bug.cgi?id=506831
        // pluginModel.getExtensions().swap(expected, actual);
        for (int j = i; j < catalogExtensions.size(); j++) {
          pluginModel.getExtensions().remove(catalogExtensions.get(j));
        }
        for (int j = i; j < orderedExtensions.size(); j++) {
          pluginModel.getExtensions().add(orderedExtensions.get(j));
        }
        break;
      }
    }
  }

}
