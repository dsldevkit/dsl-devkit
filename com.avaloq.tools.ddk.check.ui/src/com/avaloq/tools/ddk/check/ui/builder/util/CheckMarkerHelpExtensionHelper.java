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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.TriggerKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;


/**
 * The extension point utility class for Check marker help. Intended to be used by the Check builder participant.
 */
public class CheckMarkerHelpExtensionHelper extends AbstractCheckDocumentationExtensionHelper {

  public static final String MARKERHELP_EXTENSION_POINT_ID = "org.eclipse.ui.ide.markerHelp";
  private static final String MARKERHELP_ELEMENT = "markerHelp";
  public static final String CONTEXT_ID_ATTRIBUTE_TAG = "helpContextId";
  public static final String MARKERTYPE_ATTRIBUTE_TAG = "markerType";
  private static final String CHECKTYPE_PRAEFIX = "org.eclipse.xtext.ui.check";
  private static final String ATTRIBUTE_ELEMENT = "attribute";
  private static final String ATTRIBUTE_NAME_TAG = "name";
  public static final String ATTRIBUTE_VALUE_TAG = "value";

  /**
   * Creates the marker help element.
   *
   * @param extension
   *          the extension
   * @param check
   *          the check
   * @param contextId
   *          the context id
   * @return the i plugin element
   * @throws CoreException
   *           the core exception
   */
  private IPluginElement createMarkerHelpElement(final IPluginExtension extension, final Check check, final String contextId) throws CoreException {
    IPluginElement element = extension.getModel().getFactory().createElement(extension);
    element.setName(MARKERHELP_ELEMENT);
    element.setAttribute(CONTEXT_ID_ATTRIBUTE_TAG, contextId);
    element.setAttribute(MARKERTYPE_ATTRIBUTE_TAG, getCheckType(check));
    return element;
  }

  /**
   * Creates the attribute element and adds it to the give marker help element.
   *
   * @param markerHelpElement
   *          the marker help element
   * @param issueCode
   *          the issue code
   * @return the i plugin element
   * @throws CoreException
   *           the core exception if the plugin element could not be created
   */
  private IPluginElement createAttributeElement(final IPluginElement markerHelpElement, final String issueCode) throws CoreException {
    IPluginElement attributeElement = markerHelpElement.getModel().getFactory().createElement(markerHelpElement);
    attributeElement.setName(ATTRIBUTE_ELEMENT);
    attributeElement.setAttribute(ATTRIBUTE_NAME_TAG, Issue.CODE_KEY);
    attributeElement.setAttribute(ATTRIBUTE_VALUE_TAG, issueCode);
    return attributeElement;
  }

  /**
   * Gets the issue codes for a given check.
   *
   * @param check
   *          the check
   * @return the issue codes
   */
  private Iterable<String> getIssueCodeValues(final Check check) {
    final CheckGeneratorExtensions generatorExtension = getFromServiceProvider(CheckGeneratorExtensions.class, check);
    return Iterables.transform(generatorExtension.issues(check), new Function<XIssueExpression, String>() {
      @Override
      public String apply(final XIssueExpression input) {
        String issueCode = CheckGeneratorExtensions.issueCode(input);
        return CheckGeneratorExtensions.issueCodeValue(input, issueCode);
      }
    });
  }

  /**
   * Gets all issue code values from given catalog.
   *
   * @param catalog
   *          the catalog
   * @return all issue code values
   */
  private Iterable<String> getAllIssueCodeValues(final CheckCatalog catalog) {
    final CheckGeneratorExtensions generatorExtension = getFromServiceProvider(CheckGeneratorExtensions.class, catalog);
    return Iterables.transform(generatorExtension.issues(catalog), new Function<XIssueExpression, String>() {
      @Override
      public String apply(final XIssueExpression input) {
        String issueCode = CheckGeneratorExtensions.issueCode(input);
        return CheckGeneratorExtensions.issueCodeValue(input, issueCode);
      }
    });
  }

  @Override
  protected boolean isExtensionUpdateRequired(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) {
    // TODO should check if this check is too expensive; consider rewriting contents instead
    if (!super.isExtensionUpdateRequired(catalog, extension, elements)) {
      return false;
    }

    final IQualifiedNameProvider nameProvider = getFromServiceProvider(IQualifiedNameProvider.class, catalog);
    // collect all data in the extension model: mapping<context id, pair<execution mode, issue code>>
    HashMultimap<String, Pair<String, String>> contextToValue = HashMultimap.<String, Pair<String, String>> create();
    for (IPluginElement e : elements) {
      if (e.getAttribute(CONTEXT_ID_ATTRIBUTE_TAG) != null) {
        String contextId = e.getAttribute(CONTEXT_ID_ATTRIBUTE_TAG).getValue();
        String mode = e.getAttribute(MARKERTYPE_ATTRIBUTE_TAG).getValue();
        if (isCatalogContextId(nameProvider.apply(catalog), EcoreUtil.getURI(catalog), extension, contextId)) {
          for (IPluginObject o : e.getChildren()) {
            if (o instanceof IPluginElement && ((IPluginElement) o).getAttribute(ATTRIBUTE_VALUE_TAG) != null) { // NOPMD
              IPluginAttribute attribute = ((IPluginElement) o).getAttribute(ATTRIBUTE_VALUE_TAG);
              contextToValue.put(contextId, Tuples.create(mode, attribute.getValue()));
            }
          }
        }
      }
    }

    // check that the real model and the extension model have the same number of issue codes
    Iterable<String> allExtensionIssueCodes = Iterables.transform(contextToValue.values(), new Function<Pair<String, String>, String>() {
      @Override
      public String apply(final Pair<String, String> input) {
        return input.getSecond();
      }
    });
    if (Iterables.size(allExtensionIssueCodes) != Sets.newHashSet(getAllIssueCodeValues(catalog)).size()) {
      return true;
    }

    // check all relevant data, e.g. detect change of execution mode
    for (final Check check : catalog.getAllChecks()) {
      final Iterable<String> allModelIssueCodes = getIssueCodeValues(check);
      for (final String issueCode : allModelIssueCodes) {
        final String contextId = getQualifiedContextId(extension, check);
        if (contextToValue.containsKey(contextId)) {
          Set<Pair<String, String>> modeToValues = contextToValue.get(contextId);
          try {
            Iterables.find(modeToValues, new Predicate<Pair<String, String>>() {
              @Override
              public boolean apply(final Pair<String, String> input) {
                return input.getFirst().equals(getCheckType(check)) && input.getSecond().equals(issueCode);
              }
            });
          } catch (NoSuchElementException e) {
            return true;
          }
        } else {
          return true; // context id not present in extension model
        }
      }
    }
    return false;
  }

  /**
   * Gets the plugin id of the project.
   *
   * @param extension
   *          the extension
   * @return the plugin id
   */
  private String getPluginId(final IPluginExtension extension) {
    // TODO if marker help extensions used the ID attribute, it would be possible to find the extension
    // contributor using Platform.getExtensionRegistry.findExtension(extension.getId()).getContributor().getName()
    // or similar; that would be more elegant. PluginRegistry#findModel(IProject) is expensive!
    IResource file = extension.getModel().getUnderlyingResource();
    if (file.getProject() == null) {
      return null;
    }
    IPluginModelBase pluginModel = PluginRegistry.findModel(file.getProject());
    return pluginModel != null ? pluginModel.getPluginBase().getId() : null;
  }

  /**
   * Gets the value for the helpContextId field.
   *
   * @param extension
   *          the extension
   * @param check
   *          the check
   * @return the context id
   */
  private String getQualifiedContextId(final IPluginExtension extension, final Check check) {
    final CheckGeneratorNaming generatorNaming = getFromServiceProvider(CheckGeneratorNaming.class, check);
    String contextID = generatorNaming.getContextId(check);
    final String pluginId = getPluginId(extension);
    return pluginId + "." + contextID;
  }

  /**
   * Checks if is catalog context id.
   *
   * @param catalog
   *          the catalog
   * @param uri
   *          the uri
   * @param extension
   *          the extension
   * @param contextId
   *          the context id
   * @return true, if is catalog context id
   */
  private boolean isCatalogContextId(final QualifiedName catalog, final URI uri, final IPluginExtension extension, final String contextId) {
    final CheckGeneratorNaming generatorNaming = getFromServiceProvider(CheckGeneratorNaming.class, uri);
    final String prefix = getPluginId(extension) + "." + generatorNaming.getContextIdPrefix(catalog);
    return contextId.startsWith(prefix);
  }

  /**
   * Gets the check type.
   *
   * @param check
   *          the check
   * @return the check type
   */
  private String getCheckType(final Check check) {
    TriggerKind trigger = check.getKind();
    switch (trigger) {
    case FAST:
      return CHECKTYPE_PRAEFIX + ".fast";
    case NORMAL:
      return CHECKTYPE_PRAEFIX + ".normal";
    case EXPENSIVE:
      return CHECKTYPE_PRAEFIX + ".expensive";
    default:
      return null;
    }
  }

  @Override
  protected void doUpdateExtension(final CheckCatalog catalog, final IPluginExtension extension, final Iterable<IPluginElement> elements) throws CoreException {
    final IQualifiedNameProvider nameProvider = getFromServiceProvider(IQualifiedNameProvider.class, catalog);

    // Get current marker help element context IDs for this catalog
    List<String> catalogContextIds = Lists.newArrayList();
    for (final Check check : catalog.getAllChecks()) {
      catalogContextIds.add(getQualifiedContextId(extension, check));
    }

    // Remove elements of this catalog
    for (IPluginElement e : elements) {
      if (e.getAttribute(CONTEXT_ID_ATTRIBUTE_TAG) != null) {
        String contextId = e.getAttribute(CONTEXT_ID_ATTRIBUTE_TAG).getValue();
        if (isCatalogContextId(nameProvider.apply(catalog), EcoreUtil.getURI(catalog), extension, contextId)) {
          extension.remove(e);
        }
      }
    }

    // Add new elements
    Iterable<? extends IPluginObject> updatedElements = getElements(catalog, extension);
    for (IPluginObject object : updatedElements) {
      extension.add(object);
    }
  }

  /**
   * Creates a marker help element for every check in the catalog. The new elements are added to the given extension.
   *
   * @param catalog
   *          the catalog
   * @param extension
   *          the extension to add the new element to
   * @return new marker help elements in given extension which don't exist yet, but should; returns an empty list if the extension is up to date
   * @throws CoreException
   *           the core exception
   */
  @Override
  public Iterable<IPluginElement> getElements(final CheckCatalog catalog, final IPluginExtension extension) throws CoreException {
    List<IPluginElement> result = Lists.newArrayList();

    Multimap<String, String> contextIdToAttributeValues = HashMultimap.<String, String> create();
    for (Check check : catalog.getAllChecks()) {
      final String contextId = getQualifiedContextId(extension, check);
      for (String issueCode : getIssueCodeValues(check)) {
        if (!contextIdToAttributeValues.get(contextId).contains(issueCode)) {
          contextIdToAttributeValues.put(contextId, issueCode);
          IPluginElement markerHelp = createMarkerHelpElement(extension, check, contextId);
          markerHelp.add(createAttributeElement(markerHelp, issueCode));
          result.add(markerHelp);
        }
      }
    }

    return result;
  }

  /**
   * Gets the plugin elements based on an object description. This will be called when a check catalog is
   * deleted. The build delta contains object descriptions which are passed to this class in order to delete
   * obsolete extensions.
   *
   * @param catalog
   *          the catalog
   * @param uri
   *          the uri
   * @param extension
   *          the extension to be manipulated
   * @return the plugin elements
   */
  private Iterable<IPluginElement> getElements(final QualifiedName catalog, final URI uri, final IPluginExtension extension) {
    try {
      return Iterables.filter(Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class), new Predicate<IPluginElement>() {
        @Override
        public boolean apply(final IPluginElement input) {
          final IPluginAttribute attribute = input.getAttribute(CONTEXT_ID_ATTRIBUTE_TAG);
          return attribute != null && isCatalogContextId(catalog, uri, extension, attribute.getValue());
        }
      });
    } catch (NoSuchElementException e) {
      return Collections.emptyList();
    }
  }

  @Override
  public String getExtensionPointId() {
    return MARKERHELP_EXTENSION_POINT_ID;
  }

  @Override
  public String getExtensionPointName(final CheckCatalog catalog) {
    return "Extension for Marker Help";
  }

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final CheckCatalog catalog, final ExtensionType type) throws CoreException {
    final IQualifiedNameProvider nameProvider = getFromServiceProvider(IQualifiedNameProvider.class, catalog);
    if (extension.getChildCount() == Iterables.size(getElements(nameProvider.apply(catalog), catalog.eResource().getURI(), extension))) {
      // remove whole extension, all existing marker elements belong to this catalog
      base.getPluginBase().remove(extension);
    } else {
      // only remove elements of this catalog, other catalogs have marker elements
      for (IPluginElement e : getElements(nameProvider.apply(catalog), catalog.eResource().getURI(), extension)) {
        extension.remove(e);
      }
    }
  }

  @Override
  public void removeExtensionFromPluginBase(final IPluginModelBase base, final IPluginExtension extension, final IEObjectDescription obj, final ExtensionType type) throws CoreException {
    if (extension.getChildCount() == Iterables.size(getElements(obj.getName(), obj.getEObjectURI(), extension))) {
      // remove whole extension, all existing marker elements belong to this catalog
      base.getPluginBase().remove(extension);
    } else {
      // only remove elements of this catalog, other catalogs have marker elements
      for (IPluginElement e : getElements(obj.getName(), obj.getEObjectURI(), extension)) {
        extension.remove(e);
      }
    }
  }

}
